/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cw;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "CARDADJUSTMENTREQ")
public class CardAdjustmentReq implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String sequenceNo;
    private String actInd;
    private String adjCde;
    private String fi;
    private String pan;
    private String refNo;
    private String adjAmt;
    private String brchCde;
    private String merchantId;
    private String termId;
    private String officerId;
    private String remarks;
    private String partner;
    private String respCode;
    private String respDesc;
    private String status;

    @XmlElement(name = "ACTIND")
    public String getActInd() {
        return actInd;
    }

    @XmlElement(name = "ADJCDE")
    public String getAdjCde() {
        return adjCde;
    }

    @XmlElement(name = "FI")
    public String getFi() {
        return fi;
    }

    @XmlElement(name = "PAN")
    public String getPan() {
        return pan;
    }

    @XmlElement(name = "REFNO")
    public String getRefNo() {
        return refNo;
    }

    @XmlElement(name = "ADJAMT")
    public String getAdjAmt() {
        return adjAmt;
    }

    @XmlElement(name = "BRCHCDE")
    public String getBrchCde() {
        return brchCde;
    }

    @XmlElement(name = "MERCHANTID")
    public String getMerchantId() {
        return merchantId;
    }

    @XmlElement(name = "TERMID")
    public String getTermId() {
        return termId;
    }

    @XmlElement(name = "OFFICERID")
    public String getOfficerId() {
        return officerId;
    }

    @XmlElement(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }

    @XmlElement(name = "PARTNER")
    public String getPartner() {
        return partner;
    }

    public String getSequenceNo() {
        return sequenceNo;
    }

    public String getRespCode() {
        return respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setActInd(String actInd) {
        this.actInd = actInd;
    }

    public void setAdjCde(String adjCde) {
        this.adjCde = adjCde;
    }

    public void setFi(String fi) {
        this.fi = fi;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public void setAdjAmt(String adjAmt) {
        this.adjAmt = adjAmt;
    }

    public void setBrchCde(String brchCde) {
        this.brchCde = brchCde;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public void setOfficerId(String officerId) {
        this.officerId = officerId;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isInvalid() {
        return actInd == null || actInd.isEmpty()
                || adjCde == null || adjCde.isEmpty()
                || fi == null || fi.isEmpty()
                || pan == null || pan.isEmpty()
                || refNo == null || refNo.isEmpty()
                || adjAmt == null || adjAmt.isEmpty()
                || brchCde == null || brchCde.isEmpty()
                || merchantId == null || merchantId.isEmpty()
                || termId == null || termId.isEmpty()
                || officerId == null || officerId.isEmpty()
                || remarks == null || remarks.isEmpty()
                || partner == null || partner.isEmpty();
    }
}
