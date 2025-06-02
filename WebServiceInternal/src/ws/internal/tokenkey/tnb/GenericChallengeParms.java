/**
 * GenericChallengeParms.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class GenericChallengeParms  implements java.io.Serializable {
    private java.lang.String[] challengeHistory;

    private java.lang.String securityLevel;

    private java.lang.String authenticationType;

    private java.lang.String[] authenticationTypeList;

    private java.lang.String applicationName;

    private java.lang.Boolean requiresPVN;

    private java.lang.String[] authTypesRequiringPVN;

    private java.lang.Integer gridChallengeSize;

    private java.lang.Integer QAChallengeSize;

    private ws.internal.tokenkey.tnb.AuthenticationSecretParms authSecretParms;

    private ws.internal.tokenkey.tnb.SharedSecretParms sharedSecretParms;

    private java.lang.String IPAddress;

    private java.lang.Boolean updateMachineSecret;

    private java.lang.Boolean registerMachineSecret;

    private ws.internal.tokenkey.tnb.MachineSecret machineSecret;

    private java.lang.Boolean registerCertificate;

    private java.lang.String certificate;

    private ws.internal.tokenkey.tnb.ExternalRiskScoreParms externalRiskScore;

    private java.lang.Boolean useDefaultDelivery;

    private java.lang.String[] contactInfoLabel;

    private java.lang.Boolean deliverForDynamicRefresh;

    private java.lang.Boolean onlySelectOTPAuthenticationIfDeliveryAvailable;

    private ws.internal.tokenkey.tnb.com.entrust.NameValue[] transactionDetails;

    public GenericChallengeParms() {
    }

    public GenericChallengeParms(
           java.lang.String[] challengeHistory,
           java.lang.String securityLevel,
           java.lang.String authenticationType,
           java.lang.String[] authenticationTypeList,
           java.lang.String applicationName,
           java.lang.Boolean requiresPVN,
           java.lang.String[] authTypesRequiringPVN,
           java.lang.Integer gridChallengeSize,
           java.lang.Integer QAChallengeSize,
           ws.internal.tokenkey.tnb.AuthenticationSecretParms authSecretParms,
           ws.internal.tokenkey.tnb.SharedSecretParms sharedSecretParms,
           java.lang.String IPAddress,
           java.lang.Boolean updateMachineSecret,
           java.lang.Boolean registerMachineSecret,
           ws.internal.tokenkey.tnb.MachineSecret machineSecret,
           java.lang.Boolean registerCertificate,
           java.lang.String certificate,
           ws.internal.tokenkey.tnb.ExternalRiskScoreParms externalRiskScore,
           java.lang.Boolean useDefaultDelivery,
           java.lang.String[] contactInfoLabel,
           java.lang.Boolean deliverForDynamicRefresh,
           java.lang.Boolean onlySelectOTPAuthenticationIfDeliveryAvailable,
           ws.internal.tokenkey.tnb.com.entrust.NameValue[] transactionDetails) {
           this.challengeHistory = challengeHistory;
           this.securityLevel = securityLevel;
           this.authenticationType = authenticationType;
           this.authenticationTypeList = authenticationTypeList;
           this.applicationName = applicationName;
           this.requiresPVN = requiresPVN;
           this.authTypesRequiringPVN = authTypesRequiringPVN;
           this.gridChallengeSize = gridChallengeSize;
           this.QAChallengeSize = QAChallengeSize;
           this.authSecretParms = authSecretParms;
           this.sharedSecretParms = sharedSecretParms;
           this.IPAddress = IPAddress;
           this.updateMachineSecret = updateMachineSecret;
           this.registerMachineSecret = registerMachineSecret;
           this.machineSecret = machineSecret;
           this.registerCertificate = registerCertificate;
           this.certificate = certificate;
           this.externalRiskScore = externalRiskScore;
           this.useDefaultDelivery = useDefaultDelivery;
           this.contactInfoLabel = contactInfoLabel;
           this.deliverForDynamicRefresh = deliverForDynamicRefresh;
           this.onlySelectOTPAuthenticationIfDeliveryAvailable = onlySelectOTPAuthenticationIfDeliveryAvailable;
           this.transactionDetails = transactionDetails;
    }


    /**
     * Gets the challengeHistory value for this GenericChallengeParms.
     * 
     * @return challengeHistory
     */
    public java.lang.String[] getChallengeHistory() {
        return challengeHistory;
    }


    /**
     * Sets the challengeHistory value for this GenericChallengeParms.
     * 
     * @param challengeHistory
     */
    public void setChallengeHistory(java.lang.String[] challengeHistory) {
        this.challengeHistory = challengeHistory;
    }


    /**
     * Gets the securityLevel value for this GenericChallengeParms.
     * 
     * @return securityLevel
     */
    public java.lang.String getSecurityLevel() {
        return securityLevel;
    }


    /**
     * Sets the securityLevel value for this GenericChallengeParms.
     * 
     * @param securityLevel
     */
    public void setSecurityLevel(java.lang.String securityLevel) {
        this.securityLevel = securityLevel;
    }


    /**
     * Gets the authenticationType value for this GenericChallengeParms.
     * 
     * @return authenticationType
     */
    public java.lang.String getAuthenticationType() {
        return authenticationType;
    }


    /**
     * Sets the authenticationType value for this GenericChallengeParms.
     * 
     * @param authenticationType
     */
    public void setAuthenticationType(java.lang.String authenticationType) {
        this.authenticationType = authenticationType;
    }


    /**
     * Gets the authenticationTypeList value for this GenericChallengeParms.
     * 
     * @return authenticationTypeList
     */
    public java.lang.String[] getAuthenticationTypeList() {
        return authenticationTypeList;
    }


    /**
     * Sets the authenticationTypeList value for this GenericChallengeParms.
     * 
     * @param authenticationTypeList
     */
    public void setAuthenticationTypeList(java.lang.String[] authenticationTypeList) {
        this.authenticationTypeList = authenticationTypeList;
    }


    /**
     * Gets the applicationName value for this GenericChallengeParms.
     * 
     * @return applicationName
     */
    public java.lang.String getApplicationName() {
        return applicationName;
    }


    /**
     * Sets the applicationName value for this GenericChallengeParms.
     * 
     * @param applicationName
     */
    public void setApplicationName(java.lang.String applicationName) {
        this.applicationName = applicationName;
    }


    /**
     * Gets the requiresPVN value for this GenericChallengeParms.
     * 
     * @return requiresPVN
     */
    public java.lang.Boolean getRequiresPVN() {
        return requiresPVN;
    }


    /**
     * Sets the requiresPVN value for this GenericChallengeParms.
     * 
     * @param requiresPVN
     */
    public void setRequiresPVN(java.lang.Boolean requiresPVN) {
        this.requiresPVN = requiresPVN;
    }


    /**
     * Gets the authTypesRequiringPVN value for this GenericChallengeParms.
     * 
     * @return authTypesRequiringPVN
     */
    public java.lang.String[] getAuthTypesRequiringPVN() {
        return authTypesRequiringPVN;
    }


    /**
     * Sets the authTypesRequiringPVN value for this GenericChallengeParms.
     * 
     * @param authTypesRequiringPVN
     */
    public void setAuthTypesRequiringPVN(java.lang.String[] authTypesRequiringPVN) {
        this.authTypesRequiringPVN = authTypesRequiringPVN;
    }


    /**
     * Gets the gridChallengeSize value for this GenericChallengeParms.
     * 
     * @return gridChallengeSize
     */
    public java.lang.Integer getGridChallengeSize() {
        return gridChallengeSize;
    }


    /**
     * Sets the gridChallengeSize value for this GenericChallengeParms.
     * 
     * @param gridChallengeSize
     */
    public void setGridChallengeSize(java.lang.Integer gridChallengeSize) {
        this.gridChallengeSize = gridChallengeSize;
    }


    /**
     * Gets the QAChallengeSize value for this GenericChallengeParms.
     * 
     * @return QAChallengeSize
     */
    public java.lang.Integer getQAChallengeSize() {
        return QAChallengeSize;
    }


    /**
     * Sets the QAChallengeSize value for this GenericChallengeParms.
     * 
     * @param QAChallengeSize
     */
    public void setQAChallengeSize(java.lang.Integer QAChallengeSize) {
        this.QAChallengeSize = QAChallengeSize;
    }


    /**
     * Gets the authSecretParms value for this GenericChallengeParms.
     * 
     * @return authSecretParms
     */
    public ws.internal.tokenkey.tnb.AuthenticationSecretParms getAuthSecretParms() {
        return authSecretParms;
    }


    /**
     * Sets the authSecretParms value for this GenericChallengeParms.
     * 
     * @param authSecretParms
     */
    public void setAuthSecretParms(ws.internal.tokenkey.tnb.AuthenticationSecretParms authSecretParms) {
        this.authSecretParms = authSecretParms;
    }


    /**
     * Gets the sharedSecretParms value for this GenericChallengeParms.
     * 
     * @return sharedSecretParms
     */
    public ws.internal.tokenkey.tnb.SharedSecretParms getSharedSecretParms() {
        return sharedSecretParms;
    }


    /**
     * Sets the sharedSecretParms value for this GenericChallengeParms.
     * 
     * @param sharedSecretParms
     */
    public void setSharedSecretParms(ws.internal.tokenkey.tnb.SharedSecretParms sharedSecretParms) {
        this.sharedSecretParms = sharedSecretParms;
    }


    /**
     * Gets the IPAddress value for this GenericChallengeParms.
     * 
     * @return IPAddress
     */
    public java.lang.String getIPAddress() {
        return IPAddress;
    }


    /**
     * Sets the IPAddress value for this GenericChallengeParms.
     * 
     * @param IPAddress
     */
    public void setIPAddress(java.lang.String IPAddress) {
        this.IPAddress = IPAddress;
    }


    /**
     * Gets the updateMachineSecret value for this GenericChallengeParms.
     * 
     * @return updateMachineSecret
     */
    public java.lang.Boolean getUpdateMachineSecret() {
        return updateMachineSecret;
    }


    /**
     * Sets the updateMachineSecret value for this GenericChallengeParms.
     * 
     * @param updateMachineSecret
     */
    public void setUpdateMachineSecret(java.lang.Boolean updateMachineSecret) {
        this.updateMachineSecret = updateMachineSecret;
    }


    /**
     * Gets the registerMachineSecret value for this GenericChallengeParms.
     * 
     * @return registerMachineSecret
     */
    public java.lang.Boolean getRegisterMachineSecret() {
        return registerMachineSecret;
    }


    /**
     * Sets the registerMachineSecret value for this GenericChallengeParms.
     * 
     * @param registerMachineSecret
     */
    public void setRegisterMachineSecret(java.lang.Boolean registerMachineSecret) {
        this.registerMachineSecret = registerMachineSecret;
    }


    /**
     * Gets the machineSecret value for this GenericChallengeParms.
     * 
     * @return machineSecret
     */
    public ws.internal.tokenkey.tnb.MachineSecret getMachineSecret() {
        return machineSecret;
    }


    /**
     * Sets the machineSecret value for this GenericChallengeParms.
     * 
     * @param machineSecret
     */
    public void setMachineSecret(ws.internal.tokenkey.tnb.MachineSecret machineSecret) {
        this.machineSecret = machineSecret;
    }


    /**
     * Gets the registerCertificate value for this GenericChallengeParms.
     * 
     * @return registerCertificate
     */
    public java.lang.Boolean getRegisterCertificate() {
        return registerCertificate;
    }


    /**
     * Sets the registerCertificate value for this GenericChallengeParms.
     * 
     * @param registerCertificate
     */
    public void setRegisterCertificate(java.lang.Boolean registerCertificate) {
        this.registerCertificate = registerCertificate;
    }


    /**
     * Gets the certificate value for this GenericChallengeParms.
     * 
     * @return certificate
     */
    public java.lang.String getCertificate() {
        return certificate;
    }


    /**
     * Sets the certificate value for this GenericChallengeParms.
     * 
     * @param certificate
     */
    public void setCertificate(java.lang.String certificate) {
        this.certificate = certificate;
    }


    /**
     * Gets the externalRiskScore value for this GenericChallengeParms.
     * 
     * @return externalRiskScore
     */
    public ws.internal.tokenkey.tnb.ExternalRiskScoreParms getExternalRiskScore() {
        return externalRiskScore;
    }


    /**
     * Sets the externalRiskScore value for this GenericChallengeParms.
     * 
     * @param externalRiskScore
     */
    public void setExternalRiskScore(ws.internal.tokenkey.tnb.ExternalRiskScoreParms externalRiskScore) {
        this.externalRiskScore = externalRiskScore;
    }


    /**
     * Gets the useDefaultDelivery value for this GenericChallengeParms.
     * 
     * @return useDefaultDelivery
     */
    public java.lang.Boolean getUseDefaultDelivery() {
        return useDefaultDelivery;
    }


    /**
     * Sets the useDefaultDelivery value for this GenericChallengeParms.
     * 
     * @param useDefaultDelivery
     */
    public void setUseDefaultDelivery(java.lang.Boolean useDefaultDelivery) {
        this.useDefaultDelivery = useDefaultDelivery;
    }


    /**
     * Gets the contactInfoLabel value for this GenericChallengeParms.
     * 
     * @return contactInfoLabel
     */
    public java.lang.String[] getContactInfoLabel() {
        return contactInfoLabel;
    }


    /**
     * Sets the contactInfoLabel value for this GenericChallengeParms.
     * 
     * @param contactInfoLabel
     */
    public void setContactInfoLabel(java.lang.String[] contactInfoLabel) {
        this.contactInfoLabel = contactInfoLabel;
    }


    /**
     * Gets the deliverForDynamicRefresh value for this GenericChallengeParms.
     * 
     * @return deliverForDynamicRefresh
     */
    public java.lang.Boolean getDeliverForDynamicRefresh() {
        return deliverForDynamicRefresh;
    }


    /**
     * Sets the deliverForDynamicRefresh value for this GenericChallengeParms.
     * 
     * @param deliverForDynamicRefresh
     */
    public void setDeliverForDynamicRefresh(java.lang.Boolean deliverForDynamicRefresh) {
        this.deliverForDynamicRefresh = deliverForDynamicRefresh;
    }


    /**
     * Gets the onlySelectOTPAuthenticationIfDeliveryAvailable value for this GenericChallengeParms.
     * 
     * @return onlySelectOTPAuthenticationIfDeliveryAvailable
     */
    public java.lang.Boolean getOnlySelectOTPAuthenticationIfDeliveryAvailable() {
        return onlySelectOTPAuthenticationIfDeliveryAvailable;
    }


    /**
     * Sets the onlySelectOTPAuthenticationIfDeliveryAvailable value for this GenericChallengeParms.
     * 
     * @param onlySelectOTPAuthenticationIfDeliveryAvailable
     */
    public void setOnlySelectOTPAuthenticationIfDeliveryAvailable(java.lang.Boolean onlySelectOTPAuthenticationIfDeliveryAvailable) {
        this.onlySelectOTPAuthenticationIfDeliveryAvailable = onlySelectOTPAuthenticationIfDeliveryAvailable;
    }


    /**
     * Gets the transactionDetails value for this GenericChallengeParms.
     * 
     * @return transactionDetails
     */
    public ws.internal.tokenkey.tnb.com.entrust.NameValue[] getTransactionDetails() {
        return transactionDetails;
    }


    /**
     * Sets the transactionDetails value for this GenericChallengeParms.
     * 
     * @param transactionDetails
     */
    public void setTransactionDetails(ws.internal.tokenkey.tnb.com.entrust.NameValue[] transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GenericChallengeParms)) return false;
        GenericChallengeParms other = (GenericChallengeParms) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.challengeHistory==null && other.getChallengeHistory()==null) || 
             (this.challengeHistory!=null &&
              java.util.Arrays.equals(this.challengeHistory, other.getChallengeHistory()))) &&
            ((this.securityLevel==null && other.getSecurityLevel()==null) || 
             (this.securityLevel!=null &&
              this.securityLevel.equals(other.getSecurityLevel()))) &&
            ((this.authenticationType==null && other.getAuthenticationType()==null) || 
             (this.authenticationType!=null &&
              this.authenticationType.equals(other.getAuthenticationType()))) &&
            ((this.authenticationTypeList==null && other.getAuthenticationTypeList()==null) || 
             (this.authenticationTypeList!=null &&
              java.util.Arrays.equals(this.authenticationTypeList, other.getAuthenticationTypeList()))) &&
            ((this.applicationName==null && other.getApplicationName()==null) || 
             (this.applicationName!=null &&
              this.applicationName.equals(other.getApplicationName()))) &&
            ((this.requiresPVN==null && other.getRequiresPVN()==null) || 
             (this.requiresPVN!=null &&
              this.requiresPVN.equals(other.getRequiresPVN()))) &&
            ((this.authTypesRequiringPVN==null && other.getAuthTypesRequiringPVN()==null) || 
             (this.authTypesRequiringPVN!=null &&
              java.util.Arrays.equals(this.authTypesRequiringPVN, other.getAuthTypesRequiringPVN()))) &&
            ((this.gridChallengeSize==null && other.getGridChallengeSize()==null) || 
             (this.gridChallengeSize!=null &&
              this.gridChallengeSize.equals(other.getGridChallengeSize()))) &&
            ((this.QAChallengeSize==null && other.getQAChallengeSize()==null) || 
             (this.QAChallengeSize!=null &&
              this.QAChallengeSize.equals(other.getQAChallengeSize()))) &&
            ((this.authSecretParms==null && other.getAuthSecretParms()==null) || 
             (this.authSecretParms!=null &&
              this.authSecretParms.equals(other.getAuthSecretParms()))) &&
            ((this.sharedSecretParms==null && other.getSharedSecretParms()==null) || 
             (this.sharedSecretParms!=null &&
              this.sharedSecretParms.equals(other.getSharedSecretParms()))) &&
            ((this.IPAddress==null && other.getIPAddress()==null) || 
             (this.IPAddress!=null &&
              this.IPAddress.equals(other.getIPAddress()))) &&
            ((this.updateMachineSecret==null && other.getUpdateMachineSecret()==null) || 
             (this.updateMachineSecret!=null &&
              this.updateMachineSecret.equals(other.getUpdateMachineSecret()))) &&
            ((this.registerMachineSecret==null && other.getRegisterMachineSecret()==null) || 
             (this.registerMachineSecret!=null &&
              this.registerMachineSecret.equals(other.getRegisterMachineSecret()))) &&
            ((this.machineSecret==null && other.getMachineSecret()==null) || 
             (this.machineSecret!=null &&
              this.machineSecret.equals(other.getMachineSecret()))) &&
            ((this.registerCertificate==null && other.getRegisterCertificate()==null) || 
             (this.registerCertificate!=null &&
              this.registerCertificate.equals(other.getRegisterCertificate()))) &&
            ((this.certificate==null && other.getCertificate()==null) || 
             (this.certificate!=null &&
              this.certificate.equals(other.getCertificate()))) &&
            ((this.externalRiskScore==null && other.getExternalRiskScore()==null) || 
             (this.externalRiskScore!=null &&
              this.externalRiskScore.equals(other.getExternalRiskScore()))) &&
            ((this.useDefaultDelivery==null && other.getUseDefaultDelivery()==null) || 
             (this.useDefaultDelivery!=null &&
              this.useDefaultDelivery.equals(other.getUseDefaultDelivery()))) &&
            ((this.contactInfoLabel==null && other.getContactInfoLabel()==null) || 
             (this.contactInfoLabel!=null &&
              java.util.Arrays.equals(this.contactInfoLabel, other.getContactInfoLabel()))) &&
            ((this.deliverForDynamicRefresh==null && other.getDeliverForDynamicRefresh()==null) || 
             (this.deliverForDynamicRefresh!=null &&
              this.deliverForDynamicRefresh.equals(other.getDeliverForDynamicRefresh()))) &&
            ((this.onlySelectOTPAuthenticationIfDeliveryAvailable==null && other.getOnlySelectOTPAuthenticationIfDeliveryAvailable()==null) || 
             (this.onlySelectOTPAuthenticationIfDeliveryAvailable!=null &&
              this.onlySelectOTPAuthenticationIfDeliveryAvailable.equals(other.getOnlySelectOTPAuthenticationIfDeliveryAvailable()))) &&
            ((this.transactionDetails==null && other.getTransactionDetails()==null) || 
             (this.transactionDetails!=null &&
              java.util.Arrays.equals(this.transactionDetails, other.getTransactionDetails())));
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
        if (getSecurityLevel() != null) {
            _hashCode += getSecurityLevel().hashCode();
        }
        if (getAuthenticationType() != null) {
            _hashCode += getAuthenticationType().hashCode();
        }
        if (getAuthenticationTypeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAuthenticationTypeList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAuthenticationTypeList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getApplicationName() != null) {
            _hashCode += getApplicationName().hashCode();
        }
        if (getRequiresPVN() != null) {
            _hashCode += getRequiresPVN().hashCode();
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
        if (getGridChallengeSize() != null) {
            _hashCode += getGridChallengeSize().hashCode();
        }
        if (getQAChallengeSize() != null) {
            _hashCode += getQAChallengeSize().hashCode();
        }
        if (getAuthSecretParms() != null) {
            _hashCode += getAuthSecretParms().hashCode();
        }
        if (getSharedSecretParms() != null) {
            _hashCode += getSharedSecretParms().hashCode();
        }
        if (getIPAddress() != null) {
            _hashCode += getIPAddress().hashCode();
        }
        if (getUpdateMachineSecret() != null) {
            _hashCode += getUpdateMachineSecret().hashCode();
        }
        if (getRegisterMachineSecret() != null) {
            _hashCode += getRegisterMachineSecret().hashCode();
        }
        if (getMachineSecret() != null) {
            _hashCode += getMachineSecret().hashCode();
        }
        if (getRegisterCertificate() != null) {
            _hashCode += getRegisterCertificate().hashCode();
        }
        if (getCertificate() != null) {
            _hashCode += getCertificate().hashCode();
        }
        if (getExternalRiskScore() != null) {
            _hashCode += getExternalRiskScore().hashCode();
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
        if (getOnlySelectOTPAuthenticationIfDeliveryAvailable() != null) {
            _hashCode += getOnlySelectOTPAuthenticationIfDeliveryAvailable().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GenericChallengeParms.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GenericChallengeParms"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("challengeHistory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ChallengeHistory"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationType"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("authenticationTypeList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AuthenticationTypeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationType"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ApplicationName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requiresPVN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequiresPVN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("gridChallengeSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GridChallengeSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("QAChallengeSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "QAChallengeSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
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
        elemField.setFieldName("IPAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IPAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateMachineSecret");
        elemField.setXmlName(new javax.xml.namespace.QName("", "updateMachineSecret"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("registerCertificate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "registerCertificate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "certificate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("externalRiskScore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "externalRiskScore"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "ExternalRiskScoreParms"));
        elemField.setNillable(true);
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
        elemField.setFieldName("onlySelectOTPAuthenticationIfDeliveryAvailable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "onlySelectOTPAuthenticationIfDeliveryAvailable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "NameValue"));
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
