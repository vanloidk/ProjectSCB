/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hieudt
 */
@XmlRootElement(name = "REQUESTCARDBALANCEADJUST")
public class RequestCardBalanceAdjust {

    private String Pan;
    private String Amount;
    private String AdjCode;
    private String BranchCode;
    private String Description;
    private String Channel;

    /**
     *
     */
    public RequestCardBalanceAdjust() {
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "PAN")
    public String getPan() {
        return this.Pan;
    }

    /**
     *
     * @param Pan
     */
    public void setPan(String Pan) {
        this.Pan = Pan;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AMOUNT")
    public String getAmount() {
        return this.Amount;
    }

    /**
     *
     * @param Amount
     */
    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "ADJCODE")
    public String getAdjCode() {
        return this.AdjCode;
    }

    /**
     *
     * @param AdjCode
     */
    public void setAdjCode(String AdjCode) {
        this.AdjCode = AdjCode;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "BRANCHCODE")
    public String getBranchCode() {
        return this.BranchCode;
    }

    /**
     *
     * @param BranchCode
     */
    public void setBranchCode(String BranchCode) {
        this.BranchCode = BranchCode;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return this.Description;
    }

    /**
     *
     * @param Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CHANNEL")
    public String getChannel() {
        return this.Channel;
    }

    /**
     *
     * @param Channel
     */
    public void setChannel(String Channel) {
        this.Channel = Channel;
    }
}
