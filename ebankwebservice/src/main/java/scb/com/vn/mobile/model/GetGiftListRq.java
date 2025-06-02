/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "GetGiftListRq")
public class GetGiftListRq extends MobileModelRequest {

    private String AccountNo;
    private String AccountBranchOpen;

    /**
     * @return the AccountNo
     */
    @XmlElement(name = "AccountNo", nillable = true)
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
     * @return the AccountBranchOpen
     */
    @XmlElement(name = "AccountBranchOpen", nillable = true)
    public String getAccountBranchOpen() {
        return AccountBranchOpen;
    }

    /**
     * @param AccountBranchOpen the AccountBranchOpen to set
     */
    public void setAccountBranchOpen(String AccountBranchOpen) {
        this.AccountBranchOpen = AccountBranchOpen;
    }

}
