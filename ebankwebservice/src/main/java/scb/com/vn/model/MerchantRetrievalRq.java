/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import com.mastercard.api.core.exception.ApiException;
import com.mastercard.api.core.model.RequestMap;
import com.mastercard.api.p2m.MerchantRetrieval;
import org.apache.log4j.Level;
import scb.com.vn.utility.Helper;

/**
 *
 * @author minhndb
 */
public class MerchantRetrievalRq {

    /**
     * **************************** CONSTANT *********************************
     */
    private final String PARTNER_ID_VALUE = "ptnr_TSZCk-RhmfixTG4_bD8vplt6zWs";
    /**
     * **************************** CONSTANT *********************************
     */

    /**
     * **************************** NAME *********************************
     */
    private final String PARTNER_ID = "partnerId";
    private final String REF = "ref";
    private final String TRANSFER_ID = "transferId";
    /**
     * **************************** NAME *********************************
     */

    private String ref;
    private String id;

    /* index: 1 ~ byReference, 2 ~ byId */

    /**
     *
     * @param value
     * @param index
     */

    public MerchantRetrievalRq(String value, int index) {
        switch (index) {
            case 1:
                ref = value;
                break;
            case 2:
                id = value;
                break;
            default:
                ref = value;
                break;
        }
    }

    /**
     *
     * @return
     */
    public RequestMap buildRequestMapForPayment() {
        RequestMap map = new RequestMap();

        map.set(PARTNER_ID, PARTNER_ID_VALUE);

        if (ref != null && !ref.isEmpty()) {
            map.set(REF, ref);
        } else {
            map.set(TRANSFER_ID, id);
        }

        return map;
    }

    private boolean isRefMessage() {
        return (ref != null && !ref.isEmpty());
    }

    private int getIndex() {
        return isRefMessage() ? 1 : 2;
    }

    /**
     *
     * @return
     */
    public MCRetrievalRp execute() {
        MCRetrievalRp result;
        RequestMap map = buildRequestMapForPayment();
        MerchantRetrieval response;
        try {
            response = isRefMessage() ? MerchantRetrieval.readByReference(map) : MerchantRetrieval.readByID("", map);
            result = new MCRetrievalRp(response, getIndex());
        } catch (ApiException e) {
            result = new MCRetrievalRp(e);
            Helper.writeLog(this.getClass(), Level.INFO, "Failed to call MASSTERCARD. Exception = [" + e.toString() + "], Code = [" + e.getReasonCode() + "], Desc = [" + e.getMessage() + " - " + e.getSource() + "]");
        }
        return result;
    }

}
