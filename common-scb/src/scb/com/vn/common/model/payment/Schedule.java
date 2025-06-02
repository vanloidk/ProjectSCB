/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author FICOMBANK
 */
public class Schedule {

    private String brand;
    private String code;
    private String flydate;


    private String departure;
    private String arrive;
    private String timedeparture;
    private String timearrive;

    @XmlElement(name = "FLYDATE", required = false, nillable = true)
    public String getFlydate() {
        return flydate;
    }

    public void setFlydate(String flydate) {
        this.flydate = flydate;
    }

    @XmlElement(name = "BRAND", required = false, nillable = true)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @XmlElement(name = "CODE", required = false, nillable = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlElement(name = "DEPARTURE", required = false, nillable = true)
    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    @XmlElement(name = "ARRIVE", required = false, nillable = true)
    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    @XmlElement(name = "TIMEDEPARTURE", required = false, nillable = true)
    public String getTimedeparture() {
        return timedeparture;
    }

    public void setTimedeparture(String timedeparture) {
        this.timedeparture = timedeparture;
    }

    @XmlElement(name = "TIMEARRIVE", required = false, nillable = true)
    public String getTimearrive() {
        return timearrive;
    }

    public void setTimearrive(String timearrive) {
        this.timearrive = timearrive;
    }
}
