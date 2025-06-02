/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlList;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "SMSBDSDListRp")
public class SMSBDSDListRp extends MobileResponse {

    private AccountBDSD[] _ListMobileTT;
    private AccountBDSD[] _ListMobileTK;
    private String _listMobileArr;
    private String isDebitFee;
    
    public SMSBDSDListRp() {
    }

    /**
     * @return the _ListMobileTT
     */
     @XmlElement(name = "AccountBDSD")
    @XmlElementWrapper(name = "ListMobileTT", nillable = true)
    public AccountBDSD[] getListMobileTT() {
        return _ListMobileTT;
    }

    /**
     * @param _ListMobileTT the _ListMobileTT to set
     */
    public void setListMobileTT(AccountBDSD[] _ListMobileTT) {
        this._ListMobileTT = _ListMobileTT;
    }

    /**
     * @return the _ListMobileTK
     */
      @XmlElement(name = "AccountBDSD")
    @XmlElementWrapper(name = "ListMobileTK", nillable = true)
    public AccountBDSD[] getListMobileTK() {
        return _ListMobileTK;
    }

    /**
     * @param _ListMobileTK the _ListMobileTK to set
     */
    public void setListMobileTK(AccountBDSD[] _ListMobileTK) {
        this._ListMobileTK = _ListMobileTK;
    }

    /**
     * @return the _listMobileArr
     */
    @XmlElement(name = "ListMobile")
    public String getListMobileArr() {
        return _listMobileArr;
    }

    /**
     * @param _listMobileArr the _listMobileArr to set
     */
    public void setListMobileArr(String _listMobileArr) {
        this._listMobileArr = _listMobileArr;
    }

    /**
     * @return the isDebitFee
     */
    @XmlElement(name = "IsDebitFee")
    public String getIsDebitFee() {
        return isDebitFee;
    }

    /**
     * @param isDebitFee the isDebitFee to set
     */
    public void setIsDebitFee(String isDebitFee) {
        this.isDebitFee = isDebitFee;
    }



  

       
  
}
