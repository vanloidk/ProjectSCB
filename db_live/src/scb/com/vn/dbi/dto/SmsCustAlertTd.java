package scb.com.vn.dbi.dto;

/**
 *
 * @author system
 */
public class SmsCustAlertTd implements java.io.Serializable {

    private static final long serialVersionUID = 7789213856115130736L;

    private String id;
    private String cust_ac_no;
    private String branch_code;
    private String cust_no;
    private String ccy;
    private String account_class;
    private String ac_open_date;
    private String fullname;
    private String address;
    private String idcard;
    private String idcard_loc;
    private String idcard_dob;
    private String bank;
    private String active;
    private String mobile;
    private String ins_date;
    private String makerid;
    private String registertype = "CIF";
    private String tk_denhan;
    private String tk_momoi;
    private String description;
    private String modify_no;
    private String modify_user;
    private String point_reward;
    private String user_poi_rew;
    private String date_poi_rew;
    private String branch_code_mm;
    private String branch_code_poi;
    private String ref_thu;
    private String ref_chi;
    private String ref_status;
    private String so_tien;
    private String auto_poi_rew;

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
    public String getSo_tien() {
        return so_tien;
    }

    /**
     *
     * @param so_tien
     */
    public void setSo_tien(String so_tien) {
        this.so_tien = so_tien;
    }

    /**
     *
     * @return
     */
    public String getBranch_code_mm() {
        return branch_code_mm;
    }

    /**
     *
     * @param branch_code_mm
     */
    public void setBranch_code_mm(String branch_code_mm) {
        this.branch_code_mm = branch_code_mm;
    }

    /**
     *
     * @return
     */
    public String getBranch_code_poi() {
        return branch_code_poi;
    }

    /**
     *
     * @param branch_code_poi
     */
    public void setBranch_code_poi(String branch_code_poi) {
        this.branch_code_poi = branch_code_poi;
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
    public String getUser_poi_rew() {
        return user_poi_rew;
    }

    /**
     *
     * @param user_poi_rew
     */
    public void setUser_poi_rew(String user_poi_rew) {
        this.user_poi_rew = user_poi_rew;
    }

    /**
     *
     * @return
     */
    public String getDate_poi_rew() {
        return date_poi_rew;
    }

    /**
     *
     * @param date_poi_rew
     */
    public void setDate_poi_rew(String date_poi_rew) {
        this.date_poi_rew = date_poi_rew;
    }

    /**
     * Create a new instance of SmsCustAlertTd
     */
    public SmsCustAlertTd() {
    }

    /**
     *
     * @param cust_ac_no
     */
    public SmsCustAlertTd(String cust_ac_no) {
        this.cust_ac_no = cust_ac_no;
    }

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
    public String getCust_ac_no() {
        return this.cust_ac_no;
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
    public String getBranch_code() {
        return this.branch_code;
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
    public String getCust_no() {
        return this.cust_no;
    }

    /**
     *
     * @param cust_no
     */
    public void setCust_no(String cust_no) {
        this.cust_no = cust_no;
    }

    /**
     *
     * @return
     */
    public String getCcy() {
        return this.ccy;
    }

    /**
     *
     * @param ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     *
     * @return
     */
    public String getAccount_class() {
        return this.account_class;
    }

    /**
     *
     * @param account_class
     */
    public void setAccount_class(String account_class) {
        this.account_class = account_class;
    }

    /**
     *
     * @return
     */
    public String getAc_open_date() {
        return this.ac_open_date;
    }

    /**
     *
     * @param ac_open_date
     */
    public void setAc_open_date(String ac_open_date) {
        this.ac_open_date = ac_open_date;
    }

    /**
     *
     * @return
     */
    public String getFullname() {
        return this.fullname;
    }

    /**
     *
     * @param fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
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
    public String getIdcard_loc() {
        return idcard_loc;
    }

    /**
     *
     * @param idcard_loc
     */
    public void setIdcard_loc(String idcard_loc) {
        this.idcard_loc = idcard_loc;
    }

    /**
     *
     * @return
     */
    public String getIdcard_dob() {
        return idcard_dob;
    }

    /**
     *
     * @param idcard_dob
     */
    public void setIdcard_dob(String idcard_dob) {
        this.idcard_dob = idcard_dob;
    }

    /**
     *
     * @return
     */
    public String getBank() {
        return bank;
    }

    /**
     *
     * @param bank
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     *
     * @return
     */
    public String getActive() {
        return this.active;
    }

    /**
     *
     * @param active
     */
    public void setActive(String active) {
        this.active = active;
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
    public String getIns_date() {
        return ins_date;
    }

    /**
     *
     * @param ins_date
     */
    public void setIns_date(String ins_date) {
        this.ins_date = ins_date;
    }

    /**
     *
     * @return
     */
    public String getMakerid() {
        return makerid;
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
    public String getRegistertype() {
        return registertype;
    }

    /**
     *
     * @param registertype
     */
    public void setRegistertype(String registertype) {
        this.registertype = registertype;
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
     * @return the modify_no
     */
    public String getModify_no() {
        return modify_no;
    }

    /**
     * @param modify_no the modify_no to set
     */
    public void setModify_no(String modify_no) {
        this.modify_no = modify_no;
    }

    /**
     * @return the modify_user
     */
    public String getModify_user() {
        return modify_user;
    }

    /**
     * @param modify_user the modify_user to set
     */
    public void setModify_user(String modify_user) {
        this.modify_user = modify_user;
    }

    /**
     * @return the tk_denhan
     */
    public String getTk_denhan() {
        return tk_denhan;
    }

    /**
     * @param tk_denhan the tk_denhan to set
     */
    public void setTk_denhan(String tk_denhan) {
        this.tk_denhan = tk_denhan;
    }

    /**
     * @return the tk_momoi
     */
    public String getTk_momoi() {
        return tk_momoi;
    }

    /**
     * @param tk_momoi the tk_momoi to set
     */
    public void setTk_momoi(String tk_momoi) {
        this.tk_momoi = tk_momoi;
    }
}
