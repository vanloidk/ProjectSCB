/**
 * ThongTinPhieuThu.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.vn.edu.sc.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ThongTinPhieuThu")
public class ThongTinPhieuThu  implements java.io.Serializable {
    private java.lang.String maphieuthu;

    private int maloi;

    private int thanglapphieu;

    private int namlapphieu;

    private boolean tinhtrangthanhtoan;

    private int diadiemthanhtoan;

    private int tongthu;

    private java.lang.String hotenhocsinh;

    private java.lang.String tenlophoc;

    private java.lang.String tenkhoihoc;

    private java.lang.String matruong;

    private java.lang.String tentruong;

    private java.lang.String tenphuong;

    private java.lang.String tenquan;

    private java.lang.String tenthanhpho;

    private ws.internal.vn.edu.sc.ws.ThongTinChiTietPhieuThu[] danhSachChiTietPhieuThu;

    public ThongTinPhieuThu() {
    }

    public ThongTinPhieuThu(
           java.lang.String maphieuthu,
           int maloi,
           int thanglapphieu,
           int namlapphieu,
           boolean tinhtrangthanhtoan,
           int diadiemthanhtoan,
           int tongthu,
           java.lang.String hotenhocsinh,
           java.lang.String tenlophoc,
           java.lang.String tenkhoihoc,
           java.lang.String matruong,
           java.lang.String tentruong,
           java.lang.String tenphuong,
           java.lang.String tenquan,
           java.lang.String tenthanhpho,
           ws.internal.vn.edu.sc.ws.ThongTinChiTietPhieuThu[] danhSachChiTietPhieuThu) {
           this.maphieuthu = maphieuthu;
           this.maloi = maloi;
           this.thanglapphieu = thanglapphieu;
           this.namlapphieu = namlapphieu;
           this.tinhtrangthanhtoan = tinhtrangthanhtoan;
           this.diadiemthanhtoan = diadiemthanhtoan;
           this.tongthu = tongthu;
           this.hotenhocsinh = hotenhocsinh;
           this.tenlophoc = tenlophoc;
           this.tenkhoihoc = tenkhoihoc;
           this.matruong = matruong;
           this.tentruong = tentruong;
           this.tenphuong = tenphuong;
           this.tenquan = tenquan;
           this.tenthanhpho = tenthanhpho;
           this.danhSachChiTietPhieuThu = danhSachChiTietPhieuThu;
    }

    @XmlElement(name = "MAPHIEUTHU", required = false, nillable = true)
    public java.lang.String getMaphieuthu() {
        return maphieuthu;
    }

    public void setMaphieuthu(java.lang.String maphieuthu) {
        this.maphieuthu = maphieuthu;
    }

    @XmlElement(name = "MALOI", required = false, nillable = true)
    public int getMaloi() {
        return maloi;
    }

    public void setMaloi(int maloi) {
        this.maloi = maloi;
    }

    @XmlElement(name = "THANGLAPPHIEU", required = false, nillable = true)
    public int getThanglapphieu() {
        return thanglapphieu;
    }

    public void setThanglapphieu(int thanglapphieu) {
        this.thanglapphieu = thanglapphieu;
    }

    @XmlElement(name = "NAMLAPPHIEU", required = false, nillable = true)
    public int getNamlapphieu() {
        return namlapphieu;
    }

    public void setNamlapphieu(int namlapphieu) {
        this.namlapphieu = namlapphieu;
    }

    @XmlElement(name = "TINHTRANGTHANHTOAN", required = false, nillable = true)
    public boolean isTinhtrangthanhtoan() {
        return tinhtrangthanhtoan;
    }

    public void setTinhtrangthanhtoan(boolean tinhtrangthanhtoan) {
        this.tinhtrangthanhtoan = tinhtrangthanhtoan;
    }

    @XmlElement(name = "DIADIEMTHANHTOAN", required = false, nillable = true)
    public int getDiadiemthanhtoan() {
        return diadiemthanhtoan;
    }

    public void setDiadiemthanhtoan(int diadiemthanhtoan) {
        this.diadiemthanhtoan = diadiemthanhtoan;
    }

    @XmlElement(name = "TONGTHU", required = false, nillable = true)
    public int getTongthu() {
        return tongthu;
    }

    public void setTongthu(int tongthu) {
        this.tongthu = tongthu;
    }

    @XmlElement(name = "HOTENHOCSINH", required = false, nillable = true)
    public java.lang.String getHotenhocsinh() {
        return hotenhocsinh;
    }

    public void setHotenhocsinh(java.lang.String hotenhocsinh) {
        this.hotenhocsinh = hotenhocsinh;
    }

    @XmlElement(name = "TENLOPHOC", required = false, nillable = true)
    public java.lang.String getTenlophoc() {
        return tenlophoc;
    }

    public void setTenlophoc(java.lang.String tenlophoc) {
        this.tenlophoc = tenlophoc;
    }

    @XmlElement(name = "TENKHOIHOC", required = false, nillable = true)
    public java.lang.String getTenkhoihoc() {
        return tenkhoihoc;
    }

    public void setTenkhoihoc(java.lang.String tenkhoihoc) {
        this.tenkhoihoc = tenkhoihoc;
    }

    @XmlElement(name = "MATRUONG", required = false, nillable = true)
    public java.lang.String getMatruong() {
        return matruong;
    }

    public void setMatruong(java.lang.String matruong) {
        this.matruong = matruong;
    }

    @XmlElement(name = "TENTRUONG", required = false, nillable = true)
    public java.lang.String getTentruong() {
        return tentruong;
    }

    public void setTentruong(java.lang.String tentruong) {
        this.tentruong = tentruong;
    }

    @XmlElement(name = "TENPHUONG", required = false, nillable = true)
    public java.lang.String getTenphuong() {
        return tenphuong;
    }

    public void setTenphuong(java.lang.String tenphuong) {
        this.tenphuong = tenphuong;
    }

    @XmlElement(name = "TENQUAN", required = false, nillable = true)
    public java.lang.String getTenquan() {
        return tenquan;
    }

    public void setTenquan(java.lang.String tenquan) {
        this.tenquan = tenquan;
    }

    @XmlElement(name = "TENTHANHPHO", required = false, nillable = true)
    public java.lang.String getTenthanhpho() {
        return tenthanhpho;
    }

    public void setTenthanhpho(java.lang.String tenthanhpho) {
        this.tenthanhpho = tenthanhpho;
    }

    @XmlElement(name = "DANHSACHCHITIETPHIEUTHU", required = false, nillable = true)
    public ws.internal.vn.edu.sc.ws.ThongTinChiTietPhieuThu[] getDanhSachChiTietPhieuThu() {
        return danhSachChiTietPhieuThu;
    }


    /**
     * Sets the danhSachChiTietPhieuThu value for this ThongTinPhieuThu.
     * 
     * @param danhSachChiTietPhieuThu
     */
    public void setDanhSachChiTietPhieuThu(ws.internal.vn.edu.sc.ws.ThongTinChiTietPhieuThu[] danhSachChiTietPhieuThu) {
        this.danhSachChiTietPhieuThu = danhSachChiTietPhieuThu;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ThongTinPhieuThu)) return false;
        ThongTinPhieuThu other = (ThongTinPhieuThu) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.maphieuthu==null && other.getMaphieuthu()==null) || 
             (this.maphieuthu!=null &&
              this.maphieuthu.equals(other.getMaphieuthu()))) &&
            this.maloi == other.getMaloi() &&
            this.thanglapphieu == other.getThanglapphieu() &&
            this.namlapphieu == other.getNamlapphieu() &&
            this.tinhtrangthanhtoan == other.isTinhtrangthanhtoan() &&
            this.diadiemthanhtoan == other.getDiadiemthanhtoan() &&
            this.tongthu == other.getTongthu() &&
            ((this.hotenhocsinh==null && other.getHotenhocsinh()==null) || 
             (this.hotenhocsinh!=null &&
              this.hotenhocsinh.equals(other.getHotenhocsinh()))) &&
            ((this.tenlophoc==null && other.getTenlophoc()==null) || 
             (this.tenlophoc!=null &&
              this.tenlophoc.equals(other.getTenlophoc()))) &&
            ((this.tenkhoihoc==null && other.getTenkhoihoc()==null) || 
             (this.tenkhoihoc!=null &&
              this.tenkhoihoc.equals(other.getTenkhoihoc()))) &&
            ((this.matruong==null && other.getMatruong()==null) || 
             (this.matruong!=null &&
              this.matruong.equals(other.getMatruong()))) &&
            ((this.tentruong==null && other.getTentruong()==null) || 
             (this.tentruong!=null &&
              this.tentruong.equals(other.getTentruong()))) &&
            ((this.tenphuong==null && other.getTenphuong()==null) || 
             (this.tenphuong!=null &&
              this.tenphuong.equals(other.getTenphuong()))) &&
            ((this.tenquan==null && other.getTenquan()==null) || 
             (this.tenquan!=null &&
              this.tenquan.equals(other.getTenquan()))) &&
            ((this.tenthanhpho==null && other.getTenthanhpho()==null) || 
             (this.tenthanhpho!=null &&
              this.tenthanhpho.equals(other.getTenthanhpho()))) &&
            ((this.danhSachChiTietPhieuThu==null && other.getDanhSachChiTietPhieuThu()==null) || 
             (this.danhSachChiTietPhieuThu!=null &&
              java.util.Arrays.equals(this.danhSachChiTietPhieuThu, other.getDanhSachChiTietPhieuThu())));
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
        if (getMaphieuthu() != null) {
            _hashCode += getMaphieuthu().hashCode();
        }
        _hashCode += getMaloi();
        _hashCode += getThanglapphieu();
        _hashCode += getNamlapphieu();
        _hashCode += (isTinhtrangthanhtoan() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getDiadiemthanhtoan();
        _hashCode += getTongthu();
        if (getHotenhocsinh() != null) {
            _hashCode += getHotenhocsinh().hashCode();
        }
        if (getTenlophoc() != null) {
            _hashCode += getTenlophoc().hashCode();
        }
        if (getTenkhoihoc() != null) {
            _hashCode += getTenkhoihoc().hashCode();
        }
        if (getMatruong() != null) {
            _hashCode += getMatruong().hashCode();
        }
        if (getTentruong() != null) {
            _hashCode += getTentruong().hashCode();
        }
        if (getTenphuong() != null) {
            _hashCode += getTenphuong().hashCode();
        }
        if (getTenquan() != null) {
            _hashCode += getTenquan().hashCode();
        }
        if (getTenthanhpho() != null) {
            _hashCode += getTenthanhpho().hashCode();
        }
        if (getDanhSachChiTietPhieuThu() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDanhSachChiTietPhieuThu());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDanhSachChiTietPhieuThu(), i);
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
        new org.apache.axis.description.TypeDesc(ThongTinPhieuThu.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "ThongTinPhieuThu"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maphieuthu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Maphieuthu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maloi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Maloi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("thanglapphieu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Thanglapphieu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("namlapphieu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Namlapphieu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tinhtrangthanhtoan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Tinhtrangthanhtoan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diadiemthanhtoan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Diadiemthanhtoan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tongthu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Tongthu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hotenhocsinh");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Hotenhocsinh"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tenlophoc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Tenlophoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tenkhoihoc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Tenkhoihoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matruong");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Matruong"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tentruong");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Tentruong"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tenphuong");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Tenphuong"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tenquan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Tenquan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tenthanhpho");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "Tenthanhpho"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("danhSachChiTietPhieuThu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "DanhSachChiTietPhieuThu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "ThongTinChiTietPhieuThu"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "ThongTinChiTietPhieuThu"));
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
