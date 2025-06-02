/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author Administrator
 */
public class ProcedureCallDto implements java.io.Serializable {

    private String ErrorStatus;
    private String ErrorMessage;
    private String Other;

    /**
     * Create a new instance of ProcedureCallDto
     */
    public ProcedureCallDto() {
    }

    /**
     *
     * @return
     */
    public boolean isSucess() {
        return ErrorStatus.equals("0");
    }

    /**
     * @return the ErrorStatus
     */
    public String getErrorStatus() {
        return ErrorStatus;
    }

    /**
     * @param ErrorStatus the ErrorStatus to set
     */
    public void setErrorStatus(String ErrorStatus) {
        this.ErrorStatus = ErrorStatus;
    }

    /**
     * @return the ErrorMessage
     */
    public String getErrorMessage() {
        return ErrorMessage;
    }

    /**
     * @param ErrorMessage the ErrorMessage to set
     */
    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    /**
     * @return the Other
     */
    public String getOther() {
        return Other;
    }

    /**
     * @param Other the Other to set
     */
    public void setOther(String Other) {
        this.Other = Other;
    }

}
