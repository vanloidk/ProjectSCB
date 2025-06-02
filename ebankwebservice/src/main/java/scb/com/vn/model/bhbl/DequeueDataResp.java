/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.bhbl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author loinv3
 */
@XmlType(propOrder = {"fullname01", "birthday01", "idencard01", "address01",
    "phone01", "email01", "fullname02", "birthday02", "idencard02", "address02", "phone02", "email02",
    "fullname03", "birthday03", "idencard03", "address03", "phone03", "email03", "numberContractFollow",
    "gender01", "career", "relation", "relationName", "basedate01", "basedate02", "package01", "package02", "gender02", "benefits01", "benefits02", "questions"})
public class DequeueDataResp {

    private String Fullname01;
    private String Birthday01;
    private String Idencard01;
    private String Address01;
    private String Phone01;
    private String Email01;
    private String Fullname02;
    private String Birthday02;
    private String Idencard02;
    private String Address02;
    private String Phone02;
    private String Email02;
    private String Fullname03;
    private String Birthday03;
    private String Idencard03;
    private String Address03;
    private String Phone03;
    private String Email03;
    private String NumberContractFollow;
    private String Gender01;
    private String Career;
    private String Relation;
    private String RelationName;
    private String Basedate01;
    private String Basedate02;
    private String Package01;
    private String Package02;
    private String Gender02;
    private Benefits01 Benefits01;
    private Benefits02 Benefits02;

    private Questions Questions;

    @XmlElement(name = "Fullname01")
    public String getFullname01() {
        return Fullname01;
    }

    public void setFullname01(String Fullname01) {
        this.Fullname01 = Fullname01;
    }

    @XmlElement(name = "Birthday01")
    public String getBirthday01() {
        return Birthday01;
    }

    public void setBirthday01(String Birthday01) {
        this.Birthday01 = Birthday01;
    }

    @XmlElement(name = "Idencard01")
    public String getIdencard01() {
        return Idencard01;
    }

    public void setIdencard01(String Idencard01) {
        this.Idencard01 = Idencard01;
    }

    @XmlElement(name = "Address01")
    public String getAddress01() {
        return Address01;
    }

    public void setAddress01(String Address01) {
        this.Address01 = Address01;
    }

    @XmlElement(name = "Phone01")
    public String getPhone01() {
        return Phone01;
    }

    public void setPhone01(String Phone01) {
        this.Phone01 = Phone01;
    }

    @XmlElement(name = "Email01")
    public String getEmail01() {
        return Email01;
    }

    public void setEmail01(String Email01) {
        this.Email01 = Email01;
    }

    @XmlElement(name = "Fullname02")
    public String getFullname02() {
        return Fullname02;
    }

    public void setFullname02(String Fullname02) {
        this.Fullname02 = Fullname02;
    }

    @XmlElement(name = "Birthday02")
    public String getBirthday02() {
        return Birthday02;
    }

    public void setBirthday02(String Birthday02) {
        this.Birthday02 = Birthday02;
    }

    @XmlElement(name = "Idencard02")
    public String getIdencard02() {
        return Idencard02;
    }

    public void setIdencard02(String Idencard02) {
        this.Idencard02 = Idencard02;
    }

    @XmlElement(name = "Address02")
    public String getAddress02() {
        return Address02;
    }

    public void setAddress02(String Address02) {
        this.Address02 = Address02;
    }

    @XmlElement(name = "Phone02")
    public String getPhone02() {
        return Phone02;
    }

    public void setPhone02(String Phone02) {
        this.Phone02 = Phone02;
    }

    @XmlElement(name = "Email02")
    public String getEmail02() {
        return Email02;
    }

    public void setEmail02(String Email02) {
        this.Email02 = Email02;
    }

    @XmlElement(name = "Fullname03")
    public String getFullname03() {
        return Fullname03;
    }

    public void setFullname03(String Fullname03) {
        this.Fullname03 = Fullname03;
    }

    @XmlElement(name = "Birthday03")
    public String getBirthday03() {
        return Birthday03;
    }

    public void setBirthday03(String Birthday03) {
        this.Birthday03 = Birthday03;
    }

    @XmlElement(name = "Idencard03")
    public String getIdencard03() {
        return Idencard03;
    }

    public void setIdencard03(String Idencard03) {
        this.Idencard03 = Idencard03;
    }

    @XmlElement(name = "Address03")
    public String getAddress03() {
        return Address03;
    }

    public void setAddress03(String Address03) {
        this.Address03 = Address03;
    }

    @XmlElement(name = "Phone03")
    public String getPhone03() {
        return Phone03;
    }

    public void setPhone03(String Phone03) {
        this.Phone03 = Phone03;
    }

    @XmlElement(name = "Email03")
    public String getEmail03() {
        return Email03;
    }

    public void setEmail03(String Email03) {
        this.Email03 = Email03;
    }

    @XmlElement(name = "NumberContractFollow")
    public String getNumberContractFollow() {
        return NumberContractFollow;
    }

    public void setNumberContractFollow(String NumberContractFollow) {
        this.NumberContractFollow = NumberContractFollow;
    }

    @XmlElement(name = "Career")
    public String getCareer() {
        return Career;
    }

    public void setCareer(String Career) {
        this.Career = Career;
    }

    @XmlElement(name = "Relation")
    public String getRelation() {
        return Relation;
    }

    public void setRelation(String Relation) {
        this.Relation = Relation;
    }

    @XmlElement(name = "RelationName")
    public String getRelationName() {
        return RelationName;
    }

    public void setRelationName(String RelationName) {
        this.RelationName = RelationName;
    }

    @XmlElement(name = "Basedate01")
    public String getBasedate01() {
        return Basedate01;
    }

    public void setBasedate01(String Basedate01) {
        this.Basedate01 = Basedate01;
    }

    @XmlElement(name = "Basedate02")
    public String getBasedate02() {
        return Basedate02;
    }

    public void setBasedate02(String Basedate02) {
        this.Basedate02 = Basedate02;
    }

    @XmlElement(name = "Gender")
    public String getGender01() {
        return Gender01;
    }

    public void setGender01(String Gender01) {
        this.Gender01 = Gender01;
    }

    @XmlElement(name = "Package")
    public String getPackage01() {
        return Package01;
    }

    public void setPackage01(String Package01) {
        this.Package01 = Package01;
    }

    @XmlElement(name = "PPackage")
    public String getPackage02() {
        return Package02;
    }

    public void setPackage02(String Package02) {
        this.Package02 = Package02;
    }

    @XmlElement(name = "PGender")
    public String getGender02() {
        return Gender02;
    }

    public void setGender02(String Gender02) {
        this.Gender02 = Gender02;
    }

    @XmlElement(name = "PackageCost01")
    public Benefits01 getBenefits01() {
        return Benefits01;
    }

    public void setBenefits01(Benefits01 Benefits01) {
        this.Benefits01 = Benefits01;
    }

    @XmlElement(name = "PackageCost02")
    public Benefits02 getBenefits02() {
        return Benefits02;
    }

    public void setBenefits02(Benefits02 Benefits02) {
        this.Benefits02 = Benefits02;
    }

    @XmlElement(name = "Questions")
    public Questions getQuestions() {
        return Questions;
    }

    public void setQuestions(Questions Questions) {
        this.Questions = Questions;
    }

    @XmlType(propOrder = {"benefit00", "benefit01", "benefit02", "benefit03",
        "benefit04", "benefit05"})
    public static class Benefits01 {

        private String Benefit00;
        private String Benefit01;
        private String Benefit02;
        private String Benefit03;
        private String Benefit04;
        private String Benefit05;

        @XmlElement(name = "Inpatient")
        public String getBenefit00() {
            return Benefit00;
        }

        public void setBenefit00(String Benefit00) {
            this.Benefit00 = Benefit00;
        }

        @XmlElement(name = "Outpatient")
        public String getBenefit01() {
            return Benefit01;
        }

        public void setBenefit01(String Benefit01) {
            this.Benefit01 = Benefit01;
        }

        @XmlElement(name = "Dental")
        public String getBenefit02() {
            return Benefit02;
        }

        public void setBenefit02(String Benefit02) {
            this.Benefit02 = Benefit02;
        }

        @XmlElement(name = "Accident")
        public String getBenefit03() {
            return Benefit03;
        }

        public void setBenefit03(String Benefit03) {
            this.Benefit03 = Benefit03;
        }

        @XmlElement(name = "HumanLife")
        public String getBenefit04() {
            return Benefit04;
        }

        public void setBenefit04(String Benefit04) {
            this.Benefit04 = Benefit04;
        }

        @XmlElement(name = "Maternity")
        public String getBenefit05() {
            return Benefit05;
        }

        public void setBenefit05(String Benefit05) {
            this.Benefit05 = Benefit05;
        }

    }

    @XmlType(propOrder = {"benefit06", "benefit07", "benefit08", "benefit09",
        "benefit10", "benefit11"})
    public static class Benefits02 {

        private String Benefit06;
        private String Benefit07;
        private String Benefit08;
        private String Benefit09;
        private String Benefit10;
        private String Benefit11;

        @XmlElement(name = "PInpatient")
        public String getBenefit06() {
            return Benefit06;
        }

        public void setBenefit06(String Benefit06) {
            this.Benefit06 = Benefit06;
        }

        @XmlElement(name = "POutpatient")
        public String getBenefit07() {
            return Benefit07;
        }

        public void setBenefit07(String Benefit07) {
            this.Benefit07 = Benefit07;
        }

        @XmlElement(name = "PDental")
        public String getBenefit08() {
            return Benefit08;
        }

        public void setBenefit08(String Benefit08) {
            this.Benefit08 = Benefit08;
        }

        @XmlElement(name = "PAccident")
        public String getBenefit09() {
            return Benefit09;
        }

        public void setBenefit09(String Benefit09) {
            this.Benefit09 = Benefit09;
        }

        @XmlElement(name = "PHumanLife")
        public String getBenefit10() {
            return Benefit10;
        }

        public void setBenefit10(String Benefit10) {
            this.Benefit10 = Benefit10;
        }

        @XmlElement(name = "PMaternity")
        public String getBenefit11() {
            return Benefit11;
        }

        public void setBenefit11(String Benefit11) {
            this.Benefit11 = Benefit11;
        }

    }

    @XmlType(propOrder = {"question01", "answer01a", "answer01b", "question02", "answer02a",
        "answer02b", "answer02c", "answer02d", "answer02e", "answer02f"})
    public static class Questions {

        private String Question01;
        private String Answer01a;
        private String Answer01b;
        private String Question02;
        private String Answer02a;
        private String Answer02b;
        private String Answer02c;
        private String Answer02d;
        private String Answer02e;
        private String Answer02f;

        @XmlElement(name = "Question01")
        public String getQuestion01() {
            return Question01;
        }

        public void setQuestion01(String Question01) {
            this.Question01 = Question01;
        }

        @XmlElement(name = "Answer01a")

        public String getAnswer01a() {
            return Answer01a;
        }

        public void setAnswer01a(String Answer01a) {
            this.Answer01a = Answer01a;
        }

        @XmlElement(name = "Answer01b")
        public String getAnswer01b() {
            return Answer01b;
        }

        public void setAnswer01b(String Answer01b) {
            this.Answer01b = Answer01b;
        }

        @XmlElement(name = "Question02")
        public String getQuestion02() {
            return Question02;
        }

        public void setQuestion02(String Question02) {
            this.Question02 = Question02;
        }

        @XmlElement(name = "Answer02a")
        public String getAnswer02a() {
            return Answer02a;
        }

        public void setAnswer02a(String Answer02a) {
            this.Answer02a = Answer02a;
        }

        @XmlElement(name = "Answer02b")
        public String getAnswer02b() {
            return Answer02b;
        }

        public void setAnswer02b(String Answer02b) {
            this.Answer02b = Answer02b;
        }

        @XmlElement(name = "Answer02c")
        public String getAnswer02c() {
            return Answer02c;
        }

        public void setAnswer02c(String Answer02c) {
            this.Answer02c = Answer02c;
        }

        @XmlElement(name = "Answer02d")
        public String getAnswer02d() {
            return Answer02d;
        }

        public void setAnswer02d(String Answer02d) {
            this.Answer02d = Answer02d;
        }

        @XmlElement(name = "Answer02e")
        public String getAnswer02e() {
            return Answer02e;
        }

        public void setAnswer02e(String Answer02e) {
            this.Answer02e = Answer02e;
        }

        @XmlElement(name = "Answer02f")
        public String getAnswer02f() {
            return Answer02f;
        }

        public void setAnswer02f(String Answer02f) {
            this.Answer02f = Answer02f;
        }

    }

}
