/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "SearchResgisterCardMBRp")
public class SearchResgisterCardMBRp extends MobileResponse {

    /**
     *
     */
    public SearchResgisterCardMBRp() {
    }
    private String CifNo;
    private String registertype;
    private ResgisterCardMB[] regList;

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
     * @return the regList
     */
    @XmlElement(name = "ResgisterCardMB")
    @XmlElementWrapper(name = "ResgisterCardMBList", nillable = true)
    public ResgisterCardMB[] getRegList() {
        return regList;
    }

    /**
     * @param regList the regList to set
     */
    public void setRegList(ResgisterCardMB[] regList) {
        this.regList = regList;
    }

    /**
     * @return the registertype
     */
    @XmlElement(name = "RegisterType", nillable = true)
    public String getRegistertype() {
        return registertype;
    }

    /**
     * @param registertype the registertype to set
     */
    public void setRegistertype(String registertype) {
        this.registertype = registertype;
    }
    /**
     * @return the txnList
     */
    //
    //@XmlElement(name = "CardAccountNo", nillable = true)
}
