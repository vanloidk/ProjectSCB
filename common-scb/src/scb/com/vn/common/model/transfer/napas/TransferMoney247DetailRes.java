/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer.napas;

import java.io.Serializable;
import java.security.PublicKey;
import javax.xml.bind.annotation.XmlElement;
import org.apache.log4j.Logger;
import scb.com.vn.ultility.RsaUtils;

/**
 *
 * @author minhndb
 */
public class TransferMoney247DetailRes implements Serializable {

    private static final Logger logger = Logger.getLogger(TransferMoney247DetailRes.class);
    private static final long serialVersionUID = 1L;

    private String responseCode;
    private String destinationName = "";
    private String refCore = "";
    private String description = "";
    private String authorizationCode = "";
    private String settDateF15 = "";
    private String napasResponseCode = "";
    private String refCoreRefund = "";

    public TransferMoney247DetailRes() {
    }

    public TransferMoney247DetailRes(String responseCode) {
        this.responseCode = responseCode;
    }

    public TransferMoney247DetailRes(String responseCode, String destinationName, String refCore) {
        this.responseCode = responseCode;
        this.destinationName = destinationName;
        this.refCore = refCore;
    }

    @XmlElement(name = "RESPONSECODE")
    public String getResponseCode() {
        return responseCode;
    }

    @XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @XmlElement(name = "DESTINATIONNAME")
    public String getDestinationName() {
        return destinationName;
    }

    @XmlElement(name = "REFCORE")
    public String getRefCore() {
        return refCore;
    }

    @XmlElement(name = "AUTHORIZATIONCODE")
    public String getAuthorizationCode() {
        return authorizationCode;
    }

    @XmlElement(name = "SETTDATEF15")
    public String getSettDateF15() {
        return settDateF15;
    }

    @XmlElement(name = "NAPASRESPONSECODE")
    public String getNapasResponseCode() {
        return napasResponseCode;
    }

    @XmlElement(name = "REFCOREREFUND")
    public String getRefCoreRefund() {
        return refCoreRefund;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public void setRefCore(String refCore) {
        this.refCore = refCore;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public void setSettDateF15(String settDateF15) {
        this.settDateF15 = settDateF15;
    }

    public void setNapasResponseCode(String napasResponseCode) {
        this.napasResponseCode = napasResponseCode;
    }

    public void setRefCoreRefund(String refCoreRefund) {
        this.refCoreRefund = refCoreRefund;
    }

    public void updateNapasResponseForQuery(TransferMoney247DetailReq req, String napasResponse, PublicKey publicKey) {
        try {
            String[] arrResultCheckToNumber = napasResponse.replace("|", "#").split("#");
            String[] items = napasResponse.split("\\|");
            boolean responseIsValid = RsaUtils.verifyResponse(items, publicKey);
            if (responseIsValid) {
                /* Nen kiem tra do dai cua mang phan tu truoc khi lay gia tri */
                napasResponseCode = arrResultCheckToNumber[9];
                authorizationCode = arrResultCheckToNumber[8];
                destinationName = arrResultCheckToNumber[12];
                settDateF15 = "CARD".equals(req.getTypeToNumber())
                        ? arrResultCheckToNumber[14]
                        : arrResultCheckToNumber[15];
                switch (napasResponseCode) {
                    case "00": /* Giao dich thanh cong. Kiem tra tai khoan co du tien de thanh toan ko roi moi cau hinh 00 cho bien responseCode */
                        break;
                    case "01":
                        responseCode = "08"; // TK/THE DICH KHONG HOP LE
                        logger.warn("TK/THE DICH KHONG HOP LE");
                        break;
                    case "14":
                        responseCode = "09"; // TK DICH/ THE KHONG TON TAI
                        logger.warn("TK DICH/ THE KHONG TON TAI");
                        break;
                    case "41":
                        responseCode = "10"; // The mat
                        logger.warn("The mat");
                        break;
                    case "05":
                    case "04":
                    case "07":
                        responseCode = "11"; // The nghi van
                        logger.warn("The nghi van");
                        break;
                    case "54":
                        responseCode = "12"; // The het han
                        logger.warn("The het han");
                        break;
                    case "51":
                        responseCode = "13"; // The bi gioi han
                        logger.warn("The bi gioi han");
                        break;
                    case "68":
                        responseCode = "15"; // Truy van SML time out loi 68
                        logger.warn("Truy van SML time out loi 68");
                        break;
                    case "91":
                    case "92":
                    default:
                        responseCode = "14"; // Ngan hang thu huong chua tham gia
                        logger.warn("Ngan hang thu huong chua tham gia. Napas res code " + napasResponseCode);
                        break;
                }
            } else { /* Khong dung chu ky */
                responseCode = "99";
                logger.warn("Khong dung chu ky");
            }
        } catch (Exception e) {
            logger.error(e);
            responseCode = "99";
        }
    }

    public void updateNapasResponseForTrans(TransferMoney247DetailReq req, String napasResponse, PublicKey publicKey) {
        try {
            if ("TIMEOUT".equals(napasResponse)) { /* CK SML time out */
                responseCode = "18";
                logger.warn("CALL NAPAS BI TIMEOUT");
            } else {
                String[] arrResultCheckToNumber = napasResponse.replace("|", "#").split("#");
                String[] items = napasResponse.split("\\|");
                boolean responseIsValid = RsaUtils.verifyResponse(items, publicKey);

                if (responseIsValid) {
                    /* Nen kiem tra do dai cua mang phan tu truoc khi lay gia tri */
                    napasResponseCode = arrResultCheckToNumber[9];
                    authorizationCode = arrResultCheckToNumber[8];
                    settDateF15 = "CARD".equals(req.getTypeToNumber())
                            ? arrResultCheckToNumber[14]
                            : arrResultCheckToNumber[15];
                    switch (napasResponseCode) {
                        case "00": /* Giao dich thanh cong */
                            responseCode = "00";
                            break;
                        case "05":
                            responseCode = "19"; // CHUYEN TIEN SML LOI
                            logger.warn("CHUYEN TIEN SML LOI");
                            break;
                        case "61":
                            responseCode = "20"; // QUÁ HẠN MỨC SỐ TIỀN/NGÀY
                            logger.warn("Qua han muc so tien/ngay");
                            break;
                        case "65":
                            responseCode = "21"; // QUÁ HẠN MỨC SỐ LẦN/NGÀY
                            logger.warn("Qua han muc so lan/ngay");
                            break;
                        case "68":
                            responseCode = "24"; // Time out do loi CK SML 68
                            logger.warn("Time out do loi CK SML 68");
                            break;
                        default:
                            responseCode = "19"; // THUC HIEN HOAN TIEN
                            logger.warn("THUC HIEN HOAN TIEN DO LOI NAPAS RES CODE " + napasResponseCode);
                            break;
                    }
                } else { /* Khong dung chu ky  */
                    logger.warn("Khong dung chu ky");
                    responseCode = "99";
                }
            }
        } catch (Exception e) {
            logger.error(e);
            responseCode = "99";
        }
    }

    public boolean isNeedToRefund(TransferMoney247DetailReq req) {
        boolean result = false;
        /* Xet dieu kien de hoan tien cho doi tac. Tam thoi bo qua vi chua confirm duoc loi nao se hoan tien
        * Sau khi discuss voi anh Duy thi mo ra theo logic cua chi Ly. Rui ro la se hoan tien cho ca nhung truong hop ko bit truoc duoc
        */
        if (napasResponseCode != null && !napasResponseCode.isEmpty()
                && !"00".equals(napasResponseCode) && !"68".equals(napasResponseCode)
                && !"18".equals(responseCode)) {
            if (napasResponseCode.length() == 2) {
                result = true;
            } else {
                logger.warn("Ko thuc hien hoan tien cho khach hang vi Napas response code co do dai bat thuong. napasResponseCode = [" + napasResponseCode + "], responseCode = [" + responseCode + "]");
            }
        } else {
            logger.warn("Ko thuc hien hoan tien cho khach hang vi napasResponseCode = [" + napasResponseCode + "], responseCode = [" + responseCode + "]");
        }
        
        return result;
    }

    public void updateStatusForMBEB(TransferMoney247DetailReq req) {
        if ("99".equals(responseCode)) {
            responseCode = "QUE".equals(req.getTypeFunction()) ? "-99" : "24";
        }
    }
}
