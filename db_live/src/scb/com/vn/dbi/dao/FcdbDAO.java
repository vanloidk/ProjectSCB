package scb.com.vn.dbi.dao;

import antlr.StringUtils;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import scb.com.vn.dbi.dto.VwCustAccount;
import java.sql.Types;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.controller.DBIImpl;
import scb.com.vn.dbi.controller.IDBI;
import scb.com.vn.dbi.dto.ProcedureCallDto;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author minhndb
 */
public class FcdbDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(FcdbDAO.class);

    final String GET_INSURANCE_TRN = "select FCC_FN_ADDL_TEXT(acaae.ac_entry_sr_no),sttc.trn_desc,acaae.trn_dt,acaae.value_dt,acaae.lcy_amount, acaae.*  from ACTB_DAILY_LOG@fcatfcclink acaae left join STTM_TRN_CODE@fcatfcclink sttc on acaae.trn_code=sttc.trn_code WHERE TRN_REF_NO in (  select  acaae.TRN_REF_NO  from acvw_all_ac_entries@fcatfcclink acaae   left join ACTB_DAILY_LOG@fcatfcclink sttc on acaae.trn_code=sttc.trn_code  WHERE acaae.AC_NO  in ('0010100103030001')   AND SUBSTR(acaae.TRN_REF_NO,4,4) IN ('BHMI','CHDP')  ) and acaae.AC_NO  in ('0010100103030001') order by acaae.AC_ENTRY_SR_NO desc";
    final String GET_LIST_DATAPAY = "SELECT * from BC_BILL@sms_scb t where prem_typ = ? and pol_num=?";
    final String GET_LIST_POLNUM = "SELECT DISTINCT POL_NUM from BC_BILL@sms_scb t where owner_id = ?";
    final String GET_LIST_PREMTYP = "SELECT DISTINCT prem_typ from BC_BILL@sms_scb t where pol_num = ?";
    // TRA CUU SO DU THEO TAI KHOAN
    final String GET_ACCOUNTCASA = "SELECT * FROM fcc_vw_mstaccount t WHERE t.IDACCOUNT=? AND T.CODACCTTYPE = 'C' AND T.PRDCODE NOT LIKE 'KKH%'";
    final String GET_DETAILS_ACCOUNTCASA = "SELECT * FROM (SELEC tx.*, ms.customershortname "
            + "FROM fcat_vw_txnaccountactivity tx , fcat_vw_accountdetails ms "
            + "WHERE tx.nbraccount = ms.nbraccount  and tx.branchcode = ms.nbrbranch and "
            + "tx.nbraccount = ? ORDER BY tx.datvaluedate desc, srno desc) WHERE ROWNUM <= ?";
    final String GET_ACCOUNTTD = "SELECT * FROM fcc_vw_msttddetails A,  FCC_VW_MSTPRODUCTCLASSES B, FCC_VW_MSTACCOUNT C WHERE A.idaccount = C.idaccount and  C.prdcode=B.account_class and  A.codbranch = C.codbranch and  A.idaccount=?";
    final String GET_FXRATES = "SELECT A.CODCURRFROM, A.DATASOF ,A.NUMBUYRATE AS MUATM,B.NUMBUYRATE AS MUACK,"
            + "B.NUMSELLRATE AS BANCK FROM ("
            + "SELECT * FROM fcc_vw_mstfxrate WHERE txtratetype='CASH' "
            + "and id_entity = 'B001' and codcurrfrom=?)A,"
            + "(SELECT * FROM fcc_vw_mstfxrate WHERE txtratetype='TRANSFER' "
            + "and id_entity = 'B001' and codcurrfrom=?)B";
    final String GET_CUSTOMER_BYMOBILE = "SELECT * FROM MSTCHANNELUSER t WHERE t.ID_ENTITY='B001' and t.IDCHANNEL=? and t.IDCHANNELUSER=?";
    final String CHANGEPW_CUSTOMER = "UPDATE MSTCHANNELUSER t SET t.PASSWORD=? WHERE t.ID_ENTITY=? and t.IDUSER=? and t.IDCHANNEL=? and t.IDCHANNELUSER=?";
    final String CHANGEPW_CUSTOMER1 = "UPDATE MSTCHANNELUSER t SET t.PASSWORD=?, t.flagforcechangepwd=?"
            + " WHERE t.ID_ENTITY=? and t.IDUSER=? and t.IDCHANNEL=? and t.IDCHANNELUSER=?";
    final String UPDATE_OPERATIVEACC_USER = "update MSTUSER t set t.operativeacctno=?, t.operativebrncode=? "
            + "where t.id_entity=? and t.typeuser=? and t.iduser=?";
    //LYDTY modifed    
    final String GET_INFO_CUSTOMER = "SELECT t.*, mu.typeuser, mu.DOB, mu.firstname, mu.lastname, mu.email, mu.activeflag, mu.operativeacctno, mu.operativebrncode ,mu.Phonenumber "
            + "FROM MSTCHANNELUSER t, MSTUSER mu WHERE t.iduser = mu.iduser and t.ID_ENTITY=? and t.IDCHANNEL=? and t.IDCHANNELUSER=?";

    //LYDTY modifed
    final String GET_INFO_CUSTOMER_BYIDCHANNELUSER = "SELECT t.*, mu.typeuser, mu.DOB, mu.firstname, mu.lastname, mu.email, mu.activeflag, mu.operativeacctno, mu.operativebrncode, mu.makerid "
            + "FROM MSTCHANNELUSER t, MSTUSER mu WHERE t.iduser = mu.iduser and t.ID_ENTITY=? and t.IDCHANNELUSER=?";
    final String GET_ROLETXN = "SELECT r.* FROM ROLETXN r WHERE r.idrole IN (select ur.idrole from userrole ur "
            + "where ur.iduser=? and ur.id_entity=? and ur.idchannel=? "
            + "union select idrole from mstrole where id_entity=? and idchannel=? and isdefault='Y' and usertype=? )  and "
            + "r.idtxn=? and r.flginit='Y'";
    final String GET_ALLROLETXN = "SELECT r.* FROM ROLETXN r WHERE r.idrole IN (select ur.idrole from userrole ur "
            + "where ur.iduser=? and ur.id_entity=? and ur.idchannel=? "
            + "union select idrole from mstrole where id_entity=? and idchannel=? and isdefault='Y' and usertype=? )  and "
            + "r.flginit='Y'";
    String GET_ACCOUNTTD_DETAIL = "SELECT A.idaccount, A.codaccttype, A.codbranch, A.acctdesc, A.depositamt, A.accountclass, rate, bookaccount, bookbranch, ccy, customerid, TO_CHAR(A.maturitydate,'DD/MM/YYYY') as maturitydate, TO_CHAR(depositdate,'DD/MM/YYYY') as depositdate, rollovertype, rolloveramt, acyaccruedcric, altacctno, proddesc, autorollover, closeonmaturity, numavlbalance,"
            + "B.*, C.*, D.UDF_1 as idcard, D.UDF_2 as idcard_loc, D.UDF_3 as idcard_DOB "
            + " FROM fcc_vw_msttddetails A,  FCC_VW_MSTPRODUCTCLASSES B, FCC_VW_MSTACCOUNT C, FCC_STTMS_CUSTOMER D"
            + " WHERE A.idaccount = C.idaccount and  C.prdcode=B.account_class and  A.codbranch = C.codbranch and"
            + "  C.idcustomer = D.CUSTOMER_NO";
    String GETLIST_ACCOUNTTD_BY_ACCOUNTTDS = "select A.* from FCC_VW_STTMS_CUST_ACCOUNT A where A.RECORD_STAT='O' and A.AUTH_STAT='A' and A.ACCOUNT_TYPE='Y'";
    final String GET_CUST_INFO = "select * from FCC_VW_CUST_INFO t where t.idcust=?";
    final String GET_USER_ADMIN = "SELECT C.Idchanneluser,A.* FROM MSTUSER A, USERROLE B, mstchanneluser C WHERE A.IDUSER=B.IDUSER AND to_number(A.iduser) between 45111 AND 45203 and A.Iduser = c.iduser and A.TYPEUSER='INA'";
    final String GET_LASTEST_USER_BYPHONENUMBER = "select t.*, cu.idchanneluser, cu.idchannel, cu.password from mstuser t, mstchanneluser cu "
            + "where t.phonenumber=? and t.authreqd='Y' and "
            + "t.userseqnbr= (select max(userseqnbr) from mstuser) and t.iduser = cu.iduser and "
            + "t.id_entity = cu.id_entity order by t.datcreated desc, t.iduser desc"; // and t.activeflag='Y'
    final String ISHAVEROLEFT_BY_IDUSER = "select * from (select distinct t.* from MSTROLE t, ROLETXN rt where t.id_entity=? and t.idchannel=? and rt.idtxn=? and rt.flginit=? and t.idrole = rt.idrole) x, USERROLE ur where ur.iduser=? and ur.idrole = x.idrole";
    final String GET_CUSTACC_BY_ACCOUNTCASA = "select A.*, B.IDUSER from FCC_VW_STTMS_CUST_ACCOUNT A, USERCUSTREL B where A.CUST_AC_NO=? and A.CUST_NO = B.IDCUST";
    final String GET_TERMDEPOSITRATE = "select * from sms_rate where kyhan=?";
    final String GET_LISTBRANCH = "select * from vw_branch_scb";
    final String GET_ACCOUNT_BRANCH = "SELECT t.*, b.* FROM fcc_vw_mstaccount t, FCAT_VW_MSTBRANCH b WHERE t.codbranch = b.BRANCH_CODE and t.IDACCOUNT=?";
    final String ISEXISTS_BY_IDUSER_ACCNOCASA = "select B.IDUSER from FCC_VW_STTMS_CUST_ACCOUNT A, USERCUSTREL B "
            + "where A.CUST_NO = B.IDCUST and (substr(A.account_class,0,2) in ('TC','CA', 'VO') or substr(A.account_class,0,2)='KK') and A.ACCOUNT_TYPE='U' and B.iduser=? and A.cust_ac_no=?";
    final String ISEXISTS_BY_IDUSER_ACCNOTD = "select B.IDUSER from FCC_VW_STTMS_CUST_ACCOUNT A, USERCUSTREL B "
            + "where A.CUST_NO = B.IDCUST and A.ACCOUNT_TYPE='Y' and B.iduser=? and A.cust_ac_no=?";
    //LOAN                    
    final String ISEXISTS_BY_CIF_ACCLOAN = "select t.account_number from CLTB_ACCOUNT_MASTER@FCATFCCLINK t, USERCUSTREL t1 where t1.IDCUST=t.customer_id and t1.iduser=? and t.account_number=?";
    final String GETINFOACCOUNTLOAN = "select * from vw_schedule_loan where account_number=?";
    //CONTACT CENTER
    final String CC_IB_CUST_INFO = "{ ? = call OBDX_ADMIN_PROD.DVKH_PKG.IB_CUST_INFO(?)}";
    final String CC_SMS_CUST_INFO = "{ ? = call OBDX_ADMIN_PROD.DVKH_PKG.SMS_CUST_INFO(?)}";
    final String CC_SMSALERT_CUST_INFO = "{ ? = call SMS_SCB.PKG_DVKH_GW.SMSALERT_CUST_INFO(?)}";
    final String CC_SMS_ALERT_TD_INFO = "{ ? = call SMS_SCB.PKG_DVKH_GW.SMS_ALERT_TD_INFO(?)}";
    final String CC_CUST_MBANKING_INFO = "{ ? = call SMS_SCB.PKG_DVKH_GW.CUST_MBANKING_INFO(?)}";
    final String CC_GET_CUST_SMS_HIST = "{ ? = call OBDX_ADMIN_PROD.DVKH_PKG.CUST_SMS_SENDER(?,?,?,?)}";
    //MOBLIE BANKING
    final String GET_TRANSACTION_LIST_ACCOUNT_SR = "SELECT * FROM"
            + " (SELECT * FROM OBDX_ADMIN_PROD.FCC_VW_TXNACCOUNTACTIVITY_SCB WHERE branchcode = ? and nbraccount = ? AND TXNDATE >= to_date(?,'yyyyMMdd') AND TXNDATE <= to_date(?,'yyyyMMdd')"
            + " AND srno < ?"
            + " ORDER BY srno DESC"
            + " ) WHERE ROWNUM <=10";
    final String GET_TRANSACTION_LIST_ACCOUNT = "SELECT * FROM"
            + " (SELECT * FROM OBDX_ADMIN_PROD.FCC_VW_TXNACCOUNTACTIVITY_SCB WHERE branchcode = ? and nbraccount = ? AND TXNDATE >= to_date(?,'yyyyMMdd') AND TXNDATE <= to_date(?,'yyyyMMdd')"
            + " ORDER BY srno DESC"
            + " ) WHERE ROWNUM <=10";
    final String GET_TRANSACTION_LIST_TD_ACCOUNT = "SELECT  * FROM (  "
            + "SELECT  * FROM OBDX_ADMIN_PROD.FCC_VW_TXNACCOUNTACTIVITY_TD A WHERE  nbraccount = ? AND txnamount <>0 "
            + " order by  srno desc"
            + ") where rownum <= 10";
    final String GET_FCC_VW_MSTCORPORATE_CIF = "SELECT * FROM FCC_VW_MSTCORPORATE WHERE idcorporate =?";
    final String GET_FCC_VW_MSTCORPORATE_ID = "SELECT * FROM FCC_VW_MSTCORPORATE WHERE POSTAL_CODE =?";
    final String GET_VW_ACCOUNT_SUMMARY = "SELECT * FROM VW_ACCOUNT_SUMMARY where custno =?";
    final String GET_TD_BEFORE_REDEMP = "BEGIN GetAccountTDBeforeRedemp(?,?); END;";
    final String GET_FCC_VW_MSTTDDETAILS = "SELECT * FROM FCC_VW_MSTTDDETAILS WHERE IDACCOUNT =?";
    final String GET_MSTBENEBANKCODE = "SELECT * FROM MSTBENEBANKCODE WHERE code =?";
    final String GET_BANK_CITY = "SELECT * FROM BANK_CITY WHERE COD_CITY =?";
    final String GET_VW_CLACCOUNTSCB = "SELECT * FROM VW_CLACCOUNTSCB WHERE taikhoan =?";
    final String GET_VW_CASA_HASLIMIT = "SELECT * FROM VW_CASA_HASLIMIT WHERE idaccount =?";
    final String GET_VW_MASTERCARD_INFO = "SELECT * FROM VW_MASTERCARD_INFO WHERE cardnoOLD =?";
    final String GET_VW_TRANSACTION_MASTERCARD = "select * from VW_TRANSACTION_MASTERCARD where rownum <=10 and cardno=(select ccps.ecd2@cwdwdb(?,'') from dual)";
    final String GET_VW_CLSCHEDULEINTPAIDSCB = "SELECT * FROM VW_CLSCHEDULEINTPAIDSCB WHERE TAIKHOAN = ? and rownum <=10";
    final String GET_VW_CLSCHEDULEINTUNPAIDSCB = "SELECT * FROM VW_CLSCHEDULEINTUNPAIDSCB WHERE TAIKHOAN = ? and rownum <=10";
    final String GET_VW_CLSCHEDULEPRIPAIDSCB = "SELECT * FROM VW_CLSCHEDULEPRIPAIDSCB WHERE TAIKHOAN = ? and rownum <=10";
    final String GET_VW_CLSCHEDULEPRIUNPAIDSCB = "SELECT * FROM VW_CLSCHEDULEPRIUNPAIDSCB WHERE TAIKHOAN = ? and rownum <=10";
    final String GET_ACCOUNTCLASS_INFO = "SELECT TO_CHAR(TODAY,'YYYYMMDD')   OpenDate,"
            + "TO_CHAR (GETMATURITYDATE (ACCOUNT_CLASS, TODAY,'',BRANCH_CODE),'YYYYMMDD' ) Matdate,"
            + "FCAT_FN_GET_TDRATE_FCDB_MB (ACCOUNT_CLASS,'VND',TODAY) Interest"
            + " from  FCC_VW_STTMS_ACCOUNT_CLASS A ,fcc_sttms_dates B"
            + " WHERE BRANCH_CODE = ? AND ACCOUNT_CLASS =?";
    final String GET_TOKEN_BY_CUSTNO = "SELECT FN_GETUSERUDFVALUE(IDUSER,'fldtokenmap') serialno  FROM USERCUSTREL "
            + "WHERE   FN_GETUSERUDFVALUE(IDUSER,'fldtokenmap')   IS NOT NULL AND IDCUST = ? ";
    final String CHECK_AMOUNT_BEFORE_PAY = "SELECT A.CUST_AC_NO, A.ACY_CURR_BALANCE, A.ACY_AVL_BAL -  B.MIN_BALANCE AS AVL_BALANCE, B.MIN_BALANCE, NVL (d.limit_amount, 0) LIMIT_AMOUNT"
            + " FROM fcc_sttms_cust_account A JOIN  FCC_VW_ACCT_CLASS_CCY_MIN_BAL B ON A.ACCOUNT_CLASS = B.ACCOUNT_CLASS AND A.CCY = B.CCY_CODE"
            + " LEFT  JOIN FCC_STTMS_CUST_AC_LINKAGES c       ON a.cust_ac_no = c.cust_ac_no"
            + " LEFT JOIN fcc_getms_facility d   ON SUBSTR (c.linked_ref_no, 1, LENGTH (c.linked_ref_no) - 1) =      d.line_code "
            + " WHERE  A.CUST_AC_NO = ?";
    final String GET_FCC_VW_INTEREST_RATE = "SELECT * FROM FCC_VW_INTEREST_RATE WHERE CCY_CODE =?";
    final String CHECK_TOKEN_BY_SERIALNO = "select s.token, s.idchanneluser, s.iduser,s.id_entity,s.idchannel,r.idcust      from (  "
            + " select extractvalue(XMLTYPE(a.userdata, ''),'/UDFListDTO/udfList/UDFDTO[udfName = \"fldtokenmap\"]/udfValue') as token,  "
            + " b.idchanneluser,a.iduser,a.id_entity ,b.idchannel from mstuser a, mstchanneluser b"
            + " where a.userdata is not null and a.iduser = b.iduser and a.id_entity = b.id_entity and b.idchannel = '01' and b.id_entity = 'B001'  ) s,USERCUSTREL r"
            + " where s.iduser = r.iduser  and s.token = ?";
    final String VW_MSTPRODUCTCLASSES = "SELECT * FROM fcc_vw_mstproductclasses  WHERE account_class = ?";
    final String GET_STTM_CUST_ACCOOUNT = "SELECT BRANCH_CODE,   CUST_AC_NO,   AC_DESC,   CUST_NO,   CCY,   ACCOUNT_CLASS,ACY_AVL_BAL,  ACY_AVL_BAL + ACY_BLOCKED_AMOUNT AS ACY_CURR_BALANCE FROM FCC_STTMS_CUST_ACCOUNT "
            + " WHERE CUST_AC_NO =?";
    final String GET_TEMPLATE_FROM_FCDB = "BEGIN Get_Template_From_FCDB(?,?); END;";
    final String VW_BENEFICIARY_FROM_FCDB = "SELECT * FROM VW_BENEFICIARY_FROM_FCDB WHERE CIFNO= ?";
    final String GET_BENEFICIARY_FROM_FCDB = "BEGIN Get_Beneficiary_From_FCDB(?,?); END;";
    final String GET_BALANCE_AFTER_TRAN = "BEGIN GET_BALANCE_AFTER_TRAN(?,?,?); END;";
    final String FIND_CUSTOMER = "SELECT DISTINCT A.* FROM FCC_STTMS_CUSTOMER A \n"
            + " LEFT JOIN FCC_STTMS_CUST_ACCOUNT B ON A.CUSTOMER_NO=B.CUST_NO\n"
            + " where B.RECORD_STAT = 'O' AND B.CCY = 'VND' AND B.ACCOUNT_TYPE = 'U' AND SUBSTR(B.ACCOUNT_CLASS,1,2) IN ('CA','TC') AND 1=1 ";
    final String GET_NAMESTAFF = "SELECT TEN_NV FROM FCC_SCB_CBNV where ma_nv =?";
    final String INSERT_FEEDBACK = "BEGIN INS_FEEDBACK(?,?,?,?,?); END;";
    final String UPDATE_FEEDBACK = "UPDATE FEEDBACK SET ISCHECKED = ?,CHECKEDDATE = SYSDATE,IDCHECKER =?, IDCHANNELCHECKER =?  WHERE IDFEEDBACK IN ( ";
    final String UPDATE_FEEDBACK_PROCESSING = "UPDATE FEEDBACK SET ISCHECKED = ?,PROCESSINGDATE = SYSDATE,IDCHECKER =?, IDPROCESSINGUSER =?  WHERE IDFEEDBACK IN ( ";
    final String SEARCH_FEEDBACK = "SELECT B.*, DECODE(IDCHANNEL,'03','Mobile banking','01', 'Internet banking') channelname "
            + ", DECODE(ISCHECKED,'N','Chờ xử lí','Y', 'Đã xử lí','P', 'Đang xử lí') statusname FROM (SELECT ROW_NUMBER() OVER(ORDER BY idfeedback) LINENUM, A.*"
            + " FROM FEEDBACK A where trunc(insdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(insdate)<=to_date(?,'dd/MM/yyyy')"
            + " AND idchannel LIKE ? AND ISCHECKED LIKE ?  ORDER BY idfeedback) B WHERE LINENUM BETWEEN ?  AND ?";
    final String SEARCH_ALL_FEEDBACK = "SELECT count(*) sl "
            + " FROM FEEDBACK A where trunc(insdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(insdate)<=to_date(?,'dd/MM/yyyy')"
            + " AND idchannel LIKE ? AND ISCHECKED LIKE ?";
    String SEARCH_SMS_ALERT = "SELECT * FROM OBDX_ADMIN_PROD.VW_REGISTER_SMS WHERE 1=1 ";

    //duytxa 07/09/2015 for feeautosms
    //duytxa 05/08/2016 edit bo litmit 
    //KHONG LAY TK THAU CHI
    final String GET_CASACCC_MAXBAL_FEESMS_NO_TC = "SELECT * FROM ( "
            + " SELECT A.BRANCH_CODE,  A.CUST_AC_NO,  A.AC_DESC,  A.CUST_NO,  A.CCY,  A.ACCOUNT_CLASS,  A.AC_OPEN_DATE,  A.ALT_AC_NO,  A.RECORD_STAT,  "
            + " A.AUTH_STAT,  A.MAKER_ID,  A.MAKER_DT_STAMP,  A.CHECKER_ID,  A.CHECKER_DT_STAMP,  A.ONCE_AUTH,  A.LIMIT_CCY,  A.OFFLINE_LIMIT,  "
            + " A.CAS_ACCOUNT,  A.ACY_OPENING_BAL,  A.LCY_OPENING_BAL,  A.ACY_CURR_BALANCE,  A.LCY_CURR_BALANCE,  A.ACY_BLOCKED_AMOUNT,   "
            + " A.ACY_AVL_BAL ,  "
            + " A.DATE_LAST_CR_ACTIVITY,  A.DATE_LAST_DR_ACTIVITY,  A.DATE_LAST_DR,  A.DATE_LAST_CR, B.FULL_NAME,  B.UDF_1 AS IDCARD,  B.UDF_2 AS IDCARD_LOC,  "
            + " TO_CHAR(TO_DATE(B.UDF_3, 'yyyy-mm-dd'),'dd/mm/yyyy') AS IDCARD_DOB,  B.ADDRESS_LINE1 as ADDRESS,  B.CUSTOMER_TYPE  "
            + " FROM VW_ACCT_THUPHISMS A left join STTM_CUSTOMER@FCATFCCLINK B  on A.CUST_NO = B.CUSTOMER_NO "
            + " LEFT JOIN FCC_STTMS_CUST_AC_LINKAGES e ON a.CUST_AC_NO = e.cust_ac_no "
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
            + " TO_CHAR(TO_DATE(B.UDF_3, 'yyyy-mm-dd'),'dd/mm/yyyy') AS IDCARD_DOB,  B.ADDRESS_LINE1 as ADDRESS,  B.CUSTOMER_TYPE  "
            + " FROM VW_ACCT_THUPHISMS A left join STTM_CUSTOMER@FCATFCCLINK B  on A.CUST_NO = B.CUSTOMER_NO "
            + " LEFT JOIN FCC_STTMS_CUST_AC_LINKAGES e ON a.CUST_AC_NO = e.cust_ac_no "
            + " WHERE A.CUST_NO = ? "
            + " AND (substr(account_class,1,3)='TCC' OR substr(account_class,1,3)='TCI')"
            + "ORDER BY A.ACY_AVL_BAL  DESC "
            + " ) WHERE  ROWNUM=1 AND CUST_NO= ? ";
    //endduytxa 07/09/2015 for feeautosms

    //duytxa 20/06/2017 for feeautosms  
    //KHONG LAY TK THAU CHI
    final String GET_CASACCC_MAXBAL_FEESMSKHDN_NO_TC = ""
            + "SELECT * FROM "
            + "              ("
            + "                SELECT A.BRANCH_CODE,  A.CUST_AC_NO,  A.AC_DESC,  A.CUST_NO,  A.CCY,  A.ACCOUNT_CLASS,  A.AC_OPEN_DATE,  A.ALT_AC_NO,  A.RECORD_STAT,  "
            + "                A.AUTH_STAT,  A.MAKER_ID,  A.MAKER_DT_STAMP,  A.CHECKER_ID,  A.CHECKER_DT_STAMP,  A.ONCE_AUTH,  A.LIMIT_CCY,  A.OFFLINE_LIMIT,  "
            + "                A.CAS_ACCOUNT,  A.ACY_OPENING_BAL,  A.LCY_OPENING_BAL,  A.ACY_CURR_BALANCE,  A.LCY_CURR_BALANCE,  A.ACY_BLOCKED_AMOUNT,   "
            + "                A.ACY_AVL_BAL ,  "
            + "                A.DATE_LAST_CR_ACTIVITY,  A.DATE_LAST_DR_ACTIVITY,  A.DATE_LAST_DR,  A.DATE_LAST_CR,"
            + "                B.FULL_NAME,  B.UDF_1 AS IDCARD,  B.UDF_2 AS IDCARD_LOC,  "
            + "                TO_CHAR(TO_DATE(B.UDF_3, 'yyyy-mm-dd'),'dd/mm/yyyy') AS IDCARD_DOB,  B.ADDRESS_LINE1 as ADDRESS,  B.CUSTOMER_TYPE  "
            + "                FROM VW_ACCT_THUPHISMS A  "
            + "                INNER JOIN (SELECT * FROM STTM_CUSTOMER@FCATFCCLINK B  WHERE CUSTOMER_NO = ? ) B on A.CUST_NO = B.CUSTOMER_NO "
            + "                INNER JOIN (SELECT DISTINCT IDCUST,IDACCOUNT FROM usercustaccttxnxref WHERE  IDCUST = ? ) C "
            + "                 ON A.CUST_NO=C.IDCUST AND A.CUST_AC_NO=C.IDACCOUNT "
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
            + "                TO_CHAR(TO_DATE(B.UDF_3, 'yyyy-mm-dd'),'dd/mm/yyyy') AS IDCARD_DOB,  B.ADDRESS_LINE1 as ADDRESS,  B.CUSTOMER_TYPE  "
            + "                FROM VW_ACCT_THUPHISMS A  "
            + "                INNER JOIN (SELECT * FROM STTM_CUSTOMER@FCATFCCLINK B  WHERE CUSTOMER_NO = ? ) B on A.CUST_NO = B.CUSTOMER_NO "
            + "                INNER JOIN (SELECT DISTINCT IDCUST,IDACCOUNT FROM usercustaccttxnxref WHERE  IDCUST = ? ) C "
            + "                 ON A.CUST_NO=C.IDCUST AND A.CUST_AC_NO=C.IDACCOUNT "
            + "                WHERE A.CUST_NO = ? "
            + "               AND (substr(account_class,1,3)='TCC' OR substr(account_class,1,3)='TCI')"
            + "                ORDER BY A.ACY_AVL_BAL  DESC "
            + "                ) WHERE  ROWNUM=1 AND CUST_NO = ? ";
    //endduytxa 20/06/2017 for feeautosms

    final String PKS_CHECKISSUEATMCARD = "BEGIN PKG_MOBILEBANKING.CHECKISSUEATMCARD (?,?,?,?,?,?,?,?,?); END;";
    final String GET_CARD_TYPE = "SELECT * FROM CARD_TYPE WHERE BRN =? and issueNew ='1'";
    final String GET_CARD_LIST_NEW = "SELECT * FROM table(FN_GET_CARD_INFO(?))";
    final String GET_PRIMARYATM_LIST = "SELECT * FROM VW_PRIMARYATM_CARDS WHERE CIFNO =?";
    final String GET_MC_INFO_STMT = "SELECT * FROM GET_MC_INFO_STMT WHERE ACCOUNTNO =? AND PERIOD =? ";
    final String VW_STATEMENT_MASTERCARD = "SELECT * FROM VW_STATEMENT_MASTERCARD where ACCOUNTNO =? AND period=? AND SRNO>? AND ROWNUM <=?";
    final String PKS_GET_POINT_MC = "BEGIN PKG_MOBILEBANKING.GET_POINT_MC(?,?,?,?,?); END;";
    final String GET_VW_MASTERCARD_INFO_NEW = "SELECT * FROM VW_MASTERCARD_INFO WHERE PANECD =?";
    final String GET_VW_TRANSACTION_MASTERCARD_NEW = "select * from VW_TRANSACTION_MASTERCARD where rownum <=20 and cardno=?";
    final String GET_VW_ACCOUNT_SUMMARY_NEW = "SELECT * FROM VW_ACCOUNT_SUMMARY_MB where custno =? union ALL  "
            + " SELECT * FROM TABLE (FN_GET_SUMMARY_CARD(?)) ";
    final String GET_CARD_LIST_BY_ACCOUTNO = "select * from table(FN_GET_CARD_INFO(?))";
    final String PKS_REGISTER_ALERT_TD = "BEGIN PKG_MOBILEBANKING.REGISTER_ALERT_TD (?,?,?,?,?); END;";
    final String UPDATE_REGISTERINFO = "UPDATE EB_ISSUECARD SET ISCHECKED = ?,CHECKEDDATE = SYSDATE,IDCHECKER =?, IDCHANNELCHECKER =?  WHERE ID IN ( ";
    final String UPDATE_REGISTERINFO_PROCESSING = "UPDATE EB_ISSUECARD SET ISCHECKED = ?,PROCESSINGDATE = SYSDATE,IDCHECKER =?, IDPROCESSINGUSER =?  WHERE ID IN ( ";
    final String SEARCH_REGISTERINFO = "SELECT B.* FROM (SELECT ROW_NUMBER() OVER(ORDER BY id) LINENUM, A.*"
            + " FROM VW_EB_ISSUECARD A where trunc(insdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(insdate)<=to_date(?,'dd/MM/yyyy')"
            + " AND idchannel LIKE ? AND ISCHECKED LIKE ? AND issuebranchcode LIKE ? AND REGISTERTYPE LIKE ? ORDER BY id) B WHERE LINENUM BETWEEN ?  AND ?";
    final String SEARCH_ALL_REGISTERINFO = "SELECT count(*) sl "
            + " FROM EB_ISSUECARD A where trunc(insdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(insdate)<=to_date(?,'dd/MM/yyyy')"
            + " AND idchannel LIKE ? AND ISCHECKED LIKE ? AND issuebranchcode LIKE ? AND REGISTERTYPE LIKE ? ";
    final String PKS_CHECK_BALANCE_BEFORE_FEE = "BEGIN PKG_MOBILEBANKING.CHECK_BALANCE_BEFORE_FEE(?,?,?,?,?,?); END;";
    final String GET_BENE_NAME = "SELECT * FROM VW_BENEFICIARY_FOR_TRANSFER WHERE IDACCOUNT=?";
    String GET_CREDIT_CARD_LIST = "SELECT * FROM VW_MASTERCARD_INFO WHERE SUBSTR(CARDACCOUNTNO,1,1)  = 8 AND ";
    String GET_LOC_LIST_BY_CIF = "SELECT   PAN2 ID, LOC||'    '||SUBSTR(PANMASK,13,4)||'    '|| CRD_BRN||'    '|| E_NAME  AS CARDINFO "
            + "FROM table(FN_GET_SUMMARY_CARD_IB(?)) where substr(LOC,1,1)<>'2'";
    String GET_LOC_LIST_BY_LOC = "SELECT   PANECD ID, LOC||'    '||SUBSTR(PANMASK,13,4)||'    '|| CRD_BRN||'    '|| E_NAME  AS CARDINFO "
            + "FROM ccps.get_information_mc@cardwork where LOC = ? AND substr(LOC,1,1)<>'2'";
    String GET_CARD_INFO = "select cif, card_desc loaithe, loc sotkthe,panmask sothe,e_name, expi_date, thanhtoantoithieu, dunocuoiky dunodenkysaoke, "
            + "phatsinhco thanhtoantrongky, dunothe dunodenhientai, brch cnmothe from ccps.get_information_mc@CARDWORK where panecd=?";
    String ACCOUNT_CLASS_REDEMPTION = "SELECT ACCOUNTCLASS FROM ACCOUNTCLASS_REDEMPTION WHERE ISREDEM = 'Y' OR ISREDEM = '1'";
    String FN_CHK_TD_MATURITY_DATE = "BEGIN FN_CHK_TD_MATURITY_DATE(?,?,?,?,?); END;";
    final String GET_GOLDRATE = "SELECT * FROM VW_GOLDRATE";
    final String GET_EXCHANGERATE = "SELECT * FROM VW_EXCHANGERATE";
    String PROC_CHECKTD_ISEOD = "BEGIN CHECKTD_ISEOD(?,?,?); END;";
    final String SELECT_JOINT_ACCOUNT = "SELECT * FROM OBDX_ADMIN_PROD.SCB_JOINTACCOUNT";

    /**
     *
     * @param acccount
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountCASA(String acccount) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(acccount);
        return JDBCEngine.executeQuery(GET_ACCOUNTCASA, p_args, connection);
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

    /**
     *
     * @param acccount
     * @param rownum
     * @return
     * @throws Exception
     */
    public ArrayList<?> getDetailsAccountCASA(String acccount, int rownum) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(acccount);
        p_args.add(rownum);
        return JDBCEngine.executeQuery(GET_DETAILS_ACCOUNTCASA, p_args, connection);
    }

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
     * @param accounttd
     * @param cif
     * @param idcard
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListAccountTDDetail(String accounttd, String cif, String idcard) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        String _s = "";
        if (accounttd != null && accounttd.trim().length() > 0) {
            _s += " and A.idaccount=?";
            p_args.add(accounttd);
        }
        if (idcard != null && idcard.trim().length() > 0) {
            _s += " and D.UDF_1=?";
            p_args.add(idcard);
        }
        if (cif != null && cif.trim().length() > 0) {
            _s += " and A.customerid=?";
            p_args.add(cif);
        }
        if (_s.length() == 0) {
            return null;
        }
        return JDBCEngine.executeQuery(GET_ACCOUNTTD_DETAIL + _s, p_args, connection);
    }

    /**
     *
     * @param ccyfrom
     * @return
     * @throws Exception
     */
    public ArrayList<?> getFXRates(String ccyfrom) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(ccyfrom.toUpperCase());
        p_args.add(ccyfrom.toUpperCase());
        return JDBCEngine.executeQuery(GET_FXRATES, p_args, connection);
    }

    /**
     *
     * @param mobile
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCustomerSMSByMobile(String mobile) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add("41");
        p_args.add(mobile);
        return JDBCEngine.executeQuery(GET_CUSTOMER_BYMOBILE, p_args, connection);
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
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idEntity);
        p_args.add(idChannel);
        p_args.add(idChanneluser);
        return JDBCEngine.executeQuery(GET_INFO_CUSTOMER, p_args, connection);
    }

    /**
     *
     * @param idEntity
     * @param idChanneluser
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCustomerInfoByIdChannelUser(String idEntity, String idChanneluser) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idEntity);
        p_args.add(idChanneluser);
        return JDBCEngine.executeQuery(GET_INFO_CUSTOMER_BYIDCHANNELUSER, p_args, connection);
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
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(newpassword_encrypted);
        p_args.add(idEntity);
        p_args.add(userid);
        p_args.add(idChannel);
        p_args.add(idChanneluser);
        return JDBCEngine.executeUpdate(CHANGEPW_CUSTOMER, p_args, connection);
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
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(newpassword_encrypted);
        p_args.add(flagforcechangepwd);
        p_args.add(idEntity);
        p_args.add(userid);
        p_args.add(idChannel);
        p_args.add(idChanneluser);
        return JDBCEngine.executeUpdate(CHANGEPW_CUSTOMER1, p_args, connection);
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
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(iduser);
        p_args.add(identity);
        p_args.add(idchannel);
        p_args.add(identity);
        p_args.add(idchannel);
        p_args.add(usertype);
        p_args.add(idtxn);
        return JDBCEngine.executeQuery(GET_ROLETXN, p_args, connection);
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
    public ArrayList<?> getAllRoleTxn(String identity, String idchannel, String iduser, String usertype) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(iduser);
        p_args.add(identity);
        p_args.add(idchannel);
        p_args.add(identity);
        p_args.add(idchannel);
        p_args.add(usertype);
        return JDBCEngine.executeQuery(GET_ALLROLETXN, p_args, connection);
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
    public ArrayList<?> getUserAdmin() throws Exception {
        return JDBCEngine.executeQuery(GET_USER_ADMIN, null, connection);
    }

    /**
     *
     * @param phonenum
     * @return
     * @throws Exception
     */
    public ArrayList<?> getLastestUserByPhoneNum(String phonenum) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(phonenum);
        return JDBCEngine.executeQuery(GET_LASTEST_USER_BYPHONENUMBER, p_args, connection);
    }

    /**
     *
     * @param idchannel
     * @param iduser
     * @return
     * @throws Exception
     */
    public ArrayList<?> isHaveRoleFTByIduser(String idchannel, String iduser) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add("B001");
        p_args.add(idchannel);
        p_args.add("ITG");
        p_args.add("Y");
        p_args.add(iduser);
        return JDBCEngine.executeQuery(ISHAVEROLEFT_BY_IDUSER, p_args, connection);
    }

    /**
     *
     * @param account
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCustInfoIBByAccount(String account) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(account);
        return JDBCEngine.executeQuery(GET_CUSTACC_BY_ACCOUNTCASA, p_args, connection);
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
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(operativeacctno);
        p_args.add(operativebrncode);
        p_args.add(id_entity);
        p_args.add(usertype);
        p_args.add(iduser);
        return JDBCEngine.executeUpdate(UPDATE_OPERATIVEACC_USER, p_args, connection);
    }

    /**
     *
     * @param kyhan
     * @return
     * @throws Exception
     */
    public ArrayList<?> getTermDepositRate(String kyhan) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(kyhan);
        return JDBCEngine.executeQuery(GET_TERMDEPOSITRATE, p_args, connection);
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

    /**
     *
     * @param account
     * @return
     * @throws Exception
     */
    public ArrayList getBeneficaryName(String account) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(account);
        return JDBCEngine.executeQuery(GET_ACCOUNT_BRANCH, p_args, connection);
    }

    /**
     *
     * @param account
     * @return
     * @throws Exception
     */
    public ArrayList getAccountBranch(String account) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(account);
        return JDBCEngine.executeQuery(GET_ACCOUNT_BRANCH, p_args, connection);
    }

    /**
     *
     * @param iduser
     * @param custaccno
     * @return
     * @throws Exception
     */
    public boolean isExistsAccCasaByIduserAccno(String iduser, String custaccno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(iduser);
        p_args.add(custaccno);
        ArrayList list = JDBCEngine.executeQuery(ISEXISTS_BY_IDUSER_ACCNOCASA, p_args, connection);
        return !list.isEmpty();
    }

    /**
     *
     * @param iduser
     * @param custaccno
     * @return
     * @throws Exception
     */
    public boolean isExistsAccTDByIduserAccno(String iduser, String custaccno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(iduser);
        p_args.add(custaccno);
        ArrayList list = JDBCEngine.executeQuery(ISEXISTS_BY_IDUSER_ACCNOTD, p_args, connection);
        return !list.isEmpty();
    }

    /**
     *
     * @param iduser
     * @param accountloan
     * @return
     * @throws Exception
     */
    public boolean isExistsAccLoanByCifLoan(String iduser, String accountloan) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(iduser);
        p_args.add(accountloan);
        ArrayList list = JDBCEngine.executeQuery(ISEXISTS_BY_CIF_ACCLOAN, p_args, connection);
        return !list.isEmpty();
    }

    /**
     *
     * @param accountloan
     * @return
     * @throws Exception
     */
    public List getInfoAccountLoan(String accountloan) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(accountloan);
        ArrayList list = JDBCEngine.executeQuery(GETINFOACCOUNTLOAN, p_args, connection);
        return list;
    }

    //CONTACT CENTER
    /**
     *
     * @param CIF
     * @return
     * @throws SQLException
     */
    public String CC_IB_CUST_INFO(String CIF) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(CC_IB_CUST_INFO);
        try {
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, CIF);
            calStmt.executeQuery();
            return calStmt.getString(1);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param CIF
     * @return
     * @throws SQLException
     */
    public String CC_SMS_CUST_INFO(String CIF) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(CC_SMS_CUST_INFO);
        try {
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, CIF);
            calStmt.executeQuery();
            return calStmt.getString(1);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param CIF
     * @return
     * @throws SQLException
     */
    public String CC_SMSALERT_CUST_INFO(String CIF) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(CC_SMSALERT_CUST_INFO);
        try {
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, CIF);
            calStmt.executeQuery();
            return calStmt.getString(1);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param CIF
     * @return
     * @throws SQLException
     */
    public String CC_SMS_ALERT_TD_INFO(String CIF) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(CC_SMS_ALERT_TD_INFO);
        try {
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, CIF);
            calStmt.executeQuery();
            return calStmt.getString(1);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param CIF
     * @return
     * @throws SQLException
     */
    public String CC_CUST_MBANKING_INFO(String CIF) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(CC_CUST_MBANKING_INFO);
        try {
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, CIF);
            calStmt.executeQuery();
            return calStmt.getString(1);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GET_SMS_HIST(String param) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(CC_GET_CUST_SMS_HIST);
        try {
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CLOB);
            //Slit paramenters
            String[] params = param.split("#");
            calStmt.setString(2, params[0]);
            calStmt.setString(3, params[1]);
            SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
            Date fromDate = sdt.parse(params[2]);
            Date toDate = sdt.parse(params[3]);
            calStmt.setDate(4, new java.sql.Date(fromDate.getTime()));
            calStmt.setDate(5, new java.sql.Date(toDate.getTime()));
            calStmt.executeQuery();
            return calStmt.getString(1);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    //IB_CUST_INFO
    //END OF CONTACT CENTER
    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public List GetAccountListByCustNo(String custno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(custno);
        ArrayList list = JDBCEngine.executeQuery(GET_VW_ACCOUNT_SUMMARY, p_args, connection);
        return list;
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public List GetTDAccountBeforeRedemtion(String accountno) throws Exception {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(GET_TD_BEFORE_REDEMP);
            ArrayList list = new ArrayList();
            calStmt.setString(1, accountno);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(2);
            if (rs == null) {
                return list;
            }
            while (rs.next()) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("prdcode", rs.getString("accountclass"));
                h.put("prdname", rs.getString("proddesc"));
                h.put("codacctcurr", rs.getString("ccy"));
                h.put("numbalance", rs.getString("depositamt"));
                h.put("codbranch", rs.getString("codbranch"));
                h.put("numavailbal", rs.getString("numavlbalance"));
                h.put("acctopendt", rs.getString("depositdate"));
                h.put("maturitydate", rs.getString("maturitydate"));
                h.put("rate", rs.getString("rate"));
                h.put("term", rs.getString("term"));
                h.put("payinterest", rs.getString("payinterest"));
                h.put("isredem", rs.getString("isredem"));
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

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public List getCASAccountHasLimitMB(String accountno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(accountno);
        ArrayList list = JDBCEngine.executeQuery(GET_VW_CASA_HASLIMIT, p_args, connection);
        return list;
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public List getTdAccountByAccountNo(String accountno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(accountno);
        ArrayList list = JDBCEngine.executeQuery(GET_FCC_VW_MSTTDDETAILS, p_args, connection);
        return list;
    }

    /**
     *
     * @param taikhoan
     * @return
     * @throws Exception
     */
    public ArrayList getLoanAccountByAccountNo(String taikhoan) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(taikhoan);
        ArrayList list = new ArrayList();
        list = JDBCEngine.executeQuery(GET_VW_CLACCOUNTSCB, p_args, connection);
        return list;
    }

    /**
     *
     * @param unique_name
     * @param unique_id
     * @return
     * @throws Exception
     */
    public ArrayList getCustomerInfoForMB(String unique_name, String unique_id) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(unique_id);
        ArrayList list = new ArrayList();
        if (unique_name.equals("1")) {
            list = JDBCEngine.executeQuery(GET_FCC_VW_MSTCORPORATE_CIF, p_args, connection);
        } else {
            list = JDBCEngine.executeQuery(GET_FCC_VW_MSTCORPORATE_ID, p_args, connection);
        }
        return list;
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
        ArrayList p_args = new ArrayList();
        
        //kimncm add branch code
        IDBI dbi = new DBIImpl();
        String accbrn =  dbi.GET_BRANCHCODE_FROM_FCC(accountno);
        p_args.add(accbrn);
        
        p_args.add(accountno);
        p_args.add(fromDate);
        p_args.add(toDate);
        if (srno == null || srno.isEmpty() || srno.equals("0")) {
            return JDBCEngine.executeQuery(GET_TRANSACTION_LIST_ACCOUNT, p_args, connection);
        } else {
            p_args.add(srno);
            return JDBCEngine.executeQuery(GET_TRANSACTION_LIST_ACCOUNT_SR, p_args, connection);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getTDTransationListByAccountNo(String accountno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(accountno);
        return JDBCEngine.executeQuery(GET_TRANSACTION_LIST_TD_ACCOUNT, p_args, connection);
    }

    /**
     *
     * @param bankCode
     * @return
     * @throws Exception
     */
    public List getBeneBank(String bankCode) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(bankCode);
        ArrayList list = JDBCEngine.executeQuery(GET_MSTBENEBANKCODE, p_args, connection);
        return list;
    }

    /**
     *
     * @param codCity
     * @return
     * @throws Exception
     */
    public List getBankCity(String codCity) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(codCity);
        ArrayList list = JDBCEngine.executeQuery(GET_BANK_CITY, p_args, connection);
        return list;
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
        ArrayList p_args = new ArrayList();
        p_args.add(branchcode);
        p_args.add(accountclass);
        return JDBCEngine.executeQuery(GET_ACCOUNTCLASS_INFO, p_args, connection);
    }

    /**
     *
     * @param cardno
     * @return
     * @throws Exception
     */
    public ArrayList getMaterCardDetailByCardno(String cardno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(cardno);
        return JDBCEngine.executeQuery(GET_VW_MASTERCARD_INFO, p_args, connection);
    }

    /**
     *
     * @param cardno
     * @return
     * @throws Exception
     */
    public ArrayList getTransactionMaterCardByCardno(String cardno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(cardno);
        return JDBCEngine.executeQuery(GET_VW_TRANSACTION_MASTERCARD, p_args, connection);
    }

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public ArrayList getTokenbyCustno(String custno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(custno);
        return JDBCEngine.executeQuery(GET_TOKEN_BY_CUSTNO, p_args, connection);
    }

    /**
     *
     * @param serialno
     * @return
     * @throws Exception
     */
    public ArrayList checkTokenbySerialno(String serialno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(serialno);
        return JDBCEngine.executeQuery(CHECK_TOKEN_BY_SERIALNO, p_args, connection);
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getCLSCHEDULEINTPAID(String accountno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(accountno);
        return JDBCEngine.executeQuery(GET_VW_CLSCHEDULEINTPAIDSCB, p_args, connection);
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getCLSCHEDULEINTUNPAID(String accountno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(accountno);
        return JDBCEngine.executeQuery(GET_VW_CLSCHEDULEINTUNPAIDSCB, p_args, connection);
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getCLSCHEDULEPRIPAIDSCB(String accountno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(accountno);
        return JDBCEngine.executeQuery(GET_VW_CLSCHEDULEPRIPAIDSCB, p_args, connection);
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getCLSCHEDULEPRIUNPAIDSCB(String accountno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(accountno);
        return JDBCEngine.executeQuery(GET_VW_CLSCHEDULEPRIUNPAIDSCB, p_args, connection);
    }

    /**
     *
     * @param custaccno
     * @return
     * @throws Exception
     */
    public ArrayList checkAmountBeforePay(String custaccno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(custaccno);
        return JDBCEngine.executeQuery(CHECK_AMOUNT_BEFORE_PAY, p_args, connection);
    }

    /**
     *
     * @param ccy
     * @return
     * @throws Exception
     */
    public ArrayList getInterestRate(String ccy) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(ccy);
        return JDBCEngine.executeQuery(GET_FCC_VW_INTEREST_RATE, p_args, connection);
    }

    /**
     *
     * @param account_class
     * @return
     * @throws Exception
     */
    public ArrayList getProductAccountClass(String account_class) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(account_class);
        return JDBCEngine.executeQuery(VW_MSTPRODUCTCLASSES, p_args, connection);
    }

    /**
     *
     * @param acccount
     * @return
     * @throws Exception
     */
    public ArrayList getSttmCustAccountSyn(String acccount) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(GET_BALANCE_AFTER_TRAN);
            ArrayList list = new ArrayList();
            calStmt.setString(1, acccount);
            calStmt.registerOutParameter(2, Types.INTEGER);
            calStmt.registerOutParameter(3, Types.INTEGER);
            calStmt.execute();
            if (calStmt.getString(2).isEmpty()) {
                LOGGER.error("getSttmCustAccountSyn() - Không tồn tại tài khoản");
                throw new Exception("getSttmCustAccountSyn() - Không tồn tại tài khoản");
            }
            HashMap<String, String> h = new HashMap<String, String>();
            h.put("acy_avl_bal", calStmt.getString(2));
            h.put("acy_curr_balance", calStmt.getString(3));
            list.add(h);
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
     * @param custno
     * @return
     * @throws Exception
     */
    public List GetTemplateFromFCDB(String custno) throws Exception {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(GET_TEMPLATE_FROM_FCDB);
            ArrayList list = new ArrayList();
            calStmt.setString(1, custno);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(2);
            if (rs == null) {
                return list;
            }
            while (rs.next()) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("formname", rs.getString("formname"));
                h.put("txntype", rs.getString("txntype"));
                h.put("fromaccount", rs.getString("fromaccount"));
                h.put("toaccount", rs.getString("toaccount"));
                h.put("ccy", rs.getString("ccy"));
                h.put("amount", rs.getString("amount"));
                h.put("remark", rs.getString("remark"));
                h.put("benename", rs.getString("benename"));
                h.put("citycode", rs.getString("citycode"));
                h.put("cityname", rs.getString("cityname"));
                h.put("branchcode", rs.getString("branchcode"));
                h.put("branchname", rs.getString("branchname"));
                h.put("idopendate", rs.getString("idopendate"));
                h.put("idcitycode", rs.getString("idcitycode"));
                h.put("idcityname", rs.getString("idcityname"));
                h.put("bankcode", rs.getString("bankcode"));
                h.put("bankname", rs.getString("bankname"));
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

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public ArrayList getBeneficiaryFromFCDB2(String custno) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(custno);
        return JDBCEngine.executeQuery(VW_BENEFICIARY_FROM_FCDB, p_args, connection);
    }

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public ArrayList getBeneficiaryFromFCDB(String custno) throws Exception {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(GET_BENEFICIARY_FROM_FCDB);
            ArrayList list = new ArrayList();
            calStmt.setString(1, custno);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(2);
            if (rs == null) {
                return list;
            }
            while (rs.next()) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("benename", rs.getString("benename"));
                h.put("accountno", rs.getString("accountno"));
                h.put("txntype", rs.getString("txntype"));
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

    /**
     *
     * @param staffcode
     * @return
     * @throws Exception
     */
    public String GetStaffDetail(String staffcode) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(staffcode);
        ArrayList list = JDBCEngine.executeQuery(GET_NAMESTAFF, p_args, connection);
        if (!list.isEmpty()) {
            HashMap hm = (HashMap) list.get(0);
            return hm.get("ten_nv").toString();
        }
        return null;
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
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(INSERT_FEEDBACK);
            calStmt.setString(1, subject);
            calStmt.setString(2, content);
            calStmt.setString(3, idchannel);
            calStmt.setString(4, idchanneluser);
            calStmt.registerOutParameter(5, OracleTypes.INTEGER); //pID_NNT
            calStmt.execute();
            return calStmt.getInt(5);
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
     * @param idlist
     * @param status
     * @param userid
     * @param idChanneluser
     * @return
     * @throws Exception
     */
    public int updateFeedback(String idlist, String status, String userid, String idChanneluser) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        String UPDATE_FEEDBACK_id;
        int fb = 0;
        if (status.equals("Y")) {
            p_args.add(status);
            p_args.add(userid);
            p_args.add(idChanneluser);
            UPDATE_FEEDBACK_id = UPDATE_FEEDBACK + idlist + ")";
            fb = JDBCEngine.executeUpdate(UPDATE_FEEDBACK_id, p_args, connection);
        } else if (status.equals("P")) {
            p_args.add(status);
            p_args.add(userid);
            p_args.add(idChanneluser);
            UPDATE_FEEDBACK_id = UPDATE_FEEDBACK_PROCESSING + idlist + ")";
            fb = JDBCEngine.executeUpdate(UPDATE_FEEDBACK_id, p_args, connection);
        }
        return fb;
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
    public ArrayList<?> searchFeedback(String idchannel, String status, String fromdate, String todate, String beginRow, String endRow) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        p_args.add("%" + idchannel + "%");
        p_args.add("%" + status + "%");
        p_args.add(beginRow);
        p_args.add(endRow);
        return JDBCEngine.executeQuery(SEARCH_FEEDBACK, p_args, connection);
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
    public ArrayList<?> searchFeedbackAll(String idchannel, String status, String fromdate, String todate) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        p_args.add("%" + idchannel + "%");
        p_args.add("%" + status + "%");
        return JDBCEngine.executeQuery(SEARCH_ALL_FEEDBACK, p_args, connection);
    }

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
     * @param cif
     * @param cardtype
     * @param groupcode
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCardList(String cif, String cardtype, String groupcode) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(cif);
        if (cardtype == null) {
            cardtype = "";
        }
        if (groupcode == null) {
            groupcode = "";
        }
        if (cardtype.isEmpty() && groupcode.isEmpty()) {
            return JDBCEngine.executeQuery(GET_CARD_LIST_NEW, p_args, connection);
        } else {
            String GET_CARD_LIST_TEMP = GET_CARD_LIST_NEW.concat(" WHERE ");
            if (!groupcode.isEmpty()) {
                GET_CARD_LIST_TEMP = GET_CARD_LIST_TEMP.concat(" ACCOUNTGROUPCODE  IN (").concat(groupcode).concat(") AND");
            }
            if (!cardtype.isEmpty()) {
                GET_CARD_LIST_TEMP = GET_CARD_LIST_TEMP.concat(" BRN  IN (").concat(cardtype).concat(") AND");
            }
            GET_CARD_LIST_TEMP = StringUtils.stripBack(GET_CARD_LIST_TEMP, "AND");

            return JDBCEngine.executeQuery(GET_CARD_LIST_TEMP, p_args, connection);
        }
    }

    /**
     *
     * @param cardaccountno
     * @param period
     * @return
     * @throws Exception
     */
    public ArrayList<?> getMCStateDetail(String cardaccountno, String period) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(cardaccountno);
        p_args.add(period);
        return JDBCEngine.executeQuery(GET_MC_INFO_STMT, p_args, connection);
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
    public ArrayList<?> getCCStatement(String cardaccountno, String period, String srno, String rownum) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(cardaccountno);
        p_args.add(period);
        p_args.add(srno);
        p_args.add(rownum);
        return JDBCEngine.executeQuery(VW_STATEMENT_MASTERCARD, p_args, connection);
    }

    /**
     *
     * @param cardno
     * @return
     * @throws Exception
     */
    public ArrayList getMaterCardDetailByCardnoNew(String cardno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(cardno);
        return JDBCEngine.executeQuery(GET_VW_MASTERCARD_INFO_NEW, p_args, connection);
    }

    /**
     *
     * @param cardno
     * @return
     * @throws Exception
     */
    public ArrayList getTransactionMaterCardByCardnoNew(String cardno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(cardno);
        return JDBCEngine.executeQuery(GET_VW_TRANSACTION_MASTERCARD_NEW, p_args, connection);
    }

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public List GetAccountListByCustNoNew(String custno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(custno);
        p_args.add(custno);
        ArrayList list = JDBCEngine.executeQuery(GET_VW_ACCOUNT_SUMMARY_NEW, p_args, connection);
        return list;
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getCardnoByAccountno(String accountno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(accountno);
        return JDBCEngine.executeQuery(GET_CARD_LIST_BY_ACCOUTNO, p_args, connection);
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
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(PKS_REGISTER_ALERT_TD);
            calStmt.setString(1, cifno);
            calStmt.setString(2, mobileno);
            calStmt.setString(3, active);
            calStmt.registerOutParameter(4, Types.INTEGER);
            calStmt.registerOutParameter(5, Types.NVARCHAR);
            calStmt.execute();
            if (calStmt.getInt(4) != 0) {
                LOGGER.warn("regeisterAlertTDMB --> " + calStmt.getString(5));
                throw new Exception(calStmt.getString(5));
            }
            return calStmt.getInt(4);
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
        } else if (status.equals("P")) {
            p_args.add(status);
            p_args.add(userid);
            p_args.add(idChanneluser);
            UPDATE_FEEDBACK_id = UPDATE_REGISTERINFO_PROCESSING + idlist + ")";
            ri = JDBCEngine.executeUpdate(UPDATE_FEEDBACK_id, p_args, connection);
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
     * @return
     * @throws Exception
     */
    public ArrayList<?> getPrimaryATMList(String cif) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(cif);
        return JDBCEngine.executeQuery(GET_PRIMARYATM_LIST, p_args, connection);
    }

    /**
     *
     * @param acccount
     * @return
     * @throws Exception
     */
    public ArrayList<?> GetBeneficaryName(String acccount) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(acccount);
        return JDBCEngine.executeQuery(GET_BENE_NAME, p_args, connection);
    }

    /**
     *
     * @param cif
     * @param cardaccountnumber
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCreditCardList(String cif, String cardaccountnumber) throws Exception {
        ArrayList p_args = new ArrayList();
        if (cardaccountnumber == null || cardaccountnumber.length() == 0) {
            p_args.add(cif);
            GET_CREDIT_CARD_LIST = GET_CREDIT_CARD_LIST.concat(" CIF = ? ");
            return JDBCEngine.executeQuery(GET_CREDIT_CARD_LIST, p_args, connection);
        } else {
            p_args.add(cardaccountnumber);
            GET_CREDIT_CARD_LIST = GET_CREDIT_CARD_LIST.concat(" CARDACCOUNTNO = ? ");
            return JDBCEngine.executeQuery(GET_CREDIT_CARD_LIST, p_args, connection);
        }
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

    /**
     *
     * @return @throws Exception
     */
    public String[] getListAccountClassRedemEbank() throws Exception {
        ArrayList arraysCLass = JDBCEngine.executeQuery(ACCOUNT_CLASS_REDEMPTION, new ArrayList(), connection);
        String[] arrayStr = new String[arraysCLass.size()];
        for (int i = 0; i < arraysCLass.size(); i++) {
            HashMap hm = (HashMap) arraysCLass.get(i);
            arrayStr[i] = hm.get("accountclass").toString();
        }
        return arrayStr;
    }

    /**
     *
     * @param branchcode
     * @param accountclass
     * @param maturedat
     * @return
     * @throws Exception
     */
    public ProcedureCallDto verifyOpenTD(String branchcode, String accountclass, String maturedat) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(FN_CHK_TD_MATURITY_DATE);
            calStmt.setString(1, branchcode);
            calStmt.setString(2, accountclass);
            calStmt.setString(3, maturedat);
            calStmt.registerOutParameter(4, Types.INTEGER);
            calStmt.registerOutParameter(5, Types.VARCHAR);
            calStmt.execute();
            ProcedureCallDto pro = new ProcedureCallDto();
            pro.setErrorStatus(String.valueOf(calStmt.getInt(4)));
            pro.setErrorMessage(calStmt.getString(5));
            return pro;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
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
     * @return @throws Exception
     */
    public ArrayList<?> getAllListInsuranceTrn() throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        return JDBCEngine.executeQuery(GET_INSURANCE_TRN, p_args, connection);
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ProcedureCallDto checkTDIsEOD(String accountno) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(PROC_CHECKTD_ISEOD);
            calStmt.setString(1, accountno);
            calStmt.registerOutParameter(2, Types.VARCHAR);
            calStmt.registerOutParameter(3, Types.VARCHAR);
            calStmt.execute();
            ProcedureCallDto pro = new ProcedureCallDto();
            pro.setErrorStatus(calStmt.getString(3));
            pro.setErrorMessage(calStmt.getString(2));
            return pro;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public String[] getListJoinAccount() throws Exception {
        ArrayList arraysCLass = JDBCEngine.executeQuery(SELECT_JOINT_ACCOUNT, new ArrayList(), connection);
        String[] arrayStr = new String[arraysCLass.size()];
        for (int i = 0; i < arraysCLass.size(); i++) {
            HashMap hm = (HashMap) arraysCLass.get(i);
            arrayStr[i] = hm.get("idaccount").toString();
        }
        return arrayStr;
    }
}
