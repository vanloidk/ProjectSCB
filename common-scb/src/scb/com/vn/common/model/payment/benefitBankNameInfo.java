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
@XmlRootElement(name = "benefitBankNameInfo")
public class benefitBankNameInfo {
    private String narativeInfo01;
    
    @XmlElement(name = "narativeInfo01")
    public String getNarativeInfo01() {
        return narativeInfo01;
    }

    public void setNarativeInfo01(String narativeInfo01) {
        this.narativeInfo01 = narativeInfo01;
    }
        
}
