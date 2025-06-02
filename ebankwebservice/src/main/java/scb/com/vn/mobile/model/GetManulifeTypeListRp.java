/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "GetManulifeTypeListRp")
public class GetManulifeTypeListRp extends MobileResponse {

    private String owner_id;
    private String owner_name;
    private String pol_num;
    private ManulifeType[] ManulifeTypeList;

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
     * @return the pol_num
     */
    @XmlElement(name = "PolNum", nillable = true)
    public String getPol_num() {
        return pol_num;
    }

    /**
     * @param pol_num the pol_num to set
     */
    public void setPol_num(String pol_num) {
        this.pol_num = pol_num;
    }

    /**
     * @return the ManulifeTypeList
     */
    @XmlElement(name = "ManulifeType")
    @XmlElementWrapper(name = "ManulifeTypeList", nillable = true)
    public ManulifeType[] getManulifeTypeList() {
        return ManulifeTypeList;
    }

    /**
     * @param ManulifeTypeList the ManulifeTypeList to set
     */
    public void setManulifeTypeList(ManulifeType[] ManulifeTypeList) {
        this.ManulifeTypeList = ManulifeTypeList;
    }

}
