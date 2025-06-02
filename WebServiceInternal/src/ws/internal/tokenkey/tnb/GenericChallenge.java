/**
 * GenericChallenge.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class GenericChallenge  implements java.io.Serializable {
    private java.lang.String challengeRequestResult;

    private ws.internal.tokenkey.tnb.RiskScoringResult riskScoringResult;

    private java.lang.String type;

    private ws.internal.tokenkey.tnb.PVNInfo PVNInfo;

    private ws.internal.tokenkey.tnb.com.entrust.ChallengeSet gridChallenge;

    private java.lang.String[] QAChallenge;

    private ws.internal.tokenkey.tnb.com.entrust.TokenChallenge tokenChallenge;

    private ws.internal.tokenkey.tnb.OTPChallenge OTPChallenge;

    private ws.internal.tokenkey.tnb.PasswordChallenge passwordChallenge;

    private ws.internal.tokenkey.tnb.com.entrust.CertificateChallenge certificateChallenge;

    private ws.internal.tokenkey.tnb.ExternalChallenge externalChallenge;

    private ws.internal.tokenkey.tnb.MachineSecret machineSecret;

    private ws.internal.tokenkey.tnb.MachineSecretPolicy machineSecretPolicy;

    private ws.internal.tokenkey.tnb.com.entrust.IPLocation IPLocation;

    private ws.internal.tokenkey.tnb.com.entrust.CertificateData certificate;

    private ws.internal.tokenkey.tnb.com.entrust.NameValue[] authenticationSecrets;

    private ws.internal.tokenkey.tnb.com.entrust.SharedSecret[] sharedSecrets;

    private ws.internal.tokenkey.tnb.AuthenticationFault warningFault;

    private java.lang.String group;

    public GenericChallenge() {
    }

    public GenericChallenge(
           java.lang.String challengeRequestResult,
           ws.internal.tokenkey.tnb.RiskScoringResult riskScoringResult,
           java.lang.String type,
           ws.internal.tokenkey.tnb.PVNInfo PVNInfo,
           ws.internal.tokenkey.tnb.com.entrust.ChallengeSet gridChallenge,
           java.lang.String[] QAChallenge,
           ws.internal.tokenkey.tnb.com.entrust.TokenChallenge tokenChallenge,
           ws.internal.tokenkey.tnb.OTPChallenge OTPChallenge,
           ws.internal.tokenkey.tnb.PasswordChallenge passwordChallenge,
           ws.internal.tokenkey.tnb.com.entrust.CertificateChallenge certificateChallenge,
           ws.internal.tokenkey.tnb.ExternalChallenge externalChallenge,
           ws.internal.tokenkey.tnb.MachineSecret machineSecret,
           ws.internal.tokenkey.tnb.MachineSecretPolicy machineSecretPolicy,
           ws.internal.tokenkey.tnb.com.entrust.IPLocation IPLocation,
           ws.internal.tokenkey.tnb.com.entrust.CertificateData certificate,
           ws.internal.tokenkey.tnb.com.entrust.NameValue[] authenticationSecrets,
           ws.internal.tokenkey.tnb.com.entrust.SharedSecret[] sharedSecrets,
           ws.internal.tokenkey.tnb.AuthenticationFault warningFault,
           java.lang.String group) {
           this.challengeRequestResult = challengeRequestResult;
           this.riskScoringResult = riskScoringResult;
           this.type = type;
           this.PVNInfo = PVNInfo;
           this.gridChallenge = gridChallenge;
           this.QAChallenge = QAChallenge;
           this.tokenChallenge = tokenChallenge;
           this.OTPChallenge = OTPChallenge;
           this.passwordChallenge = passwordChallenge;
           this.certificateChallenge = certificateChallenge;
           this.externalChallenge = externalChallenge;
           this.machineSecret = machineSecret;
           this.machineSecretPolicy = machineSecretPolicy;
           this.IPLocation = IPLocation;
           this.certificate = certificate;
           this.authenticationSecrets = authenticationSecrets;
           this.sharedSecrets = sharedSecrets;
           this.warningFault = warningFault;
           this.group = group;
    }


    /**
     * Gets the challengeRequestResult value for this GenericChallenge.
     * 
     * @return challengeRequestResult
     */
    public java.lang.String getChallengeRequestResult() {
        return challengeRequestResult;
    }


    /**
     * Sets the challengeRequestResult value for this GenericChallenge.
     * 
     * @param challengeRequestResult
     */
    public void setChallengeRequestResult(java.lang.String challengeRequestResult) {
        this.challengeRequestResult = challengeRequestResult;
    }


    /**
     * Gets the riskScoringResult value for this GenericChallenge.
     * 
     * @return riskScoringResult
     */
    public ws.internal.tokenkey.tnb.RiskScoringResult getRiskScoringResult() {
        return riskScoringResult;
    }


    /**
     * Sets the riskScoringResult value for this GenericChallenge.
     * 
     * @param riskScoringResult
     */
    public void setRiskScoringResult(ws.internal.tokenkey.tnb.RiskScoringResult riskScoringResult) {
        this.riskScoringResult = riskScoringResult;
    }


    /**
     * Gets the type value for this GenericChallenge.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this GenericChallenge.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the PVNInfo value for this GenericChallenge.
     * 
     * @return PVNInfo
     */
    public ws.internal.tokenkey.tnb.PVNInfo getPVNInfo() {
        return PVNInfo;
    }


    /**
     * Sets the PVNInfo value for this GenericChallenge.
     * 
     * @param PVNInfo
     */
    public void setPVNInfo(ws.internal.tokenkey.tnb.PVNInfo PVNInfo) {
        this.PVNInfo = PVNInfo;
    }


    /**
     * Gets the gridChallenge value for this GenericChallenge.
     * 
     * @return gridChallenge
     */
    public ws.internal.tokenkey.tnb.com.entrust.ChallengeSet getGridChallenge() {
        return gridChallenge;
    }


    /**
     * Sets the gridChallenge value for this GenericChallenge.
     * 
     * @param gridChallenge
     */
    public void setGridChallenge(ws.internal.tokenkey.tnb.com.entrust.ChallengeSet gridChallenge) {
        this.gridChallenge = gridChallenge;
    }


    /**
     * Gets the QAChallenge value for this GenericChallenge.
     * 
     * @return QAChallenge
     */
    public java.lang.String[] getQAChallenge() {
        return QAChallenge;
    }


    /**
     * Sets the QAChallenge value for this GenericChallenge.
     * 
     * @param QAChallenge
     */
    public void setQAChallenge(java.lang.String[] QAChallenge) {
        this.QAChallenge = QAChallenge;
    }


    /**
     * Gets the tokenChallenge value for this GenericChallenge.
     * 
     * @return tokenChallenge
     */
    public ws.internal.tokenkey.tnb.com.entrust.TokenChallenge getTokenChallenge() {
        return tokenChallenge;
    }


    /**
     * Sets the tokenChallenge value for this GenericChallenge.
     * 
     * @param tokenChallenge
     */
    public void setTokenChallenge(ws.internal.tokenkey.tnb.com.entrust.TokenChallenge tokenChallenge) {
        this.tokenChallenge = tokenChallenge;
    }


    /**
     * Gets the OTPChallenge value for this GenericChallenge.
     * 
     * @return OTPChallenge
     */
    public ws.internal.tokenkey.tnb.OTPChallenge getOTPChallenge() {
        return OTPChallenge;
    }


    /**
     * Sets the OTPChallenge value for this GenericChallenge.
     * 
     * @param OTPChallenge
     */
    public void setOTPChallenge(ws.internal.tokenkey.tnb.OTPChallenge OTPChallenge) {
        this.OTPChallenge = OTPChallenge;
    }


    /**
     * Gets the passwordChallenge value for this GenericChallenge.
     * 
     * @return passwordChallenge
     */
    public ws.internal.tokenkey.tnb.PasswordChallenge getPasswordChallenge() {
        return passwordChallenge;
    }


    /**
     * Sets the passwordChallenge value for this GenericChallenge.
     * 
     * @param passwordChallenge
     */
    public void setPasswordChallenge(ws.internal.tokenkey.tnb.PasswordChallenge passwordChallenge) {
        this.passwordChallenge = passwordChallenge;
    }


    /**
     * Gets the certificateChallenge value for this GenericChallenge.
     * 
     * @return certificateChallenge
     */
    public ws.internal.tokenkey.tnb.com.entrust.CertificateChallenge getCertificateChallenge() {
        return certificateChallenge;
    }


    /**
     * Sets the certificateChallenge value for this GenericChallenge.
     * 
     * @param certificateChallenge
     */
    public void setCertificateChallenge(ws.internal.tokenkey.tnb.com.entrust.CertificateChallenge certificateChallenge) {
        this.certificateChallenge = certificateChallenge;
    }


    /**
     * Gets the externalChallenge value for this GenericChallenge.
     * 
     * @return externalChallenge
     */
    public ws.internal.tokenkey.tnb.ExternalChallenge getExternalChallenge() {
        return externalChallenge;
    }


    /**
     * Sets the externalChallenge value for this GenericChallenge.
     * 
     * @param externalChallenge
     */
    public void setExternalChallenge(ws.internal.tokenkey.tnb.ExternalChallenge externalChallenge) {
        this.externalChallenge = externalChallenge;
    }


    /**
     * Gets the machineSecret value for this GenericChallenge.
     * 
     * @return machineSecret
     */
    public ws.internal.tokenkey.tnb.MachineSecret getMachineSecret() {
        return machineSecret;
    }


    /**
     * Sets the machineSecret value for this GenericChallenge.
     * 
     * @param machineSecret
     */
    public void setMachineSecret(ws.internal.tokenkey.tnb.MachineSecret machineSecret) {
        this.machineSecret = machineSecret;
    }


    /**
     * Gets the machineSecretPolicy value for this GenericChallenge.
     * 
     * @return machineSecretPolicy
     */
    public ws.internal.tokenkey.tnb.MachineSecretPolicy getMachineSecretPolicy() {
        return machineSecretPolicy;
    }


    /**
     * Sets the machineSecretPolicy value for this GenericChallenge.
     * 
     * @param machineSecretPolicy
     */
    public void setMachineSecretPolicy(ws.internal.tokenkey.tnb.MachineSecretPolicy machineSecretPolicy) {
        this.machineSecretPolicy = machineSecretPolicy;
    }


    /**
     * Gets the IPLocation value for this GenericChallenge.
     * 
     * @return IPLocation
     */
    public ws.internal.tokenkey.tnb.com.entrust.IPLocation getIPLocation() {
        return IPLocation;
    }


    /**
     * Sets the IPLocation value for this GenericChallenge.
     * 
     * @param IPLocation
     */
    public void setIPLocation(ws.internal.tokenkey.tnb.com.entrust.IPLocation IPLocation) {
        this.IPLocation = IPLocation;
    }


    /**
     * Gets the certificate value for this GenericChallenge.
     * 
     * @return certificate
     */
    public ws.internal.tokenkey.tnb.com.entrust.CertificateData getCertificate() {
        return certificate;
    }


    /**
     * Sets the certificate value for this GenericChallenge.
     * 
     * @param certificate
     */
    public void setCertificate(ws.internal.tokenkey.tnb.com.entrust.CertificateData certificate) {
        this.certificate = certificate;
    }


    /**
     * Gets the authenticationSecrets value for this GenericChallenge.
     * 
     * @return authenticationSecrets
     */
    public ws.internal.tokenkey.tnb.com.entrust.NameValue[] getAuthenticationSecrets() {
        return authenticationSecrets;
    }


    /**
     * Sets the authenticationSecrets value for this GenericChallenge.
     * 
     * @param authenticationSecrets
     */
    public void setAuthenticationSecrets(ws.internal.tokenkey.tnb.com.entrust.NameValue[] authenticationSecrets) {
        this.authenticationSecrets = authenticationSecrets;
    }


    /**
     * Gets the sharedSecrets value for this GenericChallenge.
     * 
     * @return sharedSecrets
     */
    public ws.internal.tokenkey.tnb.com.entrust.SharedSecret[] getSharedSecrets() {
        return sharedSecrets;
    }


    /**
     * Sets the sharedSecrets value for this GenericChallenge.
     * 
     * @param sharedSecrets
     */
    public void setSharedSecrets(ws.internal.tokenkey.tnb.com.entrust.SharedSecret[] sharedSecrets) {
        this.sharedSecrets = sharedSecrets;
    }


    /**
     * Gets the warningFault value for this GenericChallenge.
     * 
     * @return warningFault
     */
    public ws.internal.tokenkey.tnb.AuthenticationFault getWarningFault() {
        return warningFault;
    }


    /**
     * Sets the warningFault value for this GenericChallenge.
     * 
     * @param warningFault
     */
    public void setWarningFault(ws.internal.tokenkey.tnb.AuthenticationFault warningFault) {
        this.warningFault = warningFault;
    }


    /**
     * Gets the group value for this GenericChallenge.
     * 
     * @return group
     */
    public java.lang.String getGroup() {
        return group;
    }


    /**
     * Sets the group value for this GenericChallenge.
     * 
     * @param group
     */
    public void setGroup(java.lang.String group) {
        this.group = group;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GenericChallenge)) return false;
        GenericChallenge other = (GenericChallenge) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.challengeRequestResult==null && other.getChallengeRequestResult()==null) || 
             (this.challengeRequestResult!=null &&
              this.challengeRequestResult.equals(other.getChallengeRequestResult()))) &&
            ((this.riskScoringResult==null && other.getRiskScoringResult()==null) || 
             (this.riskScoringResult!=null &&
              this.riskScoringResult.equals(other.getRiskScoringResult()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.PVNInfo==null && other.getPVNInfo()==null) || 
             (this.PVNInfo!=null &&
              this.PVNInfo.equals(other.getPVNInfo()))) &&
            ((this.gridChallenge==null && other.getGridChallenge()==null) || 
             (this.gridChallenge!=null &&
              this.gridChallenge.equals(other.getGridChallenge()))) &&
            ((this.QAChallenge==null && other.getQAChallenge()==null) || 
             (this.QAChallenge!=null &&
              java.util.Arrays.equals(this.QAChallenge, other.getQAChallenge()))) &&
            ((this.tokenChallenge==null && other.getTokenChallenge()==null) || 
             (this.tokenChallenge!=null &&
              this.tokenChallenge.equals(other.getTokenChallenge()))) &&
            ((this.OTPChallenge==null && other.getOTPChallenge()==null) || 
             (this.OTPChallenge!=null &&
              this.OTPChallenge.equals(other.getOTPChallenge()))) &&
            ((this.passwordChallenge==null && other.getPasswordChallenge()==null) || 
             (this.passwordChallenge!=null &&
              this.passwordChallenge.equals(other.getPasswordChallenge()))) &&
            ((this.certificateChallenge==null && other.getCertificateChallenge()==null) || 
             (this.certificateChallenge!=null &&
              this.certificateChallenge.equals(other.getCertificateChallenge()))) &&
            ((this.externalChallenge==null && other.getExternalChallenge()==null) || 
             (this.externalChallenge!=null &&
              this.externalChallenge.equals(other.getExternalChallenge()))) &&
            ((this.machineSecret==null && other.getMachineSecret()==null) || 
             (this.machineSecret!=null &&
              this.machineSecret.equals(other.getMachineSecret()))) &&
            ((this.machineSecretPolicy==null && other.getMachineSecretPolicy()==null) || 
             (this.machineSecretPolicy!=null &&
              this.machineSecretPolicy.equals(other.getMachineSecretPolicy()))) &&
            ((this.IPLocation==null && other.getIPLocation()==null) || 
             (this.IPLocation!=null &&
              this.IPLocation.equals(other.getIPLocation()))) &&
            ((this.certificate==null && other.getCertificate()==null) || 
             (this.certificate!=null &&
              this.certificate.equals(other.getCertificate()))) &&
            ((this.authenticationSecrets==null && other.getAuthenticationSecrets()==null) || 
             (this.authenticationSecrets!=null &&
              java.util.Arrays.equals(this.authenticationSecrets, other.getAuthenticationSecrets()))) &&
            ((this.sharedSecrets==null && other.getSharedSecrets()==null) || 
             (this.sharedSecrets!=null &&
              java.util.Arrays.equals(this.sharedSecrets, other.getSharedSecrets()))) &&
            ((this.warningFault==null && other.getWarningFault()==null) || 
             (this.warningFault!=null &&
              this.warningFault.equals(other.getWarningFault()))) &&
            ((this.group==null && other.getGroup()==null) || 
             (this.group!=null &&
              this.group.equals(other.getGroup())));
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
        if (getChallengeRequestResult() != null) {
            _hashCode += getChallengeRequestResult().hashCode();
        }
        if (getRiskScoringResult() != null) {
            _hashCode += getRiskScoringResult().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getPVNInfo() != null) {
            _hashCode += getPVNInfo().hashCode();
        }
        if (getGridChallenge() != null) {
            _hashCode += getGridChallenge().hashCode();
        }
        if (getQAChallenge() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getQAChallenge());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getQAChallenge(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTokenChallenge() != null) {
            _hashCode += getTokenChallenge().hashCode();
        }
        if (getOTPChallenge() != null) {
            _hashCode += getOTPChallenge().hashCode();
        }
        if (getPasswordChallenge() != null) {
            _hashCode += getPasswordChallenge().hashCode();
        }
        if (getCertificateChallenge() != null) {
            _hashCode += getCertificateChallenge().hashCode();
        }
        if (getExternalChallenge() != null) {
            _hashCode += getExternalChallenge().hashCode();
        }
        if (getMachineSecret() != null) {
            _hashCode += getMachineSecret().hashCode();
        }
        if (getMachineSecretPolicy() != null) {
            _hashCode += getMachineSecretPolicy().hashCode();
        }
        if (getIPLocation() != null) {
            _hashCode += getIPLocation().hashCode();
        }
        if (getCertificate() != null) {
            _hashCode += getCertificate().hashCode();
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
        if (getGroup() != null) {
            _hashCode += getGroup().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GenericChallenge.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GenericChallenge"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("challengeRequestResult");
        elemField.setXmlName(new javax.xml.namespace.QName("", "challengeRequestResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("riskScoringResult");
        elemField.setXmlName(new javax.xml.namespace.QName("", "riskScoringResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "RiskScoringResult"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PVNInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PVNInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "PVNInfo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gridChallenge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GridChallenge"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "ChallengeSet"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("QAChallenge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "QAChallenge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tokenChallenge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TokenChallenge"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "TokenChallenge"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OTPChallenge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OTPChallenge"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "OTPChallenge"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passwordChallenge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PasswordChallenge"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "PasswordChallenge"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificateChallenge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CertificateChallenge"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "CertificateChallenge"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("externalChallenge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ExternalChallenge"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "ExternalChallenge"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("machineSecret");
        elemField.setXmlName(new javax.xml.namespace.QName("", "machineSecret"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MachineSecret"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("machineSecretPolicy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "machineSecretPolicy"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MachineSecretPolicy"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IPLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "IPLocation"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Certificate"));
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
        elemField.setFieldName("group");
        elemField.setXmlName(new javax.xml.namespace.QName("", "group"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
