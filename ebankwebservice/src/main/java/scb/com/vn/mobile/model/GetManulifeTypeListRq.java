/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "GetManulifeTypeListRq")
public class GetManulifeTypeListRq extends MobileModelRequest {

    private String Polnum;

    /**
     * @return the Polnum
     */
    @XmlElement(name = "PolNum", nillable = true)
    public String getPolnum() {
        return Polnum;
    }

    /**
     * @param Polnum the Polnum to set
     */
    public void setPolnum(String Polnum) {
        this.Polnum = Polnum;
    }

}
