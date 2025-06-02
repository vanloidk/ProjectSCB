package scb.com.vn.dbi.controller;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateReq;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateRes;
import scb.com.vn.common.model.SqlCommand;
import scb.com.vn.common.model.cims.kht.KichHoatTheInfo;
import scb.com.vn.common.model.cims.kt.KhoaTheDetailsInfo;
import scb.com.vn.common.model.cims.kt.KhoaTheInfo;
import scb.com.vn.common.model.cims.kt.KhoaTheReq;
import scb.com.vn.common.model.cims.kt.MoKhoaTheReq;
import scb.com.vn.common.model.cims.register.FeedbackInfo;
import scb.com.vn.common.model.cims.register.RegisterInfoDetail;
import scb.com.vn.common.model.cims.taikhoan.GiaiToaTKTTReq;
import scb.com.vn.common.model.cims.taikhoan.GiaiToaTKTTRes;
import scb.com.vn.common.model.cims.taikhoan.PhongToaTKTTRes;
import scb.com.vn.common.model.cims.taikhoan.PhongToaTKTTReq;
import scb.com.vn.common.model.collated.CollatedDetail;
import scb.com.vn.common.model.cw.CardAdjustmentReq;
import scb.com.vn.common.model.cw.CardAdjustmentRes;
import scb.com.vn.common.model.cw.CardInfo;
import scb.com.vn.common.model.cw.DirectDebitRes;
import scb.com.vn.common.model.cw.SenderMSVSCardInfo;
import scb.com.vn.common.model.masterpass.MCPaymentRp;
import scb.com.vn.common.model.masterpass.MasterCardQrActionEnum;
import scb.com.vn.common.model.masterpass.PayByQRCodeRq;
import scb.com.vn.common.model.mvisa.MVISAQRRQ;
import scb.com.vn.common.model.mvisa.ResponseMessage;
import scb.com.vn.common.model.transfer.TransactionDetail;
import scb.com.vn.common.model.vnpayqr.CheckQRRp;
import scb.com.vn.common.model.vnpayqr.CheckQRRq;
import scb.com.vn.common.model.vnpayqr.PaymentQRJson;
import scb.com.vn.common.model.vnpayqr.PaymentQRRp;
import scb.com.vn.common.model.vnpayqr.PaymentQRRq;
import scb.com.vn.common.model.vnpayqr.RefundQRJson;
import scb.com.vn.common.model.vnpayqr.RefundQRRp;
import scb.com.vn.common.odbx.SCBBranch;
import scb.com.vn.ultility.DBActionEnum;
import scb.com.vn.common.model.info.SmsCustRegisInfo;
import scb.com.vn.common.model.sms.SmsDetail;
import scb.com.vn.common.model.sms.SmsInfo;
import scb.com.vn.common.model.transfer.SenderInfo;
import scb.com.vn.common.model.transfer.napas.TransferMoney247EbankReq;
import scb.com.vn.common.model.transfer.status.TransactionDetailByDate;
import scb.com.vn.common.odbx.IsExistUserEBRes;
import scb.com.vn.dbi.bo.FCCCoreBO;

import scb.com.vn.dbi.dto.*;
import scb.com.vn.dbi.dto.PmtInfoV11ResDto;
import scb.com.vn.dbi.dto.PBLBillPaidCustomerDto.PBLBillPaidCusDto;
import scb.com.vn.dbi.dto.PBLBillPaidCustomerDto.PBLBillPaidCustomerDtlForJobDto;
import scb.com.vn.dbi.dto.BillPaidCustGrpDto;
import scb.com.vn.dbi.dto.InsuranceCustInfoDto;
import scb.com.vn.model.status.transferMoney.GetStatusOfTransferMoneyReq;
import scb.com.vn.model.status.transferMoney.TransferMoneyTransactionInfo;

/**
 *
 * @author minhndb
 */
public interface IDBI extends Remote {

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
     * @throws RemoteException
     */
    public ArrayList<?> searchTBTBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchTBTBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws RemoteException;

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    public int updateStatusTBT(String id, String status) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchIBLBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchIBLBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws RemoteException;

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    public int updateStatusIBL(String id, String status) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchTVSIBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchTVSIBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws RemoteException;

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    public int updateStatusTVSI(String id, String status) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchTVSI_Bill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchTVSI_BillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws RemoteException;

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    public int updateStatus_TVSI(String id, String status) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchVTCBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchVTCBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws RemoteException;

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    public int updateStatusVTC(String id, String status) throws RemoteException;

    /**
     *
     * @param P_DATE
     * @return
     * @throws RemoteException
     */
    public String working_day(Date P_DATE) throws RemoteException;

    /**
     *
     * @param P_DATE
     * @param P_TYPE
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getListINS(Date P_DATE, String P_TYPE) throws RemoteException;

    /**
     *
     * @param servType
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getListPartner(String servType) throws RemoteException;

    /**
     *
     * @param P_DATE
     * @param P_TYPE
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getListINS_BKN(Date P_DATE, String P_TYPE) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getAllListpRoviderIns() throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getInsByidpayment(int id) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getAllPartnerMNL() throws RemoteException;

    /**
     *
     * @param pol
     * @param due
     * @param pre
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getBcBilId(String pol, String due, String pre) throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getInsurencePayByID(int id) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getSmlStatus() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getOnlTranStatus() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getRetailtatus() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getInsStatus() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getSiStatus() throws RemoteException;

    /**
     *
     * @param fromDateSearch
     * @param toDateSearch
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getlistAllInsPayment(String fromDateSearch, String toDateSearch) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public int insertIStatusYes() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public int insertIStatusNo() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public int insertBCBillHistory() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public int deleteBCBill() throws RemoteException;

    /**
     *
     * @param ownerId
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getListPolNum(String ownerId) throws RemoteException;

    /**
     *
     * @param polNum
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getListPremtyp(String polNum) throws RemoteException;

    /**
     *
     * @param premtyp
     * @param polNum
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getListDataPay(String premtyp, String polNum) throws RemoteException;

    /* ====== Test chuoi tu namespace dbi.server ====== */
    /**
     *
     * @return @throws RemoteException
     */
    public String getString() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getAllListInsurancePayment() throws RemoteException;

    /**
     *
     * @param sql
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAllListIns(String sql) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getAllListInsuranceTrn() throws RemoteException;

    /**
     *
     * @param bcbill
     * @return
     * @throws RemoteException
     */
    public int insertBCBill(BCBill bcbill) throws RemoteException;

    /**
     *
     * @param bcbill
     * @return
     * @throws RemoteException
     */
    public int insertBCBill(List<BCBill> bcbill) throws RemoteException;

    /**
     *
     * @param test
     * @return
     * @throws RemoteException
     */
    public String getString(String test) throws RemoteException;

    // public void stopDBIServer() throws RemoteException;
    /**
     *
     * @param xml
     * @param fileXslt
     * @param fileOut
     * @return
     * @throws RemoteException
     */
    public int processorPDF(String xml, String fileXslt, String fileOut) throws RemoteException;

    /* ====== LOG ====== */
    /**
     *
     * @param logger
     * @param lvl
     * @param msg
     * @return
     * @throws RemoteException
     */
    public int insertLog(String logger, String lvl, String msg) throws RemoteException;

    /* ====== GATEWAY ====== */
    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<Hashtable<String, String>> getallPartner() throws RemoteException;

    /**
     *
     * @param accesskey
     * @return
     * @throws RemoteException
     */
    public ArrayList<Hashtable<String, String>> getPartnerByAccesskey(String accesskey) throws RemoteException;

    /* ====== TOKENKEY ====== */
    /**
     *
     * @param serialno
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getTokenkeybankBySerial(String serialno) throws RemoteException;

    /* ====== SMS ====== */
    /**
     *
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws RemoteException
     */
    public int sendSMS(String mobile, String content, String servicecode, String requestid) throws RemoteException;

    /**
     *
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws RemoteException
     */
    public int sendSMS8149(String mobile, String content, String servicecode, String requestid) throws RemoteException;

    /**
     *
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws RemoteException
     */
    public int sendSMSTemp(String mobile, String content, String servicecode, String requestid) throws RemoteException;

    /**
     *
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws RemoteException
     */
    public int sendSMSTNB(String mobile, String content, String servicecode, String requestid) throws RemoteException;

    //duytxa08072015 gui tin nhan lock dich vu ebank khi no phi 
    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    public int lockEbankService(String custno) throws RemoteException;
    //endduytxa08072015

    //duytxa028062017 gui tin nhan lock dich vu khi no phi sms
    /**
     *
     * @param mobile
     * @param custno
     * @return
     * @throws RemoteException
     */
    public int lockSMS(String mobile, String custno) throws RemoteException;

    /**
     *
     * @param custno
     * @param thangnam
     * @return
     * @throws RemoteException
     */
    public ArrayList getmobileuserthuphisms(String custno, String thangnam) throws RemoteException;
    //endduytxa028062017

//    public ArrayList<Hashtable<String, String>> getSMSFromTNB() throws RemoteException;
    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    public int updateStatus(String id, String status) throws RemoteException;

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getAllSmsReceiver() throws Exception;

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int doMoveSmsReceiverToHistory(String id, String status) throws Exception;

    /* ====== FCDB ====== */
    /**
     *
     * @param acccount
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAccountCASA(String acccount) throws RemoteException;

    //duytxa 07/09/2015 for feeautosms
    /**
     *
     * @param custno
     * @param acctype
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAccountCASAMaxbalanceFeesms(String custno, String acctype) throws RemoteException;
    //endduytxa 07/09/2015 for feeautosms

    //duytxa 20/06/2017 for feeautosms
    /**
     *
     * @param custno
     * @param acctype
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAccountCASAMaxbalanceFeesmsKHDN(String custno, String acctype) throws RemoteException;
    //endduytxa 20/06/2017 for feeautosms

    /**
     *
     * @param acccount
     * @param rownum
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getDetailsAccountCASA(String acccount, int rownum) throws RemoteException;

    /**
     *
     * @param acccountTD
     * @param branchAcc
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAccountTD(String acccountTD, String branchAcc) throws RemoteException;

    /**
     *
     * @param accounttd
     * @param cif
     * @param idcard
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getListAccountTDDetail(String accounttd, String cif, String idcard) throws RemoteException;

    /**
     *
     * @param ccyfrom
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getFXRates(String ccyfrom) throws RemoteException;

    /**
     *
     * @param mobile
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getCustomerSMSByMobile(String mobile) throws RemoteException;

    /**
     *
     * @param idEntity
     * @param idChannel
     * @param idChanneluser
     * @return
     * @throws RemoteException
     */
    public ArrayList getCustomerInfo(String idEntity, String idChannel, String idChanneluser) throws RemoteException;

    /**
     *
     * @param idEntity
     * @param idChanneluser
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getCustomerInfoByIdChannelUser(String idEntity, String idChanneluser) throws RemoteException;

    /**
     *
     * @param idEntity
     * @param userid
     * @param idChannel
     * @param idChanneluser
     * @param newpassword
     * @return
     * @throws RemoteException
     */
    public String generatePassword(String idEntity, String userid, String idChannel, String idChanneluser,
            String newpassword) throws RemoteException;

    /**
     *
     * @param idEntity
     * @param userid
     * @param idChannel
     * @param idChanneluser
     * @param newpassword
     * @return
     * @throws RemoteException
     */
    public int changePassword(String idEntity, String userid, String idChannel, String idChanneluser, String newpassword)
            throws RemoteException;

    /**
     *
     * @param idEntity
     * @param userid
     * @param idChannel
     * @param idChanneluser
     * @param newpassword
     * @param flagforcechangepwd
     * @return
     * @throws RemoteException
     */
    public int changePasswordWithFlagForce(String idEntity, String userid, String idChannel, String idChanneluser,
            String newpassword, String flagforcechangepwd) throws RemoteException;

    /**
     *
     * @param idEntity
     * @param idChannel
     * @param idUser
     * @param passInput
     * @param passData
     * @return
     * @throws RemoteException
     */
    public boolean checkAuthenPass(String idEntity, String idChannel, String idUser, String passInput, String passData)
            throws RemoteException;

    /**
     *
     * @param identity
     * @param idchannel
     * @param iduser
     * @param usertype
     * @param idtxn
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getRoleTxnByTxnCode(String identity, String idchannel, String iduser, String usertype,
            String idtxn) throws RemoteException;

    /**
     *
     * @param identity
     * @param idchannel
     * @param iduser
     * @param usertype
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAllRoleTxn(String identity, String idchannel, String iduser, String usertype)
            throws RemoteException;

    /**
     *
     * @param idcustomer
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getCustomerInfoFCC(String idcustomer) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getUserAdmin() throws RemoteException;

    /**
     *
     * @param phonenum
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getLastestUserByPhoneNum(String phonenum) throws RemoteException;

    /**
     *
     * @param idchannel
     * @param iduser
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> isHaveRoleFTByIduser(String idchannel, String iduser) throws RemoteException;

    /**
     *
     * @param account
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getCustInfoIBByAccount(String account) throws RemoteException;

    /**
     *
     * @param idchanneluser
     * @param firstname
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> searchUser(String idchanneluser, String firstname) throws RemoteException;

    /**
     *
     * @param accounttd
     * @param cif
     * @param idcard
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> findListAccountTD(String accounttd, String cif, String idcard) throws RemoteException;

    // public ArrayList<?> getListAccountTDByAccounts(ArrayList<?>
    // listAccountTd) throws RemoteException;
    /**
     *
     * @param accounttd
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAccountTDByAccountTd(String accounttd) throws RemoteException;

    /**
     *
     * @param phoneNumber
     * @param defaultAcc
     * @return
     * @throws RemoteException
     */
    public boolean updateOperativeAccount(String phoneNumber, String defaultAcc) throws RemoteException;

    /**
     *
     * @param kyhan
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getTermDepositRate(String kyhan) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getListBranch() throws RemoteException;

    /**
     *
     * @param account
     * @return
     * @throws RemoteException
     */
    public ArrayList getAccountBranch(String account) throws RemoteException;

    /* ====== EXTEND SCB BANKING====== */
    // ETS
    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getListMstTxn() throws RemoteException;

    /**
     *
     * @param id_entity
     * @param usertype
     * @param idchannel
     * @param description
     * @param isdefault
     * @param createdby
     * @return
     * @throws RemoteException
     */
    public int insertMstRole(String id_entity, String usertype, String idchannel, String description, String isdefault,
            String createdby) throws RemoteException;

    /**
     *
     * @param id_entity
     * @param usertype
     * @param idchannel
     * @param idrole
     * @return
     * @throws RemoteException
     */
    public int deleteMstRole(String id_entity, String usertype, String idchannel, String idrole) throws RemoteException;

    /**
     *
     * @param idrole
     * @return
     * @throws RemoteException
     */
    public int deleteRoleTxn(String idrole) throws RemoteException;

    /**
     *
     * @param idrole
     * @param idtxn
     * @param flginit
     * @return
     * @throws RemoteException
     */
    public int insertRoleTxn(String idrole, String idtxn, String flginit) throws RemoteException;

    /**
     *
     * @param usertype
     * @param description
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> searchMstRole(String usertype, String description) throws RemoteException;

    /**
     *
     * @param idrole
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> searchRoleTxn(String idrole) throws RemoteException;

    /**
     *
     * @param mstuser
     * @return
     * @throws RemoteException
     */
    public int insertMstUser(EtsMstuser mstuser) throws RemoteException;

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
     * @throws RemoteException
     */
    public int insertChannelUserEBES(String id_entity, String iduser, String idchanneluser, String idchannel,
            String password, String lockflag, String flagforcechanngepwd) throws RemoteException;

    /**
     *
     * @param id_entity
     * @param iduser
     * @param idchannel
     * @param idrole
     * @return
     * @throws RemoteException
     */
    public int insertUserRole(String id_entity, String iduser, String idchannel, String idrole) throws RemoteException;

    /**
     *
     * @param idchanneluser
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getUserByIdChannelUser(String idchanneluser) throws RemoteException;

    // SMS
    /**
     *
     * @param smsuserpin
     * @return
     * @throws RemoteException
     */
    public int insertUserPin2(Sms_UserPin2 smsuserpin) throws RemoteException;

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getUserPin2(String idchanneluser, String usertype) throws RemoteException;

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @return
     * @throws RemoteException
     */
    public int updAccessSucc_UserPin2(String idchanneluser, String usertype) throws RemoteException;

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @return
     * @throws RemoteException
     */
    public int updAccessFail_UserPin2(String idchanneluser, String usertype) throws RemoteException;

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @param pwdtxn
     * @param lenpwd
     * @param flagforcechangepwdtxn
     * @return
     * @throws RemoteException
     */
    public int changePwd_UserPin2(String idchanneluser, String usertype, String pwdtxn, int lenpwd,
            String flagforcechangepwdtxn) throws RemoteException;

    /**
     *
     * @param idchanneluserOld
     * @param idchanneluserNew
     * @param usertype
     * @return
     * @throws RemoteException
     */
    public int changeIdChanneluser_UserPin2(String idchanneluserOld, String idchanneluserNew, String usertype)
            throws RemoteException;

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
     * @throws RemoteException
     */
    public int insertWaitResponse(String id_entity, String idchannel, String usertype, String idchanneluser,
            String typecmd, String smsmessage, String demonstring) throws RemoteException;

    /**
     *
     * @param id_entity
     * @param idchannel
     * @param usertype
     * @param idchanneluser
     * @param typecmd
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAllListWaitResponse(String id_entity, String idchannel, String usertype,
            String idchanneluser, String typecmd) throws RemoteException;

    /**
     *
     * @param matinhthanh
     * @param quan
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAllListATM(String matinhthanh, String quan) throws RemoteException;

    /**
     *
     * @param matinhthanh
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAllListATM_ext(String matinhthanh) throws RemoteException;

    /**
     *
     * @param matinhthanh
     * @param quan
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAllListBranch_main(String matinhthanh, String quan) throws RemoteException;

    /**
     *
     * @param matinhthanh
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAllListBranch_ext(String matinhthanh) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getAllListNHLM() throws RemoteException;

    /**
     *
     * @param idchanneluser
     * @param typeuser
     * @param result
     * @param msglog
     * @param idmarker
     * @return
     * @throws RemoteException
     */
    public int insSmsLog(String idchanneluser, String typeuser, String result, String msglog, String idmarker)
            throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getAllListServiceType() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getAllListServiceTypeOfAutoPay() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getAllListProvider() throws RemoteException;

    /**
     *
     * @param idservicetype
     * @param idprovider
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getPartnerServiceByPs(String idservicetype, String idprovider) throws RemoteException;

    /**
     *
     * @param ppsid
     * @return
     * @throws RemoteException
     */
    public PblPartnerservice getPartnerServiceById(PblPartnerserviceId ppsid) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getAllListPartnerService() throws RemoteException;

    /**
     *
     * @param idprovider
     * @param idservicetype
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getPartnerService(String idprovider, String idservicetype) throws RemoteException;

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
     * @throws RemoteException
     */
    public int insertPaybillLogDB(String idpartner, String idservicetype, String idprovider, String channel, String result, String msglog,
            String idmarker, String description) throws RemoteException;

    /**
     *
     * @param pb
     * @return
     * @throws RemoteException
     */
    public int insertPaybillBillPaid(PblBillpaid pb) throws RemoteException;

    /**
     *
     * @param pb
     * @throws RemoteException
     */
    public void updatePaybillBillPaid(PblBillpaid pb) throws RemoteException;

    //longle (S)
    /**
     *
     * @param idBillPaid
     * @param partnerDetailId
     * @param partnerId
     * @param accountTo
     * @return
     * @throws RemoteException
     */
    public int insertBillPaidDetail(int idBillPaid, String partnerDetailId, String partnerId, String accountTo) throws RemoteException;

    /**
     *
     * @param idschool
     * @return
     * @throws RemoteException
     */
    public String getAccountProvider(String idschool) throws RemoteException;

    /**
     *
     * @param status
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getSMSFitcom(int status) throws RemoteException;

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    public int updateStatusSMSFitcom(int id, int status) throws RemoteException;

    //
    /**
     *
     * @param pPartnerID
     * @return
     * @throws RemoteException
     */
    public String EVNHN_CreateTranscode(String pPartnerID) throws RemoteException;

    /**
     *
     * @param id
     * @param mobile
     * @param content
     * @param serviceType
     * @param serviceCode
     * @return
     * @throws RemoteException
     */
    public int insertSMS(int id, String mobile, String content, String serviceType, String serviceCode) throws RemoteException;

    /**
     *
     * @param id
     * @param mobile
     * @param content
     * @param serviceType
     * @param user
     * @return
     * @throws RemoteException
     */
    public int insertSmsSendLog(int id, String mobile, String content, String serviceType, String user) throws RemoteException;

    /**
     *
     * @param idmsg
     * @param mobile
     * @param content
     * @param idfile
     * @param typemessage
     * @return
     * @throws RemoteException
     */
    public int insertFileDetail(int idmsg, String mobile, String content, int idfile, String typemessage) throws RemoteException;

    /**
     *
     * @param id
     * @param messagetype
     * @param filename
     * @param iduser
     * @param desc
     * @return
     * @throws RemoteException
     */
    public int uploadfileSMS(int id, String messagetype, String filename, String iduser, String desc) throws RemoteException;

    /**
     *
     * @param idfile
     * @param isapproved
     * @param iduser
     * @return
     * @throws RemoteException
     */
    public int approveFileSMS(int idfile, String isapproved, String iduser) throws RemoteException;

    /**
     *
     * @param idfile
     * @param isapproved
     * @return
     * @throws RemoteException
     */
    public int updateStatusSMS(int idfile, String isapproved) throws RemoteException;

    /**
     *
     * @param idmsg
     * @return
     * @throws RemoteException
     */
    public int updateSendMSG(int idmsg) throws RemoteException;

    /**
     *
     * @param idfile
     * @param send_flag
     * @param iduser
     * @return
     * @throws RemoteException
     */
    public int updateSendFile(int idfile, String send_flag, String iduser) throws RemoteException;

    /**
     *
     * @param fromDateSearch
     * @param toDateSearch
     * @param statusfile
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> searchFileSMS(String fromDateSearch, String toDateSearch, String statusfile) throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getFileDetail(String id) throws RemoteException;

    //Modify CustAlertTD
    /**
     *
     * @param cust_no
     * @return
     * @throws RemoteException
     */
    public int writelogSMSAlertTD(String cust_no) throws RemoteException;
    //Modify CustAlertTD

    /*CK Tan Viet (S)*/
    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getAllListPartnerFI() throws RemoteException;

    /**
     *
     * @param partnerId
     * @return
     * @throws RemoteException
     */
    public String getPartnerNameById(String partnerId) throws RemoteException;

    /**
     *
     * @param servicetype
     * @param branchcode
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getApproveTranferFI(String servicetype, String branchcode) throws RemoteException;

    /**
     *
     * @param si
     * @return
     * @throws RemoteException
     */
    public int insertTrftToSI(SITrftToSIAuth si) throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public SITrftToSIAuth getTrftToSIById(int id) throws RemoteException;

    /**
     *
     * @param si
     * @throws RemoteException
     */
    public void updateTrftToSI(SITrftToSIAuth si) throws RemoteException;

    /**
     *
     * @param fromDateSearch
     * @param toDateSearch
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getlistAllSI(String fromDateSearch, String toDateSearch) throws RemoteException;

    /*CK Tan Viet (E)*/

 /*Thanh toán dư nợ thẻ tín dụng tại quầy (S)*/
    /**
     *
     * @param cif
     * @param loc
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getListCard(String cif, String loc) throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getCardInfoByID(String id) throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getPayCCInfoByID(int id) throws RemoteException;

    /**
     *
     * @param cif
     * @param loc
     * @param statuscode
     * @param branchcode
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> searchPayCCAll(String cif, String loc, String statuscode, String branchcode, String fromdate, String todate) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchPayCreditCard(String cif, String loc, String statuscode, String branchcode, String fromdate, String todate, String beginRow, String endRow) throws RemoteException;

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
     * @throws RemoteException
     */
    public String transferFCUBS(String glAcc, BigDecimal amount, String content, String idcardName, String idcardAddress, String idcard, String idcardDob, String user_maker, String user_checker, String branchcard) throws RemoteException;

    /**
     *
     * @param custAcc
     * @param amount
     * @param content
     * @param user_maker
     * @param user_checker
     * @return
     * @throws RemoteException
     */
    public String transferFCUBSCash(String custAcc, BigDecimal amount, String content, String user_maker, String user_checker) throws RemoteException;

    public String transferFCUBSCashv2(String custAcc, BigDecimal amount, String content, String user_maker, String user_checker, String noiCap, String ngayCap, String sodt, String nguoiGiaoDich, String diaChi, String cmnd) throws RemoteException;

    /**
     *
     * @param iduser_checker
     * @param idchanneluser_checker
     * @param isapproved
     * @param ref_fcubs
     * @param status
     * @param id
     * @param so_ct
     * @throws RemoteException
     */
    public void updatePayCC(String iduser_checker, String idchanneluser_checker, String isapproved, String ref_fcubs, String status, int id, String so_ct) throws RemoteException;

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
     * @param sdtKh
     * @param noiCapCMND
     * @return
     * @throws RemoteException
     */
    public int insPayCreditCard(int id, String cif, String loc, String cardtype, String cardno, String expi_date, String cardname, String branch_card, BigDecimal sotien_dtt,
            BigDecimal sotien_min, BigDecimal sotien_sk, BigDecimal sotien_duno, BigDecimal sotien_tt, String paymentmethod, String custAcNo, String fullname, String address,
            String custNo, String iduser_marker, String idchanneluser_maker, String isapproved, String branchcode, String idcard, String idcardDob, String idcardName, String idcardAddress) throws RemoteException;

    public int insPayCreditCardv2(int id, String cif, String loc, String cardtype, String cardno, String expi_date, String cardname, String branch_card, BigDecimal sotien_dtt,
            BigDecimal sotien_min, BigDecimal sotien_sk, BigDecimal sotien_duno, BigDecimal sotien_tt, String paymentmethod, String custAcNo, String fullname, String address,
            String custNo, String iduser_marker, String idchanneluser_maker, String isapproved, String branchcode, String idcard, String idcardDob, String idcardName, String idcardAddress,
            String sdtKh, String noiCapCMND) throws RemoteException;

    /*Thanh toán dư nợ thẻ tín dụng tại quầy (E)*/

 /*Đối soát Napas (S)*/
    /**
     *
     * @param np
     * @return
     * @throws RemoteException
     */
    public int InsertNapasEcomCollated(NapasCollate np) throws RemoteException;

    /**
     *
     * @return @throws Exception
     */
    public List<NapasCollate> getOutNapasEcomCollate() throws Exception;

    /**
     *
     * @param ID
     * @param pCoreref
     * @param pStatus
     * @throws Exception
     */
    public void UpdateRefundTransferNAPAS(String ID, String pCoreref, String pStatus) throws Exception;

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
     * @throws Exception
     */
    public String[] CheckRefundTransferBanknet(String pPartnerID, String pMerchanID,
            String pTransID, String pRefundTransID,
            BigDecimal pRefundAmount, String pCCY, String pADDINFO, String pLocalDatetime) throws Exception;

    /*Đối soát Napas (E)*/
 /*Mua Bao hiem (S)*/
    /**
     *
     * @param ins
     * @return
     * @throws RemoteException
     */
    public int insertInsuranceInfo(Insurance ins) throws RemoteException;

    /**
     *
     * @param ins
     * @throws RemoteException
     */
    public void updateStatusInsurance(Insurance ins) throws RemoteException;

    /**
     *
     * @param hour
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> GetlistInsuranceToExport(int hour) throws RemoteException;

    /*Mua Bao hiem (E)*/

 /*Mua the cao (S)*/
    /**
     *
     * @param bc
     * @return
     * @throws RemoteException
     */
    public int insertBuyCard(RTBuyCardDTO bc) throws RemoteException;

    /**
     *
     * @param bc
     * @throws RemoteException
     */
    public void updateStatusBuyCard(RTBuyCardDTO bc) throws RemoteException;

    /**
     *
     * @param cd
     * @return
     * @throws RemoteException
     */
    public int insertBuyCardDetail(RTCardDetailDTO cd) throws RemoteException;

    /*Mua the cao (E)*/

 /*Thanh toán Bao hiem (S)*/
    /**
     *
     * @param payins
     * @return
     * @throws RemoteException
     */
    public int insertPayIns(InsurancePayment payins) throws RemoteException;

    /**
     *
     * @param payins
     * @throws RemoteException
     */
    public void updateStatusPayIns(InsurancePayment payins) throws RemoteException;

    /**
     *
     * @param id
     * @param amount
     * @throws RemoteException
     */
    public void updateStatusBCBill(int id, long amount) throws RemoteException;

    /*Thanh toán Bao hiem (E)*/
    //longle (E)
    //duytxa08072015 bo sung cho thu phi tu dong
    /**
     *
     * @param pb
     * @throws RemoteException
     */
    public void updateSmsThuPhi(SmsThuphi pb) throws RemoteException;

    public int smsThuPhiDetails(SmsThuphi pb, SqlCommand action) throws RemoteException;

    //endduytxa08072015
    /**
     *
     * @param idbillpaid
     * @return
     * @throws RemoteException
     */
    public int deletePaybillBillPaid(String idbillpaid) throws RemoteException;

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
     * @throws RemoteException
     */
    public int insertPaybillBillPaidDetail(int idbillpaid, String idbill, String idcustomer, String custname,
            String address, String period, long amount, int result, String description, String tracenumber,
            String markerid) throws RemoteException;

    /**
     *
     * @param idpartner
     * @param idservicetype
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getPaybillAttributes(String idpartner, String idservicetype) throws RemoteException;

    /**
     *
     * @param idbillpaid
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getPaybillBillPaid(String idbillpaid) throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getPaybillInfoById(String id) throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public PblBillpaid getPaybillBillPaidById(int id) throws RemoteException;

    /**
     *
     * @param idbillpaid
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getPaybillBillPaidDetail(String idbillpaid) throws RemoteException;

    /**
     * Cap nhat Ref FCUBS theo dieu kien IDBillPaid.
     *
     * @param ref_fcubs : Ref FCUBS tra ra tu FCUBS
     * @param idbillpaid
     * @return 1: Thanh cong; -1: loi; -99: error system.
     * @exception RemoteException if the remote invocation fails.
     */
    public int updRefFcubsByIdbillpaid(String ref_fcubs, String idbillpaid) throws RemoteException;

    /**
     *
     * @param status
     * @param idbillpaid
     * @return
     * @throws RemoteException
     */
    public int updStatusByIdbillpaid(String status, String idbillpaid) throws RemoteException;

    /* ============ BAT DAU AUTOREG ============ */
    /**
     *
     * @return @throws RemoteException
     */
    public int getIdSeqAutoRegContract() throws RemoteException;

    /**
     *
     * @param parc
     * @return
     * @throws RemoteException
     */
    public String insAutoRegContract(PblAutoRegContract parc) throws RemoteException;

    /**
     *
     * @param parc
     * @throws RemoteException
     */
    public void updAutoRegContract(PblAutoRegContract parc) throws RemoteException;

    /**
     *
     * @param parc
     * @throws RemoteException
     */
    public void delAutoRegContract(PblAutoRegContract parc) throws RemoteException;

    /**
     *
     * @param pblautoreg
     * @return
     * @throws RemoteException
     */
    public int insAutoReg(PblAutoReg pblautoreg) throws RemoteException;

    /**
     *
     * @param pblautoreg
     * @return
     * @throws RemoteException
     */
    public int updAutoReg(PblAutoReg pblautoreg) throws RemoteException;

    /**
     *
     * @param pblautoreg
     * @return
     * @throws RemoteException
     */
    public int delAutoReg(PblAutoReg pblautoreg) throws RemoteException;

    /**
     *
     * @param parc
     * @return
     * @throws RemoteException
     */
    public String insAutoRegContractTemp(PblAutoRegContract parc) throws RemoteException;

    /**
     *
     * @param parc
     * @throws RemoteException
     */
    public void updAutoRegContractTemp(BPblAutoRegContract parc) throws RemoteException;

    /**
     *
     * @param bparc
     * @throws RemoteException
     */
    public void delAutoRegContractTemp(BPblAutoRegContract bparc) throws RemoteException;

    /**
     *
     * @param pblautoreg
     * @return
     * @throws RemoteException
     */
    public int insAutoRegTemp(PblAutoReg pblautoreg) throws RemoteException;

    /**
     *
     * @param pblautoreg
     * @return
     * @throws RemoteException
     */
    public int updAutoRegTemp(BPblAutoReg pblautoreg) throws RemoteException;

    /**
     *
     * @param pblautoreg
     * @return
     * @throws RemoteException
     */
    public int delAutoRegTemp(BPblAutoReg pblautoreg) throws RemoteException;

    /**
     *
     * @param idcontract
     * @return
     * @throws RemoteException
     */
    public int execApproveAutoReg(String idcontract) throws RemoteException;

    /**
     *
     * @param idcontract
     * @return
     * @throws RemoteException
     */
    public PblAutoRegContract getAutoRegContractByIdContract(String idcontract) throws RemoteException;

    /**
     *
     * @param idcontract
     * @return
     * @throws RemoteException
     */
    public boolean isExsistContractByIdContract(String idcontract) throws RemoteException;

    /**
     *
     * @param idcontract
     * @return
     * @throws RemoteException
     */
    public BPblAutoRegContract getAutoRegContractByIdContractTemp(String idcontract) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getSmsAlertNotify() throws RemoteException;

    /**
     *
     * @param idmessage
     * @param status
     * @param msgnew
     * @return
     * @throws RemoteException
     */
    public int updSmsAlertNotifyByIdMessage(String idmessage, String status, String msgnew) throws RemoteException;

    /**
     *
     * @param idchanneluser
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getChannelUserByIdChannelUser(String idchanneluser) throws RemoteException;

    /**
     *
     * @param iduser
     * @param id_entity
     * @param idchannel
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getListUserRoleByIduser(String iduser, String id_entity, String idchannel)
            throws RemoteException;

    /**
     *
     * @param accounttd
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> searchCustAlertTd(String accounttd) throws RemoteException;

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws RemoteException
     */
    public int insertAlertAccountTd(SmsCustAlertTd smscustalerttd) throws RemoteException;

    /**
     *
     * @param id_entity
     * @param iduser
     * @param idchanneluser
     * @param idchannel
     * @return
     * @throws Exception
     */
    public int deleteIdChanneluser_UserPin2(String id_entity, String iduser, String idchanneluser, String idchannel)
            throws Exception;

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAccountTDById(SmsCustAlertTd smscustalerttd) throws RemoteException;

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws RemoteException
     */
    public int updateAccountTDMobileById(SmsCustAlertTd smscustalerttd) throws RemoteException;

    /**
     *
     * @param cust_no
     * @param registertype
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> findHistoryListAccountTD(String cust_no, String registertype) throws RemoteException;

    /**
     *
     * @param cust_no
     * @param registertype
     * @param idcard
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> findHistoryListAccountTD(String cust_no, String registertype, String idcard) throws RemoteException;

    /**
     *
     * @param mstchanneluser
     * @return
     * @throws RemoteException
     */
    public int changePasswordEBES(EtsMstchanneluser mstchanneluser) throws RemoteException;

    /**
     *
     * @param idalert
     * @param mobile
     * @param message
     * @return
     * @throws RemoteException
     */
    public int insertSmsSenderLog(String idalert, String mobile, String message) throws RemoteException;

    /**
     *
     * @param idservicetype
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getProviderByIdServiceType(String idservicetype) throws RemoteException;

    /**
     *
     * @param pblautoreghist
     * @return
     * @throws RemoteException
     */
    public int insAutoRegHistory(PblAutoRegHistory pblautoreghist) throws RemoteException;

    /**
     *
     * @param pblautoreg
     * @return
     * @throws RemoteException
     */
    public List searchAutoReg(PblAutoReg pblautoreg) throws RemoteException;

    /**
     *
     * @param vwautoreg
     * @return
     * @throws RemoteException
     */
    public List searchAutoReg(VwAutoReg vwautoreg) throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public PblAutoReg searchAutoRegById(int id) throws RemoteException;

    /**
     *
     * @param idcontract
     * @return
     * @throws RemoteException
     */
    public PblAutoRegContract searchAutoRegContractById(String idcontract) throws RemoteException;

    /**
     *
     * @param idcontract
     * @return
     * @throws RemoteException
     */
    public BPblAutoRegContract searchAutoRegContractByIdTemp(String idcontract) throws RemoteException;

    /**
     *
     * @param branchcode
     * @return
     * @throws RemoteException
     */
    public List getListTransActByBranchCode(String branchcode) throws RemoteException;

    /**
     *
     * @param transcode
     * @param branchcode
     * @return
     * @throws RemoteException
     */
    public List getListTransActDetail(String transcode, String branchcode) throws RemoteException;

    /**
     *
     * @param date
     * @return
     * @throws RemoteException
     */
    public List getListBillPaidByDate(String date) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public List getListAutoRegToPay() throws RemoteException;

    //duytxa08072015 cho truong hop thu phi sms tu dong
    /**
     *
     * @return @throws RemoteException
     */
    public List getListAutoFeePay() throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public SmsThuphi getSmsThuphiById(String id) throws RemoteException;

    //end duytxa08072015
    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public VwCustAccount getCustAccountByAccountNo(String accountno) throws RemoteException;

    public VwCustAccountNew getCustAccountByAccountNoNew(String accountno) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public VwCustAccount getCustAccountFcdbByAccountNo(String accountno) throws RemoteException;

    /* ====== IBS ====== */
    /**
     *
     * @param custid
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getIbsUserByCustId(String custid) throws RemoteException;

    /**
     *
     * @param isChangePwd
     * @param userid
     * @return
     * @throws RemoteException
     */
    public int updateIbsUserChangePwd(String isChangePwd, String userid) throws RemoteException;

    /* ====== TNB ====== */
    /**
     *
     * @param mobile
     * @return
     * @throws RemoteException
     */
    public int getCountMobile(String mobile) throws RemoteException;

    /**
     *
     * @param vub
     * @return
     * @throws RemoteException
     */
    public List getUserBranch(VwUserbranch vub) throws RemoteException;

    /* ====== EBK PROCESS ====== */
    /**
     *
     * @param pep
     * @return
     * @throws RemoteException
     */
    public int insEbkProcess(PblEbkProcess pep) throws RemoteException;

    /**
     *
     * @param pep
     * @throws RemoteException
     */
    public void updEbkProcess(PblEbkProcess pep) throws RemoteException;

    /**
     *
     * @param transcode
     * @return
     * @throws RemoteException
     */
    public PblEbkProcess getEbkProcessById(int transcode) throws RemoteException;

    /**
     *
     * @param pc
     * @return
     * @throws RemoteException
     */
    public int insPblCollated(PblCollated pc) throws RemoteException;

    /**
     *
     * @param pc
     * @throws RemoteException
     */
    public void updPblCollated(PblCollated pc) throws RemoteException;

//    public int insPblLog(PblLog plg) throws RemoteException;
//    public PblCollated findPblCollatedById(int id) throws RemoteException;
//    public List getListCollatedByDate(String date, String idpartner) throws RemoteException;
    /**
     *
     * @param idscreen
     * @return
     * @throws RemoteException
     */
    public PblEbkScreen getEbkScreenById(int idscreen) throws RemoteException;

    /*chuong bo sung paybill*/
    /**
     *
     * @param pct
     * @return
     * @throws RemoteException
     */
    public int insPblCustTemplate(PblCustTemplate pct) throws RemoteException;

    /**
     *
     * @param pct
     * @return
     * @throws RemoteException
     */
    public boolean delPblCustTemplate(PblCustTemplate pct) throws RemoteException;

    /**
     *
     * @param iduser
     * @param idservicetype
     * @param idprovider
     * @return
     * @throws Exception
     */
    public List getPblCustTemplates(String iduser, String idservicetype, String idprovider) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public PblCustTemplate getPblCustTemplate(int id) throws Exception;

    /**
     *
     * @param idscreen
     * @return
     * @throws RemoteException
     */
    public PblEbkScreen getEbkScreenById_new(PblEbkScreenId idscreen) throws RemoteException;

    /**
     *
     * @param pIduser
     * @param pCust_ac_no
     * @return
     * @throws RemoteException
     */
    public int checkIduserAndCust_ac_no(String pIduser, String pCust_ac_no) throws RemoteException;

    /**
     *
     * @param ipaddress
     * @param pIduser
     * @param pIdsession
     * @return
     * @throws RemoteException
     */
    public int checkusersession_valid(String ipaddress, String pIduser, String pIdsession) throws RemoteException;

    /**
     *
     * @param pIdsession
     * @return
     * @throws RemoteException
     */
    public String getsession_lang(String pIdsession) throws RemoteException;

    /**
     *
     * @param idUser
     * @param udfName
     * @return
     * @throws RemoteException
     */
    public String getUdfValue_User(String idUser, String udfName) throws RemoteException;

    /**
     *
     * @param iduser
     * @return
     * @throws RemoteException
     */
    public String getuserphonenumber(String iduser) throws RemoteException;

    /**
     *
     * @param idUser
     * @return
     * @throws RemoteException
     */
    public String GetTypeConfirm_User(String idUser) throws RemoteException;

    /**
     *
     * @param table
     * @param field
     * @param idkey
     * @return
     * @throws RemoteException
     */
    public String getmsglog(String table, String field, String idkey) throws RemoteException;

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
     * @throws RemoteException
     */
    public boolean check_trans_limit(String p_entity, String p_channel_id, String p_txn_id, String p_iduser, String p_typeuser, Date p_txn_date, String p_amount, String p_ccy, int p_cum) throws RemoteException;

    /**
     *
     * @param customercode
     * @param refpartner
     * @return
     * @throws RemoteException
     */
    public List getPblBillPaidByRefPartner(String customercode, String refpartner) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public HashMap<String, String> getPblPartnerservices() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public HashMap<String, String> getPblEbkScreens() throws RemoteException;

    /**
     *
     * @param iduser
     * @return
     * @throws RemoteException
     */
    public VwMstchanneluser getVwMstchanneluser(String iduser) throws RemoteException;

    /**
     *
     * @param pl
     * @return
     * @throws RemoteException
     */
    public int insPblLog(PblLog pl) throws RemoteException;

    /**
     *
     * @param code
     * @return
     * @throws RemoteException
     */
    public EtsMstbranch getEtsMstBranch(String code) throws RemoteException;

    /**
     *
     * @param brn
     * @throws RemoteException
     */
    public void insOrUpdEtsMstBranch(EtsMstbranch brn) throws RemoteException;

    /**
     *
     * @param paf
     * @throws RemoteException
     */
    public void saveOrUpdatePblAutoregFail(PblAutoregFail paf) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList getListUserToNeedChgPwd() throws RemoteException;

    /**
     *
     * @param cif
     * @return
     * @throws RemoteException
     */
    public ArrayList getBranchInfoByCif(String cif) throws RemoteException;

    /**
     *
     * @param iduser
     * @param custaccno
     * @return
     * @throws RemoteException
     */
    public boolean isExistsAccCasaByIduserAccno(String iduser, String custaccno) throws RemoteException;

    /**
     *
     * @param iduser
     * @param custaccno
     * @return
     * @throws RemoteException
     */
    public boolean isExistsAccTDByIduserAccno(String iduser, String custaccno) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public ArrayList getBranchByAccno(String accountno) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public List getGwPermissions() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public HashMap<String, String> getlist_telefirstnumber() throws RemoteException;

    /**
     *
     * @param vgroup_id
     * @return
     * @throws RemoteException
     */
    public HashMap<String, String> getlist_parainfo(String vgroup_id) throws RemoteException;

    /**
     *
     * @param iduser
     * @param accountloan
     * @return
     * @throws RemoteException
     */
    public boolean isExistsAccLoanByCifLoan(String iduser, String accountloan) throws RemoteException;

    /**
     *
     * @param accountloan
     * @return
     * @throws RemoteException
     */
    public List getInfoAccountLoan(String accountloan) throws RemoteException;

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
     * @throws java.rmi.RemoteException
     */
    public List findsmssenders(char vstatus, long numtrysend) throws RemoteException;

    /**
     *
     * @param _idpartner
     * @return
     * @throws RemoteException
     */
    public SmsPartner getsmspartner(String _idpartner) throws RemoteException;

    /**
     *
     * @param smsobj
     * @throws RemoteException
     */
    public void upd_smssender(SmsSender smsobj) throws RemoteException;

    /**
     *
     * @param smsobj
     * @return
     * @throws RemoteException
     */
    public int ins_smssender(SmsSender smsobj) throws RemoteException;

    /**
     *
     * @param smsobj
     * @throws RemoteException
     */
    public void upd_smspartner(SmsPartner smsobj) throws RemoteException;

    /**
     * ******************************************************************
     */
    /**
     *******************************************************************
     * BEGIN OF TIET KIEM TICH LUY
     *
     * @param accounttichluy
     * @return
     * @throws java.rmi.RemoteException
     */
    public ArrayList<?> searchCustAlertTichLuy(String accounttichluy) throws RemoteException;

    /**
     *
     * @param smscustalerttichluy
     * @return
     * @throws RemoteException
     */
    public int insertAlertAccountTichLuy(SmsCustAlertTichLuy smscustalerttichluy) throws RemoteException;

    /**
     *
     * @param smscustalerttichluy
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAccountTichLuyById(SmsCustAlertTichLuy smscustalerttichluy) throws RemoteException;

    /**
     *
     * @param smscustalerttichluy
     * @return
     * @throws RemoteException
     */
    public int updateAccountTichLuyMobileById(SmsCustAlertTichLuy smscustalerttichluy) throws RemoteException;

    /**
     *
     * @param cust_no
     * @param registertype
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> findHistoryListAccountTichLuy(String cust_no, String registertype) throws RemoteException;

    /**
     *
     * @param accounttd
     * @param cif
     * @param idcard
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> findListAccountTichLuy(String accounttd, String cif, String idcard) throws RemoteException;

    /**
     *
     * @param accounttd
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAccountTDByAccountTichLuy(String accounttd) throws RemoteException;

    /**
     *******************************************************************
     * END OF TIET KIEM TICH LUY
     */
    /**
     *******************************************************************
     * BEGIN OF PAYOO PAYMENT
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @param billInfo
     * @return
     * @throws java.rmi.RemoteException
     */
    public int insertPbl_PayooLog(String customercode,
            String serviceID, String providerID, String billInfo) throws RemoteException;

    /**
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getPbl_PayooLog(String customercode, String serviceID, String providerID) throws RemoteException;

    /**
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getPbl_PayooLogPrint(String customercode, String serviceID, String providerID) throws RemoteException;

    /**
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @return
     * @throws RemoteException
     */
    public String getBillInfo(String customercode, String serviceID, String providerID) throws RemoteException;

    /**
     *
     * @param seqname
     * @return
     * @throws RemoteException
     */
    public int getIdSeqByName(String seqname) throws RemoteException;

    /**
     *******************************************************************
     * END OF PAYOO PAYMENT
     */
    /**
     *******************************************************************
     * END OF PAYOO PAYMENT
     */
    /**
     * Author: LyDTY Get data for webservice payonline Date: 10/Dec/2013
     *
     * @param PartnerID
     * @param TransID
     * @param CardHolderName
     * @param CardNumber
     * @param CardDate
     * @param LocalDate
     * @param MerchantId
     * @param Amount
     * @param ChannelID
     * @param ClientID
     * @param CCY
     * @param AddInfo
     * @param Language
     * @param URLAuthen
     * @param pIDVerify
     * @return
     * @throws java.lang.Exception
     */
    public String checkCustomerInfo(String PartnerID, String TransID,
            String CardNumber, String CardHolderName,
            String CardDate, String MerchantId,
            Double Amount, String CCY, String Language,
            String ClientID, String LocalDate, String AddInfo,
            String ChannelID, String URLAuthen, String pIDVerify) throws Exception;

    /**
     *
     * @param pID
     * @param pRefID
     * @throws Exception
     */
    public void TransferOnlinePayment(int pID, String pRefID
    ) throws Exception;

    /**
     *
     * @param pID
     * @param pURLREDIREC
     * @param pStatus
     * @throws Exception
     */
    public void CommitTransfer(int pID, String pURLREDIREC, String pStatus
    ) throws Exception;

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
     * @throws Exception
     */
    public String[] CheckRefundTransfer(String pPartnerID, String pMerchanID,
            String pTransID, String pRefundTransID,
            BigDecimal pRefundAmount, String pCCY, String pADDINFO, String pLocalDatetime) throws Exception;

    /**
     *
     * @param ID
     * @param pCoreref
     * @param pStatus
     * @throws Exception
     */
    public void UpdateRefundTransfer(String ID, String pCoreref, String pStatus) throws Exception;

    /**
     *
     * @param PartnerID
     * @param TransID
     * @param MerchantID
     * @param Amount
     * @param CCY
     * @param QTransID
     * @return
     * @throws Exception
     */
    public String[] QuerryTransfer(String PartnerID, String TransID, String MerchantID,
            Double Amount, String CCY, String QTransID
    ) throws Exception;

    /**
     *
     * @param pEbankUserID
     * @param pIDVerify
     * @return
     * @throws Exception
     */
    public String[] getPaymentOnlineInfo(String pEbankUserID, String pIDVerify)
            throws Exception;

    /**
     *
     * @param pID
     * @param pPhoneNumber
     * @param pOTP
     * @param pIDAddress
     * @throws Exception
     */
    public void insertOTPSMS(int pID, String pPhoneNumber, String pOTP, String pIDAddress)
            throws Exception;

    /**
     *
     * @param pID
     * @param pTimeOut
     * @return
     * @throws Exception
     */
    public String[] getOTPSMS(int pID, String pTimeOut)
            throws Exception;

    /**
     *
     * @param pID
     * @param pUserID
     * @param pClienID
     * @return
     * @throws Exception
     */
    public int checkSessionForTransfer(String pID, String pUserID, String pClienID) throws Exception;

    /**
     *
     * @param collate
     * @throws RemoteException
     */
    public void ReceiveCollateData(PayOnlineCollate collate) throws RemoteException;

    /**
     *
     * @param pPartnerID
     * @param pTransdate
     * @return
     * @throws Exception
     */
    public List<PayOnlineCollate> AnswerCollateData(String pPartnerID, String pTransdate) throws Exception;

    /**
     *
     * @param IDverify
     * @return
     * @throws Exception
     */
    public ArrayList<?> getDataByVerifyID(String IDverify) throws Exception;

    /**
     *
     * @param pPartnerID
     * @param pTransdate
     * @return
     * @throws Exception
     */
    public int isCollate(String pPartnerID, String pTransdate) throws Exception;

    /**
     *
     * @param pVerifyID
     * @param pTypeFailed
     * @return
     * @throws Exception
     */
    public int checkFailed(String pVerifyID, String pTypeFailed) throws Exception;

    /**
     *
     * @param pVerifyID
     * @param pTypeFailed
     * @throws Exception
     */
    public void insertFailed(String pVerifyID, String pTypeFailed) throws Exception;

    /**
     *
     * @param pTransID
     * @param pPartnerID
     * @return
     * @throws Exception
     */
    public int CheckBeforeTransfer(String pTransID, String pPartnerID) throws Exception;

    /**
     * Author: LyDTY Get data for webservice payonline Date: 10/Dec/2013
     *
     * @param Trace_No
     * @param Order_No
     * @param TypeOfTrans
     * @param PartnerDatetime
     * @param Amount
     * @param PayooDatetime
     * @param Note
     * @param Status
     * @param CheckSum
     * @return
     * @throws java.rmi.RemoteException
     */
    //PAYOO COLLATE
    //UPDATE BY: HIEUDT
    public int insert_PayooCollateData(String Trace_No, String Order_No, BigDecimal Amount,
            Date PartnerDatetime, Date PayooDatetime, String TypeOfTrans, String Status,
            String Note, String CheckSum) throws RemoteException;

    /**
     *
     * @param date
     * @return
     * @throws RemoteException
     */
    public int CollatePayooBillData(String date) throws RemoteException;

    /**
     *
     * @param date
     * @return
     * @throws RemoteException
     */
    public boolean isExsistPayooBillCollateData(String date) throws RemoteException;

    //END OF PAYOO COLLATE
    //Begin VNPAY COLLATE
    /**
     *
     * @param o
     * @throws Exception
     */
    public void InsertPBL_VNPAY_COLLATED(PblCollated_VNPAY o) throws Exception;

    /**
     *
     * @param pParnertid
     * @param pTransDate
     * @throws Exception
     */
    public void UPDATE_AFTER_COLLATED(String pParnertid, Date pTransDate) throws Exception;

    /**
     *
     * @param datecollate
     * @param filename
     * @throws Exception
     */
    public void INSERT_DATE_COLLATED(Date datecollate, String filename) throws Exception;

    /**
     *
     * @return @throws Exception
     */
    public Date GET_VNPAY_COLLATEDATE() throws Exception;
    //END VNPAY COLLATE

//Begin SmartLink
    /**
     *
     * @param pTYPETRANSFER
     * @param pFROMNUMBER
     * @param pPROCESSINGCODE
     * @param pTRANSAMOUNT
     * @param pTRANSDATE
     * @param pAUDITNUMBER
     * @param pMERCHANTTYPE
     * @param pACQUIRINGCODE
     * @param pAUTHORIZATIONCODE
     * @param pRESPONSECODE
     * @param pTERMID
     * @param pCARDACCEPTTOR
     * @param pDESTNUMBER
     * @param pNARRATION
     * @param pBENID
     * @param pTYPEFUNCTION
     * @param pStatus
     * @param pRefCore
     * @param pCustNo
     * @param RefCORE_REFUND
     * @param pREF_NO_F37
     * @param pSETT_DATE_F15
     * @throws Exception
     */
    public int InsertSMLLOG(String pTYPETRANSFER, String pFROMNUMBER, String pPROCESSINGCODE,
            BigDecimal pTRANSAMOUNT, String pTRANSDATE,
            String pAUDITNUMBER, String pMERCHANTTYPE,
            String pACQUIRINGCODE, String pAUTHORIZATIONCODE, String pRESPONSECODE,
            String pTERMID, String pCARDACCEPTTOR, String pDESTNUMBER,
            String pNARRATION, String pBENID, String pTYPEFUNCTION, String pStatus,
            String pRefCore, String pCustNo, String RefCORE_REFUND, String pREF_NO_F37, String pSETT_DATE_F15) throws Exception;

    /**
     *
     * @param pCardNumber
     * @param pCardHolderName
     * @param pAmount
     * @return
     * @throws Exception
     */
    public String[] getCardInfo(String pCardNumber, String pCardHolderName, BigDecimal pAmount) throws Exception;

    /**
     *
     * @param pTypeTransfer
     * @return
     * @throws Exception
     */
    public String getSMLAccount(String pTypeTransfer) throws Exception;

    /**
     *
     * @return @throws Exception
     */
    public String getAuditNumber() throws Exception;

    /**
     *
     * @return @throws Exception
     */
    public String getAuthorizationCode() throws Exception;

    /**
     *
     * @param pCardNumber
     * @return
     * @throws Exception
     */
    public String[] getCardStatus(String pCardNumber) throws Exception;

    /**
     *
     * @param pAuditNumber
     * @return
     * @throws Exception
     */
    public boolean checkAuditNumberOfSML(String pAuditNumber) throws Exception;

    /**
     *
     * @param pCustNo
     * @param pAmount
     * @return
     * @throws Exception
     */
    public String checkLimitAmount(String pCustNo, BigDecimal pAmount) throws Exception;

    /**
     *
     * @param sml
     * @throws Exception
     */
    public void InsertSMLCollated(SMLCollate sml) throws Exception;

    /**
     *
     * @param pcollatedate
     * @param pfilename
     * @param pTypefile
     * @param pSumrecord
     * @throws Exception
     */
    public void insertCollateDate(Date pcollatedate, String pfilename, String pTypefile, int pSumrecord) throws Exception;

    /**
     *
     * @param pFile
     * @return
     * @throws Exception
     */
    public int checkFile(String pFile) throws Exception;

    /**
     *
     * @param pTypefile
     * @return
     * @throws Exception
     */
    public List<SMLCollate> getOutCollate(String pTypefile) throws Exception;

    //BEGIN CONTACT CENTER
    /**
     *
     * @param function
     * @param param
     * @return
     * @throws Exception
     */
    public String queryContactCenterInfo(String function, String param) throws Exception;

    //END CONTACT CENTER
    /**
     *******************************************************************
     * BEGIN OF MOBILE BANKING
     *
     * @param cusno
     * @return
     * @throws java.rmi.RemoteException
     */
    public List GetAccountListByCustNo(String cusno) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public List getTdAccountByAccountNo(String accountno) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public List getCASAccountHasLimitMB(String accountno) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public List getLoanAccountByAccountNo(String accountno) throws RemoteException;

    /**
     *
     * @param unique_name
     * @param unique_id
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getCustomerInfoForMB(String unique_name, String unique_id) throws RemoteException;

    /**
     *
     * @param accountno
     * @param fromDate
     * @param toDate
     * @param srno
     * @return
     * @throws RemoteException
     */
    public ArrayList getTransationListByAccountNo(String accountno, String fromDate, String toDate, String srno) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public ArrayList getTDTransationListByAccountNo(String accountno) throws RemoteException;

    /**
     *
     * @param bankCode
     * @return
     * @throws RemoteException
     */
    public List getBeneBank(String bankCode) throws RemoteException;

    /**
     *
     * @param codCity
     * @return
     * @throws RemoteException
     */
    public List getBankCity(String codCity) throws RemoteException;

    /**
     *
     * @param branchcode
     * @param accountclass
     * @param amount
     * @param ccy
     * @return
     * @throws RemoteException
     */
    public ArrayList getAccountClassInfo(String branchcode, String accountclass, String amount, String ccy, String cif) throws RemoteException;

    /**
     *
     * @param branchcode
     * @param accountclass
     * @param amount
     * @param ccy
     * @return
     * @throws RemoteException
     */
    public ArrayList getAccountClassInfoNew(String branchcode, String accountclass, String amount, String ccy) throws RemoteException;

    /**
     *
     * @param cardno
     * @return
     * @throws RemoteException
     */
    public ArrayList getMaterCardDetailByCardno(String cardno) throws RemoteException;

    /**
     *
     * @param cardno
     * @return
     * @throws RemoteException
     */
    public ArrayList getTransactionMaterCardByCardno(String cardno) throws RemoteException;

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    public ArrayList getTokenbyCustno(String custno) throws RemoteException;

    /**
     *
     * @param serialno
     * @return
     * @throws RemoteException
     */
    public ArrayList checkTokenbySerialno(String serialno) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public ArrayList getCLSCHEDULEINTPAID(String accountno) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public ArrayList getCLSCHEDULEINTUNPAID(String accountno) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public ArrayList getCLSCHEDULEPRIPAIDSCB(String accountno) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public ArrayList getCLSCHEDULEPRIUNPAIDSCB(String accountno) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public ArrayList checkAmountBeforePay(String accountno) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList getExchangeRate() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList getGoldRate() throws RemoteException;

    /**
     *
     * @param ccy
     * @return
     * @throws RemoteException
     */
    public ArrayList getInterestRate(String ccy) throws RemoteException;

    /**
     *
     * @param account_class
     * @return
     * @throws RemoteException
     */
    public ArrayList getProductAccountClass(String account_class) throws RemoteException;

    /**
     *
     * @param serialno
     * @return
     * @throws RemoteException
     */
    public ArrayList getValidToken(String serialno) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public List GetTDAccountBeforeRedemtion(String accountno) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<?> getAllListServiceTypeNotTopup() throws RemoteException;

    /**
     *
     * @param username
     * @return
     * @throws RemoteException
     */
    public ArrayList getCustomerFromMobile(String username) throws RemoteException;

    /**
     *
     * @param cif
     * @param status
     * @return
     * @throws RemoteException
     */
    public ArrayList getMobileUserFromCif(String cif, String status) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public ArrayList getSttmCustAccountSyn(String accountno) throws RemoteException;

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    public List GetTemplateFromFCDB(String custno) throws RemoteException;

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    public ArrayList getBeneficiaryFromFCDB(String custno) throws RemoteException;

    /*
     *******************************************************************
     * END OF MOBILE BANKING
     */
    //START PAYOO ONLINE COLLATED
    /**
     *
     * @param pPartnerID
     * @param pCollatedDate
     * @throws RemoteException
     */
    public void InsertCollateddate(String pPartnerID, Date pCollatedDate) throws RemoteException;

    /**
     *
     * @param pPartnerID
     * @return
     * @throws RemoteException
     */
    public Date getMaxCollateDate(String pPartnerID) throws RemoteException;
    //END PAYOO ONLINE COLLATED

    //SML ECOM 
    /**
     *
     * @param PartnerID
     * @param TransID
     * @param CardNumber
     * @param CardName
     * @param MerchantId
     * @param Amount
     * @param CCY
     * @param LocalDate
     * @param AddInfo
     * @param ChannelID
     * @param URLAuthen
     * @param pIDVerify
     * @return
     * @throws RemoteException
     */
    public String[] SMLEcomCheckData(String PartnerID, String TransID,
            String CardNumber,
            String CardName,
            String MerchantId,
            Double Amount, String CCY,
            String LocalDate,
            String AddInfo,
            String ChannelID,
            String URLAuthen,
            String pIDVerify) throws RemoteException;

    /**
     *
     * @param sml
     * @return
     * @throws RemoteException
     */
    public int InsertSMLEcomCollated(SMLCollate sml) throws RemoteException;

    /**
     *
     * @return @throws Exception
     */
    public List<SMLCollate> getOutSMLEcomCollate() throws Exception;

    /**
     *
     * @param pUserID
     * @return
     * @throws Exception
     */
    public int checkTokenByUserID(String pUserID) throws Exception;

    //NOP THUE DIEN TU - 14-05-2015
    /**
     *
     * @param pMA_GDICH
     * @param pNGAY_GUI_GDICH
     * @param pMA_GDICH_TCHIEU
     * @param pPBAN_TLIEU_XML
     * @param pMST
     * @param pTEN_NNT
     * @param pDIACHI_NNT
     * @param pMA_CQT
     * @param pEMAIL_NNT
     * @param pSDT_NNT
     * @param pTEN_LHE_NTHUE
     * @param pSERIAL_CERT_NTHUE
     * @param pSUBJECT_CERT_NTHUE
     * @param pISSUER_CERT_NTHUE
     * @param pMA_NHANG
     * @param pTEN_NHANG
     * @param pVAN_ID
     * @param pTEN_TVAN
     * @param pNGAY_GUI
     * @param pLOAITHONGTIN
     * @param pMSGID
     * @param pTRANSCODE
     * @param pCKS
     * @return
     * @throws Exception
     */
    public String[] NTDT_InsertTHONGTIN_NNT(String pMA_GDICH,
            String pNGAY_GUI_GDICH,
            String pMA_GDICH_TCHIEU,
            String pPBAN_TLIEU_XML,
            String pMST,
            String pTEN_NNT,
            String pDIACHI_NNT,
            String pMA_CQT,
            String pEMAIL_NNT,
            String pSDT_NNT,
            String pTEN_LHE_NTHUE,
            String pSERIAL_CERT_NTHUE,
            String pSUBJECT_CERT_NTHUE,
            String pISSUER_CERT_NTHUE,
            String pMA_NHANG,
            String pTEN_NHANG,
            String pVAN_ID,
            String pTEN_TVAN,
            String pNGAY_GUI,
            String pLOAITHONGTIN,
            String pMSGID,
            String pTRANSCODE,
            String pCKS) throws Exception;

    /*
     pTypedata: INSERT/UPDATE
     */
    /**
     *
     * @param pAccountNo
     * @param pID_NNT
     * @param pIsDeleted
     * @param pAddinfo
     * @param pUSERID
     * @param pBRANCHID
     * @return
     * @throws Exception
     */
    public String NTDT_InsertAccountNo(String pAccountNo,
            int pID_NNT,
            int pIsDeleted,
            String pAddinfo,
            String pUSERID,
            String pBRANCHID) throws Exception;

    /**
     *
     * @param pID_NNT
     * @throws Exception
     */
    public void NTDT_THONGBAO_THAYDOI_STK(int pID_NNT) throws Exception;

    /**
     *
     * @param pMST
     * @param pLYDONGUNG
     * @param pCLOSEDATE
     * @param MA_TBAO
     * @param MAU_TBAO
     * @param SO_TBAO
     * @param TEN_TBAO
     * @param NGAY_TBAO
     * @throws Exception
     */
    public void NTDT_CloseNNT(String pMST,
            String pLYDONGUNG,
            String pCLOSEDATE,
            String MA_TBAO,
            String MAU_TBAO,
            String SO_TBAO,
            String TEN_TBAO,
            String NGAY_TBAO) throws Exception;

    /**
     *
     * @param pID_TT_NNT
     * @param pCheckStatus
     * @param pAddinfo
     * @param pUserUD
     * @param pBranchID
     * @throws Exception
     */
    public void NTDT_CheckTT_NNT(int pID_TT_NNT,
            String pCheckStatus,
            String pAddinfo,
            String pUserUD,
            String pBranchID) throws Exception;

    /**
     *
     * @param pID_TT_NNT
     * @param pUserUD
     * @param pID_NNT
     * @throws Exception
     */
    public void NTDT_Confirm_TT_NNT(int pID_TT_NNT,
            String pUserUD,
            int pID_NNT) throws Exception;

    /**
     *
     * @param pMA_GDICH
     * @param pNGAY_GUI_GDICH
     * @param pMA_GDICH_TCHIEU
     * @param pMAHIEU_CTU
     * @param pSO_CTU
     * @param pPBAN_TLIEU_XML
     * @param pID_CTU
     * @param pSO_GNT
     * @param pMA_CTU
     * @param pHTHUC_NOP
     * @param pMST_NNOP
     * @param pTEN_NNOP
     * @param pDIACHI_NNOP
     * @param pMA_CQT
     * @param pTEN_CQT
     * @param pMA_XA_NNOP
     * @param pTEN_XA_NNOP
     * @param pMA_HUYEN_NNOP
     * @param pTEN_HUYEN_NNOP
     * @param pMA_TINH_NNOP
     * @param pTEN_TINH_NNOP
     * @param pMST_NTHAY
     * @param pTEN_NTHAY
     * @param pDIACHI_NTHAY
     * @param pTEN_HUYEN_NTHAY
     * @param pTEN_TINH_NTHAY
     * @param pMA_NHANG_NOP
     * @param pTEN_NHANG_NOP
     * @param pSTK_NHANG_NOP
     * @param pMA_HIEU_KBAC
     * @param pTEN_KBAC
     * @param pMA_TINH_KBAC
     * @param pTEN_TINH_KBAC
     * @param pLOAI_TK_THU
     * @param pTEN_TK_THU
     * @param pSTK_THU
     * @param pID_TK_KNGHI
     * @param pTK_KNGHI
     * @param pMA_CQTHU
     * @param pTEN_CQTHU
     * @param pNGAY_LAP
     * @param pTONG_TIEN
     * @param pVAN_ID
     * @param pMA_DBHC_THU
     * @param pTEN_DBHC_THU
     * @param pMA_LOAI_THUE
     * @param pMSGID
     * @param pTRANSCODE
     * @param pCKS
     * @return
     * @throws Exception
     */
    public String[] NTDT_INSERT_GNT(String pMA_GDICH,
            String pNGAY_GUI_GDICH,
            String pMA_GDICH_TCHIEU,
            String pMAHIEU_CTU,
            String pSO_CTU,
            String pPBAN_TLIEU_XML,
            String pID_CTU,
            String pSO_GNT,
            String pMA_CTU,
            String pHTHUC_NOP,
            String pMST_NNOP,
            String pTEN_NNOP,
            String pDIACHI_NNOP,
            String pMA_CQT,
            String pTEN_CQT,
            String pMA_XA_NNOP,
            String pTEN_XA_NNOP,
            String pMA_HUYEN_NNOP,
            String pTEN_HUYEN_NNOP,
            String pMA_TINH_NNOP,
            String pTEN_TINH_NNOP,
            String pMST_NTHAY,
            String pTEN_NTHAY,
            String pDIACHI_NTHAY,
            String pTEN_HUYEN_NTHAY,
            String pTEN_TINH_NTHAY,
            String pMA_NHANG_NOP,
            String pTEN_NHANG_NOP,
            String pSTK_NHANG_NOP,
            String pMA_HIEU_KBAC,
            String pTEN_KBAC,
            String pMA_TINH_KBAC,
            String pTEN_TINH_KBAC,
            String pLOAI_TK_THU,
            String pTEN_TK_THU,
            String pSTK_THU,
            String pID_TK_KNGHI,
            String pTK_KNGHI,
            String pMA_CQTHU,
            String pTEN_CQTHU,
            String pNGAY_LAP,
            String pTONG_TIEN,
            String pVAN_ID,
            String pMA_DBHC_THU,
            String pTEN_DBHC_THU,
            String pMA_LOAI_THUE,
            String pMSGID,
            String pTRANSCODE,
            String pCKS) throws Exception;

    /**
     *
     * @param pID_GNT
     * @param pID_CTU_CTIET
     * @param pID_CTU
     * @param pNDUNG_NOP
     * @param pMA_NDKT
     * @param pMA_CHUONG
     * @param pKY_THUE
     * @param pTIEN_PNOP
     * @param pGHI_CHU
     * @throws Exception
     */
    public void NTDT_INSERT_CHUNGTU_CHIIET(int pID_GNT,
            String pID_CTU_CTIET,
            String pID_CTU,
            String pNDUNG_NOP,
            String pMA_NDKT,
            String pMA_CHUONG,
            String pKY_THUE,
            String pTIEN_PNOP,
            String pGHI_CHU) throws Exception;

    /**
     *
     * @param pID_THONGBAO
     * @param pMESS_CODE
     * @param pMESS_CONTENT
     * @throws Exception
     */
    public void NTDT_CONFIRMTHONGBAO(String pID_THONGBAO,
            String pMESS_CODE,
            String pMESS_CONTENT) throws Exception;

    /**
     *
     * @param pID_NNT
     * @param pIsApproved
     * @return
     * @throws Exception
     */
    public ArrayList NTDT_GET_ACCOUNNO(int pID_NNT, int pIsApproved) throws Exception;

    /**
     *
     * @param pLOAITHONGBAO
     * @param pID_REF
     * @return
     * @throws Exception
     */
    public List<NTDT_NNT> NTDT_GUITHONGBAO(String pLOAITHONGBAO, int pID_REF) throws Exception;

    //pID_DOICHIEU_GD     out number
    /**
     *
     * @param pPBAN_TLIEU_XML_DC
     * @param pMA_GDICH_DC
     * @param pNGAY_GDICH_DC
     * @param pMA_NHANG_DC
     * @param pNGAY_DC
     * @param pTU_NGAY_DC
     * @param pDEN_NGAY_DC
     * @param pMA_GDICH_TCHIEU_DC
     * @param pMSGID
     * @param pTRANSCODE
     * @return
     * @throws Exception
     */
    public int NTDT_INSERT_DOICHIEU_GD(String pPBAN_TLIEU_XML_DC,
            String pMA_GDICH_DC,
            String pNGAY_GDICH_DC,
            String pMA_NHANG_DC,
            String pNGAY_DC,
            String pTU_NGAY_DC,
            String pDEN_NGAY_DC,
            String pMA_GDICH_TCHIEU_DC,
            String pMSGID,
            String pTRANSCODE) throws Exception;

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pMA_GDICH
     * @param pNGAY_GUI_GDICH
     * @param pNGAY_NOP_GNT
     * @param pNGAY_NOP_THUE
     * @param pTTHAI_GDICH
     * @param pTTHAI_CTU
     * @param pMAHIEU_CTU
     * @param pSO_CTU
     * @param pPBAN_TLIEU_XML
     * @param pID_CTU
     * @param pSO_GNT
     * @param pMA_CTU
     * @param pHTHUC_NOP
     * @param pMST_NNOP
     * @param pTEN_NNOP
     * @param pMA_CQT
     * @param pTEN_CQT
     * @param pDIACHI_NNOP
     * @param pMA_XA_NNOP
     * @param pTEN_XA_NNOP
     * @param pMA_HUYEN_NNOP
     * @param pTEN_HUYEN_NNOP
     * @param pMA_TINH_NNOP
     * @param pTEN_TINH_NNOP
     * @param pMST_NTHAY
     * @param pTEN_NTHAY
     * @param pDIACHI_NTHAY
     * @param pTEN_HUYEN_NTHAY
     * @param pTEN_TINH_NTHAY
     * @param pMA_NHANG_NOP
     * @param pTEN_NHANG_NOP
     * @param pSTK_NHANG_NOP
     * @param pMA_HIEU_KBAC
     * @param pTEN_KBAC
     * @param pMA_TINH_KBAC
     * @param pTEN_TINH_KBAC
     * @param pLOAI_TK_THU
     * @param pTEN_TK_THU
     * @param pSTK_THU
     * @param pID_TK_KNGHI
     * @param pTK_KNGHI
     * @param pMA_CQTHU
     * @param pTEN_CQTHU
     * @param pNGAY_LAP
     * @param pTONG_TIEN
     * @param pVAN_ID
     * @return
     * @throws Exception
     */
    public int NTDT_INSERT_DOICHIEU_GNT(int pID_DOICHIEU_GD,
            String pMA_GDICH,
            String pNGAY_GUI_GDICH,
            String pNGAY_NOP_GNT,
            String pNGAY_NOP_THUE,
            String pTTHAI_GDICH,
            String pTTHAI_CTU,
            String pMAHIEU_CTU,
            String pSO_CTU,
            String pPBAN_TLIEU_XML,
            String pID_CTU,
            String pSO_GNT,
            String pMA_CTU,
            String pHTHUC_NOP,
            String pMST_NNOP,
            String pTEN_NNOP,
            String pMA_CQT,
            String pTEN_CQT,
            String pDIACHI_NNOP,
            String pMA_XA_NNOP,
            String pTEN_XA_NNOP,
            String pMA_HUYEN_NNOP,
            String pTEN_HUYEN_NNOP,
            String pMA_TINH_NNOP,
            String pTEN_TINH_NNOP,
            String pMST_NTHAY,
            String pTEN_NTHAY,
            String pDIACHI_NTHAY,
            String pTEN_HUYEN_NTHAY,
            String pTEN_TINH_NTHAY,
            String pMA_NHANG_NOP,
            String pTEN_NHANG_NOP,
            String pSTK_NHANG_NOP,
            String pMA_HIEU_KBAC,
            String pTEN_KBAC,
            String pMA_TINH_KBAC,
            String pTEN_TINH_KBAC,
            String pLOAI_TK_THU,
            String pTEN_TK_THU,
            String pSTK_THU,
            String pID_TK_KNGHI,
            String pTK_KNGHI,
            String pMA_CQTHU,
            String pTEN_CQTHU,
            String pNGAY_LAP,
            String pTONG_TIEN,
            String pVAN_ID) throws Exception;

    /**
     *
     * @param pID_CTU_CTIET
     * @param pID_CTU
     * @param pNDUNG_NOP
     * @param pMA_NDKT
     * @param pMA_CHUONG
     * @param pTIEN_PNOP
     * @param pID_DOICHIEU_GNT
     * @throws Exception
     */
    public void NTDT_INSERT_DOICHIEU_GNT_CHITIET(String pID_CTU_CTIET,
            String pID_CTU,
            String pNDUNG_NOP,
            String pMA_NDKT,
            String pMA_CHUONG,
            String pTIEN_PNOP,
            int pID_DOICHIEU_GNT) throws Exception;

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pMABAOCAO
     * @return
     * @throws RemoteException
     */
    public List<NTDT_DOICHIEUGD> NTDT_BAOCAODOICHIEU(int pID_DOICHIEU_GD, String pMABAOCAO) throws RemoteException;

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pReportType
     * @return
     * @throws RemoteException
     */
    public List<NTDT_BCDC_02> NTDT_BCDC_02(int pID_DOICHIEU_GD, String pReportType) throws RemoteException;

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pReportType
     * @return
     * @throws RemoteException
     */
    public List<NTDT_BCDC_05> NTDT_BCDC_05(int pID_DOICHIEU_GD, String pReportType) throws RemoteException;

    /**
     *
     * @param pMST
     * @param pSTATUS
     * @return
     * @throws RemoteException
     */
    public List<NTDT_searchNNT> NTDT_SearchNNT(String pMST, String pSTATUS) throws RemoteException;

    /**
     *
     * @param pLOAITHONGBAO
     * @param pID_REF
     * @return
     * @throws Exception
     */
    public List<NTDT_GNT> NTDT_GUITHONGBAO_GNT(String pLOAITHONGBAO, int pID_REF) throws Exception;

    /**
     *
     * @return @throws RemoteException
     */
    public String NTDT_getIDThongBao() throws RemoteException;

    /**
     *
     * @param pID_NNT
     * @param pUserApproves
     * @param pStatus
     * @throws RemoteException
     */
    public void NTDT_ApproveAccounts(int pID_NNT, String pUserApproves, String pStatus) throws RemoteException;

    /**
     *
     * @param pLOAITHONGBAO
     * @param pID_REF
     * @return
     * @throws Exception
     */
    public List<NTDT_TDSTK> NTDT_GUITHONGBAO_STK(String pLOAITHONGBAO, int pID_REF) throws Exception;

    /**
     *
     * @param ref_id
     * @return
     * @throws RemoteException
     */
    public String NTDT_getIDThongBaoDC(String ref_id) throws RemoteException;

    /**
     *
     * @param pID_GNT
     * @return
     * @throws RemoteException
     */
    public ArrayList NTDT_getChungTuGNT(int pID_GNT) throws RemoteException;

    /**
     *
     * @param pID_GNT
     * @return
     * @throws RemoteException
     */
    public ArrayList NTDT_getChungTuGNT_CT(int pID_GNT) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList NTDT_getAllThongBao() throws RemoteException;

    /**
     *
     * @param pMST
     * @param pSTATUS
     * @param branchID
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    public List<NTDT_searchNNT> NTDT_SearchAllNNT(String pMST, String pSTATUS, String branchID, String fromdate, String todate) throws RemoteException;

    /**
     *
     * @param ID
     * @return
     * @throws Exception
     */
    public NTDT_searchNNT NTDT_SearchIDNNT(String ID) throws Exception;

    //update ebanking mo rong
    /**
     *
     * @param smscustalerttd
     * @return
     * @throws RemoteException
     */
    public int updateAccountAlertStatusById(SmsCustAlertTd smscustalerttd) throws RemoteException;

    /**
     *
     * @param cif
     * @param idcard
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> findCustomer(String cif, String idcard) throws RemoteException;

    /**
     *
     * @param cusno
     * @return
     * @throws RemoteException
     */
    public List GetRedemptionAzListByCustNo(String cusno) throws RemoteException;

    /**
     *
     * @param staffCode
     * @return
     * @throws RemoteException
     */
    public String GetStaffDetail(String staffCode) throws RemoteException;

    /**
     *
     * @param subject
     * @param content
     * @param idchannel
     * @param idchanneluser
     * @return
     * @throws RemoteException
     */
    public int InsertFeedback(String subject, String content, String idchannel, String idchanneluser) throws RemoteException;

    //modify ebanking mo rong 
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
     * @throws RemoteException
     */
    public ArrayList<?> searchPaybill(String customercode, String idchannel, String idservicetype, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction, String beginRow, String endRow) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchPaybillAll(String customercode, String idchannel, String idservicetype, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction) throws RemoteException;

    /**
     *
     * @param channel
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> searchPaybillDiary(String channel, String fromdate, String todate) throws RemoteException;

    /**
     *
     * @param idlist
     * @param status
     * @param userid
     * @param idChanneluser
     * @return
     * @throws RemoteException
     */
    public int updateFeedback(String idlist, String status, String userid, String idChanneluser) throws RemoteException;

    /**
     *
     * @param idchannel
     * @param status
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> searchFeedback(String idchannel, String status, String fromdate, String todate, String beginRow, String endRow) throws RemoteException;

    /**
     *
     * @param idchannel
     * @param status
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> searchFeedbackAll(String idchannel, String status, String fromdate, String todate) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchOnlBill(String id, String partner, String customercode, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction, String beginRow, String endRow) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchOnlBillAll(String id, String partner, String customercode, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction) throws RemoteException;

    /**
     *
     * @param txnType
     * @param idlist
     * @param status
     * @param userid
     * @param idChanneluser
     * @return
     * @throws RemoteException
     */
    public int updateStatusPaybill(String txnType, String idlist, String status, String userid, String idChanneluser) throws RemoteException;

    /**
     *
     * @param cifno
     * @param mobileno
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> searchSMSAlert(String cifno, String mobileno) throws RemoteException;

    //nag cap mobile banking lan 
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
     * @throws RemoteException
     */
    public int checkIssueATMCard(String cifno, String accountno, String cardaccountno, String registertype, String issuetype, String cardtype, String fee, String tax) throws RemoteException;

    /**
     *
     * @param issueatm
     * @return
     * @throws RemoteException
     */
    public ProcedureCallDto insertEbIssuecard(EbIssuecard issueatm) throws RemoteException;

    /**
     *
     * @param issueatm
     * @throws RemoteException
     */
    public void updateEbIssuecard(EbIssuecard issueatm) throws RemoteException;

    /**
     *
     * @param type
     * @return
     * @throws RemoteException
     */
    public ArrayList getCardType(String type) throws RemoteException;

    /**
     *
     * @param cif
     * @param cardtype
     * @param groupcode
     * @return
     * @throws RemoteException
     */
    public ArrayList getCardList(String cif, String cardtype, String groupcode) throws RemoteException;

    /**
     *
     * @param cardaccountno
     * @param period
     * @return
     * @throws RemoteException
     */
    public ArrayList getMCStateDetail(String cardaccountno, String period) throws RemoteException;

    /**
     *
     * @param cardaccountno
     * @param period
     * @param srno
     * @param rownum
     * @return
     * @throws RemoteException
     */
    public ArrayList getCCStatement(String cardaccountno, String period, String srno, String rownum) throws RemoteException;

    /**
     *
     * @param cardaccountno
     * @return
     * @throws RemoteException
     */
    public String[] getPointMC(String cardaccountno) throws RemoteException;

    /**
     *
     * @param issueatm
     * @return
     * @throws RemoteException
     */
    public List getEbIssuecard(EbIssuecard issueatm) throws RemoteException;

    /**
     *
     * @param cardno
     * @return
     * @throws RemoteException
     */
    public ArrayList getMaterCardDetailByCardnoNew(String cardno) throws RemoteException;

    /**
     *
     * @param cardno
     * @return
     * @throws RemoteException
     */
    public ArrayList getTransactionMaterCardByCardnoNew(String cardno) throws RemoteException;

    /**
     *
     * @param cusno
     * @return
     * @throws RemoteException
     */
    public List GetAccountListByCustNoNew(String cusno) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public ArrayList getCardnoByAccountno(String accountno) throws RemoteException;

    /**
     *
     * @param cifno
     * @param mobileno
     * @param active
     * @return
     * @throws RemoteException
     */
    public int regeisterAlertTDMB(String cifno, String mobileno, String active) throws RemoteException;

    /**
     *
     * @param idlist
     * @param status
     * @param userid
     * @param idChanneluser
     * @return
     * @throws RemoteException
     */
    public int updateRegisterinfo(String idlist, String status, String userid, String idChanneluser) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchRegisterinfo(String idchannel, String status, String branchcode, String registerType, String fromdate, String todate, String beginRow, String endRow) throws RemoteException;

    /**
     *
     * @param idchannel
     * @param status
     * @param branchcode
     * @param registerType
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> searchRegisterinfoAll(String idchannel, String status, String branchcode, String registerType, String fromdate, String todate) throws RemoteException;

    /**
     *
     * @param custno
     * @param custaccno
     * @param amount
     * @return
     * @throws RemoteException
     */
    public ProcedureCallDto checkFeeMobile(String custno, String custaccno, String amount) throws RemoteException;

    /**
     *
     * @param cif
     * @return
     * @throws RemoteException
     */
    public ArrayList getPrimaryATMList(String cif) throws RemoteException;

    /*VIMO BEGIN*/
    /**
     *
     * @param PTRANSID
     * @param PTRANSDATE
     * @param PCARDNUMBER
     * @param PCARDNAME
     * @param POTPSMS
     * @param PPARTNERID
     * @param pDescription
     * @param typeCard
     * @return
     * @throws RemoteException
     */
    public String[] ONL_VERIFICARD(String PTRANSID,
            String PTRANSDATE,
            String PCARDNUMBER,
            String PCARDNAME,
            String POTPSMS,
            String PPARTNERID,
            String pDescription,
            String typeCard) throws RemoteException;

    /**
     *
     * @param PTRANSID
     * @param PTRANSDATE
     * @param PVERIFYTYPE
     * @param PREFTRANSID
     * @param POTPSMS
     * @param PPARTNERID
     * @return
     * @throws RemoteException
     */
    public String[] ONL_VERIFYOTPSMS(String PTRANSID,
            String PTRANSDATE,
            String PVERIFYTYPE,
            String PREFTRANSID,
            String POTPSMS,
            String PPARTNERID
    ) throws RemoteException;

    /**
     *
     * @param PTRANSID
     * @param PPROFILEID
     * @param PAMOUNT
     * @param PCCY
     * @param PTRANSDATE
     * @param PPARTNERID
     * @param PDESCRIPTION
     * @param PMERCHANTID
     * @param pChannelID
     * @return
     * @throws RemoteException
     */
    public String[] ONL_PAYMENTWITHPROFILEID(String PTRANSID,
            String PPROFILEID,
            Double PAMOUNT,
            String PCCY,
            String PTRANSDATE,
            String PPARTNERID,
            String PDESCRIPTION,
            String PMERCHANTID,
            String pChannelID) throws RemoteException;

    /**
     *
     * @param PTRANSID
     * @param pCardNumber
     * @param pCardHolderName
     * @param pCardDate
     * @param PAMOUNT
     * @param PCCY
     * @param PTRANSDATE
     * @param PPARTNERID
     * @param PDESCRIPTION
     * @param PMERCHANTID
     * @param pChannelID
     * @param OTP
     * @return
     * @throws RemoteException
     */
    public String[] ONL_PAYMENT(String PTRANSID,
            String pCardNumber,
            String pCardHolderName,
            String pCardDate,
            Double PAMOUNT,
            String PCCY,
            String PTRANSDATE,
            String PPARTNERID,
            String PDESCRIPTION,
            String PMERCHANTID,
            String pChannelID,
            String OTP
    ) throws RemoteException;

    /**
     *
     * @param pBankid
     * @param pStatus
     * @param pRefcore
     * @throws RemoteException
     */
    public void ONL_UpdatePayment(String pBankid,
            String pStatus,
            String pRefcore) throws RemoteException;

    /**
     *
     * @param pDestNumber
     * @param Ptypedestnumber
     * @return
     * @throws RemoteException
     */
    public String[] ONL_checkDestNumber(String pDestNumber,
            String Ptypedestnumber) throws RemoteException;

    /**
     *
     * @param PTRANSID
     * @param PDESTNUMBER
     * @param pSOURCEACCOUNT
     * @param PAMOUNT
     * @param PCCY
     * @param PTRANSDATE
     * @param PDESRCRIPTION
     * @param PPARTNERID
     * @param Ptypedestnumber
     * @return
     * @throws RemoteException
     */
    public String[] ONL_TAKEOUTWALLET(String PTRANSID,
            String PDESTNUMBER,
            String pSOURCEACCOUNT,
            Double PAMOUNT,
            String PCCY,
            String PTRANSDATE,
            String PDESRCRIPTION,
            String PPARTNERID,
            String Ptypedestnumber) throws RemoteException;

    /**
     *
     * @param PBANKID
     * @param PREFCORE
     * @throws RemoteException
     */
    public void ONL_UPDATE_TAKEOUTWALLET(String PBANKID,
            String PREFCORE) throws RemoteException;

    /**
     *
     * @param PTRANSID
     * @param PPARTNERID
     * @return
     * @throws RemoteException
     */
    public String[] ONL_REVERT_TAKEOUTWALLET(String PTRANSID,
            String PPARTNERID) throws RemoteException;

    /**
     *
     * @param PTRANSID
     * @param PREVERTTRANSID
     * @param PPARTNERID
     * @param PTRANSDATE
     * @param PDESC
     * @throws RemoteException
     */
    public void ONL_UPDATE_REVERT_TAKEOUTWALLET(String PTRANSID,
            String PREVERTTRANSID,
            String PPARTNERID,
            String PTRANSDATE,
            String PDESC) throws RemoteException;

    /**
     *
     * @param pTransid
     * @param pRefTransid
     * @param pAmount
     * @param pTransdate
     * @param pDescription
     * @param pPartnerid
     * @return
     * @throws RemoteException
     */
    public String[] ONL_REFUND(String pTransid,
            String pRefTransid,
            Double pAmount,
            String pTransdate,
            String pDescription,
            String pPartnerid) throws RemoteException;

    /**
     *
     * @param PID
     * @param PREFCORE
     * @throws RemoteException
     */
    public void ONL_UPDATE_REFUND(String PID, String PREFCORE) throws RemoteException;

    /**
     *
     * @param pRefTransid
     * @param pTRANSTYPE
     * @param pParnerid
     * @return
     * @throws RemoteException
     */
    public String[] ONL_queryTransaction(
            String pRefTransid,
            String pTRANSTYPE,
            String pParnerid) throws RemoteException;

    /**
     *
     * @param PTRANSID
     * @param PTRANSDATE
     * @param pProfileID
     * @param pDescription
     * @param pPartnerid
     * @param pOTP
     * @return
     * @throws RemoteException
     */
    public String[] ONL_DestroyConnectCard(String PTRANSID,
            String PTRANSDATE,
            String pProfileID,
            String pDescription,
            String pPartnerid,
            String pOTP) throws RemoteException;

    /**
     *
     * @param pPartnerid
     * @return
     * @throws RemoteException
     */
    public String[] ONL_GetMACkeys(String pPartnerid) throws RemoteException;

    /*VIMO END*/
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
     * @throws RemoteException
     */
    public int insertCardReloadTrans(String ID, String SRCACCOUNT, String PAN_LOC,
            String CARD_BRN, String CCY, String EXP_DATE, Double AMOUNT, String REF_FCC,
            String REF_CW, String TRANS_STATUS, String SRCCHANNEL)
            throws RemoteException;

    //Long.Le edit updateCardReloadTrans
    /**
     *
     * @param ID
     * @param REF_FCC
     * @param REF_CW
     * @param TRANS_STATUS
     * @return
     * @throws RemoteException
     */
    public int updateCardReloadTrans(String ID, String REF_FCC, String REF_CW, String TRANS_STATUS) throws RemoteException;
    //Cardword WS        

    //thay doi nguoi thu huong cho tich luy moi
    /**
     *
     * @param acccount
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> GetBeneficaryName(String acccount) throws RemoteException;

    /**
     *
     * @param cif
     * @param cardaccountnumber
     * @return
     * @throws RemoteException
     */
    public ArrayList getCreditCardList(String cif, String cardaccountnumber) throws RemoteException;

    /**
     *
     * @param dateFile
     * @return
     * @throws RemoteException
     */
    public ArrayList BCN_getlist_VW_BCN(String dateFile) throws RemoteException;

    /**
     *
     * @param pRESPONSECODE
     * @param pTypeTrans
     * @param pCCY
     * @param pAmount
     * @param pTransdate
     * @param pTranscode
     * @param pPARTNERDETAILID
     * @param pIDBILLPAID
     * @param pACCOUNT
     * @throws RemoteException
     */
    public void BCN_RECEIVECOLLATED(String pRESPONSECODE,
            String pTypeTrans,
            String pCCY,
            String pAmount,
            String pTransdate,
            String pTranscode,
            String pPARTNERDETAILID,
            String pIDBILLPAID,
            String pACCOUNT) throws RemoteException;

    /*KET NOI CONG TY CHUNG KHOAN*/
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
     * @throws RemoteException
     */
    public Object[] SI_InsertSITRANFERTOSI(
            String PPARTNERID,
            String PCUSTUMERACCOUNT,
            String PCUSTUMERNAME,
            BigDecimal PAMOUNT,
            String PCCY,
            String PCHANNELID,
            String PTRANSDATE,
            String PADDINFO) throws RemoteException;

    /**
     *
     * @param PID
     * @param PREFCORE
     * @param pSTATUS
     * @param pRESPONSECODE
     * @param pTXNREF
     * @param pDESCRESPONSE
     * @throws RemoteException
     */
    public void SI_CONFIRMSITRANFERTOSI(
            double PID,
            String PREFCORE,
            String pSTATUS,
            String pRESPONSECODE,
            String pTXNREF,
            String pDESCRESPONSE) throws RemoteException;

    /**
     *
     * @param pPARTNERID
     * @param pTRANSID
     * @param pTRANSDATE
     * @return
     * @throws RemoteException
     */
    public String[] SI_INSERTSITRANSFROMSI(
            String pPARTNERID,
            String pTRANSID,
            String pTRANSDATE
    ) throws RemoteException;

    /**
     *
     * @param pAccount
     * @param pAmount
     * @return
     * @throws RemoteException
     */
    public String[] SI_CHECKACCOUNT(
            String pAccount,
            double pAmount
    ) throws RemoteException;

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
     * @throws RemoteException
     */
    public Object[] SI_INSERTTRANSFERFROMSIDETAIL(
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
    ) throws RemoteException;

    /**
     *
     * @param PID
     * @param pREFCORE
     * @param pVALIDDATECORE
     * @param pSTATUS
     * @throws RemoteException
     */
    public void SI_UPDATETRANSFERFROMSIDETAIL(
            double PID,
            String pREFCORE,
            String pVALIDDATECORE,
            String pSTATUS) throws RemoteException;

    /**
     *
     * @param pID_MASTER
     * @return
     * @throws RemoteException
     */
    public ArrayList SI_GETLISTTRANSFROMSI(int pID_MASTER) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList SI_getListBank() throws RemoteException;

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
     * @throws RemoteException
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
            String pTYPETRANS) throws RemoteException;

    /**
     *
     * @param pDate
     * @param pPartnerid
     * @param pType
     * @return
     * @throws RemoteException
     */
    public ArrayList SI_getOutCollated(Date pDate, String pPartnerid, String pType) throws RemoteException;

    /**
     * Kiem tra ma khach hang da ton tai trong dang ky hoa don tu dong trc do
     *
     * @param idservicetype
     * @param idprodvider
     * @param customercode
     * @param CIFNO
     * @param ValidDate
     * @param UserName
     * @param DebitAccount
     * @param Mobile
     * @param ExpireDate
     * @return 0: chua dang ky trc do; -1: da dang ky truoc do
     * @throws RemoteException
     */
    public ProcedureCallDto RegisterAutoBill(String idservicetype, String idprodvider, String customercode, String CIFNO, String UserName, String Mobile, String DebitAccount, String ValidDate, String ExpireDate, String PaymentType) throws RemoteException;

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    public String[] HQ_INSERT_NOPTIEN_HQ(HQ_NOPTIEN_HQ obj) throws RemoteException;

    ;
        
    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    public String[] HQ_INSERT_HQ_NOPTIEN_HQ_GNT(HQ_NOPTIEN_HQ_GNT obj) throws RemoteException;

    ;

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    public void HQ_INSERT_HQ_NOPTIEN_HQ_GNTCT(HQ_NOPTIEN_HQ_GNTCT obj) throws RemoteException;

    ;

    /**
     *
     * @param PIDREF
     * @param PTYPE
     * @return
     * @throws RemoteException
     */
    public String[] HQ_NOPTIEN_CORE(
            int PIDREF,
            String PTYPE //HQ/CQT

    ) throws RemoteException;

    ;

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    public String[] HQ_INSERT_HQ_NOPTIEN_CQQLT(HQ_NOPTIEN_CQQLT obj) throws RemoteException;

    ;

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    public void HQ_INSERT_HQ_NOPTIEN_CQQLT_CT(
            HQ_NOPTIEN_CQQLT_CT obj) throws RemoteException;

    ;

    /**
     *
     * @param PIDREF
     * @param PTYPE
     * @param PCHECKERID
     * @return
     * @throws RemoteException
     */
    public String[] HQ_APPROVETRANSFER(
            int PIDREF,
            String PTYPE,// HQ/CQT
            String PCHECKERID) throws RemoteException;

    ;

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    public void HQ_SEND_MESSAGE(
            HQ_MSG obj
    ) throws RemoteException;

    ;

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_HQ> HQ_getDataNOPTIEN_HQ(int pID, String pStatus) throws RemoteException;

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_HQ_GNT> HQ_getDataNOPTIEN_HQ_GNT(int pID, String pStatus) throws RemoteException;

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_HQ_GNTCT> HQ_getDataNOPTIEN_HQ_GNTCT(int pID, String pStatus) throws RemoteException;

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    public String[] HQ_INSERT_HQ_BAOLANH_TK(HQ_BAOLANH_TK obj) throws RemoteException;

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    public String[] HQ_INSERT_HQ_BAOLANH_CHUNG(HQ_BAOLANH_CHUNG obj) throws RemoteException;

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    public String[] HQ_INSERT_HQ_BAOLANH_HDVD(HQ_BAOLANH_HDVD obj) throws RemoteException;

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public ArrayList HQ_selectCHUONG(String ID) throws RemoteException;

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public ArrayList HQ_selectKBNN(String ID) throws RemoteException;

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public ArrayList HQ_selectNTK(String ID) throws RemoteException;

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public ArrayList HQ_selectCCY(String ID) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public String HQ_getTransID() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public String HQ_getSOCT() throws RemoteException;

    /**
     *
     * @param pID
     * @param pMSGTYPE
     * @return
     * @throws RemoteException
     */
    public String HQ_getREQUESTID(String pID, String pMSGTYPE) throws RemoteException;

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_CQQLT> HQ_getDataNOPTIEN_HQ_CQQLT(int pID, String pStatus) throws RemoteException;

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_CQQLT_CT> HQ_getDataNOPTIEN_HQ_CQQLT_CT(int pID, String pStatus) throws RemoteException;

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    public List<HQ_BAOLANH_TK> HQ_getDataBAOLANH_TK(int pID, String pStatus) throws RemoteException;

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    public List<HQ_BAOLANH_CHUNG> HQ_getDataBAOLANH_CHUNG(int pID, String pStatus) throws RemoteException;

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    public List<HQ_BAOLANH_HDVD> HQ_getDataBAOLANH_HDVD(int pID, String pStatus) throws RemoteException;

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
     * @param typeTrans
     * @return
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_HQ> HQ_searchNOPTIEN_HQ(String pMADV,
            String pSO_TK,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            String pSO_CT,
            String pKYHIEU_CT,
            int FromRow,
            int ToRow, String typeTrans) throws RemoteException;

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    public void HQ_updateNOPTIEN_HQ(HQ_NOPTIEN_HQ obj) throws RemoteException;

    /**
     *
     * @param pID
     * @param pMsgType
     * @param pStatus
     * @throws RemoteException
     */
    public void HQ_updateStatus(int pID, String pMsgType, String pStatus) throws RemoteException;

    /**
     *
     * @param pID
     * @param pMsgType
     * @param pMakerID
     * @param pBranchID
     * @throws RemoteException
     */
    public void HQ_deleteData(int pID, String pMsgType,
            String pMakerID,
            String pBranchID) throws RemoteException;

    /**
     *
     * @param ID
     * @return
     * @throws Exception
     */
    public ArrayList selectNDKT(String ID) throws Exception;

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
     * @param typeTrans
     * @return
     * @throws RemoteException
     */
    public int HQ_getSumRow(String pMADV,
            String pSO_TK,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            String pSO_CT,
            String pKYHIEU_CT, String typeTrans) throws RemoteException;

    /**
     *
     * @param BGNUMBER
     * @return
     * @throws Exception
     */
    public String[] HQ_GETBGINFO(String BGNUMBER) throws Exception;

    /**
     *
     * @param MsgType
     * @param pDate
     * @return
     * @throws RemoteException
     */
    public List<HQ_DOICHIEU> HQ_getDataDoichieu(String MsgType, Date pDate) throws RemoteException;

    /**
     *
     * @param pNGAY_DC
     * @param pTRANSACTIONID
     * @param PMSGTYPE
     * @param status
     * @param errnumber
     * @param errmsg
     * @throws RemoteException
     */
    public void HQ_InsertDoiChieu(String pNGAY_DC,
            String pTRANSACTIONID,
            String PMSGTYPE,
            String status,
            String errnumber,
            String errmsg) throws RemoteException;

    /**
     *
     * @param pMsgType
     * @param pIDREF
     * @param pNGAY_DC
     * @param pKQDC
     * @param pTRANSACTIONID
     * @throws RemoteException
     */
    public void HQ_InsertKQDoiChieu(
            String pMsgType,
            String pIDREF,
            String pNGAY_DC,
            String pKQDC,
            String pTRANSACTIONID
    ) throws RemoteException;

    /**
     *
     * @param pID
     * @param pMSGTYPE
     * @return
     * @throws RemoteException
     */
    public String HQ_getSO_TN_CT(String pID,
            String pMSGTYPE) throws RemoteException;

    /**
     *
     * @param LOAIDM
     * @param ID
     * @param ten
     * @return
     * @throws Exception
     */
    public ArrayList HQ_selectDANHMUC(String LOAIDM, String ID, String ten) throws Exception;

    /**
     *
     * @param PIDREF
     * @param PTYPE
     * @param PCHECKERID
     * @return
     * @throws Exception
     */
    public String[] HQ_APPROVEBAOLANH(
            int PIDREF,
            String PTYPE,// TK/CHUNG/HDVD
            String PCHECKERID) throws Exception;

    /**
     *
     * @param pTRANSACTION_ID
     * @param pSO_TN_CT
     * @param pNGAY_TN_CT
     * @param pKQ_DC
     * @param pNGAY_DC
     * @param pTYPETRANS
     * @param pMSGTYPE
     * @throws Exception
     */
    public void HQ_insertKQDOICHIEUHUY(
            String pTRANSACTION_ID,
            String pSO_TN_CT,
            String pNGAY_TN_CT,
            String pKQ_DC,
            String pNGAY_DC,
            String pTYPETRANS,
            String pMSGTYPE
    ) throws Exception;

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
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_CQQLT> HQ_searchNOPTIEN_CQQLT(
            String pSo_HS,
            String pMST,
            String pSO_CT,
            String pKHCT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws RemoteException;

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
     * @throws RemoteException
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
    ) throws RemoteException;

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    public void HQ_UPDATE_HQ_BAOLANH_TK(HQ_BAOLANH_TK obj) throws RemoteException;

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    public void HQ_UPDATE_HQ_BAOLANH_CHUNG(HQ_BAOLANH_CHUNG obj) throws RemoteException;

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    public void HQ_UPDATE_HQ_BAOLANH_HDVD(HQ_BAOLANH_HDVD obj) throws RemoteException;

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    public void HQ_UPDATE_HQ_NOPTIEN_CQQLT(HQ_NOPTIEN_CQQLT obj) throws RemoteException;

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
     * @throws RemoteException
     */
    public List<HQ_BAOLANH_TK> HQ_searchBAOLANH_TK(
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws RemoteException;

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
     * @throws RemoteException
     */
    public List<HQ_BAOLANH_CHUNG> HQ_searchBAOLANH_CHUNG(
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws RemoteException;

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
     * @throws RemoteException
     */
    public List<HQ_BAOLANH_HDVD> HQ_searchBAOLANH_HDVD(
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws RemoteException;

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
     * @throws RemoteException
     */
    public int HQ_getSumSearchBAOLANH(
            String pMSGTYPE,
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus) throws RemoteException;

    /**
     *
     * @param pMA_LH
     * @param pTEN_LH
     * @param pSN_AH
     * @throws RemoteException
     */
    public void HQ_insertDM_LH(String pMA_LH,
            String pTEN_LH,
            String pSN_AH) throws RemoteException;

    /**
     *
     * @param pMa_HQ
     * @param pTen_HQ
     * @param pMa_Cu
     * @param pMa_QHNS
     * @throws RemoteException
     */
    public void HQ_insertDM_HQ(String pMa_HQ,
            String pTen_HQ,
            String pMa_Cu,
            String pMa_QHNS) throws RemoteException;

    /**
     *
     * @param pMa_KB
     * @param pTen_KB
     * @throws RemoteException
     */
    public void HQ_insertDM_KB(String pMa_KB,
            String pTen_KB
    ) throws RemoteException;

    /**
     *
     * @param pTransID
     * @param pParnerID
     * @throws RemoteException
     */
    public void PaymentAndRegister(String pTransID, String pParnerID) throws RemoteException;

    /**
     *
     * @param pCardNumber
     * @param pParnerid
     * @return
     * @throws RemoteException
     */
    public String getProfileID(
            String pCardNumber,
            String pParnerid) throws RemoteException;

    //end duytxa08072015
    /**
     *
     * @return @throws RemoteException
     */
    public List getListAutoFeePayKHCN2() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public List getListAutoFeePayKHDN() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public List getListAutoFeeUnlock() throws RemoteException;

    //duytxa16062017
    //kimncm -- them ham thu phi tren ebanking mo rong
    /**
     *
     * @param cifno
     * @return
     * @throws RemoteException
     */
    public List<ThuPhiSMS> selectDSThuPhiEbank(String cifno) throws RemoteException;

    /**
     *
     * @param cifno
     * @param refcore
     * @param usermaker
     * @param chekeruser
     * @param paydate
     * @return
     * @throws RemoteException
     */
    public ProcedureCallDto UnlockUserThuPhi(String cifno, String refcore, String usermaker, String chekeruser, Date paydate) throws RemoteException;

    /**
     *
     * @param feeTrans
     * @return
     * @throws Exception
     */
    public int insertFeeTransactionUnlock(FeeTransactionUnlock feeTrans) throws Exception;

    /**
     *
     * @param feeTrans
     * @throws Exception
     */
    public void updateFeeTransactionUnlock(FeeTransactionUnlock feeTrans) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public FeeTransactionUnlock getFeeTransactionUnlockId(int id) throws Exception;

    /**
     *
     * @param feeTrans
     * @return
     * @throws Exception
     */
    public List<FeeTransactionUnlock> getFeeTransactionUnlockList(FeeTransactionUnlock feeTrans) throws Exception;

    /**
     *
     * @param cifno
     * @return
     * @throws RemoteException
     */
    public ProcedureCallDto getAccThuPhi(String cifno) throws RemoteException;

    /**
     *
     * @param cifno
     * @param refcore
     * @param paydate
     * @return
     * @throws RemoteException
     */
    public ProcedureCallDto updateThuphi(String cifno, String refcore, Date paydate) throws RemoteException;

    /**
     *
     * @param dataxml
     * @param idbillpaid
     * @return
     * @throws RemoteException
     */
    public int updDataxmlByIdbillpaid(String dataxml, String idbillpaid) throws RemoteException;

    /**
     *
     * @param P_IDSERVICETYPE
     * @param P_IDPROVIDER
     * @param cusCode
     * @return
     * @throws RemoteException
     */
    public ProcedureCallDto getListBill(String P_IDSERVICETYPE, String P_IDPROVIDER, String cusCode) throws RemoteException;

    /**
     *
     * @param pbt
     * @return
     * @throws RemoteException
     */
    public int insertPblBillpaidTransReverse(PblBillpaidTransReverse pbt) throws RemoteException;

    /**
     *
     * @param pbt
     * @throws RemoteException
     */
    public void updatePblBillpaidTransReverse(PblBillpaidTransReverse pbt) throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public PblBillpaidTransReverse getPblBillpaidTransReverseById(int id) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchMNLBill(String id, String polnum, String isapproved, String status, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws RemoteException;

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
     * @throws RemoteException
     */
    public ArrayList<?> searchMNLBillAll(String id, String polnum, String isapproved, String status, String customercode, String statusBill, String fromdate, String todate) throws RemoteException;

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    public int updateStatusMNL(String id, String status) throws RemoteException;

    //NANG CAP MOBILE 2017
    /**
     *
     * @param branchcode
     * @return
     * @throws RemoteException
     */
    public List GetDanhMucQuaTang(String branchcode) throws RemoteException;

    /**
     *
     * @param cusdetail
     * @param gitfdetail
     * @return
     * @throws RemoteException
     */
    public String GhiNhanThongTinDoiQua(GifCustomerDetail cusdetail, GiftDetail gitfdetail) throws RemoteException;

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
     * @throws RemoteException
     */
    public String PaymentLoanAccount(String fromAcc, String loanAcc, BigDecimal laitronghan, BigDecimal vontronghan, BigDecimal laiphatlai, BigDecimal laitphatgoc,
            BigDecimal laiphatkhac, String maker, String checker) throws RemoteException;

    /**
     *
     * @param loc
     * @param pan
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    public List LayLichSuDoiQua(String loc, String pan, String fromdate, String todate) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public List laydanhdachsanphambaohiem() throws RemoteException;

    /**
     *
     * @param IdSanPham
     * @return
     * @throws RemoteException
     */
    public List chitietsanphambaohiem(String IdSanPham) throws RemoteException;

    /**
     *
     * @param Ownerid
     * @param Polnum
     * @return
     * @throws RemoteException
     */
    public List thongtinkhmanulife(String Ownerid, String Polnum) throws RemoteException;

    /**
     *
     * @param idautoreg
     * @return
     * @throws RemoteException
     */
    public int huyhoadontudong(String idautoreg) throws RemoteException;

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    public List danhsachhoadontudong(String custno) throws RemoteException;

    /* --- SUNRISE --- */
    /**
     *
     * @param maKH
     * @param cardNo
     * @param cardAccountNo
     * @param accountNo
     * @return
     * @throws RemoteException
     */
    public String[] QUERY_PERSONAL_INFORMATION(String maKH, String cardNo, String cardAccountNo, String accountNo) throws RemoteException;

    /**
     *
     * @param MaKH
     * @return
     * @throws RemoteException
     */
    public int KIEM_TRA_THONG_TIN_KHACH_HANG_IS_EXISTING(String MaKH) throws RemoteException;

    /**
     *
     * @param cardAccountNo
     * @return
     * @throws RemoteException
     */
    public int GET_TOTAL_POINT_PENDING(String cardAccountNo) throws RemoteException;

    /**
     *
     * @param ID
     * @param MaKH
     * @param HoTen
     * @param CMND
     * @param DiaChi
     * @param NgayCap
     * @param NoiCap
     * @param GioiTinh
     * @param DienThoai
     * @param Loc
     * @param Pan
     * @param locPan
     * @param LoaiThe
     * @param HangThe
     * @param CrdStat
     * @param BrchCode
     * @param TheChinhPhu
     * @param TemLock
     * @param LocLMT
     * @param MaDVNhap
     * @param UidNhap
     * @param VNAID
     * @param Ho
     * @param TenTenDem
     * @return
     * @throws RemoteException
     */
    public String INSERT_THONG_TIN_KHACH_HANG(int ID, String MaKH, String HoTen, String CMND,
            String DiaChi, String NgayCap, String NoiCap, String GioiTinh, String DienThoai,
            String Loc, String Pan, String locPan, int LoaiThe, int HangThe, String CrdStat, String BrchCode,
            String TheChinhPhu, String TemLock, String LocLMT, String MaDVNhap,
            int UidNhap, String VNAID, String Ho, String TenTenDem) throws RemoteException;

    /* --- SUNRISE --- */
    /**
     *
     * @return @throws RemoteException
     */
    public String[] getListAccountClassRedemEbank() throws RemoteException;

    /**
     *
     * @param ITRANSID
     * @param ICUSTACCCOUNT
     * @param IAMOUNT
     * @param ICCY
     * @param ITRANSDATE
     * @param IPARTNERID
     * @param IDESCRIPTION
     * @param IMERCHANTID
     * @return
     * @throws RemoteException
     */
    public String[] ONL_PAYMENTWITHACCOUNT(String ITRANSID,
            String ICUSTACCCOUNT,
            Double IAMOUNT,
            String ICCY,
            String ITRANSDATE,
            String IPARTNERID,
            String IDESCRIPTION,
            String IMERCHANTID) throws RemoteException;

    /**
     *
     * @param pTransid
     * @param pRefTransid
     * @param pAmount
     * @param pTransdate
     * @param pDescription
     * @param pPartnerid
     * @return
     * @throws RemoteException
     */
    public String[] REFUND_PAYMENT_WITH_ACCOUNT(String pTransid, String pRefTransid,
            Double pAmount, String pTransdate, String pDescription,
            String pPartnerid) throws RemoteException;

    /**
     * Lay lich su giao dich the QT theo time
     *
     * @param panceCD
     * @param fromDate
     * @param toDate
     * @param SR
     * @param rownum
     * @return
     * @throws Exception
     */
    public List getTransactionMaterCardByCardnoByTime(String panceCD, String fromDate, String toDate, String SR, String rownum) throws Exception;

    /**
     *
     * @param cifno
     * @return
     * @throws RemoteException
     */
    public List GetListInsuranceByCif(String cifno) throws RemoteException;

    /**
     *
     * @param iddangky
     * @return
     * @throws RemoteException
     */
    public Insurance GetInsuranceById(int iddangky) throws RemoteException;

    /**
     *
     * @param branchcode
     * @param accountclass
     * @param maturedate
     * @return
     * @throws RemoteException
     */
    public ProcedureCallDto verifyOpenTD(String branchcode, String accountclass, String maturedate) throws RemoteException;

    /* ****************************** NGUYEN DAC BINH MINH ****************************** */
    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     */
    public String getKMFromPhoneNumber(String phoneNumber) throws RemoteException;

    /**
     *
     * @param TransID
     * @param PartnerID
     * @param PhoneNumber
     * @param ChannelID
     * @param TransType
     * @param OTP
     * @return
     * @throws RemoteException
     */
    public String[] OTP_REQUEST(String TransID, String PartnerID, String PhoneNumber,
            String ChannelID, String TransType, String OTP) throws RemoteException;

    /**
     *
     * @param TransID
     * @param RefTransID
     * @param PartnerID
     * @param ChannelID
     * @param TransType
     * @param OTP
     * @param TransDate
     * @return
     * @throws RemoteException
     */
    public String[] OTP_RESPONSE(String TransID, String RefTransID, String PartnerID,
            String ChannelID, String TransType, String OTP, String TransDate) throws RemoteException;

    /**
     *
     * @param accountNumber
     * @return
     * @throws RemoteException
     */
    public Object[] getAccountBalance(String accountNumber) throws RemoteException;

    /* ONEPAY */
    /**
     *
     * @param ITRANSID
     * @param IPARTNERID
     * @param ICARDNUMBER
     * @param ICARDNAME
     * @param ICARDDATE
     * @param ITRANSDATE
     * @param IDESCRIPTION
     * @return
     * @throws RemoteException
     */
    public String[] ONEPAY_CARD_VERIFICATION(String ITRANSID, String IPARTNERID,
            String ICARDNUMBER, String ICARDNAME, String ICARDDATE, String ITRANSDATE,
            String IDESCRIPTION) throws RemoteException;

    /**
     *
     * @param ITRANSID
     * @param IREFTRANSID
     * @param IPARTNERID
     * @param ICHANNELID
     * @param IVERIFYTYPE
     * @param IOTP
     * @param ITRANSDATE
     * @param IDESCRIPTION
     * @return
     * @throws RemoteException
     */
    public String[] ONEPAY_OTP_VERIFICATION(String ITRANSID, String IREFTRANSID,
            String IPARTNERID, String ICHANNELID, String IVERIFYTYPE, String IOTP,
            String ITRANSDATE, String IDESCRIPTION) throws RemoteException;

    /**
     *
     * @param ITRANSID
     * @param IREFTRANSID
     * @param IPARTNERID
     * @param IMERCHANTID
     * @param ICARDNUMBER
     * @param ICARDNAME
     * @param ICARDDATE
     * @param IAMOUNT
     * @param ICCY
     * @param ITRANSDATE
     * @param ICHANNELID
     * @param IDESCRIPTION
     * @return
     * @throws RemoteException
     */
    public String[] ONEPAY_PAYMENT(String ITRANSID, String IREFTRANSID,
            String IPARTNERID, String IMERCHANTID, String ICARDNUMBER, String ICARDNAME,
            String ICARDDATE, Double IAMOUNT, String ICCY, String ITRANSDATE,
            String ICHANNELID, String IDESCRIPTION) throws RemoteException;

    /**
     *
     * @param ITRANSID
     * @param IREFTRANSID
     * @param IPARTNERID
     * @param IMERCHANTID
     * @param ICARDNUMBER
     * @param ICARDNAME
     * @param ICARDDATE
     * @param IAMOUNT
     * @param ICCY
     * @param ITRANSDATE
     * @param ICHANNELID
     * @param IDESCRIPTION
     * @return
     * @throws RemoteException
     */
    public String[] ONEPAY_PAYMENT_WITHOUT_OTP(String ITRANSID, String IREFTRANSID,
            String IPARTNERID, String IMERCHANTID, String ICARDNUMBER, String ICARDNAME,
            String ICARDDATE, Double IAMOUNT, String ICCY, String ITRANSDATE,
            String ICHANNELID, String IDESCRIPTION) throws RemoteException;

    /**
     *
     * @param RefTransID
     * @param PartnerID
     * @param OTP
     * @param PhoneNumber
     * @param ChannelID
     * @param TransType
     * @throws RemoteException
     */
    public void PAYMENT_OTP_ADDING(String RefTransID, String PartnerID, String OTP,
            String PhoneNumber, String ChannelID, String TransType) throws RemoteException;

    /* ONEPAY */

 /* DAWACO */
    /**
     *
     * @param date
     * @return
     * @throws RemoteException
     */
    public List<DawacoCollate> getOutDAWACOCollate(String date) throws RemoteException;

    /* DAWACO */

 /* ****************************** NGUYEN DAC BINH MINH ****************************** */
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
     * @throws RemoteException
     */
    public int insertCardIPPTrans(String ID, String SRCACCOUNT, String PAN_LOC,
            String CARD_BRN, String CCY, String EXP_DATE, Double AMOUNT, String REF_FCC,
            String REF_CW, String TRANS_STATUS, String SRCCHANNEL, BigDecimal debtPay, BigDecimal debtCur, String IPPPayType)
            throws RemoteException;

    //Long.Le edit updateCardReloadTrans
    /**
     *
     * @param ID
     * @param REF_FCC
     * @param REF_CW
     * @param TRANS_STATUS
     * @return
     * @throws RemoteException
     */
    public int updateCardIPPTrans(String ID, String REF_FCC, String REF_CW, String TRANS_STATUS) throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getListCWWS_CARD_IPP(int id) throws RemoteException;

    /**
     *
     * @return @throws Exception
     */
    public int CHECK_LASTMONTH_DATE() throws Exception;

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
     * @throws RemoteException
     */
    public void updateChangeStatusPaybill(String id, String module, String partner,
            String serviceType, String provider, String merchant,
            String customerCode, String oldStatus, String newStatus,
            String userUpd) throws RemoteException;

    /**
     *
     * @param pCOLLATEDDATE
     * @param pFiletype
     * @param pTRANSTYPE
     * @param pSERVICE
     * @param pFileName
     * @param pPARTNERID
     * @return
     * @throws RemoteException
     */
    public String NAPAS_CHECK_LOG_DATE_COLLATED(String pCOLLATEDDATE,
            String pFiletype,
            String pTRANSTYPE,
            String pSERVICE,
            String pFileName,
            String pPARTNERID
    ) throws RemoteException;

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    public void NAPAS_InsertDataCollated(NapasCollatedId obj) throws RemoteException;

    /**
     *
     * @param pTRANSTYPE
     * @param pCOLLATEDDATE
     * @return
     * @throws RemoteException
     */
    public List<NapasCollatedId> NAPAS_getIBTCollateOut(
            String pTRANSTYPE,
            String pCOLLATEDDATE
    ) throws RemoteException;

    /**
     *
     * @param userid
     * @return
     * @throws RemoteException
     */
    public EBANKUSER ONL_getInfoEbankUser(String userid) throws RemoteException;

    /**
     *
     * @param pAccountID
     * @param pFullName
     * @param pPartnerID
     * @param AddInfo
     * @return
     * @throws RemoteException
     */
    public String[] ONL_RegisterProfileidWithAccount(String pAccountID,
            String pFullName,
            String pPartnerID, String AddInfo) throws RemoteException;

    /**
     *
     * @param PSCBID
     * @param pTRANSID
     * @param pPROFILEID
     * @param TransDate
     * @param PartnerID
     * @return
     * @throws RemoteException
     */
    public String ONL_UpdateRegisterProfileidWithACC(String PSCBID,
            String pTRANSID,
            String pPROFILEID,
            String TransDate, String PartnerID) throws RemoteException;
    //HAI QUAN ONLINE

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    public String[] HQ_INSERT_DKNNT(HQ_DKNNT obj) throws RemoteException;

    /**
     *
     * @param pID
     * @param pUSER
     * @param pBRANCHID
     * @param pSTATUS
     * @param pACCEPTED
     * @param pDesc
     * @throws RemoteException
     */
    public void HQ_DUYET_DKNNT(
            String pID,
            String pUSER,
            String pBRANCHID,
            String pSTATUS,
            String pACCEPTED,
            String pDesc
    ) throws RemoteException;

    /**
     *
     * @param pID
     * @param pType
     * @return
     * @throws RemoteException
     */
    public String[] HQ_getData213(String pID,
            String pType) throws RemoteException;

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
     * @throws RemoteException
     */
    public List<HQ_DKNNT> HQ_searchNNT(String pSO_HS,
            String pMST,
            String pBranchID,
            String pStatus,
            String FromDate,
            String ToDate, String STK) throws RemoteException;

    /**
     *
     * @param pID
     * @return
     * @throws RemoteException
     */
    public List<HQ_DKNNT> HQ_getData312(String pID) throws RemoteException;

    /**
     *
     * @param pRequestID
     * @param pTransID
     * @param pSO_HS
     * @throws RemoteException
     */
    public void HQ_UpdateDKNNT313(String pRequestID,
            String pTransID,
            String pSO_HS) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public String HQ_getSO_TN_CT_SCB() throws RemoteException;

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    public String[] HQ_INSERT_NOPTIEN_HQ_ONLINE(HQ_NOPTIEN_HQ obj) throws RemoteException;

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    public String[] HQ_INSERT_GNT_ONLINE(HQ_NOPTIEN_HQ_GNT obj) throws RemoteException;

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    public String[] HQ_INSERT_NOPTIEN_CQT_ONINE(HQ_NOPTIEN_CQQLT obj) throws RemoteException;

    /**
     *
     * @param ID
     * @param Type
     * @param xml
     * @return
     * @throws RemoteException
     */
    public String[] HQ_NOPTIEN_CORE_ONLINE(String ID, String Type, String xml) throws RemoteException;

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_HQ_GNT> HQ_getDataGNT_CT(String ID) throws RemoteException;

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_HQ_GNTCT> getDataTOKHAI_CT(String ID) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public List<HQ_NOPTIEN_HQ> HQ_getlistDataGNT() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public String HQ_getMaxDataCollated() throws RemoteException;

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    public void HQ_UPDATE_DKNNT(HQ_DKNNT obj) throws RemoteException;

    /**
     *
     * @param ID
     * @throws RemoteException
     */
    public void HQ_SendGNTHQ(String ID) throws RemoteException;

    /**
     *
     * @param pID
     * @return
     * @throws RemoteException
     */
    public List<HQ_DKNNT> HQ_getDataDKNNT(String pID) throws RemoteException;

    /**
     *
     * @param ID
     * @param Type
     * @return
     * @throws RemoteException
     */
    public int HQ_check213(String ID, String Type) throws RemoteException;

    /**
     *
     * @param pMa_KB
     * @param pTen_KB
     * @throws RemoteException
     */
    public void HQ_insertDM_ER(String pMa_KB,
            String pTen_KB
    ) throws RemoteException;

    /* MASTERPASS */
    /**
     *
     * @param sequenceNo
     * @param responseCode
     * @param responseDescription
     * @param approvalCode
     * @param typeTrans
     * @return
     * @throws RemoteException
     */
    public int INSERTCWDIRECTDEBIT(String sequenceNo, String responseCode, String responseDescription, String approvalCode, String typeTrans) throws RemoteException;

    public int MASTERPASSQR(PayByQRCodeRq req, MasterCardQrActionEnum action) throws RemoteException;

    /**
     *
     * @param sequenceNo
     * @param referenceNo
     * @param responseCode
     * @param responseDescription
     * @param typeTrans
     * @param loc4Digits
     * @param merPan
     * @return
     * @throws RemoteException
     */
    public int INSERTCWCARDADJUSTMENT(String sequenceNo, String referenceNo, String responseCode, String responseDescription, String typeTrans, String loc4Digits, String merPan, String refCore, String cardType) throws RemoteException;

    /**
     *
     * @param sequenceNo
     * @param response
     * @return
     * @throws RemoteException
     */
    public int INSERTMASTERCARDRES(String sequenceNo, MCPaymentRp response) throws RemoteException;

    /**
     *
     * @param req
     * @param senderInfor
     * @return
     * @throws RemoteException
     */
    public String INSERTMASTERPASSQR(PayByQRCodeRq req, MasterSenderInfor senderInfor) throws RemoteException;

    public String INSERTMASTERPASSQR(PayByQRCodeRq req) throws RemoteException;

    /**
     *
     * @param loc4Digit
     * @return
     * @throws RemoteException
     */
    public MasterSenderInfor queryMasterpassCardInfor(String loc4Digit) throws RemoteException;

    public SenderMSVSCardInfo querySenderMSVSCardInfo(String loc4Digit) throws RemoteException;

    /* MASTERPASS */
 /* VISA */
    /**
     *
     * @param req
     * @param senderInfor
     * @return
     * @throws RemoteException
     */
    public String INSERTVISAQR(MVISAQRRQ req, MasterSenderInfor senderInfor) throws RemoteException;

    public String INSERTVISAQR(MVISAQRRQ req) throws RemoteException;

    /**
     *
     * @param sequenceNo
     * @param response
     * @return
     * @throws RemoteException
     */
    public int INSERTVISAQRRES(String sequenceNo, ResponseMessage response) throws RemoteException;

    /* VISA */
 /* EVNHCM */
    /**
     *
     * @param partner
     * @param customerCode
     * @param totalAmount
     * @param refPartnerId
     * @return
     * @throws RemoteException
     */
    public String queryRefCustPayooBill(String partner, String customerCode, String totalAmount, String refPartnerId) throws RemoteException;

    /**
     *
     * @param partner
     * @param customerCode
     * @param totalAmount
     * @param refPartnerId
     * @param revertStatus
     * @return
     * @throws RemoteException
     */
    public int deletePayooBill(String partner, String customerCode, String totalAmount, String refPartnerId, String revertStatus) throws RemoteException;

    /**
     *
     * @param partnerID
     * @return
     * @throws RemoteException
     */
    public List<Pbl_billpaidCollate> getOutPbl_billpaidCollate(String partnerID) throws RemoteException;

    /**
     *
     * @param partnerID
     * @param comeback
     * @return
     * @throws RemoteException
     */
    public List<Pbl_billpaidCollate> getOutPbl_billpaidCollate(String partnerID, int comeback) throws RemoteException;

    /**
     *
     * @param datas
     * @return
     * @throws RemoteException
     */
    public List<EVNHCM> queryttttEVNHCM(List<Pbl_billpaidCollate> datas) throws RemoteException;

    /* EVNHCM */
    //Add new by LYDTY- 03/2018
    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public String GET_BRANCHCODE_FROM_FCC(String accountno) throws Exception;

    /**
     *
     * @param PTRANSID
     * @param pProfileID
     * @param PAMOUNT
     * @param PCCY
     * @param PTRANSDATE
     * @param PPARTNERID
     * @param PDESCRIPTION
     * @param PMERCHANTID
     * @param pChannelID
     * @param OTP
     * @return
     * @throws RemoteException
     */
    public String[] ONL_PAYMENTWITHPROFILEIDBYVERIFY(String PTRANSID,
            String pProfileID,
            Double PAMOUNT,
            String PCCY,
            String PTRANSDATE,
            String PPARTNERID,
            String PDESCRIPTION,
            String PMERCHANTID,
            String pChannelID,
            String OTP
    ) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public ProcedureCallDto checkTDIsEOD(String accountno) throws RemoteException;

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws RemoteException
     */
    public int insertAlertAccountTd2(SmsCustAlertTd smscustalerttd) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public String[] getListJoinAccount() throws RemoteException;

    //baovq - sms & tp
    /**
     *
     * @param cust_no
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> Find_List_Cust_No(String cust_no) throws RemoteException;

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws RemoteException
     */
    public int update_REF_STATUS(SmsCustAlertTd smscustalerttd) throws RemoteException;

    //evnhcm
    /**
     *
     * @param idpartner
     * @param idservicetype
     * @param idprovider
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getACCNOFCUBS(String idpartner, String idservicetype, String idprovider) throws RemoteException;

    /**
     *
     * @param pProfileID
     * @param PAMOUNT
     * @param PPARTNERID
     * @return
     * @throws RemoteException
     */
    public String[] ONL_checkProfileIDForPayment(
            String pProfileID,
            Double PAMOUNT,
            String PPARTNERID
    ) throws RemoteException;

    /**
     *
     * @param username
     * @param passInput
     * @return
     * @throws RemoteException
     */
    public boolean checkPassEB(String username, String passInput)
            throws RemoteException;

    /**
     *
     * @param username
     * @param newpass
     * @param channel
     * @return
     * @throws Exception
     */
    public int resetPass(String username, String newpass, String channel) throws Exception;

    /**
     *
     * @param username
     * @return
     * @throws RemoteException
     */
    public ArrayList getDetailUserEB(String username) throws RemoteException;

    /**
     *
     * @param brnTrn
     * @param productcode
     * @param ccy
     * @param amt
     * @param acNo
     * @param acBrn
     * @return
     * @throws RemoteException
     */
    public ProcedureCallDto getFeeMobile(String brnTrn, String productcode, String ccy, String amt, String acNo, String acBrn) throws RemoteException;
    //dvsms - baovq

    /**
     *
     * @param user
     * @return
     * @throws RemoteException
     */
    public int insertSMSServiceUser(SMSServiceUser user) throws RemoteException;

    /**
     *
     * @param userrefid
     * @param status
     * @param userid
     * @throws RemoteException
     */
    public void approveSMSService(int userrefid, String status, String userid) throws RemoteException;

    /**
     *
     * @param cust_no
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getListAccountByCif(String cust_no) throws RemoteException;

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> SMS_getAccountTK(String custno) throws RemoteException;

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> SMS_getListAccountTT(String custno) throws RemoteException;

    /**
     *
     * @param branch
     * @return
     * @throws Exception
     */
    public ArrayList<?> GET_TENNV(String branch) throws Exception;

    /**
     *
     * @param manv
     * @return
     * @throws Exception
     */
    public ArrayList<?> QUERY_TENNV(String manv) throws Exception;

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    public String[] checkDKDVSMS(String custno) throws RemoteException;

    /**
     *
     * @param custno
     * @param idcard
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> searchCustomerCore(String custno, String idcard) throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> get_data_approve(String id) throws RemoteException;

    /**
     *
     * @param smsuser
     * @return
     * @throws Exception
     */
    public int update_REF_STATUS_SMSTK(SMSServiceUser smsuser) throws Exception;

    /**
     *
     * @param cust_no
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> CHECK_POINT_REWARD_THU(String cust_no) throws RemoteException;

    /**
     *
     * @param cust_no
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> CHECK_POINT_REWARD_CHI(String cust_no) throws RemoteException;

    /**
     *
     * @param smsservice
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAccountSMSService(SMSService smsservice) throws RemoteException;

    /**
     *
     * @param cust_no
     * @param registertype
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> findHistoryListSMSService(String cust_no, String registertype) throws RemoteException;

    /**
     *
     * @param smsservice
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAccountSMSSERVICEById(SMSService smsservice) throws RemoteException;

    /**
     *
     * @param cust_no
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> CHECK_POINT_REWARD_TC(String cust_no) throws RemoteException;

    /**
     *
     * @param P_TRN_REF
     * @param P_XREF
     * @param pm_gdv
     * @param pm_ksv
     * @return
     * @throws RemoteException
     */
    public String revertTransferFCUBS(String P_TRN_REF, String P_XREF, String pm_gdv, String pm_ksv) throws RemoteException;


    /* VNPAY QR */
    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     */
    public String INSERTCHECKQR(CheckQRRq req) throws RemoteException;

    /**
     *
     * @param res
     * @return
     * @throws RemoteException
     */
    public boolean UPDATECHECKQR(CheckQRRp res) throws RemoteException;

    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     */
    public PaymentQRJson INSERTPAYMENTQR(PaymentQRRq req) throws RemoteException;

    /**
     *
     * @param qrPayment
     * @param res
     * @return
     * @throws RemoteException
     */
    public boolean UPDATEPAYMENTQR(PaymentQRJson qrPayment, PaymentQRRp res) throws RemoteException;

    /**
     *
     * @param qrPayment
     * @return
     * @throws RemoteException
     */
    public RefundQRJson INSERTREFUNDQR(PaymentQRJson qrPayment) throws RemoteException;

    /**
     *
     * @param qrRefund
     * @param res
     * @return
     * @throws RemoteException
     */
    public boolean UPDATEREFUNDQR(RefundQRJson qrRefund, RefundQRRp res) throws RemoteException;

    /* TIMER_SMS */
    /**
     *
     * @param phoneNumber
     * @return
     * @throws Exception
     */
    public boolean checkSCBCustomer(String phoneNumber) throws Exception;

    /**
     *
     * @param phoneNumber
     * @param splitMessage
     * @return
     * @throws Exception
     */
    public String[] queryBalance(String phoneNumber, String[] splitMessage) throws Exception;

    /**
     *
     * @param phoneNumber
     * @return
     * @throws Exception
     */
    public String getAccDefaultFromMobile(String phoneNumber) throws Exception;

    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] updatePhoneNumberTo10(String phoneNumber) throws RemoteException, SQLException;

    /*ATMONLINE AND VAP*/
    /**
     *
     * @param pPARTNERID
     * @param Transid
     * @return
     * @throws RemoteException
     */
    public List SI_querryTransfer(String pPARTNERID, String Transid) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList SI_getListBank247() throws RemoteException;

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
     * @throws RemoteException
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
            String pSTATUSTRANS) throws RemoteException;

    /**
     *
     * @param pDate
     * @param pPartnerid
     * @param pType
     * @return
     * @throws RemoteException
     */
    public ArrayList TCH_getOutCollated(Date pDate, String pPartnerid, String pType) throws RemoteException;

    /*END ATMONLINE AND VAP*/
    /**
     *
     * @param obj
     * @throws Exception
     */
    public int NAPAS_INSERTIBTLOG(NAPAS_IBT obj) throws Exception;

    /**
     *
     * @param request
     * @return
     * @throws RemoteException
     */
    public String NAPAS_InsertRequestIBT(String request) throws RemoteException;

    /**
     *
     * @param reponse
     * @param ID
     * @throws RemoteException
     */
    public void NAPAS_insertResponseIBT(String reponse, String ID) throws RemoteException;

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public String NAPAS_getResponseIBT(String ID) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public String NAPAS_getRequestIBT() throws RemoteException;

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
     * @throws RemoteException
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
            String pm_ksv) throws RemoteException;

    /**
     *
     * @param pID
     * @return
     * @throws RemoteException
     */
    public ArrayList getDetailDCbyID(String pID) throws RemoteException;

    /**
     *
     * @param PPARTNERID
     * @param pProfileID
     * @param pCMND
     * @param pMobile
     * @param pAddInfo
     * @return
     * @throws RemoteException
     */
    public String ONL_checkKYC(
            String PPARTNERID,
            String pProfileID,
            String pCMND,
            String pMobile,
            String pAddInfo) throws RemoteException;

    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     */
    public List<CardInfo> GetCardInfoByPhone(String phoneNumber) throws RemoteException;

    /**
     *
     * @param phoneNumber
     * @param last4Digit
     * @return
     * @throws RemoteException
     */
    public List<CardInfo> GetCardInfoByPhone(String phoneNumber, String last4Digit) throws RemoteException;

    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     */
    public List<CardInfo> getCardInfo(KhoaTheReq req) throws RemoteException;

    /**
     *
     * @param panEncrypt
     * @return
     * @throws RemoteException
     */
    public String GetCardTransferStatus(String panEncrypt) throws RemoteException;

    /**
     *
     * @param card
     * @return
     * @throws RemoteException
     */
    public String INSERTLOCKTHE8149(CardInfo card) throws RemoteException;

    /**
     *
     * @param sequence
     * @param respCode
     * @param respDesc
     * @return
     * @throws RemoteException
     */
    public boolean UPDATELOCKTHE8149(String sequence, String respCode, String respDesc) throws RemoteException;

    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     */
    public boolean INSERTKHOATHE(KhoaTheReq req) throws RemoteException;

    /**
     *
     * @param req
     * @param card
     * @return
     * @throws RemoteException
     */
    public String INSERTKHOATHEDETAILS(KhoaTheReq req, CardInfo card) throws RemoteException;

    /**
     *
     * @param refNo
     * @param req
     * @param respCode
     * @param respDesc
     * @return
     * @throws RemoteException
     */
    public boolean UPDATEKHOATHEDETAILS(String refNo, KhoaTheReq req, String respCode, String respDesc) throws RemoteException;

    /**
     *
     * @param acccountNo
     * @param branchcode
     * @param amount
     * @return
     * @throws RemoteException
     */
    public ProcedureCallDto validateTopupTKTichLuy(String acccountNo, String branchcode, String amount) throws RemoteException;

    //IBT 247
    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList getIBTBank() throws RemoteException;

    /**
     *
     * @param cust_no
     * @return
     * @throws RemoteException
     */
    public ArrayList GET_MOBILE_NUMBER(String cust_no) throws RemoteException;

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    public String[] IBT_INSERT_CTTQ(SmlFtCounter obj) throws RemoteException;

    /**
     *
     * @param pID
     * @return
     * @throws RemoteException
     */
    public List<SmlFtCounter> IBT_getDataCTTQ(String pID) throws RemoteException;

    /**
     *
     * @param status
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> CHECK_STATUS_IBT(String status) throws RemoteException;

    /**
     *
     * @param pID
     * @param pUSER
     * @param pBRANCH_CODE
     * @param pSTATUS
     * @param pTYPEFUNCTION
     * @param pREFCORE
     * @throws RemoteException
     */
    public int IBT_DUYET_CTTQ(String pID, String pUSER, String pBRANCH_CODE, String pSTATUS, String pTYPEFUNCTION, String pREFCORE) throws RemoteException;

    /**
     *
     * @param id
     * @param user
     * @param branch
     * @param status
     * @param refcore
     * @throws RemoteException
     */
    public void IBT_TUCHOI_CTTQ(String id, String user, String branch, String status, String refcore) throws RemoteException;

    /**
     *
     * @param madv
     * @param trangthai
     * @param tungay
     * @param denngay
     * @param tknguon
     * @param tkdich
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> SEARCH_IBT(String madv, String trangthai, String tungay, String denngay, String tknguon, String tkdich) throws RemoteException;

    /**
     *
     * @param pCARDNO
     * @return
     * @throws RemoteException
     */
    public String[] checkCARD(String pCARDNO) throws RemoteException;

    /**
     *
     * @param ID
     * @throws RemoteException
     */
    public int NAPAS_updateSendIBT(String ID
    ) throws RemoteException;

    /**
     *
     * @param ID
     * @return
     * @throws Exception
     */
    public ArrayList<?> CHECK_TRANSFER(String ID) throws Exception;

    /**
     *
     * @param ITRANSID
     * @param ISOURCEACCOUNT
     * @param IDESTACCCOUNT
     * @param IAMOUNT
     * @param ICCY
     * @param ITRANSDATE
     * @param IPARTNERID
     * @param IDESCRIPTION
     * @return
     * @throws RemoteException
     */
    public String[] MMSTRANSFERACCTOACC(String ITRANSID, String ISOURCEACCOUNT, String IDESTACCCOUNT,
            Double IAMOUNT, String ICCY, String ITRANSDATE,
            String IPARTNERID, String IDESCRIPTION) throws RemoteException;

    /**
     *
     * @param IBANKID
     * @param IREFCORE
     * @return
     * @throws RemoteException
     */
    public boolean UPDATEMMSTRANSFERACCTOACC(String IBANKID, String IREFCORE) throws RemoteException;

    /**
     *
     * @param P_MERCHANT_CODE
     * @param P_TERMINAL_ID
     * @param P_ACCOUNT_MERCHANT
     * @return
     * @throws RemoteException
     */
    public boolean MMSCHECKACCOUNT(String P_MERCHANT_CODE, String P_TERMINAL_ID, String P_ACCOUNT_MERCHANT) throws RemoteException;

    /**
     *
     * @param id
     * @param reponse
     * @throws RemoteException
     */
    public void napas_insertReponseHM(String id, String reponse) throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public String napas_getReponseHM(String id) throws RemoteException;

    /**
     *
     * @param trace
     * @param request
     * @throws RemoteException
     */
    public void napas_insertRequestHM(String trace, String request) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public List napas_getRequestHM() throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public SCBBranch getSCBBranch() throws RemoteException;

    /**
     *
     * @param brnTrn
     * @param productcode
     * @param ccy
     * @param amt
     * @param acNo
     * @param acBrn
     * @return
     * @throws RemoteException
     */
    public ProcedureCallDto getFeeMobileHasSFee(String brnTrn, String productcode, String ccy, String amt, String acNo, String acBrn) throws RemoteException;

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    public ArrayList getAvgBalanceFromCore(String accountno) throws RemoteException;

    /**
     *
     * @param pTransid
     * @param pRefTransid
     * @param pAmount
     * @param pTransdate
     * @param pDescription
     * @param pPartnerid
     * @return
     * @throws RemoteException
     */
    public String[] ONL_REFUND_FOR_TAKE_OUT(String pTransid,
            String pRefTransid,
            Double pAmount,
            String pTransdate,
            String pDescription,
            String pPartnerid) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public boolean isAlive() throws RemoteException;

    /**
     *
     * @param list
     * @throws RemoteException
     */
    public void TVSI_insertKhachHang(
            List<TVSI_KHACHHANG> list) throws RemoteException;

    /**
     *
     * @param list
     * @throws RemoteException
     */
    public void TVSI_insertDanhMuc(
            List<TVSI_DANHMUC> list) throws RemoteException;

    /**
     *
     * @param list
     * @throws RemoteException
     */
    public void TVSI_insertGiaoDich(
            List<TVSI_GIAODICH> list) throws RemoteException;

    /**
     *
     * @param list
     * @throws RemoteException
     */
    public void TVSI_insertGiaoDichHuy(
            List<TVSI_GIAODICHHUY> list) throws RemoteException;

    /**
     *
     * @param list
     * @throws RemoteException
     */
    public void TVSI_insertSaoKe(
            List<TVSI_SAOKE> list) throws RemoteException;

    /**
     *
     * @return @throws Exception
     */
    public String getTransIDMK() throws Exception;

    /**
     *
     * @param serialno
     * @param challengeid
     * @return
     * @throws Exception
     */
    public ArrayList getMKVerifyInfo(String serialno, String challengeid) throws Exception;

    /**
     *
     * @param SERIAL
     * @param CHALLENGE
     * @param TRANSID
     * @param AUDITID
     * @return
     * @throws Exception
     */
    public int INSERT_MK_TOKEN_LOG(String SERIAL, String CHALLENGE, String TRANSID, String AUDITID) throws Exception;

    /**
     *
     * @param idServiceCode
     * @param idProviderCode
     * @return
     * @throws RemoteException
     */
    public String getProviderOriginalCode(String idServiceCode, String idProviderCode) throws RemoteException;

    /**
     *
     * @param idServiceCode
     * @param idProviderCode
     * @return
     * @throws RemoteException
     */
    public String getPartnerCode(String idServiceCode, String idProviderCode) throws RemoteException;

    /**
     *
     * @param userid
     * @param usertype
     * @return
     * @throws RemoteException
     */
    public EBANKUSER ONL_getInfoEbankUser(String userid, String usertype) throws RemoteException;

    /**
     *
     * @param GNT
     * @return
     * @throws Exception
     */
    public String[] NTDT_INSERT_GNT2019(NTDT_GNT GNT) throws Exception;

    /**
     *
     * @param pID_GNT
     * @param pID_CTU_CTIET
     * @param pID_CTU
     * @param pNDUNG_NOP
     * @param pMA_NDKT
     * @param pMA_CHUONG
     * @param pKY_THUE
     * @param pTIEN_PNOP
     * @param pGHI_CHU
     * @param pSO_TK_TB_QD
     * @throws RemoteException
     */
    public void NTDT_INSERT_CHUNGTU_CHIIET2019(int pID_GNT,
            String pID_CTU_CTIET,
            String pID_CTU,
            String pNDUNG_NOP,
            String pMA_NDKT,
            String pMA_CHUONG,
            String pKY_THUE,
            String pTIEN_PNOP,
            String pGHI_CHU,
            String pSO_TK_TB_QD) throws RemoteException;

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pMABAOCAO
     * @return
     * @throws RemoteException
     */
    public List<NTDT_DOICHIEUGD> NTDT_BAOCAODOICHIEU_2019(int pID_DOICHIEU_GD, String pMABAOCAO) throws RemoteException;

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pMA_GDICH
     * @param pNGAY_GUI_GDICH
     * @param pNGAY_NOP_GNT
     * @param pNGAY_NOP_THUE
     * @param pTTHAI_GDICH
     * @param pTTHAI_CTU
     * @param pMAHIEU_CTU
     * @param pSO_CTU
     * @param pPBAN_TLIEU_XML
     * @param pID_CTU
     * @param pSO_GNT
     * @param pMA_CTU
     * @param pHTHUC_NOP
     * @param pMST_NNOP
     * @param pTEN_NNOP
     * @param pMA_CQT
     * @param pTEN_CQT
     * @param pDIACHI_NNOP
     * @param pMA_XA_NNOP
     * @param pTEN_XA_NNOP
     * @param pMA_HUYEN_NNOP
     * @param pTEN_HUYEN_NNOP
     * @param pMA_TINH_NNOP
     * @param pTEN_TINH_NNOP
     * @param pMST_NTHAY
     * @param pTEN_NTHAY
     * @param pDIACHI_NTHAY
     * @param pTEN_HUYEN_NTHAY
     * @param pTEN_TINH_NTHAY
     * @param pMA_NHANG_NOP
     * @param pTEN_NHANG_NOP
     * @param pSTK_NHANG_NOP
     * @param pMA_HIEU_KBAC
     * @param pTEN_KBAC
     * @param pMA_TINH_KBAC
     * @param pTEN_TINH_KBAC
     * @param pLOAI_TK_THU
     * @param pTEN_TK_THU
     * @param pSTK_THU
     * @param pID_TK_KNGHI
     * @param pTK_KNGHI
     * @param pMA_CQTHU
     * @param pTEN_CQTHU
     * @param pNGAY_LAP
     * @param pTONG_TIEN
     * @param pVAN_ID
     * @param MaNguyente
     * @param Mathamchieu
     * @return
     * @throws Exception
     */
    public int NTDT_INSERT_DOICHIEU_GNT2019(int pID_DOICHIEU_GD,
            String pMA_GDICH,
            String pNGAY_GUI_GDICH,
            String pNGAY_NOP_GNT,
            String pNGAY_NOP_THUE,
            String pTTHAI_GDICH,
            String pTTHAI_CTU,
            String pMAHIEU_CTU,
            String pSO_CTU,
            String pPBAN_TLIEU_XML,
            String pID_CTU,
            String pSO_GNT,
            String pMA_CTU,
            String pHTHUC_NOP,
            String pMST_NNOP,
            String pTEN_NNOP,
            String pMA_CQT,
            String pTEN_CQT,
            String pDIACHI_NNOP,
            String pMA_XA_NNOP,
            String pTEN_XA_NNOP,
            String pMA_HUYEN_NNOP,
            String pTEN_HUYEN_NNOP,
            String pMA_TINH_NNOP,
            String pTEN_TINH_NNOP,
            String pMST_NTHAY,
            String pTEN_NTHAY,
            String pDIACHI_NTHAY,
            String pTEN_HUYEN_NTHAY,
            String pTEN_TINH_NTHAY,
            String pMA_NHANG_NOP,
            String pTEN_NHANG_NOP,
            String pSTK_NHANG_NOP,
            String pMA_HIEU_KBAC,
            String pTEN_KBAC,
            String pMA_TINH_KBAC,
            String pTEN_TINH_KBAC,
            String pLOAI_TK_THU,
            String pTEN_TK_THU,
            String pSTK_THU,
            String pID_TK_KNGHI,
            String pTK_KNGHI,
            String pMA_CQTHU,
            String pTEN_CQTHU,
            String pNGAY_LAP,
            String pTONG_TIEN,
            String pVAN_ID,
            String MaNguyente,
            String Mathamchieu) throws Exception;

    /**
     *
     * @param LOC
     * @param socuoi
     * @return
     * @throws Exception
     */
    public String[] getDetailCard(String LOC, String socuoi) throws Exception;

    /**
     *
     * @param LOC
     * @param Pass
     * @return
     * @throws Exception
     */
    public int cw_checkPassFile(String LOC, String Pass) throws Exception;

    /**
     *
     * @param LOC
     * @return
     * @throws Exception
     */
    public Object[] cw_getPdfFile(String LOC) throws Exception;

    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     */
    public ExchangeRateRes getExchangeRate(ExchangeRateReq req) throws RemoteException;

    /**
     *
     * @param idTrans
     * @param res
     * @return
     * @throws RemoteException
     */
    public boolean updateRateToSI_TRFFROMSI_DETAIL(String idTrans, ExchangeRateRes res) throws RemoteException;

    /**
     *
     * @param transDetail
     * @return
     * @throws RemoteException
     */
    public Object[] insertDetailToSi(TransactionDetail transDetail) throws RemoteException;

    /**
     *
     * @param pTRANSTYPE
     * @param isCollated
     * @return
     * @throws RemoteException
     */
    public List<NapasCollatedId> NAPAS_getIBTCollateOut(
            String pTRANSTYPE,
            int isCollated
    ) throws RemoteException;

    /**
     *
     * @param CARDNUMBER
     * @param mobileno
     * @param fullname
     * @return
     * @throws Exception
     */
    public String cw_INSERT_INFO_CHANGE_PIN(String CARDNUMBER, String mobileno, String fullname) throws Exception;

    /**
     *
     * @param FromNumber
     * @param AuditNumber
     * @param MerchantType
     * @param Transdate
     * @return
     * @throws Exception
     */
    public ArrayList<?> NAPAS_isExistTransfer(String FromNumber, String AuditNumber, String MerchantType,
            String Transdate) throws Exception;

    /**
     *
     * @param emailTd
     * @return
     * @throws RemoteException
     */
    public BigDecimal insertGwEmailTd(GwEmailTd emailTd) throws RemoteException;

    /**
     *
     * @param emailTd
     * @param fromDate
     * @param toDate
     * @return
     * @throws RemoteException
     */
    public List getListGwEmailTd(GwEmailTd emailTd, String fromDate, String toDate) throws RemoteException;

    /**
     *
     * @param emailTd
     * @throws RemoteException
     */
    public void updateGwEmailTd(GwEmailTd emailTd) throws RemoteException;

    /**
     *
     * @param refno
     * @return
     * @throws Exception
     */
    public Object[] IBT_getRequestTranfer(String trace, String status) throws Exception;

    /**
     *
     * @param info
     * @return
     * @throws RemoteException
     */
    public boolean KICHHOATTHE(KichHoatTheInfo info) throws RemoteException;

    public boolean CIMS_RESEND(SmsDetail info, int action) throws RemoteException;

    public List<SmsDetail> QUERY_SMS_TO_RESEND(String phone, String fromDate, String toDate) throws RemoteException;

    public SmsDetail QUERY_SMS_TO_RESEND_BY_ID(String id) throws RemoteException;

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
     * @throws RemoteException
     */
    public Object[] SI_INSERTTRANSFERFROMSIDETAILKIEUHOI(
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
    ) throws RemoteException;

    /**
     *
     * @param khoaTheInfo
     * @return
     * @throws RemoteException
     */
    public boolean INSERTKHOATHE(KhoaTheInfo khoaTheInfo) throws RemoteException;

    /**
     *
     * @param khoaTheDetailsInfo
     * @return
     * @throws RemoteException
     */
    public String INSERTKHOATHEDETAIL(KhoaTheDetailsInfo khoaTheDetailsInfo) throws RemoteException;

    /**
     *
     * @param khoaTheInfo
     * @return
     * @throws RemoteException
     */
    public boolean UPDATEKHOATHE(KhoaTheInfo khoaTheInfo) throws RemoteException;

    /**
     *
     * @param khoaTheDetailsInfo
     * @return
     * @throws RemoteException
     */
    public boolean UPDATEKHOATHEDETAIL(KhoaTheDetailsInfo khoaTheDetailsInfo) throws RemoteException;

    //BEGIN: SONNN5 - UPDATE - 13/NOV/2019  
    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public int CTTQ_DELETE(String id) throws RemoteException;

    /**
     *
     * @param pGL
     * @param brcode
     * @param ccy
     * @param type
     * @return
     * @throws RemoteException
     */
    public float GET_GL_BALANCE(String pGL, String brcode, String ccy, String type) throws RemoteException;

    //SON 01/nov/2019
    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public int DELETE_INSURANCE_PAYMENT(String id) throws RemoteException;

    //SON 03/nov/2019
    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public int DELETE_PAYMENT_CREDITCARD(String id) throws RemoteException;
    //END: SONNN5 - UPDATE - 13/NOV/2019 

    /**
     *
     * @param emailTd
     * @return
     * @throws RemoteException
     */
    public String insertGwTdLuck(GwTdLucky emailTd) throws RemoteException;

    /**
     *
     * @param tdLuck
     * @throws RemoteException
     */
    public void updateGwTdLuck(GwTdLucky tdLuck) throws RemoteException;

    /**
     *
     * @param ID
     * @throws Exception
     */
    public void HQ_UPDATE_HQ_ONLINE_201(String ID) throws Exception;

    /**
     *
     * @param SO_HS
     * @param MA_DV
     * @param status
     * @return
     * @throws Exception
     */
    public List<HQ_DKNNT> HQ_searchDKUQ(String SO_HS, String MA_DV, String status) throws Exception;

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public String[] HQ_INSERT_HQ_NOPTIEN_HQ_GNT_201(HQ_NOPTIEN_HQ_GNT obj) throws Exception;

    /**
     *
     * @param branch
     * @param number
     * @return
     * @throws RemoteException
     */
    public List<VwSmsThuphi> getThuPhiKhcnTheoBranch(String branch, int number) throws RemoteException;

    public List<VwSmsThuphi> getThuPhiKhcn(String branch, int number) throws RemoteException;

    public int isNoPhi(String custNo) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public List<VwSmsThuphi> getThuPhiKhdn() throws RemoteException;

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public int IBT_checkCounter(String ID) throws RemoteException;

    /**
     *
     * @param idPartner
     * @return
     * @throws RemoteException
     */
    public List<Pbl_partnercode> getPblPartnerCode(String idPartner) throws RemoteException;

    /**
     *
     * @param cif
     * @param number
     * @return
     * @throws RemoteException
     */
    public List<VwSmsThuphi> getUnlockPhi(String cif, int number) throws RemoteException;

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public GwEmailTd getSTKNeedToResend(String id) throws RemoteException;

    /**
     *
     * @param pMd5User
     * @return
     * @throws Exception
     */
    public MBUser getUserMBInfo(String pMd5User) throws Exception;

    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public ArrayList checkMBUser(String username) throws Exception;

    /**
     *
     * @param md5User
     * @return
     * @throws Exception
     */
    public int updateChangeUser(String md5User) throws Exception;

    /**
     *
     * @param userName
     * @param cif
     * @return
     * @throws RemoteException
     */
    public IsExistUserEBRes isExistUserBanking(String userName, String cif) throws RemoteException;

    /**
     *
     * @param key
     * @param value
     * @return
     * @throws RemoteException
     */
    public boolean setConnectionManager(String key, boolean value) throws RemoteException;

    /**
     *
     * @param key
     * @return
     * @throws RemoteException
     */
    public int getConnectionManager(String key) throws RemoteException;

    /**
     *
     * @param value
     * @return
     * @throws RemoteException
     */
    public boolean insertOrgBillPaid(OrgBillPaid value) throws RemoteException;

    /**
     *
     * @param req
     * @param res
     * @param action
     * @return
     * @throws RemoteException
     */
    public CardAdjustmentRes cardAdjustment(CardAdjustmentReq req, CardAdjustmentRes res, DBActionEnum action) throws RemoteException;

    public List<SmsOtt> getMessageOtt(int isMulti) throws Exception;

    public int updateSendOtt(int id, int pstatus, String code, String desc, int nultiid) throws Exception;

    public List<PayOnlineCollate> ONL_AnswerCollateData(String pPartnerID, String pTransdate) throws Exception;

    public void ONL_ReceiveCollateData(PayOnlineCollate collate) throws RemoteException;

    public int VISAQR(MVISAQRRQ req, MasterCardQrActionEnum action) throws RemoteException;

    public String PROCESS_SMS_ALERT(SmsCustRegisInfo obj) throws Exception;

    public List<SmsCustRegisInfo> getListRegisterSMS(String CustNo) throws Exception;

    public void IBT_UPDATE_STATUS_TRANSFER(String pTrace,
            String pStatus,
            String pRefcore,
            String pRefcoreRefund,
            String pREF_NO_F37,
            String pSETT_DATE_F15,
            String pTypeTransfer,
            String pResponseCode,
            String pAuthorizationcode,
            String pTransREFNOF63,
            String pF5) throws Exception;

    public CustomerInfoRsDto ONL_checkCustInfo(OnlinePCustomerInfoDto customerInfo) throws RemoteException;

    public Boolean PBL_insertPBLBillPaidCusDtls(List<PBLBillPaidCustomerDtlForJobDto> billPaidCustDtls) throws Exception;

    public List<PBLBillPaidCusDto> PBL_getAllPBLBillPaidCus(List<String> partnerIds, List<String> providerIds, List<String> servicetypeIds) throws Exception;

    public void updateIBTResponse(String pTrace, String pStatus, String pResponseCode, String pRefcoreRefund, String pSett_date_F15) throws Exception;

    public List<BillPaidCustGrpDto> PBL_getParameterForBillPaidWS() throws Exception;

    public Boolean isExistPartner(String accountNo) throws Exception;

    public PmtInfoV11ResDto getPMT_INFOV11(String param) throws Exception;

    public String getSeqRefNo(String type) throws Exception;

    public int InsertSMLLOGXref(String pTYPETRANSFER, String pFROMNUMBER, String pPROCESSINGCODE,
            BigDecimal pTRANSAMOUNT, String pTRANSDATE,
            String pAUDITNUMBER, String pMERCHANTTYPE,
            String pACQUIRINGCODE, String pAUTHORIZATIONCODE, String pRESPONSECODE,
            String pTERMID, String pCARDACCEPTTOR, String pDESTNUMBER,
            String pNARRATION, String pBENID, String pTYPEFUNCTION, String pStatus,
            String pRefCore, String pCustNo, String RefCORE_REFUND, String pREF_NO_F37, String pSETT_DATE_F15, String Xref) throws Exception;

    public InsuranceCustInfoDto getCustInfoByCIFID(String cif) throws Exception;

    public void updateIBTResponse(String pTrace, String pStatus,
            String pResponseCode, String pRefcoreRefund, String pSett_date_F15, String ID) throws Exception;

    public int updateIBT(String AuditNumber, String Status, String RefCORE, String typeTransfer, String ID, String xref) throws Exception;

    public int checkPartnerPregolive(String partnerid) throws Exception;

    public int checkAccountPregolive(String partnerid, String accountno) throws Exception;

    public int updateResultToSCB(String AuditNumber, String Status, String RefCORE, String ID, String responsecode) throws Exception;

    public void UpdateRevertTransfer(String pTrace,
            String pRefcore,
            String pRefcoreRefund, String ID) throws Exception;

    public int insertIBTRequestLog(String trace) throws Exception;

    public void UPDATEIBTRANSFER(String pTrace,
            String pStatus,
            String pREF_NO_F37,
            String pSETT_DATE_F15,
            String pResponseCode,
            String pAuthorizationcode,
            String pTransREFNOF63,
            String pF5) throws Exception;

    public void updateSendIBT_response(String ID, String Status) throws Exception;

    public TransferMoneyTransactionInfo checkStatusTransferMoney(GetStatusOfTransferMoneyReq req) throws Exception;

    public int IBT_updateCannotSend(String trace) throws Exception;

    public List<FeedbackInfo> getListFeedback(String isChecked) throws Exception;

    public List<RegisterInfoDetail> getListRegisterDetail(String isChecked) throws Exception;

    public int updateListReceiveFeedback(String isChecked, String id, String type, String idChecker, String idChannelChecker) throws Exception;

    public boolean insertQRPayment(QRPAYMENT qrPayment) throws Exception;

    public boolean updateQRPayment(QRPAYMENT qrPayment) throws Exception;

    public Object[] QR_CHECK_PCODE(QRPAYMENT qrPayment) throws Exception;

    public CustomerInfo getCustInfoByCif(String cifno) throws Exception;

    public long insertHealthInsBHBL(BHBLHealthCareIns payins, BHBLHealthCareInsRq.Questions ques) throws Exception;

    public void updateHealthInsBHBL(BHBLHealthCareIns payins) throws RemoteException;

    public List<BHBLPackageCostResp> BHBL_getBLPackageCost(int tuoi, int gioiTinh, String lang) throws Exception;

    public List<BHBLMetadataRes> BHBL_getBLCategories(String type) throws Exception;

    public List<BHBLQuestionResp> BHBL_getBLAllQuestions(String lang) throws Exception;

    public String BHBL_updatePaymentStatus(BHBLPaymentContractInfoRq contractInfo) throws Exception;

    public List<BHBLCstContractCollectedByTKResp> BHBL_collectedCustConTractByTK() throws Exception;

    public List<BHBLCstContractCollectedByTheResp> BHBL_collectedCustConTractByThe() throws Exception;

    public List<BHBLGoiPhiHDResp> BHBL_getGoiPhiHD() throws Exception;

    public List<BHBLHDAndGoiPhiResp> BHBL_getAllHDAndGoiPhi() throws Exception;

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
            String destBank) throws Exception;

    public Object[] DESTROY_QR_PAYMENT(String pTRANSID, String pOrderNo, String pPROVIDERID, String pDATETIME) throws Exception;

    public int UPDATE_DESTROY_QR_PAYMENT(String pRefcore, String pOrderNo, String pPROVIDERID) throws Exception;

    public String[] INSERT_SMS_PARTNER(sms_partner_request request) throws Exception;

    public ArrayList<?> isExistUserMBanking(String userName, String cif, String channel) throws RemoteException;

    public ArrayList<?> getPass(String userName, String cif) throws RemoteException;

    public ArrayList<?> IBT_searchBlackList(String destnumber, String bankcode, int isapprove) throws RemoteException;

    public int IBT_updateBlackList(String id, String userapprove, int isapprove) throws RemoteException;

    public int insertBlackList(String destnumber, String type, String bankcode, String userid, String branchcode, String benename) throws Exception;

    public ArrayList<?> IBT_getInforchBlackList(String id, int isapprove) throws RemoteException;

    public String checkOverKYC(String custAcc, String brnAcc, BigDecimal amount) throws RemoteException;

    public List<CollatedDetail> getListDataCollated(String partnerId, String transDate) throws Exception;

    public Object[] getPdfFileByMonth(String LOC, String Stmth) throws Exception;

    public String checkCifNoCredit(String cif) throws Exception;

    public ArrayList<?> getOnlTranPartnersById(String idPartner) throws RemoteException;

    /*dang test tren 73.20
    * doi tac: WU
     */
    public int insertToSiSenderDetail(SenderInfo senderInfo, Long bankTransId) throws Exception;

    /*dang test tren 73.20
    * doi tac: WU
     */
    public List<TransactionDetailByDate> getListTransactionByDate(String providerId, String partnerAccount, String startDateTime, String endDateTime) throws Exception;

    /*dang test tren 73.20
    * doi tac: WU
     */
    public String[] insertErrorKycSiTransDetail(String idMaster,
            String txnDetailId, String partnerAccount, String customerName, String customerAccount,
            String bankCode, String branchName, BigDecimal amount, String ccy, String description, String status,
            String typeTransfer, String typeCustAccount, BigDecimal rate, BigDecimal amtExchange, String ccyExchange, String personId,
            String firstName, String lastName, String passNo, String birthDate, String address, String nationality, String cusType) throws Exception;

    public ArrayList getUserOdbxDtl(String username) throws RemoteException;

    /*WU - TEST CASE 73.20 test xong xoa*/
    public String[] insertTestCaseToTransDetail(String idMaster, String txnDetailId, String partnerAccount, String customerName,
            String customerAccount, String bankCode, BigDecimal amount, String ccy, String description, String typeTransfer, String status) throws Exception;

    public ArrayList getUserDeviceOdbxInfo(String soCif) throws RemoteException;

    public int updateUserAdditionalOdbx(String userName, String deviceAllowed) throws RemoteException;

    public int insertUserOtherDeviceOdbx(UserOtherDeviceOdbxDto userDeviceOdbx) throws RemoteException;

    public int updateGrantUserOtherDevice(UserOtherDeviceOdbxDto userDeviceOdbx) throws RemoteException;

    public ArrayList getUserOtherDeviceOdbxById(String id) throws RemoteException;

    public ArrayList checkOnOffUserDeviceOdbxApproved(String userName, String soCif, String approved) throws RemoteException;

    public ArrayList GetPaymentCardRegisteredByCif(String soCif) throws RemoteException;

    public int insertPaymentCardTracking(PaymentByCardTracking paymentByCardtracking) throws RemoteException;

    public int updatePaymentCardTracking(String paymentTracking, String userChecker, String approved) throws RemoteException;

    public int updatePaymentByCardRegister(String payment, String status) throws RemoteException;

    public ArrayList checkPaymentCardApproved(String id, String approved) throws RemoteException;

    public ArrayList getPaymentCardCancelById(String id) throws RemoteException;

    /*BAOTBQ - HUY CHUNG TU*/
    public ArrayList NTDT_PAYMENT(String refcore) throws RemoteException;

    public int insertNtdtPaymentExtend(NtdtPaymentExtend ntdtPaymentExtend) throws Exception;

    public int updateNtdtPaymentExtend(String paymentExtend, String userChecker, String approved) throws RemoteException;

    public int checkNtdtPaymentApproved(String id, String approved) throws RemoteException;

    public ArrayList getNtdtPaymentExtendById(String id) throws RemoteException;

    /*BAOTBQ - KIEUHOI - 12/10/2022*/
    public ArrayList getInforRemittanceById(String magd) throws RemoteException;

    public List<TransfersRemittance> getInforRemittanceByDate(String tungay, String denngay) throws RemoteException;

    public int insertTransfersRemittanceExtend(SiTrffromsiExtend siTrffromsiExtend) throws Exception;

    public int updateTransfersRemittanceExtend(String siIdExtend, String userChecker, String approved) throws RemoteException;

    public String GetVCardDetail(String paneCd) throws Exception;

    public int checkTransfersRemittanceApproved(String id, String approved) throws RemoteException;

    public ArrayList getSiTrffromSiById(String id) throws RemoteException;

    public List<TransferMoney247EbankReq> getTransfer247Ebank(String id) throws SQLException, Exception;

    /**
     *
     * @param accountNo
     * @return
     * @throws java.rmi.RemoteException
     */
    public String getCifFromAccountNo(String accountNo) throws RemoteException;

    public ArrayList CollatedKieuHoiByEKYC(String partnerId, String status, String dateCollated) throws RemoteException;

    public ArrayList CollatedKieuHoiByDate(String partnerId, String fromDate, String toDate) throws RemoteException;

    public List<VNPTMoneyCollate> AnswerCollateVNPT(String pPartnerID, String pTransdate) throws Exception;

    public void ReceiveCollateVNPT(VNPTMoneyCollate collate) throws RemoteException;

    //Baotbq 23/12/2022 - Tra cứu HCT
    public ArrayList search_NTDT_PAYMENT_EXTEND(String refcore) throws RemoteException;

    public List<CardInfo> getCardLockedInfo(MoKhoaTheReq req) throws RemoteException;

    public boolean INSERTMOKHOATHE(MoKhoaTheReq req) throws RemoteException;

    public String INSERTMOKHOATHEDETAILS(MoKhoaTheReq req, CardInfo card) throws RemoteException;

    public boolean UPDATEMOKHOATHEDETAILS(String refNo, MoKhoaTheReq req, String respCode, String respDesc) throws RemoteException;

    public PhongToaTKTTRes CC_PhongToaTKTT(PhongToaTKTTReq req) throws RemoteException;

    public GiaiToaTKTTRes CC_GiaiToaTKTT(GiaiToaTKTTReq req) throws RemoteException;

    public String CC_InsertPhongToaGiaiToaTKTT(String partner, String accountOrCif, String phongToaGiaiToa, String userName, String expiryDate, String maDV, String status, String type) throws RemoteException;
    public long CC_UpdatePhongToaGiaiToaTKTT(long id, String status) throws RemoteException;

    public Object[] SI_INSERTTRANSFERFROMSIDETAILKIEUHOI_WU(
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
    ) throws RemoteException;
    
    
    /*Baotbq 9/5/2023*/
    public Object[] SI_UPDATETRANSFERFROMSIDETAILKIEUHOI_EBMR(
            double pID_MASTER,
            String pTXNDETAILID,
            String pPARTNERACCOUNT,            
            String pCUSTUMERACCOUNT,
            String pBANKCODE,
            double pAmount,
            String pTYPETRANSFER,
            String pTYPECUSTACCOUNT,
            BigDecimal amtExchange
    ) throws Exception;

    public Object[] getAccountBalance_wu(String accountNumber) throws RemoteException;
    
    public String checkIdPDF(String panMask, String idPdf) throws RemoteException;
        
}
