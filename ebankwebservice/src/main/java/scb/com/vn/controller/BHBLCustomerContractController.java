/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import scb.com.vn.constant.CommonConstant;
import scb.com.vn.dbi.dto.BHBLHealthCareIns;
import scb.com.vn.dbi.dto.BHBLMetadataRes;
import scb.com.vn.dbi.dto.BHBLQuestionResp;
import scb.com.vn.model.bhbl.BHBLGetHealthCareContractRq;
import scb.com.vn.model.bhbl.BHBLMetadataResp;
import scb.com.vn.model.bhbl.BHBLMetadataRq;
import scb.com.vn.model.bhbl.BHBLPackageCostsResp;
import scb.com.vn.model.bhbl.BHBLPackageCostRq;
import scb.com.vn.model.bhbl.BHBLPaymentStatusResp;
import scb.com.vn.model.bhbl.BHBLQuestionRq;
import scb.com.vn.model.bhbl.BHBLQuestionXmlResp;
import scb.com.vn.model.bhbl.BHBLResponseCodeDto;
import scb.com.vn.model.bhbl.BHBLSingnaturePaymentDto;
import scb.com.vn.dbi.dto.BHBLHealthCareInsRq;
import scb.com.vn.dbi.dto.BHBLPackageCostResp;
import scb.com.vn.dbi.dto.BHBLPaymentContractInfoRq;
import scb.com.vn.dbi.dto.VwCustAccountNew;
import scb.com.vn.model.FcubsTransferRp;
import scb.com.vn.model.NotificationFirbase;
import scb.com.vn.model.NotificationFirbaseResp;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.BHBLSignature;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;
import scb.com.vn.model.bhbl.BHBLHealthCareContractResp;
import scb.com.vn.model.bhbl.BHBLPaymentHealthCareResp;
import scb.com.vn.model.bhbl.BHBLPaymentHealthCareRq;
import scb.com.vn.model.bhbl.BHBLTokens;
import scb.com.vn.model.bhbl.ContractDataDtoResp;
import scb.com.vn.model.bhbl.EnqueueData;
import scb.com.vn.model.bhbl.EnqueueDataRq;
import scb.com.vn.model.card.BankCardDtlRq;
import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.MessageMB;
import scb.com.vn.ws.bhbl.BHBLHealCareContractService;

/**
 *
 * @author loinv3
 */
public class BHBLCustomerContractController {

    private final String insProductIB = Configuration.getProperty("insurance.fcubs.product");
    private final String insProductMB = Configuration.getProperty("insurance.fcubs.product.MB");
    private final String useridfcubsIB = Configuration.getProperty("fcubs.userid");
    private final String useridfcubsMB = Configuration.getProperty("fcubs.userid.mobile");
    private final String numberAccoutBL = Configuration.getProperty("bhbl.number.account");
    final String proxyIP = Configuration.getProperty("bhbl.proxy.server.ip");
    final String proxyPort = Configuration.getProperty("bhbl.proxy.server.port");
    final static Logger LOGGER = Logger.getLogger(BHBLCustomerContractController.class);

    /**
     * CreateHealthCareIns
     *
     * @param entity
     * @param insReq
     * @return
     * @throws Exception
     */
    private BHBLHealthCareIns createHealthCareIns(BHBLHealthCareIns entity, BHBLHealthCareInsRq insReq) throws Exception {
        entity.setInsType(insReq.getInsType());
        entity.setFullName01(insReq.getFullName01());
        entity.setAddress01(insReq.getAddress01());
        entity.setEmail01(insReq.getEmail01());
        entity.setPhone01(insReq.getPhone01());
        entity.setIndenCard01(insReq.getIndenCard01());
        entity.setBirthDay01(insReq.getBirthDay01());
        entity.setRelationBuyer(insReq.getRelationBuyer());
        entity.setFullName02(insReq.getFullName02());
        entity.setAddress02(insReq.getAddress02());
        entity.setEmail02(insReq.getEmail02());
        entity.setBirthDay02(insReq.getBirthDay02());
        entity.setIndenCard02(insReq.getIndenCard02());
        entity.setPhone02(insReq.getPhone02());
        entity.setEffDate(insReq.getEffDate());
        entity.setInsDuration(insReq.getInsDuration());
        entity.setPackageIns(insReq.getPackageIns());
        if (CommonConstant.PAYMENT_METHOD_CARD.equals(insReq.getPaymentMethod()) || "THE".equals(insReq.getPaymentMethod())) {
            entity.setPaymentCode(-1);
            entity.setPaymentMethod("THE");
        } else {
            entity.setPaymentMethod("TAIKHOAN");
        }
        BigDecimal totalCost = new BigDecimal(BigInteger.ZERO);
        BigDecimal totalExtraCost = new BigDecimal(BigInteger.ZERO);
        if (insReq.getPackageCost01() != null) {
            entity.setInpatientAmount(insReq.getPackageCost01().getInpatient());
            entity.setOutpatientAmount(insReq.getPackageCost01().getOutpatient());
            entity.setAccidentAmount(insReq.getPackageCost01().getAccident());
            entity.setHumanLifeAmount(insReq.getPackageCost01().getHumanLife());
            entity.setDentalAmount(insReq.getPackageCost01().getDental());
            entity.setMaternityAmount(insReq.getPackageCost01().getMaternity());
            totalCost = insReq.getPackageCost01().getInpatient().add(insReq.getPackageCost01().getOutpatient()).add(insReq.getPackageCost01().getAccident())
                    .add(insReq.getPackageCost01().getHumanLife()).add(insReq.getPackageCost01().getDental()).add(insReq.getPackageCost01().getMaternity());
            totalExtraCost = insReq.getPackageCost01().getOutpatient().add(insReq.getPackageCost01().getAccident())
                    .add(insReq.getPackageCost01().getHumanLife()).add(insReq.getPackageCost01().getDental()).add(insReq.getPackageCost01().getMaternity());
        }

        if (insReq.getPackageCost02() != null) {
            entity.setPPackageIns(insReq.getPPackageIns());
            entity.setPInpatientAmount(insReq.getPackageCost02().getInpatient());
            entity.setPOutPatientAmount(insReq.getPackageCost02().getOutpatient());
            entity.setPAccidentAmount(insReq.getPackageCost02().getAccident());
            entity.setPHumanLifeAmount(insReq.getPackageCost02().getHumanLife());
            entity.setPDentalAmount(insReq.getPackageCost02().getDental());
            entity.setPMaternityAmount(insReq.getPackageCost02().getMaternity());
            totalCost = totalCost.add(insReq.getPackageCost02().getInpatient()).add(insReq.getPackageCost02().getOutpatient()).add(insReq.getPackageCost02().getAccident())
                    .add(insReq.getPackageCost02().getHumanLife()).add(insReq.getPackageCost02().getDental()).add(insReq.getPackageCost02().getMaternity());
            totalExtraCost = totalExtraCost.add(insReq.getPackageCost02().getOutpatient()).add(insReq.getPackageCost02().getAccident())
                    .add(insReq.getPackageCost02().getHumanLife()).add(insReq.getPackageCost02().getDental()).add(insReq.getPackageCost02().getMaternity());
        }
        //entity.setTotalCost(insReq.getTotalCost());
        entity.setTotalCost(totalCost);
        //entity.setPackageExtraCost(insReq.getPackageExtraCost());
        entity.setPackageExtraCost(totalExtraCost);

        entity.setPEffDate(insReq.getPEffDate());
        entity.setPInsDuration(insReq.getPInsDuration());
        entity.setPGender(insReq.getPGender());
        entity.setRelationInsBuyer(insReq.getRelationInsBuyer());
        entity.setFullName03(insReq.getFullName03());
        entity.setBirthDay03(insReq.getBirthDay03());
        entity.setIndenCard03(insReq.getIndenCard03());
        entity.setPhone03(insReq.getPhone03());
        entity.setAddress03(insReq.getAddress03());
        entity.setIdPartner(insReq.getIdPartner());
        entity.setChannelReg(insReq.getChannelReg());
        entity.setPaymentMethod(insReq.getPaymentMethod());
        entity.setFromAccount(insReq.getFromAccount());
        entity.setToAccount(numberAccoutBL);//tk BHBL
        entity.setBuyerRegister(insReq.getBuyerRegister());
        entity.setIdContractFollow(insReq.getIdContractFollow());
        entity.setAgeRegister(insReq.getAgeRegister());
        entity.setAgePeopleRelationShip(insReq.getAgePeopleRelationShip());
        entity.setGender(insReq.getGender());
        entity.setCareer(insReq.getCareer());
        entity.setParentContractType(insReq.getParentContractType());
        entity.setCreatedDate(new Date());
        entity.setUpdatedDate(new Date());
        entity.setIdUserMaker(insReq.getIdUserMaker());
        entity.setDateMaker(new Date());

        return entity;
    }

    /**
     * Map lai dto SCB vs dto BL
     *
     * @param id
     * @param entity
     * @param insReq
     * @return
     */
    private EnqueueDataRq createHealthCareDtoSendBL(BHBLHealthCareInsRq entity, EnqueueData insReq) {

        insReq.setFullname01(entity.getFullName01());
        insReq.setBirthday01(entity.getBirthDay01());
        insReq.setIdencard01(entity.getIndenCard01());
        insReq.setAddress01(entity.getAddress01());
        insReq.setPhone01(entity.getPhone01());
        insReq.setEmail01(entity.getEmail01());
        insReq.setFullname02(entity.getFullName02());
        insReq.setBirthday02(entity.getBirthDay02());
        insReq.setIdencard02(entity.getIndenCard02());
        insReq.setAddress02(entity.getAddress02());
        insReq.setPhone02(entity.getPhone02());
        insReq.setEmail02(entity.getEmail02());
        insReq.setNumberContractFollow(entity.getIdContractFollow());
        //0: Nu; 1: Nam
        /*
        if (entity.getGender() != null && "0".equals(entity.getGender())) {
            insReq.setGender("F");
        } else {
            insReq.setGender("M");
        }
         */

        insReq.setCareer(entity.getCareer());
        insReq.setRelation(entity.getRelationBuyer());
        insReq.setBasedate01(entity.getEffDate());

        //benefit 01
        EnqueueData.Benefits01 benefit01 = new EnqueueData.Benefits01();
        benefit01.setBenefit00(entity.getPackageCost01().getInpatient());
        benefit01.setBenefit01(entity.getPackageCost01().getOutpatient());
        benefit01.setBenefit02(entity.getPackageCost01().getAccident());
        benefit01.setBenefit03(entity.getPackageCost01().getDental());
        benefit01.setBenefit04(entity.getPackageCost01().getHumanLife());
        benefit01.setBenefit05(entity.getPackageCost01().getMaternity());
        insReq.setBenefits01(benefit01);
        insReq.setGender01(entity.getGender());
        insReq.setGender02(entity.getPGender());
        insReq.setPackage01(entity.getPackageIns());
        insReq.setPackage02(entity.getPPackageIns());

        //RelationInsBuyer
        if (!StringUtils.isEmpty(entity.getRelationInsBuyer()) && entity.getRelationInsBuyer() != null) {
            insReq.setFullname03(entity.getFullName03());
            insReq.setBirthday03(entity.getBirthDay03());
            insReq.setIdencard03(entity.getIndenCard03());
            insReq.setAddress03(entity.getAddress03());
            insReq.setPhone03(entity.getPhone03());
            insReq.setEmail03(entity.getEmail03());
            insReq.setBasedate02(entity.getPEffDate());
        }
        //benefit 02
        if (entity.getPackageCost02() != null) {
            EnqueueData.Benefits02 benefit02 = new EnqueueData.Benefits02();
            benefit02.setBenefit06(entity.getPackageCost02().getInpatient());
            benefit02.setBenefit07(entity.getPackageCost02().getOutpatient());
            benefit02.setBenefit08(entity.getPackageCost02().getAccident());
            benefit02.setBenefit09(entity.getPackageCost02().getDental());
            benefit02.setBenefit10(entity.getPackageCost02().getHumanLife());
            benefit02.setBenefit11(entity.getPackageCost02().getMaternity());
            insReq.setBenefit02(benefit02);
        }

        EnqueueData.Questions question = new EnqueueData.Questions();
        for (BHBLHealthCareInsRq.question ques : entity.getQuestions().getQuestion()) {
            switch (ques.getId()) {
                case 1:
                    question.setQuestion01(ques.getId());
                    break;
                case 2:
                    question.setQuestion02(ques.getId());
                    break;
                default:
                    break;
            }

            if (ques.getAnswer() != null) {
                for (BHBLHealthCareInsRq.Answer ans : ques.getAnswer()) {
                    switch (ans.getId()) {
                        case 1:
                            question.setAnswer01a(ans.getId());
                            break;
                        case 2:
                            question.setAnswer01b(ans.getId());
                            break;
                        case 3:
                            question.setAnswer02a(ans.getId());
                            break;
                        case 4:
                            question.setAnswer02b(ans.getId());
                            break;
                        case 5:
                            question.setAnswer02c(ans.getId());
                            break;
                        case 6:
                            question.setAnswer02d(ans.getId());
                            break;
                        case 7:
                            question.setAnswer02e(ans.getId());
                            break;
                        case 8:
                            question.setAnswer02f(ans.getId());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        insReq.setQuestions(question);
        EnqueueDataRq conTractRq = new EnqueueDataRq();
        conTractRq.setEnqueueData(insReq);

        return conTractRq;
    }

    /**
     * UpdateHealthCareIns
     *
     * @param healCareIns
     * @throws RemoteException
     */
    private void updateHealthCareIns(BHBLHealthCareIns healCareIns) throws RemoteException {

        //healCareIns.setDateChecker(new Date());
        healCareIns.setTransDate(new Date());
        healCareIns.setUpdatedDate(new Date());

        Helper.getDBI().updateHealthInsBHBL(healCareIns);
    }

    /**
     * GetBLHealthCareContract
     *
     * @param xml
     * @return
     * @throws java.lang.Exception
     */
    public String GetBLHealthCareContract(String xml) throws Exception {

        try {
            BHBLHealthCareContractResp rsp = new BHBLHealthCareContractResp();
            LOGGER.info(xml);

            BHBLGetHealthCareContractRq healCareRq = (BHBLGetHealthCareContractRq) Xml.unMarshaller(BHBLGetHealthCareContractRq.class, xml);
            BHBLHealCareContractService blService = new BHBLHealCareContractService();

            String urlEndpoint = Configuration.getProperty("bhbl.rest.url.address");
            BHBLTokens tokens = new BHBLTokens();
            tokens.setAccessToken(Configuration.getProperty("bhbl.rest.access_token"));
            tokens.setRefreshToken(Configuration.getProperty("bhbl.rest.refresh_token"));

            rsp = blService.getHealCareContractBL(urlEndpoint, healCareRq.getIdContract(), tokens);

            if (rsp == null) {
                BHBLHealthCareContractResp rspN = new BHBLHealthCareContractResp();
                rspN.setErrorCode(CommonConstant.RESPONSE_SUCCEED);
                rspN.setErrorMsg("khong co du lieu tu BL gui ve!");
                return Xml.Marshaller(rspN);
            }
            //kt resp BL tra ve
            if (!"200".equals(rsp.getErrorCode())) {
                rsp.setErrorCode(rsp.getErrorCode());
                rsp.setErrorMsg("Co loi tu api BL!");
                return Xml.Marshaller(rsp);
            }

            rsp.setErrorCode(CommonConstant.RESPONSE_SUCCEED);
            rsp.setErrorMsg("lay hop dong bao hiem thanh cong!");
            return Xml.Marshaller(rsp);

        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            BHBLHealthCareContractResp rsp = new BHBLHealthCareContractResp();
            rsp.setErrorCode(CommonConstant.RESPONSE_FAILED);
            rsp.setErrorMsg("co loi trong qua trinh xu ly!");

            return Xml.Marshaller(rsp);
        }
    }

    /**
     * CreateBLHealthCareContract
     *
     * @param xml
     * @return
     * @throws java.lang.Exception
     */
    public String CreateBLHealthCareContract(String xml) throws Exception {

        LOGGER.info(xml);
        BHBLHealthCareIns healCareIns = new BHBLHealthCareIns();
        BHBLResponseCodeDto resp = new BHBLResponseCodeDto();
        resp.setTxnIDSCB("-");
        resp.setTxnIdPartner("-");
        resp.setErrorCode(CommonConstant.RESPONSE_FAILED);
        BHBLHealthCareInsRq insReq = new BHBLHealthCareInsRq();

        try {
            insReq = (BHBLHealthCareInsRq) Xml.unMarshaller(BHBLHealthCareInsRq.class, xml);
        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            resp.setErrorCode(CommonConstant.BHBL_VALIDATION_FAILED);
            resp.setErrorMsg("Invalid request input!");
            return Xml.Marshaller(resp);
        }

        try {
            // create data insert db
            this.createHealthCareIns(healCareIns, insReq);
            BHBLHealthCareInsRq.Questions que = insReq.getQuestions();
            //validate input
            resp = CustomerContractValidation.HealthBLInsPaymentValidation(healCareIns, que);
            if (CommonConstant.BHBL_VALIDATION_FAILED.equals(resp.getErrorCode())) {
                return Xml.Marshaller(resp);
            }

            //kt so du tk
            if (CommonConstant.PAYMENT_METHOD_ACCOUNT.equals(insReq.getPaymentMethod()) || "TAIKHOAN".equals(insReq.getPaymentMethod())) {
                LOGGER.info("Begin getCustAccountFcdbByAccountNo: " + insReq.getFromAccount());
                VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(insReq.getFromAccount());
                Helper.writeLog(BHBLCustomerContractController.class, Level.INFO, "End: getCustAccountFcdbByAccountNo: " + insReq.getFromAccount() + " - So Du: " + (custacc != null ? custacc.getAcyAvlBal() : null));

                if (custacc == null) {
                    resp.setErrorCode("07");
                    resp.setErrorMsg("TK khong ton tai!");
                    return Xml.Marshaller(resp);
                }

                //Kiem tra neu la Thau chi thi khong kiem tra so du TK, cac TK khac deu ktra so du
                if (custacc.getAccountClass().length() > 3 && (!custacc.getAccountClass().substring(0, 3).equals("TCI"))) {
                    if (custacc.getAcyAvlBal().compareTo(healCareIns.getTotalCost()) == -1) {
                        resp.setErrorCode("68");
                        resp.setErrorMsg("TK khong du so du de thanh toan!");
                        return Xml.Marshaller(resp);
                    }
                }
                //KT Han muc KYC
                String resultCheckKYC = CommonUtils.checkOverKYC(insReq.getFromAccount(), CommonUtils.getBranchAccount(healCareIns.getFromAccount()), healCareIns.getTotalCost());
                if (!resultCheckKYC.equals("00")) {
                    resp.setErrorCode(resultCheckKYC);
                    resp.setErrorMsg(MessageMB.getMessageMBResult(MessageMB.MobileResultEnum.IBT_OVERKYC));
                    return Xml.Marshaller(resp);
                }
            }

            long healCareId = Helper.getDBI().insertHealthInsBHBL(healCareIns, que);
            resp.setTxnIDSCB(String.valueOf(healCareId));

            String result = "";
            String RefFCC = "";
            String idBaoLong = "-1";
            String statusBaoLong = "";

            if (healCareId == -1) {
                LOGGER.error("Them du lieu vao BHBLHealthCareInsPayment co loi khi goi qua dbi");
                resp.setErrorCode("03");
                resp.setErrorMsg("co loi khi goi qua dbi");
                return Xml.Marshaller(resp);
            }

            healCareIns.setId(healCareId);
            //hach toan qua core
            Fcubs fcc = new Fcubs();
            String userIdFcus;
            String productcode;

            if (insReq.getChannelReg().equals("MB")) {
                userIdFcus = useridfcubsMB;
                productcode = insProductMB;
            } else {
                userIdFcus = useridfcubsIB;
                productcode = insProductIB;
            }
            if (CommonConstant.PAYMENT_METHOD_CARD.equals(insReq.getPaymentMethod()) || "THE".equals(insReq.getPaymentMethod())) {
                healCareIns.setPaymentMethod("THE");
                LOGGER.info("Thanh Toan qua the ghi no/tin dung quoc te.");
                result = "RefFCC la empty khi thanh toan qua the - " + " IDContract BaoLong: ";

            } else {
                healCareIns.setPaymentMethod("TAIKHOAN");
                // cat tien qua core
                FcubsTransferRp rpCore = fcc.ObjectTransferFCUBSWithTimeOut(productcode, userIdFcus, CommonUtils.getBranchAccount(healCareIns.getFromAccount()), healCareIns.getFromAccount(), CommonUtils.getBranchAccount(healCareIns.getToAccount()),
                        healCareIns.getToAccount(), healCareIns.getTotalCost(), "TT PHI BAO HIEM BL. NGUOI DK: " + healCareIns.getFullName01() + " - CMND: " + healCareIns.getIndenCard01(), 30);

                RefFCC = rpCore.getRefcore();
                LOGGER.info("Hach toan FCC, Ref: " + RefFCC);
                //check RefFCC
                if (rpCore.getStatus().equals("-1")) {
                    LOGGER.error("Chuyen khoan FCC tra ve loi:" + rpCore.getErrcode());
                    healCareIns.setStatus(CommonConstant.BHBL_PAYMENT_EMPTY);
                    updateHealthCareIns(healCareIns);

                    resp.setErrorCode(CommonConstant.BHBL_PAYMENT_EMPTY);
                    resp.setErrorMsg("Chuyen khoan FCC tra ve loi:" + rpCore.getErrcode());
                    return Xml.Marshaller(resp);
                }
                if ("TIMEOUT".equals(RefFCC)) {
                    LOGGER.error("Chuyen khoan FCC Time out.");
                    healCareIns.setStatus(CommonConstant.BHBL_PAYMENT_TIMEOUT);
                    updateHealthCareIns(healCareIns);

                    resp.setErrorCode(CommonConstant.BHBL_PAYMENT_TIMEOUT);
                    resp.setErrorMsg("Chuyen khoan FCC Time out.");
                    return Xml.Marshaller(resp);
                }
                //update healcare ins
                healCareIns.setPayDateFCUBS(new Date());
                healCareIns.setRefFcubs(RefFCC);

                result = "RefFCC: ".concat(RefFCC).concat(" - IDContract Bao Long: ");
            }

            // Goi qua bao long
            ContractDataDtoResp respBL = new ContractDataDtoResp();
            try {
                //tao dto gui qua bao long
                EnqueueDataRq contractDataRq = createHealthCareDtoSendBL(insReq, new EnqueueData());

                GsonBuilder builder = new GsonBuilder();
                builder.serializeNulls();
                Gson gson = builder.create();
                String jsonRequestBL = gson.toJson(contractDataRq.getEnqueueData());

                contractDataRq.setEnqueueHash(String.valueOf(healCareId));
                BHBLHealCareContractService blService = new BHBLHealCareContractService();
                String urlEndpoint = Configuration.getProperty("bhbl.rest.url.address");
                BHBLTokens tokens = new BHBLTokens();
                tokens.setAccessToken(Configuration.getProperty("bhbl.rest.access_token"));
                tokens.setRefreshToken(Configuration.getProperty("bhbl.rest.refresh_token"));

                //tao signature va gui BL
                String privateKeySCB = Configuration.getProperty("scb.ebankservice.config.privatekey");
                String signatureSCB = BHBLSignature.requestSignature(jsonRequestBL, privateKeySCB);

                healCareIns.setStatus(CommonConstant.BHBL_TO_API_CALLED);//dat flag gia tri khi goi qua BL
                updateHealthCareIns(healCareIns);
                respBL = blService.createHealCareContractBL(urlEndpoint, contractDataRq, signatureSCB, tokens);
                idBaoLong = respBL.getEnqueueResult() != null ? respBL.getEnqueueResult().getInvoiceCode() : null;
                statusBaoLong = respBL.getEnqueueStatus().getEnqueueCode();
                LOGGER.info("Ma hop dong api bao long da tao: " + idBaoLong);
                //kt trang thai tra ve tu api BL
                if (!CommonConstant.RESPONSE_SUCCEED.equals(statusBaoLong)) {
                    resp.setErrorCode(CommonConstant.BHBL_CALL_API_BL_ERROR);
                    healCareIns.setStatus(CommonConstant.BHBL_CALL_API_BL_ERROR);
                    try {
                        if (!RefFCC.isEmpty()) {
                            //khong hoan tien
                            if (CommonConstant.BHBL_TIME_OUT.equals(statusBaoLong) || "notResponseCd".equals(statusBaoLong)) {
                                LOGGER.error("Co loi khi goi qua api BL - TimeOut ");
                                resp.setErrorCode(CommonConstant.BHBL_TIME_OUT);
                                healCareIns.setStatus(CommonConstant.BHBL_TIME_OUT);

                            } else { //hoan tien cho kh
                                LOGGER.error("Co loi khi goi qua api BL - Thuc hien hoan tien: " + RefFCC);
                                String revertStatus = fcc.revertTransferFCUBS(RefFCC, 40);
                                if (revertStatus != null && "0".equals(revertStatus)) {
                                    healCareIns.setRefNum(RefFCC);
                                } else {
                                    LOGGER.error("Hoan tien khong thanh cong: " + RefFCC);
                                    healCareIns.setStatus(CommonConstant.BHBL_HOAN_TIEN_ERROR);
                                }
                            }
                        }
                    } catch (Exception ex1) {
                        healCareIns.setStatus(CommonConstant.BHBL_HOAN_TIEN_ERROR);
                        LOGGER.error("Loi khi goi ham hoan tien: " + ex1.getMessage());
                    }
                    //update db
                    updateHealthCareIns(healCareIns);

                    resp.setErrorMsg(respBL.getEnqueueStatus().getEnqueueDesc());
                    return Xml.Marshaller(resp);
                }
                
                //Verify data from BHBL
                Boolean isVerify = BHBLSignature.VerifySignature(respBL.getEnqueueResult().getSigningData(), jsonRequestBL);
                if (!isVerify) {
                    String msgError = "Chu ky so thanh toan ben doi tac bhbl gui qua khong khop so voi ky so ben scb dung - Thuc hien hoan tien lai";
                    healCareIns.setStatus(CommonConstant.BHBL_VERIFY_DATA_FAILED);
                    resp.setErrorCode(CommonConstant.BHBL_VERIFY_DATA_FAILED);

                    updateHealthCareIns(healCareIns);

                    resp.setErrorMsg(msgError);
                    return Xml.Marshaller(resp);
                }

            } catch (Exception ex) {
                String msgError = "Co loi khi goi qua api BL";
                LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);

                healCareIns.setStatus(CommonConstant.BHBL_CALL_API_BL_ERROR);
                resp.setErrorCode(CommonConstant.BHBL_CALL_API_BL_ERROR);
                //Hoan lai tien
                try {
                    if (!RefFCC.isEmpty()) {
                        if (CommonConstant.BHBL_TIME_OUT.equals(statusBaoLong) || "notResponseCd".equals(statusBaoLong)) {
                            msgError = "Co loi khi goi qua api BL - TimeOut ";
                            LOGGER.error(msgError);
                            resp.setErrorCode(CommonConstant.BHBL_TIME_OUT);
                            healCareIns.setStatus(CommonConstant.BHBL_TIME_OUT);
                        } else {

                            if (!"-1".equals(idBaoLong) && !CommonConstant.RESPONSE_SUCCEED.equals(statusBaoLong)) {
                                LOGGER.error("Co loi khi goi qua api BL - Thuc hien hoan tien: " + RefFCC);
                                String revertStatus = fcc.revertTransferFCUBS(RefFCC, 40);
                                if (revertStatus != null && "0".equals(revertStatus)) {
                                    healCareIns.setRefNum(RefFCC);
                                } else {
                                    healCareIns.setStatus(CommonConstant.BHBL_HOAN_TIEN_ERROR);
                                }
                            }
                        }
                    }
                } catch (Exception ex1) {
                    healCareIns.setStatus(CommonConstant.BHBL_HOAN_TIEN_ERROR);
                    LOGGER.error("Loi khi goi ham hoan tien: " + ex1.getMessage());
                }

                //update db
                updateHealthCareIns(healCareIns);
                resp.setErrorMsg(msgError);
                return Xml.Marshaller(resp);
            }

            //resp.setTxnIDSCB(String.valueOf(RefFCC));
            //update DB
            healCareIns.setIdContractPartner(idBaoLong);
            healCareIns.setStatus(CommonConstant.RESPONSE_SUCCEED);
            updateHealthCareIns(healCareIns);

            result += idBaoLong;
            resp.setTxnIdPartner(idBaoLong);
            resp.setErrorCode(CommonConstant.RESPONSE_SUCCEED);
            resp.setErrorMsg(result);

            //Goi api qua cong thanh toan
            if ("01".equals(insReq.getPaymentMethod()) || "THE".equals(insReq.getPaymentMethod())) {
                setUrlOnepayBL01(resp, healCareIns.getTotalCost());
            } else {
                resp.setPaymenmtUrl("Url la empty khi thanh toan qua tk");
            }

            return Xml.Marshaller(resp);
        } catch (Exception ex) {
            resp.setErrorCode(CommonConstant.RESPONSE_FAILED);
            resp.setErrorMsg("co loi exception khi goi ham");
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            return Xml.Marshaller(resp);
        }
    }

    /**
     * SetUrlOnepayBL01
     *
     * @param resp
     * @param orderAmount
     * @return
     * @throws Exception
     */
    private String setUrlOnepayBL01(BHBLResponseCodeDto resp, BigDecimal orderAmount) throws Exception {
        try {
            //kt data gui qua cong thanh toan
            if (resp.getTxnIdPartner() == null || StringUtils.isEmpty(resp.getTxnIdPartner())) {
                resp.setPaymenmtUrl("");
                return Xml.Marshaller(resp);
            }

            BHBLPaymentHealthCareResp rsp = new BHBLPaymentHealthCareResp();
            BHBLHealCareContractService blService = new BHBLHealCareContractService();
            String urlEndpoint = Configuration.getProperty("bhbl.rest.url.address");

            BHBLTokens tokens = new BHBLTokens();
            tokens.setAccessToken(Configuration.getProperty("bhbl.rest.access_token"));
            tokens.setRefreshToken(Configuration.getProperty("bhbl.rest.refresh_token"));
            rsp = blService.paymentHealthCareBL(urlEndpoint, resp.getTxnIdPartner(), orderAmount, tokens);
            //kt resp tu api BL
            if (!"200".equals(rsp.getErrorCode())) {
                resp.setPaymenmtUrl(rsp.getErrorCode());
                return Xml.Marshaller(resp);
            }

            resp.setPaymenmtUrl(rsp.getPayment_url());

            return Xml.Marshaller(resp);
        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            resp.setPaymenmtUrl("co loi khi lay link qua cong thanh toan!");
            return Xml.Marshaller(resp);
        }

    }

    /**
     * Get package cost bhbl by goihd va tuoi
     *
     * @param xml
     * @return
     */
    public String getBLPackageCost(String xml) throws Exception {

        BHBLPackageCostsResp rsp = new BHBLPackageCostsResp();
        rsp.setErrorCode(CommonConstant.RESPONSE_SUCCEED);
        rsp.setErrorMsg("lay goi phi thanh cong!");

        try {
            Helper.writeLog(BHBLCustomerContractController.class, Level.INFO, xml);
            List<BHBLPackageCostResp> pkgContract = new ArrayList<>();
            BHBLPackageCostRq pkgCostRq = (BHBLPackageCostRq) Xml.unMarshaller(BHBLPackageCostRq.class, xml);

            pkgContract = Helper.getDBI().BHBL_getBLPackageCost(pkgCostRq.getTuoi(), pkgCostRq.getGioiTinh(), pkgCostRq.getLang());
            if (pkgContract.size() <= 0) {
                rsp.setPackageContract(null);
            }
            rsp.setPackageContract(pkgContract);

            return Xml.Marshaller(rsp);
        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            rsp.setErrorCode(CommonConstant.RESPONSE_FAILED);
            rsp.setErrorMsg("co loi trong qua trinh xu ly!");

            return Xml.Marshaller(rsp);
        }
    }

    /**
     * GetBLCategories
     *
     * @param xml
     * @return
     * @throws java.lang.Exception
     */
    public String GetBLCategories(String xml) throws Exception {

        BHBLMetadataResp rsp = new BHBLMetadataResp();
        rsp.setErrorCode(CommonConstant.RESPONSE_SUCCEED);
        rsp.setErrorMsg("lay danh muc thanh cong!");

        try {
            Helper.writeLog(BHBLCustomerContractController.class, Level.INFO, xml);
            BHBLMetadataRq request = (BHBLMetadataRq) Xml.unMarshaller(BHBLMetadataRq.class, xml);
            List<BHBLMetadataRes> metaLst = new ArrayList<>();
            metaLst = Helper.getDBI().BHBL_getBLCategories(request.getType());

            rsp.setMetaDataLst(metaLst);
            return Xml.Marshaller(rsp);
        } catch (Exception ex) {

            rsp.setErrorCode(CommonConstant.RESPONSE_FAILED);
            rsp.setErrorMsg("co loi trong qua trinh xu ly!");
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            return Xml.Marshaller(rsp);
        }

    }

    /**
     * GetBLAllQuestions
     *
     * @param xml
     * @return
     */
    public String getBLAllQuestions(String xml) throws Exception {

        BHBLQuestionXmlResp rsp = new BHBLQuestionXmlResp();
        rsp.setErrorCode(CommonConstant.RESPONSE_SUCCEED);
        rsp.setErrorMsg("lay cau hoi thanh cong!");

        try {

            Helper.writeLog(InsuranceController.class, Level.INFO, xml);
            BHBLQuestionRq request = (BHBLQuestionRq) Xml.unMarshaller(BHBLQuestionRq.class, xml);
            List<BHBLQuestionResp> questions = new ArrayList<>();
            questions = Helper.getDBI().BHBL_getBLAllQuestions(request.getLang());

            rsp.setBHBLQuestions(questions);
            return Xml.Marshaller(rsp);
        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            rsp.setErrorCode(CommonConstant.RESPONSE_FAILED);
            rsp.setErrorMsg("co loi trong qua trinh xu ly!");

            return Xml.Marshaller(rsp);
        }

    }

    /**
     * GetBLHealthInsPayment
     *
     * @param xml
     * @return
     */
    public String GetUrlOnepayBL(String xml) throws Exception {

        try {
            BHBLPaymentHealthCareResp rsp = new BHBLPaymentHealthCareResp();
            Helper.writeLog(BHBLCustomerContractController.class, Level.INFO, xml);
            BHBLPaymentHealthCareRq paymentRq = (BHBLPaymentHealthCareRq) Xml.unMarshaller(BHBLPaymentHealthCareRq.class, xml);
            BHBLHealCareContractService blService = new BHBLHealCareContractService();
            String urlEndpoint = Configuration.getProperty("bhbl.rest.url.address");

            BHBLTokens tokens = new BHBLTokens();
            tokens.setAccessToken(Configuration.getProperty("bhbl.rest.access_token"));
            tokens.setRefreshToken(Configuration.getProperty("bhbl.rest.refresh_token"));
            rsp = blService.paymentHealthCareBL(urlEndpoint, paymentRq.getOrder_no(), paymentRq.getAmount(), tokens);
            //kt resp tu api BL
            if (!"200".equals(rsp.getErrorCode())) {
                rsp.setErrorCode(rsp.getErrorCode());
                rsp.setErrorMsg("Co loi goi qua api BL.");
                return Xml.Marshaller(rsp);
            }

            rsp.setErrorCode(CommonConstant.RESPONSE_SUCCEED);
            rsp.setErrorMsg("lay url onepay thanh cong!");

            //String result = Xml.Marshaller(rsp);// != null ? Xml.Marshaller(rsp).replace("&amp;", "&") : null;
            return Xml.Marshaller(rsp);
        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            BHBLPaymentHealthCareResp rsp = new BHBLPaymentHealthCareResp();
            rsp.setErrorCode(CommonConstant.RESPONSE_FAILED);
            rsp.setErrorMsg("co loi trong qua trinh xu ly!");

            return Xml.Marshaller(rsp);
        }

    }

    /**
     * Update BL Payment Contract
     *
     * @param xml
     * @return
     */
    public String UpdatePaymentStatus(String xml) throws Exception {

        BHBLPaymentStatusResp rsp = new BHBLPaymentStatusResp();
        rsp.setErrorCode(CommonConstant.RESPONSE_SUCCEED);
        rsp.setErrorMsg("Cap nhat trang thai thanh toan thanh cong!");

        try {
            Helper.writeLog(BHBLCustomerContractController.class, Level.INFO, xml);
            BHBLPaymentContractInfoRq paymentContractInfo = (BHBLPaymentContractInfoRq) Xml.unMarshaller(BHBLPaymentContractInfoRq.class, xml);

            //xac thuc thong tin payment voi ben bao long
            BHBLSingnaturePaymentDto singtureModel = new BHBLSingnaturePaymentDto();
            singtureModel.setInvoiceCode(paymentContractInfo.getInvoiceCode());
            singtureModel.setPaymentCode(String.valueOf(paymentContractInfo.getPaymentCode()));
            singtureModel.setAmount(paymentContractInfo.getAmount());
            singtureModel.setStatus(paymentContractInfo.getStatus());
            String secretKeyBL = Configuration.getProperty("bhbl.ws.payment.md5.secretkey");
            GsonBuilder builder = new GsonBuilder();
            //builder.serializeNulls();
            Gson gson = builder.create();
            String myJSON = gson.toJson(singtureModel);

            String dataMD5 = myJSON.concat(secretKeyBL);
            String signatureBL = paymentContractInfo.getSignature();
            String signatureSCB = BHBLSignature.MD5Hash(dataMD5);
            paymentContractInfo.setPaymentCode(paymentContractInfo.getPaymentCode());

            if (!signatureBL.equals(signatureSCB.toUpperCase())) {
                String msgError = "Kiem tra ky so du lieu goi qua BHBL khong khop so voi ky so ben BHBL !";
                Helper.writeLogError(scb.com.vn.controller.BHBLCustomerContractController.class, msgError);
                //update data
                paymentContractInfo.setPaymentCode(Integer.valueOf(CommonConstant.BHBL_VERIFY_PAYMENT_FAILED));
                String res01 = Helper.getDBI().BHBL_updatePaymentStatus(paymentContractInfo);

                if ("0".equals(res01)) {
                    rsp.setErrorMsg("khong tim thay thong tin HD !");
                    rsp.setErrorCode(CommonConstant.RESPONSE_FAILED);
                    return Xml.Marshaller(rsp);
                }
            }

            String res = Helper.getDBI().BHBL_updatePaymentStatus(paymentContractInfo);
            if ("0".equals(res)) {
                rsp.setErrorMsg("Khong tim thay thong tin HD !");
                rsp.setErrorCode(CommonConstant.RESPONSE_FAILED);
            }

            return Xml.Marshaller(rsp);
        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            rsp.setErrorMsg("co loi trong qua trinh xu ly qua dbi!");
            rsp.setErrorCode(CommonConstant.RESPONSE_FAILED);
            return Xml.Marshaller(rsp);
        }
    }

    private static class CustomerContractValidation {

        /**
         * HealthBLInsPaymentValidation
         *
         * @param healCareIns
         * @param ques
         * @return
         */
        private static BHBLResponseCodeDto HealthBLInsPaymentValidation(BHBLHealthCareIns healCareIns, BHBLHealthCareInsRq.Questions ques) {

            BHBLResponseCodeDto validationRs = new BHBLResponseCodeDto();
            String msgErrors = "vui long nhap:";
            validationRs.setErrorMsg("validate succeess!");
            validationRs.setErrorCode(CommonConstant.RESPONSE_SUCCEED);

            if (StringUtils.isEmpty(healCareIns.getFullName01())) {
                msgErrors += " Fullname01 ";

            }
            if (StringUtils.isEmpty(healCareIns.getIndenCard01())) {
                msgErrors += " IndenCard01 ";
            }
            if (StringUtils.isEmpty(healCareIns.getAddress01())) {
                msgErrors += " Address01 ";
            }
            if (StringUtils.isEmpty(healCareIns.getPhone01())) {
                msgErrors += " Phone01 ";
            }
            if (StringUtils.isEmpty(healCareIns.getFullName02())) {
                msgErrors += " FullName02 ";

            }
            if (StringUtils.isEmpty(healCareIns.getBirthDay02())) {
                msgErrors += " BirthDay02 ";

            }
            if (StringUtils.isEmpty(healCareIns.getAddress02())) {
                msgErrors += " Address02 ";

            }
            if (StringUtils.isEmpty(healCareIns.getPhone02())) {
                msgErrors += " Phone02 ";

            }

            if (healCareIns.getRelationInsBuyer() != null && StringUtils.isNotBlank(healCareIns.getRelationInsBuyer())) {
                if (StringUtils.isEmpty(healCareIns.getFullName03())) {
                    msgErrors += " FullName03 ";
                }
                if (StringUtils.isEmpty(healCareIns.getAddress03())) {
                    msgErrors += " Address03 ";
                }
                if (StringUtils.isEmpty(healCareIns.getPhone03())) {
                    msgErrors += " Phone03 ";
                }
                if (healCareIns.getAgePeopleRelationShip() < 18) {
                    msgErrors += " tuoi bo/me di kem khong nho hon 18 tuoi ";
                }
            }

            if (StringUtils.isEmpty(healCareIns.getGender())) {
                msgErrors += " Gender ";
            }
            if (StringUtils.isEmpty(healCareIns.getRelationBuyer())) {
                msgErrors += " RelationBuyer ";

            }
            if (StringUtils.isEmpty(healCareIns.getEffDate())) {
                msgErrors += " EffDate ";

            }
            if (StringUtils.isEmpty(String.valueOf(healCareIns.getPackageExtraCost()))) {
                msgErrors += " PackageExtraCost ";
            }

            if (ques.getQuestion() == null || ques.getQuestion().size() < 0) {
                msgErrors += " Question ";
            }

            if (!"vui long nhap:".equals(msgErrors)) {
                validationRs.setErrorMsg(msgErrors);
                validationRs.setErrorCode(CommonConstant.BHBL_VALIDATION_FAILED);
            }
            return validationRs;
        }

    }

    /**
     * 
     *
     * @param xml
     * @return
     */
    public String firebaseSendMsg(String xml) throws Exception {
        try {
            NotificationFirbaseResp rsp = new NotificationFirbaseResp();

            BankCardDtlRq req = (BankCardDtlRq) Xml.unMarshaller(BankCardDtlRq.class,
                    xml);
            
            NotificationFirbase notiRq = new NotificationFirbase();
            notiRq.setTo(req.getAccountNo());
            
            BHBLHealCareContractService blService = new BHBLHealCareContractService();
            String urlEndpoint = "";

            rsp = blService.firebaseSendMsg(urlEndpoint, notiRq);

            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            Gson gson = builder.create();

            return gson.toJson(rsp);
        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            BHBLPaymentHealthCareResp rsp = new BHBLPaymentHealthCareResp();
            rsp.setErrorCode(CommonConstant.RESPONSE_FAILED);
            rsp.setErrorMsg("co loi trong qua trinh xu ly!");

            return Xml.Marshaller(rsp);
        }

    }

}
