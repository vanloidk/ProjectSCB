/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "SendEmailOpenTD")
public class SendEmailOpenTD {

    private String AccountNo;
    private String EmailCust;
    private String CreatedDate;
    private String TDAmount;
    private String Interest;

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
     * @return the CreatedDate
     */
    @XmlElement(name = "CreatedDate", nillable = true)
    public String getCreatedDate() {
        return CreatedDate;
    }

    /**
     * @param CreatedDate the CreatedDate to set
     */
    public void setCreatedDate(String CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    /**
     * @return the TDAmount
     */
    @XmlElement(name = "AccountBalance", nillable = true)
    public String getTDAmount() {
        return TDAmount;
    }

    /**
     * @param TDAmount the TDAmount to set
     */
    public void setTDAmount(String TDAmount) {
        this.TDAmount = TDAmount;
    }
    @XmlElement(name = "Interest", nillable = true)
    public String getInterest() {
        return Interest;
    }

    /**
     * @param Interest the Interest to set
     */
    public void setInterest(String Interest) {
        this.Interest = Interest;
    }
}
