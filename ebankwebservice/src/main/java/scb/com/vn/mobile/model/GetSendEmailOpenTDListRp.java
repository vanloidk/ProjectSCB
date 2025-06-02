/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "GetSendEmailOpenTDListRp")
public class GetSendEmailOpenTDListRp extends MobileResponse {

    private SendEmailOpenTD[] sendEmailOpenTDList;

    /**
     *
     */
    public GetSendEmailOpenTDListRp() {
    }

    /**
     * @return the sendEmailOpenTDList
     */
    @XmlElement(name = "SendEmailOpenTD")
    @XmlElementWrapper(name = "SendEmailOpenTDList", nillable = true)
    public SendEmailOpenTD[] getSendEmailOpenTDList() {
        return sendEmailOpenTDList;
    }

    /**
     * @param sendEmailOpenTDList the sendEmailOpenTDList to set
     */
    public void setSendEmailOpenTDList(SendEmailOpenTD[] sendEmailOpenTDList) {
        this.sendEmailOpenTDList = sendEmailOpenTDList;
    }
}
