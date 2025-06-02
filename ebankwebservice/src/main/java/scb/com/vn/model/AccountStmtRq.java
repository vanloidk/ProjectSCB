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
@XmlRootElement(name = "AccountStmtRq")
public class AccountStmtRq {

    private String UserName;
    private String CifNo;
    private String AccountNo;
    private String AccountGroupCode;
    private String FromDate;
    private String ToDate;
    private String srno;
    private String GetAll;

    /**
     *
     */
    public AccountStmtRq() {
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
     * @return the AccountGroupCode
     */
    @XmlElement(name = "AccountGroupCode")
    public String getAccountGroupCode() {
        return AccountGroupCode;
    }

    /**
     * @param AccountGroupCode the AccountGroupCode to set
     */
    public void setAccountGroupCode(String AccountGroupCode) {
        this.AccountGroupCode = AccountGroupCode;
    }

    /**
     * @return the FromDate
     */
    @XmlElement(name = "FromDate")
    public String getFromDate() {
        return FromDate;
    }

    /**
     * @param FromDate the FromDate to set
     */
    public void setFromDate(String FromDate) {
        this.FromDate = FromDate;
    }

    /**
     * @return the ToDate
     */
    @XmlElement(name = "ToDate")
    public String getToDate() {
        return ToDate;
    }

    /**
     * @param ToDate the ToDate to set
     */
    public void setToDate(String ToDate) {
        this.ToDate = ToDate;
    }

    /**
     * @return the srno
     */
    @XmlElement(name = "srno")
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
     * @return the GetAll
     */
    @XmlElement(name = "GetAll")
    public String getGetAll() {
        return GetAll;
    }

    /**
     * @param GetAll the GetAll to set
     */
    public void setGetAll(String GetAll) {
        this.GetAll = GetAll;
    }
}
