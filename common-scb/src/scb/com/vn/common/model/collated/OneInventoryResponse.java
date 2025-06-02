/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.collated;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import scb.com.vn.ultility.Xml;
import scb.com.vn.ultility.ZipUtils;

/**
 *
 * @author hienlt6
 */
@XmlRootElement(name = "RESPONSE")
public class OneInventoryResponse implements Serializable{

    private static final Logger logger = Logger.getLogger(OneInventoryResponse.class);
    
    private String providerId;
    private String listData;
    private String responseCode;
    private String signatureScb;

    public OneInventoryResponse() {
    }

    public OneInventoryResponse(String responseCode, String signatureScb) {
        this.responseCode = responseCode;
        this.signatureScb = signatureScb;
    }

    @XmlElement(name = "PROVIDERID")
    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @XmlElement(name = "LISTDATA")
    public String getListData() {
        return listData;
    }

    public void setListData(String listData) {
        this.listData = listData;
    }

    @XmlElement(name = "RESPONSECODE")
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @XmlElement(name = "SIGNATURESCB")
    public String getSignatureScb() {
        return signatureScb;
    }

    public void setSignatureScb(String signatureScb) {
        this.signatureScb = signatureScb;
    }

    public void setTransData(Collated collated) {
        try {
            String str = Xml.Marshaller(collated);
            if (!str.isEmpty()) {
                str = str.replace("<TRANSSACTION>", "").replace("</TRANSSACTION>", "");
            }
            logger.info("du lieu truoc khi zip: " + str);
            String data = ZipUtils.zip(str);
            this.listData = data;
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
