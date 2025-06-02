/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lydty
 */
@XmlRootElement(name = "NAPAS_IBT")
public class NAPAS_IBT implements java.io.Serializable {

    String TYPETRANSFER;
    String FROMNUMBER;
    String PROCESSINGCODE;
    BigDecimal TRANSAMOUNT;
    String TRANSDATE;
    String AUDITNUMBER;
    String MERCHANTTYPE;
    String ACQUIRINGCODE;
    String AUTHORIZATIONCODE;
    String RESPONSECODE;
    String TERMID;
    String CARDACCEPTTOR;
    String DESTNUMBER;
    String NARRATION;
    String BENID;
    String TYPEFUNCTION;
    String Status;
    String RefCore;
    String CUSTNO;
    String RefCORE_REFUND;
    String REF_NO_F37;
    String SETT_DATE_F15;
    String PANF2;
    String AddDataF48;
    String TransREFNOF63;
    String CCYF49;
    String F22;
    String F25;
    String F60;
    BigDecimal F5;

    String SCBTypeCard;
    String SCBAccount;

    String LOC;
    String HIDENCARD;

    String USERCHECK;
    String USERAPPROVE;
    String REFNO;
    String FullName;
    String Address;

    @XmlElement(name = "USERCHECK")
    public String getUSERCHECK() {
        return USERCHECK;
    }

    public void setUSERCHECK(String USERCHECK) {
        this.USERCHECK = USERCHECK;
    }

    @XmlElement(name = "USERAPPROVE")
    public String getUSERAPPROVE() {
        return USERAPPROVE;
    }

    public void setUSERAPPROVE(String USERAPPROVE) {
        this.USERAPPROVE = USERAPPROVE;
    }

    @XmlElement(name = "REFNO")
    public String getREFNO() {
        return REFNO;
    }

    public void setREFNO(String REFNO) {
        this.REFNO = REFNO;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "LOC")
    public String getLOC() {
        return LOC;
    }

    /**
     *
     * @param LOC
     */
    public void setLOC(String LOC) {
        this.LOC = LOC;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "HIDENCARD")
    public String getHIDENCARD() {
        return HIDENCARD;
    }

    /**
     *
     * @param HIDENCARD
     */
    public void setHIDENCARD(String HIDENCARD) {
        this.HIDENCARD = HIDENCARD;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "SCBTypeCard")
    public String getSCBTypeCard() {
        return SCBTypeCard;
    }

    /**
     *
     * @param SCBTypeCard
     */
    public void setSCBTypeCard(String SCBTypeCard) {
        this.SCBTypeCard = SCBTypeCard;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "SCBAccount")
    public String getSCBAccount() {
        return SCBAccount;
    }

    /**
     *
     * @param SCBAccount
     */
    public void setSCBAccount(String SCBAccount) {
        this.SCBAccount = SCBAccount;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "F5")
    public BigDecimal getF5() {
        return F5;
    }

    /**
     *
     * @param F5
     */
    public void setF5(BigDecimal F5) {
        this.F5 = F5;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "TYPETRANSFER")
    public String getTYPETRANSFER() {
        return TYPETRANSFER;
    }

    /**
     *
     * @param TYPETRANSFER
     */
    public void setTYPETRANSFER(String TYPETRANSFER) {
        this.TYPETRANSFER = TYPETRANSFER;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "FROMNUMBER")
    public String getFROMNUMBER() {
        return FROMNUMBER;
    }

    /**
     *
     * @param FROMNUMBER
     */
    public void setFROMNUMBER(String FROMNUMBER) {
        this.FROMNUMBER = FROMNUMBER;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "PROCESSINGCODE")
    public String getPROCESSINGCODE() {
        return PROCESSINGCODE;
    }

    /**
     *
     * @param PROCESSINGCODE
     */
    public void setPROCESSINGCODE(String PROCESSINGCODE) {
        this.PROCESSINGCODE = PROCESSINGCODE;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "TRANSAMOUNT")
    public BigDecimal getTRANSAMOUNT() {
        return TRANSAMOUNT;
    }

    /**
     *
     * @param TRANSAMOUNT
     */
    public void setTRANSAMOUNT(BigDecimal TRANSAMOUNT) {
        this.TRANSAMOUNT = TRANSAMOUNT;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "TRANSDATE")
    public String getTRANSDATE() {
        return TRANSDATE;
    }

    /**
     *
     * @param TRANSDATE
     */
    public void setTRANSDATE(String TRANSDATE) {
        this.TRANSDATE = TRANSDATE;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AUDITNUMBER")
    public String getAUDITNUMBER() {
        return AUDITNUMBER;
    }

    /**
     *
     * @param AUDITNUMBER
     */
    public void setAUDITNUMBER(String AUDITNUMBER) {
        this.AUDITNUMBER = AUDITNUMBER;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "MERCHANTTYPE")
    public String getMERCHANTTYPE() {
        return MERCHANTTYPE;
    }

    /**
     *
     * @param MERCHANTTYPE
     */
    public void setMERCHANTTYPE(String MERCHANTTYPE) {
        this.MERCHANTTYPE = MERCHANTTYPE;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "ACQUIRINGCODE")
    public String getACQUIRINGCODE() {
        return ACQUIRINGCODE;
    }

    /**
     *
     * @param ACQUIRINGCODE
     */
    public void setACQUIRINGCODE(String ACQUIRINGCODE) {
        this.ACQUIRINGCODE = ACQUIRINGCODE;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AUTHORIZATIONCODE")
    public String getAUTHORIZATIONCODE() {
        return AUTHORIZATIONCODE;
    }

    /**
     *
     * @param AUTHORIZATIONCODE
     */
    public void setAUTHORIZATIONCODE(String AUTHORIZATIONCODE) {
        this.AUTHORIZATIONCODE = AUTHORIZATIONCODE;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "RESPONSECODE")
    public String getRESPONSECODE() {
        return RESPONSECODE;
    }

    /**
     *
     * @param RESPONSECODE
     */
    public void setRESPONSECODE(String RESPONSECODE) {
        this.RESPONSECODE = RESPONSECODE;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "TERMID")
    public String getTERMID() {
        return TERMID;
    }

    /**
     *
     * @param TERMID
     */
    public void setTERMID(String TERMID) {
        this.TERMID = TERMID;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CARDACCEPTTOR")
    public String getCARDACCEPTTOR() {
        return CARDACCEPTTOR;
    }

    /**
     *
     * @param CARDACCEPTTOR
     */
    public void setCARDACCEPTTOR(String CARDACCEPTTOR) {
        this.CARDACCEPTTOR = CARDACCEPTTOR;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "DESTNUMBER")
    public String getDESTNUMBER() {
        return DESTNUMBER;
    }

    /**
     *
     * @param DESTNUMBER
     */
    public void setDESTNUMBER(String DESTNUMBER) {
        this.DESTNUMBER = DESTNUMBER;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "NARRATION")
    public String getNARRATION() {
        return NARRATION;
    }

    /**
     *
     * @param NARRATION
     */
    public void setNARRATION(String NARRATION) {
        this.NARRATION = NARRATION;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "BENID")
    public String getBENID() {
        return BENID;
    }

    /**
     *
     * @param BENID
     */
    public void setBENID(String BENID) {
        this.BENID = BENID;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "TYPEFUNCTION")
    public String getTYPEFUNCTION() {
        return TYPEFUNCTION;
    }

    /**
     *
     * @param TYPEFUNCTION
     */
    public void setTYPEFUNCTION(String TYPEFUNCTION) {
        this.TYPEFUNCTION = TYPEFUNCTION;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "Status")
    public String getStatus() {
        return Status;
    }

    /**
     *
     * @param Status
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "RefCore")
    public String getRefCore() {
        return RefCore;
    }

    /**
     *
     * @param RefCore
     */
    public void setRefCore(String RefCore) {
        this.RefCore = RefCore;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CUSTNO")
    public String getCUSTNO() {
        return CUSTNO;
    }

    /**
     *
     * @param CUSTNO
     */
    public void setCUSTNO(String CUSTNO) {
        this.CUSTNO = CUSTNO;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "RefCORE_REFUND")
    public String getRefCORE_REFUND() {
        return RefCORE_REFUND;
    }

    /**
     *
     * @param RefCORE_REFUND
     */
    public void setRefCORE_REFUND(String RefCORE_REFUND) {
        this.RefCORE_REFUND = RefCORE_REFUND;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "REF_NO_F37")
    public String getREF_NO_F37() {
        return REF_NO_F37;
    }

    /**
     *
     * @param REF_NO_F37
     */
    public void setREF_NO_F37(String REF_NO_F37) {
        this.REF_NO_F37 = REF_NO_F37;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "SETT_DATE_F15")
    public String getSETT_DATE_F15() {
        return SETT_DATE_F15;
    }

    /**
     *
     * @param SETT_DATE_F15
     */
    public void setSETT_DATE_F15(String SETT_DATE_F15) {
        this.SETT_DATE_F15 = SETT_DATE_F15;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "PANF2")
    public String getPANF2() {
        return PANF2;
    }

    /**
     *
     * @param PANF2
     */
    public void setPANF2(String PANF2) {
        this.PANF2 = PANF2;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AddDataF48")
    public String getAddDataF48() {
        return AddDataF48;
    }

    /**
     *
     * @param AddDataF48
     */
    public void setAddDataF48(String AddDataF48) {
        this.AddDataF48 = AddDataF48;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "TransREFNOF63")
    public String getTransREFNOF63() {
        return TransREFNOF63;
    }

    /**
     *
     * @param TransREFNOF63
     */
    public void setTransREFNOF63(String TransREFNOF63) {
        this.TransREFNOF63 = TransREFNOF63;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CCYF49")
    public String getCCYF49() {
        return CCYF49;
    }

    /**
     *
     * @param CCYF49
     */
    public void setCCYF49(String CCYF49) {
        this.CCYF49 = CCYF49;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "F22")
    public String getF22() {
        return F22;
    }

    /**
     *
     * @param F22
     */
    public void setF22(String F22) {
        this.F22 = F22;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "F25")
    public String getF25() {
        return F25;
    }

    /**
     *
     * @param F25
     */
    public void setF25(String F25) {
        this.F25 = F25;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "F60")
    public String getF60() {
        return F60;
    }

    /**
     *
     * @param F60
     */
    public void setF60(String F60) {
        this.F60 = F60;
    }

    @XmlElement(name = "FullName")
    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    @XmlElement(name = "Address")
    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

}
