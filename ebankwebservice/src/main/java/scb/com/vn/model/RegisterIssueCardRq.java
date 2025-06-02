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
@XmlRootElement(name = "RegisterIssueCardRq")
public class RegisterIssueCardRq {

    private String email;
    private String fullname;
    private String existscb;
    private String existotherbank;
    private String incomegreaterfivem;
    private String telCus;

    /**
     *
     */
    public RegisterIssueCardRq() {
    }

    /**
     * @return the email
     */
    @XmlElement(name = "Email", nillable = true)
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fullname
     */
    @XmlElement(name = "FullName", nillable = true)
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the existscb
     */
    @XmlElement(name = "ExistScb", nillable = true)
    public String getExistscb() {
        return existscb;
    }

    /**
     * @param existscb the existscb to set
     */
    public void setExistscb(String existscb) {
        this.existscb = existscb;
    }

    /**
     * @return the existotherbank
     */
    @XmlElement(name = "ExistOtherBank", nillable = true)
    public String getExistotherbank() {
        return existotherbank;
    }

    /**
     * @param existotherbank the existotherbank to set
     */
    public void setExistotherbank(String existotherbank) {
        this.existotherbank = existotherbank;
    }

    /**
     * @return the incomegreaterfivem
     */
    @XmlElement(name = "IncomeGreaterFiveM", nillable = true)
    public String getIncomegreaterfivem() {
        return incomegreaterfivem;
    }

    /**
     * @param incomegreaterfivem the incomegreaterfivem to set
     */
    public void setIncomegreaterfivem(String incomegreaterfivem) {
        this.incomegreaterfivem = incomegreaterfivem;
    }

    /**
     * @return the telCus
     */
    @XmlElement(name = "TelCus", nillable = true)
    public String getTelCus() {
        return telCus;
    }

    /**
     * @param telCus the telCus to set
     */
    public void setTelCus(String telCus) {
        this.telCus = telCus;
    }

}
