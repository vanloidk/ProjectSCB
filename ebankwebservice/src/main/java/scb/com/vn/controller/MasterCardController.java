/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import com.google.gson.Gson;
import com.mastercard.api.core.exception.ApiException;
import com.mastercard.api.core.model.RequestMap;
import com.mastercard.api.p2m.MerchantTransferPayment;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.masterpass.MCPaymentRp;
import scb.com.vn.common.model.masterpass.MasterCardQrActionEnum;
import scb.com.vn.common.model.masterpass.PayByQRCodeRq;
import scb.com.vn.constant.CommonConstant;
import scb.com.vn.message.MastercardMessage.ReasonCodeEnum;
import scb.com.vn.model.CreatePayment_NON_TOKENIZED;
import scb.com.vn.model.MCRetrievalRp;
import scb.com.vn.model.MerchantRetrievalRq;
import scb.com.vn.utility.Helper;

/**
 *
 * @author minhndb
 */
public class MasterCardController {

    private static final Logger LOGGER = Logger.getLogger(MasterCardController.class);

    /**
     *
     * @param value
     * @param index
     * @return
     */
    public MCRetrievalRp executeRetrieval(String value, int index) {
        MerchantRetrievalRq request = new MerchantRetrievalRq(value, index);
        return request.execute();
    }

    /**
     *
     * @param req
     * @return
     */
    public ReasonCodeEnum executePayment(PayByQRCodeRq req) {
        ReasonCodeEnum result;
        // call ham goi qua master de di thanh toan
        MCPaymentRp response = callPayment(req);
        // cau hinh tham so tra ve sau khi goi qua master
        req.setMastercardPaymentRp(response);
        try {
            // kiem tra xem mastercard co tra ve thanh cong hay ko
            String masterStatus = "APPROVED".equalsIgnoreCase(req.getMastercardPaymentRp().getMastercardStatus()) ? CommonConstant.MASTERQR_STATUS_MASTER_SUCCESS : CommonConstant.MASTERQR_STATUS_MASTER_FAILED;
            // cau hinh status sau khi da kiem tra de cap nhat xuong database
            req.setStatus(masterStatus);
            int resultDB = Helper.getDBI().MASTERPASSQR(req, MasterCardQrActionEnum.UPDATE_MASTER);
            // kiem tra xem thanh toan qua master co thanh cong hay ko
            if (req.getMastercardPaymentRp().getErrorCode() == null) {
                /* kiem tra xem co goi duoc di mastercard hay ko. Mot so case ko goi duoc se ko co mastercardStatus
                * Ex: Connect to 192.168.55.70:9090 [/192.168.55.70] failed: Connection refused: connect - null
                 */
                if (req.getMastercardPaymentRp().getMastercardStatus() != null) {
                    switch (req.getMastercardPaymentRp().getMastercardStatus().toUpperCase()) {
                        case "APPROVED":
                            result = (1 == resultDB) ? ReasonCodeEnum.MASTERCARD_OK : ReasonCodeEnum.MASTERCARD_OK_DBI_FAILED;
                            break;
                        case "DECLINED":
                        case "ERROR":
                        case "UNKNOWN":
                        case "PENDING":
                        default:
                            result = ReasonCodeEnum.MASTERCARD_UNKNOWN;
                            break;
                    }
                } else { // phan hoi cho tra soat
                    result = ReasonCodeEnum.MASTERCARD_UNKNOWN;
                }
            } else {
                switch (req.getMastercardPaymentRp().getErrorCode()) {
                    case "AUTHORIZATION_FAILED":
                    case "INVALID_INPUT_FORMAT":
                    case "INVALID_INPUT_LENGTH":
                    case "INVALID_INPUT_VALUE":
                    case "MISSING_REQUIRED_INPUT":
                    case "AUTHENTICATION_FAILED":
                    case "RESOURCE_ERROR":
                    case "DECLINE":
                        result = ReasonCodeEnum.MASTERCARD_FAILED;
                        break;
                    case "RESOURCE_UNKOWN":
                    default:
                        result = ReasonCodeEnum.getFromName(req.getMastercardPaymentRp().getErrorCode());
                        break;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to call DBI = [" + e.getMessage() + "], response = [" + req.getMastercardPaymentRp().toString() + "]");
            result = ReasonCodeEnum.MASTERCARD_RETURN_DBI_FAILED;
        }
        return result;
    }

    private MCPaymentRp callPayment(PayByQRCodeRq req) {
        Gson g = new Gson();
        MCPaymentRp result;
        try {
            CreatePayment_NON_TOKENIZED request = new CreatePayment_NON_TOKENIZED(req);
            RequestMap map = request.buildRequestMapForPayment();
            LOGGER.info("Master request = [" + request.buildRequestMapForLogging() + "]");
            MerchantTransferPayment response = MerchantTransferPayment.create(map);
            LOGGER.info("Master response = [" + g.toJson(response) + "]");
            result = new MCPaymentRp(response);
        } catch (ApiException e) {
            result = new MCPaymentRp(e);
            LOGGER.error("Failed to call MASSTERCARD. Exception = [" + e.toString() + "], Code = [" + e.getReasonCode() + "], Desc = [" + e.getMessage() + " - " + e.getSource() + "]");
        }
        return result;
    }
}
