/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import scb.com.vn.controller.Tokenkey;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;

/**
 *
 * @author KimNCM
 */
public class MobileRequest {

    private String UserName;
    private String CifNo;
    private String TxnType;
    private String AuthenType;
    private String SerialNo;
    private String TxnOtp;

    /**
     *
     */
    public static final String AuthenType_SMS = "0";

    /**
     *
     */
    public static final String AuthenType_TOKEN = "1";

    /**
     *
     */
    public static final String AuthenType_TOUCH = "2";
    final int _SUCESSFUL = 0, _UNSUCESSFUL = -1, _LOCK_TOKEN = -3;
    private String UserId;
    String useridMobile = Configuration.getProperty("fcubs.userid.mobile");

    /**
     *
     */
    public MobileRequest() {
        this.UserId = useridMobile;
    }

    /**
     *
     * @param CifNo
     * @param TxnType
     * @param AuthenType
     * @param TxnOtp
     */
    public MobileRequest(String CifNo, String TxnType, String AuthenType, String TxnOtp) {
        this.CifNo = CifNo;
        this.AuthenType = AuthenType;
        this.TxnType = TxnType;
        this.TxnOtp = TxnOtp;
        this.UserId = useridMobile;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public int validateAuth() throws Exception {
        if (getAuthenType().equals(AuthenType_TOUCH) || getAuthenType().equals(AuthenType_SMS)) {
            return _SUCESSFUL;
        } else if (getAuthenType().equals(AuthenType_TOKEN)) {

            //COMMENT AFTER --START
            //temp for set 123456789 return locktoken
            /*
            if (TxnOtp.equalsIgnoreCase("12345676")) {
                return _UNSUCESSFUL;
            }

            if (TxnOtp.equalsIgnoreCase("12345677")) {
                return _LOCK_TOKEN;
            }
            //temp for set !12345678 return invalid
            if (TxnOtp.equalsIgnoreCase("12345678")) {
                return _SUCESSFUL;
            }
             */
            //COMMENT AFTER --END
            //UNCOMMENT AFTER --START
            Tokenkey tk = new Tokenkey();
            try {
                return tk.checkAuthenTokenkey(SerialNo, TxnOtp);
            } catch (Exception ex) {
                //Logger.getLogger(ExchangePaybill.class.getName()).log(Level.SEVERE, null, ex);                
                Helper.writeLogError(MobileRequest.class, "***- ERROR IB (FUNCTION validateAuth): " + ex.getMessage());
                return _UNSUCESSFUL;
            }
            //UNCOMMENT AFTER --END
        }
        //return _UNSUCESSFUL;
        return _SUCESSFUL;
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
     * @return the TxnType
     */
    @XmlElement(name = "TxnType", nillable = true)
    public String getTxnType() {
        return TxnType;
    }

    /**
     * @param TxnType the TxnType to set
     */
    public void setTxnType(String TxnType) {
        this.TxnType = TxnType;
    }

    /**
     * @return the AuthenType
     */
    @XmlElement(name = "AuthenType", nillable = true)
    public String getAuthenType() {
        return AuthenType;
    }

    /**
     * @param AuthenType the AuthenType to set
     */
    public void setAuthenType(String AuthenType) {
        this.AuthenType = AuthenType;
    }

    /**
     * @return the TxnOtp
     */
    @XmlElement(name = "TxnOtp", nillable = true)
    public String getTxnOtp() {
        return TxnOtp;
    }

    /**
     * @param TxnOtp the TxnOtp to set
     */
    public void setTxnOtp(String TxnOtp) {
        this.TxnOtp = TxnOtp;
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
     * @return the SerialNo
     */
    @XmlElement(name = "SerialNo", nillable = true)
    public String getSerialNo() {
        return SerialNo;
    }

    /**
     * @param SerialNo the SerialNo to set
     */
    public void setSerialNo(String SerialNo) {
        this.SerialNo = SerialNo;
    }

    /**
     * @return the UserId
     */
    public String getUserId() {
        return UserId;
    }

    /**
     * @param UserId the UserId to set
     */
    public void setUserId(String UserId) {
        this.UserId = UserId;
    }
}
