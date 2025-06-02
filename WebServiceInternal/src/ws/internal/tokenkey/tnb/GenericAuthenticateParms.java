/**
 * GenericAuthenticateParms.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class GenericAuthenticateParms  implements java.io.Serializable {
    private java.lang.String securityLevel;

    private java.lang.String authenticationType;

    private java.lang.String applicationName;

    private java.lang.Integer challengeSize;

    private java.lang.Integer numWrongAnswersAllowed;

    private java.lang.String[] authTypesRequiringPVN;

    private java.lang.String serialNumber;

    private java.lang.String tokenVendorId;

    private java.lang.String[] dataSignatureValues;

    private ws.internal.tokenkey.tnb.AuthenticationSecretParms authSecretParms;

    private ws.internal.tokenkey.tnb.SharedSecretParms sharedSecretParms;

    private java.lang.Boolean registerMachineSecret;

    private ws.internal.tokenkey.tnb.MachineSecret machineSecret;

    private java.lang.String IPAddress;

    private java.lang.String certificate;

    private java.lang.String newPassword;

    private java.lang.String newPVN;

    private java.lang.String[] challengeHistory;

    private ws.internal.tokenkey.tnb.com.entrust.NameValue[] transactionDetails;

    private java.lang.Boolean useDefaultDelivery;

    private java.lang.String[] contactInfoLabel;

    private java.lang.Boolean deliverForDynamicRefresh;

    private java.lang.String[] retrieveRepositoryAttributes;

    public GenericAuthenticateParms() {
    }

    public GenericAuthenticateParms(
           java.lang.String securityLevel,
           java.lang.String authenticationType,
           java.lang.String applicationName,
           java.lang.Integer challengeSize,
           java.lang.Integer numWrongAnswersAllowed,
           java.lang.String[] authTypesRequiringPVN,
           java.lang.String serialNumber,
           java.lang.String tokenVendorId,
           java.lang.String[] dataSignatureValues,
           ws.internal.tokenkey.tnb.AuthenticationSecretParms authSecretParms,
           ws.internal.tokenkey.tnb.SharedSecretParms sharedSecretParms,
           java.lang.Boolean registerMachineSecret,
           ws.internal.tokenkey.tnb.MachineSecret machineSecret,
           java.lang.String IPAddress,
           java.lang.String certificate,
           java.lang.String newPassword,
           java.lang.String newPVN,
           java.lang.String[] challengeHistory,
           ws.internal.tokenkey.tnb.com.entrust.NameValue[] transactionDetails,
           java.lang.Boolean useDefaultDelivery,
           java.lang.String[] contactInfoLabel,
           java.lang.Boolean deliverForDynamicRefresh,
           java.lang.String[] retrieveRepositoryAttributes) {
           this.securityLevel = securityLevel;
           this.authenticationType = authenticationType;
           this.applicationName = applicationName;
           this.challengeSize = challengeSize;
           this.numWrongAnswersAllowed = numWrongAnswersAllowed;
           this.authTypesRequiringPVN = authTypesRequiringPVN;
           this.serialNumber = serialNumber;
           this.tokenVendorId = tokenVendorId;
           this.dataSignatureValues = dataSignatureValues;
           this.authSecretParms = authSecretParms;
           this.sharedSecretParms = sharedSecretParms;
           this.registerMachineSecret = registerMachineSecret;
           this.machineSecret = machineSecret;
           this.IPAddress = IPAddress;
           this.certificate = certificate;
           this.newPassword = newPassword;
           this.newPVN = newPVN;
           this.challengeHistory = challengeHistory;
           this.transactionDetails = transactionDetails;
           this.useDefaultDelivery = useDefaultDelivery;
           this.contactInfoLabel = contactInfoLabel;
           this.deliverForDynamicRefresh = deliverForDynamicRefresh;
           this.retrieveRepositoryAttributes = retrieveRepositoryAttributes;
    }


    /**
     * Gets the securityLevel value for this GenericAuthenticateParms.
     * 
     * @return securityLevel
     */
    public java.lang.String getSecurityLevel() {
        return securityLevel;
    }


    /**
     * Sets the securityLevel value for this GenericAuthenticateParms.
     * 
     * @param securityLevel
     */
    public void setSecurityLevel(java.lang.String securityLevel) {
        this.securityLevel = securityLevel;
    }


    /**
     * Gets the authenticationType value for this GenericAuthenticateParms.
     * 
     * @return authenticationType
     */
    public java.lang.String getAuthenticationType() {
        return authenticationType;
    }


    /**
     * Sets the authenticationType value for this GenericAuthenticateParms.
     * 
     * @param authenticationType
     */
    public void setAuthenticationType(java.lang.String authenticationType) {
        this.authenticationType = authenticationType;
    }


    /**
     * Gets the applicationName value for this GenericAuthenticateParms.
     * 
     * @return applicationName
     */
    public java.lang.String getApplicationName() {
        return applicationName;
    }


    /**
     * Sets the applicationName value for this GenericAuthenticateParms.
     * 
     * @param applicationName
     */
    public void setApplicationName(java.lang.String applicationName) {
        this.applicationName = applicationName;
    }


    /**
     * Gets the challengeSize value for this GenericAuthenticateParms.
     * 
     * @return challengeSize
     */
    public java.lang.Integer getChallengeSize() {
        return challengeSize;
    }


    /**
     * Sets the challengeSize value for this GenericAuthenticateParms.
     * 
     * @param challengeSize
     */
    public void setChallengeSize(java.lang.Integer challengeSize) {
        this.challengeSize = challengeSize;
    }


    /**
     * Gets the numWrongAnswersAllowed value for this GenericAuthenticateParms.
     * 
     * @return numWrongAnswersAllowed
     */
    public java.lang.Integer getNumWrongAnswersAllowed() {
        return numWrongAnswersAllowed;
    }


    /**
     * Sets the numWrongAnswersAllowed value for this GenericAuthenticateParms.
     * 
     * @param numWrongAnswersAllowed
     */
    public void setNumWrongAnswersAllowed(java.lang.Integer numWrongAnswersAllowed) {
        this.numWrongAnswersAllowed = numWrongAnswersAllowed;
    }


    /**
     * Gets the authTypesRequiringPVN value for this GenericAuthenticateParms.
     * 
     * @return authTypesRequiringPVN
     */
    public java.lang.String[] getAuthTypesRequiringPVN() {
        return authTypesRequiringPVN;
    }


    /**
     * Sets the authTypesRequiringPVN value for this GenericAuthenticateParms.
     * 
     * @param authTypesRequiringPVN
     */
    public void setAuthTypesRequiringPVN(java.lang.String[] authTypesRequiringPVN) {
        this.authTypesRequiringPVN = authTypesRequiringPVN;
    }


    /**
     * Gets the serialNumber value for this GenericAuthenticateParms.
     * 
     * @return serialNumber
     */
    public java.lang.String getSerialNumber() {
        return serialNumber;
    }


    /**
     * Sets the serialNumber value for this GenericAuthenticateParms.
     * 
     * @param serialNumber
     */
    public void setSerialNumber(java.lang.String serialNumber) {
        this.serialNumber = serialNumber;
    }


    /**
     * Gets the tokenVendorId value for this GenericAuthenticateParms.
     * 
     * @return tokenVendorId
     */
    public java.lang.String getTokenVendorId() {
        return tokenVendorId;
    }


    /**
     * Sets the tokenVendorId value for this GenericAuthenticateParms.
     * 
     * @param tokenVendorId
     */
    public void setTokenVendorId(java.lang.String tokenVendorId) {
        this.tokenVendorId = tokenVendorId;
    }


    /**
     * Gets the dataSignatureValues value for this GenericAuthenticateParms.
     * 
     * @return dataSignatureValues
     */
    public java.lang.String[] getDataSignatureValues() {
        return dataSignatureValues;
    }


    /**
     * Sets the dataSignatureValues value for this GenericAuthenticateParms.
     * 
     * @param dataSignatureValues
     */
    public void setDataSignatureValues(java.lang.String[] dataSignatureValues) {
        this.dataSignatureValues = dataSignatureValues;
    }


    /**
     * Gets the authSecretParms value for this GenericAuthenticateParms.
     * 
     * @return authSecretParms
     */
    public ws.internal.tokenkey.tnb.AuthenticationSecretParms getAuthSecretParms() {
        return authSecretParms;
    }


    /**
     * Sets the authSecretParms value for this GenericAuthenticateParms.
     * 
     * @param authSecretParms
     */
    public void setAuthSecretParms(ws.internal.tokenkey.tnb.AuthenticationSecretParms authSecretParms) {
        this.authSecretParms = authSecretParms;
    }


    /**
     * Gets the sharedSecretParms value for this GenericAuthenticateParms.
     * 
     * @return sharedSecretParms
     */
    public ws.internal.tokenkey.tnb.SharedSecretParms getSharedSecretParms() {
        return sharedSecretParms;
    }


    /**
     * Sets the sharedSecretParms value for this GenericAuthenticateParms.
     * 
     * @param sharedSecretParms
     */
    public void setSharedSecretParms(ws.internal.tokenkey.tnb.SharedSecretParms sharedSecretParms) {
        this.sharedSecretParms = sharedSecretParms;
    }


    /**
     * Gets the registerMachineSecret value for this GenericAuthenticateParms.
     * 
     * @return registerMachineSecret
     */
    public java.lang.Boolean getRegisterMachineSecret() {
        return registerMachineSecret;
    }


    /**
     * Sets the registerMachineSecret value for this GenericAuthenticateParms.
     * 
     * @param registerMachineSecret
     */
    public void setRegisterMachineSecret(java.lang.Boolean registerMachineSecret) {
        this.registerMachineSecret = registerMachineSecret;
    }


    /**
     * Gets the machineSecret value for this GenericAuthenticateParms.
     * 
     * @return machineSecret
     */
    public ws.internal.tokenkey.tnb.MachineSecret getMachineSecret() {
        return machineSecret;
    }


    /**
     * Sets the machineSecret value for this GenericAuthenticateParms.
     * 
     * @param machineSecret
     */
    public void setMachineSecret(ws.internal.tokenkey.tnb.MachineSecret machineSecret) {
        this.machineSecret = machineSecret;
    }


    /**
     * Gets the IPAddress value for this GenericAuthenticateParms.
     * 
     * @return IPAddress
     */
    public java.lang.String getIPAddress() {
        return IPAddress;
    }


    /**
     * Sets the IPAddress value for this GenericAuthenticateParms.
     * 
     * @param IPAddress
     */
    public void setIPAddress(java.lang.String IPAddress) {
        this.IPAddress = IPAddress;
    }


    /**
     * Gets the certificate value for this GenericAuthenticateParms.
     * 
     * @return certificate
     */
    public java.lang.String getCertificate() {
        return certificate;
    }


    /**
     * Sets the certificate value for this GenericAuthenticateParms.
     * 
     * @param certificate
     */
    public void setCertificate(java.lang.String certificate) {
        this.certificate = certificate;
    }


    /**
     * Gets the newPassword value for this GenericAuthenticateParms.
     * 
     * @return newPassword
     */
    public java.lang.String getNewPassword() {
        return newPassword;
    }


    /**
     * Sets the newPassword value for this GenericAuthenticateParms.
     * 
     * @param newPassword
     */
    public void setNewPassword(java.lang.String newPassword) {
        this.newPassword = newPassword;
    }


    /**
     * Gets the newPVN value for this GenericAuthenticateParms.
     * 
     * @return newPVN
     */
    public java.lang.String getNewPVN() {
        return newPVN;
    }


    /**
     * Sets the newPVN value for this GenericAuthenticateParms.
     * 
     * @param newPVN
     */
    public void setNewPVN(java.lang.String newPVN) {
        this.newPVN = newPVN;
    }


    /**
     * Gets the challengeHistory value for this GenericAuthenticateParms.
     * 
     * @return challengeHistory
     */
    public java.lang.String[] getChallengeHistory() {
        return challengeHistory;
    }


    /**
     * Sets the challengeHistory value for this GenericAuthenticateParms.
     * 
     * @param challengeHistory
     */
    public void setChallengeHistory(java.lang.String[] challengeHistory) {
        this.challengeHistory = challengeHistory;
    }


    /**
     * Gets the transactionDetails value for this GenericAuthenticateParms.
     * 
     * @return transactionDetails
     */
    public ws.internal.tokenkey.tnb.com.entrust.NameValue[] getTransactionDetails() {
        return transactionDetails;
    }


    /**
     * Sets the transactionDetails value for this GenericAuthenticateParms.
     * 
     * @param transactionDetails
     */
    public void setTransactionDetails(ws.internal.tokenkey.tnb.com.entrust.NameValue[] transactionDetails) {
        this.transactionDetails = transactionDetails;
    }


    /**
     * Gets the useDefaultDelivery value for this GenericAuthenticateParms.
     * 
     * @return useDefaultDelivery
     */
    public java.lang.Boolean getUseDefaultDelivery() {
        return useDefaultDelivery;
    }


    /**
     * Sets the useDefaultDelivery value for this GenericAuthenticateParms.
     * 
     * @param useDefaultDelivery
     */
    public void setUseDefaultDelivery(java.lang.Boolean useDefaultDelivery) {
        this.useDefaultDelivery = useDefaultDelivery;
    }


    /**
     * Gets the contactInfoLabel value for this GenericAuthenticateParms.
     * 
     * @return contactInfoLabel
     */
    public java.lang.String[] getContactInfoLabel() {
        return contactInfoLabel;
    }


    /**
     * Sets the contactInfoLabel value for this GenericAuthenticateParms.
     * 
     * @param contactInfoLabel
     */
    public void setContactInfoLabel(java.lang.String[] contactInfoLabel) {
        this.contactInfoLabel = contactInfoLabel;
    }


    /**
     * Gets the deliverForDynamicRefresh value for this GenericAuthenticateParms.
     * 
     * @return deliverForDynamicRefresh
     */
    public java.lang.Boolean getDeliverForDynamicRefresh() {
        return deliverForDynamicRefresh;
    }


    /**
     * Sets the deliverForDynamicRefresh value for this GenericAuthenticateParms.
     * 
     * @param deliverForDynamicRefresh
     */
    public void setDeliverForDynamicRefresh(java.lang.Boolean deliverForDynamicRefresh) {
        this.deliverForDynamicRefresh = deliverForDynamicRefresh;
    }


    /**
     * Gets the retrieveRepositoryAttributes value for this GenericAuthenticateParms.
     * 
     * @return retrieveRepositoryAttributes
     */
    public java.lang.String[] getRetrieveRepositoryAttributes() {
        return retrieveRepositoryAttributes;
    }


    /**
     * Sets the retrieveRepositoryAttributes value for this GenericAuthenticateParms.
     * 
     * @param retrieveRepositoryAttributes
     */
    public void setRetrieveRepositoryAttributes(java.lang.String[] retrieveRepositoryAttributes) {
        this.retrieveRepositoryAttributes = retrieveRepositoryAttributes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GenericAuthenticateParms)) return false;
        GenericAuthenticateParms other = (GenericAuthenticateParms) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.securityLevel==null && other.getSecurityLevel()==null) || 
             (this.securityLevel!=null &&
              this.securityLevel.equals(other.getSecurityLevel()))) &&
            ((this.authenticationType==null && other.getAuthenticationType()==null) || 
             (this.authenticationType!=null &&
              this.authenticationType.equals(other.getAuthenticationType()))) &&
            ((this.applicationName==null && other.getApplicationName()==null) || 
             (this.applicationName!=null &&
              this.applicationName.equals(other.getApplicationName()))) &&
            ((this.challengeSize==null && other.getChallengeSize()==null) || 
             (this.challengeSize!=null &&
              this.challengeSize.equals(other.getChallengeSize()))) &&
            ((this.numWrongAnswersAllowed==null && other.getNumWrongAnswersAllowed()==null) || 
             (this.numWrongAnswersAllowed!=null &&
              this.numWrongAnswersAllowed.equals(other.getNumWrongAnswersAllowed()))) &&
            ((this.authTypesRequiringPVN==null && other.getAuthTypesRequiringPVN()==null) || 
             (this.authTypesRequiringPVN!=null &&
              java.util.Arrays.equals(this.authTypesRequiringPVN, other.getAuthTypesRequiringPVN()))) &&
            ((this.serialNumber==null && other.getSerialNumber()==null) || 
             (this.serialNumber!=null &&
              this.serialNumber.equals(other.getSerialNumber()))) &&
            ((this.tokenVendorId==null && other.getTokenVendorId()==null) || 
             (this.tokenVendorId!=null &&
              this.tokenVendorId.equals(other.getTokenVendorId()))) &&
            ((this.dataSignatureValues==null && other.getDataSignatureValues()==null) || 
             (this.dataSignatureValues!=null &&
              java.util.Arrays.equals(this.dataSignatureValues, other.getDataSignatureValues()))) &&
            ((this.authSecretParms==null && other.getAuthSecretParms()==null) || 
             (this.authSecretParms!=null &&
              this.authSecretParms.equals(other.getAuthSecretParms()))) &&
            ((this.sharedSecretParms==null && other.getSharedSecretParms()==null) || 
             (this.sharedSecretParms!=null &&
              this.sharedSecretParms.equals(other.getSharedSecretParms()))) &&
            ((this.registerMachineSecret==null && other.getRegisterMachineSecret()==null) || 
             (this.registerMachineSecret!=null &&
              this.registerMachineSecret.equals(other.getRegisterMachineSecret()))) &&
            ((this.machineSecret==null && other.getMachineSecret()==null) || 
             (this.machineSecret!=null &&
              this.machineSecret.equals(other.getMachineSecret()))) &&
            ((this.IPAddress==null && other.getIPAddress()==null) || 
             (this.IPAddress!=null &&
              this.IPAddress.equals(other.getIPAddress()))) &&
            ((this.certificate==null && other.getCertificate()==null) || 
             (this.certificate!=null &&
              this.certificate.equals(other.getCertificate()))) &&
            ((this.newPassword==null && other.getNewPassword()==null) || 
             (this.newPassword!=null &&
              this.newPassword.equals(other.getNewPassword()))) &&
            ((this.newPVN==null && other.getNewPVN()==null) || 
             (this.newPVN!=null &&
              this.newPVN.equals(other.getNewPVN()))) &&
            ((this.challengeHistory==null && other.getChallengeHistory()==null) || 
             (this.challengeHistory!=null &&
              java.util.Arrays.equals(this.challengeHistory, other.getChallengeHistory()))) &&
            ((this.transactionDetails==null && other.getTransactionDetails()==null) || 
             (this.transactionDetails!=null &&
              java.util.Arrays.equals(this.transactionDetails, other.getTransactionDetails()))) &&
            ((this.useDefaultDelivery==null && other.getUseDefaultDelivery()==null) || 
             (this.useDefaultDelivery!=null &&
              this.useDefaultDelivery.equals(other.getUseDefaultDelivery()))) &&
            ((this.contactInfoLabel==null && other.getContactInfoLabel()==null) || 
             (this.contactInfoLabel!=null &&
              java.util.Arrays.equals(this.contactInfoLabel, other.getContactInfoLabel()))) &&
            ((this.deliverForDynamicRefresh==null && other.getDeliverForDynamicRefresh()==null) || 
             (this.deliverForDynamicRefresh!=null &&
              this.deliverForDynamicRefresh.equals(other.getDeliverForDynamicRefresh()))) &&
            ((this.retrieveRepositoryAttributes==null && other.getRetrieveRepositoryAttributes()==null) || 
             (this.retrieveRepositoryAttributes!=null &&
              java.util.Arrays.equals(this.retrieveRepositoryAttributes, other.getRetrieveRepositoryAttributes())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getSecurityLevel() != null) {
            _hashCode += getSecurityLevel().hashCode();
        }
        if (getAuthenticationType() != null) {
            _hashCode += getAuthenticationType().hashCode();
        }
        if (getApplicationName() != null) {
            _hashCode += getApplicationName().hashCode();
        }
        if (getChallengeSize() != null) {
            _hashCode += getChallengeSize().hashCode();
        }
        if (getNumWrongAnswersAllowed() != null) {
            _hashCode += getNumWrongAnswersAllowed().hashCode();
        }
        if (getAuthTypesRequiringPVN() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAuthTypesRequiringPVN());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAuthTypesRequiringPVN(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSerialNumber() != null) {
            _hashCode += getSerialNumber().hashCode();
        }
        if (getTokenVendorId() != null) {
            _hashCode += getTokenVendorId().hashCode();
        }
        if (getDataSignatureValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDataSignatureValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDataSignatureValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAuthSecretParms() != null) {
            _hashCode += getAuthSecretParms().hashCode();
        }
        if (getSharedSecretParms() != null) {
            _hashCode += getSharedSecretParms().hashCode();
        }
        if (getRegisterMachineSecret() != null) {
            _hashCode += getRegisterMachineSecret().hashCode();
        }
        if (getMachineSecret() != null) {
            _hashCode += getMachineSecret().hashCode();
        }
        if (getIPAddress() != null) {
            _hashCode += getIPAddress().hashCode();
        }
        if (getCertificate() != null) {
            _hashCode += getCertificate().hashCode();
        }
        if (getNewPassword() != null) {
            _hashCode += getNewPassword().hashCode();
        }
        if (getNewPVN() != null) {
            _hashCode += getNewPVN().hashCode();
        }
        if (getChallengeHistory() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getChallengeHistory());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getChallengeHistory(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTransactionDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTransactionDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTransactionDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUseDefaultDelivery() != null) {
            _hashCode += getUseDefaultDelivery().hashCode();
        }
        if (getContactInfoLabel() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContactInfoLabel());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContactInfoLabel(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDeliverForDynamicRefresh() != null) {
            _hashCode += getDeliverForDynamicRefresh().hashCode();
        }
        if (getRetrieveRepositoryAttributes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRetrieveRepositoryAttributes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRetrieveRepositoryAttributes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GenericAuthenticateParms.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GenericAuthenticateParms"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("securityLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SecurityLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authenticationType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AuthenticationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ApplicationName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("challengeSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ChallengeSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numWrongAnswersAllowed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numWrongAnswersAllowed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authTypesRequiringPVN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AuthTypesRequiringPVN"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationType"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serialNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serialNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tokenVendorId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tokenVendorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataSignatureValues");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataSignatureValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authSecretParms");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authSecretParms"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSecretParms"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sharedSecretParms");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sharedSecretParms"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "SharedSecretParms"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registerMachineSecret");
        elemField.setXmlName(new javax.xml.namespace.QName("", "registerMachineSecret"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("machineSecret");
        elemField.setXmlName(new javax.xml.namespace.QName("", "machineSecret"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MachineSecret"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IPAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "certificate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "newPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newPVN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "newPVN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("challengeHistory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ChallengeHistory"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationType"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "NameValue"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("useDefaultDelivery");
        elemField.setXmlName(new javax.xml.namespace.QName("", "useDefaultDelivery"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactInfoLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contactInfoLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliverForDynamicRefresh");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliverForDynamicRefresh"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retrieveRepositoryAttributes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "retrieveRepositoryAttributes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
