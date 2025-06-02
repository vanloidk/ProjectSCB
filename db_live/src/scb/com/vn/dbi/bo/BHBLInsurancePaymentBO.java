/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dao.BHBLInsurancePaymentDAO;
import scb.com.vn.dbi.dto.BHBLCstContractCollectedByTKResp;
import scb.com.vn.dbi.dto.BHBLCstContractCollectedByTheResp;
import scb.com.vn.dbi.dto.BHBLGoiPhiHDResp;
import scb.com.vn.dbi.dto.BHBLHDAndGoiPhiResp;
import scb.com.vn.dbi.dto.BHBLHealthCareIns;
import scb.com.vn.dbi.dto.BHBLHealthCareInsRq;
import scb.com.vn.dbi.dto.BHBLMetadataRes;
import scb.com.vn.dbi.dto.BHBLPackageCostResp;
import scb.com.vn.dbi.dto.BHBLPaymentContractInfoRq;
import scb.com.vn.dbi.dto.BHBLQuestionResp;
import scb.com.vn.dbi.utility.HibernateUtil;

/**
 *
 * @author loinv3
 */
public class BHBLInsurancePaymentBO extends BaseSMSSCBBO {

    private static final Logger LOGGER = Logger.getLogger(BHBLInsurancePaymentBO.class);
    private Connection conn;
    private BHBLInsurancePaymentDAO dao = null;

    /**
     * Create a new instance of PBL_BillPaidBO
     *
     * @throws java.lang.Exception
     */
    public BHBLInsurancePaymentBO() throws Exception {
        dao = new BHBLInsurancePaymentDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactorySmsScb());
    }

    /**
     * InsertAnswerRs
     *
     * @param ques
     * @param IdContract
     * @return
     * @throws SQLException
     */
    private boolean insertAnswerRs(BHBLHealthCareInsRq.Questions ques, Long IdContract) throws SQLException {
        
        conn = super.getConnection();
        dao.setConnection(conn);
        boolean isInsert = false;
        isInsert = dao.insertAnswerRs(ques, IdContract);

        return isInsert;
    }

    /**
     * InsertHealthInsBHBL
     *
     * @param payins
     * @param ques
     * @return
     * @throws java.sql.SQLException
     * @throws Exception
     */
    public long insertHealthInsBHBL(BHBLHealthCareIns payins, BHBLHealthCareInsRq.Questions ques) throws SQLException, Exception {

        long id = -1;
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            id = dao.insertHealthInsBHBL01(payins);

            //insert anwser
            if (id != -1) {
                this.insertAnswerRs(ques, id);
            }

        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqlEx) {
                LOGGER.error(sqlEx);
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return id;
    }

    /**
     * updatePaymentStatus
     *
     * @param payins
     * @return int
     * @throws Exception
     */
    public String updatePaymentStatus(BHBLPaymentContractInfoRq payins) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            String i = dao.updatePaymentStatus(payins);
            conn.commit();

            return i;
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqlEx) {
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
     * @param healthCareIns
     * @return int
     * @throws Exception
     */
    public long updateHealthInsBHBL(BHBLHealthCareIns healthCareIns) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            long idContract = dao.updateHealthInsBHBL(healthCareIns);
            conn.commit();

            return idContract;
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqlEx) {
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
     * GetBLPackageCost
     *
     * @param tuoi
     * @param gioiTinh
     * @return
     * @throws Exception
     */
    public List<BHBLPackageCostResp> getBLPackageCost(int tuoi, int gioiTinh, String lang) throws Exception {

        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getBLPackageCost(tuoi, gioiTinh, lang);

        } catch (SQLException IBEx) {
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
     * getBLCategories
     *
     * @param type
     * @return
     * @throws Exception
     */
    public List<BHBLMetadataRes> getBLCategories(String type) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getBLCategories(type);

        } catch (SQLException IBEx) {
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
     * GetBLAllQuestions
     *
     * @return
     * @throws Exception
     */
    public List<BHBLQuestionResp> getBLAllQuestions(String lang) throws Exception {

        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getBLAllQuestions(lang);

        } catch (SQLException IBEx) {
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
     * CollectedCustConTractByTK
     *
     * @return
     * @throws Exception
     */
    public List<BHBLCstContractCollectedByTKResp> collectedCustConTractByTK() throws Exception {

        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.collectedCustConTractByTK();

        } catch (SQLException IBEx) {
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
     * CollectedCustConTractByThe
     *
     * @return
     * @throws Exception
     */
    public List<BHBLCstContractCollectedByTheResp> collectedCustConTractByThe() throws Exception {

        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.collectedCustConTractByThe();

        } catch (SQLException IBEx) {
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
     * GetGoiPhiHD
     *
     * @return
     * @throws Exception
     */
    public List<BHBLGoiPhiHDResp> getGoiPhiHD() throws Exception {

        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getGoiPhiHD();

        } catch (SQLException IBEx) {
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
     * GetAllHDAndGoiPhi
     *
     * @return
     * @throws Exception
     */
    public List<BHBLHDAndGoiPhiResp> getAllHDAndGoiPhi() throws Exception {

        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getAllHDAndGoiPhi();

        } catch (SQLException IBEx) {
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

}
