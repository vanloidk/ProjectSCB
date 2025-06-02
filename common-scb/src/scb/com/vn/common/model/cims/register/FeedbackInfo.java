/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims.register;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hienlt6
 */
@XmlRootElement(name = "THONG_TIN_GOP_Y")
public class FeedbackInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String feedbackId;
    private String typeFeedback;
    private String userId;
    private String subject;
    private String content;
    private String insDate;
    private String idChannel;

    @XmlElement(name = "MA_GOP_Y")
    public String getFeedbackId() {
        return feedbackId;
    }

    @XmlElement(name = "LOAI_Y_KIEN")
    public String getTypeFeedback() {
        return typeFeedback;
    }

    @XmlElement(name = "MA_NGUOI_DUNG")
    public String getUserId() {
        return userId;
    }

    @XmlElement(name = "TIEU_DE")
    public String getSubject() {
        return subject;
    }

    @XmlElement(name = "NOI_DUNG")
    public String getContent() {
        return content;
    }

    @XmlElement(name = "NGAY_GUI")
    public String getInsDate() {
        return insDate;
    }

    @XmlElement(name = "KENH_TIEP_NHAN")
    public String getIdChannel() {
        return idChannel;
    }

    /*-----------------------Setter------------------------------*/

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public void setTypeFeedback(String typeFeedback) {
        this.typeFeedback = typeFeedback;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setInsDate(String insDate) {
        this.insDate = insDate;
    }

    public void setIdChannel(String idChannel) {
        this.idChannel = idChannel;
    }

}
