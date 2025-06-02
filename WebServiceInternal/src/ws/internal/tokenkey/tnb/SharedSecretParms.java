/**
 * SharedSecretParms.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class SharedSecretParms  implements java.io.Serializable {
    private ws.internal.tokenkey.tnb.com.entrust.SharedSecret[] set;

    private java.lang.Boolean merge;

    private java.lang.String[] get;

    private java.lang.Boolean getAll;

    private java.lang.String[] remove;

    public SharedSecretParms() {
    }

    public SharedSecretParms(
           ws.internal.tokenkey.tnb.com.entrust.SharedSecret[] set,
           java.lang.Boolean merge,
           java.lang.String[] get,
           java.lang.Boolean getAll,
           java.lang.String[] remove) {
           this.set = set;
           this.merge = merge;
           this.get = get;
           this.getAll = getAll;
           this.remove = remove;
    }


    /**
     * Gets the set value for this SharedSecretParms.
     * 
     * @return set
     */
    public ws.internal.tokenkey.tnb.com.entrust.SharedSecret[] getSet() {
        return set;
    }


    /**
     * Sets the set value for this SharedSecretParms.
     * 
     * @param set
     */
    public void setSet(ws.internal.tokenkey.tnb.com.entrust.SharedSecret[] set) {
        this.set = set;
    }


    /**
     * Gets the merge value for this SharedSecretParms.
     * 
     * @return merge
     */
    public java.lang.Boolean getMerge() {
        return merge;
    }


    /**
     * Sets the merge value for this SharedSecretParms.
     * 
     * @param merge
     */
    public void setMerge(java.lang.Boolean merge) {
        this.merge = merge;
    }


    /**
     * Gets the get value for this SharedSecretParms.
     * 
     * @return get
     */
    public java.lang.String[] getGet() {
        return get;
    }


    /**
     * Sets the get value for this SharedSecretParms.
     * 
     * @param get
     */
    public void setGet(java.lang.String[] get) {
        this.get = get;
    }


    /**
     * Gets the getAll value for this SharedSecretParms.
     * 
     * @return getAll
     */
    public java.lang.Boolean getGetAll() {
        return getAll;
    }


    /**
     * Sets the getAll value for this SharedSecretParms.
     * 
     * @param getAll
     */
    public void setGetAll(java.lang.Boolean getAll) {
        this.getAll = getAll;
    }


    /**
     * Gets the remove value for this SharedSecretParms.
     * 
     * @return remove
     */
    public java.lang.String[] getRemove() {
        return remove;
    }


    /**
     * Sets the remove value for this SharedSecretParms.
     * 
     * @param remove
     */
    public void setRemove(java.lang.String[] remove) {
        this.remove = remove;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SharedSecretParms)) return false;
        SharedSecretParms other = (SharedSecretParms) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.set==null && other.getSet()==null) || 
             (this.set!=null &&
              java.util.Arrays.equals(this.set, other.getSet()))) &&
            ((this.merge==null && other.getMerge()==null) || 
             (this.merge!=null &&
              this.merge.equals(other.getMerge()))) &&
            ((this.get==null && other.getGet()==null) || 
             (this.get!=null &&
              java.util.Arrays.equals(this.get, other.getGet()))) &&
            ((this.getAll==null && other.getGetAll()==null) || 
             (this.getAll!=null &&
              this.getAll.equals(other.getGetAll()))) &&
            ((this.remove==null && other.getRemove()==null) || 
             (this.remove!=null &&
              java.util.Arrays.equals(this.remove, other.getRemove())));
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
        if (getSet() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSet());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSet(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMerge() != null) {
            _hashCode += getMerge().hashCode();
        }
        if (getGet() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGet());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGet(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getGetAll() != null) {
            _hashCode += getGetAll().hashCode();
        }
        if (getRemove() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRemove());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRemove(), i);
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
        new org.apache.axis.description.TypeDesc(SharedSecretParms.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "SharedSecretParms"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("set");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Set"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "SharedSecret"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("merge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Merge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("get");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Get"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getAll");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GetAll"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remove");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Remove"));
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
