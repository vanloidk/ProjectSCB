/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cw.changepin;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "INITSESSIONTOCHANGEPINRS")
public class InitSessionToChangepinRs implements Serializable {
    private static final Logger logger = Logger.getLogger(InitSessionToChangepinRs.class);
    private static final long serialVersionUID = 1L;
    
    private String errorCode;
    private String errorDescription;
    private String transactionId;
    private String accessTokenKey;
    private String publicKey;
    private String refTransactionId;

    public InitSessionToChangepinRs() {
    }

    public InitSessionToChangepinRs(ErrorCodeEnum response) {
        this.errorCode = response.getCode();
        this.errorDescription = response.name();
    }
    
    public InitSessionToChangepinRs(InitSessionToChangepinRq req, ErrorCodeEnum response) {
        this.transactionId = req.getTransactionId();
        this.errorCode = response.getCode();
        this.errorDescription = response.name();
    }

    @XmlElement(name = "ERROR_CODE")
    public String getErrorCode() {
        return errorCode;
    }

    @XmlElement(name = "ERROR_DESCRIPTION")
    public String getErrorDescription() {
        return errorDescription;
    }
    
    @XmlElement(name = "TRANSACTIONID")
    public String getTransactionId() {
        return transactionId;
    }

    @XmlElement(name = "ACCESS_TOKEN")
    public String getAccessTokenKey() {
        return accessTokenKey;
    }

    @XmlElement(name = "PUBLICKEY")
    public String getPublicKey() {
        return publicKey;
    }

    @XmlElement(name = "REFTRANSACTIONID")
    public String getRefTransactionId() {
        return refTransactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setAccessTokenKey(String accessTokenKey) {
        this.accessTokenKey = accessTokenKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void setRefTransactionId(String refTransactionId) {
        this.refTransactionId = refTransactionId;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
    
    public void setError(ErrorCodeEnum response) {
        this.errorCode = response.getCode();
        this.errorDescription = response.name();
    }
}
