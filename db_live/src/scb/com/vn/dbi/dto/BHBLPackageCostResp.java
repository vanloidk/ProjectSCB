/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author loinv3
 */
@XmlType(propOrder = {"age", "idPackageContract", "packageContractName", "packageCost"})
public class BHBLPackageCostResp implements Serializable {

    private static final long serialVersionUID = -1L;

    private String Age;
    private long IdPackageContract;
    private String PackageContractName;
    private List<PackageCost> PackageCost = new ArrayList<>();

    public BHBLPackageCostResp() {
    }

    @XmlElement(name = "Age", nillable = true)
    public String getAge() {
        return Age;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    @XmlElement(name = "IdPackageContract", nillable = true)
    public long getIdPackageContract() {
        return IdPackageContract;
    }

    public void setIdPackageContract(long IdPackageContract) {
        this.IdPackageContract = IdPackageContract;
    }

    @XmlElement(name = "PackageContractName", nillable = true)
    public String getPackageContractName() {
        return PackageContractName;
    }

    public void setPackageContractName(String PackageContractName) {
        this.PackageContractName = PackageContractName;
    }

    @XmlElement(name = "PackageCost", nillable = true)
    public List<PackageCost> getPackageCost() {
        return PackageCost;
    }

    public void setPackageCost(List<PackageCost> PackageCost) {
        this.PackageCost = PackageCost;
    }

    @XmlType(propOrder = {"idPackageCost", "packageCostName", "packageCostFeeName", "age", "moneyCost", "typeValue", "isEnable", "itemCosts"})
    public static class PackageCost implements Serializable {

        private static final long serialVersionUID = -1L;
        private long IdPackageCost;
        private String PackageCostName;
        private String PackageCostFeeName;
        private String Age;
        private BigDecimal MoneyCost;
        private List<ItemCost> ItemCosts;
        private int TypeValue;
        private int IsEnable;

        @XmlElement(name = "IdPackageCost", nillable = true)
        public long getIdPackageCost() {
            return IdPackageCost;
        }

        public void setIdPackageCost(long IdPackageCost) {
            this.IdPackageCost = IdPackageCost;
        }

        @XmlElement(name = "PackageCostName", nillable = true)
        public String getPackageCostName() {
            return PackageCostName;
        }

        public void setPackageCostName(String PackageCostName) {
            this.PackageCostName = PackageCostName;
        }

        @XmlElement(name = "PackageCostFeeName", nillable = true)
        public String getPackageCostFeeName() {
            return PackageCostFeeName;
        }

        public void setPackageCostFeeName(String PackageCostFeeName) {
            this.PackageCostFeeName = PackageCostFeeName;
        }

        @XmlElement(name = "Age", nillable = true)
        public String getAge() {
            return Age;
        }

        public void setAge(String Age) {
            this.Age = Age;
        }

        @XmlElement(name = "MoneyCost", nillable = true)
        public BigDecimal getMoneyCost() {
            return MoneyCost;
        }

        public void setMoneyCost(BigDecimal MoneyCost) {
            this.MoneyCost = MoneyCost;
        }

        @XmlElement(name = "ItemCosts", nillable = true)
        public List<ItemCost> getItemCosts() {
            return ItemCosts;
        }

        public void setItemCosts(List<ItemCost> ItemCosts) {
            this.ItemCosts = ItemCosts;
        }

        @XmlElement(name = "TypeValue", nillable = true)
        public int getTypeValue() {
            return TypeValue;
        }

        public void setTypeValue(int TypeValue) {
            this.TypeValue = TypeValue;
        }

        @XmlElement(name = "IsEnable", nillable = true)
        public int getIsEnable() {
            return IsEnable;
        }

        public void setIsEnable(int IsEnable) {
            this.IsEnable = IsEnable;
        }

    }

    @XmlType(propOrder = {"id", "giaTri"})
    public static class ItemCost implements Serializable {

        private static final long serialVersionUID = -1L;
        private long id;
        private String giaTri;

        @XmlElement(name = "Id", nillable = true)
        public long getId() {
            return id;
        }

        public void setId(long Id) {
            this.id = Id;
        }

        @XmlElement(name = "Value", nillable = true)
        public String getGiaTri() {
            return giaTri;
        }

        public void setGiaTri(String giaTri) {
            this.giaTri = giaTri;
        }

    }

}
