/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import scb.com.vn.ultility.Xml;
import scb.com.vn.common.model.exchgbill.RequestForm;
import scb.com.vn.common.model.exchgbill.Response;
import scb.com.vn.common.model.exchgbill.ResponseForm;
import scb.com.vn.common.model.payment.Billing;
import scb.com.vn.common.model.payment.Customer;
import scb.com.vn.common.model.payment.Request;
import scb.com.vn.common.model.payment.RequestPayment;
import scb.com.vn.common.model.payment.ResponsePayment;
import scb.com.vn.common.model.payment.Schedule;
import scb.com.vn.common.model.payment.ThongTinChiTietPhieuThu;
import scb.com.vn.common.model.payment.Vntopup;
import scb.com.vn.dbi.dto.PblBillpaid;
import scb.com.vn.dbi.dto.PblCustTemplate;
import scb.com.vn.dbi.dto.PblEbkProcess;
import scb.com.vn.dbi.dto.PblLog;
import scb.com.vn.dbi.dto.PblPartnerservice;
import scb.com.vn.dbi.dto.PblPartnerserviceId;
import scb.com.vn.dbi.dto.PblProvider;
import scb.com.vn.dbi.dto.PblServicetype;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.dbi.dto.VwCustAccountNew;
import scb.com.vn.dbi.dto.VwMstchanneluser;
import scb.com.vn.message.Message;

import scb.com.vn.model.PblEbkProcessEx;
import scb.com.vn.ultility.Common;
import scb.com.vn.utility.Helper;
import scb.com.vn.payment.model.Processor;
import scb.com.vn.utility.Configuration;

import scb.com.vn.payment.ControllerUtil;

import scb.com.vn.message.Message.PaymentResultEnum;
import scb.com.vn.model.BillDetail;
import scb.com.vn.utility.CommonUtils;
import vn.com.scb.bian.service.IIBBillingPaymentService;
import vn.com.scb.bian.utility.IIBConstants;

/**
 *
 * @author FICOMBANK
 */
public class ExchangePaybill {

    /**
     *
     */
    public String idlanguage = "V";

    /**
     *
     */
    public String strPartner = "";
    String[] tiketstatus = {"Chưa thanh toán", "Đã thanh toán", "Nợ thanh toán"};
    //duytxa edit 30/11/2015 thanh toan hoc phi
    String[] tthpstatus = {"Chưa thanh toán", "Đã thanh toán"};
    //end duytxa edit 30/11/2015 thanh toan hoc phi

    /**
     *
     */
    public static Hashtable<String, String> ht_providers = new Hashtable<String, String>();

    /**
     *
     */
    public static Hashtable<String, String> ht_servicetypes = new Hashtable<String, String>();

    /**
     *
     */
    public static HashMap<String, String> hm_tetefirstnums = new HashMap<String, String>();

    /**
     *
     */
    public static HashMap<String, String> hm_airlines = new HashMap<String, String>();

    /**
     *
     */
    public static HashMap<String, String> hm_cbo_partner = new HashMap<String, String>();

    /**
     *
     */
    public static HashMap<String, String> hm_screens = new HashMap<String, String>(); // Key = idscreen||'_'||idpartner||'_'||idservicetype||'_'||idprovider||'_'||idlang;

    /**
     *
     */
    public static String collect_path;

    //Ham nay kg co

    /**
     *
     */
    public enum PaymentMsgEnum {

        /**
         *
         */
        THANHTOAN_BILL("1"),

        /**
         *
         */
        THANHTOAN_VMB("2"),

        /**
         *
         */
        THANHTOAN_VNTOPUP("3"),

        /**
         *
         */
        HOAN_THANHTOAN_BILL("-1"),

        /**
         *
         */
        HOAN_THANHTOAN_VMB("-2"),

        /**
         *
         */
        HOAN_THANHTOAN_VNTOPUP("-3");
        private String code;

        private PaymentMsgEnum(String c) {
            code = c;
        }

        /**
         *
         * @param value
         * @return
         */
        public static PaymentMsgEnum get(String value) {
            for (PaymentMsgEnum pce : PaymentMsgEnum.values()) {
                if (pce.getValue().equals(value)) {
                    return pce;
                }
            }
            return null;
        }

        /**
         *
         * @return
         */
        public String getValue() {
            return code;
        }
    }

    /**
     *
     */
    public ExchangePaybill() {
        _init();
    }

    /**
     *
     * @param xml
     * @param _hm
     * @return
     * @throws Exception
     */
    public String exchgBill(String xml, HashMap _hm) throws Exception {
        String screen = "0";

        String vmsgchk;
        int sessionvalid = 0;
        RequestForm req = (RequestForm) Xml.unMarshaller(RequestForm.class, xml);

        ResponseForm response = new ResponseForm();
        try {
            PblEbkProcess pep;
            screen = req.getScreen();
            //kiem tra session            
            //sessionvalid = Helper.getDBI().checkusersession_valid(_hm.get("ipaddress").toString(), req.getIdusr(), req.getReq().getIdsession());
            sessionvalid = 0;//Helper.getDBI().checkusersession_valid(_hm.get("ipaddress").toString(), req.getIdusr(), req.getReq().getIdsession());
            if (sessionvalid != 0) {
                pep = new PblEbkProcess();
                if (req.getReq().getTranscode() == null) {
                    pep.setTranscode(0);
                } else {
                    pep.setTranscode(Integer.parseInt(req.getReq().getTranscode()));
                }
                pep.setIdusr(req.getIdusr());
                return Xml.Marshaller(showmessage(pep, PaymentResultEnum.TIMEOUT, req.getScreen(), "Session hết hiệu lực!"));
            }
            idlanguage = Helper.getDBI().getsession_lang(req.getReq().getIdsession());
            if (idlanguage.equalsIgnoreCase("vie")) {
                idlanguage = "V";
            } else {
                idlanguage = "E";
            }

            //kiem tra va khoi tao khi load trang
            if (req.getScreen().equals("0")) {
                if (req.getReq().getType().equals("onload")) {
                    return Xml.Marshaller(initProcess(req));
                } else {
                    return null;
                }
            }

            //Set IDPartner
            if (!req.getReq().getIdservicetype().equalsIgnoreCase("") && !req.getReq().getIdprovider().equalsIgnoreCase("")) {
                if (!req.getReq().getIdservicetype().equalsIgnoreCase("VNTOPUP")) {
                    strPartner = ControllerUtil.getIdPartner(req.getReq().getIdservicetype(), req.getReq().getIdprovider());
                }
            }

            //Support multi partner for topup
            if (req.getReq().getIdservicetype().equalsIgnoreCase("VNTOPUP")) {
                setTOPUPPartner(req);
            }

            //kiem tra co phai su kien change combobox khong
            if (req.getScreen().equals("1") && req.getReq().getType().equals("idservicetype_change")) {
                return Xml.Marshaller(getpartnerservices(req.getReq().getIdservicetype()));
            } else if (req.getScreen().equals("1") && req.getReq().getType().equals("help_click")) {
                response = gethelp(strPartner, req.getReq().getIdservicetype(), req.getReq().getIdprovider(), req.getScreen());
                return Xml.Marshaller(response);
            } else if (req.getScreen().equals("1") && req.getReq().getType().equals("list_templates")) {
                response = getPblCustTemplates(req.getIdusr(), req.getReq().getIdservicetype(), req.getReq().getIdprovider());
                return Xml.Marshaller(response);
            } else if (req.getScreen().equals("1") && req.getReq().getType().equals("load_template")) {
                response = getPblCustTemplate(Integer.valueOf(_hm.get("idtemplate").toString()));
                return Xml.Marshaller(response);
            } else if (req.getScreen().equals("1") && req.getReq().getType().equals("del_template")) {
                boolean check = delPblCustTemplate(Integer.valueOf(_hm.get("idtemplate").toString()), req.getIdusr());
                response = getPblCustTemplates(req.getIdusr(), req.getReq().getIdservicetype(), req.getReq().getIdprovider());
                return Xml.Marshaller(response);
            } else if (req.getScreen().equals("1") && req.getReq().getType().equals("loadKHBinhDien")) {
                return Xml.Marshaller(getThongTinOVua(req.getReq().getCustomercode(), "", ""));
            } else if (req.getScreen().equals("1") && req.getReq().getType().equals("loadKHBinhDien2")) {
                String strCustomercode = req.getReq().getCustomercode();
                return Xml.Marshaller(getThongTinKyThanhToan(strCustomercode.split("#")[0], strCustomercode.split("#")[1]));
            }

            // Truy van thong tin cua giao dich "check"
            pep = Helper.getDBI().getEbkProcessById(Integer.parseInt(req.getReq().getTranscode()));
            //cap nhap thong tin de ghi log
            if (req.getScreen().equals("1")) {
                pep.setIdservicetype(req.getReq().getIdservicetype());
                pep.setIdpartner(strPartner);
                pep.setIdprovider(req.getReq().getIdprovider());
                pep.setIdusr(req.getIdusr());
                pep.setAmount(req.getReq().getAmount());
            }
            ///////////////////////////////// 

            //Kiem tra tinh rang buoc cua Request
            vmsgchk = checkRequest(req, pep);
            if (!vmsgchk.equals("")) {
                return vmsgchk;
            }

            //thuc hien cac buoc theo trinh tu            
            if (req.getScreen().equals("1") && !req.getReq().getType().equals("idservicetype_change")) {
                if (req.getReq().getType().equals("submit")) {
                    response = getBill(req, pep);
                    if (_hm.get("issavetemplate").toString().equalsIgnoreCase("1") && response.getResp().getResult().equalsIgnoreCase("0")) {
                        PblCustTemplate pct = new PblCustTemplate();
                        pct.setIduser(req.getIdusr());
                        pct.setCustomercode(req.getReq().getCustomercode());
                        pct.setCustAcctNo(req.getReq().getCust_acc_no());
                        pct.setTepmName(_hm.get("templatename").toString());
                        pct.setIdprovider(req.getReq().getIdprovider());
                        pct.setProviderName(ht_providers.get(req.getReq().getIdprovider()));
                        pct.setIdservicetype(req.getReq().getIdservicetype());
                        pct.setServicetypeName(ht_servicetypes.get(req.getReq().getIdservicetype()));
                        Helper.getDBI().insPblCustTemplate(pct);
                    }
                    return Xml.Marshaller(response);
                }
            } else if (req.getScreen().equals("2")) {
                if (req.getReq().getType().equals("submit")) {
                    response = checkBill(req, pep);
                    return Xml.Marshaller(response);
                }
            } else if (req.getScreen().equals("3")) {
                if (req.getReq().getType().equals("sendotp")) {
                    response = sendOTP(req, pep);
                    return Xml.Marshaller(response);
                } else if (req.getReq().getType().equals("submit")) {
                    response = paybill(req, pep);
                    return Xml.Marshaller(response);
                }
            } else {
                return "";
            }

            return "";
        } catch (Exception ex) {
            ex.printStackTrace();

            //**error 1**/
            PblEbkProcess pep = new PblEbkProcess();
            if (!req.getReq().getTranscode().equals("")) {
                pep.setTranscode(Integer.parseInt(req.getReq().getTranscode()));
            }
            pep.setIdusr(req.getIdusr());
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION exchgBill): " + ex.getMessage());
            return Xml.Marshaller(showmessage(pep, PaymentResultEnum.SYSTEM_ERROR, "0", "Lỗi hệ thống *error 1* exception=" + ex.getMessage()));

        }
    }

    /**
     *
     * @param req
     */
    public void setTOPUPPartner(RequestForm req) {
        //Lay thong tin nha mang
        String telname = get_telename(req.getReq().getCustomercode());
        if (telname == null || telname.equalsIgnoreCase("")) {
            return;
        }

        if (telname.equalsIgnoreCase("Viettel")) {
            req.getReq().setIdprovider(Configuration.getProperty("ws.ebank.topup.viettel"));
            strPartner = Configuration.getProperty("ws.ebank.topup.viettel");
        } else if (telname.equalsIgnoreCase("Mobifone")) {
            req.getReq().setIdprovider(Configuration.getProperty("ws.ebank.topup.mobifone"));
            strPartner = Configuration.getProperty("ws.ebank.topup.mobifone");
        } else if (telname.equalsIgnoreCase("Vinaphone")) {
            req.getReq().setIdprovider(Configuration.getProperty("ws.ebank.topup.vinaphone"));
            strPartner = Configuration.getProperty("ws.ebank.topup.vinaphone");
        } else if (telname.equalsIgnoreCase("G Mobile")) {
            req.getReq().setIdprovider(Configuration.getProperty("ws.ebank.topup.g_mobile"));
            strPartner = Configuration.getProperty("ws.ebank.topup.g_mobile");
        } else if (telname.equalsIgnoreCase("Vietnammobile")) {
            req.getReq().setIdprovider(Configuration.getProperty("ws.ebank.topup.vietnammobile"));
            strPartner = Configuration.getProperty("ws.ebank.topup.vietnammobile");
        } else if (telname.equalsIgnoreCase("Sfone")) {
            req.getReq().setIdprovider(Configuration.getProperty("ws.ebank.topup.sfone"));
            strPartner = Configuration.getProperty("ws.ebank.topup.sfone");
        } else {
            req.getReq().setIdprovider("");
            strPartner = "";
        }
    }

    //Change histoty: 01/11/2013
    //Date: 01/11/2013
    //Author: HieuDT
    //Contents: Support many parters in bill payment on Ebanking

    /**
     *
     * @param idservicetype
     * @return
     */
    public ResponseForm getpartnerservices(String idservicetype) {
        ResponseForm respFormExchg = new ResponseForm();
        Response respExchg = new Response();
        String sXml = "";

        //Get list of partner from configuration
        String listofPartners = Configuration.getProperty("ws.ebank.billpayment.partners");
        String topupPartner = Configuration.getProperty("ws.ebank.topup.partner");

        if (!idservicetype.equalsIgnoreCase("VNTOPUP")) {
            sXml = "<option value=\"\">----Chọn----</option>";

            for (String s : listofPartners.split("#")) {
                String sKey = s + "_" + idservicetype;
                sXml = sXml + hm_cbo_partner.get(sKey);
            }
        } else {
            sXml = "<option value=\"temp\">--------</option>";
            //Temp: do nothing because we need to check Telco later.
            //String sKey = topupPartner + "_" + idservicetype;
            //sXml = sXml + hm_cbo_partner.get(sKey);
        }

        respExchg.setData(sXml);
        respFormExchg.setScreen("1");
        respExchg.setResult("0");

        respFormExchg.setResp(respExchg);
        return respFormExchg;

    }

    //Lấy thông tin ổ vựa

    /**
     *
     * @param customercode
     * @param idservicetype
     * @param idprovider
     * @return
     */
    public ResponseForm getThongTinOVua(String customercode, String idservicetype, String idprovider) {
        ResponseForm respFormExchg = new ResponseForm();
        Response respExchg = new Response();
        String sXml = "";

        if (customercode.equalsIgnoreCase("BD123456")) {
            sXml = "<option value=\"\">----Chọn----</option>";
            sXml = sXml + "<option value=\"" + "ovua1" + "\">" + "Ổ vựa 1" + "</option>";

        } else if (customercode.equalsIgnoreCase("BD123457")) {
            sXml = "<option value=\"\">----Chọn----</option>";
            sXml = sXml + "<option value=\"" + "ovua2" + "\">" + "Ổ vựa 2" + "</option>";
        }

        respExchg.setData(sXml);
        respFormExchg.setScreen("1");
        respExchg.setResult("0");

        respFormExchg.setResp(respExchg);
        return respFormExchg;

    }

    /**
     *
     * @param customercode
     * @param strMaOVua
     * @return
     */
    public ResponseForm getThongTinKyThanhToan(String customercode, String strMaOVua) {
        ResponseForm respFormExchg = new ResponseForm();
        Response respExchg = new Response();
        String sXml = "";

        if (strMaOVua.endsWith("ovua1")) {
            sXml = "<option value=\"\">----Chọn----</option>";
            sXml = sXml + "<option value=\"" + "ky1" + "\">" + "Kỳ 1" + "</option>";
        } else {
            sXml = "<option value=\"\">----Chọn----</option>";
            sXml = sXml + "<option value=\"" + "ky1" + "\">" + "Kỳ 1" + "</option>";
            sXml = sXml + "<option value=\"" + "ky2" + "\">" + "Kỳ 2" + "</option>";
        }

        respExchg.setData(sXml);
        respFormExchg.setScreen("1");
        respExchg.setResult("0");

        respFormExchg.setResp(respExchg);
        return respFormExchg;
    }

    /**
     *
     * @param iduser
     * @param idservicetype
     * @param idprovider
     * @return
     */
    public ResponseForm getPblCustTemplates(String iduser, String idservicetype, String idprovider) {
        ResponseForm respFormExchg = new ResponseForm();
        Response respExchg = new Response();
        try {
            List<PblCustTemplate> lst;
            String sXml = "<option value=\"\">----Chọn----</option>";

            lst = Helper.getDBI().getPblCustTemplates(iduser, idservicetype, idprovider);
            for (Iterator<PblCustTemplate> it = lst.iterator(); it.hasNext();) {
                PblCustTemplate pblCustTemplate = it.next();

                sXml = sXml + "<option value=\"" + pblCustTemplate.getId() + "\">" + pblCustTemplate.getTepmName() + "</option>";
                // sXml=sXml+"<item value=\""+pblPartnerservice.getIdprovider()+"\">"+pblPartnerservice.getProvidername()+"</item>";
            }

            respExchg.setData(sXml);
            respFormExchg.setScreen("1");
            respExchg.setResult("0");

            respFormExchg.setResp(respExchg);
            return respFormExchg;

        } catch (Exception ex) {
            //Logger.getLogger(ExchangePaybill.class.getName()).log(Level.SEVERE, null, ex);
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION getPblCustTemplates): " + ex.getMessage());
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public ResponseForm getPblCustTemplate(int id) {
        ResponseForm respFormExchg = new ResponseForm();
        Response respExchg = new Response();
        try {
            PblCustTemplate pblCustTemplate;
            String sXml = "";

            pblCustTemplate = Helper.getDBI().getPblCustTemplate(id);
            sXml = Xml.Marshaller(pblCustTemplate);
            respExchg.setData(sXml);
            respFormExchg.setScreen("1");
            respExchg.setResult("0");

            respFormExchg.setResp(respExchg);
            return respFormExchg;

        } catch (Exception ex) {
            //Logger.getLogger(ExchangePaybill.class.getName()).log(Level.SEVERE, null, ex);
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION getPblCustTemplate): " + ex.getMessage());
        }
        return null;
    }

    /**
     *
     * @param id
     * @param iduser
     * @return
     */
    public boolean delPblCustTemplate(int id, String iduser) {

        try {
            PblCustTemplate pblCustTemplate = new PblCustTemplate();
            pblCustTemplate.setId(id);
            pblCustTemplate.setIduser(iduser);
            String sData = "";

            boolean rs = Helper.getDBI().delPblCustTemplate(pblCustTemplate);
            if (rs == true) {
                sData = "Đã xóa thành công mẫu";
            } else {
                sData = "Không xóa mẫu thành công.";
            }

            return rs;
        } catch (Exception ex) {
            //Logger.getLogger(ExchangePaybill.class.getName()).log(Level.SEVERE, null, ex);
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION delPblCustTemplate): " + ex.getMessage());
        }
        return false;
    }

    /**
     *
     * @param idpartner
     * @param idservicetype
     * @param idprovider
     * @param idScreen
     * @return
     */
    public ResponseForm gethelp(String idpartner, String idservicetype, String idprovider, String idScreen) {

        String strKey = "";
        String strdata = "";

        ResponseForm respFormExchg = new ResponseForm();
        Response respExchg = new Response();

//            PblEbkScreenId psId = new PblEbkScreenId();
//            psId.setIdpartner(idpartner);
//            psId.setIdservicetype(idservicetype);
//            psId.setIdprovider(idprovider);
//            psId.setIdlang(idlanguage);
//            psId.setIdscreen(9);
//            PblEbkScreen pes = Helper.getDBI().getEbkScreenById_new(psId);
        strKey = "9" + "_" + idpartner + "_" + idservicetype + "_" + idprovider + "_" + idlanguage;
        strdata = hm_screens.get(strKey);
        if (collect_path == null) {
            strdata = "Duong dan file huong dan khong ton tai";
        } else if (strdata == null || strdata.equalsIgnoreCase("")) {
            strdata = collect_path + "html/tthd/" + "NO_FOUND_PAGE.html";
        } else {
            strdata = collect_path + "html/tthd/" + strdata;
        }

        respExchg.setData(strdata);
        respFormExchg.setScreen(idScreen);
        respExchg.setResult("0");

        respFormExchg.setResp(respExchg);
        return respFormExchg;
    }

    private String checkRequest(RequestForm req, PblEbkProcess pep) throws Exception {
        int vcheck;
        try {

            ResponseForm respFormExchg = new ResponseForm();
            Response respExchg = new Response();
            //kiem tra session cua giao dich

            if (pep == null
                    || !req.getReq().getIdkey().equals(pep.getIdkey())
                    || !req.getIdusr().equals(pep.getIdusr())
                    || (!req.getScreen().equals("1") && !req.getReq().getCust_acc_no().equals(pep.getCustAccNo()))
                    || (req.getReq().getType().equals("submit")
                    && Integer.parseInt(req.getScreen()) - Integer.parseInt(pep.getIdscreen()) > 1)
                    || pep.getIdscreen().equals("4")) {
                return Xml.Marshaller(showmessage(pep, PaymentResultEnum.INVALID_PROCESS, req.getScreen(), "Kiểm tra thông tin giao dịch đang xử lý của KH không hợp lệ."));

            }

            //Kiem tra TK khach hang va UserId co phai cung mot CIF
            vcheck = Helper.getDBI().checkIduserAndCust_ac_no(req.getIdusr(), req.getReq().getCust_acc_no());
            if (vcheck == 0) {
                return Xml.Marshaller(showmessage(pep, PaymentResultEnum.ACCNO_NOT_FOUND, req.getScreen(), "UserId và TK Thanh toán không cùng một CIF."));
            }
        } catch (RemoteException ex) {
            //Logger.getLogger(ExchangePaybill.class.getName()).log(Level.SEVERE, null, ex);
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION checkRequest): " + ex.getMessage());
        }
        return "";
    }

    /*
     * History:
     * - 28/03/2014: Bo sung dieu kien kiem tra thau chi.
     */
    private PaymentResultEnum checkAmountToPaybill(PblEbkProcess pep, VwCustAccountNew custacc) {

        BigDecimal billAmount = new BigDecimal(pep.getAmount());
        // 2.1 Neu khong tra ve thong bao so du TK khong du de thuc hien GD
        if (billAmount.floatValue() <= 0) {
            return PaymentResultEnum.BILL_PAID;
        }

        if (custacc.getAccountClass().substring(0, 3).equals("CAI") || custacc.getAccountClass().substring(0, 3).equals("CAC")) {
            if (custacc.getAcyAvlBal().compareTo(billAmount) == -1) // 0:=,1: 1>2; -1:1<2
            {
                return PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY;
            }
        }

        return PaymentResultEnum.OK;
    }

    private ResponseForm initProcess(RequestForm reqexchg) {
        ResponseForm respFormExchg = new ResponseForm();
        Response respExchg = new Response();
        respFormExchg.setScreen("0");

        try {
            int transcode;

            //sinh key
            PblEbkProcess pep = new PblEbkProcess();

            pep.setIdusr(reqexchg.getIdusr());
            pep.setIdscreen(reqexchg.getScreen());

            transcode = Helper.getDBI().insEbkProcess(pep);
            pep = Helper.getDBI().getEbkProcessById(transcode);
            //khoi tao giao dich

            respExchg.setData(pep.getIdkey());
            respExchg.setResult("0");
            respExchg.setTranscode(String.valueOf(transcode));
            System.out.println("Transcode=" + transcode + " Key=" + pep.getIdkey());
            respFormExchg.setResp(respExchg);
            return respFormExchg;
        } catch (RemoteException ex) {
            //Logger.getLogger(ExchangePaybill.class.getName()).log(Level.SEVERE, null, ex);
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION initProcess): " + ex.getMessage());
        }
        return respFormExchg;
    }

    private ResponseForm getBill(RequestForm reqexchg, PblEbkProcess pep) throws Exception {

        pep.setCustAccNo(reqexchg.getReq().getCust_acc_no());
        pep.setCustomercode(reqexchg.getReq().getCustomercode());
        pep.setIdprovider(reqexchg.getReq().getIdprovider());
        pep.setIdservicetype(reqexchg.getReq().getIdservicetype());

        //kimncm
        boolean isFlagBills = false;
        //kimncm
        //pep.setTranscode(transcode);

        PblEbkProcessEx pepe = new PblEbkProcessEx();
        pepe.setPep(pep);

        //0. get Billing        
        requestPaybillFromPartner(pepe, "QUERY");
        ResponseForm respFormExchg = new ResponseForm();
        Response respExchg = new Response();
        respExchg.setTranscode(reqexchg.getReq().getTranscode());

        //1. Check account
        //TODO:......
        //2. Check bill
        if (!pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())) {

            return showmessage(pep, scb.com.vn.message.Message.PaymentResultEnum.get(pepe.getResult()), "1", "Truy vấn Bill không thành công.");
        } else {

            //3. Thong tin hop le & Hien thi den man hinh 2     
            pepe.getPep().setIdpartner(pepe.getRespay().getResponse().getIdpartner());

            PblPartnerserviceId ppsid = new PblPartnerserviceId();
            ppsid.setIdpartner(pepe.getRespay().getResponse().getIdpartner());
            ppsid.setIdservicetype(pepe.getPep().getIdservicetype());
            ppsid.setIdprovider(pepe.getPep().getIdprovider());
            PblPartnerservice pps = Helper.getDBI().getPartnerServiceById(ppsid);

//            PblEbkScreenId psId = new PblEbkScreenId();
//            psId.setIdpartner(pepe.getRespay().getResponse().getIdpartner());
//            psId.setIdservicetype(pepe.getPep().getIdservicetype());
//            psId.setIdprovider(pepe.getPep().getIdprovider());
//            psId.setIdlang(idlanguage);
//            psId.setIdscreen(2);
//
//            PblEbkScreen pes = Helper.getDBI().getEbkScreenById_new(psId);
            String strKey = "";
            String strScreen = "";

            strKey = "2" + "_" + pepe.getRespay().getResponse().getIdpartner() + "_" + pepe.getPep().getIdservicetype() + "_" + pepe.getPep().getIdprovider() + "_" + idlanguage;
            strScreen = hm_screens.get(strKey);

            VwCustAccount custacc = Helper.getDBI().getCustAccountFcdbByAccountNo(pepe.getPep().getCustAccNo());

            //kiem tra loai tien cua TK TT
            if (!custacc.getCcy().equals("VND")) {
                respExchg.setData(getMsgResult(PaymentResultEnum.NOT_MATCH_CCY_VND.getValue(), idlanguage)); //NOT_ENOUGH_AMT_TO_PAY
                respExchg.setResult(PaymentResultEnum.NOT_MATCH_CCY_VND.getValue());
                respFormExchg.setScreen("1");
                respFormExchg.setResp(respExchg);
                return respFormExchg;
            }

            //Truyen du lieu vao tham so
            int lenght = StringUtils.countMatches(strScreen, "%");
            String[] strPara = new String[30];
            strPara[0] = pps.getPblServicetype().getName();

            strPara[1] = pps.getPblProvider().getProvidername();
            //thong tin tai khoan KH
            strPara[2] = pepe.getPep().getCustAccNo();
            strPara[3] = custacc.getFullName();
            strPara[4] = custacc.getAddress();
            strPara[5] = custacc.getIdcard();
            strPara[6] = custacc.getIdcardLoc();
            strPara[7] = custacc.getIdcardDob();
            //strPara[8]=custacc.getAcyAvlBal().toString(); 
            strPara[8] = Common.getFormatCurrency(Double.valueOf(custacc.getAcyAvlBal().toString()), "###,###,###,###,###.###");
            strPara[9] = pepe.getPep().getCustomercode();
            //Thong tin dich vu khach hang su dung
            if (pep.getIdservicetype().equals(Processor.ServiceTypeEnum.DIEN.toString())) {

                strPara[10] = pepe.getRespay().getResponse().getElectric().getName();
                strPara[11] = pepe.getRespay().getResponse().getElectric().getAddress();
                //strPara[12]=pepe.getRespay().getResponse().getElectric().getAmount().toString();
                strPara[12] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getElectric().getAmount()), "###,###,###,###,###.###");
            } else if (pep.getIdservicetype().equals(Processor.ServiceTypeEnum.LOGISTICS.toString())) {

                strPara[10] = pepe.getRespay().getResponse().getLogistics().getConsignment_code();
                strPara[11] = pepe.getRespay().getResponse().getLogistics().getTax_code();
                strPara[12] = pepe.getRespay().getResponse().getLogistics().getContainer_code();
                strPara[13] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getLogistics().getAmount()), "###,###,###,###,###.###");
            } else if (pep.getIdservicetype().equals(Processor.ServiceTypeEnum.VEMAYBAY.toString())) {
                if (pep.getIdpartner().equals("BANKNET")) {
                    /*
                    strPara[10] = pepe.getRespay().getResponse().getAirline().getMaker();
                    strPara[11] = tiketstatus[0];
                    strPara[12] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getAirline().getAmount()), "###,###,###,###,###.###");
                     */
                    strPara[10] = pepe.getRespay().getResponse().getBilling().getCustomername();
                    strPara[11] = tiketstatus[0];
                    strPara[12] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getBilling().getAmount()), "###,###,###,###,###.###");

                } else if (pep.getIdpartner().equals("PAYOO")) {
                    //strPara[10] = pepe.getRespay().getResponse().getBilling().getCustomercode();
                    strPara[10] = pepe.getRespay().getResponse().getAirline().getMaker();
                    //strPara[12] = pepe.getRespay().getResponse().getAirline().getMaker();
                    strPara[12] = pepe.getRespay().getResponse().getAirline().getAmount();
                } else {
                    strPara[10] = pepe.getRespay().getResponse().getAirline().getBookcode();
                    strPara[11] = tiketstatus[Integer.parseInt(pepe.getRespay().getResponse().getAirline().getStatus())];
                    //strPara[12]=pepe.getRespay().getResponse().getAirline().getListcustomer();
                    List<Customer> lstcust = pepe.getRespay().getResponse().getAirline().getListcustomer();
                    String srtCustHtml = "";

                    for (int i = 0; i < lstcust.size(); i++) {
                        srtCustHtml = srtCustHtml + "<tr>";
                        srtCustHtml = srtCustHtml + "<td class=\"labeltext\"><label>" + lstcust.get(i).getCUSTOMERNAME() + "</label></td>";
                        srtCustHtml = srtCustHtml + "<td class=\"labeltext\"><label>" + "" + "</label></td>";
                        srtCustHtml = srtCustHtml + "</tr>";
                    }
                    srtCustHtml = srtCustHtml + "";
                    strPara[12] = srtCustHtml;
                    //strPara[13]=pepe.getRespay().getResponse().getAirline().getListschedule();
                    List<Schedule> lstshe = pepe.getRespay().getResponse().getAirline().getListschedule();
                    String srtSheHtml = "";
                    for (int i = 0; i < lstshe.size(); i++) {  // i indexes each element successively.
                        String branch = lstshe.get(i).getBrand().trim();
                        srtSheHtml = srtSheHtml + "<tr>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + branch.substring(0, branch.length() - 2) + "</label></td>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + get_airline(branch.substring(branch.length() - 2, branch.length())) + "</label></td>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + lstshe.get(i).getCode() + "</label></td>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + lstshe.get(i).getFlydate() + "</label></td>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + lstshe.get(i).getDeparture() + "</label></td>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + lstshe.get(i).getArrive() + "</label></td>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + lstshe.get(i).getTimedeparture() + "</label></td>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + lstshe.get(i).getTimearrive() + "</label></td>";
                        srtSheHtml = srtSheHtml + "</tr>";
                    }
                    srtSheHtml = srtSheHtml + "";
                    strPara[13] = srtSheHtml;
                    strPara[14] = pepe.getRespay().getResponse().getAirline().getAgent();
                    strPara[15] = pepe.getRespay().getResponse().getAirline().getMaker();
                    strPara[16] = pepe.getRespay().getResponse().getAirline().getMobcust();
                    strPara[17] = pepe.getRespay().getResponse().getAirline().getBooktime();
                    //strPara[18]=pepe.getRespay().getResponse().getAirline().getAmount().toString();
                    strPara[18] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getAirline().getAmount()), "###,###,###,###,###.###");
                    strPara[19] = pepe.getRespay().getResponse().getAirline().getDescription();
                }
            } // duytxa 30.11.2015 thanh toan hoc phi 	
            else if (pep.getIdservicetype().equals(Processor.ServiceTypeEnum.HOCPHI.toString())
                    && pep.getIdprovider().equals(Processor.ProviderEnum.HPBCN.toString())) {//duytxa them hoc phi BCN
                strPara[10] = String.valueOf(pepe.getRespay().getResponse().getTtpt().getMaphieuthu());
                strPara[11] = String.valueOf(pepe.getRespay().getResponse().getTtpt().getThanglapphieu()) + "/" + String.valueOf(pepe.getRespay().getResponse().getTtpt().getNamlapphieu());
                strPara[13] = pepe.getRespay().getResponse().getTtpt().getHotenhocsinh();
                strPara[14] = pepe.getRespay().getResponse().getTtpt().getTenlophoc();
                strPara[15] = pepe.getRespay().getResponse().getTtpt().getTenkhoihoc();
                strPara[16] = pepe.getRespay().getResponse().getTtpt().getTentruong();
                strPara[17] = pepe.getRespay().getResponse().getTtpt().getTenphuong();
                strPara[18] = pepe.getRespay().getResponse().getTtpt().getTenquan();
                strPara[19] = pepe.getRespay().getResponse().getTtpt().getTenthanhpho();

                strPara[20] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getTtpt().getTongthu()), "###,###,###,###,###.###");
                strPara[21] = tthpstatus[pepe.getRespay().getResponse().getTtpt().isTinhtrangthanhtoan() ? 1 : 0];

                String srtchitietHtml = "";
                int sttct = 0;
                for (ThongTinChiTietPhieuThu ctpt : pepe.getRespay().getResponse().getTtpt().getDanhSachChiTietPhieuThu()) {
                    srtchitietHtml = srtchitietHtml + "<tr>";
                    srtchitietHtml = srtchitietHtml + "<td class=\"labeltext\"><label>" + sttct++ + "</label></td>";
                    srtchitietHtml = srtchitietHtml + "<td class=\"labeltext\"><label>" + ctpt.getMachitietphieuthu() + "</label></td>";
                    srtchitietHtml = srtchitietHtml + "<td class=\"labeltext\"><label>" + ctpt.getTenchiphi() + "</label></td>";
                    srtchitietHtml = srtchitietHtml + "<td class=\"labeltext\"><label>" + ctpt.getGiatien() + "</label></td>";
                    srtchitietHtml = srtchitietHtml + "<td class=\"labeltext\"><label>" + ctpt.getSoluong() + "</label></td>";
                    srtchitietHtml = srtchitietHtml + "<td class=\"labeltext\"><label>" + ctpt.getThanhtien() + "</label></td>";
                    srtchitietHtml = srtchitietHtml + "</tr>";
                }
                srtchitietHtml = srtchitietHtml + "";
                strPara[22] = srtchitietHtml;

            } //end duytxa 30.11.2015 thanh toan hoc phi
            else if (pep.getIdservicetype().equals("VNTOPUP")) {
                strPara[1] = get_telename(pepe.getPep().getCustomercode());
                if (pepe.getRespay().getResponse().getVntopup() != null) {
                    ///strPara[10]=pepe.getRespay().getResponse().getVntopup().getAmount();
                    strPara[10] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getVntopup().getAmount()), "###,###,###,###,###.###");
                } else {
                    strPara[10] = "";
                }
            } else {
                strPara[12] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getBilling().getAmount()), "###,###,###,###,###.###");
                if (!pepe.getRespay().getResponse().getBilling().getCustomername().isEmpty()) {
                    strPara[10] = pepe.getRespay().getResponse().getBilling().getCustomername();
                    //kimncm
                    strPara[13] = "";
                    //strPara[12] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getBilling().getAmount()), "###,###,###,###,###.###");
                    String customername = pepe.getRespay().getResponse().getBilling().getCustomername();
                    if (isFlgPrepaid(customername, pepe.getPep().getIdprovider())) {
                        isFlagBills = true;
                        String[] strTempArr = pepe.getRespay().getResponse().getBilling().getCustomername().split("@");
                        strPara[10] = strTempArr[0];
                        strPara[14] = customername;
                        ArrayList<BillDetail> billList = convertCustomerNameToBill(customername);
                        if (pepe.getPep().getIdprovider().equals(Configuration.getProperty("provider.ACS")) || pepe.getPep().getIdprovider().equals(Configuration.getProperty("provider.SCTV6"))) {
                            String sXml = "";
                            for (BillDetail b : billList) {
                                sXml = sXml + "<tr>";
                                sXml = sXml + "<td><input name=\"listidbill\" type =\"checkbox\" value =\"" + b.getBillId() + "\"  onclick='UpdateTotal();'/></td>";
                                sXml = sXml + "<td><label id=\"idbill\">" + b.getBillId() + "</label></td>";
                                sXml = sXml + "<td>" + b.getMonth() + "</td>";
                                sXml = sXml + "<td><label id=\"amount\">" + Common.getFormatCurrency(Double.valueOf(b.getMoneyAmount().toString()), "###,###,###,###,###.###") + "</label></td>";
                                sXml = sXml + "</tr>";
                            }
                            strPara[13] = sXml;
                        } else if (pepe.getPep().getIdprovider().equals(Configuration.getProperty("provider.PF"))) {
                            String[] arrPaymentRange = getPaymentRange(customername).split("-");
                            strPara[15] = Common.getFormatCurrency(Double.valueOf(arrPaymentRange[0]), "###,###,###,###,###.###");;
                            strPara[16] = Common.getFormatCurrency(Double.valueOf(arrPaymentRange[1]), "###,###,###,###,###.###");;
                        } else {
                            //if (pepe.getPep().getIdprovider().equals(Configuration.getProperty("provider.HCATV"))) {
                            String sXml = "";
                            sXml = sXml + "<tr>";
                            sXml = sXml + "<td class=\"labeltext\"><label>Ngày hết hạn:</label></td>";
                            sXml = sXml + "<td>" + getExpiredDate(customername) + "</td>";
                            sXml = sXml + "</tr>";
                            sXml = sXml + "<tr>";
                            sXml = sXml + "<td class=\"labeltext\"><label>Kỳ han:</label></td>";
                            sXml = sXml + "<td>";
                            sXml = sXml + " <select name=\"listidbill\"  id=\"listidbill\" class=\"objselect\" maxlength=\"35\" style=\"width:240px \" onchange =\"loadTotal(this)\">";
                            sXml = sXml + "<option value=\"\">----Chọn----</option>";
                            for (BillDetail b : billList) {
                                sXml = sXml + "<option value=\"" + b.getMonth().toString() + "\">" + b.getMonth().toString() + "_"
                                        + Common.getFormatCurrency(Double.valueOf(b.getMoneyAmount().toString()), "###,###,###,###,###.###")
                                        + "</option>";
                            }
                            sXml = sXml + "</select>";
                            sXml = sXml + "</td>";
                            sXml = sXml + "</tr>";

                            sXml = sXml + "<input type =\"hidden\" id =\"hidcustname2\" value =\"" + customername + "\" name =\"hidcustname2\" />";
                            strPara[13] = sXml;
                            strPara[15] = "style=\"display:none\"";
                            strPara[12] = "0";
                        }
                    } else if (pepe.getPep().getIdprovider().equals("CHOBINHDIEN")) {
                        String[] strTempArr = pepe.getRespay().getResponse().getBilling().getCustomername().split("@");

                        //Gan ten cho KH
                        strPara[9] = pepe.getPep().getCustomercode().split("#")[0];
                        strPara[10] = strTempArr[1];
                        strPara[14] = customername;
                        strPara[15] = strTempArr[2];
                        strPara[16] = strTempArr[3];

                        String sXml = "";
                        if (strTempArr.length == 5) {
                            sXml = convertBillChoBinhDien(strTempArr[4]);
                        } else {
                            sXml = convertBillChoBinhDien(strTempArr[4] + "@" + strTempArr[5]);
                        }

                        strPara[13] = sXml;
                    }
                    //kimncm                    
                } else {
                    strPara[10] = "";
                }
                if (!pepe.getRespay().getResponse().getBilling().getAddress().isEmpty()) {
                    strPara[11] = pepe.getRespay().getResponse().getBilling().getAddress();
                } else {
                    strPara[11] = "";
                }
                //kimncm

                //if (isFlagBills &&) {
                //    strPara[12] = "0";
                //    strPara[17] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getBilling().getAmount()), "###,###,###,###,###.###");
                //} else {
                //kimncm
                /*
                if (isFlagBills && pepe.getPep().getIdprovider().equals(Configuration.getProperty("provider.HCATV"))) {
                    strPara[12] = "0";
                } else {
                    strPara[12] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getBilling().getAmount()), "###,###,###,###,###.###");
                }
                * */
            }
            //
            String strShow = formatString(strScreen, strPara);
            respExchg.setData(strShow);
            respExchg.setResult("0");
            respFormExchg.setScreen("2");
        }

        respFormExchg.setResp(respExchg);
        //cap nhap buoc 1
        Date d = new Date();
        pep.setTxnDate(d);
        pep.setIdscreen("1");
        Helper.getDBI().updEbkProcess(pep);
        return respFormExchg;
    }

    private String convertBillChoBinhDien(String input) throws Exception {
        //1000000%30112016#500000@2000000%30112016#800000
        //Build ngan han
        //"BIN001%1000000%30112016#BID002%500000"
        //                                      + "@BID001%2000000%30112016#BID001%800000";        

        String sXml = "";

        if (!input.contains("@")) {
            //Build ngan han            
            sXml = sXml + "<tr>";
            sXml = sXml + "<th colspan=\"4\">Phí ngắn hạn</th></td>";
            sXml = sXml + "</tr>";

            String[] arrTemp = input.split("#");
            if (arrTemp.length > 1) {
                //Load phi quan ly
                sXml = sXml + convertBillChoBinhDienNganHan(arrTemp[0], "phimatbang");
                //Load phi dien                
                sXml = sXml + convertBillChoBinhDienNganHan(arrTemp[1], "phidiennuoc");
            } else {
                if (arrTemp[0].split("%").length == 3) {
                    sXml = sXml + convertBillChoBinhDienNganHan(arrTemp[0], "phimatbang");
                } else {
                    sXml = sXml + convertBillChoBinhDienNganHan(arrTemp[0], "phidiennuoc");
                }
            }
        } else {
            //Build ngan han & dai han
            String[] bills = input.split("@");

            //Ngan han
            sXml = sXml + "<tr>";
            sXml = sXml + "<th colspan=\"4\">Phí ngắn hạn</th></td>";
            sXml = sXml + "</tr>";

            String[] arrTemp = bills[0].split("#");
            if (arrTemp.length > 1) {
                //Load phi quan ly
                sXml = sXml + convertBillChoBinhDienNganHan(arrTemp[0], "phimatbang");
                //Load phi dien                
                sXml = sXml + convertBillChoBinhDienNganHan(arrTemp[1], "phidiennuoc");
            } else {
                if (arrTemp[0].split("%").length == 3) {
                    sXml = sXml + convertBillChoBinhDienNganHan(arrTemp[0], "phimatbang");
                } else {
                    sXml = sXml + convertBillChoBinhDienNganHan(arrTemp[0], "phidiennuoc");
                }
            }

            //Dai han
            sXml = sXml + "<tr>";
            sXml = sXml + "<th colspan=\"4\">Phí dài hạn</th></td>";
            sXml = sXml + "</tr>";

            arrTemp = bills[1].split("#");
            if (arrTemp.length > 1) {
                //Load phi quan ly
                sXml = sXml + convertBillChoBinhDienDaiHan(arrTemp[0], "phimatbang");
                //Load phi dien                
                sXml = sXml + convertBillChoBinhDienDaiHan(arrTemp[1], "phidiennuoc");
            } else {
                if (arrTemp[0].split("%").length == 3) {
                    sXml = sXml + convertBillChoBinhDienDaiHan(arrTemp[0], "phimatbang");
                } else {
                    sXml = sXml + convertBillChoBinhDienDaiHan(arrTemp[0], "phidiennuoc");
                }
            }
        }

        return sXml;
    }

    private String convertBillChoBinhDienNganHan(String input, String type) throws Exception {
        String sXml = "";

        if (type.equals("phimatbang")) {

            String[] arrTemp = input.split("%");

            sXml = sXml + "<tr>";
            sXml = sXml + "<td><input name=\"listidbill\" type =\"checkbox\" value =\"" + arrTemp[0] + "\"  onclick='UpdateTotal();'/></td>";
            sXml = sXml + "<td><label id=\"idbill\">" + "Phí thuê mặt bằng - Phí Quản lý" + "<br/>" + "Phí phạt chậm nộp (nếu có)" + "</label></td>";
            sXml = sXml + "<td>" + arrTemp[2] + "</td>";
            sXml = sXml + "<td><label id=\"amount\">" + Common.getFormatCurrency(Double.valueOf(arrTemp[1]), "###,###,###,###,###.###") + "<br/>" + "<a href=\"#\">Chi tiết</a>" + "</label></td>";
            sXml = sXml + "</tr>";
        } else if (type.equals("phidiennuoc")) {
            String[] arrTemp = input.split("%");

            sXml = sXml + "<tr>";
            sXml = sXml + "<td><input name=\"listidbill\" type =\"checkbox\" value =\"" + arrTemp[0] + "\"  onclick='UpdateTotal();'/></td>";
            sXml = sXml + "<td><label id=\"idbill\">" + "Phí điện nước" + "</label></td>";
            sXml = sXml + "<td>" + "" + "</td>";
            sXml = sXml + "<td><label id=\"amount\">" + Common.getFormatCurrency(Double.valueOf(arrTemp[1]), "###,###,###,###,###.###") + "</label></td>";
            sXml = sXml + "</tr>";
        }

        return sXml;
    }

    private String convertBillChoBinhDienDaiHan(String input, String type) throws Exception {
        String sXml = "";

        if (type.equals("phimatbang")) {

            String[] arrTemp = input.split("%");

            sXml = sXml + "<tr>";
            sXml = sXml + "<td><input name=\"listidbill\" type =\"checkbox\" value =\"" + arrTemp[0] + "\"  onclick='UpdateTotal();'/></td>";
            sXml = sXml + "<td><label id=\"idbill\">" + "Tiền gốc, lãi trả góp - Phí Quản lý" + "<br/>" + "Phí phạt chậm nộp (nếu có)" + "</label></td>";
            sXml = sXml + "<td>" + arrTemp[2] + "</td>";
            sXml = sXml + "<td><label id=\"amount\">" + Common.getFormatCurrency(Double.valueOf(arrTemp[1]), "###,###,###,###,###.###") + "<br/>" + "<a href=\"#\">Chi tiết</a>" + "</label></td>";
            sXml = sXml + "</tr>";
        } else if (type.equals("phidiennuoc")) {
            String[] arrTemp = input.split("%");

            sXml = sXml + "<tr>";
            sXml = sXml + "<td><input name=\"listidbill\" type =\"checkbox\" value =\"" + arrTemp[0] + "\"  onclick='UpdateTotal();'/></td>";
            sXml = sXml + "<td><label id=\"idbill\">" + "Phí điện nước" + "</label></td>";
            sXml = sXml + "<td>" + "" + "</td>";
            sXml = sXml + "<td><label id=\"amount\">" + Common.getFormatCurrency(Double.valueOf(arrTemp[1]), "###,###,###,###,###.###") + "</label></td>";
            sXml = sXml + "</tr>";
        }

        return sXml;
    }

    private ResponseForm checkBill(RequestForm reqexchg, PblEbkProcess pep) throws RemoteException {

        //Gan so tien giao dich thuc te d/v giao dich Prepaid, ACS, SCTV6
        if (isFlgPrepaid(reqexchg.getReq().getCustomercode(), reqexchg.getReq().getIdprovider())) {
            pep.setAmount(reqexchg.getReq().getAmount());
        }

        if (pep.getIdprovider().equalsIgnoreCase("CHOBINHDIEN")) {
            pep.setAmount(reqexchg.getReq().getAmount());
        }

        // 0: Lay thong tin giao dich
        //PblEbkProcess pep = Helper.getDBI().getEbkProcessById(Integer.parseInt(reqexchg.getReq().getTranscode()));
        ResponseForm respFormExchg = new ResponseForm();

        Response respExchg = new Response();
        respExchg.setTranscode(reqexchg.getReq().getTranscode());
        VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(reqexchg.getReq().getCust_acc_no());
        if (!custacc.getCcy().equals("VND")) {
            respExchg.setData(getMsgResult(PaymentResultEnum.NOT_MATCH_CCY_VND.getValue(), idlanguage)); //NOT_ENOUGH_AMT_TO_PAY
            respExchg.setResult(PaymentResultEnum.NOT_MATCH_CCY_VND.getValue());
            respFormExchg.setScreen("2");
            respFormExchg.setResp(respExchg);
            return respFormExchg;
        }
        // kiem tra so du tai khoan thanh toan co so du du de thanh toan bill

        PaymentResultEnum checkbill = checkAmountToPaybill(pep, custacc);
        if (!checkbill.equals(PaymentResultEnum.OK)) {
            respExchg.setData(getMsgResult(checkbill.getValue(), idlanguage)); //NOT_ENOUGH_AMT_TO_PAY
            respExchg.setResult(checkbill.getValue());
            respFormExchg.setScreen("2");
            respFormExchg.setResp(respExchg);
            return respFormExchg;
        }

        // 2.2 Neu hop le thi tra ve mang hinh 3        
        String strTypeConfirm = Helper.getDBI().GetTypeConfirm_User(reqexchg.getIdusr());
        respExchg.setResult("0");
        respExchg.setData(strTypeConfirm);
        respFormExchg.setScreen("3");
        respFormExchg.setResp(respExchg);

        // Cap nhap lai buoc thu 2
        pep.setIdscreen("2");
        Helper.getDBI().updEbkProcess(pep);

        return respFormExchg;

    }

    private ResponseForm sendOTP(RequestForm reqexchg, PblEbkProcess pep) throws Exception {
        //0. Lay Phonenumber cua nguoi dung de gui         
        //String phonenum = "0932559650";
        String phonenum = Helper.getDBI().getuserphonenumber(pep.getIdusr());

        //1. Tao so random OTP cho SMS
        String rsu = RandomStringUtils.randomAlphabetic(6);
        rsu = rsu.toUpperCase();

        //2. Luu vao table
        //PblEbkProcess pep = Helper.getDBI().getEbkProcessById(Integer.parseInt(reqexchg.getReq().getTranscode()));
        pep.setOtptype("sms");
        pep.setOtp(rsu);
        Helper.getDBI().updEbkProcess(pep);

        //3. Gui SMS
        ControllerImpl ci = new ControllerImpl("EBK");
        ci.sendsms(phonenum, rsu);

        //4. Tra ket qua
        Response respexchg = new Response();
        respexchg.setTranscode(reqexchg.getReq().getTranscode());
        respexchg.setResult("0");
        //respexchg.setData(rsu);

        ResponseForm respFormExchg = new ResponseForm();
        respFormExchg.setScreen("3");
        respFormExchg.setResp(respexchg);
        // Cap nhap lai buoc thu 3
        pep.setIdscreen("3");
        Helper.getDBI().updEbkProcess(pep);

        return respFormExchg;
    }

    private ResponseForm paybill(RequestForm reqexchg, PblEbkProcess pep) throws Exception {
        //0. Lay so thong tin tu transcode.
        //PblEbkProcess pep = Helper.getDBI().getEbkProcessById(Integer.parseInt(reqexchg.getReq().getTranscode()));

        try {
            PblEbkProcessEx pepe = new PblEbkProcessEx();
            pepe.setPep(pep);

            ResponseForm respFormExchg = new ResponseForm();
            Response respExchg = new Response();
            respExchg.setTranscode(reqexchg.getReq().getTranscode());

            //Kiem tra han muc trong ngay
            //Lay loai giao dich
            String v_txn_id = "IBP";
            if (reqexchg.getReq().getIdservicetype().equalsIgnoreCase("VNTOPUP")) {
                v_txn_id = "IBV";
            }
            boolean check_limit = Helper.getDBI().check_trans_limit("B001", "01", v_txn_id, pep.getIdusr(), "EN1", new Date(), pep.getAmount(), "VND", 1);
            if (check_limit == false) {
                return showmessage(pepe.getPep(), PaymentResultEnum.NO_ENOUGH_TRANS_LIMIT, "3", "Hết hạn mức giao dịch trong ngày.");
            }
            // end han muc

            //0. CHUNG THUC GIAO DICH
            //1. Check OTP
            if (reqexchg.getReq().getOtp().getTxnpin() == null || reqexchg.getReq().getOtp().getTxnpin().equalsIgnoreCase("")) {
                return showmessage(pepe.getPep(), PaymentResultEnum.INVALID_CONFIRMCODE, "3", "Chứng thực sms không hợp lệ.");
            } else if (reqexchg.getReq().getOtp().getType().equalsIgnoreCase("sms")) {
                if (!pepe.getPep().getOtp().equals(reqexchg.getReq().getOtp().getTxnpin())) {

                    return showmessage(pepe.getPep(), PaymentResultEnum.INVALID_CONFIRMCODE, "3", "Chứng thực sms không hợp lệ.");
                }
            } //2.  Check Token ?
            else if (reqexchg.getReq().getOtp().getType().equalsIgnoreCase("token")) {
                if (checkAuthenTokenkey(String.valueOf(pep.getIdusr()), String.valueOf(reqexchg.getReq().getOtp().getTxnpin())) != 0) {

                    return showmessage(pepe.getPep(), PaymentResultEnum.INVALID_CONFIRMCODE, "3", "Chứng thực token không hợp lệ.");
                }

            } //Chua chon loai xac thuc sms hoac token
            else {

                return showmessage(pepe.getPep(), PaymentResultEnum.INVALID_CONFIRMCODE, "3", "Chưa chọn loại xác thực sms hoặc token.");
            }

            //2. Xu ly thanh toan BILL & Chuyen tien.           
            VwCustAccountNew custaccNew = Helper.getDBI().getCustAccountByAccountNoNew(reqexchg.getReq().getCust_acc_no());
            //kiem tra loai tien cua TK TT
            if (!custaccNew.getCcy().equals("VND")) {
                respExchg.setData(getMsgResult(PaymentResultEnum.NOT_MATCH_CCY_VND.getValue(), idlanguage)); //NOT_ENOUGH_AMT_TO_PAY
                respExchg.setResult(PaymentResultEnum.NOT_MATCH_CCY_VND.getValue());
                respFormExchg.setScreen("4");
                respFormExchg.setResp(respExchg);
                return respFormExchg;
            }

            //2.4 Thuc hien cap nhap process            
            pepe.getPep().setOtptype(reqexchg.getReq().getOtp().getType());
            pepe.getPep().setOtp(reqexchg.getReq().getOtp().getTxnpin());

            //2.3 Thuc hien chuyen tien
            PblBillpaid billpaid = new PblBillpaid();
            String refCub = "";
            //IIB Service
            //IIB Payment   
            Request r = new Request();
            if (pepe.getPep().getIdservicetype().equals("VNTOPUP")) {
                Vntopup vntopup = new Vntopup();
                vntopup.setMobnoreq(pepe.getPep().getCustomercode());
                vntopup.setMobnoload(pepe.getPep().getCustomercode());
                vntopup.setAmount(pepe.getPep().getAmount());
                r.setVntopup(vntopup);

            } else {
                Billing b = new Billing();
                b.setCustomercode(pepe.getPep().getCustomercode());
                b.setAmount(pepe.getPep().getAmount());
                r.setBilling(b);
            }
            RequestPayment reqpay = new RequestPayment();
            reqpay.setTranscode(String.valueOf(pepe.getPep().getTranscode()));
            reqpay.setChannel("INTERNET");
            reqpay.setServicecode(pepe.getPep().getIdservicetype());
            reqpay.setProvidercode(pepe.getPep().getIdprovider());
            reqpay.setProcessingcode("QUERY");
            reqpay.setPaymentmethod("ACCOUNT");
            reqpay.setAccountno(pepe.getPep().getCustAccNo());
            reqpay.setDatetime(Common.getDate("ddMMyyyyHHmmss"));
            reqpay.setRequest(r);
            //IIB Payment                                                 
            IIBBillingPaymentService iibBillingService = new IIBBillingPaymentService();
            ResponsePayment responsepay = iibBillingService.callPartnerGateway(Configuration.getIIBContext(), reqpay);

            /*
            if (!responsepay.getResult().equals( Message.PaymentResultEnum.OK.getValue()))
            {      
                Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION callRequestPayment - iibBillingService - callPartnerGateway): " + responsepay.getResult()+ responsepay.getResultMessage());                
                return showmessage(pepe.getPep(), PaymentResultEnum.SYSTEM_ERROR, "4", "Lỗi hệ thống không lấy được nhà cung cấp");
            } */
            if (responsepay.getResult().equals(Message.PaymentResultEnum.OK.getValue()) && pepe.getPep().getIdpartner().equals(IIBConstants.PARTNER_BANKNET)) {
                ResponseForm iibResponse = executePayBillIIB(reqpay, pepe.getPep(), pepe, custaccNew, billpaid);
                if (!iibResponse.getResp().getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())) {
                    return iibResponse;
                }
            } else {
                //1. Kiem tra Bill & tai khoan phai hop le
                //@TODO:
                //2.1 Lay lai thong tin BIll               
                if (!pep.getIdservicetype().equals("VNTOPUP")) {

                    if (isFlgPrepaid(reqexchg.getReq().getCustomercode(), reqexchg.getReq().getIdprovider()) == false
                            && !reqexchg.getReq().getIdprovider().equalsIgnoreCase("CHOBINHDIEN")) {

                        requestPaybillFromPartner(pepe, "QUERY");

                        //2.2 KIem tra tai khoan du thanh toan
                        if (!pepe.getResult().equals("0")) {
                            return showmessage(pepe.getPep(), PaymentResultEnum.get(pepe.getResult()), "3", "Truy vấn bill để thực hiện trong quá trình thanh toán không thành công " + getMsgResult(pepe.getResult(), idlanguage));
                        }
                    }
                }

                PaymentResultEnum checkbill = checkAmountToPaybill(pepe.getPep(), custaccNew);

                if (!checkbill.equals(PaymentResultEnum.OK)) {
                    return showmessage(pepe.getPep(), PaymentResultEnum.get(checkbill.getValue()), "3", "Số tiền không đủ để thanh toán bill");
                }

                //2.3 Thuc hien chuyen tien
                try {
                    refCub = transferPaybill(reqexchg, pepe.getPep(), pepe, custaccNew, billpaid, "transfer");

                } catch (Exception e) {
                    //**error 2**/
                    Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION paybill): " + e.getMessage());
                    return showmessage(pepe.getPep(), PaymentResultEnum.SYSTEM_ERROR, "3", "Trích tiền từ tài khoản thanh toán xảy ra ngoại lệ error 2 exception=" + e.getMessage().substring(0, 500));
                }
                if (refCub == null || refCub.equalsIgnoreCase("")) {
                    return showmessage(pepe.getPep(), PaymentResultEnum.CANNOT_TRANSFERFCUBS, "3", "Không thực hiện được trích tiền từ tài khoản thanh toán khách hàng.");
                }
                //billpaid.setIdbillpaid(idBillPaid);

                //2.5 Thuc hien thanh toan Bill
                pepe.setResult("-99");

                //Gan customer code cho customer name
                if (isFlgPrepaid(reqexchg.getReq().getCustomercode(), reqexchg.getReq().getIdprovider())) {
                    pepe.getPep().setCustomercode(reqexchg.getReq().getCustomercode());
                }

                if (reqexchg.getReq().getIdprovider().equalsIgnoreCase("CHOBINHDIEN")) {
                    pepe.getPep().setCustomercode(reqexchg.getReq().getCustomercode());
                }

                requestPaybillFromPartner(pepe, "PAY");

                //2.6 Cap nhap thong tin giao dich neu giao dich khong thanh cong thi tra lai hoa don cho KH
                //cap nhap lai thong tin billpaid
                //billpaid = Helper.getDBI().getPaybillBillPaidById(idBillPaid);
                Date d = new Date();
                billpaid.setPaydate(d);
                billpaid.setDataxml(Xml.Marshaller(pepe.getRespay()));
                billpaid.setRefPartner(pepe.getRespay().getTranscode());

                if (!pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())) {
                    //hoach toan tra lai tai khoan cho khach hang khi thanh toan hoa don khong thanh cong
                    //String idreturnrefcub = "";

                    //Update sua timeout by Hieu, 31/12/2013
                    //if (!pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.TIMEOUT.getValue())) {
                    //    idreturnrefcub = transferPaybill(reqexchg, pepe.getPep(), custacc, billpaid, "recieve");
                    //}                                
                    /*
                //Cap nhat code hoan tien moi.
                if (!pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.TIMEOUT.getValue())
                        && !pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.SYSTEM_ERROR.getValue())
                        && !pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.ERROR_PARTNER.getValue())) {
                    idreturnrefcub = transferPaybill(reqexchg, pepe.getPep(), custacc, billpaid, "recieve");
                }

                if (idreturnrefcub == null || idreturnrefcub.equalsIgnoreCase("")) {
                    billpaid.setStatus(Character.valueOf('H'));
                    Helper.getDBI().updatePaybillBillPaid(billpaid);
                    return showmessage(pep, PaymentResultEnum.get(scb.com.vn.message.Message.PaymentResultEnum.TIMEOUT.getValue()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
                } else {
                    billpaid.setStatus(Character.valueOf('F'));
                    Helper.getDBI().updatePaybillBillPaid(billpaid);
                    return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Số giao dịch tham chiếu fcus trả lại tiền cho KH :" + idreturnrefcub);
                }
                     */
                    //Update code hoan tien moi
                    return updateNoneSucessBillStatus(pepe, pep, billpaid);
                }
                //cap nhap thoi gian cho giao dich voi VNPAY           
                billpaid.setStatus(Character.valueOf('D'));
                Helper.getDBI().updatePaybillBillPaid(billpaid);
            }
//            int idBillPaid = pe.getPbp().getIdbillpaid();
//            String partnerDetailId = pe.getRespay().getResponse().getTtpt().getMatruong();
//            String partnerId = pe.getRespay().getResponse().getIdpartner();
//            String accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
//            Helper.getDBI().insertBillPaidDetail(idBillPaid, partnerDetailId, partnerId, accountTo);

            if (pep.getIdservicetype().equals(Processor.ServiceTypeEnum.VEMAYBAY.toString())) {
                if (pep.getIdpartner().equalsIgnoreCase("VNPAY")) {
                    requestPaybillFromPartner(pepe, "QUERY");
                }
            } else if (pep.getIdservicetype().equals(Processor.ServiceTypeEnum.HOCPHI.toString())
                    && pep.getIdprovider().equals(Processor.ProviderEnum.HPBCN.toString())) {
                requestPaybillFromPartner(pepe, "QUERY");
            }
            // Cap nhap giao dich thanh cong BILLPAID

            //3. Nap form 4        
            respExchg.setResult("0");
            PblPartnerserviceId ppsid = new PblPartnerserviceId();
            ppsid.setIdpartner(pepe.getPep().getIdpartner());
            ppsid.setIdservicetype(pepe.getPep().getIdservicetype());
            ppsid.setIdprovider(pepe.getPep().getIdprovider());
            PblPartnerservice pps = Helper.getDBI().getPartnerServiceById(ppsid);

//            PblEbkScreenId psId = new PblEbkScreenId();
//            psId.setIdpartner(pepe.getRespay().getResponse().getIdpartner());
//            psId.setIdservicetype(pepe.getPep().getIdservicetype());
//            psId.setIdprovider(pepe.getPep().getIdprovider());
//            psId.setIdlang(idlanguage);
//            psId.setIdscreen(4);
//
//            PblEbkScreen pes = Helper.getDBI().getEbkScreenById_new(psId);
            String strKey = "";
            String strScreen = "";
            strKey = "4" + "_" + pepe.getRespay().getResponse().getIdpartner() + "_" + pepe.getPep().getIdservicetype() + "_" + pepe.getPep().getIdprovider() + "_" + idlanguage;
            strScreen = hm_screens.get(strKey);
            VwCustAccount custacc = Helper.getDBI().getCustAccountFcdbByAccountNo(pepe.getPep().getCustAccNo());

            //Cugn cap gia tri hien thi
            //Truyen du lieu vao tham so
            //String [] strPara= new String[StringUtils.countMatches(pes.getData(),"%")];
            String[] strPara = new String[30];

            strPara[0] = pps.getPblServicetype().getName();
            strPara[1] = pps.getPblProvider().getProvidername();
            //thong tin tai khoan KH
            strPara[2] = pepe.getPep().getCustAccNo();
            strPara[3] = custacc.getFullName();
            strPara[4] = custacc.getAddress();
            strPara[5] = custacc.getIdcard();
            strPara[6] = custacc.getIdcardLoc();
            strPara[7] = custacc.getIdcardDob();
            //strPara[8]=custacc.getAcyAvlBal().toString(); 
            strPara[8] = Common.getFormatCurrency(Double.valueOf(custacc.getAcyAvlBal().toString()), "###,###,###,###,###.###");
            //Thong tin dich vu khach hang su dung
            strPara[9] = pepe.getPep().getCustomercode();

            if (pep.getIdservicetype().equals(Processor.ServiceTypeEnum.DIEN.toString())) {

                //strPara[10]=pepe.getRespay().getResponse().getElectric().getAmount().toString();
                strPara[10] = pepe.getRespay().getResponse().getElectric().getName();
                strPara[11] = pepe.getRespay().getResponse().getElectric().getAddress();
                //strPara[12]=pepe.getRespay().getResponse().getElectric().getAmount().toString();
                strPara[12] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getElectric().getAmount()), "###,###,###,###,###.###");
            } else if (pep.getIdservicetype().equals(Processor.ServiceTypeEnum.LOGISTICS.toString())) {
                //strPara[10] = pepe.getRespay().getResponse().getLogistics().getRef_no();
                strPara[10] = refCub;
                strPara[11] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getLogistics().getAmount()), "###,###,###,###,###.###");
                strPara[12] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getLogistics().getAmount()), "###,###,###,###,###.###");
            } else if (pep.getIdservicetype().equals(Processor.ServiceTypeEnum.VEMAYBAY.toString())) {
                if (pep.getIdpartner().equals("BANKNET")) {
                    /*
                    strPara[10] = pepe.getRespay().getResponse().getAirline().getMaker();
                    strPara[11] = tiketstatus[1];
                    strPara[12] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getAirline().getAmount()), "###,###,###,###,###.###");
                     */
                    strPara[10] = pepe.getRespay().getResponse().getBilling().getCustomername();
                    strPara[11] = tiketstatus[1];
                    strPara[12] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getBilling().getAmount()), "###,###,###,###,###.###");

                } else if (pep.getIdpartner().equals("PAYOO")) {
                    //strPara[10] = pepe.getRespay().getResponse().getBilling().getCustomercode();
                    strPara[10] = pepe.getRespay().getResponse().getAirline().getMaker();
                    //strPara[12] = pepe.getRespay().getResponse().getAirline().getMaker();
                    strPara[12] = pepe.getRespay().getResponse().getAirline().getAmount();
                } else {
                    strPara[10] = pepe.getRespay().getResponse().getAirline().getBookcode();
                    strPara[11] = tiketstatus[Integer.parseInt(pepe.getRespay().getResponse().getAirline().getStatus())];
                    //strPara[12]=pepe.getRespay().getResponse().getAirline().getListcustomer();
                    List<Customer> lstcust = pepe.getRespay().getResponse().getAirline().getListcustomer();
                    String srtCustHtml = "";

                    for (int i = 0; i < lstcust.size(); i++) {
                        srtCustHtml = srtCustHtml + "<tr>";
                        srtCustHtml = srtCustHtml + "<td class=\"labeltext\"><label>" + lstcust.get(i).getCUSTOMERNAME() + "</label></td>";
                        srtCustHtml = srtCustHtml + "<td class=\"labeltext\"><label>" + "" + "</label></td>";
                        srtCustHtml = srtCustHtml + "</tr>";
                    }
                    srtCustHtml = srtCustHtml + "";
                    strPara[12] = srtCustHtml;
                    //strPara[13]=pepe.getRespay().getResponse().getAirline().getListschedule();
                    List<Schedule> lstshe = pepe.getRespay().getResponse().getAirline().getListschedule();
                    String srtSheHtml = "";
                    for (int i = 0; i < lstshe.size(); i++) {  // i indexes each element successively.
                        String branch = lstshe.get(i).getBrand().trim();
                        srtSheHtml = srtSheHtml + "<tr>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + branch.substring(0, branch.length() - 2) + "</label></td>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + get_airline(branch.substring(branch.length() - 2, branch.length())) + "</label></td>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + lstshe.get(i).getCode() + "</label></td>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + lstshe.get(i).getFlydate() + "</label></td>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + lstshe.get(i).getDeparture() + "</label></td>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + lstshe.get(i).getArrive() + "</label></td>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + lstshe.get(i).getTimedeparture() + "</label></td>";
                        srtSheHtml = srtSheHtml + "<td class=\"labeltext\"><label>" + lstshe.get(i).getTimearrive() + "</label></td>";
                        srtSheHtml = srtSheHtml + "</tr>";
                    }
                    srtSheHtml = srtSheHtml + "";
                    strPara[13] = srtSheHtml;
                    strPara[14] = pepe.getRespay().getResponse().getAirline().getAgent();
                    strPara[15] = pepe.getRespay().getResponse().getAirline().getMaker();
                    strPara[16] = pepe.getRespay().getResponse().getAirline().getMobcust();
                    strPara[17] = pepe.getRespay().getResponse().getAirline().getBooktime();
                    //strPara[18]=pepe.getRespay().getResponse().getAirline().getAmount().toString();
                    strPara[18] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getAirline().getAmount()), "###,###,###,###,###.###");
                    strPara[19] = pepe.getRespay().getResponse().getAirline().getDescription();
                }
            } // duytxa 30.11.2015 thanh toan hoc phi 	
            else if (pep.getIdservicetype().equals(Processor.ServiceTypeEnum.HOCPHI.toString())
                    && pep.getIdprovider().equals(Processor.ProviderEnum.HPBCN.toString())) {//duytxa them hoc phi BCN
                strPara[10] = String.valueOf(pepe.getRespay().getResponse().getTtpt().getMaphieuthu());
                strPara[11] = String.valueOf(pepe.getRespay().getResponse().getTtpt().getThanglapphieu()) + "/" + String.valueOf(pepe.getRespay().getResponse().getTtpt().getNamlapphieu());
                //strPara[12] = String.valueOf(pepe.getRespay().getResponse().getTtpt().getNamlapphieu());
                strPara[13] = pepe.getRespay().getResponse().getTtpt().getHotenhocsinh();
                strPara[14] = pepe.getRespay().getResponse().getTtpt().getTenlophoc();
                strPara[15] = pepe.getRespay().getResponse().getTtpt().getTenkhoihoc();
                strPara[16] = pepe.getRespay().getResponse().getTtpt().getTentruong();
                strPara[17] = pepe.getRespay().getResponse().getTtpt().getTenphuong();
                strPara[18] = pepe.getRespay().getResponse().getTtpt().getTenquan();
                strPara[19] = pepe.getRespay().getResponse().getTtpt().getTenthanhpho();

                strPara[20] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getTtpt().getTongthu()), "###,###,###,###,###.###");
                strPara[21] = tthpstatus[pepe.getRespay().getResponse().getTtpt().isTinhtrangthanhtoan() ? 1 : 0];

                String srtchitietHtml = "";
                int sttct = 0;
                for (ThongTinChiTietPhieuThu ctpt : pepe.getRespay().getResponse().getTtpt().getDanhSachChiTietPhieuThu()) {
                    srtchitietHtml = srtchitietHtml + "<tr>";
                    srtchitietHtml = srtchitietHtml + "<td class=\"labeltext\"><label>" + sttct++ + "</label></td>";
                    srtchitietHtml = srtchitietHtml + "<td class=\"labeltext\"><label>" + ctpt.getMachitietphieuthu() + "</label></td>";
                    srtchitietHtml = srtchitietHtml + "<td class=\"labeltext\"><label>" + ctpt.getTenchiphi() + "</label></td>";
                    srtchitietHtml = srtchitietHtml + "<td class=\"labeltext\"><label>" + ctpt.getGiatien() + "</label></td>";
                    srtchitietHtml = srtchitietHtml + "<td class=\"labeltext\"><label>" + ctpt.getSoluong() + "</label></td>";
                    srtchitietHtml = srtchitietHtml + "<td class=\"labeltext\"><label>" + ctpt.getThanhtien() + "</label></td>";
                    srtchitietHtml = srtchitietHtml + "</tr>";
                }
                srtchitietHtml = srtchitietHtml + "";
                strPara[22] = srtchitietHtml;

            } //end duytxa 30.11.2015 thanh toan hoc phi
            else if (pep.getIdservicetype().equals("VNTOPUP")) {
                strPara[1] = get_telename(pepe.getPep().getCustomercode());
                if (pepe.getRespay().getResponse().getVntopup() != null) {
                    //strPara[10]=pepe.getRespay().getResponse().getVntopup().getAmount();
                    strPara[10] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getVntopup().getAmount()), "###,###,###,###,###.###");
                } else {
                    strPara[10] = "";
                }
            } else {
                //strPara[10]=pepe.getRespay().getResponse().getBilling().getAmount().toString();

                if (!pepe.getRespay().getResponse().getBilling().getCustomername().isEmpty()) {
                    strPara[10] = pepe.getRespay().getResponse().getBilling().getCustomername();
                } else {
                    strPara[10] = "";
                }
                if (!pepe.getRespay().getResponse().getBilling().getAddress().isEmpty()) {
                    strPara[11] = pepe.getRespay().getResponse().getBilling().getAddress();
                } else {
                    strPara[11] = "";
                }

                strPara[12] = Common.getFormatCurrency(Double.valueOf(pepe.getRespay().getResponse().getBilling().getAmount()), "###,###,###,###,###.###");
            }

            String strShow = formatString(strScreen, strPara);

            respExchg.setData(strShow);
            respFormExchg.setScreen("4");
            respFormExchg.setResp(respExchg);

            return respFormExchg;
        } catch (Exception ex) {
            //**error 3**/
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION paybill 2): " + ex.getMessage());
            return showmessage(pep, PaymentResultEnum.SYSTEM_ERROR, "4", "Giao dịch thanh toán paybill của khách hàng đã xãy ra ngoại lệ error 3 exception=" + ex.getMessage().substring(0, 500));
        }
    }

    /**
     *
     * @param pepe
     * @param pep
     * @param billpaid
     * @return
     * @throws Exception
     */
    public ResponseForm updateNoneSucessBillStatus(PblEbkProcessEx pepe, PblEbkProcess pep, PblBillpaid billpaid) throws Exception {
        /**
         * DIEU CHINH HOAN TIEN THEO QUY DINH DICH VU HOAN TIEN -> TRANG THAI: F
         * TREO -> TRANG HAI: H
         *
         */
        //pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())
        if (pepe.getResult().equals(PaymentResultEnum.ERROR_PARTNER_REFUND.getValue())) {
            //Giao dich that bai, thuc hien hoan tien cho KH
            String a = "PAYBILL IS NOT SUCCESS. TTHD doi tac khong thanh cong & hoan tien bill. [idbillpaid-%1$s]-[Trancode-%2$s]";
            Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, String.format(a, billpaid.getIdbillpaid(), billpaid.getRefPartner()));

            String _msgouttransfer = "TRANSFER UBS. Thuc hien HOAN TIEN HOAN TIEN %1$s. [So but toan-%2$s]";
            String fccRef = billpaid.getRefFcubs();
            String result = revertTransferFCUBS(fccRef, 60);

            if (result == null) {
                //Revert khong thanh cong, tra soat hoan tien cho KH
                Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                updateBillStatusToDB(billpaid, 'H');
                return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
            } else if (result.equalsIgnoreCase("0")) {
                Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "THANH CONG", fccRef));
                updateBillStatusToDB(billpaid, 'F');
                return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Số giao dịch tham chiếu fcus trả lại tiền cho KH :" + fccRef);
            } else {
                //Revert khong thanh cong, tra soat hoan tien cho KH
                Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                updateBillStatusToDB(billpaid, 'H');
                return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
            }
        } else if (pepe.getResult().equals(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue())) {
            if (pepe.getPep().getIdservicetype().equals("VNTOPUP")) {
                String a = "PAYBILL IS NOT SUCCESS. TTHD doi tac khong thanh cong & hoan tien bill. [idbillpaid-%1$s]-[Trancode-%2$s]";
                Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, String.format(a, billpaid.getIdbillpaid(), billpaid.getRefPartner()));

                String _msgouttransfer = "TRANSFER UBS. Thuc hien HOAN TIEN HOAN TIEN %1$s. [So but toan-%2$s]";
                String fccRef = billpaid.getRefFcubs();
                String result = revertTransferFCUBS(fccRef, 60);

                if (result == null) {
                    //Revert khong thanh cong, tra soat hoan tien cho KH
                    Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                    updateBillStatusToDB(billpaid, 'H');
                    return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
                } else if (result.equalsIgnoreCase("0")) {
                    Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "THANH CONG", fccRef));
                    updateBillStatusToDB(billpaid, 'F');
                    return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Số giao dịch tham chiếu fcus trả lại tiền cho KH :" + fccRef);
                } else {
                    //Revert khong thanh cong, tra soat hoan tien cho KH
                    Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                    updateBillStatusToDB(billpaid, 'H');
                    return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
                }
            } else {
                //Khong hoan tien, yeu cau doi tac gach bu giao dich
                String a = "PAYBILL IS NOT SUCCESS. Hold tien KH, yeu cau doi tac gach bu tay. [idbillpaid-%1$s]";

                Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, String.format(a, billpaid.getIdbillpaid()));
                updateBillStatusToDB(billpaid, 'H');
                return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
            }
        } else {
            //Giao dich nghi van, thuc hien doi soat
            //Hold toan bo giao dich
            String a = "PAYBILL IS NOT SUCCESS. TTHD doi tac khong thanh cong, thuc hien doi soat. [idbillpaid-%1$s]";
            Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, String.format(a, billpaid.getIdbillpaid()));
            updateBillStatusToDB(billpaid, 'H');
            return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
        }
    }

    private void updateBillStatusToDB(PblBillpaid billpaid, Character status) throws Exception {
        billpaid.setStatus(status);
        Helper.getDBI().updatePaybillBillPaid(billpaid);
    }

    private String revertTransferFCUBS(String fccRef, int timeout) {
        //Kiem tra so ref hop le
        if (fccRef == null) {
            return null;
        }

        Fcubs fc = new Fcubs();
        return fc.revertTransferFCUBS(fccRef, timeout);
    }

    //Kiem tra xac thuc token
    private int checkAuthenTokenkey(String userId, String sOTP) {
        Tokenkey tk = new Tokenkey();
        String serialno;
        try {
            serialno = Helper.getDBI().getUdfValue_User(userId, "fldtokenmap");
            return tk.checkAuthenTokenkey(serialno, sOTP);
        } catch (RemoteException ex) {
            //Logger.getLogger(ExchangePaybill.class.getName()).log(Level.SEVERE, null, ex);
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION checkAuthenTokenkey): " + ex.getMessage());
        }
        return -1;
    }

    //Thuc hien hoac toan chuyen tien thanh toan hoa don
    private String transferPaybill(RequestForm reqexchg, PblEbkProcess pep, PblEbkProcessEx pepe, VwCustAccountNew from_custacc, PblBillpaid billpaid, String transfer_type) {
        try {
            String msgpay = "";
            String msgback = "";
            if (pep.getIdservicetype().equalsIgnoreCase("VEMAYBAY")) {
                msgpay = String.format(getPaymentMsgEnum(PaymentMsgEnum.THANHTOAN_VMB), pep.getCustomercode().toUpperCase());
                msgback = getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_VMB);//String.format(getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_VMB), pep.getCustomercode());
            } else if (pep.getIdservicetype().equalsIgnoreCase("VNTOPUP")) {
                msgpay = String.format(getPaymentMsgEnum(PaymentMsgEnum.THANHTOAN_VNTOPUP), pep.getCustomercode().toUpperCase());
                msgback = getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_VNTOPUP);//String.format(getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_VNTOPUP), pep.getCustomercode());
            } else {
                msgpay = String.format(getPaymentMsgEnum(PaymentMsgEnum.THANHTOAN_BILL), pep.getCustomercode().toUpperCase());
                msgback = getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_BILL);//String.format(getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_BILL), pep.getCustomercode());
            }

            //1.1 Lay tai treo theo doi doi voi nha cung cap dich vu
            PblPartnerserviceId psid = new PblPartnerserviceId();
            psid.setIdpartner(pep.getIdpartner());
            psid.setIdprovider(reqexchg.getReq().getIdprovider());
            psid.setIdservicetype(reqexchg.getReq().getIdservicetype());
            PblPartnerservice ps = Helper.getDBI().getPartnerServiceById(psid);

            //1.2 Thuc hien chuyen khoan
            Fcubs fc = new Fcubs();
            BigDecimal billAmount = new BigDecimal(pep.getAmount());

            String refCub = "";
            int idbillPaid = -1;

            if (transfer_type.equalsIgnoreCase("transfer")) {

                //1.3 Insert bang BillPaid
                //PblBillpaid billpaid =new PblBillpaid();
                VwMstchanneluser mstchanneluser = Helper.getDBI().getVwMstchanneluser(pep.getIdusr());
                billpaid.setIdpartner(pep.getIdpartner());

                PblServicetype pst = new PblServicetype();
                pst.setIdservicetype(pep.getIdservicetype());
                billpaid.setPblServicetype(pst);

                PblProvider pdr = new PblProvider();
                pdr.setIdprovider(pep.getIdprovider());
                billpaid.setPblProvider(pdr);

                billpaid.setCustomercode(pep.getCustomercode());
                billpaid.setDataxml("");
                billpaid.setIduserMaker(pep.getIdusr());
                billpaid.setIdchanneluserMaker(mstchanneluser.getIdchanneluser());
                billpaid.setIduserMaker(pep.getIdusr());
                billpaid.setIduserChecker(pep.getIdusr());
                billpaid.setIdchanneluserChecker(mstchanneluser.getIdchanneluser());
                billpaid.setIduserChecker(pep.getIdusr());
                billpaid.setIdchannel("01");
                billpaid.setUsertype("1");
                billpaid.setRefPartner("");
                billpaid.setRefFcubs("");
                billpaid.setTotalamount(Long.valueOf(pep.getAmount()));
                billpaid.setPaymentmethod(2);
                billpaid.setAccCust(from_custacc.getCustNo());
                billpaid.setAccIdaccount(from_custacc.getCustAcNo());
                billpaid.setAccHoldername(from_custacc.getFullName());
                billpaid.setAccAddress(from_custacc.getAddress());
                Date d = new Date();
                billpaid.setInsdate(d);
                billpaid.setInsdateMaker(d);
                billpaid.setInsdateChecker(d);
                billpaid.setTransdate(d);
                billpaid.setPaydate(null);
                billpaid.setPaydateFcubs(d);
                billpaid.setIsapproved(Character.valueOf('A'));
                billpaid.setStatus(Character.valueOf('W'));
                //billpaid.setBranchcode(billpaid.getAccIdaccount().substring(0, 3));
                billpaid.setBranchcode(CommonUtils.getBranchAccount(billpaid.getAccIdaccount()));
                idbillPaid = Helper.getDBI().insertPaybillBillPaid(billpaid);

                //Long.Le
                if (pep.getIdpartner().equals("BCN")) {
                    String partnerDetailId = pepe.getRespay().getResponse().getTtpt().getMatruong();
                    String partnerId = pep.getIdpartner();
                    String accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
                    Helper.getDBI().insertBillPaidDetail(idbillPaid, partnerDetailId, partnerId, accountTo);
                } else if (pep.getIdpartner().equals("EVNHN")) {
                    String partnerDetailId = pepe.getPep().getCustomercode().substring(0, 4);
                    String partnerId = pep.getIdpartner();
                    String accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
                    Helper.getDBI().insertBillPaidDetail(idbillPaid, partnerDetailId, partnerId, accountTo);
                } else if (pep.getIdpartner().equals("EVNSPC")) {
                    String partnerDetailId = pepe.getPep().getCustomercode().substring(0, 6);
                    String partnerId = pep.getIdpartner();
                    String accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
                    Helper.getDBI().insertBillPaidDetail(idbillPaid, partnerDetailId, partnerId, accountTo);
                } else if (pep.getIdpartner().equals("EVNCPC")) {
                    String partnerDetailId = pepe.getPep().getCustomercode().substring(0, 6);
                    String partnerId = pep.getIdpartner();
                    String accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
                    Helper.getDBI().insertBillPaidDetail(idbillPaid, partnerDetailId, partnerId, accountTo);
                }

                //Long.Le
                if (idbillPaid <= 0) {
                    return "";
                } else {
                    //cap nhap process thanh cong
                    pep.setIdscreen("4");
                    pep.setStatus("A");
                    pep.setRefBillpaid(String.valueOf(idbillPaid));
                    Helper.getDBI().updEbkProcess(pep);
                }
                //Cat tien thanh toan hoa don cua KH

                //LONG.LE edit (S)
                if (pepe.getPep().getIdpartner().equals("BCN")) {
                    String idschool = pepe.getRespay().getResponse().getTtpt().getMatruong();
                    String accountTo = Helper.getDBI().getAccountProvider(idschool);
                    refCub = fc.transferFCUBS(pep.getCustAccNo(), accountTo, billAmount, msgpay);
                } else if (pepe.getPep().getIdpartner().equals("EVNHN")) {
                    String branchcode = pepe.getPep().getCustomercode().substring(0, 4);
                    String accountTo = Helper.getDBI().getAccountProvider(branchcode);
                    if (pepe.getRespay().getResponse().getElectric() != null && pepe.getRespay().getResponse().getElectric().getMaHD() != null) {
                        msgpay = msgpay.concat("_HD:").concat(pepe.getRespay().getResponse().getElectric().getMaHD());
                    }
                    refCub = fc.transferFCUBS(pep.getCustAccNo(), accountTo, billAmount, msgpay);
                } else if (pepe.getPep().getIdpartner().equals("EVNSPC")) {
                    String branchcode = pepe.getPep().getCustomercode().substring(0, 6);
                    String accountTo = Helper.getDBI().getAccountProvider(branchcode);
                    refCub = fc.transferFCUBS(pep.getCustAccNo(), accountTo, billAmount, msgpay);
                } else if (pepe.getPep().getIdpartner().equals("EVNCPC")) {
                    String branchcode = pepe.getPep().getCustomercode().substring(0, 6);
                    String accountTo = Helper.getDBI().getAccountProvider(branchcode);
                    refCub = fc.transferFCUBS(pep.getCustAccNo(), accountTo, billAmount, msgpay);
                } else if (pepe.getPep().getIdpartner().equals("BANKNET")) {
                    String accountTo = ps.getAccnofcubs();
                    //  refCub = fc.transferFCUBSWithBranch(pep.getCustAccNo().substring(0, 3), pep.getCustAccNo(), 
                    //          "000", accountTo,billAmount, msgpay);
                    refCub = fc.transferFCUBSWithBranch(CommonUtils.getBranchAccount(pep.getCustAccNo()), pep.getCustAccNo(),
                            "000", accountTo, billAmount, msgpay);
                } else {
                    String accountTo = ps.getAccnofcubs();
                    refCub = fc.transferFCUBS(pep.getCustAccNo(), accountTo, billAmount, msgpay);
                }
                //LONG.LE edit (S)
                Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, "***- LOG THANH TOAN IB (FUNCTION transferPaybill): Ma Khach Hang: " + billpaid.getCustomercode() + " -- So tham chieu trich tien REFCUBS= " + refCub + " -- From Account :" + from_custacc.getCustAcNo());
                if (refCub == null || refCub.equalsIgnoreCase("")) {
                    return "";
                } else {
                    billpaid.setIdbillpaid(idbillPaid);
                    billpaid.setRefFcubs(refCub);
                    Helper.getDBI().updatePaybillBillPaid(billpaid);
                    return refCub;
                }

            } else if (transfer_type.equalsIgnoreCase("recieve")) {
//                //LONG.LE edit (S)
//                PblEbkProcessEx pepe = new PblEbkProcessEx();
//                requestPaybillFromPartner(pepe, "QUERY");
                if (pepe.getPep().getIdpartner().equals("BCN")) {
                    String idschool = pepe.getRespay().getResponse().getTtpt().getMatruong();
                    String accountTo = Helper.getDBI().getAccountProvider(idschool);
                    refCub = fc.transferFCUBS(ps.getAccnofcubs(), accountTo, billAmount, msgback);
                } else {
                    String accountTo = pep.getCustAccNo();
                    refCub = fc.transferFCUBS(ps.getAccnofcubs(), accountTo, billAmount, msgback);
                }
                //LONG.LE edit (S)
                Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, "***- LOG HOAN THANH TOAN IB (FUNCTION transferPaybill): Ma Khach hang: " + billpaid.getCustomercode() + " --  So tham chieu hoan tien  REFCUBS= " + refCub + " --  To Account :" + from_custacc.getCustAcNo());
                return refCub;
            }
            return refCub;
        } catch (RemoteException ex) {
            //Logger.getLogger(ExchangePaybill.class.getName()).log(Level.SEVERE, null, ex);
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION transferPaybill): " + ex.getMessage());
            return "";
        }
    }

    private void requestPaybillFromPartner(PblEbkProcessEx pepe, String processingcode) {
        try {
            pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue());
            String xml = callRequestPayment(pepe, processingcode);

            // Da thanh toan xong, REPONSE khong tra ket qua gi het.
            if (processingcode.equals("QUERY")) {
                setBillInfo(xml, pepe);
                if (!pepe.getResult().equals("0")) {
                    return;
                }
                //Check amount de xac nhan bill thanh toan hay chua? Va gan gia tri ket qua vao pepe
                checkAmount(pepe);
                return;

            } else if (processingcode.equals("PAY")) {
                checkBillPaidFromPartner(pepe);
                return;
            }

        } catch (Exception ex) {
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION requestPaybillFromPartner): " + ex.getMessage());
            pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
        }
    }

    private String callRequestPayment(PblEbkProcessEx pepe, String processingcode) {
        try {
            Request r = new Request();
            /*
            if (pepe.getPep().getIdservicetype().equals("VNTOPUP") && pepe.getPep().getIdprovider().equals("VNPAY")) {
                Vntopup vntopup = new Vntopup();
                vntopup.setMobnoreq(pepe.getPep().getCustomercode());
                vntopup.setMobnoload(pepe.getPep().getCustomercode());
                vntopup.setAmount(pepe.getPep().getAmount());
                r.setVntopup(vntopup);

            } else if (pepe.getPep().getIdservicetype().equals("VNTOPUP") && pepe.getPep().getIdprovider().equals("PAYOO")) {
                Vntopup vntopup = new Vntopup();
                vntopup.setMobnoreq(pepe.getPep().getCustomercode());
                vntopup.setMobnoload(pepe.getPep().getCustomercode());
                vntopup.setAmount(pepe.getPep().getAmount());
                r.setVntopup(vntopup);
            } else if (pepe.getPep().getIdservicetype().equals("VNTOPUP") && pepe.getPep().getIdpartner().equals("BANKNET")) {
                Vntopup vntopup = new Vntopup();
                vntopup.setMobnoreq(pepe.getPep().getCustomercode());
                vntopup.setMobnoload(pepe.getPep().getCustomercode());
                vntopup.setAmount(pepe.getPep().getAmount());
                r.setVntopup(vntopup);
            }*/
            if (pepe.getPep().getIdservicetype().equals("VNTOPUP")) {
                Vntopup vntopup = new Vntopup();
                vntopup.setMobnoreq(pepe.getPep().getCustomercode());
                vntopup.setMobnoload(pepe.getPep().getCustomercode());
                vntopup.setAmount(pepe.getPep().getAmount());
                r.setVntopup(vntopup);
            } /*else if (pepe.getPep().getIdservicetype().equals("BAOHIEM") && pepe.getPep().getIdpartner().equals("BANKNET")) {
                Billing b = new Billing();
                b.setCustomercode(pepe.getPep().getCustomercode());
                b.setAmount(pepe.getPep().getAmount());
                r.setBilling(b);
            } */ else if (pepe.getPep().getIdservicetype().equals("GAMETOPUP") && pepe.getPep().getIdpartner().equals("BANKNET")) {
                Billing b = new Billing();
                b.setCustomercode(pepe.getPep().getCustomercode());
                b.setAmount(pepe.getPep().getAmount());
                r.setBilling(b);
            } else {
                Billing b = new Billing();

                b.setCustomercode(pepe.getPep().getCustomercode());

                //b.setCustomername("A"); b.setCurrentdebit("2");
                if (processingcode.equals("PAY")) {
                    b.setAmount(pepe.getPep().getAmount());

                    //Reset customer code for prepaid case, SCTV6, ACS
                    if (isFlgPrepaid(pepe.getPep().getCustomercode(), pepe.getPep().getIdprovider())) {
                        b.setCustomername(pepe.getPep().getCustomercode());
                        b.setCustomercode(pepe.getPep().getCustomercode().split("@")[0]);

                    }

                    if (pepe.getPep().getIdprovider().equalsIgnoreCase("CHOBINHDIEN")) {
                        b.setCustomername(pepe.getPep().getCustomercode());
                        b.setCustomercode(pepe.getPep().getCustomercode().split("@")[0]);
                    }
                }
                r.setBilling(b);
            }

            RequestPayment reqpay = new RequestPayment();
            reqpay.setTranscode(String.valueOf(pepe.getPep().getTranscode()));
            reqpay.setChannel("INTERNET");
            reqpay.setServicecode(pepe.getPep().getIdservicetype());
            reqpay.setProvidercode(pepe.getPep().getIdprovider());
            reqpay.setProcessingcode(processingcode);
            reqpay.setPaymentmethod("ACCOUNT");
            reqpay.setAccountno(pepe.getPep().getCustAccNo());
            reqpay.setDatetime(Common.getDate("ddMMyyyyHHmmss"));
            reqpay.setRequest(r);
            String rsxml = null;

            ResponsePayment responsepay = null;
            if (processingcode.equals("QUERY")) {
                //IIB Payment                                                            
                IIBBillingPaymentService iibBillingService = new IIBBillingPaymentService();
                responsepay = iibBillingService.callPartnerGateway(Configuration.getIIBContext(), reqpay);
                if (responsepay.getResult().equals(Message.PaymentResultEnum.OK.getValue())) {
                    if (responsepay.getPartnercode().equals(IIBConstants.PARTNER_BANKNET)) {
                        responsepay = iibBillingService.queryPartner(Configuration.getIIBContext(), responsepay);
                        if (reqpay.getServicecode().equals("VNTOPUP")) {
                            pepe.getPep().setIdprovider(responsepay.getPartnercode());
                        }
                        rsxml = Xml.Marshaller(responsepay);
                    } else {
                        // da cau hinh trong partner code
                        String xml = Xml.Marshaller(reqpay);
                        ControllerImpl ci = new ControllerImpl();
                        rsxml = ci.requestPayment(xml);
                        //thiet lap gia tri tra ve
                        responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, rsxml);
                    }
                } // chưa cấu hình partner trong partner_code
                else if (responsepay.getResult().equals(Message.PaymentResultEnum.PARTNER_INVALID.getValue())) {
                    String xml = Xml.Marshaller(reqpay);
                    ControllerImpl ci = new ControllerImpl();
                    rsxml = ci.requestPayment(xml);
                    //thiet lap gia tri tra ve
                    responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, rsxml);

                } else {
                    Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION callRequestPayment - iibBillingService - callPartnerGateway): " + responsepay.getResult() + responsepay.getResultMessage());
                }
            } else if (processingcode.equals("PAY")) {
                if (pepe.getPep().getIdpartner().endsWith("EVNHN")) {
                    reqpay.setTranscode(pepe.getRespay().getTranscode());
                }
                String xml = Xml.Marshaller(reqpay);
                ControllerImpl ci = new ControllerImpl();
                rsxml = ci.requestPayment(xml);
                //thiet lap gia tri tra ve
                responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, rsxml);
            }
            if (reqpay.getServicecode().equals("VNTOPUP")) {
                pepe.setRespay(responsepay);
            }

            if (isFlgPrepaid(pepe.getPep().getCustomercode(), pepe.getPep().getIdprovider())) {
                pepe.getPep().setCustomercode(pepe.getPep().getCustomercode().split("@")[0]);
                responsepay.getResponse().getBilling().setAmount(pepe.getPep().getAmount());
                pepe.setRespay(responsepay);
            }

            if (pepe.getPep().getIdprovider().equalsIgnoreCase("CHOBINHDIEN")) {
                pepe.getPep().setCustomercode(pepe.getPep().getCustomercode().split("@")[0]);
                responsepay.getResponse().getBilling().setAmount(pepe.getPep().getAmount());
                pepe.setRespay(responsepay);
            }

            //Update for transaction code by Hieu
            if (pepe.getRespay() != null) {
                pepe.getRespay().setTranscode(responsepay.getTranscode());
            }

            pepe.setResult(responsepay.getResult());
            return rsxml;

        } catch (Exception ex) {
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION callRequestPayment): " + ex.getMessage());
            pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
            return null;
        }

    }

    /**
     *
     * @param xml
     * @param pepe
     * @throws Exception
     */
    public void setBillInfo(String xml, PblEbkProcessEx pepe) throws Exception {
        ResponsePayment respay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, xml);
        pepe.setRespay(respay);
        pepe.setResult(respay.getResult());
    }

    /**
     * Check amount de xac nhan bill thanh toan hay chua? Va gan gia tri ket qua
     * vao pepe
     */
    private void checkAmount(PblEbkProcessEx pepe) {
        //        paybill.setResult(Message.PaymentResultEnum.OK.getValue());
//        paybill.getPbp().setIdpartner(respay.getResponse().getIdpartner());
//        paybill.getPbp().setDataxml(xml);

        if (pepe.getPep().getIdservicetype().equals("DIEN")) {
            if (pepe.getRespay().getResponse().getElectric() == null) {
                pepe.setResult(pepe.getRespay().getResult());//chuong
            }// else if (pepe.getRespay().getResponse().getElectric().getAmount().equals("0")) {
            else if (Integer.parseInt(pepe.getRespay().getResponse().getElectric().getAmount()) <= 0) {
                pepe.getPep().setAmount("0");
                pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.BILL_PAID.getValue());
            } else {
                pepe.getPep().setAmount(pepe.getRespay().getResponse().getElectric().getAmount());
            }
        } else if (pepe.getPep().getIdservicetype().equals("VEMAYBAY") && !pepe.getPep().getIdpartner().equals(IIBConstants.PARTNER_BANKNET)) {
            if (pepe.getRespay().getResponse().getAirline() == null) {
                pepe.setResult(pepe.getRespay().getResult());//chuong  
            } else if (Integer.parseInt(pepe.getRespay().getResponse().getAirline().getAmount()) <= 0) {
                pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.BILL_PAID.getValue());
            } else if ((pepe.getRespay().getResponse().getAirline().getStatus() != null) && (pepe.getRespay().getResponse().getAirline().getStatus().equals("1"))) {
                pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.BILL_PAID.getValue());
            } else {
                pepe.getPep().setAmount(pepe.getRespay().getResponse().getAirline().getAmount());
            }
        } else if (pepe.getPep().getIdservicetype().equals("HOCPHI")
                && pepe.getPep().getIdprovider().equals(Processor.ProviderEnum.HPBCN.toString())) {
            if (pepe.getRespay().getResponse().getTtpt() == null) {
                pepe.setResult(pepe.getRespay().getResult());//chuong  
            } else if (pepe.getRespay().getResponse().getTtpt().getTongthu() <= 0) {
                pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.BILL_PAID.getValue());
            } else {
                pepe.getPep().setAmount(Integer.toString(pepe.getRespay().getResponse().getTtpt().getTongthu()));
            }

        } else if (pepe.getPep().getIdservicetype().equals("LOGISTICS")) {
            if (pepe.getRespay().getResponse().getLogistics() == null) {
                pepe.setResult(pepe.getRespay().getResult());//chuong  
            } else if (Integer.parseInt(pepe.getRespay().getResponse().getLogistics().getAmount()) <= 0) {
                pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.BILL_PAID.getValue());
            } else {
                pepe.getPep().setAmount(pepe.getRespay().getResponse().getLogistics().getAmount());
            }
        } else if (pepe.getPep().getIdservicetype().equals("VNTOPUP")) {
            if (pepe.getRespay().getResponse().getVntopup() == null) {
                pepe.setResult(pepe.getRespay().getResult());//chuong
            } else if (Integer.parseInt(pepe.getRespay().getResponse().getVntopup().getAmount()) <= 0) {
                pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.BILL_PAID.getValue());
            } else {
                pepe.getPep().setAmount(pepe.getRespay().getResponse().getVntopup().getAmount());
            }

        } else {
            if (pepe.getRespay().getResponse().getBilling() == null) {
                //pepe.setResult(Message.PaymentResultEnum.BILL_PAID.getValue());
                pepe.setResult(pepe.getRespay().getResult());//chuong
            } else if (Integer.parseInt(pepe.getRespay().getResponse().getBilling().getAmount()) <= 0) {
                //[Change: case Prudential]
                //[Date & Who: Update 22/05/2014 by HieuDT]
                //[Description: Truong hop Prudential, luc nao cung tra ve Bill Amount = 0
                //              KH se thanh toan trong range cho ma he thong tra ve]
                if (pepe.getPep().getIdprovider().equals(Configuration.getProperty("provider.PF"))) {
                    pepe.getPep().setAmount(pepe.getRespay().getResponse().getBilling().getAmount());
                } else {
                    pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.BILL_PAID.getValue());
                }

            } else {
                pepe.getPep().setAmount(pepe.getRespay().getResponse().getBilling().getAmount());
            }
        }
    }

    private void checkBillPaidFromPartner(PblEbkProcessEx pepe) throws Exception {
        if (!pepe.getRespay().getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())) {
            //apepe.setResult(Message.PaymentResultEnum.CANNOT_PAIDBILL_PARTNER.getValue());
            pepe.setResult(pepe.getRespay().getResult());
        }
    }

    private String getMsgResult(String result, String idlang) {
        return scb.com.vn.message.Message.getMessagePaymentResult(scb.com.vn.message.Message.PaymentResultEnum.get(result), idlang);
    }

    private String formatString(String strData, String[] arg) {
        return String.format(strData, (Object[]) arg);
    }

    private ResponseForm showmessage(PblEbkProcess pep, PaymentResultEnum msg, String screen, String des) {
        try {
            ResponseForm respFormExchg = new ResponseForm();
            Response respExchg = new Response();
            String msgdata = "";
            if (screen.equalsIgnoreCase("4") && msg.equals(PaymentResultEnum.TIMEOUT)) {
                msgdata = getMsgResult(msg.getValue(), idlanguage) + " Ngân hàng sẽ tiến hành tra soát giao dịch của Quý khách tối đa 2 Ngày làm việc tiếp theo.";
            } else {
                msgdata = getMsgResult(msg.getValue(), idlanguage);
            }
            respExchg.setData(msgdata);
            respExchg.setResult(msg.getValue());
            respFormExchg.setScreen(screen);
            respFormExchg.setResp(respExchg);

            //Ghi log du lieu
            writeLog(pep, msg.getValue(), des + " - Lỗi giao dịch: " + respExchg.getData());

            return respFormExchg;
        } catch (Exception ex) {
            // Logger.getLogger(ExchangePaybill.class.getName()).log(Level.SEVERE, null, ex);
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION showmessage): " + ex.getMessage());
        }
        return null;
    }

    //PROCESS LOG

    /**
     *
     * @param pep
     * @param result
     * @param des
     */
    public void writeLog(PblEbkProcess pep, String result, String des) {
        try {
            String msglog = Helper.getDBI().getmsglog("PBL_EBK_PROCESS", "TRANSCODE", String.valueOf(pep.getTranscode()));
            if (msglog == null) {
                msglog = scb.com.vn.message.Message.getMessagePaymentResult(scb.com.vn.message.Message.PaymentResultEnum.get(result));
            }
            insPblLog("01", pep.getIdpartner(), pep.getIdservicetype(), pep.getIdprovider(), result, msglog, des, pep.getIdusr(), new Date());
        } catch (RemoteException ex) {
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION writeLog): " + ex.getMessage());
        }

    }

    private int insPblLog(String idchannel, String idpartner, String idservicetype, String idprovider, String result, String msglog, String description, String idmarker, Date insdate) throws RemoteException {
        try {
            PblLog pl = new PblLog();
            pl.setChannel(idchannel);
            pl.setIdpartner(idpartner);
            pl.setIdservicetype(idservicetype);
            pl.setIdprovider(idprovider);
            pl.setResult(result);
            pl.setMsglog(msglog);
            pl.setIdmarker(idmarker);
            pl.setInsdate(insdate);
            pl.setDescription(description);
            int id = (int) Helper.getDBI().insPblLog(pl);
            return id;
        } catch (Exception e) {
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION insPblLog): " + e.getMessage());
            return -1;
        }
    }
//END PROCESSS LOG

    /**
     *
     * @param pre
     * @return
     */
    public static String getPaymentMsgEnum(PaymentMsgEnum pre) {
        switch (pre) {
            case THANHTOAN_BILL:
                return "THANH TOAN HOA DON MA KH %1$s";
            case THANHTOAN_VMB:
                return "THANH TOAN VE MAY BAY/VE TAU MA KH %1$s";
            case THANHTOAN_VNTOPUP:
                return "NAP TIEN CHO SO DIEN THOAI %1$s";
            case HOAN_THANHTOAN_BILL:
                return "HOAN TIEN DO THANH TOAN KHONG THANH CONG";
            case HOAN_THANHTOAN_VMB:
                return "HOAN TIEN DO THANH TOAN KHONG THANH CONG";
            case HOAN_THANHTOAN_VNTOPUP:
                return "HOAN TIEN DO THANH TOAN KHONG THANH CONG";
            default:
                return "";
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public String get_airline(String code) {
        String name = hm_airlines.get(code);
        if (name == null || name.equals("")) {
            return code;
        } else {
            return name;
        }
    }

    /**
     *
     * @param phonenum
     * @return
     */
    public String get_telename(String phonenum) {
        if (phonenum.length() >= 9) {
            String firstnum1 = phonenum.substring(0, 3);
            String firstnum2 = phonenum.substring(0, 4);
            if (hm_tetefirstnums.containsKey(firstnum1)) {
                System.out.println(firstnum1 + " la " + hm_tetefirstnums.get(firstnum1));
                return hm_tetefirstnums.get(firstnum1);
            } else if (hm_tetefirstnums.containsKey(firstnum2)) {
                System.out.println(firstnum2 + " la " + hm_tetefirstnums.get(firstnum2));
                return hm_tetefirstnums.get(firstnum2);
            }
        } else {
            return "";
        }

        return "";
    }

    //kimncm

    /**
     *
     * @param customername
     * @return
     * @throws Exception
     */
    public ArrayList<BillDetail> convertCustomerNameToBill(String customername) throws Exception {
        ArrayList<BillDetail> billList = new ArrayList<BillDetail>();
        String[] tempStr = customername.split("@");
        if (tempStr.length > 1) {
            String strBills = tempStr[4];
            for (String s : strBills.split("#")) {
                BillDetail billDetail = new BillDetail();
                String[] strBill = s.split("%");
                billDetail.setBillId(strBill[0]);
                billDetail.setMonth(strBill[1]);
                billDetail.setMoneyAmount(new BigDecimal(strBill[2]));
                //totalAmountOfBills = totalAmountOfBills.add(bill.getMoneyAmount());   
                billList.add(billDetail);
            }
        }
        return billList;
    }

    /**
     *
     * @param custname
     * @param idprovider
     * @return
     */
    public boolean isFlgPrepaid(String custname, String idprovider) {
        String[] strTempArr = custname.split("@");
        if (strTempArr.length > 1) {
            if (strTempArr[2].equalsIgnoreCase("true")) {
                return true;
            } else if (idprovider.equals(Configuration.getProperty("provider.ACS"))
                    || idprovider.equals(Configuration.getProperty("provider.SCTV6"))
                    || idprovider.equals(Configuration.getProperty("provider.PF"))) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param custname
     * @return
     */
    public String getExpiredDate(String custname) {
        String[] strTempArr = custname.split("@");
        if (strTempArr.length > 5) {
            return strTempArr[5];
        }
        return null;
    }

    /**
     *
     * @param customername
     * @return
     * @throws Exception
     */
    public String getPaymentRange(String customername) throws Exception {
        String[] tempStr = customername.split("@");
        if (tempStr.length > 5) {
            return tempStr[5];
        } else {
            return null;
        }
    }

    //kimncm

    /**
     *
     */
    public void _init() {
        try {
            collect_path = Configuration.getProperty("collect.path");
            // Khoi tao danh sach dich vu
            if (ht_servicetypes.isEmpty()) {
                ArrayList arl_servicetypes = Helper.getDBI().getAllListServiceType();
                for (int i = 0; i <= arl_servicetypes.size() - 1; i++) {
                    HashMap _hm = (HashMap) arl_servicetypes.get(i);
                    //HashMap _hm_code =(HashMap) arl_servicetypes.get(2);
                    ht_servicetypes.put(_hm.get("idservicetype").toString(), _hm.get("name").toString());
                }
            }
            if (ht_providers.isEmpty()) {
                ArrayList arl_providers = Helper.getDBI().getAllListProvider();
                for (int i = 0; i <= arl_providers.size() - 1; i++) {
                    HashMap _hm = (HashMap) arl_providers.get(i);

                    ht_providers.put(_hm.get("idprovider").toString(), _hm.get("providername").toString());
                }
            }

            if (hm_tetefirstnums.isEmpty()) {
                hm_tetefirstnums = Helper.getDBI().getlist_telefirstnumber();
            }

            if (hm_airlines.isEmpty()) {
                hm_airlines = Helper.getDBI().getlist_parainfo("Airlines");
            }
            if (hm_cbo_partner.isEmpty()) {
                hm_cbo_partner = Helper.getDBI().getPblPartnerservices();
            }

            if (hm_screens.isEmpty()) {
                hm_screens = Helper.getDBI().getPblEbkScreens();
            }

        } catch (RemoteException ex) {
            //Logger.getLogger(ExchangePaybill.class.getName()).log(Level.SEVERE, null, ex);
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION _init): " + ex.getMessage());
        }
    }

    /**
     *
     */
    public static void _reinit() {
        try {
            collect_path = Configuration.getProperty("collect.path");
            // Khoi tao danh sach dich vu
            ht_servicetypes.clear();
            ArrayList arl_servicetypes = Helper.getDBI().getAllListServiceType();
            for (int i = 0; i <= arl_servicetypes.size() - 1; i++) {
                HashMap _hm = (HashMap) arl_servicetypes.get(i);
                //HashMap _hm_code =(HashMap) arl_servicetypes.get(2);
                ht_servicetypes.put(_hm.get("idservicetype").toString(), _hm.get("name").toString());
            }

            ht_providers.clear();
            ArrayList arl_providers = Helper.getDBI().getAllListProvider();
            for (int i = 0; i <= arl_providers.size() - 1; i++) {
                HashMap _hm = (HashMap) arl_providers.get(i);

                ht_providers.put(_hm.get("idprovider").toString(), _hm.get("providername").toString());
            }

            hm_tetefirstnums.clear();
            hm_tetefirstnums = Helper.getDBI().getlist_telefirstnumber();

            hm_airlines.clear();
            hm_airlines = Helper.getDBI().getlist_parainfo("Airlines");

            hm_cbo_partner.clear();
            hm_cbo_partner = Helper.getDBI().getPblPartnerservices();

            hm_screens.clear();
            hm_screens = Helper.getDBI().getPblEbkScreens();

        } catch (RemoteException ex) {
            //Logger.getLogger(ExchangePaybill.class.getName()).log(Level.SEVERE, null, ex);
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION _reinit): " + ex.getMessage());
        }
    }

    /**
     *
     * Ham này thực hiện hạch toán core và gạch nợ đối tác Step 1: Insert
     * PblBillPaid Step 2: Call IIB hach toan Step 3: Call IIB de gach no doi
     * tac
     *
     * @param reqexchg
     * @param pep
     * @param pepe
     * @param from_custacc
     * @param billpaid
     * @param transfer_type
     * @return
     */
    private ResponseForm executePayBillIIB(RequestPayment reqpay, PblEbkProcess pep, PblEbkProcessEx pepe, VwCustAccountNew from_custacc, PblBillpaid billpaid) {
        try {

            String refCub = "";
            int idbillPaid = -1;

            //1.3 Insert bang BillPaid
            //PblBillpaid billpaid =new PblBillpaid();
            VwMstchanneluser mstchanneluser = Helper.getDBI().getVwMstchanneluser(pep.getIdusr());
            billpaid.setIdpartner(pep.getIdpartner());

            PblServicetype pst = new PblServicetype();
            pst.setIdservicetype(pep.getIdservicetype());
            billpaid.setPblServicetype(pst);

            PblProvider pdr = new PblProvider();
            pdr.setIdprovider(pep.getIdprovider());
            billpaid.setPblProvider(pdr);

            billpaid.setCustomercode(pep.getCustomercode());
            billpaid.setDataxml("");
            billpaid.setIduserMaker(pep.getIdusr());
            billpaid.setIdchanneluserMaker(mstchanneluser.getIdchanneluser());
            billpaid.setIduserMaker(pep.getIdusr());
            billpaid.setIduserChecker(pep.getIdusr());
            billpaid.setIdchanneluserChecker(mstchanneluser.getIdchanneluser());
            billpaid.setIduserChecker(pep.getIdusr());
            billpaid.setIdchannel("01");
            billpaid.setUsertype("1");
            billpaid.setRefPartner("");
            billpaid.setRefFcubs("");
            billpaid.setTotalamount(Long.valueOf(pep.getAmount()));
            billpaid.setPaymentmethod(2);
            billpaid.setAccCust(from_custacc.getCustNo());
            billpaid.setAccIdaccount(from_custacc.getCustAcNo());
            billpaid.setAccHoldername(from_custacc.getFullName());
            billpaid.setAccAddress(from_custacc.getAddress());
            Date d = new Date();
            billpaid.setInsdate(d);
            billpaid.setInsdateMaker(d);
            billpaid.setInsdateChecker(d);
            billpaid.setTransdate(d);
            billpaid.setPaydate(null);
            billpaid.setPaydateFcubs(d);
            billpaid.setIsapproved('A');
            billpaid.setStatus('W');
            //billpaid.setBranchcode(billpaid.getAccIdaccount().substring(0, 3));
            billpaid.setBranchcode(CommonUtils.getBranchAccount(billpaid.getAccIdaccount()));
            idbillPaid = Helper.getDBI().insertPaybillBillPaid(billpaid);

            if (idbillPaid <= 0) {
                Helper.writeLogError(ExchangePaybill.class, " Thực hiện insert vào idbillPaid không thành công:" + idbillPaid);
                pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
                return showmessage(pepe.getPep(), PaymentResultEnum.SYSTEM_ERROR, "3", "Thực hiện insert vào idbillPaid không thành công: " + idbillPaid);
            } else {
                //cap nhap process thanh cong
                pep.setIdscreen("4");
                pep.setStatus("A");
                pep.setRefBillpaid(String.valueOf(idbillPaid));
                Helper.getDBI().updEbkProcess(pep);
            }
            //STEP 2: Cat tien thanh toan hoa don cua KH

            reqpay.setProcessingcode("PAY");
            reqpay.setDatetime(Common.getDate("ddMMyyyyHHmmss"));
            reqpay.setTranscode("EB" + String.valueOf(System.currentTimeMillis()));

            IIBBillingPaymentService iibBillingService = new IIBBillingPaymentService();
            ResponsePayment responsepay = iibBillingService.callPartnerGateway(Configuration.getIIBContext(), reqpay);

            if (responsepay.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())) {
                refCub = responsepay.getRefcore();
                Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, "***- LOG THANH TOAN IB FUNCTION executePayBillIIB  iibBillingService): Ma Khach Hang: " + billpaid.getCustomercode() + " -- So tham chieu trich tien REFCUBS= " + refCub + " -- From Account :" + from_custacc.getCustAcNo());
                billpaid.setIdbillpaid(idbillPaid);
                billpaid.setRefFcubs(refCub);
                Helper.getDBI().updatePaybillBillPaid(billpaid);

            } else {
                Helper.writeLogError(ExchangePaybill.class, "***- LOG THANH TOAN IB FUNCTION executePayBillIIB  hach toan khong thanh cong: Ma Khach Hang: " + billpaid.getCustomercode() + " - Ma loi:" + responsepay.getResult() + responsepay.getResultMessage());
                pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.CANNOT_TRANSFERFCUBS.getValue());
                return showmessage(pepe.getPep(), PaymentResultEnum.CANNOT_TRANSFERFCUBS, "4", "Không thực hiện được trích tiền từ tài khoản thanh toán khách hàng.");
            }
            //STEP 3: GACH NO DOI TAC
            responsepay = iibBillingService.payPartner(Configuration.getIIBContext(), responsepay);
            pepe.setRespay(responsepay);
            pepe.getRespay().setTranscode(responsepay.getTranscode());
            pepe.setResult(responsepay.getResult());
            billpaid.setDataxml(Xml.Marshaller(responsepay));

            if (responsepay.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())) {
                billpaid.setPaydate(new Date());
                billpaid.setRefPartner(responsepay.getTranscode());
                billpaid.setStatus('D');
                Helper.getDBI().updatePaybillBillPaid(billpaid);
                return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán thành công.");
            } else {
                //giao dich that bai đã hoàn tiền cho KH
                if (pepe.getResult().equals(PaymentResultEnum.ERROR_PARTNER_REFUND.getValue())) {
                    String _msgouttransfer = "TRANSFER UBS. Thuc hien HOAN TIEN HOAN TIEN %1$s. [So but toan-%2$s]";
                    Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "THANH CONG", refCub));
                    updateBillStatusToDB(billpaid, 'F');
                    return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Số giao dịch tham chiếu fcus trả lại tiền cho KH :" + refCub);
                } else if (pepe.getResult().equals(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue())) {
                    //Khong hoan tien, yeu cau doi tac gach bu giao dich
                    String a = "PAYBILL IS NOT SUCCESS. Hold tien KH, yeu cau doi tac gach bu tay Result %1$s. [idbillpaid-%2$s]";
                    Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, String.format(a, pepe.getResult(), billpaid.getIdbillpaid()));
                    updateBillStatusToDB(billpaid, 'H');
                    return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");

                } else {
                    //Hold toan bo giao dich
                    String a = "PAYBILL IS NOT SUCCESS. TTHD doi tac khong thanh cong, thuc hien doi soat Result %1$s. [idbillpaid-%2$s]";
                    Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, String.format(a, pepe.getResult(), billpaid.getIdbillpaid()));
                    updateBillStatusToDB(billpaid, 'H');
                    return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
                }
            }
        } catch (RemoteException ex) {
            //Logger.getLogger(ExchangePaybill.class.getName()).log(Level.SEVERE, null, ex);
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION executePayBillIIB): " + ex.getMessage());
            pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
            return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Lỗi hệ thống.");
        } catch (Exception ex) {
            Helper.writeLogError(ExchangePaybill.class, "***- ERROR IB (FUNCTION executePayBillIIB): " + ex.getMessage());
            pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
            return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Lỗi hệ thống.");
        }
    }
}
