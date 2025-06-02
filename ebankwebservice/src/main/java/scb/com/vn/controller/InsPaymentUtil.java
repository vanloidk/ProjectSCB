package scb.com.vn.controller;

import java.math.BigDecimal;
import org.apache.log4j.Level;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;

import scb.com.vn.utility.CommonUtils;

/**
 *
 * @author CORE77
 */
public class InsPaymentUtil {

    String wsurladdress = Configuration.getProperty("ws.url.cwws.address");
    String insProductCounter = Configuration.getProperty("insurance.fcubs.product.manulife.COUNTER");
    String insProductEB = Configuration.getProperty("insurance.fcubs.product.manulife.EB");
    String insProductMB = Configuration.getProperty("insurance.fcubs.product.manulife.MB");

    String useridfcubs = Configuration.getProperty("fcubs.userid");
    String useridMB = Configuration.getProperty("fcubs.userid.mobile");

    /**
     *
     * @param CusAccount
     * @param PartnerAcc
     * @param totalamount
     * @param pol_num
     * @param idchannel
     * @return
     */
    public String PaymentInsurance(String CusAccount, String PartnerAcc, BigDecimal totalamount, String pol_num, String idchannel) {
        try {
            String RefFCC = "";
            String result = "";
            String usermake;
            String productcode;
            String insProduct;
            Fcubs fcc = new Fcubs();//Hach toan tru tien voi Core truoc
            String transTyp = "THANH TOAN PHI BAO HIEM " + pol_num;
            if (idchannel.equals("03")) {
                usermake = useridMB;
                insProduct = insProductMB;
            } else {
                usermake = useridfcubs;
                if (idchannel.equals("11")) {
                    insProduct = insProductCounter;
                } else {
                    insProduct = insProductEB;
                }
            }
            // RefFCC = fcc.transferFCUBSWithTimeOut(insProduct, usermake, CusAccount.substring(0, 3), CusAccount, PartnerAcc.substring(0, 3), 
            //        PartnerAcc, totalamount, transTyp, 30);
            RefFCC = fcc.transferFCUBSWithTimeOut(insProduct, usermake, CommonUtils.getBranchAccount(CusAccount), CusAccount, CommonUtils.getBranchAccount(PartnerAcc),
                    PartnerAcc, totalamount, transTyp, 30);
            Helper.writeLog(InsPaymentUtil.class, Level.INFO, "Hach toan FCC, Ref: " + RefFCC);
            if (RefFCC != null) {
                if (RefFCC.equals("TIMEOUT")) {
                    Helper.writeLog(InsPaymentUtil.class, Level.INFO, "Chuyen khoan FCC Time out.");
                    result = "02#";
                } else {
                    result = "00#" + RefFCC;
                }
            } else {
                result = "01#";
            }

            Helper.writeLog(InsPaymentUtil.class, Level.INFO, "PaymentInsurance out." + result);
            return result;
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.controller.InsPaymentUtil.class, ex);
            return null;
        }
    }

    /**
     *
     * @param CusAccount
     * @param totalamount
     * @param user_maker
     * @param user_checker
     * @param pol_num
     * @param idchannel
     * @return
     */
    public String PaymentInsuranceCash(String CusAccount, BigDecimal totalamount, String user_maker, String user_checker, String pol_num, String idchannel) {
        try {
            String RefFCC = "";
            String result = "";
            String responseCore = "";
            String SO_CT = "";
            String transTyp = "THANH TOAN PHI BAO HIEM " + pol_num;
            responseCore = Helper.getDBI().transferFCUBSCash(CusAccount, totalamount, transTyp, user_maker, user_checker);
            if (responseCore != null) {
                Helper.writeLog(InsPaymentUtil.class, Level.INFO, "Hach toan FCC, transferFCUBSCash: " + responseCore);
                String[] resp = responseCore.split("#");
                RefFCC = resp[0];
                SO_CT = resp[1];
                Helper.writeLog(CardwordController.class, Level.INFO, "response core tra ve cash: " + SO_CT + "#" + RefFCC);
            }
            Helper.writeLog(InsPaymentUtil.class, Level.INFO, "Hach toan FCC, Ref: " + RefFCC);

            if (RefFCC != null && !RefFCC.equalsIgnoreCase("null")) {
                if (RefFCC.equals("TIMEOUT")) {
                    Helper.writeLog(InsPaymentUtil.class, Level.INFO, "Chuyen khoan FCC Time out.");
                    result = "02#";
                } else {
                    result = "00#" + RefFCC + "#" + SO_CT;
                }
            } else {
                result = "01#";
            }
            return result;
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.controller.InsPaymentUtil.class, ex);
            return null;
        }
    }
    public String PaymentInsuranceCashv2(String CusAccount, BigDecimal totalamount, String user_maker, String user_checker, String pol_num, String idchannel, String noiCap, String ngayCap, String sodt, String nguoiGiaoDich, String diaChi, String cmnd) {
        try {
            String RefFCC = "";
            String result = "";
            String responseCore = "";
            String SO_CT = "";
            String transTyp = "THANH TOAN PHI BAO HIEM " + pol_num;
            responseCore = Helper.getDBI().transferFCUBSCashv2(CusAccount, totalamount, transTyp, user_maker, user_checker, noiCap, ngayCap, sodt, nguoiGiaoDich, diaChi,cmnd);
            if (responseCore != null) {
                Helper.writeLog(InsPaymentUtil.class, Level.INFO, "Hach toan FCC, transferFCUBSCash: " + responseCore);
                String[] resp = responseCore.split("#");
                RefFCC = resp[0];
                SO_CT = resp[1];
                Helper.writeLog(CardwordController.class, Level.INFO, "response core tra ve cash: " + SO_CT + "#" + RefFCC);
            }
            Helper.writeLog(InsPaymentUtil.class, Level.INFO, "Hach toan FCC, Ref: " + RefFCC);

            if (RefFCC != null && !RefFCC.equalsIgnoreCase("null")) {
                if (RefFCC.equals("TIMEOUT")) {
                    Helper.writeLog(InsPaymentUtil.class, Level.INFO, "Chuyen khoan FCC Time out.");
                    result = "02#";
                } else {
                    result = "00#" + RefFCC + "#" + SO_CT;
                }
            } else {
                result = "01#";
            }
            return result;
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.controller.InsPaymentUtil.class, ex);
            return null;
        }
    }
}
