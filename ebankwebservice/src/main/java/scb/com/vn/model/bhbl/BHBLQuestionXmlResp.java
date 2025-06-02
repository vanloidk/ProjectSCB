/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.bhbl;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import scb.com.vn.dbi.dto.BHBLQuestionResp;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "QuestionRp")
@XmlType(propOrder = {"errorCode", "errorMsg", "BHBLQuestions"})
public class BHBLQuestionXmlResp {

    private String errorCode;
    private String errorMsg;
    private List<BHBLQuestionResp> BHBLQuestions;

    public BHBLQuestionXmlResp() {
    }

    @XmlElement(name = "ErrorCode", nillable = true)
    public String getErrorCode() {
        return errorCode;
    }   

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @XmlElement(name = "ErrorMsg", nillable = true)
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @XmlElement(name = "Questions", nillable = true)
    public List<BHBLQuestionResp> getBHBLQuestions() {
        return BHBLQuestions;
    }

    public void setBHBLQuestions(List<BHBLQuestionResp> BHBLQuestions) {
        this.BHBLQuestions = BHBLQuestions;
    }

}
