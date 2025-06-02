/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.bhbl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "HealthCareContractRq")
public class BHBLGetHealthCareContractRq {

    private String idContract;
    
    public BHBLGetHealthCareContractRq() {
    }

    @XmlElement(name = "IdContract", required = true)
    public String getIdContract() {
        return idContract;
    }

    public void setIdContract(String idContract) {
        this.idContract = idContract;
    }
    
}
