package scb.com.vn.controller;

import ws.internal.sms_tnb.org.tempuri.ReceiveMessgeLocator;
import ws.internal.sms_tnb.org.tempuri.ReceiveMessgeSoapStub;

/**
 *
 * @author minhndb
 */
public class Sendmsgtotnb {

    /**
     *
     * @param mobile
     * @param msgcontents
     * @return
     */
    public String sendsmstotnb(String mobile, String msgcontents) {
        String _mobile = mobile, _msgcontents = msgcontents;
        String sReturn = "-1";

        if (mobile != null) {
            _mobile = "0" + _mobile.substring(2).trim();
        }

        if (_msgcontents != null) {
            _msgcontents = _msgcontents.trim();
            if (_msgcontents.length() > 3) {
                // Cat bo 3 ky tu dau SCB
                _msgcontents = _msgcontents.substring(3).trim();
            }
        }

        // Gui cho WS TNB
        ReceiveMessgeLocator rml = new ReceiveMessgeLocator();
        ReceiveMessgeSoapStub stub;

        try {
            stub = (ReceiveMessgeSoapStub) rml.getReceiveMessgeSoap();

            sReturn = stub.messageReceiver(_mobile, "1900555536", _msgcontents, "", "fptuser", "fptuser2009");

        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }

        System.out.println("[WS-sendmsgtotnb]-[mobile/msg]-[" + _mobile + "/" + _msgcontents + "]" + "Return:" + sReturn);
        return sReturn;
    }
}
