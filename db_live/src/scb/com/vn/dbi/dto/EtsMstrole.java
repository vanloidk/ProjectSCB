package scb.com.vn.dbi.dto;

/**
 *
 * @author system
 */
public class EtsMstrole implements java.io.Serializable {

    private static final long serialVersionUID = 2580212172861115015L;

    private String id_entity;
    private String usertype;
    private String idchannel;
    private String idrole;
    private String description;
    private String datcreated;
    private String datlastupdated;
    private String isdefault;
    private String iscustomeronly;
    private String createdby;
    private String modifiedby;
    private String idcust;
    private String typecust;

    /**
     * Create a new instance of EtsMstrole
     */
    public EtsMstrole() {
    }

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
    public String getUsertype() {
        return usertype;
    }

    /**
     *
     * @param usertype
     */
    public void setUsertype(String usertype) {
        this.usertype = usertype;
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
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getDatcreated() {
        return datcreated;
    }

    /**
     *
     * @param datcreated
     */
    public void setDatcreated(String datcreated) {
        this.datcreated = datcreated;
    }

    /**
     *
     * @return
     */
    public String getDatlastupdated() {
        return datlastupdated;
    }

    /**
     *
     * @param datlastupdated
     */
    public void setDatlastupdated(String datlastupdated) {
        this.datlastupdated = datlastupdated;
    }

    /**
     *
     * @return
     */
    public String getIsdefault() {
        return isdefault;
    }

    /**
     *
     * @param isdefault
     */
    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    /**
     *
     * @return
     */
    public String getIscustomeronly() {
        return iscustomeronly;
    }

    /**
     *
     * @param iscustomeronly
     */
    public void setIscustomeronly(String iscustomeronly) {
        this.iscustomeronly = iscustomeronly;
    }

    /**
     *
     * @return
     */
    public String getCreatedby() {
        return createdby;
    }

    /**
     *
     * @param createdby
     */
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    /**
     *
     * @return
     */
    public String getModifiedby() {
        return modifiedby;
    }

    /**
     *
     * @param modifiedby
     */
    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    /**
     *
     * @return
     */
    public String getIdcust() {
        return idcust;
    }

    /**
     *
     * @param idcust
     */
    public void setIdcust(String idcust) {
        this.idcust = idcust;
    }

    /**
     *
     * @return
     */
    public String getTypecust() {
        return typecust;
    }

    /**
     *
     * @param typecust
     */
    public void setTypecust(String typecust) {
        this.typecust = typecust;
    }

    /**
     *
     * @return
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
