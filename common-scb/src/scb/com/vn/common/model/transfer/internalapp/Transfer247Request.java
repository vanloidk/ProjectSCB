package scb.com.vn.common.model.transfer.internalapp;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;

/**
 *
 * @author hienlt6
 */
@XmlRootElement(name = "Transfer247Request")
public class Transfer247Request {

    private static Logger logger = Logger.getLogger(Transfer247Request.class);

    private String FromNumber;
    private String TypeFromNumber;
    private String FullName; // Ten nguoi chuyen
    private String Address; // Dia chi nguoi chuyen 
    private String ToNumber;
    private String TypeToNumber; //ACCCOUNT/CARD
    private String BenID;
    private BigDecimal Amount;
    private String CCY;
    private String Channel;  //EBANK/MB/COUNTER1/COUNTER2/COUNTER3/ATM/CRM1/CRM2/CMR3
    private String Desc; // Noi dung ck
    private String TypeFunction; //QUE/TRN
    private String TransID; // MA GD
    private String TransDate; // Ngay GD
    private String UserMaker;
    private String UserChecker;
    private String Branch;
    private String IdCard;// so cmnd
    private String IdCardDob; // ngap cap
    private String IDCardAddress; //Noi cap

    @XmlElement(name = "FromNumber")
    public String getFromNumber() {
        return FromNumber;
    }

    public void setFromNumber(String FromNumber) {
        this.FromNumber = FromNumber;
    }

    @XmlElement(name = "TypeFromNumber")
    public String getTypeFromNumber() {
        return TypeFromNumber;
    }

    public void setTypeFromNumber(String TypeFromNumber) {
        this.TypeFromNumber = TypeFromNumber;
    }

    @XmlElement(name = "FullName")
    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    @XmlElement(name = "ToNumber")
    public String getToNumber() {
        return ToNumber;
    }

    public void setToNumber(String ToNumber) {
        this.ToNumber = ToNumber;
    }

    @XmlElement(name = "TypeToNumber")
    public String getTypeToNumber() {
        return TypeToNumber;
    }

    public void setTypeToNumber(String TypeToNumber) {
        this.TypeToNumber = TypeToNumber;
    }

    @XmlElement(name = "BenID")
    public String getBenID() {
        return BenID;
    }

    public void setBenID(String BenID) {
        this.BenID = BenID;
    }

    @XmlElement(name = "Amount")
    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal Amount) {
        this.Amount = Amount;
    }

    @XmlElement(name = "CCY")
    public String getCCY() {
        return CCY;
    }

    public void setCCY(String CCY) {
        this.CCY = CCY;
    }

    @XmlElement(name = "Channel")
    public String getChannel() {
        return Channel;
    }

    public void setChannel(String Channel) {
        this.Channel = Channel;
    }

    @XmlElement(name = "Desc")
    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    @XmlElement(name = "TypeFunction")
    public String getTypeFunction() {
        return TypeFunction;
    }

    public void setTypeFunction(String TypeFunction) {
        this.TypeFunction = TypeFunction;
    }

    @XmlElement(name = "TransID")
    public String getTransID() {
        return TransID;
    }

    public void setTransID(String TransID) {
        this.TransID = TransID;
    }

    @XmlElement(name = "Address")
    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    @XmlElement(name = "TransDate")
    public String getTransDate() {
        return TransDate;
    }

    public void setTransDate(String TransDate) {
        this.TransDate = TransDate;
    }

    @XmlElement(name = "UserMaker")
    public String getUserMaker() {
        return UserMaker;
    }

    public void setUserMaker(String UserMaker) {
        this.UserMaker = UserMaker;
    }

    @XmlElement(name = "UserChecker")
    public String getUserChecker() {
        return UserChecker;
    }

    public void setUserChecker(String UserChecker) {
        this.UserChecker = UserChecker;
    }

    @XmlElement(name = "Branch")
    public String getBranch() {
        return Branch;
    }

    public void setBranch(String Branch) {
        this.Branch = Branch;
    }

    @XmlElement(name = "IdCard")
    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String IdCard) {
        this.IdCard = IdCard;
    }

    @XmlElement(name = "IdCardDob")
    public String getIdCardDob() {
        return IdCardDob;
    }

    public void setIdCardDob(String IdCardDob) {
        this.IdCardDob = IdCardDob;
    }

    @XmlElement(name = "IDCardAddress")
    public String getIDCardAddress() {
        return IDCardAddress;
    }

    public void setIDCardAddress(String IDCardAddress) {
        this.IDCardAddress = IDCardAddress;
    }

    private boolean isInvalidTypeToNumber() {
        boolean isInvalid = true;
        if (this.TypeToNumber != null) {
            switch (this.TypeToNumber) {
                case "ACCOUNT":
                    if (this.BenID != null) {
                        isInvalid = false;
                    }
                    break;
                case "CARD":
                    isInvalid = false;
                    break;
                default:
                    break;
            }
        }
        return isInvalid;
    }

    public boolean isInvalidValue() {
        return this.TransID == null
                || this.TransDate == null
                || this.FromNumber == null
                || this.TypeFromNumber == null
                || this.FullName == null
                || this.Address == null
                || isInvalidTypeToNumber()
                || this.CCY == null;
    }

    public boolean isOutOfTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date executeTime = format.parse(this.TransDate);
            Calendar now = Calendar.getInstance();
            long diffInMillies = Math.abs(now.getTime().getTime() - executeTime.getTime());
            if (diffInMillies <= 5 * 60 * 1000) { // 5 minutes
                return false;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

}
