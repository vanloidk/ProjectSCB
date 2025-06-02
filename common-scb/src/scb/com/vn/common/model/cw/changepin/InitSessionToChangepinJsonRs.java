/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cw.changepin;

import java.io.Serializable;

/**
 *
 * @author minhndb
 */
public class InitSessionToChangepinJsonRs implements Serializable {

    private static final long serialVersionUID = 1L;

    private String transactionId;
    private String refTransactionId;
    private String publicKey;
    private String accessToken;

    public String getTransactionId() {
        return transactionId;
    }

    public String getRefTransactionId() {
        return refTransactionId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

}
