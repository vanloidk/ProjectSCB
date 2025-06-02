/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "HealthCareContractRq")
public class BHBLHealthCareInsRq implements Serializable {

    private static final long serialVersionUID = -1L;
    private String fullName01;
    private String birthDay01;
    private String indenCard01;
    private String address01;
    private String email01;
    private String phone01;
    private String fullName02;
    private String indenCard02;
    private String address02;
    private String birthDay02;
    private String email02;
    private String phone02;
    private String relationBuyer;
    private String effDate;
    private String insType;
    private PackageCost01 packageCost01;
    private PackageCost02 packageCost02;
    private BigDecimal totalCost;
    private Date createdDate;
    private Date updatedDate;
    private String refFcubs;
    private String refNum;
    private String status;
    private String answers;
    private int packageIns;
    private BigDecimal packageExtraCost;
    private String relationInsBuyer;
    private float insDuration;
    private String fullName03;
    private String birthDay03;
    private String indenCard03;
    private String address03;
    private String email03;
    private String phone03;
    private String buyerRegister;
    private String channelReg;
    private String idPartner;
    private String paymentMethod;
    private Date dateMaker;
    private Date dateChecker;
    private Date transDate;
    private Date payDateFCUBS;
    private String idUserMaker;
    private String idUserChecker;
    private String branchCode;
    private String fromAccount;
    private String toAccount;
    private String idContractFollow;
    private String idContractPartner;
    private int ageRegister;
    private int agePeopleRelationShip;
    private String gender;
    private String career;
    private String parentContractType;
    private int PPackageIns;
    private String PEffDate;
    private int PInsDuration;
    private String PGender;

    private Questions questions;

    public BHBLHealthCareInsRq() {
    }

    @XmlElement(name = "PackageCost01", required = true)
    public PackageCost01 getPackageCost01() {
        return packageCost01;
    }

    public void setPackageCost01(PackageCost01 packageCost01) {
        this.packageCost01 = packageCost01;
    }

    @XmlElement(name = "PackageCost02", required = false, nillable = true)
    public PackageCost02 getPackageCost02() {
        return packageCost02;
    }

    public void setPackageCost02(PackageCost02 packageCost02) {
        this.packageCost02 = packageCost02;
    }

    @XmlElement(name = "FullName01", required = true)
    public String getFullName01() {
        return fullName01;
    }

    public void setFullName01(String fullName01) {
        this.fullName01 = fullName01;
    }

    @XmlElement(name = "BirthDay01", required = false, nillable = true)
    public String getBirthDay01() {
        return birthDay01;
    }

    public void setBirthDay01(String birthDay01) {
        this.birthDay01 = birthDay01;
    }

    @XmlElement(name = "IndenCard01", required = true)
    public String getIndenCard01() {
        return indenCard01;
    }

    public void setIndenCard01(String indenCard01) {
        this.indenCard01 = indenCard01;
    }

    @XmlElement(name = "Address01", required = true)
    public String getAddress01() {
        return address01;
    }

    public void setAddress01(String address01) {
        this.address01 = address01;
    }

    @XmlElement(name = "Email01", required = false, nillable = true)
    public String getEmail01() {
        return email01;
    }

    public void setEmail01(String email01) {
        this.email01 = email01;
    }

    @XmlElement(name = "Phone01", required = true)
    public String getPhone01() {
        return phone01;
    }

    public void setPhone01(String phone01) {
        this.phone01 = phone01;
    }

    @XmlElement(name = "FullName02", required = true)
    public String getFullName02() {
        return fullName02;
    }

    public void setFullName02(String fullName02) {
        this.fullName02 = fullName02;
    }

    @XmlElement(name = "IndenCard02", required = false, nillable = true)
    public String getIndenCard02() {
        return indenCard02;
    }

    public void setIndenCard02(String indenCard02) {
        this.indenCard02 = indenCard02;
    }

    @XmlElement(name = "Address02", required = true)
    public String getAddress02() {
        return address02;
    }

    public void setAddress02(String address02) {
        this.address02 = address02;
    }

    @XmlElement(name = "BirthDay02", required = true)
    public String getBirthDay02() {
        return birthDay02;
    }

    public void setBirthDay02(String birthDay02) {
        this.birthDay02 = birthDay02;
    }

    @XmlElement(name = "Email02", required = false, nillable = true)
    public String getEmail02() {
        return email02;
    }

    public void setEmail02(String email02) {
        this.email02 = email02;
    }

    @XmlElement(name = "Phone02", required = true)
    public String getPhone02() {
        return phone02;
    }

    public void setPhone02(String phone02) {
        this.phone02 = phone02;
    }

    @XmlElement(name = "RelationBuyer", required = true)
    public String getRelationBuyer() {
        return relationBuyer;
    }

    public void setRelationBuyer(String relationBuyer) {
        this.relationBuyer = relationBuyer;
    }

    @XmlElement(name = "EffDate", required = true)
    public String getEffDate() {
        return effDate;
    }

    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    @XmlElement(name = "InsType", required = false, nillable = true)
    public String getInsType() {
        return insType;
    }

    public void setInsType(String insType) {
        this.insType = insType;
    }

    @XmlElement(name = "TotalCost", required = false, nillable = true)
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @XmlElement(name = "CreatedDate", required = false, nillable = true)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @XmlElement(name = "UpdatedDate", required = false, nillable = true)
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @XmlElement(name = "RefFcubs", required = false, nillable = true)
    public String getRefFcubs() {
        return refFcubs;
    }

    public void setRefFcubs(String refFcubs) {
        this.refFcubs = refFcubs;
    }

    @XmlElement(name = "RefNum", required = false, nillable = true)
    public String getRefNum() {
        return refNum;
    }

    public void setRefNum(String refNum) {
        this.refNum = refNum;
    }

    @XmlElement(name = "Status", required = false, nillable = true)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "Answers", required = false, nillable = true)
    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    @XmlElement(name = "IdPackageContract", required = false, nillable = true)
    public int getPackageIns() {
        return packageIns;
    }

    public void setPackageIns(int packageIns) {
        this.packageIns = packageIns;
    }

    @XmlElement(name = "PackageExtraCost", required = false, nillable = true)
    public BigDecimal getPackageExtraCost() {
        return packageExtraCost;
    }

    public void setPackageExtraCost(BigDecimal packageExtraCost) {
        this.packageExtraCost = packageExtraCost;
    }

    @XmlElement(name = "RelationInsBuyer", required = false, nillable = true)
    public String getRelationInsBuyer() {
        return relationInsBuyer;
    }

    public void setRelationInsBuyer(String relationInsBuyer) {
        this.relationInsBuyer = relationInsBuyer;
    }

    @XmlElement(name = "InsDuration", required = false, nillable = true)
    public float getInsDuration() {
        return insDuration;
    }

    public void setInsDuration(float insDuration) {
        this.insDuration = insDuration;
    }

    @XmlElement(name = "FullName03", required = false, nillable = true)
    public String getFullName03() {
        return fullName03;
    }

    public void setFullName03(String fullName03) {
        this.fullName03 = fullName03;
    }

    @XmlElement(name = "BirthDay03", required = false, nillable = true)
    public String getBirthDay03() {
        return birthDay03;
    }

    public void setBirthDay03(String birthDay03) {
        this.birthDay03 = birthDay03;
    }

    @XmlElement(name = "IndenCard03", required = false, nillable = true)
    public String getIndenCard03() {
        return indenCard03;
    }

    public void setIndenCard03(String indenCard03) {
        this.indenCard03 = indenCard03;
    }

    @XmlElement(name = "Address03", required = false, nillable = true)
    public String getAddress03() {
        return address03;
    }

    public void setAddress03(String address03) {
        this.address03 = address03;
    }

    @XmlElement(name = "Email03", required = false, nillable = true)
    public String getEmail03() {
        return email03;
    }

    public void setEmail03(String email03) {
        this.email03 = email03;
    }

    @XmlElement(name = "Phone03", required = false, nillable = true)
    public String getPhone03() {
        return phone03;
    }

    public void setPhone03(String phone03) {
        this.phone03 = phone03;
    }

    @XmlElement(name = "BuyerRegister", required = false, nillable = true)
    public String getBuyerRegister() {
        return buyerRegister;
    }

    public void setBuyerRegister(String buyerRegister) {
        this.buyerRegister = buyerRegister;
    }

    @XmlElement(name = "ChannelReg", required = false, nillable = true)
    public String getChannelReg() {
        return channelReg;
    }

    public void setChannelReg(String channelReg) {
        this.channelReg = channelReg;
    }

    @XmlElement(name = "IdPartner", required = false, nillable = true)
    public String getIdPartner() {
        return idPartner;
    }

    public void setIdPartner(String idPartner) {
        this.idPartner = idPartner;
    }

    @XmlElement(name = "PaymentMethod", required = false, nillable = true)
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @XmlElement(name = "DateMaker", required = false, nillable = true)
    public Date getDateMaker() {
        return dateMaker;
    }

    public void setDateMaker(Date dateMaker) {
        this.dateMaker = dateMaker;
    }

    @XmlElement(name = "DateChecker", required = false, nillable = true)
    public Date getDateChecker() {
        return dateChecker;
    }

    public void setDateChecker(Date dateChecker) {
        this.dateChecker = dateChecker;
    }

    @XmlElement(name = "TransDate", required = false, nillable = true)
    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    @XmlElement(name = "PayDateFCUBS", required = false, nillable = true)
    public Date getPayDateFCUBS() {
        return payDateFCUBS;
    }

    public void setPayDateFCUBS(Date payDateFCUBS) {
        this.payDateFCUBS = payDateFCUBS;
    }

    @XmlElement(name = "IdUserMaker", required = false, nillable = true)
    public String getIdUserMaker() {
        return idUserMaker;
    }

    public void setIdUserMaker(String idUserMaker) {
        this.idUserMaker = idUserMaker;
    }

    @XmlElement(name = "IdUserChecker", required = false, nillable = true)
    public String getIdUserChecker() {
        return idUserChecker;
    }

    public void setIdUserChecker(String idUserChecker) {
        this.idUserChecker = idUserChecker;
    }

    @XmlElement(name = "BranchCode", required = false, nillable = true)
    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @XmlElement(name = "FromAccount", required = false, nillable = true)
    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    @XmlElement(name = "ToAccount", required = false, nillable = true)
    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    @XmlElement(name = "IdContractFollow", required = false, nillable = true)
    public String getIdContractFollow() {
        return idContractFollow;
    }

    public void setIdContractFollow(String idContractFollow) {
        this.idContractFollow = idContractFollow;
    }

    @XmlElement(name = "IdContractPartner", required = false, nillable = true)
    public String getIdContractPartner() {
        return idContractPartner;
    }

    public void setIdContractPartner(String idContractPartner) {
        this.idContractPartner = idContractPartner;
    }

    @XmlElement(name = "AgeRegister", required = false, nillable = true)
    public int getAgeRegister() {
        return ageRegister;
    }

    public void setAgeRegister(int ageRegister) {
        this.ageRegister = ageRegister;
    }

    @XmlElement(name = "AgePeopleRelationShip", required = false, nillable = true)
    public int getAgePeopleRelationShip() {
        return agePeopleRelationShip;
    }

    public void setAgePeopleRelationShip(int agePeopleRelationShip) {
        this.agePeopleRelationShip = agePeopleRelationShip;
    }

    @XmlElement(name = "Gender", required = true)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @XmlElement(name = "Career", required = false, nillable = true)
    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    @XmlElement(name = "Questions", required = true)
    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    @XmlElement(name = "ParentContractType", required = false, nillable = true)
    public String getParentContractType() {
        return parentContractType;
    }

    public void setParentContractType(String parentContractType) {
        this.parentContractType = parentContractType;
    }

    @XmlElement(name = "PIdPackageContract", required = false, nillable = true)
    public int getPPackageIns() {
        return PPackageIns;
    }

    public void setPPackageIns(int PPackageIns) {
        this.PPackageIns = PPackageIns;
    }
    
    @XmlElement(name = "PEffDate", required = false, nillable = true)
    public String getPEffDate() {
        return PEffDate;
    }

    public void setPEffDate(String PEffDate) {
        this.PEffDate = PEffDate;
    }

    @XmlElement(name = "PInsDuration", required = false, nillable = true)
    public int getPInsDuration() {
        return PInsDuration;
    }

    public void setPInsDuration(int PInsDuration) {
        this.PInsDuration = PInsDuration;
    }

    @XmlElement(name = "PGender", required = false, nillable = true)
    public String getPGender() {
        return PGender;
    }

    public void setPGender(String PGender) {
        this.PGender = PGender;
    }

    public static class Questions implements Serializable {

        private static final long serialVersionUID = -1L;

        private List<question> Question;

        @XmlElement(name = "Question", required = false, nillable = true)
        public List<question> getQuestion() {
            return Question;
        }

        public void setQuestion(List<question> Question) {
            this.Question = Question;
        }
    }

    public static class question implements Serializable {

        private static final long serialVersionUID = -1L;

        private int id;
        private List<Answer> answer;

        @XmlElement(name = "Id", required = false, nillable = true)
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @XmlElement(name = "Answer", required = false, nillable = true)
        public List<Answer> getAnswer() {
            return answer;
        }

        public void setAnswer(List<Answer> answer) {
            this.answer = answer;
        }

    }

    public static class Answer implements Serializable {

        private static final long serialVersionUID = -1L;

        private int id;
        private String content;

        @XmlElement(name = "Id", required = false, nillable = true)
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @XmlElement(name = "Content", required = false, nillable = true)
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class PackageCost01 implements Serializable {

        private static final long serialVersionUID = -1L;

        private BigDecimal inpatient;
        private BigDecimal outpatient;
        private BigDecimal accident;
        private BigDecimal humanLife;
        private BigDecimal dental;
        private BigDecimal maternity;

        @XmlElement(name = "Inpatient", required = false, nillable = true)
        public BigDecimal getInpatient() {
            return inpatient;
        }

        public void setInpatient(BigDecimal inpatient) {
            this.inpatient = inpatient;
        }

        @XmlElement(name = "Outpatient", required = false, nillable = true)
        public BigDecimal getOutpatient() {
            return outpatient;
        }

        public void setOutpatient(BigDecimal outpatient) {
            this.outpatient = outpatient;
        }

        @XmlElement(name = "Accident", required = false, nillable = true)
        public BigDecimal getAccident() {
            return accident;
        }

        public void setAccident(BigDecimal accident) {
            this.accident = accident;
        }

        @XmlElement(name = "HumanLife", required = false, nillable = true)
        public BigDecimal getHumanLife() {
            return humanLife;
        }

        public void setHumanLife(BigDecimal humanLife) {
            this.humanLife = humanLife;
        }

        @XmlElement(name = "Dental", required = false, nillable = true)
        public BigDecimal getDental() {
            return dental;
        }

        public void setDental(BigDecimal dental) {
            this.dental = dental;
        }

        @XmlElement(name = "Maternity", required = false, nillable = true)
        public BigDecimal getMaternity() {
            return maternity;
        }

        public void setMaternity(BigDecimal maternity) {
            this.maternity = maternity;
        }

    }

    public static class PackageCost02 implements Serializable {

        private static final long serialVersionUID = -1L;

        private BigDecimal inpatient;
        private BigDecimal outpatient;
        private BigDecimal accident;
        private BigDecimal humanLife;
        private BigDecimal dental;
        private BigDecimal maternity;

        @XmlElement(name = "PInpatient", required = false, nillable = true)
        public BigDecimal getInpatient() {
            return inpatient;
        }

        public void setInpatient(BigDecimal inpatient) {
            this.inpatient = inpatient;
        }

        @XmlElement(name = "POutpatient", required = false, nillable = true)
        public BigDecimal getOutpatient() {
            return outpatient;
        }

        public void setOutpatient(BigDecimal outpatient) {
            this.outpatient = outpatient;
        }

        @XmlElement(name = "PAccident", required = false, nillable = true)
        public BigDecimal getAccident() {
            return accident;
        }

        public void setAccident(BigDecimal accident) {
            this.accident = accident;
        }

        @XmlElement(name = "PHumanLife", required = false, nillable = true)
        public BigDecimal getHumanLife() {
            return humanLife;
        }

        public void setHumanLife(BigDecimal humanLife) {
            this.humanLife = humanLife;
        }

        @XmlElement(name = "PDental", required = false, nillable = true)
        public BigDecimal getDental() {
            return dental;
        }

        public void setDental(BigDecimal dental) {
            this.dental = dental;
        }

        @XmlElement(name = "PMaternity", required = false, nillable = true)
        public BigDecimal getMaternity() {
            return maternity;
        }

        public void setMaternity(BigDecimal maternity) {
            this.maternity = maternity;
        }

    }

}
