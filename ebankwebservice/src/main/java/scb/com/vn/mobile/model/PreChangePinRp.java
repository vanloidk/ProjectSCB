/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "PreChangePinRp")
public class PreChangePinRp extends MobileResponse {

    private String TxnType;
    private String CifNo;
    private String TxnID;
    private String TxnCommitTime;        
    private String AccountNo;
    private String PublicKey;
    private String AccessToken;
        
    /**
     *
     */
    public PreChangePinRp() {
     
    }

    /**
     * @return the TxnID
     */
    @XmlElement(name = "TxnID", nillable = true)
    public String getTxnID() {
        return TxnID;
    }

    /**
     * @param TxnID the TxnID to set
     */
    public void setTxnID(String TxnID) {
        this.TxnID = TxnID;
    }

    /**
     * @return the TxnCommitTime
     */
    @XmlElement(name = "TxnCommitTime", nillable = true)
    public String getTxnCommitTime() {
        return TxnCommitTime;
    }

    /**
     * @param TxnCommitTime the TxnCommitTime to set
     */
    public void setTxnCommitTime(String TxnCommitTime) {
        this.TxnCommitTime = TxnCommitTime;
    }

    /**
     * @return the TxnType
     */
    @XmlElement(name = "TxnType", nillable = true)
    public String getTxnType() {
        return TxnType;
    }

    /**
     * @param TxnType the TxnType to set
     */
    public void setTxnType(String TxnType) {
        this.TxnType = TxnType;
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
     * @return the AccountNo
     */
    @XmlElement(name = "AccountNo", nillable = true)
    public String getAccountNo() {
        return AccountNo;
    }

    /**
     * @param AccountNo the AccountNo to set
     */
    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }

    /**
     * @return the PublicKey
     */
    @XmlElement(name = "PublicKey", nillable = true)    
    public String getPublicKey() {
        return PublicKey;
    }

    /**
     * @param PublicKey the PublicKey to set
     */
    public void setPublicKey(String PublicKey) {
        this.PublicKey = PublicKey;
    }

    /**
     * @return the AccessToken
     */
    @XmlElement(name = "AccessToken", nillable = true)        
    public String getAccessToken() {
        return AccessToken;
    }

    /**
     * @param AccessToken the AccessToken to set
     */
    public void setAccessToken(String AccessToken) {
        this.AccessToken = AccessToken;
    }
      
}
