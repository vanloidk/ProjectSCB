/**
 * ThongTinChiTietPhieuThu.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package scb.com.vn.common.model.payment;

public class ThongTinChiTietPhieuThu  implements java.io.Serializable {
    private long machitietphieuthu;

    private java.lang.String tenchiphi;

    private int giatien;

    private int soluong;

    private int thanhtien;

    public ThongTinChiTietPhieuThu() {
    }

    public ThongTinChiTietPhieuThu(
           long machitietphieuthu,
           java.lang.String tenchiphi,
           int giatien,
           int soluong,
           int thanhtien) {
           this.machitietphieuthu = machitietphieuthu;
           this.tenchiphi = tenchiphi;
           this.giatien = giatien;
           this.soluong = soluong;
           this.thanhtien = thanhtien;
    }


    /**
     * Gets the machitietphieuthu value for this ThongTinChiTietPhieuThu.
     * 
     * @return machitietphieuthu
     */
    public long getMachitietphieuthu() {
        return machitietphieuthu;
    }


    /**
     * Sets the machitietphieuthu value for this ThongTinChiTietPhieuThu.
     * 
     * @param machitietphieuthu
     */
    public void setMachitietphieuthu(long machitietphieuthu) {
        this.machitietphieuthu = machitietphieuthu;
    }


    /**
     * Gets the tenchiphi value for this ThongTinChiTietPhieuThu.
     * 
     * @return tenchiphi
     */
    public java.lang.String getTenchiphi() {
        return tenchiphi;
    }


    /**
     * Sets the tenchiphi value for this ThongTinChiTietPhieuThu.
     * 
     * @param tenchiphi
     */
    public void setTenchiphi(java.lang.String tenchiphi) {
        this.tenchiphi = tenchiphi;
    }


    /**
     * Gets the giatien value for this ThongTinChiTietPhieuThu.
     * 
     * @return giatien
     */
    public int getGiatien() {
        return giatien;
    }


    /**
     * Sets the giatien value for this ThongTinChiTietPhieuThu.
     * 
     * @param giatien
     */
    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }


    /**
     * Gets the soluong value for this ThongTinChiTietPhieuThu.
     * 
     * @return soluong
     */
    public int getSoluong() {
        return soluong;
    }


    /**
     * Sets the soluong value for this ThongTinChiTietPhieuThu.
     * 
     * @param soluong
     */
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }


    /**
     * Gets the thanhtien value for this ThongTinChiTietPhieuThu.
     * 
     * @return thanhtien
     */
    public int getThanhtien() {
        return thanhtien;
    }


    /**
     * Sets the thanhtien value for this ThongTinChiTietPhieuThu.
     * 
     * @param thanhtien
     */
    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ThongTinChiTietPhieuThu)) return false;
        ThongTinChiTietPhieuThu other = (ThongTinChiTietPhieuThu) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.machitietphieuthu == other.getMachitietphieuthu() &&
            ((this.tenchiphi==null && other.getTenchiphi()==null) || 
             (this.tenchiphi!=null &&
              this.tenchiphi.equals(other.getTenchiphi()))) &&
            this.giatien == other.getGiatien() &&
            this.soluong == other.getSoluong() &&
            this.thanhtien == other.getThanhtien();
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
        _hashCode += new Long(getMachitietphieuthu()).hashCode();
        if (getTenchiphi() != null) {
            _hashCode += getTenchiphi().hashCode();
        }
        _hashCode += getGiatien();
        _hashCode += getSoluong();
        _hashCode += getThanhtien();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ThongTinChiTietPhieuThu.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "ThongTinChiTietPhieuThu"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("machitietphieuthu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Machitietphieuthu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tenchiphi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Tenchiphi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("giatien");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Giatien"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("soluong");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Soluong"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("thanhtien");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Thanhtien"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
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
