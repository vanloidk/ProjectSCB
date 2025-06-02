/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "CheckTokenRp")
public class CheckTokenRp extends MobileResponse {

    private String CustomerNo;
    private String serialno;
    private String IsValidToken;
    private String tokenUpg;
    private String challengeCode;

    /**
     * @return the CustomerNo
     */
    @XmlElement(name = "CustomerNo", nillable = true)
    public String getCustomerNo() {
        return CustomerNo;
    }

    /**
     * @param CustomerNo the CustomerNo to set
     */
    public void setCustomerNo(String CustomerNo) {
        this.CustomerNo = CustomerNo;
    }

    /**
     * @return the serialno
     */
    @XmlElement(name = "SerialNo", nillable = true)
    public String getSerialno() {
        return serialno;
    }

    /**
     * @param serialno the serialno to set
     */
    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    /**
     * @return the IsValidToken
     */
    @XmlElement(name = "IsValidToken", nillable = true)
    public String getIsValidToken() {
        return IsValidToken;
    }

    /**
     * @param IsValidToken the IsValidToken to set
     */
    public void setIsValidToken(String IsValidToken) {
        this.IsValidToken = IsValidToken;
    }

    /**
     * @return the tokenUpg
     */
    @XmlElement(name = "TokenUpg", nillable = true)
    public String getTokenUpg() {
        return tokenUpg;
    }

    /**
     * @param tokenUpg the tokenUpg to set
     */
    public void setTokenUpg(String tokenUpg) {
        this.tokenUpg = tokenUpg;
    }

    /**
     * @return the challengeCode
     */
    @XmlElement(name = "ChallengeCode", nillable = true)
    public String getChallengeCode() {
        return challengeCode;
    }

    /**
     * @param challengeCode the challengeCode to set
     */
    public void setChallengeCode(String challengeCode) {
        this.challengeCode = challengeCode;
    }
}
