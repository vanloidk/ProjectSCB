/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.utility.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import scb.com.vn.ultility.Log;



/**
 *
 * @author dungtq
 */
public class RESTTemplate {

    private HttpURLConnection conn;
//    private String urlString;
    private URL url;
    private ObjectMapper omapper;
    private static final String THIS_COMPONENT_NAME = RESTTemplate.class.getName();   
    private static final String CHARSET = "UTF-8";

    public RESTTemplate() {
    }

    private void openConnection(String urlString) throws IOException {
        this.omapper = new ObjectMapper();
        this.omapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        url = new URL(urlString);
        this.conn = (HttpURLConnection) url.openConnection();
    }

    private void closeConnection() {
        this.conn.disconnect();
    }

    private void logErr(String methodName, Exception e) {
        Log.writeLog(THIS_COMPONENT_NAME, String.format("### LOI KHI THUC THI %s trong %s",
                new Object[]{methodName, THIS_COMPONENT_NAME}));        
    }

    private void setHeader(Boolean doOuput, String requestMethod, RequestTypeEnum requestType) throws ProtocolException {
        this.conn.setDoOutput(doOuput);
        this.conn.setRequestMethod(requestMethod);
        this.conn.setRequestProperty("Content-Type", requestType.toString());
        this.conn.setRequestProperty("Accept-Charset", CHARSET);
//        String encoded = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(CHARSET));  //Java 8
//        this.conn.setRequestProperty("Authorization", "Basic " + encoded);
    }

    public String excute(String urlString, String requestMethod, RequestTypeEnum requestType, Object request) throws Exception {

        openConnection(urlString);
        try {
            setHeader(true, requestMethod, requestType);

            String input = this.omapper.writeValueAsString(request);
            System.out.println("Input.... : " + input);
            OutputStream os = this.conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(this.conn.getInputStream(), CHARSET));
            StringBuilder sb = new StringBuilder();
            String output;
            System.out.println("Output from IIB .... \n");
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            System.out.println("Output .... : " + sb.toString());
            return sb.toString();
        } catch (Exception e) {
            logErr("excute", e);
            throw e;
        } finally {
            closeConnection();
        }
    }

    public <T> T excute(String urlString, String requestMethod, RequestTypeEnum requestType, Object request, Class<T> responseClass) throws Exception {

        openConnection(urlString);
        try {
            setHeader(true, requestMethod, requestType);

            String input = this.omapper.writeValueAsString(request);
            System.out.println("Input.... : " + input);
            OutputStream os = this.conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(this.conn.getInputStream(), CHARSET));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            System.out.println("Output .... : " + sb.toString());
            return this.omapper.readValue(sb.toString(), responseClass);
        } catch (Exception e) {
            logErr("excute", e);
            throw e;
        } finally {
            closeConnection();
        }
    }

    public <T> T excute(String urlString, RequestMethod requestMethod, RequestTypeEnum requestType, Object request, Class<T> responseClass) throws Exception {

        openConnection(urlString);
        try {
            setHeader(true, requestMethod.toString(), requestType);

            String input = this.omapper.writeValueAsString(request);
            System.out.println("Input.... : " + input);
            OutputStream os = this.conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(this.conn.getInputStream(), CHARSET));
            StringBuilder sb = new StringBuilder();
            String output;
            System.out.println("Output from IIB .... \n");
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            System.out.println("Output .... : " + sb.toString());
            return this.omapper.readValue(sb.toString(), responseClass);
        } catch (Exception e) {
            logErr("excute", e);
            throw e;
        } finally {
            closeConnection();
        }
    }

    public <T> T post(String urlString, RequestTypeEnum requestType, Object request, Class<T> responseClass) throws IOException {

        openConnection(urlString);
        try {
            setHeader(true, RequestMethod.POST.toString(), requestType);

            String input = this.omapper.writeValueAsString(request);
            System.out.println("Input.... : " + input);
            OutputStream os = this.conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(this.conn.getInputStream(), CHARSET));
            StringBuilder sb = new StringBuilder();
            String output;
            System.out.println("Output from IIB .... \n");
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            System.out.println("Output .... : " + sb.toString());
            return this.omapper.readValue(sb.toString(), responseClass);
        } catch (IOException e) {
            logErr("excute", e);
            throw e;
        } finally {
            closeConnection();
        }
    }

    public <T> T get(String urlString, RequestTypeEnum requestType, Object request, Class<T> responseClass) throws Exception {

        openConnection(urlString);
        try {
            setHeader(true, RequestMethod.GET.toString(), requestType);

            String input = this.omapper.writeValueAsString(request);
            System.out.println("Input.... : " + input);
            OutputStream os = this.conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(this.conn.getInputStream(), CHARSET));
            StringBuilder sb = new StringBuilder();
            String output;
            System.out.println("Output from IIB .... \n");
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            System.out.println("Output .... : " + sb.toString());
            return this.omapper.readValue(sb.toString(), responseClass);
        } catch (Exception e) {
            logErr("excute", e);
            throw e;
        } finally {
            closeConnection();
        }
    }

    public <T> T postForObject(String serviceName, String operationName,String restUrl, String authUser, String authPassword, Object request, Class<T> responseClass) throws JsonProcessingException, IOException {
                    
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
        headers.setContentType(mediaType);
        //List<Charset> acceptableCharsets = new ArrayList<>();
        //acceptableCharsets.add(Charset.forName(CHARSET));
        //headers.setAcceptCharset(acceptableCharsets);

        String auth = authUser + ":" + authPassword;
        byte[] encodedAuth = org.apache.commons.codec.binary.Base64.encodeBase64String(auth.getBytes(Charset.forName("US-ASCII"))).getBytes();
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);


        ObjectMapper objectMapper = new ObjectMapper();
        String input = objectMapper.writeValueAsString(request);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        //HttpEntity<String> entity = new HttpEntity<>(input, headers);
        //String entity = headers;
        System.out.println("[" + sdf.format(new Date()) + "]----Rest IIB Input: " + input);
        Long startDate = System.currentTimeMillis();
        String output = restTemplate.postForObject(restUrl, headers, String.class);
        Long endDate = System.currentTimeMillis();
        System.out.println("[" + sdf.format(new Date()) + "][" + (endDate - startDate) + " milliseconds]----Rest IIB output: " + output);
        return objectMapper.readValue(output, responseClass);
    }
}
