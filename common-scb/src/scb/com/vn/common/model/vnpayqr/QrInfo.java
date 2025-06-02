/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.vnpayqr;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.ultility.Common;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "ItemQR")
public class QrInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String quantity;
    private String note;
    private String qrInfor;

    @XmlElement(name = "Quantity", nillable = false)
    public String getQuantity() {
        return quantity;
    }

    @XmlElement(name = "Note", nillable = true)
    public String getNote() {
        return note;
    }

    @XmlElement(name = "QrInfo", nillable = false)
    public String getQrInfor() {
        return qrInfor;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setQrInfor(String qrInfor) {
        this.qrInfor = qrInfor;
    }
    
    public boolean isValidate()
    {
        return Common.validateStringValue(quantity) && Common.validateStringValue(qrInfor);
    }
}