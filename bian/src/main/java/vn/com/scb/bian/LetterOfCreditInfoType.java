/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian;

import com.google.gson.annotations.Expose;

public class LetterOfCreditInfoType implements java.io.Serializable {

    @Expose
    private java.lang.String serialNo;

    private java.lang.String amount;

    private java.lang.String trnRef;

    private java.lang.String currency;

    private java.lang.String signDate;

    private java.lang.String toDate;

    private java.lang.String providerName;

    private java.lang.String receiveName;

    private vn.com.scb.bian.BranchInfoType branchInfo;
    

    private java.lang.String description;

    public LetterOfCreditInfoType() {
    }

    /**
     * @return the serialNo
     */
    public java.lang.String getSerialNo() {
        return serialNo;
    }

    /**
     * @param serialNo the serialNo to set
     */
    public void setSerialNo(java.lang.String serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * @return the amount
     */
    public java.lang.String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(java.lang.String amount) {
        this.amount = amount;
    }

    /**
     * @return the trnRef
     */
    public java.lang.String getTrnRef() {
        return trnRef;
    }

    /**
     * @param trnRef the trnRef to set
     */
    public void setTrnRef(java.lang.String trnRef) {
        this.trnRef = trnRef;
    }

    /**
     * @return the currency
     */
    public java.lang.String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(java.lang.String currency) {
        this.currency = currency;
    }

    /**
     * @return the signDate
     */
    public java.lang.String getSignDate() {
        return signDate;
    }

    /**
     * @param signDate the signDate to set
     */
    public void setSignDate(java.lang.String signDate) {
        this.signDate = signDate;
    }

    /**
     * @return the toDate
     */
    public java.lang.String getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(java.lang.String toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the providerName
     */
    public java.lang.String getProviderName() {
        return providerName;
    }

    /**
     * @param providerName the providerName to set
     */
    public void setProviderName(java.lang.String providerName) {
        this.providerName = providerName;
    }

    /**
     * @return the receiveName
     */
    public java.lang.String getReceiveName() {
        return receiveName;
    }

    /**
     * @param receiveName the receiveName to set
     */
    public void setReceiveName(java.lang.String receiveName) {
        this.receiveName = receiveName;
    }
   
    /**
     * @return the description
     */
    public java.lang.String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    /**
     * @return the branchInfo
     */
    public vn.com.scb.bian.BranchInfoType getBranchInfo() {
        return branchInfo;
    }

    /**
     * @param branchInfo the branchInfo to set
     */
    public void setBranchInfo(vn.com.scb.bian.BranchInfoType branchInfo) {
        this.branchInfo = branchInfo;
    }

}
