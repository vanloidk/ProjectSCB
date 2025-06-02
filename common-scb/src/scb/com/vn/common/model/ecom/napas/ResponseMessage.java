/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.ecom.napas;

import java.util.List;

/**
 *
 * @author tamnt12
 */
public class ResponseMessage {
    private String approvedCode;
    private String resCode;
    private String resMessage;
    
    private List<OtherInfo> otherInfo;

    public String getApprovedCode() {
        return approvedCode;
    }

    public void setApprovedCode(String approvedCode) {
        this.approvedCode = approvedCode;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public List<OtherInfo> getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(List<OtherInfo> otherInfo) {
        this.otherInfo = otherInfo;
    }
    
    
    
}
