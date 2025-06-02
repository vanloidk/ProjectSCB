/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims.recieveFeedback;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hienlt6
 */
@XmlRootElement(name = "RECEIVERES")
public class ReceiveFeedbackRes implements Serializable {

    private static final long serialVersionUID = 1L;

    private String responseCode;
    private String description;
    private String partnerCode;
    private String typeId;
    private String type;
    private String signature;

    public ReceiveFeedbackRes() {
    }

    public ReceiveFeedbackRes(String responseCode, String description) {
        this.responseCode = responseCode;
        this.description = description;
    }

    @XmlElement(name = "MA_LOI")
    public String getResponseCode() {
        return responseCode;
    }

    @XmlElement(name = "MO_TA")
    public String getDescription() {
        return description;
    }

    @XmlElement(name = "MA_DOI_TAC")
    public String getPartnerCode() {
        return partnerCode;
    }

    @XmlElement(name = "MA_LOAI")
    public String getTypeId() {
        return typeId;
    }

    @XmlElement(name = "LOAI")
    public String getType() {
        return type;
    }

    @XmlElement(name = "CHU_KY")
    public String getSignature() {
        return signature;
    }

    /*--------------------Setter-----------------------*/
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getValueBuildMac() {
        return new StringBuilder(this.responseCode)
                .append(this.description)
                .append(this.partnerCode)
                .append(this.typeId)
                .append(this.type).toString();
    }
}
