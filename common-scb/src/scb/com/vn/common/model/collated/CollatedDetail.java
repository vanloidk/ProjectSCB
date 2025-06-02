/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.collated;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hienlt6
 */
@XmlRootElement(name = "TRANSSACTION_DETAIL")
public class CollatedDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    private String resultCollated;
    private String transTime;
    private String transId;
    private String orderId;
    private String mercTransId;
    private String amount;
    private String ccy;
    private String status;
    private String statusProc;

    @XmlElement(name = "RESULTCOLLATED")
    public String getResultCollated() {
        return resultCollated;
    }

    @XmlElement(name = "TRANSTIME")
    public String getTransTime() {
        return transTime;
    }

    @XmlElement(name = "TRANSID")
    public String getTransId() {
        return transId;
    }

    @XmlElement(name = "ORDERID")
    public String getOrderId() {
        return orderId;
    }

    @XmlElement(name = "MERCTRANSID")
    public String getMercTransId() {
        return mercTransId;
    }

    @XmlElement(name = "AMOUNT")
    public String getAmount() {
        return amount;
    }

    @XmlElement(name = "CCY")
    public String getCcy() {
        return ccy;
    }

    @XmlElement(name = "STATUS")
    public String getStatus() {
        return status;
    }

    @XmlElement(name = "STATUSPROC")
    public String getStatusProc() {
        return statusProc;
    }

    public void setStatusProc(String statusProc) {
        this.statusProc = statusProc;
    }

    public void setResultCollated(String resultCollated) {
        this.resultCollated = resultCollated;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setMercTransId(String mercTransId) {
        this.mercTransId = mercTransId;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
