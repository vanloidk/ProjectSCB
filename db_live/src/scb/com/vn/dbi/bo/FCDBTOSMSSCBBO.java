/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import scb.com.vn.dbi.dao.FCDBTOSMSSCBDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dto.EbIssuecard;
import scb.com.vn.dbi.dto.ProcedureCallDto;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.dbi.utility.HibernateUtil;

/**
 *
 * @author lydty
 */
public class FCDBTOSMSSCBBO extends BaseSMSSCBBO {

    private static final Logger LOGGER = Logger.getLogger(FCDBTOSMSSCBBO.class);
    private Connection conn;
    private FCDBTOSMSSCBDAO dao = null;

    /**
     * Create a new instance of FCDBTOSMSSCBBO
     * @throws java.lang.Exception
     */
    public FCDBTOSMSSCBBO() throws Exception {
        dao = new FCDBTOSMSSCBDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactorySmsScb());
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
            dao.setConnection(conn);
            return dao.getCustomerInfoFCC(idcustomer);
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
     * @param premtyp
     * @param polNum
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListDataPay(String premtyp, String polNum) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getListDataPay(premtyp, polNum);
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
     * @param ownerId
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListPolNum(String ownerId) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getListPolNum(ownerId);
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
     * @param polNum
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListPremtyp(String polNum) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getListPremtyp(polNum);
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
    public ArrayList getCardInfoByID(String id) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getCardInfoByID(id);
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
            dao.setConnection(conn);
            return dao.getListCard(cif, loc);
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
            dao.setConnection(conn);
            return dao.searchRegisterinfoAll(idchannel, status, branchcode, registerType, fromdate, todate);
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
            dao.setConnection(conn);
            return dao.searchRegisterinfo(idchannel, status, branchcode, registerType, fromdate, todate, beginRow, endRow);
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
            dao.setConnection(conn);
            return dao.updateRegisterinfo(idlist, status, userid, idChanneluser);
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
     * @param issueatm
     * @return
     * @throws Exception
     */
    public List getEbIssuecard(EbIssuecard issueatm) throws Exception {
        beginTransaction();
        conn = super.getConnection();
        dao.setSession(getSession());
        List r = dao.getEbIssuecard(issueatm);
        List result = HibernateUtil.getMapper().map(r, r.getClass());
        commitTransaction();
        return result;
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
            dao.setConnection(conn);
            return dao.getCardType(type);
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
     * @param issueatm
     * @return
     * @throws Exception
     */
    public ProcedureCallDto insertEbIssuecard(EbIssuecard issueatm) throws Exception {
        beginTransaction();
        conn = super.getConnection();
        dao.setConnection(conn);
        ProcedureCallDto procedto = dao.insertEbIssuecard(issueatm);
        if (commitTransaction2() == -1) {
            procedto.setErrorStatus("-1");
            procedto.setErrorMessage("Ko the commit giao dich");
        }
        return procedto;
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
            dao.setConnection(conn);
            return dao.findCustomer(cif, idcard);
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
     * @param accountno
     * @return
     */
    public VwCustAccount getCustAccountFcdbByAccountNo(String accountno) {
        beginTransaction();
        dao.setSession(getSession());
        VwCustAccount r = dao.getCustAccountFcdbByAccountNo(accountno);
        if (r == null) {
            commitTransaction();
            return null;
        }
        commitTransaction();
        return r;
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getListBranch() throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getListBranch();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            return null;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

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
            dao.setConnection(conn);
            return dao.getAccountCASAMaxbalanceFeesms(custno, acctype);
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
            dao.setConnection(conn);
            return dao.getAccountCASAMaxbalanceFeesmsKHDN(custno, acctype);
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
     * @param issueATM
     * @throws Exception
     */
    public void updateEbIssuecard(EbIssuecard issueATM) throws Exception {
        update(issueATM);
    }
}
