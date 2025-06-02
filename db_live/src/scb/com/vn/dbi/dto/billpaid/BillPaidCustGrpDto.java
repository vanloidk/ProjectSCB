/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto.billpaid;

/**
 *
 * @author loinv3
 */
public class BillPaidCustGrpDto implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String parnerId;
    private String providerId;
    private String serviceTypeId;

    public BillPaidCustGrpDto() {
    }

    public String getParnerId() {
        return parnerId;
    }

    public void setParnerId(String parnerId) {
        this.parnerId = parnerId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

}
