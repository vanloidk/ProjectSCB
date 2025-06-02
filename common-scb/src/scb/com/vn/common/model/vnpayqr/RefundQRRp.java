/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.vnpayqr;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.MobileResponse;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "RefundQRRp")
public class RefundQRRp extends MobileResponse implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String resCode;
    private String resDesc;
    private String traceBank;
    private String payCode;

    @XmlElement(name = "ResCode", nillable = true)
    public String getResCode() {
        return resCode;
    }

    @XmlElement(name = "ResDesc", nillable = true)
    public String getResDesc() {
        return resDesc;
    }

    @XmlElement(name = "TraceBank", nillable = true)
    public String getTraceBank() {
        return traceBank;
    }

    @XmlElement(name = "PayCode", nillable = true)
    public String getPayCode() {
        return payCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public void setTraceBank(String traceBank) {
        this.traceBank = traceBank;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }   
}