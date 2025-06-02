/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Administrator
 */
public class BillPaymentRp extends MobileResponse {

    private String TxnID;
    private String TxnCommitTime;
    private String CustomerCode;

    /**
     *
     */
    public BillPaymentRp() {
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
     * @return the CustomerCode
     */
    @XmlElement(name = "CustomerCode", nillable = true)
    public String getCustomerCode() {
        return CustomerCode;
    }

    /**
     * @param CustomerCode the CustomerCode to set
     */
    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }
}
