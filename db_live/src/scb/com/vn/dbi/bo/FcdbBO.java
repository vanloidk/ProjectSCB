package scb.com.vn.dbi.bo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dao.FcdbDAO;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.dbi.utility.HibernateUtil;
import scb.com.vn.dbi.dto.ProcedureCallDto;

/**
 *
 * @author minhndb
 */
public class FcdbBO extends BaseFCDBBO {

    private static final Logger LOGGER = Logger.getLogger(FcdbBO.class);

    private Connection conn;
    private FcdbDAO fcdbDAO = null;

    /**
     * Create a new instance of FcdbBO
     * @throws java.lang.Exception
     */
    public FcdbBO() throws Exception {
        fcdbDAO = new FcdbDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactoryFcdb());
    }

    /**
     *
     * @param acccount
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountCASA(String acccount) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getAccountCASA(acccount);
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
    //duytxa 07/09/2015 for feeautosms

    /**
     *
     * @param custno
     * @param acctype
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountCASAMaxbalanceFeesms(String custno, String acctype) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getAccountCASAMaxbalanceFeesms(custno, acctype);
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
    //endduytxa 07/09/2015 for feeautosms

    //duytxa 20/06/2017 for feeautosms
    /**
     *
     * @param custno
     * @param acctype
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountCASAMaxbalanceFeesmsKHDN(String custno, String acctype) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getAccountCASAMaxbalanceFeesmsKHDN(custno, acctype);
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
    //endduytxa 20/06/2017 for feeautosms

    /**
     *
     * @param acccount
     * @param rownum
     * @return
     * @throws Exception
     */
    public ArrayList<?> getDetailsAccountCASA(String acccount, int rownum) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getDetailsAccountCASA(acccount, rownum);
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
     * @param acccountTD
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountTD(String acccountTD) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getAccountTD(acccountTD);
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
     * @param accounttd
     * @param cif
     * @param idcard
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListAccountTDDetail(String accounttd, String cif, String idcard) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getListAccountTDDetail(accounttd, cif, idcard);
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
     * @param ccyfrom
     * @return
     * @throws Exception
     */
    public ArrayList<?> getFXRates(String ccyfrom) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getFXRates(ccyfrom);
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
     * @param mobile
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCustomerSMSByMobile(String mobile) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getCustomerSMSByMobile(mobile);
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
     * @param idEntity
     * @param idChannel
     * @param idChanneluser
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCustomerInfo(String idEntity, String idChannel, String idChanneluser) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getCustomerInfo(idEntity, idChannel, idChanneluser);
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
     * @param idEntity
     * @param idChanneluser
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCustomerInfoByIdChannelUser(String idEntity, String idChanneluser) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getCustomerInfoByIdChannelUser(idEntity, idChanneluser);
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
     * @param idEntity
     * @param userid
     * @param idChannel
     * @param idChanneluser
     * @param newpassword_encrypted
     * @return
     * @throws Exception
     */
    public int changePassword(String idEntity, String userid, String idChannel, String idChanneluser,
            String newpassword_encrypted) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.changePassword(idEntity, userid, idChannel, idChanneluser, newpassword_encrypted);
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
     * @param idEntity
     * @param userid
     * @param idChannel
     * @param idChanneluser
     * @param newpassword_encrypted
     * @param flagforcechangepwd
     * @return
     * @throws Exception
     */
    public int changePasswordWithFlagForce(String idEntity, String userid, String idChannel, String idChanneluser,
            String newpassword_encrypted, String flagforcechangepwd) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.changePasswordWithFlagForce(idEntity, userid, idChannel, idChanneluser, newpassword_encrypted, flagforcechangepwd);
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
     * @param identity
     * @param idchannel
     * @param iduser
     * @param usertype
     * @param idtxn
     * @return
     * @throws Exception
     */
    public ArrayList<?> getRoleTxnByTxnCode(String identity, String idchannel, String iduser, String usertype,
            String idtxn) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getRoleTxnByTxnCode(identity, idchannel, iduser, usertype, idtxn);
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
     * @param identity
     * @param idchannel
     * @param iduser
     * @param usertype
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAllRoleTxn(String identity, String idchannel, String iduser, String usertype)
            throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getAllRoleTxn(identity, idchannel, iduser, usertype);
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
     * @param idcustomer
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCustomerInfoFCC(String idcustomer) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getCustomerInfoFCC(idcustomer);
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
    public ArrayList<?> getUserAdmin() throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getUserAdmin();
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
     * @param phonenum
     * @return
     * @throws Exception
     */
    public ArrayList<?> getLastestUserByPhoneNum(String phonenum) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getLastestUserByPhoneNum(phonenum);
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
     * @param idchannel
     * @param iduser
     * @return
     * @throws Exception
     */
    public ArrayList<?> isHaveRoleFTByIduser(String idchannel, String iduser) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.isHaveRoleFTByIduser(idchannel, iduser);
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
     * @param account
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCustInfoIBByAccount(String account) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getCustInfoIBByAccount(account);
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
     * @param operativeacctno
     * @param operativebrncode
     * @param id_entity
     * @param usertype
     * @param iduser
     * @return
     * @throws Exception
     */
    public int updateOperativeAccount(String operativeacctno, String operativebrncode, String id_entity,
            String usertype, String iduser) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.updateOperativeAccount(operativeacctno, operativebrncode, id_entity, usertype, iduser);
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
     * @param kyhan
     * @return
     * @throws Exception
     */
    public ArrayList<?> getTermDepositRate(String kyhan) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getTermDepositRate(kyhan);
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
    public ArrayList<?> getListBranch() throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getListBranch();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            IBEx.printStackTrace();
            return null;
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
     */
    public VwCustAccount getCustAccountFcdbByAccountNo(String accountno) {
        beginTransaction();
        fcdbDAO.setSession(getSession());
        VwCustAccount r = fcdbDAO.getCustAccountFcdbByAccountNo(accountno);
        if (r == null) {
            commitTransaction();
            return null;
        }
        commitTransaction();
        return r;
    }

    /**
     *
     * @param account
     * @return
     * @throws Exception
     */
    public ArrayList getAccountBranch(String account) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getAccountBranch(account);
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
     * @param iduser
     * @param custaccno
     * @return
     * @throws Exception
     */
    public boolean isExistsAccCasaByIduserAccno(String iduser, String custaccno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.isExistsAccCasaByIduserAccno(iduser, custaccno);
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
     * @param iduser
     * @param custaccno
     * @return
     * @throws Exception
     */
    public boolean isExistsAccTDByIduserAccno(String iduser, String custaccno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.isExistsAccTDByIduserAccno(iduser, custaccno);
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
     * @param iduser
     * @param accountloan
     * @return
     * @throws Exception
     */
    public boolean isExistsAccLoanByCifLoan(String iduser, String accountloan) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.isExistsAccLoanByCifLoan(iduser, accountloan);
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
     * @param accountloan
     * @return
     * @throws Exception
     */
    public List getInfoAccountLoan(String accountloan) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getInfoAccountLoan(accountloan);
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

    //Contact center
    /**
     *
     * @param CIF
     * @return
     * @throws Exception
     */
    public String CC_IB_CUST_INFO(String CIF) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.CC_IB_CUST_INFO(CIF);
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
     * @param CIF
     * @return
     * @throws Exception
     */
    public String CC_SMS_CUST_INFO(String CIF) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.CC_SMS_CUST_INFO(CIF);
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
     * @param CIF
     * @return
     * @throws Exception
     */
    public String CC_SMSALERT_CUST_INFO(String CIF) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.CC_SMSALERT_CUST_INFO(CIF);
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
     * @param CIF
     * @return
     * @throws Exception
     */
    public String CC_SMS_ALERT_TD_INFO(String CIF) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.CC_SMS_ALERT_TD_INFO(CIF);
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
     * @param CIF
     * @return
     * @throws Exception
     */
    public String CC_CUST_MBANKING_INFO(String CIF) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.CC_CUST_MBANKING_INFO(CIF);
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
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GET_SMS_HIST(String param) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.CC_GET_SMS_HIST(param);
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

    //end of contact center        
    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public List GetAccountListByCustNo(String custno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.GetAccountListByCustNo(custno);
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
    public List getCASAccountHasLimitMB(String accountno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getCASAccountHasLimitMB(accountno);
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
    public List getTdAccountByAccountNo(String accountno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getTdAccountByAccountNo(accountno);
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
    public ArrayList getLoanAccountByAccountNo(String accountno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getLoanAccountByAccountNo(accountno);
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
     * @param unique_name
     * @param unique_id
     * @return
     * @throws Exception
     */
    public ArrayList getCustomerInfoForMB(String unique_name, String unique_id) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getCustomerInfoForMB(unique_name, unique_id);
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
     * @param fromDate
     * @param toDate
     * @param srno
     * @return
     * @throws Exception
     */
    public ArrayList getTransationListByAccountNo(String accountno, String fromDate, String toDate, String srno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getTransationListByAccountNo(accountno, fromDate, toDate, srno);
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
    public ArrayList getTDTransationListByAccountNo(String accountno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getTDTransationListByAccountNo(accountno);
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
     * @param bankCode
     * @return
     * @throws Exception
     */
    public List getBeneBank(String bankCode) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getBeneBank(bankCode);
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
     * @param getBankCity
     * @return
     * @throws Exception
     */
    public List getBankCity(String getBankCity) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getBankCity(getBankCity);
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
     * @param branchcode
     * @param accountclass
     * @param amount
     * @param ccy
     * @return
     * @throws Exception
     */
    public ArrayList getAccountClassInfo(String branchcode, String accountclass, String amount, String ccy) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getAccountClassInfo(branchcode, accountclass, amount, ccy);
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
     * @param cardno
     * @return
     * @throws Exception
     */
    public ArrayList getMaterCardDetailByCardno(String cardno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getMaterCardDetailByCardno(cardno);
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
     * @param cardno
     * @return
     * @throws Exception
     */
    public ArrayList getTransactionMaterCardByCardno(String cardno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getTransactionMaterCardByCardno(cardno);
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
     * @return
     * @throws Exception
     */
    public ArrayList getTokenbyCustno(String custno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getTokenbyCustno(custno);
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
     * @param serialno
     * @return
     * @throws Exception
     */
    public ArrayList checkTokenbySerialno(String serialno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.checkTokenbySerialno(serialno);
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
    public ArrayList checkAmountBeforePay(String accountno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.checkAmountBeforePay(accountno);
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
     * @param account_class
     * @return
     * @throws Exception
     */
    public ArrayList getProductAccountClass(String account_class) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getProductAccountClass(account_class);
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
    public List GetTDAccountBeforeRedemtion(String accountno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.GetTDAccountBeforeRedemtion(accountno);
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
    public ArrayList getSttmCustAccountSyn(String acccount) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getSttmCustAccountSyn(acccount);
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
     * @param custno
     * @return
     * @throws Exception
     */
    public List GetTemplateFromFCDB(String custno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.GetTemplateFromFCDB(custno);
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
     * @return
     * @throws Exception
     */
    public ArrayList getBeneficiaryFromFCDB(String custno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getBeneficiaryFromFCDB(custno);
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
     * @param cif
     * @param idcard
     * @return
     * @throws Exception
     */
    public ArrayList<?> findCustomer(String cif, String idcard) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.findCustomer(cif, idcard);
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
     * @param staffcode
     * @return
     * @throws Exception
     */
    public String GetStaffDetail(String staffcode) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.GetStaffDetail(staffcode);
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
     * @param subject
     * @param content
     * @param idchannel
     * @param idchanneluser
     * @return
     * @throws Exception
     */
    public int InsertFeedback(String subject, String content, String idchannel, String idchanneluser) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.InsertFeedback(subject, content, idchannel, idchanneluser);
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
     * @param idlist
     * @param status
     * @param userid
     * @param idChanneluser
     * @return
     * @throws Exception
     */
    public int updateFeedback(String idlist, String status, String userid, String idChanneluser) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.updateFeedback(idlist, status, userid, idChanneluser);
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
     * @param idchannel
     * @param status
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList searchFeedbackAll(String idchannel, String status, String fromdate, String todate) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.searchFeedbackAll(idchannel, status, fromdate, todate);
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
     * @param idchannel
     * @param status
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList searchFeedback(String idchannel, String status, String fromdate, String todate, String beginRow, String endRow) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.searchFeedback(idchannel, status, fromdate, todate, beginRow, endRow);
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
     * @param cifno
     * @param mobileno
     * @return
     * @throws Exception
     */
    public ArrayList searchSMSAlert(String cifno, String mobileno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.searchSMSAlert(cifno, mobileno);
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
     * @param type
     * @return
     * @throws Exception
     */
    public ArrayList getCardType(String type) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getCardType(type);
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
     * @param cif
     * @param cardtype
     * @param groupcode
     * @return
     * @throws Exception
     */
    public ArrayList getCardList(String cif, String cardtype, String groupcode) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getCardList(cif, cardtype, groupcode);
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
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getMCStateDetail(cardaccountno, period);
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
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getCCStatement(cardaccountno, period, srno, rownum);
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
     * @param cardno
     * @return
     * @throws Exception
     */
    public ArrayList getMaterCardDetailByCardnoNew(String cardno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getMaterCardDetailByCardnoNew(cardno);
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
     * @param cardno
     * @return
     * @throws Exception
     */
    public ArrayList getTransactionMaterCardByCardnoNew(String cardno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getTransactionMaterCardByCardnoNew(cardno);
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
    public List GetAccountListByCustNoNew(String custno) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.GetAccountListByCustNoNew(custno);
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
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getCardnoByAccountno(accountno);
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
     * @param mobileno
     * @param active
     * @return
     * @throws Exception
     */
    public int regeisterAlertTDMB(String cifno, String mobileno, String active) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.regeisterAlertTDMB(cifno, mobileno, active);
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
     * @param idlist
     * @param status
     * @param userid
     * @param idChanneluser
     * @return
     * @throws Exception
     */
    public int updateRegisterinfo(String idlist, String status, String userid, String idChanneluser) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.updateRegisterinfo(idlist, status, userid, idChanneluser);
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
     * @param idchannel
     * @param status
     * @param branchcode
     * @param registerType
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList searchRegisterinfoAll(String idchannel, String status, String branchcode, String registerType, String fromdate, String todate) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.searchRegisterinfoAll(idchannel, status, branchcode, registerType, fromdate, todate);
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
     * @param idchannel
     * @param status
     * @param branchcode
     * @param registerType
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList searchRegisterinfo(String idchannel, String status, String branchcode, String registerType, String fromdate, String todate, String beginRow, String endRow) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.searchRegisterinfo(idchannel, status, branchcode, registerType, fromdate, todate, beginRow, endRow);
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
     * @param cif
     * @return
     * @throws Exception
     */
    public ArrayList getPrimaryATMList(String cif) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getPrimaryATMList(cif);
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
     * @param acccount
     * @return
     * @throws Exception
     */
    public ArrayList<?> GetBeneficaryName(String acccount) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.GetBeneficaryName(acccount);
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
     * @param cif
     * @param cardaccountnumber
     * @return
     * @throws Exception
     */
    public ArrayList getCreditCardList(String cif, String cardaccountnumber) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getCreditCardList(cif, cardaccountnumber);
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

    /*Thanh toán dư nợ thẻ tín dụng tại quầy (S) -- LONGLE*/
    /**
     *
     * @param cif
     * @param loc
     * @return
     * @throws Exception
     */
    public ArrayList getListCard(String cif, String loc) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getListCard(cif, loc);
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
    public ArrayList getCardInfoByID(String id) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getCardInfoByID(id);
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

    /*Thanh toán dư nợ thẻ tín dụng tại quầy (E) -- LONGLE*/
    /**
     *
     * @return @throws Exception
     */
    public String[] getListAccountClassRedemEbank() throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getListAccountClassRedemEbank();
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
     * @param branchcode
     * @param accountclass
     * @param maturedate
     * @return
     * @throws Exception
     */
    public ProcedureCallDto verifyOpenTD(String branchcode, String accountclass, String maturedate) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.verifyOpenTD(branchcode, accountclass, maturedate);
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

    /*Thanh toán phí bảo hiểm tại quầy (S) -- Thachdn*/
    /**
     *
     * @param premtyp
     * @param polNum
     * @return
     * @throws Exception
     */
    public ArrayList getListDataPay(String premtyp, String polNum) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getListDataPay(premtyp, polNum);
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

    /*Thanh toán phí bảo hiểm tại quầy (S) -- Thachdn*/
    /**
     *
     * @param ownerId
     * @return
     * @throws Exception
     */
    public ArrayList getListPolNum(String ownerId) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getListPolNum(ownerId);
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

    /*Thanh toán phí bảo hiểm tại quầy (S) -- Thachdn*/
    /**
     *
     * @param polNum
     * @return
     * @throws Exception
     */
    public ArrayList getListPremtyp(String polNum) throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getListPremtyp(polNum);
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
    public ArrayList<?> getAllListInsuranceTrn() throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getAllListInsuranceTrn();
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
            fcdbDAO.setConnection(conn);
            return fcdbDAO.checkTDIsEOD(accountno);
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
    public String[] getListJoinAccount() throws Exception {
        try {
            conn = super.getConnection();
            fcdbDAO.setConnection(conn);
            return fcdbDAO.getListJoinAccount();
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
}
