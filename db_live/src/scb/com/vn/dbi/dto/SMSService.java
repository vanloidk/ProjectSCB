/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author baovq
 */
public class SMSService implements java.io.Serializable {

    private String id;
    private String stt;
    private String mobile;
    private String isdeleted;
    private String cust_ac_no;
    private String record_stat;

    /**
     *
     * @return
     */
    public String getIsSelected() {
        return isSelected;
    }

    /**
     *
     * @param isSelected
     */
    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }
    String isSelected;

    /**
     *
     * @return
     */
    public String getStt() {
        return stt;
    }

    /**
     *
     * @param stt
     */
    public void setStt(String stt) {
        this.stt = stt;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getCust_ac_no() {
        return cust_ac_no;
    }

    /**
     *
     * @param cust_ac_no
     */
    public void setCust_ac_no(String cust_ac_no) {
        this.cust_ac_no = cust_ac_no;
    }

    /**
     *
     * @return
     */
    public String getMobile() {
        return mobile;
    }

    /**
     *
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     *
     * @return
     */
    public String getIsdeleted() {
        return isdeleted;
    }

    /**
     *
     * @param isdeleted
     */
    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    /**
     *
     * @return
     */
    public String getRecord_stat() {
        return record_stat;
    }

    /**
     *
     * @param record_stat
     */
    public void setRecord_stat(String record_stat) {
        this.record_stat = record_stat;
    }

}
