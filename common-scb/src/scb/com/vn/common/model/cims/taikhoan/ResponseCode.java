package scb.com.vn.common.model.cims.taikhoan;

import javax.xml.bind.annotation.XmlElement;

public class ResponseCode implements java.io.Serializable{
    private static final long serialVersionUID = 1L;    
    protected String errorCode;
    protected String errorMsg;

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
    
}
