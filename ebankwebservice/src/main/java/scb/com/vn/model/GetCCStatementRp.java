/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "GetCCStatementRp")
public class GetCCStatementRp extends MobileResponse {

    /**
     *
     */
    public GetCCStatementRp() {
    }

    private String AccountNo;
    private MCTxnDetail[] txnList;

    /**
     * @return the txnList
     */
    @XmlElement(name = "MCTxnDetail")
    @XmlElementWrapper(name = "MCStatementList", nillable = true)
    public MCTxnDetail[] getTxnList() {
        return txnList;
    }

    /**
     * @param txnList the txnList to set
     */
    public void setTxnList(MCTxnDetail[] txnList) {
        this.txnList = txnList;
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
}
