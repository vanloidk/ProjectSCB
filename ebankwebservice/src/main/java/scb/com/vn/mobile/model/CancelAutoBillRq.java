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
@XmlRootElement(name = "CancelAutoBillRq")
public class CancelAutoBillRq extends MobileRequest {

    private String AutoBillIds;
    private String TxnType;

    /**
     * @return the AutoBillIds
     */
    @XmlElement(name = "AutoBillIds", nillable = true)
    public String getAutoBillIds() {
        return AutoBillIds;
    }

    /**
     * @param AutoBillIds the AutoBillIds to set
     */
    public void setAutoBillIds(String AutoBillIds) {
        this.AutoBillIds = AutoBillIds;
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
}
