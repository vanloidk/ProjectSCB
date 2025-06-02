package scb.com.vn.dbi.dto;

/**
 *
 * @author system
 */
public class SmsCustAlertTichLuy implements java.io.Serializable {

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
    private String description;

    /**
     * Create a new instance of SmsCustAlertTichLuy
     */
    public SmsCustAlertTichLuy() {
    }

    /**
     *
     * @param cust_ac_no
     */
    public SmsCustAlertTichLuy(String cust_ac_no) {
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
}
