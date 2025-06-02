/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.math.BigDecimal;

/**
 *
 * @author baovq
 */
public class SMSServiceTK implements java.io.Serializable {

    private String id;
    private String mobile;
    private String ref_thu;
    private String ref_chi;
    private String tk_momoi;
    private String tk_denhan;
    private String ref_status;
    private String cust_ac_no;
    private String point_reward;
    private String auto_poi_rew;
    private String ma_nv_tk;

    private BigDecimal amount;

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
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     */
    public String getTk_denhan() {
        return tk_denhan;
    }

    /**
     *
     * @param tk_denhan
     */
    public void setTk_denhan(String tk_denhan) {
        this.tk_denhan = tk_denhan;
    }

    /**
     *
     * @return
     */
    public String getTk_momoi() {
        return tk_momoi;
    }

    /**
     *
     * @param tk_momoi
     */
    public void setTk_momoi(String tk_momoi) {
        this.tk_momoi = tk_momoi;
    }

    /**
     *
     * @return
     */
    public String getPoint_reward() {
        return point_reward;
    }

    /**
     *
     * @param point_reward
     */
    public void setPoint_reward(String point_reward) {
        this.point_reward = point_reward;
    }

    /**
     *
     * @return
     */
    public String getAuto_poi_rew() {
        return auto_poi_rew;
    }

    /**
     *
     * @param auto_poi_rew
     */
    public void setAuto_poi_rew(String auto_poi_rew) {
        this.auto_poi_rew = auto_poi_rew;
    }

    /**
     *
     * @return
     */
    public String getRef_thu() {
        return ref_thu;
    }

    /**
     *
     * @param ref_thu
     */
    public void setRef_thu(String ref_thu) {
        this.ref_thu = ref_thu;
    }

    /**
     *
     * @return
     */
    public String getRef_chi() {
        return ref_chi;
    }

    /**
     *
     * @param ref_chi
     */
    public void setRef_chi(String ref_chi) {
        this.ref_chi = ref_chi;
    }

    /**
     *
     * @return
     */
    public String getRef_status() {
        return ref_status;
    }

    /**
     *
     * @param ref_status
     */
    public void setRef_status(String ref_status) {
        this.ref_status = ref_status;
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
    public String getMa_nv_tk() {
        return ma_nv_tk;
    }

    /**
     *
     * @param ma_nv_tk
     */
    public void setMa_nv_tk(String ma_nv_tk) {
        this.ma_nv_tk = ma_nv_tk;
    }

}
