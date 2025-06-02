/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.email;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.cims.CIMSRequest;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "SENDEMAILREQ")
public class SendEmailReq extends CIMSRequest {
    private String title;
    private Email email;
    private String body;
    private String fileAttach;
    private byte[] fileData;
    private String fileName;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String getValueToBuildMac() {
        StringBuilder str = new StringBuilder();
        str.append(this.email).append(this.title).append(this.getTime());
        return str.toString();
    }

    /**
     * @return the fileAttach
     */
    @XmlElement(name = "FILE_ATTACH", nillable = false)
    public String getFileAttach() {
        return fileAttach;
    }

    /**
     * @param fileAttach the fileAttach to set
     */
    public void setFileAttach(String fileAttach) {
        this.fileAttach = fileAttach;
    }
    
    public boolean isValid() {
        return !title.isEmpty()
                && !body.isEmpty()
                && email != null
                && email.getEmailTo() != null
                && !email.getEmailTo().getEmailDetail().isEmpty();
    }
    
    public List<String> getEmailTo() {
        return email.getEmailTo().getEmailDetail();
    }
    
    public List<String> getEmailCc() {
        return email.getEmailCc() != null
                ? email.getEmailCc().getEmailDetail()
                : null;
    }
    
    public List<String> getEmailBcc() {
        return email.getEmailBcc() != null
                ? email.getEmailBcc().getEmailDetail()
                : null;
    }
    
    public boolean isExistEmailCc() {
        return email.getEmailCc() != null
                && email.getEmailCc().getEmailDetail() != null
                && !email.getEmailCc().getEmailDetail().isEmpty();
    }
    
    public boolean isExistEmailBcc() {
        return email.getEmailBcc() != null
                && email.getEmailBcc().getEmailDetail() != null
                && !email.getEmailBcc().getEmailDetail().isEmpty();
    }

    /**
     * @return the fileData
     */
     @XmlElement(name = "FILEDATA", nillable = false)
    public byte[] getFileData() {
        return fileData;
    }

    /**
     * @param fileData the fileData to set
     */
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    /**
     * @return the fileName
     */
     @XmlElement(name = "FILENAME", nillable = false)
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

   