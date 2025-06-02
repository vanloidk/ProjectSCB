package scb.com.vn.common.model.cims.kt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MOKHOATHERES")
public class MoKhoaTheRes implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String sequence;
    private String cmnd;
    private String phone;
    private String last4Digits;
    private String cardType;
    private String openAll;
    private String status;
    private String loc;
    
    public MoKhoaTheRes() {}

    public MoKhoaTheRes(String status) {
        this.status = status;
    }
    
    public MoKhoaTheRes(MoKhoaTheReq req) {
        this.sequence = req.getSequence();
        this.cmnd = req.getCmnd();
        this.phone = req.getPhone();
        this.last4Digits = req.getLast4Digits();
        this.cardType = req.getCardType();
        this.openAll = req.getOpenAll();
        this.loc = req.getCardAccountNumer();
    }

    public MoKhoaTheRes(MoKhoaTheReq req, String status) {
        this.sequence = req.getSequence();
        this.cmnd = req.getCmnd();
        this.phone = req.getPhone();
        this.last4Digits = req.getLast4Digits();
        this.cardType = req.getCardType();
        this.openAll = req.getOpenAll();
        this.status = status;
        this.loc = req.getCardAccountNumer();
    }

    public MoKhoaTheRes(String sequence, String cmnd, String phone, String last4Digits, String cardType, String openAll, String status, String loc) {
        this.sequence = sequence;
        this.cmnd = cmnd;
        this.phone = phone;
        this.last4Digits = last4Digits;
        this.cardType = cardType;
        this.openAll = openAll;
        this.status = status;
        this.loc = loc;
    }

    @XmlElement(name = "SEQUENCE", nillable = false)
    public String getSequence() {
        return sequence;
    }
    
    @XmlElement(name = "CMND", nillable = true)
    public String getCmnd() {
        return cmnd;
    }
    
    @XmlElement(name = "PHONE", nillable = true)
    public String getPhone() {
        return phone;
    }

    @XmlElement(name = "LAST4DIGITS", nillable = true)
    public String getLast4Digits() {
        return last4Digits;
    }

    @XmlElement(name = "CARDTYPE", nillable = true)
    public String getCardType() {
        return cardType;
    }
    @XmlElement(name= "OPENALL", nillable = false)
    public String getOpenAll() {
        return openAll;
    }

    public void setOpenAll(String openAll) {
        this.openAll = openAll;
    }
    
    @XmlElement(name = "STATUS", nillable = false)
    public String getStatus() {
        return status;
    }
    @XmlElement(name = "LOC", nillable = false)
    public String getLoc() {
        return loc;
    }
    
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLast4Digits(String last4Digits) {
        this.last4Digits = last4Digits;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }    
    public void setLoc(String loc) {
        this.loc = loc;
    }
}