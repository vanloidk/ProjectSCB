/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.utility;

/**
 *
 * @author scb
 */
public class IIBContext {
    
    private String serviceEndpoint;
    private String billingServiceEndpoint;
    private String partnerServiceEndpoint;
    private String clientId;
    private String clientPassword;
    private int timeout;

    public IIBContext() {
    }

    /**
     * @return the serviceEndpoint
     */
    public String getServiceEndpoint() {
        return serviceEndpoint;
    }

    /**
     * @param serviceEndpoint the serviceEndpoint to set
     */
    public void setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    /**
     * @return the billingServiceEndpoint
     */
    public String getBillingServiceEndpoint() {
        return billingServiceEndpoint;
    }

    /**
     * @param billingServiceEndpoint the billingServiceEndpoint to set
     */
    public void setBillingServiceEndpoint(String billingServiceEndpoint) {
        this.billingServiceEndpoint = billingServiceEndpoint;
    }

    public String getPartnerServiceEndpoint() {
        return partnerServiceEndpoint;
    }

    public void setPartnerServiceEndpoint(String partnerServiceEndpoint) {
        this.partnerServiceEndpoint = partnerServiceEndpoint;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the clientPassword
     */
    public String getClientPassword() {
        return clientPassword;
    }

    /**
     * @param clientPassword the clientPassword to set
     */
    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    /**
     * @return the timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

}
