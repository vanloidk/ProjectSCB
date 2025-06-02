/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.info;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import scb.com.vn.ultility.Common;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "RESPONSE")
public class GetSCBBranchRp implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GetSCBBranchRp.class);
    
    private String responseCode;
    private String responseDesc;
    private String listData;
    private String providerId;
    private String signature;

    public GetSCBBranchRp() {}
    
    public GetSCBBranchRp(String responseCode, String responseDesc) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
    }

    public GetSCBBranchRp(String responseCode, String responseDesc, String providerId) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
        this.providerId = providerId;
    }
    
    public GetSCBBranchRp(String responseCode, String responseDesc, String providerId, String listData) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
        this.providerId = providerId;
        this.listData = listData;
    }

    @XmlElement(name = "RESPONSECODE")
    public String getResponseCode() {
        return responseCode;
    }

    @XmlElement(name = "RESPONSEDESC")
    public String getResponseDesc() {
        return responseDesc;
    }

    @XmlElement(name = "LISTDATA")
    public String getListData() {
        return listData;
    }

    @XmlElement(name = "PROVIDERID")
    public String getProviderId() {
        return providerId;
    }

    @XmlElement(name = "SIGNATURESCB")
    public String getSignature() {
        return signature;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public void setListData(String listData) {
        this.listData = listData;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    
    public void setSignatureScb(String md5Key) {
        try {
            this.signature = Common.EncriptMD5(md5Key + this.responseCode + this.providerId);
        } catch (Exception e) {
            logger.error(e);
        }
    }
}