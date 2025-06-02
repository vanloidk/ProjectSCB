/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import scb.com.vn.utility.Configuration;
import ws.internal.payment.vnpay.navigation.*;

/**
 *
 * @author FICOMBANK
 */
public class CollatedPayment {

    String _idpartner;

    /**
     *
     * @param idpartner
     */
    public CollatedPayment(String idpartner) {
        this._idpartner = idpartner;
    }

    /**
     *
     * @param filename
     * @return
     */
    public String requestUploadFileToVNPAY(String filename) {
        try {
            if (this._idpartner.equals("VNPAY")) {
                return getStub().requestUploadFileToVNPAY(filename);
            } else {
                return null;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(CollatedPayment.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param filename
     * @return
     */
    public String requestDownloadFileToVNPAY(String filename) {
        try {
            if (this._idpartner.equals("VNPAY")) {
                return getStub().requestDownloadFileToVNPAY(filename);
            } else {
                return null;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(CollatedPayment.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     *
     * @param filename
     * @return
     */
    public String requestUploadFileToPartner(String filename) {
        try {
            if (this._idpartner.equals("ONEPAY")) {
                return getStubOnePay().requestUploadFileToPartner(filename);
            } else if (this._idpartner.equals("SML")) {
                return getStubSmartlink().requestUploadFileToPartner(filename);
            } else {
                return null;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(CollatedPayment.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param filename
     * @return
     */
    public String requestDownloadFileFromPartner(String filename) {
        try {
            if (this._idpartner.equals("ONEPAY")) {
                return getStubOnePay().requestDownloadFileFromPartner(filename);
            } else if (this._idpartner.equals("SML")) {
                return getStubSmartlink().requestDownloadFileFromPartner(filename);
            } else {
                return null;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(CollatedPayment.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private ws.internal.payment.smartlink.WSBeanSoapBindingStub getStubSmartlink() {
        ws.internal.payment.smartlink.WSBeanServiceLocator wssl = new ws.internal.payment.smartlink.WSBeanServiceLocator();
        wssl.setWSBeanEndpointAddress(Configuration.getProperty("ws.url.smartlink.address"));

        ws.internal.payment.smartlink.WSBeanSoapBindingStub wss;
        try {
            wss = (ws.internal.payment.smartlink.WSBeanSoapBindingStub) wssl.getWSBean();
            wss.setTimeout(50000);
            return wss;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ws.internal.payment.onepay.WSBeanSoapBindingStub getStubOnePay() {
        ws.internal.payment.onepay.WSBeanServiceLocator wssl = new ws.internal.payment.onepay.WSBeanServiceLocator();
        wssl.setWSBeanEndpointAddress(Configuration.getProperty("ws.url.onepay.address"));

        ws.internal.payment.onepay.WSBeanSoapBindingStub wss;
        try {
            wss = (ws.internal.payment.onepay.WSBeanSoapBindingStub) wssl.getWSBean();
            wss.setTimeout(50000);
            return wss;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private WSBeanSoapBindingStub getStub() {
        WSBeanServiceLocator wssl = new WSBeanServiceLocator();
        wssl.setWSBeanEndpointAddress(Configuration.getProperty("ws.url.vnpay.address"));

        WSBeanSoapBindingStub wss;
        try {
            wss = (WSBeanSoapBindingStub) wssl.getWSBean();
            wss.setTimeout(50000);
            return wss;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
