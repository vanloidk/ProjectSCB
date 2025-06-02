/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.model.MobileRequest;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "SendEmailOpenTDRq")
public class SendEmailOpenTDRq extends MobileRequest {

    private String AccountNo;
    private String EmailCust;
    private String SrcChannel;
    private String Language;
    private String SerialNo;

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
     * @return the EmailCust
     */
    @XmlElement(name = "EmailCust", nillable = true)
    public String getEmailCust() {
        return EmailCust;
    }

    /**
     * @param EmailCust the EmailCust to set
     */
    public void setEmailCust(String EmailCust) {
        this.EmailCust = EmailCust;
    }

    /**
     * @return the SrcChannel
     */
    @XmlElement(name = "SrcChannel", nillable = true)
    public String getSrcChannel() {
        return SrcChannel;
    }

    /**
     * @param SrcChannel the SrcChannel to set
     */
    public void setSrcChannel(String SrcChannel) {
        this.SrcChannel = SrcChannel;
    }

    /**
     * @return the Language
     */
    @XmlElement(name = "Language", nillable = true)
    public String getLanguage() {
        return Language;
    }

    /**
     * @param Language the Language to set
     */
    public void setLanguage(String Language) {
        this.Language = Language;
    }

    /**
     * @return the SerialNo
     */
    public String getSerialNo() {
        return SerialNo;
    }

    /**
     * @param SerialNo the SerialNo to set
     */
    public void setSerialNo(String SerialNo) {
        this.SerialNo = SerialNo;
    }

}
