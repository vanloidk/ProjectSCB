package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "PayLoanAccountRq")
public class PayLoanAccountRq extends MobileRequest {

    private String FromAccount;
    private String ToAccount;
    private String DuNoHienTai;
    private String DuNoGocDenHan;
    private String LaiPhaiTra;
    private String LaiDenHan;
    private String PhatChamTraLai;
    private String PhatChamTraGoc;
    private String PrincipalAmount;
    private String InterestAmount;

    /**
     * @return the ToAccount
     */
    @XmlElement(name = "ToAccount", nillable = true)
    public String getToAccount() {
        return ToAccount;
    }

    /**
     * @param ToAccount the ToAccount to set
     */
    public void setToAccount(String ToAccount) {
        this.ToAccount = ToAccount;
    }

    /**
     * @return the DuNoHienTai
     */
    @XmlElement(name = "DuNoHienTai", nillable = true)
    public String getDuNoHienTai() {
        return DuNoHienTai;
    }

    /**
     * @param DuNoHienTai the DuNoHienTai to set
     */
    public void setDuNoHienTai(String DuNoHienTai) {
        this.DuNoHienTai = DuNoHienTai;
    }

    /**
     * @return the DuNoGocDenHan
     */
    @XmlElement(name = "DuNoGocDenHan", nillable = true)
    public String getDuNoGocDenHan() {
        return DuNoGocDenHan;
    }

    /**
     * @param DuNoGocDenHan the DuNoGocDenHan to set
     */
    public void setDuNoGocDenHan(String DuNoGocDenHan) {
        this.DuNoGocDenHan = DuNoGocDenHan;
    }

    /**
     * @return the LaiPhaiTra
     */
    @XmlElement(name = "LaiPhaiTra", nillable = true)
    public String getLaiPhaiTra() {
        return LaiPhaiTra;
    }

    /**
     * @param LaiPhaiTra the LaiPhaiTra to set
     */
    public void setLaiPhaiTra(String LaiPhaiTra) {
        this.LaiPhaiTra = LaiPhaiTra;
    }

    /**
     * @return the LaiDenHan
     */
    @XmlElement(name = "LaiDenHan", nillable = true)
    public String getLaiDenHan() {
        return LaiDenHan;
    }

    /**
     * @param LaiDenHan the LaiDenHan to set
     */
    public void setLaiDenHan(String LaiDenHan) {
        this.LaiDenHan = LaiDenHan;
    }

    /**
     * @return the PhatChamTraLai
     */
    @XmlElement(name = "PhatChamTraLai", nillable = true)
    public String getPhatChamTraLai() {
        return PhatChamTraLai;
    }

    /**
     * @param PhatChamTraLai the PhatChamTraLai to set
     */
    public void setPhatChamTraLai(String PhatChamTraLai) {
        this.PhatChamTraLai = PhatChamTraLai;
    }

    /**
     * @return the PhatChamTraGoc
     */
    @XmlElement(name = "PhatChamTraGoc", nillable = true)
    public String getPhatChamTraGoc() {
        return PhatChamTraGoc;
    }

    /**
     * @param PhatChamTraGoc the PhatChamTraGoc to set
     */
    public void setPhatChamTraGoc(String PhatChamTraGoc) {
        this.PhatChamTraGoc = PhatChamTraGoc;
    }

    /**
     * @return the PrincipalAmount
     */
    @XmlElement(name = "PrincipalAmount", nillable = true)
    public String getPrincipalAmount() {
        return PrincipalAmount;
    }

    /**
     * @param PrincipalAmount the PrincipalAmount to set
     */
    public void setPrincipalAmount(String PrincipalAmount) {
        this.PrincipalAmount = PrincipalAmount;
    }

    /**
     * @return the InterestAmount
     */
    @XmlElement(name = "InterestAmount", nillable = true)
    public String getInterestAmount() {
        return InterestAmount;
    }

    /**
     * @param InterestAmount the InterestAmount to set
     */
    public void setInterestAmount(String InterestAmount) {
        this.InterestAmount = InterestAmount;
    }

    /**
     * @return the FromAccount
     */
    public String getFromAccount() {
        return FromAccount;
    }

    /**
     * @param FromAccount the FromAccount to set
     */
    @XmlElement(name = "FromAccount", nillable = true)
    public void setFromAccount(String FromAccount) {
        this.FromAccount = FromAccount;
    }

}
