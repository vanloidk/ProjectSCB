/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author thachdn
 */
@Entity
public class BCBill implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String polnum;
    private String ownername;
    private String ownerid;
    private String refnum;
    private String premtyp;
    private String duedt;
    private Long amount;
    private Long amountpaid;
    private String checksum;
    private String colcode;
    private String area;
    private String accnum;
    private String auto_db_dt;
    private Date in_date;

    /**
     * Create a new instance of BCBill
     */
    public BCBill() {
    }

    /**
     *
     * @param id
     * @param in_date
     * @param polnum
     * @param ownername
     * @param ownerid
     * @param refnum
     * @param premtyp
     * @param duedt
     * @param amount
     * @param amountpaid
     * @param checksum
     * @param colcode
     * @param area
     * @param accnum
     * @param auto_db_dt
     */
    public BCBill(int id, Date in_date, String polnum, String ownername, String ownerid, String refnum, String premtyp, String duedt, Long amount, Long amountpaid, String checksum, String colcode, String area, String accnum, String auto_db_dt) {
        this.id = id;
        this.polnum = polnum;
        this.ownername = ownername;
        this.ownerid = ownerid;
        this.refnum = refnum;
        this.premtyp = premtyp;
        this.duedt = duedt;
        this.amount = amount;
        this.amountpaid = amountpaid;
        this.checksum = checksum;
        this.colcode = colcode;
        this.area = area;
        this.accnum = accnum;
        this.auto_db_dt = auto_db_dt;
        this.in_date = in_date;
    }

    /**
     *
     * @return
     */
    public Date getIn_date() {
        return in_date;
    }

    /**
     *
     * @param in_date
     */
    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }

    /**
     *
     * @return
     */
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
    public String getPolnum() {
        return polnum;
    }

    /**
     *
     * @param polnum
     */
    public void setPolnum(String polnum) {
        this.polnum = polnum;
    }

    /**
     *
     * @return
     */
    public String getOwnername() {
        return ownername;
    }

    /**
     *
     * @param ownername
     */
    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    /**
     *
     * @return
     */
    public String getOwnerid() {
        return ownerid;
    }

    /**
     *
     * @param ownerid
     */
    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    /**
     *
     * @return
     */
    public String getRefnum() {
        return refnum;
    }

    /**
     *
     * @param refnum
     */
    public void setRefnum(String refnum) {
        this.refnum = refnum;
    }

    /**
     *
     * @return
     */
    public String getPremtyp() {
        return premtyp;
    }

    /**
     *
     * @param premtyp
     */
    public void setPremtyp(String premtyp) {
        this.premtyp = premtyp;
    }

    /**
     *
     * @return
     */
    public String getDuedt() {
        return duedt;
    }

    /**
     *
     * @param duedt
     */
    public void setDuedt(String duedt) {
        this.duedt = duedt;
    }

    /**
     *
     * @return
     */
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
    public Long getAmountpaid() {
        return amountpaid;
    }

    /**
     *
     * @param amountpaid
     */
    public void setAmountpaid(Long amountpaid) {
        this.amountpaid = amountpaid;
    }

    /**
     *
     * @return
     */
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
    public String getColcode() {
        return colcode;
    }

    /**
     *
     * @param colcode
     */
    public void setColcode(String colcode) {
        this.colcode = colcode;
    }

    /**
     *
     * @return
     */
    public String getArea() {
        return area;
    }

    /**
     *
     * @param area
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     *
     * @return
     */
    public String getAccnum() {
        return accnum;
    }

    /**
     *
     * @param accnum
     */
    public void setAccnum(String accnum) {
        this.accnum = accnum;
    }

    /**
     *
     * @return
     */
    public String getAuto_db_dt() {
        return auto_db_dt;
    }

    /**
     *
     * @param auto_db_dt
     */
    public void setAuto_db_dt(String auto_db_dt) {
        this.auto_db_dt = auto_db_dt;
    }

}
