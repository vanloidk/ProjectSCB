/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims.register;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author hienlt6
 */
@XmlRootElement(name = "REGISTERRES")
public class RegisterInfoRes implements Serializable {

    private static final long serialVersionUID = 1L;

    private String responseCode;
    private String description;
    private String partnerCode;
    private String Signature;
    private List<RegisterInfoDetail> registerDetail;
    private List<FeedbackInfo> feedbackInfo;

    public RegisterInfoRes() {
    }

    public RegisterInfoRes(String responseCode, String description) {
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

    @XmlElement(name = "CHU_KY")
    public String getSignature() {
        return Signature;
    }

    @XmlElement(name = "DANG_KY_THONG_TIN")
    public List<RegisterInfoDetail> getRegisterDetail() {
        return registerDetail;
    }

    @XmlElement(name = "GOP_Y")
    public List<FeedbackInfo> getFeedbackInfo() {
        return feedbackInfo;
    }

    /*----------------------Setter----------------------------------*/
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public void setSignature(String Signature) {
        this.Signature = Signature;
    }

    public void setRegisterDetail(List<RegisterInfoDetail> registerDetail) {
        this.registerDetail = registerDetail;
    }

    public void setFeedbackInfo(List<FeedbackInfo> feedbackInfo) {
        this.feedbackInfo = feedbackInfo;
    }

    public String getValueToBuildMac() {
        return new StringBuilder(this.responseCode).append(this.description).append(this.partnerCode).toString();
    }
}
