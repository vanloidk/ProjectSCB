package scb.com.vn.dbi.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author duytxa
 */
@XmlRootElement(name = "request")
public class sms_partner_request {
    
    String partnerid;
    String servicecode;
    String servicetype;
    String requestid;
    String phoneno;
    String content;
    String mac;
    
    @XmlElement(name = "servicetype", nillable = true)
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
    @XmlElement(name = "content", nillable = true)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @XmlElement(name = "mac", nillable = true)
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
