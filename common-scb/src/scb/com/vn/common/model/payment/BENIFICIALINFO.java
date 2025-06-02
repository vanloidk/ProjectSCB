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
@XmlRootElement(name = "BENIFICIALINFO")
public class BENIFICIALINFO {
    private String benifitCustomerName;
    
    private String benifitCustomerAddress;
    
    private IDInfo idinfo;
    
    private benefitBankNameInfo benefitbanknameinfo;

    @XmlElement(name = "benifitCustomerName")
    public String getBenifitCustomerName() {
        return benifitCustomerName;
    }

    @XmlElement(name = "benifitCustomerAddress")
    public String getBenifitCustomerAddress() {
        return benifitCustomerAddress;
    }

    @XmlElement(name = "IDINFO")
    public IDInfo getIdinfo() {
        return idinfo;
    }

    @XmlElement(name = "benefitBankNameInfo")
    public benefitBankNameInfo getBenefitbanknameinfo() {
        return benefitbanknameinfo;
    }
    
    public void setBenifitCustomerName(String benifitCustomerName) {
        this.benifitCustomerName = benifitCustomerName;
    }

    public void setBenifitCustomerAddress(String benifitCustomerAddress) {
        this.benifitCustomerAddress = benifitCustomerAddress;
    }

    public void setIdinfo(IDInfo idinfo) {
        this.idinfo = idinfo;
    }

    public void setBenefitbanknameinfo(benefitBankNameInfo benefitbanknameinfo) {
        this.benefitbanknameinfo = benefitbanknameinfo;
    }
       
    
}
