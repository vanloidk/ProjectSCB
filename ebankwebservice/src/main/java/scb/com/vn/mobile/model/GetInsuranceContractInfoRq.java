/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "GetInsuranceContractInfoRq")
public class GetInsuranceContractInfoRq extends MobileModelRequest {

    private String Polnum;
    private String PREM_TYPE;

    /**
     * @return the Ownerid
     */
    /**
     * @return the Polnum
     */
    @XmlElement(name = "Polnum", nillable = true)
    public String getPolnum() {
        return Polnum;
    }

    /**
     * @param Polnum the Polnum to set
     */
    public void setPolnum(String Polnum) {
        this.Polnum = Polnum;
    }

    /**
     * @return the PREM_TYPE
     */
    @XmlElement(name = "PREM_TYPE", nillable = true)
    public String getPREM_TYPE() {
        return PREM_TYPE;
    }

    /**
     * @param PREM_TYPE the PREM_TYPE to set
     */
    public void setPREM_TYPE(String PREM_TYPE) {
        this.PREM_TYPE = PREM_TYPE;
    }

}
