/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dto.HQ_BAOLANH_CHUNG;
import scb.com.vn.dbi.dto.HQ_BAOLANH_HDVD;
import scb.com.vn.dbi.dto.HQ_BAOLANH_TK;
import scb.com.vn.dbi.dto.HQ_DKNNT;
import scb.com.vn.dbi.dto.HQ_DKNNT_LIENHE;
import scb.com.vn.dbi.dto.HQ_DOICHIEU;
import scb.com.vn.dbi.dto.HQ_MSG;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_CQQLT;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_CQQLT_CT;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_HQ;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_HQ_GNT;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_HQ_GNTCT;

/**
 *
 * @author lydty
 */
public class HQDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(HQDAO.class);

    final String getTransID = "begin PKG_HAIQUAN.getTransID(?); end;";
    final String INSERT_NOPTIEN_HQ = "begin PKG_HAIQUAN.INSERT_NOPTIEN_HQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String INSERT_HQ_NOPTIEN_HQ_GNT = "begin PKG_HAIQUAN.INSERT_HQ_NOPTIEN_HQ_GNT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String INSERT_HQ_NOPTIEN_HQ_GNTCT = "begin PKG_HAIQUAN.INSERT_HQ_NOPTIEN_HQ_GNTCT(?,?,?,?,?,?); end;";
    final String HQ_NOPTIEN_HQ_GNTCT = "begin PKG_HAIQUAN.HQ_NOPTIEN_HQ_GNTCT(?,?,?,?,?,?); end;";
    final String NOPTIEN_CORE = "begin PKG_HAIQUAN.NOPTIEN_CORE(?,?,?,?); end;";
    final String INSERT_HQ_NOPTIEN_CQQLT = "begin PKG_HAIQUAN.INSERT_HQ_NOPTIEN_CQQLT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String INSERT_HQ_NOPTIEN_CQQLT_CT = "begin PKG_HAIQUAN.INSERT_HQ_NOPTIEN_CQQLT_CT(?,?,?,?,?,?,?); end;";
    final String APPROVETRANSFER = "begin PKG_HAIQUAN.APPROVETRANSFER(?,?,?,?,?); end;";
    final String INSERT_HQ_BAOLANH_TK = "begin PKG_HAIQUAN.INSERT_HQ_BAOLANH_TK(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String INSERT_HQ_BAOLANH_HDVD = "begin PKG_HAIQUAN.INSERT_HQ_BAOLANH_HDVD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String INSERT_HQ_BAOLANH_CHUNG = "begin PKG_HAIQUAN.INSERT_HQ_BAOLANH_CHUNG(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String SEND_MESSAGE = "begin PKG_HAIQUAN.SEND_MESSAGE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String getData = "begin PKG_HAIQUAN.getData(?,?,?,?); end;";
    final String selectKBNN = "begin PKG_HAIQUAN.selectKBNN(?,?); end;";
    final String selectCHUONG = "begin PKG_HAIQUAN.selectCHUONG(?,?); end;";
    final String selectNTK = "begin PKG_HAIQUAN.selectNTK(?,?); end;";
    final String selectCCY = "begin PKG_HAIQUAN.selectCCY(?,?); end;";
    final String getSOCT = "begin PKG_HAIQUAN.getSOCT(?); end;";
    final String getREQUESTID = "begin PKG_HAIQUAN.getREQUESTID(?,?,?); end;";
    final String searchNOPTIEN_HQ = "begin PKG_HAIQUAN.searchNOPTIEN_HQ(?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String updateNOPTIEN_HQ = "begin PKG_HAIQUAN.updateNOPTIEN_HQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String updateStatus = "begin PKG_HAIQUAN.updateStatus(?,?,?); end;";
    final String deleteData = "begin PKG_HAIQUAN.deleteData(?,?,?,?); end;";
    final String selectNDKT = "begin PKG_HAIQUAN.selectNDKT(?,?); end;";
    final String getSumRow = "begin PKG_HAIQUAN.getSumRow(?,?,?,?,?,?,?,?,?,?); end;";
    final String GETBGINFO = "begin PKG_HAIQUAN.GETBGINFO(?,?,?,?,?,?,?,?,?,?,?); end;";

    final String getDoichieu = "begin PKG_HAIQUAN.getDoichieu(?,?,?); end;";
    final String InsertDoiChieu = "begin PKG_HAIQUAN.InsertDoiChieu(?,?,?,?,?,?); end;";
    final String InsertKQDoiChieu = "begin PKG_HAIQUAN.InsertKQDoiChieu(?,?,?,?,?); end;";
    final String getSO_TN_CT = "begin PKG_HAIQUAN.getSO_TN_CT(?,?,?); end;";
    final String selectDANHMUC = "begin PKG_HAIQUAN.selectDANHMUC(?,?,?,?); end;";
    final String APPROVEBAOLANH = "begin PKG_HAIQUAN.APPROVEBAOLANH(?,?,?,?,?); end;";
    final String searchNOPTIEN_CQQLT = "begin PKG_HAIQUAN.searchNOPTIEN_CQQLT(?,?,?,?,?,?,?,?,?,?,?); end;";
    final String getSumRowNOPTIEN_CQQLT = "begin PKG_HAIQUAN.getSumRowNOPTIEN_CQQLT(?,?,?,?,?,?,?,?,?); end;";
    final String insertKQDOICHIEUHUY = "begin PKG_HAIQUAN.insertKQDOICHIEUHUY(?,?,?,?,?,?,?); end;";
    final String UPDATE_HQ_BAOLANH_TK = "begin PKG_HAIQUAN.UPDATE_HQ_BAOLANH_TK(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String UPDATE_HQ_BAOLANH_HDVD = "begin PKG_HAIQUAN.UPDATE_HQ_BAOLANH_HDVD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String UPDATE_HQ_BAOLANH_CHUNG = "begin PKG_HAIQUAN.UPDATE_HQ_BAOLANH_CHUNG(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String UPDATE_HQ_NOPTIEN_CQQLT = "begin PKG_HAIQUAN.UPDATE_HQ_NOPTIEN_CQQLT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String searchBAOLANH = "begin PKG_HAIQUAN.searchBAOLANH(?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String getSumSearchBAOLANH = "begin PKG_HAIQUAN.getSumSearchBAOLANH(?,?,?,?,?,?,?,?,?,?); end;";
    final String insertDM_LH = "begin PKG_HAIQUAN.insertDM_LH(?,?,?); end;";
    final String insertDM_HQ = "begin PKG_HAIQUAN.insertDM_HQ(?,?,?,?); end;";
    final String insertDM_KB = "begin PKG_HAIQUAN.insertDM_KB(?,?); end;";
    final String insertDM_ER = "begin PKG_HAIQUAN.insertDM_ER(?,?); end;";
    //HAI QUAN ONLINE- 09/2017
    final String INSERT_DKNNT = "begin PKG_HAIQUAN.INSERT_DKNNT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String DUYET_DKNNT = "begin PKG_HAIQUAN.DUYET_DKNNT(?,?,?,?,?,?); end;";
    final String INSERT_NNT_LIENHE = "begin PKG_HAIQUAN.INSERT_NNT_LIENHE(?,?,?); end;";
    final String getData213 = "begin PKG_HAIQUAN.getData213(?,?,?,?,?); end;";
    final String searchNNT = "begin PKG_HAIQUAN.searchNNT(?,?,?,?,?,?,?,?); end;";
    final String getData312 = "begin PKG_HAIQUAN.getData312(?,?); end;";
    final String UpdateDKNNT313 = "begin PKG_HAIQUAN.UpdateDKNNT313(?,?,?); end;";
    final String getTTLIENHE = "begin PKG_HAIQUAN.getTTLIENHE(?,?); end;";
    final String getSO_TN_CT_SCB = "begin PKG_HAIQUAN.getSO_TN_CT_SCB(?); end;";
    final String INSERT_NOPTIEN_HQ_ONLINE = "begin PKG_HAIQUAN.INSERT_NOPTIEN_HQ_ONLINE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String INSERT_GNT_ONLINE = "begin PKG_HAIQUAN.INSERT_GNT_ONLINE(?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String INSERT_NOPTIEN_CQT_ONINE = "begin PKG_HAIQUAN.INSERT_NOPTIEN_CQT_ONINE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) end;";
    final String NOPTIEN_CORE_ONLINE = "begin PKG_HAIQUAN.NOPTIEN_CORE_ONLINE(?,?,?,?,?,?,?,?,?);end;";
    final String getDataGNT_CT = "begin PKG_HAIQUAN.getDataGNT_CT(?,?);end;";
    final String getDataTOKHAI_CT = "begin PKG_HAIQUAN.getDataTOKHAI_CT(?,?);end;";
    final String getlistDataGNT = "begin PKG_HAIQUAN.getlistDataGNT(?);end;";
    final String getMaxDataCollated = "begin PKG_HAIQUAN.getMaxDataCollated(?);end;";
    final String UPDATE_DKNNT = "begin PKG_HAIQUAN.UPDATE_DKNNT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String SendGNTHQ = "begin PKG_HAIQUAN.SendGNTHQ(?); end;";
    final String getDataDKNNT = "begin PKG_HAIQUAN.getDataDKNNT(?,?); end;";
    final String check213 = "begin PKG_HAIQUAN.check213(?,?,?); end;";
    final String deleteTTLIENHE = "begin PKG_HAIQUAN.deleteTTLIENHE(?); end;";
    final String INSERT_NOPTIEN_HQ_ONLINE_201 = "begin PKG_HAIQUAN.INSERT_NOPTIEN_HQ_ONLINE_201(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
    final String UPDATE_HQ_ONLINE_201 = "begin PKG_HAIQUAN.UPDATE_HQ_ONLINE_201(?); end;";
    final String searchDKUQ = "begin PKG_HAIQUAN.searchDKUQ(?,?,?,?); end;";
    final String INSERT_HQ_NOPTIEN_HQ_GNT_201 = "begin PKG_HAIQUAN.INSERT_HQ_NOPTIEN_HQ_GNT_201(?,?,?,?,?,?,?,?,?,?,?,?,?); end;";

    /**
     *
     * @param ID
     * @return
     * @throws Exception
     */
    public ArrayList selectCHUONG(String ID) throws Exception {
        try {
            CallableStatement calStmt = connection.prepareCall(selectCHUONG);
            calStmt.setString(1, ID);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.execute();
            ResultSet rs = null;
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
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws Exception
     */
    public ArrayList selectKBNN(String ID) throws Exception {
        try {
            CallableStatement calStmt = connection.prepareCall(selectKBNN);
            calStmt.setString(1, ID);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.execute();
            ResultSet rs = null;
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
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws Exception
     */
    public ArrayList selectNTK(String ID) throws Exception {
        try {
            CallableStatement calStmt = connection.prepareCall(selectNTK);
            calStmt.setString(1, ID);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.execute();
            ResultSet rs = null;
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
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws Exception
     */
    public ArrayList selectCCY(String ID) throws Exception {
        try {
            CallableStatement calStmt = connection.prepareCall(selectCCY);
            calStmt.setString(1, ID);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.execute();
            ResultSet rs = null;
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
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public String getTransID() throws SQLException {
        try {
            CallableStatement calStmt = connection.prepareCall(getTransID);
            calStmt.registerOutParameter(1, Types.VARCHAR);
            calStmt.execute();
            return calStmt.getString(1);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public String getSOCT() throws SQLException {
        try {
            CallableStatement calStmt = connection.prepareCall(getSOCT);
            calStmt.registerOutParameter(1, Types.VARCHAR);
            calStmt.execute();
            return calStmt.getString(1);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public String[] INSERT_NOPTIEN_HQ(HQ_NOPTIEN_HQ obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_NOPTIEN_HQ);
        try {
            calStmt.setString(1, obj.getMA_NH_TH());
            calStmt.setString(2, obj.getTEN_NH_TH());
            calStmt.setString(3, obj.getMA_DV());
            calStmt.setString(4, obj.getMA_CHUONG());
            calStmt.setString(5, obj.getTEN_DV());
            calStmt.setString(6, obj.getMA_KB());
            calStmt.setString(7, obj.getTEN_KB());
            calStmt.setString(8, obj.getTKKB());
            calStmt.setString(9, obj.getMA_NTK());
            calStmt.setString(10, obj.getMA_HQ_PH());
            calStmt.setString(11, obj.getMA_HQ_CQT());
            calStmt.setString(12, obj.getKYHIEU_CT());
            calStmt.setString(13, obj.getSO_CT());
            calStmt.setString(14, obj.getLOAI_CT());
            calStmt.setString(15, obj.getSo_TN_CTS());
            calStmt.setString(16, obj.getNgay_TN_CTS());
            calStmt.setString(17, obj.getMA_NT());
            calStmt.setString(18, obj.getTY_GIA());
            calStmt.setString(19, obj.getSOTIEN_TO());
            calStmt.setString(20, obj.getDIENGIAI());
            calStmt.setString(21, obj.getTypeForm());
            calStmt.setString(22, obj.getTYPETRANS());
            calStmt.setString(23, obj.getMAKERID());
            calStmt.setString(24, obj.getBRACHID());
            calStmt.setString(25, obj.getACCOUNTNO());
            calStmt.setString(26, obj.getMA_CUC());
            calStmt.setString(27, obj.getTEN_CUC());
            calStmt.setString(28, obj.getREQUEST_ID());
            calStmt.setString(29, obj.getTen_HQ_PH());
            calStmt.setString(30, obj.getTen_NTK());
            calStmt.setString(31, obj.getDIACHI_DV());
            calStmt.setString(32, obj.getTEN_NNTHAY());
            calStmt.setString(33, obj.getDIACHI_NNTHAY());
            calStmt.setString(34, obj.getNGUOI_NOP());
            calStmt.setString(35, obj.getSOTIEN_TO_NT());
            calStmt.setString(36, obj.getSOURCEACCOUNTNAME());
            calStmt.registerOutParameter(37, Types.VARCHAR); //ma loi
            calStmt.registerOutParameter(38, Types.VARCHAR); //ma loi
            calStmt.registerOutParameter(39, Types.NUMERIC); //id
            calStmt.execute();

            String[] arrResult = new String[3];
            arrResult[0] = calStmt.getString(37);
            arrResult[1] = calStmt.getString(38);
            arrResult[2] = calStmt.getString(39);
            return arrResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public String[] INSERT_HQ_NOPTIEN_HQ_GNT(HQ_NOPTIEN_HQ_GNT obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_HQ_NOPTIEN_HQ_GNT);
        try {
            calStmt.setInt(1, obj.getIDMASTER());
            calStmt.setString(2, obj.getTTBUTTOAN());
            calStmt.setString(3, obj.getMA_HQ());
            calStmt.setString(4, obj.getMA_LH());
            calStmt.setString(5, obj.getNAM_DK());
            calStmt.setString(6, obj.getSO_TK());
            calStmt.setString(7, obj.getMA_LT());
            calStmt.setString(8, obj.getSOTIEN());
            calStmt.setBigDecimal(9, obj.getFEE());
            calStmt.setBigDecimal(10, obj.getVAT());
            calStmt.setString(11, obj.getTen_HQ());
            calStmt.setString(12, obj.getTen_LH());
            calStmt.setString(13, obj.getSOTIEN_NT());
            calStmt.setString(14, obj.getNGAY_DK());
            calStmt.registerOutParameter(15, Types.NUMERIC);
            calStmt.execute();

            String[] arrResult = new String[1];
            arrResult[0] = calStmt.getString(15);
            return arrResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @throws SQLException
     */
    public void INSERT_HQ_NOPTIEN_HQ_GNTCT(HQ_NOPTIEN_HQ_GNTCT obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_HQ_NOPTIEN_HQ_GNTCT);
        try {
            calStmt.setInt(1, obj.getIDMASTER());
            calStmt.setString(2, obj.getMA_ST());
            calStmt.setString(3, obj.getNDKT());
            calStmt.setString(4, obj.getSOTIEN_NT());
            calStmt.setString(5, obj.getSOTIEN_VND());
            calStmt.setString(6, obj.getTEN_NDKT());
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param PIDREF
     * @param PTYPE
     * @return
     * @throws SQLException
     */
    public String[] NOPTIEN_CORE(
            int PIDREF,
            String PTYPE //HQ/CQT

    ) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(NOPTIEN_CORE);
        try {
            calStmt.setInt(1, PIDREF);
            calStmt.setString(2, PTYPE);
            calStmt.registerOutParameter(3, Types.VARCHAR);
            calStmt.registerOutParameter(4, Types.VARCHAR);
            calStmt.execute();

            String[] arrResult = new String[2];
            arrResult[0] = calStmt.getString(3);
            arrResult[1] = calStmt.getString(4);
            return arrResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public String[] INSERT_HQ_NOPTIEN_CQQLT(HQ_NOPTIEN_CQQLT obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_HQ_NOPTIEN_CQQLT);
        try {
            calStmt.setString(1, obj.getKyHieu_CT());
            calStmt.setString(2, obj.getSO_CT());
            calStmt.setString(3, obj.getSO_HS());
            calStmt.setString(4, obj.getMA_DVQL());
            calStmt.setString(5, obj.getTEN_DVQL());
            calStmt.setString(6, obj.getKYHIEU_CT_PT());
            calStmt.setString(7, obj.getSO_CT_PT());
            calStmt.setString(8, obj.getNAM_CT_PT());
            calStmt.setString(9, obj.getMA_ST());
            calStmt.setString(10, obj.getTEN_DV());
            calStmt.setString(11, obj.getTT_KHAC());
            calStmt.setString(12, obj.getMA_NT());
            calStmt.setString(13, obj.getTYGIA());
            calStmt.setString(14, obj.getTONGTIEN_NT());
            calStmt.setString(15, obj.getTONGTIEN_VND());
            calStmt.setString(16, obj.getMA_NH_TH());
            calStmt.setString(17, obj.getTEN_NH_TH());
            calStmt.setString(18, obj.getTAIKHOAN_TH());
            calStmt.setString(19, obj.getTEN_TAIKHOAN_TH());
            calStmt.setString(20, obj.getMAKERID());
            calStmt.setString(21, obj.getBRANCHID());
            calStmt.setString(22, obj.getSOURCEACCOUNT());
            calStmt.setBigDecimal(23, obj.getFEE());
            calStmt.setBigDecimal(24, obj.getVAT());
            calStmt.setString(25, obj.getTYPEFORM());
            calStmt.setString(26, obj.getREQUEST_ID());
            calStmt.setString(27, obj.getDIACHI_DV());
            calStmt.setString(28, obj.getTEN_NNTHAY());
            calStmt.setString(29, obj.getDIACHI_NNTHAY());
            calStmt.setString(30, obj.getNGUOI_NOP());
            calStmt.setString(31, obj.getSOURCEACCOUNTNAME());
            calStmt.registerOutParameter(32, Types.VARCHAR); //PRESPONSECODE
            calStmt.registerOutParameter(33, Types.VARCHAR); //PRESPONSECODE
            calStmt.registerOutParameter(34, Types.NUMERIC); //ID
            calStmt.execute();

            String[] arrResult = new String[3];
            arrResult[0] = calStmt.getString(32);
            arrResult[1] = calStmt.getString(33);
            arrResult[2] = calStmt.getString(34);
            return arrResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @throws SQLException
     */
    public void INSERT_HQ_NOPTIEN_CQQLT_CT(
            HQ_NOPTIEN_CQQLT_CT obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_HQ_NOPTIEN_CQQLT_CT);
        try {
            calStmt.setString(1, obj.getSTT());
            calStmt.setString(2, obj.getNDKT());
            calStmt.setString(3, obj.getTEN_NDKT());
            calStmt.setString(4, obj.getSOTIEN_NT());
            calStmt.setString(5, obj.getSOTIEN_VND());
            calStmt.setString(6, obj.getGHICHU());
            calStmt.setInt(7, obj.getIDMASTER());
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param PIDREF
     * @param PTYPE
     * @param PCHECKERID
     * @return
     * @throws SQLException
     */
    public String[] APPROVETRANSFER(
            int PIDREF,
            String PTYPE,// HQ/CQT
            String PCHECKERID) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(APPROVETRANSFER);
        try {
            calStmt.setInt(1, PIDREF);
            calStmt.setString(2, PTYPE);
            calStmt.setString(3, PCHECKERID);
            calStmt.registerOutParameter(4, Types.VARCHAR); //PRESPONSECODE
            calStmt.registerOutParameter(5, Types.VARCHAR); //PRESPONSECODE

            calStmt.execute();
            String[] arrResult = new String[2];
            arrResult[0] = calStmt.getString(4);
            arrResult[1] = calStmt.getString(5);
            return arrResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @throws SQLException
     */
    public void SEND_MESSAGE(
            HQ_MSG obj
    ) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(SEND_MESSAGE);
        try {
            calStmt.setString(1, obj.getSENDER_CODE());
            calStmt.setString(2, obj.getSENDER_NAME());
            calStmt.setString(3, obj.getMESSAGE_VERSION());
            calStmt.setString(4, obj.getMESSAGE_TYPE());
            calStmt.setString(5, obj.getMESSAGE_NAME());
            calStmt.setString(6, obj.getTRANSACTION_DATE());
            calStmt.setString(7, obj.getTRANSACTION_ID());
            calStmt.setString(8, obj.getREQUEST_ID());
            calStmt.setString(9, obj.getMSGDATA());
            calStmt.setString(10, obj.getSTATUS()); //01: that bai; 00: thanh cong
            calStmt.setString(11, obj.getIDREF());
            calStmt.setString(12, obj.getERRORNUMBER());
            calStmt.setString(13, obj.getERRORMESSAGE());
            calStmt.setString(14, obj.getSO_TN_CT());
            calStmt.setString(15, obj.getNGAY_TN_CT());
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws SQLException
     */
    public List<HQ_NOPTIEN_HQ> getDataNOPTIEN_HQ(int pID, String pStatus) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getData);
        try {
            calStmt.setInt(1, pID);
            calStmt.setString(2, "301"); //301 hoac 302
            calStmt.setString(3, pStatus);
            calStmt.registerOutParameter(4, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(4);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_NOPTIEN_HQ> resultList = new ArrayList<HQ_NOPTIEN_HQ>();
            while (rs.next()) {
                HQ_NOPTIEN_HQ obj = new HQ_NOPTIEN_HQ();
                obj.setACCOUNTNO(rs.getString("SOURCEACCOUNT"));
                obj.setMA_NH_TH(rs.getString("MA_NH_TH"));
                obj.setTEN_NH_TH(rs.getString("TEN_NH_TH"));
                obj.setMA_DV(rs.getString("MA_DV"));
                obj.setMA_CHUONG(rs.getString("MA_CHUONG"));
                obj.setTEN_DV(rs.getString("TEN_DV"));
                obj.setMA_KB(rs.getString("MA_KB"));
                obj.setTEN_KB(rs.getString("TEN_KB"));
                obj.setTKKB(rs.getString("TKKB"));
                obj.setMA_NTK(rs.getString("MA_NTK"));
                obj.setMA_HQ_PH(rs.getString("MA_HQ_PH"));
                obj.setMA_HQ_CQT(rs.getString("MA_HQ_CQT"));
                obj.setKYHIEU_CT(rs.getString("KYHIEU_CT"));
                obj.setSO_CT(rs.getString("SO_CT"));
                obj.setLOAI_CT(rs.getString("LOAI_CT"));
                obj.setSo_TN_CTS(rs.getString("SO_TN_CTS"));
                obj.setNgay_TN_CTS(rs.getString("NGAY_TN_CTS"));
                obj.setNGAY_BN(rs.getString("NGAY_BN"));
                obj.setNGAY_BC(rs.getString("NGAY_BC"));
                obj.setNGAY_CT(rs.getString("NGAY_CT"));
                obj.setMA_NT(rs.getString("MA_NT"));
                obj.setTY_GIA(rs.getString("TY_GIA"));
                obj.setSOTIEN_TO(rs.getString("SOTIEN_TO"));
                obj.setSOTIEN_TO_NT(rs.getString("SOTIEN_TO_NT"));
                obj.setDIENGIAI(rs.getString("DIENGIAI"));
                obj.setTypeForm(rs.getString("TYPEFORM"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setMAKERID(rs.getString("MAKERID"));
                obj.setCHECKERID(rs.getString("CHECKERID"));
                obj.setBRACHID(rs.getString("BRANCHID"));
                obj.setMAKEDATE(rs.getString("MAKEDATE"));
                obj.setCHECKDATE(rs.getString("CHECKDATE"));
                obj.setCREATEDATE(rs.getString("CREATEDATE"));
                obj.setTYPETRANS(rs.getString("TYPETRANS"));
                obj.setMA_CUC(rs.getString("MA_CUC"));
                obj.setTEN_CUC(rs.getString("TEN_CUC"));
                obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                obj.setTEN_CHUONG(rs.getString("TEN_CHUONG"));
                obj.setTen_HQ_PH(rs.getString("Ten_HQ_PH"));

                obj.setTen_NTK(rs.getString("Ten_NTK"));
                obj.setID(Integer.valueOf(rs.getString("ID")));
                obj.setDIACHI_DV(rs.getString("DIACHI_DV"));
                obj.setTEN_NNTHAY(rs.getString("TEN_NNTHAY"));
                obj.setDIACHI_NNTHAY(rs.getString("DIACHI_NNTHAY"));
                obj.setNGUOI_NOP(rs.getString("NGUOI_NOP"));
                obj.setSOURCEACCOUNTNAME(rs.getString("SOURCEACCOUNTNAME"));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws SQLException
     */
    public List<HQ_NOPTIEN_HQ_GNT> getDataNOPTIEN_HQ_GNT(int pID, String pStatus) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getData);
        try {
            calStmt.setInt(1, pID);
            calStmt.setString(2, "GNT"); //301 hoac 302
            calStmt.setString(3, pStatus);
            calStmt.registerOutParameter(4, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(4);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_NOPTIEN_HQ_GNT> resultList = new ArrayList<HQ_NOPTIEN_HQ_GNT>();
            while (rs.next()) {
                HQ_NOPTIEN_HQ_GNT obj = new HQ_NOPTIEN_HQ_GNT();
                obj.setID(Integer.valueOf(rs.getString("ID")));
                obj.setIDMASTER(Integer.valueOf(rs.getString("IDMASTER")));
                obj.setTTBUTTOAN(rs.getString("TTBUTTOAN"));
                obj.setMA_HQ(rs.getString("MA_HQ"));
                obj.setMA_LH(rs.getString("MA_LH"));
                obj.setNAM_DK(rs.getString("NAM_DK"));
                obj.setSO_TK(rs.getString("SO_TK"));
                obj.setMA_LT(rs.getString("MA_LT"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setFEE(BigDecimal.valueOf(Double.valueOf(rs.getString("FEE"))));
                obj.setVAT(BigDecimal.valueOf(Double.valueOf(rs.getString("VAT"))));
                obj.setSOTIEN(rs.getString("SOTIEN"));
                obj.setREFCORE(rs.getString("TXNID"));
                obj.setERRMSGCORE(rs.getString("ERRMSG"));
                obj.setTen_HQ(rs.getString("Ten_HQ"));
                obj.setTen_LH(rs.getString("Ten_LH"));
                obj.setSOTIEN_NT(rs.getString("SOTIEN_NT"));
                obj.setNGAY_DK(rs.getString("NGAY_DK"));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws SQLException
     */
    public List<HQ_NOPTIEN_HQ_GNTCT> getDataNOPTIEN_HQ_GNTCT(int pID, String pStatus) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getData);
        try {
            calStmt.setInt(1, pID);
            calStmt.setString(2, "GNTCT");
            calStmt.setString(3, pStatus);
            calStmt.registerOutParameter(4, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(4);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_NOPTIEN_HQ_GNTCT> resultList = new ArrayList<HQ_NOPTIEN_HQ_GNTCT>();
            while (rs.next()) {
                HQ_NOPTIEN_HQ_GNTCT obj = new HQ_NOPTIEN_HQ_GNTCT();
                obj.setIDMASTER(Integer.valueOf(rs.getString("IDMASTER")));
                obj.setMA_ST(rs.getString("MA_ST"));
                obj.setNDKT(rs.getString("NDKT"));
                obj.setSOTIEN_NT(rs.getString("SOTIEN_NT"));
                obj.setSOTIEN_VND(rs.getString("SOTIEN_VND"));
                obj.setTEN_NDKT(rs.getString("TEN_NDKT"));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws SQLException
     */
    public List<HQ_NOPTIEN_CQQLT> getDataNOPTIEN_HQ_CQQLT(int pID, String pStatus) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getData);
        try {
            calStmt.setInt(1, pID);
            calStmt.setString(2, "303");
            calStmt.setString(3, pStatus);
            calStmt.registerOutParameter(4, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(4);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_NOPTIEN_CQQLT> resultList = new ArrayList<HQ_NOPTIEN_CQQLT>();
            while (rs.next()) {
                HQ_NOPTIEN_CQQLT obj = new HQ_NOPTIEN_CQQLT();
                obj.setKyHieu_CT(rs.getString("KYHIEU_CT"));
                obj.setSO_CT(rs.getString("SO_CT"));
                obj.setNGAY_CT(rs.getString("NGAY_CT"));
                obj.setNGAY_BN(rs.getString("NGAY_BN"));
                obj.setNGAY_BC(rs.getString("NGAY_BC"));
                obj.setSO_HS(rs.getString("SO_HS"));
                obj.setMA_DVQL(rs.getString("MA_DVQL"));
                obj.setTEN_DVQL(rs.getString("TEN_DVQL"));
                obj.setKYHIEU_CT_PT(rs.getString("KYHIEU_CT_PT"));
                obj.setSO_CT_PT(rs.getString("SO_CT_PT"));
                obj.setNAM_CT_PT(rs.getString("NAM_CT_PT"));
                obj.setMA_ST(rs.getString("MA_ST"));
                obj.setTEN_DV(rs.getString("TEN_DV"));
                obj.setTT_KHAC(rs.getString("TT_KHAC"));
                obj.setMA_NT(rs.getString("MA_NT"));
                obj.setTYGIA(rs.getString("TYGIA"));
                obj.setTONGTIEN_NT(rs.getString("TONGTIEN_NT"));
                obj.setTONGTIEN_VND(rs.getString("TONGTIEN_VND"));
                obj.setMA_NH_TH(rs.getString("MA_NH_TH"));
                obj.setTEN_NH_TH(rs.getString("TEN_NH_TH"));
                obj.setTAIKHOAN_TH(rs.getString("TAIKHOAN_TH"));
                obj.setTEN_TAIKHOAN_TH(rs.getString("TEN_TAIKHOAN_TH"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setMAKERID(rs.getString("MAKERID"));
                obj.setCHECKERID(rs.getString("CHECKERID"));
                obj.setBRANCHID(rs.getString("BRANCHID"));
                obj.setSOURCEACCOUNT(rs.getString("SOURCEACCOUNT"));
                obj.setFEE(BigDecimal.valueOf(Long.valueOf(rs.getString("FEE"))));
                obj.setVAT(BigDecimal.valueOf(Long.valueOf(rs.getString("VAT"))));
                obj.setTYPEFORM(rs.getString("TYPEFORM"));
                obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                obj.setID(Integer.valueOf(rs.getString("ID")));
                obj.setDIACHI_DV(rs.getString("DIACHI_DV"));
                obj.setTEN_NNTHAY(rs.getString("TEN_NNTHAY"));
                obj.setDIACHI_NNTHAY(rs.getString("DIACHI_NNTHAY"));
                obj.setNGUOI_NOP(rs.getString("NGUOI_NOP"));
                obj.setREFCORE(rs.getString("TXNID"));
                obj.setERRMSGCORE(rs.getString("ERRMSG"));
                obj.setSOURCEACCOUNTNAME(rs.getString("SOURCEACCOUNTNAME"));
                obj.setNGAY_DUYET(rs.getString("NGAY_DUYET"));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws SQLException
     */
    public List<HQ_NOPTIEN_CQQLT_CT> getDataNOPTIEN_HQ_CQQLT_CT(int pID, String pStatus) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getData);
        try {
            calStmt.setInt(1, pID);
            calStmt.setString(2, "303CT");
            calStmt.setString(3, pStatus);
            calStmt.registerOutParameter(4, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(4);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_NOPTIEN_CQQLT_CT> resultList = new ArrayList<HQ_NOPTIEN_CQQLT_CT>();
            while (rs.next()) {
                HQ_NOPTIEN_CQQLT_CT obj = new HQ_NOPTIEN_CQQLT_CT();
                obj.setID(Integer.valueOf(rs.getString("ID")));
                obj.setSTT(rs.getString("STT"));
                obj.setNDKT(rs.getString("NDKT"));
                obj.setTEN_NDKT(rs.getString("TEN_NDKT"));
                obj.setSOTIEN_NT(rs.getString("SOTIEN_NT"));
                obj.setSOTIEN_VND(rs.getString("SOTIEN_VND"));
                obj.setGHICHU(rs.getString("GHICHU"));
                obj.setIDMASTER(Integer.valueOf(rs.getString("IDMASTER")));

                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws SQLException
     */
    public List<HQ_BAOLANH_TK> getDataBAOLANH_TK(int pID, String pStatus) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getData);
        try {
            calStmt.setInt(1, pID);
            calStmt.setString(2, "401");
            calStmt.setString(3, pStatus);
            calStmt.registerOutParameter(4, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(4);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_BAOLANH_TK> resultList = new ArrayList<HQ_BAOLANH_TK>();
            while (rs.next()) {
                HQ_BAOLANH_TK obj = new HQ_BAOLANH_TK();
                obj.setMA_HQ(rs.getString("MA_HQ"));
                obj.setMA_LH(rs.getString("MA_LH"));
                obj.setSO_TK(rs.getString("SO_TK"));
                obj.setNGAY_DK(rs.getString("NGAY_DK"));
                obj.setMA_LT(rs.getString("MA_LT"));
                obj.setLOAI_CT(rs.getString("LOAI_CT"));
                obj.setKYHIEU_CT(rs.getString("KYHIEU_CT"));
                obj.setSO_CT(rs.getString("SO_CT"));
                obj.setNGAY_CT(rs.getString("NGAY_CT"));
                obj.setTTBUTTOAN(rs.getString("TTBUTTOAN"));
                obj.setSNBL(rs.getString("SNBL"));
                obj.setNGAY_HL(rs.getString("NGAY_HL"));
                obj.setNGAY_HHL(rs.getString("NGAY_HHL"));
                obj.setSOTIEN(rs.getString("SOTIEN"));
                obj.setDIENGIAI(rs.getString("DIENGIAI"));
                obj.setMAKERID(rs.getString("MAKERID"));
                obj.setCHECKERID(rs.getString("CHECKERID"));
                obj.setBRANCHID(rs.getString("BRANCHID"));
                obj.setBGNUMBER(rs.getString("BGNUMBER"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                obj.setMA_DV(rs.getString("MA_DV"));
                obj.setTEN_DV(rs.getString("TEN_DV"));
                obj.setMA_DV_DD(rs.getString("MA_DV_DD"));
                obj.setTEN_DV_DD(rs.getString("TEN_DV_DD"));
                obj.setMA_HQ_PH(rs.getString("MA_HQ_PH"));
                obj.setDIA_CHI(rs.getString("DIA_CHI"));
                obj.setTEN_HQ_PH(rs.getString("TEN_HQ_PH"));
                obj.setTEN_HQ(rs.getString("TEN_HQ"));
                obj.setNGAY_LAP(rs.getString("NGAY_LAP"));
                obj.setSO_TN_CT(rs.getString("SO_TN_CT"));
                obj.setNGAY_TN_CT(rs.getString("NGAY_TN_CT"));
                obj.setMSGTYPE(rs.getString("MESSAGE_TYPE"));
                obj.setDuNo_TO(rs.getString("DUNO_TO"));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws SQLException
     */
    public List<HQ_BAOLANH_CHUNG> getDataBAOLANH_CHUNG(int pID, String pStatus) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getData);
        try {
            calStmt.setInt(1, pID);
            calStmt.setString(2, "402");
            calStmt.setString(3, pStatus);
            calStmt.registerOutParameter(4, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(4);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_BAOLANH_CHUNG> resultList = new ArrayList<HQ_BAOLANH_CHUNG>();
            while (rs.next()) {
                HQ_BAOLANH_CHUNG obj = new HQ_BAOLANH_CHUNG();
                obj.setMA_DV(rs.getString("MA_DV"));
                obj.setTEN_DV(rs.getString("TEN_DV"));
                obj.setMA_DV_DD(rs.getString("MA_DV_DD"));
                obj.setTEN_DV_DD(rs.getString("TEN_DV_DD"));
                obj.setLOAI_CT(rs.getString("LOAI_CT"));
                obj.setKYHIEU_CT(rs.getString("KYHIEU_CT"));
                obj.setSO_CT(rs.getString("SO_CT"));
                obj.setNGAY_CT(rs.getString("NGAY_CT"));
                obj.setTTBUTTOAN(rs.getString("TTBUTTOAN"));
                obj.setNGAY_HL(rs.getString("NGAY_HL"));
                obj.setNGAY_HHL(rs.getString("NGAY_HHL"));
                obj.setSOTIEN(rs.getString("SOTIEN"));
                obj.setDIENGIAI(rs.getString("DIENGIAI"));
                obj.setMAKERID(rs.getString("MAKERID"));
                obj.setBRANCHID(rs.getString("BRANCHID"));
                obj.setBGNUMBER(rs.getString("BGNUMBER"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setCHECKERID(rs.getString("CHECKERID"));
                obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                obj.setDIA_CHI(rs.getString("DIA_CHI"));
                obj.setTEN_HQ_PH(rs.getString("TEN_HQ_PH"));
                obj.setTEN_HQ(rs.getString("TEN_HQ"));
                obj.setNGAY_LAP(rs.getString("NGAY_LAP"));
                obj.setSO_TN_CT(rs.getString("SO_TN_CT"));
                obj.setNGAY_TN_CT(rs.getString("NGAY_TN_CT"));
                obj.setMSGTYPE(rs.getString("MESSAGE_TYPE"));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws SQLException
     */
    public List<HQ_BAOLANH_HDVD> getDataBAOLANH_HDVD(int pID, String pStatus) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getData);
        try {
            calStmt.setInt(1, pID);
            calStmt.setString(2, "403");
            calStmt.setString(3, pStatus);
            calStmt.registerOutParameter(4, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(4);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_BAOLANH_HDVD> resultList = new ArrayList<HQ_BAOLANH_HDVD>();
            while (rs.next()) {
                HQ_BAOLANH_HDVD obj = new HQ_BAOLANH_HDVD();
                obj.setMA_DV(rs.getString("MA_DV"));
                obj.setTEN_DV(rs.getString("TEN_DV"));
                obj.setMA_DV_DD(rs.getString("MA_DV_DD"));
                obj.setTEN_DV_DD(rs.getString("TEN_DV_DD"));
                obj.setMA_HQ_KB(rs.getString("MA_HQ_KB"));
                obj.setSO_HD(rs.getString("SO_HD"));
                obj.setNGAY_HD(rs.getString("NGAY_HD"));
                obj.setSO_VD_01(rs.getString("SO_VD_01"));
                obj.setNGAY_VD_01(rs.getString("NGAY_VD_01"));
                obj.setSO_VD_02(rs.getString("SO_VD_02"));
                obj.setNGAY_VD_02(rs.getString("NGAY_VD_02"));
                obj.setSO_VD_03(rs.getString("SO_VD_03"));
                obj.setNGAY_VD_03(rs.getString("NGAY_VD_03"));
                obj.setSO_VD_04(rs.getString("SO_VD_04"));
                obj.setNGAY_VD_04(rs.getString("NGAY_VD_04"));
                obj.setSO_VD_05(rs.getString("SO_VD_05"));
                obj.setNGAY_VD_05(rs.getString("NGAY_VD_05"));
                obj.setLOAI_CT(rs.getString("LOAI_CT"));
                obj.setKYHIEU_CT(rs.getString("KYHIEU_CT"));
                obj.setSO_CT(rs.getString("SO_CT"));
                obj.setNGAY_CT(rs.getString("NGAY_CT"));
                obj.setTTBUTTOAN(rs.getString("TTBUTTOAN"));
                obj.setSNBL(rs.getString("SNBL"));
                obj.setNGAY_HL(rs.getString("NGAY_HL"));
                obj.setNGAY_HHL(rs.getString("NGAY_HHL"));
                obj.setSOTIEN(rs.getString("SOTIEN"));
                obj.setDIENGIAI(rs.getString("DIENGIAI"));
                obj.setMAKERID(rs.getString("MAKERID"));
                obj.setBRANCHID(rs.getString("BRANCHID"));
                obj.setBGNUMBER(rs.getString("BGNUMBER"));
                obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setCHECKERID(rs.getString("CHECKERID"));
                obj.setDIA_CHI(rs.getString("DIA_CHI"));
                obj.setTEN_HQ_PH(rs.getString("TEN_HQ_PH"));
                obj.setTEN_HQ(rs.getString("TEN_HQ"));
                obj.setNGAY_LAP(rs.getString("NGAY_LAP"));
                obj.setSO_TN_CT(rs.getString("SO_TN_CT"));
                obj.setNGAY_TN_CT(rs.getString("NGAY_TN_CT"));
                obj.setMSGTYPE(rs.getString("MESSAGE_TYPE"));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public String[] INSERT_HQ_BAOLANH_TK(HQ_BAOLANH_TK obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_HQ_BAOLANH_TK);
        try {
            calStmt.setString(1, obj.getMA_DV());
            calStmt.setString(2, obj.getTEN_DV());
            calStmt.setString(3, obj.getMA_DV_DD());
            calStmt.setString(4, obj.getTEN_DV_DD());
            calStmt.setString(5, obj.getMA_HQ_PH());
            calStmt.setString(6, obj.getMA_HQ());
            calStmt.setString(7, obj.getMA_LH());
            calStmt.setString(8, obj.getSO_TK());
            calStmt.setString(9, obj.getNGAY_DK());
            calStmt.setString(10, obj.getMA_LT());
            calStmt.setString(11, obj.getLOAI_CT());
            calStmt.setString(12, obj.getKYHIEU_CT());
            calStmt.setString(13, obj.getSO_CT());
            calStmt.setString(14, obj.getTTBUTTOAN());
            calStmt.setString(15, obj.getSNBL());
            calStmt.setString(16, obj.getNGAY_HL());
            calStmt.setString(17, obj.getNGAY_HHL());
            calStmt.setString(18, obj.getSOTIEN());
            calStmt.setString(19, obj.getDIENGIAI());
            calStmt.setString(20, obj.getMAKERID());
            calStmt.setString(21, obj.getBRANCHID());
            calStmt.setString(22, obj.getBGNUMBER());
            calStmt.setString(23, obj.getDIA_CHI());
            calStmt.setString(24, obj.getTEN_HQ_PH());
            calStmt.setString(25, obj.getTEN_HQ());
            calStmt.setString(26, obj.getDuNo_TO());
            calStmt.registerOutParameter(27, Types.VARCHAR);
            calStmt.registerOutParameter(28, Types.VARCHAR);
            calStmt.registerOutParameter(29, Types.VARCHAR);
            calStmt.execute();
            String[] kq = new String[3];
            kq[0] = calStmt.getString(27);
            kq[1] = calStmt.getString(28);
            kq[2] = calStmt.getString(29);
            return kq;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public String[] INSERT_HQ_BAOLANH_CHUNG(HQ_BAOLANH_CHUNG obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_HQ_BAOLANH_CHUNG);
        try {
            calStmt.setString(1, obj.getMA_DV());
            calStmt.setString(2, obj.getTEN_DV());
            calStmt.setString(3, obj.getMA_DV_DD());
            calStmt.setString(4, obj.getTEN_DV_DD());
            calStmt.setString(5, obj.getLOAI_CT());
            calStmt.setString(6, obj.getKYHIEU_CT());
            calStmt.setString(7, obj.getSO_CT());
            calStmt.setString(8, obj.getTTBUTTOAN());
            calStmt.setString(9, obj.getNGAY_HL());
            calStmt.setString(10, obj.getNGAY_HHL());
            calStmt.setString(11, obj.getSOTIEN());
            calStmt.setString(12, obj.getDIENGIAI());
            calStmt.setString(13, obj.getMAKERID());
            calStmt.setString(14, obj.getBRANCHID());
            calStmt.setString(15, obj.getBGNUMBER());
            calStmt.setString(16, obj.getDIA_CHI());
            calStmt.setString(17, obj.getTEN_HQ_PH());
            calStmt.setString(18, obj.getTEN_HQ());
            calStmt.registerOutParameter(19, Types.VARCHAR);
            calStmt.registerOutParameter(20, Types.VARCHAR);
            calStmt.registerOutParameter(21, Types.VARCHAR);
            calStmt.execute();
            String[] kq = new String[3];
            kq[0] = calStmt.getString(19);
            kq[1] = calStmt.getString(20);
            kq[2] = calStmt.getString(21);
            return kq;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public String[] INSERT_HQ_BAOLANH_HDVD(HQ_BAOLANH_HDVD obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_HQ_BAOLANH_HDVD);
        try {
            calStmt.setString(1, obj.getMA_DV());
            calStmt.setString(2, obj.getTEN_DV());
            calStmt.setString(3, obj.getMA_DV_DD());
            calStmt.setString(4, obj.getTEN_DV_DD());
            calStmt.setString(5, obj.getMA_HQ_KB());
            calStmt.setString(6, obj.getSO_HD());
            calStmt.setString(7, obj.getNGAY_HD());
            calStmt.setString(8, obj.getSO_VD_01());
            calStmt.setString(9, obj.getNGAY_VD_01());
            calStmt.setString(10, obj.getSO_VD_02());
            calStmt.setString(11, obj.getNGAY_VD_02());
            calStmt.setString(12, obj.getSO_VD_03());
            calStmt.setString(13, obj.getNGAY_VD_03());
            calStmt.setString(14, obj.getSO_VD_04());
            calStmt.setString(15, obj.getNGAY_VD_04());
            calStmt.setString(16, obj.getSO_VD_05());
            calStmt.setString(17, obj.getNGAY_VD_05());
            calStmt.setString(18, obj.getLOAI_CT());
            calStmt.setString(19, obj.getKYHIEU_CT());
            calStmt.setString(20, obj.getSO_CT());
            calStmt.setString(21, obj.getTTBUTTOAN());
            calStmt.setString(22, obj.getSNBL());
            calStmt.setString(23, obj.getNGAY_HL());
            calStmt.setString(24, obj.getNGAY_HHL());
            calStmt.setString(25, obj.getSOTIEN());
            calStmt.setString(26, obj.getDIENGIAI());
            calStmt.setString(27, obj.getMAKERID());
            calStmt.setString(28, obj.getBRANCHID());
            calStmt.setString(29, obj.getBGNUMBER());
            calStmt.setString(30, obj.getDIA_CHI());
            calStmt.setString(31, obj.getTEN_HQ_PH());
            calStmt.setString(32, obj.getTEN_HQ());
            calStmt.registerOutParameter(33, Types.VARCHAR);
            calStmt.registerOutParameter(34, Types.VARCHAR);
            calStmt.registerOutParameter(35, Types.VARCHAR);
            calStmt.execute();
            String[] kq = new String[3];
            kq[0] = calStmt.getString(33);
            kq[1] = calStmt.getString(34);
            kq[2] = calStmt.getString(35);
            return kq;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @param pMSGTYPE
     * @return
     * @throws SQLException
     */
    public String getREQUESTID(String pID, String pMSGTYPE) throws SQLException {
        try {
            CallableStatement calStmt = connection.prepareCall(getREQUESTID);
            calStmt.setString(1, pID);
            calStmt.setString(2, pMSGTYPE);
            calStmt.registerOutParameter(3, Types.VARCHAR);
            calStmt.execute();
            return calStmt.getString(3);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pMADV
     * @param pSO_TK
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param TypeTrans
     * @return
     * @throws SQLException
     */
    public int getSumRow(String pMADV,
            String pSO_TK,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            String pSO_CT,
            String pKYHIEU_CT,
            String TypeTrans) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getSumRow);
        try {
            calStmt.setString(1, pMADV);
            calStmt.setString(2, pSO_TK);
            if (pFromdate != null) {
                java.sql.Date sqlFromdate = new java.sql.Date(pFromdate.getTime());
                calStmt.setDate(3, sqlFromdate);
            } else {
                calStmt.setDate(3, null);
            }
            if (pTodate != null) {
                java.sql.Date sqlTodate = new java.sql.Date(pTodate.getTime());
                calStmt.setDate(4, sqlTodate);
            } else {
                calStmt.setDate(4, null);
            }
            calStmt.setString(5, pBranchCode);
            calStmt.setString(6, pStatus);
            calStmt.setString(7, pSO_CT);
            calStmt.setString(8, pKYHIEU_CT);
            calStmt.setString(9, TypeTrans);
            calStmt.registerOutParameter(10, OracleTypes.NUMBER);
            calStmt.execute();
            return calStmt.getInt(10);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pMADV
     * @param pSO_TK
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param FromRow
     * @param ToRow
     * @param TypeTrans
     * @return
     * @throws SQLException
     */
    public List<HQ_NOPTIEN_HQ> searchNOPTIEN_HQ(String pMADV,
            String pSO_TK,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            String pSO_CT,
            String pKYHIEU_CT,
            int FromRow,
            int ToRow,
            String TypeTrans) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(searchNOPTIEN_HQ);
        try {
            calStmt.setString(1, pMADV);
            calStmt.setString(2, pSO_TK);
            if (pFromdate != null) {
                java.sql.Date sqlFromdate = new java.sql.Date(pFromdate.getTime());
                calStmt.setDate(3, sqlFromdate);
            } else {
                calStmt.setDate(3, null);
            }
            if (pTodate != null) {
                java.sql.Date sqlTodate = new java.sql.Date(pTodate.getTime());
                calStmt.setDate(4, sqlTodate);
            } else {
                calStmt.setDate(4, null);
            }
            calStmt.setString(5, pBranchCode);
            calStmt.setString(6, pStatus);
            calStmt.setString(7, pSO_CT);
            calStmt.setString(8, pKYHIEU_CT);
            calStmt.setInt(9, FromRow);
            calStmt.setInt(10, ToRow);
            calStmt.setString(11, TypeTrans);
            calStmt.registerOutParameter(12, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(12);
            List<HQ_NOPTIEN_HQ> resultList = new ArrayList<HQ_NOPTIEN_HQ>();
            while (rs.next()) {
                HQ_NOPTIEN_HQ obj = new HQ_NOPTIEN_HQ();
                obj.setACCOUNTNO(rs.getString("SOURCEACCOUNT"));
                obj.setMA_NH_TH(rs.getString("MA_NH_TH"));
                obj.setTEN_NH_TH(rs.getString("TEN_NH_TH"));
                obj.setMA_DV(rs.getString("MA_DV"));
                obj.setMA_CHUONG(rs.getString("MA_CHUONG"));
                obj.setTEN_DV(rs.getString("TEN_DV"));
                obj.setMA_KB(rs.getString("MA_KB"));
                obj.setTEN_KB(rs.getString("TEN_KB"));
                obj.setTKKB(rs.getString("TKKB"));
                obj.setMA_NTK(rs.getString("MA_NTK"));
                obj.setMA_HQ_PH(rs.getString("MA_HQ_PH"));
                obj.setMA_HQ_CQT(rs.getString("MA_HQ_CQT"));
                obj.setKYHIEU_CT(rs.getString("KYHIEU_CT"));
                obj.setSO_CT(rs.getString("SO_CT"));
                obj.setLOAI_CT(rs.getString("LOAI_CT"));
                obj.setSo_TN_CTS(rs.getString("SO_TN_CTS"));
                obj.setNgay_TN_CTS(rs.getString("NGAY_TN_CTS"));
                obj.setNGAY_BN(rs.getString("NGAY_BN"));
                obj.setNGAY_BC(rs.getString("NGAY_BC"));
                obj.setNGAY_CT(rs.getString("NGAY_CT"));
                obj.setMA_NT(rs.getString("MA_NT"));
                obj.setTY_GIA(rs.getString("TY_GIA"));
                obj.setSOTIEN_TO(rs.getString("SOTIEN_TO"));
                obj.setSOTIEN_TO_NT(rs.getString("SOTIEN_TO_NT"));
                obj.setDIENGIAI(rs.getString("DIENGIAI"));
                obj.setTypeForm(rs.getString("TYPEFORM"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setMAKERID(rs.getString("MAKERID"));
                obj.setCHECKERID(rs.getString("CHECKERID"));
                obj.setBRACHID(rs.getString("BRANCHID"));
                obj.setMAKEDATE(rs.getString("MAKEDATE"));
                obj.setCHECKDATE(rs.getString("CHECKDATE"));
                obj.setCREATEDATE(rs.getString("CREATEDATE"));
                obj.setTYPETRANS(rs.getString("TYPETRANS"));
                obj.setMA_CUC(rs.getString("MA_CUC"));
                obj.setTEN_CUC(rs.getString("TEN_CUC"));
                obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                obj.setTEN_CHUONG(rs.getString("TEN_CHUONG"));
                obj.setID(Integer.valueOf(rs.getString("ID")));
                obj.setSOURCEACCOUNTNAME(rs.getString("SOURCEACCOUNTNAME"));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @throws SQLException
     */
    public void updateNOPTIEN_HQ(HQ_NOPTIEN_HQ obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(updateNOPTIEN_HQ);
        try {
            calStmt.setInt(1, obj.getID());
            calStmt.setString(2, obj.getMA_NH_TH());
            calStmt.setString(3, obj.getTEN_NH_TH());
            calStmt.setString(4, obj.getMA_DV());
            calStmt.setString(5, obj.getMA_CHUONG());
            calStmt.setString(6, obj.getTEN_DV());
            calStmt.setString(7, obj.getMA_KB());
            calStmt.setString(8, obj.getTEN_KB());
            calStmt.setString(9, obj.getTKKB());
            calStmt.setString(10, obj.getMA_NTK());
            calStmt.setString(11, obj.getMA_HQ_PH());
            calStmt.setString(12, obj.getMA_HQ_CQT());
            calStmt.setString(13, obj.getKYHIEU_CT());
            calStmt.setString(14, obj.getSO_CT());
            calStmt.setString(15, obj.getLOAI_CT());
            calStmt.setString(16, obj.getSo_TN_CTS());
            calStmt.setString(17, obj.getNgay_TN_CTS());
            calStmt.setString(18, obj.getMA_NT());
            calStmt.setString(19, obj.getTY_GIA());
            calStmt.setString(20, obj.getSOTIEN_TO());
            calStmt.setString(21, obj.getDIENGIAI());
            calStmt.setString(22, obj.getTypeForm());
            calStmt.setString(23, obj.getMAKERID());
            calStmt.setString(24, obj.getBRACHID());
            calStmt.setString(25, obj.getACCOUNTNO());
            calStmt.setString(26, obj.getMA_CUC());
            calStmt.setString(27, obj.getTEN_CUC());
            calStmt.setString(28, obj.getSOTIEN_TO_NT());
            calStmt.setString(29, obj.getSOURCEACCOUNTNAME());
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @param pMsgType
     * @param pStatus
     * @throws SQLException
     */
    public void updateStatus(int pID, String pMsgType, String pStatus) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(updateStatus);
        try {
            calStmt.setInt(1, pID);
            calStmt.setString(2, pMsgType);
            calStmt.setString(3, pStatus);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @param pMsgType
     * @param pMakerID
     * @param pBranchID
     * @throws SQLException
     */
    public void deleteData(int pID, String pMsgType,
            String pMakerID,
            String pBranchID) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(deleteData);
        try {
            calStmt.setInt(1, pID);
            calStmt.setString(2, pMsgType);
            calStmt.setString(3, pMakerID);
            calStmt.setString(4, pBranchID);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws Exception
     */
    public ArrayList selectNDKT(String ID) throws Exception {
        try {
            CallableStatement calStmt = connection.prepareCall(selectNDKT);
            calStmt.setString(1, ID);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(2);
            int numcols = rs.getMetaData().getColumnCount();
            ArrayList result = new ArrayList();
            while (rs.next()) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("MA_NDKT", rs.getString("MA_NDKT"));
                h.put("TEN_NDKT", rs.getString("TEN_NDKT"));
                h.put("MA_ST", rs.getString("MA_ST"));
                result.add(h);
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param BGNUMBER
     * @return
     * @throws SQLException
     */
    public String[] GETBGINFO(String BGNUMBER) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(GETBGINFO);
        try {
            calStmt.setString(1, BGNUMBER);
            calStmt.registerOutParameter(2, Types.VARCHAR);
            calStmt.registerOutParameter(3, Types.VARCHAR);
            calStmt.registerOutParameter(4, Types.VARCHAR);
            calStmt.registerOutParameter(5, Types.VARCHAR);
            calStmt.registerOutParameter(6, Types.VARCHAR);
            calStmt.registerOutParameter(7, Types.VARCHAR);
            calStmt.registerOutParameter(8, Types.VARCHAR);
            calStmt.registerOutParameter(9, Types.VARCHAR);
            calStmt.registerOutParameter(10, Types.VARCHAR);
            calStmt.registerOutParameter(11, Types.VARCHAR);
            calStmt.execute();
            String[] arrResult = new String[10];
            arrResult[0] = calStmt.getString(2);
            arrResult[1] = calStmt.getString(3);
            arrResult[2] = calStmt.getString(4);
            arrResult[3] = calStmt.getString(5);
            arrResult[4] = calStmt.getString(6);
            arrResult[5] = calStmt.getString(7);
            arrResult[6] = calStmt.getString(8);
            arrResult[7] = calStmt.getString(9);
            arrResult[8] = calStmt.getString(10);
            arrResult[9] = calStmt.getString(11);
            return arrResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param MsgType
     * @param pDate
     * @return
     * @throws SQLException
     */
    public List<HQ_DOICHIEU> getDataDoichieu(String MsgType, Date pDate) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getDoichieu);
        try {
            calStmt.setString(1, MsgType);
            java.sql.Date sqlTodate = new java.sql.Date(pDate.getTime());
            calStmt.setDate(2, sqlTodate);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(3);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_DOICHIEU> resultList = new ArrayList<HQ_DOICHIEU>();
            while (rs.next()) {
                HQ_DOICHIEU objDC = new HQ_DOICHIEU();
                objDC.setMsgType(MsgType);
                objDC.setSO_TN_CT(rs.getString("SO_TN_CT"));
                objDC.setNgay_TN_CT(rs.getString("Ngay_TN_CT"));
                objDC.setTransaction_id(rs.getString("Transaction_id"));
                if (MsgType.equals("801") || MsgType.equals("802") || MsgType.equals("807")) {
                    HQ_NOPTIEN_HQ obj = new HQ_NOPTIEN_HQ();
                    obj.setACCOUNTNO(rs.getString("SOURCEACCOUNT"));
                    obj.setMA_NH_TH(rs.getString("MA_NH_TH"));
                    obj.setTEN_NH_TH(rs.getString("TEN_NH_TH"));
                    obj.setMA_DV(rs.getString("MA_DV"));
                    obj.setMA_CHUONG(rs.getString("MA_CHUONG"));
                    obj.setTEN_DV(rs.getString("TEN_DV"));
                    obj.setMA_KB(rs.getString("MA_KB"));
                    obj.setTEN_KB(rs.getString("TEN_KB"));
                    obj.setTKKB(rs.getString("TKKB"));
                    obj.setMA_NTK(rs.getString("MA_NTK"));
                    obj.setMA_HQ_PH(rs.getString("MA_HQ_PH"));
                    obj.setMA_HQ_CQT(rs.getString("MA_HQ_CQT"));
                    obj.setKYHIEU_CT(rs.getString("KYHIEU_CT"));
                    obj.setSO_CT(rs.getString("SO_CT"));
                    obj.setLOAI_CT(rs.getString("LOAI_CT"));
                    obj.setSo_TN_CTS(rs.getString("SO_TN_CTS"));
                    obj.setNgay_TN_CTS(rs.getString("NGAY_TN_CTS"));
                    obj.setNGAY_BN(rs.getString("NGAY_BN"));
                    obj.setNGAY_BC(rs.getString("NGAY_BC"));
                    obj.setNGAY_CT(rs.getString("NGAY_CT"));
                    obj.setMA_NT(rs.getString("MA_NT"));
                    obj.setTY_GIA(rs.getString("TY_GIA"));
                    obj.setSOTIEN_TO(rs.getString("SOTIEN_TO"));
                    obj.setDIENGIAI(rs.getString("DIENGIAI"));
                    obj.setTypeForm(rs.getString("TYPEFORM"));
                    obj.setSTATUS(rs.getString("STATUS"));
                    obj.setMAKERID(rs.getString("MAKERID"));
                    obj.setCHECKERID(rs.getString("CHECKERID"));
                    obj.setBRACHID(rs.getString("BRANCHID"));
                    obj.setMAKEDATE(rs.getString("MAKEDATE"));
                    obj.setCHECKDATE(rs.getString("CHECKDATE"));
                    obj.setCREATEDATE(rs.getString("CREATEDATE"));
                    obj.setTYPETRANS(rs.getString("TYPETRANS"));
                    obj.setMA_CUC(rs.getString("MA_CUC"));
                    obj.setTEN_CUC(rs.getString("TEN_CUC"));
                    obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                    obj.setTEN_CHUONG(rs.getString("TEN_CHUONG"));
                    obj.setTen_HQ_PH(rs.getString("TEN_HQ_PH"));
                    obj.setTen_NTK(rs.getString("TEN_NTK"));
                    obj.setID(Integer.valueOf(rs.getString("ID")));
                    obj.setTaiKhoan_TH(rs.getString("SOURCEACCOUNT"));
                    obj.setTen_TaiKhoan_TH(rs.getString("SOURCEACCOUNTNAME"));
                    obj.setNgayLap_CT(rs.getString("NGAYLAP_CT"));
                    obj.setNgayTruyen_CT(rs.getString("NGAYTRUYEN_CT"));
                    obj.setMa_ST(rs.getString("MA_ST"));
                    obj.setSo_CMT(rs.getString("SO_CMT"));
                    obj.setTen_NNT(rs.getString("TEN_NNT"));
                    obj.setDiaChi(rs.getString("DIACHI"));
                    objDC.setObj801(obj);
                } else if (MsgType.equals("803")) {
                    HQ_NOPTIEN_CQQLT obj = new HQ_NOPTIEN_CQQLT();
                    obj.setKyHieu_CT(rs.getString("KYHIEU_CT"));
                    obj.setSO_CT(rs.getString("SO_CT"));
                    obj.setNGAY_CT(rs.getString("NGAY_CT"));
                    obj.setNGAY_BN(rs.getString("NGAY_BN"));
                    obj.setNGAY_BC(rs.getString("NGAY_BC"));
                    obj.setSO_HS(rs.getString("SO_HS"));
                    obj.setMA_DVQL(rs.getString("MA_DVQL"));
                    obj.setTEN_DVQL(rs.getString("TEN_DVQL"));
                    obj.setKYHIEU_CT_PT(rs.getString("KYHIEU_CT_PT"));
                    obj.setSO_CT_PT(rs.getString("SO_CT_PT"));
                    obj.setNAM_CT_PT(rs.getString("NAM_CT_PT"));
                    obj.setMA_ST(rs.getString("MA_ST"));
                    obj.setTEN_DV(rs.getString("TEN_DV"));
                    obj.setTT_KHAC(rs.getString("TT_KHAC"));
                    obj.setMA_NT(rs.getString("MA_NT"));
                    obj.setTYGIA(rs.getString("TYGIA"));
                    obj.setTONGTIEN_NT(rs.getString("TONGTIEN_NT"));
                    obj.setTONGTIEN_VND(rs.getString("TONGTIEN_VND"));
                    obj.setMA_NH_TH(rs.getString("MA_NH_TH"));
                    obj.setTEN_NH_TH(rs.getString("TEN_NH_TH"));
                    obj.setTAIKHOAN_TH(rs.getString("TAIKHOAN_TH"));
                    obj.setTEN_TAIKHOAN_TH(rs.getString("TEN_TAIKHOAN_TH"));
                    obj.setSTATUS(rs.getString("STATUS"));
                    obj.setMAKERID(rs.getString("MAKERID"));
                    obj.setCHECKERID(rs.getString("CHECKERID"));
                    obj.setBRANCHID(rs.getString("BRANCHID"));
                    obj.setSOURCEACCOUNT(rs.getString("SOURCEACCOUNT"));
                    obj.setFEE(BigDecimal.valueOf(Long.valueOf(rs.getString("FEE"))));
                    obj.setVAT(BigDecimal.valueOf(Long.valueOf(rs.getString("VAT"))));
                    obj.setTYPEFORM(rs.getString("TYPEFORM"));
                    obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                    obj.setID(Integer.valueOf(rs.getString("ID")));
                    obj.setDIACHI_DV(rs.getString("DIACHI_DV"));
                    objDC.setObj803(obj);
                } else if (MsgType.equals("804")) {
                    HQ_BAOLANH_TK obj = new HQ_BAOLANH_TK();
                    obj.setMA_HQ(rs.getString("MA_HQ"));
                    obj.setMA_LH(rs.getString("MA_LH"));
                    obj.setSO_TK(rs.getString("SO_TK"));
                    obj.setNGAY_DK(rs.getString("NGAY_DK"));
                    obj.setMA_LT(rs.getString("MA_LT"));
                    obj.setLOAI_CT(rs.getString("LOAI_CT"));
                    obj.setKYHIEU_CT(rs.getString("KYHIEU_CT"));
                    obj.setSO_CT(rs.getString("SO_CT"));
                    obj.setNGAY_CT(rs.getString("NGAY_CT"));
                    obj.setTTBUTTOAN(rs.getString("TTBUTTOAN"));
                    obj.setSNBL(rs.getString("SNBL"));
                    obj.setNGAY_HL(rs.getString("NGAY_HL"));
                    obj.setNGAY_HHL(rs.getString("NGAY_HHL"));
                    obj.setSOTIEN(rs.getString("SOTIEN"));
                    obj.setDIENGIAI(rs.getString("DIENGIAI"));
                    obj.setMAKERID(rs.getString("MAKERID"));
                    obj.setCHECKERID(rs.getString("CHECKERID"));
                    obj.setBRANCHID(rs.getString("BRANCHID"));
                    obj.setBGNUMBER(rs.getString("BGNUMBER"));
                    obj.setSTATUS(rs.getString("STATUS"));
                    obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                    obj.setMA_DV(rs.getString("MA_DV"));
                    obj.setTEN_DV(rs.getString("TEN_DV"));
                    obj.setMA_DV_DD(rs.getString("MA_DV_DD"));
                    obj.setMA_HQ_PH(rs.getString("MA_HQ_PH"));
                    obj.setTEN_DV_DD(rs.getString("TEN_DV_DD"));
                    objDC.setObj804(obj);
                } else if (MsgType.equals("805")) {
                    HQ_BAOLANH_CHUNG obj = new HQ_BAOLANH_CHUNG();
                    obj.setMA_DV(rs.getString("MA_DV"));
                    obj.setTEN_DV(rs.getString("TEN_DV"));
                    obj.setMA_DV_DD(rs.getString("MA_DV_DD"));
                    obj.setTEN_DV_DD(rs.getString("TEN_DV_DD"));
                    obj.setLOAI_CT(rs.getString("LOAI_CT"));
                    obj.setKYHIEU_CT(rs.getString("KYHIEU_CT"));
                    obj.setSO_CT(rs.getString("SO_CT"));
                    obj.setNGAY_CT(rs.getString("NGAY_CT"));
                    obj.setTTBUTTOAN(rs.getString("TTBUTTOAN"));
                    obj.setNGAY_HL(rs.getString("NGAY_HL"));
                    obj.setNGAY_HHL(rs.getString("NGAY_HHL"));
                    obj.setSOTIEN(rs.getString("SOTIEN"));
                    obj.setDIENGIAI(rs.getString("DIENGIAI"));
                    obj.setMAKERID(rs.getString("MAKERID"));
                    obj.setBRANCHID(rs.getString("BRANCHID"));
                    obj.setBGNUMBER(rs.getString("BGNUMBER"));
                    obj.setSTATUS(rs.getString("STATUS"));
                    obj.setCHECKERID(rs.getString("CHECKERID"));
                    obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                    objDC.setObj805(obj);
                } else if (MsgType.equals("806")) {
                    HQ_BAOLANH_HDVD obj = new HQ_BAOLANH_HDVD();
                    obj.setMA_DV(rs.getString("MA_DV"));
                    obj.setTEN_DV(rs.getString("TEN_DV"));
                    obj.setMA_DV_DD(rs.getString("MA_DV_DD"));
                    obj.setTEN_DV_DD(rs.getString("TEN_DV_DD"));
                    obj.setMA_HQ_KB(rs.getString("MA_HQ_KB"));
                    obj.setSO_HD(rs.getString("SO_HD"));
                    obj.setNGAY_HD(rs.getString("NGAY_HD"));
                    obj.setSO_VD_01(rs.getString("SO_VD_01"));
                    obj.setNGAY_VD_01(rs.getString("NGAY_VD_01"));
                    obj.setSO_VD_02(rs.getString("SO_VD_02"));
                    obj.setNGAY_VD_02(rs.getString("NGAY_VD_02"));
                    obj.setSO_VD_03(rs.getString("SO_VD_03"));
                    obj.setNGAY_VD_03(rs.getString("NGAY_VD_03"));
                    obj.setSO_VD_04(rs.getString("SO_VD_04"));
                    obj.setNGAY_VD_04(rs.getString("NGAY_VD_04"));
                    obj.setSO_VD_05(rs.getString("SO_VD_05"));
                    obj.setNGAY_VD_05(rs.getString("NGAY_VD_05"));
                    obj.setLOAI_CT(rs.getString("LOAI_CT"));
                    obj.setKYHIEU_CT(rs.getString("KYHIEU_CT"));
                    obj.setSO_CT(rs.getString("SO_CT"));
                    obj.setNGAY_CT(rs.getString("NGAY_CT"));
                    obj.setTTBUTTOAN(rs.getString("TTBUTTOAN"));
                    obj.setSNBL(rs.getString("SNBL"));
                    obj.setNGAY_HL(rs.getString("NGAY_HL"));
                    obj.setNGAY_HHL(rs.getString("NGAY_HHL"));
                    obj.setSOTIEN(rs.getString("SOTIEN"));
                    obj.setDIENGIAI(rs.getString("DIENGIAI"));
                    obj.setMAKERID(rs.getString("MAKERID"));
                    obj.setBRANCHID(rs.getString("BRANCHID"));
                    obj.setBGNUMBER(rs.getString("BGNUMBER"));
                    obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                    obj.setSTATUS(rs.getString("STATUS"));
                    obj.setCHECKERID(rs.getString("CHECKERID"));
                    objDC.setObj806(obj);
                }
                resultList.add(objDC); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pNGAY_DC
     * @param pTRANSACTIONID
     * @param PMSGTYPE
     * @param status
     * @param errnumber
     * @param errmsg
     * @throws SQLException
     */
    public void InsertDoiChieu(String pNGAY_DC,
            String pTRANSACTIONID,
            String PMSGTYPE,
            String status,
            String errnumber,
            String errmsg) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(InsertDoiChieu);
        try {
            calStmt.setString(1, pNGAY_DC);
            calStmt.setString(2, pTRANSACTIONID);
            calStmt.setString(3, PMSGTYPE);
            calStmt.setString(4, status);
            calStmt.setString(5, errnumber);
            calStmt.setString(6, errmsg);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pMsgType
     * @param pIDREF
     * @param pNGAY_DC
     * @param pKQDC
     * @param pTRANSACTIONID
     * @throws SQLException
     */
    public void InsertKQDoiChieu(
            String pMsgType,
            String pIDREF,
            String pNGAY_DC,
            String pKQDC,
            String pTRANSACTIONID
    ) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(InsertKQDoiChieu);
        try {
            calStmt.setString(1, pMsgType);
            calStmt.setString(2, pIDREF);
            calStmt.setString(3, pNGAY_DC);
            calStmt.setString(4, pKQDC);
            calStmt.setString(5, pTRANSACTIONID);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @param pMSGTYPE
     * @return
     * @throws SQLException
     */
    public String getSO_TN_CT(String pID,
            String pMSGTYPE) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getSO_TN_CT);
        try {
            calStmt.setString(1, pID);
            calStmt.setString(2, pMSGTYPE);
            calStmt.registerOutParameter(3, Types.VARCHAR);
            calStmt.execute();
            return calStmt.getString(3);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param LOAIDM
     * @param ID
     * @param ten
     * @return
     * @throws Exception
     */
    public ArrayList selectDANHMUC(String LOAIDM, String ID, String ten) throws Exception {
        try {
            CallableStatement calStmt = connection.prepareCall(selectDANHMUC);
            calStmt.setString(1, LOAIDM);
            calStmt.setString(2, ID);
            calStmt.setString(3, ten);
            calStmt.registerOutParameter(4, OracleTypes.CURSOR);
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(4);
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
        }
    }

    /**
     *
     * @param PIDREF
     * @param PTYPE
     * @param PCHECKERID
     * @return
     * @throws SQLException
     */
    public String[] APPROVEBAOLANH(
            int PIDREF,
            String PTYPE,// TK/CHUNG/HDVD
            String PCHECKERID) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(APPROVEBAOLANH);
        try {
            calStmt.setInt(1, PIDREF);
            calStmt.setString(2, PTYPE);
            calStmt.setString(3, PCHECKERID);
            calStmt.registerOutParameter(4, Types.VARCHAR); //PRESPONSECODE
            calStmt.registerOutParameter(5, Types.VARCHAR); //PRESPONSECODE
            calStmt.execute();
            String[] arrResult = new String[2];
            arrResult[0] = calStmt.getString(4);
            arrResult[1] = calStmt.getString(5);
            return arrResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pTRANSACTION_ID
     * @param pSO_TN_CT
     * @param pNGAY_TN_CT
     * @param pKQ_DC
     * @param pNGAY_DC
     * @param pTYPETRANS
     * @param pMSGTYPE
     * @throws SQLException
     */
    public void insertKQDOICHIEUHUY(
            String pTRANSACTION_ID,
            String pSO_TN_CT,
            String pNGAY_TN_CT,
            String pKQ_DC,
            String pNGAY_DC,
            String pTYPETRANS,
            String pMSGTYPE
    ) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(insertKQDOICHIEUHUY);
        try {
            calStmt.setString(1, pTRANSACTION_ID);
            calStmt.setString(2, pSO_TN_CT);
            calStmt.setString(3, pNGAY_TN_CT);
            calStmt.setString(4, pKQ_DC);
            calStmt.setString(5, pNGAY_DC);
            calStmt.setString(6, pTYPETRANS);
            calStmt.setString(7, pMSGTYPE);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pSo_HS
     * @param pMST
     * @param pSO_CT
     * @param pKHCT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pFromRow
     * @param pToRow
     * @return
     * @throws SQLException
     */
    public List<HQ_NOPTIEN_CQQLT> searchNOPTIEN_CQQLT(
            String pSo_HS,
            String pMST,
            String pSO_CT,
            String pKHCT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(searchNOPTIEN_CQQLT);
        try {
            calStmt.setString(1, pSo_HS);
            calStmt.setString(2, pMST);
            calStmt.setString(3, pSO_CT);
            calStmt.setString(4, pKHCT);
            if (pFromdate != null) {
                java.sql.Date sqlFromdate = new java.sql.Date(pFromdate.getTime());
                calStmt.setDate(5, sqlFromdate);
            } else {
                calStmt.setDate(5, null);
            }
            if (pTodate != null) {
                java.sql.Date sqlTodate = new java.sql.Date(pTodate.getTime());
                calStmt.setDate(6, sqlTodate);
            } else {
                calStmt.setDate(6, null);
            }
            calStmt.setString(7, pBranchCode);
            calStmt.setString(8, pStatus);
            calStmt.setInt(9, pFromRow);
            calStmt.setInt(10, pToRow);
            calStmt.registerOutParameter(11, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(11);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_NOPTIEN_CQQLT> resultList = new ArrayList<HQ_NOPTIEN_CQQLT>();
            while (rs.next()) {
                HQ_NOPTIEN_CQQLT obj = new HQ_NOPTIEN_CQQLT();
                obj.setKyHieu_CT(rs.getString("KYHIEU_CT"));
                obj.setSO_CT(rs.getString("SO_CT"));
                obj.setNGAY_CT(rs.getString("NGAY_CT"));
                obj.setNGAY_BN(rs.getString("NGAY_BN"));
                obj.setNGAY_BC(rs.getString("NGAY_BC"));
                obj.setSO_HS(rs.getString("SO_HS"));
                obj.setMA_DVQL(rs.getString("MA_DVQL"));
                obj.setTEN_DVQL(rs.getString("TEN_DVQL"));
                obj.setKYHIEU_CT_PT(rs.getString("KYHIEU_CT_PT"));
                obj.setSO_CT_PT(rs.getString("SO_CT_PT"));
                obj.setNAM_CT_PT(rs.getString("NAM_CT_PT"));
                obj.setMA_ST(rs.getString("MA_ST"));
                obj.setTEN_DV(rs.getString("TEN_DV"));
                obj.setTT_KHAC(rs.getString("TT_KHAC"));
                obj.setMA_NT(rs.getString("MA_NT"));
                obj.setTYGIA(rs.getString("TYGIA"));
                obj.setTONGTIEN_NT(rs.getString("TONGTIEN_NT"));
                obj.setTONGTIEN_VND(rs.getString("TONGTIEN_VND"));
                obj.setMA_NH_TH(rs.getString("MA_NH_TH"));
                obj.setTEN_NH_TH(rs.getString("TEN_NH_TH"));
                obj.setTAIKHOAN_TH(rs.getString("TAIKHOAN_TH"));
                obj.setTEN_TAIKHOAN_TH(rs.getString("TEN_TAIKHOAN_TH"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setMAKERID(rs.getString("MAKERID"));
                obj.setCHECKERID(rs.getString("CHECKERID"));
                obj.setBRANCHID(rs.getString("BRANCHID"));
                obj.setSOURCEACCOUNT(rs.getString("SOURCEACCOUNT"));
                obj.setFEE(BigDecimal.valueOf(Long.valueOf(rs.getString("FEE"))));
                obj.setVAT(BigDecimal.valueOf(Long.valueOf(rs.getString("VAT"))));
                obj.setTYPEFORM(rs.getString("TYPEFORM"));
                obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                obj.setID(Integer.valueOf(rs.getString("ID")));
                obj.setDIACHI_DV(rs.getString("DIACHI_DV"));
                obj.setTEN_NNTHAY(rs.getString("TEN_NNTHAY"));
                obj.setDIACHI_NNTHAY(rs.getString("DIACHI_NNTHAY"));
                obj.setNGUOI_NOP(rs.getString("NGUOI_NOP"));
                obj.setREFCORE(rs.getString("TXNID"));
                obj.setERRMSGCORE(rs.getString("ERRMSG"));
                obj.setMAKEDATE(rs.getString("MAKEDATE"));
                obj.setSOURCEACCOUNTNAME(rs.getString("SOURCEACCOUNTNAME"));
                obj.setNGAY_DUYET(rs.getString("NGAY_DUYET"));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pSo_HS
     * @param pMST
     * @param pSO_CT
     * @param pKHCT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @return
     * @throws SQLException
     */
    public int getSumRowNOPTIEN_CQQLT(
            String pSo_HS,
            String pMST,
            String pSO_CT,
            String pKHCT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus
    ) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getSumRowNOPTIEN_CQQLT);
        try {
            calStmt.setString(1, pSo_HS);
            calStmt.setString(2, pMST);
            calStmt.setString(3, pSO_CT);
            calStmt.setString(4, pKHCT);
            if (pFromdate != null) {
                java.sql.Date sqlFromdate = new java.sql.Date(pFromdate.getTime());
                calStmt.setDate(5, sqlFromdate);
            } else {
                calStmt.setDate(5, null);
            }
            if (pTodate != null) {
                java.sql.Date sqlTodate = new java.sql.Date(pTodate.getTime());
                calStmt.setDate(6, sqlTodate);
            } else {
                calStmt.setDate(6, null);
            }
            calStmt.setString(7, pBranchCode);
            calStmt.setString(8, pStatus);
            calStmt.registerOutParameter(9, Types.INTEGER); //pID_NNT
            calStmt.execute();
            return calStmt.getInt(9);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @throws SQLException
     */
    public void UPDATE_HQ_BAOLANH_TK(HQ_BAOLANH_TK obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(UPDATE_HQ_BAOLANH_TK);
        try {
            calStmt.setInt(1, obj.getID());
            calStmt.setString(2, obj.getMA_DV());
            calStmt.setString(3, obj.getTEN_DV());
            calStmt.setString(4, obj.getMA_DV_DD());
            calStmt.setString(5, obj.getTEN_DV_DD());
            calStmt.setString(6, obj.getMA_HQ_PH());
            calStmt.setString(7, obj.getMA_HQ());
            calStmt.setString(8, obj.getMA_LH());
            calStmt.setString(9, obj.getSO_TK());
            calStmt.setString(10, obj.getNGAY_DK());
            calStmt.setString(11, obj.getMA_LT());
            calStmt.setString(12, obj.getLOAI_CT());
            calStmt.setString(13, obj.getKYHIEU_CT());
            calStmt.setString(14, obj.getSO_CT());
            calStmt.setString(15, obj.getTTBUTTOAN());
            calStmt.setString(16, obj.getSNBL());
            calStmt.setString(17, obj.getNGAY_HL());
            calStmt.setString(18, obj.getNGAY_HHL());
            calStmt.setString(19, obj.getSOTIEN());
            calStmt.setString(20, obj.getDIENGIAI());
            calStmt.setString(21, obj.getMAKERID());
            calStmt.setString(22, obj.getBRANCHID());
            calStmt.setString(23, obj.getBGNUMBER());
            calStmt.setString(24, obj.getDIA_CHI());
            calStmt.setString(25, obj.getTEN_HQ_PH());
            calStmt.setString(26, obj.getTEN_HQ());
            calStmt.setString(27, obj.getDuNo_TO());
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @throws SQLException
     */
    public void UPDATE_HQ_BAOLANH_CHUNG(HQ_BAOLANH_CHUNG obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(UPDATE_HQ_BAOLANH_CHUNG);
        try {
            calStmt.setInt(1, obj.getID());
            calStmt.setString(2, obj.getMA_DV());
            calStmt.setString(3, obj.getTEN_DV());
            calStmt.setString(4, obj.getMA_DV_DD());
            calStmt.setString(5, obj.getTEN_DV_DD());
            calStmt.setString(6, obj.getLOAI_CT());
            calStmt.setString(7, obj.getKYHIEU_CT());
            calStmt.setString(8, obj.getSO_CT());
            calStmt.setString(9, obj.getTTBUTTOAN());
            calStmt.setString(10, obj.getNGAY_HL());
            calStmt.setString(11, obj.getNGAY_HHL());
            calStmt.setString(12, obj.getSOTIEN());
            calStmt.setString(13, obj.getDIENGIAI());
            calStmt.setString(14, obj.getMAKERID());
            calStmt.setString(15, obj.getBRANCHID());
            calStmt.setString(16, obj.getBGNUMBER());
            calStmt.setString(17, obj.getDIA_CHI());
            calStmt.setString(18, obj.getTEN_HQ_PH());
            calStmt.setString(19, obj.getTEN_HQ());
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @throws SQLException
     */
    public void UPDATE_HQ_BAOLANH_HDVD(HQ_BAOLANH_HDVD obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(UPDATE_HQ_BAOLANH_HDVD);
        try {
            calStmt.setInt(1, obj.getID());
            calStmt.setString(2, obj.getMA_DV());
            calStmt.setString(3, obj.getTEN_DV());
            calStmt.setString(4, obj.getMA_DV_DD());
            calStmt.setString(5, obj.getTEN_DV_DD());
            calStmt.setString(6, obj.getMA_HQ_KB());
            calStmt.setString(7, obj.getSO_HD());
            calStmt.setString(8, obj.getNGAY_HD());
            calStmt.setString(9, obj.getSO_VD_01());
            calStmt.setString(10, obj.getNGAY_VD_01());
            calStmt.setString(11, obj.getSO_VD_02());
            calStmt.setString(12, obj.getNGAY_VD_02());
            calStmt.setString(13, obj.getSO_VD_03());
            calStmt.setString(14, obj.getNGAY_VD_03());
            calStmt.setString(15, obj.getSO_VD_04());
            calStmt.setString(16, obj.getNGAY_VD_04());
            calStmt.setString(17, obj.getSO_VD_05());
            calStmt.setString(18, obj.getNGAY_VD_05());
            calStmt.setString(19, obj.getLOAI_CT());
            calStmt.setString(20, obj.getKYHIEU_CT());
            calStmt.setString(21, obj.getSO_CT());
            calStmt.setString(22, obj.getTTBUTTOAN());
            calStmt.setString(23, obj.getSNBL());
            calStmt.setString(24, obj.getNGAY_HL());
            calStmt.setString(25, obj.getNGAY_HHL());
            calStmt.setString(26, obj.getSOTIEN());
            calStmt.setString(27, obj.getDIENGIAI());
            calStmt.setString(28, obj.getMAKERID());
            calStmt.setString(29, obj.getBRANCHID());
            calStmt.setString(30, obj.getBGNUMBER());
            calStmt.setString(31, obj.getDIA_CHI());
            calStmt.setString(32, obj.getTEN_HQ_PH());
            calStmt.setString(33, obj.getTEN_HQ());
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @throws SQLException
     */
    public void UPDATE_HQ_NOPTIEN_CQQLT(HQ_NOPTIEN_CQQLT obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(UPDATE_HQ_NOPTIEN_CQQLT);
        try {
            calStmt.setString(1, obj.getKyHieu_CT());
            calStmt.setString(2, obj.getSO_CT());
            calStmt.setString(3, obj.getSO_HS());
            calStmt.setString(4, obj.getMA_DVQL());
            calStmt.setString(5, obj.getTEN_DVQL());
            calStmt.setString(6, obj.getKYHIEU_CT_PT());
            calStmt.setString(7, obj.getSO_CT_PT());
            calStmt.setString(8, obj.getNAM_CT_PT());
            calStmt.setString(9, obj.getMA_ST());
            calStmt.setString(10, obj.getTEN_DV());
            calStmt.setString(11, obj.getTT_KHAC());
            calStmt.setString(12, obj.getMA_NT());
            calStmt.setString(13, obj.getTYGIA());
            calStmt.setString(14, obj.getTONGTIEN_NT());
            calStmt.setString(15, obj.getTONGTIEN_VND());
            calStmt.setString(16, obj.getMA_NH_TH());
            calStmt.setString(17, obj.getTEN_NH_TH());
            calStmt.setString(18, obj.getTAIKHOAN_TH());
            calStmt.setString(19, obj.getTEN_TAIKHOAN_TH());
            calStmt.setString(20, obj.getMAKERID());
            calStmt.setString(21, obj.getBRANCHID());
            calStmt.setString(22, obj.getSOURCEACCOUNT());
            calStmt.setBigDecimal(23, obj.getFEE());
            calStmt.setBigDecimal(24, obj.getVAT());
            calStmt.setString(25, obj.getTYPEFORM());
            calStmt.setString(26, obj.getREQUEST_ID());
            calStmt.setString(27, obj.getDIACHI_DV());
            calStmt.setString(28, obj.getTEN_NNTHAY());
            calStmt.setString(29, obj.getDIACHI_NNTHAY());
            calStmt.setString(30, obj.getNGUOI_NOP());
            calStmt.setInt(31, obj.getID()); //ID
            calStmt.setString(32, obj.getSOURCEACCOUNTNAME());
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pMST
     * @param pSO_TK
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pFromRow
     * @param pToRow
     * @return
     * @throws SQLException
     */
    public List<HQ_BAOLANH_TK> searchBAOLANH_TK(
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(searchBAOLANH);
        try {
            calStmt.setString(1, "401");
            calStmt.setString(2, pMST);
            calStmt.setString(3, pSO_TK);
            calStmt.setString(4, pSO_CT);
            calStmt.setString(5, pKYHIEU_CT);
            if (pFromdate != null) {
                java.sql.Date sqlFromdate = new java.sql.Date(pFromdate.getTime());
                calStmt.setDate(6, sqlFromdate);
            } else {
                calStmt.setDate(6, null);
            }
            if (pTodate != null) {
                java.sql.Date sqlTodate = new java.sql.Date(pTodate.getTime());
                calStmt.setDate(7, sqlTodate);
            } else {
                calStmt.setDate(7, null);
            }
            calStmt.setString(8, pBranchCode);
            calStmt.setString(9, pStatus);

            calStmt.setInt(10, pFromRow);
            calStmt.setInt(11, pToRow);
            calStmt.registerOutParameter(12, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(12);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_BAOLANH_TK> resultList = new ArrayList<HQ_BAOLANH_TK>();
            while (rs.next()) {
                HQ_BAOLANH_TK obj = new HQ_BAOLANH_TK();
                obj.setMA_HQ(rs.getString("MA_HQ"));
                obj.setMA_LH(rs.getString("MA_LH"));
                obj.setSO_TK(rs.getString("SO_TK"));
                obj.setNGAY_DK(rs.getString("NGAY_DK"));
                obj.setMA_LT(rs.getString("MA_LT"));
                obj.setLOAI_CT(rs.getString("LOAI_CT"));
                obj.setKYHIEU_CT(rs.getString("KYHIEU_CT"));
                obj.setSO_CT(rs.getString("SO_CT"));
                obj.setNGAY_CT(rs.getString("NGAY_CT"));
                obj.setTTBUTTOAN(rs.getString("TTBUTTOAN"));
                obj.setSNBL(rs.getString("SNBL"));
                obj.setNGAY_HL(rs.getString("NGAY_HL"));
                obj.setNGAY_HHL(rs.getString("NGAY_HHL"));
                obj.setSOTIEN(rs.getString("SOTIEN"));
                obj.setDIENGIAI(rs.getString("DIENGIAI"));
                obj.setMAKERID(rs.getString("MAKERID"));
                obj.setCHECKERID(rs.getString("CHECKERID"));
                obj.setBRANCHID(rs.getString("BRANCHID"));
                obj.setBGNUMBER(rs.getString("BGNUMBER"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                obj.setMA_DV(rs.getString("MA_DV"));
                obj.setTEN_DV(rs.getString("TEN_DV"));
                obj.setMA_DV_DD(rs.getString("MA_DV_DD"));
                obj.setTEN_DV_DD(rs.getString("TEN_DV_DD"));
                obj.setMA_HQ_PH(rs.getString("MA_HQ_PH"));
                obj.setDIA_CHI(rs.getString("DIA_CHI"));
                obj.setTEN_HQ_PH(rs.getString("TEN_HQ_PH"));
                obj.setTEN_HQ(rs.getString("TEN_HQ"));
                obj.setNGAY_LAP(rs.getString("NGAY_LAP"));
                obj.setMAKEDATE(rs.getString("MAKEDATE"));
                obj.setCHECKDATE(rs.getString("CHECKDATE"));
                obj.setID(Integer.parseInt(rs.getString("ID")));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pMST
     * @param pSO_TK
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pFromRow
     * @param pToRow
     * @return
     * @throws SQLException
     */
    public List<HQ_BAOLANH_CHUNG> searchBAOLANH_CHUNG(
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(searchBAOLANH);
        try {
            calStmt.setString(1, "402");
            calStmt.setString(2, pMST);
            calStmt.setString(3, pSO_TK);
            calStmt.setString(4, pSO_CT);
            calStmt.setString(5, pKYHIEU_CT);
            if (pFromdate != null) {
                java.sql.Date sqlFromdate = new java.sql.Date(pFromdate.getTime());
                calStmt.setDate(6, sqlFromdate);
            } else {
                calStmt.setDate(6, null);
            }
            if (pTodate != null) {
                java.sql.Date sqlTodate = new java.sql.Date(pTodate.getTime());
                calStmt.setDate(7, sqlTodate);
            } else {
                calStmt.setDate(7, null);
            }
            calStmt.setString(8, pBranchCode);
            calStmt.setString(9, pStatus);

            calStmt.setInt(10, pFromRow);
            calStmt.setInt(11, pToRow);
            calStmt.registerOutParameter(12, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(12);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_BAOLANH_CHUNG> resultList = new ArrayList<HQ_BAOLANH_CHUNG>();
            while (rs.next()) {
                HQ_BAOLANH_CHUNG obj = new HQ_BAOLANH_CHUNG();
                obj.setMA_DV(rs.getString("MA_DV"));
                obj.setTEN_DV(rs.getString("TEN_DV"));
                obj.setMA_DV_DD(rs.getString("MA_DV_DD"));
                obj.setTEN_DV_DD(rs.getString("TEN_DV_DD"));
                obj.setLOAI_CT(rs.getString("LOAI_CT"));
                obj.setKYHIEU_CT(rs.getString("KYHIEU_CT"));
                obj.setSO_CT(rs.getString("SO_CT"));
                obj.setNGAY_CT(rs.getString("NGAY_CT"));
                obj.setTTBUTTOAN(rs.getString("TTBUTTOAN"));
                obj.setNGAY_HL(rs.getString("NGAY_HL"));
                obj.setNGAY_HHL(rs.getString("NGAY_HHL"));
                obj.setSOTIEN(rs.getString("SOTIEN"));
                obj.setDIENGIAI(rs.getString("DIENGIAI"));
                obj.setMAKERID(rs.getString("MAKERID"));
                obj.setBRANCHID(rs.getString("BRANCHID"));
                obj.setBGNUMBER(rs.getString("BGNUMBER"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setCHECKERID(rs.getString("CHECKERID"));
                obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                obj.setDIA_CHI(rs.getString("DIA_CHI"));
                obj.setTEN_HQ_PH(rs.getString("TEN_HQ_PH"));
                obj.setTEN_HQ(rs.getString("TEN_HQ"));
                obj.setNGAY_LAP(rs.getString("NGAY_LAP"));
                obj.setMAKEDATE(rs.getString("MAKEDATE"));
                obj.setCHECKDATE(rs.getString("CHECKDATE"));
                obj.setID(Integer.parseInt(rs.getString("ID")));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pMST
     * @param pSO_TK
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pFromRow
     * @param pToRow
     * @return
     * @throws SQLException
     */
    public List<HQ_BAOLANH_HDVD> searchBAOLANH_HDVD(
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(searchBAOLANH);
        try {
            calStmt.setString(1, "403");
            calStmt.setString(2, pMST);
            calStmt.setString(3, pSO_TK);
            calStmt.setString(4, pSO_CT);
            calStmt.setString(5, pKYHIEU_CT);
            if (pFromdate != null) {
                java.sql.Date sqlFromdate = new java.sql.Date(pFromdate.getTime());
                calStmt.setDate(6, sqlFromdate);
            } else {
                calStmt.setDate(6, null);
            }
            if (pTodate != null) {
                java.sql.Date sqlTodate = new java.sql.Date(pTodate.getTime());
                calStmt.setDate(7, sqlTodate);
            } else {
                calStmt.setDate(7, null);
            }
            calStmt.setString(8, pBranchCode);
            calStmt.setString(9, pStatus);

            calStmt.setInt(10, pFromRow);
            calStmt.setInt(11, pToRow);
            calStmt.registerOutParameter(12, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(12);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_BAOLANH_HDVD> resultList = new ArrayList<HQ_BAOLANH_HDVD>();
            while (rs.next()) {
                HQ_BAOLANH_HDVD obj = new HQ_BAOLANH_HDVD();
                obj.setMA_DV(rs.getString("MA_DV"));
                obj.setTEN_DV(rs.getString("TEN_DV"));
                obj.setMA_DV_DD(rs.getString("MA_DV_DD"));
                obj.setTEN_DV_DD(rs.getString("TEN_DV_DD"));
                obj.setMA_HQ_KB(rs.getString("MA_HQ_KB"));
                obj.setSO_HD(rs.getString("SO_HD"));
                obj.setNGAY_HD(rs.getString("NGAY_HD"));
                obj.setSO_VD_01(rs.getString("SO_VD_01"));
                obj.setNGAY_VD_01(rs.getString("NGAY_VD_01"));
                obj.setSO_VD_02(rs.getString("SO_VD_02"));
                obj.setNGAY_VD_02(rs.getString("NGAY_VD_02"));
                obj.setSO_VD_03(rs.getString("SO_VD_03"));
                obj.setNGAY_VD_03(rs.getString("NGAY_VD_03"));
                obj.setSO_VD_04(rs.getString("SO_VD_04"));
                obj.setNGAY_VD_04(rs.getString("NGAY_VD_04"));
                obj.setSO_VD_05(rs.getString("SO_VD_05"));
                obj.setNGAY_VD_05(rs.getString("NGAY_VD_05"));
                obj.setLOAI_CT(rs.getString("LOAI_CT"));
                obj.setKYHIEU_CT(rs.getString("KYHIEU_CT"));
                obj.setSO_CT(rs.getString("SO_CT"));
                obj.setNGAY_CT(rs.getString("NGAY_CT"));
                obj.setTTBUTTOAN(rs.getString("TTBUTTOAN"));
                obj.setSNBL(rs.getString("SNBL"));
                obj.setNGAY_HL(rs.getString("NGAY_HL"));
                obj.setNGAY_HHL(rs.getString("NGAY_HHL"));
                obj.setSOTIEN(rs.getString("SOTIEN"));
                obj.setDIENGIAI(rs.getString("DIENGIAI"));
                obj.setMAKERID(rs.getString("MAKERID"));
                obj.setBRANCHID(rs.getString("BRANCHID"));
                obj.setBGNUMBER(rs.getString("BGNUMBER"));
                obj.setREQUEST_ID(rs.getString("REQUEST_ID"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setCHECKERID(rs.getString("CHECKERID"));
                obj.setDIA_CHI(rs.getString("DIA_CHI"));
                obj.setTEN_HQ_PH(rs.getString("TEN_HQ_PH"));
                obj.setTEN_HQ(rs.getString("TEN_HQ"));
                obj.setMAKEDATE(rs.getString("MAKEDATE"));
                obj.setCHECKDATE(rs.getString("CHECKDATE"));
                obj.setID(Integer.parseInt(rs.getString("ID")));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pMSGTYPE
     * @param pMST
     * @param pSO_TK
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @return
     * @throws SQLException
     */
    public int getSumSearchBAOLANH(
            String pMSGTYPE,
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getSumSearchBAOLANH);
        try {
            calStmt.setString(1, pMSGTYPE);
            calStmt.setString(2, pMST);
            calStmt.setString(3, pSO_TK);
            calStmt.setString(4, pSO_CT);
            calStmt.setString(5, pKYHIEU_CT);
            if (pFromdate != null) {
                java.sql.Date sqlFromdate = new java.sql.Date(pFromdate.getTime());
                calStmt.setDate(6, sqlFromdate);
            } else {
                calStmt.setDate(6, null);
            }
            if (pTodate != null) {
                java.sql.Date sqlTodate = new java.sql.Date(pTodate.getTime());
                calStmt.setDate(7, sqlTodate);
            } else {
                calStmt.setDate(7, null);
            }
            calStmt.setString(8, pBranchCode);
            calStmt.setString(9, pStatus);
            calStmt.registerOutParameter(10, OracleTypes.INTEGER); //pID_NNT
            calStmt.execute();
            return calStmt.getInt(10);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pMA_LH
     * @param pTEN_LH
     * @param pSN_AH
     * @throws SQLException
     */
    public void insertDM_LH(String pMA_LH,
            String pTEN_LH,
            String pSN_AH) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(insertDM_LH);
        try {
            calStmt.setString(1, pMA_LH);
            calStmt.setString(2, pTEN_LH);
            calStmt.setString(3, pSN_AH);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pMa_HQ
     * @param pTen_HQ
     * @param pMa_Cu
     * @param pMa_QHNS
     * @throws SQLException
     */
    public void insertDM_HQ(String pMa_HQ,
            String pTen_HQ,
            String pMa_Cu,
            String pMa_QHNS) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(insertDM_HQ);
        try {
            calStmt.setString(1, pMa_HQ);
            calStmt.setString(2, pTen_HQ);
            calStmt.setString(3, pMa_Cu);
            calStmt.setString(4, pMa_QHNS);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pMa_KB
     * @param pTen_KB
     * @throws SQLException
     */
    public void insertDM_KB(String pMa_KB,
            String pTen_KB
    ) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(insertDM_KB);
        try {
            calStmt.setString(1, pMa_KB);
            calStmt.setString(2, pTen_KB);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pMa_KB
     * @param pTen_KB
     * @throws SQLException
     */
    public void insertDM_ER(String pMa_KB,
            String pTen_KB
    ) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(insertDM_ER);
        try {
            calStmt.setString(1, pMa_KB);
            calStmt.setString(2, pTen_KB);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }
    //HAI QUAN ONLINE 09/2017

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public String[] INSERT_DKNNT(HQ_DKNNT obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_DKNNT);
        try {
            calStmt.setString(1, obj.getSo_HS());
            calStmt.setString(2, obj.getLoai_HS());
            calStmt.setString(3, obj.getMa_DV());
            calStmt.setString(4, obj.getTen_DV());
            calStmt.setString(5, obj.getDiaChi());
            calStmt.setString(6, obj.getSo_CMT());
            calStmt.setString(7, obj.getHo_Ten());
            calStmt.setString(8, obj.getNgaySinh());
            calStmt.setString(9, obj.getNguyenQuan());
            calStmt.setString(10, obj.getSerialNumber());
            calStmt.setString(11, obj.getNoi_Cap());
            calStmt.setString(12, obj.getNgay_HL());
            calStmt.setString(13, obj.getNgay_HHL());
            calStmt.setString(14, obj.getPublicKey());
            calStmt.setString(15, obj.getMa_NH_TH());
            calStmt.setString(16, obj.getTen_NH_TH());
            calStmt.setString(17, obj.getTaiKhoan_TH());
            calStmt.setString(18, obj.getTen_TaiKhoan_TH());
            calStmt.setString(19, obj.getUserID());
            calStmt.setString(20, obj.getBranchID());
            calStmt.setString(21, obj.getChannelID());
            calStmt.setString(22, obj.getTransid());
            calStmt.registerOutParameter(23, OracleTypes.VARCHAR); //pID_NNT
            calStmt.registerOutParameter(24, OracleTypes.VARCHAR); //pID_NNT
            calStmt.registerOutParameter(25, OracleTypes.VARCHAR); //pID_NNT
            calStmt.registerOutParameter(26, OracleTypes.VARCHAR); //pID_NNT
            calStmt.setString(27, obj.getNGAY_HL_UQ());
            calStmt.setString(28, obj.getNGAY_HHL_UQ());
            calStmt.setString(29, obj.getMSGTYPE());
            calStmt.setString(30, obj.getRequestid());
            calStmt.execute();
            String[] result = new String[4];
            result[0] = calStmt.getString(23); //status
            result[1] = calStmt.getString(24);//desc
            result[2] = calStmt.getString(25);//ID
            result[3] = calStmt.getString(26);//ID2
            List list = obj.getThongTinLienHe();
            for (int i = 0; i < list.size(); i++) {
                HQ_DKNNT_LIENHE objLH = (HQ_DKNNT_LIENHE) list.get(i);
                objLH.setID(result[2]);
                INSERT_NNT_LIENHE(objLH);
            }
            if (obj.getLoai_HS().equals("1")) {
                for (int i = 0; i < list.size(); i++) {
                    HQ_DKNNT_LIENHE objLH = (HQ_DKNNT_LIENHE) list.get(i);
                    objLH.setID(result[3]);
                    INSERT_NNT_LIENHE(objLH);
                }
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @param pUSER
     * @param pBRANCHID
     * @param pSTATUS
     * @param pACCEPTED
     * @param pDesc
     * @throws SQLException
     */
    public void DUYET_DKNNT(
            String pID,
            String pUSER,
            String pBRANCHID,
            String pSTATUS,
            String pACCEPTED,
            String pDesc
    )
            throws SQLException {
        CallableStatement calStmt = connection.prepareCall(DUYET_DKNNT);
        try {
            calStmt.setString(1, pID);
            calStmt.setString(2, pUSER);
            calStmt.setString(3, pBRANCHID);
            calStmt.setString(4, pSTATUS);
            calStmt.setString(5, pACCEPTED);
            calStmt.setString(6, pDesc);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @throws SQLException
     */
    public void INSERT_NNT_LIENHE(
            HQ_DKNNT_LIENHE obj
    )
            throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_NNT_LIENHE);
        try {
            calStmt.setString(1, obj.getID());
            calStmt.setString(2, obj.getSO_DT());
            calStmt.setString(3, obj.getEMAIL());
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @param pType
     * @return
     * @throws SQLException
     */
    public String[] getData213(String pID,
            String pType) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getData213);
        try {
            calStmt.setString(1, pID);
            calStmt.setString(2, pType);
            calStmt.registerOutParameter(3, OracleTypes.VARCHAR); //pID_NNT
            calStmt.registerOutParameter(4, OracleTypes.VARCHAR); //pID_NNT
            calStmt.registerOutParameter(5, OracleTypes.VARCHAR); //pID_NNT
            calStmt.execute();
            String[] result = new String[3];
            result[0] = calStmt.getString(3); //status
            result[1] = calStmt.getString(4);//desc
            result[2] = calStmt.getString(5);//desc
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pSO_HS
     * @param pMST
     * @param pBranchID
     * @param pStatus
     * @param FromDate
     * @param ToDate
     * @param STK
     * @return
     * @throws SQLException
     */
    public List<HQ_DKNNT> searchNNT(String pSO_HS,
            String pMST,
            String pBranchID,
            String pStatus,
            String FromDate,
            String ToDate,
            String STK) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(searchNNT);
        try {
            calStmt.setString(1, pSO_HS);
            calStmt.setString(2, pMST);
            calStmt.setString(3, pBranchID);
            calStmt.setString(4, pStatus);
            calStmt.setString(5, FromDate);
            calStmt.setString(6, ToDate);
            calStmt.setString(7, STK);
            calStmt.registerOutParameter(8, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(8);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_DKNNT> resultList = new ArrayList<HQ_DKNNT>();
            while (rs.next()) {
                HQ_DKNNT obj = new HQ_DKNNT();
                obj.setSo_HS(rs.getString("SO_HS"));
                obj.setSo_CMT(rs.getString("SO_CMT"));
                obj.setDiaChi(rs.getString("DIACHI"));
                obj.setHo_Ten(rs.getString("HO_TEN"));
                obj.setLoai_HS(rs.getString("LOAI_HS"));
                obj.setMa_DV(rs.getString("MA_DV"));
                obj.setNgaySinh(rs.getString("NGAYSINH"));
                obj.setNguyenQuan(rs.getString("NGUYENQUAN"));
                obj.setNoi_Cap(rs.getString("NOI_CAP"));
                obj.setNgay_HL(rs.getString("NGAY_HL"));
                obj.setNgay_HHL(rs.getString("NGAY_HHL"));
                obj.setPublicKey(rs.getString("PUBLICKEY"));
                obj.setSerialNumber(rs.getString("SERIALNUMBER"));
                obj.setTaiKhoan_TH(rs.getString("TAIKHOAN_TH"));
                obj.setMa_NH_TH(rs.getString("MA_NH_TH"));
                obj.setTen_NH_TH(rs.getString("TEN_NH_TH"));
                obj.setTen_TaiKhoan_TH(rs.getString("TEN_TAIKHOAN_TH"));
                obj.setTen_DV(rs.getString("TEN_DV"));
                obj.setStatus(rs.getString("STATUS"));
                obj.setThongTinLienHe(getTTLIENHE(rs.getString("ID")));
                obj.setID(rs.getString("ID"));
                obj.setBranchID(rs.getString("BRANCHID"));
                obj.setACCEPTED(rs.getString("ACCEPTED"));
                obj.setISDELETED(rs.getString("ISDELETED"));
                obj.setCREATEDATE(rs.getString("CREATEDATE"));
                obj.setCHECKDATE(rs.getString("CHECKDATE"));
                obj.setAPPROVEDATE(rs.getString("APPROVEDATE"));
                obj.setSTATUS(rs.getString("STATUS"));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @return
     * @throws SQLException
     */
    public List<HQ_DKNNT_LIENHE> getTTLIENHE(String pID) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getTTLIENHE);
        try {
            calStmt.setString(1, pID);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(2);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_DKNNT_LIENHE> resultList = new ArrayList<HQ_DKNNT_LIENHE>();
            while (rs.next()) {
                HQ_DKNNT_LIENHE obj = new HQ_DKNNT_LIENHE();
                obj.setEMAIL(rs.getString("EMAIL"));
                obj.setSO_DT(rs.getString("SO_DT"));
                obj.setID(pID);
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @return
     * @throws SQLException
     */
    public List<HQ_DKNNT> getData312(String pID) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getData312);
        try {
            calStmt.setString(1, pID);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(2);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_DKNNT> resultList = new ArrayList<HQ_DKNNT>();
            while (rs.next()) {
                HQ_DKNNT obj = new HQ_DKNNT();
                obj.setSo_HS(rs.getString("SO_HS"));
                obj.setSo_CMT(rs.getString("SO_CMT"));
                obj.setDiaChi(rs.getString("DIACHI"));
                obj.setHo_Ten(rs.getString("HO_TEN"));
                obj.setLoai_HS(rs.getString("LOAI_HS"));
                obj.setMa_DV(rs.getString("MA_DV"));
                obj.setNgaySinh(rs.getString("NGAYSINH"));
                obj.setNguyenQuan(rs.getString("NGUYENQUAN"));
                obj.setNoi_Cap(rs.getString("NOI_CAP"));
                obj.setNgay_HL(rs.getString("NGAY_HL"));
                obj.setNgay_HHL(rs.getString("NGAY_HHL"));
                obj.setPublicKey(rs.getString("PUBLICKEY"));
                obj.setSerialNumber(rs.getString("SERIALNUMBER"));
                obj.setTaiKhoan_TH(rs.getString("TAIKHOAN_TH"));
                obj.setTen_NH_TH(rs.getString("TEN_NH_TH"));
                obj.setMa_NH_TH(rs.getString("MA_NH_TH"));
                obj.setTen_TaiKhoan_TH(rs.getString("TEN_TAIKHOAN_TH"));
                obj.setTen_DV(rs.getString("TEN_DV"));
                obj.setThongTinLienHe(getTTLIENHE(pID));
                obj.setNgay_HL_DK(rs.getString("NGAY_HL_DK"));
                obj.setID(rs.getString("ID"));
                obj.setACCEPTED(rs.getString("ACCEPTED"));
                obj.setChannelID(rs.getString("CHANNELID"));
                obj.setNGAY_HL_DK(rs.getString("NGAY_HL_DK"));
                obj.setDesc(rs.getString("Description"));
                obj.setNGAY_HL_UQ(rs.getString("NGAY_HL_UQ"));
                obj.setNGAY_HHL_UQ(rs.getString("NGAY_HHL_UQ"));
                obj.setMSGTYPE(rs.getString("MSGTYPE"));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pRequestID
     * @param pTransID
     * @param pSO_HS
     * @throws SQLException
     */
    public void UpdateDKNNT313(String pRequestID,
            String pTransID,
            String pSO_HS) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(UpdateDKNNT313);
        try {
            calStmt.setString(1, pRequestID);
            calStmt.setString(2, pTransID);
            calStmt.setString(3, pSO_HS);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public String getSO_TN_CT_SCB() throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getSO_TN_CT_SCB);
        try {
            calStmt.registerOutParameter(1, OracleTypes.VARCHAR);
            calStmt.execute();
            return calStmt.getString(1);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public String[] INSERT_NOPTIEN_HQ_ONLINE(HQ_NOPTIEN_HQ obj) throws SQLException {
        try {
            if (obj.getMsgType().equals("304")) {
                CallableStatement calStmt = connection.prepareCall(INSERT_NOPTIEN_HQ_ONLINE);
                calStmt.setString(1, obj.getNgayLap_CT());
                calStmt.setString(2, obj.getNgayTruyen_CT());
                calStmt.setString(3, obj.getMA_DV());
                calStmt.setString(4, obj.getMA_CHUONG());
                calStmt.setString(5, obj.getTEN_DV());
                calStmt.setString(6, obj.getMA_KB());
                calStmt.setString(7, obj.getTEN_KB());
                calStmt.setString(8, obj.getTKKB());
                calStmt.setString(9, obj.getMA_NTK());
                calStmt.setString(10, obj.getMA_HQ_PH());
                calStmt.setString(11, obj.getMA_HQ_CQT());
                calStmt.setString(12, obj.getKYHIEU_CT());
                calStmt.setString(13, obj.getSO_CT());
                calStmt.setString(14, obj.getLOAI_CT());
                calStmt.setString(15, obj.getNGAY_BN());
                calStmt.setString(16, obj.getNGAY_CT());
                calStmt.setString(17, obj.getMA_NT());
                calStmt.setString(18, obj.getTY_GIA());
                calStmt.setString(19, obj.getSOTIEN_TO());
                calStmt.setString(20, obj.getDIENGIAI());
                calStmt.setString(21, obj.getMa_ST());
                calStmt.setString(22, obj.getSo_CMT());
                calStmt.setString(23, obj.getTen_NNT());
                calStmt.setString(24, obj.getDiaChi());
                calStmt.setString(25, obj.getTT_Khac());
                calStmt.setString(26, obj.getMA_NH_TH());
                calStmt.setString(27, obj.getTEN_NH_TH());
                calStmt.setString(28, obj.getTaiKhoan_TH());
                calStmt.setString(29, obj.getTen_TaiKhoan_TH());
                calStmt.setString(30, obj.getTransaction_ID());
                calStmt.setString(31, obj.getTransaction_Date());
                calStmt.registerOutParameter(32, Types.VARCHAR); //ma loi
                calStmt.registerOutParameter(33, Types.VARCHAR); //ma loi
                calStmt.registerOutParameter(34, Types.VARCHAR); //id
                calStmt.registerOutParameter(35, Types.VARCHAR); //ma loi
                calStmt.registerOutParameter(36, Types.VARCHAR); //ma loi
                calStmt.execute();
                String[] arrResult = new String[5];
                arrResult[0] = calStmt.getString(32);
                arrResult[1] = calStmt.getString(33);
                arrResult[2] = calStmt.getString(34);
                arrResult[3] = calStmt.getString(35);
                arrResult[4] = calStmt.getString(36);
                return arrResult;
            } else {
                return INSERT_NOPTIEN_HQ_ONLINE_201(obj);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public String[] INSERT_GNT_ONLINE(HQ_NOPTIEN_HQ_GNT obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_GNT_ONLINE);
        try {
            calStmt.setInt(1, obj.getIDMASTER());
            calStmt.setString(2, obj.getID_HS());
            calStmt.setString(3, obj.getTTBUTTOAN());
            calStmt.setString(4, obj.getMA_HQ());
            calStmt.setString(5, obj.getMA_LH());
            calStmt.setString(6, obj.getNAM_DK());
            calStmt.setString(7, obj.getSO_TK());
            calStmt.setString(8, obj.getMA_LT());
            calStmt.setString(9, obj.getSOTIEN());
            calStmt.setString(10, obj.getSOTIEN_NT());

            calStmt.registerOutParameter(11, Types.NUMERIC);
            calStmt.registerOutParameter(12, Types.VARCHAR);
            calStmt.registerOutParameter(13, Types.VARCHAR);
            calStmt.execute();

            String[] arrResult = new String[3];
            arrResult[0] = calStmt.getString(11);
            arrResult[1] = calStmt.getString(12);
            arrResult[2] = calStmt.getString(13);
            return arrResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public String[] INSERT_NOPTIEN_CQT_ONINE(HQ_NOPTIEN_CQQLT obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_NOPTIEN_CQT_ONINE);
        try {
            calStmt.setString(1, obj.getSO_HS());
            calStmt.setString(2, obj.getMA_DVQL());
            calStmt.setString(3, obj.getTEN_DVQL());
            calStmt.setString(4, obj.getKYHIEU_CT_PT());
            calStmt.setString(5, obj.getSO_CT_PT());
            calStmt.setString(6, obj.getNAM_CT_PT());
            calStmt.setString(7, obj.getMA_ST());
            calStmt.setString(8, obj.getMA_NT());
            calStmt.setString(9, obj.getTYGIA());
            calStmt.setString(10, obj.getTONGTIEN_NT());
            calStmt.setString(11, obj.getTONGTIEN_VND());
            calStmt.setString(12, obj.getMa_ST());
            calStmt.setString(13, obj.getSo_CMT());
            calStmt.setString(14, obj.getTen_NNT());
            calStmt.setString(15, obj.getDiaChi());
            calStmt.setString(16, obj.getTT_Khac());
            calStmt.setString(17, obj.getMA_NH_TH());
            calStmt.setString(18, obj.getTEN_NH_TH());
            calStmt.setString(19, obj.getTaiKhoan_TH());
            calStmt.setString(20, obj.getTransaction_ID());
            calStmt.setString(21, obj.getTransaction_Date());

            calStmt.registerOutParameter(22, Types.VARCHAR); //PRESPONSECODE
            calStmt.registerOutParameter(23, Types.VARCHAR); //PRESPONSECODE
            calStmt.registerOutParameter(24, Types.VARCHAR);
            calStmt.registerOutParameter(25, Types.VARCHAR);
            calStmt.registerOutParameter(26, Types.VARCHAR); //ID
            calStmt.execute();

            String[] arrResult = new String[5];
            arrResult[0] = calStmt.getString(22);
            arrResult[1] = calStmt.getString(23);
            arrResult[2] = calStmt.getString(24);
            arrResult[3] = calStmt.getString(25);
            arrResult[4] = calStmt.getString(26);
            return arrResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param ID
     * @param Type
     * @param xml
     * @return
     * @throws SQLException
     */
    public String[] NOPTIEN_CORE_ONLINE(String ID, String Type, String xml) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(NOPTIEN_CORE_ONLINE);
        try {
            calStmt.setString(1, ID);
            calStmt.setString(2, Type);
            calStmt.setString(3, xml);
            calStmt.registerOutParameter(4, Types.VARCHAR);
            calStmt.registerOutParameter(5, Types.VARCHAR);
            calStmt.registerOutParameter(6, Types.VARCHAR);
            calStmt.registerOutParameter(7, Types.VARCHAR);
            calStmt.registerOutParameter(8, Types.VARCHAR);
            calStmt.registerOutParameter(9, Types.VARCHAR);
            calStmt.execute();
            String[] arrResult = new String[6];
            arrResult[0] = calStmt.getString(4);
            arrResult[1] = calStmt.getString(5);
            arrResult[2] = calStmt.getString(6);
            arrResult[3] = calStmt.getString(7);
            arrResult[4] = calStmt.getString(8);
            arrResult[5] = calStmt.getString(9);
            return arrResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws SQLException
     */
    public List<HQ_NOPTIEN_HQ_GNT> getDataGNT_CT(String ID) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getDataGNT_CT);
        try {
            calStmt.setString(1, ID);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);

            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(2);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_NOPTIEN_HQ_GNT> resultList = new ArrayList<HQ_NOPTIEN_HQ_GNT>();
            while (rs.next()) {
                HQ_NOPTIEN_HQ_GNT obj = new HQ_NOPTIEN_HQ_GNT();
                obj.setID(Integer.valueOf(rs.getString("ID")));
                obj.setID_HS(rs.getString("ID_HS"));
                obj.setTTBUTTOAN(rs.getString("TTBUTTOAN"));
                obj.setMA_HQ(rs.getString("MA_HQ"));
                obj.setMA_LH(rs.getString("MA_LH"));
                obj.setNAM_DK(rs.getString("NAM_DK"));
                obj.setSO_TK(rs.getString("SO_TK"));
                obj.setMA_LT(rs.getString("MA_LT"));
                obj.setID_HS(rs.getString("ID_HS"));

                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws SQLException
     */
    public List<HQ_NOPTIEN_HQ_GNTCT> getDataTOKHAI_CT(String ID) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getDataTOKHAI_CT);
        try {
            calStmt.setString(1, ID);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);

            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(2);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_NOPTIEN_HQ_GNTCT> resultList = new ArrayList<HQ_NOPTIEN_HQ_GNTCT>();
            while (rs.next()) {
                HQ_NOPTIEN_HQ_GNTCT obj = new HQ_NOPTIEN_HQ_GNTCT();
                obj.setID(Integer.valueOf(rs.getString("ID")));
                obj.setMA_ST(rs.getString("MA_ST"));
                obj.setNDKT(rs.getString("NDKT"));
                obj.setSOTIEN_NT(rs.getString("SOTIIEN_NT"));
                obj.setSOTIEN_VND(rs.getString("SOTIEN_VND"));

                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public List<HQ_NOPTIEN_HQ> getlistDataGNT() throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getlistDataGNT);
        try {
            calStmt.registerOutParameter(1, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(1);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_NOPTIEN_HQ> resultList = new ArrayList<HQ_NOPTIEN_HQ>();
            while (rs.next()) {
                HQ_NOPTIEN_HQ obj = new HQ_NOPTIEN_HQ();
                obj.setTransaction_ID(rs.getString("REQUEST_ID"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setADDINFO(rs.getString("ADDINFO"));
                obj.setID(Integer.valueOf(rs.getString("ID")));
                obj.setMsgType(rs.getString("MSGTYPE"));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public String getMaxDataCollated() throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getMaxDataCollated);
        try {
            calStmt.registerOutParameter(1, Types.VARCHAR);
            calStmt.executeQuery();
            return calStmt.getString(1);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @throws SQLException
     */
    public void UPDATE_DKNNT(HQ_DKNNT obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(UPDATE_DKNNT);
        try {
            calStmt.setString(1, obj.getID());
            calStmt.setString(2, obj.getTen_DV());
            calStmt.setString(3, obj.getDiaChi());
            calStmt.setString(4, obj.getSo_CMT());
            calStmt.setString(5, obj.getHo_Ten());
            calStmt.setString(6, obj.getNgaySinh());
            calStmt.setString(7, obj.getNguyenQuan());
            calStmt.setString(8, obj.getSerialNumber());
            calStmt.setString(9, obj.getNoi_Cap());
            calStmt.setString(10, obj.getNgay_HL());
            calStmt.setString(11, obj.getNgay_HHL());
            calStmt.setString(12, obj.getPublicKey());
            calStmt.setString(13, obj.getTaiKhoan_TH());
            calStmt.setString(14, obj.getTen_TaiKhoan_TH());
            calStmt.setString(15, obj.getUserID());
            calStmt.setString(16, obj.getBranchID());
            calStmt.setString(17, obj.getLoai_HS());
            calStmt.execute();
            //Thuc hien update TT LIEN HE
            deleteTTLIENHE(obj.getID());
            List objLH = obj.getThongTinLienHe();
            for (int i = 0; i < objLH.size(); i++) {
                HQ_DKNNT_LIENHE LH = (HQ_DKNNT_LIENHE) objLH.get(i);
                LH.setID(obj.getID());
                INSERT_NNT_LIENHE(LH);
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param ID
     * @throws SQLException
     */
    public void SendGNTHQ(String ID) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(SendGNTHQ);
        try {
            calStmt.setString(1, ID);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID
     * @return
     * @throws SQLException
     */
    public List<HQ_DKNNT> getDataDKNNT(String pID) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getDataDKNNT);
        try {
            calStmt.setString(1, pID);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(2);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_DKNNT> resultList = new ArrayList<HQ_DKNNT>();
            while (rs.next()) {
                HQ_DKNNT obj = new HQ_DKNNT();
                obj.setSo_HS(rs.getString("SO_HS"));
                obj.setSo_CMT(rs.getString("SO_CMT"));
                obj.setDiaChi(rs.getString("DIACHI"));
                obj.setHo_Ten(rs.getString("HO_TEN"));
                obj.setLoai_HS(rs.getString("LOAI_HS"));
                obj.setMa_DV(rs.getString("MA_DV"));
                obj.setNgaySinh(rs.getString("NGAYSINH"));
                obj.setNguyenQuan(rs.getString("NGUYENQUAN"));
                obj.setNoi_Cap(rs.getString("NOI_CAP"));
                obj.setNgay_HL(rs.getString("NGAY_HL"));
                obj.setNgay_HHL(rs.getString("NGAY_HHL"));
                obj.setPublicKey(rs.getString("PUBLICKEY"));
                obj.setSerialNumber(rs.getString("SERIALNUMBER"));
                obj.setTaiKhoan_TH(rs.getString("TAIKHOAN_TH"));
                obj.setTen_NH_TH(rs.getString("TEN_NH_TH"));
                obj.setMa_NH_TH(rs.getString("MA_NH_TH"));
                obj.setTen_TaiKhoan_TH(rs.getString("TEN_TAIKHOAN_TH"));
                obj.setTen_DV(rs.getString("TEN_DV"));
                obj.setThongTinLienHe(getTTLIENHE(pID));
                obj.setNgay_HL_DK(rs.getString("NGAY_HL_DK"));
                obj.setID(rs.getString("ID"));
                obj.setACCEPTED(rs.getString("ACCEPTED"));
                obj.setChannelID(rs.getString("CHANNELID"));
                obj.setNGAY_HL_DK(rs.getString("NGAY_HL_DK"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setDesc(rs.getString("Description"));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param ID
     * @param Type
     * @return
     * @throws SQLException
     */
    public int check213(String ID, String Type) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(check213);
        try {
            calStmt.setString(1, ID);
            calStmt.setString(2, Type);
            calStmt.registerOutParameter(3, OracleTypes.INTEGER);
            calStmt.execute();
            int check = calStmt.getInt(3);
            return check;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param ID
     * @throws SQLException
     */
    public void deleteTTLIENHE(String ID) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(deleteTTLIENHE);
        try {
            calStmt.setString(1, ID);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public String[] INSERT_NOPTIEN_HQ_ONLINE_201(HQ_NOPTIEN_HQ obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_NOPTIEN_HQ_ONLINE_201);
        try {
            calStmt.setString(1, obj.getTransaction_ID());
            calStmt.setString(2, obj.getREQUEST_ID());
            calStmt.setString(3, obj.getTransaction_Date());
            calStmt.setString(4, obj.getMA_CUC());
            calStmt.setString(5, obj.getTEN_CUC());
            calStmt.setString(6, obj.getMA_HQ_PH());
            calStmt.setString(7, obj.getTen_HQ_PH());
            calStmt.setString(8, obj.getMA_HQ_CQT());
            calStmt.setString(9, obj.getMA_DV());
            calStmt.setString(10, obj.getTEN_DV());
            calStmt.setString(11, obj.getMA_CHUONG());
            calStmt.setString(12, obj.getMA_KB());
            calStmt.setString(13, obj.getTEN_KB());
            calStmt.setString(14, obj.getTKKB());
            calStmt.setString(15, obj.getMA_NTK());
            calStmt.setString(16, obj.getTen_NTK());

            calStmt.registerOutParameter(17, Types.VARCHAR); //ma loi
            calStmt.registerOutParameter(18, Types.VARCHAR); //ID

            calStmt.execute();

            String[] arrResult = new String[2];
            arrResult[0] = calStmt.getString(17);
            arrResult[1] = calStmt.getString(18);

            return arrResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param ID
     * @throws SQLException
     */
    public void UPDATE_HQ_ONLINE_201(String ID) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(UPDATE_HQ_ONLINE_201);
        try {
            calStmt.setString(1, ID);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param SO_HS
     * @param MA_DV
     * @param status
     * @return
     * @throws SQLException
     */
    public List<HQ_DKNNT> searchDKUQ(String SO_HS, String MA_DV, String status) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(searchDKUQ);
        try {
            calStmt.setString(1, SO_HS);
            calStmt.setString(2, MA_DV);
            calStmt.setString(3, status);
            calStmt.registerOutParameter(4, OracleTypes.CURSOR);
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(4);
            int numcols = rs.getMetaData().getColumnCount();
            List<HQ_DKNNT> resultList = new ArrayList<HQ_DKNNT>();
            while (rs.next()) {
                HQ_DKNNT obj = new HQ_DKNNT();
                obj.setSo_HS(rs.getString("SO_HS"));
                obj.setSo_CMT(rs.getString("SO_CMT"));
                obj.setDiaChi(rs.getString("DIACHI"));
                obj.setHo_Ten(rs.getString("HO_TEN"));
                obj.setLoai_HS(rs.getString("LOAI_HS"));
                obj.setMa_DV(rs.getString("MA_DV"));
                obj.setNgaySinh(rs.getString("NGAYSINH"));
                obj.setNguyenQuan(rs.getString("NGUYENQUAN"));
                obj.setNoi_Cap(rs.getString("NOI_CAP"));
                obj.setNgay_HL(rs.getString("NGAY_HL"));
                obj.setNgay_HHL(rs.getString("NGAY_HHL"));
                obj.setPublicKey(rs.getString("PUBLICKEY"));
                obj.setSerialNumber(rs.getString("SERIALNUMBER"));
                obj.setTaiKhoan_TH(rs.getString("TAIKHOAN_TH"));
                obj.setTen_NH_TH(rs.getString("TEN_NH_TH"));
                obj.setMa_NH_TH(rs.getString("MA_NH_TH"));
                obj.setTen_TaiKhoan_TH(rs.getString("TEN_TAIKHOAN_TH"));
                obj.setTen_DV(rs.getString("TEN_DV"));
                obj.setID(rs.getString("ID"));
                obj.setACCEPTED(rs.getString("ACCEPTED"));
                obj.setChannelID(rs.getString("CHANNELID"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setDesc(rs.getString("Description"));
                obj.setNGAY_HL_UQ(rs.getString("NGAY_HL_UQ"));
                obj.setNGAY_HHL_UQ(rs.getString("NGAY_HHL_UQ"));
                obj.setThongTinLienHe(getTTLIENHE(rs.getString("ID")));
                resultList.add(obj); // add it to the result
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public String[] INSERT_HQ_NOPTIEN_HQ_GNT_201(HQ_NOPTIEN_HQ_GNT obj) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_HQ_NOPTIEN_HQ_GNT_201);
        try {
            calStmt.setInt(1, obj.getIDMASTER());
            calStmt.setString(2, obj.getTTBUTTOAN());
            calStmt.setString(3, obj.getMA_HQ());
            calStmt.setString(4, obj.getMA_LH());
            calStmt.setString(5, obj.getNAM_DK());
            calStmt.setString(6, obj.getSO_TK());
            calStmt.setString(7, obj.getMA_LT());
            calStmt.setString(8, obj.getSOTIEN());
            calStmt.setString(9, obj.getTen_HQ());
            calStmt.setString(10, obj.getTen_LH());
            calStmt.setString(11, obj.getSOTIEN_NT());
            calStmt.setString(12, obj.getNGAY_DK());
            calStmt.registerOutParameter(13, Types.NUMERIC);
            calStmt.execute();

            String[] arrResult = new String[1];
            arrResult[0] = calStmt.getString(13);
            return arrResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }
}
