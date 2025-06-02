/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import java.util.Date;
import java.util.HashMap;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateRes;
import scb.com.vn.common.model.transfer.SenderInfo;
import scb.com.vn.common.model.transfer.TransactionDetail;
import scb.com.vn.common.model.transfer.napas.TransferMoney247DetailReq;
import scb.com.vn.common.model.transfer.napas.TransferMoney247EbankReq;
import scb.com.vn.common.model.transfer.status.TransactionDetailByDate;
import scb.com.vn.dbi.dto.SiTrffromsiExtend;
import scb.com.vn.dbi.dto.TransfersRemittance;
import scb.com.vn.model.status.transferMoney.GetStatusOfTransferMoneyReq;
import scb.com.vn.model.status.transferMoney.TransferMoneyTransactionInfo;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author lydty
 */
public class SIDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(SIDAO.class);

    final String Insert_SI_TRANFERTOSI = "BEGIN PKG_SI_UPDATE.Insert_SI_TRANFERTOSI(?,?,?,?,?,?,?,?,?,?,?); END;";
    final String CONFIRM_SI_TRANFERTOSI = "BEGIN PKG_SI_UPDATE.CONFIRM_SI_TRANFERTOSI(?,?,?,?,?,?); END;";
    final String INSERT_SI_TRANSFROMSI = "BEGIN PKG_SI_UPDATE.INSERT_SI_TRANSFROMSI(?,?,?,?,?); END;";
    final String CHECK_ACCOUNT = "BEGIN PKG_SI_UPDATE.CHECK_ACCOUNT(?,?,?,?,?,?); END;";
    final String INSERT_TRANSFERFROMSI_DETAIL = "BEGIN PKG_SI_UPDATE.INSERT_TRANSFERFROMSI_DETAIL(?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String UPDATE_TRANSFERFROMSI_DETAIL = "BEGIN PKG_SI_UPDATE.UPDATE_TRANSFERFROMSI_DETAIL(?,?,?,?); END;";
    final String GET_LISTTRANSFROMSI = "BEGIN PKG_SI_UPDATE.GET_LISTTRANSFROMSI(?,?); END;";
    final String getListBankCode = "BEGIN PKG_SI_UPDATE.getListBankCode(?); END;";
    final String InsertCollated = "BEGIN PKG_SI_UPDATE.insertcollated(?,?,?,?,?,?,?,?,?,?,?); END;";
    final String getOutCollated = "BEGIN PKG_SI_UPDATE.getOutCollated(?,?,?,?); END;";

    //ADDNEW 2018
    final String querryTransfer = "BEGIN PKG_SI_UPDATE.querryTransfer(?,?,?); END;";
    final String getListBankCode247 = "BEGIN PKG_SI_UPDATE.getListBankCode247(?); END;";
    final String InsertCollated_TCH = "BEGIN PKG_SI_UPDATE.InsertCollated_TCH(?,?,?,?,?,?,?,?,?,?,?); END;";

    /**
     *
     * @param PPARTNERID
     * @param PCUSTUMERACCOUNT
     * @param PCUSTUMERNAME
     * @param PAMOUNT
     * @param PCCY
     * @param PCHANNELID
     * @param PTRANSDATE
     * @param PADDINFO
     * @return
     * @throws SQLException
     */
    public Object[] InsertSITRANFERTOSI(
            String PPARTNERID,
            String PCUSTUMERACCOUNT,
            String PCUSTUMERNAME,
            BigDecimal PAMOUNT,
            String PCCY,
            String PCHANNELID,
            String PTRANSDATE,
            String PADDINFO) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(Insert_SI_TRANFERTOSI);
            calStmt.setString(1, PPARTNERID);
            calStmt.setString(2, PCUSTUMERACCOUNT);
            calStmt.setString(3, PCUSTUMERNAME);
            calStmt.setBigDecimal(4, PAMOUNT);
            calStmt.setString(5, PCCY);
            calStmt.setString(6, PCHANNELID);
            calStmt.setString(7, PTRANSDATE);
            calStmt.setString(8, PADDINFO);
            calStmt.registerOutParameter(9, OracleTypes.NUMBER);
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(11, OracleTypes.VARCHAR);
            calStmt.execute();
            Object[] out = new Object[3];
            out[0] = calStmt.getInt(9);
            out[1] = calStmt.getString(10);
            out[2] = calStmt.getString(11);
            return out;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param PID
     * @param PREFCORE
     * @param pSTATUS
     * @param pRESPONSECODE
     * @param pTXNREF
     * @param pDESCRESPONSE
     * @throws SQLException
     */
    public void CONFIRMSITRANFERTOSI(
            double PID,
            String PREFCORE,
            String pSTATUS,
            String pRESPONSECODE,
            String pTXNREF,
            String pDESCRESPONSE) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(CONFIRM_SI_TRANFERTOSI);
            calStmt.setDouble(1, PID);
            calStmt.setString(2, PREFCORE);
            calStmt.setString(3, pSTATUS);
            calStmt.setString(4, pRESPONSECODE);
            calStmt.setString(5, pTXNREF);
            calStmt.setString(6, pDESCRESPONSE);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pPARTNERID
     * @param pTRANSID
     * @param pTRANSDATE
     * @return
     * @throws SQLException
     */
    public String[] INSERTSITRANSFROMSI(
            String pPARTNERID,
            String pTRANSID,
            String pTRANSDATE
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(INSERT_SI_TRANSFROMSI);
            calStmt.setString(1, pPARTNERID);
            calStmt.setString(2, pTRANSID);
            calStmt.setString(3, pTRANSDATE);
            calStmt.registerOutParameter(4, Types.VARCHAR);
            calStmt.registerOutParameter(5, Types.VARCHAR);
            calStmt.execute();
            String[] output = new String[2];
            output[0] = calStmt.getString(4);
            output[1] = calStmt.getString(5);
            return output;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pAccount
     * @param pAmount
     * @return
     * @throws SQLException
     */
    public String[] CHECKACCOUNT(
            String pAccount,
            double pAmount
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(CHECK_ACCOUNT);
            calStmt.setString(1, pAccount);
            calStmt.setDouble(2, pAmount);
            calStmt.setString(3, "02"); //01: Ghi No; 02: Ghi co
            calStmt.setString(4, "01"); //01 : khach hang; 02: Partner
            calStmt.registerOutParameter(5, Types.VARCHAR);
            calStmt.registerOutParameter(6, Types.VARCHAR);
            calStmt.execute();
            String[] output = new String[2];
            output[0] = calStmt.getString(5);
            output[1] = calStmt.getString(6);
            return output;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pID_MASTER
     * @param pTXNDETAILID
     * @param pPARTNERACCOUNT
     * @param pCUSTUMERNAME
     * @param pCUSTUMERACCOUNT
     * @param pBANKCODE
     * @param pBranchname
     * @param pAmount
     * @param pCCY
     * @param pDescription
     * @param pTYPETRANSFER
     * @param pTYPECUSTACCOUNT
     * @return
     * @throws SQLException
     */
    public Object[] INSERTTRANSFERFROMSIDETAIL(
            double pID_MASTER,
            String pTXNDETAILID,
            String pPARTNERACCOUNT,
            String pCUSTUMERNAME,
            String pCUSTUMERACCOUNT,
            String pBANKCODE,
            String pBranchname,
            double pAmount,
            String pCCY,
            String pDescription,
            String pTYPETRANSFER,
            String pTYPECUSTACCOUNT
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(INSERT_TRANSFERFROMSI_DETAIL);
            calStmt.setDouble(1, pID_MASTER);
            calStmt.setString(2, pTXNDETAILID);
            calStmt.setString(3, pPARTNERACCOUNT);
            calStmt.setString(4, pCUSTUMERNAME);
            calStmt.setString(5, pCUSTUMERACCOUNT);
            calStmt.setString(6, pBANKCODE);
            calStmt.setString(7, pBranchname);
            calStmt.setDouble(8, pAmount);
            calStmt.setString(9, pCCY);
            calStmt.setString(10, pDescription);
            calStmt.setString(11, pTYPETRANSFER);
            calStmt.setString(12, pTYPECUSTACCOUNT);
            calStmt.registerOutParameter(13, Types.VARCHAR);
            calStmt.registerOutParameter(14, Types.INTEGER);
            calStmt.execute();
            Object[] output = new Object[2];
            output[0] = calStmt.getString(13);
            output[1] = calStmt.getInt(14);
            return output;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param PID
     * @param pREFCORE
     * @param pVALIDDATECORE
     * @param pSTATUS
     * @throws SQLException
     */
    public void UPDATETRANSFERFROMSIDETAIL(
            double PID,
            String pREFCORE,
            String pVALIDDATECORE,
            String pSTATUS) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(UPDATE_TRANSFERFROMSI_DETAIL);
            calStmt.setDouble(1, PID);
            calStmt.setString(2, pREFCORE);
            calStmt.setString(3, pVALIDDATECORE);
            calStmt.setString(4, pSTATUS);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pID_MASTER
     * @return
     * @throws SQLException
     */
    public ArrayList GETLISTTRANSFROMSI(int pID_MASTER) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(GET_LISTTRANSFROMSI);
            calStmt.setInt(1, pID_MASTER);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(2);
            int numcols = rs.getMetaData().getColumnCount();
            ArrayList result = new ArrayList();
            while (rs.next()) {
                List row = new ArrayList();
                for (int i = 1; i <= numcols; i++) {  // don't skip the last column, use <=
                    row.add(rs.getString(i));
                }
                result.add(row); // add it to the result
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public ArrayList getListBank() throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(getListBankCode);
            calStmt.registerOutParameter(1, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(1);
            int numcols = rs.getMetaData().getColumnCount();
            ArrayList result = new ArrayList();
            while (rs.next()) {
                List row = new ArrayList();
                for (int i = 1; i <= numcols; i++) {  // don't skip the last column, use <=
                    row.add(rs.getString(i));
                }
                result.add(row); // add it to the result
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pBANKCODE
     * @param pACCOUNTNO
     * @param pACCOUNTNAME
     * @param pACCOUNTSI
     * @param pNAMESI
     * @param pAMOUNT
     * @param pCCY
     * @param pSITRANSID
     * @param pTRANSDATE
     * @param pSCBTRANSID
     * @param pTYPETRANS
     * @throws SQLException
     */
    public void SI_InsertCollated(String pBANKCODE,
            String pACCOUNTNO,
            String pACCOUNTNAME,
            String pACCOUNTSI,
            String pNAMESI,
            String pAMOUNT,
            String pCCY,
            String pSITRANSID,
            String pTRANSDATE,
            String pSCBTRANSID,
            String pTYPETRANS) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(InsertCollated);
            calStmt.setString(1, pBANKCODE);
            calStmt.setString(2, pACCOUNTNO);
            calStmt.setString(3, pACCOUNTNAME);
            calStmt.setString(4, pACCOUNTSI);
            calStmt.setString(5, pNAMESI);
            calStmt.setString(6, pAMOUNT);
            calStmt.setString(7, pCCY);
            calStmt.setString(8, pSITRANSID);
            calStmt.setString(9, pTRANSDATE);
            calStmt.setString(10, pSCBTRANSID);
            calStmt.setString(11, pTYPETRANS);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pDate
     * @param pPartnerid
     * @param pType
     * @return
     * @throws SQLException
     */
    public ArrayList getOutCollated(Date pDate, String pPartnerid, String pType) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(getOutCollated);
            java.sql.Date sqlDate = new java.sql.Date(pDate.getTime());
            calStmt.setDate(1, sqlDate);
            calStmt.setString(2, pPartnerid);
            calStmt.setString(3, pType);
            calStmt.registerOutParameter(4, OracleTypes.CURSOR);
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(4);
            int numcols = rs.getMetaData().getColumnCount();
            ArrayList result = new ArrayList();
            while (rs.next()) {
                List row = new ArrayList();
                for (int i = 1; i <= numcols; i++) {
                    row.add(rs.getString(i));
                }
                result.add(row);
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    //Add new 2018

    /**
     *
     * @param pPARTNERID
     * @param Transid
     * @return
     * @throws SQLException
     */
    public List querryTransfer(String pPARTNERID, String Transid) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(querryTransfer);
            calStmt.setString(1, pPARTNERID);
            calStmt.setString(2, Transid);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR);
            calStmt.execute();
            ResultSet rs = (ResultSet) calStmt.getObject(3);
            ArrayList list = new ArrayList();
            if (rs == null) {
                return list;
            }
            while (rs.next()) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("TRANSID", rs.getString("TRANSID"));
                h.put("DESCRIPTION", rs.getString("DESCRIPTION"));
                h.put("STATUS", rs.getString("STATUS"));
                h.put("REFCORE", rs.getString("REFCORE"));
                list.add(h);
            }
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public ArrayList getListBank247() throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(getListBankCode247);
            calStmt.registerOutParameter(1, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(1);
            int numcols = rs.getMetaData().getColumnCount();
            ArrayList result = new ArrayList();
            while (rs.next()) {
                List row = new ArrayList();
                for (int i = 1; i <= numcols; i++) {  // don't skip the last column, use <=
                    row.add(rs.getString(i));
                }
                result.add(row); // add it to the result
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pPARTNERID
     * @param pTYPEFUNC
     * @param pAMOUNT
     * @param pCCY
     * @param pBANKCODE
     * @param pACCOUNTNO
     * @param pACCOUNTNAME
     * @param pSITRANSID
     * @param pSCBTRANSID
     * @param pTRANSDATE
     * @param pSTATUSTRANS
     * @throws SQLException
     */
    public void InsertCollated_TCH(String pPARTNERID,
            String pTYPEFUNC,
            String pAMOUNT,
            String pCCY,
            String pBANKCODE,
            String pACCOUNTNO,
            String pACCOUNTNAME,
            String pSITRANSID,
            String pSCBTRANSID,
            String pTRANSDATE,
            String pSTATUSTRANS)
            throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(InsertCollated_TCH);
            calStmt.setString(1, pPARTNERID);
            calStmt.setString(2, pTYPEFUNC);
            calStmt.setString(3, pAMOUNT);
            calStmt.setString(4, pCCY);
            calStmt.setString(5, pBANKCODE);
            calStmt.setString(6, pACCOUNTNO);
            calStmt.setString(7, pACCOUNTNAME);
            calStmt.setString(8, pSITRANSID);
            calStmt.setString(9, pSCBTRANSID);
            calStmt.setString(10, pTRANSDATE);
            calStmt.setString(11, pSTATUSTRANS);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pDate
     * @param pPartnerid
     * @param pType
     * @return
     * @throws SQLException
     */
    public ArrayList getOutCollated_TCH(Date pDate, String pPartnerid, String pType) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(getOutCollated);
            java.sql.Date sqlDate = new java.sql.Date(pDate.getTime());
            calStmt.setDate(1, sqlDate);
            calStmt.setString(2, pPartnerid);
            calStmt.setString(3, pType);
            calStmt.registerOutParameter(4, OracleTypes.CURSOR);
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(4);
            int numcols = rs.getMetaData().getColumnCount();
            ArrayList list = new ArrayList();
            while (rs.next()) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("TYPETRANS", rs.getString("TYPETRANS"));
                h.put("Ccy", rs.getString("Ccy"));
                h.put("Amount", rs.getString("Amount"));
                h.put("Bankcode", rs.getString("Bankcode"));
                h.put("Accountno", rs.getString("Accountno"));
                h.put("Accountname", rs.getString("Accountname"));
                h.put("SITRANSID", rs.getString("SITRANSID"));
                h.put("Scbtransid", rs.getString("Scbtransid"));
                h.put("Transdate", rs.getString("Transdate"));
                h.put("Statustrans", rs.getString("Statustrans"));
                h.put("Typefunc", rs.getString("Typefunc"));
                h.put("Resultcode", rs.getString("Resultcode"));
                list.add(h);
            }
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String updateRateToSI_TRFFROMSI_DETAIL = "UPDATE SI_TRFFROMSI_DETAIL SET RATE = ?, AMT_EXCHANGE = ? , CCY_EXCHANGE = ? WHERE ID = ?";

    /**
     *
     * @param idTrans
     * @param res
     * @return
     * @throws SQLException
     */
    public boolean updateRateToSI_TRFFROMSI_DETAIL(String idTrans, ExchangeRateRes res)
            throws SQLException {
        connection.setAutoCommit(false);
        try (PreparedStatement preStatement = connection.prepareStatement(updateRateToSI_TRFFROMSI_DETAIL)) {
            preStatement.setBigDecimal(1, res.getRate());
            preStatement.setBigDecimal(2, res.getMoneyExchange());
            preStatement.setString(3, res.getCcyExchange());
            preStatement.setString(4, idTrans);
            if (preStatement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new SQLException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param transDetail
     * @return
     * @throws SQLException
     */
    public Object[] insertDetailToSi(TransactionDetail transDetail) throws SQLException {
        try (CallableStatement calStmt = connection.prepareCall(insertDetailToSiKieuHoi)) {
            calStmt.setDouble(1, Double.valueOf(transDetail.getScbDetailId()));
            calStmt.setString(2, transDetail.getTxnDetailId());
            calStmt.setString(3, transDetail.getPartnerAccount());
            calStmt.setString(4, transDetail.getCustomerName());
            calStmt.setString(5, transDetail.getCustomerAccount());
            calStmt.setString(6, transDetail.getBankCode());
            calStmt.setString(7, transDetail.getBranch());
            calStmt.setDouble(8, transDetail.getAmountDoubleType());
            calStmt.setString(9, transDetail.getCcy());
            calStmt.setString(10, transDetail.getDescription());
            calStmt.setString(11, transDetail.getTypeFunction());
            calStmt.setString(12, transDetail.getTypeCustAccount());
            calStmt.setBigDecimal(13, transDetail.getExchangeRateRes().getRate());
            calStmt.setBigDecimal(14, transDetail.getExchangeRateRes().getMoneyExchange());
            calStmt.setString(15, transDetail.getExchangeRateRes().getCcyExchange());
            calStmt.setString(16, (transDetail.getReceivingInfo() != null) ? transDetail.getReceivingInfo().getPersonId() : "");
            calStmt.setString(17, (transDetail.getReceivingInfo() != null) ? transDetail.getReceivingInfo().getFirstName() : "");
            calStmt.setString(18, (transDetail.getReceivingInfo() != null) ? transDetail.getReceivingInfo().getLastName() : "");
            calStmt.setString(19, (transDetail.getReceivingInfo() != null) ? transDetail.getReceivingInfo().getPassNo() : "");
            calStmt.setString(20, (transDetail.getReceivingInfo() != null) ? transDetail.getReceivingInfo().getBirthDate() : "");
            calStmt.setString(21, (transDetail.getReceivingInfo() != null) ? transDetail.getReceivingInfo().getAddress() : "");
            calStmt.setString(22, (transDetail.getReceivingInfo() != null) ? transDetail.getReceivingInfo().getNationality() : "");
            calStmt.setString(23, (transDetail.getReceivingInfo() != null) ? transDetail.getReceivingInfo().getCustType() : "");
            calStmt.registerOutParameter(24, Types.VARCHAR);
            calStmt.registerOutParameter(25, Types.INTEGER);
            calStmt.execute();
            Object[] output = new Object[2];
            output[0] = calStmt.getString(24);
            output[1] = calStmt.getInt(25);
            return output;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String insertDetailToSiKieuHoi = "BEGIN PRO_TRANS_DETAIL_MSB_LIVE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    /*dang test tren 73.20
    * doi tac: WU
     */
    final String insertToSenderDetail = "insert into si_sender_detail s(s.id, s.firstname, s.lastname, s.address, s.countryisocode, s.accountnumber, s.senderidentifier, s.birthdate, s.countryofbirth, s.idtype, s.idnumber, s.banktransid) values (SEQ_SI_SENDER_DETAIL.nextval,?,?,?,?,?,?,?,?,?,?,?)";

    public int insertToSiSenderDetail(SenderInfo senderInfo, Long bankTransId) throws SQLException {
        int result = 0;
        connection.setAutoCommit(false);
        PreparedStatement preParedStatement = null;
        try {
            preParedStatement = connection.prepareStatement(insertToSenderDetail);
            preParedStatement.setString(1, senderInfo.getFirstName());
            preParedStatement.setString(2, senderInfo.getLastName());
            preParedStatement.setString(3, senderInfo.getAddress());
            preParedStatement.setString(4, senderInfo.getCountryIsoCode());
            preParedStatement.setString(5, senderInfo.getAccountNumber());
            preParedStatement.setString(6, senderInfo.getSenderIdentifier());
            preParedStatement.setString(7, senderInfo.getBirthDate());
            preParedStatement.setString(8, senderInfo.getCountryOfBitrh());
            preParedStatement.setString(9, senderInfo.getIdType());
            preParedStatement.setString(10, senderInfo.getIdNumber());
            preParedStatement.setLong(11, bankTransId);
            int rs = preParedStatement.executeUpdate();
            if (rs > 0) {
                connection.commit();
                result = 1;
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pID_MASTER
     * @param pTXNDETAILID
     * @param pPARTNERACCOUNT
     * @param pCUSTUMERNAME
     * @param pCUSTUMERACCOUNT
     * @param pBANKCODE
     * @param pBranchname
     * @param pAmount
     * @param pCCY
     * @param pDescription
     * @param pTYPETRANSFER
     * @param pTYPECUSTACCOUNT
     * @param rate
     * @param amtExchange
     * @param ccyExchange
     * @return
     * @throws SQLException
     */
    public Object[] INSERTTRANSFERFROMSIDETAIL_KIEUHOI(
            double pID_MASTER,
            String pTXNDETAILID,
            String pPARTNERACCOUNT,
            String pCUSTUMERNAME,
            String pCUSTUMERACCOUNT,
            String pBANKCODE,
            String pBranchname,
            double pAmount,
            String pCCY,
            String pDescription,
            String pTYPETRANSFER,
            String pTYPECUSTACCOUNT,
            BigDecimal rate,
            BigDecimal amtExchange,
            String ccyExchange,
            String personId,
            String firstName,
            String lastName,
            String passNo,
            String birthday,
            String address,
            String nationality,
            String custtype
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(insertDetailToSiKieuHoi);
            calStmt.setDouble(1, pID_MASTER);
            calStmt.setString(2, pTXNDETAILID);
            calStmt.setString(3, pPARTNERACCOUNT);
            calStmt.setString(4, pCUSTUMERNAME);
            calStmt.setString(5, pCUSTUMERACCOUNT);
            calStmt.setString(6, pBANKCODE);
            calStmt.setString(7, pBranchname);
            calStmt.setDouble(8, pAmount);
            calStmt.setString(9, pCCY);
            calStmt.setString(10, pDescription);
            calStmt.setString(11, pTYPETRANSFER);
            calStmt.setString(12, pTYPECUSTACCOUNT);
            calStmt.setBigDecimal(13, rate);
            calStmt.setBigDecimal(14, amtExchange);
            calStmt.setString(15, ccyExchange);
            calStmt.setString(16, personId);
            calStmt.setString(17, firstName);
            calStmt.setString(18, lastName);
            calStmt.setString(19, passNo);
            calStmt.setString(20, birthday);
            calStmt.setString(21, address);
            calStmt.setString(22, nationality);
            calStmt.setString(23, custtype);
            calStmt.registerOutParameter(24, Types.VARCHAR);
            calStmt.registerOutParameter(25, Types.INTEGER);
            calStmt.execute();
            Object[] output = new Object[2];
            output[0] = calStmt.getString(24);
            output[1] = calStmt.getInt(25);
            return output;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private String CHECKSTATUSTRANSFERMONEY = "SELECT COALESCE(t.STATUS, '99') AS STATUS  FROM Si_Trffromsi b LEFT JOIN SI_TRFFROMSI_DETAIL t ON b.id = t.id_master WHERE b.transid = ? AND b.partnerid = ? ";

    public TransferMoneyTransactionInfo checkStatusTransferMoney(GetStatusOfTransferMoneyReq req) throws SQLException {
        TransferMoneyTransactionInfo result = new TransferMoneyTransactionInfo();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            /*
            if (req.getTxnDetailId() != null && !req.getTxnDetailId().isEmpty()) {
                CHECKSTATUSTRANSFERMONEY = CHECKSTATUSTRANSFERMONEY + "AND t.txndetailid = ?";
            } */
            preStatement = connection.prepareStatement(CHECKSTATUSTRANSFERMONEY);
            preStatement.setString(1, req.getTransId());
            preStatement.setString(2, req.getProviderId());
            
            /*
            if (req.getTxnDetailId() != null && !req.getTxnDetailId().isEmpty()) {
                preStatement.setString(3, req.getTxnDetailId());
            }
            */
            rs = preStatement.executeQuery();
            while (rs.next()) {
                result.setStatus(rs.getString("STATUS"));
                break;
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    String checkPartnerPregolive = "select count(*) as vcheck from PREGOLIVE_PARTNER where PARTNERID=?";

    public int checkPartnerPregolive(String partnerid) throws Exception {
        ArrayList<String> p = new ArrayList<String>();
        p.add(partnerid);
        ArrayList<?> a = JDBCEngine.executeQuery(checkPartnerPregolive, p, connection);
        if (a != null && a.size() > 0) {
            // Co ton tai USERROLE da su dung ROLE nay. KHong the xoa
            HashMap<?, ?> hm = (HashMap<?, ?>) a.get(0);
            int cntrole = Integer.parseInt(hm.get("vcheck").toString());
            if (cntrole > 0) {
                return 1;
            }
        }
        return 0;
    }
    String checkAccountPregolive = "select count(*) as vcheck from PREGOLIVE_PARTNER where PARTNERID=? and accountno=?";

    public int checkAccountPregolive(String partnerid, String accountno) throws Exception {
        ArrayList<String> p = new ArrayList<String>();
        p.add(partnerid);
        p.add(accountno);
        ArrayList<?> a = JDBCEngine.executeQuery(checkAccountPregolive, p, connection);
        if (a != null && a.size() > 0) {
            // Co ton tai USERROLE da su dung ROLE nay. KHong the xoa
            HashMap<?, ?> hm = (HashMap<?, ?>) a.get(0);
            int cntrole = Integer.parseInt(hm.get("vcheck").toString());
            if (cntrole > 0) {
                return 1;
            }
        }
        return 0;
    }
    /*dang test tren 73.20
    * doi tac: WU
     */
    final String getListTransactionByDate = "select si.transid, de.* "
            + "from si_trffromsi si left join  si_trffromsi_detail de on  si.id = de.id_master "
            + "where si.partnerid=? and  to_date(TO_CHAR(SI.CREATEDATE, 'ddMMyyyyHH24mi'),'ddMMyyyyHH24mi') between to_date(?,'ddMMyyyyHH24mi') and to_date(?, 'ddMMyyyyHH24mi')";

    public List<TransactionDetailByDate> getListTransactionByDate(String providerId, String partnerAccount, String startDateTime, String endDateTime) throws SQLException {
        List<TransactionDetailByDate> listTransaction = new ArrayList<>();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            preStatement = connection.prepareCall(getListTransactionByDate);
            preStatement.setString(1, providerId);
            preStatement.setString(2, startDateTime);
            preStatement.setString(3, endDateTime);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                TransactionDetailByDate transDetail = new TransactionDetailByDate();
                transDetail.setTransId(rs.getString("TRANSID"));
                transDetail.setTxnDetailId(rs.getString("TXNDETAILID"));
                transDetail.setBankTransId(rs.getString("ID"));
                transDetail.setCustomerAccount(rs.getString("CUSTUMERACCOUNT"));
                transDetail.setCustomerName(rs.getString("CUSTUMERNAME"));
                transDetail.setBankCode(rs.getString("BANKCODE"));
                transDetail.setAmount(rs.getBigDecimal("AMOUNT"));
                transDetail.setCcy(rs.getString("CCY"));
                transDetail.setTypeTransfer(rs.getString("TYPETRANSFER"));
                transDetail.setStatus(rs.getString("STATUS") == null ? "99" : rs.getString("STATUS"));
                transDetail.setContent(rs.getString("DESCRIPTION"));
                transDetail.setCreateDate(rs.getDate("CREATEDATE"));
                listTransaction.add(transDetail);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return listTransaction;
    }

    /*dang test tren 73.20
    * doi tac: WU
     */
    final String insertErrorKycSiTransDetail = "BEGIN PRO_TRANS_DETAIL_MSB_LIVE_2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    public String[] insertErrorKycSiTransDetail(String idMaster, String txnDetailId, String partnerAccount, String customerName, String customerAccount,
            String bankCode, String branchName, BigDecimal amount, String ccy, String description, String status, String typeTransfer,
            String typeCustAccount, BigDecimal rate, BigDecimal amtExchange, String ccyExchange, String personId, String firtName, String lastName,
            String passNo, String birthDate, String address, String nationlity, String custType) throws SQLException {

        try (CallableStatement calStmt = connection.prepareCall(insertErrorKycSiTransDetail)) {
            calStmt.setDouble(1, Double.valueOf(idMaster));
            calStmt.setString(2, txnDetailId);
            calStmt.setString(3, partnerAccount);
            calStmt.setString(4, customerName);
            calStmt.setString(5, customerAccount);
            calStmt.setString(6, bankCode);
            calStmt.setString(7, branchName);
            calStmt.setBigDecimal(8, amount);
            calStmt.setString(9, ccy);
            calStmt.setString(10, description);
            calStmt.setString(11, status);
            calStmt.setString(12, typeTransfer);
            calStmt.setString(13, typeCustAccount);
            calStmt.setBigDecimal(14, rate);
            calStmt.setBigDecimal(15, amtExchange);
            calStmt.setString(16, ccyExchange);
            calStmt.setString(17, personId);
            calStmt.setString(18, firtName);
            calStmt.setString(19, lastName);
            calStmt.setString(20, passNo);
            calStmt.setString(21, birthDate);
            calStmt.setString(22, address);
            calStmt.setString(23, nationlity);
            calStmt.setString(24, custType);
            calStmt.registerOutParameter(25, Types.VARCHAR);
            calStmt.registerOutParameter(26, Types.INTEGER);
            calStmt.execute();
            String[] str = new String[2];
            str[0] = calStmt.getString(25);
            str[1] = String.valueOf(calStmt.getInt(26));
            return str;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /*WU - TEST CASE 73.20 test xong xoa*/
    final String insertTestCaseToTransDetail = "BEGIN PRO_TRANS_TEST_CASE_WU(?,?,?,?,?,?,?,?,?,?,?,?); END;";

    public String[] insertTestCaseToTransDetail(String idMaster, String txnDetailId, String partnerAccount, String customerName,
            String customerAccount, String bankCode, BigDecimal amount, String ccy, String description, String typeTransfer, String status) throws SQLException {
        try (CallableStatement calStmt = connection.prepareCall(insertTestCaseToTransDetail)) {
            calStmt.setDouble(1, Double.valueOf(idMaster));
            calStmt.setString(2, txnDetailId);
            calStmt.setString(3, partnerAccount);
            calStmt.setString(4, customerAccount);
            calStmt.setString(5, customerName);
            calStmt.setString(6, bankCode);
            calStmt.setBigDecimal(7, amount);
            calStmt.setString(8, ccy);
            calStmt.setString(9, description);
            calStmt.setString(10, typeTransfer);
            calStmt.setString(11, status);
            calStmt.registerOutParameter(11, Types.VARCHAR);
            calStmt.registerOutParameter(12, Types.INTEGER);
            calStmt.execute();
            String[] str = new String[2];
            str[0] = calStmt.getString(11);
            str[1] = String.valueOf(calStmt.getInt(12));
            return str;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /*BAOTBQ - KIEUHOI*/
    final String getInforRemittance = "select dd.*,vv.*, vv.id as iddetail, vv.status as statusdetail from si_trffromsi dd inner join si_trffromsi_detail vv on DD.TRANSID = VV.TXNDETAILID and dd.id = vv.id_master and dd.partnerid = 'WU' and vv.txndetailid = ?";

    public ArrayList getInforRemittance(String magd) throws SQLException {
        try {
            ArrayList<String> p_args = new ArrayList<String>();
            p_args.add(magd);
            return JDBCEngine.executeQuery(getInforRemittance, p_args, connection);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String getInforRemittanceByDate = "select dd.*,vv.*, vv.id as iddetail, vv.status as statusdetail from si_trffromsi dd inner join si_trffromsi_detail vv on DD.TRANSID = VV.TXNDETAILID and dd.id = vv.id_master and dd.partnerid = 'WU' and dd.transdate between ? and ?";

    public List<TransfersRemittance> getInforRemittanceByDate(String tungay, String denngay) throws SQLException {
        List<TransfersRemittance> listTransfersRemittance = new ArrayList<>();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            preStatement = connection.prepareCall(getInforRemittanceByDate);
            preStatement.setString(1, tungay);
            preStatement.setString(2, denngay);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                TransfersRemittance transdersRm = new TransfersRemittance();
                transdersRm.setAccountPartner(rs.getString("PARTNERACCOUNT"));
                transdersRm.setAddress(rs.getString("ADDRESS"));
                transdersRm.setAmount(rs.getBigDecimal("AMOUNT"));
                transdersRm.setBenId(rs.getString("BANKCODE"));
                transdersRm.setBirthDay(rs.getString("BIRTHDATE"));
                transdersRm.setCcy(rs.getString("CCY"));
                transdersRm.setDescription("DESCRIPTION");
                transdersRm.setId(rs.getString("IDDETAIL"));
                transdersRm.setMsgId(rs.getString("TYPETRANSFER"));
                transdersRm.setNational(rs.getString("NATIONALITY"));
                transdersRm.setPassNo(rs.getString("PASSNO"));
                transdersRm.setProviderId(rs.getString("PARTNERID"));
                transdersRm.setToNumber(rs.getString("PARTNERACCOUNT"));
                transdersRm.setTransId(rs.getString("TRANSID"));
                transdersRm.setTypeToNumber(rs.getString("TYPECUSTACCOUNT"));
                transdersRm.setTnxTransId(rs.getString("TXNDETAILID"));
                transdersRm.setStatus(rs.getString("STATUSDETAIL"));
                transdersRm.setFirstName(rs.getString("FIRSTNAME"));
                transdersRm.setLastName(rs.getString("LASTNAME"));
                listTransfersRemittance.add(transdersRm);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return listTransfersRemittance;
    }

    final String INS_TransfersRemittanceExtend = "INSERT INTO SMS_SCB.SI_TRFFROMSI_EXTEND(SI_ID, IDUSER_MAKER, DATE_MAKER, CREATED_DATE, BRANCHCODE, TYPE, APPROVED , PASSNO, BIRTHDAY, FULLNAME, NATIONALITY) Values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public int insertTransfersRemittanceExtend(SiTrffromsiExtend siTrffromsiExtend) throws SQLException {
        PreparedStatement preStatement = null;
        try {
            Date nowDate = new Date();
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(INS_TransfersRemittanceExtend);
            preStatement.setString(1, siTrffromsiExtend.getSiId());
            preStatement.setString(2, siTrffromsiExtend.getIdUserMarker());
            preStatement.setTimestamp(3, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setTimestamp(4, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setString(5, siTrffromsiExtend.getBranchCode());
            preStatement.setString(6, siTrffromsiExtend.getType());
            preStatement.setString(7, siTrffromsiExtend.getApproved());
            preStatement.setString(8, siTrffromsiExtend.getPassNo());
            preStatement.setString(9, siTrffromsiExtend.getBirthDate());
            preStatement.setString(10, siTrffromsiExtend.getFullName());
            preStatement.setString(11, siTrffromsiExtend.getNational());
            int result = preStatement.executeUpdate();
            if (result > 0) {
                connection.commit();
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
            throw ex;
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String UD_SI_TRFFROMSI_EXTEND = "update SMS_SCB.SI_TRFFROMSI_EXTEND  set APPROVED = ?, IDUSER_CHECKER=?, DATE_CHECKER=?, UPDATED_DATE = ?  where ID = ?";

    public int updateTransfersRemittanceExtend(String siIdExtend, String userChecker, String approved) throws SQLException {
        PreparedStatement preStatement = null;
        try {
            Date nowDate = new Date();
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(UD_SI_TRFFROMSI_EXTEND);
            //update SI_TRFFROMSI_EXTEND
            preStatement.setString(1, approved);
            preStatement.setString(2, userChecker);
            preStatement.setTimestamp(3, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setTimestamp(4, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setString(5, siIdExtend);
            int result = preStatement.executeUpdate();
            return result;
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
            throw e;
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String G_checkTransfersRemittanceApproved = "select * from SI_TRFFROMSI_EXTEND dd where dd.si_id = ? and dd.approved = ?";

    public int checkTransfersRemittanceApproved(String id, String approved) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(id);
        p_args.add(approved);
        ArrayList list = JDBCEngine.executeQuery(G_checkTransfersRemittanceApproved, p_args, connection);
        return (list.size() > 0) ? 1 : 0;
    }

    final String G_GetSiTrffromSiById = "select * from SI_TRFFROMSI_EXTEND WHERE ID=?";

    public ArrayList getSiTrffromSiById(String id) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(id);
        return JDBCEngine.executeQuery(G_GetSiTrffromSiById, p_args, connection);
    }

    final String GET_LIST_TRANSFER247_EBANK = "select t.partnerid,m.* from sms_scb.si_trffromsi t, sms_scb.si_trffromsi_detail m, sms_scb.si_trffromsi_extend e where t.id = m.id_master \n" +
            "and e.si_id = m.txndetailid and e.id= ?";
    public List<TransferMoney247EbankReq> getTransfer247Ebank (String id) throws SQLException{
        List<TransferMoney247EbankReq> listTransfer247Detail = new ArrayList<>();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try{
            preStatement = connection.prepareCall(GET_LIST_TRANSFER247_EBANK);
            preStatement.setString(1, id);
            rs = preStatement.executeQuery();
            while(rs.next()){
                TransferMoney247EbankReq detail = new TransferMoney247EbankReq();
                detail.setId(rs.getString("ID"));
                detail.setBenId(rs.getString("BANKCODE"));
                detail.setAmount(new BigDecimal(rs.getString("AMOUNT")));
                detail.setCcy(rs.getString("CCY"));
                detail.setProviderId(rs.getString("PARTNERID"));
                detail.setAccountPartner(rs.getString("PARTNERACCOUNT"));
                detail.setToNumber(rs.getString("CUSTUMERACCOUNT"));
                detail.setTypeToNumber(rs.getString("TYPECUSTACCOUNT"));
                detail.setTransId(rs.getString("TXNDETAILID"));
                detail.setDescription(rs.getString("DESCRIPTION"));
                detail.setIdMaster(rs.getString("ID_MASTER"));
                detail.setBankCode(rs.getString("BANKCODE"));
                detail.setBranhName(rs.getString("BRANCHNAME"));
                detail.setCcy_exchange(rs.getString("CCY_EXCHANGE"));
                detail.setAmt_exchange(rs.getString("AMT_EXCHANGE"));
                detail.setTypeTransfer(rs.getString("TYPETRANSFER"));
                detail.setRate(rs.getString("RATE"));
                detail.setPersonID(rs.getString("PERSONID"));
                detail.setFirstName(rs.getString("FIRSTNAME"));
                detail.setLastName(rs.getString("LASTNAME"));
                detail.setPassNo(rs.getString("PASSNO"));
                detail.setBirthDate(rs.getString("BIRTHDATE"));
                detail.setAddress(rs.getString("ADDRESS"));
                detail.setNational(rs.getString("NATIONALITY"));
                detail.setCustType(rs.getString("CUSTTYPE"));
                listTransfer247Detail.add(detail);               
            }
            return listTransfer247Detail;
        }catch(Exception ex){
            LOGGER.error(ex);
            return null;
        }finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
                
    }
    /*BAOTBQ - KIEUHOI - end*/

    final String insertDetailToSiKieuHoi_wu = "BEGIN PRO_TRANS_DETAIL_MSB_LIVE_NEW_WU(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    public Object[] INSERTTRANSFERFROMSIDETAIL_KIEUHOI_WU(
            double pID_MASTER,
            String pTXNDETAILID,
            String pPARTNERACCOUNT,
            String pCUSTUMERNAME,
            String pCUSTUMERACCOUNT,
            String pBANKCODE,
            String pBranchname,
            double pAmount,
            String pCCY,
            String pDescription,
            String pTYPETRANSFER,
            String pTYPECUSTACCOUNT,
            BigDecimal rate,
            BigDecimal amtExchange,
            String ccyExchange,
            String personId,
            String firstName,
            String lastName,
            String passNo,
            String birthday,
            String address,
            String nationality,
            String custtype
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(insertDetailToSiKieuHoi_wu);
            calStmt.setDouble(1, pID_MASTER);
            calStmt.setString(2, pTXNDETAILID);
            calStmt.setString(3, pPARTNERACCOUNT);
            calStmt.setString(4, pCUSTUMERNAME);
            calStmt.setString(5, pCUSTUMERACCOUNT);
            calStmt.setString(6, pBANKCODE);
            calStmt.setString(7, pBranchname);
            calStmt.setDouble(8, pAmount);
            calStmt.setString(9, pCCY);
            calStmt.setString(10, pDescription);
            calStmt.setString(11, pTYPETRANSFER);
            calStmt.setString(12, pTYPECUSTACCOUNT);
            calStmt.setBigDecimal(13, rate);
            calStmt.setBigDecimal(14, amtExchange);
            calStmt.setString(15, ccyExchange);
            calStmt.setString(16, personId);
            calStmt.setString(17, firstName);
            calStmt.setString(18, lastName);
            calStmt.setString(19, passNo);
            calStmt.setString(20, birthday);
            calStmt.setString(21, address);
            calStmt.setString(22, nationality);
            calStmt.setString(23, custtype);
            calStmt.registerOutParameter(24, Types.VARCHAR);
            calStmt.registerOutParameter(25, Types.INTEGER);
            calStmt.execute();
            Object[] output = new Object[2];
            output[0] = calStmt.getString(24);
            output[1] = calStmt.getInt(25);
            return output;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    /*Baotbq - 5/9/2023 - check and update status trc khi goi qua napas*/
    final String UPDATETRANSFERFROMSIDETAILKIEUHOI_EBMR = "BEGIN PRO_TRANS_DETAIL_MSB_LIVE_EBMR(?,?,?,?,?,?,?,?,?,?,?); END;";
    public Object[] UPDATETRANSFERFROMSIDETAILKIEUHOI_EBMR(
            double pID_MASTER,
            String pTXNDETAILID,
            String pPARTNERACCOUNT,
            String pCUSTUMERACCOUNT,
            String pBANKCODE,
            double pAmount,
            String pTYPETRANSFER,
            String pTYPECUSTACCOUNT,
            BigDecimal amtExchange
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(UPDATETRANSFERFROMSIDETAILKIEUHOI_EBMR);
            calStmt.setDouble(1, pID_MASTER);
            calStmt.setString(2, pTXNDETAILID);
            calStmt.setString(3, pPARTNERACCOUNT);
            calStmt.setString(4, pCUSTUMERACCOUNT);
            calStmt.setString(5, pBANKCODE);
            calStmt.setDouble(6, pAmount);
            calStmt.setString(7, pTYPETRANSFER);
            calStmt.setString(8, pTYPECUSTACCOUNT);
            calStmt.setBigDecimal(9, amtExchange);          
            calStmt.registerOutParameter(10, Types.VARCHAR);
            calStmt.registerOutParameter(11, Types.INTEGER);
            calStmt.execute();
            Object[] output = new Object[2];
            output[0] = calStmt.getString(10);
            output[1] = calStmt.getInt(11);
            //String output = calStmt.getString(10);
            return output;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
