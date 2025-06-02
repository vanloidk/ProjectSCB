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
@XmlRootElement(name = "REQUESTREWARDADJUST")
public class RequestRewardAdjust {

    private String Cif;
    private String Pan;
    private String Point;
    private String RewardAction;
    private String Channel;
    private String TxnDesc;

    /**
     *
     */
    public RequestRewardAdjust() {
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CIF")
    public String getCif() {
        return this.Cif;
    }

    /**
     *
     * @param Cif
     */
    public void setCif(String Cif) {
        this.Cif = Cif;
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
    @XmlElement(name = "POINT")
    public String getPoint() {
        return this.Point;
    }

    /**
     *
     * @param Point
     */
    public void setPoint(String Point) {
        this.Point = Point;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "REWARDACTION")
    public String getRewardAction() {
        return this.RewardAction;
    }

    /**
     *
     * @param RewardAction
     */
    public void setRewardAction(String RewardAction) {
        this.RewardAction = RewardAction;
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

    /**
     *
     * @return
     */
    @XmlElement(name = "TXNDESC")
    public String getTxnDesc() {
        return this.TxnDesc;
    }

    /**
     *
     * @param TxnDesc
     */
    public void setTxnDesc(String TxnDesc) {
        this.TxnDesc = Channel;
    }
}
