/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.constant.DBIConstants;
import scb.com.vn.dbi.dto.BHBLAnswerDto;
import scb.com.vn.dbi.dto.BHBLCstContractCollectedByTKResp;
import scb.com.vn.dbi.dto.BHBLCstContractCollectedByTheResp;
import scb.com.vn.dbi.dto.BHBLGoiPhiHDResp;
import scb.com.vn.dbi.dto.BHBLHDAndGoiPhiResp;
import scb.com.vn.dbi.dto.BHBLHealthCareIns;
import scb.com.vn.dbi.dto.BHBLHealthCareInsRq;
import scb.com.vn.dbi.dto.BHBLMetadataRes;
import scb.com.vn.dbi.dto.BHBLPackageCostResp;
import scb.com.vn.dbi.dto.BHBLPackageCostResp.ItemCost;
import scb.com.vn.dbi.dto.BHBLPackageCostResp.PackageCost;
import scb.com.vn.dbi.dto.BHBLPaymentContractInfoRq;
import scb.com.vn.dbi.dto.BHBLQuestionResp;

/**
 *
 * @author loinv3
 */
public class BHBLInsurancePaymentDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(BHBLInsurancePaymentDAO.class);

    final String s_GoiPhiHD = "select HD.ID Id, HD.TENHD TenHD, HD.TEN_GOI_PHI_HD TenGoiPhiHD,  HD.DM_HD IdHD, HD.DM_GOI_PHI_HD  IdGoiPhiHD, HD.SOTIEN SoTien"
            + " from BHBL_GOI_HD HD";

    final String s_HDAndGoiPhi = "select HD.ID IdHD, HD.DM_HD DMHD, HD.DM_GOI_PHI_HD DMGoiPhi, PHI.TUTUOI TuoiTu, PHI.DENTUOI TuoiDen, PHI.PHI SoTien, PHI.GOI_HD IdGoiPhi"
            + " from BHBL_GOI_HD hd, BHBL_GOI_PHI phi"
            + " where PHI.GOI_HD = HD.ID";

    final String Proc_GET_CONTRACTBYACCOUNT = "BEGIN PKG_BHBL_INSURANCE.GET_CONTRACTBYACCOUNT(?); END;";
    final String Proc_GET_CONTRACTBYCARD = "BEGIN PKG_BHBL_INSURANCE.GET_CONTRACTBYCARD(?); END;";

    /**
     *
     * @param helthCare
     * @param ques
     * @return
     * @throws Exception
     */
    public long insertHealthInsBHBL(BHBLHealthCareIns helthCare, BHBLHealthCareInsRq.Questions ques) throws Exception {

        try {
            getSession().save(helthCare);
            long id = helthCare.getId();

            return id;
        } catch (Exception ex) {
            LOGGER.error("Loi them vao bang BHBL_CUSTOMER_CONTRACT " + ex);
            return -1;
        }
    }

    final String ins_HealthInsBHBL = "insert into BHBL_CUSTOMER_CONTRACT(FULLNAME_1, BIRTHDAY_1, IDENCARD_1, ADDRESS_1, EMAIL_1, PHONE_1, FULLNAME_2, BIRTHDAY_2, IDENCARD_2, ADDRESS_2, EMAIL_2, PHONE_2, "
            + "RELATION_BUYER, EFFDATE, INSURANCE_TYPE, CREATED_DATE, UPDATED_DATE, REF_FCUBS, REF_NUM, STATUS, PACKAGE_INS, RELATION_INS_BUYER, FULLNAME3, BIRTHDAY_3, INDENCARD_3, ADDRESS_3, "
            + "EMAIL_3, PHONE_3, CHANNEL_REG, IDPARTNER, PAYMENT_METHOD, OUTPATIENT_AMOUNT, ACCIDENT_AMOUNT, HUMAN_LIFE_AMOUNT, DENTAL_AMOUNT, MATERNITY_AMOUNT, TOTAL_COST, INPATIENT_AMOUNT, "
            + "PACKAGE_EXTRA_COST, INS_DURATION, DATE_MAKER, DATE_CHECKER, TRANSDATE, PAYDATE_FCUBS, IDUSER_MAKER, IDUSER_CHECKER, FROM_ACCOUNT, TO_ACCOUNT, PARENT_CONTRACT_TYPE, "
            + "ID_CONTRACT_FOLLOW, ID_CONTRACT_PARTNER, AGE_REGISTER, AGE_PEOPLE_RELATIONHIP, GENDER, CAREER, BUYER_REGISTER, P_INPATIENT_AMOUNT, P_OUTPATIENT_AMOUNT, P_ACCIDENT_AMOUNT, "
            + "P_HUMAN_LIFE_AMOUNT, P_DENTAL_AMOUNT, P_MATERNITY_AMOUNT, P_EFFDATE, P_INS_DURATION, PAYMENT_CODE, P_GENDER, P_PACKAGE_INS) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public long insertHealthInsBHBL01(BHBLHealthCareIns helthCare) throws Exception {

        PreparedStatement preStatement = null;
        long id = -1;

        try {
            String generatedColumns[] = {"ID"};
            preStatement = connection.prepareStatement(ins_HealthInsBHBL, generatedColumns);

            preStatement.setString(1, helthCare.getFullName01());
            preStatement.setString(2, helthCare.getBirthDay01());
            preStatement.setString(3, helthCare.getIndenCard01());
            preStatement.setString(4, helthCare.getAddress01());
            preStatement.setString(5, helthCare.getEmail01());
            preStatement.setString(6, helthCare.getPhone01());
            preStatement.setString(7, helthCare.getFullName02());
            preStatement.setString(8, helthCare.getBirthDay02());
            preStatement.setString(9, helthCare.getIndenCard02());
            preStatement.setString(10, helthCare.getAddress02());
            preStatement.setString(11, helthCare.getEmail02());
            preStatement.setString(12, helthCare.getPhone02());
            preStatement.setString(13, helthCare.getRelationBuyer());
            preStatement.setString(14, helthCare.getEffDate());
            preStatement.setString(15, helthCare.getInsType());
            preStatement.setTimestamp(16, helthCare.getCreatedDate() != null ? new java.sql.Timestamp(helthCare.getCreatedDate().getTime()) : null);
            preStatement.setTimestamp(17, helthCare.getUpdatedDate() != null ? new java.sql.Timestamp(helthCare.getUpdatedDate().getTime()) : null);
            preStatement.setString(18, helthCare.getRefFcubs());
            preStatement.setString(19, helthCare.getRefNum());
            preStatement.setString(20, helthCare.getStatus());
            preStatement.setInt(21, helthCare.getPackageIns());
            preStatement.setString(22, helthCare.getRelationInsBuyer());
            preStatement.setString(23, helthCare.getFullName03());
            preStatement.setString(24, helthCare.getBirthDay03());
            preStatement.setString(25, helthCare.getIndenCard03());
            preStatement.setString(26, helthCare.getAddress03());
            preStatement.setString(27, helthCare.getEmail03());
            preStatement.setString(28, helthCare.getPhone03());
            preStatement.setString(29, helthCare.getChannelReg());
            preStatement.setString(30, helthCare.getIdPartner());
            preStatement.setString(31, helthCare.getPaymentMethod());
            preStatement.setBigDecimal(32, helthCare.getOutpatientAmount());
            preStatement.setBigDecimal(33, helthCare.getAccidentAmount());
            preStatement.setBigDecimal(34, helthCare.getHumanLifeAmount());
            preStatement.setBigDecimal(35, helthCare.getDentalAmount());
            preStatement.setBigDecimal(36, helthCare.getMaternityAmount());
            preStatement.setBigDecimal(37, helthCare.getTotalCost());
            preStatement.setBigDecimal(38, helthCare.getInpatientAmount());
            preStatement.setBigDecimal(39, helthCare.getPackageExtraCost());
            preStatement.setFloat(40, helthCare.getInsDuration());
            preStatement.setTimestamp(41, helthCare.getDateMaker() != null ? new java.sql.Timestamp(helthCare.getDateMaker().getTime()) : null);
            preStatement.setTimestamp(42, helthCare.getDateChecker() != null ? new java.sql.Timestamp(helthCare.getDateChecker().getTime()) : null);
            preStatement.setTimestamp(43, helthCare.getTransDate() != null ? new java.sql.Timestamp(helthCare.getTransDate().getTime()) : null);
            preStatement.setTimestamp(44, helthCare.getPayDateFCUBS() != null ? new java.sql.Timestamp(helthCare.getPayDateFCUBS().getTime()) : null);
            preStatement.setString(45, helthCare.getIdUserMaker());
            preStatement.setString(46, helthCare.getIdUserChecker());
            preStatement.setString(47, helthCare.getFromAccount());
            preStatement.setString(48, helthCare.getToAccount());
            preStatement.setString(49, helthCare.getParentContractType());
            preStatement.setString(50, helthCare.getIdContractFollow());
            preStatement.setString(51, helthCare.getIdContractPartner());
            preStatement.setInt(52, helthCare.getAgeRegister());
            preStatement.setInt(53, helthCare.getAgePeopleRelationShip());
            preStatement.setString(54, helthCare.getGender());
            preStatement.setString(55, helthCare.getCareer());
            preStatement.setString(56, helthCare.getBuyerRegister());
            preStatement.setBigDecimal(57, helthCare.getPInpatientAmount());
            preStatement.setBigDecimal(58, helthCare.getPOutPatientAmount());
            preStatement.setBigDecimal(59, helthCare.getPAccidentAmount());
            preStatement.setBigDecimal(60, helthCare.getPHumanLifeAmount());
            preStatement.setBigDecimal(61, helthCare.getPDentalAmount());
            preStatement.setBigDecimal(62, helthCare.getPMaternityAmount());
            preStatement.setString(63, helthCare.getPEffDate());
            preStatement.setInt(64, helthCare.getPInsDuration());
            preStatement.setInt(65, helthCare.getPaymentCode());
            preStatement.setString(66, helthCare.getPGender());
            preStatement.setInt(67, helthCare.getPPackageIns());

            preStatement.execute();
            connection.commit();

            id = helthCare.getId();
            ResultSet rs = preStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }

            return id;
        } catch (SQLException ex) {
            LOGGER.error("Loi them vao BHBL_Answer_Rs" + ex);
            return id;
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String ins_AnswersRs = "insert into BHBL_ANSWER_RS(IDQUESTION,IDANSWER,ANSWER_CONTENT, ID_CUSTOMER_CONTRACT)"
            + "VALUES(?,?,?,?)";

    public boolean insertAnswerRs(BHBLHealthCareInsRq.Questions ques, Long IdContract) throws SQLException {
        PreparedStatement preStatement = null;
        boolean isInsert = false;
        try {
            if (ques.getQuestion() != null) {
                for (BHBLHealthCareInsRq.question qu : ques.getQuestion()) {
                    if (qu.getAnswer() != null) {
                        for (BHBLHealthCareInsRq.Answer ans : qu.getAnswer()) {
                            preStatement = connection.prepareCall(ins_AnswersRs);
                            preStatement.setInt(1, qu.getId());
                            preStatement.setInt(2, ans.getId());
                            preStatement.setString(3, ans.getContent());
                            preStatement.setLong(4, IdContract);
                            isInsert = preStatement.execute();

                            connection.commit();
                            preStatement.close();
                        }
                    }
                }
            }

            return isInsert;
        } catch (SQLException ex) {
            LOGGER.error("Loi them vao BHBL_Answer_Rs" + ex);
            return isInsert;
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }

        }
    }

    final String s_PackageCostBHBLByAge01 = "SELECT HD.DM_HD IdContract, HD.TENHD ContractName "
            + "FROM BHBL_GOI_HD hd LEFT JOIN BHBL_GOI_PHI phi on hd.id = phi.goi_hd "
            + "LEFT JOIN BHBL_DM_METADATA cc on  HD.DM_GOI_PHI_HD = cc.id "
            + "AND phi.tutuoi <= ? and phi.dentuoi >= ? "
            + "GROUP BY  HD.DM_HD , HD.TENHD "
            + "ORDER BY HD.DM_HD";

    private String s_PackageCostBHBLByAge02 = "SELECT PHI.TUOI Age, HD.DM_HD IdContract, HD.TENHD ContractName, PHI.TUTUOI TuoiTu, PHI.DENTUOI TuoiDen, CC.TEN PackageCostName, CC.TEN_PHU PackageCostFeeName, CC.TEN_EN PackageCostNameE, CC.TEN_PHU_EN PackageCostFeeNameE, HD.DM_GOI_PHI_HD IdPackageCost, PHI.PHI MoneyCost,  cc.LOAIGIATRI TypeValue, cc.HIENTHI IsEnable"
            + " FROM BHBL_GOI_HD hd LEFT JOIN  BHBL_GOI_PHI phi on hd.id = phi.goi_hd "
            + "LEFT JOIN BHBL_DM_METADATA cc on  HD.DM_GOI_PHI_HD = cc.id "
            + "WHERE phi.tutuoi <= ? and phi.dentuoi >= ? and HD.DM_HD = ? ";

    public List<BHBLPackageCostResp> getBLPackageCost(int tuoi, int gioiTinh, String lang) throws SQLException {

        List<BHBLPackageCostResp> rsl = new ArrayList<>();
        PreparedStatement preStatement01 = null;
        try {
            ResultSet rs01 = null;
            /*
            if (gioiTinh == 1) {
                s_PackageCostBHBLByAge02 += " and cc.HIENTHI = 1 ";
            }
             */
            s_PackageCostBHBLByAge02 += " ORDER BY cc.SORT_ORDER ";
            preStatement01 = connection.prepareStatement(s_PackageCostBHBLByAge01);
            preStatement01.setFloat(1, tuoi);
            preStatement01.setFloat(2, tuoi);

            rs01 = preStatement01.executeQuery();
            if (rs01 == null) {
                return rsl;
            }

            //PLATINUM, DIAMOND
            List<Long> packageForAge0 = new ArrayList<Long>(Arrays.asList(4L, 5L));

            while (rs01.next()) {

                long idContract = rs01.getLong("IdContract");
                if (tuoi == 0 && !packageForAge0.contains(idContract)) {
                    continue;
                }

                BHBLPackageCostResp packageCost = new BHBLPackageCostResp();
                packageCost.setAge("Tuoi Trong Khoang: " + tuoi);
                packageCost.setIdPackageContract(rs01.getLong("IdContract"));
                packageCost.setPackageContractName(rs01.getString("ContractName"));
                setPackageCostDtl(packageCost, idContract, tuoi, gioiTinh, lang);

                rsl.add(packageCost);
            }

            return rsl;

        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (preStatement01 != null) {
                preStatement01.close();
            }

        }
    }

    private BHBLPackageCostResp setPackageCostDtl(BHBLPackageCostResp packageCost, long idContract, int tuoi, int gioiTinh, String lang) throws SQLException {

        PreparedStatement preStatement02 = null;
        ResultSet rs02 = null;
        preStatement02 = connection.prepareStatement(s_PackageCostBHBLByAge02);
        preStatement02.setInt(1, tuoi);
        preStatement02.setInt(2, tuoi);
        preStatement02.setLong(3, idContract);
        rs02 = preStatement02.executeQuery();
        if (rs02 == null) {
            return packageCost;
        }

        while (rs02.next()) {
            PackageCost packageDtl = new PackageCost();
            long idPackageCost = rs02.getLong("IdPackageCost");

            if (idPackageCost == DBIConstants.BHBL_TAINAN) {
                List<ItemCost> itemCosts = new ArrayList<>();
                itemCosts = getItemCost("TAINAN");
                packageDtl.setItemCosts(itemCosts);
            }
            if (idPackageCost == DBIConstants.BHBL_SINHMANG) {
                List<ItemCost> itemCosts = new ArrayList<>();
                itemCosts = getItemCost("SINHMANG");
                packageDtl.setItemCosts(itemCosts);
            }

            packageDtl.setIdPackageCost(rs02.getLong("IdPackageCost"));
            //en: tieng anh; vn: tieng viet
            if (lang != null && "EN".equals(lang.toUpperCase())) {
                packageDtl.setPackageCostName(rs02.getString("PackageCostNameE"));
                packageDtl.setPackageCostFeeName(rs02.getString("PackageCostFeeNameE"));
            } else {
                packageDtl.setPackageCostName(rs02.getString("PackageCostName"));
                packageDtl.setPackageCostFeeName(rs02.getString("PackageCostFeeName"));
            }

            BigDecimal moneyCost = rs02.getBigDecimal("MoneyCost");
            packageDtl.setAge(rs02.getString("Age"));
            packageDtl.setMoneyCost(moneyCost);
            packageDtl.setTypeValue(rs02.getInt("TypeValue"));

            //0: an; 1 hien thi
            packageDtl.setIsEnable(1);

            if ((gioiTinh == 1 && rs02.getInt("IsEnable") == 0) || moneyCost.compareTo(new BigDecimal(BigInteger.ZERO)) == 0) {
                packageDtl.setIsEnable(0);
            }

            packageCost.getPackageCost().add(packageDtl);
        }

        if (preStatement02 != null) {
            preStatement02.close();
        }
        return packageCost;
    }

    /**
     * GetItemCost
     *
     * @param type
     * @return
     * @throws SQLException
     */
    private String s_GetItemCostsByType = "SELECT dd.id Id, dd.ten Ten, dd.loai_dm LoaiDM, dd.giatri Giatri "
            + "FROM BHBL_DM_METADATA dd "
            + "WHERE dd.loai_dm = ?";

    private List<ItemCost> getItemCost(String type) throws SQLException {

        PreparedStatement preStatement = null;
        ResultSet rs = null;
        List<ItemCost> rsl = new ArrayList<>();

        preStatement = connection.prepareStatement(s_GetItemCostsByType);
        preStatement.setString(1, type);

        rs = preStatement.executeQuery();
        if (rs == null) {
            return rsl;
        }

        while (rs.next()) {
            ItemCost packageCost = new ItemCost();
            packageCost.setId(rs.getLong("Id"));
            packageCost.setGiaTri(rs.getString("Giatri"));
            rsl.add(packageCost);
        }

        if (preStatement != null) {
            preStatement.close();
        }
        return rsl;
    }

    private String s_GetCategoryByType = "SELECT dd.id Id, dd.IdValue idValue, dd.ten Ten, dd.loai_dm LoaiDM, dd.giatri Giatri, dd.TEN_PHU tenphu "
            + "FROM BHBL_DM_METADATA dd "
            + "WHERE 1=1";

    public List<BHBLMetadataRes> getBLCategories(String type) throws SQLException {

        PreparedStatement preStatement = null;
        try {
            ResultSet rs = null;
            List<BHBLMetadataRes> rsl = new ArrayList<>();

            if (type != null && !type.isEmpty()) {
                s_GetCategoryByType += " and dd.loai_dm = ?";
                preStatement = connection.prepareStatement(s_GetCategoryByType);
                preStatement.setString(1, type);
            } else {
                preStatement = connection.prepareStatement(s_GetCategoryByType);
            }

            rs = preStatement.executeQuery();
            if (rs == null) {
                return rsl;
            }

            while (rs.next()) {
                BHBLMetadataRes packageCost = new BHBLMetadataRes();
                packageCost.setId(rs.getLong("Id"));
                packageCost.setIdValue(rs.getString("IdValue") != null ? Long.parseLong(rs.getString("IdValue")) : -1);
                packageCost.setTen(rs.getString("Ten"));
                packageCost.setLoaiDM(rs.getString("LoaiDM"));
                packageCost.setGiaTri(rs.getBigDecimal("Giatri"));
                packageCost.setTenPhu(rs.getString("tenphu"));

                rsl.add(packageCost);
            }

            return rsl;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }
        }
    }

    final String s_AllQuestion = "SELECT QUE.Id ID, QUE.QUESTION_TEXT NoiDung, QUE.Answer Answer, QUE.Type Type, QUE.CHECKBOX CheckBox, QUE.TYPE_QUESTION TypeQuestion, QUE.QUESTION_TEXT_EN NoiDungE  FROM BHBL_QUESTION QUE";

    public List<BHBLQuestionResp> getBLAllQuestions(String lang) throws SQLException {

        PreparedStatement preStatement = null;
        try {
            ResultSet rs = null;
            List<BHBLQuestionResp> rsl = new ArrayList<>();
            preStatement = connection.prepareStatement(s_AllQuestion);

            rs = preStatement.executeQuery();
            if (rs == null) {
                return rsl;
            }

            while (rs.next()) {
                BHBLQuestionResp questions = new BHBLQuestionResp();
                questions.setIdQuestion(rs.getLong("Id"));
                //en: tieng anh; vn: tieng viet
                if (lang != null && "EN".equals(lang.toUpperCase())) {
                    questions.setNoiDung(rs.getString("NoiDungE"));

                } else {
                    questions.setNoiDung(rs.getString("NoiDung"));
                }
                questions.setType(rs.getString("Type"));
                questions.setCheckBox(rs.getInt("CheckBox"));
                questions.setTypeQuestion(rs.getInt("TypeQuestion"));

                //lay danh sanh cau tra loi
                String anwsers = rs.getString("Answer");
                if (anwsers != null && !anwsers.isEmpty()) {
                    List<BHBLAnswerDto> answers = new ArrayList<>();
                    answers = getBLAnwserByQuestion(rs.getLong("Id"), lang);
                    questions.setAnswers(answers);
                }
                rsl.add(questions);
            }

            return rsl;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }
        }
    }

    final String s_AnswersByQuesion = "SELECT dd.ID Id, DD.ANSWER_TEXT NoiDung, DD.ANSWER_TEXT_EN NoiDungE, DD.ANSWER_NOTE GhiChu, DD.ANSWER_NOTE_EN GhiChuE, DD.ANSWER_TYPE Type FROM bhbl_answer dd WHERE dd.idquestion = ?";

    public List<BHBLAnswerDto> getBLAnwserByQuestion(Long questionId, String lang) throws SQLException {

        PreparedStatement preStatement = null;
        try {
            ResultSet rs = null;
            List<BHBLAnswerDto> rsl = new ArrayList<>();
            preStatement = connection.prepareStatement(s_AnswersByQuesion);
            preStatement.setLong(1, questionId);

            rs = preStatement.executeQuery();
            if (rs == null) {
                return rsl;
            }

            while (rs.next()) {
                BHBLAnswerDto answer = new BHBLAnswerDto();
                answer.setIdAnswer(rs.getLong("Id"));
                //en: tieng anh; vn: tieng viet
                if (lang != null && "EN".equals(lang.toUpperCase())) {
                    answer.setNoiDung(rs.getString("NoiDungE"));
                    answer.setGhiChu(rs.getString("GhiChuE"));
                } else {
                    answer.setNoiDung(rs.getString("NoiDung"));
                    answer.setGhiChu(rs.getString("GhiChu"));
                }

                answer.setType(rs.getString("Type"));
                rsl.add(answer);
            }

            return rsl;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }
        }
    }

    final String s_updateBLPayment = "UPDATE BHBL_CUSTOMER_CONTRACT dd SET dd.PAYMENT_CODE = ?, DD.UPDATED_DATE = ? WHERE dd.ID_CONTRACT_PARTNER = ?";

    public String updatePaymentStatus(BHBLPaymentContractInfoRq payins) throws SQLException {

        PreparedStatement preStatement = null;
        try {
            Date nowDate = new Date();
            String idPartner = payins.getInvoiceCode();
            preStatement = connection.prepareStatement(s_updateBLPayment);
            preStatement.setInt(1, payins.getPaymentCode());
            preStatement.setTimestamp(2, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setString(3, payins.getInvoiceCode());

            if (preStatement.executeUpdate() <= 0) {
                return "0";
            }

            return idPartner;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            return "-1";
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }
        }
    }

    final String up_updateBLPayment = "UPDATE BHBL_CUSTOMER_CONTRACT DD "
            + "SET DD.ID_CONTRACT_PARTNER = ?, DD.PAYMENT_METHOD=?, DD.REF_FCUBS = ?, DD.STATUS = ?, DD.TRANSDATE = ?, DD.PAYDATE_FCUBS = ?, DD.REF_NUM = ? "
            + "WHERE dd.ID = ? ";

    public long updateHealthInsBHBL(BHBLHealthCareIns healthCareIns) throws SQLException {

        PreparedStatement preStatement = null;
        try {
            long idContract = healthCareIns.getId();
            preStatement = connection.prepareStatement(up_updateBLPayment);
            preStatement.setString(1, healthCareIns.getIdContractPartner());
            preStatement.setString(2, healthCareIns.getPaymentMethod());
            preStatement.setString(3, healthCareIns.getRefFcubs());
            preStatement.setString(4, healthCareIns.getStatus());
            preStatement.setTimestamp(5, healthCareIns.getTransDate() != null ? new java.sql.Timestamp(healthCareIns.getTransDate().getTime()) : null);
            preStatement.setTimestamp(6, healthCareIns.getPayDateFCUBS() != null ? new java.sql.Timestamp(healthCareIns.getPayDateFCUBS().getTime()) : null);
            preStatement.setString(7, healthCareIns.getRefNum());
            preStatement.setLong(8, idContract);

            if (preStatement.executeUpdate() <= 0) {
                return -1;
            }

            return idContract;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            return -1;
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }
        }
    }

    /**
     * CollectedCusConTractByTK
     *
     * @return
     * @throws SQLException
     */
    public List<BHBLCstContractCollectedByTKResp> collectedCustConTractByTK() throws SQLException {

        CallableStatement callStm = null;
        try {
            ResultSet rs = null;
            List<BHBLCstContractCollectedByTKResp> result = new ArrayList();
            callStm = connection.prepareCall(Proc_GET_CONTRACTBYACCOUNT);
            callStm.registerOutParameter(1, OracleTypes.CURSOR);
            callStm.executeQuery();

            rs = (ResultSet) callStm.getObject(1);
            if (rs == null) {
                return result;
            }

            while (rs.next()) {
                BHBLCstContractCollectedByTKResp collectedByTk = new BHBLCstContractCollectedByTKResp();
                collectedByTk.setEnterFromBL(rs.getString("EnterFromBL"));
                collectedByTk.setContractBL(rs.getString("ContractBL"));
                collectedByTk.setContractSCB(rs.getString("ContractSCB"));
                collectedByTk.setTotalCost(rs.getBigDecimal("TotalCost"));
                collectedByTk.setPaymentDate(rs.getTimestamp("PaymentDate"));
                collectedByTk.setStatus(rs.getString("Status"));
                collectedByTk.setFromAccount(rs.getString("FromAccount"));
                collectedByTk.setHoTen1(rs.getString("HoTen1"));
                collectedByTk.setHoTen2(rs.getString("HoTen2"));
                collectedByTk.setGioiTinh(rs.getString("GioiTinh"));
                collectedByTk.setSdt(rs.getString("sdt"));
                collectedByTk.setNgaySinh(rs.getString("NgaySinh"));
                collectedByTk.setCMND(rs.getString("cmnd"));
                collectedByTk.setEmail(rs.getString("email"));
                collectedByTk.setGoiSP(rs.getString("GoiSP"));
                collectedByTk.setNgoaiTru(rs.getBigDecimal("NgoaiTru"));
                collectedByTk.setNhaKhoa(rs.getBigDecimal("NhaKhoa"));
                collectedByTk.setTaiNan(rs.getBigDecimal("TaiNan"));
                collectedByTk.setSinhMang(rs.getBigDecimal("SinhMang"));
                collectedByTk.setThaiSan(rs.getBigDecimal("ThaiSan"));
                collectedByTk.setBHChinh(rs.getBigDecimal("BHChinh"));
                collectedByTk.setTongPhi(rs.getBigDecimal("TongPhi"));
                collectedByTk.setNgayHieuLuc(rs.getTimestamp("NgayHieuLuc"));
                collectedByTk.setNgayKetThuc(rs.getTimestamp("NgayKetThuc"));
                collectedByTk.setNgayTao(rs.getTimestamp("NgayTao"));
                collectedByTk.setHTThanhToan(rs.getString("HTThanhToan"));
                collectedByTk.setNgayTT(rs.getTimestamp("NgayTT"));
                collectedByTk.setMaGDSCB(rs.getString("MaGDSCB"));
                collectedByTk.setIdGoiSP(rs.getLong("IdGoiSP"));
                collectedByTk.setTuoi(rs.getInt("Tuoi"));
                result.add(collectedByTk);
            }

            return result;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (callStm != null) {
                callStm.close();
            }
        }
    }

    /**
     * CollectedCusConTractByThe
     *
     * @return
     * @throws SQLException
     */
    public List<BHBLCstContractCollectedByTheResp> collectedCustConTractByThe() throws SQLException {

        CallableStatement callStm = null;
        try {
            ResultSet rs = null;

            List<BHBLCstContractCollectedByTheResp> result = new ArrayList();
            callStm = connection.prepareCall(Proc_GET_CONTRACTBYCARD);
            callStm.registerOutParameter(1, OracleTypes.CURSOR);
            callStm.executeQuery();

            rs = (ResultSet) callStm.getObject(1);
            if (rs == null) {
                return result;
            }

            while (rs.next()) {
                BHBLCstContractCollectedByTheResp collectedByThe = new BHBLCstContractCollectedByTheResp();
                collectedByThe.setEnterFromBL(rs.getString("EnterFromBL"));
                collectedByThe.setContractBL(rs.getString("ContractBL"));
                collectedByThe.setContractSCB(rs.getString("ContractSCB"));
                collectedByThe.setTotalCost(rs.getBigDecimal("TotalCost"));
                collectedByThe.setPaymentDate(rs.getTimestamp("PaymentDate"));
                collectedByThe.setStatus(rs.getString("Status"));
                collectedByThe.setPaymentCode(rs.getInt("PaymentCode"));
                collectedByThe.setHoTen1(rs.getString("HoTen1"));
                collectedByThe.setHoTen2(rs.getString("HoTen2"));
                collectedByThe.setGioiTinh(rs.getString("GioiTinh"));
                collectedByThe.setSdt(rs.getString("sdt"));
                collectedByThe.setNgaySinh(rs.getString("NgaySinh"));
                collectedByThe.setCMND(rs.getString("cmnd"));
                collectedByThe.setEmail(rs.getString("email"));
                collectedByThe.setGoiSP(rs.getString("GoiSP"));
                collectedByThe.setNgoaiTru(rs.getBigDecimal("NgoaiTru"));
                collectedByThe.setNhaKhoa(rs.getBigDecimal("NhaKhoa"));
                collectedByThe.setTaiNan(rs.getBigDecimal("TaiNan"));
                collectedByThe.setSinhMang(rs.getBigDecimal("SinhMang"));
                collectedByThe.setThaiSan(rs.getBigDecimal("ThaiSan"));
                collectedByThe.setBHChinh(rs.getBigDecimal("BHChinh"));
                collectedByThe.setTongPhi(rs.getBigDecimal("TongPhi"));
                collectedByThe.setNgayHieuLuc(rs.getTimestamp("NgayHieuLuc"));
                collectedByThe.setNgayKetThuc(rs.getTimestamp("NgayKetThuc"));
                collectedByThe.setNgayTao(rs.getTimestamp("NgayTao"));
                collectedByThe.setHTThanhToan(rs.getString("HTThanhToan"));
                collectedByThe.setNgayTT(rs.getTimestamp("NgayTT"));
                collectedByThe.setMaGDSCB(rs.getString("MaGDSCB"));
                collectedByThe.setIdGoiSP(rs.getLong("IdGoiSP"));
                collectedByThe.setTuoi(rs.getInt("Tuoi"));
                result.add(collectedByThe);
            }
            return result;

        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (callStm != null) {
                callStm.close();
            }
        }
    }

    /**
     * GetGoiPhiHD
     *
     * @return
     * @throws Exception
     */
    public List<BHBLGoiPhiHDResp> getGoiPhiHD() throws Exception {

        ResultSet rs = null;
        PreparedStatement preStatement = null;

        try {
            List<BHBLGoiPhiHDResp> result = new ArrayList();
            preStatement = connection.prepareStatement(s_GoiPhiHD);
            rs = preStatement.executeQuery();

            if (rs == null) {
                return result;
            }

            while (rs.next()) {
                BHBLGoiPhiHDResp goiPhiHD = new BHBLGoiPhiHDResp();
                goiPhiHD.setId(rs.getLong("Id"));
                goiPhiHD.setIdHD(rs.getString("IdHD") != null ? Integer.parseInt(rs.getString("IdHD")) : 0);
                goiPhiHD.setTenHD(rs.getString("TenHD"));
                goiPhiHD.setIdGoiPhiHD(rs.getString("IdGoiPhiHD") != null ? Integer.parseInt(rs.getString("IdGoiPhiHD")) : 0);
                goiPhiHD.setTenGoiPhiHD(rs.getString("TenGoiPhiHD"));
                BigDecimal moneyCost = rs.getBigDecimal("SoTien");
                goiPhiHD.setSoTien(moneyCost);
                result.add(goiPhiHD);
            }

            return result;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }
        }
    }

    /**
     * GetAllHDAndGoiPhi
     *
     * @return
     * @throws Exception
     */
    public List<BHBLHDAndGoiPhiResp> getAllHDAndGoiPhi() throws Exception {

        ResultSet rs = null;
        PreparedStatement preStatement = null;

        try {
            List<BHBLHDAndGoiPhiResp> result = new ArrayList();
            preStatement = connection.prepareStatement(s_HDAndGoiPhi);
            rs = preStatement.executeQuery();

            if (rs == null) {
                return result;
            }

            while (rs.next()) {
                BHBLHDAndGoiPhiResp hdAndGoiPhi = new BHBLHDAndGoiPhiResp();
                hdAndGoiPhi.setIdHD(rs.getLong("IdHD"));
                hdAndGoiPhi.setDMHD(rs.getLong("DMHD"));
                hdAndGoiPhi.setIdGoiPhi(rs.getLong("IdGoiPhi"));
                hdAndGoiPhi.setDMGoiPhi(rs.getLong("DMGoiPhi"));
                hdAndGoiPhi.setTuoiTu(rs.getInt("TuoiTu"));
                hdAndGoiPhi.setDenTuoi(rs.getInt("TuoiDen"));
                BigDecimal moneyCost = rs.getBigDecimal("SoTien");
                hdAndGoiPhi.setSoTien(moneyCost);
                result.add(hdAndGoiPhi);
            }

            return result;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }
        }

    }

}
