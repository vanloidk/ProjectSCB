package scb.com.vn.controller;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.log4j.Level;
import scb.com.vn.dbi.dto.InsurancePayment;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.dbi.dto.VwCustAccountNew;
import scb.com.vn.model.InsPaymentDetailRp;
import scb.com.vn.model.InsPaymentRp;
import scb.com.vn.payment.PaymentException;
import scb.com.vn.utility.Helper;

import scb.com.vn.ultility.Xml;

/**
 *
 * @author CORE77
 */
public class InsPayment {

    /**
     *
     * @param xml
     * @return
     */
    public String PayIns(String xml) {
        InsurancePayment ins = new InsurancePayment();
        InsPaymentUtil ipu = new InsPaymentUtil();
        try {
            Helper.writeLog(InsPayment.class, Level.INFO, xml);
            InsPaymentRp ins_req = unMarshallerPayment(xml);
            String RefFCC = "";
            String result = "";
            int idpayment = getIdSeqByName("SQ_IDPAYMENT_INSURANCE");
            long totalamount = ins_req.getTotalamount();
            Helper.writeLog(CardwordController.class, Level.INFO, "Call getCustAccountFcdbByAccountNo: " + ins_req.getAccIdaccount());
            //Kiem tra Balance cua giao dich
            VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(ins_req.getAccIdaccount());
            Helper.writeLog(CardwordController.class, Level.INFO, "Call end: getCustAccountFcdbByAccountNo: " + ins_req.getAccIdaccount());
            //Kiem tra neu la Thau chi thi khong kiem tra so du TK, cac TK khac deu ktra so du
            if ((!custacc.getAccountClass().substring(0, 3).equals("TCI"))) {
                if (custacc.getAcyAvlBal().compareTo(BigDecimal.valueOf(totalamount)) == -1) // 0:=,1: 1>2; -1:1<2
                {
                    result = "02#";
                    return result;
                }
            }
            //INSERT DATA INSURANCE
            ins.setIdpartner(ins_req.getIdPartner());
            ins.setOwnerid(ins_req.getOwnerID());
            ins.setOwnername(ins_req.getOwnerName());
            ins.setPolnum(ins_req.getPol_num());
            ins.setPretyp(ins_req.getPrem_typ());
            ins.setDataxml(xml);
            ins.setIduserMaker(ins_req.getIduserMaker());
            ins.setIdchanneluserMaker(ins_req.getIdchanneluserMaker());
            ins.setInsdateMaker(new Date());
            ins.setIdchannel(ins_req.getIdchannel());
            ins.setUsertype("1");
            ins.setPaymentmethod(ins_req.getPaymentmethod());
            ins.setAcccust(custacc.getCustNo());
            ins.setAccIdaccount(ins_req.getAccIdaccount());
            ins.setAccHoldername(custacc.getFullName());
            ins.setAccAddress(custacc.getAddress());
            ins.setInsdate(new Date());
            ins.setIsapproved("W");
            ins.setBranchcode(ins_req.getBranchcode());
            ins.setArea(ins_req.getArea());
            ins.setIdpayment(idpayment);
            for (int i = 0; i < ins_req.getInspaydetaillist().size(); i++) {
                InsPaymentDetailRp insdetail = ins_req.getInspaydetaillist().get(i);
                ins.setDuedt(insdetail.getTerm());
                ins.setTotalamount(insdetail.getAmount());
                ins.setCol_code(insdetail.getCol_code());
                ins.setChecksum(insdetail.getChecksum());
                ins.setRef_num(insdetail.getRef_num());
                int id = Helper.getDBI().insertPayIns(ins);
                if (id <= 0) {
                    result = "03#";
                    return result;
                }
            }
            result = ipu.PaymentInsurance(ins_req.getAccIdaccount(), ins_req.getAccountTo(), BigDecimal.valueOf(totalamount), ins_req.getPol_num(), ins_req.getIdchannel());
            String[] rsp = result.split("#");
            RefFCC = rsp[1];
            if (rsp[0].equals("00")) {
                //Giao dich thanh cong, UPDATE DB
                ins.setRefFcubs(RefFCC);
                ins.setIduserChecker(ins_req.getIduserChecker());
                ins.setIdchanneluserChecker(ins_req.getIdchanneluserChecker());
                ins.setIsapproved("A");
                ins.setStatus("D");

                Helper.getDBI().updateStatusPayIns(ins);
                for (int i = 0; i < ins_req.getInspaydetaillist().size(); i++) {
                    InsPaymentDetailRp insdetail = ins_req.getInspaydetaillist().get(i);
                    Helper.writeLogError(scb.com.vn.controller.InsPayment.class, "updateStatusBCBill:" + insdetail.getId() + " - " + insdetail.getAmount());
                    Helper.getDBI().updateStatusBCBill(insdetail.getId(), insdetail.getAmount());
                }
            }
            return result;
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.controller.InsPayment.class, ex);
            return null;
        }
    }

    private int getIdSeqByName(String seqname) {
        try {
            return Helper.getDBI().getIdSeqByName(seqname);
        } catch (Exception ex) {
            throw new PaymentException(ex);
        }
    }

    /**
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public static InsPaymentRp unMarshallerPayment(String xml) throws Exception {
        if (xml == null) {
            throw new Exception("Tham so string xml marshaller insurance khong the phan tich");
        }
        InsPaymentRp request;
        try {
            request = (InsPaymentRp) Xml.unMarshaller(InsPaymentRp.class, xml);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        if (request == null) {
            throw new Exception("Tham so string xml marshaller insurance khong the phan tich");
        }
        return request;
    }
}
