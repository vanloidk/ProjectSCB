/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.bhbl;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author loinv3
 */
public class BHBLCustomerContract {
    
    private Integer insType;
    private String fullName1;
    private String birthDay1;
    private String indenCard1;
    private String address1;
    private String email1;
    private String phone1;
    
    private Integer insPerson;
    private String fullName2;
    private String birthDay2;
    private String indenCard2;
    private String address2;
    private String email2;
    private String phone2;

    private String relation_buyer;
    private String effDate;
    private Integer insParent;

    private String fullName3;
    private String birthDay3;
    private String indenCard3;
    private String address3;
    private String email3;
    private String phone3;

    private String relationInsBuyer;
    private String packageIns;
    private BigDecimal packageCost;
    private BigDecimal packageExtraCost;
    private Float insDuration;
    private PackageExtra packageExtra;
    private Long question;
    private List<Integer> answers;

    public BHBLCustomerContract(){}
    
    public Integer getInsType() {
        return insType;
    }

    public void setInsType(Integer insType) {
        this.insType = insType;
    }

    public String getFullName1() {
        return fullName1;
    }

    public void setFullName1(String fullName1) {
        this.fullName1 = fullName1;
    }

    public String getBirthDay1() {
        return birthDay1;
    }

    public void setBirthDay1(String birthDay1) {
        this.birthDay1 = birthDay1;
    }

    public String getIndenCard1() {
        return indenCard1;
    }

    public void setIndenCard1(String indenCard1) {
        this.indenCard1 = indenCard1;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public Integer getInsPerson() {
        return insPerson;
    }

    public void setInsPerson(Integer insPerson) {
        this.insPerson = insPerson;
    }

    public String getFullName2() {
        return fullName2;
    }

    public void setFullName2(String fullName2) {
        this.fullName2 = fullName2;
    }

    public String getBirthDay2() {
        return birthDay2;
    }

    public void setBirthDay2(String birthDay2) {
        this.birthDay2 = birthDay2;
    }

    public String getIndenCard2() {
        return indenCard2;
    }

    public void setIndenCard2(String indenCard2) {
        this.indenCard2 = indenCard2;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getRelation_buyer() {
        return relation_buyer;
    }

    public void setRelation_buyer(String relation_buyer) {
        this.relation_buyer = relation_buyer;
    }

    public String getEffDate() {
        return effDate;
    }

    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    public Integer getInsParent() {
        return insParent;
    }

    public void setInsParent(Integer insParent) {
        this.insParent = insParent;
    }

    public String getFullName3() {
        return fullName3;
    }

    public void setFullName3(String fullName3) {
        this.fullName3 = fullName3;
    }

    public String getBirthDay3() {
        return birthDay3;
    }

    public void setBirthDay3(String birthDay3) {
        this.birthDay3 = birthDay3;
    }

    public String getIndenCard3() {
        return indenCard3;
    }

    public void setIndenCard3(String indenCard3) {
        this.indenCard3 = indenCard3;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getRelationInsBuyer() {
        return relationInsBuyer;
    }

    public void setRelationInsBuyer(String relationInsBuyer) {
        this.relationInsBuyer = relationInsBuyer;
    }

    public String getPackageIns() {
        return packageIns;
    }

    public void setPackageIns(String packageIns) {
        this.packageIns = packageIns;
    }

    public BigDecimal getPackageCost() {
        return packageCost;
    }

    public void setPackageCost(BigDecimal packageCost) {
        this.packageCost = packageCost;
    }

    public BigDecimal getPackageExtraCost() {
        return packageExtraCost;
    }

    public void setPackageExtraCost(BigDecimal packageExtraCost) {
        this.packageExtraCost = packageExtraCost;
    }

    public Float getInsDuration() {
        return insDuration;
    }

    public void setInsDuration(Float insDuration) {
        this.insDuration = insDuration;
    }

    public PackageExtra getPackageExtra() {
        return packageExtra;
    }

    public void setPackageExtra(PackageExtra packageExtra) {
        this.packageExtra = packageExtra;
    }

    public Long getQuestion() {
        return question;
    }

    public void setQuestion(Long question) {
        this.question = question;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }

    /**
     * dto package extra
     */
    public static class PackageExtra {

        private String except;
        private BigDecimal exceptAmount;
        private String humanLife;
        private BigDecimal humanLifeAmount;
        private String dental;
        private BigDecimal dentalAmount;
        private String maternity;
        private BigDecimal maternityAmount;

        public PackageExtra() {
        }

        public String getExcept() {
            return except;
        }

        public void setExcept(String except) {
            this.except = except;
        }

        public BigDecimal getExceptAmount() {
            return exceptAmount;
        }

        public void setExceptAmount(BigDecimal exceptAmount) {
            this.exceptAmount = exceptAmount;
        }

        public String getHumanLife() {
            return humanLife;
        }

        public void setHumanLife(String humanLife) {
            this.humanLife = humanLife;
        }

        public BigDecimal getHumanLifeAmount() {
            return humanLifeAmount;
        }

        public void setHumanLifeAmount(BigDecimal humanLifeAmount) {
            this.humanLifeAmount = humanLifeAmount;
        }

        public String getDental() {
            return dental;
        }

        public void setDental(String dental) {
            this.dental = dental;
        }

        public BigDecimal getDentalAmount() {
            return dentalAmount;
        }

        public void setDentalAmount(BigDecimal dentalAmount) {
            this.dentalAmount = dentalAmount;
        }

        public String getMaternity() {
            return maternity;
        }

        public void setMaternity(String maternity) {
            this.maternity = maternity;
        }

        public BigDecimal getMaternityAmount() {
            return maternityAmount;
        }

        public void setMaternityAmount(BigDecimal maternityAmount) {
            this.maternityAmount = maternityAmount;
        }

    }

}
