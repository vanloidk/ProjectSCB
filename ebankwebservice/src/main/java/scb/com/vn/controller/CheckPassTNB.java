package scb.com.vn.controller;

import ws.internal.tnb.checkpass.tempuri.*;

/**
 *
 * @author minhndb
 */
public class CheckPassTNB {

    /**
     *
     * @param username
     * @param password
     * @param ip
     * @return
     */
    public boolean checkpassTNB(String username, String password, String ip) {
        ServiceLocator sl = new ServiceLocator();
        ServiceSoapStub stub;

        try {
//            sl.setServiceSoapEndpointAddress();
            stub = (ServiceSoapStub) sl.getServiceSoap();
            return stub.checkPass(username, password, "192.168.1.1");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
