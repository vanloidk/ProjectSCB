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
@XmlRootElement(name = "VerifyTokenCodeRp")
public class VerifyTokenCodeRp extends MobileResponse {

    private String AccountNo;
    private String TxnType;
    private String CifNo;

    /**
     * @return the TypeToNumber
     */
    @XmlElement(name = "TxnType", nillable = true)
    public String getTxnType() {
        return TxnType;
    }

    /**
     * @param TxnType the TypeToNumber to set
     */
    public void setTxnType(String TxnType) {
        this.TxnType = TxnType;
    }

    /**
     * @return the ToAccount
     */
    @XmlElement(name = "AccountNo", nillable = true)
    public String getAccountNo() {
        return AccountNo;
    }

    /**
     * @param AccountNo
     */
    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }
}
