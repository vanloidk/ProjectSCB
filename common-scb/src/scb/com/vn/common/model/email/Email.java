/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.email;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "EMAIL")
public class Email {
    private EmailTo emailTo;
    private EmailCc emailCc;
    private EmailBcc emailBcc;

    public Email() {}

    public Email(List<String> emailTo, List<String> emailCc, List<String> emailBcc) {
        this.emailTo = new EmailTo();
        this.emailTo.setEmailDetail(emailTo);
        
        this.emailCc = new EmailCc();
        this.emailCc.setEmailDetail(emailCc);
        
        this.emailBcc = new EmailBcc();
        this.emailBcc.setEmailDetail(emailBcc);    
    }
    
    @XmlElement(name = "EMAILTO")
    public EmailTo getEmailTo() {
        return emailTo;
    }

    @XmlElement(name = "EMAILCC")
    public EmailCc getEmailCc() {
        return emailCc;
    }

    @XmlElement(name = "EMAILBCC")
    public EmailBcc getEmailBcc() {
        return emailBcc;
    }

    public void setEmailTo(EmailTo emailTo) {
        this.emailTo = emailTo;
    }

    public void setEmailCc(EmailCc emailCc) {
        this.emailCc = emailCc;
    }

    public void setEmailBcc(EmailBcc emailBcc) {
        this.emailBcc = emailBcc;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        
        str.append(emailTo);
        str.append(emailCc);
        str.append(emailBcc);
        
        return str.toString();
    }
    
    
}