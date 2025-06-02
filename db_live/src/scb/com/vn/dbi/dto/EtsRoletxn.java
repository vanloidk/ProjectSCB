package scb.com.vn.dbi.dto;

import java.util.ArrayList;

/**
 *
 * @author system
 */
public class EtsRoletxn implements java.io.Serializable {

    private static final long serialVersionUID = 6895854396503066429L;

    private String idrole;
    private String idtxn;
    private String flginit;
    private String flgauth;
    private String flgview;

    private ArrayList<EtsMsttxn> listMsttxn;

    /**
     * Create a new instance of EtsRoletxn
     */
    public EtsRoletxn() {
    }

    /**
     *
     * @return
     */
    public String getIdrole() {
        return idrole;
    }

    /**
     *
     * @param idrole
     */
    public void setIdrole(String idrole) {
        this.idrole = idrole;
    }

    /**
     *
     * @return
     */
    public String getIdtxn() {
        return idtxn;
    }

    /**
     *
     * @param idtxn
     */
    public void setIdtxn(String idtxn) {
        this.idtxn = idtxn;
    }

    /**
     *
     * @return
     */
    public String getFlginit() {
        return flginit;
    }

    /**
     *
     * @param flginit
     */
    public void setFlginit(String flginit) {
        this.flginit = flginit;
    }

    /**
     *
     * @return
     */
    public String getFlgauth() {
        return flgauth;
    }

    /**
     *
     * @param flgauth
     */
    public void setFlgauth(String flgauth) {
        this.flgauth = flgauth;
    }

    /**
     *
     * @return
     */
    public String getFlgview() {
        return flgview;
    }

    /**
     *
     * @param flgview
     */
    public void setFlgview(String flgview) {
        this.flgview = flgview;
    }

    /**
     *
     * @return
     */
    public ArrayList<EtsMsttxn> getListMsttxn() {
        return listMsttxn;
    }

    /**
     *
     * @param listMsttxn
     */
    public void setListMsttxn(ArrayList<EtsMsttxn> listMsttxn) {
        this.listMsttxn = listMsttxn;
    }

    /**
     *
     * @return
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
