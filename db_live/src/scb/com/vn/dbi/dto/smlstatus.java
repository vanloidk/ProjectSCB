/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author thachdn
 */
public class smlstatus implements java.io.Serializable {

    private String status;
    private String descr;

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public String getDescr() {
        return descr;
    }

    /**
     *
     * @param descr
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

}
