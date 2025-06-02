/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author CORE77
 */
public class PayooCollate {

    String Trace_No;
    String Order_No;
    BigDecimal Amount;
    Date PartnerDatetime;
    Date PayooDatetime;
    String TypeOfTrans;
    String Status;
    String Note;
    String CheckSum;

    /**
     *
     * @return
     */
    public String getTrace_No() {
        return Trace_No;
    }

    /**
     *
     * @param Trace_No
     */
    public void setTrace_No(String Trace_No) {
        this.Trace_No = Trace_No;
    }

    /**
     *
     * @return
     */
    public String getOrder_No() {
        return Order_No;
    }

    /**
     *
     * @param Order_No
     */
    public void setOrder_No(String Order_No) {
        this.Order_No = Order_No;
    }

    /**
     *
     * @return
     */
    public BigDecimal getAmount() {
        return Amount;
    }

    /**
     *
     * @param Amount
     */
    public void setOrder_No(BigDecimal Amount) {
        this.Amount = Amount;
    }

    /**
     *
     * @return
     */
    public Date getPayooDatetime() {
        return PayooDatetime;
    }

    /**
     *
     * @param PayooDatetime
     */
    public void setPayooDatetime(Date PayooDatetime) {
        this.PayooDatetime = PayooDatetime;
    }

    /**
     *
     * @return
     */
    public Date getPartnerDatetime() {
        return PartnerDatetime;
    }

    /**
     *
     * @param PartnerDatetime
     */
    public void setPartnerDatetime(Date PartnerDatetime) {
        this.PartnerDatetime = PartnerDatetime;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return Status;
    }

    /**
     *
     * @param Status
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     *
     * @return
     */
    public String getNote() {
        return Note;
    }

    /**
     *
     * @param Note
     */
    public void setNote(String Note) {
        this.Note = Note;
    }

    /**
     *
     * @return
     */
    public String getCheckSum() {
        return CheckSum;
    }

    /**
     *
     * @param CheckSum
     */
    public void setCheckSum(String CheckSum) {
        this.CheckSum = CheckSum;
    }
}
