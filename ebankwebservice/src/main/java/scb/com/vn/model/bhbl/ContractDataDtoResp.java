/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.bhbl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "HealthCareContractRp")
public class ContractDataDtoResp {

    private EnqueueStatus EnqueueStatus;
    private EnqueueResult EnqueueResult;

    @XmlElement(name = "EnqueueStatus")
    public EnqueueStatus getEnqueueStatus() {
        return EnqueueStatus;
    }

    public void setEnqueueStatus(EnqueueStatus EnqueueStatus) {
        this.EnqueueStatus = EnqueueStatus;
    }

    @XmlElement(name = "EnqueueResult")
    public EnqueueResult getEnqueueResult() {
        return EnqueueResult;
    }

    public void setEnqueueResult(EnqueueResult EnqueueResult) {
        this.EnqueueResult = EnqueueResult;
    }

    /* dto01 */
    public static class EnqueueStatus {

        public EnqueueStatus() {
        }

        private String EnqueueCode;
        private String EnqueueDesc;

        @XmlElement(name = "EnqueueCode")
        public String getEnqueueCode() {
            return EnqueueCode;
        }

        public void setEnqueueCode(String EnqueueCode) {
            this.EnqueueCode = EnqueueCode;
        }

        @XmlElement(name = "EnqueueDesc")
        public String getEnqueueDesc() {
            return EnqueueDesc;
        }

        public void setEnqueueDesc(String EnqueueDesc) {
            this.EnqueueDesc = EnqueueDesc;
        }

    }

    /* dto02 */
    public class EnqueueResult {

        private String SigningData;
        private String SigningHash;
        private String InvoiceCode;

        @XmlElement(name = "SigningData")
        public String getSigningData() {
            return SigningData;
        }

        public void setSigningData(String SigningData) {
            this.SigningData = SigningData;
        }

        @XmlElement(name = "SigningHash")
        public String getSigningHash() {
            return SigningHash;
        }

        public void setSigningHash(String SigningHash) {
            this.SigningHash = SigningHash;
        }

        @XmlElement(name = "InvoiceCode")
        public String getInvoiceCode() {
            return InvoiceCode;
        }

        public void setInvoiceCode(String InvoiceCode) {
            this.InvoiceCode = InvoiceCode;
        }

    }

}
