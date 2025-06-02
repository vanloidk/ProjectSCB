/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "SearchResgisterCardMBRq")
public class SearchResgisterCardMBRq {

    private String UserName;
    private String CifNo;
    private String registertype;

    /**
     *
     */
    public SearchResgisterCardMBRq() {
    }

    /**
     * @return the UserName
     */
    @XmlElement(name = "UserName")
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
    @XmlElement(name = "CifNo")
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
     * @return the registertype
     */
    @XmlElement(name = "RegisterType")
    public String getRegistertype() {
        return registertype;
    }

    /**
     * @param registertype the registertype to set
     */
    public void setRegistertype(String registertype) {
        this.registertype = registertype;
    }
}
