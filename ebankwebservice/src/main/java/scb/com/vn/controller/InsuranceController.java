package scb.com.vn.controller;

import com.google.gson.Gson;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.log4j.Level;
import scb.com.vn.common.model.email.Email;
import scb.com.vn.common.model.email.SendEmailReq;
import scb.com.vn.payment.PaymentException;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;

import scb.com.vn.dbi.dto.Insurance;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.model.InsuranceRp;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.EmailUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import scb.com.vn.dbi.dto.VwCustAccountNew;

/**
 *
 * @author CORE77
 */
public class InsuranceController {

    String wsurladdress = Configuration.getProperty("ws.url.cwws.address");
    String insProductIB = Configuration.getProperty("insurance.fcubs.product");
    String insProductMB = Configuration.getProperty("insurance.fcubs.product.MB");
    String useridfcubsIB = Configuration.getProperty("fcubs.userid");
    String useridfcubsMB = Configuration.getProperty("fcubs.userid.mobile");
    static String templateBaolongfile = Configuration.getProperty("templateBaolong.htm");
    static String subjectBaolongfile = Configuration.getProperty("templateBaolong.subject");
    static String GCNBaolongfile = Configuration.getProperty("templateBaolong.GCN");
    static String pathBaolongfile = Configuration.getProperty("templateBaolong.path");

    /**
     *
     * @param xml
     * @return
     */
    public String InsurancePayment(String xml) {
        Insurance ins = new Insurance();
        try {

            InsuranceRp ins_req = unMarshallerPayment(xml);
            Helper.writeLog(InsuranceController.class, Level.INFO, xml);
            String RefFCC = "";
            String result = "";
            String soxe = "";
            String somay = "";
            String sokhung = "";

            Helper.writeLog(CardwordController.class, Level.INFO, "Call getCustAccountFcdbByAccountNo: " + ins_req.getAccIdaccount());
            //Kiem tra Balance cua giao dich
            VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(ins_req.getAccIdaccount());
            Helper.writeLog(CardwordController.class, Level.INFO, "Call end: getCustAccountFcdbByAccountNo: " + ins_req.getAccIdaccount());

            //Kiem tra neu la Thau chi thi khong kiem tra so du TK, cac TK khac deu ktra so du
            if ((!custacc.getAccountClass().substring(0, 3).equals("TCI"))) {
                if (custacc.getAcyAvlBal().compareTo(BigDecimal.valueOf(ins_req.getTotalamount())) == -1) // 0:=,1: 1>2; -1:1<2
                {
                    result = "02#";
                    return result;
                }
            }
            //INSERT DATA INSURANCE
            ins.setIdPartner(ins_req.getIdPartner());
            ins.setIdProduct(ins_req.getIdProduct());
            ins.setIdDiscount(ins_req.getIdDiscount());
            ins.setRegName(ins_req.getRegName());
            ins.setBenName(ins_req.getBenName());
            ins.setAddService(ins_req.getAddService());
            ins.setAddBen(ins_req.getAddBen());
            ins.setAddress(ins_req.getAddress());
            ins.setRegPhone(ins_req.getRegPhone());
            ins.setRegMail(ins_req.getRegMail());
            ins.setSex(ins_req.getSex());
            ins.setSoxe(ins_req.getSoxe());
            ins.setSokhung(ins_req.getSokhung());
            ins.setSomay(ins_req.getSomay());
            ins.setLoaixe(ins_req.getLoaixe());
            ins.setDob(ins_req.getDob());
            ins.setIdcard(ins_req.getIdcard());
            ins.setFeeName(ins_req.getFeeName());
            ins.setBusinessCode(ins_req.getBusinessCode());
            ins.setChannel(ins_req.getChannel());
            ins.setRegDate(ins_req.getRegDate());
            ins.setValidDate(ins_req.getValidDate());
            ins.setExpireDate(ins_req.getExpireDate());
            ins.setSotien_bhbb(ins_req.getSotien_bhbb());
            ins.setSotien_bhtn(ins_req.getSotien_bhtn());
            ins.setPhi_bhbb(ins_req.getPhi_bhbb());
            ins.setPhi_bhtn(ins_req.getPhi_bhtn());
            //ins.setAccCust(ins_req.getAccCust());
            ins.setAccCust(custacc.getCustNo());
            ins.setAccIdaccount(ins_req.getAccIdaccount());
            ins.setBcAccount(ins_req.getBcAccount());
            ins.setDiscountAamount(ins_req.getDiscountAamount());
            ins.setTotalamount(ins_req.getTotalamount());
            ins.setIdchanneluserMaker(ins_req.getIdchanneluserMaker());
            ins.setInsdateMaker(new Date());
            ins.setStatus("02");
            ins.setBranchcode(ins_req.getBranchcode());
            Gson gson = new Gson();
            Helper.writeLog(InsuranceController.class, Level.INFO, "Insurance: " + gson.toJson(ins));
            int id = Helper.getDBI().insertInsuranceInfo(ins);
            ins.setIdREG(id);
            if (id != -1) {
                Fcubs fcc = new Fcubs();//Hach toan tru tien voi Core truoc

                if (ins_req.getSoxe() != null && !ins_req.getSoxe().trim().isEmpty()) {
                    soxe = ". SO XE: " + ins_req.getSoxe();
                }
                if (ins_req.getSokhung() != null && !ins_req.getSokhung().trim().isEmpty()) {
                    somay = ". SO MAY: " + ins_req.getSomay();
                    sokhung = ". SO KHUNG: " + ins_req.getSokhung();
                }

                String userIdFcus;
                String productcode;
                if (ins.getChannel().equals("MB")) {
                    userIdFcus = useridfcubsMB;
                    productcode = insProductMB;
                } else {
                    userIdFcus = useridfcubsIB;
                    productcode = insProductIB;
                }

                // RefFCC = fcc.transferFCUBSWithTimeOut(productcode, userIdFcus, ins.getAccIdaccount().substring(0, 3), ins.getAccIdaccount(), ins.getBcAccount().substring(0, 3), 
                //        ins.getBcAccount(), BigDecimal.valueOf(ins.getTotalamount()), "TT PHI BAO HIEM. KH: " + ins.getRegName() + soxe + somay + sokhung, 30);
                RefFCC = fcc.transferFCUBSWithTimeOut(productcode, userIdFcus, CommonUtils.getBranchAccount(ins.getAccIdaccount()), ins.getAccIdaccount(), CommonUtils.getBranchAccount(ins.getBcAccount()),
                        ins.getBcAccount(), BigDecimal.valueOf(ins.getTotalamount()), "TT PHI BAO HIEM. KH: " + ins.getRegName() + soxe + somay + sokhung, 30);

                Helper.writeLog(InsuranceController.class, Level.INFO, "Hach toan FCC, Ref: " + RefFCC);
                if (RefFCC != null) {
                    Helper.writeLog(InsuranceController.class, Level.INFO, "Hach toan FCC, Ref1: " + RefFCC);
                    if (RefFCC.equals("TIMEOUT")) {
                        Helper.writeLog(InsuranceController.class, Level.INFO, "Chuyen khoan FCC Time out.");
                        result = "01#";
                    } else {
                        Helper.writeLog(InsuranceController.class, Level.INFO, "Hach toan FCC, Ref2: " + RefFCC);
                        //Giao dich thanh cong, UPDATE DB
                        ins.setRefFcubs(RefFCC);
                        ins.setIdchanneluserChecker(ins_req.getIdchanneluserChecker());
                        ins.setInsdateChecker(new Date());
                        ins.setTransdate(new Date());
                        ins.setPaydateFcubs(new Date());
                        ins.setStatus("00");

                        Helper.getDBI().updateStatusInsurance(ins);
                        Helper.writeLog(InsuranceController.class, Level.INFO, "Hach toan FCC, Ref3: " + RefFCC);
                        result = "00#" + RefFCC;
                    }
                } else {
                    //Log here
                    result = "01#";
                }
            } else {
                result = "03#";
            }
            return result;
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.controller.InsuranceController.class, ex);
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
    public static InsuranceRp unMarshallerPayment(String xml)
            throws Exception {
        if (xml == null) {
            throw new Exception("Tham so string xml marshaller insurance khong the phan tich");
        }
        InsuranceRp request;
        try {
            request = (InsuranceRp) Xml.unMarshaller(InsuranceRp.class, xml);
        } catch (Exception ex) {
            throw new Exception(ex);
        }

        if (request == null) {
            throw new Exception("Tham so string xml marshaller insurance khong the phan tich");
        }
        return request;
    }

    /**
     *
     * @param cifno
     * @param refCore
     * @return
     */
    public static String sendEmailBaoLong(String cifno, String refCore) {
        String result = "";
        try {
            List issuranceByCifList = Helper.getDBI().GetListInsuranceByCif(cifno);
            if (issuranceByCifList != null && issuranceByCifList.size() > 0) {
                for (int i = 0; i < issuranceByCifList.size(); i++) {
                    HashMap _hm = (HashMap) issuranceByCifList.get(i);
                    if (_hm.get("ref_fcubs") != null && _hm.get("ref_fcubs").toString().equals(refCore)) {
                        Insurance insurance = Helper.getDBI().GetInsuranceById(Integer.parseInt(_hm.get("iddangky").toString()));
                        List<String> to = new ArrayList<>();
                        List<String> cc = new ArrayList<>();
                        List<String> bcc = new ArrayList<>();
                        to.add(insurance.getRegMail());

                        Email email = new Email(to, cc, bcc);

                        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                        Date date = new Date();

                        String time = dateFormat.format(date);
                        String partner = "VNPAYQR";
                        String body = loadBodyEmail(insurance);

                        SendEmailReq request = new SendEmailReq();
                        request.setTitle(subjectBaolongfile);
                        request.setBody(body);
                        request.setEmail(email);
                        request.setPartner(partner);
                        request.setTime(time);
                        request.setFileAttach(genGiayChungNhan(insurance));

                        IController control = new ControllerImpl();
                        result = control.sendEmail(Xml.Marshaller(request));
                        break;
                    }
                }
            }
            if (1 > 0) {
                result = "";

            } else {
                result = "01#";
            }
            return result;
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.controller.InsuranceController.class, ex);
            return null;
        }
    }

    /**
     *
     * @param insurance
     * @return
     */
    public static String loadBodyEmail(Insurance insurance) {
        try {
            Map<String, String> input = new HashMap<String, String>();
            input.put("[iddangky]", String.valueOf(insurance.getIdREG()));
            input.put("[reg_name]", insurance.getRegName());
            input.put("[address]", insurance.getAddress());
            input.put("[email]", insurance.getRegMail());
            input.put("[reg_phone]", insurance.getRegPhone());
            input.put("[ben_name]", insurance.getBenName());
            input.put("[ben_address]", insurance.getAddBen());
            input.put("[phamvibaohiem]", insurance.getLoaixe());

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Locale localeVN = new Locale("vi", "VN");
            NumberFormat vn = NumberFormat.getInstance(localeVN);

            input.put("[total_amount]", vn.format(insurance.getTotalamount()));

            if (insurance.getValidDate() != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(insurance.getValidDate());
                input.put("[date_from]", format.format(insurance.getValidDate()));
                calendar.add(Calendar.YEAR, 1);
                input.put("[date_to]", format.format(calendar.getTime()));
            }

            //HTML mail content
            String htmlText = EmailUtils.readEmailFromHtml(templateBaolongfile, input);
            return htmlText;
        } catch (Exception ex) {
            throw new PaymentException(ex);
        }
    }

    /**
     *
     * @param insurance
     * @return
     */
    public static String genGiayChungNhan(Insurance insurance) {
        try {

            //Loading an existing document
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Locale localeVN = new Locale("vi", "VN");
            NumberFormat vn = NumberFormat.getInstance(localeVN);
            String filepathname = pathBaolongfile.concat("GCNBH.Nha tu nhan.").concat(String.valueOf(insurance.getIdREG()).concat(".pdf"));
            File file = new File(GCNBaolongfile);
            PDDocument document = PDDocument.load(file);

            //Retrieving the pages of the document 
            PDPage page = document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true);

            //Begin the Content stream 
            contentStream.beginText();

            //Setting the font to the Content stream  
            contentStream.setFont(PDType1Font.TIMES_BOLD, 11);

            //Setting the position for the line 
            contentStream.newLineAtOffset(259, 689);
            String text = String.valueOf(insurance.getIdREG());

            //Adding text in the form of string 
            contentStream.showText(text);

            contentStream.newLineAtOffset(38, -13);
            text = format.format(new Date());
            //Adding text in the form of string 
            contentStream.showText(text);

            contentStream.newLineAtOffset(-82, -53);
            text = insurance.getBenName();
            //Adding text in the form of string 
            contentStream.showText(text);

            contentStream.newLineAtOffset(0, -19);
            text = insurance.getAddBen();
            //Adding text in the form of string 
            contentStream.showText(text);

            //hieu luc tu ngay
            contentStream.newLineAtOffset(133, -93);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(insurance.getValidDate());

            text = format.format(insurance.getValidDate());
            //Adding text in the form of string 
            contentStream.showText(text);

            // hieu luc den ngay
            contentStream.newLineAtOffset(159, 0);
            calendar.add(Calendar.YEAR, 1);
            text = format.format(calendar.getTime());
            //Adding text in the form of string 
            contentStream.showText(text);

            //so tien bao hiem
            contentStream.newLineAtOffset(-294, -138);
            text = vn.format(Integer.parseInt(insurance.getFeeName()));
            //Adding text in the form of string 
            contentStream.showText(text);

            //phi bao hiem
            contentStream.newLineAtOffset(0, -18);
            text = vn.format(insurance.getTotalamount());
            //Adding text in the form of string 
            contentStream.showText(text);

            if (insurance.getLoaixe().contains("cơ bản")) {
                contentStream.newLineAtOffset(3, 137);
            } else {
                contentStream.newLineAtOffset(2, 119);
            }

            text = "x";
            //Adding text in the form of string 
            contentStream.showText(text);

            //Ending the content stream
            contentStream.endText();

            //Closing the content stream
            contentStream.close();

            //Saving the document
            document.save(new File(filepathname));

            //Closing the document
            document.close();
            return filepathname;
        } catch (Exception ex) {
            throw new PaymentException(ex);
        }
    }
}
