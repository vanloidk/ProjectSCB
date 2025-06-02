/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lydty
 */

public class HistoryTransferResponse_detail {
 
    private String TRANSNO;
    private String TRANSDATE;
    private String AMOUNT;
    private String TRANSDESC;
    private String DESTACCOUNT;
    
    @XmlElement(name="TRANSNO")
    public String getTRANSNO() {
        return TRANSNO;
    }

    public void setTRANSNO(String TRANSNO) {
        this.TRANSNO = TRANSNO;
    }
    @XmlElement(name="TRANSDATE")
    public String getTRANSDATE() {
        return TRANSDATE;
    }

    public void setTRANSDATE(String TRANSDATE) {
        this.TRANSDATE = TRANSDATE;
    }
    @XmlElement(name="AMOUNT")
    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }
    @XmlElement(name="TRANSDESC")
    public String getTRANSDESC() {
        return TRANSDESC;
    }

    public void setTRANSDESC(String TRANSDESC) {
        this.TRANSDESC = TRANSDESC;
    }
    @XmlElement(name="DESTACCOUNT")
    public String getDESTACCOUNT() {
        return DESTACCOUNT;
    }

    public void setDESTACCOUNT(String DESTACCOUNT) {
        this.DESTACCOUNT = DESTACCOUNT;
    }
    
    
}
