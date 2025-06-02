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
@XmlType(propOrder = {"idGoiPhi", "age", "idDMGoiHD", "tenGoiHD", "idDMGoiPhiHD", "tenGoiPhiHD", "soTienPhi"})
public class BHBLPackageCostRsDto implements Serializable {

    private static final long serialVersionUID = -1L;
    private Long IdGoiPhi;
    private String Age;
    private Long IdDMGoiHD;
    private String TenGoiHD;
    private Long IdDMGoiPhiHD;
    private String TenGoiPhiHD;
    private BigDecimal SoTienPhi;

    public BHBLPackageCostRsDto() {
    }

    @XmlElement(name = "Id", nillable = true)
    public Long getIdGoiPhi() {
        return IdGoiPhi;
    }

    public void setIdGoiPhi(Long IdGoiPhi) {
        this.IdGoiPhi = IdGoiPhi;
    }

    @XmlElement(name = "Age", nillable = true)
    public String getAge() {
        return Age;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    @XmlElement(name = "IdPackageContract", nillable = true)
    public Long getIdDMGoiHD() {
        return IdDMGoiHD;
    }

    public void setIdDMGoiHD(Long IdDMGoiHD) {
        this.IdDMGoiHD = IdDMGoiHD;
    }

    @XmlElement(name = "PackageContractName", nillable = true)
    public String getTenGoiHD() {
        return TenGoiHD;
    }

    public void setTenGoiHD(String TenGoiHD) {
        this.TenGoiHD = TenGoiHD;
    }

    @XmlElement(name = "IdPackageCost", nillable = true)
    public Long getIdDMGoiPhiHD() {
        return IdDMGoiPhiHD;
    }

    public void setIdDMGoiPhiHD(Long IdDMGoiPhiHD) {
        this.IdDMGoiPhiHD = IdDMGoiPhiHD;
    }

    @XmlElement(name = "PackageCostName", nillable = true)
    public String getTenGoiPhiHD() {
        return TenGoiPhiHD;
    }

    public void setTenGoiPhiHD(String TenGoiPhiHD) {
        this.TenGoiPhiHD = TenGoiPhiHD;
    }

    @XmlElement(name = "MoneyCost", nillable = true)
    public BigDecimal getSoTienPhi() {
        return SoTienPhi;
    }

    public void setSoTienPhi(BigDecimal SoTienPhi) {
        this.SoTienPhi = SoTienPhi;
    }
}
