/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.service;

import java.util.Arrays;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import vn.com.scb.bian.partner.dto.PartnerSCBTransactionInfoDto;
import vn.com.scb.bian.partner.dto.PartnerTransactionInfoDto;
import vn.com.scb.bian.partner.dto.PartnerTransactionInfoDto.accountInfo;
import vn.com.scb.bian.partner.dto.PartnerTransactionInfoDto.searchInfo;
import vn.com.scb.bian.partner.dto.PartnerTransactionInfoDto.selectTransactionInfoPartner_in;
import vn.com.scb.bian.partner.dto.PartnerTransactionInfoDto.transactionInfo;
import vn.com.scb.bian.partner.dto.PartnerTransactionInfoRespDto;
import vn.com.scb.bian.utility.RestUtils;
import org.apache.log4j.Logger;
import vn.com.scb.bian.utility.IIBContext;

/**
 *
 * @author loinv3
 */
public class IIBCoreService {

    /**
     * LOGGER instance
     */
    private static final Logger LOGGER = Logger.getLogger(RestUtils.class);
    public static final String ENPOINT_HISTORY_TRANSFER = "/apisocialnetwork/v1.0/rest/selectTransactionInfoPartner";

    /**
     *
     * @param iibContext
     * @param req
     * @return
     */
    public PartnerTransactionInfoRespDto getHistoryTransfer(IIBContext iibContext, PartnerSCBTransactionInfoDto req) {

        String tvsiRestUrl = iibContext.getServiceEndpoint().concat(ENPOINT_HISTORY_TRANSFER);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        try {

            PartnerTransactionInfoDto reqDtoTemp = buildReqTransactionInfoPartner(req);
            HttpEntity<PartnerTransactionInfoDto> entity = new HttpEntity<>(reqDtoTemp, headers);
            RestUtils resUtl = new RestUtils();
            ResponseEntity<PartnerTransactionInfoRespDto> result = resUtl.getRestTemplate().exchange(tvsiRestUrl, HttpMethod.POST, entity, PartnerTransactionInfoRespDto.class);
            PartnerTransactionInfoRespDto respResults = result.getBody();

            return respResults;
        } catch (RestClientException ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            return null;
        }

    }

    /**
     * Build request transaction info
     *
     * @param req
     * @return
     */
    private PartnerTransactionInfoDto buildReqTransactionInfoPartner(PartnerSCBTransactionInfoDto req) {

        //Parent properties
        selectTransactionInfoPartner_in reqDto = new selectTransactionInfoPartner_in();
        PartnerTransactionInfoDto result = new PartnerTransactionInfoDto();
        //account
        accountInfo account = new accountInfo();
        account.setAccountNum(req.getACCOUNTNUM());
        //search
        searchInfo search = new searchInfo();
        search.setFromDate(req.getFROMDATE());
        search.setToDate(req.getTODATE());
        search.setPartnerName(req.getPARTNER());
        search.setKeyWord(req.getDESCRIPTION());
        //transaction info
        transactionInfo tranInfo = new transactionInfo();
        //tranInfo.setClientCode(req.getClientCode());
        tranInfo.setcRefNum(req.getTRANSID());
        //tranInfo.setAccountInfo(account);
        reqDto.setTransactionInfo(tranInfo);
        reqDto.setSearchInfo(search);
        reqDto.setAccountInfo(account);
        result.setSelectTransactionInfoPartner_in(reqDto);

        return result;
    }
}
