/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import scb.com.vn.dbi.connection.ConnectionManager;
import scb.com.vn.ultility.jdbc.JDBCEngine;
import java.util.HashMap;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateReq;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateRes;
import scb.com.vn.common.model.cims.taikhoan.GiaiToaTKTTReq;
import scb.com.vn.common.model.cims.taikhoan.GiaiToaTKTTRes;
import scb.com.vn.common.model.cims.taikhoan.PhongToaTKTTRes;
import scb.com.vn.common.model.cims.taikhoan.PhongToaTKTTReq;
import scb.com.vn.dbi.dto.CustomerInfo;
import scb.com.vn.dbi.dto.InsuranceCustInfoDto;
import scb.com.vn.dbi.dto.ProcedureCallDto;
import scb.com.vn.dbi.utility.Utils;

/**
 *
 *
 * @author minhndb
 */
public class FCCCoreDAO {

    private static final Logger LOGGER = Logger.getLogger(FCCCoreDAO.class);

    String TRANSFERFCUBS_WITH_PAYCREDITCARD_CASH = "BEGIN FCUSR01.PKG_THU_THUENOIDIA.pr_thutienmat(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    String TRANSFERFCUBS_WITH_INSPAYMENT_CASH = "BEGIN FCUSR01.PKG_THU_THUENOIDIA.pr_thutienmat_casa(?,?,?,?,?,?,?); END;";

    String TRANSFERFCUBS_WITH_INSPAYMENT_CASHV2 = "BEGIN FCUSR01.PKG_THU_THUENOIDIA.pr_thutienmat_casa(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    String TRANSFERFCUBS_FOR_IBT = "BEGIN FCUSR01.SCB_IIB_FCC_ACCOUNT_INFO.pr_noptienmat(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    String TRANSFERFCUBS_FOR_IBT_247 = "BEGIN FCUSR01.SCB_IIB_FCC_ACCOUNT_INFO.pr_noptienmat(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    final String FN_GET_SUMMARY_FCC = " SELECT * FROM TABLE (FCUSR01.SCB_fn_get_custaccount(?))";
    final String FN_GET_CUSTACC_LIMIT = " select * From table(fcusr01.SCB_fn_get_custacc_limit(?))";
    final String FN_CREATE_PAYMENT_LOAN_ACCOUNT = "BEGIN SCB_PLGD_TIENVAY.PR_CREATE_PAYMENT_LOAN_ACCOUNT(?,?,?,?,?,?,?,?,?,?); END;";

    // final String GET_BANCH_CODE = "SELECT  BRANCH_CODE FROM STTM_CUST_ACCOUNT WHERE CUST_AC_NO = ?";
    final String GET_ACCOUNTCLASS_INFO = "{ ? = call fcusr01.scb_plgd.fn_get_aclass_info(?,?,?,?,?)}";
    final String GET_ACCOUNTCLASS_INFO_MORE = "{ ? = call fcusr01.scb_plgd.fn_get_aclass_info(?,?,?,?)}";

    String PRO_RT_CHARGE_PICKUP = "BEGIN FCUSR01.cspks_scb_utils.FN_RT_CHARGE_PICKUP(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    String PROC_RT_CHARGE_RETURN = "BEGIN PROC_RT_CHARGE_RETURN(?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    String VALIDATE_TOPUP_TKTICHLUY = "{ ? = call FCUSR01.SCB_KHANHLQ_UTILS.FN_CHECK_ALLOW_TOPUP(?,?,?,?,?)}";

    final String FN_GET_AVERAGE_AMT_SFREE = "{ ? = call fcusr01.SCB_KHANHLQ_UTILS.FN_GET_AVERAGE_AMT_SFREE(?,?,?)}";

    String getBranchCode = "select BRANCH_CODE from (SELECT A.BRANCH_CODE,A.CUST_AC_NO FROM sttms_cust_account A\n"
            + "     union all\n"
            + "     SELECT   A.BRANCH_CODE,D.VIR_ACC_NO CUST_AC_NO FROM sttms_cust_account A,fcusr01.STTM_VIRTUAL_ACCOUNTS D\n"
            + "    WHERE  D.PHY_ACC = A.CUST_AC_NO) A where A.CUST_AC_NO=?";

    final String getCustInfoByCIFID = "SELECT  dd.txtcorpdesc fullname,(C.address_line1 || ' ' || C.address_line2 || ' ' || C.address_line3 || ' ' || C.address_line4) address, "
            + " dd.unique_id_value as identitycard, B.issued_place as placeofissued,  TO_CHAR(TO_DATE (B.issued_date, 'dd/MM/yy'), 'dd-MM-yyyy') as dateissued, dd.telno as telno  "
            + " FROM STTMS_CUSTOMER C "
            + " LEFT JOIN fcc_vw_customer_details dd on   C.customer_no = dd.idcorporate"
            + " LEFT JOIN FCUSR01.STTM_CUSTOMER_CUSTOM B on  C.customer_no=B.customer_no"
            + " WHERE C.customer_no=? AND  C.customer_no=B.customer_no ";

    final String checkOverKYC = "begin FCUSR01.SCB_PLGD.Pr_Check_Trans_Limit('ODC1',?,?,?,?,?); end;";

    final String PR_CREATE_AMOUNT_BLOCK = "begin FCUSR01.SCB_GIAOTIEP_FCC_CIMS.PR_CREATE_AMOUNT_BLOCK(?,?,?,?,?,?); end;";
    final String PR_CREATE_LIST_AMOUNT_BLOCK = "begin FCUSR01.SCB_GIAOTIEP_FCC_CIMS.PR_CREATE_LIST_AMOUNT_BLOCK(?,?,?,?,?,?); end;";
    final String PR_CLOSE_AMOUNT_BLOCK = "begin FCUSR01.SCB_GIAOTIEP_FCC_CIMS.PR_CLOSE_AMOUNT_BLOCK(?,?,?,?,?); end;";

    /**
     *
     * @param custAcc
     * @param amount
     * @param content
     * @param user_maker
     * @param user_checker
     * @return
     * @throws SQLException
     */
    public String transferFCUBSCash(String custAcc, BigDecimal amount, String content, String user_maker, String user_checker) throws SQLException {
        CallableStatement calStmt = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("fcccore_live");
            calStmt = connection.prepareCall(TRANSFERFCUBS_WITH_INSPAYMENT_CASH);
            calStmt.setString(1, custAcc);
            calStmt.setBigDecimal(2, amount);
            calStmt.setString(3, content);
            calStmt.setString(4, user_maker);
            calStmt.setString(5, user_checker);
            calStmt.registerOutParameter(6, Types.VARCHAR); //status
            calStmt.registerOutParameter(7, Types.VARCHAR); //ref
            calStmt.execute();
            if ((calStmt.getString(6)).contains("CHUA MO TILL")) {
                String err = calStmt.getString(6);
                return "null" + "#" + err;
            }
            String ref = calStmt.getString(7) + "#" + calStmt.getString(6);
            return ref;
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
     * @param custAcc
     * @param amount
     * @param content
     * @param user_maker
     * @param user_checker
     * @param noiCap
     * @param ngayCap
     * @param sodt
     * @param nguoiGiaoDich
     * @param diaChi
     * @param cmnd
     * @return
     * @throws SQLException
     */
    public String transferFCUBSCashv2(String custAcc, BigDecimal amount, String content, String user_maker, String user_checker, String noiCap, String ngayCap, String sodt, String nguoiGiaoDich, String diaChi, String cmnd) throws SQLException {
        CallableStatement calStmt = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("fcccore_live");
            calStmt = connection.prepareCall(TRANSFERFCUBS_WITH_INSPAYMENT_CASHV2);
            calStmt.setString(1, custAcc);
            calStmt.setBigDecimal(2, amount);
            calStmt.setString(3, content);
            calStmt.setString(4, user_maker);
            calStmt.setString(5, user_checker);
            calStmt.setString(6, noiCap);
            calStmt.setString(7, ngayCap);
            calStmt.setString(8, sodt);
            calStmt.setString(9, nguoiGiaoDich);
            calStmt.setString(10, diaChi);
            calStmt.setString(11, cmnd);
            calStmt.registerOutParameter(12, Types.VARCHAR); //status
            calStmt.registerOutParameter(13, Types.VARCHAR); //ref
            calStmt.execute();
            if ((calStmt.getString(12)).contains("CHUA MO TILL")) {
                String err = calStmt.getString(12);
                return "null" + "#" + err;
            }
            String ref = calStmt.getString(13) + "#" + calStmt.getString(12);
            return ref;
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

    String REVERTTRANSFERFCUBS = "BEGIN FCUSR01.PKG_THU_THUENOIDIA.pr_reverse_thutienmat(?,?,?,?,?); END;";

    /**
     *
     * @param P_TRN_REF
     * @param P_XREF
     * @param pm_gdv
     * @param pm_ksv
     * @return
     * @throws SQLException
     */
    public String revertTransferFCUBS(String P_TRN_REF, String P_XREF, String pm_gdv, String pm_ksv) throws SQLException {
        CallableStatement calStmt = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("fcccore_live");
            calStmt = connection.prepareCall(REVERTTRANSFERFCUBS);
            calStmt.setString(1, P_TRN_REF);
            calStmt.setString(2, P_XREF);
            calStmt.setString(3, pm_gdv);
            calStmt.setString(4, pm_ksv);
            calStmt.registerOutParameter(5, Types.VARCHAR); //status
            calStmt.execute();
            String result = calStmt.getString(5);
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
     * @throws SQLException
     */
    public String transferFCUBS(String glAcc, BigDecimal amount, String content, String idcardName, String idcardAddress, String idcard, String idcardDob, String user_maker, String user_checker, String branchcard) throws SQLException {
        CallableStatement calStmt = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("fcccore_live");
            calStmt = connection.prepareCall(TRANSFERFCUBS_WITH_PAYCREDITCARD_CASH);
            calStmt.setString(1, branchcard);
            calStmt.setString(2, glAcc);
            calStmt.setBigDecimal(3, amount);
            calStmt.setString(4, content);
            calStmt.setString(5, idcardName);
            calStmt.setString(6, idcardAddress);
            calStmt.setString(7, idcard);
            calStmt.setString(8, idcardDob);
            calStmt.setString(9, user_maker);
            calStmt.setString(10, user_checker);
            calStmt.registerOutParameter(11, Types.VARCHAR); //status
            calStmt.registerOutParameter(12, Types.VARCHAR); //ref
            calStmt.setInt(13, 0);
            calStmt.setInt(14, 0);
            calStmt.setInt(15, 0);
            calStmt.setInt(16, 0);
            calStmt.execute();
            if ((calStmt.getString(11)).contains("CHUA MO TILL")) {
                String err = calStmt.getString(11);
                return "null" + "#" + err;
            }
            String ref = calStmt.getString(12) + "#" + calStmt.getString(11);
            return ref;
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
     * @param custno
     * @return
     * @throws Exception
     */
    public List GetAccountListByCustNoFCC(String custno) throws Exception {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("fcccore");
            ArrayList p_args = new ArrayList();
            p_args.add(custno);
            ArrayList list = JDBCEngine.executeQuery(FN_GET_SUMMARY_FCC, p_args, connection);
            return list;
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
     * @param acccount
     * @return
     * @throws Exception
     */
    public ArrayList getSttmCustAccountSynFCC(String acccount) throws Exception {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("fcccore");
            ArrayList p_args = new ArrayList();
            p_args.add(acccount);
            ArrayList list = JDBCEngine.executeQuery(FN_GET_CUSTACC_LIMIT, p_args, connection);
            return list;
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
     * @param fromAcc
     * @param loanAcc
     * @param laitronghan
     * @param vontronghan
     * @param laiphatlai
     * @param laitphatgoc
     * @param laiphatkhac
     * @param maker
     * @param checker
     * @return
     * @throws SQLException
     */
    public String PaymentLoanAccount(String fromAcc, String loanAcc, BigDecimal laitronghan, BigDecimal vontronghan, BigDecimal laiphatlai, BigDecimal laitphatgoc,
            BigDecimal laiphatkhac, String maker, String checker) throws SQLException {
        CallableStatement calStmt = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("fcccore");
            calStmt = connection.prepareCall(FN_CREATE_PAYMENT_LOAN_ACCOUNT);
            calStmt.setString(1, loanAcc);
            calStmt.setBigDecimal(2, vontronghan);
            calStmt.setBigDecimal(3, laitronghan);
            calStmt.setBigDecimal(4, laitphatgoc);
            calStmt.setBigDecimal(5, laiphatlai);
            calStmt.setBigDecimal(6, laiphatkhac);
            calStmt.setString(7, fromAcc);
            calStmt.setString(8, maker);
            calStmt.setString(9, checker);
            calStmt.registerOutParameter(10, Types.VARCHAR);
            calStmt.execute();
            String ref = calStmt.getString(10);
            return ref;
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
    //Add by Ly; 03/2018

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public String getBranchCode(String accountno) throws Exception {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("fcccore");
            ArrayList<String> p_args = new ArrayList<String>();
            p_args.add(accountno);
            ArrayList list = JDBCEngine.executeQuery(getBranchCode, p_args, connection);
            if (!list.isEmpty()) {
                HashMap hm = (HashMap) list.get(0);
                return hm.get("branch_code").toString();
            }
            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    //Add by Ly; 15/08/2018

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
     * @param Fee
     * @param Vat
     * @return
     * @throws SQLException
     */
    public String transferFCUBSWithFee(String glAcc, BigDecimal amount, String content,
            String idcardName, String idcardAddress, String idcard,
            String idcardDob, String user_maker, String user_checker,
            String branchcard,
            BigDecimal Fee, BigDecimal Vat) throws SQLException {
        CallableStatement calStmt = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("fcccore");
            calStmt = connection.prepareCall(TRANSFERFCUBS_WITH_PAYCREDITCARD_CASH);
            calStmt.setString(1, branchcard);
            calStmt.setString(2, glAcc);
            calStmt.setBigDecimal(3, amount);
            calStmt.setString(4, content);
            calStmt.setString(5, idcardName);
            calStmt.setString(6, idcardAddress);
            calStmt.setString(7, idcard);
            calStmt.setString(8, idcardDob);
            calStmt.setString(9, user_maker);
            calStmt.setString(10, user_checker);
            calStmt.registerOutParameter(11, Types.VARCHAR); //status
            calStmt.registerOutParameter(12, Types.VARCHAR); //ref
            calStmt.setBigDecimal(13, Fee);
            calStmt.setBigDecimal(14, Vat);
            calStmt.setInt(15, 0);
            calStmt.setInt(16, 0);
            calStmt.execute();
            if ((calStmt.getString(11)).contains("CHUA MO TILL")) {
                String err = calStmt.getString(11);
                return "null" + "#" + err;
            }
            String ref = calStmt.getString(12) + "#" + calStmt.getString(11);
            return ref;
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
     * @param branchcode
     * @param accountclass
     * @param amount
     * @param ccy
     * @param cif
     * @return
     * @throws Exception
     */
    public ArrayList getAccountClassInfo(String branchcode, String accountclass, String amount, String ccy, String cif) throws Exception {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        Connection connection = null;
        ArrayList retList = new ArrayList();
        try {
            connection = ConnectionManager.getConnection("fcccore");
            calStmt = connection.prepareCall(GET_ACCOUNTCLASS_INFO);
            calStmt.registerOutParameter(1, OracleTypes.CURSOR);
            calStmt.setString(2, branchcode);
            calStmt.setString(3, accountclass);
            calStmt.setString(4, ccy);
            calStmt.setString(5, amount);
            calStmt.setString(6, cif);
            calStmt.executeQuery();
            rs = (ResultSet) calStmt.getObject(1);
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("opendate", rs.getString("today"));
                h.put("matdate", rs.getString("ngaydaohan"));
                h.put("interest", rs.getString("laisuat"));
                retList.add(h);
            }
            return retList;
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
     * @param branchcode
     * @param accountclass
     * @param amount
     * @param ccy
     * @return
     * @throws Exception
     */
    public ArrayList getAccountClassInfoNew(String branchcode, String accountclass, String amount, String ccy) throws Exception {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        Connection connection = null;
        ArrayList retList = new ArrayList();
        try {
            connection = ConnectionManager.getConnection("fcccore");
            calStmt = connection.prepareCall(GET_ACCOUNTCLASS_INFO_MORE);
            calStmt.registerOutParameter(1, OracleTypes.CURSOR);
            calStmt.setString(2, branchcode);
            calStmt.setString(3, accountclass);
            calStmt.setString(4, ccy);
            calStmt.setString(5, amount);
            calStmt.executeQuery();
            rs = (ResultSet) calStmt.getObject(1);
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("opendate", rs.getString("today"));
                h.put("matdate", rs.getString("ngaydaohan"));
                h.put("interest", rs.getString("laisuat"));
                retList.add(h);
            }
            return retList;
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
     * @param brnTrn
     * @param productcode
     * @param ccy
     * @param amt
     * @param accNo
     * @param accBrn
     * @return
     * @throws Exception
     */
    public ProcedureCallDto getFeeMobile(String brnTrn, String productcode, String ccy, String amt, String accNo, String accBrn) throws Exception {
        CallableStatement calStmt = null;
        Connection connection = null;
        ProcedureCallDto pro = new ProcedureCallDto();
        try {
            connection = ConnectionManager.getConnection("fcccore_live");
            calStmt = connection.prepareCall(PRO_RT_CHARGE_PICKUP);
            calStmt.setString(1, brnTrn);
            calStmt.setString(2, productcode);
            calStmt.setString(3, ccy);
            calStmt.setString(4, amt);
            calStmt.setString(5, accNo);
            calStmt.setString(6, accBrn);
            calStmt.registerOutParameter(7, Types.INTEGER);
            calStmt.registerOutParameter(8, Types.INTEGER);
            calStmt.registerOutParameter(9, Types.INTEGER);
            calStmt.registerOutParameter(10, Types.INTEGER);
            calStmt.registerOutParameter(11, Types.INTEGER);
            calStmt.registerOutParameter(12, Types.VARCHAR);
            calStmt.registerOutParameter(13, Types.VARCHAR);
            calStmt.execute();
            int charg1 = calStmt.getInt(7);
            int charg2 = calStmt.getInt(8);
            int charg3 = calStmt.getInt(9);
            int charg4 = calStmt.getInt(10);
            int charg5 = calStmt.getInt(11);
            int sum = 0;
            sum += charg1;
            sum += charg2;
            sum += charg3;
            sum += charg4;
            sum += charg5;
            pro.setErrorStatus("0");
            pro.setErrorMessage(Integer.toString(sum));
            if (calStmt.getString(12) != null) {
                pro.setOther(calStmt.getString(12));
                if (calStmt.getString(13) != null) {
                    pro.setOther(pro.getOther().concat("_").concat(calStmt.getString(13)));
                }
            }
            return pro;
        } catch (Exception ex) {
            LOGGER.error("getFeeMobile  - productcode" + ex);
            pro.setErrorStatus("-99");
            pro.setErrorMessage(ex.toString());
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
     * @param pm_product
     * @param pm_cr_brn
     * @param pm_cr_acc
     * @param pm_amt
     * @param pm_loaitien
     * @param pm_noidung
     * @param pm_tennguoinop
     * @param pm_diachinguoinop
     * @param pm_socmnd
     * @param pm_ngaycap
     * @param pm_noicap
     * @param pm_gdv
     * @param pm_ksv
     * @return
     * @throws SQLException
     */
    public String[] transferFCUBSForIBTCounter(String pm_product,
            String pm_cr_brn,
            String pm_cr_acc,
            String pm_amt,
            String pm_loaitien,
            String pm_noidung,
            String pm_tennguoinop,
            String pm_diachinguoinop,
            String pm_socmnd,
            String pm_ngaycap,
            String pm_noicap,
            String pm_gdv,
            String pm_ksv) throws SQLException {
        CallableStatement calStmt = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("fcccore_live");
            calStmt = connection.prepareCall(TRANSFERFCUBS_FOR_IBT_247);
            calStmt.setString(1, pm_product);
            calStmt.setString(2, pm_cr_brn);
            calStmt.setString(3, pm_cr_acc);
            calStmt.setString(4, pm_amt);
            calStmt.setString(5, pm_loaitien);
            calStmt.setString(6, pm_noidung);
            calStmt.setString(7, pm_tennguoinop);
            calStmt.setString(8, pm_diachinguoinop);
            calStmt.setString(9, pm_socmnd);
            calStmt.setString(10, pm_ngaycap);
            calStmt.setString(11, pm_noicap);
            calStmt.setString(12, pm_gdv);
            calStmt.setString(13, pm_ksv);
            calStmt.setString(14, "");
            calStmt.setString(15, "");
            calStmt.registerOutParameter(16, Types.VARCHAR); //pm_fccref
            calStmt.registerOutParameter(17, Types.VARCHAR); //pm_xref
            calStmt.registerOutParameter(18, Types.VARCHAR); //RESULT_CODE
            calStmt.registerOutParameter(19, Types.VARCHAR); //RESULT_DESC
            calStmt.execute();
            String[] result = new String[4];
            result[0] = calStmt.getString(16);
            result[1] = calStmt.getString(17);
            result[2] = calStmt.getString(18);
            result[3] = calStmt.getString(19);
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

    String CHECKPHONENUMBERATCORE = "BEGIN KHANHLQ.PR_SCB_TRACUU_MOBILE(?,?,?); END;";

    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean checkPhoneNumberAtCore(String phoneNumber) throws RemoteException, SQLException {
        boolean result = false;
        CallableStatement calStmt = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("fcccore");
            calStmt = connection.prepareCall(CHECKPHONENUMBERATCORE);
            calStmt.setString(1, phoneNumber);
            calStmt.registerOutParameter(2, Types.VARCHAR); // PM_ERR. 00 ~ thanh cong, 01 ~ that bai
            calStmt.registerOutParameter(3, Types.VARCHAR); // PM_ERR_DESC
            calStmt.execute();
            String errorCode = calStmt.getString(2);
            if ("00".equals(errorCode)) {
                result = true;
            } else {
                String newPhone = Utils.swapPhoneNumber(phoneNumber);
                calStmt = connection.prepareCall(CHECKPHONENUMBERATCORE);
                calStmt.setString(1, newPhone);
                calStmt.registerOutParameter(2, Types.VARCHAR); // PM_ERR. 00 ~ thanh cong, 01 ~ that bai
                calStmt.registerOutParameter(3, Types.VARCHAR); // PM_ERR_DESC
                calStmt.execute();
                errorCode = calStmt.getString(2);
                if ("00".equals(errorCode)) {
                    result = true;
                }
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error("checkPhoneNumberAtCore --> phoneNumber = ["
                    + phoneNumber + "], Ex: " + ex);
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
     * @param acccountNo
     * @param branchcode
     * @param amount
     * @return
     * @throws Exception
     */
    public ProcedureCallDto validateTopupTKTichLuy(String acccountNo, String branchcode, String amount) throws Exception {
        CallableStatement calStmt = null;
        Connection connection = null;
        ProcedureCallDto pro = new ProcedureCallDto();
        try {
            connection = ConnectionManager.getConnection("fcccore");
            calStmt = connection.prepareCall(VALIDATE_TOPUP_TKTICHLUY);
            calStmt.setString(2, acccountNo);
            calStmt.setString(3, branchcode);
            calStmt.setString(4, amount);
            calStmt.registerOutParameter(1, Types.NUMERIC);
            calStmt.registerOutParameter(5, Types.VARCHAR);
            calStmt.registerOutParameter(6, Types.VARCHAR);
            calStmt.execute();
            int result = calStmt.getInt(1);
            String errorcode = calStmt.getString(5);
            String errorMessage = calStmt.getString(6);
            pro.setErrorStatus("1");
            if (result == 0) {
                pro.setErrorStatus("0");
            } else {
                pro.setErrorStatus("1");
                pro.setErrorMessage(errorcode);
                pro.setOther(errorMessage);
            }
            return pro;
        } catch (Exception ex) {
            LOGGER.error("validateTopupTKTichLuy -" + ex);
            pro.setErrorStatus("-99");
            pro.setErrorMessage(ex.toString());
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
     * @param brnTrn
     * @param productcode
     * @param ccy
     * @param amt
     * @param accNo
     * @param accBrn
     * @return
     * @throws Exception
     */
    public ProcedureCallDto getFeeMobileHasSFee(String brnTrn, String productcode, String ccy, String amt, String accNo, String accBrn) throws Exception {
        CallableStatement calStmt = null;
        Connection connection = null;
        ProcedureCallDto pro = new ProcedureCallDto();
        try {
            connection = ConnectionManager.getConnection("fcccore");
            calStmt = connection.prepareCall(PROC_RT_CHARGE_RETURN);
            calStmt.setString(1, brnTrn);
            calStmt.setString(2, productcode);
            calStmt.setString(3, ccy);
            calStmt.setString(4, amt);
            calStmt.setString(5, accNo);
            calStmt.setString(6, accBrn);
            calStmt.registerOutParameter(7, Types.INTEGER);
            calStmt.registerOutParameter(8, Types.INTEGER);
            calStmt.registerOutParameter(9, Types.INTEGER);
            calStmt.registerOutParameter(10, Types.INTEGER);
            calStmt.registerOutParameter(11, Types.INTEGER);
            calStmt.registerOutParameter(12, Types.VARCHAR);
            calStmt.registerOutParameter(13, Types.VARCHAR);
            calStmt.registerOutParameter(14, Types.VARCHAR);
            calStmt.execute();
            int charg1 = calStmt.getInt(7);
            int charg2 = calStmt.getInt(8);
            int charg3 = calStmt.getInt(9);
            int charg4 = calStmt.getInt(10);
            int charg5 = calStmt.getInt(11);
            int sum = 0;
            sum += charg1;
            sum += charg2;
            sum += charg3;
            sum += charg4;
            sum += charg5;
            pro.setErrorStatus("0");
            pro.setErrorMessage(Integer.toString(sum));
            if (calStmt.getString(12) != null) {
                pro.setOther(calStmt.getString(12));
                if (calStmt.getString(13) != null) {
                    pro.setOther(pro.getOther().concat("_").concat(calStmt.getString(13)));
                }
            }
            if (calStmt.getString(14) != null) {
                pro.setOther(calStmt.getString(14));
            }
            return pro;
        } catch (Exception ex) {
            LOGGER.error("getFeeMobile  - productcode" + ex);
            pro.setErrorStatus("-99");
            pro.setErrorMessage(ex.toString());
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
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getAvgBalanceFromCore(String accountno) throws Exception {
        CallableStatement calStmt = null;
        Connection connection = null;
        ArrayList retList = new ArrayList();
        try {
            connection = ConnectionManager.getConnection("fcccore");
            calStmt = connection.prepareCall(FN_GET_AVERAGE_AMT_SFREE);
            calStmt.setString(2, accountno);
            calStmt.registerOutParameter(1, Types.NUMERIC);
            calStmt.registerOutParameter(3, Types.NUMERIC);
            calStmt.registerOutParameter(4, Types.NUMERIC);
            calStmt.execute();
            Object result = calStmt.getObject(1);
            if (result.equals(BigDecimal.ZERO)) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("curMonthAvgBal", String.valueOf(calStmt.getBigDecimal(3)));
                h.put("lastMonthAvgBal", String.valueOf(calStmt.getBigDecimal(4)));
                retList.add(h);
                return retList;
            } else {
                LOGGER.info("getAvgBalanceFromCore-accountno: " + accountno);
                LOGGER.info("getAvgBalanceFromCore: " + result);
                return null;
            }
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

    final String getExchangeRate = "BEGIN FCUSR01.SCB_IIB_FCC_ACCOUNT_INFO.PR_TRANSFER_AMT_FOR_API(?,?,?,?,?,?,?,?); END;";

    /**
     *
     * @param req
     * @return
     * @throws Exception
     */
    public ExchangeRateRes getExchangeRate(ExchangeRateReq req) throws Exception {
        CallableStatement calStmt = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("fcccore");
            calStmt = connection.prepareCall(getExchangeRate);
            calStmt.setString(1, req.getBranch());
            calStmt.setString(2, req.getProduct());
            calStmt.setString(3, req.getCcy());
            calStmt.setBigDecimal(4, req.getMoney());
            calStmt.registerOutParameter(5, OracleTypes.NUMBER);
            calStmt.registerOutParameter(6, OracleTypes.NUMBER);
            calStmt.registerOutParameter(7, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(8, OracleTypes.VARCHAR);
            calStmt.execute();
            ExchangeRateRes res = new ExchangeRateRes();
            res.setCcy(req.getCcy());
            res.setMoney(req.getMoney());
            res.setProduct(req.getProduct());
            res.setRate(calStmt.getBigDecimal(5));
            res.setMoneyExchange(calStmt.getBigDecimal(6));
            res.setResultCode(calStmt.getString(7));
            res.setResultDesc(calStmt.getString(8));
            return res;
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
     * Get customer by cifid
     *
     * @param cif
     * @return
     * @throws Exception
     */
    public InsuranceCustInfoDto getCustInfoByCIFID(String cif) throws Exception {

        Connection connection = null;
        InsuranceCustInfoDto custInof = new InsuranceCustInfoDto();
        try {
            connection = ConnectionManager.getConnection("fcccore");
            ArrayList p_args = new ArrayList();
            p_args.add(cif);
            ArrayList list = JDBCEngine.executeQuery(getCustInfoByCIFID, p_args, connection);

            if (list.size() > 0) {
                HashMap hm = (HashMap) list.get(0);
                custInof.setFullname(hm.get("fullname") != null ? hm.get("fullname").toString() : "");
                custInof.setAddress(hm.get("address") != null ? hm.get("address").toString() : "");
                custInof.setTelNo(hm.get("telno") != null ? hm.get("telno").toString() : "");
                custInof.setIdentityCard(hm.get("identitycard") != null ? hm.get("identitycard").toString() : "");
                custInof.setPlaceOfIssued(hm.get("placeofissued") != null ? hm.get("placeofissued").toString() : "");
                custInof.setDateIssued((hm.get("dateissued") != null ? hm.get("dateissued").toString() : ""));
            }

            return custInof;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    String getCustInfobyCif = "select C.Mobile_Number,\n"
            + "       C.D_ADDRESS1||' '||C.D_ADDRESS2||' '|| C.D_ADDRESS3||' '||C.D_ADDRESS4 address,\n"
            + "       C.Date_Of_Birth,\n"
            + "       S.Full_Name,\n"
            + "       S.unique_id_value ,\n"
            + "       D.ISSUED_PLACE,\n"
            + "       D.ISSUED_DATE,\n"
            + "       C.E_MAIL,\n"
            + "       S.UDF_5\n"
            + "from \n"
            + "     STTMS_CUSTOMER S left join STTM_CUST_PERSONAL C\n"
            + "                    on C.Customer_No=S.Customer_No\n"
            + "                    left join FCUSR01.STTM_CUSTOMER_CUSTOM D\n"
            + "                    on S.Customer_No=D.Customer_No\n"
            + "where C.Customer_No=?";

    public CustomerInfo getCustInfoByCif(String cifno) throws SQLException {
        Connection connection = null;
        CustomerInfo custInfo = new CustomerInfo();
        try {
            connection = ConnectionManager.getConnection("fcccore");
            ArrayList p_args = new ArrayList();
            p_args.add(cifno);
            ArrayList list = JDBCEngine.executeQuery(getCustInfobyCif, p_args, connection);

            if (list.size() > 0) {
                HashMap hm = (HashMap) list.get(0);
                custInfo.setFull_Name(hm.get("full_name") != null ? hm.get("full_name").toString() : "");
                custInfo.setAddress(hm.get("address") != null ? hm.get("address").toString() : "");
                custInfo.setMobile_Number(hm.get("mobile_number") != null ? hm.get("mobile_number").toString() : "");
                custInfo.setUnique_id_value(hm.get("unique_id_value") != null ? hm.get("unique_id_value").toString() : "");
                custInfo.setISSUED_PLACE(hm.get("issued_place") != null ? hm.get("issued_place").toString() : "");
                custInfo.setISSUED_DATE((hm.get("issued_date") != null ? hm.get("issued_date").toString() : ""));
                custInfo.setEMAIL(hm.get("e_mail") != null ? hm.get("e_mail").toString() : "");
                custInfo.setDate_Of_Birth(hm.get("date_of_birth") != null ? hm.get("date_of_birth").toString() : "");
                custInfo.setUDF_5(hm.get("udf_5") != null ? hm.get("udf_5").toString() : "");
            }
            return custInfo;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public String[] transferFCUBSForIBTCounter_247(String pm_product,
            String pm_cr_brn,
            String pm_cr_acc,
            String pm_amt,
            String pm_loaitien,
            String pm_noidung,
            String pm_tennguoinop,
            String pm_diachinguoinop,
            String pm_socmnd,
            String pm_ngaycap,
            String pm_noicap,
            String pm_gdv,
            String pm_ksv,
            String destAccount,
            String destBank) throws SQLException {
        CallableStatement calStmt = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("fcccore_live");
            calStmt = connection.prepareCall(TRANSFERFCUBS_FOR_IBT_247);
            calStmt.setString(1, pm_product);
            calStmt.setString(2, pm_cr_brn);
            calStmt.setString(3, pm_cr_acc);
            calStmt.setString(4, pm_amt);
            calStmt.setString(5, pm_loaitien);
            calStmt.setString(6, pm_noidung);
            calStmt.setString(7, pm_tennguoinop);
            calStmt.setString(8, pm_diachinguoinop);
            calStmt.setString(9, pm_socmnd);
            calStmt.setString(10, pm_ngaycap);
            calStmt.setString(11, pm_noicap);
            calStmt.setString(12, pm_gdv);
            calStmt.setString(13, pm_ksv);

            calStmt.setString(14, destBank);
            calStmt.setString(15, destAccount);

            calStmt.registerOutParameter(16, Types.VARCHAR); //pm_fccref
            calStmt.registerOutParameter(17, Types.VARCHAR); //pm_xref
            calStmt.registerOutParameter(18, Types.VARCHAR); //RESULT_CODE
            calStmt.registerOutParameter(19, Types.VARCHAR); //RESULT_DESC
            calStmt.execute();
            String[] result = new String[4];
            result[0] = calStmt.getString(16);
            result[1] = calStmt.getString(17);
            result[2] = calStmt.getString(18);
            result[3] = calStmt.getString(19);
            LOGGER.info("result transferFCUBSForIBTCounter_247:" + calStmt.getString(16));
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

    public String checkOverKYC(String custAcc, String brnAcc, BigDecimal amount) throws SQLException {
        CallableStatement calStmt = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("fcccore_live");
            calStmt = connection.prepareCall(checkOverKYC);
            calStmt.setString(1, custAcc);
            calStmt.setString(2, brnAcc);
            calStmt.setBigDecimal(3, amount);
            calStmt.registerOutParameter(4, Types.VARCHAR); //status
            calStmt.registerOutParameter(5, Types.VARCHAR); //ref
            calStmt.execute();
            String ref = calStmt.getString(4) + "#" + calStmt.getString(5);
            return ref;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public PhongToaTKTTRes phongToaTKTT(PhongToaTKTTReq req) throws Exception {
        CallableStatement calStmt = null;
        Connection connection = null;
        PhongToaTKTTRes resp = null;
        
        try {
            connection = ConnectionManager.getConnection("fcccore");
            if ("0".equals(req.getType())) {//phong toa theo tk
                calStmt = connection.prepareCall(PR_CREATE_AMOUNT_BLOCK);
            } else { // phong toa theo cif
                calStmt = connection.prepareCall(PR_CREATE_LIST_AMOUNT_BLOCK);
            }
            resp = new PhongToaTKTTRes();
            calStmt.setString(1, req.getAccounNumberOrCIF());
            calStmt.setString(2, req.getUserName());
            calStmt.setString(3, req.getMaDonVi());
            calStmt.setString(4, req.getExpiryDate());
            calStmt.registerOutParameter(5, Types.VARCHAR);
            calStmt.registerOutParameter(6, Types.VARCHAR);
            calStmt.execute();

            resp.setErrorCode(calStmt.getString(5));
            resp.setErrorMsg(calStmt.getString(6));

            return resp;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return resp;
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public GiaiToaTKTTRes giaiToaTKTT(GiaiToaTKTTReq req) throws Exception {
        CallableStatement calStmt = null;
        Connection connection = null;
        GiaiToaTKTTRes resp = null;
        
        try {
            connection = ConnectionManager.getConnection("fcccore");
            calStmt = connection.prepareCall(PR_CLOSE_AMOUNT_BLOCK);
            resp = new GiaiToaTKTTRes();
            calStmt.setString(1, req.getAccounNumber());
            calStmt.setString(2, req.getUserName());
            calStmt.setString(3, req.getMaDonVi());
            calStmt.registerOutParameter(4, Types.VARCHAR);
            calStmt.registerOutParameter(5, Types.VARCHAR);
            calStmt.execute();

            resp.setErrorCode(calStmt.getString(4));
            resp.setErrorMsg(calStmt.getString(5));

            return resp;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return resp;
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
