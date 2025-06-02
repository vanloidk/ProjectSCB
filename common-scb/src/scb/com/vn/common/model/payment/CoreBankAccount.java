/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author baovq
 */
@XmlRootElement(name = "COREBANKACCOUNT")
public class CoreBankAccount {
      
    private String makerAccount;
        
    private String checkerAccount;
    
    @XmlElement(name = "makerAccount")
    public String getMakerAccount() {
        return makerAccount;
    }

    @XmlElement(name = "checkerAccount")
    public String getCheckerAccount() {
        return checkerAccount;
    }

    public void setMakerAccount(String makerAccount) {
        this.makerAccount = makerAccount;
    }

    public void setCheckerAccount(String checkerAccount) {
        this.checkerAccount = checkerAccount;
    }
   
      
}
