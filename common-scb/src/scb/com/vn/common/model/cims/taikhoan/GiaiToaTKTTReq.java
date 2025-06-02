package scb.com.vn.common.model.cims.taikhoan;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GiaiToaTKTTReq")
public class GiaiToaTKTTReq extends TaiKhoanReq implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String accounNumber;
    private String userName;
    private String maDonVi;

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

    public String getValueToBuildMac() {
        return this.accounNumber + this.userName + this.maDonVi + this.getTime();
    }

}
