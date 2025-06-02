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
@XmlRootElement(name = "GetCCStatementRq")
public class GetCCStatementRq {

    private String UserName;
    private String CifNo;
    private String AccountNo;
    private String Period;
    private String srno;
    private String credittype;

    /**
     *
     */
    public GetCCStatementRq() {
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
     * @return the srno
     */
    @XmlElement(name = "SRNO")
    public String getSrno() {
        return srno;
    }

    /**
     * @param srno the srno to set
     */
    public void setSrno(String srno) {
        this.srno = srno;
    }

    /**
     * @return the Period
     */
    @XmlElement(name = "Period")
    public String getPeriod() {
        return Period;
    }

    /**
     * @param Period the Period to set
     */
    public void setPeriod(String Period) {
        this.Period = Period;
    }

    /**
     * @return the AccountNo
     */
    @XmlElement(name = "AccountNo")
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
     * @return the credittype
     */
    @XmlElement(name = "CreditType")
    public String getCredittype() {
        return credittype;
    }

    /**
     * @param credittype the credittype to set
     */
    public void setCredittype(String credittype) {
        this.credittype = credittype;
    }
}
