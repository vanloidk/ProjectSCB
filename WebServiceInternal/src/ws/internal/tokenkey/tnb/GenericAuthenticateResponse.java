/**
 * GenericAuthenticateResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class GenericAuthenticateResponse  implements java.io.Serializable {
    private ws.internal.tokenkey.tnb.com.entrust.CardData cardInfo;

    private ws.internal.tokenkey.tnb.com.entrust.TokenData tokenInfo;

    private ws.internal.tokenkey.tnb.PasswordInfo passwordInfo;

    private ws.internal.tokenkey.tnb.com.entrust.CertificateData certificateInfo;

    private ws.internal.tokenkey.tnb.com.entrust.NameValue[] authenticationSecrets;

    private ws.internal.tokenkey.tnb.com.entrust.SharedSecret[] sharedSecrets;

    private ws.internal.tokenkey.tnb.AuthenticationFault warningFault;

    private ws.internal.tokenkey.tnb.MachineSecret machineSecret;

    private ws.internal.tokenkey.tnb.com.entrust.IPLocation IPLocation;

    private ws.internal.tokenkey.tnb.com.entrust.CertificateData certificateRegistered;

    private java.lang.String[] challengeHistory;

    private byte[] MSCHAPV2AuthenticatorResponse;

    private ws.internal.tokenkey.tnb.TransactionReceiptInfo transactionReceiptInfo;

    private ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanismUsed;

    private ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanismFailed;

    private ws.internal.tokenkey.tnb.AuthenticationFault[] deliveryMechanismFailureReason;

    private java.lang.Boolean OTPDynamicRefresh;

    private java.lang.Boolean OTPNewGenerated;

    private java.lang.String group;

    private java.lang.String fullName;

    private ws.internal.tokenkey.tnb.com.entrust.AuthenticationInfo lastAuth;

    private ws.internal.tokenkey.tnb.com.entrust.AuthenticationInfo lastFailedAuth;

    private ws.internal.tokenkey.tnb.com.entrust.NameValues[] retrievedRepositoryAttributes;

    public GenericAuthenticateResponse() {
    }

    public GenericAuthenticateResponse(
           ws.internal.tokenkey.tnb.com.entrust.CardData cardInfo,
           ws.internal.tokenkey.tnb.com.entrust.TokenData tokenInfo,
           ws.internal.tokenkey.tnb.PasswordInfo passwordInfo,
           ws.internal.tokenkey.tnb.com.entrust.CertificateData certificateInfo,
           ws.internal.tokenkey.tnb.com.entrust.NameValue[] authenticationSecrets,
           ws.internal.tokenkey.tnb.com.entrust.SharedSecret[] sharedSecrets,
           ws.internal.tokenkey.tnb.AuthenticationFault warningFault,
           ws.internal.tokenkey.tnb.MachineSecret machineSecret,
           ws.internal.tokenkey.tnb.com.entrust.IPLocation IPLocation,
           ws.internal.tokenkey.tnb.com.entrust.CertificateData certificateRegistered,
           java.lang.String[] challengeHistory,
           byte[] MSCHAPV2AuthenticatorResponse,
           ws.internal.tokenkey.tnb.TransactionReceiptInfo transactionReceiptInfo,
           ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanismUsed,
           ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanismFailed,
           ws.internal.tokenkey.tnb.AuthenticationFault[] deliveryMechanismFailureReason,
           java.lang.Boolean OTPDynamicRefresh,
           java.lang.Boolean OTPNewGenerated,
           java.lang.String group,
           java.lang.String fullName,
           ws.internal.tokenkey.tnb.com.entrust.AuthenticationInfo lastAuth,
           ws.internal.tokenkey.tnb.com.entrust.AuthenticationInfo lastFailedAuth,
           ws.internal.tokenkey.tnb.com.entrust.NameValues[] retrievedRepositoryAttributes) {
           this.cardInfo = cardInfo;
           this.tokenInfo = tokenInfo;
           this.passwordInfo = passwordInfo;
           this.certificateInfo = certificateInfo;
           this.authenticationSecrets = authenticationSecrets;
           this.sharedSecrets = sharedSecrets;
           this.warningFault = warningFault;
           this.machineSecret = machineSecret;
           this.IPLocation = IPLocation;
           this.certificateRegistered = certificateRegistered;
           this.challengeHistory = challengeHistory;
           this.MSCHAPV2AuthenticatorResponse = MSCHAPV2AuthenticatorResponse;
           this.transactionReceiptInfo = transactionReceiptInfo;
           this.deliveryMechanismUsed = deliveryMechanismUsed;
           this.deliveryMechanismFailed = deliveryMechanismFailed;
           this.deliveryMechanismFailureReason = deliveryMechanismFailureReason;
           this.OTPDynamicRefresh = OTPDynamicRefresh;
           this.OTPNewGenerated = OTPNewGenerated;
           this.group = group;
           this.fullName = fullName;
           this.lastAuth = lastAuth;
           this.lastFailedAuth = lastFailedAuth;
           this.retrievedRepositoryAttributes = retrievedRepositoryAttributes;
    }


    /**
     * Gets the cardInfo value for this GenericAuthenticateResponse.
     * 
     * @return cardInfo
     */
    public ws.internal.tokenkey.tnb.com.entrust.CardData getCardInfo() {
        return cardInfo;
    }


    /**
     * Sets the cardInfo value for this GenericAuthenticateResponse.
     * 
     * @param cardInfo
     */
    public void setCardInfo(ws.internal.tokenkey.tnb.com.entrust.CardData cardInfo) {
        this.cardInfo = cardInfo;
    }


    /**
     * Gets the tokenInfo value for this GenericAuthenticateResponse.
     * 
     * @return tokenInfo
     */
    public ws.internal.tokenkey.tnb.com.entrust.TokenData getTokenInfo() {
        return tokenInfo;
    }


    /**
     * Sets the tokenInfo value for this GenericAuthenticateResponse.
     * 
     * @param tokenInfo
     */
    public void setTokenInfo(ws.internal.tokenkey.tnb.com.entrust.TokenData tokenInfo) {
        this.tokenInfo = tokenInfo;
    }


    /**
     * Gets the passwordInfo value for this GenericAuthenticateResponse.
     * 
     * @return passwordInfo
     */
    public ws.internal.tokenkey.tnb.PasswordInfo getPasswordInfo() {
        return passwordInfo;
    }


    /**
     * Sets the passwordInfo value for this GenericAuthenticateResponse.
     * 
     * @param passwordInfo
     */
    public void setPasswordInfo(ws.internal.tokenkey.tnb.PasswordInfo passwordInfo) {
        this.passwordInfo = passwordInfo;
    }


    /**
     * Gets the certificateInfo value for this GenericAuthenticateResponse.
     * 
     * @return certificateInfo
     */
    public ws.internal.tokenkey.tnb.com.entrust.CertificateData getCertificateInfo() {
        return certificateInfo;
    }


    /**
     * Sets the certificateInfo value for this GenericAuthenticateResponse.
     * 
     * @param certificateInfo
     */
    public void setCertificateInfo(ws.internal.tokenkey.tnb.com.entrust.CertificateData certificateInfo) {
        this.certificateInfo = certificateInfo;
    }


    /**
     * Gets the authenticationSecrets value for this GenericAuthenticateResponse.
     * 
     * @return authenticationSecrets
     */
    public ws.internal.tokenkey.tnb.com.entrust.NameValue[] getAuthenticationSecrets() {
        return authenticationSecrets;
    }


    /**
     * Sets the authenticationSecrets value for this GenericAuthenticateResponse.
     * 
     * @param authenticationSecrets
     */
    public void setAuthenticationSecrets(ws.internal.tokenkey.tnb.com.entrust.NameValue[] authenticationSecrets) {
        this.authenticationSecrets = authenticationSecrets;
    }


    /**
     * Gets the sharedSecrets value for this GenericAuthenticateResponse.
     * 
     * @return sharedSecrets
     */
    public ws.internal.tokenkey.tnb.com.entrust.SharedSecret[] getSharedSecrets() {
        return sharedSecrets;
    }


    /**
     * Sets the sharedSecrets value for this GenericAuthenticateResponse.
     * 
     * @param sharedSecrets
     */
    public void setSharedSecrets(ws.internal.tokenkey.tnb.com.entrust.SharedSecret[] sharedSecrets) {
        this.sharedSecrets = sharedSecrets;
    }


    /**
     * Gets the warningFault value for this GenericAuthenticateResponse.
     * 
     * @return warningFault
     */
    public ws.internal.tokenkey.tnb.AuthenticationFault getWarningFault() {
        return warningFault;
    }


    /**
     * Sets the warningFault value for this GenericAuthenticateResponse.
     * 
     * @param warningFault
     */
    public void setWarningFault(ws.internal.tokenkey.tnb.AuthenticationFault warningFault) {
        this.warningFault = warningFault;
    }


    /**
     * Gets the machineSecret value for this GenericAuthenticateResponse.
     * 
     * @return machineSecret
     */
    public ws.internal.tokenkey.tnb.MachineSecret getMachineSecret() {
        return machineSecret;
    }


    /**
     * Sets the machineSecret value for this GenericAuthenticateResponse.
     * 
     * @param machineSecret
     */
    public void setMachineSecret(ws.internal.tokenkey.tnb.MachineSecret machineSecret) {
        this.machineSecret = machineSecret;
    }


    /**
     * Gets the IPLocation value for this GenericAuthenticateResponse.
     * 
     * @return IPLocation
     */
    public ws.internal.tokenkey.tnb.com.entrust.IPLocation getIPLocation() {
        return IPLocation;
    }


    /**
     * Sets the IPLocation value for this GenericAuthenticateResponse.
     * 
     * @param IPLocation
     */
    public void setIPLocation(ws.internal.tokenkey.tnb.com.entrust.IPLocation IPLocation) {
        this.IPLocation = IPLocation;
    }


    /**
     * Gets the certificateRegistered value for this GenericAuthenticateResponse.
     * 
     * @return certificateRegistered
     */
    public ws.internal.tokenkey.tnb.com.entrust.CertificateData getCertificateRegistered() {
        return certificateRegistered;
    }


    /**
     * Sets the certificateRegistered value for this GenericAuthenticateResponse.
     * 
     * @param certificateRegistered
     */
    public void setCertificateRegistered(ws.internal.tokenkey.tnb.com.entrust.CertificateData certificateRegistered) {
        this.certificateRegistered = certificateRegistered;
    }


    /**
     * Gets the challengeHistory value for this GenericAuthenticateResponse.
     * 
     * @return challengeHistory
     */
    public java.lang.String[] getChallengeHistory() {
        return challengeHistory;
    }


    /**
     * Sets the challengeHistory value for this GenericAuthenticateResponse.
     * 
     * @param challengeHistory
     */
    public void setChallengeHistory(java.lang.String[] challengeHistory) {
        this.challengeHistory = challengeHistory;
    }


    /**
     * Gets the MSCHAPV2AuthenticatorResponse value for this GenericAuthenticateResponse.
     * 
     * @return MSCHAPV2AuthenticatorResponse
     */
    public byte[] getMSCHAPV2AuthenticatorResponse() {
        return MSCHAPV2AuthenticatorResponse;
    }


    /**
     * Sets the MSCHAPV2AuthenticatorResponse value for this GenericAuthenticateResponse.
     * 
     * @param MSCHAPV2AuthenticatorResponse
     */
    public void setMSCHAPV2AuthenticatorResponse(byte[] MSCHAPV2AuthenticatorResponse) {
        this.MSCHAPV2AuthenticatorResponse = MSCHAPV2AuthenticatorResponse;
    }


    /**
     * Gets the transactionReceiptInfo value for this GenericAuthenticateResponse.
     * 
     * @return transactionReceiptInfo
     */
    public ws.internal.tokenkey.tnb.TransactionReceiptInfo getTransactionReceiptInfo() {
        return transactionReceiptInfo;
    }


    /**
     * Sets the transactionReceiptInfo value for this GenericAuthenticateResponse.
     * 
     * @param transactionReceiptInfo
     */
    public void setTransactionReceiptInfo(ws.internal.tokenkey.tnb.TransactionReceiptInfo transactionReceiptInfo) {
        this.transactionReceiptInfo = transactionReceiptInfo;
    }


    /**
     * Gets the deliveryMechanismUsed value for this GenericAuthenticateResponse.
     * 
     * @return deliveryMechanismUsed
     */
    public ws.internal.tokenkey.tnb.DeliveryMechanism[] getDeliveryMechanismUsed() {
        return deliveryMechanismUsed;
    }


    /**
     * Sets the deliveryMechanismUsed value for this GenericAuthenticateResponse.
     * 
     * @param deliveryMechanismUsed
     */
    public void setDeliveryMechanismUsed(ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanismUsed) {
        this.deliveryMechanismUsed = deliveryMechanismUsed;
    }


    /**
     * Gets the deliveryMechanismFailed value for this GenericAuthenticateResponse.
     * 
     * @return deliveryMechanismFailed
     */
    public ws.internal.tokenkey.tnb.DeliveryMechanism[] getDeliveryMechanismFailed() {
        return deliveryMechanismFailed;
    }


    /**
     * Sets the deliveryMechanismFailed value for this GenericAuthenticateResponse.
     * 
     * @param deliveryMechanismFailed
     */
    public void setDeliveryMechanismFailed(ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanismFailed) {
        this.deliveryMechanismFailed = deliveryMechanismFailed;
    }


    /**
     * Gets the deliveryMechanismFailureReason value for this GenericAuthenticateResponse.
     * 
     * @return deliveryMechanismFailureReason
     */
    public ws.internal.tokenkey.tnb.AuthenticationFault[] getDeliveryMechanismFailureReason() {
        return deliveryMechanismFailureReason;
    }


    /**
     * Sets the deliveryMechanismFailureReason value for this GenericAuthenticateResponse.
     * 
     * @param deliveryMechanismFailureReason
     */
    public void setDeliveryMechanismFailureReason(ws.internal.tokenkey.tnb.AuthenticationFault[] deliveryMechanismFailureReason) {
        this.deliveryMechanismFailureReason = deliveryMechanismFailureReason;
    }


    /**
     * Gets the OTPDynamicRefresh value for this GenericAuthenticateResponse.
     * 
     * @return OTPDynamicRefresh
     */
    public java.lang.Boolean getOTPDynamicRefresh() {
        return OTPDynamicRefresh;
    }


    /**
     * Sets the OTPDynamicRefresh value for this GenericAuthenticateResponse.
     * 
     * @param OTPDynamicRefresh
     */
    public void setOTPDynamicRefresh(java.lang.Boolean OTPDynamicRefresh) {
        this.OTPDynamicRefresh = OTPDynamicRefresh;
    }


    /**
     * Gets the OTPNewGenerated value for this GenericAuthenticateResponse.
     * 
     * @return OTPNewGenerated
     */
    public java.lang.Boolean getOTPNewGenerated() {
        return OTPNewGenerated;
    }


    /**
     * Sets the OTPNewGenerated value for this GenericAuthenticateResponse.
     * 
     * @param OTPNewGenerated
     */
    public void setOTPNewGenerated(java.lang.Boolean OTPNewGenerated) {
        this.OTPNewGenerated = OTPNewGenerated;
    }


    /**
     * Gets the group value for this GenericAuthenticateResponse.
     * 
     * @return group
     */
    public java.lang.String getGroup() {
        return group;
    }


    /**
     * Sets the group value for this GenericAuthenticateResponse.
     * 
     * @param group
     */
    public void setGroup(java.lang.String group) {
        this.group = group;
    }


    /**
     * Gets the fullName value for this GenericAuthenticateResponse.
     * 
     * @return fullName
     */
    public java.lang.String getFullName() {
        return fullName;
    }


    /**
     * Sets the fullName value for this GenericAuthenticateResponse.
     * 
     * @param fullName
     */
    public void setFullName(java.lang.String fullName) {
        this.fullName = fullName;
    }


    /**
     * Gets the lastAuth value for this GenericAuthenticateResponse.
     * 
     * @return lastAuth
     */
    public ws.internal.tokenkey.tnb.com.entrust.AuthenticationInfo getLastAuth() {
        return lastAuth;
    }


    /**
     * Sets the lastAuth value for this GenericAuthenticateResponse.
     * 
     * @param lastAuth
     */
    public void setLastAuth(ws.internal.tokenkey.tnb.com.entrust.AuthenticationInfo lastAuth) {
        this.lastAuth = lastAuth;
    }


    /**
     * Gets the lastFailedAuth value for this GenericAuthenticateResponse.
     * 
     * @return lastFailedAuth
     */
    public ws.internal.tokenkey.tnb.com.entrust.AuthenticationInfo getLastFailedAuth() {
        return lastFailedAuth;
    }


    /**
     * Sets the lastFailedAuth value for this GenericAuthenticateResponse.
     * 
     * @param lastFailedAuth
     */
    public void setLastFailedAuth(ws.internal.tokenkey.tnb.com.entrust.AuthenticationInfo lastFailedAuth) {
        this.lastFailedAuth = lastFailedAuth;
    }


    /**
     * Gets the retrievedRepositoryAttributes value for this GenericAuthenticateResponse.
     * 
     * @return retrievedRepositoryAttributes
     */
    public ws.internal.tokenkey.tnb.com.entrust.NameValues[] getRetrievedRepositoryAttributes() {
        return retrievedRepositoryAttributes;
    }


    /**
     * Sets the retrievedRepositoryAttributes value for this GenericAuthenticateResponse.
     * 
     * @param retrievedRepositoryAttributes
     */
    public void setRetrievedRepositoryAttributes(ws.internal.tokenkey.tnb.com.entrust.NameValues[] retrievedRepositoryAttributes) {
        this.retrievedRepositoryAttributes = retrievedRepositoryAttributes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GenericAuthenticateResponse)) return false;
        GenericAuthenticateResponse other = (GenericAuthenticateResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cardInfo==null && other.getCardInfo()==null) || 
             (this.cardInfo!=null &&
              this.cardInfo.equals(other.getCardInfo()))) &&
            ((this.tokenInfo==null && other.getTokenInfo()==null) || 
             (this.tokenInfo!=null &&
              this.tokenInfo.equals(other.getTokenInfo()))) &&
            ((this.passwordInfo==null && other.getPasswordInfo()==null) || 
             (this.passwordInfo!=null &&
              this.passwordInfo.equals(other.getPasswordInfo()))) &&
            ((this.certificateInfo==null && other.getCertificateInfo()==null) || 
             (this.certificateInfo!=null &&
              this.certificateInfo.equals(other.getCertificateInfo()))) &&
            ((this.authenticationSecrets==null && other.getAuthenticationSecrets()==null) || 
             (this.authenticationSecrets!=null &&
              java.util.Arrays.equals(this.authenticationSecrets, other.getAuthenticationSecrets()))) &&
            ((this.sharedSecrets==null && other.getSharedSecrets()==null) || 
             (this.sharedSecrets!=null &&
              java.util.Arrays.equals(this.sharedSecrets, other.getSharedSecrets()))) &&
            ((this.warningFault==null && other.getWarningFault()==null) || 
             (this.warningFault!=null &&
              this.warningFault.equals(other.getWarningFault()))) &&
            ((this.machineSecret==null && other.getMachineSecret()==null) || 
             (this.machineSecret!=null &&
              this.machineSecret.equals(other.getMachineSecret()))) &&
            ((this.IPLocation==null && other.getIPLocation()==null) || 
             (this.IPLocation!=null &&
              this.IPLocation.equals(other.getIPLocation()))) &&
            ((this.certificateRegistered==null && other.getCertificateRegistered()==null) || 
             (this.certificateRegistered!=null &&
              this.certificateRegistered.equals(other.getCertificateRegistered()))) &&
            ((this.challengeHistory==null && other.getChallengeHistory()==null) || 
             (this.challengeHistory!=null &&
              java.util.Arrays.equals(this.challengeHistory, other.getChallengeHistory()))) &&
            ((this.MSCHAPV2AuthenticatorResponse==null && other.getMSCHAPV2AuthenticatorResponse()==null) || 
             (this.MSCHAPV2AuthenticatorResponse!=null &&
              java.util.Arrays.equals(this.MSCHAPV2AuthenticatorResponse, other.getMSCHAPV2AuthenticatorResponse()))) &&
            ((this.transactionReceiptInfo==null && other.getTransactionReceiptInfo()==null) || 
             (this.transactionReceiptInfo!=null &&
              this.transactionReceiptInfo.equals(other.getTransactionReceiptInfo()))) &&
            ((this.deliveryMechanismUsed==null && other.getDeliveryMechanismUsed()==null) || 
             (this.deliveryMechanismUsed!=null &&
              java.util.Arrays.equals(this.deliveryMechanismUsed, other.getDeliveryMechanismUsed()))) &&
            ((this.deliveryMechanismFailed==null && other.getDeliveryMechanismFailed()==null) || 
             (this.deliveryMechanismFailed!=null &&
              java.util.Arrays.equals(this.deliveryMechanismFailed, other.getDeliveryMechanismFailed()))) &&
            ((this.deliveryMechanismFailureReason==null && other.getDeliveryMechanismFailureReason()==null) || 
             (this.deliveryMechanismFailureReason!=null &&
              java.util.Arrays.equals(this.deliveryMechanismFailureReason, other.getDeliveryMechanismFailureReason()))) &&
            ((this.OTPDynamicRefresh==null && other.getOTPDynamicRefresh()==null) || 
             (this.OTPDynamicRefresh!=null &&
              this.OTPDynamicRefresh.equals(other.getOTPDynamicRefresh()))) &&
            ((this.OTPNewGenerated==null && other.getOTPNewGenerated()==null) || 
             (this.OTPNewGenerated!=null &&
              this.OTPNewGenerated.equals(other.getOTPNewGenerated()))) &&
            ((this.group==null && other.getGroup()==null) || 
             (this.group!=null &&
              this.group.equals(other.getGroup()))) &&
            ((this.fullName==null && other.getFullName()==null) || 
             (this.fullName!=null &&
              this.fullName.equals(other.getFullName()))) &&
            ((this.lastAuth==null && other.getLastAuth()==null) || 
             (this.lastAuth!=null &&
              this.lastAuth.equals(other.getLastAuth()))) &&
            ((this.lastFailedAuth==null && other.getLastFailedAuth()==null) || 
             (this.lastFailedAuth!=null &&
              this.lastFailedAuth.equals(other.getLastFailedAuth()))) &&
            ((this.retrievedRepositoryAttributes==null && other.getRetrievedRepositoryAttributes()==null) || 
             (this.retrievedRepositoryAttributes!=null &&
              java.util.Arrays.equals(this.retrievedRepositoryAttributes, other.getRetrievedRepositoryAttributes())));
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
        if (getCardInfo() != null) {
            _hashCode += getCardInfo().hashCode();
        }
        if (getTokenInfo() != null) {
            _hashCode += getTokenInfo().hashCode();
        }
        if (getPasswordInfo() != null) {
            _hashCode += getPasswordInfo().hashCode();
        }
        if (getCertificateInfo() != null) {
            _hashCode += getCertificateInfo().hashCode();
        }
        if (getAuthenticationSecrets() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAuthenticationSecrets());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAuthenticationSecrets(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSharedSecrets() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSharedSecrets());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSharedSecrets(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getWarningFault() != null) {
            _hashCode += getWarningFault().hashCode();
        }
        if (getMachineSecret() != null) {
            _hashCode += getMachineSecret().hashCode();
        }
        if (getIPLocation() != null) {
            _hashCode += getIPLocation().hashCode();
        }
        if (getCertificateRegistered() != null) {
            _hashCode += getCertificateRegistered().hashCode();
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
        if (getMSCHAPV2AuthenticatorResponse() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMSCHAPV2AuthenticatorResponse());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMSCHAPV2AuthenticatorResponse(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTransactionReceiptInfo() != null) {
            _hashCode += getTransactionReceiptInfo().hashCode();
        }
        if (getDeliveryMechanismUsed() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDeliveryMechanismUsed());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDeliveryMechanismUsed(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDeliveryMechanismFailed() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDeliveryMechanismFailed());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDeliveryMechanismFailed(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDeliveryMechanismFailureReason() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDeliveryMechanismFailureReason());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDeliveryMechanismFailureReason(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOTPDynamicRefresh() != null) {
            _hashCode += getOTPDynamicRefresh().hashCode();
        }
        if (getOTPNewGenerated() != null) {
            _hashCode += getOTPNewGenerated().hashCode();
        }
        if (getGroup() != null) {
            _hashCode += getGroup().hashCode();
        }
        if (getFullName() != null) {
            _hashCode += getFullName().hashCode();
        }
        if (getLastAuth() != null) {
            _hashCode += getLastAuth().hashCode();
        }
        if (getLastFailedAuth() != null) {
            _hashCode += getLastFailedAuth().hashCode();
        }
        if (getRetrievedRepositoryAttributes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRetrievedRepositoryAttributes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRetrievedRepositoryAttributes(), i);
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
        new org.apache.axis.description.TypeDesc(GenericAuthenticateResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GenericAuthenticateResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "CardData"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tokenInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tokenInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "TokenData"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passwordInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PasswordInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "PasswordInfo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificateInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "certificateInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "CertificateData"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authenticationSecrets");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AuthenticationSecrets"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "NameValue"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sharedSecrets");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SharedSecrets"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "SharedSecret"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("warningFault");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WarningFault"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationFault"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("machineSecret");
        elemField.setXmlName(new javax.xml.namespace.QName("", "machineSecret"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MachineSecret"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IPLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "IPLocation"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificateRegistered");
        elemField.setXmlName(new javax.xml.namespace.QName("", "certificateRegistered"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "CertificateData"));
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
        elemField.setFieldName("MSCHAPV2AuthenticatorResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MSCHAPV2AuthenticatorResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionReceiptInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionReceiptInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "TransactionReceiptInfo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryMechanismUsed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliveryMechanismUsed"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "DeliveryMechanism"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryMechanismFailed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliveryMechanismFailed"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "DeliveryMechanism"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryMechanismFailureReason");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliveryMechanismFailureReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationFault"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OTPDynamicRefresh");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OTPDynamicRefresh"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OTPNewGenerated");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OTPNewGenerated"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("group");
        elemField.setXmlName(new javax.xml.namespace.QName("", "group"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FullName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastAuth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastAuth"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationInfo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastFailedAuth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastFailedAuth"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationInfo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retrievedRepositoryAttributes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "retrievedRepositoryAttributes"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "NameValues"));
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
