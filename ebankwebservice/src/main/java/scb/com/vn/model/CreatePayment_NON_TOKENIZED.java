/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import com.mastercard.api.core.model.RequestMap;
import scb.com.vn.common.model.masterpass.PayByQRCodeRq;
import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.MasterUtils;

/**
 *
 * @author minhndb
 */
public class CreatePayment_NON_TOKENIZED {

    /**
     * **************************** CONSTANT *********************************
     */
    private final String PARTNER_ID_VALUE = Configuration.getProperty("masterpass.partnerId");
    private final String SENDERPAN = "pan:%s;exp=%s";
    private final String MERPAN = "pan:";
    private final String PAYMENTTYPE = "P2M";

    /**
     * **************************** CONSTANT *********************************
     */
    /**
     * **************************** NAME *********************************
     */
    private final String PARTNER_ID = "partnerId";
    private final String TRANSFER_REFERENCE = "merchant_payment_transfer.transfer_reference";
    private final String FUNDING_SOURCE = "merchant_payment_transfer.funding_source";
    private final String DATETIME = "merchant_payment_transfer.transaction_local_date_time";
    private final String AMOUNT = "merchant_payment_transfer.amount";
    private final String CCY = "merchant_payment_transfer.currency";
    private final String SENDER_ACCOUNT = "merchant_payment_transfer.sender_account_uri";
    private final String SENDER_FNAME = "merchant_payment_transfer.sender.first_name";
    private final String SENDER_LNAME = "merchant_payment_transfer.sender.last_name";
    private final String SENDER_ADDRESS = "merchant_payment_transfer.sender.address.line1";
    private final String SENDER_CITY = "merchant_payment_transfer.sender.address.city";
    private final String SENDER_POSTALCODE = "merchant_payment_transfer.sender.address.postal_code";
    private final String SENDER_COUNTRY = "merchant_payment_transfer.sender.address.country";
    private final String MER_ACCOUNT = "merchant_payment_transfer.recipient_account_uri";
    private final String MER_FNAME = "merchant_payment_transfer.recipient.first_name";
    private final String MER_LNAME = "merchant_payment_transfer.recipient.last_name";
    private final String MER_CATEGORYCODE = "merchant_payment_transfer.recipient.merchant_category_code";
    private final String MER_CARD_NAME = "merchant_payment_transfer.participant.card_acceptor_name";
    private final String CHANNEL = "merchant_payment_transfer.channel";
    private final String ADD_MSG = "merchant_payment_transfer.additional_message";

    /* NOT NECCESSARY */
    private final String PAYMENT_TYPE = "merchant_payment_transfer.payment_type";
    private final String SENDER_MNAME = "merchant_payment_transfer.sender.middle_name";
    private final String MER_MNAME = "merchant_payment_transfer.recipient.middle_name";
    private final String MER_POSTALCODE = "merchant_payment_transfer.recipient.address.postal_code";
    private final String MER_CARD_ID = "merchant_payment_transfer.participant.card_acceptor_id";
    private final String DEVICE_ID = "merchant_payment_transfer.device_id";
    private final String MER_CITY = "merchant_payment_transfer.recipient.address.city";
    private final String MER_COUNTRY = "merchant_payment_transfer.recipient.address.country";
    private final String MER_COUNTRYSUB = "merchant_payment_transfer.recipient.address.country_subdivision";

    /**
     * **************************** NAME *********************************
     */
    private final String sequenceNo;
    private final String amount;
    private final String ccy;
    private final String dateTime;
    private final String fundingS;
    private final String senderAcc;
    private final String senderExpire;
    private final String senderFName;
    private final String senderLName;
    private final String senderAddress;
    private final String senderCity;
    private final String senderPostalCode;
    private final String senderCountry;
    private final String merAcc;
    private final String merFName;
    private final String merLName;
    private final String merCategory;
    private final String merCardName;
    private final String channel;
    private final String add_msg;

    /* NOT NECCESSARY */
    private final String senderMName;
    private final String merMName;
    private final String merPostalCode;
    private final String merCardId;
    private final String deviceId;
    private final String merCity;
    private final String merCountrySub;
    private final String merCountry;

    /**
     *
     * @param req
     */
    public CreatePayment_NON_TOKENIZED(PayByQRCodeRq req) {
        this.sequenceNo = req.getSequenceNo();
        this.amount = req.getAmount();
        this.ccy = req.getCcy();
        this.dateTime = req.getDateTime();
        this.fundingS = req.getFundingS();
        this.senderAcc = req.getSenderMSVSCardInfo().getPan_clear();
        this.senderExpire = CommonUtils.fromISO8601UTCFormat(req.getSenderMSVSCardInfo().getExpi_date(), "MMyyyy", "yyyy-MM");
        this.senderFName = req.getSenderMSVSCardInfo().getFrist_name();
        this.senderLName = req.getSenderMSVSCardInfo().getLast_name();
        this.senderAddress = req.getSenderMSVSCardInfo().getHme_addr();
        this.senderCity = req.getSenderMSVSCardInfo().getHme_town();
        this.senderPostalCode = req.getSenderMSVSCardInfo().getHme_zip();
        this.senderCountry = req.getSenderMSVSCardInfo().getHme_cntry();
        this.merAcc = req.getMerchantId();
        this.merFName = req.getMerFName();
        this.merLName = req.getMerLName();
        this.merCategory = req.getMerCategory();
        this.merCardName = req.getMerCardName();
        this.channel = req.getChannel();
        this.add_msg = req.getAdd_msg();

        this.senderMName = checkValue(req.getSenderMSVSCardInfo().getMiddleName());

        this.merMName = checkValue(req.getMerMName());
        this.merPostalCode = checkValue(req.getMerPostalCode());
        this.merCardId = checkValue(req.getMerCardId());
        this.deviceId = checkValue(req.getDeviceId());
        this.merCity = checkValue(req.getMerCity());
        this.merCountrySub = checkValue(req.getMerCountrySub());
        this.merCountry = checkValue(req.getMerCountry());
    }

    /**
     *
     * @return
     */
    public RequestMap buildRequestMapForPayment() {
        RequestMap map = new RequestMap();

        map.set(PARTNER_ID, PARTNER_ID_VALUE);
        map.set(TRANSFER_REFERENCE, sequenceNo);
        map.set(AMOUNT, amount);
        map.set(CCY, ccy);
        map.set(DATETIME, dateTime);
        map.set(FUNDING_SOURCE, fundingS);
        map.set(SENDER_ACCOUNT, String.format(SENDERPAN, senderAcc, senderExpire));
        map.set(SENDER_FNAME, senderFName);
        map.set(SENDER_LNAME, senderLName);
        map.set(SENDER_ADDRESS, senderAddress);
        map.set(SENDER_CITY, senderCity);
        map.set(SENDER_POSTALCODE, senderPostalCode);
        map.set(SENDER_COUNTRY, senderCountry);
        map.set(MER_ACCOUNT, MERPAN + merAcc);
        map.set(MER_FNAME, merFName);
        map.set(MER_LNAME, merLName);
        map.set(MER_CATEGORYCODE, merCategory);
        map.set(MER_CARD_NAME, merCardName);
        map.set(CHANNEL, channel);
        map.set(ADD_MSG, add_msg);

        map.set(PAYMENT_TYPE, PAYMENTTYPE);
        map.set(SENDER_MNAME, senderMName);
        map.set(MER_MNAME, merMName);
        map.set(MER_POSTALCODE, merPostalCode);
        //map.set(MER_CARD_ID, merCardId);
        map.set(DEVICE_ID, deviceId);
        map.set(MER_CITY, merCity);
        map.set(MER_COUNTRYSUB, merCountrySub);
        map.set(MER_COUNTRY, merCountry);

        return map;
    }

    /**
     *
     * @return
     */
    public String buildRequestMapForLogging() {
        StringBuilder str = new StringBuilder();
        
        str.append(PARTNER_ID).append(" = [").append(PARTNER_ID_VALUE).append("], ");
        str.append(TRANSFER_REFERENCE).append(" = [").append(sequenceNo).append("], ");
        str.append(AMOUNT).append(" = [").append(amount).append("], ");
        str.append(CCY).append(" = [").append(ccy).append("], ");
        str.append(DATETIME).append(" = [").append(dateTime).append("], ");
        str.append(FUNDING_SOURCE).append(" = [").append(fundingS).append("], ");
        str.append(SENDER_ACCOUNT).append(" = [").append(String.format(SENDERPAN, MasterUtils.hideCardnumber(senderAcc), senderExpire)).append("], ");
        str.append(SENDER_FNAME).append(" = [").append(senderFName).append("], ");
        str.append(SENDER_LNAME).append(" = [").append(senderLName).append("], ");
        str.append(SENDER_ADDRESS).append(" = [").append(senderAddress).append("], ");
        str.append(SENDER_CITY).append(" = [").append(senderCity).append("], ");
        str.append(SENDER_POSTALCODE).append(" = [").append(senderPostalCode).append("], ");
        str.append(SENDER_COUNTRY).append(" = [").append(senderCountry).append("], ");
        str.append(MER_ACCOUNT).append(" = [").append(MERPAN + MasterUtils.hideCardnumber(merAcc)).append("], ");
        str.append(MER_FNAME).append(" = [").append(merFName).append("], ");
        str.append(MER_LNAME).append(" = [").append(merLName).append("], ");
        str.append(MER_CATEGORYCODE).append(" = [").append(merCategory).append("], ");
        str.append(MER_CARD_NAME).append(" = [").append(merCardName).append("], ");
        str.append(CHANNEL).append(" = [").append(channel).append("], ");
        str.append(ADD_MSG).append(" = [").append(add_msg).append("], ");

        str.append(PAYMENT_TYPE).append(" = [").append(PAYMENTTYPE).append("], ");
        str.append(SENDER_MNAME).append(" = [").append(senderMName).append("], ");
        str.append(MER_MNAME).append(" = [").append(merMName).append("], ");
        str.append(MER_POSTALCODE).append(" = [").append(merPostalCode).append("], ");
        str.append(DEVICE_ID).append(" = [").append(deviceId).append("], ");
        str.append(MER_CITY).append(" = [").append(merCity).append("], ");
        str.append(MER_COUNTRYSUB).append(" = [").append(merCountrySub).append("], ");
        str.append(MER_COUNTRY).append(" = [").append(merCountry).append("], ");

        return str.toString();
    }

    private String checkValue(String value) {
        return (value == null) ? "" : value;
    }
}
