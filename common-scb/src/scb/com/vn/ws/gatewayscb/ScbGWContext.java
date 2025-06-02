/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.ws.gatewayscb;

/**
 *
 * @author kimncm
 */
public class ScbGWContext {
    private String url;
    private String timeout;
    private String accessKey;
    private String partnerCode;
    private String signature;

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the timeout
     */
    public String getTimeout() {
        return timeout;
    }

    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    /**
     * @return the accessKey
     */
    public String getAccessKey() {
        return accessKey;
    }

    /**
     * @param accessKey the accessKey to set
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    /**
     * @return the partnerCode
     */
    public String getPartnerCode() {
        return partnerCode;
    }

    /**
     * @param partnerCode the partnerCode to set
     */
    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    /**
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @param signature the signature to set
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }
    
  
}
