/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Level;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import scb.com.vn.common.model.email.Email;
import scb.com.vn.common.model.email.SendEmailReq;
import scb.com.vn.common.model.email.SendEmailRes;
import scb.com.vn.common.model.info.SmsCustRegisInfo;
import scb.com.vn.dbi.dto.CardType;
import scb.com.vn.dbi.dto.EbIssuecard;
import scb.com.vn.dbi.dto.EtsMstbranch;
import scb.com.vn.dbi.dto.GwEmailTd;
import scb.com.vn.dbi.dto.ProcedureCallDto;
import scb.com.vn.dbi.dto.VwMbCustomer;
import scb.com.vn.enumUtils.EmailResultEnum;
import scb.com.vn.message.Message.PaymentResultEnum;
import scb.com.vn.mobile.model.*;
import scb.com.vn.model.CasaRegisterRq;
import scb.com.vn.model.GuaranteeRegisterRq;
import scb.com.vn.model.InterestRateDetail;
import scb.com.vn.model.InterestRateRp;
import scb.com.vn.model.InterestRateRq;
import scb.com.vn.model.MobileResponse;
import scb.com.vn.model.TxnCommitRp;
import scb.com.vn.ultility.Xml;
import static scb.com.vn.utility.CommonUtils.createQrCode;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.EmailUtils;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.MessageMB;
import scb.com.vn.utility.VNCharacterUtils;
import vn.com.scb.bian.AccountInfoType;
import vn.com.scb.bian.CIFDataType;
import vn.com.scb.bian.CreateRewardNumber_in_Type;
import vn.com.scb.bian.CreateRewardNumber_out_Type;
import vn.com.scb.bian.CustomerInfoType;
import vn.com.scb.bian.RetrieveCustomerRefDataMgmt_out_Type;
import vn.com.scb.bian.RetrieveDepositAccountTD_out_Type;
import vn.com.scb.bian.RewardNumInfoType;
import vn.com.scb.bian.RewardNumListType;
import vn.com.scb.bian.SelectRewardNumberFromAcctNo_in_Type;
import vn.com.scb.bian.SelectRewardNumberFromAcctNo_out_Type;
import vn.com.scb.bian.UpdateCIFInfo_out_Type;
import vn.com.scb.bian.service.DvkhFunctionServices;
import vn.com.scb.bian.service.IIBCustomerService;
import vn.com.scb.bian.service.IIBDepositAccountService;
import vn.com.scb.bian.utility.IIBConstants;
import vn.com.scb.bian.utility.IIBUtils;

/**
 *
 * @author kimncm
 */
public class MobileControlExt {

    static String bodyEmaiTDVN = Configuration.getProperty("templateEmailTD.htmVN");
    static String bodyEmaiTDEN = Configuration.getProperty("templateEmailTD.htmEN");
    static String subjectEmaiTDVN = Configuration.getProperty("templateEmailTD.subjectVN");
    static String subjectEmaiTDEN = Configuration.getProperty("templateEmailTD.subjectEN");
    static String templateEmaifile = Configuration.getProperty("templateEmailTD.pdf");
    static String templateEmaiLuckyfile = Configuration.getProperty("templateEmaiLuckyfile.pdf");
    static String pathEmaiTDfile = Configuration.getProperty("templateEmailTD.path");
    static String fontOpenSans = Configuration.getProperty("templateEmailTD.fontOpenSans");
    final String IIB_CHANNEL_MOBILE = "MOBILE";
    final String SlabAmountTD = "1499999999";
    final String SlabAmountTD_Next = "3999999999";
    final String SlabAmountTD_DN = "3000000001";
    final String SlabAmountTD_DN_2 = "99999999999990";
    static String luckStartDate = Configuration.getProperty("luckTD.StartDate");
    static String luckEndDate = Configuration.getProperty("luckTD.EndDate");
    static String luckProgramCode = Configuration.getProperty("luckTD.programCode");
    static DateFormat formatDatec = new SimpleDateFormat("yyyy-MM-dd");
    static Date dateLuck ;
    static Date dateEndLuck;
    
   
    static 
    {
        try
        {
            dateLuck = formatDatec.parse(luckStartDate);
            dateEndLuck = formatDatec.parse(luckEndDate);           
        }
        catch (ParseException e)
        {
        }
    }


    /**
     * Gá»­i email má»Ÿ má»›i tiáº¿t kiá»‡m: for 4 steps 1: insert DB gwEmailTd
     * 2:get IIB customer and accountino 3:send email 4:check upate email
     *
     * @param xml
     * @return
     */
    public String sendEmailOpenTD(String xml) {
        try {
            Helper.writeLog(MobileControlExt.class, Level.ALL, "sendEmailOpenTD start");
            SendEmailOpenTDRq req = (SendEmailOpenTDRq) Xml.unMarshaller(SendEmailOpenTDRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            BeanUtils.copyProperties(response, req);
            if (req.getLanguage() == null || req.getLanguage().isEmpty()) {
                req.setLanguage("VN");
            } else {
                req.setLanguage(req.getLanguage().toUpperCase());
            }

            IIBDepositAccountService iibAccService = new IIBDepositAccountService();
            RetrieveDepositAccountTD_out_Type retrieveDepositAccountTD_out_Type = iibAccService.retrieveDepositAccountTDSimple(Configuration.getIIBContext(), req.getAccountNo(), IIB_CHANNEL_MOBILE);
            if (!retrieveDepositAccountTD_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.NO_EXISTS_ACCOUNT));
            }
            AccountInfoType accountdetailIIB = retrieveDepositAccountTD_out_Type.getAccountInfo();
            if (req.getCifNo() == null) {
                req.setCifNo(accountdetailIIB.getCIFInfo().getCIFNum());
            }
            //lay thong tin in so TK
            IIBCustomerService iibCustomerService = new IIBCustomerService();
            RetrieveCustomerRefDataMgmt_out_Type retrieveCustomerRefDataMgmt = iibCustomerService.retrieveCustomerRefDataMgmtSimple(Configuration.getIIBContext(), req.getCifNo(), IIB_CHANNEL_MOBILE);
            if (!retrieveCustomerRefDataMgmt.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.NO_EXISTS_ACCOUNT));
            }
            CustomerInfoType customerinfo = retrieveCustomerRefDataMgmt.getCustomerInfo();

            GwEmailTd gwEmailTd = new GwEmailTd();
            gwEmailTd.setCustAcNo(req.getAccountNo());
            List<GwEmailTd> listAccount = Helper.getDBI().getListGwEmailTd(gwEmailTd, null, null);
            gwEmailTd = new GwEmailTd();
            gwEmailTd.setCustomerNo(req.getCifNo());
            gwEmailTd.setCustAcNo(req.getAccountNo());
            gwEmailTd.setEmail(req.getEmailCust());
            gwEmailTd.setSrcchannel(req.getSrcChannel());
            gwEmailTd.setCreatedDate(new Date());
            gwEmailTd.setStatus("INIT");
            gwEmailTd.setTdAmount(accountdetailIIB.getAccountBalance().longValue());
           if (accountdetailIIB.getAccountInterestRate()!=null){              
                gwEmailTd.setInterest(Float.valueOf(accountdetailIIB.getAccountInterestRate()));
            } else {
                gwEmailTd.setInterest(0);
            }                
 																						
            //So cu in lai
            String serialNO = "";
            if (listAccount != null && listAccount.size() > 0) {
                 if (listAccount.get(0).getSerialno()==null){
                    serialNO = req.getAccountNo();
                 } else{
                    serialNO = listAccount.get(0).getSerialno();
                 }
            }

            BigDecimal idgwemailTD = Helper.getDBI().insertGwEmailTd(gwEmailTd);

            if (idgwemailTD.equals(BigDecimal.ZERO)) {
                Helper.writeLogError(this.getClass(), "insert idgwemailTD return " + BigDecimal.ZERO);
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.NOT_SENDEMAIL_OPENTD));
            }
            if (serialNO.isEmpty()) {
                /*
                serialNO = idgwemailTD.toString();
                while (serialNO.length() < 7) {
                    serialNO = "0".concat(serialNO);
                }*/
                serialNO = req.getAccountNo();
                //Helper.writeLogError(this.getClass(), "serialNO:" + serialNO);
            }
            gwEmailTd.setId(idgwemailTD);
            gwEmailTd.setSerialno(serialNO);
            req.setSerialNo(serialNO);

            //send email
            String sendEmailTDContro = sendEmailTD(req, customerinfo, accountdetailIIB);
            if (sendEmailTDContro.equalsIgnoreCase(EmailResultEnum.SUCCESS.getText())) {
                gwEmailTd.setStatus("COM");
                Helper.getDBI().updateGwEmailTd(gwEmailTd);
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
            } else {
                gwEmailTd.setStatus("ERROR");
                Helper.getDBI().updateGwEmailTd(gwEmailTd);
                Helper.writeLogError(this.getClass(), "sendEmailTD " + sendEmailTDContro);
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.NOT_SENDEMAIL_OPENTD));
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            throw null;
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String UpdateCustomerInfo(String xml) {
        try {
            UpdateCustomerInfoRq req = (UpdateCustomerInfoRq) Xml.unMarshaller(UpdateCustomerInfoRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();

            IIBCustomerService iibCustomerService = new IIBCustomerService();
            CIFDataType CIFInfo = new CIFDataType();
            CIFInfo.setCIFNum(req.getCifNo());
            CIFInfo.setFieldName("EMAIL");
            CIFInfo.setFieldValue(req.getEmailCust());
            UpdateCIFInfo_out_Type updateCIFInfo_out = iibCustomerService.updateCIFInfo(Configuration.getIIBContext(), CIFInfo, IIB_CHANNEL_MOBILE);
            if (!updateCIFInfo_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                Helper.writeLogError(this.getClass(), "updateCIFInfo_out return " + updateCIFInfo_out.getTransactionInfo().getTransactionReturn());
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.NOT_UPDATE_EMAIL));
            }
            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));

        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            throw null;
        }
    }

    /**
     *
     * @param req
     * @param customerinfo
     * @param accountdetailIIB
     * @return
     */
    public String sendEmailTD(SendEmailOpenTDRq req, CustomerInfoType customerinfo, AccountInfoType accountdetailIIB) {
        Helper.writeLog(MobileControlExt.class, Level.ALL, "sendEmailTD start");
        String result = "";
        try {
            List<String> to = new ArrayList<>();
            List<String> cc = new ArrayList<>();
            List<String> bcc = new ArrayList<>();
            to.add(req.getEmailCust());

            Email email = new Email(to, cc, bcc);

            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();

            String time = dateFormat.format(date);
            String partner = "VNPAYQR";
            String body = loadBodyEmail(req, accountdetailIIB, customerinfo);

            SendEmailReq request = new SendEmailReq();
            if (req.getLanguage().equals("VN")) {
                request.setTitle(subjectEmaiTDVN);
            } else {
                request.setTitle(subjectEmaiTDEN);
            }

            request.setBody(body);
            request.setEmail(email);
            request.setPartner(partner);
            request.setTime(time);
            request.setFileAttach(genPDFTD(req, customerinfo, accountdetailIIB));

            IController control = new ControllerImpl();
            result = control.sendEmail(Xml.Marshaller(request));

            SendEmailRes res = (SendEmailRes) Xml.unMarshaller(SendEmailRes.class, result);
            if (res.getStatus().equals(EmailResultEnum.SUCCESS.getText())) {
                return EmailResultEnum.SUCCESS.getText();
            } else {
                return EmailResultEnum.FAILTOSENDEMAIL.getText();
            }

        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.controller.MobileControlExt.class, ex);
            throw null;
        }
    }

    /**
     *
     * @param req
     * @param accountdetailIIB
     * @param customerinfo
     * @return
     */
    public static String loadBodyEmail(SendEmailOpenTDRq req, AccountInfoType accountdetailIIB, CustomerInfoType customerinfo) {
        try {
            Helper.writeLog(MobileControlExt.class, Level.ALL, "loadBodyEmail start");
            Map<String, String> input = new HashMap<String, String>();
            input.put("[customername]", customerinfo.getFullname_vn());
            input.put("[custacno]", accountdetailIIB.getAccountNum());

            //HTML mail content            
            String htmlText;
            if (req.getLanguage().equals("VN")) {
                htmlText = EmailUtils.readEmailFromHtml(bodyEmaiTDVN, input);
            } else {
                htmlText = EmailUtils.readEmailFromHtml(bodyEmaiTDEN, input);
            }

            return htmlText;
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.controller.MobileControlExt.class, ex);
            throw null;
        }
    }

    /**
     *
     * @param req
     * @param customerinfo
     * @param accountdetailIIB
     * @return
     */
    public String genPDFTD(SendEmailOpenTDRq req, CustomerInfoType customerinfo, AccountInfoType accountdetailIIB) {
        try {
            //load branch name
            EtsMstbranch mstBranch = Helper.getDBI().getEtsMstBranch(accountdetailIIB.getAccountOpenBrandCode());
            if (mstBranch == null) {
                Helper.writeLogError(scb.com.vn.controller.MobileControlExt.class, "genPDFTD - mstBranch null:" + accountdetailIIB.getAccountOpenBrandCode());
            }

            //ktra có cấp số dự thưởng            
            Boolean hasLuckNumber = false;
            Boolean oldTD = false;
            SelectRewardNumberFromAcctNo_out_Type selectRewardNumber_out = new SelectRewardNumberFromAcctNo_out_Type(); ;
         
            //Loading an existing document                        
            Locale localeVN = new Locale("vi", "VN");
            NumberFormat vn = NumberFormat.getInstance(localeVN);

            String filepathname = pathEmaiTDfile.concat(accountdetailIIB.getAccountNum().concat(".pdf"));
            DateFormat formatDateSrc = new SimpleDateFormat("yyyy-MM-dd");

            Date dateOpen = formatDateSrc.parse(accountdetailIIB.getAccountOpenDate());
            /*
            if (dateLuck == null) {
                //DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                dateLuck = formatDateSrc.parse(luckStartDate);
            }
            if (dateEndLuck == null) {
                //DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                dateEndLuck = formatDateSrc.parse(luckEndDate);
            }
            */
            File file;                 
            if (dateOpen.compareTo(dateLuck) >= 0 && dateEndLuck.compareTo(dateOpen) >= 0) {
                file = new File(templateEmaiLuckyfile);
                selectRewardNumber_out = getLuckNumber("", accountdetailIIB.getAccountNum());
                if (selectRewardNumber_out != null) {
                    hasLuckNumber = true;
                }
            } else {
                file = new File(templateEmaifile);
                oldTD = true;
            }

            PDDocument document = PDDocument.load(file);

            BufferedImage image = createQrCode(accountdetailIIB.getAccountNum());
            //PDImageXObject img = new PDImageXObject(document, new FileInputStream(someImage));
            PDImageXObject img = JPEGFactory.createFromImage(document, image);
            //new PDJpeg(document, image);;

            //Retrieving the pages of the document 
            PDPage page = document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

            PDFont unicodeFont = PDType0Font.load(document, new File(fontOpenSans));
            contentStream.setFont(unicodeFont, 13);
            //contentStream.setFont(PDType1Font.HELVETICA, 12);

            contentStream.setNonStrokingColor(Color.BLACK);

            contentStream.drawImage(img, 491, 758, 70, 70);

            //Begin the Content stream 
            contentStream.beginText();

            //Setting the font to the Content stream  
            //contentStream.set
            //Setting the position for the line 
            contentStream.setNonStrokingColor(Color.WHITE);
            contentStream.newLineAtOffset(317, 657);
            String text = String.valueOf(req.getSerialNo());

            //Adding text in the form of string 
            contentStream.showText(text);
            contentStream.setFont(unicodeFont, 10);
            contentStream.setNonStrokingColor(Color.BLACK);
            contentStream.newLineAtOffset(-186, -77);
            text = customerinfo.getFullname_vn();
            //Adding text in the form of string 
            contentStream.showText(text);

            contentStream.newLineAtOffset(30, -21);
            text = customerinfo.getIDInfo().getIDNum();
            //Adding text in the form of string 
            contentStream.showText(text);

            contentStream.newLineAtOffset(290, 0);
            text = changeFormatDatePdf(customerinfo.getIDInfo().getIDIssuedDate());
            //Adding text in the form of string 
            contentStream.showText(text);

            contentStream.newLineAtOffset(-328, -20);
            text = customerinfo.getAddress().getAddress_vn();
            //Adding text in the form of string 
            contentStream.showText(text);

            //so tai khoan
            contentStream.newLineAtOffset(35, -61);
            text = accountdetailIIB.getAccountNum();
            contentStream.showText(text);

            //lai suat
            contentStream.newLineAtOffset(310, 0);
            text = accountdetailIIB.getAccountInterestRate().concat("%/năm");
            contentStream.showText(text);

            //so tien
            contentStream.newLineAtOffset(-340, -20);
            //text = vn.format(Integer.parseInt(accountdetailIIB.getAccountBalanceAvailable()));
            text = vn.format(accountdetailIIB.getAccountBalanceAvailable());
            contentStream.showText(text);

            //loai tien
            contentStream.newLineAtOffset(360, 0);
            text = accountdetailIIB.getAccountCurrency();
            contentStream.showText(text);

            //hieu luc tu ngay
            contentStream.newLineAtOffset(-340, -21);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());

            text = changeFormatDatePdf(accountdetailIIB.getAccountOpenDate());
            //Adding text in the form of string 
            contentStream.showText(text);

            // hieu luc den ngay
            contentStream.newLineAtOffset(255, 0);
            text = changeFormatDatePdf(accountdetailIIB.getAccountMaturityDate());
            //Adding text in the form of string 
            contentStream.showText(text);

            //Ten san pham
            contentStream.newLineAtOffset(-230, -21);
            text = accountdetailIIB.getAccountClassName();
            //Adding text in the form of string 
            contentStream.showText(text);

            //Ky han gui 
            contentStream.newLineAtOffset(-47, -21);
            text = accountdetailIIB.getAccountTerm();
            //Adding text in the form of string 
            contentStream.showText(text);

            //Phuong thuc tra lai 
            contentStream.newLineAtOffset(105, -20);
            text = accountdetailIIB.getAccountInterestReceivableType();
            contentStream.showText(text);

            //chi dinh khi den han 
            contentStream.newLineAtOffset(0, -21);
            text = accountdetailIIB.getAccountDesignate();
            switch (text) {
                case "TAI KY GOC VA LAI":
                    text = "Tái ký gốc và lãi";
                    break;
                case "TAI KY GOC":
                    text = "Tái ký gốc";
                    break;
                default:
                    text = "Tự động tất toán gốc và lãi";
            }
            contentStream.showText(text);
            contentStream.setFont(unicodeFont, 12);
            //branchname
            contentStream.newLineAtOffset(174, -45);
            text = mstBranch.getName();
            contentStream.showText(text);
            contentStream.setFont(unicodeFont, 10);
            if (hasLuckNumber) {
                List<RewardNumListType> rewardNumList = selectRewardNumber_out.getRewardNumList();

                int total = 0;
                String fromnumer = null;
                String tonumer = null;
                int count = rewardNumList.size();
                String[] arrayNumber = new String[count];
                int index = 0;
                for (vn.com.scb.bian.RewardNumListType rewardNumListLoop : rewardNumList) {
                    fromnumer = rewardNumListLoop.getRewardNumInfo().getFromNum();
                    tonumer = rewardNumListLoop.getRewardNumInfo().getToNum();
                    total += Integer.parseInt(tonumer.trim()) - Integer.parseInt(fromnumer.trim()) + 1;
                    arrayNumber[index] = "Từ số/From number: " + fromnumer + "   Đến số/To number: " + tonumer;
                    index++;
                    if (index != count) {
                        arrayNumber[index - 1] = arrayNumber[index - 1].concat(",");
                    }
                }
                contentStream.newLineAtOffset(-179, -49);
                text = String.valueOf(total);
                contentStream.showText(text);

                //item dau tien
                contentStream.newLineAtOffset(-30, -16);
                contentStream.showText(arrayNumber[0]);
                //loop neu co cap so du thuong khac
                for (int i = 1; i < count; i++) {
                    contentStream.newLineAtOffset(0, -16);
                    contentStream.showText(arrayNumber[i]);
                }
            } else {
                if (!oldTD) {
                    contentStream.setNonStrokingColor(Color.GRAY);
                    text = "Không thỏa điều kiện chương trình";
                    contentStream.newLineAtOffset(-179, -49);
                    contentStream.showText(text);

                    contentStream.newLineAtOffset(-30, -16);
                    contentStream.showText(text);
                }

            }

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
            ex.printStackTrace();
            throw null;
        }
    }

    private static String changeFormatDatePdf(String dateSrc) {
        try {
            DateFormat formatDateSrc = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            DateFormat fromatDateDes = new SimpleDateFormat("dd/MM/yyyy");

            Date date = formatDateSrc.parse(dateSrc);
            return fromatDateDes.format(date);
        } catch (Exception ex) {
            Helper.writeLogError(MobileController.class, "***- checkJoinForTrans: " + dateSrc);
            return dateSrc;
        }
    }

    /**
     * Lay lich su gui email tiet kiem
     *
     * @param xml
     * @return
     */
    public String GetSendEmailOpenTDList(String xml) {
        try {
            GetSendEmailOpenTDListRq req = (GetSendEmailOpenTDListRq) Xml.unMarshaller(GetSendEmailOpenTDListRq.class, xml);
            GetSendEmailOpenTDListRp response = new GetSendEmailOpenTDListRp();

            GwEmailTd gwEmailTd = new GwEmailTd();
            gwEmailTd.setCustomerNo(req.getCifNo());
            List<GwEmailTd> listAccount = Helper.getDBI().getListGwEmailTd(gwEmailTd, req.getFromDate(), req.getToDate());
            if (listAccount == null || listAccount.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.NO_EXISTS_TRANSACTION));
            }

            SendEmailOpenTD[] sendEmailOpenTDs = new SendEmailOpenTD[listAccount.size()];
            for (int i = 0; i < sendEmailOpenTDs.length; i++) {
                DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                sendEmailOpenTDs[i] = new SendEmailOpenTD();
                sendEmailOpenTDs[i].setAccountNo(listAccount.get(i).getCustAcNo());
                sendEmailOpenTDs[i].setEmailCust(listAccount.get(i).getEmail());
                String dd = formatter.format(listAccount.get(i).getCreatedDate());
                sendEmailOpenTDs[i].setCreatedDate(dd);
                sendEmailOpenTDs[i].setTDAmount(listAccount.get(i).getTdAmount().toString());
		sendEmailOpenTDs[i].setInterest(Float.toString(listAccount.get(i).getInterest()));																				  
            }

            response.setSendEmailOpenTDList(sendEmailOpenTDs);
            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     * Cong cụ tính lãi suất
     *
     * @param xml
     * @return
     */
    public String InterestRateSlab_old(String xml) {
        try {
            InterestRateRq req = (InterestRateRq) Xml.unMarshaller(InterestRateRq.class, xml);
            InterestRateRp response = new InterestRateRp();
            List interestRateList = null;

            if (req.getCcy()
                    == null || req.getCcy().isEmpty()) {
                interestRateList = Helper.getDBI().getInterestRate("VND");
            } else {
                interestRateList = Helper.getDBI().getInterestRate(req.getCcy());
            }
            if (interestRateList
                    == null || interestRateList.size()
                    == 0) {
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.NO_EXISTS_RATE));
            } else {
                ArrayList<InterestRateDetail> listInterestRate = new ArrayList<InterestRateDetail>();
                InterestRateDetail rate = new InterestRateDetail();;
                if (req.getLanguage() == null || req.getLanguage().isEmpty() || req.getLanguage().equals("VN")) {
                    for (int i = 0; i < interestRateList.size(); i++) {
                        HashMap hm_Provider = (HashMap) interestRateList.get(i);
                        String amount_slab = hm_Provider.get("amountslab").toString();
                        if (amount_slab.equals(SlabAmountTD)) {
                            rate = new InterestRateDetail();
                            BeanUtils.copyProperties(rate, interestRateList.get(i));
                            rate.setRatetruoc(rate.getInterest());
                        } else if (amount_slab.equals(SlabAmountTD_Next)) {
                            rate.setRatesau(hm_Provider.get("interest").toString());
                            listInterestRate.add(rate);
                        }
                    }
                } else if (req.getLanguage().equals("EN")) {
                    for (int i = 0; i < interestRateList.size(); i++) {
                        HashMap hm_Provider = (HashMap) interestRateList.get(i);
                        String amount_slab = hm_Provider.get("amountslab").toString();
                        if (amount_slab.equals(SlabAmountTD)) {
                            rate = new InterestRateDetail();
                            BeanUtils.copyProperties(rate, interestRateList.get(i));
                            if (hm_Provider.get("term_eng") != null) {
                                rate.setTerm(hm_Provider.get("term_eng").toString());
                            }
                            rate.setRatetruoc(rate.getInterest());
                        } else if (amount_slab.equals(SlabAmountTD_Next)) {
                            rate.setRatesau(hm_Provider.get("interest").toString());
                            listInterestRate.add(rate);
                        }
                    }
                }
                response.setListInterestRate((InterestRateDetail[]) listInterestRate.toArray(new InterestRateDetail[listInterestRate.size()]));
                //response.setListInterestRate(listInterestRate);
            }

            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;

        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String InterestRateSlab(String xml) {
        try {
            InterestRateRq req = (InterestRateRq) Xml.unMarshaller(InterestRateRq.class, xml);
            InterestRateRp response = new InterestRateRp();
            List interestRateList = null;
            
            String  cifno = req.getCifNo();
            String custtype = "I";

            if (cifno != null && !cifno.isEmpty()  )
            {                                
                //get Type KH
                IIBCustomerService iibCustomerService = new IIBCustomerService();
                RetrieveCustomerRefDataMgmt_out_Type retrieveCustomerRefDataMgmt = iibCustomerService.retrieveCustomerRefDataMgmtSimple(Configuration.getIIBContext(), cifno, IIBConstants.CHANNEL_MOBILE);
                if (retrieveCustomerRefDataMgmt.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    CIFDataType cifdatatype = retrieveCustomerRefDataMgmt.getCIFInfo();
                    custtype = cifdatatype.getCustomerType();                
                };
            }
            
             if (req.getCcy()
                    == null || req.getCcy().isEmpty()) {
                interestRateList = Helper.getDBI().getInterestRate("VND");
            } else {
                Helper.writeLogError(this.getClass(), "InterestRateSlab 1:".concat(req.getCcy()));
                if (req.getTypetd() != null && (req.getTypetd().equalsIgnoreCase("LDK") || req.getTypetd().equalsIgnoreCase("LTT") )) {
                    Helper.writeLogError(this.getClass(), "InterestRateSlab LDK:".concat(req.getCcy()));
                    interestRateList = Helper.getDBI().getInterestRate(req.getTypetd().toUpperCase());
                } else {                    
                    if (custtype.equalsIgnoreCase("C")) {
                        interestRateList = Helper.getDBI().getInterestRate("DN");
                        Helper.writeLogError(this.getClass(), "InterestRateSlab DN:".concat(req.getCcy()));
                    } else {
                        interestRateList = Helper.getDBI().getInterestRate(req.getCcy());
                    }
                }
            }
          
            if (interestRateList
                    == null || interestRateList.size()
                    == 0) {
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.NO_EXISTS_RATE));
            } else {
                ArrayList<InterestRateDetail> listInterestRate = new ArrayList<InterestRateDetail>();
                InterestRateDetail rate = new InterestRateDetail();;
                if (req.getLanguage() == null || req.getLanguage().isEmpty() || req.getLanguage().equalsIgnoreCase("VN")) {
                    for (int i = 0; i < interestRateList.size(); i++) {
                        HashMap hm_Provider = (HashMap) interestRateList.get(i);
                        String amount_slab = hm_Provider.get("amountslab").toString();
                        //Helper.writeLogError(this.getClass(), "InterestRateSlab amount_slab:".concat(amount_slab));
                     //   Helper.writeLogError(this.getClass(), "InterestRateSlab rate.getInterest():".concat(rate.getInterest()));
                        if (amount_slab.equals(SlabAmountTD)) {
                            rate = new InterestRateDetail();
                            BeanUtils.copyProperties(rate, interestRateList.get(i));
                            rate.setRatetruoc(rate.getInterest());
                        } else if (amount_slab.equals(SlabAmountTD_Next)) {
                            rate.setRatesau(hm_Provider.get("interest").toString());
                            listInterestRate.add(rate);
                        } else if (amount_slab.equals(SlabAmountTD_DN)||amount_slab.equals(SlabAmountTD_DN_2)){
                            rate = new InterestRateDetail();
                            BeanUtils.copyProperties(rate, interestRateList.get(i));
                            rate.setRatetruoc(rate.getInterest());
                            listInterestRate.add(rate);
                        }
                    }
                } else if (req.getLanguage().equalsIgnoreCase("EN") || req.getLanguage().equals("en_US")) {
                    for (int i = 0; i < interestRateList.size(); i++) {
                        HashMap hm_Provider = (HashMap) interestRateList.get(i);
                        String amount_slab = hm_Provider.get("amountslab").toString();
                        if (amount_slab.equals(SlabAmountTD)) {
                            rate = new InterestRateDetail();
                            BeanUtils.copyProperties(rate, interestRateList.get(i));
                            if (hm_Provider.get("term_eng") != null) {
                                rate.setTerm(hm_Provider.get("term_eng").toString());
                            }
                            rate.setRatetruoc(rate.getInterest());
                        } else if (amount_slab.equals(SlabAmountTD_Next)) {
                            rate.setRatesau(hm_Provider.get("interest").toString());
                            listInterestRate.add(rate);
                        } else if (amount_slab.equals(SlabAmountTD_DN)||amount_slab.equals(SlabAmountTD_DN_2)){
                            rate = new InterestRateDetail();
                            BeanUtils.copyProperties(rate, interestRateList.get(i));
                            rate.setRatetruoc(rate.getInterest());
                            listInterestRate.add(rate);
                        }
                    }
                }
                response.setListInterestRate((InterestRateDetail[]) listInterestRate.toArray(new InterestRateDetail[listInterestRate.size()]));
                //response.setListInterestRate(listInterestRate);
            }

            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;

        }
    }

    /**
     * Cấp số dự thưởng online
     *
     * @param xml
     * @return
     */
    public String GrantLuckNumber(String xml) {
        try {
            GrantLuckNumberRq req = (GrantLuckNumberRq) Xml.unMarshaller(GrantLuckNumberRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            BeanUtils.copyProperties(response, req);
            //ngung cap so
            FuncGrantLuckNumber(req);
            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param req
     * @return
     */
    public String FuncGrantLuckNumber(GrantLuckNumberRq req) {
        try {
            scb.com.vn.dbi.dto.GwTdLucky luck = new scb.com.vn.dbi.dto.GwTdLucky();
            luck.setCustomerNo(req.getCifNo());
            luck.setCustAcNo(req.getAccountNo());
            luck.setSrcchannel(req.getSrcChannel());
            luck.setCreatedDate(new Date());
            Helper.getDBI().insertGwTdLuck(luck);
            
            IIBCustomerService iibCustomerService = new IIBCustomerService();
            RetrieveCustomerRefDataMgmt_out_Type retrieveCustomerRefDataMgmt = iibCustomerService.retrieveCustomerRefDataMgmtSimple(Configuration.getIIBContext(), req.getCifNo(), IIB_CHANNEL_MOBILE);
            if (!retrieveCustomerRefDataMgmt.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                Helper.writeLogError(this.getClass(), MessageMB.MobileResultEnum.NO_EXISTS_ACCOUNT);
            }          
            CustomerInfoType customerinfo = retrieveCustomerRefDataMgmt.getCustomerInfo();           
           /* 
            if (customerinfo.getIsStaff() != null && customerinfo.getIsStaff().equals("Y")) {
                Helper.writeLogError(this.getClass(), "customerinfo.getIsStaff() - cbnv:".concat(customerinfo.getIsStaff()));
                return null;
            }*/
            CIFDataType cifdata = retrieveCustomerRefDataMgmt.getCIFInfo();            

            
            if (cifdata.getCustomerType() != null && !cifdata.getCustomerType().equals("I")) {
                Helper.writeLogError(this.getClass(), "cifdata.getCustomerType() - TO CHUC:".concat(cifdata.getCustomerType()));
                return null;
            }
            
          
            TRANRESULT transresult = new TRANRESULT(); 
            transresult.setTRANCODE("DVKH");
                    
            AccountXML accountStr = new AccountXML();
            accountStr.setMAKH(req.getCifNo());
            accountStr.setSEGMENT_KH(customerinfo.getSegmentType());
            accountStr.setHOTEN(customerinfo.getFullname());
            accountStr.setDIACHI(VNCharacterUtils.removeAccent(customerinfo.getAddress().getAddress1()));
            accountStr.setCMND(customerinfo.getIDInfo().getIDNum());
            accountStr.setNGAYCAP(changeFormatDatePdf(customerinfo.getIDInfo().getIDIssuedDate()));
            accountStr.setLOAIKH(retrieveCustomerRefDataMgmt.getCIFInfo().getCustomerType());
            accountStr.setGIOITINH(customerinfo.getGender());
            accountStr.setNGAYSINH(changeFormatDatePdf(customerinfo.getBirthDay()));
            accountStr.setSOTAIKHOAN(req.getAccountNo());
            accountStr.setDIENTHOAI(customerinfo.getAddress().getMobileNum());
            accountStr.setNGAY_MO_CIF(changeFormatDatePdf(retrieveCustomerRefDataMgmt.getCIFInfo().getCIFIssuedDate()));
            accountStr.setISSTAFF(customerinfo.getIsStaff());            
            accountStr.setCHANNEL("MOBILE");                   
                        
            IIBDepositAccountService iibAccService = new IIBDepositAccountService();
            RetrieveDepositAccountTD_out_Type retrieveDepositAccountTD_out_Type = iibAccService.retrieveDepositAccountTDSimple(Configuration.getIIBContext(), req.getAccountNo(), IIB_CHANNEL_MOBILE);

            if (retrieveDepositAccountTD_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                AccountInfoType accountdetailIIB = retrieveDepositAccountTD_out_Type.getAccountInfo();
                accountStr.setSODU_MENHGIA(accountdetailIIB.getAccountBalance().toString());
                accountStr.setSODU_MENHGIA_QD(accountdetailIIB.getAccountBalance().toString());
                accountStr.setCCY(accountdetailIIB.getAccountCurrency());
                accountStr.setKY_HAN(accountdetailIIB.getAccountTerm());
                accountStr.setLAI_SUAT(accountdetailIIB.getAccountInterestRate());
                accountStr.setNGAYMO(changeFormatDatePdf(accountdetailIIB.getAccountOpenDate()));
                accountStr.setNGAYDENHAN(changeFormatDatePdf(accountdetailIIB.getAccountMaturityDate()));
                accountStr.setMASP(accountdetailIIB.getAccountClassCode());
                accountStr.setTENSP(accountdetailIIB.getAccountClassName());
                accountStr.setLOAIGIAODICH(accountdetailIIB.getAccountClassName());
                accountStr.setDONVIMO(accountdetailIIB.getAccountOpenBrandCode());

                DateFormat formatDateSrc = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOpen = formatDateSrc.parse(accountdetailIIB.getAccountOpenDate());
                /*
                if (dateLuck == null) {
                    //DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                    dateLuck = formatDateSrc.parse(luckStartDate);
                }
                */
                if (dateOpen.compareTo(dateLuck) < 0) {
                    Helper.writeLogError(this.getClass(), "Chua den ngay cap so accountno:" + req.getAccountNo());
                    Helper.writeLogError(this.getClass(), "Chua den ngay cap so opendate: " + dateOpen);
                    Helper.writeLogError(this.getClass(), "Chua den ngay cap so dateLuck: " + dateLuck);
                    return null;
                }else if (dateOpen.compareTo(dateEndLuck) > 0) {
                    Helper.writeLogError(this.getClass(), "Qua ngay cap so accountno:" + req.getAccountNo());
                    Helper.writeLogError(this.getClass(), "Qua ngay cap so opendate: " + dateOpen);
                    Helper.writeLogError(this.getClass(), "Qua ngay cap so dateEndLuck: " + dateEndLuck);
                    return null;
                }                
                luck.setTdAmount(accountdetailIIB.getAccountBalance().longValue());
                luck.setAccountClass(accountdetailIIB.getAccountClassCode());               
            }

            DvkhFunctionServices dvkhServices = new DvkhFunctionServices();
            CreateRewardNumber_in_Type createRewardNumber_in = new CreateRewardNumber_in_Type();
            //set tranasaction
            createRewardNumber_in.setTransactionInfo(IIBUtils.getTransInfpTypeCommon(IIB_CHANNEL_MOBILE));

            //set rewardNumInfo
            vn.com.scb.bian.RewardNumInfoType rewardNumInfo = new RewardNumInfoType();
            rewardNumInfo.setProgramCode(luckProgramCode);
            createRewardNumber_in.setRewardNumInfo(rewardNumInfo);

            //set accountInfo
            AccountInfoType accountInfo = new AccountInfoType();            
            transresult.setTRANDATA(accountStr);            
            accountInfo.setAccountInfoStr(MarshallerNOTFormat(transresult));
            createRewardNumber_in.setAccountInfo(accountInfo);

            createRewardNumber_in.setRewardNumInfo(rewardNumInfo);

            CreateRewardNumber_out_Type createRewardNumber_out = dvkhServices.createRewardNumber(Configuration.getIIBContext(), createRewardNumber_in);
            if (!createRewardNumber_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                Helper.writeLogError(this.getClass(), createRewardNumber_out.getTransactionInfo().getTransactionReturn());
                //    return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.NO_EXISTS_ACCOUNT));
            }
            if (!createRewardNumber_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                Helper.writeLogError(this.getClass(), createRewardNumber_out.getTransactionInfo().getTransactionReturn());
                luck.setStatus("ERR");
                luck.setDataStr(createRewardNumber_out.getTransactionInfo().getTransactionReturn().toString().concat(createRewardNumber_out.getTransactionInfo().getTransactionReturnMsg()));
            } else {
                luck.setStatus("COM");
                luck.setDataStr(createRewardNumber_out.getRewardNumInfo().toString());
            }
            Helper.getDBI().updateGwTdLuck(luck);
            return MessageMB.MobileResultEnum.OK.getValue();
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }

    }

    /**
     *
     * @param obj
     * @return
     */
    public static String MarshallerNOTFormat(Object obj) {
        try {
            JAXBContext jc = JAXBContext.newInstance(obj.getClass());
            Marshaller m = jc.createMarshaller();
            //m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            m.marshal(obj, sw);
            return sw.toString();
        } catch (Exception e) {
            //Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param custno
     * @param accountno
     * @return
     */
    public SelectRewardNumberFromAcctNo_out_Type getLuckNumber(String custno, String accountno) {
        try {
            DvkhFunctionServices dvkhServices = new DvkhFunctionServices();
            SelectRewardNumberFromAcctNo_in_Type selectRewardNumber_in = new SelectRewardNumberFromAcctNo_in_Type();
            selectRewardNumber_in.setTransactionInfo(IIBUtils.getTransInfpTypeCommon(IIB_CHANNEL_MOBILE));

            //set rewardNumInfo
            vn.com.scb.bian.RewardNumInfoType rewardNumInfo = new RewardNumInfoType();
            rewardNumInfo.setProgramCode(luckProgramCode);
            selectRewardNumber_in.setRewardNumInfo(rewardNumInfo);

            //set accountInfo
            AccountInfoType accountInfo = new AccountInfoType();
            accountInfo.setAccountNum(accountno);
            selectRewardNumber_in.setAccountInfo(accountInfo);

            selectRewardNumber_in.setRewardNumInfo(rewardNumInfo);

            SelectRewardNumberFromAcctNo_out_Type selectRewardNumber_out = dvkhServices.selectRewardNumberFromAcctNo(Configuration.getIIBContext(), selectRewardNumber_in);
            if (!selectRewardNumber_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                return null;
            }
            return selectRewardNumber_out;
        } catch (Exception e) {
            //Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param xml
     * @return
     *
     */
    public String CasaRegister(String xml) {
        try {
            CasaRegisterRq req = (CasaRegisterRq) Xml.unMarshaller(CasaRegisterRq.class, xml);
            MobileResponse response = new MobileResponse();

            EbIssuecard issueATM = new EbIssuecard();
            issueATM.setIschecked('N');
            //neu KH dang ky nhan the tai CN thi CN la don vi phat hanh the
            //nguoc lai lay don vi mo tk thanh toan lam don vi phat hanh the
            VwMbCustomer customer = new VwMbCustomer();
            customer.setCustomerNo(req.getCifNo());
            issueATM.setCUSTOMERNO(customer);
            issueATM.setIssuebranchcode(req.getBranchcode());
            issueATM.setCcy(req.getCcy());
            issueATM.setLoanpurpose(req.getPurpose());

            CardType card = new CardType();
            card.setCardtype(req.getCardtype());
            issueATM.setCARDTYPE(card);

            issueATM.setIdchannel(req.getUserName());
            issueATM.setIdchanneluser(req.getUserName());
            issueATM.setRegistertype("CA");
            issueATM.setIdchannel(req.getIdchannel());
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            Timestamp timestamp = Timestamp.valueOf(sdfDate.format(now));
            issueATM.setInsdate(timestamp);
            ProcedureCallDto rep = Helper.getDBI().insertEbIssuecard(issueATM);
            if (!rep.isSucess()) {
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.CANNOT_ADD_CASAREGISTER));
            }
            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }
    
    /**
     * Đăng ký phát hành bảo lãnh
     *
     * @param xml
     * @return
     *
     */
    public String GuaranteeRegister(String xml) {
        try {
            GuaranteeRegisterRq req = (GuaranteeRegisterRq) Xml.unMarshaller(GuaranteeRegisterRq.class, xml);
            MobileResponse response = new MobileResponse();

            EbIssuecard issueATM = new EbIssuecard();

            issueATM.setIschecked('N');

            VwMbCustomer customer = new VwMbCustomer();
            customer.setCustomerNo(req.getCifNo());
            issueATM.setIdchanneluser(req.getUserName());
            issueATM.setRegistertype("GU");
            issueATM.setIdchannel(req.getIdchannel());
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            Timestamp timestamp = Timestamp.valueOf(sdfDate.format(now));
            issueATM.setInsdate(timestamp);

            issueATM.setIssuetype(req.getGuaranteeType().charAt(0));
            issueATM.setGuaranteemode(req.getGuaranteeMode());
            issueATM.setLoanamount(Long.valueOf(req.getGuaranteeAmount()));
            issueATM.setLoanpurpose(req.getGuaranteePurpose());
            issueATM.setIssueidDate(req.getGuaranteeTime());
            
            issueATM.setAddress01(req.getAddress01());
            issueATM.setGuaranteeFromDate(req.getGuaranteeFromDate());
            issueATM.setGuaranteeToDate(req.getGuaranteeToDate());
            issueATM.setCollateral(req.getCollateral());
            issueATM.setCollateralDesc(req.getCollateralDesc());
            issueATM.setGuaranteeType(req.getGuaranteeType());
            
            ProcedureCallDto rep = Helper.getDBI().insertEbIssuecard(issueATM);
            if (!rep.isSucess()) {

                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.CANNOT_ADD_GUARANTEEREGISTER));
            }
            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }
    
    /**
     * Lay danh sach đk bdsd
     * @param xml
     * @return 
     */
 public String SMSBDSDList(String xml) {
        try {
            SMSBDSDListRq req = (SMSBDSDListRq) Xml.unMarshaller(SMSBDSDListRq.class, xml);
            String custno = req.getCifNo();

            List listAccountTT = Helper.getDBI().SMS_getListAccountTT(custno);

            SMSBDSDListRp response = new SMSBDSDListRp();
            response.setIsDebitFee("N");
            ArrayList<AccountBDSD> listMobileTT = new ArrayList<AccountBDSD>();
            ArrayList<AccountBDSD> listMobileTK = new ArrayList<AccountBDSD>();
            List<String> arrMobile = new ArrayList<String>();
            List<String> arrMobileLoop = new ArrayList<String>();

            List listAccountTK = Helper.getDBI().SMS_getAccountTK(custno);
            if ((listAccountTT == null || listAccountTT.isEmpty()) && (listAccountTK == null || listAccountTK.isEmpty())) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
            }
            Helper.writeLogError(getClass(), "SMSBDSDList - CIFNO:" + custno);
            for (int i = 0; i < listAccountTK.size(); i++) {
                HashMap<?, ?> hm = (HashMap) listAccountTK.get(i);
                String mobile = (String) hm.get("mobile");

                String isDeleted = (String) hm.get("isdeleted");
                BigDecimal id = (BigDecimal) hm.get("id");
                Helper.writeLogError(getClass(), "SMSBDSDList - ID:" + isDeleted);
                if (id != null && !id.equals(BigDecimal.ZERO) && !isDeleted.contentEquals("1")) {
                    Helper.writeLogError(getClass(), "SMSBDSDList - ID <> DEL:" + id);
                    AccountBDSD mobiletk = new AccountBDSD();
                    mobiletk.setListCustAcNo(req.getCifNo());
                    mobiletk.setMobileNo(mobile);
                    if (isDeleted.equalsIgnoreCase("2")) {
                        response.setIsDebitFee("Y");
                    }
                    listMobileTK.add(mobiletk);
                    if (!arrMobile.contains(mobile)) {
                        arrMobile.add(mobile);
                    }
                }
            }
            Helper.writeLogError(getClass(), "SMSBDSDList - SIZE TT:" + listAccountTK.size());
            Helper.writeLogError(getClass(), "SMSBDSDList - SIZE TK:" + listAccountTT.size());
            for (int i = 0; i < listAccountTT.size(); i++) {
                HashMap<?, ?> hm = (HashMap) listAccountTT.get(i);
                String cust_ac_no = (String) hm.get("cust_ac_no");
                String mobile = (String) hm.get("mobile");

                String isDeleted = (String) hm.get("isdeleted");
                BigDecimal id = (BigDecimal) hm.get("id");
                if (id != null && !id.equals(BigDecimal.ZERO ) && !isDeleted.contentEquals("1")) {                    
                    //if (!arrMobileLoop.contains(mobile)) {
                    Helper.writeLogError(getClass(), "SMSBDSDList - loop listAccountTT:" + id);
                    Helper.writeLogError(getClass(), "SMSBDSDList - loop listAccountTT:" + cust_ac_no);
                    Helper.writeLogError(getClass(), "SMSBDSDList - loop listAccountTT:" + isDeleted);
                    
                    if (isDeleted.equalsIgnoreCase("2")) {
                        response.setIsDebitFee("Y");
                    }
                    if (arrMobileLoop.indexOf(mobile) < 0) {
                        AccountBDSD mobilett = new AccountBDSD();
                        mobilett.setMobileNo(mobile);
                        mobilett.setListCustAcNo(cust_ac_no);

                        if (mobilett.getListCustAcNo() != null) {
                            arrMobileLoop.add(mobile);
                            listMobileTT.add(mobilett);
                        }
                        Helper.writeLogError(getClass(), "SMSBDSDList - loop listMobileTT add mobile:" + id);
                    } else {
                        int index = arrMobileLoop.indexOf(mobile);
                        ((AccountBDSD) listMobileTT.get(index)).setListCustAcNo(((AccountBDSD) listMobileTT.get(index)).getListCustAcNo().concat(";").concat(cust_ac_no));
                    }
                }
                if (!arrMobile.contains(mobile)) {
                    arrMobile.add(mobile);
                }

            }
            response.setListMobileTT((AccountBDSD[]) listMobileTT.toArray(new AccountBDSD[listMobileTT.size()]));
            response.setListMobileTK((AccountBDSD[]) listMobileTK.toArray(new AccountBDSD[listMobileTK.size()]));
            if (arrMobile != null && arrMobile.size() > 0) {
                Helper.writeLogError(getClass(), "SMSBDSDList - arrMobile:" + arrMobile);
                response.setListMobileArr(arrMobile.toString().replaceAll(",", ";").replaceAll(" ", "").replace("[", "").replace("]", ""));
            }
            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(getClass(), e);
            return null;
        }
    }
    
    public String RegisterSMSBDSD(String xml) {
        try {

            RegisterSMSBDSDRq req = (RegisterSMSBDSDRq) Xml.unMarshaller(RegisterSMSBDSDRq.class, xml);
            String custno = req.getCifNo();
            String typeAcc = req.getAccountType();

            String user = req.getUserId();

            String actionType = req.getActionType();

            String[] listAccountNo = req.getListCustAcNo().split(";");
            String[] listMobileNo = req.getMobileNo().split(";");
            MobileResponse response = new MobileResponse();

            if (custno == null) {
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
            }
            if (actionType.equals("UPDATE")) {
                String mobileNo_N = "";
                if (!req.getMobileNo().equals("")) {
                    mobileNo_N = listMobileNo[0];
                }
                List<SmsCustRegisInfo> listRegister = Helper.getDBI().getListRegisterSMS(custno);
                Helper.writeLogError(getClass(), "getListRegisterSMS:" + custno);
                if (listRegister != null) {

                    if (!mobileNo_N.equals("")) {
                        for (int j = 0; j < listRegister.size(); j++) {
                            SmsCustRegisInfo obj = listRegister.get(j);
                            String mobileNo_O = obj.getPhoneno();
                            String typeacc_on = obj.getTypeacc();
                            String custacct_on = typeacc_on == "TK" ? "" : obj.getCustaccno();

                            if (typeacc_on.equals("TT") & typeAcc.equals("TT")) {
                                int isDelete = 1;
                                if (mobileNo_N.equals(mobileNo_O)) {
                                    if (req.getListCustAcNo().equals("")) {
                                        isDelete = 1;
                                    } else {
                                        for (int i = 0; i < listAccountNo.length; i++) {
                                            String custAcc = listAccountNo[i];
                                            if (custacct_on.equals(custAcc)) {
                                                isDelete = 0;
                                            }
                                        }
                                    }
                                    if (isDelete == 1) {
                                        obj.setUserregister(user);
                                        obj.setTypefunction("DELETE");
                                        obj.setChannel("MB");

                                        //thuc hien huy so dt da dang ky truoc do
                                        String status = Helper.getDBI().PROCESS_SMS_ALERT(obj);
                                        Helper.writeLogError(getClass(), "Huy DKBDSD TKTT:" + custacct_on + " mobileNo:" + mobileNo_O);
                                    }
                                }
                            } else if (typeacc_on.equals("TK") & typeAcc.equals("TK")) {
                                if (!mobileNo_O.equals(mobileNo_N)) {
                                    obj.setUserregister(user);
                                    obj.setTypefunction("DELETE");
                                    obj.setChannel("MB");
                                    //thuc hien huy so dt da dang ky truoc do
                                    String status = Helper.getDBI().PROCESS_SMS_ALERT(obj);
                                    System.out.println("Huy DKBDSD TKTK cif:" + custno + " mobileNo:" + mobileNo_O + " status:" + status);
                                }
                            }
                        }
                    } else {
                        if (typeAcc.equals("TK")) {
                            SmsCustRegisInfo obj = new SmsCustRegisInfo();
                            obj.setTypefunction("DELETE");
                            obj.setCustno(custno);
                            obj.setUserregister(user);
                            obj.setTypeacc("TK");
                            obj.setChannel("MB");
                            String status = Helper.getDBI().PROCESS_SMS_ALERT(obj);
                            Helper.writeLogError(getClass(), "Huy DKBDSD TKTK cif:" + custno + " status:" + status);
                        } else {
                            for (int i = 0; i < listAccountNo.length; i++) {
                                String custAcc = listAccountNo[i];
                                SmsCustRegisInfo obj = new SmsCustRegisInfo();
                                obj.setTypefunction("DELETE");
                                obj.setCustaccno(custAcc);
                                obj.setCustno(custno);
                                obj.setUserregister(user);
                                obj.setTypeacc("TT");
                                obj.setChannel("MB");
                                //thuc hien huy so dt da dang ky truoc do
                                String status = Helper.getDBI().PROCESS_SMS_ALERT(obj);
                                Helper.writeLogError(getClass(), "Huy DKBDSD TKTT:" + custAcc + " status:" + status);

                            }
                        }
                    }
                }
            } else {
                if (req.getMobileNo().equals("")) {
                    return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
                }
            }
            if (!req.getMobileNo().equals("")) {
                int isRegister = 1;
                if (typeAcc.equals("TT") & req.getListCustAcNo().equals("")) {
                    isRegister = 0;
                }
                if (isRegister == 1) {
                    for (int i = 0; i < listMobileNo.length; i++) {
                        String mobileNo = listMobileNo[i];
                        for (int j = 0; j < listAccountNo.length; j++) {
                            //dang ky;
                            String custAcc = listAccountNo[j];
                            SmsCustRegisInfo obj = new SmsCustRegisInfo();
                            obj.setChannel("MB");
                            obj.setCustaccno(custAcc);
                            obj.setCustno(custno);
                            obj.setPhoneno(mobileNo);
                            obj.setTypefunction("REGISTER");
                            obj.setUserregister(user);
                            obj.setTypeacc(typeAcc);
                            String status = Helper.getDBI().PROCESS_SMS_ALERT(obj);
                            Helper.writeLogError(getClass(), " DKBDSD  cif:" + custno + " mobileNo:" + mobileNo + " custAcc:" + custAcc + " typeAcc:" + typeAcc);
                        }
                    }
                }
            }

            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(getClass(), "Exception RegisterSMSBDSD:" + e);
            return null;
        }
    }
}
