/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "RewardPointRp")
public class RewardPointRp extends MobileResponse {

    private String accountno;
    private String openingpoint;
    private String earnedpoint;
    private String closingpoint;
    private String presentpoint;

    /**
     * @return the cardaccountno
     */
    /**
     * @return the openingpoint
     */
    @XmlElement(name = "OpeningPoint", nillable = true)
    public String getOpeningpoint() {
        return openingpoint;
    }

    /**
     * @param openingpoint the openingpoint to set
     */
    public void setOpeningpoint(String openingpoint) {
        this.openingpoint = openingpoint;
    }

    /**
     * @return the earnedpoint
     */
    @XmlElement(name = "EarnedPoint", nillable = true)
    public String getEarnedpoint() {
        return earnedpoint;
    }

    /**
     * @param earnedpoint the earnedpoint to set
     */
    public void setEarnedpoint(String earnedpoint) {
        this.earnedpoint = earnedpoint;
    }

    /**
     * @return the closingpoint
     */
    @XmlElement(name = "ClosingPoint", nillable = true)
    public String getClosingpoint() {
        return closingpoint;
    }

    /**
     * @param closingpoint the closingpoint to set
     */
    public void setClosingpoint(String closingpoint) {
        this.closingpoint = closingpoint;
    }

    /**
     * @return the presentpoint
     */
    @XmlElement(name = "PresentPoint", nillable = true)
    public String getPresentpoint() {
        return presentpoint;
    }

    /**
     * @param presentpoint the presentpoint to set
     */
    public void setPresentpoint(String presentpoint) {
        this.presentpoint = presentpoint;
    }

    /**
     * @return the accountno
     */
    @XmlElement(name = "AccountNo", nillable = true)
    public String getAccountno() {
        return accountno;
    }

    /**
     * @param accountno the accountno to set
     */
    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }
}
