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
@XmlRootElement(name = "UnLockFeeMobileBankingOfSCBRq")
public class UnLockFeeMobileBankingOfSCBRq {

    private String UserName;
    private String CifNo;
    private String UserLock;

    /**
     *
     */
    public UnLockFeeMobileBankingOfSCBRq() {
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
     * @return the UserLock
     */
    @XmlElement(name = "UserLock")
    public String getUserLock() {
        return UserLock;
    }

    /**
     * @param UserLock the UserLock to set
     */
    public void setUserLock(String UserLock) {
        this.UserLock = UserLock;
    }
}
