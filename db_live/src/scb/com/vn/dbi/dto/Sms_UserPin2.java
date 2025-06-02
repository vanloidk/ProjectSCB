package scb.com.vn.dbi.dto;

import java.math.BigDecimal;

/**
 *
 * @author system
 */
public class Sms_UserPin2 implements java.io.Serializable {

    private static final long serialVersionUID = 639055957353140978L;

    private String id_entity;
    private String iduser;
    private String idchanneluser;
    private String idchannel;
    private String usertype;
    private String passwordtxn;
    private BigDecimal lengthpwd;
    private String lockflag;
    private String flagforcechangetxnpwd;
    private String markerid;
    private String status;

    /**
     * Create a new instance of Sms_UserPin2
     */
    public Sms_UserPin2() {
    }

    /**
     *
     * @return
     */
    public String getId_entity() {
        return this.id_entity;
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
    public String getIdchanneluser() {
        return idchanneluser;
    }

    /**
     *
     * @param idchanneluser
     */
    public void setIdchanneluser(String idchanneluser) {
        this.idchanneluser = idchanneluser;
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
    public String getPasswordtxn() {
        return passwordtxn;
    }

    /**
     *
     * @param passwordtxn
     */
    public void setPasswordtxn(String passwordtxn) {
        this.passwordtxn = passwordtxn;
    }

    /**
     *
     * @return
     */
    public BigDecimal getLengthpwd() {
        return lengthpwd;
    }

    /**
     *
     * @param lengthpwd
     */
    public void setLengthpwd(BigDecimal lengthpwd) {
        this.lengthpwd = lengthpwd;
    }

    /**
     *
     * @return
     */
    public String getLockflag() {
        return lockflag;
    }

    /**
     *
     * @param lockflag
     */
    public void setLockflag(String lockflag) {
        this.lockflag = lockflag;
    }

    /**
     *
     * @return
     */
    public String getFlagforcechangetxnpwd() {
        return flagforcechangetxnpwd;
    }

    /**
     *
     * @param flagforcechangetxnpwd
     */
    public void setFlagforcechangetxnpwd(String flagforcechangetxnpwd) {
        this.flagforcechangetxnpwd = flagforcechangetxnpwd;
    }

    /**
     *
     * @return
     */
    public String getMarkerid() {
        return markerid;
    }

    /**
     *
     * @param markerid
     */
    public void setMarkerid(String markerid) {
        this.markerid = markerid;
    }

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

}
