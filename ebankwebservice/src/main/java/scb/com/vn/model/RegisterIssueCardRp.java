/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "RegisterIssueCardRp")
public class RegisterIssueCardRp {

    private String ErrorCode;
    private String ErrorMsg;

    /**
     *
     */
    public RegisterIssueCardRp() {
    }

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

}
