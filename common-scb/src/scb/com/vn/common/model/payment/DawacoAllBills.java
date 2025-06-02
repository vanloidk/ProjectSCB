/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimncm
 */

@XmlRootElement(name ="Data")
public class DawacoAllBills {
              
     private List<DawacoBillDetail> billList;                  

    /**
     * @return the billList
     */
     @XmlElement(name = "Item")    
    public List<DawacoBillDetail> getBillList() {
        return billList;
    }

    /**
     * @param billList the billList to set
     */
    public void setBillList(List<DawacoBillDetail> billList) {
        this.billList = billList;
    }
}
