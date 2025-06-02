/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.payment.RequestPayment;
import scb.com.vn.dbi.dto.PblBillpaid;
import scb.com.vn.dbi.dto.Pbl_partnercode;

/**
 *
 * @author minhndb
 */
public class VnpayUtils {
    
    private static final Logger LOGGER = Logger.getLogger(VnpayUtils.class);

    /**
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        TimeZone utc = TimeZone.getTimeZone("UTC");
        sdfDate.setTimeZone(utc);
        Date now = new Date();
        return sdfDate.format(now);
    }

    /**
     *
     * @param response
     * @return
     * @throws IOException
     */
    public static String getResponseMessage(CloseableHttpResponse response) throws IOException {
        // Get the response json object
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        // Print the response details
        if (!StringUtils.isEmpty(result.toString())) {
            ObjectMapper mapper = getObjectMapperInstance();
            Object tree;
            try {
                tree = mapper.readValue(result.toString(), Object.class);
                return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree);
            } catch (JsonProcessingException e) {
                throw e;
            } catch (IOException e) {
                throw e;
            }
        }
        return "500";
    }

    /**
     *
     * @return
     */
    protected static ObjectMapper getObjectMapperInstance() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true); // format json
        return mapper;
    }
    
    public static Pbl_partnercode getPartnercode(List<Pbl_partnercode> partnerCode, RequestPayment item) {
        Pbl_partnercode result = null;
        
        for (Pbl_partnercode pbl_partnercode : partnerCode) {
            if (pbl_partnercode.getIdServiceType().equals(item.getServicecode())
                    && pbl_partnercode.getIdProvider().equals(item.getProvidercode())) {
                return pbl_partnercode;
            }
        }
        LOGGER.warn("Ko tim thay du lieu providerCode va serviceCode trong cau hinh database. Service = ["
                + item.getServicecode() + "], Provider = ["
                + item.getProvidercode() + "]");
        return result;
    }
}
