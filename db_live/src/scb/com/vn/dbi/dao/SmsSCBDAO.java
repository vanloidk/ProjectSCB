package scb.com.vn.dbi.dao;

import antlr.StringUtils;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import oracle.jdbc.OracleTypes;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import scb.com.vn.dbi.bo.GenericHibernateBO;
import scb.com.vn.dbi.dto.*;
import scb.com.vn.ultility.jdbc.JDBCEngine;
import scb.com.vn.ultility.Common;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.cims.register.RegisterInfoDetail;
import scb.com.vn.dbi.dto.SMSService;
import scb.com.vn.dbi.dto.SMSServiceTK;
import scb.com.vn.dbi.dto.SMSServiceUser;
import scb.com.vn.dbi.dto.sms_partner_request;

/**
 *
 * @author minhndb
 */
public class SmsSCBDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(SmsSCBDAO.class.getName());

    final String LIST_PARTNER_PRO = "begin proc_txn_partner(?); end;";
    final String LIST_PARTNER = "SELECT * FROM TMP_TXN_PARTNER";
    final String GETLISTALL_INSURANCETRN = "select FCC_FN_ADDL_TEXT(acaae.ac_entry_sr_no),sttc.trn_desc,acaae.trn_dt,acaae.value_dt,acaae.lcy_amount, acaae.*  from ACTB_DAILY_LOG@fcatfcclink acaae left join STTM_TRN_CODE@fcatfcclink sttc on acaae.trn_code=sttc.trn_code WHERE TRN_REF_NO in (  select  acaae.TRN_REF_NO  from acvw_all_ac_entries@fcatfcclink acaae   left join ACTB_DAILY_LOG@fcatfcclink sttc on acaae.trn_code=sttc.trn_code  WHERE acaae.AC_NO  in ('0010100103030001')   AND SUBSTR(acaae.TRN_REF_NO,4,4) IN ('BHMI','CHDP')  ) and acaae.AC_NO  in ('0010100103030001') order by acaae.AC_ENTRY_SR_NO desc";
    final String GETINSBYIDPAY = "SELECT * from insurance_payment t where idpayment=?";
    final String UPDATE_INSURANCESTATUS_Y = "UPDATE INSURANCE_STATUS SET STATUS=? WHERE ID=1";
    final String UPDATE_INSURANCESTATUS_N = "UPDATE INSURANCE_STATUS SET STATUS=? WHERE ID=1";
    final String DELETE_BCBILL = "DELETE FROM BC_BILL";
    final String GETID_BCBILL = "select * from BC_BILL t where prem_typ=? and due_dt=? and pol_num=?";
    final String INSERT_BCBILLHISTORY = "insert into BC_BILL_HISTORY(id,pol_num,owner_name,owner_id,ref_num,prem_typ,due_dt,amount,col_code,area,checksum,accnum,auto_db_dt,date_backup,in_date_bcbill)select id,pol_num,owner_name,owner_id,ref_num,prem_typ,due_dt,amount,col_code,area,checksum,accnum,auto_db_dt,sysdate,in_date from BC_BILL";
    // =================== ETS ===================
    final String GETLIST_ETS_MSTTXN = "select * from ETS_MSTTXN where isenable='Y'";
    final String INS_SP_ETS_ROLE = "INS_ETS_ROLE";
    final String INS_ETS_ROLETXN = "insert into ETS_ROLETXN(idrole, idtxn, flginit) values(?,?,?)";
    final String SEARCH_ETS_MSTROLE = "select mr.*, TO_CHAR(mr.datcreated,'DD/MM/YYYY HH24:MI:SS') datcreated_vn from ETS_MSTROLE mr where 1=1";
    final String SEARCH_ETS_ROLETXN = "select rt.*, mr.id_entity, mr.usertype, mr.idchannel, mr.description, mr.isdefault  from ETS_MSTROLE mr, ETS_ROLETXN rt where mr.idrole=? and mr.idrole = rt.idrole ";
    final String COUNT_ETS_USERROLE = "select count(t.idrole) as cntrole from ETS_USERROLE t where t.id_entity=? and t.idchannel=? and t.idrole=?";
    final String DEL_ETS_ROLETXN = "delete ETS_ROLETXN t where t.idrole=?";
    final String DEL_ETS_MSTROLE = "delete ETS_MSTROLE t where t.id_entity=? and t.usertype=? and t.idchannel=? and t.idrole=?";
    // insert user
    final String INS_SP_ETS_USER = "INS_ETS_USER";
    final String INS_ETS_CHANNELUSER = "insert into ETS_MSTCHANNELUSER(ID_ENTITY,IDUSER,IDCHANNELUSER,IDCHANNEL,PASSWORD,LOCKFLAG,FLAGFORCECHANGEPWD) values (?,?,?,?,?,?,?)";
    final String INS_ETS_USERROLE = "insert into ETS_USERROLE(ID_ENTITY,IDUSER,IDCHANNEL,IDROLE) values(?,?,?,?)";
    // update
    // change password
    final String UPD_ETS_PASS = "update ETS_MSTCHANNELUSER t set t.password=?, t.lockflag=?, t.flagforcechangepwd=?, t.DATELASTCHANGEPASS=sysdate where t.id_entity=? and t.iduser=? and t.idchanneluser=? and t.idchannel=?";
    final String GET_ETS_USER_BYIDCHANNELUSER = "select u.* from ETS_MSTUSER u where u.activeflag='Y' and u.iduser = (select cu.iduser from ETS_MSTCHANNELUSER cu where cu.idchanneluser=?)";
    final String GET_ETS_CHANNELUSER_BYIDCHANNELUSER = "select * from ETS_MSTCHANNELUSER where idchanneluser=?";
    final String GET_ETS_USERROLE_BYIDUSER = "select * from ETS_USERROLE ur where ur.iduser=? and ur.id_entity=? and ur.idchannel=?";
    final String GET_ETS_ROLETXN_BYIDROLE = "select * from ETS_ROLETXN t where t.idrole=?";
    // SEARCH
    final String SEARCH_ETS_USER = "select cu.*, u.* from ETS_MSTUSER u, ETS_MSTCHANNELUSER cu where u.iduser=cu.iduser and u.id_entity=cu.id_entity";
    // =================== SMS ===================
    final String INSERT_USERPIN2 = "INSERT INTO SMS_USERPIN2(id_entity, iduser, idchanneluser, idchannel, usertype, passwordtxn, lengthpwd, lockflag, flagforcechangetxnpwd, markerid, status) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    final String DELETE_USERPIN2 = "DELETE SMS_USERPIN2 where id_entity=? and iduser=? and idchanneluser=? and idchannel=?";
    final String GET_USERPIN2 = "SELECT * FROM SMS_USERPIN2 t WHERE t.idchanneluser=? and t.usertype=?";
    final String UPD_UP2_SUCC = "UPDATE SMS_USERPIN2 t SET t.datelastsucc = sysdate, t.nbractsucc=t.nbractsucc+1 WHERE  t.idchanneluser=? and t.usertype=?";
    final String UPD_UP2_FAIL = "UPDATE SMS_USERPIN2 t SET t.datelastfail = sysdate, t.nbractfail=t.nbractfail+1 WHERE  t.idchanneluser=? and t.usertype=?";
    final String UPD_PWD = "UPDATE SMS_USERPIN2 t SET t.passwordtxn=?, t.lengthpwd=?, t.flagforcechangetxnpwd=? WHERE  t.idchanneluser=? and t.usertype=?";
    final String UPD_UP2_IDCHANNELUSER = "UPDATE SMS_USERPIN2 t SET t.idchanneluser=? WHERE  t.idchanneluser=? and t.usertype=?";
    final String INSERT_WAITRESPONSE = "INSERT INTO SMS_WAITRESPONSE(id_entity, idchannel, usertype, idchanneluser, typecmd, smsmessage, DEMONSTRING) VALUES (?,?,?,?,?,?,?)";
    final String GET_WAITRESPONSE = "SELECT * FROM SMS_WAITRESPONSE t WHERE t.id_entity=? and t.idchannel=? and t.usertype=? and t.idchanneluser=? and t.typecmd=? and t.indate > (sysdate - interval '5' minute)";
    final String GET_ATMLOCATION = "select * from SMS_ATMLOCATION t WHERE t.matinhthanh=? and t.quan=?";
    final String GET_ATMLOCATION_EXT = "select * from SMS_ATMLOCATION t WHERE t.matinhthanh=?";
    final String GET_BRANCHLOCATION_MAIN = "select * from SMS_BRANCHLOCATION WHERE matinhthanh=? and quan=?";
    final String GET_BRANCHLOCATION_EXT = "select * from SMS_BRANCHLOCATION WHERE matinhthanh=?";
    final String GET_NHLM = "select * from SMS_NHLM";
    final String INS_SMS_LOG = "INS_SMS_LOG";
    final String GET_ALERTNOTIFY = "select * from SMS_ALERTNOTIFY t where t.messagestat='N'";
    final String UPD_ALERTNOTIFY = "update SMS_ALERTNOTIFY set messagestat=?, messagenew=?, senddate=sysdate WHERE idmessage=?";
    final String INS_SMS_SENDER_LOG = "PROC_SENDSMS_WS";

    final String GETLISTALL_RoviderIns = "select a.idpartner, a.name, b.accountno, b.isactive, b.publickey, b.privatekey from PFI_PARTNER a left join onl_trans_partners b on a.idpartner=b.partnerid where idpartner='MNL'";
    /*CK Tan Viet (S)*/
    final String GETLISTALL_PFIPARTNER = "select * from PFI_PARTNER where idpartner='TVSI'";
    final String GETLISTALL_INSURANCEPAYMENT = "select * from INSURANCE_PAYMENT t where isapproved='A' and status='D' and to_char(paydate_fcubs,'dd/MM/yyyy')=to_char(sysdate,'dd/MM/yyyy') and to_char(paydate_fcubs, 'hh24')>=12 and to_char(paydate_fcubs, 'hh24')<18 order by idinsurancepayment desc";
    final String GETLIST_SI = "SELECT S.ID, S.CHANNELID, S.AMOUNT, S.CUSTUMERACCOUNT, S.CUSTUMERNAME, S.REFCORE, S.ADDINFO, S.CREATEDATE, S.PARTNERID, S.STATUS FROM SI_TRFTOSI S "
            + "WHERE TRUNC(S.CREATEDATE)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(S.CREATEDATE)<=to_date(?,'dd/MM/yyyy')";
    final String GETLIST_PFITRANF_APPROVE = "SELECT  T.IDBILLPAID , t.CUSTOMERCODE, T.IDCHANNELUSER_MAKER , T.INSDATE_MAKER , T.BRANCH_CODE, T.ACC_HOLDERNAME, T.ACC_IDACCOUNT, T.CUSTOMERSINAME  "
            + "FROM SI_TRFTOSI_AUTH t WHERE t.IDSERVICETYPE=? AND t.BRANCH_CODE=? AND t.ISAPPROVED = 'W' ORDER BY T.IDBILLPAID desc";

    final String GETNAME_PFIPARTNER = "select NAME from PFI_PARTNER WHERE IDPARTNER=?";
    final String GETLISTALL_Partner = "select * from PFI_PARTNER a left join onl_trans_partners b on a.idpartner=b.partnerid where idpartner='MNL'";
    /*CK Tan Viet (E)*/

    // PBL
    final String GETLISTALL_SERVICETYPE = "select * from pbl_servicetype";
    //final String GETLISTALL_PROVIDER = "select * from PBL_PROVIDER";
    final String GETLISTALL_PROVIDER = "select DD.IDPARTNER, CC.IDPROVIDER, CC.PROVIDERNAME, CC.PROVIDERNAME_ENG, CC.AUTOPAY  from PBL_PROVIDER cc left join pbl_partnerservice dd on CC.IDPROVIDER = DD.IDPROVIDER";
    final String GET_PARTNERSERVICE_BY_PS = "select * from PBL_PARTNERSERVICE t where t.idservicetype=? and t.idprovider=? order by t.priority asc";
    final String GETLISTALL_PARTNERSERVICE = "select t.*, p.name as partnername, p.shortname from pbl_partnerservice t, pbl_partner p where t.idpartner = p.idpartner";
    final String GETLIST_PARTNERSERVICEBYSERVICE = "select t.*, p.name as partnername, p.shortname from pbl_partnerservice t, pbl_partner p where t.idservicetype=? and t.idpartner = p.idpartner";
    final String GET_PARTNERSERVICE = "select ps.*,  prov.providername , p.name as partnername, p.shortname, st.name as servicename"
            + " from pbl_partnerservice ps, pbl_partner p, pbl_servicetype st, PBL_PROVIDER prov"
            + " where ps.idprovider=? and ps.idservicetype=? and ps.idpartner = p.idpartner and st.idservicetype = ps.idservicetype and prov.idprovider = ps.idprovider";
    final String INSERT_SP_PBL_LOG = "INS_PBL_LOG";
    final String DEL_PBL_BILLPAID = "delete PBL_BILLPAID where IDBILLPAID=?";
    //HieuDT update for routing sub-branch, sub-company: EVNHN
    final String GET_PROVIDER_BY_IDSERVICETYPE = "select idprovider, providername, providername_eng, AUTOPAY from vw_partnerservices where idservicetype=?";
    final String UPD_REFFUBS_BY_IDBILLPAID = "UPDATE PBL_BILLPAID SET ref_fcubs=? WHERE IDBILLPAID=?";
    final String UPD_STATUS_BY_IDBILLPAID = "UPDATE PBL_BILLPAID SET STATUS=? WHERE IDBILLPAID=?";
    final String UPD_DATAXML_BY_IDBILLPAID = "UPDATE PBL_BILLPAID SET DATAXML=? WHERE IDBILLPAID=?";
    final String GET_PBL_BILLPAID = "select bd.*, st.name as servicetypename, prov.* from pbl_billpaid bd, pbl_servicetype st, pbl_provider prov where bd.idbillpaid=? and bd.idservicetype = st.idservicetype and bd.idprovider = prov.idprovider";
    final String GETLISTALL_SERVICETYPE_OF_AUTOPAY = "select * from pbl_servicetype where hasautopay='Y'";
    // VNPAY
    final String INS_PBL_BILLPAID_DETAIL = "INSERT INTO PBL_BILLPAID_DETAIL(idbillpaid, customercode, dataxml) VALUES (?,?,?)";
    final String INS_PBL_BILLPAID_PARTNERDETAIL = "INSERT INTO PBL_BILLPAID_PARTNERDETAIL(idbillpaid, partnerdetailid, partnerid, account) VALUES (?,?,?,?)";
    final String GET_PBL_BILLPAID_DETAIL = "select * from PBL_BILLPAID_DETAIL t where t.idbillpaid=?";
    // ECPAY
    final String INS_PBL_BILLPAID_DETAIL_ECPAY = "INSERT INTO PBL_BILLPAID_DETAIL_ECPAY(IDBILLPAID, IDBILL, IDCUSTOMER, CUSTNAME, ADDRESS, PERIOD, AMOUNT, RESULT, DESCRIPTION, TRACENUMBER, MARKERID) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    final String GET_PBL_ATTRIBUTE = "select t.* from pbl_attribute t where t.idpartner=? and t.idservicetype=?";
    final String GET_PBL_BILLPAIDDETAIL_ECPAY = "select * from PBL_BILLPAID_DETAIL_ECPAY t where t.idbillpaid=?";
    final String GET_PBL_BILLPAID_BY_ID = "select * from vw_billpaid_customername t where t.idbillpaid=?";
    // THONG BAO SMS TIET KIEM KY HAN DEN HAN
    final String GET_ALERTACCOUNTTD_BY_CUST_AC_NO = "select * from SMS_CUST_ALERTTD t where t.cust_ac_no=?";
    final String GET_ALERTACCOUNTTD_BY_CUST_NO = "select t.*, TO_CHAR(t.ac_open_date,'DD/MM/YYYY') as ac_open_date_vn from SMS_CUST_ALERTTD t where t.cust_no=? and t.registertype=?";
    final String GET_ALERTACCOUNTTD_BY_CUST_NO_IDCARD = "select t.*, TO_CHAR(t.ac_open_date,'DD/MM/YYYY') as ac_open_date_vn from SMS_CUST_ALERTTD t where t.registertype=?";
    final String GET_ALERTACCOUNTTD_BY_CUST_AC_NO_WITH_ACC = "select * from SMS_CUST_ALERTTD t where t.cust_no=? and t.cust_ac_no=? and t.registertype='ACC'";
    final String INS_ALERT_ACCOUNTTD = "INS_CUST_ALERTTD";
    final String GET_ALERTACCOUNTTD_BY_ID = "select t.*, TO_CHAR(t.ac_open_date,'DD/MM/YYYY') as ac_open_date_vn, NVL( t.modify_user,NVL(B.IDCHANNELUSER,t.MAKERID)) IDCHANNELUSER  from SMS_CUST_ALERTTD t,ETS_MSTCHANNELUSER B"
            + " where t.MAKERID = B.IDUSER(+)   and t.id=?";
    final String UPD_ALERT_ACCOUNTTD_BY_MOBILE = "update SMS_CUST_ALERTTD set mobile=?,modify_user=?, tk_denhan=?, tk_momoi=?, branch_code_mm=?, modify_no = modify_no+1, MODIFY_DATE = sysdate   where id=?";
    final String UPD_ALERT_POINT_REWARD = "update SMS_CUST_ALERTTD set mobile=?, modify_user=?, tk_denhan=?, tk_momoi=?, point_reward=?, user_poi_rew=?, date_poi_rew=sysdate, branch_code_mm=?, branch_code_poi=?, modify_no= modify_no+1, MODIFY_DATE= sysdate where id=?";
    final String get_Find_List_Cust_No = "select cust_ac_no, ac_desc, acy_avl_bal from vw_acct_thuphisms where cust_no = ?";
    final String UPD_REF_STATUS = "update SMS_CUST_ALERTTD set ref_status=? where cust_no=?";
    final String UPD_REF_THU = "update SMS_CUST_ALERTTD set cust_ac_no=?, point_reward=?, auto_poi_rew=?, user_poi_rew=?, so_tien=?, ref_thu=?, ref_status=?, date_poi_rew=sysdate where cust_no=?";
    final String UPD_REF_CHI = "update SMS_CUST_ALERTTD set so_tien=?, ref_chi=?, ref_status=? where cust_no=?";
    final String UPD_THU_CONG = "update SMS_CUST_ALERTTD set point_reward=?, user_poi_rew=?, auto_poi_rew=?, date_poi_rew=sysdate where cust_no=?";
    final String FIND_ACCOUNTTD = "select A.* from alert_accounttd_detail A where 1=1";

    // THONG BAO SMS TIET KIEM TICH LUY
    final String GET_ALERTACCOUNTTICHLUY_BY_CUST_AC_NO = "select * from SMS_CUST_ALERTTICHLUY t where t.cust_ac_no=?";
    final String GET_ALERTACCOUNTTICHLUY_BY_CUST_NO = "select t.*, TO_CHAR(t.ac_open_date,'DD/MM/YYYY') as ac_open_date_vn from SMS_CUST_ALERTTICHLUY t where t.cust_no=? and t.registertype=?";
    final String GET_ALERTACCOUNTTICHLUY_BY_CUST_AC_NO_WITH_ACC = "select * from SMS_CUST_ALERTTICHLUY t where t.cust_no=? and t.cust_ac_no=? and t.registertype='ACC'";
    final String INS_ALERT_ACCOUNTTICHLUY = "INS_CUST_ALERTTICHLUY";
    final String GET_ALERTACCOUNTTICHLUY_BY_ID = "select t.*, TO_CHAR(t.ac_open_date,'DD/MM/YYYY') as ac_open_date_vn from SMS_CUST_ALERTTICHLUY t where t.id=?";
    final String UPD_ALERT_ACCOUNTTICHLUY_BY_MOBILE = "update SMS_CUST_ALERTTICHLUY set mobile=? where id=?";
    //Luu y la can phai loc lai theo dk of tiet kiem tich luy
    final String FIND_ACCOUNTTICHLUY = "select A.* from alert_accounttichluy_detail A where 1=1";

    // AUTO REGISTER
    final String INS_AUTO_REG = "INS_PBL_AUTO_REG";
    final String UPD_AUTO_REG = "update pbl_auto_reg set %1$s where id=?";
    final String DEL_AUTO_REG = "delete pbl_auto_reg where id=?";
    final String VW_PBL_AUTO_REG = "select * from VW_PBL_AUTO_REG where cust_acc_cif = ? ";
    final String GETLIST_PBL_BILLPAID_BYDATE = "select * from VW_BILLPAID_TO_COLLATED where TO_CHAR(insdate,'DD/MM/YYYY') = ?";
    final String GETLIST_PBL_COLLATED_BYDATE = "select * from PBL_COLLATED where TO_CHAR(insdate,'DD/MM/YYYY') = ? and idpartner = ?";
    final String GETLIST_USER_NEEDCHGPWD = "select * from ETS_MSTCHANNELUSER t where t.iduser>1451 and t.iduser<1510";
    final String GETBRANCHINFO_BY_CIF = "select * from VW_ACC_BRANCH t where t.customer_no=?";
    final String GETBRANCHBYACCNO = "select v.* from VW_CUST_ACCOUNT t, FCC_VW_MSTBRANCH v where t.CUST_AC_NO=? and t.BRANCH_CODE = v.branch_code";
    final String ISEXSISTCONTRACTBYIDCONTRACT = "select t.idcontract from PBL_AUTO_REG_CONTRACT t where t.idcontract=?";
    //CHUONG BO SUNG SQL
    //lay phonemumber
    final String SQL_GET_USERPHONENUMBER = "SELECT SMS_SCB.GETUSERPHONENUMBER(:PIDUSER) FROM DUAL";
    //lay messgage log
    final String SQL_GET_MSGLOG = "SELECT SMS_SCB.getmsglog(:pTable,:pIdfield,:pIdvalue) FROM DUAL";
    final String SQL_GET_TYPECONFIRMUSER = "SELECT SMS_SCB.FN_GETTYPECOMFIRM(:piduser) FROM DUAL";
    final String SQL_GET_SESSION_LANG = "SELECT SMS_SCB.fn_getsession_lang(:pidsession)  FROM DUAL";
    final String SQL_GET_UDFVALUE_USER = "SELECT SMS_SCB.FN_GET_DELIVERYOPTUSER(:piduser,:pudfname) FROM DUAL";
    final String SQL_CHECK_USERANDACCOUNT = "SELECT SMS_SCB.checkIsuserAndAccount(:piduser,:pcust_ac_no) FROM DUAL";
    final String SQL_GET_PERMISSIONS = "SELECT PARTNER_ID,B.NAME FUNCTION_NAME FROM LOC_DEV.GW_PERMISSION A,LOC_DEV.GW_FUNCTION B WHERE A.FUNCTION_ID=B.FUNCTION_ID AND A.ISPERMISSION='Y' ORDER BY PARTNER_ID";
    final String SQL_GET_LIST_TETEFIRSTNUMBER = "SELECT * FROM PBL_TELE_FIRSTNUMER";
    final String SQL_GET_LIST_PARTNER = "SELECT IDPARTNER||'_'||IDSERVICETYPE as groupid, IDPROVIDER as code,providername as name from VW_PARTNER ORDER BY IDPARTNER||'_'||IDSERVICETYPE";
    final String SQL_GET_LIST_SCREEN = "select idscreen||'_'||idpartner||'_'||idservicetype||'_'||idprovider||'_'||idlang as key,data,data_01 from PBL_EBK_SCREEN";
    final String SQL_GET_LIST_PARAINFO = "SELECT CODE, NAME FROM SMS_SCB.PBL_PARA_INFO WHERE GROUP_ID=?";

    //PAYOO PAYMENT
    final String INS_PBL_PAYOOLOG = "INSERT INTO PBL_PAYOOLOG(CUSTOMERCODE, IDSERVICETYPE, IDPROVIDER, BILLINFO, INSDATE) VALUES (?,?,?,?, SYSDATE)";
    final String GET_PBL_PAYOOLOG_BYLATEST = "SELECT * FROM (SELECT * FROM PBL_PAYOOLOG WHERE CUSTOMERCODE=? AND IDSERVICETYPE=? AND IDPROVIDER=?  ORDER BY INSDATE DESC) WHERE ROWNUM = 1";
    final String GET_PBL_PAYOOLOG_BYLATEST_PRINT = "SELECT * FROM (SELECT * FROM PBL_PAYOOLOG WHERE CUSTOMERCODE in (select UPPER(customercode) from pbl_billpaid where idbillpaid =?)"
            + " AND IDSERVICETYPE=? AND IDPROVIDER=?  ORDER BY INSDATE DESC) WHERE ROWNUM = 1";
    final String GET_LASTESTBILLINFO = "SELECT T.BILLINFO AS BILLINFO FROM SMS_SCB.PBL_PAYOOLOG T WHERE T.CUSTOMERCODE =  ? AND T.IDSERVICETYPE = ? AND T.IDPROVIDER = ? AND TRUNC(T.INSDATE) > TRUNC(SYSDATE-1) ORDER BY T.INSDATE DESC";

    //PAYOO COLLATE    
    final String INS_PAYOOCOLLATE = "INS_PAYOO_COLLATE";
    final String COLLATE_PAYOOBILL_DATA = "EXC_PAYOOCOLLATE";
    final String ISEXSISTPAYOOBILLDATACOLLATED = "SELECT cust_no FROM PAYOOCOLLATE where to_char(PAYOODATETIME,'ddMMyyyy') = ?";
    final String GETLISTALL_SERVICETYPE_NOTOPUP = "select * from pbl_servicetype WHERE IDSERVICETYPE  <>  'VNTOPUP' ";
    //ebanking mo rong
    final String UPD_ALERT_ACCOUNTTD_BY_ACTIVE = "update SMS_CUST_ALERTTD set active=?,modify_user=?, modify_no = modify_no+1, MODIFY_DATE = sysdate   where id=?";

    //=============ebankin mo rong search bill===========================
    final String SEARCH_ALL_BILLPAID = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY idbillpaid) LINENUM, A.*"
            + " FROM VW_SEARCH_BILLPAID_LIVE A where trn_dt between to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND to_date(?,'dd/MM/yyyy') "
            + " AND IDBILLPAID LIKE ? AND (IDPARTNER =? OR 'ALL' = ?) "
            + " AND (BRANCHCODE = ? OR 'ALL' = ?) AND UPPER(CUSTOMERCODE) LIKE UPPER(?)"
            + " AND (idchannel = ? OR 'ALL' = ? ) AND ( idservicetype = ? OR 'ALL' = ? )  AND (STATUSCODE = ? OR 'ALL' = ?) "
            + " ORDER BY idbillpaid"
            + " ) WHERE LINENUM BETWEEN ?  AND ?";
    final String SEARCH_ALL_BILLPAID_SUM = "SELECT count(*) sl "
            + " FROM VW_SEARCH_BILLPAID_LIVE A where trn_dt between to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND to_date(?,'dd/MM/yyyy')"
            + " AND IDBILLPAID LIKE ? AND (IDPARTNER =? OR 'ALL' = ?) "
            + " AND (BRANCHCODE = ? OR 'ALL' = ?) AND UPPER(CUSTOMERCODE) LIKE UPPER(?)"
            + " AND (idchannel = ? OR 'ALL' = ? ) AND ( idservicetype = ? OR 'ALL' = ? )  AND (STATUSCODE = ? OR 'ALL' = ?) ";

    final String SEARCH_ALL_BILLPAID_DIARY = "SELECT * FROM  VW_TRANS_IB_MB_TTHD  where trunc(ngaygd)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy')"
            + "AND trunc(ngaygd)<=to_date(?,'dd/MM/yyyy')"
            + "AND channel like ? ";

    final String SEARCH_ALL_ONLBILL = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY ID) LINENUM, A.*"
            + " FROM VW_SEARCH_ONL_TRANS A where trunc(transdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(transdate)<=to_date(?,'dd/MM/yyyy')"
            + " AND ID LIKE ? AND TRANSACTIONID LIKE ? AND PARTNERID LIKE ? AND nvl(A.CUSTNO,'null') LIKE ? AND STATUSID LIKE ?"
            + " ORDER BY ID"
            + " ) WHERE LINENUM BETWEEN ?  AND ?";
    final String SEARCH_ALL_MNL = "SELECT * FROM (select ROW_NUMBER() OVER(ORDER BY IDINSURANCEPAYMENT) LINENUM,a.IDINSURANCEPAYMENT, decode(a.IDCHANNEL,'01','Internet Banking', '41','SMS Banking', '03','Mobile Banking', '11', 'Tai quay') kenh,a.insdate,a.transdate,decode(a.PAYMENTMETHOD,'1','Tiền mặt', '2','Chuyển khoản') hinhthuc, a.OWNER_ID MAKH, a.OWNER_NAME TENKH,  a.TOTALAMOUNT SOTIEN, a.REF_FCUBS SOBUTTOAN, a.ISAPPROVED,a.STATUS, a.POL_NUM, a.idchanneluser_maker userMaker, a.ACC_IDACCOUNT SOTK, a.ACC_HOLDERNAME TENNGUOITHUCHIEN,a.PREM_TYP, a.DUE_DT, a.COL_CODE, a.BRANCH_CODE MADONVI, case when a.isapproved='A' and a.status='D' then 'Thanh cong' when a.isapproved='H' and a.status is null then 'Huy' when a.isapproved='W' and a.status is null then 'Cho duyet' when a.isapproved='A' and a.status='W' then 'Trich tien khong thanh cong' when a.isapproved='A' and a.status='H' then 'Cho tra soat' when a.isapproved='A' and a.status='F' then 'That bai' end Trangthai,idchanneluser_maker nguoithuchien from (Select b.*,nvl(b.status,'null') t_status,nvl(b.OWNER_ID,'null') t_OWNER_ID,nvl(b.POL_NUM,'null') t_POL_NUM from insurance_payment b) a WHERE  trunc(a.insdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(a.insdate)<=to_date(?,'dd/MM/yyyy') and a.idinsurancepayment like ? and a.t_POL_NUM like ? and a.ISAPPROVED like ? and  a.t_status like ? and a.t_OWNER_ID like ? order by insdate) WHERE LINENUM BETWEEN ? AND ?";
    final String SEARCH_ALL_MNL_SUM = "select count(*) sl from( select  case when a.isapproved='A' and a.status='D' then 'Thanh cong' when a.isapproved='H' and a.status is null then 'Huy' when a.isapproved='W' and a.status is null then 'Cho duyet' when a.isapproved='A' and a.status='W' then 'Trich tien khong thanh cong' when a.isapproved='A' and a.status='H' then 'Cho tra soat' when a.isapproved='A' and a.status='F' then 'That bai' end Trangthai,idchanneluser_maker nguoithuchien from (Select b.*,nvl(b.status,'null') t_status, nvl(b.OWNER_ID,'null') t_OWNER_ID,nvl(b.POL_NUM,'null') t_POL_NUM from insurance_payment b) a WHERE trunc(a.insdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') and trunc(a.insdate)<=to_date(?,'dd/MM/yyyy') and a.idinsurancepayment like ? and a.t_POL_NUM like ? and a.ISAPPROVED like ? and  a.t_status like ? and a.t_OWNER_ID like ? order by insdate)";
    final String UPDATE_STATUS_MNL = "UPDATE insurance_payment SET STATUS=? WHERE IDINSURANCEPAYMENT=?";
    final String SEARCH_ALL_TBT = "SELECT * FROM (select ROW_NUMBER() OVER(ORDER BY ID) LINENUM, a.id, a.status, a.createdate, a.typetransfer, b.merchantname, a.auditnumber, a.custno, c.customer_name1, a.transamount, a.refcore, d.descr, a.fromnumber, a.destnumber, a.narration, a.acquiringcode, decode(processingcode,'910000',substr(destnumber,1,6),benid) BENID, substr(a.refcore, 1, 3) branchcode from sml_ft_master a left join sttm_customer@fcatfcclink c on  a.custno = c.customer_no , sml_merchant b,  sml_status d  where a.merchanttype = b.merchantid and a.status = d.status and a.typetransfer=d.typetransfer and a.typefunction='TRN' and trunc(a.createdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(a.createdate)<=to_date(?,'dd/MM/yyyy') and id like ? and a.typefunction = 'TRN' and nvl(a.custno,'0') like ? and a.status like ? and auditnumber like ? order by createdate) WHERE LINENUM BETWEEN ?  AND ?";
    final String SEARCH_ALL_VTC = "SELECT * FROM (select a.status, ROW_NUMBER() OVER(ORDER BY ID) LINENUM, a.id,a.transdate, decode(a.idchannel,'01','IB','41','SMS','03','MB','11','TQ') KENH, a.ref_partner, a.acc_cust, a.acc_holdername, a.totalamount, a.ref_fcubs, DECODE(a.status,'D','Thanh cong','F','That bai','H','Cho tra soat','W','Trich tien khong thanh cong') Trangthai, a.acc_idaccount, a.idprovider, a.quantity, a.pricetag, a.idpartner, a.branch_code  from (select b.*, nvl(b.ref_partner,'null') t_ref_partner from retail_buycard b) a  where trunc(a.transdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(a.transdate)<=to_date(?,'dd/MM/yyyy') and id like ? and a.t_ref_partner like ? and a.status like ? and a.acc_cust like ? and a.idpartner like ? order by transdate) WHERE LINENUM BETWEEN ?  AND ?";
    final String SEARCH_ALL_VTC_SUM = "select count(*) sl from (select b.*, nvl(b.ref_partner,'null') t_ref_partner from retail_buycard b) a  where trunc(a.transdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(a.transdate)<=to_date(?,'dd/MM/yyyy') and id like ? and a.t_ref_partner like ? and a.status like ? and a.acc_cust like ? and a.idpartner like ? order by transdate";
    final String UPDATE_STATUS_TBT = "UPDATE sml_ft_master SET STATUS=? WHERE ID=?";
    final String UPDATE_STATUS_VTC = "UPDATE retail_buycard SET STATUS=? WHERE ID=?";
    final String SEARCH_ALL_IBL = "SELECT * FROM (select a.status, ROW_NUMBER() OVER(ORDER BY IDDANGKY) LINENUM,a.iddangky, a.transdate, a.kenhdangky, substr(a.acc_idaccount, 6, 7) makh, a.tennguoidangky, a.totalamount, a.ref_fcubs, decode(a.status,'00','Thanh cong','01','Trich tien khong thanh cong','03','That bai') Trangthai, a.acc_idaccount, a.idsanpham, a.tennguoithuhuong, a.idpartner, substr(a.ref_fcubs,1,3) branhcode from ins_dangky a where status <> '02' and trunc(a.transdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(a.transdate)<=to_date(?,'dd/MM/yyyy') and iddangky like ? and a.status like ? and substr(a.acc_idaccount, 6, 7) like ? and a.idpartner like ? order by transdate ) WHERE LINENUM BETWEEN ?  AND ?";
    final String UPDATE_STATUS_IBL = "UPDATE ins_dangky SET STATUS=? WHERE IDDANGKY=?";
    final String SEARCH_ALL_IBL_SUM = "select count(*) sl from ins_dangky a where status <> '02' and trunc(a.transdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(a.transdate)<=to_date(?,'dd/MM/yyyy') and iddangky like ? and a.status like ? and substr(a.acc_idaccount, 6, 7) like ? and a.idpartner like ? order by transdate";
    final String SEARCH_ALL_TVSI = "SELECT * FROM (select  ROW_NUMBER() OVER(ORDER BY ID) LINENUM,a.id, a.createdate, decode(a.bankcode,'000000','Chuyen tien cho nha dau tu trong he thong','Chuyen tien cho nha dau tu ngoai he thong') dichvu,'' kenh, a.txndetailid MAGD, A.PARTNERACCOUNT MAKH, 'CTCP Chung khoan Tan Viet' TENKH,  A.AMOUNT SOTIEN, A.REFCORE SOBUTTOAN, B.ERR_DESCRIPTION TRANGTHAI, A.CUSTUMERACCOUNT SOTK, A.BRANCHNAME CNNHNHAN, A.CUSTUMERNAME TENNDT, '' TKCK, A.DESCRIPTION DIENGIAI,'' NGUOITHUCHIEN, 'TVSI' DOITAC, SUBSTR(A.REFCORE,1,3) MADV, status from si_trffromsi_detail a, si_status b WHERE A.STATUS = B.ERR_CODE and trunc(a.createdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(a.createdate)<=to_date(?,'dd/MM/yyyy') and id like ? and a.status like ? and a.txndetailid like ? and A.PARTNERACCOUNT like ? order by createdate) WHERE LINENUM BETWEEN ? AND ?";
    final String UPDATE_STATUS_TVSI = "UPDATE si_trffromsi_detail SET STATUS=? WHERE ID=?";
    final String SEARCH_ALL_TVSI_SUM = "select  count(*) sl from si_trffromsi_detail a, si_status b WHERE A.STATUS = B.ERR_CODE and trunc(a.createdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(a.createdate)<=to_date(?,'dd/MM/yyyy') and id like ? and a.status like ? and a.txndetailid like ? and A.PARTNERACCOUNT like ? order by createdate";
    final String SEARCH_ALL_TVSI_ = "SELECT * FROM (select a.status, ROW_NUMBER() OVER(ORDER BY ID) LINENUM,a.id,a.createdate, 'Nhan nop tien dau tu chung khoan' dichvu, decode(a.channelid,'100','IB', '101','MB', '102','TQ bang TK', '103','TQ bang TM') kenh, a.txnref MAGD, PKG_CHECK_ACCOUNT.getcif(a.custumeraccount) MAKH, a.custumername TENKH,  A.AMOUNT SOTIEN, A.REFCORE SOBUTTOAN, B.ERR_DESCRIPTION TRANGTHAI, A.CUSTUMERACCOUNT SOTK, substr(a.PARTNERACCOUNT,1,3) CNNHNHAN, A.CUSTUMERNAME TENNDT, substr(a.addinfo,1,instr(a.addinfo,'|',1)-1) TKCK, A.Addinfo DIENGIAI, custumeraccount nguoithuchien, 'TVSI' DOITAC, substr(a.custumeraccount,1,3) MADV, c.idchanneluser_maker usermaker from si_trftosi a left join si_status b on A.STATUS = B.ERR_CODE left join si_trftosi_auth c on c.idchannel = '11' and a.refcore = c.ref_fcubs and A.STATUS = B.ERR_CODE where trunc(a.createdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(a.createdate)<=to_date(?,'dd/MM/yyyy') and id like ? and a.status like ? and a.txnref like ? and PKG_CHECK_ACCOUNT.getcif(a.custumeraccount) like ? order by createdate) WHERE LINENUM BETWEEN ? AND ?";
    final String UPDATE_STATUS_TVSI_ = "UPDATE si_trftosi SET STATUS=? WHERE ID=?";
    final String SEARCH_ALL_TVSI_SUM_ = "select count(*) sl from si_trftosi a, si_status b WHERE A.STATUS = B.ERR_CODE and trunc(a.createdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(a.createdate)<=to_date(?,'dd/MM/yyyy') and id like ? and a.status like ? and a.txnref like ? and PKG_CHECK_ACCOUNT.getcif(a.custumeraccount) like ? order by createdate";
    final String SEARCH_ALL_TBT_SUM = "SELECT count(*) sl FROM SML_FT_MASTER A left join  sttm_customer@fcatfcclink c on a.custno = c.customer_no , sml_merchant b, sml_status d  where a.merchanttype = b.merchantid and a.status = d.status and a.typetransfer=d.typetransfer and trunc(createdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(createdate)<=to_date(?,'dd/MM/yyyy') AND ID like ? AND TYPEFUNCTION = 'TRN' AND nvl(CUSTNO,'0') LIKE ? AND a.STATUS LIKE ? and auditnumber like ? ORDER BY ID";
    final String SEARCH_ALL_ONLBILL_SUM = "SELECT count(*) sl  FROM VW_SEARCH_ONL_TRANS A where trunc(transdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(transdate)<=to_date(?,'dd/MM/yyyy') AND ID LIKE ? AND TRANSACTIONID LIKE ? AND PARTNERID LIKE ?  AND nvl(A.CUSTNO,'null') LIKE ? AND STATUSID LIKE ?";

    //================================================================
    final String UPD_STATUS_PAYBILL = "UPD_STATUS_TRANSACTION";
    //================================================================
    final String UPD_PROC_TRANS = "PROC_TRANS_UPDATE";

    //duytxa08072015
    final String LOCKSMS = "LOCKSMS";
    //endduytxa08072015

    //duytxa28062017 
    final String USER_LOCK_THUPHI = "PKG_FEE_EBANK.USER_LOCK_THUPHI";

    //lay user mobile
    final String GET_USER_THUPHISMS = "select * from fee_mb_init where cif= ? and thangnam= ? ";
    //endduytxa28062017

    //CARD WEB SERVICES
    final String INS_CWWS_CARD_RELOAD = "PROC_INS_CWWS_CARD_RELOAD";
    final String insertCardReloadTrans = "INSERT INTO SMS_SCB.CWWS_CARD_RELOAD(ID, SRCACCOUNT, PAN_LOC, CARD_BRN, CCY, EXP_DATE, AMOUNT, REF_FCC, REF_CW, TRANS_STATUS, TRANS_DATE, SRCCHANNEL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?)";
    final String UPD_CWWS_CARD_RELOAD = "PROC_UPD_CWWS_CARD_RELOAD";
    final String updateCardReloadTrans = "UPDATE SMS_SCB.CWWS_CARD_RELOAD SET REF_FCC = ?, REF_CW = ?, TRANS_STATUS = ?, TRANS_DATE = SYSDATE WHERE ID=?";
    //CARD WEB SERVICES
    //Longle
    final String INSERT_LOG_SMSCUSTALERTTD = "INSERT INTO SMS_CUST_ALERTTD_LOG select * from SMS_CUST_ALERTTD where CUST_NO LIKE ?";
    final String GET_ACCOUNTPROVIDER_LIST = "SELECT ACCOUNT FROM PBL_PROVIDERACCOUNT WHERE BRANCHCODE LIKE ?";
    final String GET_SMSFITCOM_LIST = "SELECT * FROM SMS_SENDER_FCOM@SMS WHERE SEND_FLAG = ?";
    final String UPDATE_STATUS_SMSFITCOM = "UPDATE SMS_SENDER_FCOM@SMS SET SEND_FLAG = ?,SEND_TIME = sysdate WHERE ID = ?";
    final String INSERT_SMSFILE = "INSERT INTO SMS_SENDER_FCOM_BK@SMS(ID,ACCOUNT,MOBILE, CONTENT, SERVICE_TYPE, SERVICE_CODE, REQUEST_ID, SEND_FLAG, ENCRYPT, INS_TIME) VALUES (?,'1',?,?,?,?,'SCB',0,0,sysdate)";
    final String INSERT_SMSSENDERLOG = "INSERT INTO SMS_FILELOG(ID,MOBILE,CONTENT,MESSAGETYPE,USER_SENDER,SEND_DATE) VALUES (?,?,?,?,?,sysdate)";
    final String INSERT_SMSFILE_DETAIL = "INSERT INTO SMS_FILEDETAIL(IDMSG,MOBILE,CONTENT,STATUS, IDFILE, MESSAGETYPE, ISAPPROVED) VALUES (?,?,?,0,?,?,'W')";
    final String UPLOADFILESMS = "INSERT INTO SMS_FILEUPLOAD(IDFILE,MESSAGETYPE,FILENAME, ISAPPROVED, INS_DATE, IDUSER_MARKER, DECRIPTIONS, SEND_FLAG) VALUES (?,?,?,'W',sysdate,?,?,0)";
    final String APPROVEFILESMS = "UPDATE SMS_FILEUPLOAD SET ISAPPROVED=?, IDUSER_CHECKER=?, CHECKED_DATE=sysdate WHERE IDFILE=?";
    final String UPDATESTATUSSMS = "UPDATE SMS_FILEDETAIL SET ISAPPROVED=? WHERE IDFILE=?";
    final String UPDATESENDMSG = "UPDATE SMS_FILEDETAIL SET STATUS=1, SEND_TIME=sysdate WHERE IDMSG=?";
    final String UPDATESENDFILE = "UPDATE SMS_FILEUPLOAD SET SEND_FLAG=?,IDUSER_SENDER=?,SEND_DATE=sysdate WHERE IDFILE=?";
    final String GETFILESMS = "SELECT DISTINCT  F.IDFILE, F.MESSAGETYPE, F.FILENAME, F.ISAPPROVED, F.SEND_FLAG,F.INS_DATE, F.IDUSER_MARKER, F.CHECKED_DATE, F.IDUSER_CHECKER, F.IDUSER_SENDER, F.SEND_DATE ,F.DECRIPTIONS,"
            + "(select count(*) from SMS_FILEDETAIL d where D.IDFILE=F.IDFILE) as TOTAL, (SELECT MIN(D.SEND_TIME) FROM SMS_FILEDETAIL D WHERE  D.IDFILE=F.IDFILE AND D.STATUS=1) AS FROMSEND_DATE, "
            + "(SELECT MAX(D.SEND_TIME) FROM SMS_FILEDETAIL D WHERE  D.IDFILE=F.IDFILE AND D.STATUS=1) AS TOSEND_DATE FROM SMS_FILEUPLOAD F , SMS_FILEDETAIL D "
            + "WHERE F.IDFILE = D.IDFILE(+) AND F.ISAPPROVED LIKE ? AND trunc(F.INS_DATE)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(F.INS_DATE)<=to_date(?,'dd/MM/yyyy')";
    final String GETFILESMS_DETAIL = "SELECT F.IDFILE ,D.MESSAGETYPE, F.FILENAME, F.ISAPPROVED, F.INS_DATE, F.IDUSER_MARKER,"
            + "D.IDMSG, D.MOBILE, D.CONTENT, D.SEND_TIME, D.STATUS, F.SEND_FLAG FROM SMS_FILEUPLOAD F , SMS_FILEDETAIL D "
            + "WHERE F.IDFILE = D.IDFILE(+)  AND F.IDFILE=?";
    final String GET_TRANSCODE_EVNHN = "BEGIN PKG_PBL_BILLPAID.EVN_GETID(?,?); END;";
    final String insertNapasCollate = "BEGIN PKG_PBL_BILLPAID.insertNapasBillingCollate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String CheckRefundTransferBanknet = "BEGIN PKG_PBL_BILLPAID.CheckRefundTransfer(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String getOutNapasEcomCollate = "BEGIN PKG_PBL_BILLPAID.getOutNapasBillingCollate(?); END;";
    final String UpdateRefundTransferNAPAS = "BEGIN PKG_PBL_BILLPAID.UpDateRefundTransfer(?,?,?); END;";
    //Thanh toan the tin dung tai quay

    String INS_PAYCREDITCARD = "INSERT INTO PAYCREDITCARD(ID, CIF, LOC, CARDTYPE, PANMASK, EXPI_DATE, E_NAME, BRANCHCARD, THANHTOANTRONGKY, THANHTOANTOITHIEU, DUNOCUOIKY, DUNOTHE, IDUSER_MAKER, "
            + "IDCHANNELUSER_MAKER, INSDATE_MAKER, TOTALAMOUNT, PAYMENTMETHOD, ACC_CUST, ACC_IDACCOUNT, ACC_HOLDERNAME, ACC_ADDRESS, INSDATE, ISAPPROVED, BRANCH_CODE, IDCARD, IDCARDDOB, IDCARDNAME, IDCARDADDRESS)"
            + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?,?,?,?,SYSDATE,?,?,?,?,?,?)";

    String INS_PAYCREDITCARDV2 = "INSERT INTO PAYCREDITCARD(ID, CIF, LOC, CARDTYPE, PANMASK, EXPI_DATE, E_NAME, BRANCHCARD, THANHTOANTRONGKY, THANHTOANTOITHIEU, DUNOCUOIKY, DUNOTHE, IDUSER_MAKER, "
            + "IDCHANNELUSER_MAKER, INSDATE_MAKER, TOTALAMOUNT, PAYMENTMETHOD, ACC_CUST, ACC_IDACCOUNT, ACC_HOLDERNAME, ACC_ADDRESS, INSDATE, ISAPPROVED, BRANCH_CODE, IDCARD, IDCARDDOB, IDCARDNAME, IDCARDADDRESS, SDT_KH, NoiCap_CMND)"
            + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?,?,?,?,SYSDATE,?,?,?,?,?,?, ?,?)";
    String GET_PAYCC_INFO_BYID = "SELECT * FROM PAYCREDITCARD WHERE ID=?";
    String GET_SML_STATUS = "SELECT * FROM sml_status";
    String GET_ONLTRAN_STATUS = "SELECT * FROM onl_trans_status";
    String GET_RETAIL_STATUS = "SELECT * FROM retail_status";
    String GET_INS_STATUS = "SELECT * FROM ins_status";
    String GET_SI_STATUS = "SELECT * FROM si_status";
    String GET_INSURANCE_BYID = "SELECT * FROM INSURANCE_PAYMENT WHERE IDPAYMENT=?";
    final String SEARCH_ALL_PAYCC_SUM = "SELECT count(*) sl"
            + " FROM VW_SEARCH_PAYCREDITCARD A where trunc(TRANSDATE)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(TRANSDATE)<=to_date(?,'dd/MM/yyyy')"
            + " AND CIF LIKE ? AND LOC LIKE ? AND BRANCHCODE LIKE ? AND STATUSCODE LIKE ?";
    final String SEARCH_ALL_PAYCREDITCARD = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY ID) LINENUM, A.*"
            + " FROM VW_SEARCH_PAYCREDITCARD A where trunc(transdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(transdate)<=to_date(?,'dd/MM/yyyy')"
            + " AND CIF LIKE ? AND LOC LIKE ? AND STATUSCODE LIKE ? AND BRANCHCODE LIKE ? ORDER BY id"
            + " ) WHERE LINENUM BETWEEN ?  AND ?";
    String TRANSFERFCUBS_WITH_PAYCREDITCARD_CASH = "BEGIN PKG_PAYMENT_CREDITCARD.PAYMENT_CREDITCARD(?,?,?,?,?,?,?,?,?,?,?,?); END;";
    String UPDATE_PAYCREDITCARD = "begin PKG_PAYMENT_CREDITCARD.UPDATE_PAYCREDITCARD(?,?,?,?,?,?,?);end;";
    //Thanh toan the tin dung tai quay

    /*Thanh toan Bao hiem (S)*/
    String GETLIST_INSURANCE_TOEXPORT_11 = "select * from ins_export_baolog11h";
    String GETLIST_INSURANCE_TOEXPORT_15 = "select * from ins_export_baolog15h";
//    String UPDATE_INSURANCE_PAYMENT = "UPDATE INSURANCE_PAYMENT SET IDUSER_CHECKER=?,IDCHANNELUSER_CHECKER=?,INSDATE_CHECKER=sysdate,REF_FCUBS=?,TRANSDATE=sysdate,PAYDATE=sysdate,PAYDATE_FCUBS=sysdate,ISAPPROVED=?,STATUS=? WHERE IDPAYMENT=?";
    String UPDATE_INSURANCE_PAYMENT = "BEGIN PKG_PAYMENT_INSURANCE.UPDATE_INSURANCE_PAYMENT(?,?,?,?,?,?); END;";
    String UPDATE_BCBILL = "BEGIN PKG_PAYMENT_INSURANCE.UPDATE_BCBILL(?,?); END;";
    /*Thanh toan Bao hiem (S)*/

    //Contact Center by Hieu     
    final String CC_GET_CUST_EMAIL_HIST = "{ ? = call MC_GetLogSendEmailMC(?,?)}";

    //End of Contact Center by Hieu
    final String VW_GW_TOKENOTP = "SELECT * FROM GW_TOKENOTP WHERE SERIALNO =?";
    final String REGISTER_AUTOBILL = "BEGIN PKG_MOBILEBANKING_GW.RegisterAutoBill(?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String REGISTER_AUTOBILL_VNPTBILL = "BEGIN PKG_MOBILEBANKING_VNPTBILL.RegisterAutoBill(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String getKMFromPhoneNumber = "BEGIN SMS_COMMON.getKMFromPhoneNumber(?,?); END;";
    final String PROC_DS_THUPHI_EBANK = "BEGIN PKG_FEE_EBANK.PROC_DS_THUPHI_EBANK(?,?); END;";
    final String USER_UNLOCK_THUPHI = "BEGIN PKG_FEE_EBANK.USER_UNLOCK_THUPHI(?,?,?,?,?,?); END;";
    final String PROC_ACC_THUPHI = "BEGIN PKG_FEE_EBANK.PROC_ACC_THUPHI(?,?,?); END;";
    final String FEE_THUPHI = "BEGIN PKG_FEE_EBANK.FEE_THUPHI(?,?,?,?); END;";
    final String INS_SANPHAM = "SELECT * FROM INS_SANPHAM";
    final String INS_SANPHAM_DETAIL = "SELECT * FROM INS_PHI P ,INS_CHIETKHAU C WHERE P.IDSANPHAM = C.IDSANPHAM  AND P.IDSANPHAM =? ORDER BY SOTIENPHI1 , TENPHI ";
    final String LIST_BILL_CANCEL = "BEGIN PROC_CHECK_TRANS_EVN(?,?,?,?,?); END;";
    final String INSERT_BILLPAID_REVERT = "INSERT INTO SMS_FILELOG(IDPARTNER,IDSERVICETYPE,IDPROVIDER,CUSTOMERCODE,IDCHANNEL,REF_PARTNER\n"
            + ",REF_FCUBS,TOTALAMOUNT,TRANSDATE,STATUS,BRANCH_CODE,IDUSER_MAKER_REVERSE,IDUSER_CHECKER_REVERSE\n"
            + ",REVERSE_STATUS,BRANCH_CODE_REVERSE) \n"
            + "VALUES (?,?,?,?,?,?,?,?,sysdate,?,?,?,?,?,?)";

    String BC_BILL_DETAIL = "SELECT * FROM BC_BILL WHERE ";
    String GETLIST_INSURANCE_BYCIF = "SELECT * FROM INS_DANGKY WHERE PKG_CHECK_ACCOUNT.getcif(acc_idaccount)  = ? ORDER BY IDDANGKY DESC";
    final String INS_CWWS_CARD_IPP = "PROC_INS_CWWS_CARD_IPP";
    final String insertCardIPPTrans = "INSERT INTO SMS_SCB.CWWS_CARD_IPP(ID, SRCACCOUNT, PAN_LOC, CARD_BRN, CCY, EXP_DATE, AMOUNT, REF_FCC, REF_CW, TRANS_STATUS, TRANS_DATE, SRCCHANNEL,DEBTPAY,DEBTCUR,ipppaytype) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?,?, ?, ?)";
    final String UPD_CWWS_CARD_IPP = "PROC_UPD_CWWS_CARD_IPP";
    final String updateCardIPPTrans = "UPDATE SMS_SCB.CWWS_CARD_IPP SET REF_FCC = ?, REF_CW = ?, TRANS_STATUS = ?, TRANS_DATE = SYSDATE WHERE ID = ?";
    final String LIST_CWWS_CARD_IPP = "SELECT * FROM CWWS_CARD_IPP where id = ?";
    final String LAST_MONTH_DATE = "select fn_check_eom('28/09/2017') as returnVal from dual;";
    // =================== ETS ===================
    private final String INSERT_BC_BILL = "INSERT INTO BC_BILL(ID, POL_NUM, OWNER_NAME, OWNER_ID, REF_NUM, PREM_TYP, DUE_DT, AMOUNT, COL_CODE, AREA, CHECKSUM, ACCNUM, AUTO_DB_DT, AMOUNTPAID, IN_DATE) VALUES (SEQ_BC_BILL.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

    //ADD BY LYDTY 03/2018
    private final String GET_BRANCHCODE_FROM_FCC = "begin PROC_GET_BRANCHCODE_FROM_FCC(?,?); end;";

    /* EVNHCM */
    final String DELETE_PAYOOBILL = "UPDATE PBL_BILLPAID T SET T.STATUS = 'C', T.DELETEINFO = '%s', T.DELETEDATE = SYSDATE WHERE T.IDPARTNER = '%s' AND T.REF_FCUBS IS NOT NULL AND (T.STATUS = 'D' OR T.STATUS = 'H') AND T.REF_PARTNER IS NOT NULL AND T.CUSTOMERCODE = '%s' AND T.TOTALAMOUNT = '%s' AND NVL(SUBSTR(T.REF_PARTNER, 0, INSTR(T.REF_PARTNER, '#')-1), T.REF_PARTNER) = '%s'";
    final String QUERY_REF_PAYOOLOG = "SELECT * FROM PBL_BILLPAID T WHERE T.IDPARTNER = ? AND T.REF_FCUBS IS NOT NULL AND (T.STATUS = 'D' OR T.STATUS = 'H') AND T.REF_PARTNER IS NOT NULL AND T.REF_FCUBS IS NOT NULL AND T.CUSTOMERCODE = ? AND T.TOTALAMOUNT = ? AND NVL(SUBSTR(T.REF_PARTNER, 0, INSTR(T.REF_PARTNER, '#')-1), T.REF_PARTNER) = ?";
    final String getACCNOFCUBS = "select accnofcubs from pbl_partnerservice where idpartner = ? and idservicetype = ? and idprovider = ?";
    /* EVNHCM */
    final String INS_ALERT_ACCOUNTTD2 = "INS_CUST_ALERTTD2";

    final String GET_ACCOUNTCLASS_INFO = "SELECT TO_CHAR(TODAY,'YYYYMMDD')   OpenDate,"
            + "TO_CHAR (GETMATURITYDATE (ACCOUNT_CLASS, TODAY,'',BRANCH_CODE),'YYYYMMDD' ) Matdate,"
            + "FCAT_FN_GET_TDRATE_FCDB_MB (ACCOUNT_CLASS,'VND',TODAY) Interest"
            + " from  FCC_VW_STTMS_ACCOUNT_CLASS A ,fcc_sttms_dates B"
            + " WHERE BRANCH_CODE = ? AND ACCOUNT_CLASS =?";

    String ACCOUNT_CLASS_REDEMPTION = "SELECT ACCOUNTCLASS FROM ACCOUNTCLASS_REDEMPTION WHERE ISREDEM = 'Y' OR ISREDEM = '1'";

    final String GET_BENE_NAME = "SELECT * FROM VW_BENEFICIARY_FOR_TRANSFER WHERE IDACCOUNT=?";

    final String VW_MSTPRODUCTCLASSES = "SELECT * FROM fcc_vw_mstproductclasses  WHERE account_class = ?";
    String PROC_CHECKTD_ISEOD = "BEGIN CHECKTD_ISEOD(?,?,?); END;";
    final String SELECT_JOINT_ACCOUNT = "SELECT * FROM jointaccount";
    final String GET_CARD_LIST_NEW = "SELECT * FROM table(FN_GET_CARD_INFO(?))";
    final String GET_MC_INFO_STMT = "SELECT * FROM GET_MC_INFO_STMT WHERE ACCOUNTNO =? AND PERIOD =? ";
    final String VW_STATEMENT_MASTERCARD = "SELECT * FROM VW_STATEMENT_MASTERCARD where ACCOUNTNO =? AND period=? AND SRNO>? AND ROWNUM <=?";
    final String GET_VW_REDEMPTION_AZ = "SELECT * FROM  VW_ACCOUNT_AZ_REDEMPTION where custno =?";
    final String GET_CARD_TYPE = "SELECT * FROM CARD_TYPE WHERE BRN =? and issueNew ='1'";
    final String GET_BANK_CITY = "SELECT * FROM BANK_CITY WHERE COD_CITY =?";
    final String PKS_CHECKISSUEATMCARD = "BEGIN PKG_MOBILEBANKING.CHECKISSUEATMCARD (?,?,?,?,?,?,?,?,?); END;";
    final String PKS_GET_POINT_MC = "BEGIN PKG_MOBILEBANKING.GET_POINT_MC(?,?,?,?,?); END;";
    final String GET_CARD_LIST_BY_ACCOUTNO = "select * from table(FN_GET_CARD_INFO(?))";

    /*SEARCH KHACH HANG TU CORE THEO CIF or CMND
    CREATED BY: BAOVQ
    CREATED DATE: 08/Aug/2018
     */
    //dvsms--------------------------------
    final String GET_SMSService_BY_ID = "select t.*, TO_CHAR(t.ac_open_date,'DD/MM/YYYY') as ac_open_date_vn, NVL( t.modify_user,NVL(B.IDCHANNELUSER,t.MAKERID)) IDCHANNELUSER, P.* from SMS_CUST_ALERTTD t,ETS_MSTCHANNELUSER B,Sms_Cust_Alerttd_Phone P"
            + " where t.MAKERID = B.IDUSER(+) and t.id = P.ID_SCA  and t.id=?";
    final String GET_SMSSERVICE_BY_CUST_NO = "select t.* from SMS_CUST_ALERTTD_PHONE t  where t.cust_no=? and t.isdeleted='0'";
    final String GET_ACCOUNTSMSService_BY_ID = "select t.*, TO_CHAR(t.ac_open_date,'DD/MM/YYYY') as ac_open_date_vn, NVL( t.modify_user,NVL(B.IDCHANNELUSER,t.MAKERID)) IDCHANNELUSER, P.* from SMS_CUST_ALERTTD t,ETS_MSTCHANNELUSER B, SMS_CUST_ALERTTD_PHONE P"
            + "where t.MAKERID = B.IDUSER(+) and t.id = P.ID_SCA and t.id=?";
    final String INS_SMSService = "PKG_SMS_CUST_ALERT_BAO.INS_CUST_ALERTTD";
    final String INS_SMSService_TT = "PKG_SMS_CUST_ALERT_BAO.INSERT_SMS_CUST_ALERT_TT";
    final String INS_SMSService_TK = "PKG_SMS_CUST_ALERT_BAO.INSERT_SMS_CUST_ALERT_TK";
    final String INS_SMSService_user = "PKG_SMS_CUST_ALERT_BAO.INSERT_SMS_CUST_ALERT_USER";
    final String Update_SMSService_tk = "PKG_SMS_CUST_ALERT_BAO.UPDATE_SMS_CUST_ALERT_TK";
    final String Update_SMSService_tt = "PKG_SMS_CUST_ALERT_BAO.UPDATE_SMS_CUST_ALERT_TT";
    final String Approve_SMSService_phone = "PKG_SMS_CUST_ALERT_BAO.APPROVE";
    final String getAccountTK = "select t.* from SMS_CUST_ALERTTD_PHONE t  where t.cust_no=? and t.isdeleted='0' and typeaccount='TK'";
    final String getlistAccountTT = "select CUST_AC_NO, MOBILE, IDPHONEALERT ID,rownum STT, isdeleted, ma_nv, record_stat from vw_acct_thuphisms_tt where cust_no = ?";
    final String GET_TENNV = "begin GET_MA_NV(?,?); end;";
    // final String QUERY_TENNV = "select emp_code||' - '||emp_name as nhanvien from SCB_PRO.HUV_HRM_TEMP@dbhistaff where emp_code = ?";
    final String QUERY_TENNV = "select emp_code||' - '||emp_name as nhanvien from HUV_HRM_TEMP where emp_code = ?";
    final String checkInsertDVSMS = "select *  from SMS_CUST_ALERT_USER where status='W' and custno = ? ";
    final String SEARCH_CUSTOMER_CORE = "select * from fcusr01.STTMS_CUSTOMER@FCATFCCLINK where customer_no=? or unique_id_value=?";
    final String get_data_approve = "select * from sms_cust_alerttd_phone_log where userrefid = ?";
    final String UPD_REF_STATUS_SMSTK = "update SMS_CUST_ALERTTD_PHONE_LOG set ref_status=?,ref_thu=?,ref_chi=? where id=? and typeaccount='TK'";
    final String CHECK_POINT_REWARD_THU = "select * from sms_cust_alerttd_phone_log where cust_no =? and ref_thu is not null and point_reward = 'Y' and typeaccount='TK'";
    final String CHECK_POINT_REWARD_CHI = "select * from sms_cust_alerttd_phone_log where cust_no =? and ref_chi is not null and point_reward = 'Y' and typeaccount='TK'";
    final String CHECK_POINT_REWARD_TC = "select * from sms_cust_alerttd_phone where cust_no =? and point_reward = 'Y' and typeaccount='TK'";
    /*-----------------END DVSMS---------*/
    final String GET_MSTBENEBANKCODE = "SELECT * FROM MSTBENEBANKCODE WHERE code =?";
    final String GET_FCC_VW_MSTCORPORATE_CIF = "SELECT * FROM FCC_VW_MSTCORPORATE WHERE idcorporate =?";
    final String GET_FCC_VW_MSTCORPORATE_ID = "SELECT * FROM FCC_VW_MSTCORPORATE WHERE POSTAL_CODE =?";
    final String GET_NAMESTAFF = "SELECT TEN_NV FROM FCC_SCB_CBNV where ma_nv =?";

    final String PKS_CHECK_BALANCE_BEFORE_FEE = "BEGIN PKG_MOBILEBANKING.CHECK_BALANCE_BEFORE_FEE(?,?,?,?,?,?); END;";
    final String GET_FCC_VW_INTEREST_RATE = "SELECT * FROM FCC_VW_INTEREST_RATE WHERE CCY_CODE =?";
    final String GET_FCC_VW_INTEREST_RATE_LDK = "SELECT * FROM FCC_VW_INTEREST_RATE_LDK WHERE CCY_CODE =?";
    final String GET_FCC_VW_INTEREST_RATE_LTT = "SELECT * FROM FCC_VW_INTEREST_RATE_LTT WHERE CCY_CODE =?";
    final String GET_FCC_VW_INTEREST_RATE_DN = "SELECT * FROM FCC_VW_INTEREST_RATE_DN WHERE CCY_CODE =?";

    final String GET_GOLDRATE = "SELECT * FROM VW_GOLDRATE";
    final String GET_EXCHANGERATE = "SELECT * FROM VW_EXCHANGERATE";

    final String GET_VW_CLSCHEDULEINTPAIDSCB = "SELECT * FROM VW_CLSCHEDULEINTPAIDSCB WHERE TAIKHOAN = ? and rownum <=10";
    final String GET_VW_CLSCHEDULEINTUNPAIDSCB = "SELECT * FROM VW_CLSCHEDULEINTUNPAIDSCB WHERE TAIKHOAN = ? and rownum <=10";
    final String GET_VW_CLSCHEDULEPRIPAIDSCB = "SELECT * FROM VW_CLSCHEDULEPRIPAIDSCB WHERE TAIKHOAN = ? and rownum <=10";
    final String GET_VW_CLSCHEDULEPRIUNPAIDSCB = "SELECT * FROM VW_CLSCHEDULEPRIUNPAIDSCB WHERE TAIKHOAN = ? and rownum <=10";

    // final String GETLISTALL_InsPayment = "SELECT * FROM insurance_payment S WHERE S.isapproved !='C' and TRUNC(S.insdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(S.insdate)<=to_date(?,'dd/MM/yyyy')";
    final String GETLISTALL_InsPayment = "SELECT * FROM VW_SEARCH_INSURANCE_PAYMENT S WHERE S.isapproved !='C' and TRUNC(S.insdate)>=to_date(nvl(?,'01/01/1900'),'dd/MM/yyyy') AND trunc(S.insdate)<=to_date(?,'dd/MM/yyyy')";

    //BAOTBQ 18/07/2022
    String GET_ONL_TRANS_PARTNER = "select * from ONL_TRANS_PARTNERS t where t.partnerid = ?";
    String GET_ONL_PAYMENT_BY_CARD = "select * from ONL_PAYMENT_BYCARD t where t.transid = ?";

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getListMstTxn() throws Exception {
        return JDBCEngine.executeQuery(GETLIST_ETS_MSTTXN, null, connection);
    }

    /**
     *
     * @param id_entity
     * @param usertype
     * @param idchannel
     * @param description
     * @param isdefault
     * @param createdby
     * @return
     * @throws Exception
     */
    public int insertMstRole(String id_entity, String usertype, String idchannel, String description, String isdefault, String createdby) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + INS_SP_ETS_ROLE + " (?,?,?,?,?,?, ?,?,?); END;");
            calStmt.setString(1, id_entity);
            calStmt.setString(2, usertype);
            calStmt.setString(3, idchannel);
            calStmt.setString(4, description);
            calStmt.setString(5, isdefault);
            calStmt.setString(6, createdby);
            calStmt.registerOutParameter(7, Types.INTEGER);
            calStmt.registerOutParameter(8, Types.INTEGER);
            calStmt.registerOutParameter(9, Types.NVARCHAR);
            calStmt.execute();

            if (calStmt.getInt(8) != 1) {
                LOGGER.warn("insertMstRole --> calStmt.getString(9) = " + calStmt.getString(9));
                throw new Exception(calStmt.getString(9));
            }
            return calStmt.getInt(7);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
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
     * @param id_entity
     * @param usertype
     * @param idchannel
     * @param idrole
     * @return
     * @throws Exception
     */
    public int deleteMstRole(String id_entity, String usertype, String idchannel, String idrole) throws Exception {
        ArrayList<String> p = new ArrayList<String>();
        p.add(id_entity);
        p.add(idchannel);
        p.add(idrole);
        ArrayList<?> a = JDBCEngine.executeQuery(COUNT_ETS_USERROLE, p, connection);
        if (a != null && a.size() > 0) {
            // Co ton tai USERROLE da su dung ROLE nay. KHong the xoa
            HashMap<?, ?> hm = (HashMap<?, ?>) a.get(0);
            int cntrole = Integer.parseInt(hm.get("cntrole").toString());
            if (cntrole > 0) {
                return -2;
            }
        }
        // Co the xoa
        ArrayList<String> pRoleTxn = new ArrayList<String>();
        pRoleTxn.add(idrole);
        JDBCEngine.executeUpdate(DEL_ETS_ROLETXN, pRoleTxn, connection);
        ArrayList<String> pRole = new ArrayList<String>();
        pRole.add(id_entity);
        pRole.add(usertype);
        pRole.add(idchannel);
        pRole.add(idrole);
        return JDBCEngine.executeUpdate(DEL_ETS_MSTROLE, pRole, connection);
    }

    /**
     *
     * @param idrole
     * @return
     * @throws Exception
     */
    public int deleteRoleTxn(String idrole) throws Exception {
        // Co the xoa
        ArrayList<String> pRoleTxn = new ArrayList<String>();
        pRoleTxn.add(idrole);
        return JDBCEngine.executeUpdate(DEL_ETS_ROLETXN, pRoleTxn, connection);
    }

    /**
     *
     * @param idrole
     * @param idtxn
     * @param flginit
     * @return
     * @throws Exception
     */
    public int insertRoleTxn(String idrole, String idtxn, String flginit) throws Exception {
        ArrayList<String> params = new ArrayList<String>();
        params.add(idrole);
        params.add(idtxn);
        params.add(flginit);
        return JDBCEngine.executeUpdate(INS_ETS_ROLETXN, params, connection);
    }

    /**
     *
     * @param idchannel
     * @param description
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchMstRole(String idchannel, String description) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        String sqlExtend = "";
        if (idchannel != null && idchannel.length() > 0) {
            sqlExtend += " and mr.idchannel=?";
            p_args.add(idchannel);
        }
        if (description != null && description.length() > 0) {
            sqlExtend += " and mr.description like ?";
            p_args.add(description);
        }
        return JDBCEngine.executeQuery(SEARCH_ETS_MSTROLE + sqlExtend, p_args,
                connection);
    }

    /**
     *
     * @param idrole
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchRoleTxn(String idrole) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idrole);
        return JDBCEngine.executeQuery(SEARCH_ETS_ROLETXN, p_args, connection);
    }

    // insert user
    /**
     *
     * @param mstuser
     * @return
     * @throws Exception
     */
    public int insertMstUser(EtsMstuser mstuser) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + INS_SP_ETS_USER + " (?,?,?,?,?,?,?,?,?, ?,?,?); END;");
            calStmt.setString(1, mstuser.getId_entity());
            calStmt.setString(2, mstuser.getTypeuser());
            calStmt.setString(3, mstuser.getFirstname());
            calStmt.setString(4, mstuser.getEmail());
            calStmt.setString(5, String.valueOf(mstuser.getActiveflag()));
            calStmt.setString(6, mstuser.getPhonenumber());
            calStmt.setString(7, String.valueOf(mstuser.getAuthreqd()));
            calStmt.setString(8, mstuser.getMakerid());
            calStmt.setString(9, (String) mstuser.getBranch_code());
            calStmt.registerOutParameter(10, Types.INTEGER);
            calStmt.registerOutParameter(11, Types.INTEGER);
            calStmt.registerOutParameter(12, Types.NVARCHAR);
            calStmt.execute();
            if (calStmt.getInt(11) != 1) {
                throw new Exception(calStmt.getString(12));
            }
            return calStmt.getInt(10);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
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
     * @param id_entity
     * @param iduser
     * @param idchanneluser
     * @param idchannel
     * @param password
     * @param lockflag
     * @param flagforcechanngepwd
     * @return
     * @throws Exception
     */
    public int insertChannelUser(String id_entity, String iduser, String idchanneluser, String idchannel, String password, String lockflag, String flagforcechanngepwd) throws Exception {
        PreparedStatement preStatement = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(INS_ETS_CHANNELUSER);
            preStatement.setString(1, id_entity);
            preStatement.setString(2, iduser);
            preStatement.setString(3, idchanneluser);
            preStatement.setString(4, idchannel);
            preStatement.setString(5, password);
            preStatement.setString(6, lockflag);
            preStatement.setString(7, flagforcechanngepwd);
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

    /**
     *
     * @param id_entity
     * @param iduser
     * @param idchannel
     * @param idrole
     * @return
     * @throws Exception
     */
    /*
    public int insertUserRole(String id_entity, String iduser, String idchannel, String idrole) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(id_entity);
        p_args.add(iduser);
        p_args.add(idchannel);
        p_args.add(idrole);
        return JDBCEngine.executeUpdate(INS_ETS_USERROLE, p_args, connection);
    }*/
    public int insertUserRole(String id_entity, String iduser, String idchannel, String idrole) throws Exception {
        PreparedStatement preStatement = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(INS_ETS_USERROLE);
            preStatement.setString(1, id_entity);
            preStatement.setString(2, iduser);
            preStatement.setString(3, idchannel);
            preStatement.setString(4, idrole);
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

    // =================== SMS ===================
    /**
     *
     * @param smsuserpin2
     * @return
     * @throws Exception
     */
    public int insertUserPin2(Sms_UserPin2 smsuserpin2) throws Exception {
        ArrayList<Object> p_del_args = new ArrayList<Object>();
        p_del_args.add(smsuserpin2.getId_entity());
        p_del_args.add(smsuserpin2.getIduser());
        p_del_args.add(smsuserpin2.getIdchanneluser());
        p_del_args.add(smsuserpin2.getIdchannel());
        JDBCEngine.executeUpdate(DELETE_USERPIN2, p_del_args, connection);
        ArrayList<Object> p_args = new ArrayList<Object>();
        p_args.add(smsuserpin2.getId_entity());
        p_args.add(smsuserpin2.getIduser());
        p_args.add(smsuserpin2.getIdchanneluser());
        p_args.add(smsuserpin2.getIdchannel());
        p_args.add(smsuserpin2.getUsertype());
        p_args.add(smsuserpin2.getPasswordtxn());
        p_args.add(smsuserpin2.getLengthpwd());
        p_args.add(smsuserpin2.getLockflag());
        p_args.add(smsuserpin2.getFlagforcechangetxnpwd());
        p_args.add(smsuserpin2.getMarkerid());
        p_args.add(smsuserpin2.getStatus());
        return JDBCEngine.executeUpdate(INSERT_USERPIN2, p_args, connection);
    }

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @return
     * @throws Exception
     */
    public ArrayList<?> getUserPin2(String idchanneluser, String usertype) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idchanneluser);
        p_args.add(usertype);
        return JDBCEngine.executeQuery(GET_USERPIN2, p_args, connection);
    }

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @return
     * @throws Exception
     */
    public int updAccessSucc_UserPin2(String idchanneluser, String usertype) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idchanneluser);
        p_args.add(usertype);
        return JDBCEngine.executeUpdate(UPD_UP2_SUCC, p_args, connection);
    }

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @return
     * @throws Exception
     */
    public int updAccessFail_UserPin2(String idchanneluser, String usertype) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idchanneluser);
        p_args.add(usertype);
        return JDBCEngine.executeUpdate(UPD_UP2_FAIL, p_args, connection);
    }

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @param pwdtxn
     * @param lenpwd
     * @param flagforcechangepwdtxn
     * @return
     * @throws Exception
     */
    public int changePwd_UserPin2(String idchanneluser, String usertype,
            String pwdtxn, int lenpwd, String flagforcechangepwdtxn) throws Exception {
        ArrayList<Object> p_args = new ArrayList<Object>();
        p_args.add(pwdtxn);
        p_args.add(lenpwd);
        p_args.add(flagforcechangepwdtxn);
        p_args.add(idchanneluser);
        p_args.add(usertype);
        return JDBCEngine.executeUpdate(UPD_PWD, p_args, connection);
    }

    /**
     *
     * @param idchanneluserOld
     * @param idchanneluserNew
     * @param usertype
     * @return
     * @throws Exception
     */
    public int changeIdChanneluser_UserPin2(String idchanneluserOld,
            String idchanneluserNew, String usertype) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idchanneluserNew);
        p_args.add(idchanneluserOld);
        p_args.add(usertype);
        return JDBCEngine.executeUpdate(UPD_UP2_IDCHANNELUSER, p_args,
                connection);
    }

    /**
     *
     * @param id_entity
     * @param iduser
     * @param idchanneluser
     * @param idchannel
     * @return
     * @throws Exception
     */
    public int deleteIdChanneluser_UserPin2(String id_entity, String iduser,
            String idchanneluser, String idchannel) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(id_entity);
        p_args.add(iduser);
        p_args.add(idchanneluser);
        p_args.add(idchannel);
        return JDBCEngine.executeUpdate(DELETE_USERPIN2, p_args, connection);
    }

    /**
     *
     * @param id_entity
     * @param idchannel
     * @param usertype
     * @param idchanneluser
     * @param typecmd
     * @param smsmessage
     * @param demonstring
     * @return
     * @throws Exception
     */
    public int insertWaitResponse(String id_entity, String idchannel, String usertype, String idchanneluser, String typecmd, String smsmessage, String demonstring) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(id_entity);
        p_args.add(idchannel);
        p_args.add(usertype);
        p_args.add(idchanneluser);
        p_args.add(typecmd);
        p_args.add(smsmessage);
        p_args.add(demonstring);
        return JDBCEngine.executeUpdate(INSERT_WAITRESPONSE, p_args, connection);
    }

    /**
     *
     * @param id_entity
     * @param idchannel
     * @param usertype
     * @param idchanneluser
     * @param typecmd
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAllListWaitResponse(String id_entity, String idchannel, String usertype, String idchanneluser, String typecmd) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(id_entity);
        p_args.add(idchannel);
        p_args.add(usertype);
        p_args.add(idchanneluser);
        p_args.add(typecmd);
        return JDBCEngine.executeQuery(GET_WAITRESPONSE, p_args, connection);
    }

    /**
     *
     * @param matinhthanh
     * @param quan
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAllListATM(String matinhthanh, String quan) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(matinhthanh);
        p_args.add(quan);
        return JDBCEngine.executeQuery(GET_ATMLOCATION, p_args, connection);
    }

    /**
     *
     * @param matinhthanh
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAllListATM_ext(String matinhthanh) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(matinhthanh);
        return JDBCEngine.executeQuery(GET_ATMLOCATION_EXT, p_args, connection);
    }

    /**
     *
     * @param matinhthanh
     * @param quan
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAllListBranch_main(String matinhthanh, String quan) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(matinhthanh);
        p_args.add(quan);
        return JDBCEngine.executeQuery(GET_BRANCHLOCATION_MAIN, p_args,
                connection);
    }

    /**
     *
     * @param matinhthanh
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAllListBranch_ext(String matinhthanh) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(matinhthanh);
        return JDBCEngine.executeQuery(GET_BRANCHLOCATION_EXT, p_args, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getAllListNHLM() throws Exception {
        return JDBCEngine.executeQuery(GET_NHLM, null, connection);
    }

    /**
     *
     * @param idchanneluser
     * @param typeuser
     * @param result
     * @param msglog
     * @param idmarker
     * @return
     * @throws Exception
     */
    public int insSmsLog(String idchanneluser, String typeuser, String result, String msglog, String idmarker) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + INS_SMS_LOG + " (?,?,?,?,?, ?,?); END;");
            calStmt.setString(1, idchanneluser);
            calStmt.setString(2, typeuser);
            calStmt.setString(3, result);
            calStmt.setString(4, msglog);
            calStmt.setString(5, idmarker);
            calStmt.registerOutParameter(6, Types.INTEGER);
            calStmt.registerOutParameter(7, Types.NVARCHAR);
            calStmt.execute();
            if (calStmt.getInt(6) != 1) {
                LOGGER.warn("insSmsLog --> calStmt.getString(7) = " + calStmt.getString(7));
                throw new Exception(calStmt.getString(7));
            }
            return calStmt.getInt(6);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /*CK Tan Viet (S)*/
    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getAllListPartnerFI() throws Exception {
        return JDBCEngine.executeQuery(GETLISTALL_PFIPARTNER, null, connection);
    }

    /**
     *
     * @param servicetype
     * @param branchcode
     * @return
     * @throws Exception
     */
    public ArrayList<?> getApproveTranferFI(String servicetype, String branchcode) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(servicetype);
        p_args.add(branchcode);
        return JDBCEngine.executeQuery(GETLIST_PFITRANF_APPROVE, p_args, connection);
    }

    /**
     *
     * @param fromDateSearch
     * @param toDateSearch
     * @return
     * @throws Exception
     */
    public ArrayList<?> getlistAllSI(String fromDateSearch, String toDateSearch) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(fromDateSearch);
        p_args.add(toDateSearch);
        return JDBCEngine.executeQuery(GETLIST_SI, p_args, connection);
    }

    /**
     *
     * @param si
     * @return
     */
    public int insertTrftToSI(SITrftToSIAuth si) {
        try {
            getSession().save(si);
            return si.getIdbillpaid();
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        }
    }

    /**
     *
     * @param partnerId
     * @return
     * @throws Exception
     */
    public String getPartnerNameById(String partnerId) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(partnerId);
        ArrayList list = JDBCEngine.executeQuery(GETNAME_PFIPARTNER, p_args, connection);
        if (!list.isEmpty()) {
            HashMap hm = (HashMap) list.get(0);
            return hm.get("name").toString();
        }
        return null;
    }

    /*CK Tan Viet (E)*/
    // ************* PBL ************
    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getAllListServiceType() throws Exception {
        return JDBCEngine.executeQuery(GETLISTALL_SERVICETYPE, null, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getAllListServiceTypeOfAutoPay() throws Exception {
        return JDBCEngine.executeQuery(GETLISTALL_SERVICETYPE_OF_AUTOPAY, null, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getAllListProvider() throws Exception {
        return JDBCEngine.executeQuery(GETLISTALL_PROVIDER, null, connection);
    }

    /**
     *
     * @param idservicetype
     * @param idprovider
     * @return
     * @throws Exception
     */
    public ArrayList<?> getPartnerServiceByPs(String idservicetype, String idprovider) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idservicetype);
        p_args.add(idprovider);
        return JDBCEngine.executeQuery(GET_PARTNERSERVICE_BY_PS, p_args, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getAllListPartnerService() throws Exception {
        return JDBCEngine.executeQuery(GETLISTALL_PARTNERSERVICE, null, connection);
    }

    /**
     *
     * @param idprovider
     * @param idservicetype
     * @return
     * @throws Exception
     */
    public ArrayList<?> getPartnerService(String idprovider, String idservicetype) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idprovider);
        p_args.add(idservicetype);
        return JDBCEngine.executeQuery(GET_PARTNERSERVICE, p_args, connection);
    }

    /**
     *
     * @param ppsid
     * @return
     * @throws Exception
     */
    public PblPartnerservice getPartnerServiceById(PblPartnerserviceId ppsid) throws Exception {
        return (PblPartnerservice) getSession().get(PblPartnerservice.class, ppsid);
    }

    /**
     *
     * @param idpartner
     * @param idservicetype
     * @param idprovider
     * @param channel
     * @param result
     * @param msglog
     * @param idmarker
     * @param description
     * @return
     * @throws Exception
     */
    public int insertPaybillLogDB(String idpartner, String idservicetype, String idprovider, String channel, String result, String msglog, String idmarker, String description) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + INSERT_SP_PBL_LOG + " (?,?,?,?,?,?,?,?, ?,?); END;");
            calStmt.setString(1, idpartner);
            calStmt.setString(2, idservicetype);
            calStmt.setString(3, idprovider);
            calStmt.setString(4, channel);
            calStmt.setString(5, result);
            calStmt.setString(6, msglog);
            calStmt.setString(7, idmarker);
            calStmt.setString(8, description);
            calStmt.registerOutParameter(9, Types.INTEGER);
            calStmt.registerOutParameter(10, Types.NVARCHAR);
            calStmt.execute();
            if (calStmt.getInt(9) < 0) {
                LOGGER.warn("insertPaybillLogDB --> calStmt.getString(10) = " + calStmt.getString(10));
                throw new Exception(calStmt.getString(10));
            }
            return calStmt.getInt(9);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
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
     * @param pb
     * @return
     */
    public int insertPaybillBillPaid(PblBillpaid pb) {
        try {
            getSession().save(pb);
            return pb.getIdbillpaid();
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        }
    }

    /**
     *
     * @param idbillpaid
     * @return
     * @throws Exception
     */
    public int deletePaybillBillPaid(String idbillpaid) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idbillpaid);
        return JDBCEngine.executeUpdate(DEL_PBL_BILLPAID, p_args, connection);
    }

    /**
     *
     * @param idbillpaid
     * @param idbill
     * @param idcustomer
     * @param custname
     * @param address
     * @param period
     * @param amount
     * @param result
     * @param description
     * @param tracenumber
     * @param markerid
     * @return
     * @throws Exception
     */
    public int insertPaybillBillPaidDetail(int idbillpaid, String idbill,
            String idcustomer, String custname, String address, String period,
            long amount, int result, String description, String tracenumber,
            String markerid) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(idbillpaid);
        p_args.add(idbill);
        p_args.add(idcustomer);
        p_args.add(custname);
        p_args.add(address);
        p_args.add(period);
        p_args.add(amount);
        p_args.add(result);
        p_args.add(description);
        p_args.add(tracenumber);
        p_args.add(markerid);
        return JDBCEngine.executeUpdate(INS_PBL_BILLPAID_DETAIL, p_args, connection);
    }

    /**
     *
     * @param idpartner
     * @param idservicetype
     * @return
     * @throws Exception
     */
    public ArrayList<?> getPaybillAttributes(String idpartner, String idservicetype) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idpartner);
        p_args.add(idservicetype);
        return JDBCEngine.executeQuery(GET_PBL_ATTRIBUTE, p_args, connection);
    }

    /**
     *
     * @param idbillpaid
     * @return
     * @throws Exception
     */
    public ArrayList<?> getPaybillBillPaid(String idbillpaid) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idbillpaid);
        return JDBCEngine.executeQuery(GET_PBL_BILLPAID, p_args, connection);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList<?> getPaybillInfoById(String id) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(id);
        return JDBCEngine.executeQuery(GET_PBL_BILLPAID_BY_ID, p_args, connection);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public PblBillpaid getPaybillBillPaidById(int id) throws Exception {
        return (PblBillpaid) getSession().get(PblBillpaid.class, id);
    }

    /**
     *
     * @param idbillpaid
     * @return
     * @throws Exception
     */
    public ArrayList<?> getPaybillBillPaidDetail(String idbillpaid) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idbillpaid);
        return JDBCEngine.executeQuery(GET_PBL_BILLPAID_DETAIL, p_args, connection);
    }

    /**
     *
     * @param ref_fcubs
     * @param idbillpaid
     * @return
     * @throws Exception
     */
    public int updRefFcubsByIdbillpaid(String ref_fcubs, String idbillpaid) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(ref_fcubs);
        p_args.add(idbillpaid);
        return JDBCEngine.executeUpdate(UPD_REFFUBS_BY_IDBILLPAID, p_args, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getSmsAlertNotify() throws Exception {
        return JDBCEngine.executeQuery(GET_ALERTNOTIFY, null, connection);
    }

    /**
     *
     * @param idmessage
     * @param status
     * @param msgnew
     * @return
     * @throws Exception
     */
    public int updSmsAlertNotifyByIdMessage(String idmessage, String status, String msgnew) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(status);
        p_args.add(msgnew);
        p_args.add(idmessage);
        return JDBCEngine.executeUpdate(UPD_ALERTNOTIFY, p_args, connection);
    }

    /**
     *
     * @param idchanneluser
     * @return
     * @throws Exception
     */
    public ArrayList<?> getUserByIdChannelUser(String idchanneluser) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idchanneluser);
        return JDBCEngine.executeQuery(GET_ETS_USER_BYIDCHANNELUSER, p_args, connection, EtsMstuser.class);
    }

    /**
     *
     * @param idchanneluser
     * @return
     * @throws Exception
     */
    public ArrayList<?> getChannelUserByIdChannelUser(String idchanneluser) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idchanneluser);
        return JDBCEngine.executeQuery(GET_ETS_CHANNELUSER_BYIDCHANNELUSER, p_args, connection, EtsMstchanneluser.class);
    }

    /**
     *
     * @param iduser
     * @param id_entity
     * @param idchannel
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListUserRoleByIduser(String iduser, String id_entity, String idchannel) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(iduser);
        p_args.add(id_entity);
        p_args.add(idchannel);
        ArrayList<?> listUserRole = JDBCEngine.executeQueryWithoutCloseConnection(GET_ETS_USERROLE_BYIDUSER, p_args, connection, EtsUserrole.class);

        ArrayList _listnew = new ArrayList();
        for (int i = 0; i < listUserRole.size(); i++) {
            EtsUserrole ur = (EtsUserrole) listUserRole.get(i);
            ArrayList<String> pRoleTxn = new ArrayList<String>();
            pRoleTxn.add(ur.getIdrole());
            ArrayList listRoleTxn = JDBCEngine.executeQueryWithoutCloseConnection(GET_ETS_ROLETXN_BYIDROLE, pRoleTxn, connection, EtsRoletxn.class);
            ur.setListEtsMstRoletxn(listRoleTxn);
            _listnew.add(ur);
        }
        return _listnew;
    }

    /**
     *
     * @param idchanneluser
     * @param firstname
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchUser(String idchanneluser, String firstname) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        String extend = "";
        if (idchanneluser != null && idchanneluser.trim().length() > 0) {
            p_args.add(idchanneluser);
            extend = " and cu.idchanneluser LIKE ?";
        }

        if (firstname != null && firstname.trim().length() > 0) {
            p_args.add(firstname);
            extend = " and u.firstname LIKE ?";
        }
        return JDBCEngine.executeQuery(SEARCH_ETS_USER + extend, p_args, connection);
    }

    /**
     *
     * @param accounttd
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchCustAlertTd(String accounttd) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(accounttd);
        return JDBCEngine.executeQuery(GET_ALERTACCOUNTTD_BY_CUST_AC_NO, p_args, connection);
    }

    /**
     * @param smscustalerttd
     * @return -3: Ton tai KH da dang ky voi voi CIF; -2: Ton tai KH da dang ky
     * voi TK.
     * @throws java.lang.Exception
     */
    public int insertAlertAccountTd(SmsCustAlertTd smscustalerttd) throws Exception {
        /* 1. Check co CIF & registerType dang ky TD chua, neu co, bao loi
        ArrayList p_args_check1 = new ArrayList();
        p_args_check1.add(smscustalerttd.getCust_no());
        p_args_check1.add(smscustalerttd.getRegistertype());
        p_args_check1.add(smscustalerttd.getRegistercatalogory());
        ArrayList list1 = JDBCEngine.executeQueryWithoutCloseConnection(GET_ALERTACCOUNTTD_BY_CUST_NO, p_args_check1, connection, null);
        if ((list1 != null) && (list1.size() > 0)) {
            return -3;
        } */

 /* 2. Check cust_ac_no with ACC */
        ArrayList p_args_check2 = new ArrayList();
        p_args_check2.add(smscustalerttd.getCust_no());
        p_args_check2.add(smscustalerttd.getCust_ac_no());
        ArrayList list2 = JDBCEngine.executeQueryWithoutCloseConnection(GET_ALERTACCOUNTTD_BY_CUST_AC_NO_WITH_ACC, p_args_check2, connection, null);
        if ((list2 != null) && (list2.size() > 0)) {
            return -2;
        }
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + INS_ALERT_ACCOUNTTD
                    + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;");
            calStmt.setString(1, smscustalerttd.getBranch_code());
            calStmt.setString(2, smscustalerttd.getCust_ac_no());
            calStmt.setString(3, smscustalerttd.getCust_no());
            calStmt.setString(4, smscustalerttd.getCcy());
            calStmt.setString(5, smscustalerttd.getAccount_class());
            calStmt.setString(6, smscustalerttd.getAc_open_date());
            calStmt.setString(7, smscustalerttd.getFullname());
            calStmt.setString(8, smscustalerttd.getAddress());
            calStmt.setString(9, smscustalerttd.getIdcard());
            calStmt.setString(10, smscustalerttd.getIdcard_loc());
            calStmt.setString(11, smscustalerttd.getIdcard_dob());
            calStmt.setString(12, smscustalerttd.getBank());
            calStmt.setString(13, smscustalerttd.getActive());
            calStmt.setString(14, smscustalerttd.getMobile());
            calStmt.setString(15, smscustalerttd.getMakerid());
            calStmt.setString(16, smscustalerttd.getRegistertype());
            calStmt.setString(17, smscustalerttd.getTk_denhan());
            calStmt.setString(18, smscustalerttd.getTk_momoi());
            calStmt.setString(19, smscustalerttd.getDescription());
            calStmt.setString(20, smscustalerttd.getPoint_reward());
            calStmt.registerOutParameter(21, Types.INTEGER);
            calStmt.registerOutParameter(22, Types.NVARCHAR);
            calStmt.execute();
            if (calStmt.getInt(21) != 1) {
                LOGGER.warn("insertAlertAccountTd --> calStmt.getString(22) = " + calStmt.getString(22));
                throw new Exception(calStmt.getString(22));
            }
            return calStmt.getInt(21);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
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
     * @param smscustalerttd
     * @return
     * @throws Exception
     */
    public int insertAlertAccountTd2(SmsCustAlertTd smscustalerttd) throws Exception {
        /* 1. Check co CIF & registerType dang ky TD chua, neu co, bao loi
        ArrayList p_args_check1 = new ArrayList();
        p_args_check1.add(smscustalerttd.getCust_no());
        p_args_check1.add(smscustalerttd.getRegistertype());
        p_args_check1.add(smscustalerttd.getRegistercatalogory());
        ArrayList list1 = JDBCEngine.executeQueryWithoutCloseConnection(GET_ALERTACCOUNTTD_BY_CUST_NO, p_args_check1, connection, null);
        if ((list1 != null) && (list1.size() > 0)) {
            return -3;
        } */

 /* 2. Check cust_ac_no with ACC */
        ArrayList p_args_check2 = new ArrayList();
        p_args_check2.add(smscustalerttd.getCust_no());
        p_args_check2.add(smscustalerttd.getCust_ac_no());
        ArrayList list2 = JDBCEngine.executeQueryWithoutCloseConnection(GET_ALERTACCOUNTTD_BY_CUST_AC_NO_WITH_ACC, p_args_check2, connection, null);
        if ((list2 != null) && (list2.size() > 0)) {
            return -2;
        }
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + INS_ALERT_ACCOUNTTD2
                    + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;");
            calStmt.setString(1, smscustalerttd.getBranch_code());
            calStmt.setString(2, smscustalerttd.getCust_ac_no());
            calStmt.setString(3, smscustalerttd.getCust_no());
            calStmt.setString(4, smscustalerttd.getCcy());
            calStmt.setString(5, smscustalerttd.getAccount_class());
            calStmt.setString(6, smscustalerttd.getAc_open_date());
            calStmt.setString(7, smscustalerttd.getFullname());
            calStmt.setString(8, smscustalerttd.getAddress());
            calStmt.setString(9, smscustalerttd.getIdcard());
            calStmt.setString(10, smscustalerttd.getIdcard_loc());
            calStmt.setString(11, smscustalerttd.getIdcard_dob());
            calStmt.setString(12, smscustalerttd.getBank());
            calStmt.setString(13, smscustalerttd.getActive());
            calStmt.setString(14, smscustalerttd.getMobile());
            calStmt.setString(15, smscustalerttd.getMakerid());
            calStmt.setString(16, smscustalerttd.getRegistertype());
            calStmt.setString(17, smscustalerttd.getTk_denhan());
            calStmt.setString(18, smscustalerttd.getTk_momoi());
            calStmt.setString(19, smscustalerttd.getDescription());
            calStmt.setString(20, smscustalerttd.getPoint_reward());
            calStmt.registerOutParameter(21, Types.INTEGER);
            calStmt.registerOutParameter(22, Types.NVARCHAR);
            calStmt.execute();
            if (calStmt.getInt(21) != 1) {
                LOGGER.warn("insertAlertAccountTd2 --> calStmt.getString(22) = " + calStmt.getString(22));
                throw new Exception(calStmt.getString(22));
            }
            return calStmt.getInt(21);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
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
     * @param accounttd
     * @param cif
     * @param idcard
     * @return
     * @throws Exception
     */
    public ArrayList<?> findListAccountTD(String accounttd, String cif,
            String idcard) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        String _s = "";// A.CUST_NO=? and A.CUST_AC_NO=? and D.UDF_1=?
        if (accounttd != null && accounttd.trim().length() > 0) {
            _s += " and A.CUST_AC_NO=?";
            p_args.add(accounttd);
        }
        if (idcard != null && idcard.trim().length() > 0) {
            _s += " and A.idcard=?";
            p_args.add(idcard);
        }
        if (cif != null && cif.trim().length() > 0) {
            _s += " and A.CUST_NO=?";
            p_args.add(cif);
        }
        if (_s.length() == 0) {
            return null;
        }
        return JDBCEngine.executeQuery(FIND_ACCOUNTTD + _s, p_args, connection);
    }

    /**
     *
     * @param accounttd
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountTDByAccountTd(String accounttd) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        String _s = "";
        if (accounttd != null && accounttd.trim().length() > 0) {
            _s += " and A.CUST_AC_NO=?";
            p_args.add(accounttd);
        }
        if (_s.length() == 0) {
            return null;
        }
        return JDBCEngine.executeQuery(FIND_ACCOUNTTD + _s, p_args, connection);
    }

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountTDById(SmsCustAlertTd smscustalerttd) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(smscustalerttd.getId());
        return JDBCEngine.executeQuery(GET_ALERTACCOUNTTD_BY_ID, p_args, connection);
    }

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws Exception
     */
    public int updateAccountTDMobileById(SmsCustAlertTd smscustalerttd) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        String query = "";
        if (smscustalerttd.getBranch_code_poi() == null) {
            p_args.add(smscustalerttd.getMobile());
            p_args.add(smscustalerttd.getModify_user());
            p_args.add(smscustalerttd.getTk_denhan());
            p_args.add(smscustalerttd.getTk_momoi());
            p_args.add(smscustalerttd.getBranch_code_mm());
            p_args.add(smscustalerttd.getId());
            query = UPD_ALERT_ACCOUNTTD_BY_MOBILE;
        } else {
            p_args.add(smscustalerttd.getMobile());
            p_args.add(smscustalerttd.getModify_user());
            p_args.add(smscustalerttd.getTk_denhan());
            p_args.add(smscustalerttd.getTk_momoi());
            p_args.add(smscustalerttd.getPoint_reward());
            p_args.add(smscustalerttd.getUser_poi_rew());
            p_args.add(smscustalerttd.getBranch_code_mm());
            p_args.add(smscustalerttd.getBranch_code_poi());
            p_args.add(smscustalerttd.getId());
            query = UPD_ALERT_POINT_REWARD;
        }
        return JDBCEngine.executeUpdate(query, p_args, connection);
    }

    /**
     *
     * @param cust_no
     * @param registertype
     * @return
     * @throws Exception
     */
    public ArrayList<?> findHistoryListAccountTD(String cust_no,
            String registertype) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(cust_no);
        p_args.add(registertype);
        return JDBCEngine.executeQuery(GET_ALERTACCOUNTTD_BY_CUST_NO, p_args, connection);
    }

    /**
     *
     * @param cust_no
     * @param registertype
     * @param idcard
     * @return
     * @throws Exception
     */
    public ArrayList<?> findHistoryListAccountTD(String cust_no,
            String registertype, String idcard) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        String _s = "";// A.CUST_NO=? and A.CUST_AC_NO=? and D.UDF_1=?
        //1. Register type
        p_args.add(registertype);
        //2. custno
        if (cust_no != null && cust_no.trim().length() > 0) {
            _s += " and t.CUST_NO=?";
            p_args.add(cust_no);
        }
        if (idcard != null && idcard.trim().length() > 0) {
            _s += " and t.idcard=?";
            p_args.add(idcard);
        }
        if (_s.length() == 0) {
            return null;
        }
        return JDBCEngine.executeQuery(GET_ALERTACCOUNTTD_BY_CUST_NO_IDCARD + _s, p_args, connection);
    }

    /**
     *
     * @param mstchanneluser
     * @return
     * @throws Exception
     */
    public int changePasswordEBES(EtsMstchanneluser mstchanneluser) throws Exception {
        PreparedStatement preStatement = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(UPD_ETS_PASS);
            preStatement.setString(1, mstchanneluser.getPassword());
            preStatement.setString(2, mstchanneluser.getLockflag());
            preStatement.setString(3, mstchanneluser.getFlagforcechangepwd());
            preStatement.setString(4, mstchanneluser.getId_entity());
            preStatement.setString(5, mstchanneluser.getIduser());
            preStatement.setString(6, mstchanneluser.getIdchanneluser());
            preStatement.setString(7, mstchanneluser.getIdchannel());
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

    /**
     *
     * @param idalert
     * @param mobile
     * @param message
     * @return
     * @throws Exception
     */
    public int insertSmsSenderLog(String idalert, String mobile, String message)
            throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + INS_SMS_SENDER_LOG + " (?,?,?, ?,?); END;");
            calStmt.setString(1, idalert);
            calStmt.setString(2, mobile);
            calStmt.setString(3, message);
            calStmt.registerOutParameter(4, Types.INTEGER);
            calStmt.registerOutParameter(5, Types.NVARCHAR);
            calStmt.execute();
            if (calStmt.getInt(4) != 1) {
                LOGGER.warn("insertSmsSenderLog --> calStmt.getString(5) = " + calStmt.getString(5));
                throw new Exception(calStmt.getString(5));
            }
            return calStmt.getInt(4);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
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
     * @param idservicetype
     * @return
     * @throws Exception
     */
    public ArrayList<?> getProviderByIdServiceType(String idservicetype) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idservicetype);
        return JDBCEngine.executeQuery(GET_PROVIDER_BY_IDSERVICETYPE, p_args, connection);
    }

    /**
     *
     * @param customercode
     * @param refpartner
     * @return
     * @throws Exception
     */
    public List<PblBillpaid> getPblBillPaidByRefPartner(String customercode, String refpartner) throws Exception {
        Criteria crit = getSession().createCriteria(PblBillpaid.class);
        crit.add(Restrictions.eq("customercode", customercode));
        crit.add(Restrictions.eq("refPartner", refpartner));
        crit.addOrder(Order.desc("insdate"));
        return crit.list();
    }

    //************* AUTO REG ******************
    /**
     *
     * @param pblautoreg
     * @return
     * @throws Exception
     */
    public List searchAutoReg(PblAutoReg pblautoreg) throws Exception {
        String s = "select r.* from PBL_AUTO_REG r, PBL_AUTO_REG_CONTRACT c where c.idcontract = r.idcontract ";
        if (pblautoreg.getPblAutoRegContract() != null && !Common.isEmpty(pblautoreg.getPblAutoRegContract().getIdcontract())) {
            s += " and r.idcontract = :idcontract";
        }
        if (pblautoreg.getPblServicetype() != null && !Common.isEmpty(pblautoreg.getPblServicetype().getIdservicetype())) {
            s += " and r.idservicetype = :idservicetype";
        }
        if (pblautoreg.getPblProvider() != null && !Common.isEmpty(pblautoreg.getPblProvider().getIdprovider())) {
            s += " and r.idprovider = :idprovider";
        }
        if (!Common.isEmpty(pblautoreg.getCustomercode())) {
            s += " and upper(r.customercode) = :customercode";
        }
        if (pblautoreg.getPblAutoRegContract() != null && !Common.isEmpty(pblautoreg.getPblAutoRegContract().getCustAccCif())) {
            s += " and c.cust_acc_cif = :cust_acc_cif";
        }
        if (pblautoreg.getPblAutoRegContract() != null && pblautoreg.getPblAutoRegContract().getActive() != null) {
            s += " and c.active = :active";
        }
        if (pblautoreg.getPblAutoRegContract() != null && !Common.isEmpty(pblautoreg.getPblAutoRegContract().getCustAccNo())) {
            s += " and c.cust_acc_no = :cust_acc_no";
        }
        if (pblautoreg.getPblAutoRegContract() != null && !Common.isEmpty(pblautoreg.getPblAutoRegContract().getCustAccIdcard())) {
            s += " and c.cust_acc_idcard = :cust_acc_idcard";
        }
        if (pblautoreg.getPblAutoRegContract() != null && !Common.isEmpty(pblautoreg.getPblAutoRegContract().getMobile())) {
            s += " and c.mobile = :mobile";
        }
        if (pblautoreg.getPblAutoRegContract() != null && pblautoreg.getPblAutoRegContract().getIsapproved() != null) {
            s += " and c.isapproved = :isapproved";
        }
        Query q = getSession().createSQLQuery(s).addEntity(PblAutoReg.class);
        if (pblautoreg.getPblAutoRegContract() != null && !Common.isEmpty(pblautoreg.getPblAutoRegContract().getIdcontract())) {
            q.setString("idcontract", pblautoreg.getPblAutoRegContract().getIdcontract());
        }
        if (pblautoreg.getPblServicetype() != null && !Common.isEmpty(pblautoreg.getPblServicetype().getIdservicetype())) {
            q.setString("idservicetype", pblautoreg.getPblServicetype().getIdservicetype());
        }
        if (pblautoreg.getPblProvider() != null && !Common.isEmpty(pblautoreg.getPblProvider().getIdprovider())) {
            q.setString("idprovider", pblautoreg.getPblProvider().getIdprovider());
        }
        if (pblautoreg.getPblAutoRegContract() != null && pblautoreg.getPblAutoRegContract().getActive() != null) {
            q.setString("active", pblautoreg.getPblAutoRegContract().getActive().toString());
        }
        if (pblautoreg.getPblAutoRegContract() != null && !Common.isEmpty(pblautoreg.getPblAutoRegContract().getCustAccCif())) {
            q.setString("cust_acc_cif", pblautoreg.getPblAutoRegContract().getCustAccCif());
        }
        if (!Common.isEmpty(pblautoreg.getCustomercode())) {
            q.setString("customercode", pblautoreg.getCustomercode());
        }
        if (pblautoreg.getPblAutoRegContract() != null && !Common.isEmpty(pblautoreg.getPblAutoRegContract().getCustAccNo())) {
            q.setString("cust_acc_no", pblautoreg.getPblAutoRegContract().getCustAccNo());
        }
        if (pblautoreg.getPblAutoRegContract() != null && !Common.isEmpty(pblautoreg.getPblAutoRegContract().getCustAccIdcard())) {
            q.setString("cust_acc_idcard", pblautoreg.getPblAutoRegContract().getCustAccIdcard());
        }
        if (pblautoreg.getPblAutoRegContract() != null && !Common.isEmpty(pblautoreg.getPblAutoRegContract().getMobile())) {
            q.setString("mobile", pblautoreg.getPblAutoRegContract().getMobile());
        }
        if (pblautoreg.getPblAutoRegContract() != null && pblautoreg.getPblAutoRegContract().getIsapproved() != null) {
            q.setCharacter("isapproved", pblautoreg.getPblAutoRegContract().getIsapproved());
        }
        return q.list();
    }

    /**
     *
     * @param vwautoreg
     * @return
     * @throws Exception
     */
    public List searchAutoReg(VwAutoReg vwautoreg) throws Exception {
        Criteria crit = getSession().createCriteria(VwAutoReg.class);
        Example ex = Example.create(vwautoreg);
        ex.enableLike();
        ex.excludeZeroes();
        ex.setPropertySelector(GenericHibernateBO.NOT_NULL_OR_EMPTY);
        ex.excludeProperty("scheduletype");
        if (!Common.isEmpty(vwautoreg.getIdservicetype())) {
            crit.add(Restrictions.eq("idservicetype", vwautoreg.getIdservicetype()));
        }
        if (!Common.isEmpty(vwautoreg.getIdprovider())) {
            crit.add(Restrictions.eq("idprovider", vwautoreg.getIdprovider()));
        }
        if (vwautoreg.getId() != 0) {
            crit.add(Restrictions.eq("id", vwautoreg.getId()));
        }
        List l = crit.add(ex).list();
        return l;
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public PblAutoReg searchAutoRegById(int id) throws Exception {
        return (PblAutoReg) getSession().get(PblAutoReg.class, id);
    }

    /**
     *
     * @return @throws Exception
     */
    public int getIdSeqAutoRegContract() throws Exception {
        SQLQuery q = getSession().createSQLQuery("select sq_pbl_auto_reg.nextval from dual");
        List l = q.list();
        return Integer.parseInt(l.get(0).toString());
    }

    //************* KET THUC AUTO REG ******************
    //************* AUTHORIZED *******************
    /**
     *
     * @param transcode
     * @param branchcode
     * @return
     * @throws Exception
     */
    public List getListTransActDetail(String transcode, String branchcode) throws Exception {
        Criteria crit = getSession().createCriteria(VwWaitapprove.class);
        crit.add(Restrictions.eq("id.txncode", transcode));
        crit.add(Restrictions.eq("branchCodeInit", branchcode));
        return crit.list();
    }

    //************* KET THUC AUTHORIZED *******************
    //************* SEARCH LICH SU GIAO DICH *******************
    /**
     *
     * @param date
     * @return
     */
    public List getListBillPaidByDate(String date) {
        return getSession().createSQLQuery(GETLIST_PBL_BILLPAID_BYDATE).addEntity(PblBillpaid.class).setString(0, date).list();
    }

    //************* KET THUC SEARCH LICH SU GIAO DICH *******************
    /**
     *
     * @param accountno
     * @return
     */
    public VwCustAccount getCustAccountByAccountNo(String accountno) {
        Criteria cre = getSession().createCriteria(VwCustAccount.class);
        List listcustacc = cre.add(Restrictions.eq("custAcNo", accountno)).list();
        if (listcustacc.isEmpty()) {
            return null;
        } else {
            return (VwCustAccount) listcustacc.get(0);
        }
    }

    public VwCustAccountNew getCustAccountByAccountNoNew(String accountno) {
        Criteria cre = getSession().createCriteria(VwCustAccountNew.class);
        List listcustacc = cre.add(Restrictions.eq("custAcNo", accountno)).list();
        if (listcustacc.isEmpty()) {
            return null;
        } else {
            return (VwCustAccountNew) listcustacc.get(0);
        }
    }

    /**
     *
     * @param vub
     * @return
     * @throws Exception
     */
    public List getUserBranch(VwUserbranch vub) throws Exception {
        Criteria crit = getSession().createCriteria(VwUserbranch.class);
        Example ex = Example.create(vub);
        ex.enableLike();
        ex.excludeZeroes();
        ex.setPropertySelector(GenericHibernateBO.NOT_NULL_OR_EMPTY);
        if (!Common.isEmpty(vub.getIduser())) {
            crit.add(Restrictions.eq("iduser", vub.getIduser()));
        }
        List l = crit.add(ex).list();
        return l;
    }

    /**
     *
     * @param date
     * @param idpartner
     * @return
     */
    public List getListCollatedByDate(String date, String idpartner) {
        SQLQuery q = getSession().createSQLQuery(GETLIST_PBL_COLLATED_BYDATE).addEntity(PblCollated.class);
        q.setString(0, date);
        q.setString(1, idpartner);
        return q.list();
    }

    /**
     *
     * @param transcode
     * @return
     * @throws Exception
     */
    public PblEbkProcess getEbkProcessById(int transcode) throws Exception {
        return (PblEbkProcess) getSession().get(PblEbkProcess.class, transcode);
    }

    /**
     *
     * @param idscreen
     * @return
     * @throws Exception
     */
    public PblEbkScreen getEbkScreenById(int idscreen) throws Exception {
        return (PblEbkScreen) getSession().get(PblEbkScreen.class, idscreen);
    }

    /**
     *
     * @param iduser
     * @return
     * @throws Exception
     */
    public VwMstchanneluser getVwMstchanneluser(String iduser) throws Exception {
        return (VwMstchanneluser) getSession().get(VwMstchanneluser.class, iduser);
    }

    /**
     *
     * @param idscreen
     * @return
     * @throws Exception
     */
    public PblEbkScreen getEbkScreenById_new(PblEbkScreenId idscreen) throws Exception {
        return (PblEbkScreen) getSession().get(PblEbkScreen.class, idscreen);
    }

    /**
     *
     * @param pIduser
     * @param pCust_ac_no
     * @return
     */
    public int checkIduserAndCust_ac_no(String pIduser, String pCust_ac_no) {
        Query query = getSession().createSQLQuery(SQL_CHECK_USERANDACCOUNT);
        query.setParameter("piduser", pIduser);
        query.setParameter("pcust_ac_no", pCust_ac_no);
        final BigDecimal nr = (BigDecimal) query.uniqueResult();
        return nr.intValue();
    }

    /**
     *
     * @param ipaddress
     * @param pIduser
     * @param pIdsession
     * @return
     * @throws Exception
     */
    public int checkusersession_valid(String ipaddress, String pIduser, String pIdsession) throws Exception {
        CallableStatement call = null;
        try {
            call = (CallableStatement) connection.prepareCall("BEGIN " + "?:= SMS_SCB.fn_check_sessionuser(?,?,?);END;");
            call.registerOutParameter(1, Types.INTEGER); // or whatever it is
            call.setString(2, ipaddress);
            call.setString(3, pIduser);
            call.setString(4, pIdsession);
            call.execute();
            int result = call.getInt(1); // propagate this back to enclosing class
            return result;
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
        } finally {
            if (call != null) {
                call.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param idUser
     * @param udfName
     * @return
     */
    public String getUdfValue_User(String idUser, String udfName) {
        Query query = getSession().createSQLQuery(SQL_GET_UDFVALUE_USER);
        query.setParameter("piduser", idUser);
        query.setParameter("pudfname", udfName);
        String nr = (String) query.uniqueResult();
        return nr;
    }

    /**
     *
     * @param pIdsession
     * @return
     */
    public String getsession_lang(String pIdsession) {
        Query query = getSession().createSQLQuery(SQL_GET_SESSION_LANG);
        query.setParameter("pidsession", pIdsession);
        String nr = (String) query.uniqueResult();
        return nr;
    }

    /**
     *
     * @param idUser
     * @return
     */
    public String GetTypeConfirm_User(String idUser) {
        Query query = getSession().createSQLQuery(SQL_GET_TYPECONFIRMUSER);
        query.setParameter("piduser", idUser);
        String nr = (String) query.uniqueResult();
        return nr;

    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getPblPartnerservices() throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        return JDBCEngine.executeQuery(SQL_GET_LIST_PARTNER, null, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getPblEbkScreens() throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        return JDBCEngine.executeQuery(SQL_GET_LIST_SCREEN, null, connection);
    }

    /**
     *
     * @param table
     * @param field
     * @param idkey
     * @return
     */
    public String getmsglog(String table, String field, String idkey) {
        Query query = getSession().createSQLQuery(SQL_GET_MSGLOG);
        query.setParameter("pTable", table);
        query.setParameter("pIdfield", field);
        query.setParameter("pIdvalue", idkey);
        String nr = (String) query.uniqueResult();
        return nr;
    }

    /**
     *
     * @param iduser
     * @return
     */
    public String getuserphonenumber(String iduser) {
        Query query = getSession().createSQLQuery(SQL_GET_USERPHONENUMBER);
        query.setParameter("PIDUSER", iduser);
        String nr = (String) query.uniqueResult();
        return nr;
    }

    //HIBERNATE ho tro rat kem STORE PROCEDURE, may thay, co connection JDBC.
    /**
     *
     * @param idcontract
     * @return
     * @throws Exception
     */
    public int execApproveAutoReg(String idcontract) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN EXC_APPROVE_PBL_AUTOREG(?, ?,?); END;");
            calStmt.setString(1, idcontract);
            calStmt.registerOutParameter(2, Types.INTEGER);
            calStmt.registerOutParameter(3, Types.VARCHAR);
            calStmt.execute();
            if (calStmt.getInt(2) < 0) {
                LOGGER.warn("execApproveAutoReg --> calStmt.getString(3) = " + calStmt.getString(3));
                throw new Exception(calStmt.getString(3));
            }
            return calStmt.getInt(2);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
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
     * @return @throws Exception
     */
    public ArrayList<?> getListUserToNeedChgPwd() throws Exception {
        return JDBCEngine.executeQuery(GETLIST_USER_NEEDCHGPWD, null, connection);
    }

    /**
     *
     * @param cif
     * @return
     * @throws Exception
     */
    public ArrayList getBranchInfoByCif(String cif) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(cif);
        return JDBCEngine.executeQuery(GETBRANCHINFO_BY_CIF, p_args, connection);
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getBranchByAccno(String accountno) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(accountno);
        return JDBCEngine.executeQuery(GETBRANCHBYACCNO, p_args, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getGwPermissions() throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        //p_args.add(accountno);
        return JDBCEngine.executeQuery(SQL_GET_PERMISSIONS, p_args, connection);
    }

    /**
     *
     * @param idcontract
     * @return
     * @throws Exception
     */
    public boolean isExsistContractByIdContract(String idcontract) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(idcontract);
        ArrayList l = JDBCEngine.executeQuery(ISEXSISTCONTRACTBYIDCONTRACT, p_args, connection);
        if (l.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param p_entity
     * @param p_channel_id
     * @param p_txn_id
     * @param p_iduser
     * @param p_typeuser
     * @param p_txn_date
     * @param p_amount
     * @param p_ccy
     * @param p_cum
     * @return
     * @throws Exception
     */
    public boolean check_trans_limit(String p_entity, String p_channel_id, String p_txn_id, String p_iduser, String p_typeuser, java.util.Date p_txn_date, String p_amount, String p_ccy, int p_cum) throws Exception {
        CallableStatement call = null;
        try {
            call = (CallableStatement) connection.prepareCall("BEGIN " + "?:= SMS_SCB.check_trans_limit(?,?,?,?,?,?,?,?,?) ;END;");
            call.registerOutParameter(1, Types.INTEGER); // or whatever it is
            call.setString(2, p_entity);
            call.setString(3, p_channel_id);
            call.setString(4, p_txn_id);
            call.setString(5, p_iduser);
            call.setString(6, p_typeuser);
            call.setDate(7, new java.sql.Date(p_txn_date.getTime()));
            call.setInt(8, Integer.parseInt(p_amount));
            call.setString(9, p_ccy);
            call.setInt(10, p_cum);
            call.execute();
            int result = call.getInt(1); // propagate this back to enclosing class
            return result == 1;
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
        } finally {
            if (call != null) {
                call.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param iduser
     * @param idservicetype
     * @param idprovider
     * @return
     * @throws Exception
     */
    public List getPblCustTemplates(String iduser, String idservicetype, String idprovider) throws Exception {
        Criteria crit = getSession().createCriteria(PblCustTemplate.class);
        crit.add(Restrictions.eq("iduser", iduser));
        crit.addOrder(Order.asc("idservicetype"));
        return crit.list();
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public PblCustTemplate getPblCustTemplate(int id) throws Exception {
        return (PblCustTemplate) getSession().get(PblCustTemplate.class, id);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getlist_telefirstnumber() throws Exception {
        return JDBCEngine.executeQuery(SQL_GET_LIST_TETEFIRSTNUMBER, null, connection);
    }

    /**
     *
     * @param vgroup_id
     * @return
     * @throws Exception
     */
    public ArrayList getlist_parainfo(String vgroup_id) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(vgroup_id);
        return JDBCEngine.executeQuery(SQL_GET_LIST_PARAINFO, p_args, connection);
    }

    /**
     * *****************************SEND
     * SMS*************************************
     */
    /**
     * Tim danh sach cac mau tin chua duoc goi den khach hang
     *
     * @param vstatus
     * @param numtrysend
     * @return
     * @throws java.lang.Exception
     */
    public List findsmssenders(char vstatus, long numtrysend) throws Exception {
        Criteria crit = getSession().createCriteria(SmsSender.class);
        crit.add(Restrictions.eq("messagestat", vstatus));
        crit.add(Restrictions.le("nbrRetry", numtrysend)); //nho hon hoac bang so lan truyen vao
        crit.addOrder(Order.asc("senddate"));
        return crit.list();
    }

    /**
     * ******************************************************************
     */
    /**
     * BEGIN OF ********NHAN TIN SMS TIET KIEM TICH
     * LUY************************************* DATE: 19/09/2013
     *
     * @param accounttichluy
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<?> searchCustAlertTichLuy(String accounttichluy) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(accounttichluy);
        return JDBCEngine.executeQuery(GET_ALERTACCOUNTTICHLUY_BY_CUST_AC_NO, p_args, connection);
    }

    /**
     * @param smscustalerttichluy
     * @return -3: Ton tai KH da dang ky voi voi CIF; -2: Ton tai KH da dang ky
     * voi TK.
     * @throws java.lang.Exception
     */
    public int insertAlertAccountTichLuy(SmsCustAlertTichLuy smscustalerttichluy) throws Exception {
        // 1. Check co CIF & registerType dang ky TD chua, neu co, bao loi
        ArrayList p_args_check1 = new ArrayList();
        p_args_check1.add(smscustalerttichluy.getCust_no());
        p_args_check1.add(smscustalerttichluy.getRegistertype());
        ArrayList list1 = JDBCEngine.executeQueryWithoutCloseConnection(GET_ALERTACCOUNTTICHLUY_BY_CUST_NO, p_args_check1, connection, null);
        if ((list1 != null) && (list1.size() > 0)) {
            return -3;
        }
        // 2. Check cust_ac_no with ACC
        ArrayList p_args_check2 = new ArrayList();
        p_args_check2.add(smscustalerttichluy.getCust_no());
        p_args_check2.add(smscustalerttichluy.getCust_ac_no());
        ArrayList list2 = JDBCEngine.executeQueryWithoutCloseConnection(GET_ALERTACCOUNTTICHLUY_BY_CUST_AC_NO_WITH_ACC, p_args_check2, connection, null);
        if ((list2 != null) && (list2.size() > 0)) {
            return -2;
        }
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + INS_ALERT_ACCOUNTTICHLUY
                    + " (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?, ?,?); END;");
            calStmt.setString(1, smscustalerttichluy.getBranch_code());
            calStmt.setString(2, smscustalerttichluy.getCust_ac_no());
            calStmt.setString(3, smscustalerttichluy.getCust_no());
            calStmt.setString(4, smscustalerttichluy.getCcy());
            calStmt.setString(5, smscustalerttichluy.getAccount_class());
            calStmt.setString(6, smscustalerttichluy.getAc_open_date());
            calStmt.setString(7, smscustalerttichluy.getFullname());
            calStmt.setString(8, smscustalerttichluy.getAddress());
            calStmt.setString(9, smscustalerttichluy.getIdcard());
            calStmt.setString(10, smscustalerttichluy.getIdcard_loc());
            calStmt.setString(11, smscustalerttichluy.getIdcard_dob());
            calStmt.setString(12, smscustalerttichluy.getBank());
            calStmt.setString(13, smscustalerttichluy.getActive());
            calStmt.setString(14, smscustalerttichluy.getMobile());
            calStmt.setString(15, smscustalerttichluy.getMakerid());
            calStmt.setString(16, smscustalerttichluy.getRegistertype());
            calStmt.setString(17, smscustalerttichluy.getDescription());
            calStmt.registerOutParameter(18, Types.INTEGER);
            calStmt.registerOutParameter(19, Types.NVARCHAR);
            calStmt.execute();

            if (calStmt.getInt(18) != 1) {
                LOGGER.warn("insertAlertAccountTichLuy --> calStmt.getString(19) = " + calStmt.getString(19));
                throw new Exception(calStmt.getString(19));
            }
            return calStmt.getInt(18);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
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
     * @param accounttichluy
     * @param cif
     * @param idcard
     * @return
     * @throws Exception
     */
    public ArrayList<?> findListAccountTichLuy(String accounttichluy, String cif,
            String idcard) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        String _s = "";// A.CUST_NO=? and A.CUST_AC_NO=? and D.UDF_1=?
        if (accounttichluy != null && accounttichluy.trim().length() > 0) {
            _s += " and A.CUST_AC_NO=?";
            p_args.add(accounttichluy);
        }
        if (idcard != null && idcard.trim().length() > 0) {
            _s += " and A.idcard=?";
            p_args.add(idcard);
        }
        if (cif != null && cif.trim().length() > 0) {
            _s += " and A.CUST_NO=?";
            p_args.add(cif);
        }
        if (_s.length() == 0) {
            return null;
        }
        return JDBCEngine.executeQuery(FIND_ACCOUNTTICHLUY + _s, p_args, connection);
    }

    /**
     *
     * @param accounttichluy
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountTDByAccountTichLuy(String accounttichluy) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        String _s = "";
        if (accounttichluy != null && accounttichluy.trim().length() > 0) {
            _s += " and A.CUST_AC_NO=?";
            p_args.add(accounttichluy);
        }
        if (_s.length() == 0) {
            return null;
        }
        return JDBCEngine.executeQuery(FIND_ACCOUNTTICHLUY + _s, p_args, connection);
    }

    /**
     *
     * @param smscustalerttichluy
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountTichLuyById(SmsCustAlertTichLuy smscustalerttichluy) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(smscustalerttichluy.getId());
        return JDBCEngine.executeQuery(GET_ALERTACCOUNTTICHLUY_BY_ID, p_args, connection);
    }

    /**
     *
     * @param smscustalerttichluy
     * @return
     * @throws Exception
     */
    public int updateAccountTichLuyMobileById(SmsCustAlertTichLuy smscustalerttichluy) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(smscustalerttichluy.getMobile());
        p_args.add(smscustalerttichluy.getId());
        return JDBCEngine.executeUpdate(UPD_ALERT_ACCOUNTTICHLUY_BY_MOBILE, p_args, connection);
    }

    /**
     *
     * @param cust_no
     * @param registertype
     * @return
     * @throws Exception
     */
    public ArrayList<?> findHistoryListAccountTichLuy(String cust_no,
            String registertype) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(cust_no);
        p_args.add(registertype);
        return JDBCEngine.executeQuery(GET_ALERTACCOUNTTICHLUY_BY_CUST_NO, p_args, connection);
    }

    /**
     * END OF ********NHAN TIN SMS TIET KIEM TICH
     * LUY************************************* DATE: 19/09/2013
     */
    /**
     * BEGIN OF ********PAYOO PAYMENT************************************* DATE:
     * 17/10/2013
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @param billInfo
     * @return
     * @throws java.lang.Exception
     */
    public int insertPbl_PayooLog(String customercode, String serviceID, String providerID,
            String billInfo) throws Exception {
        PreparedStatement preStatement = null;
        try {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(INS_PBL_PAYOOLOG);
            preStatement.setString(1, customercode);
            preStatement.setString(2, serviceID);
            preStatement.setString(3, providerID);
            preStatement.setString(4, billInfo);
            return preStatement.executeUpdate() > 0 ? 1 : 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
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

    /**
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @return
     * @throws Exception
     */
    public ArrayList<?> getPbl_PayooLog(String customercode, String serviceID, String providerID) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(customercode);
        p_args.add(serviceID);
        p_args.add(providerID);
        return JDBCEngine.executeQuery(GET_PBL_PAYOOLOG_BYLATEST, p_args, connection);
    }

    /**
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @return
     * @throws Exception
     */
    public ArrayList<?> getPbl_PayooLogPrint(String customercode, String serviceID, String providerID) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(customercode);
        p_args.add(serviceID);
        p_args.add(providerID);
        return JDBCEngine.executeQuery(GET_PBL_PAYOOLOG_BYLATEST_PRINT, p_args, connection);
    }

    /**
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @return
     * @throws Exception
     */
    public String getBillInfo(String customercode, String serviceID, String providerID) throws Exception {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(GET_LASTESTBILLINFO);
            preStatement.setString(1, customercode);
            preStatement.setString(2, serviceID);
            preStatement.setString(3, providerID);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                return rs.getString("BILLINFO");
            }
            LOGGER.warn("CUSTOMERCODE = [" + customercode
                    + "}, SERVICECODE = [" + serviceID + "], PROVIDERCODE = ["
                    + providerID + "] DOES NOT FOUND IN TABLE PAYOO LOG");
            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
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

    /**
     *
     * @param seqname
     * @return
     * @throws Exception
     */
    public int getIdSeqByName(String seqname) throws Exception {
        String query = "select " + seqname + ".nextval from dual";
        SQLQuery q = getSession().createSQLQuery(query);
        List l = q.list();
        return Integer.parseInt(l.get(0).toString());
    }

    /**
     *
     * @param seqname
     * @return
     * @throws Exception
     */
    public int getIdSeqByName_2(String seqname) throws Exception {
        String query = "select " + seqname + ".nextval seqnum from dual";
        ArrayList<String> p_args = new ArrayList<String>();
        ArrayList List = JDBCEngine.executeQuery(query, p_args, connection);
        HashMap<?, ?> HM = (HashMap<?, ?>) List.get(0);
        return Integer.parseInt(HM.get("seqnum").toString());
    }

    /**
     * END OF ********PAYOO PAYMENT************************************* DATE:
     * 17/10/2013
     *
     * @param Trace_No
     * @param Order_No
     * @param Status
     * @param PartnerDatetime
     * @param PayooDatetime
     * @param Amount
     * @param TypeOfTrans
     * @param Note
     * @param CheckSum
     * @return
     * @throws java.sql.SQLException
     */
    //PAYOO COLLATE
    //UPDATE BY: HIEUDT         
    //ReceivePayooCollateData          
    public int insert_PayooCollateData(String Trace_No, String Order_No, BigDecimal Amount,
            Date PartnerDatetime, Date PayooDatetime, String TypeOfTrans, String Status,
            String Note, String CheckSum) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + INS_PAYOOCOLLATE + " (?,?,?,?,?,?,?,?,?,?,?,?); END;");
            calStmt.setString(1, Note.split("-")[1]);
            calStmt.setString(2, Trace_No);
            calStmt.setString(3, Order_No);
            calStmt.setBigDecimal(4, Amount);
            calStmt.setTimestamp(5, new java.sql.Timestamp(PartnerDatetime.getTime()));
            calStmt.setTimestamp(6, new java.sql.Timestamp(PayooDatetime.getTime()));
            calStmt.setString(7, TypeOfTrans);
            calStmt.setString(8, Status);
            calStmt.setString(9, Note);
            calStmt.setString(10, CheckSum);
            calStmt.registerOutParameter(11, Types.INTEGER);
            calStmt.registerOutParameter(12, Types.NVARCHAR);
            calStmt.execute();
            return calStmt.getInt(11);
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
     * @param date
     * @return
     * @throws SQLException
     */
    public int CollatePayooBillData(String date) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + COLLATE_PAYOOBILL_DATA + " (?); END;");
            calStmt.setString(1, date);
            calStmt.execute();
            return 1;
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
     * @param date
     * @return
     * @throws Exception
     */
    public boolean isExsistPayooBillCollateData(String date) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(date);
        ArrayList l = JDBCEngine.executeQuery(ISEXSISTPAYOOBILLDATACOLLATED, p_args, connection);
        return !l.isEmpty();
    }

    final String PROC_INSERT_VNPAY_COLLATED = "BEGIN PROC_INSERT_VNPAY_COLLATED(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String PROC_UPDATE_AFTER_COLLATED = "BEGIN PROC_UPDATE_AFTER_COLLATED(?,?); END;";
    final String INSERT_DATE_COLLATED = " insert into PBL_VNPAY_DATE_COLLATED values(?,?)";
    final String SELECT_DATE_COLLATED = " select to_char(max(C.DATECOLLATED),'dd/MM/yyyy') maxdate from PBL_VNPAY_DATE_COLLATED C";

    /**
     *
     * @param o
     * @throws SQLException
     */
    public void InsertPBL_VNPAY_COLLATED(PblCollated_VNPAY o) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(PROC_INSERT_VNPAY_COLLATED);
        try {
            calStmt.setString(1, o.getACCOUNTNO());
            calStmt.setString(2, o.getTYETRANS());
            calStmt.setString(3, o.getAMOUNT());
            calStmt.setString(4, o.getTRANSID());
            calStmt.setString(5, o.getTRANSTIME());
            calStmt.setString(6, o.getTRANSDATE());
            calStmt.setString(7, o.getPAYDATE());
            calStmt.setString(8, o.getDEVICETYPE());
            calStmt.setString(9, o.getBANKTRANSID());
            calStmt.setString(10, o.getRESPONSECODE());
            calStmt.setString(11, o.getNOACCEPTCARD());
            calStmt.setString(12, o.getADDINFO());
            calStmt.setString(13, o.getIdpartner());
            calStmt.setString(14, o.getCUSTOMERCODE());
            java.sql.Date sqlDate = new java.sql.Date(o.getTRANSDATEFULL().getTime());
            calStmt.setDate(15, sqlDate);
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

    //Update after collated VNPAY
    /**
     *
     * @param pParnertid
     * @param pTransDate
     * @throws SQLException
     */
    public void UPDATE_AFTER_COLLATED(String pParnertid, Date pTransDate) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(PROC_UPDATE_AFTER_COLLATED);
        try {
            calStmt.setString(1, pParnertid);
            java.sql.Date sqlDate = new java.sql.Date(pTransDate.getTime());
            calStmt.setDate(2, sqlDate);
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

    // Insert date collated
    /**
     *
     * @param datecollate
     * @param filename
     * @throws SQLException
     */
    public void INSERT_DATE_COLLATED(Date datecollate, String filename) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(INSERT_DATE_COLLATED);
        try {
            java.sql.Date sqlDate = new java.sql.Date(datecollate.getTime());
            calStmt.setDate(1, sqlDate);
            calStmt.setString(2, filename);
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
     * @return @throws Exception
     */
    public Date GET_VNPAY_COLLATEDATE() throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        ArrayList List = JDBCEngine.executeQuery(SELECT_DATE_COLLATED, p_args, connection);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        HashMap<?, ?> HMDate = (HashMap<?, ?>) List.get(0);
        Date maxdate = df.parse(HMDate.get("maxdate").toString());
        return maxdate;
    }

    //END OF VNPay Collated by Yen Ly
    /**
     * **********Mobile banking
     *
     ***************
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<?> getAllListServiceTypeNotTopup() throws Exception {
        return JDBCEngine.executeQuery(GETLISTALL_SERVICETYPE_NOTOPUP, null, connection);
    }

    /**
     * *******update status for ebanking mo rong
     *
     *
     * @param smscustalerttd
     * @return
     * @throws java.lang.Exception
     */
    public int updateAccountAlertStatusById(SmsCustAlertTd smscustalerttd) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(smscustalerttd.getActive());
        p_args.add(smscustalerttd.getModify_user());
        p_args.add(smscustalerttd.getId());
        return JDBCEngine.executeUpdate(UPD_ALERT_ACCOUNTTD_BY_ACTIVE, p_args, connection);
    }

    /**
     *
     * @param customercode
     * @param idchannel
     * @param idservicetype
     * @param statusBill
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param idpartner
     * @param idtransaction
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchPaybill(String customercode, String idchannel, String idservicetype, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction, String beginRow, String endRow) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (idtransaction.equals("") || idtransaction.equals(null)) {
            p_args.add("%" + idtransaction + "%");
        } else {
            p_args.add(idtransaction);
        }
        p_args.add(idpartner);
        p_args.add(idpartner);
        p_args.add(branchcode);
        p_args.add(branchcode);
        p_args.add("%" + customercode + "%");
        p_args.add(idchannel);
        p_args.add(idchannel);
        p_args.add(idservicetype);
        p_args.add(idservicetype);
        p_args.add(statusBill);
        p_args.add(statusBill);
        p_args.add(beginRow);
        p_args.add(endRow);
        return JDBCEngine.executeQuery(SEARCH_ALL_BILLPAID, p_args, connection);
    }

    /**
     *
     * @param customercode
     * @param idchannel
     * @param idservicetype
     * @param statusBill
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param idpartner
     * @param idtransaction
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchPaybillAll(String customercode, String idchannel, String idservicetype, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (idtransaction.equals("") || idtransaction.equals(null)) {
            p_args.add("%" + idtransaction + "%");
        } else {
            p_args.add(idtransaction);
        }
        p_args.add(idpartner);
        p_args.add(idpartner);
        p_args.add(branchcode);
        p_args.add(branchcode);
        p_args.add("%" + customercode + "%");
        p_args.add(idchannel);
        p_args.add(idchannel);
        p_args.add(idservicetype);
        p_args.add(idservicetype);
        p_args.add(statusBill);
        p_args.add(statusBill);
        return JDBCEngine.executeQuery(SEARCH_ALL_BILLPAID_SUM, p_args, connection);
    }

    /**
     *
     * @param channel
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchPaybillDiary(String channel, String fromdate, String todate) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        p_args.add("%" + channel + "%");
        return JDBCEngine.executeQuery(SEARCH_ALL_BILLPAID_DIARY, p_args, connection);
    }

    /**
     *
     * @param txnType
     * @param idlist
     * @param status
     * @param userid
     * @param idChanneluser
     * @return
     * @throws Exception
     */
    public int updateStatusPaybill(String txnType, String idlist, String status, String userid, String idChanneluser) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + UPD_STATUS_PAYBILL + " (?,?,?,?,?,?,?); END;");
            calStmt.setString(1, txnType);
            calStmt.setString(2, idlist);
            calStmt.setString(3, status);
            calStmt.setString(4, userid);
            calStmt.setString(5, idChanneluser);
            calStmt.registerOutParameter(6, Types.INTEGER);
            calStmt.registerOutParameter(7, Types.NVARCHAR);
            calStmt.execute();
            if (calStmt.getInt(6) != 1) {
                LOGGER.warn("updateStatusPaybill --> calStmt.getInt(6) != 1");
                throw new Exception(calStmt.getString(7));
            }
            return calStmt.getInt(6);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
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
     * @param partner
     * @param customercode
     * @param statusBill
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param idpartner
     * @param idtransaction
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchOnlBill(String id, String partner, String customercode, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction, String beginRow, String endRow) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (id.equals("") || id.equals(null)) {
            p_args.add("%" + id + "%");
        } else {
            p_args.add("" + id + "");
        }
        p_args.add("%" + idtransaction + "%");
        p_args.add("%" + idpartner + "%");
        p_args.add("%" + customercode + "%");
        p_args.add("%" + statusBill + "%");
        //p_args.add("%" + branchcode + "%");
        p_args.add(beginRow);
        p_args.add(endRow);
        return JDBCEngine.executeQuery(SEARCH_ALL_ONLBILL, p_args, connection);
    }

    /**
     *
     * @param id
     * @param partner
     * @param customercode
     * @param statusBill
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param idpartner
     * @param idtransaction
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchOnlBillAll(String id, String partner, String customercode, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (id == null || id.equals("")) {
            p_args.add("%" + id + "%");
        } else {
            p_args.add("" + id + "");
        }
        p_args.add("%" + idtransaction + "%");
        p_args.add("%" + idpartner + "%");
        p_args.add("%" + customercode + "%");
        p_args.add("%" + statusBill + "%");
        return JDBCEngine.executeQuery(SEARCH_ALL_ONLBILL_SUM, p_args, connection);
    }

    //duytxa08072015. su dung de huy dich vu sms khi no phi
    /**
     *
     * @param mobile
     * @param custno
     * @return
     * @throws Exception
     */
    public int lockSMS(String mobile, String custno) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + LOCKSMS + " (?,?, ?,?); END;");

            calStmt.setString(1, mobile);
            calStmt.setString(2, custno);
            calStmt.registerOutParameter(3, Types.INTEGER);
            calStmt.registerOutParameter(4, Types.NVARCHAR);
            calStmt.execute();

            if (calStmt.getInt(3) == -1) {
                LOGGER.warn("lockSMS --> calStmt.getInt(3) == -1");
                throw new Exception(calStmt.getString(4));
            }
            return calStmt.getInt(3);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    //end duytxa08072015
    //duytxa28062017. su dung de huy dich vu ebanking khi no phi

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public int lockEbankService(String custno) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + USER_LOCK_THUPHI + " (?, ?); END;");
            calStmt.setString(1, custno);
            calStmt.registerOutParameter(2, Types.INTEGER);
            calStmt.execute();
            if (calStmt.getInt(2) == -1) {
                LOGGER.warn("lockEbankService --> calStmt.getInt(2) == -1");
                throw new Exception(calStmt.getString(2));
            }
            return calStmt.getInt(2);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    //lay user mobile
    /**
     *
     * @param custno
     * @param thangnam
     * @return
     * @throws Exception
     */
    public ArrayList<?> getmobileuserthuphisms(String custno, String thangnam) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(custno);
        p_args.add(thangnam);
        return JDBCEngine.executeQuery(GET_USER_THUPHISMS, p_args, connection);
    }

    //end duytxa28062017
    //Cardword WS       
    /**
     *
     * @param ID
     * @param SRCACCOUNT
     * @param PAN_LOC
     * @param CARD_BRN
     * @param CCY
     * @param EXP_DATE
     * @param AMOUNT
     * @param REF_FCC
     * @param REF_CW
     * @param TRANS_STATUS
     * @param SRCCHANNEL
     * @return
     * @throws Exception
     */
    public int insertCardReloadTrans(String ID, String SRCACCOUNT, String PAN_LOC,
            String CARD_BRN, String CCY, String EXP_DATE, Double AMOUNT, String REF_FCC,
            String REF_CW, String TRANS_STATUS, String SRCCHANNEL)
            throws Exception {

        PreparedStatement preStatement = null;
        ResultSet rs = null;

        try {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(insertCardReloadTrans);
            preStatement.setString(1, ID);
            preStatement.setString(2, SRCACCOUNT);
            preStatement.setString(3, PAN_LOC);
            preStatement.setString(4, CARD_BRN);
            preStatement.setString(5, CCY);
            preStatement.setString(6, EXP_DATE);
            preStatement.setDouble(7, AMOUNT);
            preStatement.setString(8, REF_FCC);
            preStatement.setString(9, REF_CW);
            preStatement.setString(10, TRANS_STATUS);
            preStatement.setString(11, SRCCHANNEL);
            if (preStatement.executeUpdate() > 0) {
                return 1;
            }
            LOGGER.info("insertCardReloadTrans -> Failed to update database");
            throw new Exception("insertCardReloadTrans -> Failed to update database");
        } catch (Exception ex) {
            LOGGER.error("insertCardReloadTrans -> " + ex);
            throw ex;
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

    //Long.Le edit updateCardReloadTrans
    /**
     *
     * @param ID
     * @param REF_FCC
     * @param REF_CW
     * @param TRANS_STATUS
     * @return
     * @throws Exception
     */
    public int updateCardReloadTrans(String ID, String REF_FCC, String REF_CW, String TRANS_STATUS)
            throws Exception {
        PreparedStatement preStatement = null;
        ResultSet rs = null;

        try {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(updateCardReloadTrans);
            preStatement.setString(1, REF_FCC);
            preStatement.setString(2, REF_CW);
            preStatement.setString(3, TRANS_STATUS);
            preStatement.setString(4, ID);

            if (preStatement.executeUpdate() > 0) {
                return 1;
            }
            LOGGER.info("updateCardReloadTrans -> Failed to update database");
            throw new Exception("updateCardReloadTrans -> Failed to update database");
        } catch (Exception ex) {
            LOGGER.error("updateCardReloadTrans -> " + ex);
            throw ex;
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

    //End of Cardword WS    
    //LongLe
    /**
     *
     * @param idschool
     * @return
     * @throws Exception
     */
    public String getAccountProvider(String idschool) throws Exception {
        idschool = idschool.toUpperCase();

        ArrayList p_args = new ArrayList();
        p_args.add("%" + idschool + "%");
        ArrayList list = JDBCEngine.executeQuery(GET_ACCOUNTPROVIDER_LIST, p_args, connection);
        if (!list.isEmpty()) {
            HashMap hm = (HashMap) list.get(0);
            return hm.get("account").toString();
        }
        return null;
    }

    /**
     *
     * @param status
     * @return
     * @throws Exception
     */
    public ArrayList<?> getSMSFitcom(int status) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(status);
        return JDBCEngine.executeQuery(GET_SMSFITCOM_LIST, p_args, connection);

    }

    /**
     *
     * @param idbillpaid
     * @param partnerDetailId
     * @param partnerId
     * @param accountTo
     * @return
     * @throws Exception
     */
    public int insertBillPaidDetail(int idbillpaid, String partnerDetailId,
            String partnerId, String accountTo) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(idbillpaid);
        p_args.add(partnerDetailId);
        p_args.add(partnerId);
        p_args.add(accountTo);
        return JDBCEngine.executeUpdate(INS_PBL_BILLPAID_PARTNERDETAIL, p_args, connection);
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatusSMSFitcom(int id, int status) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(status);
        p_args.add(id);
        return JDBCEngine.executeUpdate(UPDATE_STATUS_SMSFITCOM, p_args,
                connection);
    }

    /**
     *
     * @param id
     * @param mobile
     * @param content
     * @param serviceType
     * @param serviceCode
     * @return
     * @throws Exception
     */
    public int insertSMS(int id, String mobile, String content, String serviceType, String serviceCode) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(id);
        p_args.add(mobile);
        p_args.add(content);
        p_args.add(serviceType);
        p_args.add(serviceCode);
        return JDBCEngine.executeUpdate(INSERT_SMSFILE, p_args, connection);
    }

    /**
     *
     * @param id
     * @param mobile
     * @param content
     * @param serviceType
     * @param user
     * @return
     * @throws Exception
     */
    public int insertSmsSendLog(int id, String mobile, String content, String serviceType, String user) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(id);
        p_args.add(mobile);
        p_args.add(content);
        p_args.add(serviceType);
        p_args.add(user);
        return JDBCEngine.executeUpdate(INSERT_SMSSENDERLOG, p_args, connection);
    }

    /**
     *
     * @param idmsg
     * @param mobile
     * @param content
     * @param idfile
     * @param typemessage
     * @return
     * @throws Exception
     */
    public int insertFileDetail(int idmsg, String mobile, String content, int idfile, String typemessage) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(idmsg);
        p_args.add(mobile);
        p_args.add(content);
        p_args.add(idfile);
        p_args.add(typemessage);
        return JDBCEngine.executeUpdate(INSERT_SMSFILE_DETAIL, p_args, connection);
    }

    /**
     *
     * @param id
     * @param messagetype
     * @param filename
     * @param iduser
     * @param desc
     * @return
     * @throws Exception
     */
    public int uploadfileSMS(int id, String messagetype, String filename, String iduser, String desc) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(id);
        p_args.add(messagetype);
        p_args.add(filename);
        p_args.add(iduser);
        p_args.add(desc);
        return JDBCEngine.executeUpdate(UPLOADFILESMS, p_args, connection);
    }

    /**
     *
     * @param idfile
     * @param isapproved
     * @param iduser
     * @return
     * @throws Exception
     */
    public int approveFileSMS(int idfile, String isapproved, String iduser) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(isapproved);
        p_args.add(iduser);
        p_args.add(idfile);
        return JDBCEngine.executeUpdate(APPROVEFILESMS, p_args, connection);
    }

    /**
     *
     * @param idfile
     * @param isapproved
     * @return
     * @throws Exception
     */
    public int updateStatusSMS(int idfile, String isapproved) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(isapproved);
        p_args.add(idfile);
        return JDBCEngine.executeUpdate(UPDATESTATUSSMS, p_args, connection);
    }

    /**
     *
     * @param idmsg
     * @return
     * @throws Exception
     */
    public int updateSendMSG(int idmsg) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(idmsg);
        return JDBCEngine.executeUpdate(UPDATESENDMSG, p_args, connection);
    }

    /**
     *
     * @param idfile
     * @param send_flag
     * @param iduser
     * @return
     * @throws Exception
     */
    public int updateSendFile(int idfile, String send_flag, String iduser) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(send_flag);
        p_args.add(iduser);
        p_args.add(idfile);
        return JDBCEngine.executeUpdate(UPDATESENDFILE, p_args, connection);
    }

    /**
     *
     * @param fromDateSearch
     * @param toDateSearch
     * @param statusfile
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchFileSMS(String fromDateSearch, String toDateSearch, String statusfile) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add("%" + statusfile + "%");
        p_args.add(fromDateSearch);
        p_args.add(toDateSearch);
        return JDBCEngine.executeQuery(GETFILESMS, p_args, connection);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList<?> getFileDetail(String id) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(id);
        return JDBCEngine.executeQuery(GETFILESMS_DETAIL, p_args, connection);
    }

    /*Mua Bao hiem (S)*/
    /**
     *
     * @param ins
     * @return
     */
    public int insertInsuranceInfo(Insurance ins) {
        try {
            getSession().save(ins);
            return ins.getIdREG();
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        }
    }

    /**
     *
     * @param cd
     * @return
     */
    public int insertBuyCardDetail(RTCardDetailDTO cd) {
        try {
            getSession().save(cd);
            return cd.getId();
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        }
    }

    /**
     *
     * @param hour
     * @return
     * @throws Exception
     */
    public ArrayList<?> GetlistInsuranceToExport(int hour) throws Exception {
        switch (hour) {
            case 11:
                return JDBCEngine.executeQuery(GETLIST_INSURANCE_TOEXPORT_11, null, connection);
            case 16:
                return JDBCEngine.executeQuery(GETLIST_INSURANCE_TOEXPORT_15, null, connection);
            default:
                return null;
        }
    }

    /*Mua Bao hiem (E)*/

 /*Mua the cao (S)*/
    /**
     *
     * @param bc
     * @return
     */
    public int insertBuyCard(RTBuyCardDTO bc) {
        try {
            getSession().save(bc);
            return bc.getId();
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        }
    }

    /*Mua the cao (E)*/

 /*Thanh toan Bao hiem (S)*/
    /**
     *
     * @param payins
     * @return
     */
    public int insertPayIns(InsurancePayment payins) {
        try {
            getSession().save(payins);
            return payins.getIdinsurancepayment();
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        }
    }

    /**
     *
     * @param payins
     * @throws Exception
     */
    public void updateStatusPayIns(InsurancePayment payins) throws Exception {
        CallableStatement calStmt = connection.prepareCall(UPDATE_INSURANCE_PAYMENT);
        try {
            calStmt.setString(1, payins.getIduserChecker());
            calStmt.setString(2, payins.getIdchanneluserChecker());
            calStmt.setString(3, payins.getRefFcubs());
            calStmt.setString(4, payins.getIsapproved());
            calStmt.setString(5, payins.getStatus());
            calStmt.setString(6, String.valueOf(payins.getIdpayment()));
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
     * @param id
     * @param amount
     * @throws Exception
     */
    public void updateStatusBCBill(int id, long amount) throws Exception {
        CallableStatement calStmt = connection.prepareCall(UPDATE_BCBILL);
        try {
            calStmt.setString(1, String.valueOf(id));
            calStmt.setString(2, String.valueOf(amount));
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

    /*Thanh toan Bao hiem (E)*/
    //Create TranscodeEVNHN
    /**
     *
     * @param pPartnerID
     * @return
     * @throws SQLException
     */
    public String CreateTranscode(String pPartnerID) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(GET_TRANSCODE_EVNHN);
        try {
            calStmt.setString(1, pPartnerID);
            calStmt.registerOutParameter(2, Types.VARCHAR); //status
            calStmt.execute();
            String transcode = calStmt.getString(2);
            return transcode;
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

    //Modify CustAlertTD
    /**
     *
     * @param cust_no
     * @return
     * @throws Exception
     */
    public int writelogSMSAlertTD(String cust_no) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add("%" + cust_no + "%");
        return JDBCEngine.executeUpdate(INSERT_LOG_SMSCUSTALERTTD, p_args, connection);
    }
    //Modify CustAlertTD

    /*Đối soát Napas (S)*/
    /**
     *
     * @param np
     * @return
     * @throws SQLException
     */
    public int InsertNapasEcomCollated(NapasCollate np) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(insertNapasCollate);
        try {
            calStmt.setString(1, np.getACCOUNTNO());
            calStmt.setString(2, np.getPROCESSINGCODDE());
            calStmt.setString(3, np.getAMOUNT());
            calStmt.setString(4, np.getAUDITNUMBER());
            calStmt.setString(5, np.getTRANSTIME());
            calStmt.setString(6, np.getTRANSDATE());
            calStmt.setString(7, np.getPAYDATE());
            calStmt.setString(8, np.getMERCHANTTYPE());
            calStmt.setString(9, np.getACQUIRINGCODE());
            calStmt.setString(10, np.getAUTHORIZATIONNUMBER());
            calStmt.setString(11, np.getTERMID());
            calStmt.setString(12, np.getCCY());
            calStmt.setString(13, np.getSOURCEACCOUNT());
            calStmt.setString(14, np.getDESTACCOUNT());
            calStmt.setString(15, np.getRC());
            calStmt.setString(16, np.getCHECKSUM());
            calStmt.setString(17, np.getFILETYPE());
            calStmt.registerOutParameter(18, OracleTypes.NUMBER);
            calStmt.execute();
            int result = calStmt.getInt(18);
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
     * @param ID
     * @param pCoreref
     * @param pStatus
     * @throws SQLException
     */
    public void UpdateRefundTransferNAPAS(String ID, String pCoreref, String pStatus) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(UpdateRefundTransferNAPAS);
        try {
            calStmt.setString(1, ID);
            calStmt.setString(2, pCoreref);
            calStmt.setString(3, pStatus);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pPartnerID
     * @param pMerchanID
     * @param pTransID
     * @param pRefundTransID
     * @param pRefundAmount
     * @param pCCY
     * @param pADDINFO
     * @param pLocalDatetime
     * @return
     * @throws SQLException
     */
    public String[] CheckRefundTransferBanknet(String pPartnerID, String pMerchanID, String pTransID, String pRefundTransID,
            BigDecimal pRefundAmount, String pCCY, String pADDINFO, String pLocalDatetime) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(CheckRefundTransferBanknet);
        try {
            calStmt.setString(1, pPartnerID);
            calStmt.setString(2, pMerchanID);
            calStmt.setString(3, pTransID);
            calStmt.setString(4, pRefundTransID);
            calStmt.setBigDecimal(5, pRefundAmount);
            calStmt.setString(6, pCCY);
            calStmt.setString(7, pADDINFO);
            calStmt.setString(8, pLocalDatetime);
            calStmt.registerOutParameter(9, Types.VARCHAR); //status
            calStmt.registerOutParameter(10, Types.VARCHAR); //acc customer
            calStmt.registerOutParameter(11, Types.VARCHAR); //ID
            calStmt.registerOutParameter(12, Types.VARCHAR); //IDProvider
            calStmt.registerOutParameter(13, Types.VARCHAR); //CustomerCode
            calStmt.execute();
            String Status = calStmt.getString(9);
            String AccCustomer = calStmt.getString(10);
            String ID = calStmt.getString(11);
            String IDProvider = calStmt.getString(12);
            String CustomerCode = calStmt.getString(13);

            String[] ArrayResult = new String[5];
            ArrayResult[0] = Status;
            ArrayResult[1] = AccCustomer;
            ArrayResult[2] = ID;
            ArrayResult[3] = IDProvider;
            ArrayResult[4] = CustomerCode;
            return ArrayResult;
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
    public List<NapasCollate> getOutNapasEcomCollate() throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(getOutNapasEcomCollate);
            calStmt.registerOutParameter(1, OracleTypes.CURSOR);
            calStmt.executeQuery();
            List<NapasCollate> resultList = new ArrayList<NapasCollate>();
            rs = (ResultSet) calStmt.getObject(1);
            if (rs == null) {
                LOGGER.warn("Not found");
                throw new Exception("Not found");
            }
            while (rs.next()) {
                NapasCollate collate = new NapasCollate();
                collate.setACCOUNTNO(rs.getString("CARDNUMBER"));
                collate.setPROCESSINGCODDE(rs.getString("PROCESSINGCODE"));
                collate.setAMOUNT(rs.getString("AMOUNT"));
                collate.setAUDITNUMBER(rs.getString("AUDITNUMBER"));
                collate.setTRANSTIME(rs.getString("TRANSTIME"));
                collate.setTRANSDATE(rs.getString("TRANSDATE"));
                collate.setPAYDATE(rs.getString("PAYDATE"));
                collate.setMERCHANTTYPE(rs.getString("MERCHANTTYPE"));
                collate.setACQUIRINGCODE(rs.getString("ACQUIRINGCODE"));
                collate.setAUTHORIZATIONNUMBER(rs.getString("AUTHORIZATIONNUMBER"));
                collate.setTERMID(rs.getString("TERMID"));
                collate.setCCY(rs.getString("CCY"));
                collate.setSOURCEACCOUNT(rs.getString("SOURCEACCOUNT"));
                collate.setDESTACCOUNT(rs.getString("DESTACCOUNT"));
                collate.setRC(rs.getString("RC"));
                resultList.add(collate);
            }
            return resultList;
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

    /*Đối soát Napas (E)*/

 /*Thanh toán dư nợ thẻ tín dụng tại quầy (S) -- LONGLE*/
    /**
     *
     * @param id
     * @param cif
     * @param loc
     * @param cardtype
     * @param cardno
     * @param expi_date
     * @param cardname
     * @param branch_card
     * @param sotien_dtt
     * @param sotien_min
     * @param sotien_sk
     * @param sotien_duno
     * @param sotien_tt
     * @param paymentmethod
     * @param custAcNo
     * @param fullname
     * @param address
     * @param custNo
     * @param iduser_marker
     * @param idchanneluser_maker
     * @param isapproved
     * @param branchcode
     * @param idcard
     * @param idcardDob
     * @param idcardName
     * @param idcardAddress
     * @return
     * @throws Exception
     */
    public int insPayCreditCard(int id, String cif, String loc, String cardtype, String cardno, String expi_date, String cardname, String branch_card, BigDecimal sotien_dtt,
            BigDecimal sotien_min, BigDecimal sotien_sk, BigDecimal sotien_duno, BigDecimal sotien_tt, String paymentmethod, String custAcNo, String fullname, String address,
            String custNo, String iduser_marker, String idchanneluser_maker, String isapproved, String branchcode, String idcard, String idcardDob, String idcardName, String idcardAddress) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(id);
        p_args.add(cif);
        p_args.add(loc);
        p_args.add(cardtype);
        p_args.add(cardno);
        p_args.add(expi_date);
        p_args.add(cardname);
        p_args.add(branch_card);
        p_args.add(sotien_dtt);
        p_args.add(sotien_min);
        p_args.add(sotien_sk);
        p_args.add(sotien_duno);
        p_args.add(iduser_marker);
        p_args.add(idchanneluser_maker);
        p_args.add(sotien_tt);
        p_args.add(paymentmethod);
        p_args.add(custNo);
        p_args.add(custAcNo);
        p_args.add(fullname);
        p_args.add(address);
        p_args.add(isapproved);
        p_args.add(branchcode);
        p_args.add(idcard);
        p_args.add(idcardDob);
        p_args.add(idcardName);
        p_args.add(idcardAddress);

        int result = JDBCEngine.executeUpdate(INS_PAYCREDITCARD, p_args, connection);
        connection.commit();
        return result;
    }

    public int insPayCreditCardv2(int id, String cif, String loc, String cardtype, String cardno, String expi_date, String cardname, String branch_card, BigDecimal sotien_dtt,
            BigDecimal sotien_min, BigDecimal sotien_sk, BigDecimal sotien_duno, BigDecimal sotien_tt, String paymentmethod, String custAcNo, String fullname, String address,
            String custNo, String iduser_marker, String idchanneluser_maker, String isapproved, String branchcode, String idcard, String idcardDob, String idcardName, String idcardAddress, String sdtKh, String noiCapCMND) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(id);
        p_args.add(cif);
        p_args.add(loc);
        p_args.add(cardtype);
        p_args.add(cardno);
        p_args.add(expi_date);
        p_args.add(cardname);
        p_args.add(branch_card);
        p_args.add(sotien_dtt);
        p_args.add(sotien_min);
        p_args.add(sotien_sk);
        p_args.add(sotien_duno);
        p_args.add(iduser_marker);
        p_args.add(idchanneluser_maker);
        p_args.add(sotien_tt);
        p_args.add(paymentmethod);
        p_args.add(custNo);
        p_args.add(custAcNo);
        p_args.add(fullname);
        p_args.add(address);
        p_args.add(isapproved);
        p_args.add(branchcode);
        p_args.add(idcard);
        p_args.add(idcardDob);
        p_args.add(idcardName);
        p_args.add(idcardAddress);
        p_args.add(sdtKh);
        p_args.add(noiCapCMND);

        int result = JDBCEngine.executeUpdate(INS_PAYCREDITCARDV2, p_args, connection);
        connection.commit();
        return result;
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
     * @throws Exception
     */
    public String transferFCUBS(String glAcc, BigDecimal amount, String content, String idcardName, String idcardAddress, String idcard, String idcardDob, String user_maker, String user_checker, String branchcard) throws Exception {
        CallableStatement calStmt = connection.prepareCall(TRANSFERFCUBS_WITH_PAYCREDITCARD_CASH);
        try {
            calStmt.setString(1, glAcc);
            calStmt.setBigDecimal(2, amount);
            calStmt.setString(3, content);
            calStmt.setString(4, idcardName);
            calStmt.setString(5, idcardAddress);
            calStmt.setString(6, idcard);
            calStmt.setString(7, idcardDob);
            calStmt.setString(8, user_maker);
            calStmt.setString(9, user_checker);
            calStmt.setString(10, branchcard);
            calStmt.registerOutParameter(11, Types.VARCHAR); //status
            calStmt.registerOutParameter(12, Types.VARCHAR); //ref
            calStmt.execute();
            if ((calStmt.getString(11)).contains("CHUA MO TILL")) {
                String err = calStmt.getString(11);
                LOGGER.warn("user_checker = [" + user_checker + "], amount = [" + amount + "] CHUA MO TILL");
                return "null" + "#" + err;
            }
            String ref = calStmt.getString(12) + "#" + calStmt.getString(11);
            return ref;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList getPayCCInfoByID(int id) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(id);
        return JDBCEngine.executeQuery(GET_PAYCC_INFO_BYID, p_args, connection);
    }

    /**
     *
     * @param cif
     * @param loc
     * @param statuscode
     * @param branchcode
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchPayCCAll(String cif, String loc, String statuscode, String branchcode, String fromdate, String todate) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        p_args.add("%" + cif + "%");
        p_args.add("%" + loc + "%");
        p_args.add("%" + branchcode + "%");
        p_args.add("%" + statuscode + "%");
        return JDBCEngine.executeQuery(SEARCH_ALL_PAYCC_SUM, p_args, connection);
    }

    /**
     *
     * @param cif
     * @param loc
     * @param statuscode
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchPayCreditCard(String cif, String loc, String statuscode, String branchcode, String fromdate, String todate, String beginRow, String endRow) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        p_args.add("%" + cif + "%");
        p_args.add("%" + loc + "%");
        p_args.add("%" + statuscode + "%");
        p_args.add("%" + branchcode + "%");
        p_args.add(beginRow);
        p_args.add(endRow);
        return JDBCEngine.executeQuery(SEARCH_ALL_PAYCREDITCARD, p_args, connection);
    }

    /**
     *
     * @param iduser_checker
     * @param idchanneluser_checker
     * @param isapproved
     * @param ref_fcubs
     * @param status
     * @param id
     * @param so_ct
     * @throws Exception
     */
    public void updatePayCC(String iduser_checker, String idchanneluser_checker, String isapproved, String ref_fcubs, String status, int id, String so_ct) throws Exception {
        CallableStatement calStmt = connection.prepareCall(UPDATE_PAYCREDITCARD);
        try {
            calStmt.setString(1, iduser_checker);
            calStmt.setString(2, idchanneluser_checker);
            calStmt.setString(3, ref_fcubs);
            calStmt.setString(4, isapproved);
            calStmt.setString(5, status);
            calStmt.setString(6, so_ct);
            calStmt.setString(7, String.valueOf(id));
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /*Thanh toán dư nợ thẻ tín dụng tại quầy (E) -- LONGLE*/
    //Long.Le (E)
    //Contact Center
    /**
     *
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GET_EMAIL_HIST(String param) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(CC_GET_CUST_EMAIL_HIST);
        try {
            calStmt.registerOutParameter(1, Types.VARCHAR);
            //Slit paramenters
            String[] params = param.split("#");
            calStmt.setString(2, params[0]);
            calStmt.setString(3, params[1]);
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

    //End of Contact Center
    /**
     *
     * @param serialno
     * @return
     * @throws Exception
     */
    public ArrayList getValidToken(String serialno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(serialno);
        return JDBCEngine.executeQuery(VW_GW_TOKENOTP, p_args, connection);
    }

    /**
     *
     * @param idservicetype
     * @param idprodvider
     * @param customercode
     * @param CIFNO
     * @param UserName
     * @param Mobile
     * @param DebitAccount
     * @param ValidDate
     * @param ExpireDate
     * @param PaymentType
     * @return
     * @throws Exception
     */
    public ProcedureCallDto RegisterAutoBill(String idservicetype, String idprodvider, String customercode, String CIFNO, String UserName, String Mobile, String DebitAccount, String ValidDate, String ExpireDate) throws Exception {
        CallableStatement calStmt = connection.prepareCall(REGISTER_AUTOBILL);
        calStmt.setString(1, idservicetype);
        calStmt.setString(2, idprodvider);
        calStmt.setString(3, customercode);
        calStmt.setString(4, CIFNO);
        calStmt.setString(5, UserName);
        calStmt.setString(6, DebitAccount);
        calStmt.setString(7, ValidDate);
        calStmt.setString(8, ExpireDate);
        calStmt.setString(9, Mobile);
        calStmt.registerOutParameter(10, Types.INTEGER);
        calStmt.registerOutParameter(11, Types.VARCHAR);
        calStmt.registerOutParameter(12, Types.VARCHAR);
        calStmt.execute();

        ProcedureCallDto pro = new ProcedureCallDto();
        pro.setErrorStatus(String.valueOf(calStmt.getInt(10)));
        pro.setErrorMessage(calStmt.getString(11));
        pro.setOther(calStmt.getString(12));
        return pro;
    }

    
     /**
     *
     * @param idservicetype
     * @param idprodvider
     * @param customercode
     * @param CIFNO
     * @param UserName
     * @param Mobile
     * @param DebitAccount
     * @param ValidDate
     * @param ExpireDate
     * @param PaymentType
     * @return
     * @throws Exception
     */
    public ProcedureCallDto RegisterAutoBill_vnpt(String idservicetype, String idprodvider, String customercode, String CIFNO, String UserName, String Mobile, String DebitAccount, String ValidDate, String ExpireDate, String PaymentType) throws Exception {
        CallableStatement calStmt = connection.prepareCall(REGISTER_AUTOBILL_VNPTBILL);
        calStmt.setString(1, idservicetype);
        calStmt.setString(2, idprodvider);
        calStmt.setString(3, customercode);
        calStmt.setString(4, CIFNO);
        calStmt.setString(5, UserName);
        calStmt.setString(6, DebitAccount);
        calStmt.setString(7, ValidDate);
        calStmt.setString(8, ExpireDate);
        calStmt.setString(9, Mobile);
        calStmt.setString(10, PaymentType);
        calStmt.registerOutParameter(11, Types.INTEGER);
        calStmt.registerOutParameter(12, Types.VARCHAR);
        calStmt.registerOutParameter(13, Types.VARCHAR);
        calStmt.execute();

        ProcedureCallDto pro = new ProcedureCallDto();
        pro.setErrorStatus(String.valueOf(calStmt.getInt(11)));
        pro.setErrorMessage(calStmt.getString(12));
        pro.setOther(calStmt.getString(13));
        return pro;
    }

    
    
    /**
     *
     * @param phoneNumber
     * @return
     * @throws SQLException
     */
    public String getKMFromPhoneNumber(String phoneNumber) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getKMFromPhoneNumber);
        try {
            calStmt.setString(1, phoneNumber);
            calStmt.registerOutParameter(2, Types.VARCHAR);
            calStmt.execute();
            String transcode = calStmt.getString(2);
            return transcode;
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
     * @param cifno
     * @return
     * @throws Exception
     */
    public List<ThuPhiSMS> selectDSThuPhiEbank(String cifno) throws Exception {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(PROC_DS_THUPHI_EBANK);
            calStmt.setString(1, cifno);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.executeQuery();
            List<ThuPhiSMS> resultList = new ArrayList<ThuPhiSMS>();
            rs = (ResultSet) calStmt.getObject(2);
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                ThuPhiSMS thuphiSMS = new ThuPhiSMS();
                thuphiSMS.setThangnamretry(rs.getString("thangnamretry"));
                thuphiSMS.setThangnam(rs.getString("thangnam"));
                thuphiSMS.setMatrangthaikhoa(rs.getString("matrangthaikhoa"));
                thuphiSMS.setTrangthaikhoa(rs.getString("trangthaikhoa"));
                thuphiSMS.setMatrangthaithuphi(rs.getString("matrangthaithuphi"));
                thuphiSMS.setTrangthaithuphi(rs.getString("trangthaithuphi"));
                thuphiSMS.setLockdate(rs.getString("lockdate"));
                thuphiSMS.setPhoneno_user(rs.getString("phoneno_user"));
                thuphiSMS.setLoaidv(rs.getString("loaidv"));
                thuphiSMS.setCif(rs.getString("cif"));
                thuphiSMS.setCustname(rs.getString("custname"));
                thuphiSMS.setLoaikh(rs.getString("loaikh"));
                thuphiSMS.setFeeamount(rs.getString("feeamount"));
                thuphiSMS.setNgaythuphi(rs.getString("ngaythuphi"));
                thuphiSMS.setSogiaodich(rs.getString("sogiaodich"));
                thuphiSMS.setTkmd(rs.getString("tkmd"));
                thuphiSMS.setTkthuphi(rs.getString("tkthuphi"));
                thuphiSMS.setSdtsms(rs.getString("sdt_sms"));
                thuphiSMS.setNgayhachtoan(rs.getString("ngay_hach_toan"));
                resultList.add(thuphiSMS);
            }
            return resultList;
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
     * @param cifno
     * @param refcore
     * @param usermaker
     * @param userchecker
     * @param paydate
     * @return
     * @throws Exception
     */
    public ProcedureCallDto UnlockUserThuPhi(String cifno, String refcore, String usermaker, String userchecker, Date paydate) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(USER_UNLOCK_THUPHI);
            calStmt.setString(1, cifno);
            calStmt.setString(2, refcore);
            calStmt.setDate(3, new java.sql.Date(paydate.getTime()));
            calStmt.setString(4, usermaker);
            calStmt.setString(5, userchecker);
            calStmt.registerOutParameter(6, Types.INTEGER);
            calStmt.execute();
            ProcedureCallDto pro = new ProcedureCallDto();
            pro.setErrorStatus(String.valueOf(calStmt.getInt(6)));
            return pro;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param feeTrans
     * @return
     */
    public int insertFeeTransactionUnlock(FeeTransactionUnlock feeTrans) {
        try {
            getSession().save(feeTrans);
            return feeTrans.getId();
        } catch (Exception ex) {
            LOGGER.error("insertFeeTransactionUnlock - error: " + ex.toString());
            return -1;
        }
    }

    /**
     *
     * @param feeTrans
     * @return
     * @throws Exception
     */
    public List<FeeTransactionUnlock> getFeeTransactionUnlockList(FeeTransactionUnlock feeTrans) throws Exception {
        Criteria crit = getSession().createCriteria(FeeTransactionUnlock.class);
        if (feeTrans.getCif() != null && feeTrans.getCif().length() > 0) {
            crit.add(Restrictions.eq("cif", feeTrans.getCif()));
        }
        if (feeTrans.getBranchCode() != null && feeTrans.getBranchCode().length() > 0) {
            crit.add(Restrictions.eq("branchCode", feeTrans.getBranchCode()));
        }
        if (feeTrans.getStatus() != null && feeTrans.getStatus().toString().length() > 0) {
            crit.add(Restrictions.eq("status", feeTrans.getStatus()));
        }
        if (feeTrans.getFromDate() != null && feeTrans.getFromDate().length() > 0) {
            crit.add(Restrictions.sqlRestriction("trunc(insdate) >= to_date('" + feeTrans.getFromDate() + "','dd/mm/yyyy')"));
        }
        if (feeTrans.getToDate() != null && feeTrans.getToDate().length() > 0) {
            crit.add(Restrictions.sqlRestriction("trunc(insdate) <= to_date('" + feeTrans.getToDate() + "','dd/mm/yyyy')"));
        }
        crit.addOrder(Order.desc("insdate"));
        return crit.list();
    }

    /**
     *
     * @param cifno
     * @return
     * @throws Exception
     */
    public ProcedureCallDto getAccThuPhi(String cifno) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(PROC_ACC_THUPHI);
            calStmt.setString(1, cifno);
            calStmt.registerOutParameter(2, Types.VARCHAR);
            calStmt.registerOutParameter(3, Types.INTEGER);
            calStmt.execute();
            ProcedureCallDto pro = new ProcedureCallDto();
            pro.setErrorStatus(String.valueOf(calStmt.getInt(3)));
            pro.setOther(calStmt.getString(2));
            return pro;
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
     * @param cifno
     * @param refcore
     * @param paydate
     * @return
     * @throws Exception
     */
    public ProcedureCallDto updateThuphi(String cifno, String refcore, Date paydate) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(FEE_THUPHI);
            calStmt.setString(1, cifno);
            calStmt.setString(2, refcore);
            calStmt.setTime(3, new java.sql.Time(paydate.getTime()));
            calStmt.registerOutParameter(4, Types.NUMERIC);
            calStmt.execute();
            ProcedureCallDto pro = new ProcedureCallDto();
            pro.setErrorStatus(String.valueOf(calStmt.getInt(4)));
            return pro;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public List laydanhdachsanphambaohiem() throws Exception {
        ArrayList p_args = new ArrayList();
        return JDBCEngine.executeQuery(INS_SANPHAM, p_args, connection);
    }

    /**
     *
     * @param IdSanPham
     * @return
     * @throws Exception
     */
    public List chitietsanphambaohiem(String IdSanPham) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(IdSanPham);
        return JDBCEngine.executeQuery(INS_SANPHAM_DETAIL, p_args, connection);
    }

    /**
     *
     * @param Ownerid
     * @param Polnum
     * @return
     * @throws Exception
     */
    public List thongtinkhmanulife(String Ownerid, String Polnum) throws Exception {
        ArrayList p_args = new ArrayList();
        if (Ownerid != null && Ownerid.length() > 0) {
            BC_BILL_DETAIL += " OWNER_ID = ?";
            p_args.add(Ownerid);
        }
        if (Polnum != null && Polnum.length() > 0) {
            BC_BILL_DETAIL += " POL_NUM = ?";
            p_args.add(Polnum);
        }
        BC_BILL_DETAIL += " order by ID  ";
        return JDBCEngine.executeQuery(BC_BILL_DETAIL, p_args, connection);
    }

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public List danhsachhoadontudong(String custno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(custno);
        return JDBCEngine.executeQuery(VW_PBL_AUTO_REG, p_args, connection);
    }

    /**
     *
     * @param idautoreg
     * @return
     * @throws Exception
     */
    public int huyhoadontudong(String idautoreg) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(idautoreg);
        return JDBCEngine.executeUpdate(DEL_AUTO_REG, p_args, connection);
    }

    /**
     *
     * @param cifno
     * @return
     * @throws Exception
     */
    public List GetListInsuranceByCif(String cifno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(cifno);
        return JDBCEngine.executeQuery(GETLIST_INSURANCE_BYCIF, p_args, connection);
    }

    /**
     *
     * @param servType
     * @return
     * @throws SQLException
     */
    public ArrayList<?> getListPartner(String servType) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(LIST_PARTNER_PRO);
            calStmt.setString(1, servType);
            calStmt.execute();
            ArrayList<String> p_args = new ArrayList<String>();
            return JDBCEngine.executeQuery(LIST_PARTNER, p_args, connection);
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
     * @param dataxml
     * @param idbillpaid
     * @return
     * @throws Exception
     */
    public int updDataxmlByIdbillpaid(String dataxml, String idbillpaid) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(dataxml);
        p_args.add(idbillpaid);
        return JDBCEngine.executeUpdate(UPD_DATAXML_BY_IDBILLPAID, p_args,
                connection);
    }

    /**
     *
     * @param P_IDSERVICETYPE
     * @param P_IDPROVIDER
     * @param cusCode
     * @return
     * @throws SQLException
     */
    public ProcedureCallDto getListBill(String P_IDSERVICETYPE, String P_IDPROVIDER, String cusCode) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(LIST_BILL_CANCEL);
        try {
            calStmt.setString(1, P_IDSERVICETYPE);
            calStmt.setString(2, P_IDPROVIDER);
            calStmt.setString(3, cusCode);
            calStmt.registerOutParameter(4, Types.NUMERIC);
            calStmt.registerOutParameter(5, Types.NUMERIC);
            calStmt.execute();
            String id = calStmt.getString(4);
            String result = calStmt.getString(5);
            ProcedureCallDto resultCall = new ProcedureCallDto();
            resultCall.setErrorStatus(result);
            resultCall.setOther(id);
            return resultCall;
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
     * @param pbt
     * @return
     */
    public int insertPblBillpaidTransReverse(PblBillpaidTransReverse pbt) {
        try {
            getSession().save(pbt);
            return pbt.getIdbillpaid();
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        }
    }

    /**
     *
     * @param id
     * @param polnum
     * @param isapproved
     * @param status
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchMNLBill(String id, String polnum, String isapproved, String status, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (id == null || id.equals("")) {
            p_args.add("%" + id + "%");
        } else {
            p_args.add("" + id + "");
        }
        p_args.add("%" + polnum + "%");
        p_args.add("%" + isapproved + "%");
        p_args.add("%" + status + "%");
        p_args.add("%" + customercode + "%");
        p_args.add(beginRow);
        p_args.add(endRow);
        return JDBCEngine.executeQuery(SEARCH_ALL_MNL, p_args, connection);
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatusMNL(String id, String status) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(status);
        p_args.add(id);
        return JDBCEngine.executeUpdate(UPDATE_STATUS_MNL, p_args, connection);
    }

    /**
     *
     * @param id
     * @param polnum
     * @param isapproved
     * @param status
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchMNLBillAll(String id, String polnum, String isapproved, String status, String customercode, String statusBill, String fromdate, String todate) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (id == null || id.equals("")) {
            p_args.add("%" + id + "%");
        } else {
            p_args.add("" + id + "");
        }
        p_args.add("%" + polnum + "%");
        p_args.add("%" + isapproved + "%");
        p_args.add("%" + status + "%");
        p_args.add("%" + customercode + "%");
        return JDBCEngine.executeQuery(SEARCH_ALL_MNL_SUM, p_args, connection);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList<?> getInsByidpayment(int id) throws Exception {
        ArrayList<Integer> p_args = new ArrayList<Integer>();
        p_args.add(id);
        return JDBCEngine.executeQuery(GETINSBYIDPAY, p_args, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getAllListpRoviderIns() throws Exception {
        return JDBCEngine.executeQuery(GETLISTALL_RoviderIns, null, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getAllPartnerMNL() throws Exception {
        return JDBCEngine.executeQuery(GETLISTALL_Partner, null, connection);
    }

    /**
     *
     * @param fromDateSearch
     * @param toDateSearch
     * @return
     * @throws Exception
     */
    public ArrayList<?> getlistAllInsPayment(String fromDateSearch, String toDateSearch) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(fromDateSearch);
        p_args.add(toDateSearch);
        return JDBCEngine.executeQuery(GETLISTALL_InsPayment, p_args, connection);
    }

    /**
     *
     * @param bcbill
     * @return
     */
    public int insertBCBill(BCBill bcbill) {
        try {
            getSession().save(bcbill);
            return bcbill.getId();
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        }
    }

    /**
     *
     * @param items
     * @return
     * @throws SQLException
     */
    public int insertBCBill(List<BCBill> items) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(INSERT_BC_BILL);
            for (BCBill item : items) {
                preStatement.setString(1, item.getPolnum());
                preStatement.setString(2, item.getOwnername());
                preStatement.setString(3, item.getOwnerid());
                preStatement.setString(4, item.getRefnum());
                preStatement.setString(5, item.getPremtyp());
                preStatement.setString(6, item.getDuedt());
                preStatement.setLong(7, item.getAmount());
                preStatement.setString(8, item.getColcode());
                preStatement.setString(9, item.getArea());
                preStatement.setString(10, item.getChecksum());
                preStatement.setString(11, item.getAccnum());
                preStatement.setString(12, item.getAuto_db_dt());
                preStatement.setLong(13, item.getAmountpaid());
                preStatement.executeUpdate();
            }
            connection.commit();
            return 1;
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
            return -1;
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

    /**
     *
     * @return @throws SQLException
     */
    public int insertBCBillHistory() throws SQLException {
        PreparedStatement preStatement = null;
        try {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(INSERT_BCBILLHISTORY);
            return preStatement.executeUpdate() > 0 ? 1 : 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        } finally {
            if (preStatement != null) {
                preStatement.close();
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
    public int insertIStatusNo() throws SQLException {
        PreparedStatement preStatement = null;
        try {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(UPDATE_INSURANCESTATUS_N);
            preStatement.setString(1, "no");
            return preStatement.executeUpdate() > 0 ? 1 : 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        } finally {
            if (preStatement != null) {
                preStatement.close();
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
    public int insertIStatusYes() throws SQLException {
        PreparedStatement preStatement = null;
        try {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(UPDATE_INSURANCESTATUS_Y);
            preStatement.setString(1, "yes");
            return preStatement.executeUpdate() > 0 ? 1 : 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        } finally {
            if (preStatement != null) {
                preStatement.close();
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
    public int deleteBCBill() throws SQLException {
        PreparedStatement preStatement = null;
        try {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(DELETE_BCBILL);
            return preStatement.executeUpdate() > 0 ? 1 : 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pol
     * @param due
     * @param pre
     * @return
     * @throws Exception
     */
    public ArrayList<?> getBcBilId(String pol, String due, String pre) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(pre);
        p_args.add(due);
        p_args.add(pol);
        return JDBCEngine.executeQuery(GETID_BCBILL, p_args, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getAllListInsurancePayment() throws Exception {
        return JDBCEngine.executeQuery(GETLISTALL_INSURANCEPAYMENT, null, connection);
    }

    /**
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAllListIns(String sql) throws Exception {
        return JDBCEngine.executeQuery(sql, null, connection);
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatusTBT(String id, String status) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(status);
        p_args.add(id);
        return JDBCEngine.executeUpdate(UPDATE_STATUS_TBT, p_args, connection);
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchTBTBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (id == null || id.equals("")) {
            p_args.add("%" + id + "%");
        } else {
            p_args.add("" + id + "");
        }
        p_args.add("%" + customercode + "%");
        p_args.add("%" + statusBill + "%");
        p_args.add("%" + idtransaction + "%");
        return JDBCEngine.executeQuery(SEARCH_ALL_TBT_SUM, p_args, connection);
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchIBLBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (id == null || id.equals("")) {
            p_args.add("%" + id + "%");
        } else {
            p_args.add("" + id + "");
        }
        p_args.add("%" + statusBill + "%");
        p_args.add("%" + customercode + "%");
        p_args.add("%" + partner + "%");
        p_args.add(beginRow);
        p_args.add(endRow);
        return JDBCEngine.executeQuery(SEARCH_ALL_IBL, p_args, connection);
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatusIBL(String id, String status) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(status);
        p_args.add(id);
        return JDBCEngine.executeUpdate(UPDATE_STATUS_IBL, p_args, connection);
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchIBLBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (id == null || id.equals("")) {
            p_args.add("%" + id + "%");
        } else {
            p_args.add("" + id + "");
        }
        p_args.add("%" + statusBill + "%");
        p_args.add("%" + customercode + "%");
        p_args.add("%" + partner + "%");
        return JDBCEngine.executeQuery(SEARCH_ALL_IBL_SUM, p_args, connection);
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchTVSIBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (id == null || id.equals("")) {
            p_args.add("%" + id + "%");
        } else {
            p_args.add("" + id + "");
        }
        p_args.add("%" + statusBill + "%");
        p_args.add("%" + idtransaction + "%");
        p_args.add("%" + customercode + "%");
        p_args.add(beginRow);
        p_args.add(endRow);
        return JDBCEngine.executeQuery(SEARCH_ALL_TVSI, p_args, connection);
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatusTVSI(String id, String status) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(status);
        p_args.add(id);
        return JDBCEngine.executeUpdate(UPDATE_STATUS_TVSI, p_args, connection);
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchTVSIBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (id == null || id.equals("")) {
            p_args.add("%" + id + "%");
        } else {
            p_args.add("" + id + "");
        }
        p_args.add("%" + statusBill + "%");
        p_args.add("%" + idtransaction + "%");
        p_args.add("%" + customercode + "%");
        return JDBCEngine.executeQuery(SEARCH_ALL_TVSI_SUM, p_args, connection);
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchTVSI_Bill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (id == null || id.equals("")) {
            p_args.add("%" + id + "%");
        } else {
            p_args.add("" + id + "");
        }
        p_args.add("%" + statusBill + "%");
        p_args.add("%" + idtransaction + "%");
        p_args.add("%" + customercode + "%");
        p_args.add(beginRow);
        p_args.add(endRow);
        return JDBCEngine.executeQuery(SEARCH_ALL_TVSI_, p_args, connection);
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatus_TVSI(String id, String status) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(status);
        p_args.add(id);
        return JDBCEngine.executeUpdate(UPDATE_STATUS_TVSI_, p_args, connection);
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchTVSI_BillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (id == null || id.equals("")) {
            p_args.add("%" + id + "%");
        } else {
            p_args.add("" + id + "");
        }
        p_args.add("%" + statusBill + "%");
        p_args.add("%" + idtransaction + "%");
        p_args.add("%" + customercode + "%");
        return JDBCEngine.executeQuery(SEARCH_ALL_TVSI_SUM_, p_args, connection);
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchVTCBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (id == null || id.equals("")) {
            p_args.add("%" + id + "%");
        } else {
            p_args.add("" + id + "");
        }
        p_args.add("%" + idtransaction + "%");
        p_args.add("%" + statusBill + "%");
        p_args.add("%" + customercode + "%");
        p_args.add("%" + partner + "%");
        p_args.add(beginRow);
        p_args.add(endRow);
        return JDBCEngine.executeQuery(SEARCH_ALL_VTC, p_args, connection);
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int updateStatusVTC(String id, String status) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(status);
        p_args.add(id);
        return JDBCEngine.executeUpdate(UPDATE_STATUS_VTC, p_args, connection);
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchVTCBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (id == null || id.equals("")) {
            p_args.add("%" + id + "%");
        } else {
            p_args.add("" + id + "");
        }
        p_args.add("%" + idtransaction + "%");
        p_args.add("%" + statusBill + "%");
        p_args.add("%" + customercode + "%");
        p_args.add("%" + partner + "%");
        return JDBCEngine.executeQuery(SEARCH_ALL_VTC_SUM, p_args, connection);
    }

    /**
     *
     * @param status
     * @param idbillpaid
     * @return
     * @throws Exception
     */
    public int updStatusByIdbillpaid(String status, String idbillpaid) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(status);
        p_args.add(idbillpaid);
        return JDBCEngine.executeUpdate(UPD_STATUS_BY_IDBILLPAID, p_args,
                connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getSmlStatus() throws Exception {
        return JDBCEngine.executeQuery(GET_SML_STATUS, null, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getOnlTranStatus() throws Exception {
        return JDBCEngine.executeQuery(GET_ONLTRAN_STATUS, null, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getRetailtatus() throws Exception {
        return JDBCEngine.executeQuery(GET_RETAIL_STATUS, null, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getInsStatus() throws Exception {
        return JDBCEngine.executeQuery(GET_INS_STATUS, null, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getSiStatus() throws Exception {
        return JDBCEngine.executeQuery(GET_SI_STATUS, null, connection);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList getInsurencePayByID(int id) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(id);
        return JDBCEngine.executeQuery(GET_INSURANCE_BYID, p_args, connection);
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchTBTBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws Exception {
        LOGGER.info("SEARCH_ALL_TBT:id" + id + "-idtransaction:" + idtransaction + "-partner:" + partner + "-customercode:" + customercode + "-statusBill:" + statusBill + "-todate:" + todate + "-beginRow:" + beginRow + "-endRow:" + endRow);
        ArrayList p_args = new ArrayList();
        p_args.add(fromdate);
        p_args.add(todate);
        if (id == null || id.equals("")) {
            p_args.add("%" + id + "%");
        } else {
            p_args.add("" + id + "");
        }
        p_args.add("%" + customercode + "%");
        p_args.add("%" + statusBill + "%");
        p_args.add("%" + idtransaction + "%");
        p_args.add(beginRow);
        p_args.add(endRow);
        return JDBCEngine.executeQuery(SEARCH_ALL_TBT, p_args, connection);
    }

    /**
     *
     * @param id
     * @param cif
     * @param loc
     * @param cardtype
     * @param cardno
     * @param expi_date
     * @param cardname
     * @param branch_card
     * @param sotien_dtt
     * @param sotien_min
     * @param sotien_sk
     * @param sotien_duno
     * @param sotien_tt
     * @param paymentmethod
     * @param custAcNo
     * @param fullname
     * @param address
     * @param custNo
     * @param iduser_marker
     * @param idchanneluser_maker
     * @param isapproved
     * @param branchcode
     * @param idcard
     * @param idcardDob
     * @param idcardName
     * @param idcardAddress
     * @return
     * @throws Exception
     */
    public int insPayCreditCardIPP(int id, String cif, String loc, String cardtype, String cardno, String expi_date, String cardname, String branch_card, BigDecimal sotien_dtt,
            BigDecimal sotien_min, BigDecimal sotien_sk, BigDecimal sotien_duno, BigDecimal sotien_tt, String paymentmethod, String custAcNo, String fullname, String address,
            String custNo, String iduser_marker, String idchanneluser_maker, String isapproved, String branchcode, String idcard, String idcardDob, String idcardName, String idcardAddress) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(id);
        p_args.add(cif);
        p_args.add(loc);
        p_args.add(cardtype);
        p_args.add(cardno);
        p_args.add(expi_date);
        p_args.add(cardname);
        p_args.add(branch_card);
        p_args.add(sotien_dtt);
        p_args.add(sotien_min);
        p_args.add(sotien_sk);
        p_args.add(sotien_duno);
        p_args.add(iduser_marker);
        p_args.add(idchanneluser_maker);
        p_args.add(sotien_tt);
        p_args.add(paymentmethod);
        p_args.add(custNo);
        p_args.add(custAcNo);
        p_args.add(fullname);
        p_args.add(address);
        p_args.add(isapproved);
        p_args.add(branchcode);
        p_args.add(idcard);
        p_args.add(idcardDob);
        p_args.add(idcardName);
        p_args.add(idcardAddress);
        int result = JDBCEngine.executeUpdate(INS_PAYCREDITCARD, p_args, connection);
        connection.commit();
        return result;
    }

    /**
     *
     * @param ID
     * @param SRCACCOUNT
     * @param PAN_LOC
     * @param CARD_BRN
     * @param CCY
     * @param EXP_DATE
     * @param AMOUNT
     * @param REF_FCC
     * @param REF_CW
     * @param TRANS_STATUS
     * @param SRCCHANNEL
     * @param debtPay
     * @param debtCur
     * @param IPPPayType
     * @return
     * @throws Exception
     */
    public int insertCardIPPTrans(String ID, String SRCACCOUNT, String PAN_LOC,
            String CARD_BRN, String CCY, String EXP_DATE, Double AMOUNT, String REF_FCC,
            String REF_CW, String TRANS_STATUS, String SRCCHANNEL, BigDecimal debtPay, BigDecimal debtCur, String IPPPayType)
            throws Exception {
        PreparedStatement preStatement = null;
        ResultSet rs = null;

        try {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(insertCardIPPTrans);
            preStatement.setString(1, ID);
            preStatement.setString(2, SRCACCOUNT);
            preStatement.setString(3, PAN_LOC);
            preStatement.setString(4, CARD_BRN);
            preStatement.setString(5, CCY);
            preStatement.setString(6, EXP_DATE);
            preStatement.setDouble(7, AMOUNT);
            preStatement.setString(8, REF_FCC);
            preStatement.setString(9, REF_CW);
            preStatement.setString(10, TRANS_STATUS);
            preStatement.setString(11, SRCCHANNEL);
            preStatement.setDouble(12, debtPay.doubleValue());
            preStatement.setDouble(13, debtCur.doubleValue());
            preStatement.setString(14, IPPPayType);
            if (preStatement.executeUpdate() > 0) {
                return 1;
            }
            LOGGER.info("insertCardIPPTrans -> Failed to update database");
            throw new Exception("insertCardIPPTrans -> Failed to update database");
        } catch (Exception ex) {
            LOGGER.error("insertCardIPPTrans -> " + ex);
            throw ex;
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

    /**
     *
     * @param ID
     * @param REF_FCC
     * @param REF_CW
     * @param TRANS_STATUS
     * @return
     * @throws Exception
     */
    public int updateCardIPPTrans(String ID, String REF_FCC, String REF_CW, String TRANS_STATUS)
            throws Exception {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(updateCardIPPTrans);
            preStatement.setString(1, REF_FCC);
            preStatement.setString(2, REF_CW);
            preStatement.setString(3, TRANS_STATUS);
            preStatement.setString(4, ID);
            if (preStatement.executeUpdate() > 0) {
                return 1;
            }
            LOGGER.info("updateCardIPPTrans -> Failed to update database");
            throw new Exception("updateCardIPPTrans -> Failed to update database");
        } catch (Exception ex) {
            LOGGER.error("updateCardIPPTrans --> " + ex);
            throw ex;
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

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public ArrayList<?> getListCWWS_CARD_IPP(int id) throws SQLException {
        try {
            ArrayList<Integer> p_args = new ArrayList<Integer>();
            p_args.add(id);
            return JDBCEngine.executeQuery(LIST_CWWS_CARD_IPP, p_args, connection);
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
     * @return @throws Exception
     */
    public int CHECK_LASTMONTH_DATE() throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        ArrayList List = JDBCEngine.executeQuery(LAST_MONTH_DATE, p_args, connection);
        HashMap<?, ?> HMDate = (HashMap<?, ?>) List.get(0);
        int returnVal = Integer.parseInt(HMDate.get("returnVal").toString());
        return returnVal;
    }

    /**
     *
     * @param id
     * @param module
     * @param partner
     * @param serviceType
     * @param provider
     * @param merchant
     * @param customerCode
     * @param oldStatus
     * @param newStatus
     * @param userUpd
     * @throws Exception
     */
    public void updateChangeStatusPaybill(String id, String module, String partner,
            String serviceType, String provider, String merchant,
            String customerCode, String oldStatus, String newStatus,
            String userUpd) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + UPD_PROC_TRANS + " (?,?,?,?,?,?,?,?,?,?); END;");
            calStmt.setString(1, id);
            calStmt.setString(2, module);
            calStmt.setString(3, partner);
            calStmt.setString(4, serviceType);
            calStmt.setString(5, provider);
            calStmt.setString(6, merchant);
            calStmt.setString(7, customerCode);
            calStmt.setString(8, oldStatus);
            calStmt.setString(9, newStatus);
            calStmt.setString(10, userUpd);
            calStmt.execute();
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
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
    public String GET_BRANCHCODE_FROM_FCC(String accountno) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall(GET_BRANCHCODE_FROM_FCC);
            calStmt.setString(1, accountno);
            calStmt.registerOutParameter(2, OracleTypes.VARCHAR);
            calStmt.execute();
            String result = calStmt.getString(2);
            return result;
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /* EVNHCM */
    /**
     *
     * @param partner
     * @param customerCode
     * @param totalAmount
     * @param refPartnerId
     * @param revertStatus
     * @return
     * @throws Exception
     */
    public int deletePayooBill(String partner, String customerCode, String totalAmount, String refPartnerId, String revertStatus) throws Exception {
        try {
            String request = String.format(DELETE_PAYOOBILL, revertStatus, partner, customerCode, totalAmount, refPartnerId);
            int result = JDBCEngine.executeUpdate(request, null, connection);
            return (result > 0) ? 0 : -2;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param partner
     * @param customerCode
     * @param totalAmount
     * @param refPartnerId
     * @return
     * @throws Exception
     */
    public String queryRefCustPayooBill(String partner, String customerCode, String totalAmount, String refPartnerId) throws Exception {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String result = "";
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(QUERY_REF_PAYOOLOG);
            preStatement.setString(1, partner);
            preStatement.setString(2, customerCode);
            preStatement.setString(3, totalAmount);
            preStatement.setString(4, refPartnerId);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                result = rs.getString("REF_FCUBS");
                break;
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
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

    /* EVNHCM */
    // baovq - sms & tp
    /**
     *
     * @param cust_no
     * @return
     * @throws SQLException
     */
    public ArrayList<?> Find_List_Cust_No(String cust_no) throws SQLException {
        try {
            ArrayList<String> p_args = new ArrayList<>();
            p_args.add(cust_no);
            return JDBCEngine.executeQuery(get_Find_List_Cust_No, p_args, connection);
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
     * @param smscustalerttd
     * @return
     * @throws Exception
     */
    public int update_REF_STATUS(SmsCustAlertTd smscustalerttd) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        String sql = "";
        if (smscustalerttd.getRef_status().equals("ERROR_THU") || smscustalerttd.getRef_status().equals("ERROR_CHI")) {
            p_args.add(smscustalerttd.getRef_status());
            p_args.add(smscustalerttd.getCust_no());
            sql = UPD_REF_STATUS;
        }
        if (smscustalerttd.getRef_status().equals("DONE_THU")) {
            p_args.add(smscustalerttd.getCust_ac_no());
            p_args.add(smscustalerttd.getPoint_reward());
            p_args.add(smscustalerttd.getAuto_poi_rew());
            p_args.add(smscustalerttd.getUser_poi_rew());
            p_args.add(smscustalerttd.getSo_tien());
            p_args.add(smscustalerttd.getRef_thu());
            p_args.add(smscustalerttd.getRef_status());
            p_args.add(smscustalerttd.getCust_no());
            sql = UPD_REF_THU;
        }
        if (smscustalerttd.getRef_status().equals("DONE_CHI")) {
            p_args.add(smscustalerttd.getSo_tien());
            p_args.add(smscustalerttd.getRef_chi());
            p_args.add(smscustalerttd.getRef_status());
            p_args.add(smscustalerttd.getCust_no());
            sql = UPD_REF_CHI;
        }
        if (smscustalerttd.getRef_status().equals("NOT_THUCHI")) {
            p_args.add(smscustalerttd.getPoint_reward());
            p_args.add(smscustalerttd.getUser_poi_rew());
            p_args.add(smscustalerttd.getAuto_poi_rew());
            p_args.add(smscustalerttd.getCust_no());
            sql = UPD_THU_CONG;
        }
        return JDBCEngine.executeUpdate(sql, p_args, connection);
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
     * @param idpartner
     * @param idservicetype
     * @param idprovider
     * @return
     * @throws Exception
     */
    public ArrayList<?> getACCNOFCUBS(String idpartner, String idservicetype, String idprovider) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(idpartner);
        p_args.add(idservicetype);
        p_args.add(idprovider);
        return JDBCEngine.executeQuery(getACCNOFCUBS, p_args, connection);
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
     * @param custno
     * @return
     * @throws Exception
     */
    public List GetRedemptionAzListByCustNo(String custno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(custno);
        ArrayList list = JDBCEngine.executeQuery(GET_VW_REDEMPTION_AZ, p_args, connection);
        return list;
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
     * @param cifno
     * @param accountno
     * @param cardaccountno
     * @param registertype
     * @param issuetype
     * @param cardtype
     * @param fee
     * @param tax
     * @return
     * @throws Exception
     */
    public int checkIssueATMCard(String cifno, String accountno, String cardaccountno, String registertype, String issuetype, String cardtype, String fee, String tax) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(PKS_CHECKISSUEATMCARD);
            calStmt.setString(1, cifno);
            calStmt.setString(2, accountno);
            calStmt.setString(3, cardaccountno);
            calStmt.setString(4, registertype);
            calStmt.setString(5, issuetype);
            calStmt.setString(6, cardtype);
            calStmt.setString(7, fee);
            calStmt.setString(8, tax);
            calStmt.registerOutParameter(9, OracleTypes.NUMBER);
            calStmt.execute();
            return calStmt.getInt(9);
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
     * @param cardaccountno
     * @return
     * @throws Exception
     */
    public String[] getPointMC(String cardaccountno) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(PKS_GET_POINT_MC);
            calStmt.setString(1, cardaccountno);
            calStmt.registerOutParameter(2, Types.NUMERIC);
            calStmt.registerOutParameter(3, Types.NUMERIC);
            calStmt.registerOutParameter(4, Types.NUMERIC);
            calStmt.registerOutParameter(5, Types.NUMERIC);
            calStmt.execute();
            String[] arrResult = new String[4];
            arrResult[0] = String.valueOf(calStmt.getInt(2));
            arrResult[1] = String.valueOf(calStmt.getInt(3));
            arrResult[2] = String.valueOf(calStmt.getInt(4));
            arrResult[3] = String.valueOf(calStmt.getInt(5));
            return arrResult;
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

    //dvsms
    /**
     *
     * @param branch
     * @return
     * @throws Exception
     */
    public ArrayList<?> GET_TENNV(String branch) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(GET_TENNV);
            calStmt.setString(1, branch);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(2);
            ArrayList result = new ArrayList();
            while (rs.next()) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("manv", rs.getString("manv"));
                h.put("nhanvien", rs.getString("nhanvien"));
                result.add(h);
            }
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
     * @param manv
     * @return
     * @throws Exception
     */
    public ArrayList<?> QUERY_TENNV(String manv) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(manv);
        return JDBCEngine.executeQuery(QUERY_TENNV, p_args, connection);
    }

    /**
     *
     * @param smsservice
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountSMSService(SMSService smsservice) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(smsservice.getId());
        return JDBCEngine.executeQuery(GET_SMSService_BY_ID, p_args, connection);
    }

    /**
     *
     * @param cust_no
     * @param registertype
     * @return
     * @throws Exception
     */
    public ArrayList<?> findHistoryListSMSService(String cust_no,
            String registertype) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(cust_no);
        return JDBCEngine.executeQuery(GET_SMSSERVICE_BY_CUST_NO, p_args, connection);
    }

    /**
     *
     * @param smsservice
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountSMSSERVICEById(SMSService smsservice) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(smsservice.getId());
        return JDBCEngine.executeQuery(GET_ACCOUNTSMSService_BY_ID, p_args, connection);
    }

    /**
     *
     * @param smsservice
     * @param iduserref
     * @param cust_no
     * @param ma_nv_tt
     * @throws Exception
     */
    public void insertSMSServiceTT(SMSService smsservice, int iduserref, String cust_no, String ma_nv_tt) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + INS_SMSService_TT
                    + " (?,?,?,?,?,?); END;");
            calStmt.setInt(1, iduserref);
            calStmt.setString(2, smsservice.getCust_ac_no());
            calStmt.setString(3, cust_no);
            calStmt.setString(4, "TT");
            calStmt.setString(5, smsservice.getMobile());
            calStmt.setString(6, ma_nv_tt);
            calStmt.execute();
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
        }
    }

    /**
     *
     * @param smsservice
     * @param iduserref
     * @param cust_no
     * @throws Exception
     */
    public void insertSMSServiceTK(SMSServiceTK smsservice, int iduserref, String cust_no) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + INS_SMSService_TK
                    + " (?,?,?,?,?,?,?,?,?,?); END;");
            calStmt.setInt(1, iduserref);
            calStmt.setString(2, smsservice.getCust_ac_no());
            calStmt.setString(3, cust_no);
            calStmt.setString(4, "TK");
            calStmt.setString(5, smsservice.getMobile());
            calStmt.setString(6, smsservice.getTk_denhan());
            calStmt.setString(7, smsservice.getTk_momoi());
            calStmt.setString(8, smsservice.getPoint_reward());
            calStmt.setString(9, smsservice.getAuto_poi_rew());
            calStmt.setString(10, smsservice.getMa_nv_tk());
            calStmt.execute();
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
        }
    }

    /**
     *
     * @param smsservice
     * @param userrefid
     * @param custno
     * @throws Exception
     */
    public void updateSMSServiceTK(SMSServiceTK smsservice, int userrefid, String custno) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + Update_SMSService_tk
                    + " (?,?,?,?,?,?,?,?,?); END;");
            calStmt.setInt(1, userrefid);
            calStmt.setString(2, smsservice.getId());
            calStmt.setString(3, smsservice.getCust_ac_no());
            calStmt.setString(4, custno);
            calStmt.setString(5, smsservice.getMobile());
            calStmt.setString(6, smsservice.getTk_denhan());
            calStmt.setString(7, smsservice.getTk_momoi());
            calStmt.setString(8, smsservice.getPoint_reward());
            calStmt.setString(9, smsservice.getAuto_poi_rew());
            calStmt.execute();
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
        }
    }

    /**
     *
     * @param smsservice
     * @param userrefid
     * @param custno
     * @throws Exception
     */
    public void updateSMSServiceTT(SMSService smsservice, int userrefid, String custno) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + Update_SMSService_tt
                    + " (?,?,?,?,?,?); END;");
            calStmt.setInt(1, userrefid);
            calStmt.setString(2, smsservice.getId());
            calStmt.setString(3, smsservice.getCust_ac_no());
            calStmt.setString(4, custno);
            calStmt.setString(5, smsservice.getMobile());
            calStmt.setString(6, smsservice.getIsdeleted());
            calStmt.execute();
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
        }
    }

    /**
     *
     * @param userrefid
     * @param status
     * @param userid
     * @throws Exception
     */
    public void approveSMSService(int userrefid, String status, String userid) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + Approve_SMSService_phone
                    + " (?,?,?); END;");
            calStmt.setInt(1, userrefid);
            calStmt.setString(2, status);
            calStmt.setString(3, userid);
            calStmt.execute();
        } catch (Exception sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
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
     * @param user
     * @return
     * @throws Exception
     */
    public int insertSMSServiceUser(SMSServiceUser user) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + INS_SMSService_user
                    + " (?,?,?,?); END;");
            calStmt.setString(1, user.getUserid());
            calStmt.setString(2, user.getBranchid());
            calStmt.setString(3, user.getCust_no());
            calStmt.registerOutParameter(4, Types.INTEGER);
            calStmt.execute();
            int id = calStmt.getInt(4);
            //Insert/Update dk tk thanh toan
            List<SMSService> listSMS = user.getListSMS();
            for (int i = 0; i < listSMS.size(); i++) {
                SMSService sms = listSMS.get(i);
                if (sms.getIsSelected().equals("1")) {
                    if (!"0".equals(sms.getId())) {
                        updateSMSServiceTT(sms, id, user.getCust_no());
                    } else {
                        if (sms.getMobile() != null) {
                            insertSMSServiceTT(sms, id, user.getCust_no(), user.getMa_nv_tt());
                        }
                    }
                }
            }
            //Insert/Update dk tk thanh toan
            SMSServiceTK smstk = user.getSmstk();
            if (!"0".equals(smstk.getId()) & smstk.getId() != null & !smstk.getId().equals("")) {
                updateSMSServiceTK(smstk, id, user.getCust_no());
            } else {
                if (smstk.getTk_momoi().equals("Y") || smstk.getPoint_reward().equals("Y")) {
                    insertSMSServiceTK(smstk, id, user.getCust_no());
                }
            }
            return id;
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountTK(String custno) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(custno);
        return JDBCEngine.executeQuery(getAccountTK, p_args, connection);
    }

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public ArrayList<?> getListAccountTT(String custno) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(custno);
        return JDBCEngine.executeQuery(getlistAccountTT, p_args, connection);
    }

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public String[] checkDKDVSMS(String custno) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(custno);
        ArrayList<?> result = JDBCEngine.executeQuery(checkInsertDVSMS, p_args, connection);
        if (result != null && result.size() > 0) {
            String[] listResult = new String[2];
            // Co ton tai USERROLE da su dung ROLE nay. KHong the xoa
            HashMap<?, ?> hm = (HashMap<?, ?>) result.get(0);
            listResult[0] = hm.get("branchid").toString();
            listResult[1] = hm.get("userid").toString();
            return listResult;
        }
        return null;
    }
    //add new by BAOVQ ; 08/Aug/2018

    /**
     *
     * @param custno
     * @param idcard
     * @return
     * @throws Exception
     */
    public ArrayList<?> searchCustomerCore(String custno, String idcard) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(custno);
        p_args.add(idcard);
        return JDBCEngine.executeQuery(SEARCH_CUSTOMER_CORE, p_args, connection);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList<?> get_data_approve(String id) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(id);
        return JDBCEngine.executeQuery(get_data_approve, p_args, connection);
    }

    /**
     *
     * @param smsuser
     * @return
     * @throws Exception
     */
    public int update_REF_STATUS_SMSTK(SMSServiceUser smsuser) throws Exception {
        SMSServiceTK smstk = smsuser.getSmstk();
        ArrayList<String> p_args = new ArrayList<>();
        String sql = UPD_REF_STATUS_SMSTK;
        p_args.add(smstk.getRef_status() == null ? "" : smstk.getRef_status());
        p_args.add(smstk.getRef_thu() == null ? "" : smstk.getRef_thu());
        p_args.add(smstk.getRef_chi() == null ? "" : smstk.getRef_chi());
        p_args.add(smstk.getId());
        return JDBCEngine.executeUpdate(sql, p_args, connection);
    }

    /**
     *
     * @param cust_no
     * @return
     * @throws Exception
     */
    public ArrayList<?> CHECK_POINT_REWARD_THU(String cust_no) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(cust_no);
        return JDBCEngine.executeQuery(CHECK_POINT_REWARD_THU, p_args, connection);
    }

    /**
     *
     * @param cust_no
     * @return
     * @throws Exception
     */
    public ArrayList<?> CHECK_POINT_REWARD_CHI(String cust_no) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(cust_no);
        return JDBCEngine.executeQuery(CHECK_POINT_REWARD_CHI, p_args, connection);
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
    final String getListAccountByCif = "select cust_ac_no, ac_desc, acy_avl_bal from VW_LIST_ACCOUNT where cust_no =? ";

    /**
     *
     * @param cust_no
     * @return
     * @throws SQLException
     */
    public ArrayList<?> getListAccountByCif(String cust_no) throws SQLException {
        try {
            ArrayList<String> p_args = new ArrayList<>();
            p_args.add(cust_no);
            return JDBCEngine.executeQuery(getListAccountByCif, p_args, connection);

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
     * @param ccy
     * @return
     * @throws Exception
     */
    public ArrayList getInterestRate(String ccy) throws Exception {
        ArrayList p_args = new ArrayList();

        if (ccy.equals("LDK")) {
            p_args.add("VND");
            return JDBCEngine.executeQuery(GET_FCC_VW_INTEREST_RATE_LDK, p_args, connection);
        } else if (ccy.equals("DN")) {
            p_args.add("VND");
            return JDBCEngine.executeQuery(GET_FCC_VW_INTEREST_RATE_DN, p_args, connection);
        } else if (ccy.equals("LTT")) {
            p_args.add("VND");
            return JDBCEngine.executeQuery(GET_FCC_VW_INTEREST_RATE_LTT, p_args, connection);
        } else {
            p_args.add(ccy);
            return JDBCEngine.executeQuery(GET_FCC_VW_INTEREST_RATE, p_args, connection);
        }

    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getExchangeRate() throws Exception {
        return JDBCEngine.executeQuery(GET_EXCHANGERATE, new ArrayList<String>(), connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getGoldRate() throws Exception {
        return JDBCEngine.executeQuery(GET_GOLDRATE, new ArrayList<String>(), connection);
    }

    /**
     *
     * @param custno
     * @param custaccno
     * @param amount
     * @return
     * @throws Exception
     */
    public ProcedureCallDto checkFeeMobile(String custno, String custaccno, String amount) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(PKS_CHECK_BALANCE_BEFORE_FEE);
            calStmt.setString(1, custno);
            calStmt.setString(2, custaccno);
            calStmt.setString(3, amount);
            calStmt.registerOutParameter(4, Types.INTEGER);
            calStmt.registerOutParameter(5, Types.VARCHAR);
            calStmt.registerOutParameter(6, Types.VARCHAR);
            calStmt.execute();

            ProcedureCallDto pro = new ProcedureCallDto();
            pro.setErrorStatus(String.valueOf(calStmt.getInt(4)));
            pro.setErrorMessage(calStmt.getString(5));
            pro.setOther(calStmt.getString(6));
            return pro;
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
     * @param cust_no
     * @return
     * @throws Exception
     */
    public ArrayList<?> CHECK_POINT_REWARD_TC(String cust_no) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(cust_no);
        return JDBCEngine.executeQuery(CHECK_POINT_REWARD_TC, p_args, connection);
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
     * @param emailTd
     * @return
     * @throws Exception
     */
    public BigDecimal insertGwEmailTd(GwEmailTd emailTd) throws Exception {
        try {
            getSession().save(emailTd);
            return emailTd.getId();
        } catch (Exception ex) {
            LOGGER.error(ex);
            return BigDecimal.ZERO;
        }
    }

    /**
     *
     * @param emailTd
     * @param fromDate
     * @param toDate
     * @return
     * @throws Exception
     */
    public List getListGwEmailTd(GwEmailTd emailTd, String fromDate, String toDate) throws Exception {
        Criteria crit = getSession().createCriteria(GwEmailTd.class);
        if (!Common.isEmpty(emailTd.getCustomerNo())) {
            crit.add(Restrictions.eq("customerNo", emailTd.getCustomerNo()));
        }
        if (!Common.isEmpty(emailTd.getCustAcNo())) {
            crit.add(Restrictions.eq("custAcNo", emailTd.getCustAcNo()));
        }
        if (fromDate != null && !fromDate.isEmpty()) {
            crit.add(Restrictions.sqlRestriction("trunc(created_date) >= to_date('" + fromDate + "','yyyymmdd')"));
        }
        if (toDate != null && !toDate.isEmpty()) {
            crit.add(Restrictions.sqlRestriction("trunc(created_date) <= to_date('" + toDate + "','yyyymmdd')"));
        }
        crit.addOrder(Order.desc("id"));
        List l = crit.list();
        return l;
    }
    //son 01/nov/2019
    final String DELETE_INSURANCE_PAYMENT = "begin PKG_PAYMENT_INSURANCE.delete_INSURANCE_PAYMENT(?,?); end;";
    //son 04/nov/2019
    final String DELETE_PAYMENT_CREDITCARD = "begin PKG_PAYMENT_CREDITCARD.delete_PAYMENT_CREDITCARD(?,?); end;";
    //son edit 01/nov/2019

    /**
     *
     * @param pid
     * @return
     * @throws SQLException
     */
    public int DELETE_INSURANCE_PAYMENT_Dao(String pid
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(DELETE_INSURANCE_PAYMENT);
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
//son edit 04/nov/2019

    /**
     *
     * @param pid
     * @return
     * @throws SQLException
     */
    public int DELETE_PAYMENT_CREDITCARD_Dao(String pid
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(DELETE_PAYMENT_CREDITCARD);
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

    /**
     *
     * @param tdLuck
     * @return
     * @throws Exception
     */
    public String insertGwTdLuck(GwTdLucky tdLuck) throws Exception {
        try {
            getSession().save(tdLuck);
            return tdLuck.getCustAcNo();
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }
    String SEARCH_SMS_ALERT = "SELECT * FROM VW_REGISTER_SMS_NEW WHERE 1=1 ";

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

    private final String GET_LIST_REGISTERDETAIL = "select t.*, v.*, (select cardname from SMS_SCB.card_type c where c.cardtype = t.cardtype) as CARDNAME from eb_issuecard t , vw_mb_customer v where t.customer_no = v.customer_no and t.ischecked = ? and t.registertype in ('IA', 'MD', 'LO', 'CA')";

    public List<RegisterInfoDetail> getListRegisterDetail(String isChecked) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        List<RegisterInfoDetail> result = new ArrayList<>();
        try {
            preStatement = connection.prepareStatement(GET_LIST_REGISTERDETAIL);
            preStatement.setString(1, isChecked);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                RegisterInfoDetail items = new RegisterInfoDetail();
                items.setRegisterId(rs.getString("ID"));
                if (rs.getString("IDCHANNEL") != null) {
                    switch (rs.getString("IDCHANNEL")) {
                        case "01":
                            items.setIdChannel("INTERNET BANKING");
                            break;
                        case "03":
                            items.setIdChannel("MOBILE BANKING");
                            break;
                        case "05":
                            items.setIdChannel("WEBSITE SCB");
                            break;
                        default:
                            break;
                    }
                }
                items.setInsDate(rs.getString("INSDATE"));
                items.setCustomerNo(rs.getString("CUSTOMER_NO"));
                items.setBranchCode(rs.getString("BRANCHCODE"));
                items.setIdChannelUser(rs.getString("IDCHANNELUSER"));
                switch (rs.getString("REGISTERTYPE")) {
                    case "IA":
                        items.setRegisterType("Phat hanh the ATM");
                        break;
                    case "MD":
                        items.setRegisterType("phat hanh the thanh toan quoc te");
                        break;
                    case "CA":
                        items.setRegisterType("Dang ky tai khoan thanh toan");
                        break;
                    case "LO":
                        items.setRegisterType("Dang ky tai khoan thanh toan");
                        break;
                    default:
                        break;
                }
                items.setCustName(rs.getString("CUS_NAME"));
                items.setCustTel(rs.getString("CUS_TEL"));
                items.setCustAddress(rs.getString("CUS_ADDR"));
                items.setSourceAccount(rs.getString("SOURCEACCOUNT"));
                if (rs.getString("ISSUETYPE") != null) {
                    switch (rs.getString("ISSUETYPE")) {
                        case "1":
                            items.setIssueType("Phat hanh the moi");
                            break;
                        case "2":
                            items.setIssueType("Cap lai the");
                            break;
                        case "3":
                            items.setIssueType("Gia han the");
                            break;
                        case "4":
                            items.setIssueType("Phat hanh the phu");
                            break;
                        default:
                            break;
                    }
                }

                // SUNRISE
                items.setCardType(rs.getString("CARDNAME"));

                items.setFullName(rs.getString("FULLNAME"));
                items.setRelationship(rs.getString("RELATIONSHIP"));
                items.setIdNumber(rs.getString("IDNUMBER"));
                items.setIssueIdDate(rs.getString("ISSUEID_DATE"));
                items.setIssueIdPlace(rs.getString("ISSUEID_PLACE"));
                if (rs.getString("SEX") != null) {
                    switch (rs.getString("SEX")) {
                        case "F":
                            items.setSex("Nu");
                            break;
                        case "M":
                            items.setSex("Nam");
                            break;
                        default:
                            break;
                    }
                }
                items.setCardAccountNo(rs.getString("CARDACCOUNTNO"));
                items.setCardNo(rs.getString("CARDNO"));
                if (rs.getString("CARDRECIEVEMODE") != null) {
                    switch (rs.getString("CARDRECIEVEMODE")) {
                        case "1":
                            items.setCardRecieveMode("Nhan tai nha");
                            break;
                        case "2":
                            items.setCardRecieveMode("Nhan tai chi nhanh");
                            break;
                        default:
                            break;
                    }
                }
                items.setAddress(rs.getString("ADDRESS"));
                items.setLoanPurpose(rs.getString("LOANPURPOSE"));
                items.setLoanAmount(rs.getString("LOANAMOUNT"));
                items.setExpectedTime(rs.getString("EXPECTEDTIME"));
                items.setExpectInterest(rs.getString("EXPECTINTEREST"));
                items.setRePaymentSource(rs.getString("REPAYMENTSOURCE"));

                result.add(items);
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

    private final String UPDATE_REGISTERID = "update eb_issuecard t set t.ischecked = ?, t.idchecker = ?, t.idchannelchecker = ? where t.id = ? ";

    public int updateRegisterId(String isChecked, String registerId, String idChecker, String idChannelChecker) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            preStatement = connection.prepareCall(UPDATE_REGISTERID);
            preStatement.setString(1, isChecked);
            preStatement.setString(2, idChecker);
            preStatement.setString(3, idChannelChecker);
            preStatement.setString(4, registerId);
            int result = preStatement.executeUpdate();
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
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

    public String[] INSERT_SMS_PARTNER(sms_partner_request request)
            throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN  PROC_SMS_PARTNER(?,?,?, ?,?,?,?,?); END;");
            calStmt.setString(1, request.getPartnerid());
            calStmt.setString(2, request.getServicecode());
            calStmt.setString(3, request.getServicetype());
            calStmt.setString(4, request.getRequestid());
            calStmt.setString(5, request.getPhoneno());
            calStmt.setString(6, request.getContent());
            calStmt.registerOutParameter(7, Types.VARCHAR);
            calStmt.registerOutParameter(8, Types.VARCHAR);
            calStmt.execute();
            String[] result = new String[2];
            result[0] = calStmt.getString(7);
            result[1] = calStmt.getString(8);
            return result;
        } catch (SQLException sqlEx) {
            LOGGER.error("SMS_PARTNER:" + sqlEx);
            // throw new Exception(sqlEx);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    public ArrayList getOnlTranPartnersById(String idPartner) throws Exception {
        ArrayList arl = new ArrayList();
        arl.add(idPartner);
        return JDBCEngine.executeQuery(GET_ONL_TRANS_PARTNER, arl, connection);
    }

    public ArrayList getOnlPaymentByCard(String idTrans) throws Exception {
        ArrayList arls = new ArrayList();
        arls.add(idTrans);
        return JDBCEngine.executeQuery(GET_ONL_PAYMENT_BY_CARD, arls, connection);
    }

    final String INS_USER_OTHER_DEVICE = "Insert into SMS_SCB.GRANT_USER_OTHER_DEVICE(SO_CIF, USER_NAME, APPROVED, ACCESS_OTHER_DEVICE, IDUSER_MAKER, IDUSER_CHECKER, DATE_MAKER, CREATED_DATE, BRANCHCODE) Values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public int insertUserOtherDeviceOdbx(UserOtherDeviceOdbxDto userDeviceOdbx) throws Exception {
        PreparedStatement preStatement = null;
        try {
            Date nowDate = new Date();
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(INS_USER_OTHER_DEVICE);
            preStatement.setString(1, userDeviceOdbx.getSoCif());
            preStatement.setString(2, userDeviceOdbx.getUserName());
            preStatement.setString(3, userDeviceOdbx.getApproved());
            preStatement.setString(4, userDeviceOdbx.getAccessOtherDevice());
            preStatement.setString(5, userDeviceOdbx.getIdUserMarker());
            preStatement.setString(6, userDeviceOdbx.getIdUserChecker());
            preStatement.setTimestamp(7, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setTimestamp(8, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setString(9, userDeviceOdbx.getBranchCode());
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

    final String UD_USER_OTHER_DEVICE = "update GRANT_USER_OTHER_DEVICE  set APPROVED = ?, IDUSER_CHECKER=?, DATE_CHECKER=?, UPDATED_DATE = ?  where ID = ?";

    public int updateGrantUserOtherDevice(UserOtherDeviceOdbxDto userDeviceOdbx) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            Date nowDate = new Date();
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(UD_USER_OTHER_DEVICE);
            preStatement.setString(1, userDeviceOdbx.getApproved());
            preStatement.setString(2, userDeviceOdbx.getIdUserChecker());
            preStatement.setTimestamp(3, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setTimestamp(4, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setString(5, userDeviceOdbx.getId());
            int result = preStatement.executeUpdate();

            return result;
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
            throw e;
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

    final String G_UserOtherDiviceOdbx = "select * from GRANT_USER_OTHER_DEVICE WHERE ID=?";

    public ArrayList getUserOtherDeviceOdbxById(String id) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(id);
        return JDBCEngine.executeQuery(G_UserOtherDiviceOdbx, p_args, connection);
    }

    final String G_OnOffUserDiviceOdbxApproved = "select dd.USER_NAME, dd.BRANCHCODE  from GRANT_USER_OTHER_DEVICE dd where dd.USER_NAME = ? and dd.SO_CIF = ? and dd.APPROVED = ?";
    
    public ArrayList checkOnOffUserDeviceOdbxApproved(String userName, String soCif, String approved) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(userName);
        p_args.add(soCif);
        p_args.add(approved);
        
        ArrayList list = JDBCEngine.executeQuery(G_OnOffUserDiviceOdbxApproved, p_args, connection);
        return list;
    }

    final String G_PaymentCardRegisteredByCif = "select DD.ID, DD.PARTNERID, TO_CHAR(DD.VERIFYDATE, 'DD/MM/YYYY HH:MM:SS') VERIFYDATE, DD.ACCOUNTNO, DD.CARDNAME, DD.CARDNUMBER, DD.CMND, DD.DESCRIPTION  "
            + " from sms_scb.onl_payment_bycard_register dd "
            + " where dd.cardnumber in (select cust_ac_no from fcusr01.sttm_cust_account@fcatfcclink where record_stat='O' and account_class like 'CAI%' and cust_no = ?) and dd.isverified=1 and dd.isdeleted=0 and dd.tyepcard='02' "
            + " ORDER BY DD.ID DESC ";

    public ArrayList GetPaymentCardRegisteredByCif(String soCif) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(soCif);
        return JDBCEngine.executeQuery(G_PaymentCardRegisteredByCif, p_args, connection);
    }

    final String INS_PaymentCardTracking = "INSERT INTO SMS_SCB.ONL_PAYMENT_BYCARD_TRACKING(PAYMENT_CARD_ID, TYPE, BRANCHCODE, APPROVED, IDUSER_MAKER, DATE_MAKER, CREATED_DATE, SO_CIF, CARD_NUMBER, CARD_NAME, VERIFY_DATE, PARTNER_ID) Values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public int insertPaymentCardTracking(PaymentByCardTracking paymentByCardtracking) throws Exception {
        PreparedStatement preStatement = null;
        try {
            Date nowDate = new Date();
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(INS_PaymentCardTracking);
            preStatement.setString(1, paymentByCardtracking.getPaymentId());
            preStatement.setString(2, paymentByCardtracking.getType());
            preStatement.setString(3, paymentByCardtracking.getBranchCode());
            preStatement.setString(4, paymentByCardtracking.getApproved());
            preStatement.setString(5, paymentByCardtracking.getIdUserMarker());
            preStatement.setTimestamp(6, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setTimestamp(7, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setString(8, paymentByCardtracking.getSoCif());
            preStatement.setString(9, paymentByCardtracking.getCardNumber());
            preStatement.setString(10, paymentByCardtracking.getCardName());
            preStatement.setString(11, paymentByCardtracking.getVerifyDate());
            preStatement.setString(12, paymentByCardtracking.getPartnerId());

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

    final String UD_PAYMENT_BYCARD_TRACKING = "update SMS_SCB.ONL_PAYMENT_BYCARD_TRACKING  set APPROVED = ?, IDUSER_CHECKER=?, DATE_CHECKER=?, UPDATED_DATE = ?  where ID = ?";

    public int updatePaymentCardTracking(String paymentTracking, String userChecker, String approved) throws SQLException {
        PreparedStatement preStatement = null;

        try {
            Date nowDate = new Date();
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(UD_PAYMENT_BYCARD_TRACKING);
            //update ONL_PAYMENT_BYCARD_TRACKING
            preStatement.setString(1, approved);
            preStatement.setString(2, userChecker);
            preStatement.setTimestamp(3, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setTimestamp(4, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setString(5, paymentTracking);
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
                 
    final String UD_PAYMENT_BYCARD_REGISTER = "update SMS_SCB.onl_payment_bycard_register  set ISDELETED = ?, DESTROYDATE=?  where ID = ?";

    public int updatePaymentByCardRegister(String payment, String status) throws SQLException {
        PreparedStatement preStatement = null;
        try {
            Date nowDate = new Date();
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(UD_PAYMENT_BYCARD_REGISTER);
            //update onl_payment_bycard_register
            preStatement.setString(1, status);
            preStatement.setTimestamp(2, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setString(3, payment);
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

    final String G_checkPaymentCardApproved = "select *  from ONL_PAYMENT_BYCARD_TRACKING dd where DD.PAYMENT_CARD_ID = ? and dd.APPROVED = ?";

    public ArrayList checkPaymentCardApproved(String id, String approved) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(id);
        p_args.add(approved);

        ArrayList list = JDBCEngine.executeQuery(G_checkPaymentCardApproved, p_args, connection);
        return list;
    }

    final String G_PaymentCardCanceldById = "select * from ONL_PAYMENT_BYCARD_TRACKING WHERE ID=?";

    public ArrayList getPaymentCardCancelById(String id) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(id);
        return JDBCEngine.executeQuery(G_PaymentCardCanceldById, p_args, connection);
    }

    /*BAOTBQ - 10/07/2022*/
    final String INS_NTDTPersonalPaymentExtend = "INSERT INTO SMS_SCB.NTDT_PERSONAL_PAYMENT_EXTEND(NTDT_PAYMENT_ID, IDUSER_MAKER, DATE_MAKER, CREATED_DATE, BRANCHCODE, TYPE, APPROVED , REFCORE, ACCOUNTNO, THOIGIANGIAODICH, SOTIEN) Values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public int insertNtdtPaymentExtend(NtdtPaymentExtend ntdtPaymentExtend) throws Exception {
        PreparedStatement preStatement = null;
        try {
            Date nowDate = new Date();
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(INS_NTDTPersonalPaymentExtend);
            preStatement.setString(1, ntdtPaymentExtend.getNtdtPaymentId());
            preStatement.setString(2, ntdtPaymentExtend.getIdUserMarker());
            preStatement.setTimestamp(3, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setTimestamp(4, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setString(5, ntdtPaymentExtend.getBranchCode());
            preStatement.setString(6, ntdtPaymentExtend.getType());
            preStatement.setString(7, ntdtPaymentExtend.getApproved());
            preStatement.setString(8, ntdtPaymentExtend.getRefCore());
            preStatement.setString(9, ntdtPaymentExtend.getAccountNo());
            preStatement.setString(10, ntdtPaymentExtend.getThoigianGD());
            preStatement.setString(11, ntdtPaymentExtend.getSotien());
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

    final String UD_NTDT_PAYMENT_EXTEND = "update SMS_SCB.NTDT_PERSONAL_PAYMENT_EXTEND  set APPROVED = ?, IDUSER_CHECKER=?, DATE_CHECKER=?, UPDATED_DATE = ?  where ID = ?";

    public int updateNtdtPaymentExtend(String paymentExtend, String userChecker, String approved) throws SQLException {
        PreparedStatement preStatement = null;
        try {
            Date nowDate = new Date();
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(UD_NTDT_PAYMENT_EXTEND);
            //update NTDT_PERSONAL_PAYMENT_EXTEND
            preStatement.setString(1, approved);
            preStatement.setString(2, userChecker);
            preStatement.setTimestamp(3, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setTimestamp(4, new java.sql.Timestamp(nowDate.getTime()));
            preStatement.setString(5, paymentExtend);           
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

    final String G_checkNtdtPaymentApproved = "select * from NTDT_PERSONAL_PAYMENT_EXTEND dd where dd.ntdt_payment_id = ? and dd.APPROVED = ?";

    public int checkNtdtPaymentApproved(String id, String approved) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(id);
        p_args.add(approved);
        ArrayList list = JDBCEngine.executeQuery(G_checkNtdtPaymentApproved, p_args, connection);
        return (list.size() > 0) ? 1 : 0;
    }

    final String G_NtdtPaymentExtendById = "select * from NTDT_PERSONAL_PAYMENT_EXTEND WHERE ID=?";

    public ArrayList getNtdtPaymentExtendById(String id) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(id);
        return JDBCEngine.executeQuery(G_NtdtPaymentExtendById, p_args, connection);
    }

    final String GET_CIF = "{ ? = call pkg_check_account.getcif(?)}";
    public String getCifFromAccountNo(String accountNo) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(GET_CIF);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, accountNo);
            calStmt.execute();
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
    
     final String G_CollatedKieuHoiByEKYC = "select de.createdate createdated, si.partnerid, de.txndetailid, de.refcore, de.partneraccount, de.custumeraccount, de.custtype ,de.personid, de.custumername, de.passno, de.birthdate, de.address, "
            + " de.nationality, de.typetransfer, de.typecustaccount, de.bankcode, ob.bank_name bankname, de.refcore , de.amount, de.ccy, de.description, de.status"
            + " from si_trffromsi si, si_trffromsi_detail de, obdx_bene_bank ob "
            + " where si.partnerid=? "
            + " and de.status=? "
            + " and de.bankcode = ob.bank_code and SI.ID = DE.ID_MASTER "
            + " and trunc(de.createdate) = trunc(sysdate) and ((sysdate - trunc(sysdate) ) * 24 * 60) - ((de.createdate - trunc(de.createdate)) * 24 * 60)<=?";

    public ArrayList<?> CollatedKieuHoiByEKYC(String partnerId, String status, String dateCollated) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(partnerId);
        p_args.add(status);
        p_args.add(dateCollated);
        ArrayList<?> list = JDBCEngine.executeQuery(G_CollatedKieuHoiByEKYC, p_args, connection);
        
        return list;
    }
    
    final String G_CollatedKieuHoiByDate = "select de.createdate createdated, si.partnerid, de.txndetailid, de.refcore, de.partneraccount, de.custumeraccount, de.custtype ,de.personid, de.custumername, de.passno, de.birthdate, de.address, "
            + " de.nationality, de.typetransfer, de.typecustaccount, de.bankcode, ob.bank_name bankname ,de.refcore , de.amount, de.ccy, de.description, de.status "
            + " from si_trffromsi si, si_trffromsi_detail de, obdx_bene_bank ob where si.partnerid =? and trunc(de.createdate) = trunc(sysdate -1) "
            + " and SI.ID = DE.ID_MASTER and de.bankcode = ob.bank_code and de.status='100' ";
    
    public ArrayList CollatedKieuHoiByDate(String partnerId, String time, String toDate) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(partnerId);
        //p_args.add(fromDate);
        //p_args.add(toDate);
        return JDBCEngine.executeQuery(G_CollatedKieuHoiByDate, p_args, connection);
    }
    
}
