/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "SendSMSToMBRq")
public class SendSMSToMBRq {

    private String UserName;
    private String CifNo;
    private String MobileNo;
    private String SMSContent;

    /**
     * @return the UserName
     */
    @XmlElement(name = "UserName", nillable = true)
    public String getUserName() {
        return UserName;
    }

    /**
     * @param UserName the UserName to set
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     * @return the CifNo
     */
    @XmlElement(name = "CifNo", nillable = true)
    public String getCifNo() {
        return CifNo;
    }

    /**
     * @param CifNo the CifNo to set
     */
    public void setCifNo(String CifNo) {
        this.CifNo = CifNo;
    }

    /**
     * @return the MobileNo
     */
    @XmlElement(name = "MobileNo", nillable = true)
    public String getMobileNo() {
        return MobileNo;
    }

    /**
     * @param MobileNo the MobileNo to set
     */
    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }

    /**
     * @return the SMSContent
     */
    @XmlElement(name = "SMSContent", nillable = true)
    public String getSMSContent() {
        return SMSContent;
    }

    /**
     * @param SMSContent the SMSContent to set
     */
    public void setSMSContent(String SMSContent) {
        this.SMSContent = SMSContent;
    }
}
