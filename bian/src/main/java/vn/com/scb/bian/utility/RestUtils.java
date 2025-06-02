/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.utility;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

/**
 *
 * @author loinv3
 */
public class RestUtils {

    /**
     * LOGGER instance
     */
    private static final Logger LOGGER = Logger.getLogger(RestUtils.class);

    /**
     * REST TEMPLATE instance is a thread-safe
     */
    private RestTemplate restTemplate;

    /**
     * private constructor
     */
    public RestUtils() {
        try {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setReadTimeout(0);
            factory.setConnectTimeout(0);
            restTemplate = new RestTemplate(factory);
            ObjectMapper _mapper = new ObjectMapper();
            _mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            _mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setObjectMapper(_mapper);

            for (int i = 0; i < restTemplate.getMessageConverters().size(); i++) {
                final HttpMessageConverter<?> httpMessageConverter = restTemplate.getMessageConverters().get(i);
                if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                    restTemplate.getMessageConverters().set(i, converter);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
        }
    }

    /**
     * get instance of RestTemplate
     *
     * @return RestTemplate
     */
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

}
