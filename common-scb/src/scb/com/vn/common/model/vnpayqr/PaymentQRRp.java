/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.vnpayqr;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.MobileResponse;
import scb.com.vn.message.CommonMessage;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "PaymentQRRp")
public class PaymentQRRp extends MobileResponse implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String clientId;
    private String payCode;
    private String traceTransfer;
    private String resCode;
    private String resDesc;

    @XmlElement(name = "ClientId", nillable = true)
    public String getClientId() {
        return clientId;
    }

    @XmlElement(name = "PayCode", nillable = true)
    public String getPayCode() {
        return payCode;
    }

    @XmlElement(name = "TraceTransfer", nillable = true)
    public String getTraceTransfer() {
        return traceTransfer;
    }
    
    @XmlElement(name = "ResCode", nillable = true)
    public String getResCode() {
        return resCode;
    }

    @XmlElement(name = "ResDesc", nillable = true)
    public String getResDesc() {
        return resDesc;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setTraceTransfer(String traceTransfer) {
        this.traceTransfer = traceTransfer;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }
    
    public void setupBeforeReturn(String clientId, PaymentQRJson qrPayment) {
        this.clientId = clientId;
        this.traceTransfer = qrPayment.getTraceTransfer();
    }
    
    @Override
    public MobileResponse getMBResponse(CommonMessage.CommontEnum msg)
    {
        this.resCode = msg.getValue();
        this.resDesc = CommonMessage.getCommontEnumDescription(msg);
        return this;
    }
}