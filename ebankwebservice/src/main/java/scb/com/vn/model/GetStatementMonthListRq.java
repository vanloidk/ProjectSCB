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
@XmlRootElement(name = "GetStatementMonthListRq")
public class GetStatementMonthListRq {

    private String _UserName;
    private String _CifNo;
    private String _CreditType;

    /**
     *
     */
    public GetStatementMonthListRq() {
    }

    /**
     * @return the _UserName
     */
    @XmlElement(name = "UserName")
    public String getUserName() {
        return _UserName;
    }

    /**
     * @param UserName the _UserName to set
     */
    public void setUserName(String UserName) {
        this._UserName = UserName;
    }

    /**
     * @return the _CifNo
     */
    @XmlElement(name = "CifNo")
    public String getCifNo() {
        return _CifNo;
    }

    /**
     * @param CifNo the _CifNo to set
     */
    public void setCifNo(String CifNo) {
        this._CifNo = CifNo;
    }

    /**
     * @return the _CreditType
     */
    @XmlElement(name = "CreditType")
    public String getCreditType() {
        return _CreditType;
    }

    /**
     * @param CreditType the _CreditType to set
     */
    public void setCreditType(String CreditType) {
        this._CreditType = CreditType;
    }

}
