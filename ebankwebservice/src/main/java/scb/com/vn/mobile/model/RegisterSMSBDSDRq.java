/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.model.MobileRequest;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "RegisterSMSBDSDRq")
public class RegisterSMSBDSDRq extends MobileRequest {

    private String AccountType; //'TT'  TK THANH TOAN ; 'TK'  TK TIET KIEM
    //private String CustAcNo;
    //private String ListMobile;
    private String MobileNo;
    private String ListCustAcNo;
    private String ActionType;
    private String CifNo;
    
    @XmlElement(name = "ActionType", nillable = true)
    public String getActionType() {
        return ActionType;
    }

    public void setActionType(String ActionType) {
        this.ActionType = ActionType;
    }
    
    
    
    /**
     * @return the AccountType
     */
    @XmlElement(name = "AccountType", nillable = true)
    public String getAccountType() {
        return AccountType;
    }

    /**
     * @param AccountType the AccountType to set
     */
    public void setAccountType(String AccountType) {
        this.AccountType = AccountType;
    }
    @XmlElement(name = "MobileNo", nillable = true)
    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }
    @XmlElement(name = "ListCustAcNo", nillable = true)
    public String getListCustAcNo() {
        return ListCustAcNo;
    }

    public void setListCustAcNo(String ListCustAcNo) {
        this.ListCustAcNo = ListCustAcNo;
    }
    @XmlElement(name = "CifNo", nillable = true)
    public String getCifNo() {
        return CifNo;
    }

    public void setCifNo(String CifNo) {
        this.CifNo = CifNo;
    }

    

   

   

}
