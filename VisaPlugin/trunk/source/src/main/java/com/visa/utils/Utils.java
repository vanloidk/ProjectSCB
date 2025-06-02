/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visa.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import static com.visa.utils.VisaAPIClient.logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;

/**
 *
 * @author Nguyen Dac Binh Minh
 */
public class Utils
{
    public static String getCurrentDate()
    {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        TimeZone utc = TimeZone.getTimeZone("UTC");
        sdfDate.setTimeZone(utc);
        Date now = new Date();
        return sdfDate.format(now);
    }
    
    public static String getResponseMessage(CloseableHttpResponse response) throws IOException
    {   
        logger.error(response.toString());
        // Get the response json object
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null)
        {
            result.append(line);
        }
        
        // Print the response details
        if(!StringUtils.isEmpty(result.toString()))
        {
            ObjectMapper mapper = getObjectMapperInstance();
            Object tree;
            try
            {
                tree = mapper.readValue(result.toString(), Object.class);
                return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree);
            }
            catch (JsonProcessingException e)
            {
                logger.error(e.getMessage());
            }
            catch (IOException e)
            {
                logger.error(e.getMessage());
            }
        }
        return "500";
    }
    
    protected static ObjectMapper getObjectMapperInstance()
    {    
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true); // format json
        return mapper;
    }
    
    public static String buildResourcePath(MessageType messageType)
    {
        switch(messageType)
        {
            case FUNDSTRANSFER:
                return com.visa.utils.Contants.URL_FUNDSTRANSFER;
            case MERPUSHPAYMENT:
                return com.visa.utils.Contants.URL_MERPUSHPAYMENT;
            case CASHINPUSHPAYMENT:
                return com.visa.utils.Contants.URL_CASHINPUSHPAYMENT;
            case WATCHLISTSCREENING:
                return com.visa.utils.Contants.URL_WATCHLISTSCREENING;
            case HELLOWORLD:
                return com.visa.utils.Contants.URL_HELLOWORLD;
            default:
                return "";
        }
    }
}
