/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

public class CardInfoDto implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    private String panceCd;
    private String cardInfo;

    public String getPanceCd() {
        return panceCd;
    }

    public void setPanceCd(String panceCd) {
        this.panceCd = panceCd;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }
    
}
