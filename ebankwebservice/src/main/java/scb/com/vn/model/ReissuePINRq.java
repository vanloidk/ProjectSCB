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
@XmlRootElement(name = "ReissuePINRq")
public class ReissuePINRq extends MobileRequest {

    private String brn;

    private String sourceaccount;
    private String accountno;
    private String amountfee;
    private String amounttax;

    private String fullname;
    private String idnumber;
    private String birthday;
    private String relationship;
    private String cardrecievemode;
    private String branchcode;
    private String address;

    /**
     * @return the sourceaccount
     */
    @XmlElement(name = "FeeAccount", nillable = true)
    public String getSourceaccount() {
        return sourceaccount;
    }

    /**
     * @param sourceaccount the sourceaccount to set
     */
    public void setSourceaccount(String sourceaccount) {
        this.sourceaccount = sourceaccount;
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
     * @return the idnumber
     */
    @XmlElement(name = "IDNumber", nillable = true)
    public String getIdnumber() {
        return idnumber;
    }

    /**
     * @param idnumber the idnumber to set
     */
    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    /**
     * @return the birthday
     */
    @XmlElement(name = "BirthDay", nillable = true)
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the relationship
     */
    @XmlElement(name = "Relationship", nillable = true)
    public String getRelationship() {
        return relationship;
    }

    /**
     * @param relationship the relationship to set
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    /**
     * @return the cardrecievemode
     */
    @XmlElement(name = "CardRecieveMode", nillable = true)
    public String getCardrecievemode() {
        return cardrecievemode;
    }

    /**
     * @param cardrecievemode the cardrecievemode to set
     */
    public void setCardrecievemode(String cardrecievemode) {
        this.cardrecievemode = cardrecievemode;
    }

    /**
     * @return the branchcode
     */
    @XmlElement(name = "BranchCode", nillable = true)
    public String getBranchcode() {
        return branchcode;
    }

    /**
     * @param branchcode the branchcode to set
     */
    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    /**
     * @return the address
     */
    @XmlElement(name = "Address", nillable = true)
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the amountfee
     */
    @XmlElement(name = "FeeAmount", nillable = true)
    public String getAmountfee() {
        return amountfee;
    }

    /**
     * @param amountfee the amountfee to set
     */
    public void setAmountfee(String amountfee) {
        this.amountfee = amountfee;
    }

    /**
     * @return the amounttax
     */
    @XmlElement(name = "FeeTax", nillable = true)
    public String getAmounttax() {
        return amounttax;
    }

    /**
     * @param amounttax the amounttax to set
     */
    public void setAmounttax(String amounttax) {
        this.amounttax = amounttax;
    }

    /**
     * @return the accountno
     */
    @XmlElement(name = "AccountNo", nillable = true)
    public String getAccountno() {
        return accountno;
    }

    /**
     * @param accountno the accountno to set
     */
    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    /**
     * @return the brn
     */
    @XmlElement(name = "CardType", nillable = true)
    public String getBrn() {
        return brn;
    }

    /**
     * @param brn the brn to set
     */
    public void setBrn(String brn) {
        this.brn = brn;
    }
    /**
     * @return the FromAccount
     */
    //@XmlElement(name = "FromAccount", nillable = true)
}
