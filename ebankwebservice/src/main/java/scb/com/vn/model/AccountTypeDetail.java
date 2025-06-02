/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Administrator
 */
public class AccountTypeDetail {

    private String accounttypecode;
    private String accounttypename;
    //private String Ccy;
    private String Matdate;
    private String Interest;

    /**
     * @return the AccountTypeCode
     */
    @XmlElement(name = "AccountTypeCode")
    public String getAccounttypecode() {
        return accounttypecode;
    }

    /**
     * @param accounttypecode
     */
    public void setAccounttypecode(String accounttypecode) {
        this.accounttypecode = accounttypecode;
    }

    /**
     * @return the AccountTypeName
     */
    @XmlElement(name = "AccountTypeName")
    public String getAccounttypename() {
        return accounttypename;
    }

    /**
     * @param accounttypename
     */
    public void setAccounttypename(String accounttypename) {
        this.accounttypename = accounttypename;
    }

    /**
     * @return the MatDate
     */
    @XmlElement(name = "MatDate")
    public String getMatdate() {
        return Matdate;
    }

    /**
     * @param Matdate
     */
    public void setMatdate(String Matdate) {
        this.Matdate = Matdate;
    }

    /**
     * @return the Interest
     */
    @XmlElement(name = "Interest")
    public String getInterest() {
        return Interest;
    }

    /**
     * @param Interest the Interest to set
     */
    public void setInterest(String Interest) {
        this.Interest = Interest;
    }
}
