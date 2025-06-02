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
@XmlRootElement(name = "GetAccountTypeAzInfoRp")
public class GetAccountTypeAzInfoRp extends MobileResponse {

    private String accounttypecode;
    private String ccy;
    private String opendate;
    private String matdate;
    private String interest;

    /**
     * @return the accounttypecode
     */
    @XmlElement(name = "AccountTypeCode", nillable = true)
    public String getAccounttypecode() {
        return accounttypecode;
    }

    /**
     * @param accounttypecode the accounttypecode to set
     */
    public void setAccounttypecode(String accounttypecode) {
        this.accounttypecode = accounttypecode;
    }

    /**
     * @return the ccy
     */
    @XmlElement(name = "Ccy", nillable = true)
    public String getCcy() {
        return ccy;
    }

    /**
     * @param ccy the ccy to set
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * @return the opendate
     */
    @XmlElement(name = "OpenDate", nillable = true)
    public String getOpendate() {
        return opendate;
    }

    /**
     * @param opendate the opendate to set
     */
    public void setOpendate(String opendate) {
        this.opendate = opendate;
    }

    /**
     * @return the matdate
     */
    @XmlElement(name = "MatDate", nillable = true)
    public String getMatdate() {
        return matdate;
    }

    /**
     * @param matdate the matdate to set
     */
    public void setMatdate(String matdate) {
        this.matdate = matdate;
    }

    /**
     * @return the interest
     */
    @XmlElement(name = "Interest", nillable = true)
    public String getInterest() {
        return interest;
    }

    /**
     * @param interest the interest to set
     */
    public void setInterest(String interest) {
        this.interest = interest;
    }

}
