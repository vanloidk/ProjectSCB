/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.util.Arrays;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author hieudt
 */
@XmlRootElement(name = "result")
public class CIMSCustResult {

    private String Code;

    private CIMSCustInfo[] custInfo;

    private CIMSCardInfo cardInfo;

    /**
     * Create a new instance of CIMSCustResult
     */
    public CIMSCustResult() {
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "code")
    public String getCode() {
        return this.Code;
    }

    /**
     *
     * @param Code
     */
    public void setCode(String Code) {
        this.Code = Code;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "ccinfo")
    public CIMSCustInfo[] getCustInfo() {
        return this.custInfo;
    }

    /**
     *
     * @param custInfo
     */
    public void setCustInfo(CIMSCustInfo[] custInfo) {
        this.custInfo = custInfo;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CardInfo")
    public CIMSCardInfo getCardInfo() {
        return this.cardInfo;
    }

    /**
     *
     * @param cardInfo
     */
    public void setCardInfo(CIMSCardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    /**
     * sort cims cust info
     */
    public void sortCIMSCustInfo() {
        if (custInfo != null && custInfo.length > 0) {
            Arrays.sort(custInfo);
        }
        /* FOR JAVA 8 
        * Arrays.sort(custInfo, Comparator.comparing(CIMSCustInfo::getCif));
         */
    }

}
