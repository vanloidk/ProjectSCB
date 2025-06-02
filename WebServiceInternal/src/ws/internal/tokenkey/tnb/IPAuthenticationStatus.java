/**
 * IPAuthenticationStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class IPAuthenticationStatus  implements java.io.Serializable {
    private java.lang.Boolean expectedLocations;

    private java.lang.Boolean IPBlacklist;

    private java.lang.Boolean countryBlacklist;

    private java.lang.Boolean locationHistory;

    private java.lang.Boolean velocity;

    public IPAuthenticationStatus() {
    }

    public IPAuthenticationStatus(
           java.lang.Boolean expectedLocations,
           java.lang.Boolean IPBlacklist,
           java.lang.Boolean countryBlacklist,
           java.lang.Boolean locationHistory,
           java.lang.Boolean velocity) {
           this.expectedLocations = expectedLocations;
           this.IPBlacklist = IPBlacklist;
           this.countryBlacklist = countryBlacklist;
           this.locationHistory = locationHistory;
           this.velocity = velocity;
    }


    /**
     * Gets the expectedLocations value for this IPAuthenticationStatus.
     * 
     * @return expectedLocations
     */
    public java.lang.Boolean getExpectedLocations() {
        return expectedLocations;
    }


    /**
     * Sets the expectedLocations value for this IPAuthenticationStatus.
     * 
     * @param expectedLocations
     */
    public void setExpectedLocations(java.lang.Boolean expectedLocations) {
        this.expectedLocations = expectedLocations;
    }


    /**
     * Gets the IPBlacklist value for this IPAuthenticationStatus.
     * 
     * @return IPBlacklist
     */
    public java.lang.Boolean getIPBlacklist() {
        return IPBlacklist;
    }


    /**
     * Sets the IPBlacklist value for this IPAuthenticationStatus.
     * 
     * @param IPBlacklist
     */
    public void setIPBlacklist(java.lang.Boolean IPBlacklist) {
        this.IPBlacklist = IPBlacklist;
    }


    /**
     * Gets the countryBlacklist value for this IPAuthenticationStatus.
     * 
     * @return countryBlacklist
     */
    public java.lang.Boolean getCountryBlacklist() {
        return countryBlacklist;
    }


    /**
     * Sets the countryBlacklist value for this IPAuthenticationStatus.
     * 
     * @param countryBlacklist
     */
    public void setCountryBlacklist(java.lang.Boolean countryBlacklist) {
        this.countryBlacklist = countryBlacklist;
    }


    /**
     * Gets the locationHistory value for this IPAuthenticationStatus.
     * 
     * @return locationHistory
     */
    public java.lang.Boolean getLocationHistory() {
        return locationHistory;
    }


    /**
     * Sets the locationHistory value for this IPAuthenticationStatus.
     * 
     * @param locationHistory
     */
    public void setLocationHistory(java.lang.Boolean locationHistory) {
        this.locationHistory = locationHistory;
    }


    /**
     * Gets the velocity value for this IPAuthenticationStatus.
     * 
     * @return velocity
     */
    public java.lang.Boolean getVelocity() {
        return velocity;
    }


    /**
     * Sets the velocity value for this IPAuthenticationStatus.
     * 
     * @param velocity
     */
    public void setVelocity(java.lang.Boolean velocity) {
        this.velocity = velocity;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IPAuthenticationStatus)) return false;
        IPAuthenticationStatus other = (IPAuthenticationStatus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.expectedLocations==null && other.getExpectedLocations()==null) || 
             (this.expectedLocations!=null &&
              this.expectedLocations.equals(other.getExpectedLocations()))) &&
            ((this.IPBlacklist==null && other.getIPBlacklist()==null) || 
             (this.IPBlacklist!=null &&
              this.IPBlacklist.equals(other.getIPBlacklist()))) &&
            ((this.countryBlacklist==null && other.getCountryBlacklist()==null) || 
             (this.countryBlacklist!=null &&
              this.countryBlacklist.equals(other.getCountryBlacklist()))) &&
            ((this.locationHistory==null && other.getLocationHistory()==null) || 
             (this.locationHistory!=null &&
              this.locationHistory.equals(other.getLocationHistory()))) &&
            ((this.velocity==null && other.getVelocity()==null) || 
             (this.velocity!=null &&
              this.velocity.equals(other.getVelocity())));
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
        if (getExpectedLocations() != null) {
            _hashCode += getExpectedLocations().hashCode();
        }
        if (getIPBlacklist() != null) {
            _hashCode += getIPBlacklist().hashCode();
        }
        if (getCountryBlacklist() != null) {
            _hashCode += getCountryBlacklist().hashCode();
        }
        if (getLocationHistory() != null) {
            _hashCode += getLocationHistory().hashCode();
        }
        if (getVelocity() != null) {
            _hashCode += getVelocity().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IPAuthenticationStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "IPAuthenticationStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expectedLocations");
        elemField.setXmlName(new javax.xml.namespace.QName("", "expectedLocations"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPBlacklist");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IPBlacklist"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryBlacklist");
        elemField.setXmlName(new javax.xml.namespace.QName("", "countryBlacklist"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locationHistory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locationHistory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("velocity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "velocity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
