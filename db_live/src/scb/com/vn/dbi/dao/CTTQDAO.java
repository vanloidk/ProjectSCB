/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dto.SmlFtCounter;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author baovq
 */
public class CTTQDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(CTTQDAO.class);

    final String SQL_GET_BANK_LIST = "SELECT BANK_NAME as BANKNAME, BANK_CODE as BANKCODE FROM  obdx_bene_bank WHERE bank_quick=1 and BANK_CODE !='79334001' order by BANK_NAME";
    final String GET_MOBILE_NUMBER = "SELECT mobile_number FROM fcc_vw_customer_details@FCATFCCLINK where idcorporate = ?";
    final String INSERT_CTTQ = "begin PKG_IBT_COUNTER.INSERT_CTTQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String getDataCTTQ = "begin PKG_IBT_COUNTER.getDataCTTQ(?,?); end;";
    final String CHECK_STATUS_IBT = "select TYPETRANSFER, DESCR from SML_STATUS where TYPETRANSFER='FROM_SCB' and STATUS = ?";
    final String DUYET_CTTQ = "begin PKG_IBT_COUNTER.DUYET_CTTQ(?,?,?,?,?,?,?); end;";
    final String IBT_TUCHOI_CTTQ = "update sml_ft_counter set userapprove=?, branch_approve=?, status=?, refcore=?, dateapprove=sysdate where id=? and status ='03'";
    final String SEARCH_IBT = "select * from vw_sml_ft_counter c where c.status !='07' and c.branch_code like ? and c.status like ? and trunc(c.datecheck)>=to_date(?,'dd/MM/yyyy') and trunc(c.datecheck)<=to_date(?,'dd/MM/yyyy') and c.paymentmethod like ? and c.toaccnumber like ? order by c.id";
    final String CHECK_TRANSFER = "select * from sml_ft_counter c where ID=? and status='03'";

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> GET_BANK_LIST() throws Exception {
        return JDBCEngine.executeQuery(SQL_GET_BANK_LIST, new ArrayList(), connection);
    }

    /**
     *
     * @param cust_no
     * @return
     * @throws Exception
     */
    public ArrayList<?> GET_MOBILE_NUMBER(String cust_no) throws Exception {
        try {
            ArrayList<String> p_args = new ArrayList<String>();
            p_args.add(cust_no);
            return JDBCEngine.executeQuery(GET_MOBILE_NUMBER, p_args, connection);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_CTTQ(SmlFtCounter obj) throws SQLException, RemoteException {
        CallableStatement calStmt = connection.prepareCall(INSERT_CTTQ);
        try {
            calStmt.setString(1, obj.getToaccnumber());
            calStmt.setString(2, obj.getTonamecard());
            calStmt.setString(3, obj.getBenid());
            calStmt.setString(4, obj.getBenname());
            calStmt.setString(5, obj.getFromaccount());
            calStmt.setString(6, obj.getFromname());
            calStmt.setString(7, obj.getCmt());
            calStmt.setString(8, obj.getNgaycap());
            calStmt.setString(9, obj.getNoicap());
            calStmt.setString(10, obj.getAddress());
            calStmt.setString(11, obj.getSdt());
            calStmt.setString(12, obj.getTypetransfer());
            calStmt.setString(13, obj.getDescription());
            calStmt.setString(14, obj.getPaymentmethod());
            calStmt.setString(15, obj.getCcy());
            calStmt.setString(16, obj.getTypefunction());
            calStmt.setString(17, obj.getTermid());
            calStmt.setString(18, obj.getCardacceptor());
            calStmt.setString(19, obj.getFeeamount());
            calStmt.setString(20, obj.getVatamount());
            calStmt.setString(21, obj.getTotalamount());
            calStmt.setString(22, obj.getUsercheck());
            calStmt.setString(23, obj.getDatecheck());
            calStmt.setString(24, obj.getUserapprove());
            calStmt.setString(25, obj.getDateapprove());
            calStmt.setString(26, obj.getBranch_code());
            calStmt.registerOutParameter(27, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(28, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] result = new String[2];
            result[0] = calStmt.getString(27); //status
            result[1] = calStmt.getString(28);//description                        
            return result;
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
     * @param pID
     * @return
     * @throws SQLException
     */
    public List<SmlFtCounter> getDataCTTQ(String pID) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getDataCTTQ);
        try {
            calStmt.setString(1, pID);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = (ResultSet) calStmt.getObject(2);
            List<SmlFtCounter> resultList = new ArrayList<>();
            while (rs.next()) {
                SmlFtCounter obj = new SmlFtCounter();
                obj.setToaccnumber(rs.getString("TOACCNUMBER"));
                obj.setTonamecard(rs.getString("TONAMECARD"));
                obj.setBenid(rs.getString("BENID"));
                obj.setBenname(rs.getString("BENNAME"));
                obj.setFromaccount(rs.getString("FROMACCOUNT"));
                obj.setFromname(rs.getString("FROMNAME"));
                obj.setCmt(rs.getString("CMT"));
                obj.setNgaycap(rs.getString("NGAYCAP"));
                obj.setNoicap(rs.getString("NOICAP"));
                obj.setAddress(rs.getString("ADDRESS"));
                obj.setSdt(rs.getString("SDT"));
                obj.setTypetransfer(rs.getString("TYPETRANSFER"));
                obj.setDescription(rs.getString("DESCRIPTION"));
                obj.setPaymentmethod(rs.getString("PAYMENTMETHOD"));
                obj.setCcy(rs.getString("CCY"));
                obj.setTypefunction(rs.getString("TYPEFUNCTION"));
                obj.setTermid(rs.getString("TERMID"));
                obj.setCardacceptor(rs.getString("CARDACCEPTOR"));
                obj.setFeeamount(rs.getString("FEEAMOUNT"));
                obj.setVatamount(rs.getString("VATAMOUNT"));
                obj.setTotalamount(rs.getString("TOTALAMOUNT"));
                obj.setUsercheck(rs.getString("USERCHECK"));
                obj.setDateCheck(rs.getDate("DATECHECK"));
                obj.setRefCore(rs.getString("REFCORE"));
                obj.setID(rs.getBigDecimal("ID") != null ? rs.getBigDecimal("ID").toString() : "");
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
        finally {
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
     * @param status
     * @return
     * @throws SQLException
     */
    public ArrayList<?> CHECK_STATUS_IBT(String status) throws SQLException {
        try {
            ArrayList<String> p_args = new ArrayList<>();
            p_args.add(status);
            return JDBCEngine.executeQuery(CHECK_STATUS_IBT, p_args, connection);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pID
     * @param pUSER
     * @param pBRANCH_CODE
     * @param pSTATUS
     * @param pTYPEFUNCTION
     * @param pREFCORE
     * @throws SQLException
     */
    public int DUYET_CTTQ(String pID, String pUSER, String pBRANCH_CODE, String pSTATUS, String pTYPEFUNCTION, String pREFCORE) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(DUYET_CTTQ);
        try {
            calStmt.setString(1, pID);
            calStmt.setString(2, pUSER);
            calStmt.setString(3, pBRANCH_CODE);
            calStmt.setString(4, pSTATUS);
            calStmt.setString(5, pTYPEFUNCTION);
            calStmt.setString(6, pREFCORE);
            calStmt.registerOutParameter(7, OracleTypes.INTEGER);
            calStmt.execute();
            int rs = (int) calStmt.getObject(7);
            return rs;
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
     * @param id
     * @param userapprove
     * @param branch_approve
     * @param status
     * @param refcore
     * @throws SQLException
     */
    public void IBT_TUCHOI_CTTQ(String id, String userapprove, String branch_approve, String status, String refcore) throws SQLException {
        try {
            ArrayList<String> p_args = new ArrayList<>();
            p_args.add(userapprove);
            p_args.add(branch_approve);
            p_args.add(status);
            p_args.add(refcore);
            p_args.add(id);
            JDBCEngine.executeUpdate(IBT_TUCHOI_CTTQ, p_args, connection);
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
     * @param madv
     * @param trangthai
     * @param tungay
     * @param denngay
     * @param tknguon
     * @param tkdich
     * @return
     * @throws SQLException
     */
    public ArrayList<?> SEARCH_IBT(String madv, String trangthai, String tungay, String denngay, String tknguon, String tkdich) throws SQLException {
        try {
            ArrayList p_args = new ArrayList();
            p_args.add("%" + madv + "%");
            p_args.add("%" + trangthai + "%");
            p_args.add(tungay);
            p_args.add(denngay);
            p_args.add("%" + tknguon + "%");
            p_args.add("%" + tkdich + "%");
            return JDBCEngine.executeQuery(SEARCH_IBT, p_args, connection);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws Exception
     */
    public ArrayList<?> CHECK_TRANSFER(String ID) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(ID);
        return JDBCEngine.executeQuery(CHECK_TRANSFER, p_args, connection);
    }

    //SONN5: ADD
    final String DELETE_CTTQ = "begin PKG_IBT_COUNTER.deleteCTTQ(?,?); end;";
    final String GET_GL_BALANCE = "{ ? = call pkg_check_account.getGLBalance(?,?,?,?)}";

    /**
     *
     * @param pid
     * @return
     * @throws SQLException
     */
    public int DELETE_CTTQ_Dao(String pid
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(DELETE_CTTQ);
            calStmt.setString(1, pid);

            calStmt.registerOutParameter(2, OracleTypes.INTEGER);
            calStmt.execute();
            return calStmt.getInt(2);
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
    //son 28/10/2019

    /**
     *
     * @param pGL
     * @param brcode
     * @param ccy
     * @param type
     * @return
     * @throws SQLException
     */
    public float get_GL_BALANCE_DAO(String pGL, String brcode, String ccy, String type
    ) throws SQLException {

        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(GET_GL_BALANCE);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.FLOAT);
            calStmt.setString(2, pGL);
            calStmt.setString(3, brcode);
            calStmt.setString(4, ccy);
            calStmt.setString(5, type);
            calStmt.execute();
            return calStmt.getFloat(1);
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
