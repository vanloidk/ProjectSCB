/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.info;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.axis.encoding.Serializer;

/**
 *
 * @author lydty
 */
@XmlRootElement(name = "SMSCUSTREGISINFO")
public class SmsCustRegisInfo implements Serializable
{
    String custno;
    String custaccno;
    String phoneno;
    String typeacc;
    String userregister;
    String userapprove;
    String channel;
    String branch;
    String typefunction;


    @XmlElement(name = "TYPEFUCTION")
    public String getTypefunction() {
        return typefunction;
    }

    public void setTypefunction(String typefunction) {
        this.typefunction = typefunction;
    }
    
   
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
    
    
    
    @XmlElement(name = "USERREGISTER")
    public String getUserregister() {
        return userregister;
    }

    public void setUserregister(String userregister) {
        this.userregister = userregister;
    }

    @XmlElement(name = "USERAPPROVE")
    public String getUserapprove() {
        return userapprove;
    }

    public void setUserapprove(String userapprove) {
        this.userapprove = userapprove;
    }
    
    @XmlElement(name = "CHANNEL")
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
    

    
    @XmlElement(name = "CUSTNO")
    public String getCustno() {
        return custno;
    }

    public void setCustno(String custno) {
        this.custno = custno;
    }
    @XmlElement(name = "CUSTACCNO")
    public String getCustaccno() {
        return custaccno;
    }

    public void setCustaccno(String custaccno) {
        this.custaccno = custaccno;
    }

    @XmlElement(name = "PHONENO")
    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
    @XmlElement(name = "TYPEACC")
    public String getTypeacc() {
        return typeacc;
    }

    public void setTypeacc(String typeacc) {
        this.typeacc = typeacc;
    }
    
    
    
}
