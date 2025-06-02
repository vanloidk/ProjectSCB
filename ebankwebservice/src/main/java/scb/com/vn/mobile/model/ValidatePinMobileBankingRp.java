/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "ValidatePinMobileBankingRp")
public class ValidatePinMobileBankingRp {

    private String ErrorCode;
    private String ErrorMsg;
    private String NewPassword;

    /**
     *
     */
    public ValidatePinMobileBankingRp() {
    }

    /**
     * @return the ErrorCode
     */
    @XmlElement(name = "ErrorCode")
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
    @XmlElement(name = "ErrorMsg")
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
     * @return the NewPassword
     */
    @XmlElement(name = "NewPassword")
    public String getNewPassword() {
        return NewPassword;
    }

    /**
     * @param NewPassword the NewPassword to set
     */
    public void setNewPassword(String NewPassword) {
        this.NewPassword = NewPassword;
    }
}
