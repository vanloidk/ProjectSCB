/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model;

import javax.xml.bind.annotation.XmlElement;
import scb.com.vn.message.CardwordMessage;
import scb.com.vn.message.CommonMessage;
import scb.com.vn.message.CommonMessage.CommontEnum;
import scb.com.vn.message.MastercardMessage;
import scb.com.vn.message.Message;
import scb.com.vn.message.VisaMessage;

/**
 *
 * @author minhndb
 */
public class MobileResponse implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String ErrorCode;
    private String ErrorMsg;

    @XmlElement(name = "ErrorCode", nillable = true)
    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    @XmlElement(name = "ErrorMsg", nillable = true)
    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public MobileResponse getMBResponse(Message.PaymentResultEnum msg) {
        this.setErrorCode(msg.getValue());
        this.setErrorMsg(Message.getMessagePaymentResult(msg));
        return this;
    }
    
    public MobileResponse getMBResponse(Message.PaymentResultEnum msg, String description) {
        this.setErrorCode(msg.getValue());
        this.setErrorMsg(description);
        return this;
    }
    
    public MobileResponse getMBResponse(String errorCode, String errorDescription) {
        this.setErrorCode(errorCode);
        this.setErrorMsg(errorDescription);
        return this;
    }
    
    public MobileResponse getMBResponse(CommontEnum msg)
    {
        this.setErrorCode(msg.getValue());
        this.setErrorMsg(CommonMessage.getCommontEnumDescription(msg));
        return this;
    }
    
    public MobileResponse getMBResponse(MastercardMessage.ReasonCodeEnum msg)
    {
        CommontEnum index = CommontEnum.getFromName(msg.toString());
        if (index != null)
        {
            this.setErrorCode(index.getValue());
            this.setErrorMsg(CommonMessage.getCommontEnumDescription(index));
        }
        else
        {
            this.setErrorCode(CommontEnum.MASTERCARD_FAILED.getValue());
            this.setErrorMsg(msg.getValue() + " - " + MastercardMessage.getReasonCodeDescription(msg));
        }
        
        return this;
    }
    
    public MobileResponse getMBResponse(CardwordMessage.ErrorCodeEnum msg)
    {
        CommontEnum index = CommontEnum.getFromName(msg.toString());
        if (index != null)
        {
            this.setErrorCode(index.getValue());
            this.setErrorMsg(CommonMessage.getCommontEnumDescription(index));
        }
        else
        {
            this.setErrorCode(CommontEnum.CARDWORD_FAILED.getValue());
            this.setErrorMsg(msg.getValue() + " - " + CardwordMessage.getErrorCodeDescription(msg));
        }
        
        return this;
    }
    
    public MobileResponse getMBResponse(VisaMessage.VisaCodeEnum msg)
    {
        CommontEnum index = CommontEnum.getFromName(msg.toString());
        if (index != null)
        {
            this.setErrorCode(index.getValue());
            this.setErrorMsg(CommonMessage.getCommontEnumDescription(index));
        }
        else
        {
            this.setErrorCode(CommontEnum.CARDWORD_FAILED.getValue());
            this.setErrorMsg(msg.getValue() + " - " + VisaMessage.getReasonCodeDescription(msg));
        }
        
        return this;
    }
}
