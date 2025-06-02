package scb.com.vn.common.model.cims.taikhoan;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PhongToaTKTTRes")
public class PhongToaTKTTRes extends ResponseCode implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String accounNumberOrCIF;
    private String type;
    private String userName;
    private String expiryDate;
    private String maDonVi;

    public PhongToaTKTTRes() {
    }

    public PhongToaTKTTRes(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public PhongToaTKTTRes(PhongToaTKTTReq req, String errorCode, String errorMsg) {
        this.accounNumberOrCIF = req.getAccounNumberOrCIF();
        this.type = req.getType();
        this.userName = req.getUserName();
        this.expiryDate = req.getExpiryDate();
        this.maDonVi = req.getMaDonVi();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

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

    @XmlElement(name = "MaDonVi", nillable = false)
    public String getMaDonVi() {
        return maDonVi;
    }

    public void setMaDonVi(String maDonVi) {
        this.maDonVi = maDonVi;
    }

}
