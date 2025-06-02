package scb.com.vn.controller;


import java.math.BigDecimal;
import scb.com.vn.payment.*;
import java.rmi.RemoteException;
import org.apache.log4j.Level;
import scb.com.vn.common.model.payment.*;
import scb.com.vn.message.Message.PaymentResultEnum;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import scb.com.vn.model.RTBuyCardRq;
import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.MessageMB;
/**
 *
 * @author minhndb
 */
public class BuyCardController {
    
    String IDPARTNER;
    String wsurlvtcaddress = Configuration.getProperty("ws.url.vtc.address");
    int wstimeoutvtc = Integer.parseInt(Configuration.getProperty("ws.url.vtc.timeout"));
    String version = Configuration.getProperty("ws.vtc.version");
    String partnercode = Configuration.getProperty("ws.vtc.partnercode");
    String decryptKey = Configuration.getProperty("ws.vtc.tripdeskey");
    Date date = new Date();
    SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddHHmmss");
    String transdate = dt.format(date);
    String privatekey = Configuration.getProperty("ws.buycard.scb.privatekey");
    String publickey = Configuration.getProperty("ws.buycard.vtc.publickey");
    
    
    //String wsurlvtcaddress = null;//Configuration.getProperty("ws.url.vtc.address");
    /*
    int wstimeoutvtc = 12000;//Integer.parseInt(Configuration.getProperty("ws.url.vtc.timeout"));
    String version = null;// Configuration.getProperty("ws.vtc.version");
    String partnercode = null;// Configuration.getProperty("ws.vtc.partnercode");
    String decryptKey = null;//Configuration.getProperty("ws.vtc.tripdeskey");
    Date date = new Date();
    SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddHHmmss");
    String transdate = dt.format(date);
    String privatekey = null;//Configuration.getProperty("ws.buycard.scb.privatekey");
    String publickey = null;//Configuration.getProperty("ws.buycard.vtc.publickey");
    
    /**
     *
     */
    public BuyCardController() {
        
        Helper.writeLog(BuyCardController.class, Level.INFO, "AAAAAAAAAAAAA");
    }

    /**
     *
     * @param rtbuycardrq
     * @return
     * @throws RemoteException
     */
    public ResponsePayment requestQuery(RTBuyCardRq rtbuycardrq) throws RemoteException {
        ResponsePayment _rp = new ResponsePayment();
        IPayment payment = null;
        if (rtbuycardrq.getIdpartner().equals("VTC")) {
            String transcode = String.valueOf(getIdSeqByName("SQ_ORGTRANSID_REFNO"));
            payment = null;//new scb.com.vn.payment.vtc.retail.Payment(this.wsurlvtcaddress, this.wstimeoutvtc, transcode, version, decryptKey, rtbuycardrq.getQuantity().toString(), rtbuycardrq.getProvider(), partnercode,
                    //rtbuycardrq.getPricetag(), String.valueOf(rtbuycardrq.getTotalamount()), transdate, publickey, privatekey);
            try {
                Helper.writeLog(BuyCardController.class, Level.INFO, "abv");
                _rp = payment.requestQuery();
                return _rp;
            } catch (Exception ex) {
                Helper.writeLogError(BuyCardController.class, String.format("[TYPE-MSG]-[RESPONSE QUERY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                return _rp;
            } finally {
                Helper.writeLog(BuyCardController.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST QUERY-%1$s]", new Object[]{_rp.getResult()}));
                Helper.writeLog(BuyCardController.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST QUERY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(BuyCardController.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE QUERY-%1$s]", new Object[]{payment.getResponse()}));
            }
        }
        _rp.setResult(PaymentResultEnum.PARTNER_INVALID.getValue());
        return _rp;
    }

    /**
     *
     * @param rtbuycardrq
     * @return
     * @throws Exception
     */
    public ResponsePayment requestPay(RTBuyCardRq rtbuycardrq) throws Exception {
        IPayment payment = null;
        ResponsePayment _rp = new ResponsePayment();
       
        /*
        if (rtbuycardrq.getIdpartner().equals("VTC")) {
            String transcode = String.valueOf(getIdSeqByName("SQ_ORGTRANSID_REFNO"));
            payment = null;// new scb.com.vn.payment.vtc.retail.Payment(this.wsurlvtcaddress, this.wstimeoutvtc, transcode, version, decryptKey, rtbuycardrq.getQuantity().toString(), rtbuycardrq.getProvider(), partnercode,
                    //rtbuycardrq.getPricetag(), String.valueOf(rtbuycardrq.getTotalamount()), transdate, publickey, privatekey);
            try {
                //_rp = payment.requestPay();
                //return _rp;
            } catch (Exception ex) {
                Helper.writeLogError(BuyCardController.class, String.format("[TYPE-MSG]-[RESPONSE PAY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.SYSTEM_ERROR.getValue());
                return _rp;
            } finally {
                Helper.writeLog(BuyCardController.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST PAY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(BuyCardController.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE PAY-%1$s]", new Object[]{payment.getResponse()}));
            }
        }*/
        CardInfo cardinfo = new CardInfo();
        List<CardDetail> list = new ArrayList<CardDetail>();
            
                CardDetail cd = new CardDetail();
                cd.setCardcode("20874904654967");
                cd.setCardserial("59700001968479");
                cd.setExpiredate("04/01/2023");
                list.add(cd);
            
            cardinfo.setCarddetail(list);
           _rp.getResponse().setCardinfo(cardinfo);
        _rp.setResult(PaymentResultEnum.OK.getValue());
        return _rp;
    }

    private int getIdSeqByName(String seqname) {
        try {
            return Helper.getDBI().getIdSeqByName(seqname);
        } catch (Exception ex) {
            throw new PaymentException(ex);
        }
    }

}
