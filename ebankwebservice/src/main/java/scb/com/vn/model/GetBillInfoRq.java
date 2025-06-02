/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "GetBillInfoRq")
public class GetBillInfoRq {

    private String UserName;
    private String CifNo;
    private String CustomerCode;
    private String idservicetype;
    private String idprovider;
    private String DebitAccount;
    private String IdPartner;
    private String Channel;
    private String AddInfo;
    private String billAmt;
    
    private String DataInput;
    
    @XmlElement(name = "BillAmt", nillable = true)
    public String getBillAmt() {
        return billAmt;
    }

    public void setBillAmt(String billAmt) {
        this.billAmt = billAmt;
    }

    /**
     * @return the UserName
     */
    @XmlElement(name = "UserName", nillable = true)
    public String getUserName() {
        return UserName;
    }

    /**
     * @param UserName the UserName to set
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     * @return the CifNo
     */
    @XmlElement(name = "CifNo", nillable = true)
    public String getCifNo() {
        return CifNo;
    }

    /**
     * @param CifNo the CifNo to set
     */
    public void setCifNo(String CifNo) {
        this.CifNo = CifNo;
    }

    /**
     * @return the CustomerCode
     */
    @XmlElement(name = "CustomerCode", nillable = true)
    public String getCustomerCode() {
        return CustomerCode;
    }

    /**
     * @param CustomerCode the CustomerCode to set
     */
    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    /**
     * @return the idservicetype
     */
    @XmlElement(name = "BillServiceId", nillable = true)
    public String getIdservicetype() {
        return idservicetype;
    }

    /**
     * @param idservicetype the idservicetype to set
     */
    public void setIdservicetype(String idservicetype) {
        this.idservicetype = idservicetype;
    }

    /**
     * @return the idprovider
     */
    @XmlElement(name = "BillProviderId", nillable = true)
    public String getIdprovider() {
        return idprovider;
    }

    /**
     * @param idprovider the idprovider to set
     */
    public void setIdprovider(String idprovider) {
        this.idprovider = idprovider;
    }

    /**
     * @return the DebitAccount
     */
    @XmlElement(name = "DebitAccount", nillable = true)
    public String getDebitAccount() {
        return DebitAccount;
    }

    /**
     * @param DebitAccount the DebitAccount to set
     */
    public void setDebitAccount(String DebitAccount) {
        this.DebitAccount = DebitAccount;
    }

    @XmlElement(name = "Channel", nillable = true)
    public String getChannel() {
        return Channel;
    }

    public void setChannel(String Channel) {
        this.Channel = Channel;
    }
    
    @XmlElement(name = "AddInfo", nillable = true)
    public String getAddInfo() {
        return AddInfo;
    }
    
    public void setAddInfo(String AddInfo) {
        this.AddInfo = AddInfo;
    }
    
    @XmlElement(name = "IdPartner", nillable = true)
     public String getIdPartner() {
        return IdPartner;
    }

    public void setIdPartner(String IdPartner) {
        this.IdPartner = IdPartner;
    }
    
    @XmlElement(name = "DataInput", nillable = true)
    public String getDataInput() {
        return DataInput;
    }
    
    public void setDataInput(String DataInput) {
        this.DataInput = DataInput;
    }
    
}
