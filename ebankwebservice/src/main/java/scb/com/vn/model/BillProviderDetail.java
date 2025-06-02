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
public class BillProviderDetail {

    private String idPartner;
    private String idprovider;
    private String providername;
    private String note;

    /**
     *
     * @param idprovider
     * @param providername
     */
    public BillProviderDetail(String idprovider, String providername) {
        this.idprovider = idprovider;
        this.providername = providername;

    }

    public BillProviderDetail(String idprovider, String providername, String note) {
        this.idprovider = idprovider;
        this.providername = providername;
        this.note = note;

    }
    
     public BillProviderDetail(String idprovider, String providername, String  idPartner, String note) {
        this.idprovider = idprovider;
        this.providername = providername;
        this.idPartner = idPartner;
        this.note = note;
    }

    /**
     *
     */
    public BillProviderDetail() {
    }

    /**
     * @return the idprovider
     */
    @XmlElement(name = "BillProviderId")
    public String getIdprovider() {
        return idprovider;
    }

    /**
     * @param idprovider the idprovider to set
     */
    public void setIdprovider(String idprovider) {
        this.idprovider = idprovider;
    }

    /**
     * @return the providername
     */
    @XmlElement(name = "BillProviderName")
    public String getProvidername() {
        return providername;
    }

    /**
     * @param providername the providername to set
     */
    public void setProvidername(String providername) {
        this.providername = providername;
    }

    /**
     * @return the note
     */
    @XmlElement(name = "Note")
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    @XmlElement(name = "IdPartner")
    public String getIdPartner() {
        return idPartner;
    }

    public void setIdPartner(String idPartner) {
        this.idPartner = idPartner;
    }

}
