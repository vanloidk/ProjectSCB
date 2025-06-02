/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author lydty
 */
public class HQ_NOPTIEN_CQQLT_CT implements java.io.Serializable {

    String STT;
    String NDKT;
    String TEN_NDKT;
    String SOTIEN_NT;
    String SOTIEN_VND;

    /**
     *
     * @return
     */
    public String getSOTIEN_NT() {
        return SOTIEN_NT;
    }

    /**
     *
     * @param SOTIEN_NT
     */
    public void setSOTIEN_NT(String SOTIEN_NT) {
        this.SOTIEN_NT = SOTIEN_NT;
    }

    /**
     *
     * @return
     */
    public String getSOTIEN_VND() {
        return SOTIEN_VND;
    }

    /**
     *
     * @param SOTIEN_VND
     */
    public void setSOTIEN_VND(String SOTIEN_VND) {
        this.SOTIEN_VND = SOTIEN_VND;
    }
    String GHICHU;
    int IDMASTER;
    int ID;

    /**
     *
     * @return
     */
    public String getSTT() {
        return STT;
    }

    /**
     *
     * @param STT
     */
    public void setSTT(String STT) {
        this.STT = STT;
    }

    /**
     *
     * @return
     */
    public String getNDKT() {
        return NDKT;
    }

    /**
     *
     * @param NDKT
     */
    public void setNDKT(String NDKT) {
        this.NDKT = NDKT;
    }

    /**
     *
     * @return
     */
    public String getTEN_NDKT() {
        return TEN_NDKT;
    }

    /**
     *
     * @param TEN_NDKT
     */
    public void setTEN_NDKT(String TEN_NDKT) {
        this.TEN_NDKT = TEN_NDKT;
    }

    /**
     *
     * @return
     */
    public String getGHICHU() {
        return GHICHU;
    }

    /**
     *
     * @param GHICHU
     */
    public void setGHICHU(String GHICHU) {
        this.GHICHU = GHICHU;
    }

    /**
     *
     * @return
     */
    public int getIDMASTER() {
        return IDMASTER;
    }

    /**
     *
     * @param IDMASTER
     */
    public void setIDMASTER(int IDMASTER) {
        this.IDMASTER = IDMASTER;
    }

    /**
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     *
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

}
