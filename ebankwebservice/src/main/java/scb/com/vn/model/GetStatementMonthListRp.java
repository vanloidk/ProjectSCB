/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "GetStatementMonthListRp")
public class GetStatementMonthListRp extends MobileResponse {

    private String StatementMonthList;
    private List<CreditCardStatementRp> statementList;

    /**
     *
     */
    public GetStatementMonthListRp() {
    }

    /**
     * @return the StatementMonthList
     */
    @XmlElement(name = "StatementMonthList")
    public String getStatementMonthList() {
        return StatementMonthList;
    }

    /**
     * @param StatementMonthList the StatementMonthList to set
     */
    public void setStatementMonthList(String StatementMonthList) {
        this.StatementMonthList = StatementMonthList;
    }

    /**
     * @return the statementList
     */
    @XmlElement(name = "statementList")
    @XmlElementWrapper(name = "CreditCardStatementRp", nillable = true)
    public List<CreditCardStatementRp> getStatementList() {
        return statementList;
    }

    /**
     * @param statementList the statementList to set
     */
    public void setStatementList(List<CreditCardStatementRp> statementList) {
        this.statementList = statementList;
    }

}
