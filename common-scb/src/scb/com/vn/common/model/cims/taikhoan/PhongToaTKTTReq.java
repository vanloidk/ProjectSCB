package scb.com.vn.common.model.cims.taikhoan;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PhongToaTKTTReq")
public class PhongToaTKTTReq extends TaiKhoanReq implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String accounNumberOrCIF;
    private String type;
    private String userName;
    private String expiryDate;
    private String maDonVi;

    @XmlElement(name = "AccountNumberOrCif", nillable = false)
    public String getAccounNumberOrCIF() {
        return accounNumberOrCIF;
    }

    public void setAccounNumberOrCIF(String accounNumberOrCIF) {
        this.accounNumberOrCIF = accounNumberOrCIF;
    }

    @XmlElement(name = "Type", nillable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "UserName", nillable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @XmlElement(name = "ExpiryDate", nillable = false)
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @XmlElement(name = "MaDV", nillable = false)
    public String getMaDonVi() {
        return maDonVi;
    }

    public void setMaDonVi(String maDonVi) {
        this.maDonVi = maDonVi;
    }

    public String getValueToBuildMac() {
        return this.accounNumberOrCIF + this.type + this.userName + this.expiryDate + this.maDonVi + this.getTime();
    }

}
