/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kimncm
 */
@XmlRootElement(name = "GoldRateRp")
public class GoldRateRp extends MobileResponse {

    private GoldRateDetail[] listGoldRate;
    private String dateUpdate;
    private String timeUpdate;

    /**
     * @return the listExchangeRate
     */
    @XmlElement(name = "GoldRateDetail")
    @XmlElementWrapper(name = "ListGoldRate", nillable = true)
    public GoldRateDetail[] getListGoldRate() {
        return listGoldRate;
    }

    /**
     * @param listGoldRate
     */
    public void setListGoldRate(GoldRateDetail[] listGoldRate) {
        this.listGoldRate = listGoldRate;
    }

    /**
     * @return the dateUpdate
     */
    @XmlElement(name = "DateUpdate")
    public String getDateUpdate() {
        return dateUpdate;
    }

    /**
     * @param dateUpdate the dateUpdate to set
     */
    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    /**
     * @return the timeUpdate
     */
    @XmlElement(name = "TimeUpdate")
    public String getTimeUpdate() {
        return timeUpdate;
    }

    /**
     * @param timeUpdate the timeUpdate to set
     */
    public void setTimeUpdate(String timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

}
