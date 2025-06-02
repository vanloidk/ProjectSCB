/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.ws.gatewayscb;

/**
 *
 * @author kimncm
 */
public class SCBGW {
    
    
    public static Object[] callExecWs(ScbGWContext context, String methodName, Object[] objs) {
        try {
            WSBeanServiceLocator wssl = new WSBeanServiceLocator();
            wssl.setWSBeanEndpointAddress(context.getUrl());
            WSBeanSoapBindingStub wss = (WSBeanSoapBindingStub) wssl.getWSBean();
            int timeout = Integer.parseInt(context.getTimeout());
            wss.setTimeout(timeout);
            wss.setHeader(scb.com.vn.ultility.ws.Stub.setSoapHeaderElement(context.getAccessKey(), methodName, context.getSignature()));
            return wss.callExecution(methodName, context.getPartnerCode(), objs);
        } catch (Exception e) {
            //writeLog(Helper.class, e);
            throw null;
        }
    }

}
