/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author loinv3
 */
public class BHBLHealthCareIns implements Serializable {

    private static final long serialVersionUID = -1L;
    private long Id;
    private String FullName01;
    private String BirthDay01;
    private String IndenCard01;
    private String Address01;
    private String Email01;
    private String Phone01;
    private String FullName02;
    private String IndenCard02;
    private String Address02;
    private String BirthDay02;
    private String Email02;
    private String Phone02;
    private String RelationBuyer;
    private String EffDate;
    private String InsType;
    private Date CreatedDate;
    private Date UpdatedDate;
    private String RefFcubs;
    private String RefNum;
    private String Status;
    private int PackageIns;
    private String RelationInsBuyer;
    private float InsDuration;
    private String FullName03;
    private String BirthDay03;
    private String IndenCard03;
    private String Address03;
    private String Email03;
    private String Phone03;
    private String ChannelReg;
    private String IdPartner;
    private String PaymentMethod;
    private BigDecimal InpatientAmount;
    private BigDecimal PackageExtraCost;
    private BigDecimal DentalAmount;
    private BigDecimal HumanLifeAmount;
    private BigDecimal AccidentAmount;
    private BigDecimal OutpatientAmount;
    private BigDecimal MaternityAmount;
    private BigDecimal TotalCost;
    private Date DateMaker;
    private Date dateChecker;
    private Date TransDate;
    private Date PayDateFCUBS;
    private String IdUserMaker;
    private String IdUserChecker;
    private String BranchCode;
    private String FromAccount;
    private String ToAccount;
    private String ParentContractType;
    private String IdContractFollow;
    private int AgeRegister;
    private int AgePeopleRelationShip;
    private String IdContractPartner;
    private String Gender;
    private String Career;
    private String BuyerRegister;
    private int PPackageIns;
    private BigDecimal PInpatientAmount;
    private BigDecimal POutPatientAmount;
    private BigDecimal PAccidentAmount;
    private BigDecimal PHumanLifeAmount;
    private BigDecimal PDentalAmount;
    private BigDecimal PMaternityAmount;
    private String PEffDate;
    private int PInsDuration;
    private int paymentCode;
    private String PGender;

    public BHBLHealthCareIns() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getFullName01() {
        return FullName01;
    }

    public void setFullName01(String FullName01) {
        this.FullName01 = FullName01;
    }

    public String getBirthDay01() {
        return BirthDay01;
    }

    public void setBirthDay01(String BirthDay01) {
        this.BirthDay01 = BirthDay01;
    }

    public String getIndenCard01() {
        return IndenCard01;
    }

    public void setIndenCard01(String IndenCard01) {
        this.IndenCard01 = IndenCard01;
    }

    public String getAddress01() {
        return Address01;
    }

    public void setAddress01(String Address01) {
        this.Address01 = Address01;
    }

    public String getEmail01() {
        return Email01;
    }

    public void setEmail01(String Email01) {
        this.Email01 = Email01;
    }

    public String getPhone01() {
        return Phone01;
    }

    public void setPhone01(String Phone01) {
        this.Phone01 = Phone01;
    }

    public String getFullName02() {
        return FullName02;
    }

    public void setFullName02(String FullName02) {
        this.FullName02 = FullName02;
    }

    public String getIndenCard02() {
        return IndenCard02;
    }

    public void setIndenCard02(String IndenCard02) {
        this.IndenCard02 = IndenCard02;
    }

    public String getAddress02() {
        return Address02;
    }

    public void setAddress02(String Address02) {
        this.Address02 = Address02;
    }

    public String getBirthDay02() {
        return BirthDay02;
    }

    public void setBirthDay02(String BirthDay02) {
        this.BirthDay02 = BirthDay02;
    }

    public String getEmail02() {
        return Email02;
    }

    public void setEmail02(String Email02) {
        this.Email02 = Email02;
    }

    public String getPhone02() {
        return Phone02;
    }

    public void setPhone02(String Phone02) {
        this.Phone02 = Phone02;
    }

    public String getRelationBuyer() {
        return RelationBuyer;
    }

    public void setRelationBuyer(String RelationBuyer) {
        this.RelationBuyer = RelationBuyer;
    }

    public String getEffDate() {
        return EffDate;
    }

    public void setEffDate(String EffDate) {
        this.EffDate = EffDate;
    }

    public String getInsType() {
        return InsType;
    }

    public void setInsType(String InsType) {
        this.InsType = InsType;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    public Date getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(Date UpdatedDate) {
        this.UpdatedDate = UpdatedDate;
    }

    public String getRefFcubs() {
        return RefFcubs;
    }

    public void setRefFcubs(String RefFcubs) {
        this.RefFcubs = RefFcubs;
    }

    public String getRefNum() {
        return RefNum;
    }

    public void setRefNum(String RefNum) {
        this.RefNum = RefNum;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getPackageIns() {
        return PackageIns;
    }

    public void setPackageIns(int PackageIns) {
        this.PackageIns = PackageIns;
    }

    public String getRelationInsBuyer() {
        return RelationInsBuyer;
    }

    public void setRelationInsBuyer(String RelationInsBuyer) {
        this.RelationInsBuyer = RelationInsBuyer;
    }

    public float getInsDuration() {
        return InsDuration;
    }

    public void setInsDuration(float InsDuration) {
        this.InsDuration = InsDuration;
    }

    public String getFullName03() {
        return FullName03;
    }

    public void setFullName03(String FullName03) {
        this.FullName03 = FullName03;
    }

    public String getBirthDay03() {
        return BirthDay03;
    }

    public void setBirthDay03(String BirthDay03) {
        this.BirthDay03 = BirthDay03;
    }

    public String getIndenCard03() {
        return IndenCard03;
    }

    public void setIndenCard03(String IndenCard03) {
        this.IndenCard03 = IndenCard03;
    }

    public String getAddress03() {
        return Address03;
    }

    public void setAddress03(String Address03) {
        this.Address03 = Address03;
    }

    public String getEmail03() {
        return Email03;
    }

    public void setEmail03(String Email03) {
        this.Email03 = Email03;
    }

    public String getPhone03() {
        return Phone03;
    }

    public void setPhone03(String Phone03) {
        this.Phone03 = Phone03;
    }

    public String getChannelReg() {
        return ChannelReg;
    }

    public void setChannelReg(String ChannelReg) {
        this.ChannelReg = ChannelReg;
    }

    public String getIdPartner() {
        return IdPartner;
    }

    public void setIdPartner(String IdPartner) {
        this.IdPartner = IdPartner;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }

    public BigDecimal getInpatientAmount() {
        return InpatientAmount;
    }

    public void setInpatientAmount(BigDecimal InpatientAmount) {
        this.InpatientAmount = InpatientAmount;
    }

    public BigDecimal getPackageExtraCost() {
        return PackageExtraCost;
    }

    public void setPackageExtraCost(BigDecimal PackageExtraCost) {
        this.PackageExtraCost = PackageExtraCost;
    }

    public BigDecimal getDentalAmount() {
        return DentalAmount;
    }

    public void setDentalAmount(BigDecimal DentalAmount) {
        this.DentalAmount = DentalAmount;
    }

    public BigDecimal getHumanLifeAmount() {
        return HumanLifeAmount;
    }

    public void setHumanLifeAmount(BigDecimal HumanLifeAmount) {
        this.HumanLifeAmount = HumanLifeAmount;
    }

    public BigDecimal getAccidentAmount() {
        return AccidentAmount;
    }

    public void setAccidentAmount(BigDecimal AccidentAmount) {
        this.AccidentAmount = AccidentAmount;
    }

    public BigDecimal getOutpatientAmount() {
        return OutpatientAmount;
    }

    public void setOutpatientAmount(BigDecimal OutpatientAmount) {
        this.OutpatientAmount = OutpatientAmount;
    }

    public BigDecimal getMaternityAmount() {
        return MaternityAmount;
    }

    public void setMaternityAmount(BigDecimal MaternityAmount) {
        this.MaternityAmount = MaternityAmount;
    }

    public BigDecimal getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(BigDecimal TotalCost) {
        this.TotalCost = TotalCost;
    }

    public Date getDateMaker() {
        return DateMaker;
    }

    public void setDateMaker(Date DateMaker) {
        this.DateMaker = DateMaker;
    }

    public Date getDateChecker() {
        return dateChecker;
    }

    public void setDateChecker(Date dateChecker) {
        this.dateChecker = dateChecker;
    }

    public Date getTransDate() {
        return TransDate;
    }

    public void setTransDate(Date TransDate) {
        this.TransDate = TransDate;
    }

    public Date getPayDateFCUBS() {
        return PayDateFCUBS;
    }

    public void setPayDateFCUBS(Date PayDateFCUBS) {
        this.PayDateFCUBS = PayDateFCUBS;
    }

    public String getIdUserMaker() {
        return IdUserMaker;
    }

    public void setIdUserMaker(String IdUserMaker) {
        this.IdUserMaker = IdUserMaker;
    }

    public String getIdUserChecker() {
        return IdUserChecker;
    }

    public void setIdUserChecker(String IdUserChecker) {
        this.IdUserChecker = IdUserChecker;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String BranchCode) {
        this.BranchCode = BranchCode;
    }

    public String getFromAccount() {
        return FromAccount;
    }

    public void setFromAccount(String FromAccount) {
        this.FromAccount = FromAccount;
    }

    public String getToAccount() {
        return ToAccount;
    }

    public void setToAccount(String ToAccount) {
        this.ToAccount = ToAccount;
    }

    public String getParentContractType() {
        return ParentContractType;
    }

    public void setParentContractType(String ParentContractType) {
        this.ParentContractType = ParentContractType;
    }

    public String getIdContractFollow() {
        return IdContractFollow;
    }

    public void setIdContractFollow(String IdContractFollow) {
        this.IdContractFollow = IdContractFollow;
    }

    public int getAgeRegister() {
        return AgeRegister;
    }

    public void setAgeRegister(int AgeRegister) {
        this.AgeRegister = AgeRegister;
    }

    public int getAgePeopleRelationShip() {
        return AgePeopleRelationShip;
    }

    public void setAgePeopleRelationShip(int AgePeopleRelationShip) {
        this.AgePeopleRelationShip = AgePeopleRelationShip;
    }

    public String getIdContractPartner() {
        return IdContractPartner;
    }

    public void setIdContractPartner(String IdContractPartner) {
        this.IdContractPartner = IdContractPartner;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getCareer() {
        return Career;
    }

    public void setCareer(String Career) {
        this.Career = Career;
    }

    public String getBuyerRegister() {
        return BuyerRegister;
    }

    public void setBuyerRegister(String BuyerRegister) {
        this.BuyerRegister = BuyerRegister;
    }
    
    public int getPPackageIns() {
        return PPackageIns;
    }
    
    public void setPPackageIns(int PPackageIns) {
        this.PPackageIns = PPackageIns;
    }
    
    public BigDecimal getPInpatientAmount() {
        return PInpatientAmount;
    }

    public void setPInpatientAmount(BigDecimal PInpatientAmount) {
        this.PInpatientAmount = PInpatientAmount;
    }

    public BigDecimal getPOutPatientAmount() {
        return POutPatientAmount;
    }

    public void setPOutPatientAmount(BigDecimal POutPatientAmount) {
        this.POutPatientAmount = POutPatientAmount;
    }

    public BigDecimal getPAccidentAmount() {
        return PAccidentAmount;
    }

    public void setPAccidentAmount(BigDecimal PAccidentAmount) {
        this.PAccidentAmount = PAccidentAmount;
    }

    public BigDecimal getPHumanLifeAmount() {
        return PHumanLifeAmount;
    }

    public void setPHumanLifeAmount(BigDecimal PHumanLifeAmount) {
        this.PHumanLifeAmount = PHumanLifeAmount;
    }

    public BigDecimal getPDentalAmount() {
        return PDentalAmount;
    }

    public void setPDentalAmount(BigDecimal PDentalAmount) {
        this.PDentalAmount = PDentalAmount;
    }

    public BigDecimal getPMaternityAmount() {
        return PMaternityAmount;
    }

    public void setPMaternityAmount(BigDecimal PMaternityAmount) {
        this.PMaternityAmount = PMaternityAmount;
    }

    public String getPEffDate() {
        return PEffDate;
    }

    public void setPEffDate(String PEffDate) {
        this.PEffDate = PEffDate;
    }

    public int getPInsDuration() {
        return PInsDuration;
    }

    public void setPInsDuration(int PInsDuration) {
        this.PInsDuration = PInsDuration;
    }

    public int getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(int paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPGender() {
        return PGender;
    }

    public void setPGender(String PGender) {
        this.PGender = PGender;
    }
}
