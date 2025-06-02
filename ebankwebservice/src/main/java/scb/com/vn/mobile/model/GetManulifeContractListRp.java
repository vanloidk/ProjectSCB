/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "GetManulifeContractListRp")
public class GetManulifeContractListRp extends MobileResponse {

    private String owner_id;
    private String owner_name;
    private String PolNumList;

    /**
     * @return the owner_id
     */
    @XmlElement(name = "Ownerid", nillable = true)
    public String getOwner_id() {
        return owner_id;
    }

    /**
     * @param owner_id the owner_id to set
     */
    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    /**
     * @return the owner_name
     */
    @XmlElement(name = "Ownername", nillable = true)
    public String getOwner_name() {
        return owner_name;
    }

    /**
     * @param owner_name the owner_name to set
     */
    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    /**
     * @return the PolNumList
     */
    @XmlElement(name = "PolNumList", nillable = true)
    public String getPolNumList() {
        return PolNumList;
    }

    /**
     * @param PolNumList the PolNumList to set
     */
    public void setPolNumList(String PolNumList) {
        this.PolNumList = PolNumList;
    }

}
