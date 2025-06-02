/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.model.MobileRequest;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "GrantLuckNumberRq")
public class GrantLuckNumberRq extends MobileRequest {

    private String AccountNo;
    private String SrcChannel;
    private String Language;

    /**
     * @return the AccountNo
     */
    @XmlElement(name = "AccountNo", nillable = true)
    public String getAccountNo() {
        return AccountNo;
    }

    /**
     * @param AccountNo the AccountNo to set
     */
    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }

    /**
     * @return the SrcChannel
     */
    @XmlElement(name = "SrcChannel", nillable = true)
    public String getSrcChannel() {
        return SrcChannel;
    }

    /**
     * @param SrcChannel the SrcChannel to set
     */
    public void setSrcChannel(String SrcChannel) {
        this.SrcChannel = SrcChannel;
    }

    /**
     * @return the Language
     */
    @XmlElement(name = "Language", nillable = true)
    public String getLanguage() {
        return Language;
    }

    /**
     * @param Language the Language to set
     */
    public void setLanguage(String Language) {
        this.Language = Language;
    }

}
