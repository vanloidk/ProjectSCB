/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "CARDINFO")
public class CardInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<CardDetail> cardDetail;

    public CardInfo() {
        this.cardDetail = new ArrayList<CardDetail>();
    }
    
    public CardInfo(List<CardDetail> cardDetail) {
        this.cardDetail = cardDetail;
    }

    @XmlElement(name = "CARDDETAIL", nillable = false)
    public List<CardDetail> getCardDetail() {
        return cardDetail;
    }

    public void setCardDetail(List<CardDetail> cardDetail) {
        this.cardDetail = cardDetail;
    }
    
    public void addCardDetail(CardDetail cardDetail) {
        if (this.cardDetail == null) {
            this.cardDetail = new ArrayList<CardDetail>();
        }
        this.cardDetail.add(cardDetail);
    }
}