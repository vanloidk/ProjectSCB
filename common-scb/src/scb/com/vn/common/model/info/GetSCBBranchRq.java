/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.info;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.cims.CIMSRequest;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "REQUEST")
public class GetSCBBranchRq extends CIMSRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String transId;
    private String transDate;
    private String providerId;
    private String signature;

    @XmlElement(name = "TRANSID")
    public String getTransId() {
        return transId;
    }

    @XmlElement(name = "TRANSDATE")
    public String getTransDate() {
        return transDate;
    }

    @XmlElement(name = "PROVIDERID")
    public String getProviderId() {
        return providerId;
    }

    @XmlElement(name = "SIGNATURE")
    public String getSignature() {
        return signature;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String getValueToBuildMac() {
        return new StringBuilder().append(this.transId).append(getTime()).append(this.providerId).toString();
    }
}