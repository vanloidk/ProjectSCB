/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author loinv3
 */
@XmlType(propOrder = {"idAnswer", "noiDung", "ghiChu", "type"})
public class BHBLAnswerDto implements Serializable {

    private static final long serialVersionUID = -1L;
    private Long idAnswer;
    private String noiDung;
    private String ghiChu;
    private String type;

    @XmlElement(name = "IdAnswer", nillable = true)
    public Long getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(Long idAnswer) {
        this.idAnswer = idAnswer;
    }

    @XmlElement(name = "Content", nillable = true)
    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
    
    @XmlElement(name = "AnswerNote", nillable = true)
     public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    

    @XmlElement(name = "Type", nillable = true)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
