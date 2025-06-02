package scb.com.vn.dbi.dto;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author system
 */
public class EtsMstuser implements java.io.Serializable {

    private static final long serialVersionUID = -3749324374371216317L;
    private String iduser;
    private String id_entity;
    private String typeuser;
    private String nicname;
    private String salution;
    private String firstname;
    private String lastname;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip;
    private Date dob;
    private String email;
    private String activeflag;
    private String phonenumber;
    private String faxnbr;
    private String authreqd;
    private Date datcreated;
    private String makerid;
    private String branch_code;
    private String preferences;
    private String userdata;
    private String idcard;
    private String package_id;
    private String operativeacctno;
    private String operativebrncode;
    private String feeacctno;
    private String feebrncode;
    private String deliverypwdtype;
    private String cardtype;
    private String issamepwd;
    ArrayList<EtsMstchanneluser> listEtsMstchanneluser;
    ArrayList<EtsUserrole> listEtsUserrole;

    /**
     * Create a new instance of EtsMstuser
     */
    public EtsMstuser() {
    }

    /**
     *
     * @return
     */
    public String getIduser() {
        return this.iduser;
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
    public String getTypeuser() {
        return this.typeuser;
    }

    /**
     *
     * @param typeuser
     */
    public void setTypeuser(String typeuser) {
        this.typeuser = typeuser;
    }

    /**
     *
     * @return
     */
    public String getNicname() {
        return this.nicname;
    }

    /**
     *
     * @param nicname
     */
    public void setNicname(String nicname) {
        this.nicname = nicname;
    }

    /**
     *
     * @return
     */
    public String getSalution() {
        return this.salution;
    }

    /**
     *
     * @param salution
     */
    public void setSalution(String salution) {
        this.salution = salution;
    }

    /**
     *
     * @return
     */
    public String getFirstname() {
        return this.firstname;
    }

    /**
     *
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return
     */
    public String getLastname() {
        return this.lastname;
    }

    /**
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return this.address;
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
    public String getCity() {
        return this.city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public String getState() {
        return this.state;
    }

    /**
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return this.country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     */
    public String getZip() {
        return this.zip;
    }

    /**
     *
     * @param zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     *
     * @return
     */
    public Date getDob() {
        return this.dob;
    }

    /**
     *
     * @param dob
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return this.email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getActiveflag() {
        return this.activeflag;
    }

    /**
     *
     * @param activeflag
     */
    public void setActiveflag(String activeflag) {
        this.activeflag = activeflag;
    }

    /**
     *
     * @return
     */
    public String getPhonenumber() {
        return this.phonenumber;
    }

    /**
     *
     * @param phonenumber
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     *
     * @return
     */
    public String getFaxnbr() {
        return this.faxnbr;
    }

    /**
     *
     * @param faxnbr
     */
    public void setFaxnbr(String faxnbr) {
        this.faxnbr = faxnbr;
    }

    /**
     *
     * @return
     */
    public String getAuthreqd() {
        return this.authreqd;
    }

    /**
     *
     * @param authreqd
     */
    public void setAuthreqd(String authreqd) {
        this.authreqd = authreqd;
    }

    /**
     *
     * @return
     */
    public Date getDatcreated() {
        return this.datcreated;
    }

    /**
     *
     * @param datcreated
     */
    public void setDatcreated(Date datcreated) {
        this.datcreated = datcreated;
    }

    /**
     *
     * @return
     */
    public String getMakerid() {
        return this.makerid;
    }

    /**
     *
     * @param makerid
     */
    public void setMakerid(String makerid) {
        this.makerid = makerid;
    }

    /**
     *
     * @return
     */
    public ArrayList<EtsMstchanneluser> getListEtsMstchanneluser() {
        return listEtsMstchanneluser;
    }

    /**
     *
     * @param listEtsMstchanneluser
     */
    public void setListEtsMstchanneluser(ArrayList<EtsMstchanneluser> listEtsMstchanneluser) {
        this.listEtsMstchanneluser = listEtsMstchanneluser;
    }

    /**
     *
     * @return
     */
    public ArrayList<EtsUserrole> getListEtsUserrole() {
        return listEtsUserrole;
    }

    /**
     *
     * @param listEtsUserrole
     */
    public void setListEtsUserrole(ArrayList<EtsUserrole> listEtsUserrole) {
        this.listEtsUserrole = listEtsUserrole;
    }

    /**
     *
     * @return
     */
    public String getBranch_code() {
        return branch_code;
    }

    /**
     *
     * @param branch_code
     */
    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    /**
     *
     * @return
     */
    public String getPreferences() {
        return preferences;
    }

    /**
     *
     * @param preferences
     */
    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    /**
     *
     * @return
     */
    public String getUserdata() {
        return userdata;
    }

    /**
     *
     * @param userdata
     */
    public void setUserdata(String userdata) {
        this.userdata = userdata;
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
    public String getPackage_id() {
        return package_id;
    }

    /**
     *
     * @param package_id
     */
    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    /**
     *
     * @return
     */
    public String getOperativeacctno() {
        return operativeacctno;
    }

    /**
     *
     * @param operativeacctno
     */
    public void setOperativeacctno(String operativeacctno) {
        this.operativeacctno = operativeacctno;
    }

    /**
     *
     * @return
     */
    public String getOperativebrncode() {
        return operativebrncode;
    }

    /**
     *
     * @param operativebrncode
     */
    public void setOperativebrncode(String operativebrncode) {
        this.operativebrncode = operativebrncode;
    }

    /**
     *
     * @return
     */
    public String getFeeacctno() {
        return feeacctno;
    }

    /**
     *
     * @param feeacctno
     */
    public void setFeeacctno(String feeacctno) {
        this.feeacctno = feeacctno;
    }

    /**
     *
     * @return
     */
    public String getFeebrncode() {
        return feebrncode;
    }

    /**
     *
     * @param feebrncode
     */
    public void setFeebrncode(String feebrncode) {
        this.feebrncode = feebrncode;
    }

    /**
     *
     * @return
     */
    public String getDeliverypwdtype() {
        return deliverypwdtype;
    }

    /**
     *
     * @param deliverypwdtype
     */
    public void setDeliverypwdtype(String deliverypwdtype) {
        this.deliverypwdtype = deliverypwdtype;
    }

    /**
     *
     * @return
     */
    public String getCardtype() {
        return cardtype;
    }

    /**
     *
     * @param cardtype
     */
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    /**
     *
     * @return
     */
    public String getIssamepwd() {
        return issamepwd;
    }

    /**
     *
     * @param issamepwd
     */
    public void setIssamepwd(String issamepwd) {
        this.issamepwd = issamepwd;
    }
}
