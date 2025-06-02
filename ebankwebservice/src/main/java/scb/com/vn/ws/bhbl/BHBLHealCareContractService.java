/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.ws.bhbl;

import java.math.BigDecimal;
import java.util.Arrays;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import scb.com.vn.model.NotificationFirbase;
import scb.com.vn.model.NotificationFirbaseResp;
import scb.com.vn.model.bhbl.BHBLHealthCareContractResp;
import scb.com.vn.model.bhbl.BHBLPaymentHealthCareResp;
import scb.com.vn.model.bhbl.BHBLPaymentHealthCareRq;
import scb.com.vn.model.bhbl.BHBLRefreshTokenResp;
import scb.com.vn.model.bhbl.BHBLTokens;
import scb.com.vn.model.bhbl.ContractDataDtoResp;
import scb.com.vn.model.bhbl.ContractDataDtoResp.EnqueueStatus;
import scb.com.vn.model.bhbl.DequeueDataResp;
import scb.com.vn.model.bhbl.EnqueueDataRq;
import scb.com.vn.model.bhbl.GetHealCareDtoRq;
import scb.com.vn.utility.RestUtils;

/**
 *
 * @author loinv3
 */
public class BHBLHealCareContractService {

    private static final Logger LOGGER = Logger.getLogger(BHBLHealCareContractService.class);
    private static final String ENPOINT_GET_HEALTHCARE_CONTRACT = "api/GoldenHealth/IDequeue";
    private static final String ENPOINT_HEALTHCARE_CONTRACT = "api/GoldenHealth/IEnqueueSync";
    private static final String ENPOINT_HEALTHCARE_PAYMENT = "api/GoldenHealth/IPayment";
    private static final String ENPOINT_REFRESH_TOKEN = "token";
    private final RestUtils resUtl = new RestUtils();
    private final String errorMessage = "Co loi xay ra khi call api BL.";

    /*    
    public void setProxyServer(String ip, String port) {
        Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(ip, port != null ? Integer.parseInt(port) : 0));
        resUtl.factory.setProxy(proxy);
    }
     */
    /**
     * GetHealCareContractBL
     *
     * @param urlEndpoint
     * @param idContract
     * @param tokens
     * @return
     */
    public BHBLHealthCareContractResp getHealCareContractBL(String urlEndpoint, String idContract, BHBLTokens tokens) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + tokens.getAccessToken());
        String bhblRestUrl = urlEndpoint.concat(ENPOINT_GET_HEALTHCARE_CONTRACT);

        try {
            GetHealCareDtoRq HealCareRq = new GetHealCareDtoRq();
            HealCareRq.setInvoiceCode(idContract);
            HttpEntity<GetHealCareDtoRq> entity = new HttpEntity<>(HealCareRq, headers);

            ResponseEntity<BHBLHealthCareContractResp> result;
            try {
                result = resUtl.getRestTemplate().exchange(bhblRestUrl, HttpMethod.POST, entity, BHBLHealthCareContractResp.class);

            } catch (HttpStatusCodeException e) {
                if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    BHBLRefreshTokenResp refreshToken = refreshToken(urlEndpoint, tokens.getRefreshToken());
                    headers.remove("Authorization");
                    headers.set("Authorization", "Bearer " + (refreshToken != null ? refreshToken.getAccess_token() : ""));
                    HttpEntity<GetHealCareDtoRq> entityR = new HttpEntity<>(HealCareRq, headers);

                    result = resUtl.getRestTemplate().exchange(bhblRestUrl, HttpMethod.POST, entityR, BHBLHealthCareContractResp.class);
                } else {
                    LOGGER.error(ExceptionUtils.getRootCauseMessage(e), e);

                    BHBLHealthCareContractResp resp = new BHBLHealthCareContractResp();
                    resp.setErrorCode(String.valueOf(e.getStatusCode()));
                    resp.setErrorMsg(errorMessage);

                    return resp;
                }
            }

            BHBLHealthCareContractResp resp = result.getBody();
            if (resp == null) {
                resp = new BHBLHealthCareContractResp();
                resp.setDequeueData(new DequeueDataResp());
            }

            resp.setErrorCode("200");
            return resp;
        } catch (HttpStatusCodeException ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);

            BHBLHealthCareContractResp resp = new BHBLHealthCareContractResp();
            resp.setErrorCode(String.valueOf(ex.getStatusCode()));
            resp.setErrorMsg(errorMessage);
            return resp;
        }
    }

    /**
     * HealthCareBLInsPayment
     *
     * @param urlEndpoint
     * @param conTractDataRq
     * @param signature
     * @param tokens
     * @return
     */
    public ContractDataDtoResp createHealCareContractBL(String urlEndpoint, EnqueueDataRq conTractDataRq, String signature, BHBLTokens tokens) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + tokens.getAccessToken());
        headers.add("Signature", signature);

        String bhblRestUrl = urlEndpoint.concat(ENPOINT_HEALTHCARE_CONTRACT);

        try {
            HttpEntity<EnqueueDataRq> entity = new HttpEntity<>(conTractDataRq, headers);
            ResponseEntity<ContractDataDtoResp> result;

            try {
                result = resUtl.getRestTemplate().exchange(bhblRestUrl, HttpMethod.POST, entity, ContractDataDtoResp.class);

            } catch (HttpStatusCodeException e) {
                if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    BHBLRefreshTokenResp refreshToken = refreshToken(urlEndpoint, tokens.getRefreshToken());
                    headers.remove("Authorization");
                    headers.add("Authorization", "Bearer " + (refreshToken != null ? refreshToken.getAccess_token() : ""));
                    HttpEntity<EnqueueDataRq> entityR = new HttpEntity<>(conTractDataRq, headers);

                    result = resUtl.getRestTemplate().exchange(bhblRestUrl, HttpMethod.POST, entityR, ContractDataDtoResp.class);
                } else {
                    LOGGER.error(ExceptionUtils.getRootCauseMessage(e), e);
                    ContractDataDtoResp resp = new ContractDataDtoResp();
                    EnqueueStatus equ = new EnqueueStatus();
                    equ.setEnqueueCode(String.valueOf(e.getStatusCode()));
                    equ.setEnqueueDesc(errorMessage);
                    resp.setEnqueueStatus(equ);

                    return resp;
                }
            } catch (RestClientException ex02) { //api khong co phan hoi - time out

                LOGGER.error(ExceptionUtils.getRootCauseMessage(ex02), ex02);
                ContractDataDtoResp resp = new ContractDataDtoResp();
                EnqueueStatus equ = new EnqueueStatus();

                equ.setEnqueueCode("notResponseCd");
                equ.setEnqueueDesc(ExceptionUtils.getRootCauseMessage(ex02));
                resp.setEnqueueStatus(equ);

                return resp;
            }

            ContractDataDtoResp resp = result.getBody();
            return resp;
        } catch (HttpStatusCodeException ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);

            EnqueueStatus equ = new EnqueueStatus();
            equ.setEnqueueCode(String.valueOf(ex.getStatusCode()));
            equ.setEnqueueDesc(errorMessage);
            ContractDataDtoResp resp = new ContractDataDtoResp();
            resp.setEnqueueStatus(equ);
            return resp;
        }
    }

    /**
     * paymentHealthCareBL
     *
     * @param urlEndpoint
     * @param orderNo
     * @param amount
     * @param tokens
     * @return
     */
    public BHBLPaymentHealthCareResp paymentHealthCareBL(String urlEndpoint, String orderNo, BigDecimal amount, BHBLTokens tokens) {

        HttpHeaders headers = new HttpHeaders();
        headers.clear();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + tokens.getAccessToken());
        String bhblRestUrl = urlEndpoint.concat(ENPOINT_HEALTHCARE_PAYMENT);

        try {
            BHBLPaymentHealthCareRq paymentRq = new BHBLPaymentHealthCareRq();
            paymentRq.setOrder_no(orderNo);
            paymentRq.setAmount(amount);
            HttpEntity<BHBLPaymentHealthCareRq> entity = new HttpEntity<>(paymentRq, headers);
            ResponseEntity<BHBLPaymentHealthCareResp> result;

            try {
                result = resUtl.getRestTemplate().exchange(bhblRestUrl, HttpMethod.POST, entity, BHBLPaymentHealthCareResp.class);

            } catch (HttpStatusCodeException e) {
                if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    BHBLRefreshTokenResp refreshToken = refreshToken(urlEndpoint, tokens.getRefreshToken());
                    headers.remove("Authorization");
                    headers.add("Authorization", "Bearer " + (refreshToken != null ? refreshToken.getAccess_token() : ""));
                    result = resUtl.getRestTemplate().exchange(bhblRestUrl, HttpMethod.POST, entity, BHBLPaymentHealthCareResp.class);

                } else {
                    LOGGER.error(ExceptionUtils.getRootCauseMessage(e), e);
                    BHBLPaymentHealthCareResp resp = new BHBLPaymentHealthCareResp();
                    resp.setErrorCode(String.valueOf(e.getStatusCode()));
                    resp.setErrorMsg("Co loi api BL");

                    return resp;
                }
            }
            BHBLPaymentHealthCareResp resp = result.getBody();
            resp.setErrorCode(HttpStatus.OK.toString());

            return resp;
        } catch (HttpStatusCodeException ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            BHBLPaymentHealthCareResp resp = new BHBLPaymentHealthCareResp();
            resp.setErrorCode(String.valueOf(ex.getStatusCode()));
            resp.setErrorMsg(errorMessage);

            return resp;
        }
    }

    /**
     * RefreshToken
     *
     * @param iibContext
     * @param refreshToken
     * @return
     */
    private BHBLRefreshTokenResp refreshToken(String urlEndpoint, String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.clear();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> parmaters = new LinkedMultiValueMap<>();
        parmaters.add("refresh_token", refreshToken);
        parmaters.add("grant_type", "refresh_token");
        String bhblRestUrl = urlEndpoint.concat(ENPOINT_REFRESH_TOKEN);

        try {
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parmaters, headers);
            ResponseEntity<BHBLRefreshTokenResp> result = resUtl.getRestTemplate().exchange(bhblRestUrl, HttpMethod.POST, request, BHBLRefreshTokenResp.class);
            BHBLRefreshTokenResp resp = result.getBody();

            return resp;
        } catch (RestClientException ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            return null;
        }
    }

    public NotificationFirbaseResp firebaseSendMsg(String urlEndpoint, NotificationFirbase notiRq) {
        String tokens = "AAAAaG4Rlr8:APA91bF7C8ECu4cthgl1rzbDqZujCP5GCrZHpfHSOkhgAdALfBPhlsNdyDRltcqZcvqrg-bNWNNTgz5nahi6I0Ztme6G-D7E-z2xJ04SdivTRh4iaW74FGGRrQNvPBdXHlIJtqS5z_fn";
        urlEndpoint = "https://fcm.googleapis.com/fcm/send";
        
        HttpHeaders headers = new HttpHeaders();
        headers.clear();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + tokens);
        String bhblRestUrl = urlEndpoint;
        
        try {
            //notiRq.setTo("eyp2INg9Dq-qpzfQk0JxuM:APA91bFIgFKygFc1ngDIsbEuivLPqyuwrbCde_K5qvTlKQL-xAsqw2sO8wuLjh1S_LlY2yk4IT0kHXSt2aMgEwhZ_Gqw2wgTj2yDvkhLbrB-hIYaxXcVaUqpCfs2OiG--QaCJTkTppiU");

            NotificationFirbase.notification notiSubRq = new NotificationFirbase.notification();
            notiSubRq.setTitle("Firebase");
            notiSubRq.setBody("Firebase is awesomeddd");
            notiSubRq.setIcon("");
            notiSubRq.setClick_action("notiSubRq");
            notiRq.setNotification(notiSubRq);
            //HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parmaters, headers);
            HttpEntity<NotificationFirbase> entity = new HttpEntity<>(notiRq, headers);
            ResponseEntity<NotificationFirbaseResp> result = resUtl.getRestTemplate().exchange(bhblRestUrl, HttpMethod.POST, entity, NotificationFirbaseResp.class);
            NotificationFirbaseResp resp = result.getBody();

            return resp;
        } catch (RestClientException ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            return null;
        }
    }
    
}
