/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

/**
 *
 * @author hieudt
 */
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hieudt
 */
@XmlRootElement(name = "RESPONSEREWARDADJUST")

public class ResponseRewardAdjust {

    private String ResponseCode;
    private String ResponseDescription;
    private String RefNo;

    /**
     *
     */
    public ResponseRewardAdjust() {
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "RESPONSECODE")
    public String getResponseCode() {
        return this.ResponseCode;
    }

    /**
     *
     * @param ResponseCode
     */
    public void setResponseCode(String ResponseCode) {
        this.ResponseCode = ResponseCode;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "RESPONSEDESCRIPTION")
    public String getResponseDescription() {
        return this.ResponseDescription;
    }

    /**
     *
     * @param ResponseDescription
     */
    public void setResponseDescription(String ResponseDescription) {
        this.ResponseDescription = ResponseDescription;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "REFNO")
    public String getRefNo() {
        return this.RefNo;
    }

    /**
     *
     * @param RefNo
     */
    public void setRefNo(String RefNo) {
        this.RefNo = RefNo;
    }
}
