package scb.com.vn.dbi.dao;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;
import org.apache.log4j.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import scb.com.vn.dbi.dto.EbIssuecard;
import scb.com.vn.dbi.dto.ProcedureCallDto;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author lydty
 */
public class FCDBTOSMSSCBDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(FCDBTOSMSSCBDAO.class);

    //CO SU DUNG
    final String GET_LIST_DATAPAY = "SELECT * from BC_BILL t where prem_typ = ? and pol_num=?";
    final String GET_LIST_POLNUM = "SELECT DISTINCT POL_NUM from BC_BILL t where owner_id = ?";
    final String GET_LIST_PREMTYP = "SELECT DISTINCT prem_typ from BC_BILL t where pol_num = ?";
    //check lai voi BAO
    final String GET_ACCOUNTTD = "SELECT * FROM fcc_vw_msttddetails A,  FCC_VW_MSTPRODUCTCLASSES B, FCC_VW_MSTACCOUNT C WHERE A.idaccount = C.idaccount and  C.prdcode=B.account_class and  A.codbranch = C.codbranch and  A.idaccount=?";
    final String GET_CUST_INFO = "select * from FCC_VW_CUST_INFO t where t.idcust=?";
    final String GET_LISTBRANCH = "select * from vw_branch_scb";
    final String FIND_CUSTOMER = "SELECT DISTINCT A.* FROM STTMS_CUSTOMER@FCATFCCLINK A \n"
            + " LEFT JOIN STTMS_CUST_ACCOUNT@FCATFCCLINK B ON A.CUSTOMER_NO=B.CUST_NO\n"
            + " where B.RECORD_STAT = 'O' AND B.CCY = 'VND' AND B.ACCOUNT_TYPE = 'U' AND SUBSTR(B.ACCOUNT_CLASS,1,2) IN ('CA','TC') AND 1=1 ";
    //check lai voi BAO
    String SEARCH_SMS_ALERT = "SELECT * FROM VW_REGISTER_SMS WHERE 1=1 ";

    //THU PHI TU DONG
    //KHONG LAY TK THAU CHI
    // Thieu bang tren core
    final String GET_CASACCC_MAXBAL_FEESMS_NO_TC = "SELECT * FROM ( "
            + " SELECT A.BRANCH_CODE,  A.CUST_AC_NO,  A.AC_DESC,  A.CUST_NO,  A.CCY,  A.ACCOUNT_CLASS,  A.AC_OPEN_DATE,  A.ALT_AC_NO,  A.RECORD_STAT,  "
            + " A.AUTH_STAT,  A.MAKER_ID,  A.MAKER_DT_STAMP,  A.CHECKER_ID,  A.CHECKER_DT_STAMP,  A.ONCE_AUTH,  A.LIMIT_CCY,  A.OFFLINE_LIMIT,  "
            + " A.CAS_ACCOUNT,  A.ACY_OPENING_BAL,  A.LCY_OPENING_BAL,  A.ACY_CURR_BALANCE,  A.LCY_CURR_BALANCE,  A.ACY_BLOCKED_AMOUNT,   "
            + " A.ACY_AVL_BAL ,  "
            + " A.DATE_LAST_CR_ACTIVITY,  A.DATE_LAST_DR_ACTIVITY,  A.DATE_LAST_DR,  A.DATE_LAST_CR, B.FULL_NAME,  B.UDF_1 AS IDCARD,  B.UDF_2 AS IDCARD_LOC,  "
            + " B.UDF_3 AS IDCARD_DOB,  B.ADDRESS_LINE1 as ADDRESS,  B.CUSTOMER_TYPE  "
            + " FROM VW_ACCT_THUPHISMS A left join STTMS_CUSTOMER@FCATFCCLINK B  on A.CUST_NO = B.CUSTOMER_NO "
            + " LEFT JOIN STTM_CUST_ACCOUNT_LINKAGES@FCATFCCLINK e ON a.CUST_AC_NO = e.cust_ac_no "
            + " WHERE A.CUST_NO = ? "
            + " AND substr(account_class,1,3)<>'TCC' and substr(account_class,1,3)<>'TCI'"
            + " ORDER BY A.ACY_AVL_BAL  DESC "
            + " ) WHERE  ROWNUM=1 AND CUST_NO= ? ";
    //CHI LAY TK THAU CHI
    final String GET_CASACCC_MAXBAL_FEESMS_TC = "SELECT * FROM ( "
            + " SELECT A.BRANCH_CODE,  A.CUST_AC_NO,  A.AC_DESC,  A.CUST_NO,  A.CCY,  A.ACCOUNT_CLASS,  A.AC_OPEN_DATE,  A.ALT_AC_NO,  A.RECORD_STAT,  "
            + " A.AUTH_STAT,  A.MAKER_ID,  A.MAKER_DT_STAMP,  A.CHECKER_ID,  A.CHECKER_DT_STAMP,  A.ONCE_AUTH,  A.LIMIT_CCY,  A.OFFLINE_LIMIT,  "
            + " A.CAS_ACCOUNT,  A.ACY_OPENING_BAL,  A.LCY_OPENING_BAL,  A.ACY_CURR_BALANCE,  A.LCY_CURR_BALANCE,  A.ACY_BLOCKED_AMOUNT,   "
            + " A.ACY_AVL_BAL ,  "
            + " A.DATE_LAST_CR_ACTIVITY,  A.DATE_LAST_DR_ACTIVITY,  A.DATE_LAST_DR,  A.DATE_LAST_CR, B.FULL_NAME,  B.UDF_1 AS IDCARD,  B.UDF_2 AS IDCARD_LOC,  "
            + " B.UDF_3 AS IDCARD_DOB,  B.ADDRESS_LINE1 as ADDRESS,  B.CUSTOMER_TYPE  "
            + " FROM VW_ACCT_THUPHISMS A left join STTMS_CUSTOMER@FCATFCCLINK B  on A.CUST_NO = B.CUSTOMER_NO "
            + " LEFT JOIN STTM_CUST_ACCOUNT_LINKAGES@FCATFCCLINK e ON a.CUST_AC_NO = e.cust_ac_no "
            + " WHERE A.CUST_NO = ? "
            + " AND (substr(account_class,1,3)='TCC' OR substr(account_class,1,3)='TCI')"
            + "ORDER BY A.ACY_AVL_BAL  DESC "
            + " ) WHERE  ROWNUM=1 AND CUST_NO= ? ";

    //KHONG LAY TK THAU CHI
    //Khong ton tai bang :usercustaccttxnxref
    final String GET_CASACCC_MAXBAL_FEESMSKHDN_NO_TC = ""
            + "SELECT * FROM "
            + "              ("
            + "                SELECT A.BRANCH_CODE,  A.CUST_AC_NO,  A.AC_DESC,  A.CUST_NO,  A.CCY,  A.ACCOUNT_CLASS,  A.AC_OPEN_DATE,  A.ALT_AC_NO,  A.RECORD_STAT,  "
            + "                A.AUTH_STAT,  A.MAKER_ID,  A.MAKER_DT_STAMP,  A.CHECKER_ID,  A.CHECKER_DT_STAMP,  A.ONCE_AUTH,  A.LIMIT_CCY,  A.OFFLINE_LIMIT,  "
            + "                A.CAS_ACCOUNT,  A.ACY_OPENING_BAL,  A.LCY_OPENING_BAL,  A.ACY_CURR_BALANCE,  A.LCY_CURR_BALANCE,  A.ACY_BLOCKED_AMOUNT,   "
            + "                A.ACY_AVL_BAL ,  "
            + "                A.DATE_LAST_CR_ACTIVITY,  A.DATE_LAST_DR_ACTIVITY,  A.DATE_LAST_DR,  A.DATE_LAST_CR,"
            + "                B.FULL_NAME,  B.UDF_1 AS IDCARD,  B.UDF_2 AS IDCARD_LOC,  "
            + "                B.UDF_3 AS IDCARD_DOB,  B.ADDRESS_LINE1 as ADDRESS,  B.CUSTOMER_TYPE  "
            + "                FROM VW_ACCT_THUPHISMS A  "
            + "                INNER JOIN (SELECT * FROM STTMS_CUSTOMER@FCATFCCLINK B  WHERE CUSTOMER_NO = ? ) B on A.CUST_NO = B.CUSTOMER_NO "
            + "                LEFT JOIN (select ACCOUNT_NUMBER,a.partyid from OBDX_ADMIN_PROD.Digx_Am_Account_Access@FCATSMSLINK a  ,\n"
            + "                OBDX_ADMIN_PROD.DIGX_AM_ACCOUNT_EXCEPTION@FCATSMSLINK b\n"
            + "                where a.account_access_id=b.account_access_id\n"
            + "                 and a.partyid=? and a.status = 'N') C  \n"
            + "                 ON A.CUST_NO= C.partyid AND A.CUST_AC_NO=C.ACCOUNT_NUMBER "
            + "                WHERE A.CUST_NO = ? "
            + "                AND (substr(account_class,1,3)<>'TCC' and substr(account_class,1,3)<>'TCI')"
            + "                ORDER BY A.ACY_AVL_BAL  DESC "
            + "                ) WHERE  ROWNUM=1 AND CUST_NO = ? ";
    //CHI LAY THAU CHI
    final String GET_CASACCC_MAXBAL_FEESMSKHDN_TC = ""
            + "SELECT * FROM "
            + "              ("
            + "                SELECT A.BRANCH_CODE,  A.CUST_AC_NO,  A.AC_DESC,  A.CUST_NO,  A.CCY,  A.ACCOUNT_CLASS,  A.AC_OPEN_DATE,  A.ALT_AC_NO,  A.RECORD_STAT,  "
            + "                A.AUTH_STAT,  A.MAKER_ID,  A.MAKER_DT_STAMP,  A.CHECKER_ID,  A.CHECKER_DT_STAMP,  A.ONCE_AUTH,  A.LIMIT_CCY,  A.OFFLINE_LIMIT,  "
            + "                A.CAS_ACCOUNT,  A.ACY_OPENING_BAL,  A.LCY_OPENING_BAL,  A.ACY_CURR_BALANCE,  A.LCY_CURR_BALANCE,  A.ACY_BLOCKED_AMOUNT,   "
            + "                A.ACY_AVL_BAL ,  "
            + "                A.DATE_LAST_CR_ACTIVITY,  A.DATE_LAST_DR_ACTIVITY,  A.DATE_LAST_DR,  A.DATE_LAST_CR,"
            + "                B.FULL_NAME,  B.UDF_1 AS IDCARD,  B.UDF_2 AS IDCARD_LOC,  "
            + "                B.UDF_3 AS IDCARD_DOB,  B.ADDRESS_LINE1 as ADDRESS,  B.CUSTOMER_TYPE  "
            + "                FROM VW_ACCT_THUPHISMS A  "
            + "                INNER JOIN (SELECT * FROM STTMS_CUSTOMER@FCATFCCLINK B  WHERE CUSTOMER_NO = ? ) B on A.CUST_NO = B.CUSTOMER_NO "
            + "                LEFT JOIN (select ACCOUNT_NUMBER,a.partyid from OBDX_ADMIN_PROD.Digx_Am_Account_Access@FCATSMSLINK a  ,\n"
            + "                OBDX_ADMIN_PROD.DIGX_AM_ACCOUNT_EXCEPTION@FCATSMSLINK b\n"
            + "                where a.account_access_id=b.account_access_id\n"
            + "                 and a.partyid=? and a.status = 'N') C  \n"
            + "                 ON A.CUST_NO= C.partyid AND A.CUST_AC_NO=C.ACCOUNT_NUMBER "
            + "                WHERE A.CUST_NO = ? "
            + "               AND (substr(account_class,1,3)='TCC' OR substr(account_class,1,3)='TCI')"
            + "                ORDER BY A.ACY_AVL_BAL  DESC "
            + "                ) WHERE  ROWNUM=1 AND CUST_NO = ? ";

    String GET_LOC_LIST_BY_CIF = "SELECT   PAN2 ID, LOC||'    '||SUBSTR(PANMASK,13,4)||'    '|| CRD_BRN||'    '|| E_NAME  AS CARDINFO "
            + "FROM table(FN_GET_SUMMARY_CARD_IB(?)) where substr(LOC,1,1)<>'2'";

    String GET_LOC_LIST_BY_LOC = "SELECT   PANECD ID, LOC||'    '||SUBSTR(PANMASK,13,4)||'    '|| CRD_BRN||'    '|| E_NAME  AS CARDINFO "
            + "FROM ccps.get_information_mc@CARDWORKIMSTBY where LOC = ? AND substr(LOC,1,1)<>'2'";
    //endduytxa 20/06/2017 for feeautosms
    //--------------------------------CHECK LAI
    final String GET_CARD_TYPE = "SELECT * FROM CARD_TYPE WHERE BRN =? and issueNew ='1'";
    final String UPDATE_REGISTERINFO = "UPDATE EB_ISSUECARD SET ISCHECKED = ?,CHECKEDDATE = SYSDATE,IDCHECKER =?, IDCHANNELCHECKER =?  WHERE ID IN ( ";

    final String UPDATE_REGISTERINFO_PROCESSING = "UPDATE EB_ISSUECARD SET ISCHECKED = ?,PROCESSINGDATE = SYSDATE,IDCHECKER =?, IDPROCESSINGUSER =?  WHERE ID IN ( ";
    final String SEARCH_REGISTERINFO = "SELECT B.* FROM (SELECT ROW_NUMBER() OVER(ORDER BY id) LINENUM, A.*"
            + " FROM VW_EB_ISSUECARD A where trunc(insdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(insdate)<=to_date(?,'dd/MM/yyyy')"
            + " AND idchannel LIKE ? AND ISCHECKED LIKE ? AND issuebranchcode LIKE ? AND REGISTERTYPE LIKE ? ORDER BY id) B WHERE LINENUM BETWEEN ?  AND ?";
    final String SEARCH_ALL_REGISTERINFO = "SELECT count(*) sl "
            + " FROM EB_ISSUECARD A where trunc(insdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(insdate)<=to_date(?,'dd/MM/yyyy')"
            + " AND idchannel LIKE ? AND ISCHECKED LIKE ? AND issuebranchcode LIKE ? AND REGISTERTYPE LIKE ? ";
    //-----------END CHECK LAI     

    //duytxa 07/09/2015 for feeautosms
    String GET_CARD_INFO = "select cif,so_cmnd,ngay_cap,cus_name,cus_add, card_desc loaithe, loc sotkthe,panmask sothe,e_name, expi_date, thanhtoantoithieu, dunocuoiky dunodenkysaoke, "
            + "phatsinhco thanhtoantrongky, dunothe dunodenhientai, brch cnmothe from ccps.get_information_mc@CARDWORKIMSTBY where panecd=?";

    /**
     *
     * @param custno
     * @param acctype
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountCASAMaxbalanceFeesms(String custno, String acctype) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(custno);
        p_args.add(custno);
        if ("TC".equals(acctype)) { //chi lay tk thau chi
            return JDBCEngine.executeQuery(GET_CASACCC_MAXBAL_FEESMS_TC, p_args, connection);
        } else {
            return JDBCEngine.executeQuery(GET_CASACCC_MAXBAL_FEESMS_NO_TC, p_args, connection);
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
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(custno);
        p_args.add(custno);
        p_args.add(custno);
        p_args.add(custno);
        if ("TC".equals(acctype)) { //chi lay tk thau chi
            return JDBCEngine.executeQuery(GET_CASACCC_MAXBAL_FEESMSKHDN_TC, p_args, connection);
        } else {
            return JDBCEngine.executeQuery(GET_CASACCC_MAXBAL_FEESMSKHDN_NO_TC, p_args, connection);
        }
    }
    //endduytxa 20/06/2017 for feeautosms
    //CHUA LAY DUOC DU LIEU
    //CHUA DUOC

    /**
     *
     * @param acccountTD
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountTD(String acccountTD) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(acccountTD);
        return JDBCEngine.executeQuery(GET_ACCOUNTTD, p_args, connection);
    }

    /**
     *
     * @param idcustomer
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCustomerInfoFCC(String idcustomer) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idcustomer);
        return JDBCEngine.executeQuery(GET_CUST_INFO, p_args, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getListBranch() throws Exception {
        return JDBCEngine.executeQuery(GET_LISTBRANCH, null, connection);
    }

    /**
     *
     * @param cif
     * @param idcard
     * @return
     * @throws Exception
     */
    public ArrayList<?> findCustomer(String cif, String idcard) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        String _s = "";// A.CUST_NO=? and A.CUST_AC_NO=? and D.UDF_1=?        
        if (idcard != null && idcard.trim().length() > 0) {
            _s += " and A.unique_id_value=?";
            p_args.add(idcard);
        }
        if (cif != null && cif.trim().length() > 0) {
            _s += " and A.customer_no=?";
            p_args.add(cif);
        }
        if (_s.length() == 0) {
            return null;
        }
        return JDBCEngine.executeQuery(FIND_CUSTOMER + _s, p_args, connection);
    }
    //CHUA DUOC

    /**
     *
     * @param cifno
     * @param mobileno
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchSMSAlert(String cifno, String mobileno) throws Exception {
        String _s = "";
        ArrayList p_args = new ArrayList();
        if (cifno != null && cifno.trim().length() > 0) {
            _s += " and MA_KHACH_HANG=?";
            p_args.add(cifno);
        }
        if (mobileno != null && mobileno.trim().length() > 0) {
            _s += " and SO_DIEN_THOAI=?";
            p_args.add(mobileno);
        }
        return JDBCEngine.executeQuery(SEARCH_SMS_ALERT + _s, p_args, connection);
    }

    /**
     *
     * @param issueatm
     * @return
     */
    public ProcedureCallDto insertEbIssuecard(EbIssuecard issueatm) {
        ProcedureCallDto procedto = new ProcedureCallDto();
        try {
            getSession().save(issueatm);
            procedto.setErrorStatus("0");
            procedto.setOther(issueatm.getId().toString());
            return procedto;
        } catch (Exception ex) {
            LOGGER.error(ex);
            procedto.setErrorStatus("-1");
            procedto.setErrorMessage(ex.toString());
            return procedto;
        }
    }

    /**
     *
     * @param type
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCardType(String type) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(type);
        return JDBCEngine.executeQuery(GET_CARD_TYPE, p_args, connection);
    }

    /**
     *
     * @param issueatm
     * @return
     * @throws Exception
     */
    public List<EbIssuecard> getEbIssuecard(EbIssuecard issueatm) throws Exception {
        try {
            Criteria crit = getSession().createCriteria(EbIssuecard.class);
            if (issueatm.getRegistertype() != null) {
                crit.add(Restrictions.like("registertype", "%" + issueatm.getRegistertype() + "%"));
            }
            if (issueatm.getCUSTOMERNO() != null) {

                crit.add(Restrictions.eq("CUSTOMERNO", issueatm.getCUSTOMERNO()));
            }
            if (issueatm.getId() != null) {
                crit.add(Restrictions.eq("id", issueatm.getId()));
            }
            if (issueatm.getIschecked() != null && issueatm.getIschecked().equals('X')) {
                crit.add(Restrictions.in("ischecked", new Character[]{'Y', 'N'}));
            }
            crit.addOrder(Order.desc("id"));
            crit.setMaxResults(20);
            return crit.list();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
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
        ArrayList<String> p_args = new ArrayList<String>();
        String UPDATE_FEEDBACK_id;
        int ri = 0;
        if (status.equals("Y") || status.equals("C")) {
            p_args.add(status);
            p_args.add(userid);
            p_args.add(idChanneluser);
            UPDATE_FEEDBACK_id = UPDATE_REGISTERINFO + idlist + ")";
            ri = JDBCEngine.executeUpdate(UPDATE_FEEDBACK_id, p_args, connection);
            connection.commit();
        } else if (status.equals("P")) {
            p_args.add(status);
            p_args.add(userid);
            p_args.add(idChanneluser);
            UPDATE_FEEDBACK_id = UPDATE_REGISTERINFO_PROCESSING + idlist + ")";
            ri = JDBCEngine.executeUpdate(UPDATE_FEEDBACK_id, p_args, connection);
            connection.commit();
        }
        return ri;
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
    public ArrayList<?> searchRegisterinfo(String idchannel, String status, String branchcode, String registerType, String fromdate, String todate, String beginRow, String endRow) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        p_args.add("%" + idchannel + "%");
        p_args.add("%" + status + "%");
        p_args.add("%" + branchcode + "%");
        p_args.add("%" + registerType + "%");
        p_args.add(beginRow);
        p_args.add(endRow);
        return JDBCEngine.executeQuery(SEARCH_REGISTERINFO, p_args, connection);
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
    public ArrayList<?> searchRegisterinfoAll(String idchannel, String status, String branchcode, String registerType, String fromdate, String todate) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        p_args.add("%" + idchannel + "%");
        p_args.add("%" + status + "%");
        p_args.add("%" + branchcode + "%");
        p_args.add("%" + registerType + "%");
        return JDBCEngine.executeQuery(SEARCH_ALL_REGISTERINFO, p_args, connection);
    }

    /**
     *
     * @param cif
     * @param loc
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListCard(String cif, String loc) throws Exception {
        ArrayList p_args = new ArrayList();
        if (cif.equals("")) {
            p_args.add(loc);
            return JDBCEngine.executeQuery(GET_LOC_LIST_BY_LOC, p_args, connection);
        } else {
            p_args.add(cif);
            return JDBCEngine.executeQuery(GET_LOC_LIST_BY_CIF, p_args, connection);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCardInfoByID(String id) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(id);
        return JDBCEngine.executeQuery(GET_CARD_INFO, p_args, connection);
    }

    /*Thanh toán phí bảo hiểm tại quầy (S) -- Thachdn*/
    /**
     *
     * @param ownerId
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListPolNum(String ownerId) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(ownerId);
        return JDBCEngine.executeQuery(GET_LIST_POLNUM, p_args, connection);
    }

    /*Thanh toán phí bảo hiểm tại quầy (S) -- Thachdn*/
    /**
     *
     * @param premtyp
     * @param polNum
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListDataPay(String premtyp, String polNum) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(premtyp);
        p_args.add(polNum);
        return JDBCEngine.executeQuery(GET_LIST_DATAPAY, p_args, connection);
    }

    /*Thanh toán phí bảo hiểm tại quầy (S) -- Thachdn*/
    /**
     *
     * @param polNum
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListPremtyp(String polNum) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(polNum);
        return JDBCEngine.executeQuery(GET_LIST_PREMTYP, p_args, connection);
    }

    /**
     *
     * @param accountno
     * @return
     */
    public VwCustAccount getCustAccountFcdbByAccountNo(String accountno) {
        Criteria cre = getSession().createCriteria(VwCustAccount.class);
        List listcustacc = cre.add(Restrictions.eq("custAcNo", accountno)).list();
        if (listcustacc.isEmpty()) {
            return null;
        } else {
            return (VwCustAccount) listcustacc.get(0);
        }
    }
}
