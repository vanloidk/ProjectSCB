/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visa.utils;

import static com.visa.utils.VisaAPIClient.logger;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Visa
 * @modifier Nguyen Dac Binh Minh
 */
public class BasicAuthHeaderGenerator
{
    public static String getBasicAuthHeader()
    {
        String userId = VisaProperties.getProperty(Property.USER_ID);
//        logger.info("User ID : " + userId);
        String password = VisaProperties.getProperty(Property.PASSWORD);
//        logger.info("password : " + password);
        return "Basic " + base64Encode(userId + ":" + password);
    }

    private static String base64Encode(String token)
    {
        byte[] encodedBytes = Base64.encodeBase64(token.getBytes());
        return new String(encodedBytes, Charset.forName("UTF-8"));
    }
}