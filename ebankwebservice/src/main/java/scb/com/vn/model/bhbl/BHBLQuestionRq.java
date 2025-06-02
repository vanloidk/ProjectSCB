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
@XmlRootElement(name = "QuestionRq")
public class BHBLQuestionRq {

    private String Type;
    private String Lang;

    public BHBLQuestionRq() {
    }

    @XmlElement(name = "Type", required = false, nillable = true)
    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    @XmlElement(name = "Lang", required = false, nillable = true)
    public String getLang() {
        return Lang;
    }

    public void setLang(String Lang) {
        this.Lang = Lang;
    }

}
