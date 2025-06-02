/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cw;

/**
 *
 * @author minhndb
 */
public class DirectDebitRes implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String responseCode;
    private String responseDescription;
    private String approvalCode;

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
    
}
