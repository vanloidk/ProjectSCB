/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import com.google.gson.Gson;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateReq;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateRes;
import scb.com.vn.common.model.transfer.TransactionDetail;
import scb.com.vn.common.model.transfer.SenderInfo;
import scb.com.vn.common.model.transfer.napas.TransferMoney247DetailReq;
import scb.com.vn.common.model.transfer.napas.TransferMoney247DetailRes;
import scb.com.vn.common.model.transfer.napas.TransferMoney247Req;
import scb.com.vn.common.model.transfer.napas.TransferMoney247Res;
import scb.com.vn.enumUtils.ResponseCodeEnum;
import scb.com.vn.model.FundTransferRq;
import scb.com.vn.payment.ControllerUtil;
import vn.com.scb.bian.AccountInfoType;
import vn.com.scb.bian.BenificialInfoType;
import vn.com.scb.bian.BranchInfoType;
import vn.com.scb.bian.CoreBankAccountType;
import vn.com.scb.bian.ExchangeRateInfoType;
import vn.com.scb.bian.ExecutePaymentTransactionExternal_in_Type;
import vn.com.scb.bian.ExecutePaymentTransactionInternal_in_Type;
import vn.com.scb.bian.FundTransferInfoType;
import vn.com.scb.bian.IDInfoType;
import vn.com.scb.bian.SenderInfoType;
import vn.com.scb.bian.TransactionInfoType;

/**
 *
 * @author minhndb
 */
public class SiUtils {

    private static final Logger logger = Logger.getLogger(SiUtils.class);

    private static final String CHANNEL = "MOBILE";
    private static final String MB = "MB";
    private static final String TCHACCOUNT = Configuration.getProperty("fcubs.tch.listAccount");
    private static final String USERIDMOBILE = Configuration.getProperty("fcubs.userid.mobile");

    private static final String MACISDIFFERENCE = "SCB = [%s] != %s = [%s]. MAC IS DIFFERENCE.";

    /**
     *
     * @param partner
     * @param value
     * @param macPartner
     * @return
     */
    public static boolean canAccess(String partner, String value, String macPartner) {
        try {
            String[] items = Helper.getDBI().ONL_GetMACkeys(partner);
            if (items.length > 0) {
                String md5Key = items[0];
                String macScb = ControllerUtil.EncriptMD5(md5Key + value);
                
                logger.warn("macScb123456 = [" + macScb + "]");
                
                if (macScb.equalsIgnoreCase(macPartner)) {
                    return true;
                } else {
                    String info = String.format(MACISDIFFERENCE, macScb, partner, macPartner);
                    logger.warn(info);
                }
            } else {
                logger.warn("Does not found the md5 key with partner = [" + partner + "]");
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return false;
    }

    /**
     *
     * @param partner
     * @return
     */
    public static String getMd5Key(String partner) {
        try {
            String[] items = Helper.getDBI().ONL_GetMACkeys(partner);
            if (items.length > 0) {
                return items[0];
            } else {
                logger.warn("Does not found the md5 key with partner = [" + partner + "]");
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    /**
     *
     * @param partner
     * @return
     */
    public static String getAccountPartner(String partner) {
        try {
            String[] items = Helper.getDBI().ONL_GetMACkeys(partner);
            if (items.length > 0) {
                /* 
                * 0 ~ PRIVATEKEY
                * 1 ~ PUBLICKEY
                * 2 ~ ISVERIFIEDFORPAYBYPROFILEID
                * 3 ~ LIMITAMOUNTFORVERIFIED
                * 4 ~ ACCOUNTNO
                 */
                return items[4];
            } else {
                logger.warn("Does not found the md5 key with partner = [" + partner + "]");
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    /**
     *
     * @param partner
     * @param value
     * @return
     */
    public static String signData(String partner, String value) {
        try {
            String[] items = Helper.getDBI().ONL_GetMACkeys(partner);
            if (items.length > 0) {
                String md5Key = items[0];
                return ControllerUtil.EncriptMD5(value + md5Key);
            } else {
                logger.warn("Does not found the md5 key with partner = [" + partner + "]");
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    /**
     *
     * @param transDetail
     * @return
     */
    public static ExchangeRateRes getExchangeRate(TransactionDetail transDetail) {
        try {
            if (transDetail.isNotVnd()) {
                // lay branch cua tai khoan doi tac
                String branchCust = CommonUtils.getBranchAccount(transDetail.getPartnerAccount());

                // tao data de chuyen doi tien
                ExchangeRateReq exchangeRateReq = new ExchangeRateReq();

                exchangeRateReq.setProduct(transDetail.getProduct());
                exchangeRateReq.setBranch(branchCust);
                exchangeRateReq.setCcy(transDetail.getCcy());
                exchangeRateReq.setMoney(transDetail.getAmountBigDecimalType());
                // call qua database core de chuyen doi menh gia tien
                return Helper.getDBI().getExchangeRate(exchangeRateReq);
            } else {
                // tao data mapping 1-1 ty gia VND de tra ve
                ExchangeRateRes exchangeRateRes = new ExchangeRateRes();
                exchangeRateRes.setProduct(transDetail.getProduct());
                exchangeRateRes.setMoney(transDetail.getAmountBigDecimalType());
                exchangeRateRes.setRate(BigDecimal.ONE);
                exchangeRateRes.setCcy(transDetail.getCcy());
                exchangeRateRes.setMoneyExchange(transDetail.getAmountBigDecimalType());
                exchangeRateRes.setCcyExchange(transDetail.getCcy());
                exchangeRateRes.setResultCode("00");
                return exchangeRateRes;
            }
        } catch (Exception e) {
            logger.error(e);
            return new ExchangeRateRes();
        }
    }

    /**
     *
     * @param cardNumber
     * @return
     */
    public static String getAccountFromCardNumber(String cardNumber) {
        try {
            String[] cardInfo = Helper.getDBI().getCardStatus(cardNumber);
            if (cardInfo.length >= 2 && "00".equals(cardInfo[0])) {
                return cardInfo[2];
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    /**
     *
     * @param transDetail
     * @return
     */
    public static List<String> insertDetailToSi(TransactionDetail transDetail) {
        List<String> result = new ArrayList<>();
        try {
            Object[] items = Helper.getDBI().insertDetailToSi(transDetail);
            for (Object item : items) {
                result.add(item.toString());
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    /**
     *
     * @param senderInfo
     * @param scbDetailId
     * @return
    * Doi tac dung: WU
    * dang test tren 73.20
     */
    public static String insertToSiSenderDetail(SenderInfo senderInfo, String scbDetailId) {
        String result = "00";
        try {
            int resultInsert = Helper.getDBI().insertToSiSenderDetail(senderInfo, Long.valueOf(scbDetailId));
            if (resultInsert == 0) {
                /*insert ko thanh cong*/
                result = "01";
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    /*
    * Doi tac dung: WU
    * dang test tren 73.20
    */
    public static String[] insertErrorKycToSiTransDetail(TransactionDetail transDetail) {
        try {
            String idMaster = transDetail.getScbDetailId();
            /*Check txnDetailid da ton tai chua*/
            String result[] = Helper.getDBI().insertErrorKycSiTransDetail(idMaster, transDetail.getTxnDetailId(), transDetail.getPartnerAccount(),
                    (transDetail.getCustomerName() != null) ? transDetail.getCustomerName() : "",
                    transDetail.getCustomerAccount(),
                    transDetail.getBankCode(), transDetail.getBranchCode(), transDetail.getAmountBigDecimalType(),
                    transDetail.getCcy(), transDetail.getDescription(), transDetail.getResponseCode(), transDetail.getTypeTransfer(), transDetail.getTypeCustAccount(),
                    /*Thong tin ExchangeRes*/
                    (transDetail.getExchangeRateRes().getRate() != null) ? transDetail.getExchangeRateRes().getRate() : new BigDecimal(""),
                    (transDetail.getExchangeRateRes().getMoneyExchange() != null) ? transDetail.getExchangeRateRes().getMoneyExchange() : new BigDecimal(""),
                    (transDetail.getExchangeRateRes().getCcyExchange() != null) ? transDetail.getExchangeRateRes().getCcyExchange() : "",
                    /*Thong tin receivingInfo*/
                    (transDetail.getReceivingInfo().getPersonId()), (transDetail.getReceivingInfo().getFirstName() != null) ? transDetail.getReceivingInfo().getFirstName() : "",
                    (transDetail.getReceivingInfo().getLastName() != null) ? transDetail.getReceivingInfo().getLastName() : "",
                    (transDetail.getReceivingInfo().getPassNo() != null) ? transDetail.getReceivingInfo().getPassNo() : "",
                    (transDetail.getReceivingInfo().getBirthDate() != null) ? transDetail.getReceivingInfo().getBirthDate() : "",
                    (transDetail.getReceivingInfo().getAddress() != null) ? transDetail.getReceivingInfo().getAddress() : "",
                    (transDetail.getReceivingInfo().getNationality() != null) ? transDetail.getReceivingInfo().getNationality() : "",
                    (transDetail.getReceivingInfo().getCustType() != null) ? transDetail.getReceivingInfo().getCustType() : "");
            return result;
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    /*dang test tren 73.20
    * doi tac: WU
     */
    public static String insertErrorKycToSiTransDetail(TransferMoney247Req req, TransferMoney247Res response, TransferMoney247DetailReq detailReq) {
        String bankTransId = "0";
        try {
            // insert thong tin vo table goc
            String[] resultTransfer = Helper.getDBI().SI_INSERTSITRANSFROMSI(req.getProviderId(),
                    req.getTransId(), req.getDateTime());
            String status = resultTransfer[1];
            // kiem tra xem co insert thong tin vo table goc duoc ko
            if ("00".equals(status)) {
                // lay thong tin lien ket (id) toi table goc moi duoc insert
                String idMaster = resultTransfer[0];
                // gan transaction detail id = transaction id = 1 ma cho gon
                String transDetailId = req.getTransId();
                // insert thong tin vo bang chi tiet
                String[] rs = Helper.getDBI().insertErrorKycSiTransDetail(idMaster, transDetailId, detailReq.getFromNumber(), "", detailReq.getToNumber(), detailReq.getBenId(),
                        "", detailReq.getAmount(), detailReq.getCcy(),
                        detailReq.getDescription(), response.getResponseCode(), "03", "CARD".equals(detailReq.getTypeToNumber()) ? "01" : "02",
                        /*Thong tin exchangeRes*/
                        (detailReq.getExchangeRateRes().getRate() != null) ? detailReq.getExchangeRateRes().getRate() : new BigDecimal(""),
                        (detailReq.getExchangeRateRes().getMoneyExchange() != null) ? detailReq.getExchangeRateRes().getMoneyExchange() : new BigDecimal(""),
                        (detailReq.getExchangeRateRes().getCcyExchange() != null) ? detailReq.getExchangeRateRes().getCcyExchange() : "",
                        /*Thong tin receiving info*/
                        (req.getReceivingInfo().getPersonId() != null) ? req.getReceivingInfo().getPersonId() : "",
                        (req.getReceivingInfo().getFirstName() != null) ? req.getReceivingInfo().getFirstName() : "",
                        (req.getReceivingInfo().getLastName() != null) ? req.getReceivingInfo().getLastName() : "",
                        (req.getReceivingInfo().getPassNo() != null) ? req.getReceivingInfo().getPassNo() : "",
                        (req.getReceivingInfo().getBirthDate() != null) ? req.getReceivingInfo().getBirthDate() : "",
                        (req.getReceivingInfo().getAddress() != null) ? req.getReceivingInfo().getAddress() : "",
                        (req.getReceivingInfo().getNationality() != null) ? req.getReceivingInfo().getNationality() : "",
                        (req.getReceivingInfo().getCustType() != null) ? req.getReceivingInfo().getCustType() : "");
                bankTransId = rs[1];
            } else {
                logger.warn("insert du lieu xuong table master that bai. TransID = [" + req.getTransId() + "], Status = [" + status + "]");
                response.setResponseCode(status);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return bankTransId;
    }

    /**
     *
     * @param req
     * @param response
     * @param transdate
     * @param auditNumber
     * @param refNoF37
     * @param acquiringCode
     * @return
     * @throws Exception
     */
    public static boolean insertNapasLog(TransferMoney247DetailReq req,
             TransferMoney247DetailRes response, String transdate, String auditNumber,
             String refNoF37, String acquiringCode) throws Exception {
        boolean result = false;
        try {
            Helper.getDBI().InsertSMLLOG("FROM_SCB", req.getFromNumber(), req.getProcessingCode(),
                     req.getAmount(), transdate, auditNumber, req.getMerchantType(),
                     acquiringCode, response.getAuthorizationCode(), response.getNapasResponseCode(),
                     req.getTermId(), req.getCardAcceptor(), req.getToNumber(), req.getDescription(),
                     req.getBenId(), req.getTypeFunction(), response.getResponseCode(),
                     response.getRefCore(), req.getCif(), response.getRefCoreRefund(), refNoF37,
                     response.getSettDateF15());
            result = true;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
        return result;
    }

    /**
     *
     * @param fundTransferRq
     * @return
     */
    public static ExecutePaymentTransactionInternal_in_Type getReqToTransferInternalAcc(FundTransferRq fundTransferRq) {
        ExecutePaymentTransactionInternal_in_Type internal_input = new ExecutePaymentTransactionInternal_in_Type();

        TransactionInfoType transactionInfo = new TransactionInfoType();
        transactionInfo.setClientCode(CHANNEL);
        transactionInfo.setCRefNum(CHANNEL.substring(0, 2).concat(String.valueOf(System.currentTimeMillis())));
        String brnAccountFrom = CommonUtils.getBranchAccount(fundTransferRq.getFromAccount());
        String brnAccountTo = CommonUtils.getBranchAccount(fundTransferRq.getToAccount());
        BranchInfoType branchinfo = new BranchInfoType();
        branchinfo.setBranchCode(brnAccountFrom);
        transactionInfo.setBranchInfo(branchinfo);
        internal_input.setTransactionInfo(transactionInfo);
        //tk nguon
        AccountInfoType sourceAccount = new AccountInfoType();
        sourceAccount.setAccountOpenBrandCode(brnAccountFrom);
        sourceAccount.setAccountNum(fundTransferRq.getFromAccount());
        sourceAccount.setAccountCurrency(fundTransferRq.getResExchange().getCcy());
        internal_input.setSourceAccount(sourceAccount);
        //tk dich     
        AccountInfoType toAccount = new AccountInfoType();
        toAccount.setAccountOpenBrandCode(brnAccountTo);
        toAccount.setAccountNum(fundTransferRq.getToAccount());
        toAccount.setAccountCurrency(fundTransferRq.getResExchange().getCcyExchange());
        internal_input.setDestinationAccount(toAccount);
        //thong tin chuyen
        FundTransferInfoType fundTransferInfo = new FundTransferInfoType();
        fundTransferInfo.setFundTransferProductCode(fundTransferRq.getProductCode());
        fundTransferInfo.setFundTransferAmount(new Double(fundTransferRq.getAmount()));
        fundTransferInfo.setFundTransferNarative(fundTransferRq.getRemark());
        // ty gia
        ExchangeRateInfoType exchangeRateInfo = new ExchangeRateInfoType(fundTransferRq.getResExchange().getRate().toString());
        fundTransferInfo.setExchangeRateInfo(exchangeRateInfo);
        internal_input.setFundTransferInfo(fundTransferInfo);
        //user lam        
        CoreBankAccountType coreBankAccount = new CoreBankAccountType();
        coreBankAccount.setMakerAccount(fundTransferRq.getUserId());
        coreBankAccount.setSourceHeader("ODC1");
        internal_input.setCoreBankAccount(coreBankAccount);
        return internal_input;
    }

    /**
     *
     * @param fundTransferRq
     * @return
     */
    public static ExecutePaymentTransactionExternal_in_Type getReqToTransferExternalAcc(FundTransferRq fundTransferRq) {
        try {
            ExecutePaymentTransactionExternal_in_Type external_input = new ExecutePaymentTransactionExternal_in_Type();
            TransactionInfoType transactionType = new TransactionInfoType();
            String xref = MB + String.valueOf(System.currentTimeMillis());

            transactionType.setClientCode(CHANNEL);
            transactionType.setCRefNum(xref);
            BranchInfoType branchinfo = new BranchInfoType();
            branchinfo.setBranchCode(CommonUtils.getBranchAccount(fundTransferRq.getFromAccount()));
            transactionType.setBranchInfo(branchinfo);
            external_input.setTransactionInfo(transactionType);

            FundTransferInfoType fundTransferInfo = new FundTransferInfoType();
            fundTransferInfo.setFundTransferAmount(new Double(fundTransferRq.getAmount()));
            fundTransferInfo.setFundTransferNarative(fundTransferRq.getRemark());
            fundTransferInfo.setFundTransferProductCode(fundTransferRq.getProductCode());
            external_input.setFundTransferInfo(fundTransferInfo);

            AccountInfoType sourceAccount = new AccountInfoType();
            sourceAccount.setAccountOpenBrandCode(CommonUtils.getBranchAccount(fundTransferRq.getFromAccount()));
            sourceAccount.setAccountNum(fundTransferRq.getFromAccount());
            sourceAccount.setAccountCurrency(fundTransferRq.getResExchange().getCcy());
            external_input.setSourceAccount(sourceAccount);

            AccountInfoType toAccount = new AccountInfoType();
            toAccount.setAccountOpenBrandCode("000");
            toAccount.setAccountNum(fundTransferRq.getGlAccount());
            toAccount.setAccountCurrency(fundTransferRq.getResExchange().getCcyExchange());
            external_input.setDestinationAccount(toAccount);

            BenificialInfoType benificialInfo = new BenificialInfoType();
            benificialInfo.setBenifitAccountNum(fundTransferRq.getToAccount());
            benificialInfo.setBenifitCustomerName(fundTransferRq.getBeneName());

            /* Comment do thay trong FCUBS da bi comment 
            List bankCity = Helper.getDBI().getBankCity(fundTransferRq.getCityCode());
            if (bankCity != null && !bankCity.isEmpty()) {
                HashMap hm_city = (HashMap) bankCity.get(0);
                benificialInfo.setBenefitCityCode(hm_city.get("nam_city_full").toString());
            }
             */
            benificialInfo.setBenefitBranchName(fundTransferRq.getBranchName());
            if (fundTransferRq.getBeneTel() != null) {
                benificialInfo.setBenifitCustomerTEL(fundTransferRq.getBeneTel());
            }
            //thong tin ngan hang thu huong
            List benebank = Helper.getDBI().getBeneBank(fundTransferRq.getBankCode());
            if (benebank != null && !benebank.isEmpty()) {
                HashMap hm_bene = (HashMap) benebank.get(0);
                benificialInfo.setBenefitBankCode(fundTransferRq.getBankCode());
                benificialInfo.setBenefitBankName(hm_bene.get("bankname").toString());
            }
            external_input.setBenificialInfo(benificialInfo);

            SenderInfoType senderInfo = new SenderInfoType();
            senderInfo.setSenderAccount(fundTransferRq.getFromAccount());
            senderInfo.setSenderName(fundTransferRq.getUserName());
            senderInfo.setSenderCIFNum(fundTransferRq.getCifNo());
            external_input.setSenderInfo(senderInfo);

            CoreBankAccountType coreBankAccount = new CoreBankAccountType();
            coreBankAccount.setMakerAccount(fundTransferRq.getUserId());
            coreBankAccount.setSourceHeader("FCAT");
            external_input.setCoreBankAccount(coreBankAccount);

            ExchangeRateInfoType exchangeRateInfo = new ExchangeRateInfoType(fundTransferRq.getResExchange().getRate().toString());
            fundTransferInfo.setExchangeRateInfo(exchangeRateInfo);
            external_input.setFundTransferInfo(fundTransferInfo);
            return external_input;
        } catch (Exception e) {
            logger.error(e);
        }
        logger.warn("Cannot build request ExecutePaymentTransactionExternal_in_Type to send iib to cut money from account " + fundTransferRq.getFromAccount() + " to account " + fundTransferRq.getToAccount());
        return null;
    }

    /**
     *
     * @param Account
     * @return
     */
    public static boolean isAccountTCH(String Account) {
        String[] listAccount = TCHACCOUNT.split(";");
        for (String item : listAccount) {
            if (item.equals(Account)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param oldPartern
     * @param newPartern
     * @param dateStr
     * @return
     */
    public static String changeFormatDate(String oldPartern, String newPartern, String dateStr) {
        try {
            DateFormat oldP = new SimpleDateFormat(oldPartern);
            DateFormat newP = new SimpleDateFormat(newPartern);
            Date date = oldP.parse(dateStr);
            return newP.format(date);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    /**
     *
     * @param fundTransferRq
     * @return
     */
    public static ExecutePaymentTransactionExternal_in_Type getReqToTransferExternalId(FundTransferRq fundTransferRq) {
        try {
            String date = fundTransferRq.getIdOpenDate("dd-MM-yyyy");
            if (date != null) {
                ExecutePaymentTransactionExternal_in_Type external_input = new ExecutePaymentTransactionExternal_in_Type();
                TransactionInfoType transactionType = new TransactionInfoType();
                String xref = MB + String.valueOf(System.currentTimeMillis());

                transactionType.setClientCode(CHANNEL);
                transactionType.setCRefNum(xref);
                BranchInfoType branchinfo = new BranchInfoType();
                branchinfo.setBranchCode(CommonUtils.getBranchAccount(fundTransferRq.getFromAccount()));
                transactionType.setBranchInfo(branchinfo);

                external_input.setTransactionInfo(transactionType);

                FundTransferInfoType fundTransferInfo = new FundTransferInfoType();
                fundTransferInfo.setFundTransferAmount(Double.valueOf(fundTransferRq.getAmount()));
              //  fundTransferInfo.setFundTransferAmountStr(fundTransferRq.getAmountConvert());
                fundTransferInfo.setFundTransferTransactionType("ID");
                fundTransferInfo.setFundTransferProductCode(fundTransferRq.getProductCode());

                AccountInfoType sourceAccount = new AccountInfoType();
                sourceAccount.setAccountOpenBrandCode(CommonUtils.getBranchAccount(fundTransferRq.getFromAccount()));
                sourceAccount.setAccountNum(fundTransferRq.getFromAccount());
                sourceAccount.setAccountCurrency(fundTransferRq.getResExchange().getCcy());
                external_input.setSourceAccount(sourceAccount);

                AccountInfoType toAccount = new AccountInfoType();
                toAccount.setAccountOpenBrandCode(fundTransferRq.getBranchCode());
                toAccount.setAccountNum(fundTransferRq.getGlAccount());
                toAccount.setAccountCurrency(fundTransferRq.getResExchange().getCcyExchange());
                external_input.setDestinationAccount(toAccount);

                BenificialInfoType benificialInfo = new BenificialInfoType();
                benificialInfo.setBenifitCustomerName(fundTransferRq.getBeneName());
                benificialInfo.setBenefitCityCode(fundTransferRq.getCityCode());
                benificialInfo.setBenefitBranchName(fundTransferRq.getBranchName());

                if (fundTransferRq.isValidIDOpenDate()) {
                    IDInfoType infoType = new IDInfoType();
                    infoType.setIDIssuedDate(date);
                    infoType.setIDNum(fundTransferRq.getToAccount());
                    fundTransferRq.setIDOpenDate(infoType.getIDIssuedDate());
                    if (fundTransferRq.isValidIDCityCode()) {
                        infoType.setIDIssuedLocation(fundTransferRq.getIDCityCode());
                    }
                    benificialInfo.setIDInfo(infoType);
                }
                external_input.setBenificialInfo(benificialInfo);
                fundTransferInfo.setFundTransferNarative(fundTransferRq.buildNarativeForTransferInternalId());
                external_input.setFundTransferInfo(fundTransferInfo);

                SenderInfoType senderInfo = new SenderInfoType();
                senderInfo.setSenderAccount(fundTransferRq.getFromAccount());

                senderInfo.setSenderName(fundTransferRq.getUserName());
                senderInfo.setSenderCIFNum(fundTransferRq.getCifNo());
                external_input.setSenderInfo(senderInfo);

                CoreBankAccountType coreBankAccount = new CoreBankAccountType();
                coreBankAccount.setMakerAccount(fundTransferRq.getUserId());
                coreBankAccount.setSourceHeader("FCAT");
                external_input.setCoreBankAccount(coreBankAccount);

                ExchangeRateInfoType exchangeRateInfo = new ExchangeRateInfoType(fundTransferRq.getResExchange().getRate().toString());
                fundTransferInfo.setExchangeRateInfo(exchangeRateInfo);
                external_input.setFundTransferInfo(fundTransferInfo);

                return external_input;
            }
            else
            {
                logger.warn("Du lieu ngay cap CMND/CCCD/PASSPORT ko dung. Ngay cap = [" + fundTransferRq.getIDOpenDate() + "]");
            }
        } catch (Exception e) {
            logger.error(e);
        }
        Gson gson = new Gson();
        String fundTransferRqStr = gson.toJson(fundTransferRq);
        logger.warn("Cannot build request ExecutePaymentTransactionExternal_in_Type to send iib to cut money from account " + fundTransferRq.getFromAccount() + " to account " + fundTransferRq.getToAccount() + ". fundTransferRq = [" + fundTransferRqStr + "]");
        return null;
    }
    
    /*Test case cho WU*/
    public static String insertTestCaseForWU(TransferMoney247Req req, TransferMoney247Res response, TransferMoney247DetailReq detailReq, String statusTestcase){
        String bankTransId = "0";        
        try {
             // insert thong tin vo table goc
            String[] resultTransfer = Helper.getDBI().SI_INSERTSITRANSFROMSI(req.getProviderId(),
                    req.getTransId(), req.getDateTime());
            String status = resultTransfer[1];
            // kiem tra xem co insert thong tin vo table goc duoc ko
            if ("00".equals(status)) {
                // lay thong tin lien ket (id) toi table goc moi duoc insert
                String idMaster = resultTransfer[0];
                // gan transaction detail id = transaction id = 1 ma cho gon
                String transDetailId = req.getTransId();                
                String[] insertTestCase = Helper.getDBI().insertTestCaseToTransDetail(idMaster, transDetailId, detailReq.getFromNumber(), "", detailReq.getToNumber(), detailReq.getBenId(), 
                        detailReq.getAmount(), detailReq.getCcy(), detailReq.getDescription(), "03", statusTestcase);
                bankTransId = insertTestCase[1];           
            }  else {
                logger.warn("insert du lieu xuong table master that bai. TransID = [" + req.getTransId() + "], Status = [" + status + "]");
                response.setResponseCode(status);
            }
        } catch (Exception e){
            logger.error(e);
        }
        return bankTransId;
    }    
}
