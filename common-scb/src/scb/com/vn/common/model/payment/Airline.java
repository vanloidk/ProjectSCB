/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author FICOMBANK
 */
public class Airline {

    private String status;
    private String bookcode;
    private List<Customer> listcustomer;
    private List<Schedule> listschedule;
    private String agent;
    private String maker;
    private String mobcust;
    private String booktime;
    private String amount;
    private String description;

    public String getStatus() {
        return status;
    }

    @XmlElement(name = "STATUS", required = false, nillable = true)
    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookcode() {
        return bookcode;
    }

    @XmlElement(name = "BOOKCODE", required = false, nillable = true)
    public void setBookcode(String bookcode) {
        this.bookcode = bookcode;
    }

    @XmlElementWrapper(name = "LISTCUSTOMER", required = false, nillable = true)
    @XmlElement(name = "CUSTOMER", required = false, nillable = true)
    public List<Customer> getListcustomer() {
        return listcustomer;
    }

    public void setListcustomer(List<Customer> listcustomer) {
        this.listcustomer = listcustomer;
    }

    @XmlElement(name = "AGENT", required = false, nillable = true)
    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    @XmlElement(name = "MAKER", required = false, nillable = true)
    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @XmlElement(name = "MOBCUST", required = false, nillable = true)
    public String getMobcust() {
        return mobcust;
    }

    public void setMobcust(String mobcust) {
        this.mobcust = mobcust;
    }

    @XmlElement(name = "BOOKTIME", required = false, nillable = true)
    public String getBooktime() {
        return booktime;
    }

    public void setBooktime(String booktime) {
        this.booktime = booktime;
    }

    @XmlElement(name = "AMOUNT", required = false, nillable = true)
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @XmlElement(name = "DESCRIPTION", required = false, nillable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @XmlElementWrapper(name = "LISTSCHEDULE", required = false, nillable = true)
    @XmlElement(name = "SCHEDULE", required = false, nillable = true)
    public List<Schedule> getListschedule() {
        return listschedule;
    }

    public void setListschedule(List<Schedule> listschedule) {
        this.listschedule = listschedule;
    }
}
