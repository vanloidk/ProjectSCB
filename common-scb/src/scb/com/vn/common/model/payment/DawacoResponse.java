/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scb.com.vn.common.model.payment;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlValue;


/**
 *
 * @author kimncm
 */
 
 @XmlRootElement(name ="DEBT_CHECKResponse")
public class DawacoResponse  {       
       private String result; 
       private DawacoAllBills  allBills; 

    /**
     * @return the result
     */    
      @XmlElement(name = "DEBT_CHECKResult", nillable = true)
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the allBills
     */
   
    public DawacoAllBills getAllBills() {
        return allBills;
    }

    /**
     * @param allBills the allBills to set
     */
    public void setAllBills(DawacoAllBills allBills) {
        this.allBills = allBills;
    }

   
      
}
