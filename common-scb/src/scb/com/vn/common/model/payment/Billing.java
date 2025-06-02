/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author FICOMBANK
 */
public class Billing {

    private String customercode;
    private String currentdebit;
    private String customername = "";
    private String address = "";
    private String amount;
    private String addinfo;

    @XmlElement(name = "CUSTOMERCODE", required = false, nillable = true)
    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    @XmlElement(name = "CURRENTDEBIT", required = false, nillable = true)
    public String getCurrentdebit() {
        return currentdebit;
    }

    public void setCurrentdebit(String currentdebit) {
        this.currentdebit = currentdebit;
    }

    @XmlElement(name = "CUSTOMERNAME", required = false, nillable = true)
    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    @XmlElement(name = "ADDRESS", required = false, nillable = true)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlElement(name = "AMOUNT", required = false, nillable = true)
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the addinfo
     */
    @XmlElement(name = "ADDINFO", required = false, nillable = true)
    public String getAddinfo() {
        return addinfo;
    }

    /**
     * @param addinfo the addinfo to set
     */
    public void setAddinfo(String addinfo) {
        this.addinfo = addinfo;
    }
}
