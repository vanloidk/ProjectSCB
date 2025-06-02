/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.bhbl;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import scb.com.vn.dbi.dto.BHBLPackageCostResp;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "PackageCostRp")
@XmlType(propOrder = {"errorCode", "errorMsg", "packageContract"})
public class BHBLPackageCostsResp {

    private String errorCode;
    private String errorMsg;
    private List<BHBLPackageCostResp> PackageContract;

    public BHBLPackageCostsResp() {
    }

    @XmlElement(name = "ErrorCode", nillable = true)
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @XmlElement(name = "ErrorMsg", nillable = true)
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @XmlElement(name = "PackageContract", nillable = true)
    public List<BHBLPackageCostResp> getPackageContract() {
        return PackageContract;
    }

    public void setPackageContract(List<BHBLPackageCostResp> PackageContract) {
        this.PackageContract = PackageContract;
    }

}
