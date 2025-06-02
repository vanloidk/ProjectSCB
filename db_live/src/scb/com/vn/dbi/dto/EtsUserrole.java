package scb.com.vn.dbi.dto;

import java.util.ArrayList;

/**
 *
 * @author system
 */
public class EtsUserrole implements java.io.Serializable {

    private static final long serialVersionUID = 9145959670209851042L;

    private String id_entity;
    private String iduser;
    private String idchannel;
    private String idrole;

    ArrayList<EtsRoletxn> listEtsMstRoletxn;

    /**
     *
     * @return
     */
    public String getId_entity() {
        return id_entity;
    }

    /**
     *
     * @param id_entity
     */
    public void setId_entity(String id_entity) {
        this.id_entity = id_entity;
    }

    /**
     *
     * @return
     */
    public String getIduser() {
        return iduser;
    }

    /**
     *
     * @param iduser
     */
    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    /**
     *
     * @return
     */
    public String getIdchannel() {
        return idchannel;
    }

    /**
     *
     * @param idchannel
     */
    public void setIdchannel(String idchannel) {
        this.idchannel = idchannel;
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
    public ArrayList<EtsRoletxn> getListEtsMstRoletxn() {
        return listEtsMstRoletxn;
    }

    /**
     *
     * @param listEtsMstRoletxn
     */
    public void setListEtsMstRoletxn(ArrayList<EtsRoletxn> listEtsMstRoletxn) {
        this.listEtsMstRoletxn = listEtsMstRoletxn;
    }

}
