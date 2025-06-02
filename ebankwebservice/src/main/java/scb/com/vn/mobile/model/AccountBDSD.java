/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author KimNCM
 */
public class AccountBDSD {     
    
    private String MobileNo;
    private String ListCustAcNo;
     
    public AccountBDSD() {
    }
    
    /**
     * @return the MobileNo
     */
      @XmlElement(name = "MobileNo")
    public String getMobileNo() {
        return MobileNo;
    }

    /**
     * @param MobileNo the MobileNo to set
     */
    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }

    /**
     * @return the ListCustAcNo
     */
      @XmlElement(name = "ListCustAcNo")
    public String getListCustAcNo() {
        return ListCustAcNo;
    }

    /**
     * @param ListCustAcNo the ListCustAcNo to set
     */
    public void setListCustAcNo(String ListCustAcNo) {
        this.ListCustAcNo = ListCustAcNo;
    }

 

   
         
}
