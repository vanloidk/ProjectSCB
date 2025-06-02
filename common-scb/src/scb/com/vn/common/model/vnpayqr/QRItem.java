/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.vnpayqr;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "QRItem")
public class QRItem implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private QrInfo[] qrItem;

    @XmlElement(name = "QrItem", nillable = false)
    public QrInfo[] getQrItem() {
        return qrItem;
    }

    public void setQrItem(QrInfo[] qrItem) {
        this.qrItem = qrItem;
    }
}