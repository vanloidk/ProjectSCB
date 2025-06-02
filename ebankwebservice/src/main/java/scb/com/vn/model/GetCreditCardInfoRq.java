/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "GetCreditCardInfoRq")
public class GetCreditCardInfoRq {

    private String UserName;
    private String CifNo;

    private String CardNo;

    /**
     * @return the UserName
     */
    @XmlElement(name = "UserName", nillable = true)
    public String getUserName() {
        return UserName;
    }

    /**
     * @param UserName the UserName to set
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     * @return the CifNo
     */
    @XmlElement(name = "CifNo", nillable = true)
    public String getCifNo() {
        return CifNo;
    }

    /**
     * @param CifNo the CifNo to set
     */
    public void setCifNo(String CifNo) {
        this.CifNo = CifNo;
    }

    /**
     * @return the CardNo
     */
    @XmlElement(name = "CardNo", nillable = true)
    public String getCardNo() {
        return CardNo;
    }

    /**
     * @param CardNo the CardNo to set
     */
    public void setCardNo(String CardNo) {
        this.CardNo = CardNo;
    }
}
