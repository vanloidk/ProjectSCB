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
@XmlRootElement(name = "IDINFO")
public class IDInfo {            
    private String IDNum;
    
    private String IDIssuedDate;
    
    private String IDIssuedLocation;

    @XmlElement(name = "IDNum")
    public String getIDNum() {
        return IDNum;
    }

    @XmlElement(name = "IDIssuedDate")
    public String getIDIssuedDate() {
        return IDIssuedDate;
    }

    @XmlElement(name = "IDIssuedLocation")
    public String getIDIssuedLocation() {
        return IDIssuedLocation;
    }
    
    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
    }   

    public void setIDIssuedDate(String IDIssuedDate) {
        this.IDIssuedDate = IDIssuedDate;
    }

    public void setIDIssuedLocation(String IDIssuedLocation) {
        this.IDIssuedLocation = IDIssuedLocation;
    }
    
    
    
    
    
}
