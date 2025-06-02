/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lydty
 */

public class HistoryTransferResponse_info {
   List<HistoryTransferResponse_detail> TRANSHISTORY;

   @XmlElement(name="TRANSHISTORYBYDESCRES")
    public List<HistoryTransferResponse_detail> getTRANSHISTORY() {
        return TRANSHISTORY;
    }

    public void setTRANSHISTORY(List<HistoryTransferResponse_detail> TRANSHISTORY) {
        this.TRANSHISTORY = TRANSHISTORY;
    }
    
}
