/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author system
 */
public class Pbl_partnercode implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String idPartner;
    private String idServiceType;
    private String idProvider;
    private String commonProviderId;
    private String serviceCode;
    private String providerCode;

    /**
     *
     * @return
     */
    public String getIdPartner() {
        return idPartner;
    }

    /**
     *
     * @param idPartner
     */
    public void setIdPartner(String idPartner) {
        this.idPartner = idPartner;
    }

    /**
     *
     * @return
     */
    public String getIdServiceType() {
        return idServiceType;
    }

    /**
     *
     * @param idServiceType
     */
    public void setIdServiceType(String idServiceType) {
        this.idServiceType = idServiceType;
    }

    /**
     *
     * @return
     */
    public String getIdProvider() {
        return idProvider;
    }

    /**
     *
     * @param idProvider
     */
    public void setIdProvider(String idProvider) {
        this.idProvider = idProvider;
    }

    /**
     *
     * @return
     */
    public String getCommonProviderId() {
        return commonProviderId;
    }

    /**
     *
     * @param commonProviderId
     */
    public void setCommonProviderId(String commonProviderId) {
        this.commonProviderId = commonProviderId;
    }

    /**
     *
     * @return
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     *
     * @param serviceCode
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     *
     * @return
     */
    public String getProviderCode() {
        return providerCode;
    }

    /**
     *
     * @param providerCode
     */
    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }
}
