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
@XmlRootElement(name = "GetManulifeContractListRq")
public class GetManulifeContractListRq extends MobileModelRequest {

    private String Ownerid;

    /**
     * @return the Ownerid
     */
    @XmlElement(name = "Ownerid", nillable = true)
    public String getOwnerid() {
        return Ownerid;
    }

    /**
     * @param Ownerid the Ownerid to set
     */
    public void setOwnerid(String Ownerid) {
        this.Ownerid = Ownerid;
    }

}
