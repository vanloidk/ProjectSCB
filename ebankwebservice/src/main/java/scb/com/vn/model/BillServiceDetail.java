/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Administrator
 */
public class BillServiceDetail {

    private String idservicetype;
    private String name;

    /**
     *
     * @param idservicetype
     * @param name
     */
    public BillServiceDetail(String idservicetype, String name) {
        this.idservicetype = idservicetype;
        this.name = name;

    }

    /**
     *
     */
    public BillServiceDetail() {
    }

    /**
     * @return the idservicetype
     */
    @XmlElement(name = "BillServiceId")
    public String getIdservicetype() {
        return idservicetype;
    }

    /**
     * @param idservicetype the idservicetype to set
     */
    public void setIdservicetype(String idservicetype) {
        this.idservicetype = idservicetype;
    }

    /**
     * @return the name
     */
    @XmlElement(name = "BillServiceName")
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
