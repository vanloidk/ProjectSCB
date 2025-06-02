/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cw;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.MobileResponse;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "CARDADJUSTMENTRES")
public class CardAdjustmentRes extends MobileResponse implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String sequenceNo;
    private String refNo;

    @XmlElement(name = "SEQUENCENO")
    public String getSequenceNo() {
        return sequenceNo;
    }

    @XmlElement(name = "REFNO")
    public String getRefNo() {
        return refNo;
    }

    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }
}
