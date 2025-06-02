/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims.kt;

import java.io.Serializable;
import scb.com.vn.common.model.cw.CardInfo;

/**
 *
 * @author minhndb
 */
public class KhoaTheDetailsInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String khoaTheDetailsId;
    private String khoaTheId;
    private String requester;
    private String last4Digits;
    private String cardNumber;
    private CardInfo cardInfo;
    private String responseCode = "";
    private String responseDesc = "";
    
    private String panEncrypt;
    
    private String status = "2";

    public String getKhoaTheDetailsId() {
        return khoaTheDetailsId;
    }

    public void setKhoaTheDetailsId(String khoaTheDetailsId) {
        this.khoaTheDetailsId = khoaTheDetailsId;
    }

    public String getKhoaTheId() {
        return khoaTheId;
    }

    public void setKhoaTheId(String khoaTheId) {
        this.khoaTheId = khoaTheId;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getLast4Digits() {
        return last4Digits;
    }

    public void setLast4Digits(String last4Digits) {
        this.last4Digits = last4Digits;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }
    
     public String getPanEncrypt() {
        return panEncrypt;
    }

    public void setPanEncrypt(String panEncrypt) {
        this.panEncrypt = panEncrypt;
    }
}