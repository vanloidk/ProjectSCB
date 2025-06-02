/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "TRANRESULT")
public class TRANRESULT {

    private String TRANCODE;
    private AccountXML TRANDATA;

    /**
     * @return the TRANCODE
     */
    @XmlElement(name = "TRANCODE")   
    public String getTRANCODE() {
        return TRANCODE;
    }

    /**
     * @param TRANCODE the TRANCODE to set
     */
    public void setTRANCODE(String TRANCODE) {
        this.TRANCODE = TRANCODE;
    }

    /**
     * @return the TRANDATA
     */     
    public AccountXML getTRANDATA() {
        return TRANDATA;
    }

    /**
     * @param TRANDATA the TRANDATA to set
     */
    public void setTRANDATA(AccountXML TRANDATA) {
        this.TRANDATA = TRANDATA;
    }

}
