/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.model.bhbl;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "UrlOnepayRq")
public class BHBLPaymentHealthCareRq implements Serializable {

    private static final long serialVersionUID = -1L;
    private String order_no;
    private BigDecimal amount;

    @XmlElement(name = "IdContract")
    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    @XmlElement(name = "Amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
