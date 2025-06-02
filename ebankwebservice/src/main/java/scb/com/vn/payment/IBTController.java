/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;

import java.math.BigDecimal;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Level;

import scb.com.vn.controller.Fcubs;
import scb.com.vn.dbi.dto.NAPAS_IBT;
import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;
import java.util.TimeZone;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.transfer.internalapp.Transfer247Request;
import scb.com.vn.common.model.transfer.internalapp.Transfer247Response;
import scb.com.vn.common.model.transfer.napas.TransferMoney247DetailReq;
import scb.com.vn.common.model.transfer.napas.TransferMoney247DetailRes;
import scb.com.vn.dbi.dto.VwCustAccountNew;
import scb.com.vn.enumUtils.ResponseCodeEnum;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.SiUtils;
import scb.com.vn.utility.VNCharacterUtils;

/**
 *
 * @author LyDTY Createdate: 28/May/2014 TypeNumber: CARD/ ACCOUNT Channel:
 * EBANK/ ATM/ SMS/POS/MB/COUNTER: QUẦY
 */
public class IBTController {

    private static final Logger logger = Logger.getLogger(IBTController.class);

    String ProductFromSCB_EBANK = Configuration.getProperty("fcubs.smartlink.product_fromscb");
    String ProductFromSCB_MOBILE = Configuration.getProperty("fcubs.producttransfer.mobile.ibt");
    String ProductFromSCB_COUNTER1 = Configuration.getProperty("fcubs.smartlink.product_fromscb_COUNTER1");
    String ProductFromSCB_COUNTER2 = Configuration.getProperty("fcubs.smartlink.product_fromscb_COUNTER2");
    String ProductFromSCB_COUNTER3 = Configuration.getProperty("fcubs.smartlink.product_fromscb_COUNTER3");
    String ProductToSCB = Configuration.getProperty("fcubs.smartlink.product");
    String useridfcubs_EB = Configuration.getProperty("fcubs.userid");
    String useridfcubs_MB = Configuration.getProperty("fcubs.userid.mobile");
    String listBankTPTVTC = Configuration.getProperty("ws.smartlink.TPTVTC.bankid");
    String listAccountTPTVTC = Configuration.getProperty("ws.smartlink.TPTVTC.account");
    String listAccountFreeFee = Configuration.getProperty("ws.smartlink.AccountFreeFee.list");
    String listCustFreeFee = Configuration.getProperty("ws.smartlink.CustFreeFee.list");
    String listAccClassFreeFee = Configuration.getProperty("ws.smartlink.AccClass.list");
    //ADD NEW FOR IBT SOCKET
    //String GLACCOUNT=Configuration.getProperty("fcubs.smartlink.COUNTER1.GLACCOUNT");
    String Acquiring_Code = "157979";
    String ProductTCH = Configuration.getProperty("fcubs.producttransfer.tch.247");
    String AccountTCH = Configuration.getProperty("fcubs.tch.listAccount");
    private static final String productKieuhoiExternalSCB = Configuration.getProperty("fcubs.producttransfer.kieuhoi.external");
    private static final String productKieuhoiExternalSCB247 = Configuration.getProperty("fcubs.producttransfer.kieuhoi.external247");

    String NapasAccountISS = Configuration.getProperty("fcubs.GLPayment.external.napas");
    String NapasAccountBNB = Configuration.getProperty("247.napas.account");
    // String listBlackListAccount = Configuration.getProperty("247.destaccount.blacklist");
    //  String listBlackListCard = Configuration.getProperty("247.destcard.blacklist");

    public String transfeFromSCB(String FromNumber,
            String TypeFromNumber,
            String FullName,
            String ToNumber,
            String TypeToNumber,
            String BenID,
            BigDecimal Amount,
            String CCY,
            String channel,
            String Desc, // TAI QUAY : DESC#FEE#VAT#user_maker#KSV#idcardName#idcardAddress#idcard#idcardDob#branchcard; MB: DESC#FEE#VAT#FEETC#VATCN;
            String TermID,
            String CardAcceptor,
            String TypeFunction) throws RemoteException, Exception {
        CardAcceptor = "SCB                      HCM         VNM";
        String resultQue = transfeFromSCBv2(FromNumber, TypeFromNumber, FullName, ToNumber, TypeToNumber, BenID, Amount, CCY, channel, Desc, TermID, CardAcceptor, TypeFunction);
        /*  if (TypeFunction.equals("TRN")) {

            if (resultQue != null) {
                String[] listQUE = resultQue.split("#");
                if (listQUE[0].equals("00")) {
                    return transfeFromSCBv2(FromNumber, TypeFromNumber, FullName, ToNumber, TypeToNumber, BenID, Amount, CCY, channel, Desc, TermID, CardAcceptor, TypeFunction);
                }
            }
        }*/
        return resultQue;
    }

    public String transfeFromSCBv1(String FromNumber,
            String TypeFromNumber,
            String FullName,
            String ToNumber,
            String TypeToNumber,
            String BenID,
            BigDecimal Amount,
            String CCY,
            String channel,
            String Desc, // TAI QUAY : DESC#FEE#VAT#user_maker#KSV#idcardName#idcardAddress#idcard#idcardDob#branchcard#ID; MB: DESC#FEE#VAT#FEETC#VATCN#PRODUCT; GW: DESC#ID
            String TermID,
            String CardAcceptor,
            String TypeFunction) throws RemoteException, Exception {

        //transferFCUBSWithTimeOutFeeAndVAT : TAI QUAY CK;COUNTER2
        //transferFCUBSCash : TAI QUAY TM;COUNTER1
        String Transdate = "";
        String AuditNumber = "";
        String Merchant_Type = "";
        String Authorization_Code = "";
        String ResponseCode = "";
        String ProcessingCode = "";
        String RefCORE = "";
        String AccountOfCust = FromNumber;
        String DESTNAME = "";
        String CustNo = "";
        String cust_account_class = "";
        String RefCORE_REFUND = "";
        String SMLAccount = "";
        String Sett_date_F15 = "";
        BigDecimal Fee = BigDecimal.valueOf(0);
        BigDecimal Vat = BigDecimal.valueOf(0);
        BigDecimal finFee = BigDecimal.valueOf(0);
        BigDecimal finFeeVAT = BigDecimal.valueOf(0);

        // ADD for COUNTER
        String user_maker = "";
        String user_checker = "";
        String idcardName = "";
        String idcardAddress = "";
        String Address = "";
        String idcard = "";
        String idcardDob = "";
        String branchCust = "";
        String branchCounter = "";
        String x_ref = "";
        String Status;
        String IDREQUEST = "";
        try {

            //check the
            if (TypeToNumber.equals("CARD")) {
                String BIN = ToNumber.substring(0, 6);
                String strListBINCard = Configuration.getProperty("scb.listtypecard");

                if (strListBINCard.contains(BIN)) {
                    return "25";
                }
            }
            String[] arrDesc = Desc.split("#");
            Desc = arrDesc[0];
            if (!checkSpecialDigit(Desc)) {
                return "-1";
            }

            if (channel.equals("CH")) {
                if (arrDesc.length >= 2) {
                    IDREQUEST = arrDesc[1] == null ? "" : arrDesc[1];
                    if (TypeFunction.equals("TRN") & IDREQUEST != null) {
                        /*
                        int check = Helper.getDBI().IBT_checkCounter(IDREQUEST);
                        if(check>0)
                        {
                            logger.info("GIAO DICH DA TON TAI:"+IDREQUEST);
                            return "18";
                        }*/
                    }
                }
            }
            if (channel.equalsIgnoreCase("MB") || channel.equals("EBANK")) {
                if (arrDesc.length >= 3) {
                    Fee = BigDecimal.valueOf(Long.valueOf(arrDesc[1]));
                    Vat = BigDecimal.valueOf(Long.valueOf(arrDesc[2]));
                }
                if (arrDesc.length >= 5) {
                    finFee = BigDecimal.valueOf(Long.valueOf(arrDesc[3]));
                    finFeeVAT = BigDecimal.valueOf(Long.valueOf(arrDesc[4]));

                }
                if (arrDesc.length >= 6) {
                    ProductFromSCB_MOBILE = arrDesc[5];
                }
            }
            if (channel.contains("COUNTER")) {
                if (arrDesc.length >= 5) {
                    user_maker = arrDesc[3];
                    user_checker = arrDesc[4];
                    logger.info("[IBT] user_maker:" + user_maker + "-user_checker:" + user_checker + "-toaccount:" + ToNumber);
                }
                // ADD for COUNTER
                if (arrDesc.length >= 11) {
                    // COUNTER1;
                    idcardName = arrDesc[5];
                    Address = arrDesc[6];
                    idcard = arrDesc[7];
                    idcardDob = arrDesc[8];
                    idcardAddress = arrDesc[9];
                    branchCounter = arrDesc[10];
                }
                if (arrDesc.length >= 12) {
                    IDREQUEST = arrDesc[11] == null ? "" : arrDesc[11];
                    if (TypeFunction.equals("TRN") & IDREQUEST != null) {
                        int check = Helper.getDBI().IBT_checkCounter(IDREQUEST);
                        if (check > 0) {
                            logger.info("GIAO DICH DA TON TAI:" + IDREQUEST);
                            return "18";
                        }
                    }
                }
            }
            String resultCheck = "00#00#00";
            if (!channel.endsWith("COUNTER1") & !channel.endsWith("COUNTER3")) {
                logger.info("CheckInputFromSCB:" + FromNumber + "-tonumber:" + ToNumber);
                resultCheck = CheckInputFromSCB(FromNumber, TypeFromNumber, FullName, ToNumber, TypeToNumber,
                        Amount, channel, TypeFunction, BenID);
                logger.info("kQ CheckInputFromSCB:" + resultCheck);
            }
            String[] resultArr = resultCheck.split("#");
            Status = resultArr[0];
            if (Status.equals("00")) {
                CustNo = resultArr[1];
                cust_account_class = resultArr[2];

                //Check exist tonumber
                if (TypeToNumber.equals("CARD")) {
                    BenID = "";
                }
                DecimalFormat formater = new DecimalFormat("0.00");
                //String strAmount= Helper.addExtraChar(formater.format(Amount).replace(",", ""),12,"0");
                String strAmount = Helper.addExtraChar(formater.format(Amount).replace(".", "").replace(",", ""), 12, "0");
                Date currentdate = new Date();
                SimpleDateFormat sdfDestination = new SimpleDateFormat("MMddHHmmss");
                Transdate = sdfDestination.format(currentdate);
                SimpleDateFormat fDateF37 = new SimpleDateFormat("DDDHH");
                SimpleDateFormat fDateF37Y = new SimpleDateFormat("Y");
                String Ref_no_F37 = fDateF37Y.format(currentdate).substring(3, 4) + fDateF37.format(currentdate); //add by Lydty,14/Aug/2017 
                if (channel.equals("EBANK") || channel.equals("CH")) {
                    Merchant_Type = "7399";
                } else if (channel.equals("ATM")) {
                    Merchant_Type = "6011";
                } else if (channel.equals("POS")) {
                    Merchant_Type = "6012";
                } else if (channel.equals("MB")) {
                    Merchant_Type = "7299";
                } else if (channel.equals("SMS")) {
                    Merchant_Type = "6023";
                } else if (channel.equals("COUNTER")) {
                    // Merchant_Type = "5999";
                    Merchant_Type = "7399";
                } else if (channel.equals("COUNTER1") || channel.equals("COUNTER2") || channel.equals("COUNTER3")) {
                    //TAI QUAY TIEN MAT
                    //  Merchant_Type = "5999";
                    Merchant_Type = "7399";
                }
                AuditNumber = Helper.getDBI().getAuditNumber(); //Audit check
                Ref_no_F37 = Ref_no_F37 + AuditNumber; //add by Lydty,14/Aug/2017 
                if (TypeFunction.equals("QUE")) {

                    if (TypeFromNumber.equals("CARD")) {
                        if (TypeToNumber.equals("CARD")) {
                            ProcessingCode = "430000";
                        } else {
                            ProcessingCode = "430020";
                        }
                    } else {
                        if (TypeToNumber.equals("CARD")) {
                            //ProcessingCode = "432000";
                            ProcessingCode = "430000";
                        } else {
                            ProcessingCode = "432020";
                        }
                    }
                    strAmount = "000000000000";
                    String paramCheckToNumber = paramCheckToNumber = "0200|" + FromNumber + "|" + ProcessingCode + "|" + strAmount + "|" + Transdate + "|"
                            + AuditNumber + "|" + Merchant_Type + "|" + Acquiring_Code + "|" + TermID + "|" + CardAcceptor + "|"
                            + ToNumber + "|" + Desc;
                    String dataSignCheck = "0200" + FromNumber + ProcessingCode + strAmount + Transdate
                            + AuditNumber + Merchant_Type + Acquiring_Code + TermID + ToNumber;
                    if (TypeToNumber.equals("ACCOUNT")) {
                        dataSignCheck = dataSignCheck + BenID;
                        paramCheckToNumber = paramCheckToNumber + "|" + BenID;
                    }
                    String Signature = Helper.createRequestSignature(dataSignCheck, Configuration.privateKeySCB_2_SML);
                    paramCheckToNumber = paramCheckToNumber + "|" + Ref_no_F37 + "||" + Signature;

                    logger.info("Request QUE Napas:" + paramCheckToNumber);
                    //String resultCheckToNumber = Helper.callwsSmartlink(paramCheckToNumber, 40);
                    String resultCheckToNumber = Helper.callwsIBTNAPAS(paramCheckToNumber, 40);
                    logger.info("Reponse QUE NAPAS: " + cheThe(resultCheckToNumber, "..."));
                    if (resultCheckToNumber == null) {
                        Status = "07"; // Truy van SML time out
                    } else {
                        if (resultCheckToNumber.equals("TIMEOUT") || resultCheckToNumber.equals("REVERT")) {
                            Status = "07"; // Truy van SML time out
                        } else {
                            String[] arrResultCheckToNumber = resultCheckToNumber.replace("|", "#").split("#");
                            //Check Sinature
                            boolean isCheckSignature = Helper.verifyResponseSignature(resultCheckToNumber.split("\\|"), Configuration.pubKeySmartlink);

                            ResponseCode = arrResultCheckToNumber[9];
                            Authorization_Code = arrResultCheckToNumber[8];
                            DESTNAME = arrResultCheckToNumber[12];
                            if (TypeToNumber.equals("CARD")) {
                                Sett_date_F15 = arrResultCheckToNumber[14].toString();
                            } else {
                                Sett_date_F15 = arrResultCheckToNumber[15].toString();
                            }
                            //Insert Log Query

                            if (!isCheckSignature) {
                                Status = "99"; // Khong dung signature 
                            } else if (!ResponseCode.equals("00")) {
                                //Status = ResponseCode;
                                if (ResponseCode.equals("01")) {
                                    Status = "08"; // TK/THE DICH KHONG HOP LE
                                } else if (ResponseCode.equals("14")) {
                                    Status = "09"; // TK DICH/ THE KHONG TON TAI
                                } else if (ResponseCode.equals("41")) {
                                    Status = "10"; // The mat
                                } else if (ResponseCode.equals("05") || ResponseCode.equals("04") || ResponseCode.equals("07")) {
                                    Status = "11"; // The nghi van
                                } else if (ResponseCode.equals("54")) {
                                    Status = "12"; // The het han
                                } else if (ResponseCode.equals("51")) {
                                    Status = "13"; // The bi gioi han
                                } else if (ResponseCode.equals("91") || ResponseCode.equals("92")) {
                                    Status = "14"; // Ngan hang thu huong chua tham gia
                                } else if (ResponseCode.equals("68")) {
                                    Status = "15"; // Truy van SML time out loi 68
                                } else {
                                    Status = "14";
                                }
                            } else {
                                String statusLimit = Helper.getDBI().checkLimitAmount(CustNo, Amount);
                                if (!statusLimit.equals("00")) {
                                    if (statusLimit.equals("01")) {
                                        Status = "22"; // Nho hon so so tien toi thieu
                                    } else if (statusLimit.equals("02")) {
                                        Status = "23"; // Vuot han muc 50000000/ 1giao dich
                                    }
                                }
                            }
                        }
                    }
                    logger.info("INSERT GD QUE TRACE:" + AuditNumber);
                    //Insert log transfer from scb
                    Helper.getDBI().InsertSMLLOG("FROM_SCB", FromNumber, ProcessingCode, Amount, Transdate,
                            AuditNumber, Merchant_Type, Acquiring_Code, Authorization_Code, ResponseCode,
                            TermID, CardAcceptor, ToNumber, IDREQUEST + "-" + cheThe(Desc, "..."), BenID, TypeFunction, Status, RefCORE, CustNo, RefCORE_REFUND, Ref_no_F37, Sett_date_F15);

                } else if (TypeFunction.equals("TRN")) {
                    //Tiep tuc thuc hien transfer
                    //get SML Account
                    ResponseCode = "";
                    //Transfer
                    scb.com.vn.controller.Fcubs controller = new Fcubs();
                    String statusLimit = Helper.getDBI().checkLimitAmount(CustNo, Amount);
                    if (statusLimit.equals("02")) {
                        Status = "23"; // Vuot han muc 50000000/ 1giao dich
                    } else {
                        Status = "16";
                    }

                    if (TypeFromNumber.equals("CARD")) {
                        if (TypeToNumber.equals("CARD")) {
                            ProcessingCode = "910000";
                        } else {
                            ProcessingCode = "910020";
                        }
                    } else {
                        if (TypeToNumber.equals("CARD")) {
                            //ProcessingCode = "912000";
                            ProcessingCode = "910000";
                        } else {
                            ProcessingCode = "912020";
                        }
                    }
                    String refno = CommonUtils.getRefno("11");
                    int resultInsert = Helper.getDBI().InsertSMLLOG("FROM_SCB", FromNumber, ProcessingCode, Amount, Transdate,
                            AuditNumber, Merchant_Type, Acquiring_Code, Authorization_Code, ResponseCode,
                            TermID, CardAcceptor, ToNumber, IDREQUEST + "-" + cheThe(Desc, ".."), BenID, TypeFunction, Status, RefCORE, CustNo, RefCORE_REFUND, Ref_no_F37, Sett_date_F15);
                    logger.info("INSERT GD TRN TRACE:" + AuditNumber + " Status:" + Status + " Id:" + resultInsert + " refno:" + refno);
                    if (resultInsert > 0) {
                        Status = "16";
                    } else {
                        logger.info("LOI INSERT GD TRN TRACE:" + AuditNumber);
                        Status = "01";
                    }
                    if (Status.equals("16")) {
                        //tiep tuc hach toan
                        //Chuyen khoan di

                        //SMLAccount = Helper.getDBI().getSMLAccount("FROM_SCB");
                        // String branchCust = AccountOfCust.substring(0, 3);                        
                        if (!channel.endsWith("COUNTER1") & !channel.endsWith("COUNTER3")) {
                            branchCust = CommonUtils.getBranchAccount(AccountOfCust);
                        }
                        String branchSML = "000";
                        String product = "";
                        String useridfcubs = useridfcubs_EB;

                        if (channel.equals("EBANK") || channel.equals("MB")) {
                            if (channel.equals("MB")) {
                                useridfcubs = useridfcubs_MB;
                            }
                            product = ProductFromSCB_MOBILE;
                            //RefCORE = controller.transferFCUBSWithTimeOutFeeAndVAT(product, useridfcubs, branchCust, AccountOfCust, branchSML, SMLAccount, Amount, Desc, 30, Fee, Vat, finFee, finFeeVAT);
                            RefCORE = controller.transferFCUBSWithTimeOutFeeAndVATRefno(product, useridfcubs, branchCust, AccountOfCust, branchSML, SMLAccount, Amount, Desc, 30, Fee, Vat, finFee, finFeeVAT, refno);
                        } else if (channel.equals("COUNTER1")) {
                            // Chuyen khoan tai quay bang tien mat
                            product = ProductFromSCB_COUNTER1;
                            String[] result = Helper.getDBI().transferFCUBSForIBTCounter(ProductFromSCB_COUNTER1,
                                    "000",
                                    NapasAccountISS,
                                    Amount.toString(),
                                    "VND",
                                    Desc, idcardName, Address,
                                    idcard,
                                    idcardDob,
                                    idcardAddress,
                                    user_maker.toUpperCase(),
                                    user_checker.toUpperCase());
                            if (result != null) {
                                RefCORE = result[0];
                                logger.info("[IBT]: KET QUA HACH TOAN QUA CORE CHO SO TRACE: " + AuditNumber + " LA: " + RefCORE + " -KQ[1]:" + result[1] + " -KQ[2]:" + result[2] + " -KQ[3]:" + result[3]);
                                if (RefCORE != null && !RefCORE.equals("null") && !RefCORE.equals("")) {
                                    x_ref = result[1];
                                    refno = x_ref;
                                }
                            } else {
                                RefCORE = "TIMEOUT";
                            }
                            // Chuyen khoan tai quay bang tien mat
                        } else if (channel.equals("CH")) {
                            product = ProductTCH;
                            //RefCORE = controller.transferFCUBSWithTimeOut(product, useridfcubs, branchCust, AccountOfCust, branchSML, NapasAccountISS, Amount, Desc, 30);
                            RefCORE = controller.transferFCUBSWithReturnTimeOutRefno(product, useridfcubs, branchCust, AccountOfCust, branchSML, NapasAccountISS, Amount, Desc, 30, refno);
                            // Chuyen khoan qua g chhuyen khoan
                        } else if (channel.equals("COUNTER2")) {
                            product = ProductFromSCB_COUNTER2;
                            RefCORE = controller.transferFCUBSCounter(product, AccountOfCust, "", Amount, Desc, branchCounter, refno);
                            // Chuyen khoan tai quay bang chhuyen khoan
                        } else if (channel.equals("COUNTER3")) {
                            product = ProductFromSCB_COUNTER3;
                            RefCORE = controller.transferFCUBSWithReturnTimeOutRefno(product, useridfcubs, branchCounter, AccountOfCust, branchSML, NapasAccountISS, Amount, Desc, 30, refno);
                            //RefCORE = controller.transferFCUBSWithTimeOut(product, useridfcubs, branchCounter, AccountOfCust, branchSML, NapasAccountISS, Amount, Desc, 30);
                        }
                        if (RefCORE != null) {
                            if (RefCORE.equals("TIMEOUT") || RefCORE.equals("")) {
                                Status = "16"; // Chuyen khoan FCC time out
                            } else {
                                if (Status.equals("16")) {
                                    Status = "18";
                                }
                            }

                        } else {
                            RefCORE = "";
                            Status = "17";// Không thực hiện cắt tiền được từ FCC
                        }
                        logger.info("UPDATE KQ HACH TOAN CHO SO TRACE:" + AuditNumber + " LA:" + RefCORE + " refno:" + refno);
                        // int resultUpdate = Helper.getDBI().updateIBT(AuditNumber, Status, RefCORE, "", Ref_no_F37, Sett_date_F15, "FROM_SCB");
                        int resultUpdate = Helper.getDBI().updateIBT(AuditNumber, Status, RefCORE, "FROM_SCB", String.valueOf(resultInsert), refno);
                        if (resultUpdate == 0) {
                            logger.info("LOI UPDATE KQ HACH TOAN CHO SO TRACE:" + AuditNumber);
                            Status = "16";
                        }
                        if (Status.equals("18")) {
                            //tiep tuc call napass

                            String dataToSign = "0200" + FromNumber + ProcessingCode + strAmount + Transdate
                                    + AuditNumber + Merchant_Type + Acquiring_Code + TermID + ToNumber;
                            String paramTransfer = "0200|" + FromNumber + "|" + ProcessingCode + "|" + strAmount + "|" + Transdate + "|"
                                    + AuditNumber + "|" + Merchant_Type + "|" + Acquiring_Code + "|" + TermID + "|" + CardAcceptor + "|"
                                    + ToNumber + "|" + Desc;
                            if (TypeToNumber.equals("ACCOUNT")) {
                                dataToSign = dataToSign + BenID;
                                paramTransfer = paramTransfer + "|" + BenID;
                            }
                            String Signaturetf = Helper.createRequestSignature(dataToSign, Configuration.privateKeySCB_2_SML);
                            paramTransfer = paramTransfer + "|" + Ref_no_F37 + "||" + Signaturetf;
                            /*
                                Helper.getDBI().InsertSMLLOG("FROM_SCB", FromNumber, ProcessingCode, Amount, Transdate,
                                        AuditNumber, Merchant_Type, Acquiring_Code, Authorization_Code, ResponseCode,
                                        TermID, CardAcceptor, ToNumber, IDREQUEST + "-" + cheThe(Desc, ".."), BenID, TypeFunction, Status, RefCORE, CustNo, RefCORE_REFUND, Ref_no_F37, Sett_date_F15);
                             */
                            logger.info("Request TRN NAPAS AUDITNUMBER[" + AuditNumber + "]: " + cheThe(paramTransfer, ".."));
                            //String resultTransfer = Helper.callwsSmartlink(paramTransfer, 40);
                            String resultTransfer = Helper.callwsIBTNAPASwithTrace(paramTransfer, 40, AuditNumber);
                            logger.info("Reponse TRN NAPAS AUDITNUMBER[" + AuditNumber + "]: " + cheThe(resultTransfer, "..."));
                            if (resultTransfer == null) {
                                Status = "18"; // CK SML time out
                            } else {
                                if (resultTransfer.equals("TIMEOUT")) {
                                    Status = "18"; // CK SML time out
                                    // DOI TRA SOAT
                                } else if (resultTransfer.equals("REVERT")) {

                                    // Status = "18"; // CK SML time out
                                    Status = "19"; // chuyen khoan bi loi thuc hien revert
                                    //Thuc hien revert giao dich
                                    if (channel.equals("COUNTER1")) {
                                        RefCORE_REFUND = Helper.getDBI().revertTransferFCUBS(RefCORE, x_ref, user_maker.toUpperCase(), user_checker.toUpperCase());
                                    } else {
                                        RefCORE_REFUND = controller.revertTransferFCUBS(RefCORE, 40);
                                    }
                                    logger.info("Thực hiện revert cho loi exception cho số ref: " + RefCORE + " Kết quả:" + RefCORE_REFUND);

                                } else {
                                    String[] arrResultTransfer = resultTransfer.replace("|", "#").split("#");
                                    //Check Sinature
                                    boolean isCheckSignatureTF = Helper.verifyResponseSignature(resultTransfer.split("\\|"), Configuration.pubKeySmartlink);

                                    ResponseCode = arrResultTransfer[9];
                                    Authorization_Code = arrResultTransfer[8];
                                    String RefAudit = arrResultTransfer[5];
                                    String Code = arrResultTransfer[0];
                                    if (TypeToNumber.equals("CARD")) {
                                        Sett_date_F15 = arrResultTransfer[14].toString();
                                    } else {
                                        Sett_date_F15 = arrResultTransfer[15].toString();
                                    }
                                    if (RefAudit.equals(AuditNumber) & Code.equals("0210") & !ResponseCode.equals("")) {
                                        if (!isCheckSignatureTF) {
                                            Status = "99"; // Khong dung signature 
                                        } else if (!ResponseCode.equals("00")) {
                                            // Status = ResponseCode;
                                            if (ResponseCode.equals("05")) {
                                                Status = "19"; // CHUYEN TIEN SML LOI 
                                            } else if (ResponseCode.equals("61")) {
                                                Status = "20"; // QUÁ HẠN MỨC SỐ TIỀN/NGÀY
                                            } else if (ResponseCode.equals("65")) {
                                                Status = "21"; // QUÁ HẠN MỨC SỐ LẦN/NGÀY
                                            } else if (ResponseCode.equals("68")) {
                                                Status = "24"; // Time out do loi CK SML 68
                                            } else {
                                                Status = "19"; // THUC HIEN HOAN TIEN
                                            }                                                    //if(ResponseCode.equals("05")||ResponseCode.equals("61")||ResponseCode.equals("65"))
                                            if (!ResponseCode.equals("68")) {
                                                //RefCORE_REFUND = controller.revertTransferFCUBS(RefCORE, 40);
                                                if (TypeFunction.equals("TRN") & ResponseCode.length() == 2) {
                                                    if (channel.equals("COUNTER1")) {
                                                        RefCORE_REFUND = Helper.getDBI().revertTransferFCUBS(RefCORE, x_ref, user_maker.toUpperCase(), user_checker.toUpperCase());
                                                    } else {
                                                        RefCORE_REFUND = controller.revertTransferFCUBS(RefCORE, 40);
                                                    }
                                                    logger.info("Thực hiện revert cho số ref: " + RefCORE + " Kết quả:" + RefCORE_REFUND);
                                                }
                                            }
                                        } else {

                                            Status = ResponseCode;
                                        }

                                    }

                                }
                            }

                            logger.info("UPDATE SAU KHI CALL NAPAS TRACE:" + AuditNumber + " LA:" + ResponseCode);
                            // Helper.getDBI().updateIBT(AuditNumber, Status, RefCORE, RefCORE_REFUND, Ref_no_F37, Sett_date_F15, "FROM_SCB");
                            /*  Helper.getDBI().InsertSMLLOG("FROM_SCB", FromNumber, ProcessingCode, Amount, Transdate,
                                 AuditNumber, Merchant_Type, Acquiring_Code, Authorization_Code, ResponseCode,
                                 TermID, CardAcceptor, ToNumber, IDREQUEST + "-" + cheThe(Desc, ".."), BenID, TypeFunction, Status, RefCORE, CustNo, RefCORE_REFUND, Ref_no_F37, Sett_date_F15);*/
                            Helper.getDBI().updateIBTResponse(AuditNumber, Status, ResponseCode, RefCORE_REFUND, Sett_date_F15);
                            // Helper.getDBI().updateIBTResponse(AuditNumber, Status, ResponseCode, RefCORE_REFUND, Sett_date_F15,String.valueOf(resultInsert));
                        }

                    }
                }
            }
        } catch (Exception ex) {
            logger.info("Exception: " + ex.toString());
            Status = "99";
            if (channel.equals("EBANK") || channel.equals("MB")) {
                if (TypeFunction.equals("QUE")) {
                    Status = "-99";
                }
            }
            if (RefCORE != null & TypeFunction.equals("TRN")) {
                if (!RefCORE.equals("")) {
                    Status = "18";
                }
            }

        }
        logger.info("KQ TRA VE CHO TRACE:" + AuditNumber + " LA:" + Status + "#" + DESTNAME + "#" + RefCORE);
        return Status + "#" + DESTNAME + "#" + RefCORE;
    }

    private String CheckInputFromSCB(String FromNumber, String TypeFromNumber, String FullName, String ToNumber, String TypeToNumber,
            BigDecimal Amount, String channel, String TypeFunction, String BENID) throws RemoteException, Exception {
        try {
            //Check balance and exist From number
            if (FromNumber == "" || TypeFromNumber == "" || ToNumber == "" || TypeToNumber == "" || channel == "" || TypeFunction == "") {
                return "-1";
            }
            if (TypeToNumber.equals("ACCOUNT")) {
                if (BENID == "") {
                    return "-1";
                }
            }
            if (Amount.compareTo(BigDecimal.valueOf(0)) == -1) {
                return "-1"; // Số tiền không hợp lệ
            }
            if (!TypeFunction.equals("QUE") && !TypeFunction.equals("TRN")) {
                return "01"; // Sai TypeFunction
            }
            if (!TypeFromNumber.equals("ACCOUNT") && !TypeFromNumber.equals("CARD")) {
                return "02"; // Sai TypeNumber
            }
            if (!TypeToNumber.equals("ACCOUNT") && !TypeToNumber.equals("CARD")) {
                return "02"; // Sai TypeNumber
            }
            /*if (TypeToNumber.equals("CARD")) {
                String[] checkToCard = Helper.getDBI().getCardStatus(ToNumber);
                String checkCardStatus = checkToCard[0];
                if (checkCardStatus.equals("00")) {
                    return "25"; //THẺ ĐICH THUỘC NGÂN HÀNG SCB
                }
            }*/
            if (!channel.equals("EBANK") && !channel.equals("ATM") && !channel.equals("SMS") && !channel.equals("MB") && !channel.equals("POS") && !channel.equals("COUNTER") && !channel.equals("COUNTER1") && !channel.equals("COUNTER2") && !channel.equals("CH") && !channel.equals("QR")) {
                return "03"; // Sai Channel
            }
            //Check The
            if (TypeFromNumber.equals("CARD")) {
                String[] ArrInfoCard = Helper.getDBI().getCardInfo(FromNumber, FullName, Amount);
                FromNumber = ArrInfoCard[0];
                String StatusCard = ArrInfoCard[1];
                /*
             o	00: Được phép thanh toán; 
             o	02: Thẻ bị khóa: 
             o	03: Thẻ chưa đăng ky; 
             o	04: Các thông tin về thẻ bị sai; 
             o	05: Khong du so du
                 */
                if (!StatusCard.equals("00")) {
                    if (StatusCard.equals("05")) {
                        return "04"; // không đủ số dư
                    } else {
                        return "05"; // The SCB ko hop le
                    }
                }
            }
            logger.info("Check tk:" + FromNumber + " - tonumber:" + ToNumber);
            scb.com.vn.dbi.dto.VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(FromNumber);
            if (custacc == null) {
                return "06"; // Sai thong tin tk
            } else if (!custacc.getCcy().equalsIgnoreCase("VND")) {
                return "06"; // Sai thong tin tk
            } else {
                BigDecimal Balance = custacc.getAcyAvlBal();

                logger.info("So du tk " + FromNumber + ":" + String.valueOf(Balance.intValue()) + "- tonumber:" + ToNumber);
                if (!checkAccountClass(custacc.getAccountClass())) {
                    return "05";
                }
                /*
            if (!custacc.getAccountClass().substring(0, 3).equals("CAI") 
                    && !custacc.getAccountClass().substring(0, 3).equals("CAC") 
                    && !custacc.getAccountClass().substring(0, 3).equals("TCI")
                    && !custacc.getAccountClass().equals("CDC010")
                    && !custacc.getAccountClass().substring(0, 3).equals("TCC")) {
                return "05"; // The SCB ko hop le
            }*/
                if (custacc.getAccountClass().substring(0, 3).equals("CAI") || custacc.getAccountClass().substring(0, 3).equals("CAC")) {
                    BigDecimal minamount = new BigDecimal(Configuration.getProperty("ws.smartlink.minamount"));
                    BigDecimal fee = new BigDecimal(Configuration.getProperty("ws.smartlink.fee"));
                    BigDecimal CheckBalace = Balance.subtract(fee).subtract(Amount);

                    //logger.info("CheckBalace: " + String.valueOf(CheckBalace.intValue()));                
                    //logger.info("fee: " + String.valueOf(fee.intValue()));                
                    //logger.info("Amount: " + String.valueOf(Amount.intValue()));
                    if (TypeFunction.equals("TRN")) {
                        if (CheckBalace.compareTo(BigDecimal.valueOf(0)) == -1) {
                            return "04"; // không đủ số dư
                        }
                        if (Amount.compareTo(minamount) == -1) {
                            return "22";// nho hon so tien toi thieu
                        }
                    }
                }

                return "00#" + custacc.getCustNo() + "#" + custacc.getAccountClass();
            }
        } catch (Exception ex) {
            logger.info("Loi check fromnumber:" + FromNumber + "-tonumber:" + ToNumber + " ex:" + ex.getMessage());
            return null;
        }
    }

    public String transferToSCB(String requestData) throws RemoteException, Exception {
        int isTransfer = 0;

        logger.info("Request:" + requestData);
        String ResponseCode = "00"; //Approved
        String[] result = requestData.split("\\|");

        String AuthorizationCode = Helper.getDBI().getAuthorizationCode().trim();
        String Status = "";
        String RefCore = "";
        String CardName = "";

        String MTI = result[0];
        String FromNumber = result[1];
        String ProcessingCode = result[2];
        String strAmount = result[3];
        String transferdate = result[4];
        String AuditTransfer = result[5];
        String MerchantType = result[6];
        String AcquiringCode = result[7];
        String TermId = result[8];
        String CardAcceptor = result[9];
        String DestAccount = result[10];
        String Desc = result[11];
        String AccountOfCust = DestAccount;
        String BenID = "";
        String Sett_date_F15 = "";
        String Ref_no_F37 = "";
        String refno = "";
        if (ProcessingCode.equals("430000") || ProcessingCode.equals("432000") || ProcessingCode.equals("910000") || ProcessingCode.equals("912000")) {
            //THE
            Ref_no_F37 = result[12];
            Sett_date_F15 = result[13];
        } else {
            //TAI KHOAN
            BenID = result[12];
            Ref_no_F37 = result[13];
            Sett_date_F15 = result[14];
        }
        try {

            String FULLNAME = "";
            String sFunction = "";
            String CustNo = "";
            String SMLAccount;

            BigDecimal tranferAmount = BigDecimal.valueOf(0);

            String[] resultCheck = CheckInput(requestData).split("#");
            ResponseCode = resultCheck[0];
            if (ProcessingCode.equals("430020") || ProcessingCode.equals("432020") || ProcessingCode.equals("430000") || ProcessingCode.equals("432000")) {
                sFunction = "QUE";
                Desc = FULLNAME;
            } else {
                sFunction = "TRN";
            }
            if (ResponseCode.equals("00")) {
                FULLNAME = resultCheck[1];
                CustNo = resultCheck[3];
            }
            int idInsert = 0;
            if (sFunction.equals("TRN")) {
                if (ResponseCode.equals("00")) {
                    Status = "16";
                } else {
                    Status = "01";
                    logger.info("GIAO DICH KHONG HACH TOAN:" + AuditTransfer);
                }
                try {
                    String requestAmount = strAmount.substring(0, strAmount.length() - 2) + "." + strAmount.substring(strAmount.length() - 2, strAmount.length());
                    tranferAmount = new BigDecimal(requestAmount);
                    refno = CommonUtils.getRefno("002");
                    idInsert = Helper.getDBI().InsertSMLLOG("TO_SCB", FromNumber, ProcessingCode, tranferAmount,
                            transferdate, AuditTransfer, MerchantType, AcquiringCode, AuthorizationCode,
                            ResponseCode, TermId, CardAcceptor, DestAccount, cheThe(Desc, "..."),
                            BenID, sFunction, Status, RefCore, CustNo, "", Ref_no_F37, Sett_date_F15);
                    logger.info("InsertSMLLOG AuditTransfer:" + AuditTransfer + " ID:" + idInsert + " refno:" + refno);
                    if (idInsert > 0) {
                        ResponseCode = "00";
                    } else {
                        ResponseCode = "05";
                    }
                } catch (Exception ex) {
                    if (ResponseCode.equals("00")) {
                        ResponseCode = "05";
                    }
                    logger.info("Loi Insert (ko hach toan):" + AuditTransfer + " ex:" + ex.getMessage());
                }
            }
            if (ResponseCode.equals("00")) {
                if (ProcessingCode.equals("430000") || ProcessingCode.equals("432000") || ProcessingCode.equals("910000") || ProcessingCode.equals("912000")) {
                    AccountOfCust = resultCheck[2];
                }
                //Check processing code
                if (sFunction.equals("QUE")) {
                    Desc = FULLNAME;

                } else {
                    if (FULLNAME == null || FULLNAME.equals("")) {
                        ResponseCode = "05"; //TK KHONG HOP LE
                    } else {

                        //Thuc hien chuyen khoan
                        scb.com.vn.controller.Fcubs controller = new Fcubs();
                        String product = ProductToSCB;
                        //Chuyen khoan den
                        //SMLAccount = Helper.getDBI().getSMLAccount("TO_SCB");
                        String useridfcubs = useridfcubs_EB;
                        SMLAccount = Helper.getDBI().getSMLAccount("FROM_SCB");
                        //String branchCust = AccountOfCust.substring(0, 3);
                        String branchCust = CommonUtils.getBranchAccount(AccountOfCust);
                        String branchSML = "000";

                        isTransfer = 1;
                        RefCore = controller.transferFCUBSWithReturnTimeOutRefno(product, useridfcubs, branchSML, SMLAccount, branchCust, AccountOfCust, tranferAmount, Desc, 30, refno);
                        logger.info("RefCore:" + RefCore + " cho so trace:" + AuditTransfer + " refno:" + refno);
                        if (RefCore == null) {
                            isTransfer = 0;
                            ResponseCode = "05";
                        } else if (RefCore.equals("TIMEOUT")) {
                            ResponseCode = "68";

                        }
                    }
                }
                if (ResponseCode.equals("00")) {
                    Status = "00";
                } else {
                    if (ResponseCode.equals("68")) {
                        Status = "16";
                    } else {
                        Status = "01";

                    }
                }
                if (sFunction.equals("TRN")) {
                    //InsertLog
                    logger.info("Update AuditTransfer:" + AuditTransfer + " status:" + Status + " responsecore:" + ResponseCode);
                    // Helper.getDBI().updateIBT(AuditTransfer, Status, RefCore, "", Ref_no_F37, Sett_date_F15, "TO_SCB");
                    //Helper.getDBI().updateIBT(AuditTransfer, Status, RefCore,"TO_SCB",String.valueOf(idInsert));
                    Helper.getDBI().updateResultToSCB(AuditTransfer, Status, RefCore, String.valueOf(idInsert), ResponseCode);
                    /*
                    Helper.getDBI().InsertSMLLOG("TO_SCB", FromNumber, ProcessingCode, tranferAmount,
                            transferdate, AuditTransfer, MerchantType, AcquiringCode, AuthorizationCode,
                            ResponseCode, TermId, CardAcceptor, DestAccount, cheThe(Desc,"..."),
                            BenID, sFunction, Status, RefCore, CustNo, "",Ref_no_F37,Sett_date_F15);
                     */
                }
            }

        } catch (Exception ex) {
            logger.info("EX:" + ex.getMessage());
            if (isTransfer == 1) {
                ResponseCode = "68";
            } else {
                ResponseCode = "96";
            }
        }
        String strData = "0210" + "|" + FromNumber + "|" + ProcessingCode + "|" + strAmount + "|" + transferdate + "|" + AuditTransfer + "|" + MerchantType
                + "|" + AcquiringCode + "|" + AuthorizationCode + "|" + ResponseCode + "|" + TermId + "|" + DestAccount + "|" + Desc;
        String datatosign = "0210" + FromNumber + ProcessingCode + strAmount + transferdate + AuditTransfer + MerchantType
                + AcquiringCode + AuthorizationCode + ResponseCode + TermId + DestAccount + Desc;
        if (ProcessingCode.equals("430000") || ProcessingCode.equals("432000") || ProcessingCode.equals("910000") || ProcessingCode.equals("912000")) {
            //CHUYEN KHOAN/TRUY VAN DEN THE
        } else {
            //CHUYEN KHOAN/TRUY VAN DEN TAI KHOAN
            strData = strData + "|" + BenID;
            datatosign = datatosign + BenID;
        }
        String SignatureResponse = Helper.createResponseSignature(strData, Configuration.privateKeySCB_2_SML);
        String ResponseString = strData + "|" + Ref_no_F37 + "|" + Sett_date_F15 + "|" + SignatureResponse;
        logger.info("Response:" + ResponseString);
        return ResponseString;
    }

    private boolean checkAmount(String requestAmount) {
        try {
            BigDecimal tranferAmount = new BigDecimal(requestAmount);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean checkDate(String strDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
            Date date = dateFormat.parse(strDate);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private String CheckInput(String requestData) throws RemoteException, Exception {
        String ResponseCode = "00"; //Approved
        String[] result = requestData.replace("|", "#").split("#");

        String MTI = result[0];
        String FromNumber = result[1];
        String ProcessingCode = result[2];
        String strAmount = result[3];
        String transferdate = result[4];
        String AuditTransfer = result[5];
        String MerchantType = result[6];
        String AcquiringCode = result[7];
        String TermId = result[8];
        String CardAcceptor = result[9];
        String DestAccount = result[10];
        String Desc = result[11];

        String AccountOfCust = DestAccount;
        String FULLNAME = "";
        //check signature
        if (!MTI.equals("0200") || FromNumber.equals("") || transferdate.equals("")
                || AuditTransfer.equals("") || MerchantType.equals("")
                || AcquiringCode.equals("") || DestAccount.equals("")) {
            return "96";
        }
        boolean isSuccess = Helper.verifyRequestSignature(requestData.split("\\|"), Configuration.pubKeySmartlink);
        if (!isSuccess) {
            return "63"; // Khong dung chu ky
        }
        String requestAmount = strAmount.substring(0, strAmount.length() - 2) + "." + strAmount.substring(strAmount.length() - 2, strAmount.length());
        if (!checkAmount(requestAmount)) {
            return "13"; // invalid amount
        }
        //Check auditnumber 
        if (!Helper.getDBI().checkAuditNumberOfSML(AuditTransfer)) {
            return "12"; //auditnumber is exist : Invalid transaction
        }
        //check merchant type
        if (!MerchantType.equals("6011") && !MerchantType.equals("6012") && !MerchantType.equals("7399") && !MerchantType.equals("7299") && !MerchantType.equals("6023") && !MerchantType.equals("5999")) {
            return "03"; //invalid merchant type
        }
        //Check dest number
        if (ProcessingCode.equals("430000") || ProcessingCode.equals("432000") || ProcessingCode.equals("910000") || ProcessingCode.equals("912000")) {
            //Truy van thong tin the
            logger.info("BEGIN getCardStatus DestAccount:" + DestAccount);
            String[] CardIfo = Helper.getDBI().getCardStatus(DestAccount);
            logger.info("END getCardStatus DestAccount:" + DestAccount);
            if (CardIfo[0].equals("01")) {
                return "01"; //The dong : Expired card ; khong co case the mat
            } else if (CardIfo[0].equals("02")) {
                return "54"; //The het han ,Expired card
            } else if (CardIfo[0].equals("03")) {
                return "14"; //The chua active :Card number does not exist
            } else if (CardIfo[0].equals("04")) {
                return "14"; //The khong ton tai : Card number does not exist
            } else if (CardIfo[0].equals("05") || CardIfo[0].equals("06")) {
                return "01"; //TK KHONG HOP LE
            }
            if (CardIfo[0].equals("00")) {
                AccountOfCust = CardIfo[2];
                FULLNAME = CardIfo[1];
            }
        }
        logger.info("BEGIN getCustAccountByAccountNo:" + AccountOfCust);
        //Truy van thong tin tai khoan
        scb.com.vn.dbi.dto.VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(AccountOfCust);
        logger.info("END getCustAccountByAccountNo:" + AccountOfCust);
        if (custacc == null) {
            return "14"; //TK khong ton tai : No checking account
        } else {
            char C = 'C';
            if (ProcessingCode.equals("430020") || ProcessingCode.equals("432020") || ProcessingCode.equals("910020") || ProcessingCode.equals("912020")) {

                if (custacc.getFullName() == null) {
                    FULLNAME = "";
                } else {
                    FULLNAME = custacc.getFullName();
                }
            }
            if (!custacc.getCcy().equals("VND")) {
                return "01";
            }
            if (custacc.getAccountClass().substring(0, 6).equals("CAI012")) {
                return "01"; // The SCB ko hop le
            }
            if (!custacc.getAccountClass().substring(0, 3).equals("CAI")
                    && !custacc.getAccountClass().substring(0, 3).equals("CAC")
                    && !custacc.getAccountClass().substring(0, 3).equals("TCI")
                    && !custacc.getAccountClass().equals("CDC010")
                    && !custacc.getAccountClass().substring(0, 3).equals("TCC")) {
                return "01"; // The SCB ko hop le
            } else if (custacc.getRecordStat().equals(C)) {
                return "14"; //TK da dong : No checking account
            }
            /* else if (!custacc.getAuthStat().equals('A')) {
                    return "14"; //TK chua duyet : No checking account
                }*/
        }
        //Check processing code
        if (ProcessingCode.equals("430020") || ProcessingCode.equals("432020") || ProcessingCode.equals("430000") || ProcessingCode.equals("432000")
                || ProcessingCode.equals("910000") || ProcessingCode.equals("910020") || ProcessingCode.equals("912000") || ProcessingCode.equals("912020")) {

        } else {
            return "30"; //invalid processing code (format error)
        }
        //Check transferDate
        if (!checkDate(transferdate)) {
            return "80"; //invalid date (format error)
        }
        return "00#" + FULLNAME + "#" + AccountOfCust + "#" + custacc.getCustNo();
    }

    /*
     * 
     * Mobile bắn phí thuế vào core
     */
    public String transfeFromSCBForMobile(String FromNumber,
            String TypeFromNumber,
            String FullName,
            String ToNumber,
            String TypeToNumber,
            String BenID,
            BigDecimal Amount,
            String CCY,
            String channel,
            String Desc,
            String TermID,
            String CardAcceptor,
            String TypeFunction, BigDecimal fee, BigDecimal vat) throws RemoteException, Exception {
        try {
            String Transdate = "";
            String AuditNumber = "";
            String Merchant_Type = "";
            String Acquiring_Code = "157979";
            String Authorization_Code = "";
            String ResponseCode = "";
            String ProcessingCode = "";
            String RefCORE = "";
            String AccountOfCust = FromNumber;
            String DESTNAME = "";
            String CustNo = "";
            String cust_account_class = "";
            String RefCORE_REFUND = "";
            String SMLAccount = "";
            String Sett_date_F15 = "";
            String resultCheck = CheckInputFromSCB(FromNumber, TypeFromNumber, FullName, ToNumber, TypeToNumber,
                    Amount, channel, TypeFunction, BenID);

            //logger.info("resultCheck: " + resultCheck);
            String[] resultArr = resultCheck.split("#");
            String Status = resultArr[0];
            if (Status.equals("00")) {
                CustNo = resultArr[1];
                cust_account_class = resultArr[2];

                //Check exist tonumber
                if (TypeToNumber.equals("CARD")) {
                    BenID = "";
                }
                DecimalFormat formater = new DecimalFormat("0.00");
                //String strAmount= Helper.addExtraChar(formater.format(Amount).replace(",", ""),12,"0");
                String strAmount = Helper.addExtraChar(formater.format(Amount).replace(".", "").replace(",", ""), 12, "0");
                Date currentdate = new Date();
                SimpleDateFormat sdfDestination = new SimpleDateFormat("MMddHHmmss");
                Transdate = sdfDestination.format(currentdate);
                SimpleDateFormat fDateF37 = new SimpleDateFormat("DDDHH");
                SimpleDateFormat fDateF37Y = new SimpleDateFormat("Y");
                String Ref_no_F37 = fDateF37Y.format(currentdate).substring(3, 4) + fDateF37.format(currentdate); //add by Lydty,14/Aug/2017  
                if (channel.equals("EBANK") || channel.equals("CH")) {
                    Merchant_Type = "7399";
                } else if (channel.equals("ATM")) {
                    Merchant_Type = "6011";
                } else if (channel.equals("POS")) {
                    Merchant_Type = "6012";
                } else if (channel.equals("MB")) {
                    Merchant_Type = "7299";
                } else if (channel.equals("SMS")) {
                    Merchant_Type = "6023";
                } else if (channel.equals("COUNTER")) {
                    Merchant_Type = "5999";
                }
                AuditNumber = Helper.getDBI().getAuditNumber(); //Audit check
                Ref_no_F37 = Ref_no_F37 + AuditNumber;
                if (TypeFunction.equals("QUE")) {

                    if (TypeFromNumber.equals("CARD")) {
                        if (TypeToNumber.equals("CARD")) {
                            ProcessingCode = "430000";
                        } else {
                            ProcessingCode = "430020";
                        }
                    } else {
                        if (TypeToNumber.equals("CARD")) {
                            //ProcessingCode = "432000";
                            ProcessingCode = "430000";
                        } else {
                            ProcessingCode = "432020";
                        }
                    }
                    strAmount = "000000000000";

                    String paramCheckToNumber = paramCheckToNumber = "0200|" + FromNumber + "|" + ProcessingCode + "|" + strAmount + "|" + Transdate + "|"
                            + AuditNumber + "|" + Merchant_Type + "|" + Acquiring_Code + "|" + TermID + "|" + CardAcceptor + "|"
                            + ToNumber + "|" + Desc;
                    String dataSignCheck = "0200" + FromNumber + ProcessingCode + strAmount + Transdate
                            + AuditNumber + Merchant_Type + Acquiring_Code + TermID + ToNumber + BenID;
                    if (TypeToNumber.equals("ACCOUNT")) {
                        dataSignCheck = dataSignCheck + BenID;
                        paramCheckToNumber = paramCheckToNumber + "|" + BenID;
                    }
                    String Signature = Helper.createRequestSignature(dataSignCheck, Configuration.privateKeySCB_2_SML);
                    paramCheckToNumber = paramCheckToNumber + "|" + Ref_no_F37 + "||" + Signature;
                    // String resultCheckToNumber = Helper.callwsSmartlink(paramCheckToNumber, 40);
                    String resultCheckToNumber = Helper.callwsIBTNAPAS(paramCheckToNumber, 40);

                    if (resultCheckToNumber.equals("TIMEOUT")) {
                        Status = "07"; // Truy van SML time out
                    } else {
                        String[] arrResultCheckToNumber = resultCheckToNumber.replace("|", "#").split("#");
                        //Check Sinature
                        boolean isCheckSignature = Helper.verifyResponseSignature(resultCheckToNumber.split("\\|"), Configuration.pubKeySmartlink);

                        ResponseCode = arrResultCheckToNumber[9];
                        Authorization_Code = arrResultCheckToNumber[8];
                        DESTNAME = arrResultCheckToNumber[12];
                        if (TypeToNumber.equals("CARD")) {
                            Sett_date_F15 = arrResultCheckToNumber[14].toString();
                        } else {
                            Sett_date_F15 = arrResultCheckToNumber[15].toString();
                        }
                        //Insert Log Query

                        if (!isCheckSignature) {
                            Status = "99"; // Khong dung signature 
                        } else if (!ResponseCode.equals("00")) {
                            Status = ResponseCode;
                            if (ResponseCode.equals("01")) {
                                Status = "08"; // TK/THE DICH KHONG HOP LE
                            } else if (ResponseCode.equals("14")) {
                                Status = "09"; // TK DICH/ THE KHONG TON TAI
                            } else if (ResponseCode.equals("41")) {
                                Status = "10"; // The mat
                            } else if (ResponseCode.equals("05") || ResponseCode.equals("04") || ResponseCode.equals("07")) {
                                Status = "11"; // The nghi van
                            } else if (ResponseCode.equals("54")) {
                                Status = "12"; // The het han
                            } else if (ResponseCode.equals("51")) {
                                Status = "13"; // The bi gioi han
                            } else if (ResponseCode.equals("91")) {
                                Status = "14"; // Ngan hang thu huong chua tham gia
                            } else if (ResponseCode.equals("68")) {
                                Status = "15"; // Truy van SML time out loi 68
                            }
                        } else {
                            String statusLimit = Helper.getDBI().checkLimitAmount(CustNo, Amount);
                            if (!statusLimit.equals("00")) {
                                if (statusLimit.equals("01")) {
                                    Status = "22"; // Nho hon so so tien toi thieu
                                } else if (statusLimit.equals("02")) {
                                    Status = "23"; // Vuot han muc 50000000/ 1giao dich
                                }
                            }
                        }
                    }
                } else if (TypeFunction.equals("TRN")) {
                    //Tiep tuc thuc hien transfer
                    //get SML Account
                    ResponseCode = "";
                    //Transfer
                    scb.com.vn.controller.Fcubs controller = new Fcubs();
                    //HIEUDT: Cap nhat ma san pham ban vao core, theo kenh giao dich
                    String product = Configuration.getProperty("fcubs.producttransfer.mobile.ibt");
                    String statusLimit = Helper.getDBI().checkLimitAmount(CustNo, Amount);
                    if (statusLimit.equals("00")) {
                        //Chuyen khoan di
                        String useridfcubs = "";
                        if (channel.equals("MB")) {
                            useridfcubs = Configuration.getProperty("fcubs.userid.mobile");
                        } else {
                            useridfcubs = Configuration.getProperty("fcubs.userid");
                        }

                        //SMLAccount = Helper.getDBI().getSMLAccount("FROM_SCB");
                        //String branchCust = AccountOfCust.substring(0, 3);
                        String branchCust = CommonUtils.getBranchAccount(AccountOfCust);
                        String branchSML = "000";
                        RefCORE = controller.transferFCUBSWithTimeOutFeeAndVAT(product, useridfcubs, branchCust, AccountOfCust, branchSML, SMLAccount, Amount, Desc, 30, fee, vat, new BigDecimal(0), new BigDecimal(0));

                        if (RefCORE != null) {
                            if (RefCORE.equals("TIMEOUT")) {
                                RefCORE = "";
                                Status = "16"; // Chuyen khoan FCC time out
                            } else {

                                //request transfer to Smarklink
                                //MÃ£ xá»­ lÃ½ giao dá»‹ch - 910000: YÃªu cáº§u chuyá»ƒn khoáº£n vÃ o tháº» tá»« tháº» - 910020: YÃªu cáº§u chuyá»ƒn khoáº£n vÃ o tÃ i khoáº£n tá»« tháº» - 912000: YÃªu cáº§u chuyá»ƒn khoáº£n vÃ o tháº» tá»« tÃ i khoáº£n - 912020: YÃªu cáº§u chuyá»ƒn khoáº£n vÃ o tÃ i khoáº£n tá»« tÃ i khoáº£n
                                if (TypeFromNumber.equals("CARD")) {
                                    if (TypeToNumber.equals("CARD")) {
                                        ProcessingCode = "910000";
                                    } else {
                                        ProcessingCode = "910020";
                                    }
                                } else {
                                    if (TypeToNumber.equals("CARD")) {
                                        //ProcessingCode = "912000";
                                        ProcessingCode = "910000";
                                    } else {
                                        ProcessingCode = "912020";
                                    }
                                }
                                String dataToSign = "0200" + FromNumber + ProcessingCode + strAmount + Transdate
                                        + AuditNumber + Merchant_Type + Acquiring_Code + TermID + ToNumber;
                                String paramTransfer = "0200|" + FromNumber + "|" + ProcessingCode + "|" + strAmount + "|" + Transdate + "|"
                                        + AuditNumber + "|" + Merchant_Type + "|" + Acquiring_Code + "|" + TermID + "|" + CardAcceptor + "|"
                                        + ToNumber + "|" + Desc;
                                if (TypeToNumber.equals("ACCOUNT")) {
                                    dataToSign = dataToSign + BenID;
                                    paramTransfer = paramTransfer + "|" + BenID;
                                }
                                //INSERT XUONG CORE
                                Helper.getDBI().InsertSMLLOG("FROM_SCB", FromNumber, ProcessingCode, Amount, Transdate,
                                        AuditNumber, Merchant_Type, Acquiring_Code, Authorization_Code, ResponseCode,
                                        TermID, CardAcceptor, ToNumber, Desc, BenID, TypeFunction, Status, RefCORE, CustNo, RefCORE_REFUND, Ref_no_F37, Sett_date_F15);

                                // goi qua NAPAS
                                String Signaturetf = Helper.createRequestSignature(dataToSign, Configuration.privateKeySCB_2_SML);

                                paramTransfer = paramTransfer + "|" + Ref_no_F37 + "||" + Signaturetf;
                                // String resultTransfer = Helper.callwsSmartlink(paramTransfer, 40);
                                String resultTransfer = Helper.callwsIBTNAPAS(paramTransfer, 40);
                                if (resultTransfer.equals("TIMEOUT")) {
                                    Status = "18"; // CK SML time out
                                    // DOI TRA SOAT
                                } else {
                                    String[] arrResultTransfer = resultTransfer.replace("|", "#").split("#");
                                    //Check Sinature
                                    boolean isCheckSignatureTF = Helper.verifyResponseSignature(resultTransfer.split("\\|"), Configuration.pubKeySmartlink);

                                    ResponseCode = arrResultTransfer[9];
                                    Authorization_Code = arrResultTransfer[8];
                                    if (TypeToNumber.equals("CARD")) {
                                        Sett_date_F15 = arrResultTransfer[14].toString();
                                    } else {
                                        Sett_date_F15 = arrResultTransfer[15].toString();
                                    }
                                    if (!isCheckSignatureTF) {
                                        Status = "99"; // Khong dung signature 
                                    } else if (!ResponseCode.equals("00")) {
                                        Status = ResponseCode;
                                        if (ResponseCode.equals("05")) {
                                            Status = "19"; // CHUYEN TIEN SML LOI 
                                        } else if (ResponseCode.equals("61")) {
                                            Status = "20"; // QUÃ Háº N Má»¨C Sá» TIá»€N/NGÃ€Y
                                        } else if (ResponseCode.equals("65")) {
                                            Status = "21"; // QUÃ Háº N Má»¨C Sá» Láº¦N/NGÃ€Y
                                        } else if (ResponseCode.equals("68")) {
                                            Status = "24"; // Time out do loi CK SML 68
                                        }
                                        //if(ResponseCode.equals("05")||ResponseCode.equals("61")||ResponseCode.equals("65"))
                                        if (!ResponseCode.equals("68")) {
                                            // THUC HIEN HOAN TIEN
                                            RefCORE_REFUND = controller.revertTransferFCUBS(RefCORE, 40);
                                        }
                                    }
                                }
                            }

                        } else {
                            RefCORE = "";
                            Status = "17";// KhÃ´ng thá»±c hiá»‡n cáº¯t tiá»n Ä‘Æ°á»£c tá»« FCC
                        }

                    } else {
                        if (statusLimit.equals("02")) {
                            Status = "23"; // Vuot han muc 50000000/ 1giao dich
                        }
                    }
                }
                //Insert log transfer from scb
                Helper.getDBI().InsertSMLLOG("FROM_SCB", FromNumber, ProcessingCode, Amount, Transdate,
                        AuditNumber, Merchant_Type, Acquiring_Code, Authorization_Code, ResponseCode,
                        TermID, CardAcceptor, ToNumber, Desc, BenID, TypeFunction, Status, RefCORE, CustNo, RefCORE_REFUND, Ref_no_F37, Sett_date_F15);
            }
            logger.info("result 2: " + Status + "#" + DESTNAME + "#" + RefCORE);
            return Status + "#" + DESTNAME + "#" + RefCORE;
        } catch (Exception ex) {
            logger.info("ex: " + ex.toString());
            throw ex;
        }
    }

    private boolean checkTVTPTC(String BenID, String DestAccount) {
        try {
            String[] arrayBankTPTVTC = listBankTPTVTC.split("#");
            String[] arrayAccountTPTVTC = listAccountTPTVTC.split("#");
            for (int i = 0; i < arrayBankTPTVTC.length; i++) {
                if (BenID.equalsIgnoreCase(arrayBankTPTVTC[i])) {
                    for (int j = 0; j < arrayAccountTPTVTC.length; j++) {
                        if (DestAccount.equalsIgnoreCase(arrayAccountTPTVTC[j])) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Exception ex) {
            logger.info("Loi check TVTPTC: " + ex.toString());
            return false;
        }
    }

    private boolean isFreeFee(String Account, String CustNo, String AccClass) {
        try {
            String[] listAccount = listAccountFreeFee.split("#");
            String[] listCustNo = listCustFreeFee.split("#");
            String[] listAccClass = listAccClassFreeFee.split("#");
            for (int i = 0; i < listAccount.length; i++) {
                if (Account.equalsIgnoreCase(listAccount[i])) {
                    return true;
                }
            }
            for (int i = 0; i < listCustNo.length; i++) {
                if (CustNo.equalsIgnoreCase(listCustNo[i])) {
                    return true;
                }
            }
            for (int i = 0; i < listAccClass.length; i++) {
                if (AccClass.equalsIgnoreCase(listAccClass[i])) {
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            logger.info("Loi check free fee: " + ex.toString());
            return false;
        }
    }

    private String CheckInputv2(
            String MST,
            String ProcessingCode,
            String strAmount,
            String transferdate,
            String AuditTransfer,
            String DestAccount) throws RemoteException, Exception {

        String ResponseCode = "00"; //Approved
        String AccountOfCust = DestAccount;
        String FULLNAME = "";
        String custno = "";

        try {
            if (!MST.endsWith("0200")) {
                logger.info("ex: MST is invalid: " + MST);
                return "12";
            }
            //check format amount
            String requestAmount = strAmount.substring(0, strAmount.length() - 2) + "." + strAmount.substring(strAmount.length() - 2, strAmount.length());
            if (!checkAmount(requestAmount)) {
                return "13"; // invalid amount
            }
            //Check processing code
            if (!IBTV2Controller.isCheckProcessingCode(ProcessingCode)) {
                logger.info("ex: Processing Code is invalid: " + ProcessingCode);
                return "05";
            }
            //Check transferDate
            if (!checkDate(transferdate)) {
                logger.info("ex: transfer date is invalid: " + transferdate);
                return "05";
            }
            //Check auditnumber 
            if (!Helper.getDBI().checkAuditNumberOfSML(AuditTransfer)) {

                return "05"; //auditnumber is exist : Invalid transaction
            }
            //Check dest number
            if (ProcessingCode.equals("430000") || ProcessingCode.equals("432000") || ProcessingCode.equals("910000") || ProcessingCode.equals("912000")) {
                //Truy van thong tin the
                String[] CardIfo = Helper.getDBI().getCardStatus(DestAccount);
                if (CardIfo[0].equals("01")) {
                    return "01"; //The dong : Expired card ; khong co case the mat
                } else if (CardIfo[0].equals("02")) {
                    return "54"; //The het han ,Expired card
                } else if (CardIfo[0].equals("03")) {
                    return "21"; //The chua active :Card number does not exist
                } else if (CardIfo[0].equals("04")) {
                    return "14"; //The khong ton tai : Card number does not exist
                } else if (CardIfo[0].equals("05") || CardIfo[0].equals("06")) {
                    return "01"; //TK KHONG HOP LE
                }
                if (CardIfo[0].equals("00")) {
                    AccountOfCust = CardIfo[2];
                    FULLNAME = CardIfo[1];
                    if (FULLNAME == null) {
                        FULLNAME = ""; //TK KHONG HOP LE
                    }
                } else {
                    return "01";
                }
            }
            //Truy van thong tin tai khoan
            scb.com.vn.dbi.dto.VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(AccountOfCust);
            if (custacc == null) {
                logger.info("ex: select viewcustaccount is null with account: " + AccountOfCust);
                return "14"; //TK khong ton tai : No checking account
            } else {
                char C = 'C';
                if (custacc.getRecordStat().equals(C)) {
                    logger.info("ex: Account is closed");
                    return "01"; //TK da dong : No checking account
                }
                if (!custacc.getCcy().equals("VND")) {
                    logger.info("ex: CCY of account is not VND: " + custacc.getCcy());
                    return "01";
                }
                if (custacc.getAccountClass().substring(0, 6).equals("CAI012")) {
                    return "01"; // The SCB ko hop le
                }
                if (!custacc.getAccountClass().substring(0, 3).equals("CAI")
                        && !custacc.getAccountClass().substring(0, 3).equals("CAC")
                        && !custacc.getAccountClass().equals("TCI001")
                        && !custacc.getAccountClass().equals("CDC010")
                        && !custacc.getAccountClass().substring(0, 3).equals("TCC")) {
                    logger.info("ex: AccountClass is invalid: " + custacc.getAccountClass());
                    return "01";
                }
                if (custacc.getFullName() == null) {
                    FULLNAME = "";
                } else {
                    FULLNAME = custacc.getFullName();
                }
            }
            custno = custacc.getCustNo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return "05";
        }
        return "00#" + FULLNAME + "#" + AccountOfCust + "#" + custno;
    }

    private String CheckInputFromSCB2(String FromNumber, String TypeFromNumber, String FullName, String ToNumber, String TypeToNumber,
            BigDecimal Amount, String channel, String TypeFunction, String BENID) throws RemoteException, Exception {

        //Check balance and exist From number
        if (FromNumber == "" || TypeFromNumber == "" || ToNumber == "" || TypeToNumber == "" || channel == "" || TypeFunction == "") {
            return IBTV2Controller.StatusResultEnum.INVALID_FORMAT.getValue();
        }
        if (TypeToNumber.equals("ACCOUNT")) {
            if (BENID == "") {
                return IBTV2Controller.StatusResultEnum.INVALID_FORMAT.getValue();
            }
        }
        if (Amount.compareTo(BigDecimal.valueOf(0)) == -1) {
            return IBTV2Controller.StatusResultEnum.INVALID_FORMAT.getValue();
        }
        if (!TypeFunction.equals("QUE") && !TypeFunction.equals("TRN")) {
            return IBTV2Controller.StatusResultEnum.INVALID_FORMAT.getValue();
        }
        if (!TypeFromNumber.equals("ACCOUNT") && !TypeFromNumber.equals("CARD")) {
            return IBTV2Controller.StatusResultEnum.INVALID_FORMAT.getValue();
        }
        if (!TypeToNumber.equals("ACCOUNT") && !TypeToNumber.equals("CARD")) {
            return IBTV2Controller.StatusResultEnum.INVALID_FORMAT.getValue();
        }
        if (!channel.equals("EBANK") && !channel.equals("ATM") && !channel.equals("SMS") && !channel.equals("MB") && !channel.equals("POS") && !channel.equals("COUNTER") && !channel.equals("COUNTER1") && !channel.equals("COUNTER2") && !channel.equals("COUNTER3") && !channel.equals("CH") && !channel.equals("QR") && !channel.equals("COUNTER") && !channel.equals("CRM1") && !channel.equals("CRM2") && !channel.equals("CRM3")) {
            return IBTV2Controller.StatusResultEnum.INVALID_FORMAT.getValue();
        }
        /* if (TypeToNumber.equals("CARD")) {
             if(ToNumber.length()!=16)
                {
                    return "08";
                }
            String[] checkToCard = Helper.getDBI().getCardStatus(ToNumber);
            String checkCardStatus = checkToCard[0];
            if (checkCardStatus.equals("00")) {
                return IBTV2Controller.StatusResultEnum.DESTNUMBER_INSCB.getValue();
            }
        }*/
        //Check The
        if (TypeFromNumber.equals("CARD")) {
            String[] ArrInfoCard = Helper.getDBI().getCardInfo(FromNumber, FullName, Amount);
            FromNumber = ArrInfoCard[0];
            String StatusCard = ArrInfoCard[1];
            /*
             o	00: Được phép thanh toán; 
             o	02: Thẻ bị khóa: 
             o	03: Thẻ chưa đăng ky; 
             o	04: Các thông tin về thẻ bị sai; 
             o	05: Khong du so du
             */
            if (!StatusCard.equals("00")) {
                if (StatusCard.equals("05")) {
                    return IBTV2Controller.StatusResultEnum.BALANCE.getValue();
                } else {
                    return IBTV2Controller.StatusResultEnum.INVALID_SOURCENUMBER.getValue();
                }
            }
        }
        scb.com.vn.dbi.dto.VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(FromNumber);
        if (custacc == null) {
            return IBTV2Controller.StatusResultEnum.INVALID_SOURCENUMBER.getValue();
        } else if (!custacc.getCcy().equalsIgnoreCase("VND")) {
            return IBTV2Controller.StatusResultEnum.INVALID_SOURCENUMBER.getValue();
        } else {
            BigDecimal Balance = custacc.getAcyAvlBal();

            /*
            if (!custacc.getAccountClass().substring(0, 3).equals("CAI")
                    && !custacc.getAccountClass().substring(0, 3).equals("CAC")
                    && !custacc.getAccountClass().substring(0, 3).equals("TCI")
                    && !custacc.getAccountClass().equals("CDC010")
                    && !custacc.getAccountClass().substring(0, 3).equals("TCC")) {
                return IBTV2Controller.StatusResultEnum.INVALID_SOURCENUMBER.getValue();
            }*/
            if (custacc.getAccountClass().substring(0, 3).equals("CAI") || custacc.getAccountClass().substring(0, 3).equals("CAC")) {
                BigDecimal minamount = new BigDecimal(Configuration.getProperty("ws.smartlink.minamount"));
                BigDecimal fee = new BigDecimal(Configuration.getProperty("ws.smartlink.fee"));
                BigDecimal CheckBalace = Balance.subtract(fee).subtract(Amount);
                if (CheckBalace.compareTo(BigDecimal.valueOf(0)) == -1) {
                    return IBTV2Controller.StatusResultEnum.BALANCE.getValue();
                }
            }
            // if (TypeFunction.equals("TRN")) {
            //check han muc
            String statusLimit = Helper.getDBI().checkLimitAmount(custacc.getCustNo(), Amount);
            if (!statusLimit.equals("00")) {
                if (statusLimit.equals("02")) {
                    return IBTV2Controller.StatusResultEnum.OVERLIMIT1TIME.getValue();
                } else if (statusLimit.equals("01")) {
                    return IBTV2Controller.StatusResultEnum.MINLIMIT.getValue();
                } else if (statusLimit.equals("03")) {
                    return IBTV2Controller.StatusResultEnum.OVERLIMIT1DAY.getValue();
                }
            }
            String branchCust = CommonUtils.getBranchAccount(custacc.getCustAcNo());
            String resultCheck = CommonUtils.checkOverKYC(custacc.getCustAcNo(), branchCust, Amount);
            if (!resultCheck.equals("00")) {
                return resultCheck;
            }

            // }
            return "00#" + custacc.getCustNo() + "#" + custacc.getAccountClass() + "#" + custacc.getFullName() + "#" + custacc.getAddress();
        }
    }

    public String transfeFromSCBv2(String FromNumber,
            String TypeFromNumber,
            String FullName,
            String ToNumber,
            String TypeToNumber,
            String BenID,
            BigDecimal Amount,
            String CCY,
            String channel,
            String Desc, // TAI QUAY : DESC#FEE#VAT#user_maker#KSV#idcardName#idcardAddress#idcard#idcardDob#branchcard; MB: DESC#FEE#VAT#FEETC#VATCN;
            String TermID,
            String CardAcceptor,
            String TypeFunction) throws RemoteException, Exception {

        //transferFCUBSWithTimeOutFeeAndVAT : TAI QUAY CK;COUNTER2
        //transferFCUBSCash : TAI QUAY TM;COUNTER1
        CardAcceptor = "SCB                      HCM         VNM";
        String Transdate = "";
        String AuditNumber = "";
        String Merchant_Type = "";
        String Acquiring_Code = "157979";
        String Authorization_Code = "";
        String ResponseCode = "";
        String ProcessingCode = "";
        String RefCORE = "";
        String AccountOfCust = FromNumber;
        String DESTNAME = "";
        String CustNo = "00";
        String cust_account_class = "";
        String RefCORE_REFUND = "";
        String SMLAccount = "";
        String Sett_date_F15 = "";
        BigDecimal Fee = BigDecimal.valueOf(0);
        BigDecimal Vat = BigDecimal.valueOf(0);
        BigDecimal finFee = BigDecimal.valueOf(0);
        BigDecimal finFeeVAT = BigDecimal.valueOf(0);
        String user_maker = "";
        String user_checker = "";
        // ADD for COUNTER
        String idcardName = "";
        String idcardAddress = "NULL";
        String Address = "";
        String idcard = "";
        String idcardDob = "";
        String branchCust = "";
        String branchCounter = "";
        //Add new
        char CR = '\r';
        String AddDataF48 = "";
        String PANF2 = FromNumber;
        String F22 = "000";
        String F25 = "00";
        String F60 = IBTV2Controller.getF60(channel);
        String TransREFNOF63 = "";
        String F7, F12;
        BigDecimal F5 = null;
        String x_ref = "";
        String IDREQUEST = "";
        String status = IBTV2Controller.StatusResultEnum.SUCCESS.getValue(); //00: Thanh cong; 01: NAPAS PHAN HOI THAT BAI HOAN TIEN; 02: TRU TIEN THAT BAI; 03: TRU TIEN THANH CONG DOI TRA SOAT;04: GOI CORE TIMEOUT; 05: SAI CHU KY;06: CALL NAPASTIMEOUT
        int isCallNapas = 0;
        try {
            //check the
            if (TypeToNumber.equals("CARD")) {
                ArrayList listBlackListCard = Helper.getDBI().IBT_searchBlackList(ToNumber, null, 1);
                if (listBlackListCard.size() > 0) {
                    return "13";
                }

                String BIN = ToNumber.substring(0, 6);
                String strListBINCard = Configuration.getProperty("scb.listtypecard");
                if (strListBINCard.contains(BIN)) {
                    return "01";
                }
            } else {
                ArrayList listBlackListCard = Helper.getDBI().IBT_searchBlackList(ToNumber, BenID, 1);
                if (listBlackListCard.size() > 0) {
                    return "13";
                }
            }
            if (channel.equals("EBANK") || channel.equals("CH") || channel.equals("QR")) {
                Merchant_Type = "7399";
            } else if (channel.equals("ATM")) {
                Merchant_Type = "6011";
            } else if (channel.equals("POS")) {
                Merchant_Type = "6012";
            } else if (channel.equals("MB")) {
                Merchant_Type = "7299";
            } else if (channel.equals("SMS")) {
                Merchant_Type = "6023";
            } else if (channel.equals("COUNTER")) {
                Merchant_Type = "5999";
            } else if (channel.equals("COUNTER1") || channel.equals("COUNTER2") || channel.equals("COUNTER3") || channel.equals("CRM1") || channel.equals("CRM2") || channel.equals("CRM3")) {
                //TAI QUAY TIEN MAT
                Merchant_Type = "5999";
            }

            String[] arrDesc = Desc.split("#");
            Desc = arrDesc[0];
            if (arrDesc.length >= 2) {
                if (channel.equalsIgnoreCase("CH")) {
                    IDREQUEST = arrDesc[1];
                }
            }
            if (arrDesc.length >= 3) {
                Fee = BigDecimal.valueOf(Long.valueOf(arrDesc[1]));
                Vat = BigDecimal.valueOf(Long.valueOf(arrDesc[2]));
            }
            if (arrDesc.length >= 5) {
                if (channel.equalsIgnoreCase("MB") || channel.equalsIgnoreCase("EBANK") || channel.equals("QR")) {
                    finFee = BigDecimal.valueOf(Long.valueOf(arrDesc[3]));
                    finFeeVAT = BigDecimal.valueOf(Long.valueOf(arrDesc[4]));
                } else {
                    // COUNTER1; COUNTER2
                    user_maker = arrDesc[3];
                    user_checker = arrDesc[4];
                }
            }
            if (channel.equals("MB") || channel.equals("EBANK") || channel.equals("QR")) {
                if (arrDesc.length >= 6) {
                    ProductFromSCB_MOBILE = arrDesc[5];
                }
            }
            if (arrDesc.length >= 11) {
                // COUNTER1;
                idcardName = arrDesc[5];
                Address = arrDesc[6];
                idcard = arrDesc[7];
                idcardDob = arrDesc[8];;
                idcardAddress = arrDesc[9];
                branchCounter = arrDesc[10];
                if (arrDesc.length >= 12) {
                    IDREQUEST = arrDesc[11] == null ? "" : arrDesc[11];
                }
            }

            if (!channel.endsWith("COUNTER1") & !channel.endsWith("COUNTER3") && !channel.endsWith("CRM1") & !channel.endsWith("CRM3")) {
                Helper.writeLogErrorNonDB(IBTController.class, "CheckInputFromSCB2 begin");
                String resultCheck = CheckInputFromSCB2(FromNumber, TypeFromNumber, FullName, ToNumber, TypeToNumber,
                        Amount, channel, TypeFunction, BenID);
                Helper.writeLogErrorNonDB(IBTController.class, "CheckInputFromSCB2 resultCheck:" + resultCheck);
                String[] resultArr = resultCheck.split("#");
                status = resultArr[0];
                if (!status.equals("00")) {
                    return status;
                }
                branchCust = CommonUtils.getBranchAccount(AccountOfCust);
                AddDataF48 = resultArr[3] + CR + resultArr[4];
                FullName = resultArr[3];
                Address = resultArr[4];
                CustNo = resultArr[1];
                cust_account_class = resultArr[2];
            } else {
                AddDataF48 = idcardName + CR + idcardAddress;
                FullName = idcardName;
            }
            if (status.equals("00")) {
                String hidencard = "";
                String v_destnumber = ToNumber;
                //Check exist tonumber
                if (TypeToNumber.equals("CARD")) {
                    BenID = "";
                    //2/11/2023 dieu chinh che the theo format moi cua napas: the 15-19 so
                    //chuoi hidden mac dinh la XXXXXX, khong phu thuoc chieu dai the
                    if(ToNumber.length()>=15 && ToNumber.length()<=19){
                        hidencard = ToNumber.substring(6, ToNumber.length()-4);
                        v_destnumber = cheThe_New(ToNumber, "XXXXXX");
                    }
//                    if (ToNumber.length() == 16) {
//                        hidencard = ToNumber.substring(6, 12);
//                        v_destnumber = cheThe(ToNumber, "XXXXXX");
//                    }
                }
                DecimalFormat formater = new DecimalFormat("0.00");
                String strAmount = Helper.addExtraChar(formater.format(Amount).replace(".", "").replace(",", ""), 12, "0");
                Date currentdate = new Date();
                SimpleDateFormat sdfDestination = new SimpleDateFormat("MMddHHmmss");
                Transdate = sdfDestination.format(currentdate);

                sdfDestination.setTimeZone(TimeZone.getTimeZone("GMT"));
                F7 = sdfDestination.format(currentdate);

                SimpleDateFormat fDateF37 = new SimpleDateFormat("DDDHH");
                fDateF37.setTimeZone(TimeZone.getTimeZone("GMT"));

                SimpleDateFormat fDateF37Y = new SimpleDateFormat("Y");
                String Ref_no_F37 = fDateF37Y.format(currentdate).substring(3, 4) + fDateF37.format(currentdate); //add by Lydty,14/Aug/2017 
                AuditNumber = Helper.getDBI().getAuditNumber(); //Audit check
                Ref_no_F37 = Ref_no_F37 + AuditNumber; //add by Lydty,14/Aug/2017 

                ProcessingCode = IBTV2Controller.getProcessingCode(TypeFromNumber, TypeToNumber, TypeFunction);

                if (TypeFunction.equals("QUE")) {
                    strAmount = "000000000000";
                }

                NAPAS_IBT IBT = new NAPAS_IBT();
                IBT.setACQUIRINGCODE(Acquiring_Code);
                IBT.setAUDITNUMBER(AuditNumber);
                IBT.setAddDataF48(AddDataF48);
                IBT.setBENID(BenID);
                IBT.setCARDACCEPTTOR(CardAcceptor);
                IBT.setCCYF49(CCY);
                IBT.setCUSTNO(CustNo);
                IBT.setDESTNUMBER(v_destnumber);
                IBT.setF22(F22);
                IBT.setF25(F25);
                IBT.setF60(F60);
                IBT.setFROMNUMBER(FromNumber);
                IBT.setMERCHANTTYPE(Merchant_Type);
                IBT.setNARRATION(cheThe(VNCharacterUtils.removeAccent(Desc), "..."));
                IBT.setPANF2(PANF2);
                IBT.setPROCESSINGCODE(ProcessingCode);
                IBT.setREF_NO_F37(Ref_no_F37);
                IBT.setTERMID(TermID);
                IBT.setTRANSAMOUNT(Amount);
                IBT.setTRANSDATE(Transdate);
                IBT.setTYPEFUNCTION(TypeFunction);
                IBT.setTYPETRANSFER("FROM_SCB");
                IBT.setStatus("16");
                IBT.setHIDENCARD(hidencard);
                IBT.setREFNO(IDREQUEST);
                IBT.setFullName(VNCharacterUtils.removeAccent(FullName));
                IBT.setAddress(VNCharacterUtils.removeAccent(Address));
                int idInsert = Helper.getDBI().NAPAS_INSERTIBTLOG(IBT);
                logger.info("insert ibt xong");
                if (idInsert <= 0) {
                    logger.info("GD NAY KHONG GUI NAPAS -LOI INSERT DUOC XUONG DBGW DESTNUMBER:" + v_destnumber);
                    return "01";
                }
                scb.com.vn.controller.Fcubs controller = new Fcubs();
                if (TypeFunction.equals("TRN")) {
                    logger.info("batdau hach toan ibt:" + AuditNumber);
                    //Chuyen khoan di
                    String useridfcubs = useridfcubs_EB;

                    String branchSML = "000";
                    String product = "";
                    String refno = CommonUtils.getRefno("11");
                    if (channel.equals("EBANK") || channel.equals("MB") || channel.equals("QR")) {
                        if (channel.equals("MB") || channel.equals("QR")) {
                            useridfcubs = useridfcubs_MB;
                        }
                        product = ProductFromSCB_MOBILE;
                        //RefCORE = controller.transferFCUBSWithTimeOutFeeAndVAT(product, useridfcubs, branchCust, AccountOfCust, branchSML, SMLAccount, Amount, Desc, 30, Fee, Vat, finFee, finFeeVAT);
                        // RefCORE = controller.transferFCUBSWithTimeOutFeeAndVATRefno(product, useridfcubs, branchCust, AccountOfCust, branchSML, SMLAccount, Amount, Desc, 30, Fee, Vat, finFee, finFeeVAT,refno);
                        String[] resultRefCORE = controller.transferFCUBSWithTimeOutFeeAndVATRefno_247(product, useridfcubs, branchCust, AccountOfCust, branchSML, SMLAccount, Amount, Desc, 30, Fee, Vat, finFee, finFeeVAT, refno, ToNumber, BenID == null ? "" : BenID);
                        Helper.writeLog(IBTController.class, Level.INFO, "resultRefCORE:" + resultRefCORE);
                        RefCORE = null;
                        if (resultRefCORE != null) {
                            if (resultRefCORE[0].equals("0")) {
                                status = IBTV2Controller.StatusResultEnum.SUCCESS.getValue();
                                RefCORE = resultRefCORE[1];
                            }
                            if (resultRefCORE[0].equals("-2")) {
                                status = IBTV2Controller.StatusResultEnum.CORE_TIMEOUT.getValue();
                            }
                            if (resultRefCORE[0].equals("-1")) {

                                RefCORE = null;
                                if (resultRefCORE[1].equals("AC-SCB06")) {
                                    status = IBTV2Controller.StatusResultEnum.COREFAILED_OVERLIMITKYC.getValue();
                                } else {
                                    status = IBTV2Controller.StatusResultEnum.COREFAILED.getValue();
                                }
                            }
                        }
                    } else if (channel.equals("CH")) {
                        product = ProductTCH;
                        //RefCORE = controller.transferFCUBSWithTimeOutFeeAndVAT(product, useridfcubs, branchCust, AccountOfCust, branchSML, SMLAccount, Amount, Desc, 30, Fee, Vat, finFee, finFeeVAT);
                        RefCORE = controller.transferFCUBSWithReturnTimeOutRefno(product, useridfcubs, branchCust, AccountOfCust, branchSML, NapasAccountISS, Amount, Desc, 30, refno);
                    } else if (channel.equals("COUNTER1") || channel.equals("CRM1")) {
                        // Chuyen khoan tai quay bang tien mat
                        product = ProductFromSCB_COUNTER1;

                        Helper.writeLog(IBTController.class, Level.INFO, "[IBT]: Tham so gui qua core: "
                                + "\n pm_product: "+ProductFromSCB_COUNTER1+" "
                                + "\n pm_cr_brn:"+ "000"
                                + "\n pm_cr_acc: "+ "459906034"
                                + "\n pm_amt: "+Amount.toString()
                                + "\n pm_loaitien: "+"VND"
                                + "\n pm_noidung: "+Desc
                                + "\n pm_tennguoinop: "+idcardName
                                + "\n pm_diachinguoinop: "+Address
                                + "\n pm_socmnd: "+idcard
                                + "\n pm_ngaycap: "+idcardDob
                                + "\n pm_noicap: "+idcardAddress
                                + "\n pm_gdv: "+user_maker.toUpperCase()
                                + "\n pm_ksv: "+user_checker.toUpperCase()
                                + "\n destAccount: "+ToNumber
                                + "\n bankCode: "+BenID
                        );

                        String[] result = Helper.getDBI().transferFCUBSForIBTCounter_247(ProductFromSCB_COUNTER1,
                                "000",
                                "459906034",
                                Amount.toString(),
                                "VND",
                                Desc, idcardName, Address,
                                idcard,
                                idcardDob,
                                idcardAddress,
                                user_maker.toUpperCase(),
                                user_checker.toUpperCase(),
                                ToNumber,
                                BenID);
                        Helper.writeLog(IBTController.class, Level.INFO, "[IBT]: KET QUA HACH TOAN QUA CORE CHO SO TRACE: " + AuditNumber + " LA: " + RefCORE + " -KQ[1]:" + result[1] + " -KQ[2]:" + result[2] + " -KQ[3]:" + result[3]);
                        if (result != null) {
                            RefCORE = result[0];
                            if (RefCORE != null && !RefCORE.equals("null") && !RefCORE.equals("")) {
                                x_ref = result[1];
                                refno = x_ref;
                            }
                        }
                    } else if (channel.equals("COUNTER2") || channel.equals("CRM2")) {
                        product = ProductFromSCB_COUNTER2;
                        //RefCORE = controller.transferFCUBSCounter(product, AccountOfCust, "", Amount, Desc, branchCounter,refno);
                        // Chuyen khoan tai quay bang chhuyen khoan
                        String[] resultRefCORE = controller.transferFCUBSForCounter_247(product, useridfcubs, branchCust, AccountOfCust, branchSML, NapasAccountISS,
                                Amount, Desc, branchCounter, refno, ToNumber, BenID == null ? "" : BenID);
                        RefCORE = null;
                        if (resultRefCORE != null) {
                            if (resultRefCORE[0].equals("0")) {
                                RefCORE = resultRefCORE[1];
                                status = IBTV2Controller.StatusResultEnum.SUCCESS.getValue();
                            } else if (resultRefCORE[0].equals("-2")) {
                                status = IBTV2Controller.StatusResultEnum.CORE_TIMEOUT.getValue();
                            } else if (resultRefCORE[0].equals("-1")) {
                                RefCORE = null;
                                if (resultRefCORE[1].equals("AC-SCB06")) {
                                    status = IBTV2Controller.StatusResultEnum.COREFAILED_OVERLIMITKYC.getValue();
                                }
                            }
                        }

                    } else if (channel.equals("COUNTER3") || channel.equals("CRM3")) {
                        product = ProductFromSCB_COUNTER3;
                        RefCORE = controller.transferFCUBSWithReturnTimeOutRefno(product, useridfcubs, branchCounter, AccountOfCust, branchSML, NapasAccountISS, Amount, Desc, 30, refno);
                    }

                    Helper.writeLog(IBTController.class, Level.INFO, "[IBT]: KET QUA HACH TOAN QUA CORE CHO SO TRACE: " + AuditNumber + " LA: " + RefCORE + "refno:" + refno);
                    // RefCORE="TEST";
                    if (status.equals(IBTV2Controller.StatusResultEnum.SUCCESS.getValue())) {
                        if (RefCORE != null) {
                            if (RefCORE.equals("TIMEOUT")) {
                                status = IBTV2Controller.StatusResultEnum.CORE_TIMEOUT.getValue();

                            } else {
                                status = IBTV2Controller.StatusResultEnum.SUCCESS.getValue();
                            }
                        } else {
                            status = IBTV2Controller.StatusResultEnum.COREFAILED.getValue();
                            RefCORE = "";

                        }
                    }
                    //Update so ref sau hach toan
                    // Helper.getDBI().IBT_UPDATE_STATUS_TRANSFER_XREF(AuditNumber, status=="00"?"18":status, RefCORE, "", Ref_no_F37, Sett_date_F15, "FROM_SCB", "", "", "","",x_ref);
                    int resultUpdate = Helper.getDBI().updateIBT(AuditNumber, status == "00" ? "16" : status, RefCORE, "FROM_SCB", String.valueOf(idInsert), refno);
                    if (resultUpdate != 1) {
                        status = "16";
                        Helper.writeLogErrorNonDB(IBTController.class, "Khong update duoc refcore:" + AuditNumber);
                    }
                }              
                if (status.equals("00")) {
                    //Insert log into 
                    isCallNapas = Helper.getDBI().insertIBTRequestLog(AuditNumber);
                    if (isCallNapas > 0) {
                        String requestTransfer = Xml.Marshaller(IBT);
                        Helper.writeLog(IBTController.class, Level.INFO, "Request To Napas:" + requestTransfer);
                        IBTV2Controller ibtv2 = new IBTV2Controller();
                        //ibtv2.putRequest(requestTransfer);
                        int timeout = 20;
                        int timesleep = 500;
                        boolean isResponse = false;
                        long begintime = System.currentTimeMillis();
                        String resultCheckToNumber = null;
                        while (!isResponse) {
//                            resultCheckToNumber=ibtv2.getResponse(AuditNumber +Ref_no_F37);
                            /*Gia lap kq thanh cong va that bai cua napas tra ve*/
                            resultCheckToNumber= ibtv2.simulatorResponseFromNapas(channel, TypeToNumber, ToNumber);
                            //resultCheckToNumber = Helper.getDBI().NAPAS_getResponseIBT(AuditNumber);
                            if (resultCheckToNumber != null) {
                                isResponse = true;
                                break;
                            }
                            long endtime = System.currentTimeMillis();
                            if (endtime - begintime >= timeout * 1000) {
                                /*
                             resultCheckToNumber = Helper.getDBI().NAPAS_getResponseIBT(AuditNumber);
                             if (endtime - begintime >= timeout*2 * 1000) 
                             {
                                isResponse = true;
                                break;
                             }*/
                                isResponse = true;
                                break;
                            }
                            Thread.sleep(timesleep);
                        }
                        if (resultCheckToNumber == null) {
                            Helper.writeLog(IBTController.class, Level.INFO, "[IBT]CALL NAPAS TIMEOUT TRACE: " + AuditNumber);
                            if (TypeFunction.equals("QUE")) {
                                status = IBTV2Controller.StatusResultEnum.QUE_TIMEOUT.getValue();
                            } else {
                                status = IBTV2Controller.StatusResultEnum.TIMEOUT.getValue();
                            }
                            // DOI TRA SOAT
                        } else {
                            Helper.writeLog(IBTController.class, Level.INFO, "[IBT]:Response from Napas [" + AuditNumber + "]:" + resultCheckToNumber);
                            //CODE TOI UU.
                            if (resultCheckToNumber != null) {
                                String[] listResult = resultCheckToNumber.split("#");
                                status = listResult[0];
                                ResponseCode = listResult[1];
                                if (listResult.length >= 3) {
                                    DESTNAME = listResult[2];
                                }
                                if (TypeFunction.equals("TRN")) {
                                    if (!status.equals("26") && !status.equals("27") && !status.equals("00")) {
                                        if (ResponseCode.length() == 2) {
                                            if (!ResponseCode.equals("68") && !ResponseCode.equals("00")) {

                                                if (channel.equals("COUNTER1") || channel.equals("CRM1")) {
                                                    RefCORE_REFUND = Helper.getDBI().revertTransferFCUBS(RefCORE, x_ref, user_maker.toUpperCase(), user_checker.toUpperCase());
                                                } else {
                                                    RefCORE_REFUND = controller.revertTransferFCUBS(RefCORE, 40);
                                                }
                                                Helper.writeLog(IBTController.class, Level.INFO, "Thực hiện revert va update kq cho số ref: " + RefCORE + " Kết quả:" + RefCORE_REFUND);
                                                Helper.getDBI().UpdateRevertTransfer(AuditNumber, RefCORE, RefCORE_REFUND, String.valueOf(idInsert));
                                            }
                                        }
                                    }
                                }
                            } else {
                                status = "18";
                            }
                        }
                    } else {

                        Helper.writeLog(IBTController.class, Level.INFO, "Insert trace that bai khong call Napas+" + AuditNumber);
                        // thuc hoan tien;
                    }

                }
            }
        } catch (Exception ex) {
            Helper.writeLog(IBTController.class, Level.INFO, "LOI IBT: " + ex.toString());
            if (isCallNapas > 0) {
                status = "18";

            } else {
                status = IBTV2Controller.StatusResultEnum.ERROR_SYSTEM.getValue();
            }
        }
        if (status.equals("26") || status.equals("27") || status.equals("99")) {
            if (TypeFunction.equals("TRN")) {
                status = "24"; //cho tra soat
            }
        }
        Helper.writeLog(IBTController.class, Level.INFO, "KQ TRA VE CHO TRACE:" + AuditNumber + " LA:" + status + "#" + DESTNAME + "#" + RefCORE);
        return status + "#" + DESTNAME + "#" + RefCORE;
    }

    public String transferToSCBv2(String requestData) throws RemoteException, Exception {
        try {
            requestData = convertStrByteToString(requestData);
            //logger.info( "Request:" +requestData);
            String ResponseCode = "00"; //Approved
            String AuthorizationCode = Helper.getDBI().getAuthorizationCode().trim();
            IBTV2Controller ibtv2 = new IBTV2Controller();
            //
            String[] result = ibtv2.readMsg(requestData);
            writelogxml(result);
            String Status = "";
            String RefCore = "";
            String CardName = "";
            String MST = result[0];
            String FromNumber = result[102].substring(2);
            String ProcessingCode = result[3];
            String strAmount = result[4];
            String transferdate = result[7];//MMDDhhmmss
            String AuditNumber = result[11];
            String MerchantType = result[18];
            Acquiring_Code = result[32].substring(2);
            String Ref_no_F37 = result[37];
            String CardAcceptor = result[43];
            String Sett_date_F15 = result[15];
            String TermId = result[41];
            String BenID = "";
            if (result[100] != null) {
                BenID = result[100].substring(2);
            }
            String DestAccount = result[103].substring(2);
            String Desc = result[104].substring(3);
            String MAC = result[128];
            String AccountOfCust = DestAccount;
            String PANF2 = result[2]; //new
            String AddDataF48 = result[48].substring(3);//new
            String TransREFNOF63 = result[63]; //Addnew
            String CCYF49 = result[49]; //Addnew
            String F22 = result[22];// new 
            String F25 = result[25];//new
            String F60 = result[60].substring(3);//new
            String FULLNAME = "";
            String TypeFunction = "";
            String CustNo = "";
            String SMLAccount;
            String F7 = result[7];//new
            String F102 = result[102].substring(2);
            String F9 = result[9];//new
            String F18 = null;
            String F12 = result[12];
            String F13 = result[13];
            String F41 = result[41];
            if (result[18] != null) {
                F18 = result[18];
            }
            BigDecimal tranferAmount = BigDecimal.valueOf(0);
            //Check signatue
            boolean isCheckSignatureTF = IBTV2Controller.isVerifyMac(result);
            if (!isCheckSignatureTF) {
                ResponseCode = "63";
            } else {
                String[] resultCheck = CheckInputv2(MST, ProcessingCode, strAmount, transferdate, AuditNumber, DestAccount).split("#");
                ResponseCode = resultCheck[0];
                if (ProcessingCode.substring(0, 2).endsWith("43")) {
                    TypeFunction = "QUE";
                } else {
                    TypeFunction = "TRN";
                }
                if (ResponseCode.equals("00")) {
                    FULLNAME = resultCheck[1];
                    CustNo = resultCheck[3];
                    String requestAmount = strAmount.substring(0, strAmount.length() - 2) + "." + strAmount.substring(strAmount.length() - 2, strAmount.length());
                    tranferAmount = new BigDecimal(requestAmount);
                    if (ProcessingCode.equals("430000") || ProcessingCode.equals("432000") || ProcessingCode.equals("910000") || ProcessingCode.equals("912000")) {
                        AccountOfCust = resultCheck[2];
                    }
                    //Check processing code
                    if (TypeFunction.equals("TRN")) {
                        //Thuc hien chuyen khoan
                        scb.com.vn.controller.Fcubs controller = new Fcubs();
                        String product = ProductToSCB;
                        //Chuyen khoan den
                        //SMLAccount = Helper.getDBI().getSMLAccount("TO_SCB");
                        String useridfcubs = useridfcubs_EB;
                        SMLAccount = Helper.getDBI().getSMLAccount("FROM_SCB");
                        String branchCust = CommonUtils.getBranchAccount(AccountOfCust);
                        String branchSML = "000";
                        logger.info("[IBT]Bat dau goi qua core de hach toan cho TRACE: " + AuditNumber);
                        RefCore = controller.transferFCUBSWithTimeOut(product, useridfcubs, branchSML, SMLAccount, branchCust, AccountOfCust, tranferAmount, Desc, 30);
                        // RefCore="TEST"; 
                        logger.info("[IBT]Core tra kq cho trace:" + AuditNumber + " LA: " + RefCore);
                        if (RefCore == null || RefCore.equals("")) {
                            ResponseCode = "05";
                            Status = IBTV2Controller.StatusResultEnum.UNSUCCESS.getValue();
                        } else if (RefCore.equals("TIMEOUT")) {
                            ResponseCode = "68";
                            Status = IBTV2Controller.StatusResultEnum.CORE_TIMEOUT.getValue();
                            logger.info("[IBT]Giao dich goi qua core timeout cho trace:" + AuditNumber);
                        } else {
                            Status = IBTV2Controller.StatusResultEnum.SUCCESS.getValue();
                        }
                    }
                }
                if (ResponseCode.equals("00")) {
                    Status = IBTV2Controller.StatusResultEnum.SUCCESS.getValue();
                }
                String AcquiringCode = "";
                //InsertLog
                //Helper.writeLogErrorNonDB(Controller.class,  "Insert database");
                NAPAS_IBT IBT = new NAPAS_IBT();
                IBT.setACQUIRINGCODE(AcquiringCode);
                IBT.setAUDITNUMBER(AuditNumber);
                IBT.setAUTHORIZATIONCODE(AuthorizationCode);
                IBT.setAddDataF48(AddDataF48);
                IBT.setBENID(BenID);
                IBT.setCARDACCEPTTOR(CardAcceptor);
                IBT.setCCYF49(CCYF49);
                IBT.setCUSTNO(CustNo);
                IBT.setDESTNUMBER(DestAccount);
                IBT.setF22(F22);
                IBT.setF25(F25);
                IBT.setF60(F60);
                IBT.setFROMNUMBER(FromNumber);
                IBT.setMERCHANTTYPE(MerchantType);
                IBT.setNARRATION(Desc);
                IBT.setPANF2(PANF2);
                IBT.setPROCESSINGCODE(ProcessingCode);
                IBT.setRefCore(RefCore);
                IBT.setSETT_DATE_F15(Sett_date_F15);
                IBT.setStatus(Status);
                IBT.setTERMID(TermId);
                IBT.setTRANSAMOUNT(tranferAmount);
                IBT.setTRANSDATE(transferdate);
                IBT.setTYPEFUNCTION(TypeFunction);
                IBT.setTYPETRANSFER("TO_SCB");
                IBT.setTransREFNOF63(TransREFNOF63);
                IBT.setRESPONSECODE(ResponseCode);
                Helper.getDBI().NAPAS_INSERTIBTLOG(IBT);
            }
            String msgtype = "0210";
            String[] dataRequest = IBTV2Controller.getDataForCall(FromNumber, ProcessingCode,
                    strAmount, transferdate, AuditNumber,
                    Ref_no_F37, CCYF49, F60, TypeFunction,
                    BenID, DestAccount, Desc, FULLNAME, msgtype, ResponseCode, AddDataF48, F7, Acquiring_Code,
                    Sett_date_F15, TransREFNOF63, F102, F9, F18, F12, F13, F41);
            writelogxml(dataRequest);
            String strResponse = IBTV2Controller.createMsg(dataRequest);
            //logger.info( "RESPONSE FROM SCB:"+strResponse);
            strResponse = convertStringtoStrByte(strResponse);
            return strResponse;
        } catch (Exception ex) {
            logger.info("Exception:" + ex.getMessage());
            return "96";
        }
    }

    public String insertResponseIBT(String response, String ID) throws RemoteException {
        try {
            Helper.getDBI().NAPAS_insertResponseIBT(response, ID);
            return "1";
        } catch (Exception ex) {
            logger.info("ex: " + ex.toString());
            return "0";
        }
    }

    public static void writelogxml(String[] input) {
        String xml = "<data>";
        for (int i = 0; i < input.length; i++) {
            if (input[i] != null) {
                xml = xml + "<field id=" + i + " value=" + input[i] + "/>";
            }
        }
        logger.info("data: " + xml);
    }

    public static String convertStrByteToString(String requestData) {
        String[] ArrayData = requestData.split("#");
        byte[] bData = new byte[ArrayData.length];
        for (int i = 0; i < bData.length; i++) {
            int a = Integer.valueOf(ArrayData[i]);
            byte b = (byte) a;
            bData[i] = b;
        }
        requestData = new String(bData);
        return requestData;
    }

    private static String convertStringtoStrByte(String data) {
        byte[] bRequest = data.getBytes();
        String sRequest = "";
        for (int i = 0; i < bRequest.length; i++) {
            sRequest = sRequest + bRequest[i] + "#";
        }
        return sRequest;
    }

    private boolean isAccountTCH(String Account) {
        String[] listAccount = AccountTCH.split(";");
        for (String item : listAccount) {
            if (item.equals(Account)) {
                return true;
            }
        }
        return false;
    }

    public String getResponseEcho(String requestData) throws Exception {
        try {
            requestData = convertStrByteToString(requestData);
            IBTV2Controller ibtv2 = new IBTV2Controller();
            String[] result = ibtv2.readMsg(requestData);
            result[0] = "0810";
            result[39] = "00";
            String strResponse = IBTV2Controller.createMsg(result);
            strResponse = convertStringtoStrByte(strResponse);
            return strResponse;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public TransferMoney247DetailRes transferMoney247(TransferMoney247DetailReq req) throws Exception {
        TransferMoney247DetailRes response = new TransferMoney247DetailRes();
        try {
            // kiem tra du lieu co valid hay ko, kiem tra so tien co du de giao dich hay ko
            if (isValidData(req) && setupCifAccClass(req)) {
                // lay thoi gian hien tai de truyen qua NAPAS
                String transdate = req.getTransDate("MMddHHmmss");
                // lay transaction Id tu duoi database de goi qua NAPAS
                String auditNumber = Helper.getDBI().getAuditNumber();
                String refNoF37 = req.getDateRefNo(auditNumber);
                // kiem tra neu la giao dich query
                if (req.isQueryReq()) {
                    String message = req.buildMessageToCallNapas(auditNumber,
                            Configuration.privateKeySCB_2_SML, Acquiring_Code, transdate, refNoF37);
                    String napasResponse = Helper.callwsIBTNAPAS(message, 40);
                    logger.info("napasResponse = " + napasResponse);
                    if (napasResponse != null) {
                        if ("TIMEOUT".equals(napasResponse)) {
                            response.setResponseCode("07");
                            /* Truy van SML time out */
                        } else {
                            // verify message tra ve cua NAPAS va cau hinh response
                            response.updateNapasResponseForQuery(req, napasResponse,
                                    Configuration.pubKeySmartlink);
                            if ("00".equals(response.getNapasResponseCode())) {
                                // kiem tra han muc giao dich
                                String statusLimit = Helper.getDBI().checkLimitAmount(req.getCif(), req.getExchangeRateRes().getMoneyExchange());
                                switch (statusLimit) {
                                    case "01": // Nho hon so so tien toi thieu. 0 VND
                                        response.setResponseCode("22");
                                        break;
                                    case "02": // Vuot han muc 300.000.000 VND/1 giao dich
                                        response.setResponseCode("23");
                                        break;
                                    case "00": // duoc phep giao dich
                                        response.setResponseCode("00");
                                        break;
                                    default:
                                        /* Source cu ko bat truong hop default */
                                        response.setResponseCode(ResponseCodeEnum.SYSTEMERROR.name());
                                        break;
                                }
                            }
                        }
                    } else {
                        /* Code cu ko thay xu ly nen tam thoi de ma loi 99 */
                        response.setResponseCode("99");
                        logger.warn("Failed to call NAPAS. Napas response = " + napasResponse);
                    }
                } else if (req.isTransReq()) {
                    /* day la giao dich chuyen khoan */
                    String statusLimit = Helper.getDBI().checkLimitAmount(req.getCif(), req.getExchangeRateRes().getMoneyExchange());
                    switch (statusLimit) {
                        case "00":
                            // thuc hien goi qua core tru tien
                            String refCore = executeTransferToCore(req);
                            // kiem tra xem da tru tien thanh cong hay ko
                            if (refCore != null && !"null".equalsIgnoreCase(refCore)) {
                                if ("TIMEOUT".equals(refCore)) {
                                    /* Chuyen khoan FCC time out */
                                    response.setResponseCode("16");
                                } else {
                                    // cau hinh du lieu tra ve
                                    response.setResponseCode("18");
                                    response.setRefCore(refCore);
                                    String message = req.buildMessageToCallNapas(auditNumber,
                                            Configuration.privateKeySCB_2_SML, Acquiring_Code,
                                            transdate, refNoF37);
                                    // insert du lieu goi qua NAPAS xuong database
                                    SiUtils.insertNapasLog(req, response, transdate, auditNumber,
                                            refNoF37, Acquiring_Code);
                                    String napasResponse = Helper.callwsIBTNAPAS(message, 40);
                                    logger.info("napasResponse = " + napasResponse);
                                    if (napasResponse != null) {
                                        // verify message tra ve cua NAPAS va cau hinh response
                                        response.updateNapasResponseForTrans(req, napasResponse,
                                                Configuration.pubKeySmartlink);
                                        // kiem tra cac ma loi se ko hoan tien. empty, 00, 18, 68.
                                        // Kiem tra lai logic cho nay. Le ra phai define cac ma loi hoan tien chu sao lai define cac ma loi giu lai tien
                                        if (response.isNeedToRefund(req)) {
                                            logger.warn("Thuc hien hoan tien vi NAPAS tra loi [" + response.getNapasResponseCode() + "]");
                                            executeRefundToCore(req, response);
                                        }
                                    } else {
                                        /* Goi qua napas bi loi --> mac dinh hold giao dich tra ve ma loi 18 */
                                        logger.warn("Failed to call NAPAS. Napas response = " + napasResponse);
                                    }
                                }
                            } else {
                                /* Co loi khi thực hiện cắt tiền được từ FCC */
                                response.setResponseCode("17");
                            }
                            break;
                        case "02": // Vuot han muc 300.000.000 VND/1 giao dich
                            response.setResponseCode("23");
                            break;
                        case "01":
                        /* Source cu ko bat truong hop 01 */ // Nho hon so so tien toi thieu. 0 VND
                        default:
                            /* Source cu ko bat default = 00 --> Sai logic */
                            response.setResponseCode(ResponseCodeEnum.SYSTEMERROR.name());
                            break;
                    }
                }
                // insert du lieu phan hoi cua NAPAS xuong database
                SiUtils.insertNapasLog(req, response, transdate, auditNumber, refNoF37, Acquiring_Code);
            } else {
                response.setResponseCode(ResponseCodeEnum.INVALIDVALUE.name());
            }
            // cap nhat lai gia tri tra ve cho kenh EB, chac co the chi Kim ko dung ma 99 cho EB, MB
            response.updateStatusForMBEB(req);
        } catch (Exception ex) {
            logger.error(ex);
            response.setResponseCode(ResponseCodeEnum.SYSTEMERROR.name());
        }
        return response;
    }

    private boolean executeRefundToCore(TransferMoney247DetailReq req,
            TransferMoney247DetailRes response) throws Exception {
        boolean result = false;
        Fcubs controller = new Fcubs();
        try {
            // Thuc hien goi qua core revert tien cho khach hang
            String refCoreRefund = controller.revertTransferFCUBS(response.getRefCore(), 40);
            response.setRefCoreRefund(refCoreRefund);
            result = true;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
        return result;
    }

    private String executeTransferToCore(TransferMoney247DetailReq req) {
        Fcubs controller = new Fcubs();
        String refCore = null;
        try {
            // lay branch cua tai khoan cat tien
            String branchCust = CommonUtils.getBranchAccount(req.getFromNumber());
            req.setBranchCust(branchCust);

            // thuc hien goi qua core tru tien
            refCore = controller.transferFCUBSWithTimeOut(req, 30);
        } catch (Exception e) {
            logger.error(e);
        }
        return refCore;
    }

    private boolean toNumberIsSCBCard(TransferMoney247DetailReq req) {
        boolean result = false;
        try {
            if ("CARD".equals(req.getTypeToNumber())) {
                String[] cardInfo = Helper.getDBI().getCardStatus(req.getToNumber());
                /* Kiem tra lai le ra theo logic thi chi co truong hop 04 moi duoc phep di tiep */
 /* 00: binh thuong, 01: the dong, 02: the het han, 03: the chua activate
                , 04: so the khong ton tai */
                String cardStatus = cardInfo[0];
                if ("00".equals(cardStatus)) {
                    /* THẺ ĐICH THUỘC NGÂN HÀNG SCB */
                    result = true;
                    logger.warn("card [" + req.getToNumber() + "] is SCB card. TypeCard = ["
                            + req.getTypeToNumber() + "]");
                }
            }
        } catch (Exception e) {
            result = true;
            /* Tam thoi chan ko cho chuyen neu bi loi ko xac dinh duoc tk */
            logger.error(e);
        }
        return result;
    }

    private boolean setupCifAccClass(TransferMoney247DetailReq req) {
        boolean result = false;
        try {
            VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(req.getFromNumber());
            if (custacc != null && req.getCcy().equals(custacc.getCcy())) {
                /* Theo logic cu thi reject neu !custacc.getCcy().equalsIgnoreCase("VND") */
                req.setAvailableBalance(custacc.getAcyAvlBal());
                req.setAccountClass(custacc.getAccountClass());
                req.setCif(custacc.getCustNo());
                if (!"CDC010".equals(req.getAccountClass())) {
                    switch (req.getSubAccountClass()) {
                        case "TCI":
                        case "TCC":
                            result = true;
                            break;
                        case "CAI":
                        case "CAC":
                            if ("TRN".equals(req.getTypeFunction())) {
                                BigDecimal minamount = new BigDecimal(Configuration.getProperty("ws.smartlink.minamount"));
                                BigDecimal fee = new BigDecimal(Configuration.getProperty("ws.smartlink.fee"));
                                BigDecimal checkBalace;
                                /* Logic cu thi ko co check VND va co tru them fee */
                                if ("VND".equals(req.getCcy())) {
                                    if (req.getAmount().compareTo(minamount) == -1) {
                                        logger.warn("So tien nho hon so tien cau hinh toi thieu chuyen qua NAPAS. Amount = [" + req.getAmount() + "]");
                                        break;
                                    }
                                    checkBalace = req.getAvailableBalance().subtract(fee).subtract(req.getAmount());
                                } else {
                                    checkBalace = req.getAvailableBalance().subtract(req.getAmount());
                                }
                                if (checkBalace.compareTo(BigDecimal.valueOf(0)) == -1) {
                                    logger.warn("So tien ko du de thuc hien giao dich. Available = [" + req.getAvailableBalance() + "], Amount = [" + req.getAmount() + "], Fee = [" + fee + "]");
                                    break;
                                }
                            }
                            result = true;
                            break;
                        default:
                            logger.warn("Account class does not approve for transfer. Account class = [" + req.getSubAccountClass() + "]");
                            break;
                    }
                } else {
                    result = true;
                }
            } else {
                logger.warn("Account == null || ccy input is not equal with account ccy");
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    private boolean isValidData(TransferMoney247DetailReq req) throws RemoteException, Exception {
        boolean result = false;
        // kiem tra gia tri tung field xem co valid ko
        if (req.isValid()) {
            // kiem tra xem so the dich co phai cua SCB hay ko
            if (!toNumberIsSCBCard(req)) {
                /* Theo logic cu thi setup account nam tai day */
 /* Theo logic thi setup Cif va Account Class nam tai day */
                result = true;
            }
        }

        return result;
    }

    public static String cheThe(String input, String hiden) {

        String output = input;
        try {
            String list = "3;4;5;6";
            String[] array = list.split(";");
            boolean flag = true;
            while (flag) {
                flag = false;
                for (int i = 0; i < array.length; i++) {
                    int index = output.indexOf(array[i]);
                    if (index >= 0) {
                        if (input.length() >= index + 16) {
                            String sub = output.substring(index, index + 16);
                            if (checkNumber(sub)) {
                                flag = true;
                                String newsub = sub.substring(0, 6) + hiden + sub.substring(12, 16);
                                output = output.replace(sub, newsub);
                            }
                        }
                    }

                }
            }
        } catch (Exception ex) {

        }
        return output;
    }
    
    //che the chieu dai 15 - 19 so
    public static String cheThe_New(String input, String hiden) {

        String output = input;
        try {
            String list = "3;4;5;6";
            String[] array = list.split(";");
            boolean flag = true;
//            int hiddenLength = input.length() -10; //tru chieu dai cua 6 so dau + 4 so cuoi the
//            if(hiden.length() == 10){ //case truong hop che so the 
//                hiden =hiden.substring(0, hiddenLength);
//            }
//            hiden =hiden.substring(0, hiddenLength);
            //truong hop che log/description thi ko cat chuoi hiden
            while (flag) {
                flag = false;
                for (int i = 0; i < array.length; i++) {
                    int index = output.indexOf(array[i]);
                    if (index >= 0) {
                        if (input.length() >= index + input.length()) {
                            String sub = output.substring(index, index + input.length());
                            if (checkNumber(sub)) {
                                flag = true;
                                String newsub = sub.substring(0, 6) + hiden + sub.substring(input.length()-4, input.length());
                                output = output.replace(sub, newsub);
                            }
                        }
                    }

                }
            }
        } catch (Exception ex) {

        }
        return output;
    }

    private static boolean checkNumber(String input) {
        String regex = "[0-9]+";
        return input.matches(regex);
    }

    private static boolean checkSpecialDigit(String input) {
        String[] list = "&".split("#");
        for (int i = 0; i < list.length; i++) {
            int index = input.indexOf(list[i]);
            if (index > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAccountClass(String AccountClass) {
        String listAccountClass = Configuration.getProperty("247.accountclass");
        String[] arrClass = listAccountClass.split("#");
        for (int i = 0; i < arrClass.length; i++) {
            int lenghtClass = arrClass[i].length();
            if (AccountClass.substring(0, lenghtClass).equals(arrClass[i])) {
                return true;
            }
        }
        return false;
    }

    public String transfeFromSCBwithObj(TransferMoney247DetailReq req) throws RemoteException, Exception {
        String FromNumber = req.getFromNumber();
        String TypeFromNumber = req.getTypeFromNumber();
        String FullName = req.getFullName();
        String ToNumber = req.getToNumber();
        String TypeToNumber = req.getTypeToNumber();
        String BenID = req.getBenId();
        BigDecimal Amount = req.getAmount();
        String CCY = req.getCcy();
        String channel = "CH";
        String Desc = req.getDescription() + "#" + req.getBankTransId();
        String TermID = req.getTermId();
        String CardAcceptor = "SCB                      HCM         VNM";
        String TypeFunction = req.getTypeFunction();
        ProductTCH = req.getProduct();
        return transfeFromSCB(FromNumber, TypeFromNumber, FullName, ToNumber, TypeToNumber, BenID, Amount, CCY, channel, Desc, TermID, CardAcceptor, TypeFunction);
    }

    public TransferMoney247DetailRes transferFromSCBwithObj(TransferMoney247DetailReq req) throws RemoteException, Exception {
        TransferMoney247DetailRes response = new TransferMoney247DetailRes();

        String result = transfeFromSCBwithObj(req);
        /* Log sau khi call boi vi trong ham trong da bat try catch nen se ko bi quang exception */
        logger.info("transferFromSCBwithObj --> request = [" + req.toString() + "], result = [" + result + "]");
        if (result != null) {
            String[] items = result.split("#");
            response.setResponseCode(items[0]);

            if (items.length > 1) {
                switch (response.getResponseCode()) {
                    /* Chi Ly confirm so refCore chi xuat hien tai 2 ma 00 va 24 */
                    case "00":
                    case "24":
                    case "18":
                    case "16":
                        /* Giao dich thanh cong va chuoi duoc cat phai > 1 */
                        response.setDestinationName(items[1]);
                        if (items.length > 2) {
                            response.setRefCore(items[2]);
                        }
                        logger.info("Thuc hien goi ham transferFromSCBwithObj thanh cong --> typeFunction = [" + req.getTypeFunction() + "], fromNumber = [" + req.getFromNumber() + "], toNumber = [" + req.getToNumber() + "], responseCode = [" + response.getResponseCode() + "], DestName = [" + response.getDestinationName() + "], RefCore = [" + response.getRefCore() + "]");
                        break;
                    default:
                        logger.warn("Thuc hien goi ham transfeFromSCBwithObj ko thanh cong. Result =[" + result + "],  ResponseCode = [" + response.getResponseCode() + "], item size = [" + items.length + "]");
                        break;
                }
            } else {
                logger.warn("Thuc hien goi ham transfeFromSCBwithObj ko thanh cong. Result =[" + result + "],  ResponseCode = [" + response.getResponseCode() + "], item size = [" + items.length + "]");
            }
        } else {
            logger.warn("Thuc hien goi ham transfeFromSCBwithObj co response = null");
            response.setResponseCode("99");
        }
        return response;
    }

    public TransferMoney247DetailRes transferFromSCBwithObjV2(TransferMoney247DetailReq req) throws RemoteException, Exception {
        TransferMoney247DetailRes response = new TransferMoney247DetailRes();

        logger.info("transferFromSCBwithObjV2 ");
        String result = transfeFromSCBwithObj(req);
        /* Log sau khi call boi vi trong ham trong da bat try catch nen se ko bi quang exception */
        logger.info("transferFromSCBwithObj --> request = [" + req.toString() + "], result = [" + result + "]");
        if (result != null) {
            String[] items = result.split("#");
            response.setResponseCode(items[0]);

            if (items.length > 1) {
                switch (response.getResponseCode()) {
                    /* Chi Ly confirm so refCore chi xuat hien tai 2 ma 00 va 24 */
                    case "00":
                    case "24":
                    case "18":
                    case "16":
                        /* Giao dich thanh cong va chuoi duoc cat phai > 1 */
                        response.setDestinationName(items[1]);
                        if (items.length > 2) {
                            response.setRefCore(items[2]);
                        }
                        logger.info("Thuc hien goi ham transferFromSCBwithObj thanh cong --> typeFunction = [" + req.getTypeFunction() + "], fromNumber = [" + req.getFromNumber() + "], toNumber = [" + req.getToNumber() + "], responseCode = [" + response.getResponseCode() + "], DestName = [" + response.getDestinationName() + "], RefCore = [" + response.getRefCore() + "]");
                        break;
                    case "08":    
                    case "09":
                        response.setResponseCode(ResponseCodeEnum.INVALIDRECEIVINGINFO.getText());
                        logger.warn("Thuc hien goi ham transfeFromSCBwithObj ko thanh cong. Result =[" + result + "],  ResponseCode = [" + response.getResponseCode() + "], item size = [" + items.length + "]");
                        break;
                    default:
                        logger.warn("Thuc hien goi ham transfeFromSCBwithObj ko thanh cong. Result =[" + result + "],  ResponseCode = [" + response.getResponseCode() + "], item size = [" + items.length + "]");
                        break;
                }
            } else {
                logger.warn("Thuc hien goi ham transfeFromSCBwithObj ko thanh cong. Result =[" + result + "],  ResponseCode = [" + response.getResponseCode() + "], item size = [" + items.length + "]");
            }
        } else {
            logger.warn("Thuc hien goi ham transfeFromSCBwithObj co response = null");
            response.setResponseCode("99");
        }
        return response;
    }

    public String Transfer247(String xmlrequest) throws Exception {
        Transfer247Response response = new Transfer247Response();
        try {
            Transfer247Request request = (Transfer247Request) Xml.unMarshaller(Transfer247Request.class, xmlrequest);
            //check data
            String CardAcceptor = "SCB                      HCM         VNM";
            String TermID = getTermid(request.getChannel());
            //Check input    
            String resultQue = transfeFromSCBv2(request.getFromNumber(), request.getTypeFromNumber(), request.getFullName(), request.getToNumber(), request.getTypeToNumber(), request.getBenID(), request.getAmount(), request.getCCY(), request.getChannel(), request.getDesc(), TermID, CardAcceptor, "QUE");
            String[] listQUE = resultQue.split("#");
            String ResponseCode = listQUE[0];
            response.setResponseCode(ResponseCode);
            response.setTransID(request.getTransID());
            if (ResponseCode.equals("00")) {
                String Benname = listQUE[1];
                response.setBenname(Benname);
                if (request.getTypeFunction().equals("TRN")) {
                    String Desc = request.getDesc();
                    if (request.getChannel().contains("COUNTER") || request.getChannel().contains("CRM")) {
                        Desc = Desc + "#0#0#" + request.getUserMaker() + "#" + request.getUserChecker() + "#" + request.getFullName() + "#" + request.getAddress() + "#" + request.getIdCard() + "#" + request.getIdCardDob() + "#" + request.getIDCardAddress() + "#" + request.getBranch() + "#" + request.getTransID();
                    }
                    String refult = transfeFromSCBv2(request.getFromNumber(), request.getTypeFromNumber(), request.getFullName(), request.getToNumber(), request.getTypeToNumber(), request.getBenID(), request.getAmount(), request.getCCY(), request.getChannel(), Desc, TermID, CardAcceptor, "TRN");
                    String[] listTRN = refult.split("#");
                    ResponseCode = listTRN[0];
                    response.setResponseCode(ResponseCode);
                    if (ResponseCode.equals("00")) {
                        String refcore = listTRN[2];
                        response.setRefcore(refcore);
                    }
                }
            }

        } catch (Exception ex) {
            logger.error("Transfer247:" + ex);
            response.setResponseCode("99");
        }
        return Xml.Marshaller(response);
    }

    private String getTermid(String channel) {
        switch (channel) {
            case "MB":
                return "11111111";
            case "COUNTER":
                return "22222222";
            case "EBANK":
                return "33333333";
            case "ATM":
                return "44444444";
            default:
                return "11111111";
        }
    }

}
