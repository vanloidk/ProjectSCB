/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cw;

/**
 *
 * @author minhndb
 */
public class CardAdjRes implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String cardAdjSequence;
    private String responseCode;
    private String responseDescription;

    public String getCardAdjSequence() {
        return cardAdjSequence;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setCardAdjSequence(String cardAdjSequence) {
        this.cardAdjSequence = cardAdjSequence;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }
    
    
}
