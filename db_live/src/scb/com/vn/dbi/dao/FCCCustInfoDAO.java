/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import scb.com.vn.constant.CIMSConstant;
import scb.com.vn.dbi.bo.CWLiveBO;
import scb.com.vn.dbi.connection.ConnectionManager;
import scb.com.vn.dbi.dto.CIMSCardInfo;
import scb.com.vn.dbi.dto.CIMSCustResult;
import scb.com.vn.ultility.Xml;

/**
 *
 * @author CORE77
 */
public class FCCCustInfoDAO {

    private static final Logger LOGGER = Logger.getLogger(FCCCustInfoDAO.class);

    final String GetCustomerInfoByCIF = "{ ? = call fcusr01.SCB_GIAOTIEP_FCC_CIMS.fn_GetCustomerInfoByCIF(?)}";
    final String GetCustomerInfoByIDCard = "{ ? = call fcusr01.SCB_GIAOTIEP_FCC_CIMS.Fn_GetCustomerInfoByIDCard(?)}";
    final String GetCustomerInfoByPhone = "{ ? = call fcusr01.SCB_GIAOTIEP_FCC_CIMS.Fn_GetCustomerInfoByPhone(?)}";
    final String CC_GetListAccount = "{ ? = call fcusr01.SCB_GIAOTIEP_FCC_CIMS.FN_GetListAccountbyCIF(?)}";
    final String CC_GetRecentTransaction = "{ ? = call fcusr01.SCB_GIAOTIEP_FCC_CIMS.FN_GetRecentTransaction(?)}";

    final String CC_GetTDAccounts = "{ ? = call fcusr01.SCB_GIAOTIEP_FCC_CIMS.FN_GET_TD_ACC_MASTER(?)}";
    final String CC_GetTDAccountDetails = "{ ? = call fcusr01.SCB_GIAOTIEP_FCC_CIMS.FN_GET_TD_ACC_DETAILS(?)}";
    final String CC_GetTDAccountTranHistDetails = "{ ? = call fcusr01.SCB_GIAOTIEP_FCC_CIMS.FN_GET_TD_ACC_DETAILS_HIST(?)}";

    final String CC_GetCLAccounts = "{ ? = call fcusr01.SCB_GIAOTIEP_FCC_CIMS.FN_GET_CL_ACC_MASTER(?)}";
    final String CC_GetCLAccountDetails = "{ ? = call fcusr01.SCB_GIAOTIEP_FCC_CIMS.FN_GET_CL_ACC_DETAILS(?)}";
    final String CC_GetCLHistPaymentDetails = "{ ? = call fcusr01.SCB_GIAOTIEP_FCC_CIMS.FN_GET_CT_LSU_TRANOVAY(?)}";
    final String CC_GetCLAccountPayCalDetails = "{ ? = call fcusr01.SCB_GIAOTIEP_FCC_CIMS.FN_GET_LICH_TRANOVAY(?)}";

    /**
     *
     * @param CIF
     * @return
     * @throws SQLException
     */
    public String GetCustomerInfoByCIF(String CIF) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        int id = new Random().nextInt(99999);
        try {
            LOGGER.info("[IN] - GetCustomerInfoByCIF - Goi qua core - cif = [" + CIF + "] - id = [" + id + "]");
            connection = ConnectionManager.getConnection("fccgw");
            calStmt = connection.prepareCall(GetCustomerInfoByCIF);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, CIF);
            calStmt.executeQuery();
            String fccResult = calStmt.getString(1);
            LOGGER.info("[OUT] - GetCustomerInfoByCIF - Goi qua core - cif = [" + CIF + "] - id = [" + id + "] - result = [" + fccResult + "]");
            return GetCustomerInfoExtended(fccResult);
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
     * @param CMND
     * @return
     * @throws SQLException
     */
    public String GetCustomerInfoByIDCard(String CMND) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("fccgw");
            calStmt = connection.prepareCall(GetCustomerInfoByIDCard);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, CMND);
            calStmt.executeQuery();
            return GetCustomerInfoExtended(calStmt.getString(1));
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
     * @param phone
     * @return
     * @throws SQLException
     */
    public String GetCustomerInfoByPhone(String phone) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            //1. lay so cif core
            
            connection = ConnectionManager.getConnection("fccgw");
            calStmt = connection.prepareCall(GetCustomerInfoByPhone);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, phone);
            calStmt.executeQuery();
            
            //2. lay thong tin cw theo so cif và sdt core tra ra
            
            return GetCustomerInfoExtendedForPhone(calStmt.getString(1), phone);
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

    private String GetCustomerInfoExtendedForPhone(String xml, String phone) throws RemoteException {
        try {
            /* Thuc hien thay ky tu & thanh ma chuan XML de co the convert ve XML thanh cong */
            xml = xml.replace(CIMSConstant.AMPERSAND, CIMSConstant.AMPERSAND_XML_CODE);
            CIMSCustResult req = (CIMSCustResult) Xml.unMarshaller(CIMSCustResult.class, xml);
            req.sortCIMSCustInfo();
            if ("0".equalsIgnoreCase(req.getCode())) {
                // Thanh cong, lay them so dt trong cw.
                CWLiveBO cwLiveBO = new CWLiveBO();
                String info = cwLiveBO.CC_GetExtendedCustInfo(req.getCustInfo()[0].getCif());
                CIMSCardInfo cardInfo = new CIMSCardInfo();
                if (info == null) {
                    cardInfo.setPhone("");
                    cardInfo.setCustType("");
                } else if (info.contains(";")) {
                    cardInfo.setPhone(info.split(";")[0]);
                    cardInfo.setCustType(info.split(";")[1]);
                    if (info.split(";").length > 2) {
                        if ("".equals(req.getCustInfo()[0].getVIP().trim())) {
                            req.getCustInfo()[0].setVIP(info.split(";")[2]);
                        } else {
                            req.getCustInfo()[0].setVIP(req.getCustInfo()[0].getVIP() + ";" + info.split(";")[2]);
                        }
                    }
                } else if (!"".equals(info)) {
                    cardInfo.setPhone(info);
                    cardInfo.setCustType("");
                } else {
                    cardInfo.setPhone("");
                    cardInfo.setCustType("");
                }
                req.setCardInfo(cardInfo);
                String result = Xml.Marshaller(req);
                return result;
            } else {
                // Core ko co du lieu --> di qua CW lay gia tri tra ve
                CWLiveBO cwLiveBO = new CWLiveBO();
                String info = cwLiveBO.CC_GetExtendedCustInfoByPhone(phone);
                if (!info.isEmpty()) {
                    req = (CIMSCustResult) Xml.unMarshaller(CIMSCustResult.class, info);
                    if ("0".equalsIgnoreCase(req.getCode())) {
                        return GetCustomerInfoByCIF(req.getCustInfo()[0].getCif());
                    }
                }
            }
            return xml;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    private String GetCustomerInfoExtended(String xml) throws RemoteException {
        try {
            /* Thuc hien thay ky tu & thanh ma chuan XML de co the convert ve XML thanh cong */
            xml = xml.replace(CIMSConstant.AMPERSAND, CIMSConstant.AMPERSAND_XML_CODE);
            CIMSCustResult req = (CIMSCustResult) Xml.unMarshaller(CIMSCustResult.class, xml);
            req.sortCIMSCustInfo();
            if ("0".equalsIgnoreCase(req.getCode())) {
                // Thanh cong, lay them so dt trong cw.
                CWLiveBO cwLiveBO = new CWLiveBO();
                String info = cwLiveBO.CC_GetExtendedCustInfo(req.getCustInfo()[0].getCif());
                CIMSCardInfo cardInfo = new CIMSCardInfo();
                if (info == null) {
                    cardInfo.setPhone("");
                    cardInfo.setCustType("");
                } else if (info.contains(";")) {
                    cardInfo.setPhone(info.split(";")[0]);
                    cardInfo.setCustType(info.split(";")[1]);
                    if (info.split(";").length > 2) {
                        if (req.getCustInfo()[0].getVIP().trim().equals("")) {
                            req.getCustInfo()[0].setVIPVipInfo(info.split(";")[2]);
                        } else {
                            req.getCustInfo()[0].setVIPVipInfo(req.getCustInfo()[0].getVIP() + ";" + info.split(";")[2]);
                        }
                    }
                } else if (!info.equals("")) {
                    cardInfo.setPhone(info);
                    cardInfo.setCustType("");
                } else {
                    cardInfo.setPhone("");
                    cardInfo.setCustType("");
                }
                req.setCardInfo(cardInfo);
                String result = Xml.Marshaller(req);
                return result;
            } else {
                //Khong thanh cong, tra loi
                return xml;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetListAccount(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("fccgw");
            calStmt = connection.prepareCall(CC_GetListAccount);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetRecentTransaction(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("fccgw");
            calStmt = connection.prepareCall(CC_GetRecentTransaction);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetTDAccounts(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("fccgw");
            calStmt = connection.prepareCall(CC_GetTDAccounts);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetTDAccountDetails(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("fccgw");
            calStmt = connection.prepareCall(CC_GetTDAccountDetails);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetTDAccountTranHistDetails(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("fccgw");
            calStmt = connection.prepareCall(CC_GetTDAccountTranHistDetails);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetCLAccounts(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("fccgw");
            calStmt = connection.prepareCall(CC_GetCLAccounts);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetCLAccountDetails(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("fccgw");
            calStmt = connection.prepareCall(CC_GetCLAccountDetails);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetCLHistPaymentDetails(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("fccgw");
            calStmt = connection.prepareCall(CC_GetCLHistPaymentDetails);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetCLAccountPayCalDetails(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("fccgw");
            calStmt = connection.prepareCall(CC_GetCLAccountPayCalDetails);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
