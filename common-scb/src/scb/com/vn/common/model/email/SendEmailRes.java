/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.email;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "SENDEMAILRES")
public class SendEmailRes {
    private String status;
    private String title;
    private Email email;
    private String body;

    public SendEmailRes() {}

    public SendEmailRes(String status) {
        this.status = status;
    }

    public SendEmailRes(SendEmailReq req) {
        this.title = req.getTitle();
        this.email = req.getEmail();
        this.body = req.getBody();
    }
    
    public SendEmailRes(SendEmailReq req, String status) {
        this.status = status;
        this.title = req.getTitle();
        this.email = req.getEmail();
        this.body = req.getBody();
    }

    @XmlElement(name = "STATUS", nillable = false)
    public String getStatus() {
        return status;
    }

    @XmlElement(name = "TITLE", nillable = false)
    public String getTitle() {
        return title;
    }

    @XmlElement(name = "EMAIL", nillable = false)
    public Email getEmail() {
        return email;
    }

    @XmlElement(name = "BODY", nillable = false)
    public String getBody() {
        return body;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
