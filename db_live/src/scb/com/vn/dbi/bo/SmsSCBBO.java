package scb.com.vn.dbi.bo;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import scb.com.vn.common.model.cims.register.RegisterInfoDetail;
import scb.com.vn.dbi.dto.GwEmailTd;
import scb.com.vn.dbi.dao.SmsSCBDAO;
import scb.com.vn.dbi.dto.BCBill;
import scb.com.vn.dbi.dto.EbIssuecard;
import scb.com.vn.dbi.dto.EtsMstchanneluser;
import scb.com.vn.dbi.dto.EtsMstuser;
import scb.com.vn.dbi.dto.FeeTransactionUnlock;
import scb.com.vn.dbi.dto.GwTdLucky;
import scb.com.vn.dbi.dto.Insurance;
import scb.com.vn.dbi.dto.InsurancePayment;
import scb.com.vn.dbi.dto.NapasCollate;
import scb.com.vn.dbi.dto.NtdtPaymentExtend;
import scb.com.vn.dbi.dto.PaymentByCardTracking;
import scb.com.vn.dbi.dto.PblAutoReg;
import scb.com.vn.dbi.dto.PblBillpaid;
import scb.com.vn.dbi.dto.PblBillpaidTransReverse;
import scb.com.vn.dbi.dto.PblCustTemplate;
import scb.com.vn.dbi.dto.PblEbkProcess;
import scb.com.vn.dbi.dto.PblEbkScreen;
import scb.com.vn.dbi.dto.PblEbkScreenId;
import scb.com.vn.dbi.dto.PblLog;
import scb.com.vn.dbi.dto.PblPartnerservice;
import scb.com.vn.dbi.dto.PblPartnerserviceId;
import scb.com.vn.dbi.dto.SmsCustAlertTd;
import scb.com.vn.dbi.dto.SmsCustAlertTichLuy;
import scb.com.vn.dbi.dto.SmsPartner;
import scb.com.vn.dbi.dto.SmsSender;
import scb.com.vn.dbi.dto.Sms_UserPin2;
import scb.com.vn.dbi.dto.VwAutoReg;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.dbi.dto.VwMstchanneluser;
import scb.com.vn.dbi.dto.VwUserbranch;
import scb.com.vn.dbi.dto.VwWaitapproveBranch;
import scb.com.vn.dbi.utility.HibernateUtil;
import scb.com.vn.dbi.dto.PblCollated_VNPAY;
import scb.com.vn.dbi.dto.ProcedureCallDto;
import scb.com.vn.dbi.dto.RTBuyCardDTO;
import scb.com.vn.dbi.dto.RTCardDetailDTO;
import scb.com.vn.dbi.dto.SITrftToSIAuth;
import scb.com.vn.dbi.dto.SMSService;
import scb.com.vn.dbi.dto.SMSServiceTK;
import scb.com.vn.dbi.dto.SMSServiceUser;
import scb.com.vn.dbi.dto.SmsThuphi;
import scb.com.vn.dbi.dto.ThuPhiSMS;
import scb.com.vn.dbi.dto.UserOtherDeviceOdbxDto;
import scb.com.vn.dbi.dto.VwCustAccountNew;
import scb.com.vn.dbi.dto.sms_partner_request;
import scb.com.vn.dbi.utility.Helper;

/**
 *
 * @author minhndb
 */
public class SmsSCBBO extends BaseSMSSCBBO {

    private static final Logger LOGGER = Logger.getLogger(SmsSCBBO.class);

    private Connection conn;
    private SmsSCBDAO smsscbdao = null;

    /**
     * Create a new instance of SmsSCBBO
     *
     * @throws java.lang.Exception
     */
    public SmsSCBBO() throws Exception {
        smsscbdao = new SmsSCBDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactorySmsScb());
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getListMstTxn() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getListMstTxn();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id_entity
     * @param usertype
     * @param idchannel
     * @param description
     * @param isdefault
     * @param createdby
     * @return
     * @throws Exception
     */
    public int insertMstRole(String id_entity, String usertype, String idchannel, String description, String isdefault, String createdby) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertMstRole(id_entity, usertype, idchannel,
                    description, isdefault, createdby);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id_entity
     * @param usertype
     * @param idchannel
     * @param idrole
     * @return
     * @throws Exception
     */
    public int deleteMstRole(String id_entity, String usertype, String idchannel, String idrole) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.deleteMstRole(id_entity, usertype, idchannel,
                    idrole);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idrole
     * @return
     * @throws Exception
     */
    public int deleteRoleTxn(String idrole) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.deleteRoleTxn(idrole);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idrole
     * @param idtxn
     * @param flginit
     * @return
     * @throws Exception
     */
    public int insertRoleTxn(String idrole, String idtxn, String flginit) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertRoleTxn(idrole, idtxn, flginit);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param usertype
     * @param description
     * @return
     * @throws Exception
     */
    public ArrayList searchMstRole(String usertype, String description) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchMstRole(usertype, description);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idrole
     * @return
     * @throws Exception
     */
    public ArrayList searchRoleTxn(String idrole) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchRoleTxn(idrole);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param mstuser
     * @return
     * @throws Exception
     */
    public int insertMstUser(EtsMstuser mstuser) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertMstUser(mstuser);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id_entity
     * @param iduser
     * @param idchanneluser
     * @param idchannel
     * @param password
     * @param lockflag
     * @param flagforcechanngepwd
     * @return
     * @throws Exception
     */
    public int insertChannelUser(String id_entity, String iduser, String idchanneluser, String idchannel, String password, String lockflag, String flagforcechanngepwd) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertChannelUser(id_entity, iduser,
                    idchanneluser, idchannel, password, lockflag,
                    flagforcechanngepwd);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id_entity
     * @param iduser
     * @param idchannel
     * @param idrole
     * @return
     * @throws Exception
     */
    public int insertUserRole(String id_entity, String iduser, String idchannel, String idrole) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertUserRole(id_entity, iduser, idchannel,
                    idrole);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    // ======================== SMS =======================
    /**
     *
     * @param smsuserpin
     * @return
     * @throws Exception
     */
    public int insertUserPin2(Sms_UserPin2 smsuserpin) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertUserPin2(smsuserpin);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @return
     * @throws Exception
     */
    public ArrayList getUserPin2(String idchanneluser, String usertype) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getUserPin2(idchanneluser, usertype);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @return
     * @throws Exception
     */
    public int updAccessSucc_UserPin2(String idchanneluser, String usertype) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updAccessSucc_UserPin2(idchanneluser, usertype);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @return
     * @throws Exception
     */
    public int updAccessFail_UserPin2(String idchanneluser, String usertype) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updAccessFail_UserPin2(idchanneluser, usertype);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @param pwdtxn
     * @param lenpwd
     * @param flagforcechangepwdtxn
     * @return
     * @throws Exception
     */
    public int changePwd_UserPin2(String idchanneluser, String usertype, String pwdtxn, int lenpwd, String flagforcechangepwdtxn) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.changePwd_UserPin2(idchanneluser, usertype,
                    pwdtxn, lenpwd, flagforcechangepwdtxn);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idchanneluserOld
     * @param idchanneluserNew
     * @param usertype
     * @return
     * @throws Exception
     */
    public int changeIdChanneluser_UserPin2(String idchanneluserOld, String idchanneluserNew, String usertype) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.changeIdChanneluser_UserPin2(idchanneluserOld,
                    idchanneluserNew, usertype);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id_entity
     * @param idchannel
     * @param usertype
     * @param idchanneluser
     * @param typecmd
     * @param smsmessage
     * @param demonstring
     * @return
     * @throws Exception
     */
    public int insertWaitResponse(String id_entity, String idchannel, String usertype, String idchanneluser, String typecmd, String smsmessage, String demonstring) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertWaitResponse(id_entity, idchannel, usertype,
                    idchanneluser, typecmd, smsmessage, demonstring);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id_entity
     * @param idchannel
     * @param usertype
     * @param idchanneluser
     * @param typecmd
     * @return
     * @throws Exception
     */
    public ArrayList getAllListWaitResponse(String id_entity, String idchannel, String usertype, String idchanneluser, String typecmd) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListWaitResponse(id_entity, idchannel,
                    usertype, idchanneluser, typecmd);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param matinhthanh
     * @param quan
     * @return
     * @throws Exception
     */
    public ArrayList getAllListATM(String matinhthanh, String quan) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListATM(matinhthanh, quan);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param matinhthanh
     * @return
     * @throws Exception
     */
    public ArrayList getAllListATM_ext(String matinhthanh) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListATM_ext(matinhthanh);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param matinhthanh
     * @param quan
     * @return
     * @throws Exception
     */
    public ArrayList getAllListBranch_main(String matinhthanh, String quan) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListBranch_main(matinhthanh, quan);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param matinhthanh
     * @return
     * @throws Exception
     */
    public ArrayList getAllListBranch_ext(String matinhthanh) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListBranch_ext(matinhthanh);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getAllListNHLM() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListNHLM();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idchanneluser
     * @param typeuser
     * @param result
     * @param msglog
     * @param idmarker
     * @return
     * @throws Exception
     */
    public int insSmsLog(String idchanneluser, String typeuser, String result, String msglog, String idmarker) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insSmsLog(idchanneluser, typeuser, result, msglog, idmarker);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getAllListServiceType() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListServiceType();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*CK Tan Viet (S)*/
    /**
     *
     * @return @throws Exception
     */
    public ArrayList getAllListPartnerFI() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListPartnerFI();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param si
     * @return
     * @throws Exception
     */
    public int insertTrftToSI(SITrftToSIAuth si) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        int i = smsscbdao.insertTrftToSI(si);
        commitTransaction();
        return i;
    }

    /**
     *
     * @param id
     * @return
     */
    public SITrftToSIAuth getTrftToSIById(int id) {
        beginTransaction();
        SITrftToSIAuth r = (SITrftToSIAuth) getSession().get(SITrftToSIAuth.class, id);
        if (r == null) {
            return null;
        }
        SITrftToSIAuth si = (SITrftToSIAuth) HibernateUtil.getMapper().map(r, SITrftToSIAuth.class);
        commitTransaction();
        return si;
    }

    /**
     *
     * @param si
     * @throws Exception
     */
    public void updateTrftToSI(SITrftToSIAuth si) throws Exception {
        update(si);
    }

    /**
     *
     * @param fromDateSearch
     * @param toDateSearch
     * @return
     * @throws Exception
     */
    public ArrayList getlistAllSI(String fromDateSearch, String toDateSearch) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getlistAllSI(fromDateSearch, toDateSearch);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param servicetype
     * @param branchcode
     * @return
     * @throws Exception
     */
    public ArrayList getApproveTranferFI(String servicetype, String branchcode) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getApproveTranferFI(servicetype, branchcode);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param partnerId
     * @return
     * @throws Exception
     */
    public String getPartnerNameById(String partnerId) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getPartnerNameById(partnerId);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*CK Tan Viet (E)*/
    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getAllListServiceTypeOfAutoPay() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListServiceTypeOfAutoPay();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getAllListProvider() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListProvider();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idservicetype
     * @param idprovider
     * @return
     * @throws Exception
     */
    public ArrayList<?> getPartnerServiceByPs(String idservicetype, String idprovider) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getPartnerServiceByPs(idservicetype, idprovider);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getAllListPartnerService() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListPartnerService();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idprovider
     * @param idservicetype
     * @return
     * @throws Exception
     */
    public ArrayList getPartnerService(String idprovider, String idservicetype) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getPartnerService(idprovider, idservicetype);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idpartner
     * @param idservicetype
     * @param idprovider
     * @param channel
     * @param result
     * @param msglog
     * @param idmarker
     * @param description
     * @return
     * @throws Exception
     */
    public int insertPaybillLogDB(String idpartner, String idservicetype,
            String idprovider, String channel, String result, String msglog,
            String idmarker, String description) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertPaybillLogDB(idpartner, idservicetype,
                    idprovider, channel, result, msglog, idmarker, description);

        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idbillpaid
     * @return
     * @throws Exception
     */
    public int deletePaybillBillPaid(String idbillpaid) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.deletePaybillBillPaid(idbillpaid);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idbillpaid
     * @param idbill
     * @param idcustomer
     * @param custname
     * @param address
     * @param period
     * @param amount
     * @param result
     * @param description
     * @param tracenumber
     * @param markerid
     * @return
     * @throws Exception
     */
    public int insertPaybillBillPaidDetail(int idbillpaid, String idbill,
            String idcustomer, String custname, String address, String period,
            long amount, int result, String description, String tracenumber,
            String markerid) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertPaybillBillPaidDetail(idbillpaid, idbill,
                    idcustomer, custname, address, period, amount, result,
                    description, tracenumber, markerid);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idpartner
     * @param idservicetype
     * @return
     * @throws Exception
     */
    public ArrayList getPaybillAttributes(String idpartner, String idservicetype)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getPaybillAttributes(idpartner, idservicetype);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idbillpaid
     * @return
     * @throws Exception
     */
    public ArrayList getPaybillBillPaid(String idbillpaid) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getPaybillBillPaid(idbillpaid);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idbillpaid
     * @return
     * @throws Exception
     */
    public ArrayList getPaybillBillPaidDetail(String idbillpaid)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getPaybillBillPaidDetail(idbillpaid);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param ref_fcubs
     * @param idbillpaid
     * @return
     * @throws Exception
     */
    public int updRefFcubsByIdbillpaid(String ref_fcubs, String idbillpaid)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updRefFcubsByIdbillpaid(ref_fcubs, idbillpaid);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getSmsAlertNotify() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getSmsAlertNotify();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idmessage
     * @param status
     * @param msgnew
     * @return
     * @throws Exception
     */
    public int updSmsAlertNotifyByIdMessage(String idmessage, String status,
            String msgnew) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updSmsAlertNotifyByIdMessage(idmessage, status,
                    msgnew);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idchanneluser
     * @return
     * @throws Exception
     */
    public ArrayList getChannelUserByIdChannelUser(String idchanneluser)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getChannelUserByIdChannelUser(idchanneluser);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param iduser
     * @param id_entity
     * @param idchannel
     * @return
     * @throws Exception
     */
    public ArrayList getListUserRoleByIduser(String iduser, String id_entity, String idchannel) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getListUserRoleByIduser(iduser, id_entity, idchannel);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            if (conn != null) {
                conn.close();
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idchanneluser
     * @return
     * @throws Exception
     */
    public ArrayList getUserByIdChannelUser(String idchanneluser)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getUserByIdChannelUser(idchanneluser);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idchanneluser
     * @param firstname
     * @return
     * @throws Exception
     */
    public ArrayList searchUser(String idchanneluser, String firstname)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchUser(idchanneluser, firstname);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param accounttd
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchCustAlertTd(String accounttd) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchCustAlertTd(accounttd);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws Exception
     */
    public int insertAlertAccountTd(SmsCustAlertTd smscustalerttd)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertAlertAccountTd(smscustalerttd);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param accounttd
     * @param cif
     * @param idcard
     * @return
     * @throws Exception
     */
    public ArrayList<?> findListAccountTD(String accounttd, String cif,
            String idcard) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.findListAccountTD(accounttd, cif, idcard);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param accounttd
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountTDByAccountTd(String accounttd)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAccountTDByAccountTd(accounttd);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id_entity
     * @param iduser
     * @param idchanneluser
     * @param idchannel
     * @return
     * @throws Exception
     */
    public int deleteIdChanneluser_UserPin2(String id_entity, String iduser,
            String idchanneluser, String idchannel) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.deleteIdChanneluser_UserPin2(id_entity, iduser,
                    idchanneluser, idchannel);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountTDById(SmsCustAlertTd smscustalerttd)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAccountTDById(smscustalerttd);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws Exception
     */
    public int updateAccountTDMobileById(SmsCustAlertTd smscustalerttd)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateAccountTDMobileById(smscustalerttd);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cust_no
     * @param registertype
     * @return
     * @throws Exception
     */
    public ArrayList<?> findHistoryListAccountTD(String cust_no,
            String registertype) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.findHistoryListAccountTD(cust_no, registertype);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cust_no
     * @param registertype
     * @param idcard
     * @return
     * @throws Exception
     */
    public ArrayList<?> findHistoryListAccountTD(String cust_no,
            String registertype, String idcard) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.findHistoryListAccountTD(cust_no, registertype, idcard);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param mstchanneluser
     * @return
     * @throws Exception
     */
    public int changePasswordEBES(EtsMstchanneluser mstchanneluser)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.changePasswordEBES(mstchanneluser);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idalert
     * @param mobile
     * @param message
     * @return
     * @throws Exception
     */
    public int insertSmsSenderLog(String idalert, String mobile, String message)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertSmsSenderLog(idalert, mobile, message);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idservicetype
     * @return
     * @throws Exception
     */
    public ArrayList<?> getProviderByIdServiceType(String idservicetype)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getProviderByIdServiceType(idservicetype);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param ppsid
     * @return
     * @throws Exception
     */
    public PblPartnerservice getPartnerServiceById(PblPartnerserviceId ppsid) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        PblPartnerservice r = smsscbdao.getPartnerServiceById(ppsid);
        PblPartnerservice result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @param pb
     * @return
     * @throws Exception
     */
    public int insertPaybillBillPaid(PblBillpaid pb) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        int i = smsscbdao.insertPaybillBillPaid(pb);
        commitTransaction();
        return i;
    }

    /**
     *
     * @param pb
     * @throws Exception
     */
    public void updatePaybillBillPaid(PblBillpaid pb) throws Exception {
        update(pb);
    }

    //duytxa08072015
    /**
     *
     * @param pb
     * @throws Exception
     */
    public void updateSmsThuPhi(SmsThuphi pb) throws Exception {
        update(pb);
    }
    //endduytxa08072015

    /**
     *
     * @param id
     * @return
     */
    public PblBillpaid getPaybillBillPaidById(int id) {
        beginTransaction();
        PblBillpaid r = (PblBillpaid) getSession().get(PblBillpaid.class, id);
        if (r == null) {
            return null;
        }
        PblBillpaid pbp = (PblBillpaid) HibernateUtil.getMapper().map(r, PblBillpaid.class);
        commitTransaction();
        return pbp;
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList getPaybillInfoById(String id) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getPaybillInfoById(id);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pblautoreg
     * @return
     * @throws Exception
     */
    public List<PblAutoReg> searchAutoReg(PblAutoReg pblautoreg) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        List<PblAutoReg> r = smsscbdao.searchAutoReg(pblautoreg);
        List result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @param vwautoreg
     * @return
     * @throws Exception
     */
    public List<VwAutoReg> searchAutoReg(VwAutoReg vwautoreg) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        List<VwAutoReg> r = smsscbdao.searchAutoReg(vwautoreg);
        List result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public PblAutoReg searchAutoRegById(int id) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        PblAutoReg r = smsscbdao.searchAutoRegById(id);
        PblAutoReg result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @return @throws Exception
     */
    public int getIdSeqAutoRegContract() throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        int id = smsscbdao.getIdSeqAutoRegContract();
        commitTransaction();
        return id;
    }

    /**
     *
     * @param idcontract
     * @return
     * @throws Exception
     */
    public int execApproveAutoReg(String idcontract) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.execApproveAutoReg(idcontract);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    //************* KET THUC AUTO REG ******************
    /**
     *
     * @param branchcode
     * @return
     * @throws Exception
     */
    public List getListTransActByBranchCode(String branchcode) throws Exception {
        beginTransaction();
        //smsscbdao.setSession(getSession());
        //List<VwWaitapproveBranch> r = smsscbdao.getListTransActByBranchCode(branchcode);
        List<VwWaitapproveBranch> r = getSession().createCriteria(VwWaitapproveBranch.class).add(Restrictions.eq("branchCodeInit", branchcode)).list();
        List result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @param transcode
     * @param branchcode
     * @return
     * @throws Exception
     */
    public List getListTransActDetail(String transcode, String branchcode) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        List r = smsscbdao.getListTransActDetail(transcode, branchcode);
        List result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @param date
     * @return
     * @throws Exception
     */
    public List getListBillPaidByDate(String date) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        List r = smsscbdao.getListBillPaidByDate(date);
        List result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public VwCustAccount getCustAccountByAccountNo(String accountno)
            throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        VwCustAccount r = smsscbdao.getCustAccountByAccountNo(accountno);
        VwCustAccount result = null;
        if (r != null) {
            result = HibernateUtil.getMapper().map(r, r.getClass());
        }
        commitTransaction();
        return result;
    }

    public VwCustAccountNew getCustAccountByAccountNoNew(String accountno)
            throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        VwCustAccountNew r = smsscbdao.getCustAccountByAccountNoNew(accountno);
        VwCustAccountNew result = null;
        if (r != null) {
            result = HibernateUtil.getMapper().map(r, r.getClass());
        }
        commitTransaction();
        return result;
    }

    /**
     *
     * @param vub
     * @return
     * @throws Exception
     */
    public List getUserBranch(VwUserbranch vub) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        List r = smsscbdao.getUserBranch(vub);
        List result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @param customercode
     * @param refpartner
     * @return
     * @throws Exception
     */
    public List getPblBillPaidByRefPartner(String customercode, String refpartner) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        List r = smsscbdao.getPblBillPaidByRefPartner(customercode, refpartner);
        List result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    //===========  PBL EBANK PROCESS ===========
    /**
     *
     * @param pep
     * @return
     * @throws Exception
     */
    public int insEbkProcess(PblEbkProcess pep) throws Exception {
        int i = (Integer) create(pep);
        return i;
    }

    /**
     *
     * @param pep
     * @throws Exception
     */
    public void updEbkProcess(PblEbkProcess pep) throws Exception {
        update(pep);
    }

    /**
     *
     * @param pct
     * @return
     * @throws Exception
     */
    public int insPblCustTemplate(PblCustTemplate pct) throws Exception {
        int i = (Integer) create(pct);
        return i;
    }

    /**
     *
     * @param pct
     * @throws Exception
     */
    public void delPblCustTemplate(PblCustTemplate pct) throws Exception {
        delete(pct);
    }

    /**
     *
     * @param iduser
     * @param idservicetype
     * @param idprovider
     * @return
     * @throws Exception
     */
    public List getPblCustTemplates(String iduser, String idservicetype, String idprovider) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        List r = smsscbdao.getPblCustTemplates(iduser, idservicetype, idprovider);
        commitTransaction();
        return r;
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public PblCustTemplate getPblCustTemplate(int id) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        PblCustTemplate r = smsscbdao.getPblCustTemplate(id);
        PblCustTemplate result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @param transcode
     * @return
     * @throws Exception
     */
    public PblEbkProcess getEbkProcessById(int transcode) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        PblEbkProcess r = smsscbdao.getEbkProcessById(transcode);
        PblEbkProcess result = r;
        commitTransaction();
        return result;
    }

    /**
     *
     * @param idscreen
     * @return
     * @throws Exception
     */
    public PblEbkScreen getEbkScreenById(int idscreen) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        PblEbkScreen r = smsscbdao.getEbkScreenById(idscreen);
        PblEbkScreen result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @param iduser
     * @return
     * @throws Exception
     */
    public VwMstchanneluser getVwMstchanneluser(String iduser) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        VwMstchanneluser r = smsscbdao.getVwMstchanneluser(iduser);
        VwMstchanneluser result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @param idscreen
     * @return
     * @throws Exception
     */
    public PblEbkScreen getEbkScreenById_new(PblEbkScreenId idscreen) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        PblEbkScreen r = smsscbdao.getEbkScreenById_new(idscreen);
        PblEbkScreen result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @param pIduser
     * @param pCust_ac_no
     * @return
     */
    public int checkIduserAndCust_ac_no(String pIduser, String pCust_ac_no) {
        beginTransaction();
        smsscbdao.setSession(getSession());
        int r = smsscbdao.checkIduserAndCust_ac_no(pIduser, pCust_ac_no);
        commitTransaction();
        return r;
    }

    /**
     *
     * @param ipaddress
     * @param pIduser
     * @param pIdsession
     * @return
     */
    public int checkusersession_valid(String ipaddress, String pIduser, String pIdsession) {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            int rs = smsscbdao.checkusersession_valid(ipaddress, pIduser, pIdsession);
            return rs;
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }

        }
        return -1;
    }

    /**
     *
     * @param pIdsession
     * @return
     * @throws Exception
     */
    public String getsession_lang(String pIdsession) throws Exception {

        beginTransaction();
        smsscbdao.setSession(getSession());
        String r = smsscbdao.getsession_lang(pIdsession);
        commitTransaction();
        return r;

    }

    /**
     *
     * @param idUser
     * @param udfName
     * @return
     * @throws Exception
     */
    public String getUdfValue_User(String idUser, String udfName) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        String r = smsscbdao.getUdfValue_User(idUser, udfName);
        commitTransaction();
        return r;
    }

    /**
     *
     * @param idUser
     * @return
     */
    public String GetTypeConfirm_User(String idUser) {
        beginTransaction();
        smsscbdao.setSession(getSession());
        String r = smsscbdao.GetTypeConfirm_User(idUser);
        commitTransaction();
        return r;
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getPblPartnerservices() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getPblPartnerservices();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getPblEbkScreens() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getPblEbkScreens();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    //===========  KET THUC PBL EBANK PROCESS ============
    /**
     *
     * @param pl
     * @return
     * @throws Exception
     */
    public int insPblLog(PblLog pl) throws Exception {
        return (Integer) create(pl);
    }

    /**
     *
     * @param table
     * @param field
     * @param idkey
     * @return
     */
    public String getmsglog(String table, String field, String idkey) {
        beginTransaction();
        smsscbdao.setSession(getSession());
        String r = smsscbdao.getmsglog(table, field, idkey);
        commitTransaction();
        return r;
    }

    /**
     *
     * @param iduser
     * @return
     * @throws Exception
     */
    public String getuserphonenumber(String iduser) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        String r = smsscbdao.getuserphonenumber(iduser);
        commitTransaction();
        return r;
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getListUserToNeedChgPwd() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getListUserToNeedChgPwd();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cif
     * @return
     * @throws Exception
     */
    public ArrayList getBranchInfoByCif(String cif) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getBranchInfoByCif(cif);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param p_entity
     * @param p_channel_id
     * @param p_txn_id
     * @param p_iduser
     * @param p_typeuser
     * @param p_txn_date
     * @param p_amount
     * @param p_ccy
     * @param p_cum
     * @return
     * @throws Exception
     */
    public boolean check_trans_limit(String p_entity, String p_channel_id, String p_txn_id, String p_iduser, String p_typeuser, Date p_txn_date, String p_amount, String p_ccy, int p_cum) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            boolean r = smsscbdao.check_trans_limit(p_entity, p_channel_id, p_txn_id, p_iduser, p_typeuser, p_txn_date, p_amount, p_ccy, p_cum);
            return r;
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    /**
     *
     * @param idcontract
     * @return
     * @throws Exception
     */
    public boolean isExsistContractByIdContract(String idcontract) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.isExsistContractByIdContract(idcontract);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getBranchByAccno(String accountno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getBranchByAccno(accountno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getlist_telefirstnumber() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getlist_telefirstnumber();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param vgroup_id
     * @return
     * @throws Exception
     */
    public ArrayList getlist_parainfo(String vgroup_id) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getlist_parainfo(vgroup_id);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * *****************************SEND
     * SMS*************************************
     */
    /**
     * Tim danh sach cac mau tin chua duoc goi den khach hang
     *
     * @param vstatus
     * @param numtrysend
     * @return
     * @throws java.lang.Exception
     */
    public List findsmssenders(char vstatus, long numtrysend) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        List r = smsscbdao.findsmssenders(vstatus, numtrysend);
        List result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return r;
    }

    /**
     *
     * @param smsobj
     * @throws RemoteException
     */
    public void upd_smssender(SmsSender smsobj) throws RemoteException {
        update(smsobj);
    }

    /**
     *
     * @param smsobj
     * @return
     * @throws RemoteException
     */
    public int ins_smssender(SmsSender smsobj) throws RemoteException {
        int i = (Integer) create(smsobj);
        return i;
    }

    /**
     *
     * @param smsobj
     * @throws RemoteException
     */
    public void upd_smspartner(SmsPartner smsobj) throws RemoteException {
        update(smsobj);
    }

    /**
     * ******************************************************************
     */
    /**
     *******************************************************************
     * BEGIN OF TIET KIEM TICH LUY
     *
     * @param accounttichluy
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<?> searchCustAlertTichLuy(String accounttichluy) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchCustAlertTichLuy(accounttichluy);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param smscustalerttichluy
     * @return
     * @throws Exception
     */
    public int insertAlertAccountTichLuy(SmsCustAlertTichLuy smscustalerttichluy)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertAlertAccountTichLuy(smscustalerttichluy);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param accounttichluy
     * @param cif
     * @param idcard
     * @return
     * @throws Exception
     */
    public ArrayList<?> findListAccountTichLuy(String accounttichluy, String cif,
            String idcard) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.findListAccountTichLuy(accounttichluy, cif, idcard);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param accounttichluy
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountTDByAccountTichLuy(String accounttichluy)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAccountTDByAccountTichLuy(accounttichluy);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param smscustalerttichluy
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountTichLuyById(SmsCustAlertTichLuy smscustalerttichluy)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAccountTichLuyById(smscustalerttichluy);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param smscustalerttichluy
     * @return
     * @throws Exception
     */
    public int updateAccountTichLuyMobileById(SmsCustAlertTichLuy smscustalerttichluy)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateAccountTichLuyMobileById(smscustalerttichluy);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cust_no
     * @param registertype
     * @return
     * @throws Exception
     */
    public ArrayList<?> findHistoryListAccountTichLuy(String cust_no,
            String registertype) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.findHistoryListAccountTichLuy(cust_no, registertype);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *******************************************************************
     * END OF TIET KIEM TICH LUY
     */
    /**
     *******************************************************************
     * BEGIN OF PAYOO PAYMENT
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @param billInfo
     * @return
     * @throws java.lang.Exception
     */
    public int insertPbl_PayooLog(String customercode, String serviceID, String providerID,
            String billInfo) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertPbl_PayooLog(customercode, serviceID, providerID, billInfo);
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @return
     * @throws Exception
     */
    public ArrayList<?> getPbl_PayooLog(String customercode, String serviceID, String providerID) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getPbl_PayooLog(customercode, serviceID, providerID);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @return
     * @throws Exception
     */
    public ArrayList<?> getPbl_PayooLogPrint(String customercode, String serviceID, String providerID) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getPbl_PayooLogPrint(customercode, serviceID, providerID);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @return
     * @throws Exception
     */
    public String getBillInfo(String customercode, String serviceID, String providerID) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getBillInfo(customercode, serviceID, providerID);
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     *
     * @param seqname
     * @return
     * @throws Exception
     */
    public int getIdSeqByName(String seqname) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getIdSeqByName_2(seqname);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *******************************************************************
     * END OF PAYOO PAYMENT
     *
     * @param Trace_No
     * @param PartnerDatetime
     * @param Amount
     * @param Order_No
     * @param TypeOfTrans
     * @param Note
     * @param PayooDatetime
     * @param Status
     * @param CheckSum
     * @return
     * @throws java.lang.Exception
     */
    //PAYOO COLLATE
    //UPDATE BY: HIEUDT     
    public int insert_PayooCollateData(String Trace_No, String Order_No, BigDecimal Amount,
            Date PartnerDatetime, Date PayooDatetime, String TypeOfTrans, String Status,
            String Note, String CheckSum) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insert_PayooCollateData(Trace_No, Order_No, Amount,
                    PartnerDatetime, PayooDatetime, TypeOfTrans, Status,
                    Note, CheckSum);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param date
     * @return
     * @throws Exception
     */
    public int CollatePayooBillData(String date) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.CollatePayooBillData(date);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param date
     * @return
     * @throws Exception
     */
    public boolean isExsistPayooBillCollateData(String date) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.isExsistPayooBillCollateData(date);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    //END OF PAYOO COLLATE   

    //VNPay Collated by Yen Ly
    //Begin VNPAY COLLATE
    //Begin VNPAY COLLATE
    /**
     *
     * @param o
     * @throws Exception
     */
    public void InsertPBL_VNPAY_COLLATED(PblCollated_VNPAY o) throws Exception {
        conn = super.getConnection();
        smsscbdao.setConnection(conn);
        smsscbdao.InsertPBL_VNPAY_COLLATED(o);
    }

    /**
     *
     * @param pParnertid
     * @param pTransDate
     * @throws Exception
     */
    public void UPDATE_AFTER_COLLATED(String pParnertid, Date pTransDate) throws Exception {
        conn = super.getConnection();
        smsscbdao.setConnection(conn);
        smsscbdao.UPDATE_AFTER_COLLATED(pParnertid, pTransDate);
    }

    /**
     *
     * @param datecollate
     * @param filename
     * @throws Exception
     */
    public void INSERT_DATE_COLLATED(Date datecollate, String filename) throws Exception {
        conn = super.getConnection();
        smsscbdao.setConnection(conn);
        smsscbdao.INSERT_DATE_COLLATED(datecollate, filename);
    }

    /**
     *
     * @return @throws Exception
     */
    public Date GET_VNPAY_COLLATEDATE() throws Exception {
        conn = super.getConnection();
        smsscbdao.setConnection(conn);
        return smsscbdao.GET_VNPAY_COLLATEDATE();
    }

    //VNPay Collated by Yen Ly
    /**
     * ************mobile banking
     *
     **********************
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList getAllListServiceTypeNotTopup() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListServiceTypeNotTopup();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws Exception
     */
    public int updateAccountAlertStatusById(SmsCustAlertTd smscustalerttd)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateAccountAlertStatusById(smscustalerttd);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param customercode
     * @param idchannel
     * @param idservicetype
     * @param statusBill
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param idpartner
     * @param idtransaction
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList searchPaybill(String customercode, String idchannel, String idservicetype, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction, String beginRow, String endRow)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchPaybill(customercode, idchannel, idservicetype, statusBill, branchcode, fromdate, todate, idpartner, idtransaction, beginRow, endRow);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param customercode
     * @param idchannel
     * @param idservicetype
     * @param statusBill
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param idpartner
     * @param idtransaction
     * @return
     * @throws Exception
     */
    public ArrayList searchPaybillAll(String customercode, String idchannel, String idservicetype, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchPaybillAll(customercode, idchannel, idservicetype, statusBill, branchcode, fromdate, todate, idpartner, idtransaction);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param channel
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList searchPaybillDiary(String channel, String fromdate, String todate)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchPaybillDiary(channel, fromdate, todate);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param txnType
     * @param idlist
     * @param status
     * @param userid
     * @param idChanneluser
     * @return
     * @throws Exception
     */
    public int updateStatusPaybill(String txnType, String idlist, String status, String userid, String idChanneluser) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateStatusPaybill(txnType, idlist, status, userid, idChanneluser);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param partner
     * @param customercode
     * @param statusBill
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param idpartner
     * @param idtransaction
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList searchOnlBill(String id, String partner, String customercode, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction, String beginRow, String endRow)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchOnlBill(id, partner, customercode, statusBill, branchcode, fromdate, todate, idpartner, idtransaction, beginRow, endRow);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param partner
     * @param customercode
     * @param statusBill
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param idpartner
     * @param idtransaction
     * @return
     * @throws Exception
     */
    public ArrayList searchOnlBillAll(String id, String partner, String customercode, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchOnlBillAll(id, partner, customercode, statusBill, branchcode, fromdate, todate, idpartner, idtransaction);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    //duytxa08072015 
    /**
     * ************thu phi sms tu dong
     *
     **********************
     * @param id
     * @return
     */
    public SmsThuphi getSmsThuphiById(String id) {
        beginTransaction();
        SmsThuphi r = (SmsThuphi) getSession().get(SmsThuphi.class, id);
        if (r == null) {
            return null;
        }
        SmsThuphi pbp = (SmsThuphi) HibernateUtil.getMapper().map(r, SmsThuphi.class);
        commitTransaction();
        return pbp;
    }

    /**
     *
     * @param mobile
     * @param custno
     * @return
     * @throws Exception
     */
    public int lockSMS(String mobile, String custno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.lockSMS(mobile, custno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    //endduytxa08072015

    //duytxa28062017
    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public int lockEbankService(String custno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.lockEbankService(custno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    //LAY DS USER MB THEO CIF DE KHOA DV
    /**
     *
     * @param custno
     * @param thangnam
     * @return
     * @throws Exception
     */
    public ArrayList<?> getmobileuserthuphisms(String custno, String thangnam) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getmobileuserthuphisms(custno, thangnam);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    //endduytxa28062017
    /**
     * ************end thu phi sms tu dong
     *
     **********************
     * @param ID
     * @param SRCACCOUNT
     * @param CARD_BRN
     * @param PAN_LOC
     * @param AMOUNT
     * @param EXP_DATE
     * @param CCY
     * @param TRANS_STATUS
     * @param REF_FCC
     * @param REF_CW
     * @param SRCCHANNEL
     * @return
     * @throws java.lang.Exception
     */
    public int insertCardReloadTrans(String ID, String SRCACCOUNT, String PAN_LOC,
            String CARD_BRN, String CCY, String EXP_DATE, Double AMOUNT, String REF_FCC,
            String REF_CW, String TRANS_STATUS, String SRCCHANNEL)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertCardReloadTrans(ID, SRCACCOUNT, PAN_LOC,
                    CARD_BRN, CCY, EXP_DATE, AMOUNT, REF_FCC,
                    REF_CW, TRANS_STATUS, SRCCHANNEL);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    //Long.Le edit updateCardReloadTrans
    /**
     *
     * @param ID
     * @param REF_FCC
     * @param REF_CW
     * @param TRANS_STATUS
     * @return
     * @throws Exception
     */
    public int updateCardReloadTrans(String ID, String REF_FCC, String REF_CW, String TRANS_STATUS) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateCardReloadTrans(ID, REF_FCC, REF_CW, TRANS_STATUS);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    //Cardword WS 

    //LongLe (S)
    /**
     *
     * @param idschool
     * @return
     * @throws Exception
     */
    public String getAccountProvider(String idschool) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAccountProvider(idschool);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param status
     * @return
     * @throws Exception
     */
    public ArrayList getSMSFitcom(int status) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getSMSFitcom(status);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idBillPaid
     * @param partnerDetailId
     * @param partnerId
     * @param accountTo
     * @return
     * @throws Exception
     */
    public int insertBillPaidDetail(int idBillPaid, String partnerDetailId,
            String partnerId, String accountTo) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertBillPaidDetail(idBillPaid, partnerDetailId,
                    partnerId, accountTo);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatusSMSFitcom(int id, int status) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateStatusSMSFitcom(id, status);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param mobile
     * @param content
     * @param serviceType
     * @param serviceCode
     * @return
     * @throws Exception
     */
    public int insertSMS(int id, String mobile, String content, String serviceType, String serviceCode) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertSMS(id, mobile, content, serviceType, serviceCode);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param mobile
     * @param content
     * @param serviceType
     * @param user
     * @return
     * @throws Exception
     */
    public int insertSmsSendLog(int id, String mobile, String content, String serviceType, String user) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertSmsSendLog(id, mobile, content, serviceType, user);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idmsg
     * @param mobile
     * @param content
     * @param idfile
     * @param typemessage
     * @return
     * @throws Exception
     */
    public int insertFileDetail(int idmsg, String mobile, String content, int idfile, String typemessage) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertFileDetail(idmsg, mobile, content, idfile, typemessage);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param fromDateSearch
     * @param toDateSearch
     * @param statusfile
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchFileSMS(String fromDateSearch, String toDateSearch, String statusfile) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchFileSMS(fromDateSearch, toDateSearch, statusfile);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList<?> getFileDetail(String id) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getFileDetail(id);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param messagetype
     * @param filename
     * @param iduser
     * @param desc
     * @return
     * @throws Exception
     */
    public int uploadfileSMS(int id, String messagetype, String filename, String iduser, String desc) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.uploadfileSMS(id, messagetype, filename, iduser, desc);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idfile
     * @param isapproved
     * @param iduser
     * @return
     * @throws Exception
     */
    public int approveFileSMS(int idfile, String isapproved, String iduser) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.approveFileSMS(idfile, isapproved, iduser);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idfile
     * @param isapproved
     * @return
     * @throws Exception
     */
    public int updateStatusSMS(int idfile, String isapproved) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateStatusSMS(idfile, isapproved);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idmsg
     * @return
     * @throws Exception
     */
    public int updateSendMSG(int idmsg) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateSendMSG(idmsg);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idfile
     * @param send_flag
     * @param iduser
     * @return
     * @throws Exception
     */
    public int updateSendFile(int idfile, String send_flag, String iduser) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateSendFile(idfile, send_flag, iduser);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pPartnerID
     * @return
     * @throws Exception
     */
    public String CreateTranscode(String pPartnerID) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.CreateTranscode(pPartnerID);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    //Modify CustAlertTD
    /**
     *
     * @param cust_no
     * @return
     * @throws Exception
     */
    public int writelogSMSAlertTD(String cust_no) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.writelogSMSAlertTD(cust_no);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    //Modify CustAlertTD

    /*Ð?i soát Napas (S)*/
    /**
     *
     * @param np
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public int InsertNapasEcomCollated(NapasCollate np) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.InsertNapasEcomCollated(np);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param ID
     * @param pCoreref
     * @param pStatus
     * @throws Exception
     */
    public void UpdateRefundTransferNAPAS(String ID, String pCoreref, String pStatus) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            smsscbdao.UpdateRefundTransferNAPAS(ID, pCoreref, pStatus);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pPartnerID
     * @param pMerchanID
     * @param pTransID
     * @param pRefundTransID
     * @param pRefundAmount
     * @param pCCY
     * @param pADDINFO
     * @param pLocalDatetime
     * @return
     * @throws Exception
     */
    public String[] CheckRefundTransferBanknet(String pPartnerID, String pMerchanID,
            String pTransID, String pRefundTransID,
            BigDecimal pRefundAmount, String pCCY, String pADDINFO, String pLocalDatetime) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.CheckRefundTransferBanknet(pPartnerID, pMerchanID, pTransID, pRefundTransID, pRefundAmount, pCCY, pADDINFO, pLocalDatetime);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws SQLException
     * @throws RemoteException
     */
    public List<NapasCollate> getOutNapasEcomCollate() throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getOutNapasEcomCollate();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*Ð?i soát Napas (E)*/

 /*Mua Bao hiem (S)*/
    /**
     *
     * @param ins
     * @return
     * @throws RemoteException
     */
    public int insertInsuranceInfo(Insurance ins) throws RemoteException {
        beginTransaction();
        smsscbdao.setSession(getSession());
        int i = smsscbdao.insertInsuranceInfo(ins);
        commitTransaction();
        return i;
    }

    /**
     *
     * @param ins
     * @throws Exception
     */
    public void updateStatusInsurance(Insurance ins) throws Exception {
        update(ins);
    }

    /**
     *
     * @param hour
     * @return
     * @throws Exception
     */
    public ArrayList<?> GetlistInsuranceToExport(int hour) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.GetlistInsuranceToExport(hour);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*Mua Bao hiem (E)*/

 /*Mua the cao (S)*/
    /**
     *
     * @param bc
     * @return
     * @throws RemoteException
     */
    public int insertBuyCard(RTBuyCardDTO bc) throws RemoteException {
        beginTransaction();
        smsscbdao.setSession(getSession());
        int i = smsscbdao.insertBuyCard(bc);
        commitTransaction();
        return i;
    }

    /**
     *
     * @param cd
     * @return
     * @throws RemoteException
     */
    public int insertBuyCardDetail(RTCardDetailDTO cd) throws RemoteException {
        beginTransaction();
        smsscbdao.setSession(getSession());
        int i = smsscbdao.insertBuyCardDetail(cd);
        commitTransaction();
        return i;
    }

    /**
     *
     * @param bc
     * @throws Exception
     */
    public void updateStatusBuyCard(RTBuyCardDTO bc) throws Exception {
        update(bc);
    }

    /*Mua the cao (E)*/

 /*Thanh toan Bao hiem (S)*/
    /**
     *
     * @param payins
     * @return
     * @throws RemoteException
     */
    public int insertPayIns(InsurancePayment payins) throws RemoteException {
        beginTransaction();
        smsscbdao.setSession(getSession());
        int i = smsscbdao.insertPayIns(payins);
        commitTransaction();
        return i;
    }

    /**
     *
     * @param payins
     * @throws Exception
     */
    public void updateStatusPayIns(InsurancePayment payins) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            smsscbdao.updateStatusPayIns(payins);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param amount
     * @throws Exception
     */
    public void updateStatusBCBill(int id, long amount) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            smsscbdao.updateStatusBCBill(id, amount);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*Thanh toan Bao hiem (E)*/

 /*Thanh toan du no the tin dung tai quay (S)*/
    /**
     *
     * @param id
     * @param cif
     * @param loc
     * @param cardtype
     * @param cardno
     * @param expi_date
     * @param cardname
     * @param branch_card
     * @param sotien_dtt
     * @param sotien_min
     * @param sotien_sk
     * @param sotien_duno
     * @param sotien_tt
     * @param paymentmethod
     * @param custAcNo
     * @param fullname
     * @param address
     * @param custNo
     * @param iduser_marker
     * @param idchanneluser_maker
     * @param isapproved
     * @param branchcode
     * @param idcard
     * @param idcardDob
     * @param idcardName
     * @param idcardAddress
     * @return
     * @throws Exception
     */
    public int insPayCreditCard(int id, String cif, String loc, String cardtype, String cardno, String expi_date, String cardname, String branch_card, BigDecimal sotien_dtt,
            BigDecimal sotien_min, BigDecimal sotien_sk, BigDecimal sotien_duno, BigDecimal sotien_tt, String paymentmethod, String custAcNo, String fullname, String address,
            String custNo, String iduser_marker, String idchanneluser_maker, String isapproved, String branchcode, String idcard, String idcardDob, String idcardName, String idcardAddress) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insPayCreditCard(id, cif, loc, cardtype, cardno, expi_date, cardname, branch_card, sotien_dtt,
                    sotien_min, sotien_sk, sotien_duno, sotien_tt, paymentmethod, custAcNo, fullname, address, custNo,
                    iduser_marker, idchanneluser_maker, isapproved, branchcode, idcard, idcardDob, idcardName, idcardAddress);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int insPayCreditCardv2(int id, String cif, String loc, String cardtype, String cardno, String expi_date, String cardname, String branch_card, BigDecimal sotien_dtt,
            BigDecimal sotien_min, BigDecimal sotien_sk, BigDecimal sotien_duno, BigDecimal sotien_tt, String paymentmethod, String custAcNo, String fullname, String address,
            String custNo, String iduser_marker, String idchanneluser_maker, String isapproved, String branchcode, String idcard, String idcardDob, String idcardName, String idcardAddress, String sdtKh, String noiCapCMND) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insPayCreditCardv2(id, cif, loc, cardtype, cardno, expi_date, cardname, branch_card, sotien_dtt,
                    sotien_min, sotien_sk, sotien_duno, sotien_tt, paymentmethod, custAcNo, fullname, address, custNo,
                    iduser_marker, idchanneluser_maker, isapproved, branchcode, idcard, idcardDob, idcardName, idcardAddress, sdtKh, noiCapCMND);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param glAcc
     * @param amount
     * @param content
     * @param idcardName
     * @param idcardAddress
     * @param idcard
     * @param idcardDob
     * @param user_maker
     * @param user_checker
     * @param branchcard
     * @return
     * @throws Exception
     */
    public String transferFCUBS(String glAcc, BigDecimal amount, String content, String idcardName, String idcardAddress, String idcard, String idcardDob, String user_maker, String user_checker, String branchcard) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.transferFCUBS(glAcc, amount, content, idcardName, idcardAddress, idcard, idcardDob, user_maker, user_checker, branchcard);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList getPayCCInfoByID(int id) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getPayCCInfoByID(id);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cif
     * @param loc
     * @param statuscode
     * @param branchcode
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchPayCCAll(String cif, String loc, String statuscode, String branchcode, String fromdate, String todate) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchPayCCAll(cif, loc, statuscode, branchcode, fromdate, todate);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cif
     * @param loc
     * @param statuscode
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList searchPayCreditCard(String cif, String loc, String statuscode, String branchcode, String fromdate, String todate, String beginRow, String endRow) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchPayCreditCard(cif, loc, statuscode, branchcode, fromdate, todate, beginRow, endRow);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param iduser_checker
     * @param idchanneluser_checker
     * @param isapproved
     * @param ref_fcubs
     * @param status
     * @param id
     * @param so_ct
     * @throws Exception
     */
    public void updatePayCC(String iduser_checker, String idchanneluser_checker, String isapproved, String ref_fcubs, String status, int id, String so_ct) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            smsscbdao.updatePayCC(iduser_checker, idchanneluser_checker, isapproved, ref_fcubs, status, id, so_ct);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*Thanh toan du no the tin dung tai quay (E)*/
    //Long.Le (E)
    //Contact Center
    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GET_EMAIL_HIST(String param) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.CC_GET_EMAIL_HIST(param);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    //End of Contact Center
    /**
     *
     * @param serialno
     * @return
     * @throws Exception
     */
    public ArrayList getValidToken(String serialno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getValidToken(serialno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idservicetype
     * @param idprodvider
     * @param customercode
     * @param CIFNO
     * @param UserName
     * @param Mobile
     * @param DebitAccount
     * @param CardAccountNumer
     * @param CardNo
     * @param PaymentType
     * @return
     * @throws Exception
     */
    public ProcedureCallDto RegisterAutoBill(String idservicetype, String idprodvider, String customercode, String CIFNO, String UserName, String Mobile, String DebitAccount, String CardAccountNumer, String CardNo, String PaymentType) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            if("VNPTBILL".equals(idservicetype) && "VNP".equals(idprodvider))
            {
                return smsscbdao.RegisterAutoBill_vnpt(idservicetype, idprodvider, customercode, CIFNO, UserName, Mobile, DebitAccount, CardAccountNumer, CardNo,PaymentType);
            } else {
                return smsscbdao.RegisterAutoBill(idservicetype, idprodvider, customercode, CIFNO, UserName, Mobile, DebitAccount, CardAccountNumer, CardNo);
            }
            
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param phoneNumber
     * @return
     * @throws Exception
     */
    public String getKMFromPhoneNumber(String phoneNumber) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getKMFromPhoneNumber(phoneNumber);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cifno
     * @return
     * @throws Exception
     */
    public List<ThuPhiSMS> selectDSThuPhiEbank(String cifno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.selectDSThuPhiEbank(cifno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cifno
     * @param refcore
     * @param usermaker
     * @param userchecker
     * @param paydate
     * @return
     * @throws Exception
     */
    public ProcedureCallDto UnlockUserThuPhi(String cifno, String refcore, String usermaker, String userchecker, Date paydate) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            ProcedureCallDto procedto = smsscbdao.UnlockUserThuPhi(cifno, refcore, usermaker, userchecker, paydate);
            return procedto;
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param feeTrans
     * @return
     * @throws Exception
     */
    public int insertFeeTransactionUnlock(FeeTransactionUnlock feeTrans) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        int i = smsscbdao.insertFeeTransactionUnlock(feeTrans);
        commitTransaction();
        return i;
    }

    /**
     *
     * @param feeTrans
     * @throws Exception
     */
    public void updateFeeTransactionUnlock(FeeTransactionUnlock feeTrans) throws Exception {
        update(feeTrans);
    }

    /**
     *
     * @param id
     * @return
     */
    public FeeTransactionUnlock getFeeTransactionUnlockId(int id) {
        beginTransaction();
        FeeTransactionUnlock r = (FeeTransactionUnlock) getSession().get(FeeTransactionUnlock.class, id);
        if (r == null) {
            return null;
        }
        FeeTransactionUnlock feeTrans = (FeeTransactionUnlock) HibernateUtil.getMapper().map(r, FeeTransactionUnlock.class);
        commitTransaction();
        return feeTrans;
    }

    /**
     *
     * @param feeTrans
     * @return
     * @throws Exception
     */
    public List<FeeTransactionUnlock> getFeeTransactionUnlockList(FeeTransactionUnlock feeTrans) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        List r = smsscbdao.getFeeTransactionUnlockList(feeTrans);
        List result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @param cifno
     * @return
     * @throws Exception
     */
    public ProcedureCallDto getAccThuPhi(String cifno) throws Exception {
        conn = super.getConnection();
        smsscbdao.setConnection(conn);
        ProcedureCallDto procedto = smsscbdao.getAccThuPhi(cifno);
        return procedto;
    }

    /**
     *
     * @param cifno
     * @param refcore
     * @param paydate
     * @return
     * @throws Exception
     */
    public ProcedureCallDto updateThuphi(String cifno, String refcore, Date paydate) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            ProcedureCallDto procedto = smsscbdao.updateThuphi(cifno, refcore, paydate);
            return procedto;
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param P_IDSERVICETYPE
     * @param P_IDPROVIDER
     * @param cusCode
     * @return
     * @throws Exception
     */
    public ProcedureCallDto getListBill(String P_IDSERVICETYPE, String P_IDPROVIDER, String cusCode) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getListBill(P_IDSERVICETYPE, P_IDPROVIDER, cusCode);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param servType
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListPartner(String servType) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getListPartner(servType);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList searchTBTBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchTBTBill(id, idtransaction, partner, customercode, statusBill, fromdate, todate, beginRow, endRow);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatusTBT(String id, String status) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateStatusTBT(id, status);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList searchTBTBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchTBTBillAll(id, idtransaction, partner, customercode, statusBill, fromdate, todate);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList searchIBLBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchIBLBill(id, idtransaction, partner, customercode, statusBill, fromdate, todate, beginRow, endRow);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList searchTVSIBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchTVSIBill(id, idtransaction, partner, customercode, statusBill, fromdate, todate, beginRow, endRow);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatusTVSI(String id, String status) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateStatusTVSI(id, status);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList searchTVSIBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchTVSIBillAll(id, idtransaction, partner, customercode, statusBill, fromdate, todate);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList searchTVSI_Bill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchTVSI_Bill(id, idtransaction, partner, customercode, statusBill, fromdate, todate, beginRow, endRow);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatus_TVSI(String id, String status) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateStatus_TVSI(id, status);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList searchTVSI_BillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchTVSI_BillAll(id, idtransaction, partner, customercode, statusBill, fromdate, todate);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatusIBL(String id, String status) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateStatusIBL(id, status);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList searchIBLBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchIBLBillAll(id, idtransaction, partner, customercode, statusBill, fromdate, todate);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList searchVTCBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchVTCBill(id, idtransaction, partner, customercode, statusBill, fromdate, todate, beginRow, endRow);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatusVTC(String id, String status) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateStatusVTC(id, status);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList searchVTCBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchVTCBillAll(id, idtransaction, partner, customercode, statusBill, fromdate, todate);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*getAllListInsurancePayment*/
    /**
     *
     * @return @throws Exception
     */
    public ArrayList getAllListInsurancePayment() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListInsurancePayment();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*getAllListIns*/
    /**
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public ArrayList getAllListIns(String sql) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListIns(sql);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getAllListpRoviderIns() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllListpRoviderIns();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList getInsByidpayment(int id) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getInsByidpayment(id);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getAllPartnerMNL() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAllPartnerMNL();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pol
     * @param due
     * @param pre
     * @return
     * @throws Exception
     */
    public ArrayList getBcBilId(String pol, String due, String pre) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getBcBilId(pol, due, pre);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param fromDateSearch
     * @param toDateSearch
     * @return
     * @throws Exception
     */
    public ArrayList getlistAllInsPayment(String fromDateSearch, String toDateSearch) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getlistAllInsPayment(fromDateSearch, toDateSearch);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param bcbill
     * @return
     * @throws Exception
     */
    public int insertBCBill(BCBill bcbill) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        int i = smsscbdao.insertBCBill(bcbill);
        commitTransaction();
        return i;
    }

    /**
     *
     * @param bcbill
     * @return
     * @throws Exception
     */
    public int insertBCBill(List<BCBill> bcbill) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertBCBill(bcbill);
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public int insertBCBillHistory() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertBCBillHistory();
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public int insertIStatusNo() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertIStatusNo();
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public int insertIStatusYes() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertIStatusYes();
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public int deleteBCBill() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.deleteBCBill();
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }
    }

    /**
     *
     * @param status
     * @param idbillpaid
     * @return
     * @throws Exception
     */
    public int updStatusByIdbillpaid(String status, String idbillpaid)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updStatusByIdbillpaid(status, idbillpaid);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getOnlTranStatus() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getOnlTranStatus();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getRetailtatus() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getRetailtatus();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getInsStatus() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getInsStatus();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getSiStatus() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getSiStatus();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getSmlStatus() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getSmlStatus();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList getInsurencePayByID(int id) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getInsurencePayByID(id);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param dataxml
     * @param idbillpaid
     * @return
     * @throws Exception
     */
    public int updDataxmlByIdbillpaid(String dataxml, String idbillpaid)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updDataxmlByIdbillpaid(dataxml, idbillpaid);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pbt
     * @return
     * @throws Exception
     */
    public int insertPblBillpaidTransReverse(PblBillpaidTransReverse pbt) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        int i = smsscbdao.insertPblBillpaidTransReverse(pbt);
        commitTransaction();
        return i;
    }

    /**
     *
     * @param id
     * @return
     */
    public PblBillpaidTransReverse getPblBillpaidTransReverseById(int id) {
        beginTransaction();
        PblBillpaidTransReverse r = (PblBillpaidTransReverse) getSession().get(PblBillpaidTransReverse.class, id);
        if (r == null) {
            return null;
        }
        PblBillpaidTransReverse pbp = (PblBillpaidTransReverse) HibernateUtil.getMapper().map(r, PblBillpaidTransReverse.class);
        commitTransaction();
        return pbp;
    }

    /**
     *
     * @param pb
     * @throws Exception
     */
    public void updatePblBillpaidTransReverse(PblBillpaidTransReverse pb) throws Exception {
        update(pb);
    }

    /**
     *
     * @param id
     * @param polnum
     * @param isapproved
     * @param status
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList searchMNLBill(String id, String polnum, String isapproved, String status, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchMNLBill(id, polnum, isapproved, status, customercode, statusBill, fromdate, todate, beginRow, endRow);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatusMNL(String id, String status) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateStatusMNL(id, status);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @param polnum
     * @param isapproved
     * @param status
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList searchMNLBillAll(String id, String polnum, String isapproved, String status, String customercode, String statusBill, String fromdate, String todate)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchMNLBillAll(id, polnum, isapproved, status, customercode, statusBill, fromdate, todate);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public List laydanhdachsanphambaohiem() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.laydanhdachsanphambaohiem();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param IdSanPham
     * @return
     * @throws Exception
     * @throws RemoteException
     */
    public List chitietsanphambaohiem(String IdSanPham) throws Exception, RemoteException {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.chitietsanphambaohiem(IdSanPham);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param Ownerid
     * @param Polnum
     * @return
     * @throws Exception
     * @throws RemoteException
     */
    public List thongtinkhmanulife(String Ownerid, String Polnum) throws Exception, RemoteException {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.thongtinkhmanulife(Ownerid, Polnum);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     * @throws RemoteException
     */
    public List danhsachhoadontudong(String custno) throws Exception, RemoteException {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.danhsachhoadontudong(custno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idautoreg
     * @return
     * @throws Exception
     * @throws RemoteException
     */
    public int huyhoadontudong(String idautoreg) throws Exception, RemoteException {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.huyhoadontudong(idautoreg);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cifno
     * @return
     * @throws Exception
     * @throws RemoteException
     */
    public List GetListInsuranceByCif(String cifno) throws Exception, RemoteException {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.GetListInsuranceByCif(cifno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Insurance GetInsuranceById(int id) {
        beginTransaction();
        Insurance r = (Insurance) getSession().get(Insurance.class, id);
        if (r == null) {
            return null;
        }
        Insurance ins = (Insurance) HibernateUtil.getMapper().map(r, Insurance.class);
        commitTransaction();
        return ins;
    }

    /**
     *
     * @param ID
     * @param SRCACCOUNT
     * @param PAN_LOC
     * @param CARD_BRN
     * @param CCY
     * @param EXP_DATE
     * @param AMOUNT
     * @param REF_FCC
     * @param REF_CW
     * @param TRANS_STATUS
     * @param SRCCHANNEL
     * @param debtPay
     * @param debtCur
     * @param IPPPayType
     * @return
     * @throws Exception
     */
    public int insertCardIPPTrans(String ID, String SRCACCOUNT, String PAN_LOC,
            String CARD_BRN, String CCY, String EXP_DATE, Double AMOUNT, String REF_FCC,
            String REF_CW, String TRANS_STATUS, String SRCCHANNEL, BigDecimal debtPay, BigDecimal debtCur, String IPPPayType)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertCardIPPTrans(ID, SRCACCOUNT, PAN_LOC,
                    CARD_BRN, CCY, EXP_DATE, AMOUNT, REF_FCC,
                    REF_CW, TRANS_STATUS, SRCCHANNEL, debtPay, debtCur, IPPPayType);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    //Long.Le edit updateCardReloadTrans
    /**
     *
     * @param ID
     * @param REF_FCC
     * @param REF_CW
     * @param TRANS_STATUS
     * @return
     * @throws Exception
     */
    public int updateCardIPPTrans(String ID, String REF_FCC, String REF_CW, String TRANS_STATUS) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateCardIPPTrans(ID, REF_FCC, REF_CW, TRANS_STATUS);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListCWWS_CARD_IPP(int id) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getListCWWS_CARD_IPP(id);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public int CHECK_LASTMONTH_DATE() throws Exception {
        conn = super.getConnection();
        smsscbdao.setConnection(conn);
        return smsscbdao.CHECK_LASTMONTH_DATE();
    }

    /**
     *
     * @param id
     * @param module
     * @param partner
     * @param serviceType
     * @param provider
     * @param merchant
     * @param customerCode
     * @param oldStatus
     * @param newStatus
     * @param userUpd
     * @throws Exception
     */
    public void updateChangeStatusPaybill(String id, String module, String partner,
            String serviceType, String provider, String merchant,
            String customerCode, String oldStatus, String newStatus,
            String userUpd) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            smsscbdao.updateChangeStatusPaybill(id, module, partner, serviceType,
                    provider, merchant, customerCode, oldStatus,
                    newStatus, userUpd);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                Helper.writeLog(SmsSCBBO.class, org.apache.log4j.Level.INFO, "Can't close connection: " + IBEx.getMessage());
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public String GET_BRANCHCODE_FROM_FCC(String accountno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.GET_BRANCHCODE_FROM_FCC(accountno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                Helper.writeLog(SmsSCBBO.class, org.apache.log4j.Level.INFO, "Can't close connection: " + IBEx.getMessage());
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* EVNHCM */
    /**
     *
     * @param partner
     * @param customerCode
     * @param totalAmount
     * @param refPartnerId
     * @param revertStatus
     * @return
     * @throws Exception
     */
    public int deletePayooBill(String partner, String customerCode, String totalAmount, String refPartnerId, String revertStatus) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.deletePayooBill(partner, customerCode, totalAmount, refPartnerId, revertStatus);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param partner
     * @param customerCode
     * @param totalAmount
     * @param refPartnerId
     * @return
     * @throws Exception
     */
    public String queryRefCustPayooBill(String partner, String customerCode, String totalAmount, String refPartnerId) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.queryRefCustPayooBill(partner, customerCode, totalAmount, refPartnerId);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws Exception
     */
    public int insertAlertAccountTd2(SmsCustAlertTd smscustalerttd)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertAlertAccountTd2(smscustalerttd);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* EVNHCM */
    //baovq - sms & tp
    /**
     *
     * @param cust_no
     * @return
     * @throws Exception
     */
    public ArrayList<?> Find_List_Cust_No(String cust_no) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.Find_List_Cust_No(cust_no);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws Exception
     */
    public int update_REF_STATUS(SmsCustAlertTd smscustalerttd)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.update_REF_STATUS(smscustalerttd);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public String[] getListAccountClassRedemEbank() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getListAccountClassRedemEbank();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param acccount
     * @return
     * @throws Exception
     */
    public ArrayList<?> GetBeneficaryName(String acccount) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.GetBeneficaryName(acccount);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }

    /**
     *
     * @param account_class
     * @return
     * @throws Exception
     */
    public ArrayList getProductAccountClass(String account_class) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getProductAccountClass(account_class);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ProcedureCallDto checkTDIsEOD(String accountno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.checkTDIsEOD(accountno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    /**
     *
     * @param cif
     * @param cardtype
     * @param groupcode
     * @return
     * @throws Exception
     */
    public ArrayList getCardList(String cif, String cardtype, String groupcode) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getCardList(cif, cardtype, groupcode);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cardaccountno
     * @param period
     * @return
     * @throws Exception
     */
    public ArrayList getMCStateDetail(String cardaccountno, String period) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getMCStateDetail(cardaccountno, period);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cardaccountno
     * @param period
     * @param srno
     * @param rownum
     * @return
     * @throws Exception
     */
    public ArrayList getCCStatement(String cardaccountno, String period, String srno, String rownum) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getCCStatement(cardaccountno, period, srno, rownum);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idpartner
     * @param idservicetype
     * @param idprovider
     * @return
     * @throws Exception
     */
    public ArrayList<?> getACCNOFCUBS(String idpartner, String idservicetype, String idprovider) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getACCNOFCUBS(idpartner, idservicetype, idprovider);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param issueatm
     * @return
     * @throws Exception
     */
    public List getEbIssuecard(EbIssuecard issueatm) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        List r = smsscbdao.getEbIssuecard(issueatm);
        List result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @param issueatm
     * @return
     * @throws Exception
     */
    public ProcedureCallDto insertEbIssuecard(EbIssuecard issueatm) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        ProcedureCallDto procedto = smsscbdao.insertEbIssuecard(issueatm);
        if (commitTransaction2() == -1) {
            procedto.setErrorStatus("-1");
            procedto.setErrorMessage("Ko the commit giao dich");
        }
        return procedto;
    }

    /**
     *
     * @param issueATM
     * @throws Exception
     */
    public void updateEbIssuecard(EbIssuecard issueATM) throws Exception {
        update(issueATM);
    }

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public List GetRedemptionAzListByCustNo(String custno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.GetRedemptionAzListByCustNo(custno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param type
     * @return
     * @throws Exception
     */
    public ArrayList getCardType(String type) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getCardType(type);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param getBankCity
     * @return
     * @throws Exception
     */
    public List getBankCity(String getBankCity) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getBankCity(getBankCity);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cifno
     * @param accountno
     * @param cardaccountno
     * @param registertype
     * @param issuetype
     * @param cardtype
     * @param fee
     * @param tax
     * @return
     * @throws Exception
     */
    public int checkIssueATMCard(String cifno, String accountno, String cardaccountno, String registertype, String issuetype, String cardtype, String fee, String tax) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.checkIssueATMCard(cifno, accountno, cardaccountno, registertype, issuetype, cardtype, fee, tax);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cardaccountno
     * @return
     * @throws Exception
     */
    public String[] getPointMC(String cardaccountno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getPointMC(cardaccountno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    //dvsms - baovq

    /**
     *
     * @param smsservice
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountSMSService(SMSService smsservice)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAccountSMSService(smsservice);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param branch
     * @return
     * @throws Exception
     */
    public ArrayList<?> GET_TENNV(String branch) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.GET_TENNV(branch);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param manv
     * @return
     * @throws Exception
     */
    public ArrayList<?> QUERY_TENNV(String manv) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.QUERY_TENNV(manv);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cust_no
     * @param registertype
     * @return
     * @throws Exception
     */
    public ArrayList<?> findHistoryListSMSService(String cust_no,
            String registertype) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.findHistoryListSMSService(cust_no, registertype);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param smsservice
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountSMSSERVICEById(SMSService smsservice)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAccountSMSSERVICEById(smsservice);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    public int insertSMSServiceUser(SMSServiceUser user)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertSMSServiceUser(user);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param userrefid
     * @param status
     * @param userid
     * @throws Exception
     */
    public void approveSMSService(int userrefid, String status, String userid)
            throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            smsscbdao.approveSMSService(userrefid, status, userid);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountTK(String custno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getAccountTK(custno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListAccountTT(String custno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getListAccountTT(custno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param smsservice
     * @param iduserref
     * @param cust_no
     * @throws Exception
     */
    public void insertSMSServiceTK(SMSServiceTK smsservice, int iduserref, String cust_no) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            smsscbdao.insertSMSServiceTK(smsservice, iduserref, cust_no);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public String[] checkDKDVSMS(String custno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.checkDKDVSMS(custno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param custno
     * @param idcard
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchCustomerCore(String custno, String idcard) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchCustomerCore(custno, idcard);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList<?> get_data_approve(String id) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.get_data_approve(id);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param smsuser
     * @return
     * @throws Exception
     */
    public int update_REF_STATUS_SMSTK(SMSServiceUser smsuser) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.update_REF_STATUS_SMSTK(smsuser);

        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cust_no
     * @return
     * @throws Exception
     */
    public ArrayList<?> CHECK_POINT_REWARD_THU(String cust_no) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.CHECK_POINT_REWARD_THU(cust_no);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cust_no
     * @return
     * @throws Exception
     */
    public ArrayList<?> CHECK_POINT_REWARD_CHI(String cust_no) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.CHECK_POINT_REWARD_CHI(cust_no);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param bankCode
     * @return
     * @throws Exception
     */
    public List getBeneBank(String bankCode) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getBeneBank(bankCode);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cust_no
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListAccountByCif(String cust_no) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getListAccountByCif(cust_no);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param unique_name
     * @param unique_id
     * @return
     * @throws Exception
     */
    public ArrayList getCustomerInfoForMB(String unique_name, String unique_id) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getCustomerInfoForMB(unique_name, unique_id);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param staffcode
     * @return
     * @throws Exception
     */
    public String GetStaffDetail(String staffcode) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.GetStaffDetail(staffcode);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getGoldRate() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getGoldRate();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getExchangeRate() throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getExchangeRate();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param ccy
     * @return
     * @throws Exception
     */
    public ArrayList getInterestRate(String ccy) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getInterestRate(ccy);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param custno
     * @param custaccno
     * @param amount
     * @return
     * @throws Exception
     */
    public ProcedureCallDto checkFeeMobile(String custno, String custaccno, String amount) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.checkFeeMobile(custno, custaccno, amount);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cust_no
     * @return
     * @throws Exception
     */
    public ArrayList<?> CHECK_POINT_REWARD_TC(String cust_no) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.CHECK_POINT_REWARD_TC(cust_no);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getCLSCHEDULEINTPAID(String accountno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getCLSCHEDULEINTPAID(accountno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getCLSCHEDULEINTUNPAID(String accountno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getCLSCHEDULEINTUNPAID(accountno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getCLSCHEDULEPRIPAIDSCB(String accountno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getCLSCHEDULEPRIPAIDSCB(accountno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getCLSCHEDULEPRIUNPAIDSCB(String accountno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getCLSCHEDULEPRIUNPAIDSCB(accountno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getCardnoByAccountno(String accountno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getCardnoByAccountno(accountno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param emailTd
     * @return
     * @throws Exception
     */
    public BigDecimal insertGwEmailTd(GwEmailTd emailTd) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        BigDecimal i = smsscbdao.insertGwEmailTd(emailTd);
        commitTransaction();
        return i;
    }

    /**
     *
     * @param emailTd
     * @param fromDate
     * @param toDate
     * @return
     * @throws Exception
     */
    public List<GwEmailTd> getListGwEmailTd(GwEmailTd emailTd, String fromDate, String toDate) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        List<GwEmailTd> r = smsscbdao.getListGwEmailTd(emailTd, fromDate, toDate);
        List result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
    }

    /**
     *
     * @param emailTd
     * @throws Exception
     */
    public void updateGwEmailTd(GwEmailTd emailTd) throws Exception {
        update(emailTd);
    }

    //son 01/nov/2019
    /**
     *
     * @param ID
     * @return
     * @throws Exception
     */
    public int DELETE_INSURANCE_PAYMENT_BO(String ID) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.DELETE_INSURANCE_PAYMENT_Dao(ID);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    //son 04/nov/2019
    /**
     *
     * @param ID
     * @return
     * @throws Exception
     */
    public int DELETE_PAYMENT_CREDITCARD_BO(String ID) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.DELETE_PAYMENT_CREDITCARD_Dao(ID);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param tdLuck
     * @return
     * @throws Exception
     */
    public String insertGwTdLuck(GwTdLucky tdLuck) throws Exception {
        beginTransaction();
        smsscbdao.setSession(getSession());
        smsscbdao.insertGwTdLuck(tdLuck);
        commitTransaction();
        return tdLuck.getCustAcNo();
    }

    /**
     *
     * @param tdLuck
     * @throws Exception
     */
    public void updateGwTdLuck(GwTdLucky tdLuck) throws Exception {
        update(tdLuck);
    }

    public ArrayList searchSMSAlert(String cifno, String mobileno) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.searchSMSAlert(cifno, mobileno);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<RegisterInfoDetail> getListRegisterDetail(String isChecked) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getListRegisterDetail(isChecked);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int updateRegisterId(String isChecked, String registerId, String idChecker, String idChannelChecker) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateRegisterId(isChecked, registerId, idChecker, idChannelChecker);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String[] INSERT_SMS_PARTNER(sms_partner_request request) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.INSERT_SMS_PARTNER(request);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getOnlTranPartnersById(String idPartner) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getOnlTranPartnersById(idPartner);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList getOnlPaymentByCard(String idTrans) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getOnlPaymentByCard(idTrans);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int insertUserOtherDeviceOdbx(UserOtherDeviceOdbxDto userDeviceOdbx) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertUserOtherDeviceOdbx(userDeviceOdbx);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int updateGrantUserOtherDevice(UserOtherDeviceOdbxDto userDeviceOdbx) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateGrantUserOtherDevice(userDeviceOdbx);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList getUserOtherDeviceOdbxById(String id) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getUserOtherDeviceOdbxById(id);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList checkOnOffUserDeviceOdbxApproved(String userName, String soCif, String approved) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.checkOnOffUserDeviceOdbxApproved(userName, soCif, approved);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList GetPaymentCardRegisteredByCif(String soCif) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.GetPaymentCardRegisteredByCif(soCif);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int insertPaymentCardTracking(PaymentByCardTracking paymentByCardtracking) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertPaymentCardTracking(paymentByCardtracking);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int updatePaymentCardTracking(String paymentTracking, String userChecker, String approved) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updatePaymentCardTracking(paymentTracking, userChecker, approved);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int updatePaymentByCardRegister(String payment, String status) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updatePaymentByCardRegister(payment, status);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList checkPaymentCardApproved(String id, String approved) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.checkPaymentCardApproved(id, approved);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList getPaymentCardCancelById(String id) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getPaymentCardCancelById(id);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    public String getCifFromAccountNo(String accountNo) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getCifFromAccountNo(accountNo);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*Baotbq - huychungtu*/
    public int insertNtdtPaymentExtend(NtdtPaymentExtend ntdtPaymentExtend) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.insertNtdtPaymentExtend(ntdtPaymentExtend);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int updateNtdtPaymentExtend(String paymentExtend, String userChecker, String approved) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.updateNtdtPaymentExtend(paymentExtend, userChecker, approved);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int checkNtdtPaymentApproved(String id, String approved) throws SQLException, Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.checkNtdtPaymentApproved(id, approved);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList getNtdtPaymentExtendById(String id) throws SQLException, Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.getNtdtPaymentExtendById(id);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public ArrayList<?> CollatedKieuHoiByEKYC(String partnerId, String status, String dateCollated) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            return smsscbdao.CollatedKieuHoiByEKYC(partnerId, status, dateCollated);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }   
    
    public ArrayList CollatedKieuHoiByDate(String partnerId, String fromDate, String toDate) throws Exception {
        try {
            conn = super.getConnection();
            smsscbdao.setConnection(conn);
            
            return smsscbdao.CollatedKieuHoiByDate(partnerId, fromDate, toDate);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }   
}
