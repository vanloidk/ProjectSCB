package scb.com.vn.common.model.cims.taikhoan;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GiaiToaTKTTRes")
public class GiaiToaTKTTRes extends ResponseCode implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String accounNumber;
    private String userName;
    private String maDonVi;

    public GiaiToaTKTTRes() {
    }

    public GiaiToaTKTTRes(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public GiaiToaTKTTRes(GiaiToaTKTTReq req, String errorCode, String errorMsg) {
        this.accounNumber = req.getAccounNumber();
        this.userName = req.getUserName();
        this.maDonVi = req.getMaDonVi();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @XmlElement(name = "AccountNumber", nillable = false)
    public String getAccounNumber() {
        return accounNumber;
    }

    public void setAccounNumber(String accounNumber) {
        this.accounNumber = accounNumber;
    }

    @XmlElement(name = "UserName", nillable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @XmlElement(name = "MaDV", nillable = false)
    public String getMaDonVi() {
        return maDonVi;
    }

    public void setMaDonVi(String maDonVi) {
        this.maDonVi = maDonVi;
    }

}
