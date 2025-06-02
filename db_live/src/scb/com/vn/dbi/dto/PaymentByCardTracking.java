package scb.com.vn.dbi.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author loinv3
 */
public class PaymentByCardTracking implements Serializable {

    private static final long serialVersionUID = 1L;

    String id;
    String paymentId;
    String approved;
    String idUserMarker;
    String idUserChecker;
    String branchCode;
    String type;
    String soCif;
    String cardNumber;
    String cardName;
    String verifyDate;
    String partnerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getIdUserMarker() {
        return idUserMarker;
    }

    public void setIdUserMarker(String idUserMarker) {
        this.idUserMarker = idUserMarker;
    }

    public String getIdUserChecker() {
        return idUserChecker;
    }

    public void setIdUserChecker(String idUserChecker) {
        this.idUserChecker = idUserChecker;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSoCif() {
        return soCif;
    }

    public void setSoCif(String soCif) {
        this.soCif = soCif;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(String verifyDate) {
        this.verifyDate = verifyDate;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

}
