package scb.com.vn.common.model.cims.kt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.cims.CIMSRequest;

@XmlRootElement(name = "MOKHOATHEREQ")
public class MoKhoaTheReq extends CIMSRequest implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String sequence;
    private String cmnd;
    private String phone;
    private String last4Digits;
    private String cardType;
    private String openAll;
    private String cardAccountNumer;
    private String channel;

    @XmlElement(name = "SEQUENCE", nillable = false)
    public String getSequence() {
        return sequence;
    }

    @XmlElement(name = "CMND", nillable = false)
    public String getCmnd() {
        return cmnd;
    }

    @XmlElement(name = "PHONE", nillable = false)
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

    @XmlElement(name = "OPENALL", nillable = false)
    public String getOpenAll() {
        return openAll;
    }

    public void setOpenAll(String openAll) {
        this.openAll = openAll;
    }

    @XmlElement(name = "LOC", nillable = false)
    public String getCardAccountNumer() {
        return cardAccountNumer;
    }

    public void setCardAccountNumer(String cardAccountNumer) {
        this.cardAccountNumer = cardAccountNumer;
    }

    @XmlElement(name = "CHANNEL", nillable = false)
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String getValueToBuildMac() {
        if (this.channel != null && "CRM".equals(this.channel)) {
            return this.last4Digits + this.sequence + this.cardType + this.cardAccountNumer + this.getTime();
        } else {
            return this.last4Digits + this.phone + this.sequence + this.cardType + this.cmnd + this.getTime();
        }
    }

}
