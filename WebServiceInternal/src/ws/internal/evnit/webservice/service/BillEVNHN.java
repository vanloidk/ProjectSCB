/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.internal.evnit.webservice.service;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thachdn
 */
@XmlRootElement(name = "BillEVNHN")
public class BillEVNHN {
    private ArrayList<Bill> Bill;

    @XmlElement(name = "Bill")
    public ArrayList<Bill> getBill() {
        return Bill;
    }

    public void setBill(ArrayList<Bill> Bill) {
        this.Bill = Bill;
    }
    
    
}
