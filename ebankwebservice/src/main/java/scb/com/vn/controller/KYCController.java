/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import java.util.List;
import scb.com.vn.common.model.transfer.ReceivingInfo;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.KYCErrorMsg;
import scb.com.vn.utility.KYCUtils;
import vn.com.scb.bian.ws.FCCServicesStub;
import vn.com.scb.bian.ws.KycScore_in;
import vn.com.scb.bian.ws.KycScore_out;
import vn.com.scb.bian.ws.KycScore_outType;
import vn.com.scb.bian.ws.TransactionInfoType;

/**
 *
 * @author minhndb
 */
public class KYCController {

    private static final Logger LOGGER = Logger.getLogger(KYCController.class);

    private static final String ENDPOINT = Configuration.getProperty("kyc.endpoint");
    /* thong tin cau hinh header bao gom user/password de co quyen truy cap IIB
    private static final String USER = Configuration.getProperty("kyc.endpoint.user");
    private static final String PASS = Configuration.getProperty("kyc.endpoint.pass");
     */
    public static final List<String> PARTNER = KYCUtils.getKYCPartner();
    public static final Gson GSON = new Gson();

    public KYCErrorMsg kycScore(ReceivingInfo info) {
        try {
            // cau hinh dia chi goi den
            FCCServicesStub stub = new FCCServicesStub(ENDPOINT);

            /* cau hinh header bao gom user/password de co quyen truy cap IIB 
            ServiceClient client = stub._getServiceClient();
            HttpTransportPropertiesImpl.Authenticator authenticator = new HttpTransportPropertiesImpl.Authenticator();
            authenticator.setUsername(USER);
            authenticator.setPassword(PASS);
            authenticator.setPreemptiveAuthentication(true);
            Options options = client.getOptions();
            options.setProperty(HTTPConstants.AUTHENTICATE, authenticator);
            client.setOptions(options);
             */
            // tao thong tin request
            KycScore_in request = KYCUtils.convertToKYC(info);
            LOGGER.info("KYC Request --> " + GSON.toJson(request));

            // thuc hien goi qua IIB
            KycScore_out mesResponse = stub.kycScore(request);

            // ket qua tra ve cua IIB
            KycScore_outType response = mesResponse.getKycScore_out();
            LOGGER.info("KYC Response --> " + GSON.toJson(response));

            // thuc hien loc lay ket qua
            TransactionInfoType transInfo = response.getTransactionInfo();
            String errorMessage = transInfo.getTransactionErrorMsg();

            // chuyen doi ve gia tri ENUM da duoc define san
            return KYCErrorMsg.valueOf(errorMessage.trim());
        } catch (Exception e) {
            LOGGER.error("kycScore --> Ex: " + e);
        }
        return KYCErrorMsg.UNACCEPT;
    }
    public KYCErrorMsg kycScoreV2(ReceivingInfo info) {
        try {
            // cau hinh dia chi goi den
            FCCServicesStub stub = new FCCServicesStub(ENDPOINT);

            /* cau hinh header bao gom user/password de co quyen truy cap IIB 
            ServiceClient client = stub._getServiceClient();
            HttpTransportPropertiesImpl.Authenticator authenticator = new HttpTransportPropertiesImpl.Authenticator();
            authenticator.setUsername(USER);
            authenticator.setPassword(PASS);
            authenticator.setPreemptiveAuthentication(true);
            Options options = client.getOptions();
            options.setProperty(HTTPConstants.AUTHENTICATE, authenticator);
            client.setOptions(options);
             */
            // tao thong tin request
            KycScore_in request = KYCUtils.convertToKYC02(info);
            LOGGER.info("KYC Request --> " + GSON.toJson(request));

            // thuc hien goi qua IIB
            KycScore_out mesResponse = stub.kycScore(request);

            // ket qua tra ve cua IIB
            KycScore_outType response = mesResponse.getKycScore_out();
            LOGGER.info("KYC Response --> " + GSON.toJson(response));

            // thuc hien loc lay ket qua
            TransactionInfoType transInfo = response.getTransactionInfo();
            String errorMessage = transInfo.getTransactionErrorMsg();
            if (!("LOW".equals(errorMessage.trim())) && !("MEDIUM".equals(errorMessage.trim())) && !("HIGH".equals(errorMessage.trim()))){
                //KYC blocked
                return KYCErrorMsg.UNACCEPT;
            }
            // chuyen doi ve gia tri ENUM da duoc define san
            return KYCErrorMsg.valueOf(errorMessage.trim());
        } catch (Exception e) {
            LOGGER.error("kycScore --> Ex: " + e);
            return KYCErrorMsg.KYCEXCEPTION;
        }
    }    	
}
