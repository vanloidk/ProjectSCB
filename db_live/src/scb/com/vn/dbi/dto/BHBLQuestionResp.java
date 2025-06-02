/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author loinv3
 */
@XmlType(propOrder = {"idQuestion", "noiDung", "answers", "type", "typeQuestion", "checkBox"})
public class BHBLQuestionResp implements Serializable {

    private static final long serialVersionUID = -1L;
    private Long idQuestion;
    private String noiDung;
    List<BHBLAnswerDto> answers;
    private String type;
    private int typeQuestion;
    private int checkBox;

    @XmlElement(name = "IdQuestion", nillable = true)
    public Long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Long idQuestion) {
        this.idQuestion = idQuestion;
    }

    @XmlElement(name = "Content", nillable = true)
    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @XmlElement(name = "Answers", nillable = true)
    public List<BHBLAnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<BHBLAnswerDto> answers) {
        this.answers = answers;
    }

    @XmlElement(name = "Type", nillable = true)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "TypeQ", nillable = true)
    public int getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(int typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    @XmlElement(name = "CheckBox", nillable = true)
    public int getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(int checkBox) {
        this.checkBox = checkBox;
    }
}
