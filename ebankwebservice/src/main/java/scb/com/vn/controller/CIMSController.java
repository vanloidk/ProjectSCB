/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import java.rmi.RemoteException;
import java.util.List;
import my.com.webservices.CardProfileMaintReqBean;
import my.com.webservices.CardProfileMaintRespBean;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.cims.CardDetail;
import scb.com.vn.common.model.cims.kht.KichHoatTheInfo;
import scb.com.vn.common.model.cims.kht.KichHoatTheReq;
import scb.com.vn.common.model.cims.kht.KichHoatTheRes;
import scb.com.vn.common.model.cims.kt.KhoaTheReq;
import scb.com.vn.common.model.cims.kt.KhoaTheRes;
import scb.com.vn.common.model.cims.kt.MoKhoaTheReq;

import scb.com.vn.common.model.cims.kt.MoKhoaTheRes;
import scb.com.vn.common.model.cims.recieveFeedback.ReceiveFeedbackReq;
import scb.com.vn.common.model.cims.recieveFeedback.ReceiveFeedbackRes;
import scb.com.vn.common.model.cims.register.FeedbackInfo;
import scb.com.vn.common.model.cims.register.RegisterInfoDetail;
import scb.com.vn.common.model.cims.register.RegisterInfoReq;
import scb.com.vn.common.model.cims.register.RegisterInfoRes;
import scb.com.vn.common.model.cims.validatecmnd.ValidateCmndReq;
import scb.com.vn.common.model.cims.validatecmnd.ValidateCmndRes;
import scb.com.vn.common.model.cw.CardInfo;
import scb.com.vn.enumUtils.CIMSResultEnum;
import scb.com.vn.utility.CimsUtils;
import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.XMLUtils;

/**
 *
 * @author minhndb
 */
public class CIMSController {

    private static final String REQUESTER = "CIMS";
    private static final String CWFI = "970429";
    private static final String CWACTIND = "3";
    private static final String KHTCHANNEL = "SMS";

    private static final int KHT_SUCCESS = 0;
    private static final int KHT_FAILED = 1;
    private static final int KHT_SUCCESSNOTDELI = 3;
    private static final int KHT_INVALIDREQ = 4;
    private static final int KHT_SYSTEMERROR = 5;
    private static final int KHT_AUTO_SUCCESS = 6;

    private final String isCheckedQuery = "N";
    private final String isCheckedUpdate = "Y";

    private static final String ID_USERNAME = "17126";
    private static final String USERNAME = "htkhib";

    private static final Logger LOGGER = Logger.getLogger(CIMSController.class);

    /**
     *
     * @param req
     * @return
     */
    public String validateCMND(ValidateCmndReq req) {
        ValidateCmndRes info = new ValidateCmndRes(req);
        try {
            List<CardInfo> cards = Helper.getDBI().GetCardInfoByPhone(req.getPhone());
            if (!cards.isEmpty()) {
                boolean isMultipleCif = CimsUtils.isMultipleCif(cards);
                if (!isMultipleCif) {
                    String uncharCmndR = req.getCmnd().replaceAll("\\D+", "");
                    for (CardInfo item : cards) {
                        String uncharCmnd = item.getCmnd().replaceAll("\\D+", "");
                        if (uncharCmndR.equalsIgnoreCase(uncharCmnd)) {
                            CardDetail cardDetail = new CardDetail();
                            cardDetail.setLast4Digits(item.getLast4Digits());
                            cardDetail.setCardType(item.getCardType());
                            cardDetail.setActive(!"0".equals(item.getActiveDate()));
                            cardDetail.setCardTypeM(item.getLocLimit(), item.getCardBranch());
                            info.getCardInfo().addCardDetail(cardDetail);
                        }
                    }
                    if (info.getCardInfo().getCardDetail().size() > 0) {
                        info.setStatus(CIMSResultEnum.SUCCESS.getText());
                    } else {
                        info.setStatus(CIMSResultEnum.UNKNOWNCMND.getText());
                    }
                } else {
                    info.setStatus(CIMSResultEnum.MULTIPLECIF.getText());
                }
            } else {
                info.setStatus(CIMSResultEnum.UNKNOWNPHONE.getText());
            }
        } catch (Exception e) {
            LOGGER.error(e);
            info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
        }
        return XMLUtils.Marshaller(info);
    }

    /**
     *
     * @param req
     * @return
     */
    public String khoaThe(KhoaTheReq req) {
        KhoaTheRes info = new KhoaTheRes(req);
        try {
            List<CardInfo> cards = Helper.getDBI().getCardInfo(req);
            if (!cards.isEmpty()) {
                //bo sung kenh hotline ko nhap vao card type
                if (cards.size() == 1) {
                    req.setCardType(cards.get(1).getCardType());
                }
                boolean result = Helper.getDBI().INSERTKHOATHE(req);
                if (result) {
                    int lockCard = 0;
                    for (CardInfo card : cards) {
                        String refNo = Helper.getDBI().INSERTKHOATHEDETAILS(req, card);
                        if (refNo != null && !refNo.isEmpty()) {
                            CardProfileMaintRespBean resp = callCWKhoaThe(refNo, card);
                            if (resp != null) {
                                Helper.getDBI().UPDATEKHOATHEDETAILS(refNo, req, resp.getResponseCode(), resp.getResponseDescription());
                                if ("000".equals(resp.getResponseCode())) {
                                    lockCard++;
                                    LOGGER.info("Khoa the " + card.getLast4Digits() + " thanh cong");
                                } else {
                                    LOGGER.info("Khoa the " + card.getLast4Digits() + " that bai. Decc = " + resp.getResponseDescription());
                                }
                            } else {
                                LOGGER.info("Goi qua CW that bai khi lock the " + card.getLast4Digits());
                                Helper.getDBI().UPDATEKHOATHEDETAILS(refNo, req, "null", "Failed to execute CW");
                            }
                        } else {
                            LOGGER.info("Loi khi insert thong tin chi tiet the " + card.getLast4Digits() + " xuong database");
                        }
                    }
                    if (lockCard == cards.size()) {
                        info.setStatus(CIMSResultEnum.SUCCESS.getText());
                    } else {
                        info.setStatus(CIMSResultEnum.LOCKCARDISERROR.getText());
                    }
                } else {
                    info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
                }
            } else {
                info.setStatus(CIMSResultEnum.CARDISLOCKED.getText());
            }
        } catch (Exception e) {
            LOGGER.error(e);
            info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
        }
        return XMLUtils.Marshaller(info);
    }

    private CardProfileMaintRespBean callCWKhoaThe(String refNo, CardInfo card) {
        try {
            CardProfileMaintReqBean cardProfileMainReq = new CardProfileMaintReqBean();
            cardProfileMainReq.setSequenceNo(refNo);
            cardProfileMainReq.setFi("970429");
            cardProfileMainReq.setPan(card.getLoc() + card.getLast4Digits()); //12 Digit LOC Account + Last 4 Digit Card No. ~ 800000312171763911111
            cardProfileMainReq.setActInd("3"); //Account Indicator; Refer to Webservices Spec
            cardProfileMainReq.setActType("CD"); //Action Type; Refer to Webservices Spec
            cardProfileMainReq.setActCde("B"); //Action Code; Refer to Webservices Spec
            CardwordController cwController = new CardwordController();
            return cwController.getCardworkWS().cardProfileMaintenance(cardProfileMainReq);
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    /**
     * Mo khoa the hotline
     *
     * @param req
     * @return
     */
    public String MoKhoaTheHotline(MoKhoaTheReq req) {

        MoKhoaTheRes info = new MoKhoaTheRes(req);
        try {
            List<CardInfo> cards = Helper.getDBI().getCardLockedInfo(req);
            // kt thong tin the
            if (cards.isEmpty()) {
                info.setStatus(CIMSResultEnum.CARDDOESNOTFOUND.getText());
                return XMLUtils.Marshaller(info);
            }
            // insert thong tin the vao gateway
            boolean result = Helper.getDBI().INSERTMOKHOATHE(req);

            if (!result) {
                LOGGER.info("Loi khi insert thong tin the " + req.getLast4Digits() + " xuong database");
                info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
                return XMLUtils.Marshaller(info);
            }

            int openCard = 0;
            for (CardInfo card : cards) {
                String refNo = Helper.getDBI().INSERTMOKHOATHEDETAILS(req, card);
                if (refNo == null || refNo.isEmpty()) {
                    LOGGER.info("Loi khi insert thong tin chi tiet the " + card.getLast4Digits() + " xuong database");
                    info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
                    return XMLUtils.Marshaller(info);
                }
                //goi qua cw
                CardProfileMaintRespBean resp = callCWMoThe(refNo, card);

                if (resp != null) {
                    boolean isUpdate = Helper.getDBI().UPDATEMOKHOATHEDETAILS(refNo, req, resp.getResponseCode(), resp.getResponseDescription());
                    if (!isUpdate) {
                        LOGGER.info("Loi khi update thong tin chi tiet the khi da goi qua cw thanh cong" + card.getLast4Digits() + " xuong database");
                        info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
                        return XMLUtils.Marshaller(info);
                    }
                    if ("000".equals(resp.getResponseCode())) {
                        openCard++;
                        LOGGER.info("Mo the khoa" + card.getLast4Digits() + " thanh cong");
                    } else {
                        LOGGER.info("Mo the khoa" + card.getLast4Digits() + " khong thanh con. Decc = " + resp.getResponseDescription());
                    }
                } else {
                    LOGGER.info("Goi qua CW that bai khi mo khoa the " + card.getLast4Digits());
                    boolean isUpdate = Helper.getDBI().UPDATEMOKHOATHEDETAILS(refNo, req, "null", "Failed to execute CW");
                    if (!isUpdate) {
                        LOGGER.info("Loi khi update thong tin chi tiet the khi da goi qua cw khong thanh cong " + card.getLast4Digits() + " xuong database");
                        info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
                        return XMLUtils.Marshaller(info);
                    }
                }
            }

            if (openCard == cards.size()) {
                info.setStatus(CIMSResultEnum.SUCCESS.getText());
            } else {
                info.setStatus(CIMSResultEnum.OPENCARDISERROR.getText());
            }
        } catch (RemoteException e) {
            LOGGER.error(e);
            info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
            return XMLUtils.Marshaller(info);
        }
        return XMLUtils.Marshaller(info);
    }

    /**
     * Mo khoa the tren crm
     *
     * @param req
     * @return
     */
    public String MoKhoaTheCRM(MoKhoaTheReq req) {

        MoKhoaTheRes info = new MoKhoaTheRes(req);
        req.setOpenAll("N");
        try {
            // insert thong tin the vao gateway
            boolean result = Helper.getDBI().INSERTMOKHOATHE(req);
            if (!result) {
                LOGGER.info("Loi khi insert thong tin the " + req.getLast4Digits() + " xuong database");
                info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
                return XMLUtils.Marshaller(info);
            }

            CardInfo card = new CardInfo();
            card.setLoc(req.getCardAccountNumer());
            card.setPan_mask("XXXXXXXXXXXX" + req.getLast4Digits());
            String refNo = Helper.getDBI().INSERTMOKHOATHEDETAILS(req, card);

            if (refNo == null || refNo.isEmpty()) {
                LOGGER.info("Loi khi insert thong tin chi tiet the " + card.getLast4Digits() + " xuong database");
                info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
                return XMLUtils.Marshaller(info);
            }
            //goi qua cw
            CardProfileMaintRespBean resp = callCWMoThe(refNo, card);

            if (resp != null) {
                boolean isUpdate = Helper.getDBI().UPDATEMOKHOATHEDETAILS(refNo, req, resp.getResponseCode(), resp.getResponseDescription());
                if (!isUpdate) {
                    LOGGER.info("Loi khi update thong tin chi tiet the khi da goi qua cw thanh cong" + card.getLast4Digits() + " xuong database");
                    info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
                    return XMLUtils.Marshaller(info);
                }
                if ("000".equals(resp.getResponseCode())) {
                    LOGGER.info("Mo the khoa" + card.getLast4Digits() + " thanh cong");
                    info.setStatus(CIMSResultEnum.SUCCESS.getText());
                } else {
                    LOGGER.info("Mo the khoa" + card.getLast4Digits() + " khong thanh con. Desc = " + resp.getResponseDescription());
                    info.setStatus(CIMSResultEnum.LOCKCARDISERROR.getText());
                }
            } else {
                LOGGER.info("Goi qua CW that bai khi mo khoa the " + card.getLast4Digits());
                boolean isUpdate = Helper.getDBI().UPDATEMOKHOATHEDETAILS(refNo, req, "null", "Failed to execute CW");
                if (!isUpdate) {
                    LOGGER.info("Loi khi update thong tin chi tiet the khi da goi qua cw khong thanh cong " + card.getLast4Digits() + " xuong database");
                    info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
                    return XMLUtils.Marshaller(info);
                }

                info.setStatus(CIMSResultEnum.LOCKCARDISERROR.getText());
            }

        } catch (RemoteException e) {
            LOGGER.error(e);
            info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
            return XMLUtils.Marshaller(info);
        }
        return XMLUtils.Marshaller(info);
    }

    /**
     * KhoaThe tren CRM
     *
     * @param req
     * @return
     */
    public String KhoaTheCRM(KhoaTheReq req) {
        KhoaTheRes info = new KhoaTheRes(req);
        req.setLockAll(false);
        try {

            boolean result = Helper.getDBI().INSERTKHOATHE(req);
            if (result) {
                CardInfo card = new CardInfo();
                card.setLoc(req.getCardAccountNumer());
                card.setPan_mask("XXXXXXXXXXXX" + req.getLast4Digits());

                String refNo = Helper.getDBI().INSERTKHOATHEDETAILS(req, card);
                if (refNo != null && !refNo.isEmpty()) {
                    CardProfileMaintRespBean resp = callCWKhoaThe(refNo, card);
                    if (resp != null) {
                        Helper.getDBI().UPDATEKHOATHEDETAILS(refNo, req, resp.getResponseCode(), resp.getResponseDescription());
                        if ("000".equals(resp.getResponseCode())) {
                            LOGGER.info("Khoa the " + card.getLast4Digits() + " thanh cong");
                            info.setStatus(CIMSResultEnum.SUCCESS.getText());
                        } else {
                            LOGGER.info("Khoa the " + card.getLast4Digits() + " that bai. Desc = " + resp.getResponseDescription());
                            info.setStatus(CIMSResultEnum.LOCKCARDISERROR.getText());
                        }
                    } else {
                        LOGGER.info("Goi qua CW that bai khi lock the " + card.getLast4Digits());
                        info.setStatus(CIMSResultEnum.LOCKCARDISERROR.getText());
                        Helper.getDBI().UPDATEKHOATHEDETAILS(refNo, req, "null", "Failed to execute CW");
                    }
                } else {
                    LOGGER.info("Loi khi insert thong tin chi tiet the KHOATHEDETAIL." + card.getLast4Digits() + " xuong database");
                    info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
                }

            } else {
                LOGGER.info("Loi khi insert thong tin vao bang KHOATHE.");
                info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
            }

        } catch (Exception e) {
            LOGGER.error(e);
            info.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
        }

        return XMLUtils.Marshaller(info);
    }

    /**
     *
     * @param req
     * @return
     */
    public String kichHoatThe(KichHoatTheReq req) {
        LOGGER.info("Start to kich hoat the");
        KichHoatTheRes response = new KichHoatTheRes(req);
        int flag = KHT_SYSTEMERROR;
        KichHoatTheInfo info = new KichHoatTheInfo(req.getSequence(), REQUESTER,
                req.getPhone(), "SCB KHT " + req.getLast4Digits() + " " + req.getCmnd(),
                req.getFormatTime());
        try {
            boolean databaseIsOk = Helper.getDBI().KICHHOATTHE(info);
            if (databaseIsOk) {
                LOGGER.info("Phone = [" + info.getPhoneNumber() + "], Message = [" + info.getReqMessage() + "], Time = [" + req.getTime() + "]");
                info.setLast4Digits(req.getLast4Digits());
                info.setCmnd(req.getCmnd());
                /* Lay thong tin the */
                List<CardInfo> cards = Helper.getDBI().GetCardInfoByPhone(info.getPhoneNumber(), info.getLast4Digits());
                LOGGER.info("Card size: " + cards.size() + ", phone: " + info.getPhoneNumber() + ", digit: " + info.getLast4Digits());
                if (CommonUtils.validCusForKHT(cards)) {
                    for (CardInfo item : cards) {
                        LOGGER.info("Duyet the --> last number = [" + item.getLast4Digits() + "], CMND = [" + item.getCmnd() + "]");
                        if (info.isCardLikeToActive(item.getCmnd(), item.getLast4Digits())) {
                            if (!item.isActive()) {
                                String transferStatus = Helper.getDBI()
                                        .GetCardTransferStatus(item.getPanEncrypt());
                                item.setTransferStatus(transferStatus);
                                info.setDelivered(item.isIsTransfered());
                                info.setupInfoBeforeExec(item);
                                CardwordController cwController = new CardwordController();
                                boolean khtResult = cwController.callCwKichHoatThe(info);
                                if (khtResult) {
                                    LOGGER.info("Kich hoat the qua CW thanh cong");
                                    flag = KHT_AUTO_SUCCESS;
                                } else {
                                    flag = KHT_FAILED;
                                }
                                break;
                            } else {
                                LOGGER.warn("The [" + info.getLast4Digits() + "] da duoc active ngay [" + item.getActiveDate() + "]");
                            }
                        } else {
                            LOGGER.info("The " + item.getLast4Digits() + ", CMND " + item.getCmnd() + " ko map voi the can kich hoat");
                        }
                    }
                } else {
                    LOGGER.warn("Danh sach the bi invalid");
                }
                /* Kiem tra xem da co the nao kich hoat thanh cong chua */
                if (flag == KHT_SYSTEMERROR) {
                    LOGGER.warn("Ko tim thay the thoa dieu kien cccd = [" + info.getCmnd() + "], lastNum = [" + info.getLast4Digits() + "] chua duoc active");
                    flag = KHT_FAILED;
                }
            } else {
                LOGGER.warn("Cannot insert to database");
            }
        } catch (Exception e) {
            LOGGER.error(e);
            response.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
        } finally {
            try {
                setupKHTResponse(response, info, flag);
                Helper.getDBI().KICHHOATTHE(info);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        LOGGER.info("Finish to kich hoat the");
        return XMLUtils.Marshaller(response);
    }

    private void setupKHTResponse(KichHoatTheRes response, KichHoatTheInfo info, int flag) {
        info.setStatus(String.valueOf(flag));
        switch (flag) {
            case KHT_AUTO_SUCCESS:
                response.setStatus(CIMSResultEnum.SUCCESS.getText());
                info.setResMessage(String.format("The SCB %s da duoc kich hoat thanh cong.", info.getLast4Digits()));
                break;
            case KHT_FAILED:
                response.setStatus(CIMSResultEnum.UNSUCCESS.getText());
                info.setResMessage("Tin nhan khong hop le, vui long lien he 19006538.");
                break;
            case KHT_SYSTEMERROR:
            default:
                response.setStatus(CIMSResultEnum.SYSTEMERROR.getText());
                info.setResMessage("Tin nhan khong hop le, vui long lien he 19006538.");
                break;
        }
    }
    
    public String getInfoRegister(RegisterInfoReq req) {
        RegisterInfoRes response = new RegisterInfoRes();
        response.setPartnerCode(req.getPartnerCode());
        try {
            /*Lay danh sach dang ky dang check = 'N'*/
            List<RegisterInfoDetail> registerInfoDetail = Helper.getDBI().getListRegisterDetail(isCheckedQuery);
            /*Lay danh sach gop y dang check = 'N'*/
            List<FeedbackInfo> feedbackInfo = Helper.getDBI().getListFeedback(isCheckedQuery);
            /*Kiem tra danh sach co bi rong ko*/
            response.setFeedbackInfo(feedbackInfo);
            response.setRegisterDetail(registerInfoDetail);
            response.setResponseCode(CIMSResultEnum.SUCCESS.getText());
            response.setDescription(CIMSResultEnum.SUCCESS.name());

            /*check chu ky*/
            String mac = CimsUtils.buildMac(response.getValueToBuildMac(), req.getPartnerCode());
            response.setSignature(mac);

        } catch (Exception ex) {
            LOGGER.error(ex);
            response.setResponseCode(CIMSResultEnum.UNSUCCESS.getText());
            response.setDescription(CIMSResultEnum.UNSUCCESS.name());
        }
        return XMLUtils.Marshaller(response);
    }

    public String getReceiveFeedback(ReceiveFeedbackReq req) {
        ReceiveFeedbackRes response = new ReceiveFeedbackRes();
        response.setPartnerCode(req.getPartnerCode());
        response.setTypeId(req.getTypeId());
        response.setType(req.getType());

        if (req.isValid()) {
            try {
                if (USERNAME.equals(req.getUsername())) {
                    req.setIdUsername(ID_USERNAME);
                }
                int result = Helper.getDBI().updateListReceiveFeedback(isCheckedUpdate, req.getTypeId(), req.getType(), req.getIdUsername(), req.getUsername());
                if (result != 0) {
                    response.setResponseCode(CIMSResultEnum.SUCCESS.getText());
                    response.setDescription(CIMSResultEnum.SUCCESS.name());
                } else {
                    response.setResponseCode(CIMSResultEnum.IDDOESNOTFOUND.getText());
                    response.setDescription(CIMSResultEnum.IDDOESNOTFOUND.name());
                }
                /*check chu ky*/
                String mac = CimsUtils.buildMac(response.getValueBuildMac(), req.getPartnerCode());
                response.setSignature(mac);
            } catch (Exception ex) {
                LOGGER.error(ex);
                response.setResponseCode(CIMSResultEnum.UNSUCCESS.getText());
                response.setDescription(CIMSResultEnum.UNSUCCESS.name());
            }
        } else {
            response.setResponseCode(CIMSResultEnum.INVALIDVALUE.getText());
            response.setDescription(CIMSResultEnum.INVALIDVALUE.name());
        }
        return XMLUtils.Marshaller(response);
    }
    
    /**
     * CallCWMoThe
     *
     * @param refNo
     * @param card
     * @return
     */
    private CardProfileMaintRespBean callCWMoThe(String refNo, CardInfo card) {
        try {
            CardProfileMaintReqBean cardProfileMainReq = new CardProfileMaintReqBean();
            cardProfileMainReq.setSequenceNo(refNo);
            cardProfileMainReq.setFi("970429");
            cardProfileMainReq.setPan(card.getLoc() + card.getLast4Digits()); //12 Digit LOC Account + Last 4 Digit Card No. ~ 800000312171763911111
            cardProfileMainReq.setActInd("3"); //Account Indicator; Refer to Webservices Spec
            cardProfileMainReq.setActType("CD"); //Action Type; Refer to Webservices Spec
            cardProfileMainReq.setActCde("U"); //Action Code; Refer to Webservices Spec
            CardwordController cwController = new CardwordController();
            return cwController.getCardworkWS().cardProfileMaintenance(cardProfileMainReq);
        } catch (RemoteException e) {
            LOGGER.error(e);
            return null;
        }
    }

}
