/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thachdn
 */
public class BillEVNSPC_table1 {
    private String BCS;
    private int CHISO_CU;
    private int CHISO_MOI;

    @XmlElement(name = "BCS")
    public String getBCS() {
        return BCS;
    }

    public void setBCS(String BCS) {
        this.BCS = BCS;
    }

    @XmlElement(name = "CHISO_CU")
    public int getCHISO_CU() {
        return CHISO_CU;
    }

    public void setCHISO_CU(int CHISO_CU) {
        this.CHISO_CU = CHISO_CU;
    }

    @XmlElement(name = "CHISO_MOI")
    public int getCHISO_MOI() {
        return CHISO_MOI;
    }

    public void setCHISO_MOI(int CHISO_MOI) {
        this.CHISO_MOI = CHISO_MOI;
    }

    
    
}
