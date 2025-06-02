/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author lydty
 */
public class HQ_DKNNT_LIENHE implements java.io.Serializable {

    String SO_DT;
    String EMAIL;

    /**
     *
     * @return
     */
    public String getID() {
        return ID;
    }

    /**
     *
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    String ID;

    /**
     *
     * @return
     */
    public String getSO_DT() {
        return SO_DT;
    }

    /**
     *
     * @param SO_DT
     */
    public void setSO_DT(String SO_DT) {
        this.SO_DT = SO_DT;
    }

    /**
     *
     * @return
     */
    public String getEMAIL() {
        return EMAIL;
    }

    /**
     *
     * @param EMAIL
     */
    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }
}
