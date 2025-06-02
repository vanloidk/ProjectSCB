/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

/**
 *
 * @author lydty
 */
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import vn.com.scb.bian.HistoryTransfer_fundTransferInfo;

@XmlRootElement(name="TRANSHISTORYBYDESCRES")
public class HistoryTransferResponse {
 private String TRANSID   ;
 private String PARTNER;
 private String RESPONSECODE;
 private String RESPONSEDESC;
 private HistoryTransferResponse_info TRANSHISTORY;

    @XmlElement(name="TRANSNO")
    public String getTRANSID() {
        return TRANSID;
    }

    public void setTRANSID(String TRANSID) {
        this.TRANSID = TRANSID;
    }
    @XmlElement(name="PARTNER")
    public String getPARTNER() {
        return PARTNER;
    }

    public void setPARTNER(String PARTNER) {
        this.PARTNER = PARTNER;
    }
    @XmlElement(name="RESPONSECODE")
    public String getRESPONSECODE() {
        return RESPONSECODE;
    }

    public void setRESPONSECODE(String RESPONSECODE) {
        this.RESPONSECODE = RESPONSECODE;
    }
    @XmlElement(name="RESPONSEDESC")
    public String getRESPONSEDESC() {
        return RESPONSEDESC;
    }

    public void setRESPONSEDESC(String RESPONSEDESC) {
        this.RESPONSEDESC = RESPONSEDESC;
    }
    @XmlElement(name="TRANSHISTORY")
    public HistoryTransferResponse_info getTRANSHISTORY() {
        return TRANSHISTORY;
    }

    public void setTRANSHISTORY(HistoryTransferResponse_info TRANSHISTORY) {
        this.TRANSHISTORY = TRANSHISTORY;
    }
    
    
 
}
