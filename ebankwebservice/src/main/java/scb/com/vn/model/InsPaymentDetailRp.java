package scb.com.vn.model;
// Generated Feb 25, 2013 10:12:56 AM by Hibernate Tools 3.2.1.GA

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "INSPAYMENTDETAIL")
public class InsPaymentDetailRp implements java.io.Serializable {

    private int id;
    private int idpay;
    private String term;
    private String col_code;
    private String checksum;
    private String ref_num;
    private Long amount;
    //them cho mobile
    private String prem_typ;

    private String amountpaid;

    /**
     *
     */
    public InsPaymentDetailRp() {
    }

    /**
     *
     * @param id
     * @param idpay
     * @param term
     * @param amount
     * @param col_code
     * @param checksum
     * @param ref_num
     */
    public InsPaymentDetailRp(int id, int idpay, String term, Long amount, String col_code, String checksum, String ref_num) {
        this.id = id;
        this.idpay = idpay;
        this.term = term;
        this.term = col_code;
        this.term = checksum;
        this.amount = amount;
        this.ref_num = ref_num;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "ID", required = false, nillable = true)
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getIdpay() {
        return idpay;
    }

    /**
     *
     * @param idpay
     */
    public void setIdpay(int idpay) {
        this.idpay = idpay;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "TERM", required = false, nillable = true)
    public String getTerm() {
        return term;
    }

    /**
     *
     * @param term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AMOUNT", required = false, nillable = true)
    public Long getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "COL_CODE", required = false, nillable = true)
    public String getCol_code() {
        return col_code;
    }

    /**
     *
     * @param col_code
     */
    public void setCol_code(String col_code) {
        this.col_code = col_code;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CHECKSUM", required = false, nillable = true)
    public String getChecksum() {
        return checksum;
    }

    /**
     *
     * @param checksum
     */
    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "REF_NUM", required = false, nillable = true)
    public String getRef_num() {
        return ref_num;
    }

    /**
     *
     * @param ref_num
     */
    public void setRef_num(String ref_num) {
        this.ref_num = ref_num;
    }

    /**
     * @return the amountpaid
     */
    @XmlElement(name = "AMOUNTPAID", required = false, nillable = true)
    public String getAmountpaid() {
        return amountpaid;
    }

    /**
     * @param amountpaid the amountpaid to set
     */
    public void setAmountpaid(String amountpaid) {
        this.amountpaid = amountpaid;
    }

    /**
     * @return the prem_type
     */
    @XmlElement(name = "PREM_TYPE", required = false, nillable = true)
    public String getPrem_typ() {
        return prem_typ;
    }

    /**
     * @param prem_typ
     */
    public void setPrem_typ(String prem_typ) {
        this.prem_typ = prem_typ;
    }

}
