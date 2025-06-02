
package scb.com.vn.dbi.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author duytxa
 */
@XmlRootElement(name = "response")
public class sms_partner_response {
    
    String partnerid;
    String servicecode;
    String servicetype;
    String requestid;
    String phoneno;
    String responsecode;
    String mac;

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }
    
    
    
     @XmlElement(name = "partnerid", nillable = true)
    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }
    
     @XmlElement(name = "servicecode", nillable = true)
    public String getServicecode() {
        return servicecode;
    }

    public void setServicecode(String servicecode) {
        this.servicecode = servicecode;
    }

   @XmlElement(name = "requestid", nillable = true)
    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }
  @XmlElement(name = "phoneno", nillable = true)
    public String getPhoneno() {
   return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
@XmlElement(name = "responsecode", nillable = true)
    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }
@XmlElement(name = "mac", nillable = true)
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
    
    
}
