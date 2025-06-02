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
public class HQ_NOPTIEN_HQ_GNTCT implements java.io.Serializable {

    int IDMASTER;
    String MA_ST;
    String NDKT;
    String SOTIEN_NT;
    String SOTIEN_VND;
    int ID;
    String TEN_NDKT;

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
    public String getMA_ST() {
        return MA_ST;
    }

    /**
     *
     * @param MA_ST
     */
    public void setMA_ST(String MA_ST) {
        this.MA_ST = MA_ST;
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

}
