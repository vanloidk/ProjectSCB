/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.message.Message;
import scb.com.vn.utility.MessageMB;
//import scb.com.vn.utility.Message;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "MobileResponse")
public class MobileResponse {

    private String ErrorCode;
    private String ErrorMsg;

    /**
     * @return the ErrorCode
     */
    @XmlElement(name = "ErrorCode", nillable = true)
    public String getErrorCode() {
        return ErrorCode;
    }

    /**
     * @param ErrorCode the ErrorCode to set
     */
    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    /**
     * @return the ErrorMsg
     */
    @XmlElement(name = "ErrorMsg", nillable = true)
    public String getErrorMsg() {
        return ErrorMsg;
    }

    /**
     * @param ErrorMsg the ErrorMsg to set
     */
    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    /**
     *
     * @param msg
     * @return
     */
    public MobileResponse getMBResponse(Message.PaymentResultEnum msg) {
        this.setErrorCode(msg.getValue());
        this.setErrorMsg(Message.getMessagePaymentResult(msg));
        return this;
    }

    /**
     *
     * @param msg
     * @return
     */
    public MobileResponse getMBResponse(MessageMB.MobileResultEnum msg) {
        this.setErrorCode(msg.getValue());
        this.setErrorMsg(MessageMB.getMessageMBResult(msg));
        return this;
    }
}
