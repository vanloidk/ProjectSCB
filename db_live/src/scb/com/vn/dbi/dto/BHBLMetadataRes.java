/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author loinv3
 */
@XmlType(propOrder = {"id", "idValue", "ten", "tenPhu", "loaiDM", "giaTri"})
public class BHBLMetadataRes implements Serializable {

    private static final long serialVersionUID = -1L;
    private long id;
    private long idValue;
    private String ten;
    private String tenPhu;
    private String loaiDM;
    private BigDecimal giaTri;

    @XmlElement(name = "Id", nillable = true)
    public long getId() {
        return id;
    }

    public void setId(long Id) {
        this.id = Id;
    }

    @XmlElement(name = "IdDM", nillable = true)
    public long getIdValue() {
        return idValue;
    }

    public void setIdValue(long idValue) {
        this.idValue = idValue;
    }

    @XmlElement(name = "Name", nillable = true)
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @XmlElement(name = "SubName", nillable = true)
    public String getTenPhu() {
        return tenPhu;
    }

    public void setTenPhu(String tenPhu) {
        this.tenPhu = tenPhu;
    }

    @XmlElement(name = "Type", nillable = true)
    public String getLoaiDM() {
        return loaiDM;
    }

    public void setLoaiDM(String loaiDM) {
        this.loaiDM = loaiDM;
    }

    @XmlElement(name = "Value", nillable = true)
    public BigDecimal getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(BigDecimal giaTri) {
        this.giaTri = giaTri;
    }

}
