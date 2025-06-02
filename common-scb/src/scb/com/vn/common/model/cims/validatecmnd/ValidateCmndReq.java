/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims.validatecmnd;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.cims.CIMSRequest;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "VALIDATECMNDREQ")
public class ValidateCmndReq extends CIMSRequest implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String cmnd;
    private String phone;

    @XmlElement(name = "CMND", nillable = false)
    public String getCmnd() {
        return cmnd;
    }

    @XmlElement(name = "PHONE", nillable = false)
    public String getPhone() {
        return phone;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String getValueToBuildMac() {
        return this.phone + this.cmnd + this.getTime();
    }
    
    public boolean isValid(){
        return !cmnd.isEmpty()
                && !phone.isEmpty()
                && !this.getTime().isEmpty()
                && !this.getPartner().isEmpty()
                && !this.getMac().isEmpty();
    }
}
