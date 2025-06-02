/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.service.bhbl;

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
import vn.com.scb.bian.model.bhbl.BHBLHealthCareContractResp;
import vn.com.scb.bian.model.bhbl.BHBLPaymentHealthCareResp;
import vn.com.scb.bian.model.bhbl.BHBLPaymentHealthCareRq;
import vn.com.scb.bian.model.bhbl.BHBLRefreshTokenResp;
import vn.com.scb.bian.model.bhbl.BHBLTokens;
import vn.com.scb.bian.model.bhbl.ContractDataDtoResp;
import vn.com.scb.bian.model.bhbl.ContractDataDtoResp.EnqueueStatus;
import vn.com.scb.bian.model.bhbl.EnqueueDataRq;
import vn.com.scb.bian.model.bhbl.GetHealCareDtoRq;
import vn.com.scb.bian.utility.IIBContext;
import vn.com.scb.bian.utility.RestUtils;

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
    
    private final String errorMessage = "Co loi xay ra khi call api BL.";

    /**
     * GetHealCareContractBL
     *
     * @param iibContext
     * @param idContract
     * @param tokens
     * @return
     */
    public BHBLHealthCareContractResp getHealCareContractBL(IIBContext iibContext, String idContract, BHBLTokens tokens) {
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + tokens.getAccessToken());
        String bhblRestUrl = iibContext.getPartnerServiceEndpoint().concat(ENPOINT_GET_HEALTHCARE_CONTRACT);
        
        try {
            GetHealCareDtoRq HealCareRq = new GetHealCareDtoRq();
            HealCareRq.setInvoiceCode(idContract);
            HttpEntity<GetHealCareDtoRq> entity = new HttpEntity<>(HealCareRq, headers);
            RestUtils resUtl = new RestUtils();
            
            ResponseEntity<BHBLHealthCareContractResp> result;
            try {
                result = resUtl.getRestTemplate().exchange(bhblRestUrl, HttpMethod.POST, entity, BHBLHealthCareContractResp.class);
                
            } catch (HttpStatusCodeException e) {
                if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    BHBLRefreshTokenResp refreshToken = refreshToken(iibContext, tokens.getRefreshToken());
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
     * @param iibContext
     * @param conTractDataRq
     * @param tokens
     * @return
     */
    public ContractDataDtoResp HealthCareBLInsPayment(IIBContext iibContext, EnqueueDataRq conTractDataRq, BHBLTokens tokens) {
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + tokens.getAccessToken());
        String bhblRestUrl = iibContext.getPartnerServiceEndpoint().concat(ENPOINT_HEALTHCARE_CONTRACT);
        
        try {
            HttpEntity<EnqueueDataRq> entity = new HttpEntity<>(conTractDataRq, headers);
            RestUtils resUtl = new RestUtils();
            ResponseEntity<ContractDataDtoResp> result;
            
            try {
                result = resUtl.getRestTemplate().exchange(bhblRestUrl, HttpMethod.POST, entity, ContractDataDtoResp.class);
                
            } catch (HttpStatusCodeException e) {
                if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    BHBLRefreshTokenResp refreshToken = refreshToken(iibContext, tokens.getRefreshToken());
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
     * @param iibContext
     * @param orderNo
     * @param amount
     * @param tokens
     * @return
     */
    public BHBLPaymentHealthCareResp paymentHealthCareBL(IIBContext iibContext, String orderNo, BigDecimal amount, BHBLTokens tokens) {
        
        HttpHeaders headers = new HttpHeaders();
        headers.clear();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + tokens.getAccessToken());
        String bhblRestUrl = iibContext.getPartnerServiceEndpoint().concat(ENPOINT_HEALTHCARE_PAYMENT);
        
        try {
            BHBLPaymentHealthCareRq paymentRq = new BHBLPaymentHealthCareRq();
            paymentRq.setOrder_no(orderNo);
            paymentRq.setAmount(amount);
            HttpEntity<BHBLPaymentHealthCareRq> entity = new HttpEntity<>(paymentRq, headers);
            RestUtils resUtl = new RestUtils();
            ResponseEntity<BHBLPaymentHealthCareResp> result;
            
            try {
                result = resUtl.getRestTemplate().exchange(bhblRestUrl, HttpMethod.POST, entity, BHBLPaymentHealthCareResp.class);
                
            } catch (HttpStatusCodeException e) {
                if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    BHBLRefreshTokenResp refreshToken = refreshToken(iibContext, tokens.getRefreshToken());
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
    private BHBLRefreshTokenResp refreshToken(IIBContext iibContext, String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.clear();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> parmaters = new LinkedMultiValueMap<>();
        parmaters.add("refresh_token", refreshToken);
        parmaters.add("grant_type", "refresh_token");
        String bhblRestUrl = iibContext.getPartnerServiceEndpoint().concat(ENPOINT_REFRESH_TOKEN);
        
        try {
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parmaters, headers);
            RestUtils resUtl = new RestUtils();
            ResponseEntity<BHBLRefreshTokenResp> result = resUtl.getRestTemplate().exchange(bhblRestUrl, HttpMethod.POST, request, BHBLRefreshTokenResp.class);
            BHBLRefreshTokenResp resp = result.getBody();
            
            return resp;
        } catch (RestClientException ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            return null;
        }
    }
    
}
