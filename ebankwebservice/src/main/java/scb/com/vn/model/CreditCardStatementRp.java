/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "CreditCardStatementRp")
public class CreditCardStatementRp extends MobileResponse {

    private String cardaccountno;
    private String statementdate;
    private String openingbalance;
    private String closingbalance;
    private String amtlimitpay;
    private String matdatecreditcard;
    private String limitcreditcard;
    private String statementfromdate;
    private String cardtype;
    private String atmpayipp;
    private String totalpayamt;
    private String period;
    private byte[] pdffile;

    //  
    /**
     * @return the statementdate
     */
    @XmlElement(name = "StatementDate", nillable = true)
    public String getStatementdate() {
        return statementdate;
    }

    /**
     * @param statementdate the statementdate to set
     */
    public void setStatementdate(String statementdate) {
        this.statementdate = statementdate;
    }

    /**
     * @return the openingbalance
     */
    @XmlElement(name = "OpeningBalance", nillable = true)
    public String getOpeningbalance() {
        return openingbalance;
    }

    /**
     * @param openingbalance the openingbalance to set
     */
    public void setOpeningbalance(String openingbalance) {
        this.openingbalance = openingbalance;
    }

    /**
     * @return the closingbalance
     */
    @XmlElement(name = "ClosingBalance", nillable = true)
    public String getClosingbalance() {
        return closingbalance;
    }

    /**
     * @param closingbalance the closingbalance to set
     */
    public void setClosingbalance(String closingbalance) {
        this.closingbalance = closingbalance;
    }

    /**
     * @return the amtlimitpay
     */
    @XmlElement(name = "AmtLimitPay", nillable = true)
    public String getAmtlimitpay() {
        return amtlimitpay;
    }

    /**
     * @param amtlimitpay the amtlimitpay to set
     */
    public void setAmtlimitpay(String amtlimitpay) {
        this.amtlimitpay = amtlimitpay;
    }

    /**
     * @return the matdatecreditcard
     */
    @XmlElement(name = "MatDateCreditCard", nillable = true)
    public String getMatdatecreditcard() {
        return matdatecreditcard;
    }

    /**
     * @param matdatecreditcard the matdatecreditcard to set
     */
    public void setMatdatecreditcard(String matdatecreditcard) {
        this.matdatecreditcard = matdatecreditcard;
    }

    /**
     * @return the cardaccountno
     */
    @XmlElement(name = "CardAccountNo", nillable = true)
    public String getCardaccountno() {
        return cardaccountno;
    }

    /**
     * @param cardaccountno the cardaccountno to set
     */
    public void setCardaccountno(String cardaccountno) {
        this.cardaccountno = cardaccountno;
    }

    /**
     * @return the limitcreditcard
     */
    @XmlElement(name = "LimitCreditCard", nillable = true)
    public String getLimitcreditcard() {
        return limitcreditcard;
    }

    /**
     * @param limitcreditcard the limitcreditcard to set
     */
    public void setLimitcreditcard(String limitcreditcard) {
        this.limitcreditcard = limitcreditcard;
    }

    /**
     * @return the statementfromdate
     */
    @XmlElement(name = "StatementFromDate", nillable = true)
    public String getStatementfromdate() {
        return statementfromdate;
    }

    /**
     * @param statementfromdate the statementfromdate to set
     */
    public void setStatementfromdate(String statementfromdate) {
        this.statementfromdate = statementfromdate;
    }

    /**
     * @return the cardtype
     */
    @XmlElement(name = "CardType", nillable = true)
    public String getCardtype() {
        return cardtype;
    }

    /**
     * @param cardtype the cardtype to set
     */
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    /**
     * @return the atmpayipp
     */
    @XmlElement(name = "CardIPPStatementAmount", nillable = true)
    public String getAtmpayipp() {
        return atmpayipp;
    }

    /**
     * @param atmpayipp the atmpayipp to set
     */
    public void setAtmpayipp(String atmpayipp) {
        this.atmpayipp = atmpayipp;
    }

    /**
     * @return the totalpayamt
     */
    @XmlElement(name = "SumAmtLimitPay", nillable = true)
    public String getTotalpayamt() {
        return totalpayamt;
    }

    /**
     * @param totalpayamt the totalpayamt to set
     */
    public void setTotalpayamt(String totalpayamt) {
        this.totalpayamt = totalpayamt;
    }

    /**
     * @return the period
     */
    @XmlElement(name = "Period", nillable = true)
    public String getPeriod() {
        return period;
    }

    /**
     * @param period the period to set
     */
    
    public void setPeriod(String period) {
        this.period = period;
    }
    @XmlElement(name = "pdffile", nillable = true)
    public byte[] getPdffile() {
        return pdffile;
    }

    public void setPdffile(byte[] pdffile) {
        this.pdffile = pdffile;
    }
    
}
