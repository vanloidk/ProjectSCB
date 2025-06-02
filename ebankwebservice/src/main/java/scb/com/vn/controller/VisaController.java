/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import com.visa.IVisaFactory;
import com.visa.VisaAPIFactory;
import com.visa.utils.MessageType;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.masterpass.MasterCardQrActionEnum;
import scb.com.vn.common.model.mvisa.Address;
import scb.com.vn.common.model.mvisa.CardAcceptor;
import scb.com.vn.common.model.mvisa.MVISAQRRQ;
import scb.com.vn.common.model.mvisa.PurchaseIdentifier;
import scb.com.vn.common.model.mvisa.RequestMessage;
import scb.com.vn.common.model.mvisa.ResponseMessage;
import scb.com.vn.constant.CommonConstant;
import scb.com.vn.message.VisaMessage.VisaCodeEnum;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.VisaConstant;

/**
 *
 * @author minhndb
 */
public class VisaController {
    private static final Logger LOGGER = Logger.getLogger(VisaController.class);
    
    /**
     *
     * @param req
     * @return
     */
    public VisaCodeEnum executePayment(MVISAQRRQ req) {
        VisaCodeEnum result;
        try {
            // call ham goi qua Visa de di thanh toan
            ResponseMessage response = callPayment(req);
            // cau hinh tham so tra ve sau khi goi qua Visa
            req.setVisaResponse(response);
            // kiem tra xem thanh toan qua visa co thanh cong hay ko?
            switch (req.getVisaResponse().getActionCode()) {
                case "00": // Approved and completed successfully
                case "10": // Partial approval
                case "11": // Approved (V.I.P)
                case "85": //No reason to decline a request for address verification, CVV2 verification, or a credit voucher or merchandise return
                    // thanh toan qua visa thanh cong
                    req.setStatus(CommonConstant.MASTERQR_STATUS_MASTER_SUCCESS);
                    result = VisaCodeEnum.VISA_OK;
                    break;
                case "01": // Refer to card issuer
                case "02": // Refer to card issuer, special condition
                case "03": // Invalid merchant
                case "12": // Invalid transaction
                case "13": // Invalid amount or currency conversion field overflow
                case "14": // Invalid account number (no such number)
                case "15": // No such issuer
                case "54": // Expired card or expiration date is missing
                case "55": // Incorrect PIN or PIN missing
                case "57": // Transaction not permitted to cardholder
                case "61": // Exceeds approval amount limit
                case "75": // Allowable number of PIN entry tries exceeded
                case "81": // Cryptographic error found in PIN
                case "86": // Cannot verify PIN; for example, no PVV
                case "92": // Financial institution or intermediate network facility cannot be found for routing (receiving institution ID is invalid)
                case "N3": // Cash service not available
                case "N4": // Cash request exceeds issuer or approved limit
                case "N7": // Decline for CVV2 failure
                case "N8": // Transaction amount exceeds preauthorized approval amount
                case "Q1": // Card Authentication failed
                    // thanh toan qua visa that bai
                    req.setStatus(CommonConstant.MASTERQR_STATUS_MASTER_FAILED);
                    result = VisaCodeEnum.VISA_FAILED;
                    break;

                case "04": // Pick up card (no fraud)
                case "05": // Do not honor
                case "06": // Error
                case "07": // Pick up card, special condition (fraud account)
                case "19": // Re-enter transaction
                case "21": // No action taken
                case "25": // Unable to locate record in file
                case "28": // File temporarily not available for update or inquiry
                case "39": // No credit account
                case "41": // Lost card, pick up (fraud account)
                case "43": // Stolen card, pick up (fraud account)
                case "51": // Not sufficient funds
                case "52": // No checking account
                case "53": // No savings account
                case "59": // Suspected fraud
                case "62": // Restricted card (card invalid in this region or country)
                case "63": // Security violation (source is not correct issuer)
                case "64": // Transaction does not fulfill AML requirement
                case "65": // Exceeds withdrawal frequency limit
                case "76": // Unsolicited reversal
                case "79": // Already reversed (by Switch)
                case "80": // No financial impact
                case "82": // Negative CAM, dCVV, iCVV, or CVV results
                case "89": // Ineligible to receive financial position information (GIV)
                case "91": // Issuer or switch inoperative and STIP not applicable or not available for this transaction; Time-out when no stand-in; POS Check Service: Destination unavailable; Credit Voucher and Merchandise Return Authorizations: V.I.P. sent the transaction to the issuer, but the issuer was unavailable.
                case "93": // Transaction cannot be completed - violation of law
                case "96": // System malfunction or certain field error conditions
                case "B2": // Surcharge amount not supported by debit network issuer.
                case "N0": // Force STIP
                case "N5": // Ineligible for resubmission
                case "R0": // Stop Payment Order
                default:
                    // cac ma loi can cho tra soat
                    req.setStatus(CommonConstant.MASTERQR_STATUS_MASTER_FAILED);
                    result = VisaCodeEnum.getFromName(req.getVisaResponse().getResponseCode());
                    break;
            }
            
            // cap nhat thong tin phan hoi cua Visa xuong database
            int resultDB = Helper.getDBI().VISAQR(req, MasterCardQrActionEnum.UPDATE_MASTER);
            // kiem tra xem cap nhat database co thanh cong hay ko
            if (resultDB != 1) {
                result = VisaCodeEnum.VISA_OK_DBI_FAILED;
            }
        } catch (Exception e) {
            LOGGER.error("Failed to call DBI = [" + e.getMessage() + "]");
            result = VisaCodeEnum.VISA_RETURN_DBI_FAILED;
        }
        return result;
    }
    
    private ResponseMessage callPayment(MVISAQRRQ req) {
        IVisaFactory factory = new VisaAPIFactory();
        ResponseMessage result;
        RequestMessage message;
        try {
            message = new RequestMessage();

            message.setAcquirerCountryCode(req.getAcquirerCountryCode());
            message.setAcquiringBin(VisaConstant.ACQUIRINGBIN);
            message.setAmount(req.getAmount());
            message.setBusinessApplicationId(VisaConstant.BUSINESSAPPID);

            CardAcceptor cardAcceptor = new CardAcceptor();
            cardAcceptor.setIdCode(req.getCardAccIdCode());
            cardAcceptor.setName(req.getCardAccName());
            Address address = new Address();
            address.setCity(req.getCardAccCity());
            address.setState(req.getCardAccState());
            address.setCountry(req.getCardAccCountry());
            cardAcceptor.setAddress(address);
            message.setCardAcceptor(cardAcceptor);
            /* 
            * message.setFeeProgramIndicator(req.getFeeProgramIndicator());
            */
            
            if (new BigDecimal("0").compareTo(new BigDecimal(req.getTransFeeAmt())) != 0) {
                message.setTransactionFeeAmt(req.getTransFeeAmt());
            }
            message.setMerchantCategoryCode(req.getMerCategoryCode());
            message.setLocalTransactionDateTime(req.getLocalTransDate());

            PurchaseIdentifier purchaseIdentifier = new PurchaseIdentifier();
            purchaseIdentifier.setReferenceNumber(req.getPIRefNum());
            purchaseIdentifier.setType(req.getPIType());
            message.setPurchaseIdentifier(purchaseIdentifier);

            message.setRecipientName(req.getCardAccName());
            message.setRecipientPrimaryAccountNumber(req.getMerPrimaryAccNum());
            message.setRetrievalReferenceNumber(req.getRetrievalRefNum());
            if (req.getSecondaryId() != null && !req.getSecondaryId().isEmpty()) {
                message.setSecondaryId(req.getSecondaryId());
            }
            
            message.setSenderAccountNumber(req.getSenderMSVSCardInfo().getPan_clear());
            message.setSenderName(req.getSenderMSVSCardInfo().getCardName());
            message.setSenderReference(req.getRefNo());
            message.setSystemsTraceAuditNumber(req.getSysTraceAuditNum());
            message.setTransactionCurrencyCode(req.getTransCurrencyCode());

            // thuc hien goi qua Visa
            result = factory.execute(message, VisaConstant.TRANSACTIONINFO, MessageType.MERPUSHPAYMENT);
        } catch (Exception e) {
            LOGGER.error("Failed to call VISA. Exception = [" + e.toString() + "]");
            result = new ResponseMessage();
            result.setError("-98", e.toString(), "Call to Visa failed", "High", "Not successfully");
        }
        return result;
    }
}