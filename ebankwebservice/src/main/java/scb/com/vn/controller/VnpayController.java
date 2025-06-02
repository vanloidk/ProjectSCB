/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import com.google.gson.Gson;
import com.visa.utils.MethodTypes;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.vnpayqr.CheckQRRp;
import scb.com.vn.common.model.vnpayqr.CheckQRRq;
import scb.com.vn.common.model.vnpayqr.PaymentQRJson;
import scb.com.vn.common.model.vnpayqr.PaymentQRRp;
import scb.com.vn.common.model.vnpayqr.PaymentQRRq;
import scb.com.vn.common.model.vnpayqr.RefundQRJson;
import scb.com.vn.common.model.vnpayqr.RefundQRRp;
import scb.com.vn.message.CommonMessage;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.VnpayUtils;

/**
 *
 * @author minhndb
 */
public class VnpayController {

    final static Logger LOGGER = Logger.getLogger(VnpayController.class);
    final String vnpayqrUrlCheckQR = Configuration.getProperty("vnpayQR.url.checkQR");
    final String vnpayqrUrlPaymentQR = Configuration.getProperty("vnpayQR.url.paymentQR");
    final String vnpayqrUrlRefundQR = Configuration.getProperty("vnpayQR.url.refundQR");
    final String vnpayqrHost = Configuration.getProperty("vnpayQR.host");
    final String vnpayqrPort = Configuration.getProperty("vnpayQR.port");
    final int vnpayqrTimeout = Integer.parseInt(Configuration.getProperty("vnpayQR.timeout"));
    final String productTransfer = Configuration.getProperty("fcubs.producttransfer.onlinetransfer");

    private CloseableHttpClient mutualAuthHttpClient;

    private CloseableHttpClient fetchMutualAuthHttpClient(String host, String port) throws KeyManagementException, UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        if (mutualAuthHttpClient == null) {
            HttpClientBuilder httpClient = HttpClients.custom().setSSLSocketFactory(getSSLSocketFactory());
            if (host != null && !host.isEmpty() && port != null && !port.isEmpty()) {
                HttpHost proxy = new HttpHost(host,
                         Integer.parseInt(port), "https");
                httpClient.setProxy(proxy);
            }
            mutualAuthHttpClient = httpClient.build();
        }
        return mutualAuthHttpClient;
    }

    private SSLConnectionSocketFactory getSSLSocketFactory() throws KeyManagementException, UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        SSLContextBuilder builder = SSLContexts.custom();
        builder.loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                return true;
            }
        });
        SSLContext sslContext = builder.build();
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            @Override
            public boolean verify(String string, SSLSession ssls) {
                return true;
            }
        };
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
                new String[]{"TLSv1.2"}, null, allHostsValid);
        return sslSocketFactory;
    }

    private HttpRequest createHttpRequest(MethodTypes methodType, String url) throws Exception {
        HttpRequest request = null;
        switch (methodType) {
            case GET:
                request = new HttpGet(url);
                break;
            case POST:
                request = new HttpPost(url);
                break;
            case PUT:
                request = new HttpPut(url);
                break;
            case DELETE:
                request = new HttpDelete(url);
                break;
            default:
                LOGGER.error("Incompatible HTTP request method " + methodType);
                break;
        }
        return request;
    }

    /**
     *
     * @param url
     * @param host
     * @param port
     * @param body
     * @return
     * @throws Exception
     */
    public CloseableHttpResponse doMutualAuthRequest(String url, String host, String port, String body) throws Exception {
        HttpRequest request = createHttpRequest(MethodTypes.POST, url);
        request.setHeader(HttpHeaders.CONTENT_TYPE, "Text/plain");
        ((HttpPost) request).setEntity(new StringEntity(body, "UTF-8"));
        RequestConfig params = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build();
        ((HttpPost) request).setConfig(params);
        CloseableHttpResponse response = fetchMutualAuthHttpClient(host, port).execute((HttpUriRequest) request);
        return response;
    }

    /**
     *
     * @param req
     * @return
     */
    public CheckQRRp checkVNPAYQRCode(CheckQRRq req) {
        Gson g = new Gson();
        CheckQRRp result;
        try {
            String body = req.exportCheckQrJson();
            LOGGER.info("request = [" + body + "]");
            CloseableHttpResponse vnpayResponse = doMutualAuthRequest(vnpayqrUrlCheckQR, vnpayqrHost, vnpayqrPort, body);
            String responseMessage = VnpayUtils.getResponseMessage(vnpayResponse);
            result = g.fromJson(responseMessage, CheckQRRp.class);
            result.setupBeforeReturn(req.getClientId(), req.getPayCode());
            boolean updateData = Helper.getDBI().UPDATECHECKQR(result);
            if (!updateData) {
                result = new CheckQRRp();
                result.setResCode("99");
                result.setResDesc("DATABASE IS ERROR");
            }
            return result;
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    private String callPaymentWithAccount(PaymentQRRq req, PaymentQRJson qrPayment) {
        try {
            String respCode;
            String transId = qrPayment.getOrderCode();
            String custAccount = qrPayment.getAccountNo();
            Double dblAmount = Double.valueOf(qrPayment.getRealAmount());
            String ccy = "VND";
            String dateTime = qrPayment.getPayDate();
            String description = req.getDescription();
            String merchantId = qrPayment.getMerchantId();
            String[] accVerify = Helper.getDBI().ONL_PAYMENTWITHACCOUNT(transId, custAccount, dblAmount,
                     ccy, dateTime, req.getPartner(), description, merchantId);
            if ("00".equals(accVerify[1])) {
                String parnertAccount = accVerify[0];
                String bankId = accVerify[2];
                Fcubs fc = new Fcubs();
                String coreRef = fc.transferFCUBSWithProductWithTimeOut(productTransfer, custAccount,
                         parnertAccount, BigDecimal.valueOf(dblAmount), description + "MAGD:" + qrPayment.getTraceTransfer(), vnpayqrTimeout);

                LOGGER.info("productTransfer = {" + productTransfer + "], custAccount = ["
                        + custAccount + "], parnertAccount = [" + parnertAccount
                        + "], dblAmount = [" + dblAmount + "], description = ["
                        + description + "], coreRef = [" + coreRef + "]");

                if (coreRef == null || coreRef.isEmpty()) {
                    respCode = CommonMessage.CommontEnum.INTERNALERROR.getValue();
                } else if ("TIMEOUT".equals(coreRef)) {
                    respCode = CommonMessage.CommontEnum.TIMEOUT.getValue();
                } else {
                    respCode = "00";
                }
                Helper.getDBI().ONL_UpdatePayment(bankId, respCode, coreRef);
            } else {
                respCode = accVerify[1];
            }
            return respCode;
        } catch (Exception e) {
            LOGGER.error("VnpayController --> callPaymentWithAccount --> " + e);
            return CommonMessage.CommontEnum.SYSTEM_IS_ERROR.getValue();
        }
    }

    private String callRefundPaymentWithAccount(PaymentQRRq req, PaymentQRJson qrPayment, PaymentQRRp vnPayResult) {
        try {
            Double dblAmount = Double.valueOf(qrPayment.getRealAmount());
            String transId = qrPayment.getTraceTransfer();
            String refTransId = qrPayment.getOrderCode();
            String dateTime = qrPayment.getPayDate();
            String description = "SCB HOAN TIEN THANH TOAN TRUC TUYEN QUA VNPAYQR. MAGD:" + qrPayment.getTraceTransfer();

            String[] result = Helper.getDBI().REFUND_PAYMENT_WITH_ACCOUNT(transId, refTransId,
                     dblAmount, dateTime, description + ". Code: " + vnPayResult.getResCode() + ". Desc: " + vnPayResult.getResDesc(), req.getPartner());
            String pStatus = result[0];
            String bankId;
            Helper.writeLog(this.getClass(), Level.INFO, "ONL_REFUND_PAYMENT_WITH_ACCOUNT DBI --> status = [" + pStatus + "]");
            if ("00".equals(pStatus)) {
                String custAccount = result[1];
                String partnerAccount = result[2];
                bankId = result[3];
                Fcubs fc = new Fcubs();
                String coreRef = fc.transferFCUBSWithProduct(productTransfer, partnerAccount, custAccount,
                         BigDecimal.valueOf(dblAmount), description);
                Helper.writeLog(this.getClass(), Level.INFO, "CoreRef = [" + coreRef + "]");
                if (coreRef != null && !coreRef.isEmpty()) {
                    Helper.getDBI().ONL_UPDATE_REFUND(bankId, coreRef);
                } else {
                    pStatus = "04";
                }
            }
            return pStatus;
        } catch (Exception e) {
            LOGGER.error("callRefundPaymentWithAccount --> " + e);
        }
        return null;
    }

    private String getDesciption(String respCode) {
        switch (respCode) {
            case "00":
                return "GIAO DICH THANH TOAN TRU TIEN THANH CONG";
            case "01":
                return "GIAO DICH THANH TOAN TRU TIEN THAT BAI";
            case "06":
                return "SO TIEN GIAO DICH NHO HON HAN MUC TOI THIEU TRONG 1 LAN GIAO DICH";
            case "07":
                return "SO TIEN GIAO DICH VUOT HAN MUC TOI DA TREN 1 LAN GIAO DICH";
            case "08":
                return "SO TIEN GIAO DICH VUOT HAN MUC TOI DA TRONG 1 NGAY";
            case "09":
                return "SO LAN GIAO DICH LON HON SO LAN GIAO DICH CHO PHEP TRONG NGAY";
            case "10":
                return "TRANSID NAY DA DUOC THUC HIEN GIAO DICH TRONG QUA KHU";
            case "99":
                return "SYSTEM IS ERROR";
            default:
                return "UNKNOWN ERROR (" + respCode + ")";
        }
    }

    private PaymentQRRp callPayment(PaymentQRRq req, PaymentQRJson qrPayment) {
        Gson g = new Gson();
        PaymentQRRp result;
        try {
            String body = qrPayment.exportToJsonString();
            CloseableHttpResponse vnpayResponse = doMutualAuthRequest(vnpayqrUrlPaymentQR, vnpayqrHost, vnpayqrPort, body);
            String responseMessage = VnpayUtils.getResponseMessage(vnpayResponse);
            result = g.fromJson(responseMessage, PaymentQRRp.class);
            result.setupBeforeReturn(req.getClientId(), qrPayment);
        } catch (Exception e) {
            LOGGER.error("callPayment --> " + e);
            result = new PaymentQRRp();
            result.setResCode(CommonMessage.CommontEnum.TIMEOUT.getValue());
            result.setResDesc(CommonMessage.getCommontEnumDescription(CommonMessage.CommontEnum.TIMEOUT));
            result.setupBeforeReturn(req.getClientId(), qrPayment);
        }
        return result;
    }

    private RefundQRRp callRefund(RefundQRJson qrRefund) {
        Gson g = new Gson();
        RefundQRRp result;
        try {
            String body = qrRefund.exportToJsonString();
            CloseableHttpResponse vnpayResponse = doMutualAuthRequest(vnpayqrUrlRefundQR, vnpayqrHost, vnpayqrPort, body);
            String responseMessage = VnpayUtils.getResponseMessage(vnpayResponse);
            result = g.fromJson(responseMessage, RefundQRRp.class);
            return result;
        } catch (Exception e) {
            LOGGER.error("VnpayController --> callRefund --> " + e);
            return null;
        }
    }

    /**
     *
     * @param req
     * @param qrPayment
     * @param accessKey
     * @return
     */
    public PaymentQRRp paymentVNPAYQR(PaymentQRRq req, PaymentQRJson qrPayment, String accessKey) {
        try {
            String respCode = callPaymentWithAccount(req, qrPayment);
            qrPayment.preSetupData(respCode, getDesciption(respCode), accessKey);
            PaymentQRRp result = callPayment(req, qrPayment);
            boolean updateData = Helper.getDBI().UPDATEPAYMENTQR(qrPayment, result);
            if (!updateData) {
                LOGGER.warn("qrPayment = [" + qrPayment.exportToJsonString() + "], resCode = ["
                        + result.getResCode() + "], resDesc = [" + result.getResDesc() + "]");
            }

            if (result != null) {
                switch (result.getResCode()) {
                    case "01":
                    case "02":
                    case "03":
                    case "04":
                    case "05":
                    case "06":
                    case "07":
                    case "11":
                    case "12":
                    case "13":
                    case "14":
                    case "15":
                    case "16":
                    case "17":
                    case "18":
                    case "19":
                    case "20":
                    case "21":
                    case "23":
                    case "24":
                    case "25":
                    case "26":
                    case "27":
                    case "28":
                    case "29":
                    case "71":
                    case "76":
                    case "89":
                    case "96":
                    case "99":
                        RefundQRJson refundResult = Helper.getDBI().INSERTREFUNDQR(qrPayment);
                        String bankRefundCode = callRefundPaymentWithAccount(req, qrPayment, result);
                        refundResult.preSetupData(bankRefundCode, "", accessKey);
                        RefundQRRp resultCallRefund = callRefund(refundResult);
                        Helper.getDBI().UPDATEREFUNDQR(refundResult, resultCallRefund);
                        break;
                    /* Ko hoan tien doi voi 2 case 08 va 88 */
                    case "88":
                        result.setResCode(respCode);
                        break;
                    case "08":
                    case "00":
                    default:
                        break;
                }
            }
            return result;
        } catch (Exception e) {
            LOGGER.error("paymentVNPAYQR --> " + e);
            return null;
        }
    }
}
