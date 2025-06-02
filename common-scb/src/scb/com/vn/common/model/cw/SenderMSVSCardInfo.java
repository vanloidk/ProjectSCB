/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cw;

/**
 *
 * @author minhndb
 */
public class SenderMSVSCardInfo implements java.io.Serializable {
    
    private static final long serialVersionUID = -1L;
    
    /**
     *
     */
    private String loc;

    /**
     *
     */
    private String pan_encr;

    /**
     *
     */
    private String pan_mask;

    /**
     *
     */
    private String pan_clear;

    /**
     *
     */
    private String frist_name;

    /**
     *
     */
    private String last_name;

    /**
     *
     */
    private String hme_addr;

    /**
     *
     */
    private String hme_town;

    /**
     *
     */
    private String hme_cntry;

    /**
     *
     */
    private String hme_zip;

    /**
     *
     */
    private String expi_date;

    /**
     *
     */
    private String brch_cde;

    /**
     *
     */
    private String senderCountrySub;

    /* VISA */
    /**
     *
     */
    private String senderName;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String phone;

    /**
     *
     */
    private String cardName;

    /* NOT NECCESSARY */
    /**
     *
     */
    private String middleName;

    /* MORE INFOMATION */
    private String creditType; // VS ~ VISA, MC ~ MASTER
    private String cardType; // C ~ Credit, D ~ Debit
    private String accountDebit;

    /**
     * Create a new instance of SenderMSVSCardInfo
     */
    public SenderMSVSCardInfo() {
        this.senderCountrySub = "VNM";
        this.hme_cntry = "VNM";
        this.hme_zip = "70000";
        this.middleName = "";
    }

    /**
     *
     * @return
     */
    public String getLoc() {
        return loc;
    }

    /**
     *
     * @param loc
     */
    public void setLoc(String loc) {
        this.loc = removeSpace(loc);
    }

    /**
     *
     * @return
     */
    public String getPan_encr() {
        return pan_encr;
    }

    /**
     *
     * @param pan_encr
     */
    public void setPan_encr(String pan_encr) {
        this.pan_encr = removeSpace(pan_encr);
    }

    /**
     *
     * @return
     */
    public String getPan_mask() {
        return pan_mask;
    }

    /**
     *
     * @param pan_mask
     */
    public void setPan_mask(String pan_mask) {
        this.pan_mask = removeSpace(pan_mask);
    }

    /**
     *
     * @return
     */
    public String getFrist_name() {
        return frist_name;
    }

    /**
     *
     * @param frist_name
     */
    public void setFrist_name(String frist_name) {
        this.frist_name = validateParam(frist_name, "UNKNOWN");
    }

    /**
     *
     * @return
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     *
     * @param last_name
     */
    public void setLast_name(String last_name) {
        this.last_name = validateParam(last_name, "UNKNOWN");
    }

    /**
     *
     * @return
     */
    public String getHme_addr() {
        return hme_addr;
    }

    /**
     *
     * @param hme_addr
     */
    public void setHme_addr(String hme_addr) {
        this.hme_addr = validateParam(hme_addr, "UNKNOWN");
    }

    /**
     *
     * @return
     */
    public String getExpi_date() {
        return expi_date;
    }

    /**
     *
     * @param expi_date
     */
    public void setExpi_date(String expi_date) {
        this.expi_date = removeSpace(expi_date);
    }

    /**
     *
     * @return
     */
    public String getBrch_cde() {
        return brch_cde;
    }

    /**
     *
     * @param brch_cde
     */
    public void setBrch_cde(String brch_cde) {
        this.brch_cde = removeSpace(brch_cde);
    }

    /**
     *
     * @return
     */
    public String getPan_clear() {
        return pan_clear;
    }

    /**
     *
     * @param pan_clear
     */
    public void setPan_clear(String pan_clear) {
        this.pan_clear = removeSpace(pan_clear);
    }

    /**
     *
     * @return
     */
    public String getHme_town() {
        return hme_town;
    }

    /**
     *
     * @param hme_town
     */
    public void setHme_town(String hme_town) {
        this.hme_town = validateParam(hme_town, "VNM");
    }

    /**
     *
     * @return
     */
    public String getHme_cntry() {
        return hme_cntry;
    }

    /**
     *
     * @param hme_cntry
     */
    public void setHme_cntry(String hme_cntry) {
        this.hme_cntry = validateParam(hme_cntry, "VNM");
    }

    /**
     *
     * @return
     */
    public String getHme_zip() {
        return hme_zip;
    }

    /**
     *
     * @param hme_zip
     */
    public void setHme_zip(String hme_zip) {
        this.hme_zip = validateParam(hme_zip, "70000");
    }

    /**
     *
     * @return
     */
    public String getSenderCountrySub() {
        return senderCountrySub;
    }

    /**
     *
     * @param senderCountrySub
     */
    public void setSenderCountrySub(String senderCountrySub) {
        this.senderCountrySub = validateParam(senderCountrySub, "VNM");
    }

    /**
     *
     * @return
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     *
     * @param middleName
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     *
     * @return
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     *
     * @param senderName
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
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
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     */
    public String getCardName() {
        return cardName;
    }

    /**
     *
     * @param cardName
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     *
     * @return
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     *
     * @param creditType
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    /**
     *
     * @return
     */
    public String getCardType() {
        return cardType;
    }

    /**
     *
     * @param cardType
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     *
     * @return
     */
    public String getAccountDebit() {
        return accountDebit;
    }

    /**
     *
     * @param accountDebit
     */
    public void setAccountDebit(String accountDebit) {
        this.accountDebit = accountDebit;
    }

    /**
     *
     * @return
     */
    public boolean isInvalidData() {
        return loc == null || pan_encr == null || pan_mask == null || pan_clear == null
                || frist_name == null || last_name == null || hme_addr == null || hme_town == null
                || hme_cntry == null || hme_zip == null || expi_date == null || brch_cde == null
                || senderCountrySub == null || cardType == null;
    }

    private String removeSpace(String data) {
        return (data == null || data.isEmpty()) ? null : data.trim();
    }

    private String validateParam(String data, String defData) {
        return (data == null || data.isEmpty()) ? defData : data.trim();
    }
    
    /*
    * Credit ~ C
    * Debit ~ D
    */
    public boolean isCreditCard() {
        return "C".equals(cardType);
    }
    
}
