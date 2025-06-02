/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "RetrieveOnlineInsBuyHistoryRq")
public class RetrieveOnlineInsBuyHistoryRq extends MobileModelRequest {

    private String InsBuyId;

    /**
     *
     */
    public RetrieveOnlineInsBuyHistoryRq() {
    }

    /**
     * @return the InsBuyId
     */
    @XmlElement(name = "InsBuyId")
    public String getInsBuyId() {
        return InsBuyId;
    }

    /**
     * @param InsBuyId the InsBuyId to set
     */
    public void setInsBuyId(String InsBuyId) {
        this.InsBuyId = InsBuyId;
    }

}
