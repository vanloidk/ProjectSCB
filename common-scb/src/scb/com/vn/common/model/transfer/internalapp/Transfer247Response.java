package scb.com.vn.common.model.transfer.internalapp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hienlt6
 */
@XmlRootElement(name = "Transfer247Response")
public class Transfer247Response {

    private String TransID;
    private String ResponseCode;
    private String ReponseMessage;
    private String Refcore;
    private String Benname;

    public Transfer247Response() {
    }

    public Transfer247Response(String ResponseCode, String ReponseMessage) {
        this.ResponseCode = ResponseCode;
        this.ReponseMessage = ReponseMessage;
    }

    @XmlElement(name = "TransID")
    public String getTransID() {
        return TransID;
    }

    public void setTransID(String TransID) {
        this.TransID = TransID;
    }

    @XmlElement(name = "ResponseCode")
    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String ResponseCode) {
        this.ResponseCode = ResponseCode;
    }

    @XmlElement(name = "ReponseMessage")
    public String getReponseMessage() {
        return ReponseMessage;
    }

    public void setReponseMessage(String ReponseMessage) {
        this.ReponseMessage = ReponseMessage;
    }

    @XmlElement(name = "Refcore")
    public String getRefcore() {
        return Refcore;
    }

    public void setRefcore(String Refcore) {
        this.Refcore = Refcore;
    }

    @XmlElement(name = "Benname")
    public String getBenname() {
        return Benname;
    }

    public void setBenname(String Benname) {
        this.Benname = Benname;
    }

}
