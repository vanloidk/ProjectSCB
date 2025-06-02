/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.bhbl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "CategoriesRq")
public class BHBLMetadataRq {

    private String type;

    public BHBLMetadataRq() {
    }

    @XmlElement(name = "Type", required = false, nillable = true)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
