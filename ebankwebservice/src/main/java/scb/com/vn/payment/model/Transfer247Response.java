/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "Transfer247Response")
public class Transfer247Response {

    String TransID;
    String ResponseCode;
    String ReponseMessage;
    String Refcore;
    String Benname;

    public Transfer247Response(){
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
