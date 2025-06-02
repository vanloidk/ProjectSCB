/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Administrator
 */
public class ExchangeRateDetail {

    private String name;
    private String cashbuying;
    private String transferbuying;
    private String transferselling;
    private String ordernumber;
    private String note;

    /**
     * @return the name
     */
    @XmlElement(name = "ExchangeCode")
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the cashbuying
     */
    @XmlElement(name = "CashBuying")
    public String getCashbuying() {
        return cashbuying;
    }

    /**
     * @param cashbuying the cashbuying to set
     */
    public void setCashbuying(String cashbuying) {
        this.cashbuying = cashbuying;
    }

    /**
     * @return the transferbuying
     */
    @XmlElement(name = "TransferBuying")
    public String getTransferbuying() {
        return transferbuying;
    }

    /**
     * @param transferbuying the transferbuying to set
     */
    public void setTransferbuying(String transferbuying) {
        this.transferbuying = transferbuying;
    }

    /**
     * @return the transferselling
     */
    @XmlElement(name = "TransferSelling")
    public String getTransferselling() {
        return transferselling;
    }

    /**
     * @param transferselling the transferselling to set
     */
    public void setTransferselling(String transferselling) {
        this.transferselling = transferselling;
    }

    /**
     * @return the ordernumber
     */
    @XmlElement(name = "DateEffect")
    public String getOrdernumber() {
        return ordernumber;
    }

    /**
     * @param ordernumber the ordernumber to set
     */
    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    /**
     * @return the note
     */
    @XmlElement(name = "Effective", nillable = true)
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

}
