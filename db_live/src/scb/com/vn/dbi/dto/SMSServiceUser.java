/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.util.List;

/**
 *
 * @author baovq
 */
public class SMSServiceUser implements java.io.Serializable {

    String id;
    String userid;
    String branchid;
    SMSServiceTK smstk;
    List<SMSService> listSMS;
    private String idcard;
    private String cust_no;
    private String address;
    private String fullname;
    private String idcard_loc;
    private String idcard_dob;
    private String ma_nv_tt;

    /**
     *
     * @return
     */
    public SMSServiceTK getSmstk() {
        return smstk;
    }

    /**
     *
     * @param smstk
     */
    public void setSmstk(SMSServiceTK smstk) {
        this.smstk = smstk;
    }

    /**
     *
     * @return
     */
    public String getCust_no() {
        return cust_no;
    }

    /**
     *
     * @param cust_no
     */
    public void setCust_no(String cust_no) {
        this.cust_no = cust_no;
    }

    /**
     *
     * @return
     */
    public String getFullname() {
        return fullname;
    }

    /**
     *
     * @param fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     *
     * @param idcard
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     *
     * @return
     */
    public String getIdcard_loc() {
        return idcard_loc;
    }

    /**
     *
     * @param idcard_loc
     */
    public void setIdcard_loc(String idcard_loc) {
        this.idcard_loc = idcard_loc;
    }

    /**
     *
     * @return
     */
    public String getIdcard_dob() {
        return idcard_dob;
    }

    /**
     *
     * @param idcard_dob
     */
    public void setIdcard_dob(String idcard_dob) {
        this.idcard_dob = idcard_dob;
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
    public String getUserid() {
        return userid;
    }

    /**
     *
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     *
     * @return
     */
    public String getBranchid() {
        return branchid;
    }

    /**
     *
     * @param branchid
     */
    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    /**
     *
     * @return
     */
    public List<SMSService> getListSMS() {
        return listSMS;
    }

    /**
     *
     * @param listSMS
     */
    public void setListSMS(List<SMSService> listSMS) {
        this.listSMS = listSMS;
    }

    /**
     *
     * @return
     */
    public String getMa_nv_tt() {
        return ma_nv_tt;
    }

    /**
     *
     * @param ma_nv_tt
     */
    public void setMa_nv_tt(String ma_nv_tt) {
        this.ma_nv_tt = ma_nv_tt;
    }

}
