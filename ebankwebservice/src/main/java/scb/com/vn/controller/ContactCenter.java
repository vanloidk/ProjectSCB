/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import org.apache.log4j.Logger;
import scb.com.vn.common.model.cims.taikhoan.GiaiToaTKTTReq;
import scb.com.vn.common.model.cims.taikhoan.GiaiToaTKTTRes;
import scb.com.vn.common.model.cims.taikhoan.PhongToaTKTTRes;
import scb.com.vn.common.model.cims.taikhoan.PhongToaTKTTReq;
import scb.com.vn.constant.CommonConstant;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.XMLUtils;

/**
 *
 * @author CORE77
 */
public class ContactCenter {

    private static final Logger LOGGER = Logger.getLogger(ContactCenter.class);

    final String _UNSUCESSFUL = "";

    /**
     *
     */
    public ContactCenter() {

    }

    //Customer info    

    /**
     *
     * @param SoDienThoai
     * @return
     */
    public String CC_GetCustomerInfoBySoDienThoai(String SoDienThoai) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetCustomerInfoBySoDienThoai", SoDienThoai);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_GetCustomerInfoByCIF(String CIF) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetCustomerInfoByCIF", CIF);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param IDCard
     * @return
     */
    public String CC_GetCustomerInfoByIDCard(String IDCard) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetCustomerInfoByIDCard", IDCard);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetListAccount(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetListAccount", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }
    
    /**
     *
     * @param param
     * @return
     */
    public String CC_GetRecentTransaction(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetRecentTransaction", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetTDAccounts(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetTDAccounts", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetTDAccountDetails(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetTDAccountDetails", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetTDAccountTranHistDetails(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetTDAccountTranHistDetails", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetCLAccounts(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetCLAccounts", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetCLAccountDetails(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetCLAccountDetails", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetCLHistPaymentDetails(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetCLHistPaymentDetails", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetCLAccountPayCalDetails(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetCLAccountPayCalDetails", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    //Ebanking    
    /**
     *
     * @param CIF
     * @return
     */
    public String CC_GetEBankingInfo(String CIF) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetEBankingInfo", CIF);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_IB_CUST_INFO(String CIF) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_IB_CUST_INFO", CIF);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_SMS_CUST_INFO(String CIF) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_SMS_CUST_INFO", CIF);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_SMSALERT_CUST_INFO(String CIF) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_SMSALERT_CUST_INFO", CIF);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_SMS_ALERT_TD_INFO(String CIF) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_SMS_ALERT_TD_INFO", CIF);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_CUST_MBANKING_INFO(String CIF) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_CUST_MBANKING_INFO", CIF);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_GetTTHD_TRANS_HIST(String CIF) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetTTHD_TRANS_HIST", CIF);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_GetTTHD_AUTO_CONTRACT(String CIF) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetTTHD_AUTO_CONTRACT", CIF);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    //CARD

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_GetInternalCardInfo(String CIF) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetInternalCardInfo", CIF);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GetInternalCardTransaction(String CardNumber) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetInternalCardTransaction", CardNumber);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }
    
    /**
     *
     * @param CIF
     * @return
     */
    public String CC_GetInternationalCardInfo(String CIF) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetInternationalCardInfo", CIF);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetInternalCardInfo_MC(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetInternalCardInfo_MC", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetInternalCardInfo_MCDB(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetInternalCardInfo_MCDB", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetCard_Profile(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetCard_Profile", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetAward_Point(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetAward_Point", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetInternalCardTran_MC(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetInternalCardTran_MC", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GET_TT_SaoKe(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GET_TT_SaoKe", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetCreditPayTransaction(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetCreditPayTransaction", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetMonth(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetMonth", param);

        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetSMSHist(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetSMSHist", param);
        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetEmailHist(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetEmailHist", param);
        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetIPP(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetIPP", param);
        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetIPPDetail(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetIPPDetail", param);
        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetIPPHist(String param) {
        try {
            return Helper.getDBI().queryContactCenterInfo("CC_GetIPPHist", param);
        } catch (Exception e) {
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     * CC_PhongToaTKTT
     *
     * @param xml
     * @return
     */
    public String CC_PhongToaTKTT(String xml) {
        try {
            PhongToaTKTTReq phongToaTKRq = (PhongToaTKTTReq) XMLUtils.unMarshaller(PhongToaTKTTReq.class, xml);
            //insert data to gateway
            String insertGateWay = Helper.getDBI().CC_InsertPhongToaGiaiToaTKTT(phongToaTKRq.getPartner(), phongToaTKRq.getAccounNumberOrCIF(), "PHONGTOA", phongToaTKRq.getUserName(), phongToaTKRq.getExpiryDate(), phongToaTKRq.getMaDonVi(), "", phongToaTKRq.getType());
            /*
            if (CommonConstant.KHOA_MOKHOA_TKTT_EXITS_DBI.equals(insertGateWay)) {
                PhongToaTKTTRes phongToaTKRes = new PhongToaTKTTRes();
                phongToaTKRes.setAccounNumberOrCIF(phongToaTKRq.getAccounNumberOrCIF());
                phongToaTKRes.setErrorCode(CommonConstant.KHOA_MOKHOA_TKTT_EXITS_GW);
                phongToaTKRes.setErrorMsg("Tai khoan da phong toa lan 1.");
                return Xml.Marshaller(phongToaTKRes);
            }
             */
            if ("-1".equals(insertGateWay)) {
                PhongToaTKTTRes phongToaTKRes = new PhongToaTKTTRes();
                phongToaTKRes.setAccounNumberOrCIF(phongToaTKRq.getAccounNumberOrCIF());
                phongToaTKRes.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
                phongToaTKRes.setErrorMsg("Error insert data at dbi.");
                return Xml.Marshaller(phongToaTKRes);
            }
            //giai toa tk qua core
            PhongToaTKTTRes phongToaTKRes = Helper.getDBI().CC_PhongToaTKTT(phongToaTKRq);
            if (phongToaTKRes != null) {
                phongToaTKRes.setAccounNumberOrCIF(phongToaTKRq.getAccounNumberOrCIF());
                phongToaTKRes.setUserName(phongToaTKRq.getUserName());
                phongToaTKRes.setType(phongToaTKRq.getType());
                phongToaTKRes.setExpiryDate(phongToaTKRq.getExpiryDate());
            }

            //update data to gateway
            long updateGateWay = Helper.getDBI().CC_UpdatePhongToaGiaiToaTKTT(Long.parseLong(insertGateWay), phongToaTKRes != null ? phongToaTKRes.getErrorCode() : CommonConstant.EXCEPTION_GATEWAY);
            if (updateGateWay <= 0) {
                PhongToaTKTTRes phongToaTKNewRes = new PhongToaTKTTRes();
                phongToaTKNewRes.setAccounNumberOrCIF(phongToaTKRq.getAccounNumberOrCIF());
                phongToaTKNewRes.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
                phongToaTKNewRes.setErrorMsg("Error update data at dbi.");
                return Xml.Marshaller(phongToaTKNewRes);
            }

            return Xml.Marshaller(phongToaTKRes);
        } catch (Exception e) {
            PhongToaTKTTRes phongToaTKRes = new PhongToaTKTTRes();
            phongToaTKRes.setAccounNumberOrCIF(phongToaTKRes.getAccounNumberOrCIF());
            phongToaTKRes.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
            phongToaTKRes.setErrorMsg("Error at gateway.");
            LOGGER.error(e);
            return _UNSUCESSFUL;
        }
    }

    /**
     * CC_GiaiToaTKTT
     *
     * @param xml
     * @return
     * @throws java.lang.Exception
     */
    public String CC_GiaiToaTKTT(String xml) throws Exception {
        try {
            GiaiToaTKTTReq giaiToaTkRq = (GiaiToaTKTTReq) XMLUtils.unMarshaller(GiaiToaTKTTReq.class, xml);

            //insert data to gateway
            String insertGateWay = Helper.getDBI().CC_InsertPhongToaGiaiToaTKTT(giaiToaTkRq.getPartner(), giaiToaTkRq.getAccounNumber(), "GIAIPHONGTOA", giaiToaTkRq.getUserName(), "", giaiToaTkRq.getMaDonVi(), "", "0");
            if ("-1".equals(insertGateWay)) {
                GiaiToaTKTTRes giaToaTKRes = new GiaiToaTKTTRes();
                giaToaTKRes.setAccounNumber(giaiToaTkRq.getAccounNumber());
                giaToaTKRes.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
                giaToaTKRes.setErrorMsg("Error insert data at dbi.");
                return Xml.Marshaller(giaToaTKRes);
            }
            //giai toa tk qua core
            GiaiToaTKTTRes giaToaTKRes = Helper.getDBI().CC_GiaiToaTKTT(giaiToaTkRq);
            if (giaToaTKRes != null) {
                giaToaTKRes.setMaDonVi(giaiToaTkRq.getMaDonVi());
                giaToaTKRes.setUserName(giaiToaTkRq.getUserName());
                giaToaTKRes.setAccounNumber(giaiToaTkRq.getAccounNumber());
            }

            //update data to gateway
            long updateGateWay = Helper.getDBI().CC_UpdatePhongToaGiaiToaTKTT(Long.parseLong(insertGateWay), giaToaTKRes != null ? giaToaTKRes.getErrorCode() : CommonConstant.EXCEPTION_GATEWAY);
            if (updateGateWay <= 0) {
                GiaiToaTKTTRes giaToaTKNewRes = new GiaiToaTKTTRes();
                giaToaTKNewRes.setAccounNumber(giaiToaTkRq.getAccounNumber());
                giaToaTKNewRes.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
                giaToaTKNewRes.setErrorMsg("Error update data at dbi.");
                return Xml.Marshaller(giaToaTKNewRes);
            }

            return Xml.Marshaller(giaToaTKRes);
        } catch (Exception e) {
            LOGGER.error(e);
            GiaiToaTKTTRes giaToaTKRes = new GiaiToaTKTTRes();
            giaToaTKRes.setAccounNumber(giaToaTKRes.getAccounNumber());
            giaToaTKRes.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
            giaToaTKRes.setErrorMsg("Error at gateway.");
            return Xml.Marshaller(giaToaTKRes);
        }
    }

}
