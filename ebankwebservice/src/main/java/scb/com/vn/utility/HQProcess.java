/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import org.apache.axis.AxisFault;
import ws.internal.payment.tcs.TTDTPortalLocator;
import ws.internal.payment.tcs.TTDTPortalSoapStub;
import ws.internal.payment.tcs.dc.DCTTDTPortalStub;
import ws.internal.payment.tcs.dc.ReconcileProcess;
import ws.internal.payment.tcs.dc.ReconcileProcessResponse;

/**
 *
 * @author lydty
 */
public class HQProcess {

    static String HQAdress = Configuration.getProperty("ws.url.haiquan.address");
    static String HQAdress247 = Configuration.getProperty("ws.url.haiquan.address247");
    static String HQAdress247DC = Configuration.getProperty("ws.url.haiquan.address247DC");

    /**
     *
     * @return
     */
    public static String getPublicKey() {
        try {

            TTDTPortalLocator wssl = new TTDTPortalLocator();
            wssl.setTTDTPortalSoapEndpointAddress(HQAdress);
            TTDTPortalSoapStub wss = (TTDTPortalSoapStub) wssl.getTTDTPortalSoap();
            wss.setTimeout(40000);// 40giay
            return wss.getPublicKey();
        } catch (AxisFault af) {
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @param publicKey
     * @param xml
     * @return
     */
    public static String callWS(String publicKey, String xml) {
        try {
            TTDTPortalLocator wssl = new TTDTPortalLocator();
            wssl.setTTDTPortalSoapEndpointAddress(HQAdress);
            TTDTPortalSoapStub wss = (TTDTPortalSoapStub) wssl.getTTDTPortalSoap();
            wss.setTimeout(40000);// 40giay
            return wss.WSProcess(publicKey, xml);
        } catch (AxisFault af) {
            Helper.writeLogError(Helper.class, af.getMessage());
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                return null;
            }
        } catch (Exception e) {
            Helper.writeLogError(Helper.class, e.getMessage());
            return null;
        }
    }

    /**
     *
     * @param publicKey
     * @param xml
     * @return
     */
    public static String callWS247(String publicKey, String xml) {
        try {
            TTDTPortalLocator wssl = new TTDTPortalLocator();
            wssl.setTTDTPortalSoapEndpointAddress(HQAdress247);
            TTDTPortalSoapStub wss = (TTDTPortalSoapStub) wssl.getTTDTPortalSoap();
            wss.setTimeout(40000);// 40giay
            return wss.WSProcess(publicKey, xml);
        } catch (AxisFault af) {
            Helper.writeLogError(Helper.class, af.getMessage());
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                return null;
            }
        } catch (Exception e) {
            Helper.writeLogError(Helper.class, e.getMessage());
            return null;
        }
    }

    /**
     *
     * @param publicKey
     * @param xml
     * @return
     */
    public static String callWS247DC(String publicKey, String xml) {

        try {
            DCTTDTPortalStub stub = new DCTTDTPortalStub(HQAdress247DC);
            ReconcileProcess req = new ReconcileProcess();
            req.setPublicKey(publicKey);
            req.setMsg(xml);

            ReconcileProcessResponse response = stub.reconcileProcess(req);
            String result = response.getReconcileProcessResult();
            return result;
        } catch (AxisFault af) {
            Helper.writeLogError(Helper.class, af.getMessage());
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                return null;
            }
        } catch (Exception e) {
            Helper.writeLogError(Helper.class, e.getMessage());
            return null;
        }
    }

}
