package scb.com.vn.controller;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.net.ssl.SSLContext;

import my.com.webservices.CardAdjustmentReqBean;
import my.com.webservices.CardAdjustmentRespBean;
import my.com.webservices.CardProfileMaintReqBean;
import my.com.webservices.CardProfileMaintRespBean;
import my.com.webservices.CardReloadReqBean;
import my.com.webservices.CardReloadRespBean;
import my.com.webservices.CardworksServiceLocator;
import my.com.webservices.Cardworks_PortType;
import my.com.webservices.DirectDebitReqBean;
import my.com.webservices.DirectDebitRespBean;
import my.com.webservices.RedeemRewardReqBean;
import my.com.webservices.RedeemRewardRespBean;
import my.com.webservices.PINSelectionReqBean;
import my.com.webservices.PINSelectionRespBean;
import my.com.webservices.CardActivationReqBean;
import my.com.webservices.CardActivationRespBean;
import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.cims.kht.KichHoatTheInfo;
import scb.com.vn.common.model.cw.CardAdjustmentReq;
import scb.com.vn.common.model.cw.CardAdjustmentRes;
import scb.com.vn.common.model.masterpass.MasterCardQrActionEnum;
import scb.com.vn.common.model.masterpass.PayByQRCodeRq;
import scb.com.vn.common.model.mvisa.MVISAQRRQ;
import scb.com.vn.constant.CWConstant;
import scb.com.vn.constant.CommonConstant;
import scb.com.vn.payment.PaymentException;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;

import scb.com.vn.dbi.controller.IDBI;
import scb.com.vn.dbi.dto.PmtInfoV11ResDto;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.dbi.dto.VwCustAccountNew;
import scb.com.vn.message.CardwordMessage;
import scb.com.vn.message.CardwordMessage.ErrorCodeEnum;
import scb.com.vn.ultility.Xml;

import scb.com.vn.model.RequestRewardAdjust;
import scb.com.vn.model.ResponseRewardAdjust;
import scb.com.vn.model.RequestCardBalanceAdjust;
import scb.com.vn.model.ResponseCardBalanceAdjust;
import scb.com.vn.model.RequestChangePINCard;
import scb.com.vn.model.ResponseChangePINCard;
import scb.com.vn.ultility.DBActionEnum;
import scb.com.vn.utility.CardwordConstant;
import scb.com.vn.utility.CommonUtils;

/**
 *
 * @author CORE77
 */
public class CardwordController {

    private static final Logger LOGGER = Logger.getLogger(CardwordController.class);
    String wsurladdress = Configuration.getProperty("ws.url.cwws.address");
    String cardreloadProdIB = Configuration.getProperty("ws.cwws.reload.product.ib");
    String cardreloadProdEB = Configuration.getProperty("ws.cwws.reload.product.teller");
    String cardreloadProdMB = Configuration.getProperty("ws.cwws.reload.product.mb");
    String cardreloadGLAccVisa = Configuration.getProperty("ws.cwws.reload.glacc.visa");
    String cardreloadGLAccMaster = Configuration.getProperty("ws.cwws.reload.glacc.master");

    private static final String CWFI = "970429";
    private static final String CWACTIND = "3";
    private static final String KHTCHANNEL = "SMS";

    public CardwordController() {
        try {
            SSLContext ctx = SSLContext.getInstance("TLSv1.2");
            ctx.init(null, null, null);
            SSLContext.setDefault(ctx);
            LOGGER.info("Enable TLSv1.2 for CardwordController");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public String cardReloadWithNarative(String srcCasaAccount, String pan, String expDate, String source, String brandCode,
            String currencyCode, BigDecimal amount, BigDecimal debtPay, BigDecimal debtCur, String IPPPayType, String narative) {
        String reloadSource = "";
        String responseCode = "";
        String RefFCC = "";
        String RefCardwork = "";
        String latestBal = "";
        String seqNo = "";
        String first4DigitCardNo = "";
        String glAcc = "";
        String result;
        String cardreloadProd = "";
        BigDecimal debtBalance;
        String responseCodeIIP = "04";

        int iRe = 0;
        int iRe1 = 0;
        //String seqNoIPP="";

        try {
            LOGGER.info("Call getCustAccountFcdbByAccountNo: " + srcCasaAccount);
            String cardreloadProdFB = "";
            if (!source.equals("FB")) {
                //Kiem tra Balance cua giao dich
                VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(srcCasaAccount);

                if (custacc == null) {
                    LOGGER.warn("LOI GET DBI");
                    return "01";
                }

                LOGGER.info("Call end: getCustAccountFcdbByAccountNo: " + srcCasaAccount);

                //Kiem tra neu la Thau chi thi khong kiem tra so du TK, cac TK khac deu ktra so du
                if ((!custacc.getAccountClass().substring(0, 3).equals("TCI"))) {
                    if (custacc.getAcyAvlBal().compareTo(amount) == -1) // 0:=,1: 1>2; -1:1<2
                    {
                        return "02";
                    }
                }
            } else {
                cardreloadProdFB = Configuration.getProperty("fcubs.product.ibt.credit");
            }

            Fcubs fcc = new Fcubs();//Hach toan tru tien voi Core truoc

            String useridfcubs = "";

            if (source.equals("MB")) {
                useridfcubs = Configuration.getProperty("fcubs.userid.mobile");
                reloadSource = "I";
                cardreloadProd = cardreloadProdMB;
            } else if (source.equals("IB")) {
                useridfcubs = Configuration.getProperty("fcubs.userid");
                reloadSource = "I";
                cardreloadProd = cardreloadProdIB;
            } else if (source.equals("FB")) {
                useridfcubs = Configuration.getProperty("fcubs.userid");
                reloadSource = "I";
                cardreloadProd = cardreloadProdFB;
            } else {
                useridfcubs = Configuration.getProperty("fcubs.userid");
                reloadSource = "T";
//                cardreloadProd = cardreloadProdIB;
                cardreloadProd = cardreloadProdEB;
            }
            if (cardreloadProd == null) {
                LOGGER.warn("KHONG GET DUOC MA SAN PHAM");
                return "01";
            }
            LOGGER.info("BAT DAU HACH TOAN");
            //Phan loai tai khoan GL hach toan Visa - Master
            //12 So LOC - 4 so dau cua The - 4 So cuoi the
            first4DigitCardNo = pan.substring(12, 16);
            pan = pan.substring(0, 12) + pan.substring(16);
            if (first4DigitCardNo.substring(0, 1).equalsIgnoreCase("4")) {
                glAcc = cardreloadGLAccVisa;
            } else {
                glAcc = cardreloadGLAccMaster;
            }

            seqNo = String.valueOf(getIdSeqByName("SQ_CWWSREFNO"));
            //seqNoIPP = String.valueOf(getIdSeqByName("SEQ_CARD_IPP"));
            responseCode = "04";
            if (IPPPayType.equals("0")) {
                debtBalance = debtPay;
            } else {
                debtBalance = debtCur;
            }
            LOGGER.info("seqNo:" + seqNo);
            //String PMT_INFO = Helper.getDBI().queryContactCenterInfo("getPMT_INFO", pan.substring(0, 12));
            //BigDecimal debtPay = new BigDecimal(String.valueOf(PMT_INFO.split("#")[0]));

            LOGGER.info("debtBalance:" + debtBalance);
            if (debtBalance.compareTo(BigDecimal.ZERO) == 0) {
                iRe = Helper.getDBI().insertCardReloadTrans(seqNo, srcCasaAccount, pan, brandCode,
                        currencyCode, expDate, amount.doubleValue(), "", "", responseCode, source);

                if (iRe > 0) {
                    LOGGER.info("insertCardReloadTrans - THANH CONG");
                } else {
                    LOGGER.info("insertCardReloadTrans - THAT BAI");
                    return "01";
                }
            } else if (amount.compareTo(debtBalance) == -1 || amount.compareTo(debtBalance) == 0) {
                iRe1 = Helper.getDBI().insertCardIPPTrans(seqNo, srcCasaAccount, pan, brandCode,
                        "VND", expDate, amount.doubleValue(), "", "", responseCode, source,
                        debtPay, debtCur, IPPPayType);
                if (iRe1 > 0) {
                    LOGGER.info("insertCardIPPTrans -  THANH CONG");
                } else {
                    LOGGER.info("insertCardIPPTrans -  THAT BAI");
                    return "01";
                }
            } else {
                iRe1 = Helper.getDBI().insertCardIPPTrans(seqNo, srcCasaAccount, pan, brandCode,
                        "VND", expDate, debtBalance.doubleValue(), "", "", responseCode, source,
                        debtPay, debtCur, IPPPayType);

                iRe = Helper.getDBI().insertCardReloadTrans(seqNo, srcCasaAccount, pan, brandCode,
                        currencyCode, expDate, amount.subtract(debtBalance).doubleValue(), "", "", responseCode, source);

                if (iRe > 0 && iRe1 > 0) {
                    LOGGER.info("insertCardReloadTrans -  THANH CONG");
                } else {
                    LOGGER.info("insertCardReloadTrans -  THAT BAI");
                    return "01";
                }
            }
            LOGGER.info(glAcc + "#" + amount + "#" + debtBalance + "#" + pan + "#" + expDate + "#" + brandCode);
            // RefFCC = fcc.transferFCUBSWithTimeOut(cardreloadProd, useridfcubs, brandCode, 
            //     glAcc, srcCasaAccount.substring(0, 3), srcCasaAccount, amount, "TT DU NO THE XXX"+ pan.substring(12, 16) + ". LOC: "+pan.substring(0, 12), 30);
            //update get branchcode
            /* RefFCC = fcc.transferFCUBSWithTimeOut(cardreloadProd, useridfcubs, brandCode, 
                    glAcc, CommonUtils.getBranchAccount(srcCasaAccount) , srcCasaAccount, amount, "TT DU NO THE XXX"+ pan.substring(12, 16) + ". LOC: "+pan.substring(0, 12), 30);*/
            if (cardreloadProd.equals(cardreloadProdMB) || cardreloadProd.equals(cardreloadProdFB)) {
                String Desc;
                if (cardreloadProd.equals(cardreloadProdFB)) {
                    Desc = narative;
                } else {
                    Desc = "TT DU NO THE XXX" + pan.substring(12, 16) + ". LOC: " + pan.substring(0, 12);
                }
                RefFCC = fcc.transferFCUBSWithTimeOut(cardreloadProd, useridfcubs, CommonUtils.getBranchAccount(srcCasaAccount), srcCasaAccount, brandCode,
                        glAcc, amount, Desc, 30);
            } else {
                RefFCC = fcc.transferFCUBSWithTimeOut(cardreloadProd, useridfcubs, brandCode,
                        glAcc, CommonUtils.getBranchAccount(srcCasaAccount), srcCasaAccount, amount, "TT DU NO THE XXX" + pan.substring(12, 16) + ". LOC: " + pan.substring(0, 12), 30);
            }

            LOGGER.info("Hach toan FCC, Ref: " + RefFCC);

            if (RefFCC != null) {
                if (RefFCC.equals("TIMEOUT")) {
                    RefFCC = "";
                    responseCode = "03"; // Chuyen khoan FCC time out                
                    LOGGER.info("Chuyen khoan FCC Time out, Resp Code: " + responseCode);
                } else {

                    if (debtBalance.compareTo(BigDecimal.ZERO) == 0) {
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
                        String dt = sdt.format(c.getTime());
                        RefCardwork = dt + seqNo;

                        String cwResult = callCWCardReload(pan, expDate, reloadSource, brandCode,
                                convertCurrencyCode(currencyCode), amount, seqNo, RefCardwork);

                        String cwResultArr[] = cwResult.split("#");
                        if (cwResultArr[0].equalsIgnoreCase("00")) {
                            //Giao dịch thành công
                            //RefCardwork = cwResultArr[1];
                            latestBal = cwResultArr[2];
                            responseCode = "00";
                            LOGGER.info("Thuc hien thanh toan du no CW, Thanh cong, Resp Code: " + responseCode);
                        } else if (cwResultArr[0].equalsIgnoreCase("04")) {
                            //Cardwork timeout
                            //Cho tra soat
                            responseCode = "04";
                            LOGGER.info("Thuc hien thanh toan du no CW, Time out, Resp Code: " + responseCode);
                        } else {
                            //Giao dich that bai
                            //Xu ly hoan tien giao dich
                            //Check lai truong hop nay voi eProtea de chac chan co the hoan tien.
                            LOGGER.info("Thuc hien thanh toan du no CW, That bai - Hoan tien so Ref Core, Ref CW: " + RefFCC + ", " + RefCardwork);

                            String refundResult = fcc.revertTransferFCUBS(RefFCC, 40);
                            if (refundResult == null || refundResult.equals("TIMEOUT")) {
                                //Không thể hoàn tiền cho KH, chờ tra soát
                                responseCode = "04";
                            } else {
                                //Hoàn tiền cho KH thành công.
                                if (cwResultArr[0].equalsIgnoreCase("05") && source.equals("MB")) {
                                    responseCode = "05";
                                } else {
                                    responseCode = "01";
                                }
                            }
                            LOGGER.info("Thuc hien thanh toan du no CW, That bai - Ket qua Hoan tien, Resp Code: " + responseCode);
                        }
                    } else if (amount.compareTo(debtBalance) == -1 || amount.compareTo(debtBalance) == 0) {
                        String request = pan.substring(0, 12) + "#" + seqNo + "#" + String.valueOf(amount) + "#CP";
                        String IPP_PAYMENT_PROCESSOR = Helper.getDBI().queryContactCenterInfo("getIPP_PAYMENT_PROCESSOR", request);

                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
                        String dt = sdt.format(c.getTime());
                        RefCardwork = dt + seqNo;
                        responseCodeIIP = IPP_PAYMENT_PROCESSOR;
                        if (responseCodeIIP.equalsIgnoreCase("00")) {
                            //Giao dịch thành công
                            //RefCardwork = cwResultArr[1];
                            //latestBal  = cwResultArr[2];
                            responseCode = "00";
                            LOGGER.info("Thuc hien thanh toan du no trả góp CW, Thanh cong, Resp Code: " + responseCode);
                        } else if (responseCodeIIP.equalsIgnoreCase("04")) {
                            //Cardwork timeout
                            //Cho tra soat
                            responseCode = "04";
                            LOGGER.info("Thuc hien thanh toan du no tra gop CW, Time out, Resp Code: " + responseCode);
                        } else {
                            //Giao dich that bai
                            responseCode = "04";
                            LOGGER.info("Thuc hien thanh toan du no tra gop CW, Time out, Resp Code: " + responseCode);
                        }
                    } else {
                        //thanh toan the tra gop truoc
                        String request = pan.substring(0, 12) + "#" + seqNo + "#" + String.valueOf(debtBalance) + "#CP";
                        String IPP_PAYMENT_PROCESSOR = Helper.getDBI().queryContactCenterInfo("getIPP_PAYMENT_PROCESSOR", request);
                        LOGGER.info("Thuc hien thanh toan du IPP truoc IPP_PAYMENT_PROCESSOR: " + IPP_PAYMENT_PROCESSOR);

                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
                        String dt = sdt.format(c.getTime());
                        RefCardwork = dt + seqNo;

                        String cwResult = callCWCardReload(pan, expDate, reloadSource, brandCode,
                                convertCurrencyCode(currencyCode), amount.subtract(debtBalance), seqNo, RefCardwork);
                        responseCodeIIP = IPP_PAYMENT_PROCESSOR;
                        String cwResultArr[] = cwResult.split("#");
                        if (cwResultArr[0].equalsIgnoreCase("00") && responseCodeIIP.equalsIgnoreCase("00")) {
                            //Giao dịch thành công
                            //RefCardwork = cwResultArr[1];
                            latestBal = cwResultArr[2];
                            responseCode = "00";
                            LOGGER.info("Thuc hien thanh toan du no CW va du no tra gop, Thanh cong, Resp Code: " + responseCode);
                        } else if (cwResultArr[0].equalsIgnoreCase("04")) {
                            //Cardwork timeout
                            //Cho tra soat
                            if (!IPP_PAYMENT_PROCESSOR.equalsIgnoreCase("00")) {
                                LOGGER.info("Thuc hien thanh toan du no tra gop, Khong thanh cong, Resp Code: " + responseCode);
                            } else {
                                LOGGER.info("Thuc hien thanh toan du no tra gop, Thanh cong, Resp Code: " + responseCode);
                            }
                            responseCode = "04";
                            LOGGER.info("Thuc hien thanh toan du no CW, Time out, Resp Code: " + responseCode);
                        } else {
                            if (!IPP_PAYMENT_PROCESSOR.equalsIgnoreCase("00")) {
                                LOGGER.info("Thuc hien thanh toan du no tra gop, Khong thanh cong, Resp Code: " + responseCode);
                            } else {
                                LOGGER.info("Thuc hien thanh toan du no tra gop, Thanh cong, Resp Code: " + responseCode);
                            }
                            //Giao dich that bai
                            //Xu ly hoan tien giao dich
                            //Check lai truong hop nay voi eProtea de chac chan co the hoan tien.
                            //kimncm -- ko hoàn tien trong TH IPP thành cong & card ws that bại - start
                            /*
                            LOGGER.info("Thuc hien thanh toan du no CW, That bai - Hoan tien so Ref Core, Ref CW: " + RefFCC + ", " + RefCardwork);

                            String refundResult = fcc.revertTransferFCUBS(RefFCC, 40);
                            if (refundResult == null || refundResult.equals("TIMEOUT")) {
                                //Không thể hoàn tiền cho KH, chờ tra soát
                                responseCode = "04";
                            }
                            else {
                                //Hoàn tiền cho KH thành công.
                                if (cwResultArr[0].equalsIgnoreCase("05") && source.equals("MB")){
                                    responseCode = "05";
                                }
                                else{
                                    responseCode = "01";
                                }                            
                            }*/
                            //LOGGER.info("Thuc hien thanh toan du no CW, That bai - Ket qua Hoan tien, Resp Code: " + responseCode);
                            responseCode = "04";
                            LOGGER.info("Thuc hien thanh toan du no CW, That bai - Cho tra soat ALL, Resp Code: " + responseCode);
                            //kimncm -- ko hoàn tien trong TH IPP thành cong & card ws that bại - end

                        }
                    }

                }
            } else {
                //Log here
                RefFCC = "";
                responseCode = "01";//Không thực hiện cắt tiền được từ FCC
            }

            //Cap nhat ket qua giao dich de doi soat sau.
            if (debtBalance.compareTo(BigDecimal.ZERO) == 0) {
                LOGGER.info("updateCardReloadTrans  -  just paycard: " + seqNo);
                Helper.getDBI().updateCardReloadTrans(seqNo, RefFCC, RefCardwork, responseCode);
            } else if (amount.compareTo(debtBalance) == -1 || amount.compareTo(debtBalance) == 0) {
                LOGGER.info("updateCardReloadTrans  -  just IPP: " + seqNo);
                Helper.getDBI().updateCardIPPTrans(seqNo, RefFCC, "", responseCodeIIP);
            } else {
                LOGGER.info("updateCardReloadTrans  -  both: " + seqNo);
                Helper.getDBI().updateCardReloadTrans(seqNo, RefFCC, RefCardwork, responseCode);
                Helper.getDBI().updateCardIPPTrans(seqNo, RefFCC, "", responseCodeIIP);
            }

            result = responseCode + "#" + RefFCC + "#" + RefCardwork + "#" + latestBal;
            return result;
        } catch (Exception ex) {
            try {
                if (iRe <= 0 & iRe1 <= 0 & RefFCC == null) {
                    LOGGER.warn("iRe <= 0 & iRe1 <= 0 & RefFCC==null: GIAO DICH THAT BAI");
                    LOGGER.warn("THANH TOAN DU NO THE LOI:" + ex.getMessage());
                    return "01";
                }
                if (iRe <= 0 & iRe1 <= 0 & RefFCC.equals("")) {
                    LOGGER.warn("iRe <= 0 & iRe1 <= 0 & RefFCC==null: GIAO DICH THAT BAI");
                    LOGGER.warn("THANH TOAN DU NO THE LOI:" + ex.getMessage());
                    return "01";
                }

                Helper.getDBI().updateCardReloadTrans(seqNo, RefFCC, RefCardwork, responseCode);
//              Helper.getDBI().insertCardReloadTrans(seqNo, srcCasaAccount, pan, brandCode, 
//                        currencyCode, expDate, amount.doubleValue(), RefFCC, RefCardwork, responseCode, source);                
            } catch (Exception e) {
                LOGGER.error("UPDATECardReloadTrans - CAP NHAT THAT BAI");
            }

            LOGGER.error(ex);
            return null;
        }
    }

    public String cardReload(String srcCasaAccount, String pan, String expDate, String source, String brandCode,
            String currencyCode, BigDecimal amount, BigDecimal debtPay, BigDecimal debtCur, String IPPPayType) {
        String reloadSource = "";
        String responseCode = "";
        String RefFCC = "";
        String RefCardwork = "";
        String latestBal = "";
        String seqNo = "";
        String first4DigitCardNo = "";
        String glAcc = "";
        String result;
        String cardreloadProd = "";
        BigDecimal debtBalance;
        String responseCodeIIP = "04";
        //String seqNoIPP="";

        try {
            LOGGER.info("Call getCustAccountFcdbByAccountNo: " + srcCasaAccount);
            String cardreloadProdFB = "";
            if (!source.equals("FB")) {
                //Kiem tra Balance cua giao dich
                VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(srcCasaAccount);

                LOGGER.info("Call end: getCustAccountFcdbByAccountNo: " + srcCasaAccount);

                //Kiem tra neu la Thau chi thi khong kiem tra so du TK, cac TK khac deu ktra so du
                if ((!custacc.getAccountClass().substring(0, 3).equals("TCI"))) {
                    if (custacc.getAcyAvlBal().compareTo(amount) == -1) // 0:=,1: 1>2; -1:1<2
                    {
                        return "02";
                    }
                }
            } else {
                cardreloadProdFB = Configuration.getProperty("fcubs.smartlink.productCredit");
            }

            Fcubs fcc = new Fcubs();//Hach toan tru tien voi Core truoc

            String useridfcubs = "";

            if (source.equals("MB")) {
                useridfcubs = Configuration.getProperty("fcubs.userid.mobile");
                reloadSource = "I";
                cardreloadProd = cardreloadProdMB;
            } else if (source.equals("IB")) {
                useridfcubs = Configuration.getProperty("fcubs.userid");
                reloadSource = "I";
                cardreloadProd = cardreloadProdIB;
            } else if (source.equals("FB")) {
                useridfcubs = Configuration.getProperty("fcubs.userid");
                reloadSource = "I";
                cardreloadProd = cardreloadProdFB;
            } else {
                useridfcubs = Configuration.getProperty("fcubs.userid");
                reloadSource = "T";
//                cardreloadProd = cardreloadProdIB;
                cardreloadProd = cardreloadProdEB;
            }

            //Phan loai tai khoan GL hach toan Visa - Master
            //12 So LOC - 4 so dau cua The - 4 So cuoi the
            first4DigitCardNo = pan.substring(12, 16);
            pan = pan.substring(0, 12) + pan.substring(16);
            if (first4DigitCardNo.substring(0, 1).equalsIgnoreCase("4")) {
                glAcc = cardreloadGLAccVisa;
            } else {
                glAcc = cardreloadGLAccMaster;
            }

            seqNo = String.valueOf(getIdSeqByName("SQ_CWWSREFNO"));
            //seqNoIPP = String.valueOf(getIdSeqByName("SEQ_CARD_IPP"));
            responseCode = "04";
            if (IPPPayType.equals("0")) {
                debtBalance = debtPay;
            } else {
                debtBalance = debtCur;
            }
            //String PMT_INFO = Helper.getDBI().queryContactCenterInfo("getPMT_INFO", pan.substring(0, 12));
            //BigDecimal debtPay = new BigDecimal(String.valueOf(PMT_INFO.split("#")[0]));
            int iRe = 0;
            int iRe1 = 0;
            if (debtBalance.compareTo(BigDecimal.ZERO) == 0) {
                iRe = Helper.getDBI().insertCardReloadTrans(seqNo, srcCasaAccount, pan, brandCode,
                        currencyCode, expDate, amount.doubleValue(), "", "", responseCode, source);

                if (iRe > 0) {
                    LOGGER.info("insertCardReloadTrans - CAP NHAT THANH CONG");
                } else {
                    LOGGER.info("insertCardReloadTrans - INSERT THAT BAI");
                    return null;
                }
            } else if (amount.compareTo(debtBalance) == -1 || amount.compareTo(debtBalance) == 0) {
                iRe1 = Helper.getDBI().insertCardIPPTrans(seqNo, srcCasaAccount, pan, brandCode,
                        "VND", expDate, amount.doubleValue(), "", "", responseCode, source,
                        debtPay, debtCur, IPPPayType);
                if (iRe1 > 0) {
                    LOGGER.info("insertCardReloadTrans - CAP NHAT THANH CONG");
                } else {
                    LOGGER.info("insertCardReloadTrans - INSERT THAT BAI");
                    return null;
                }
            } else {
                iRe1 = Helper.getDBI().insertCardIPPTrans(seqNo, srcCasaAccount, pan, brandCode,
                        "VND", expDate, debtBalance.doubleValue(), "", "", responseCode, source,
                        debtPay, debtCur, IPPPayType);

                iRe = Helper.getDBI().insertCardReloadTrans(seqNo, srcCasaAccount, pan, brandCode,
                        currencyCode, expDate, amount.subtract(debtBalance).doubleValue(), "", "", responseCode, source);

                if (iRe > 0 && iRe1 > 0) {
                    LOGGER.info("insertCardReloadTrans - CAP NHAT THANH CONG");
                } else {
                    LOGGER.info("insertCardReloadTrans - INSERT THAT BAI");
                    return null;
                }
            }

            LOGGER.info(glAcc + "#" + amount + "#" + debtBalance + "#" + pan + "#" + expDate + "#" + brandCode);
            // RefFCC = fcc.transferFCUBSWithTimeOut(cardreloadProd, useridfcubs, brandCode, 
            //     glAcc, srcCasaAccount.substring(0, 3), srcCasaAccount, amount, "TT DU NO THE XXX"+ pan.substring(12, 16) + ". LOC: "+pan.substring(0, 12), 30);
            //update get branchcode
            /* RefFCC = fcc.transferFCUBSWithTimeOut(cardreloadProd, useridfcubs, brandCode, 
                    glAcc, CommonUtils.getBranchAccount(srcCasaAccount) , srcCasaAccount, amount, "TT DU NO THE XXX"+ pan.substring(12, 16) + ". LOC: "+pan.substring(0, 12), 30);*/
            if (cardreloadProd.equals(cardreloadProdMB) || cardreloadProd.equals(cardreloadProdFB)) {
                RefFCC = fcc.transferFCUBSWithTimeOut(cardreloadProd, useridfcubs, CommonUtils.getBranchAccount(srcCasaAccount), srcCasaAccount, brandCode,
                        glAcc, amount, "TT DU NO THE XXX" + pan.substring(12, 16) + ". LOC: " + pan.substring(0, 12), 30);
            } else {
                RefFCC = fcc.transferFCUBSWithTimeOut(cardreloadProd, useridfcubs, brandCode,
                        glAcc, CommonUtils.getBranchAccount(srcCasaAccount), srcCasaAccount, amount, "TT DU NO THE XXX" + pan.substring(12, 16) + ". LOC: " + pan.substring(0, 12), 30);
            }

            LOGGER.info("Hach toan FCC, Ref: " + RefFCC);

            if (RefFCC != null) {
                if (RefFCC.equals("TIMEOUT")) {
                    RefFCC = "";
                    responseCode = "03"; // Chuyen khoan FCC time out                
                    LOGGER.info("Chuyen khoan FCC Time out, Resp Code: " + responseCode);
                } else {

                    if (debtBalance.compareTo(BigDecimal.ZERO) == 0) {
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
                        String dt = sdt.format(c.getTime());
                        RefCardwork = dt + seqNo;

                        String cwResult = callCWCardReload(pan, expDate, reloadSource, brandCode,
                                convertCurrencyCode(currencyCode), amount, seqNo, RefCardwork);

                        String cwResultArr[] = cwResult.split("#");
                        if (cwResultArr[0].equalsIgnoreCase("00")) {
                            //Giao dịch thành công
                            //RefCardwork = cwResultArr[1];
                            latestBal = cwResultArr[2];
                            responseCode = "00";
                            LOGGER.info("Thuc hien thanh toan du no CW, Thanh cong, Resp Code: " + responseCode);
                        } else if (cwResultArr[0].equalsIgnoreCase("04")) {
                            //Cardwork timeout
                            //Cho tra soat
                            responseCode = "04";
                            LOGGER.info("Thuc hien thanh toan du no CW, Time out, Resp Code: " + responseCode);
                        } else {
                            //Giao dich that bai
                            //Xu ly hoan tien giao dich
                            //Check lai truong hop nay voi eProtea de chac chan co the hoan tien.
                            LOGGER.info("Thuc hien thanh toan du no CW, That bai - Hoan tien so Ref Core, Ref CW: " + RefFCC + ", " + RefCardwork);

                            String refundResult = fcc.revertTransferFCUBS(RefFCC, 40);
                            if (refundResult == null || refundResult.equals("TIMEOUT")) {
                                //Không thể hoàn tiền cho KH, chờ tra soát
                                responseCode = "04";
                            } else {
                                //Hoàn tiền cho KH thành công.
                                if (cwResultArr[0].equalsIgnoreCase("05") && source.equals("MB")) {
                                    responseCode = "05";
                                } else {
                                    responseCode = "01";
                                }
                            }
                            LOGGER.info("Thuc hien thanh toan du no CW, That bai - Ket qua Hoan tien, Resp Code: " + responseCode);
                        }
                    } else if (amount.compareTo(debtBalance) == -1 || amount.compareTo(debtBalance) == 0) {
                        String request = pan.substring(0, 12) + "#" + seqNo + "#" + String.valueOf(amount) + "#CP";
                        String IPP_PAYMENT_PROCESSOR = Helper.getDBI().queryContactCenterInfo("getIPP_PAYMENT_PROCESSOR", request);

                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
                        String dt = sdt.format(c.getTime());
                        RefCardwork = dt + seqNo;
                        responseCodeIIP = IPP_PAYMENT_PROCESSOR;
                        if (responseCodeIIP.equalsIgnoreCase("00")) {
                            //Giao dịch thành công
                            //RefCardwork = cwResultArr[1];
                            //latestBal  = cwResultArr[2];
                            responseCode = "00";
                            LOGGER.info("Thuc hien thanh toan du no trả góp CW, Thanh cong, Resp Code: " + responseCode);
                        } else if (responseCodeIIP.equalsIgnoreCase("04")) {
                            //Cardwork timeout
                            //Cho tra soat
                            responseCode = "04";
                            LOGGER.warn("Thuc hien thanh toan du no tra gop CW, Time out, Resp Code: " + responseCode);
                        } else {
                            //Giao dich that bai
                            responseCode = "04";
                            LOGGER.warn("Thuc hien thanh toan du no tra gop CW, Time out, Resp Code: " + responseCode);
                        }
                    } else {
                        //thanh toan the tra gop truoc
                        String request = pan.substring(0, 12) + "#" + seqNo + "#" + String.valueOf(debtBalance) + "#CP";
                        String IPP_PAYMENT_PROCESSOR = Helper.getDBI().queryContactCenterInfo("getIPP_PAYMENT_PROCESSOR", request);
                        LOGGER.info("Thuc hien thanh toan du IPP truoc IPP_PAYMENT_PROCESSOR: " + IPP_PAYMENT_PROCESSOR);

                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
                        String dt = sdt.format(c.getTime());
                        RefCardwork = dt + seqNo;

                        String cwResult = callCWCardReload(pan, expDate, reloadSource, brandCode,
                                convertCurrencyCode(currencyCode), amount.subtract(debtBalance), seqNo, RefCardwork);
                        responseCodeIIP = IPP_PAYMENT_PROCESSOR;
                        String cwResultArr[] = cwResult.split("#");
                        if (cwResultArr[0].equalsIgnoreCase("00") && responseCodeIIP.equalsIgnoreCase("00")) {
                            //Giao dịch thành công
                            //RefCardwork = cwResultArr[1];
                            latestBal = cwResultArr[2];
                            responseCode = "00";
                            LOGGER.info("Thuc hien thanh toan du no CW va du no tra gop, Thanh cong, Resp Code: " + responseCode);
                        } else if (cwResultArr[0].equalsIgnoreCase("04")) {
                            //Cardwork timeout
                            //Cho tra soat
                            if (!IPP_PAYMENT_PROCESSOR.equalsIgnoreCase("00")) {
                                LOGGER.warn("Thuc hien thanh toan du no tra gop, Khong thanh cong, Resp Code: " + responseCode);
                            } else {
                                LOGGER.info("Thuc hien thanh toan du no tra gop, Thanh cong, Resp Code: " + responseCode);
                            }
                            responseCode = "04";
                            LOGGER.warn("Thuc hien thanh toan du no CW, Time out, Resp Code: " + responseCode);
                        } else {
                            if (!IPP_PAYMENT_PROCESSOR.equalsIgnoreCase("00")) {
                                LOGGER.warn("Thuc hien thanh toan du no tra gop, Khong thanh cong, Resp Code: " + responseCode);
                            } else {
                                LOGGER.info("Thuc hien thanh toan du no tra gop, Thanh cong, Resp Code: " + responseCode);
                            }
                            //Giao dich that bai
                            //Xu ly hoan tien giao dich
                            //Check lai truong hop nay voi eProtea de chac chan co the hoan tien.
                            //kimncm -- ko hoàn tien trong TH IPP thành cong & card ws that bại - start
                            /*
                            LOGGER.info("Thuc hien thanh toan du no CW, That bai - Hoan tien so Ref Core, Ref CW: " + RefFCC + ", " + RefCardwork);

                            String refundResult = fcc.revertTransferFCUBS(RefFCC, 40);
                            if (refundResult == null || refundResult.equals("TIMEOUT")) {
                                //Không thể hoàn tiền cho KH, chờ tra soát
                                responseCode = "04";
                            }
                            else {
                                //Hoàn tiền cho KH thành công.
                                if (cwResultArr[0].equalsIgnoreCase("05") && source.equals("MB")){
                                    responseCode = "05";
                                }
                                else{
                                    responseCode = "01";
                                }                            
                            }*/
                            //LOGGER.info("Thuc hien thanh toan du no CW, That bai - Ket qua Hoan tien, Resp Code: " + responseCode);
                            responseCode = "04";
                            LOGGER.warn("Thuc hien thanh toan du no CW, That bai - Cho tra soat ALL, Resp Code: " + responseCode);
                            //kimncm -- ko hoàn tien trong TH IPP thành cong & card ws that bại - end

                        }
                    }

                }
            } else {
                //Log here
                RefFCC = "";
                responseCode = "01";// Không thực hiện cắt tiền được từ FCC
            }

            //Cap nhat ket qua giao dich de doi soat sau.
            if (debtBalance.compareTo(BigDecimal.ZERO) == 0) {
                LOGGER.info("updateCardReloadTrans  -  just paycard: " + seqNo);
                Helper.getDBI().updateCardReloadTrans(seqNo, RefFCC, RefCardwork, responseCode);
            } else if (amount.compareTo(debtBalance) == -1 || amount.compareTo(debtBalance) == 0) {
                LOGGER.info("updateCardReloadTrans  -  just IPP: " + seqNo);
                Helper.getDBI().updateCardIPPTrans(seqNo, RefFCC, "", responseCodeIIP);
            } else {
                LOGGER.info("updateCardReloadTrans  -  both: " + seqNo);
                Helper.getDBI().updateCardReloadTrans(seqNo, RefFCC, RefCardwork, responseCode);
                Helper.getDBI().updateCardIPPTrans(seqNo, RefFCC, "", responseCodeIIP);
            }

            result = responseCode + "#" + RefFCC + "#" + RefCardwork + "#" + latestBal;
            return result;
        } catch (Exception ex) {
            try {
                Helper.getDBI().updateCardReloadTrans(seqNo, RefFCC, RefCardwork, responseCode);
//                Helper.getDBI().insertCardReloadTrans(seqNo, srcCasaAccount, pan, brandCode, 
//                        currencyCode, expDate, amount.doubleValue(), RefFCC, RefCardwork, responseCode, source);                
            } catch (Exception e) {
                LOGGER.error("UPDATECardReloadTrans - CAP NHAT THAT BAI");
            }

            LOGGER.error(ex);
            return null;
        }
    }

    /**
     * Credit Card PayMent -- thanh toan qua the
     *
     * @param srcCasaAccount
     * @param pan
     * @param expDate
     * @param source
     * @param brandCode
     * @param currencyCode
     * @param amount
     * @param pmtInfoDto
     * @param IPPPayType
     * @return
     */
    public String CreditCardPayMent(String srcCasaAccount, String pan, String expDate, String source, String brandCode,
            String currencyCode, BigDecimal amount, PmtInfoV11ResDto pmtInfoDto, String IPPPayType) {

        String reloadSource = "";
        String RefFCC = "";
        String RefCardwork = "";
        String latestBal = "";
        String seqNo = "";
        String first4DigitCardNo = "";
        String glAcc = "";
        String result;
        String cardreloadProd = "";
        String responseCode = CommonConstant.THANH_TOAN_DUNO_TIMEOUT;
        String responseCodeIIP = CommonConstant.THANH_TOAN_TRAGOP_ERROR;

        try {
            LOGGER.info("Call getCustAccountFcdbByAccountNo: " + srcCasaAccount);
            String cardreloadProdFB = "";
            if (!source.equals("FB")) {
                //Kiem tra Balance cua giao dich
                VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(srcCasaAccount);

                LOGGER.info("Call end: getCustAccountFcdbByAccountNo: " + srcCasaAccount);
                //Kiem tra neu la Thau chi thi khong kiem tra so du TK, cac TK khac deu ktra so du
                if ((!custacc.getAccountClass().substring(0, 3).equals("TCI"))) {
                    if (custacc.getAcyAvlBal().compareTo(amount) == -1) // 0:=,1: 1>2; -1:1<2
                    {
                        return "02";
                    }
                }
            } else {
                cardreloadProdFB = Configuration.getProperty("fcubs.smartlink.productCredit");
            }

            Fcubs fcc = new Fcubs();
            String useridfcubs = "";

            switch (source) {
                case "MB":
                    useridfcubs = Configuration.getProperty("fcubs.userid.mobile");
                    reloadSource = "I";
                    cardreloadProd = cardreloadProdMB;
                    break;
                case "IB":
                    useridfcubs = Configuration.getProperty("fcubs.userid");
                    reloadSource = "I";
                    cardreloadProd = cardreloadProdIB;
                    break;
                case "FB":
                    useridfcubs = Configuration.getProperty("fcubs.userid");
                    reloadSource = "I";
                    cardreloadProd = cardreloadProdFB;
                    break;
                default:
                    useridfcubs = Configuration.getProperty("fcubs.userid");
                    reloadSource = "T";
                    cardreloadProd = cardreloadProdEB;
                    break;
            }

            //Phan loai tai khoan GL hach toan Visa - Master
            //12 So LOC - 4 so dau cua The - 4 So cuoi the
            first4DigitCardNo = pan.substring(12, 16);
            pan = pan.substring(0, 12) + pan.substring(16);
            if (first4DigitCardNo.substring(0, 1).equalsIgnoreCase("4")) {
                glAcc = cardreloadGLAccVisa;
            } else {
                glAcc = cardreloadGLAccMaster;
            }

            seqNo = String.valueOf(getIdSeqByName("SQ_CWWSREFNO"));
            boolean isCase1 = false;
            boolean isCase2 = false;

            //case1
            if (pmtInfoDto.getPrevStmtClsBal().add(pmtInfoDto.getStmtClsBal()).compareTo(amount) <= 0) {
                isCase1 = true;
                int iRe1 = Helper.getDBI().insertCardIPPTrans(seqNo, srcCasaAccount, pan, brandCode,
                        "VND", expDate, amount.doubleValue(), "", "", responseCode, source,
                        amount, pmtInfoDto.getPrevCardStmtClsBal().add(pmtInfoDto.getStmtClsBal()), IPPPayType);
                if (iRe1 > 0) {
                    LOGGER.info("insertCardReloadTrans - THEM THANH CONG");
                } else {
                    LOGGER.error("insertCardReloadTrans - THEM THAT BAI");
                    return null;
                }
            } else {
                //case2
                if (pmtInfoDto.getPrevStmtClsBal().add(pmtInfoDto.getStmtClsBal()).compareTo(amount) > 0) {
                    isCase2 = true;
                    BigDecimal dunoThe = amount.subtract(pmtInfoDto.getPrevCardStmtClsBal().add(pmtInfoDto.getStmtClsBal()));

                    //du no tra gop
                    int iRe1 = Helper.getDBI().insertCardIPPTrans(seqNo, srcCasaAccount, pan, brandCode,
                            "VND", expDate, amount.doubleValue(), "", "", responseCode, source,
                            amount, pmtInfoDto.getPrevCardStmtClsBal().add(pmtInfoDto.getStmtClsBal()), IPPPayType);

                    //du no the
                    int iRe = Helper.getDBI().insertCardReloadTrans(seqNo, srcCasaAccount, pan, brandCode,
                            currencyCode, expDate, dunoThe.doubleValue(), "", "", responseCode, source);
                    if (iRe > 0 && iRe1 > 0) {
                        LOGGER.info("insertCardReloadTrans + insertCardIPPTrans  THEM THANH CONG");
                    } else {
                        LOGGER.error("insertCardReloadTrans + insertCardIPPTrans - THEM THAT BAI");
                        return null;
                    }
                }
            }

            // Thuc hien hoach toan qua core
            LOGGER.info(glAcc + "#" + amount + "#" + pmtInfoDto.getPrevCardStmtClsBal().add(pmtInfoDto.getStmtClsBal()) + "#" + pan + "#" + expDate + "#" + brandCode);
            if (cardreloadProd.equals(cardreloadProdMB) || cardreloadProd.equals(cardreloadProdFB)) {
                RefFCC = fcc.transferFCUBSWithTimeOut(cardreloadProd, useridfcubs, CommonUtils.getBranchAccount(srcCasaAccount), srcCasaAccount, brandCode,
                        glAcc, amount, "TT DU NO THE XXX" + pan.substring(12, 16) + ". LOC: " + pan.substring(0, 12), 30);
            } else {
                RefFCC = fcc.transferFCUBSWithTimeOut(cardreloadProd, useridfcubs, brandCode,
                        glAcc, CommonUtils.getBranchAccount(srcCasaAccount), srcCasaAccount, amount, "TT DU NO THE XXX" + pan.substring(12, 16) + ". LOC: " + pan.substring(0, 12), 30);
            }

            LOGGER.info("Hach toan FCC, Ref: " + RefFCC);
            try {
                if (RefFCC == null) {
                    LOGGER.error("Hach toan FCC, ko thanh cong Ref: " + RefFCC);
                    return null;
                }

                //Check timeout
                if (RefFCC.equals("TIMEOUT")) {
                    RefFCC = "";
                    responseCode = CommonConstant.HACHTOAN_CORE_TIMEOUT; // Chuyen khoan FCC time out                
                    LOGGER.error("Chuyen khoan FCC Time out, Resp Code: " + responseCode);
                    return null;
                }
                //thanh toan du no tra gop
                if (isCase1) {
                    String request = pan.substring(0, 12) + "#" + seqNo + "#" + String.valueOf(pmtInfoDto.getPrevCardStmtClsBal().add(pmtInfoDto.getStmtClsBal())) + "#CP";
                    String IPP_PAYMENT_PROCESSOR = Helper.getDBI().queryContactCenterInfo("getIPP_PAYMENT_PROCESSOR", request);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
                    String dt = sdt.format(c.getTime());
                    RefCardwork = dt + seqNo;
                    responseCodeIIP = IPP_PAYMENT_PROCESSOR;
                    if (responseCodeIIP.equalsIgnoreCase("00")) {
                        //RefCardwork = cwResultArr[1];
                        //latestBal  = cwResultArr[2];
                        responseCode = CommonConstant.RESPONSE_SUCCEED;
                        LOGGER.info("Thuc hien thanh toan du no trả góp CW, Thanh cong, Resp Code: " + responseCode);
                    } else if (responseCodeIIP.equalsIgnoreCase("04")) {
                        //Cardwork timeout
                        //Cho tra soat
                        responseCode = CommonConstant.THANH_TOAN_TRAGOP_TIMEOUT;
                        LOGGER.warn("Thuc hien thanh toan du no tra gop CW, Time out, Resp Code: " + responseCode);
                    } else if (responseCodeIIP.equalsIgnoreCase("03")) {
                        responseCode = CommonConstant.THANH_TOAN_TRAGOP_NOTEXISTED;
                        LOGGER.warn("Khong tim thay the trong CW, Resp Code: " + responseCode);
                    } else {
                        //Giao dich that bai
                        responseCode = CommonConstant.THANH_TOAN_TRAGOP_ERROR;
                        LOGGER.warn("Thuc hien thanh toan du no tra gop CW, ko thanh cong Resp Code: " + responseCode);
                    }
                }

                boolean isRollBack = true;
                if (isCase2) //thanh toan du no the tra gop va du no the
                {
                    String request = pan.substring(0, 12) + "#" + seqNo + "#" + String.valueOf(pmtInfoDto.getPrevCardStmtClsBal().add(pmtInfoDto.getStmtClsBal())) + "#CP";
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
                    String dt = sdt.format(c.getTime());
                    RefCardwork = dt + seqNo;

                    //thanh toan du no the tra gop
                    String tragopResult = Helper.getDBI().queryContactCenterInfo("getIPP_PAYMENT_PROCESSOR", request);
                    LOGGER.info("Thuc hien thanh toan du IPP truoc IPP_PAYMENT_PROCESSOR: " + tragopResult);

                    //thanh toan du no the
                    String theResult = callCWCardReload(pan, expDate, reloadSource, brandCode,
                            convertCurrencyCode(currencyCode), amount.subtract(pmtInfoDto.getPrevCardStmtClsBal().add(pmtInfoDto.getStmtClsBal())), seqNo, RefCardwork);

                    String theResultArr[] = theResult.split("#");

                    //session du no the
                    if (theResultArr[0].equalsIgnoreCase("00")) {
                        isRollBack = false;
                        responseCode = CommonConstant.RESPONSE_SUCCEED;
                        LOGGER.info("Thuc hien thanh toan du no CW Thanh cong, Resp Code: " + responseCode);
                    }
                    if (theResultArr[0].equalsIgnoreCase("04")) {
                        //Cardwork timeout
                        responseCode = CommonConstant.THANH_TOAN_DUNO_TIMEOUT;
                        LOGGER.warn("Thuc hien thanh toan du no CW, Time out, Resp Code: " + responseCode);
                    }
                    if (!theResultArr[0].equalsIgnoreCase("00") && !theResultArr[0].equalsIgnoreCase("04")) {
                        //Cho tra soat
                        responseCode = CommonConstant.THANH_TOAN_DUNO_ERROR;
                        LOGGER.info("Thuc hien thanh toan du no CW, co loi: " + responseCode);
                    }

                    //session du no the tra gop
                    if (!tragopResult.equalsIgnoreCase("00")) {
                        LOGGER.warn("Thuc hien thanh toan du no tra gop, Khong thanh cong, Resp Code: " + CommonConstant.THANH_TOAN_DUNO_ERROR);
                    } else {
                        LOGGER.info("Thuc hien thanh toan du no tra gop, Thanh cong, Resp Code: " + CommonConstant.RESPONSE_SUCCEED);
                    }

                    // Giao dich that bai - Thuc hien hoan tien lai cho kh
                    if (isRollBack) {
                        //Xu ly hoan tien giao dich
                        //Check lai truong hop nay voi eProtea de chac chan co the hoan tien.
                        LOGGER.warn("Thuc hien thanh toan du no CW, That bai - Hoan tien so Ref Core, Ref CW: " + RefFCC + ", " + RefCardwork);

                        String refundResult = fcc.revertTransferFCUBS(RefFCC, 40);
                        if (refundResult == null || refundResult.equals("TIMEOUT")) {
                            //Không thể hoàn tiền cho KH, chờ tra soát
                            responseCode = CommonConstant.THANH_TOAN_DUNO_TIMEOUT;
                        } else {
                            //Hoàn tiền cho KH thành công.
                            if (theResultArr[0].equalsIgnoreCase("05") && source.equals("MB")) {
                                responseCode = CommonConstant.HOANTIEN_KH_KENH_MB_SUCCEED;
                            } else {
                                responseCode = CommonConstant.HOANTIEN_KH_KENH_SUCCEED;
                            }
                        }
                        LOGGER.info("Thuc hien thanh toan du no CW, That bai - Ket qua Hoan tien, Resp Code: " + responseCode);
                    }
                }

                //Cap nhat ket qua giao dich de doi soat sau.
                if (isCase1) {
                    LOGGER.info("updateCardIPPTrans  -  just paycard: " + seqNo);
                    Helper.getDBI().updateCardIPPTrans(seqNo, RefFCC, "", responseCodeIIP);
                    //Helper.getDBI().updateCardReloadTrans(seqNo, RefFCC, RefCardwork, responseCode);
                }
                if (isCase2) {
                    LOGGER.info("updateCardReloadTrans  + updateCardIPPTrans -  both: " + seqNo);
                    Helper.getDBI().updateCardReloadTrans(seqNo, RefFCC, RefCardwork, responseCode);
                    Helper.getDBI().updateCardIPPTrans(seqNo, RefFCC, "", responseCodeIIP);
                }
                result = responseCode + "#" + RefFCC + "#" + RefCardwork + "#" + latestBal;
                return result;

            } catch (Exception ex) {
                LOGGER.error(ex);
                return null;
            }

        } catch (RemoteException ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    public String cardReloadCash(String srcCasaAccount, String pan, String expDate, String source, String brandCode, String currencyCode,
            BigDecimal amount, String user_maker, String user_checker, String idcard, String idcardDob, String idcardName, String idcardAddress, BigDecimal debtPay, BigDecimal debtCur, String IPPPayType) {
        String reloadSource = "";
        String responseCode = "";
        String responseCore = "";
        String SO_CT = "";
        String RefFCC = "";
        String RefCardwork = "";
        String latestBal = "";
        String seqNo = "";
        String first4DigitCardNo = "";
        String glAcc = "";
        String result;
        LOGGER.info("Call getCustAccountFcdbByAccountNo: " + srcCasaAccount);
        reloadSource = "T";
        BigDecimal debtBalance;
        String responseCodeIIP = "04";
        //String seqNoIPP ="";
        try {

//            //Phan loai tai khoan GL hach toan Visa - Master
//            //12 So LOC - 4 so dau cua The - 4 So cuoi the
            first4DigitCardNo = pan.substring(12, 16);
            pan = pan.substring(0, 12) + pan.substring(16);
            if (first4DigitCardNo.substring(0, 1).equalsIgnoreCase("4")) {
                glAcc = cardreloadGLAccVisa;
            } else {
                glAcc = cardreloadGLAccMaster;
            }

            seqNo = String.valueOf(getIdSeqByName("SQ_CWWSREFNO"));
            responseCode = "04";

            //seqNoIPP = String.valueOf(getIdSeqByName("SEQ_CARD_IPP"));
//            String PMT_INFO = Helper.getDBI().queryContactCenterInfo("getPMT_INFO", pan.substring(0, 12));
//            BigDecimal debtPay = new BigDecimal(String.valueOf(PMT_INFO.split("#")[0]));
            int iRe = 0;
            int iRe1 = 0;

            if (IPPPayType.equals("0")) {
                debtBalance = debtPay;
            } else {
                debtBalance = debtCur;
            }
            if (debtBalance.compareTo(BigDecimal.ZERO) == 0) {
                iRe = Helper.getDBI().insertCardReloadTrans(seqNo, srcCasaAccount, pan, brandCode,
                        currencyCode, expDate, amount.doubleValue(), "", "", responseCode, source);

                if (iRe > 0) {
                    LOGGER.info("insertCardReloadTrans - CAP NHAT THANH CONG");
                } else {
                    LOGGER.warn("insertCardReloadTrans - CAP NHAT THAT BAI");
                }
            } else if (amount.compareTo(debtBalance) == -1 || amount.compareTo(debtBalance) == 0) {
                iRe1 = Helper.getDBI().insertCardIPPTrans(seqNo, srcCasaAccount, pan, brandCode,
                        "VND", expDate, amount.doubleValue(), "", "", responseCode, source,
                        debtPay, debtCur, IPPPayType);
                if (iRe1 > 0) {
                    LOGGER.info("insertCardReloadTrans - CAP NHAT THANH CONG");
                } else {
                    LOGGER.warn("insertCardReloadTrans - CAP NHAT THAT BAI");
                }
            } else {

                iRe1 = Helper.getDBI().insertCardIPPTrans(seqNo, srcCasaAccount, pan, brandCode,
                        "VND", expDate, debtBalance.doubleValue(), "", "", responseCode, source,
                        debtPay, debtCur, IPPPayType);

                iRe = Helper.getDBI().insertCardReloadTrans(seqNo, srcCasaAccount, pan, brandCode,
                        currencyCode, expDate, amount.subtract(debtBalance).doubleValue(), "", "", responseCode, source);

                if (iRe > 0 && iRe1 > 0) {
                    LOGGER.info("insertCardReloadTrans - CAP NHAT THANH CONG");
                } else {
                    LOGGER.warn("insertCardReloadTrans - CAP NHAT THAT BAI");
                }
            }

            LOGGER.info(glAcc + "#" + amount + "#" + debtBalance + "#" + idcard + "#" + idcardDob + "#" + idcardName + "#" + idcardAddress + "#" + user_maker + "#" + user_checker + "#" + brandCode);

            LOGGER.info("bat dau goi sang core cash duytxa " + RefFCC);

            responseCore = Helper.getDBI().transferFCUBS(glAcc, amount, "TT DU NO THE XXX" + pan.substring(12, 16) + ". LOC: " + pan.substring(0, 12), idcardName, idcardAddress, idcard, idcardDob, user_maker, user_checker, brandCode);
            LOGGER.info("response sau khi  goi sang core cash duytxa " + responseCore);

            if (responseCore != null) {
                String[] resp = responseCore.split("#");
                RefFCC = resp[0];
                SO_CT = resp[1];
                LOGGER.info("response core tra ve cash: " + SO_CT + "#" + RefFCC);
            }
            LOGGER.info("sau khi  goi sang core cash duytxa " + RefFCC);
            LOGGER.info("Hach toan FCC, Ref: " + RefFCC);

            //Neu Ref = null, hoac null ->  Ref = "" > Fail
            if (RefFCC != null && !RefFCC.equalsIgnoreCase("null")) {
                if (RefFCC.equals("TIMEOUT")) {
                    RefFCC = "";
                    responseCode = "03"; // Chuyen khoan FCC time out                
                    LOGGER.warn("Chuyen khoan FCC Time out, Resp Code: " + responseCode);
                } else {
                    //Giao dich thanh cong, hach toan he thong CW

                    if (debtBalance.compareTo(BigDecimal.ZERO) == 0) {
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
                        String dt = sdt.format(c.getTime());
                        RefCardwork = dt + seqNo;

                        String cwResult = callCWCardReload(pan, expDate, reloadSource, brandCode,
                                convertCurrencyCode(currencyCode), amount, seqNo, RefCardwork);

                        String cwResultArr[] = cwResult.split("#");
                        if (cwResultArr[0].equalsIgnoreCase("00")) {
                            //Giao dịch thành công
                            //RefCardwork = cwResultArr[1];
                            latestBal = cwResultArr[2];
                            responseCode = "00";
                            LOGGER.info("Thuc hien thanh toan du no CW, Thanh cong, Resp Code: " + responseCode);
                        } else if (cwResultArr[0].equalsIgnoreCase("04")) {
                            //Cardwork timeout
                            //Cho tra soat
                            responseCode = "04";
                            LOGGER.warn("Thuc hien thanh toan du no CW, Time out, Resp Code: " + responseCode);
                        } else {
                            //Giao dich that bai
                            responseCode = "04";
                            LOGGER.warn("Thuc hien thanh toan du no CW, Time out, Resp Code: " + responseCode);
                        }
                    } else if (amount.compareTo(debtBalance) == -1 || amount.compareTo(debtBalance) == 0) {
                        String request = pan.substring(0, 12) + "#" + seqNo + "#" + String.valueOf(amount) + "#CP";
                        String IPP_PAYMENT_PROCESSOR = Helper.getDBI().queryContactCenterInfo("getIPP_PAYMENT_PROCESSOR", request);

                        responseCodeIIP = IPP_PAYMENT_PROCESSOR;

                        if (responseCodeIIP.equalsIgnoreCase("00")) {
                            //Giao dịch thành công
                            //RefCardwork = cwResultArr[1];
                            //latestBal  = cwResultArr[2];
                            if (!IPP_PAYMENT_PROCESSOR.equalsIgnoreCase("00")) {
                                LOGGER.warn("Thuc hien thanh toan du no tra gop, Khong thanh cong, Resp Code: " + responseCode);
                            } else {
                                LOGGER.info("Thuc hien thanh toan du no tra gop, Thanh cong, Resp Code: " + responseCode);
                            }
                            responseCode = "00";
                            LOGGER.info("Thuc hien thanh toan du no CW, Thanh cong, Resp Code: " + responseCode);
                        } else if (responseCodeIIP.equalsIgnoreCase("04")) {
                            //Cardwork timeout
                            //Cho tra soat
                            if (!IPP_PAYMENT_PROCESSOR.equalsIgnoreCase("00")) {
                                LOGGER.warn("Thuc hien thanh toan du no tra gop, Khong thanh cong, Resp Code: " + responseCode);
                            } else {
                                LOGGER.info("Thuc hien thanh toan du no tra gop, Thanh cong, Resp Code: " + responseCode);
                            }
                            responseCode = "04";
                            LOGGER.warn("Thuc hien thanh toan du no CW, Time out, Resp Code: " + responseCode);
                        } else {
                            //Giao dich that bai
                            responseCode = "04";
                            LOGGER.warn("Thuc hien thanh toan du no CW, Time out, Resp Code: " + responseCode);
                        }
                    } else {
                        String request = pan.substring(0, 12) + "#" + seqNo + "#" + String.valueOf(debtBalance) + "#CP";
                        String IPP_PAYMENT_PROCESSOR = Helper.getDBI().queryContactCenterInfo("getIPP_PAYMENT_PROCESSOR", request);

                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
                        String dt = sdt.format(c.getTime());
                        RefCardwork = dt + seqNo;

                        String cwResult = callCWCardReload(pan, expDate, reloadSource, brandCode,
                                convertCurrencyCode(currencyCode), amount.subtract(debtBalance), seqNo, RefCardwork);
                        responseCodeIIP = IPP_PAYMENT_PROCESSOR;
                        String cwResultArr[] = cwResult.split("#");
                        if (cwResultArr[0].equalsIgnoreCase("00") && responseCodeIIP.equalsIgnoreCase("00")) {
                            //Giao dịch thành công
                            //RefCardwork = cwResultArr[1];
                            latestBal = cwResultArr[2];
                            responseCode = "00";
                            LOGGER.info("Thuc hien thanh toan du no CW, Thanh cong, Resp Code: " + responseCode);
                        } else if (cwResultArr[0].equalsIgnoreCase("04")) {
                            //Cardwork timeout
                            //Cho tra soat
                            if (!IPP_PAYMENT_PROCESSOR.equalsIgnoreCase("00")) {
                                LOGGER.warn("Thuc hien thanh toan du no tra gop, Khong thanh cong, Resp Code: " + responseCode);
                            } else {
                                LOGGER.info("Thuc hien thanh toan du no tra gop, Thanh cong, Resp Code: " + responseCode);
                            }
                            responseCode = "04";
                            LOGGER.warn("Thuc hien thanh toan du no CW, Time out, Resp Code: " + responseCode);
                        } else {
                            //Giao dich that bai
                            if (!IPP_PAYMENT_PROCESSOR.equalsIgnoreCase("00")) {
                                LOGGER.warn("Thuc hien thanh toan du no tra gop, Khong thanh cong, Resp Code: " + responseCode);
                            } else {
                                LOGGER.info("Thuc hien thanh toan du no tra gop, Thanh cong, Resp Code: " + responseCode);
                            }
                            responseCode = "04";
                            LOGGER.warn("Thuc hien thanh toan du no CW, Time out, Resp Code: " + responseCode);
                        }
                    }
                }
            } else {
                //Log here
                RefFCC = "";
                responseCode = "01";// Không thực hiện cắt tiền được từ FCC
            }

            //Cap nhat ket qua giao dich de doi soat sau.
            if (debtBalance.compareTo(BigDecimal.ZERO) == 0) {
                LOGGER.info("updateCardReloadTrans  -  just paycard: " + seqNo);
                Helper.getDBI().updateCardReloadTrans(seqNo, RefFCC, RefCardwork, responseCode);
            } else if (amount.compareTo(debtBalance) == -1 || amount.compareTo(debtBalance) == 0) {
                LOGGER.info("updateCardReloadTrans  -  just IPP: " + seqNo);
                Helper.getDBI().updateCardIPPTrans(seqNo, RefFCC, "", responseCodeIIP);
            } else {
                LOGGER.info("updateCardReloadTrans  -  both: " + seqNo);
                Helper.getDBI().updateCardReloadTrans(seqNo, RefFCC, RefCardwork, responseCode);
                Helper.getDBI().updateCardIPPTrans(seqNo, RefFCC, "", responseCodeIIP);
            }

            result = responseCode + "#" + RefFCC + "#" + SO_CT + "#" + RefCardwork + "#" + latestBal;
            return result;
        } catch (Exception ex) {
            try {
                Helper.getDBI().updateCardReloadTrans(seqNo, RefFCC, RefCardwork, responseCode);
            } catch (Exception e) {
                LOGGER.error("UPDATECardReloadTrans - CAP NHAT THAT BAI");
            }
            LOGGER.error(ex);
            return null;
        }
    }

    private String callCWCardReload(String pan, String expDate, String source, String brandCode,
            String currencyCode, BigDecimal amount, String seqNo, String RefCardwork) {
        String result = null;

        try {
            String cwRefNo = RefCardwork;

            CardReloadReqBean cardReloadReq = new CardReloadReqBean();

            //Sequence No
            String sequenceNo = seqNo;
            cardReloadReq.setSequenceNo(sequenceNo);

            cardReloadReq.setFi("970429");
            cardReloadReq.setSrc("WEB");

            //LOC Number + last 4 digits PAN
            cardReloadReq.setActInd("3");
            cardReloadReq.setPan(pan);

            cardReloadReq.setReferenceNo(cwRefNo);

            cardReloadReq.setExpiryDate(expDate); //YYYYmm

            //C - Cash, X - Fund transfer
            cardReloadReq.setReloadMethod("X");
            cardReloadReq.setReloadSource(source);
            cardReloadReq.setBranchCode(brandCode);
            cardReloadReq.setCurrencyCode(currencyCode);

            //Format amount follow by Cardwork          
            cardReloadReq.setAmount(amount.stripTrailingZeros().toPlainString() + ".00");

            LOGGER.info("Xml.Marshaller(cardReloadReq)=" + cardReloadReq.getPan() + cardReloadReq.getExpiryDate());

            LOGGER.info("formated amount =" + cardReloadReq.getAmount());

            CardReloadRespBean cardReloadResp = getCardworkWS().cardReload(cardReloadReq);

            //Xu ly ke qua tra ve, tra lai client
            LOGGER.info("cardReloadResp.getSequenceNo() =" + cardReloadResp.getSequenceNo());
            LOGGER.info("cardReloadResp.getResponseCode() = " + cardReloadResp.getResponseCode());
            LOGGER.info("cardReloadResp.getResponseDescription() = " + cardReloadResp.getResponseDescription());
            LOGGER.info("cardReloadResp.getApprovalCode() = " + cardReloadResp.getApprovalCode());
            LOGGER.info("cardReloadResp.getLatestBalance() = " + cardReloadResp.getLatestBalance());

            //Ghi log giao dịch
            if (cardReloadResp.getResponseCode().equalsIgnoreCase("000")) {
                //Giao dịch thành công
                result = "00" + "#" + cwRefNo + "#" + cardReloadResp.getLatestBalance();
            } else if (cardReloadResp.getResponseCode().equalsIgnoreCase("164")) {
                //Card not active, phan hoi cho KH.
                result = "05";
            } else if (cardReloadResp.getResponseCode().equalsIgnoreCase("009")) {
                //group them trang thai moi cho tra soat.
                result = "04";
            } else {
                //Giao dịch không thành công
                //Cập nhật thông tin giao dịch
                result = "01";
            }
            return result;

        } catch (AxisFault af) {
//            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
//                af.printStackTrace();
//                result = "04";
//                return result;                
//            } else {
//                af.printStackTrace();
//                result = "01";
//                return result;                
//            }
            LOGGER.error(af);
            result = "04";
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            result = "04";
            return result;
        }
    }

    public String cardRewardAdjustment(String xml) {
        ResponseRewardAdjust response = new ResponseRewardAdjust();

        try {
            RequestRewardAdjust req = (RequestRewardAdjust) Xml.unMarshaller(RequestRewardAdjust.class, xml);

            RedeemRewardReqBean redeemRewardReq = new RedeemRewardReqBean();

            String seqNo = String.valueOf(getIdSeqByName("SQ_CWWSREFNO"));
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
            String dt = sdt.format(c.getTime());

            redeemRewardReq.setSequenceNo(seqNo);
            redeemRewardReq.setFi("970429");
            redeemRewardReq.setActInd("3");
            redeemRewardReq.setCrn(req.getCif());
            redeemRewardReq.setPan(req.getPan());
            redeemRewardReq.setPoint(req.getPoint());
            redeemRewardReq.setRewardAction(req.getRewardAction());
            redeemRewardReq.setTxnDesc(req.getTxnDesc());

            LOGGER.info("redeemReward.channel =" + req.getChannel());
            LOGGER.info("redeemReward.txnDesc =" + req.getTxnDesc());

            RedeemRewardRespBean redeemRewardResp = getCardworkWS().rewardAdjustment(redeemRewardReq);

            LOGGER.info("redeemRewardResp.getResponseCode() =" + redeemRewardResp.getResponseCode());
            LOGGER.info("redeemRewardResp.getResponseCode() =" + redeemRewardResp.getResponseDescription());
            LOGGER.info("redeemRewardResp.getSequenceNo() =" + redeemRewardResp.getSequenceNo());

            response.setResponseCode(redeemRewardResp.getResponseCode());
            response.setResponseDescription(redeemRewardResp.getResponseDescription());
            response.setRefNo(redeemRewardResp.getSequenceNo());

            return Xml.Marshaller(response);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    public String cardBalanceAdjustment(String xml) {
        ResponseCardBalanceAdjust response = new ResponseCardBalanceAdjust();

        try {
            RequestCardBalanceAdjust req = (RequestCardBalanceAdjust) Xml.unMarshaller(RequestCardBalanceAdjust.class, xml);

            CardAdjustmentReqBean cardAdjustReq = new CardAdjustmentReqBean();

            String seqNo = String.valueOf(getIdSeqByName("SQ_CWWSREFNO"));
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
            String dt = sdt.format(c.getTime());

            cardAdjustReq.setSequenceNo(seqNo);
            cardAdjustReq.setFi("970429");
            cardAdjustReq.setActInd("3");
            cardAdjustReq.setTermId("01000006");
            cardAdjustReq.setMerchantId("000000120000011");
            cardAdjustReq.setOfficerId("00402");

            cardAdjustReq.setPan(req.getPan());
            cardAdjustReq.setAdjCde(req.getAdjCode());
            cardAdjustReq.setAdjAmt(req.getAmount());
            cardAdjustReq.setBrchCde(req.getBranchCode());
            cardAdjustReq.setRemarks(req.getDescription());

            LOGGER.info("cardAdjust.channel =" + req.getChannel());
            LOGGER.info("cardAdjust.Description =" + req.getDescription());

            CardAdjustmentRespBean cardAdjustResp = getCardworkWS().cardAdjustment(cardAdjustReq);

            LOGGER.info("cardAdjustResp.getResponseCode() =" + cardAdjustResp.getResponseCode());
            LOGGER.info("cardAdjustResp.getResponseCode() =" + cardAdjustResp.getResponseDescription());
            LOGGER.info("cardAdjustResp.getSequenceNo() =" + cardAdjustResp.getSequenceNo());

            response.setResponseCode(cardAdjustResp.getResponseCode());
            response.setResponseDescription(cardAdjustResp.getResponseDescription());
            response.setRefNo(cardAdjustResp.getSequenceNo());

            return Xml.Marshaller(response);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }

    }

    public String cardProfileMaintenance(String pan, String ActionType, String ActionCode) {
        String result = null;  // 00: Thành công, 01: Thất bại, 04: Timeout

        try {
            CardProfileMaintReqBean cardProfileMainReq = new CardProfileMaintReqBean();
            String seqNo = String.valueOf(getIdSeqByName("SQ_CWWSREFNO"));
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
            String dt = sdt.format(c.getTime());

            cardProfileMainReq.setSequenceNo(seqNo);
            cardProfileMainReq.setFi("970429");
            cardProfileMainReq.setActInd("3");

            //first4DigitCardNo = pan.substring(12, 16);
            pan = pan.substring(0, 12) + pan.substring(16);

            cardProfileMainReq.setPan(pan);
            cardProfileMainReq.setActType(ActionType);
            cardProfileMainReq.setActCde(ActionCode);

            LOGGER.info("cardProfileMaintenance.SEQ =" + dt + seqNo);
            LOGGER.info("cardProfileMaintenance.PAN =" + pan);
            LOGGER.info("cardProfileMaintenance.ActionType =" + ActionType);
            LOGGER.info("cardProfileMaintenance.ActionCode =" + ActionCode);

            CardProfileMaintRespBean cardProfileMainResp = getCardworkWS().cardProfileMaintenance(cardProfileMainReq);

            LOGGER.info("cardProfileMainResp.getResponseCode() =" + cardProfileMainResp.getResponseCode());
            LOGGER.info("cardProfileMainResp.getResponseDescription() =" + cardProfileMainResp.getResponseDescription());
            LOGGER.info("cardProfileMainResp.getSequenceNo() =" + cardProfileMainResp.getSequenceNo());

            if (cardProfileMainResp.getResponseCode().equalsIgnoreCase("000")) {
                result = "00";
            } else {
                result = "01";
            }

            return result;
        } catch (AxisFault af) {
            LOGGER.error(af);
            result = "04";
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            result = "04";
            return result;
        }
    }

    public String resetPINCard(String xml) {
        String result = null;  // 00: Thành công, 01: Thất bại, 04: Timeout

        ResponseChangePINCard response = new ResponseChangePINCard();

        try {
            RequestChangePINCard req = (RequestChangePINCard) Xml.unMarshaller(RequestChangePINCard.class, xml);

            PINSelectionReqBean pinSelectionReq = new PINSelectionReqBean();
            String seqNo = String.valueOf(getIdSeqByName("SQ_CWWSREFNO"));
            //Calendar c = Calendar.getInstance();
            //SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
            //String dt = sdt.format(c.getTime());

            //pinSelectionReq.setSequenceNo(dt + seqNo);
            pinSelectionReq.setSequenceNo(seqNo);
            pinSelectionReq.setFi("970429");
            pinSelectionReq.setActInd("3");

            //first4DigitCardNo = pan.substring(12, 16);
            //pan = pan.substring(0, 12) + pan.substring(16);
            pinSelectionReq.setPan(req.getPan());
            pinSelectionReq.setMobileNo(req.getMobileNo());
            pinSelectionReq.setNewPIN(req.getNewPIN());

            LOGGER.info("resetPINCard.SEQ =" + seqNo);
            LOGGER.info("resetPINCard.PAN =" + req.getPan());
            LOGGER.info("resetPINCard.MobileNo =" + req.getMobileNo());
            LOGGER.info("resetPINCard.NewPIN =" + req.getNewPIN());

            PINSelectionRespBean pinSelectionResp = getCardworkWS().PINSelection(pinSelectionReq);

            LOGGER.info("pinSelectionResp.getResponseCode() =" + pinSelectionResp.getResponseCode());
            LOGGER.info("pinSelectionResp.getResponseDescription() =" + pinSelectionResp.getResponseDescription());
            LOGGER.info("pinSelectionResp.getSequenceNo() =" + pinSelectionResp.getSequenceNo());

            response.setResponseCode(pinSelectionResp.getResponseCode());
            response.setResponseDescription(pinSelectionResp.getResponseDescription());
            response.setRefNo(pinSelectionResp.getSequenceNo());

            return Xml.Marshaller(response);
        } catch (AxisFault af) {
            LOGGER.error(af);
            result = "04";
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            result = "04";
            return result;
        }
    }

    private String convertCurrencyCode(String code) {
        String codemapped;

        if (code.equalsIgnoreCase("VND")) {
            codemapped = "704";
        } else {
            codemapped = "704";
        }

        return codemapped;
    }

    private int getIdSeqByName(String seqname) {
        try {
            return Helper.getDBI().getIdSeqByName(seqname);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new PaymentException(ex);
        }
    }

    public Cardworks_PortType getCardworkWS() {
        try {
            CardworksServiceLocator locator = new CardworksServiceLocator();
            locator.setCardworksEndpointAddress(wsurladdress);
            locator.setMaintainSession(true);
            return locator.getCardworks();
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    private static boolean isIntegerValue(BigDecimal bd) {
        return bd.signum() == 0 || bd.scale() <= 0 || bd.stripTrailingZeros().scale() <= 0;
    }

    public ErrorCodeEnum executeDirectDebit(PayByQRCodeRq req) {
        // Setup du lieu default to execute
        ErrorCodeEnum result;
        try {
            // Thuc hien goi qua CW de thuc hien cat tien
            DirectDebitRespBean resultDirectDebit = this.callCWDirectDebit(req);
            // Kiem tra xem goi qua CW duoc ko de thuc hien cau hinh response
            if (resultDirectDebit != null) { // Thuc hien goi qua CW thanh cong
                // kiem tra xem gia tri tra ve cua CW co thanh cong hay ko
                String status = CWConstant.SUCCESSFULLY.equals(resultDirectDebit.getResponseCode()) ? CommonConstant.MASTERQR_STATUS_DIRECTDEBIT_SUCCESS : CommonConstant.MASTERQR_STATUS_DIRECTDEBIT_FAILED;
                req.setStatus(status);
                // cau hinh tham so tra ve cua CW
                req.getDirectDebitRes().setResponseCode(resultDirectDebit.getResponseCode());
                req.getDirectDebitRes().setResponseDescription(resultDirectDebit.getResponseDescription());
                req.getDirectDebitRes().setApprovalCode(resultDirectDebit.getApprovalCode());
            } else { // Ko goi duoc webservice cua CW. Kiem tra log xem nguyen nhan tai sao. Co kha nang network rot
                LOGGER.warn("Fail to call CW Direct debit. --> Check log");
                req.setStatus(CommonConstant.MASTERQR_STATUS_DIRECTDEBIT_FAILED);
                req.getDirectDebitRes().setResponseDescription("Fail to call CW Direct debit. --> Check log");
            }

            // Cap nhat ket qua tra ve cua CW xuong database
            int resultDB = Helper.getDBI().MASTERPASSQR(req, MasterCardQrActionEnum.UPDATE_DIRECTDEBIT);
            // Kiem tra xem CW phan hoi ket qua co thanh cong hay ko
            if (ErrorCodeEnum.COMPLETE_RESPONSE_WITHOUT_ERROR.getValue().equals(req.getDirectDebitRes().getResponseCode())) {
                // CW phan hoi thanh cong va cap nhat database thanh cong
                if (resultDB == 1) {
                    result = ErrorCodeEnum.CARDWORD_OK;
                } else { // CW phan hoi thanh cong nhung cap nhat database that bai
                    result = ErrorCodeEnum.CARDWORD_OK_DBI_FAILED;
                }
            } else { // CW phan hoi ket qua ko thanh cong
                if (req.getDirectDebitRes().getResponseCode() == null) { // Ko goi duoc webservice cua CW
                    result = ErrorCodeEnum.CARDWORD_IS_MAINTANING;
                } else {
                    result = ErrorCodeEnum.get(req.getDirectDebitRes().getResponseCode());
                }
            }
        } catch (Exception e) { // Loi trong qua trinh goi qua webservice CW de thuc hien cat tien khach hang
            LOGGER.error("Exception at executeDirectDebit = [" + e.getMessage() + "], sequenceNo = [" + req.getSequenceNo() + "]");
            result = ErrorCodeEnum.CARDWORD_RETURN_DBI_FAILED;
        }

        return result;
    }

    public ErrorCodeEnum executeCardAdjustment(PayByQRCodeRq req) {
        ErrorCodeEnum result;
        try {
            // cau hinh sequence cho thao tac hoan tien
            String cardAdjSeq = String.valueOf(Helper.getDBI().getIdSeqByName("SEQ_DIRECTDEBIT"));
            req.getCardAdjRes().setCardAdjSequence(cardAdjSeq);
            // Thuc hien goi qua CW de thuc hien hoan tien
            CardAdjustmentRespBean resultOfAdjustment = this.callCWCardAdjustment(req);
            // Kiem tra xem goi qua CW duoc ko de thuc hien cau hinh response
            if (resultOfAdjustment != null) {
                // kiem tra xem gia tri tra ve cua CW co thanh cong hay ko
                String status = "000".equals(resultOfAdjustment.getResponseCode()) ? CommonConstant.MASTERQR_STATUS_REFUND_SUCCESS : CommonConstant.MASTERQR_STATUS_REFUND_FAILED;
                req.setStatus(status);
                // cau hinh tham so tra ve cua CW
                req.getCardAdjRes().setResponseCode(resultOfAdjustment.getResponseCode());
                req.getCardAdjRes().setResponseDescription(resultOfAdjustment.getResponseDescription());
            } else { // Ko goi duoc webservice cua CW. Kiem tra log xem nguyen nhan tai sao. Co kha nang network rot
                req.setStatus(CommonConstant.MASTERQR_STATUS_REFUND_FAILED);
                req.getCardAdjRes().setResponseDescription("Failed to call CW Card adjustment.");
            }
            // Cap nhat ket qua tra ve cua CW xuong database
            int resultDB = Helper.getDBI().MASTERPASSQR(req, MasterCardQrActionEnum.UPDATE_CARDADJUSTMENT);
            // Kiem tra xem CW phan hoi ket qua co thanh cong hay ko
            if (ErrorCodeEnum.COMPLETE_RESPONSE_WITHOUT_ERROR.getValue().equals(req.getCardAdjRes().getResponseCode())) {
                // CW phan hoi thanh cong va cap nhat database thanh cong
                if (resultDB == 1) {
                    result = ErrorCodeEnum.CARDWORD_OK;
                } else { // CW phan hoi thanh cong nhung cap nhat database that bai
                    result = ErrorCodeEnum.CARDWORD_OK_DBI_FAILED;
                }
            } else { // CW phan hoi ket qua ko thanh cong
                if (req.getCardAdjRes().getResponseCode() == null) { // Ko goi duoc webservice cua CW
                    result = ErrorCodeEnum.CARDWORD_IS_MAINTANING;
                } else {
                    result = ErrorCodeEnum.get(req.getCardAdjRes().getResponseCode());
                }
            }
        } catch (Exception e) { // Loi trong qua trinh goi qua webservice CW de thuc hien hoan tien khach hang
            LOGGER.error("EXCEPTION AT CARD ADJUSTMENT = [" + e.getMessage() + "]");
            result = ErrorCodeEnum.CARDWORD_RETURN_DBI_FAILED;
        }
        return result;
    }

    private DirectDebitRespBean callCWDirectDebit(PayByQRCodeRq req) {
        try {
            String localDate = CommonUtils.fromISO8601UTCFormat(req.getDateTime(), "MMyy");
            String localTime = CommonUtils.fromISO8601UTCFormat(req.getDateTime(), "HHmmss");

            DirectDebitReqBean directDebitReq = new DirectDebitReqBean();
            directDebitReq.setSequenceNo(req.getSequenceNo());
            directDebitReq.setFi(CardwordConstant.FI);
            directDebitReq.setTrxDate(localDate);
            directDebitReq.setTrxTime(localTime);
            directDebitReq.setActInd(CardwordConstant.ACCOUNT_INDICATOR);
            directDebitReq.setPan(req.getLoc4Digit());
            directDebitReq.setExpiryDate(req.getSenderMSVSCardInfo().getExpi_date());
            directDebitReq.setAmount((req.getAmount().contains(".")) ? req.getAmount() : req.getAmount() + ".00");
            directDebitReq.setCurrencyCode(req.getCcyCode());
            directDebitReq.setMid(CardwordConstant.MID_CARDDIRECTDEBIT);
            directDebitReq.setTid(CardwordConstant.TID_CARDDIRECTDEBIT);
            directDebitReq.setReferenceNo(req.getRefNo());
            directDebitReq.setMerName("MPQR_" + req.getMerCardName());

            return getCardworkWS().directDebit(directDebitReq);
        } catch (RemoteException ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    public ErrorCodeEnum executeDirectDebitForVisa(MVISAQRRQ req) {
        ErrorCodeEnum result;
        try {
            DirectDebitRespBean resultDirectDebit = this.callCWDirectDebitForVisa(req);
            if (resultDirectDebit != null) {
                // kiem tra xem gia tri tra ve cua CW co thanh cong hay ko
                String status = CWConstant.SUCCESSFULLY.equals(resultDirectDebit.getResponseCode()) ? CommonConstant.MASTERQR_STATUS_DIRECTDEBIT_SUCCESS : CommonConstant.MASTERQR_STATUS_DIRECTDEBIT_FAILED;
                req.setStatus(status);
                // cau hinh tham so tra ve cua CW
                req.getDirectDebitRes().setResponseCode(resultDirectDebit.getResponseCode());
                req.getDirectDebitRes().setResponseDescription(resultDirectDebit.getResponseDescription());
                req.getDirectDebitRes().setApprovalCode(resultDirectDebit.getApprovalCode());
            } else { // Ko goi duoc webservice cua CW. Kiem tra log xem nguyen nhan tai sao. Co kha nang network rot
                LOGGER.warn("Fail to call CW Direct debit. --> Check log");
                req.setStatus(CommonConstant.MASTERQR_STATUS_DIRECTDEBIT_FAILED);
                req.getDirectDebitRes().setResponseDescription("Fail to call CW Direct debit. --> Check log");
            }

            // Cap nhat ket qua tra ve cua CW xuong database
            int resultDB = Helper.getDBI().VISAQR(req, MasterCardQrActionEnum.UPDATE_DIRECTDEBIT);
            // Kiem tra xem CW phan hoi ket qua co thanh cong hay ko
            if (ErrorCodeEnum.COMPLETE_RESPONSE_WITHOUT_ERROR.getValue().equals(req.getDirectDebitRes().getResponseCode())) {
                // CW phan hoi thanh cong va cap nhat database thanh cong
                if (resultDB == 1) {
                    result = ErrorCodeEnum.CARDWORD_OK;
                } else { // CW phan hoi thanh cong nhung cap nhat database that bai
                    result = ErrorCodeEnum.CARDWORD_OK_DBI_FAILED;
                }
            } else { // CW phan hoi ket qua ko thanh cong
                if (req.getDirectDebitRes().getResponseCode() == null) { // Ko goi duoc webservice cua CW
                    result = ErrorCodeEnum.CARDWORD_IS_MAINTANING;
                } else {
                    result = ErrorCodeEnum.get(req.getDirectDebitRes().getResponseCode());
                }
            }
        } catch (Exception e) { // Loi trong qua trinh goi qua webservice CW de thuc hien cat tien khach hang
            LOGGER.error("Exception at executeDirectDebit = [" + e.getMessage() + "], sequenceNo = [" + req.getSequenceNo() + "]");
            result = ErrorCodeEnum.CARDWORD_RETURN_DBI_FAILED;
        }

        return result;
    }

    private DirectDebitRespBean callCWDirectDebitForVisa(MVISAQRRQ req) {
        try {
            // tach du lieu cho 2 bien localDate va localTime tu du lieu thoi gian nhan duoc
            String localDate = CommonUtils.fromISO8601UTCFormat(req.getLocalTransDate(), "yyyy-MM-dd'T'HH:mm:ss", "MMyy");
            String localTime = CommonUtils.fromISO8601UTCFormat(req.getLocalTransDate(), "yyyy-MM-dd'T'HH:mm:ss", "HHmmss");

            // build du lieu de goi qua CW
            DirectDebitReqBean directDebitReq = new DirectDebitReqBean();
            directDebitReq.setSequenceNo(req.getSequenceNo());
            directDebitReq.setFi(CardwordConstant.FI);
            directDebitReq.setTrxDate(localDate);
            directDebitReq.setTrxTime(localTime);
            directDebitReq.setActInd(CardwordConstant.ACCOUNT_INDICATOR);
            directDebitReq.setPan(req.getLoc4digit());
            directDebitReq.setExpiryDate(req.getSenderMSVSCardInfo().getExpi_date());
            // kiem tra xem co phi di kem giao dich hay ko
            if (req.getTransFeeAmt() != null && !req.getTransFeeAmt().isEmpty()) {
                // chuyen doi ve dinh dang double de thuc hien tinh tong phan tien va phan phi
                BigDecimal amount = new BigDecimal(req.getAmount());
                BigDecimal fee = new BigDecimal(req.getTransFeeAmt());
                BigDecimal total = amount.add(fee);
                DecimalFormat df = new DecimalFormat("#00.00");
                // theo yeu cau cua CW, cau hinh so tien ve dinh dang .00
                directDebitReq.setAmount(df.format(total));
            } else {
                // theo yeu cau cua CW, kiem tra xem cau hinh so tien co dung dinh dang .00 hay ko. Neu ko thi cau hinh cho dung
                directDebitReq.setAmount((req.getAmount().contains(".")) ? req.getAmount() : req.getAmount() + ".00");
            }
            directDebitReq.setCurrencyCode(req.getTransCurrencyCode());
            directDebitReq.setMid(CardwordConstant.MID_CARDDIRECTDEBIT);
            directDebitReq.setTid(CardwordConstant.TID_CARDDIRECTDEBIT);
            directDebitReq.setReferenceNo(req.getRefNo());
            directDebitReq.setMerName("VSQR_" + req.getCardAccName());

            // chinh thuc thuc hien goi qua CW
            return getCardworkWS().directDebit(directDebitReq);
        } catch (RemoteException ex) { // co loi khi thuc hien goi qua CW
            LOGGER.error(ex);
            return null;
        }
    }

    public ErrorCodeEnum executeCardAdjustmentForVisa(MVISAQRRQ req) {
        ErrorCodeEnum result;
        try {
            // cau hinh sequence cho thao tac hoan tien
            String cardAdjSeq = String.valueOf(Helper.getDBI().getIdSeqByName("SEQ_DIRECTDEBIT"));
            req.getCardAdjRes().setCardAdjSequence(cardAdjSeq);
            // Thuc hien goi qua CW de thuc hien hoan tien
            CardAdjustmentRespBean resultOfAdjustment = this.callCWCardAdjustmentForVisa(req);
            // Kiem tra xem goi qua CW duoc ko de thuc hien cau hinh response
            if (resultOfAdjustment != null) {
                // kiem tra xem gia tri tra ve cua CW co thanh cong hay ko
                String status = "000".equals(resultOfAdjustment.getResponseCode()) ? CommonConstant.MASTERQR_STATUS_REFUND_SUCCESS : CommonConstant.MASTERQR_STATUS_REFUND_FAILED;
                req.setStatus(status);
                // cau hinh tham so tra ve cua CW
                req.getCardAdjRes().setResponseCode(resultOfAdjustment.getResponseCode());
                req.getCardAdjRes().setResponseDescription(resultOfAdjustment.getResponseDescription());
            } else { // Ko goi duoc webservice cua CW. Kiem tra log xem nguyen nhan tai sao. Co kha nang network rot
                req.setStatus(CommonConstant.MASTERQR_STATUS_REFUND_FAILED);
                req.getCardAdjRes().setResponseDescription("Failed to call CW Card adjustment.");
            }

            // Cap nhat ket qua tra ve cua CW xuong database
            int resultDB = Helper.getDBI().VISAQR(req, MasterCardQrActionEnum.UPDATE_CARDADJUSTMENT);
            // Kiem tra xem CW phan hoi ket qua co thanh cong hay ko
            if (ErrorCodeEnum.COMPLETE_RESPONSE_WITHOUT_ERROR.getValue().equals(req.getCardAdjRes().getResponseCode())) {
                // CW phan hoi thanh cong va cap nhat database thanh cong
                if (resultDB == 1) {
                    result = ErrorCodeEnum.CARDWORD_OK;
                } else { // CW phan hoi thanh cong nhung cap nhat database that bai
                    result = ErrorCodeEnum.CARDWORD_OK_DBI_FAILED;
                }
            } else { // CW phan hoi ket qua ko thanh cong
                if (req.getCardAdjRes().getResponseCode() == null) { // Ko goi duoc webservice cua CW
                    result = ErrorCodeEnum.CARDWORD_IS_MAINTANING;
                } else {
                    result = ErrorCodeEnum.get(req.getCardAdjRes().getResponseCode());
                }
            }
        } catch (Exception e) { // Loi trong qua trinh goi qua webservice CW de thuc hien hoan tien khach hang
            LOGGER.error("EXCEPTION AT CARD ADJUSTMENT = [" + e.getMessage() + "]");
            result = ErrorCodeEnum.CARDWORD_RETURN_DBI_FAILED;
        }
        return result;
    }

    private CardAdjustmentRespBean callCWCardAdjustmentForVisa(MVISAQRRQ req) {
        try {
            CardAdjustmentReqBean cardAdjustmentReq = new CardAdjustmentReqBean();
            cardAdjustmentReq.setSequenceNo(req.getCardAdjRes().getCardAdjSequence());
            cardAdjustmentReq.setFi(CardwordConstant.FI);
            cardAdjustmentReq.setActInd(CardwordConstant.ACCOUNT_INDICATOR);
            cardAdjustmentReq.setPan(req.getLoc4digit());
            // kiem tra xem co phi di kem giao dich hay ko
            if (req.getTransFeeAmt() != null && !req.getTransFeeAmt().isEmpty()) {
                // chuyen doi ve dinh dang double de thuc hien tinh tong phan tien va phan phi
                BigDecimal amount = new BigDecimal(req.getAmount());
                BigDecimal fee = new BigDecimal(req.getTransFeeAmt());
                BigDecimal total = amount.add(fee);
                DecimalFormat df = new DecimalFormat("#00.00");
                // theo yeu cau cua CW, cau hinh so tien ve dinh dang .00
                cardAdjustmentReq.setAdjAmt(df.format(total));
            } else {
                // theo yeu cau cua CW, kiem tra xem cau hinh so tien co dung dinh dang .00 hay ko. Neu ko thi cau hinh cho dung
                cardAdjustmentReq.setAdjAmt((req.getAmount().contains(".")) ? req.getAmount() : req.getAmount() + ".00");
            }
            cardAdjustmentReq.setAdjCde(CardwordConstant.ADJUST_CODE);
            cardAdjustmentReq.setBrchCde(req.getSenderMSVSCardInfo().getBrch_cde());
            cardAdjustmentReq.setMerchantId(CardwordConstant.MID_CARDADJUSTMENT);
            cardAdjustmentReq.setTermId(CardwordConstant.TID_CARDADJUSTMENT);
            cardAdjustmentReq.setOfficerId(CardwordConstant.OFFICERID);
            cardAdjustmentReq.setRemarks("Revert VSQR_" + req.getCardAccName());

            return getCardworkWS().cardAdjustment(cardAdjustmentReq);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    private CardAdjustmentRespBean callCWCardAdjustment(PayByQRCodeRq req) {
        try {
            CardAdjustmentReqBean cardAdjustmentReq = new CardAdjustmentReqBean();
            cardAdjustmentReq.setSequenceNo(req.getCardAdjRes().getCardAdjSequence());
            cardAdjustmentReq.setFi(CardwordConstant.FI);
            cardAdjustmentReq.setActInd(CardwordConstant.ACCOUNT_INDICATOR);
            cardAdjustmentReq.setPan(req.getLoc4Digit());
            cardAdjustmentReq.setAdjAmt((req.getAmount().contains(".")) ? req.getAmount() : req.getAmount() + ".00");
            cardAdjustmentReq.setAdjCde(CardwordConstant.ADJUST_CODE);
            cardAdjustmentReq.setBrchCde(req.getSenderMSVSCardInfo().getBrch_cde());
            cardAdjustmentReq.setMerchantId(CardwordConstant.MID_CARDADJUSTMENT);
            cardAdjustmentReq.setTermId(CardwordConstant.TID_CARDADJUSTMENT);
            cardAdjustmentReq.setOfficerId(CardwordConstant.OFFICERID);
            cardAdjustmentReq.setRemarks("Revert MPQR_" + req.getMerCardName());

            return getCardworkWS().cardAdjustment(cardAdjustmentReq);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

   public CardActivationRespBean callCwKichHoatThe02(KichHoatTheInfo info) {
        try {
            CardActivationRespBean resp = new CardActivationRespBean();
            CardActivationReqBean req = new CardActivationReqBean();
            req.setActInd(CWACTIND);
            req.setAtvChannel(KHTCHANNEL);
            req.setExpiryDate(info.getExpiryDate());
            req.setFi(CWFI);
            req.setPan(info.getLoc() + info.getLast4Digits());
            req.setSequenceNo(info.getTransId());
            Cardworks_PortType cwPort = getCardworkWS();
            if (cwPort != null) {
                LOGGER.info("Call KHT CW --> expiryDate = [" + req.getExpiryDate() + "], Pan = [" + req.getPan() + "], Sequence = [" + req.getSequenceNo() + "]");
                
                resp = cwPort.cardActivation(req);
                
                if (CardwordMessage.ErrorCodeEnum.COMPLETE_RESPONSE_WITHOUT_ERROR.getValue().equals(resp.getResponseCode())) {
                    LOGGER.warn("CW return okie --> response code [" + resp.getResponseCode() + "], error description = [" + resp.getResponseDescription() + "], sequence = [" + resp.getSequenceNo() + "]");
                } else {
                    LOGGER.warn("CW return error --> response code [" + resp.getResponseCode() + "], error description = [" + resp.getResponseDescription() + "], sequence = [" + resp.getSequenceNo() + "]");
                }
            } else {
                LOGGER.error("Ko the connect den CW, link = [" + wsurladdress + "]");
            }
            
            return resp;
        } catch (RemoteException e) {
            LOGGER.error(e);
            CardActivationRespBean resp02 = new CardActivationRespBean();
            resp02.setResponseCode(CommonConstant.SERVICE_PARTNER_FAILED);
            resp02.setResponseDescription("Co loi khi goi service cardword.");
            return resp02;
        }

    }

    public boolean callCwKichHoatThe(KichHoatTheInfo info) {
        boolean result = false;
        try {
            CardActivationReqBean req = new CardActivationReqBean();
            req.setActInd(CWACTIND);
            req.setAtvChannel(KHTCHANNEL);
            req.setExpiryDate(info.getExpiryDate());
            req.setFi(CWFI);
            req.setPan(info.getLoc() + info.getLast4Digits());
            req.setSequenceNo(info.getTransId());
            Cardworks_PortType cwPort = getCardworkWS();
            if (cwPort != null) {
                LOGGER.info("Call KHT CW --> expiryDate = [" + req.getExpiryDate() + "], Pan = [" + req.getPan() + "], Sequence = [" + req.getSequenceNo() + "]");
                CardActivationRespBean resp = cwPort.cardActivation(req);
                info.setResponseCode(resp.getResponseCode());
                info.setResponseDescription(resp.getResponseDescription());
                info.setSequence(resp.getSequenceNo());
                if (CardwordMessage.ErrorCodeEnum.COMPLETE_RESPONSE_WITHOUT_ERROR.getValue().equals(resp.getResponseCode())) {
                    result = true;
                    LOGGER.warn("CW return okie --> response code [" + resp.getResponseCode() + "], error description = [" + resp.getResponseDescription() + "], sequence = [" + resp.getSequenceNo() + "]");
                } else {
                    LOGGER.warn("CW return error --> response code [" + resp.getResponseCode() + "], error description = [" + resp.getResponseDescription() + "], sequence = [" + resp.getSequenceNo() + "]");
                }
            } else {
                LOGGER.error("Ko the connect den CW, link = [" + wsurladdress + "]");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }

    public String cardAdjustment(String xml) {
        try {
            CardAdjustmentRes result = new CardAdjustmentRes();
            CardAdjustmentReq cardAdjReq = (CardAdjustmentReq) Xml.unMarshaller(CardAdjustmentReq.class, xml);
            if (cardAdjReq != null && !cardAdjReq.isInvalid()) {
                result = cardAdjustment(cardAdjReq);
            } else {
                if (cardAdjReq != null) {
                    result.setRefNo(cardAdjReq.getRefNo());
                }
                result.setErrorCode("12");
                result.setErrorMsg("Invalid request message");
            }
            return Xml.Marshaller(result);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return "<CARDADJUSTMENTRES><ErrorCode>99</ErrorCode><ErrorMsg>SYSTEM IS ERROR</ErrorMsg></CARDADJUSTMENTRES>";
        }

    }

    public CardAdjustmentRes cardAdjustment(CardAdjustmentReq cardAdjReq) {
        CardAdjustmentRes result = new CardAdjustmentRes();
        try {
            IDBI dbi = Helper.getDBI();
            result = dbi.cardAdjustment(cardAdjReq, null, DBActionEnum.INSERT);

            if (result.getSequenceNo() != null && !result.getSequenceNo().isEmpty()) {
                // Cau hinh lai sequenceNo cho request de cap nhat dbi
                cardAdjReq.setSequenceNo(result.getSequenceNo());

                CardAdjustmentReqBean cardAdjustmentReq = new CardAdjustmentReqBean();
                cardAdjustmentReq.setSequenceNo(result.getSequenceNo());
                cardAdjustmentReq.setFi(cardAdjReq.getFi());
                cardAdjustmentReq.setActInd(cardAdjReq.getActInd());
                cardAdjustmentReq.setPan(cardAdjReq.getPan());
                cardAdjustmentReq.setAdjAmt(cardAdjReq.getAdjAmt());
                cardAdjustmentReq.setAdjCde(cardAdjReq.getAdjCde());
                cardAdjustmentReq.setBrchCde(cardAdjReq.getBrchCde());
                cardAdjustmentReq.setMerchantId(cardAdjReq.getMerchantId());
                cardAdjustmentReq.setTermId(cardAdjReq.getTermId());
                cardAdjustmentReq.setOfficerId(cardAdjReq.getOfficerId());
                cardAdjustmentReq.setRemarks(cardAdjReq.getRemarks());
                CardAdjustmentRespBean response = getCardworkWS().cardAdjustment(cardAdjustmentReq);
                if (response != null) {
                    LOGGER.warn("response from CW. Code = [" + response.getResponseCode() + "], Desc = [" + response.getResponseDescription() + "]");
                    result.setErrorCode(response.getResponseCode());
                    result.setErrorMsg(response.getResponseDescription());
                } else {
                    LOGGER.warn("response from CW = [" + response + "]");
                    result.setErrorCode("02");
                    result.setErrorMsg("Hold transaction lai, cho tra soat");
                }
                result = dbi.cardAdjustment(null, result, DBActionEnum.UPDATE);
            } else {
                LOGGER.warn("Cannot execute card adjustment. Code = [" + result.getErrorCode() + "], Desc = [" + result.getErrorMsg() + "]");
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            result.setErrorCode("99");
            result.setErrorMsg("SYSTEM IS ERROR");
        }
        return result;
    }

}
