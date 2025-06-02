package scb.com.vn.utility;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.rmi.Naming;
import java.security.PrivateKey;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.controller.IDBI;
import java.security.PublicKey;
import java.util.Iterator;
import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.stream.StreamSource;
import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.soap.MessageFactoryImpl;
import vn.com.sml.ibt.rsa.EncodingType;
import vn.com.sml.ibt.rsa.RSAAlgorithm;
import vn.com.sml.ibt.rsa.RSASignature;
import ws.internal.payment.smartlink.WSBeanSoapBindingStub;
import vn.com.tvsi.www.SCBWSLocator;
import vn.com.tvsi.www.SCBWSSoapStub;

/**
 *
 * @author minhndb
 */
public class Helper {

    private static final Logger logger = Logger.getLogger(Helper.class);

    //mac_vas_query_res / mac_query_res / mac_vas_pay_res / mac_transfer_res
    //mac_vas_query_req / mac_query_req / mac_vas_pay_req / mac_transfer_req
    private static final int[] mac_vas_query_res = {0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11};
    private static final int[] mac_query_res = {0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 13};
    private static final int[] mac_vas_pay_res = {0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11};
    private static final int[] mac_transfer_res = {0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 13};

    private static final int[] mac_vas_query_req = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10};
    private static final int[] mac_query_req = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 12};
    private static final int[] mac_vas_pay_req = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10};
    private static final int[] mac_transfer_req = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 12};

    private static final String urldbi = "rmi://"
            + Configuration.getProperty("rmi.registry.host") + ":"
            + Configuration.getProperty("rmi.registry.port") + "/"
            + Configuration.getProperty("rmi.registry.service");

    /**
     *
     * @return
     */
    public static IDBI getDBI() {
//        String urldbi = "rmi://"
//                + Configuration.getProperty("rmi.registry.host") + ":"
//                + Configuration.getProperty("rmi.registry.port") + "/"
//                + Configuration.getProperty("rmi.registry.service");
        try {
            IDBI idbi = (IDBI) Naming.lookup(urldbi);
            return idbi;

        } catch (Exception e) {
            writeLogError(Helper.class, e);
            return null;
        }
    }

    /**
     *
     * @return
     */
    public static IDBI getDBI_IBT() {
        String urldbi = "rmi://"
                + Configuration.getProperty("rmi_1141.registry.host") + ":"
                + Configuration.getProperty("rmi_1141.registry.port") + "/"
                + Configuration.getProperty("rmi_1141.registry.service");
        try {
            IDBI idbi = (IDBI) Naming.lookup(urldbi);
            return idbi;

        } catch (Exception e) {
            writeLogError(Helper.class, e);
            return null;
        }
    }

    private static boolean checkParam(Class<?>[] cTypes, Object[] o2) {
        if (cTypes == null) {
            return o2 == null || o2.length == 0;
        }
        if (o2 == null) {
            return (cTypes.length == 0);
        }
        if (cTypes.length != o2.length) {
            return false;
        }

        return true;
    }

    /**
     *
     * @param cTypes
     * @param o2
     * @return
     * @throws Exception
     */
    public static Object[] convertParasIn(Class<?>[] cTypes, Object[] o2) throws Exception {
        Object[] _o = o2;
        Object[] _oresult;

        //1. Kiem tra so luong
        if (checkParam(cTypes, _o) == false) {
            return null;
        }

        _oresult = new Object[cTypes.length];
        for (int i = 0; i < cTypes.length; i++) {

            if (_o[i].getClass().getName().equals("java.lang.Short") && (cTypes[i].getName().equals("short"))) {
                _oresult[i] = Short.parseShort(_o[i].toString());
            } else if (_o[i].getClass().getName().equals("java.lang.Integer") && (cTypes[i].getName().equals("int"))) {
                _oresult[i] = Integer.parseInt(_o[i].toString());
            } else if (_o[i].getClass().getName().equals("java.lang.Long") && (cTypes[i].getName().equals("long"))) {
                _oresult[i] = Long.parseLong(_o[i].toString());
            } else if (_o[i].getClass().getName().equals("java.lang.Float") && (cTypes[i].getName().equals("float"))) {
                _oresult[i] = Float.parseFloat(_o[i].toString());
            } else if (_o[i].getClass().getName().equals("java.lang.Double") && (cTypes[i].getName().equals("double"))) {
                _oresult[i] = Double.parseDouble(_o[i].toString());
            } else if (_o[i].getClass().getName().equals("java.lang.String") && (cTypes[i].getName().equals("java.lang.String"))) {
                _oresult[i] = _o[i].toString();
            } else if (_o[i].getClass().getName().equals("java.math.BigDecimal") && (cTypes[i].getName().equals("java.math.BigDecimal"))) {
                _oresult[i] = (BigDecimal) _o[i];
            } else if (_o[i].getClass().getName().equals("java.util.HashMap") && (cTypes[i].getName().equals("java.util.HashMap"))) {
                _oresult[i] = (HashMap) _o[i];
            } else {
                return null;
            }

//            if (_o[i].getClass().getName().equals("java.lang.Short") || _o[i].getClass().getName().equals("java.lang.Integer")
//                    || _o[i].getClass().getName().equals("java.lang.Long") || _o[i].getClass().getName().equals("java.lang.Float")
//                    || _o[i].getClass().getName().equals("java.lang.Double")) {
//
//                if (cTypes[i].getName().equals("short")) {
//                    _o[i] = Short.parseShort(_o[i].toString());
//                } else if (cTypes[i].getName().equals("int")) {
//                    _o[i] = Integer.parseInt(_o[i].toString());
//                } else if (cTypes[i].getName().equals("long")) {
//                    _o[i] = Long.parseLong(_o[i].toString());
//                } else if (cTypes[i].getName().equals("float")) {
//                    _o[i] = Float.parseFloat(_o[i].toString());
//                } else if (cTypes[i].getName().equals("double")) {
//                    _o[i] = Double.parseDouble(_o[i].toString());
//                }
//            } // if
        } // for
        return _oresult;
    }

    /**
     *
     * @param clsname
     * @param methodname
     * @param arrParams
     * @return
     * @throws Exception
     */
    public static Object[] convertParasIn(String clsname, String methodname, Object[] arrParams) throws Exception {
        Class<?> cls = Class.forName(clsname);
        Method[] m1 = cls.getDeclaredMethods();

        for (int i = 0; i < m1.length; i++) {
            if (m1[i].getName().equals(methodname)) {
                return Helper.convertParasIn(m1[i].getParameterTypes(), arrParams);
            }
        }
        return null;

    }

    /**
     *
     * @param arrParams
     * @return
     * @throws Exception
     */
    public static Class<?>[] convertParamsTypeToPrimitive(Object[] arrParams) throws Exception {
        Class<?>[] paramsTypePrimitive = null;
        Object[] _o = null;

        // khong co tham so thi object obj[0]=null
        _o = (arrParams == null) ? (new Object[0]) : arrParams;
        paramsTypePrimitive = new Class[_o.length];

        for (int i = 0; i < _o.length; i++) {
            paramsTypePrimitive[i] = nameToPrimitiveClass(_o[i]);// lay thong tin kieu cua tham so goi
        }

        return paramsTypePrimitive;
    }

    private static Class<?> nameToPrimitiveClass(Object objParaTypes) {
        if (objParaTypes == null) {
            Object o = new Object();
            return o.getClass();
        }

        if (objParaTypes.getClass().getName().equals("java.lang.Boolean")) {
            return java.lang.Boolean.TYPE;
        } else if (objParaTypes.getClass().getName().equals("java.lang.Byte")) {
            return java.lang.Byte.TYPE;
        } else if (objParaTypes.getClass().getName().equals("java.lang.Character")) {
            return java.lang.Character.TYPE;
        } else if (objParaTypes.getClass().getName().equals("java.lang.Short")) {
            return java.lang.Short.TYPE;
        } else if (objParaTypes.getClass().getName().equals("java.lang.Integer")) {
            return java.lang.Integer.TYPE;
        } else if (objParaTypes.getClass().getName().equals("java.lang.Long")) {
            return java.lang.Long.TYPE;
        } else if (objParaTypes.getClass().getName().equals("java.lang.Float")) {
            return java.lang.Float.TYPE;
        } else if (objParaTypes.getClass().getName().equals("java.lang.Double")) {
            return java.lang.Double.TYPE;
        } else {
            return objParaTypes.getClass();
        }
    }

    /**
     *
     * @param arrObjParas
     * @return
     */
    public static String getDescriptionParam(Object[] arrObjParas) {
        String _s = "";
        for (Object o : arrObjParas) {
            if (o == null) {
                _s += "null='null', ";
                continue;
            }
            _s += o.getClass() + "='" + o.toString() + "', ";
        }

        _s = (_s.length() > 2) ? _s.substring(0, _s.length() - 2) : "";
        return _s;
    }

    /**
     *
     * @param clazz
     * @param lvl
     * @param msg
     * @return
     */
    public static int writeLog(Class<?> clazz, Level lvl, String msg) {
        Logger.getLogger(clazz).info(msg);

        //HieuDT: Remove logging to Database, just logging to log file.
        /*
        try {
            Helper.getDBI().insertLog(clazz.getName(), "INFO", msg);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
         */
        return 1;
    }

    /**
     *
     * @param clazz
     * @param msg
     */
    public static void writeLogError(Class<?> clazz, Object msg) {
        Logger.getLogger(clazz).error(msg);

        //HieuDT: Remove logging to Database, just logging to log file.
        /*
        try {
            Helper.getDBI().insertLog(clazz.getName(), "ERROR", msg.toString());
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
         */
    }

    /**
     *
     * @param clazz
     * @param msg
     */
    public static void writeLogErrorNonDB(Class<?> clazz, String msg) {
        Logger.getLogger(clazz).error(msg);
    }

    //SML support
    /**
     * create signature to call web service of partner
     * @param output
     * @param prvKey
     * @return 
     * @throws java.lang.Exception 
     */
    public static String createRequestSignature(String output, PrivateKey prvKey) throws Exception {
//        if (output != null && output.length() > 0 && prvKey != null) {
//            StringBuilder sb = new StringBuilder();
//            String[] aOutput = output.split("\\|");
//            if (aOutput[2].equals("430000")) {
//                for (int i = 0; i < mac_query_req.length; i++) {
//                    sb.append(aOutput[mac_query_req[i]]);
//                }
//            } else if (aOutput[2].equals("050000")) {
//                for (int i = 0; i < mac_vas_query_req.length; i++) {
//                    sb.append(aOutput[mac_vas_query_req[i]]);
//                }
//            } else if (aOutput[2].equals("910000")) {
//                for (int i = 0; i < mac_transfer_req.length; i++) {
//                    sb.append(aOutput[mac_transfer_req[i]]);
//                }
//            } else if (aOutput[2].equals("060000")) {
//                for (int i = 0; i < mac_vas_pay_req.length; i++) {
//                    sb.append(aOutput[mac_vas_pay_req[i]]);
//                }
//            } else if (aOutput[2].equals("432020")) {
//                for (int i = 0; i < mac_vas_pay_req.length; i++) {
//                    sb.append(aOutput[mac_vas_pay_req[i]]);
//                }
//            } else if (aOutput[2].equals("432020")) {
//                for (int i = 0; i < mac_vas_pay_req.length; i++) {
//                    sb.append(aOutput[mac_vas_pay_req[i]]);
//                }
//            } else if (aOutput[2].equals("430020")) {
//                for (int i = 0; i < mac_vas_pay_req.length; i++) {
//                    sb.append(aOutput[mac_vas_pay_req[i]]);
//                }
//            } else if (aOutput[2].equals("432000")) {
//                for (int i = 0; i < mac_vas_pay_req.length; i++) {
//                    sb.append(aOutput[mac_vas_pay_req[i]]);
//                }                
//            } else {
//                throw new Exception("Invalid processing code: " + aOutput[2]);
//            }
        System.out.println("strOutput : = " + output);
        String signature = RSASignature.makeSignature(output, prvKey, RSAAlgorithm.MD5RSA, EncodingType.BASE64);
        return signature;

//        } else {
//            return null;
//        }
    }

    /**
     * Verify response
     * @param aInput
     * @param pubKey
     * @return 
     * @throws java.lang.Exception 
     */
    public static boolean verifyResponseSignature(String[] aInput, PublicKey pubKey) throws Exception {
        if (aInput != null && aInput.length > 2 && pubKey != null) {
            StringBuilder sb = new StringBuilder();
            String signature = aInput[aInput.length - 1];
            if (aInput[2].equals("432000")) {
                for (int i = 0; i < mac_vas_query_res.length; i++) {
                    sb.append(aInput[mac_vas_query_res[i]]);
                }
            } else if (aInput[2].equals("430000")) {
                for (int i = 0; i < mac_vas_query_res.length; i++) {
                    sb.append(aInput[mac_vas_query_res[i]]);
                }
            } else if (aInput[2].equals("432020")) {
                for (int i = 0; i < mac_query_res.length; i++) {
                    sb.append(aInput[mac_query_res[i]]);
                }
            } else if (aInput[2].equals("912000")) {
                for (int i = 0; i < mac_vas_pay_res.length; i++) {
                    sb.append(aInput[mac_vas_pay_res[i]]);
                }
            } else if (aInput[2].equals("910000")) {
                for (int i = 0; i < mac_vas_pay_res.length; i++) {
                    sb.append(aInput[mac_vas_pay_res[i]]);
                }
            } else if (aInput[2].equals("912020")) {
                for (int i = 0; i < mac_transfer_res.length; i++) {
                    sb.append(aInput[mac_transfer_res[i]]);
                }

            } else {
                throw new Exception("Invalid processing code: " + aInput[2]);
            }
            System.out.println("verify signature -- data = " + sb.toString());
            System.out.println("verify signature -- signature = " + signature);
            return RSASignature.verifySignature(pubKey, sb.toString(), signature, RSAAlgorithm.MD5RSA, EncodingType.BASE64);
        } else {
            return false;
        }
    }

    /**
     * Verify request
     * @param aInput
     * @param pubKey
     * @return 
     * @throws java.lang.Exception
     */
    public static boolean verifyRequestSignature(String[] aInput, PublicKey pubKey) throws Exception {
        if (aInput != null && aInput.length > 2 && pubKey != null) {
            StringBuilder sb = new StringBuilder();
            String signature = aInput[aInput.length - 1];
            if (aInput[2].equals("430000")) {
                for (int i = 0; i < mac_vas_query_req.length; i++) {
                    sb.append(aInput[mac_vas_query_req[i]]);
                }
            } else if (aInput[2].equals("430020")) {
                for (int i = 0; i < mac_query_req.length; i++) {
                    sb.append(aInput[mac_query_req[i]]);
                }
            } else if (aInput[2].equals("432000")) {
                for (int i = 0; i < mac_vas_query_req.length; i++) {
                    sb.append(aInput[mac_vas_query_req[i]]);
                }
            } else if (aInput[2].equals("432020")) {
                for (int i = 0; i < mac_query_req.length; i++) {
                    sb.append(aInput[mac_query_req[i]]);
                }
            } else if (aInput[2].equals("910000")) {
                for (int i = 0; i < mac_vas_pay_req.length; i++) {
                    sb.append(aInput[mac_vas_pay_req[i]]);
                }
            } else if (aInput[2].equals("910020")) {
                for (int i = 0; i < mac_transfer_req.length; i++) {
                    sb.append(aInput[mac_transfer_req[i]]);
                }
            } else if (aInput[2].equals("912000")) {
                for (int i = 0; i < mac_vas_pay_req.length; i++) {
                    sb.append(aInput[mac_vas_pay_req[i]]);
                }
            } else if (aInput[2].equals("912020")) {
                for (int i = 0; i < mac_transfer_req.length; i++) {
                    sb.append(aInput[mac_transfer_req[i]]);
                }
            } else {
                throw new Exception("Invalid processing code: " + aInput[2]);
            }
            System.out.println("verify signature -- data = " + sb.toString());
            System.out.println("verify signature -- signature = " + signature);
            return RSASignature.verifySignature(pubKey, sb.toString(), signature, RSAAlgorithm.MD5RSA, EncodingType.BASE64);
        } else {
            return false;
        }
    }

    /**
     * create signature to call web service of partner
     * @param output
     * @param prvKey
     * @return 
     * @throws java.lang.Exception
     */
    public static String createResponseSignature(String output, PrivateKey prvKey) throws Exception {
        if (output != null && output.length() > 0 && prvKey != null) {
            StringBuilder sb = new StringBuilder();
            String[] aOutput = output.split("\\|");
            if (aOutput[2].equals("430000")) {
                for (int i = 0; i < mac_vas_query_res.length; i++) {
                    sb.append(aOutput[mac_vas_query_res[i]]);
                }
            } else if (aOutput[2].equals("430020")) {
                for (int i = 0; i < mac_query_res.length; i++) {
                    sb.append(aOutput[mac_query_res[i]]);
                }
            } else if (aOutput[2].equals("432000")) {
                for (int i = 0; i < mac_vas_query_res.length; i++) {
                    sb.append(aOutput[mac_vas_query_res[i]]);
                }
            } else if (aOutput[2].equals("432020")) {
                for (int i = 0; i < mac_query_res.length; i++) {
                    sb.append(aOutput[mac_query_res[i]]);
                }
            } else if (aOutput[2].equals("910000")) {
                for (int i = 0; i < mac_vas_pay_res.length; i++) {
                    sb.append(aOutput[mac_vas_pay_res[i]]);
                }
            } else if (aOutput[2].equals("910020")) {
                for (int i = 0; i < mac_transfer_res.length; i++) {
                    sb.append(aOutput[mac_transfer_res[i]]);
                }
            } else if (aOutput[2].equals("912000")) {
                for (int i = 0; i < mac_vas_pay_res.length; i++) {
                    sb.append(aOutput[mac_vas_pay_res[i]]);
                }
            } else if (aOutput[2].equals("912020")) {
                for (int i = 0; i < mac_transfer_res.length; i++) {
                    sb.append(aOutput[mac_transfer_res[i]]);
                }
            } else {
                throw new Exception("Invalid processing code: " + aOutput[2]);
            }
            System.out.println("strOutput : = " + sb.toString());
            String signature = RSASignature.makeSignature(sb.toString(), prvKey, RSAAlgorithm.MD5RSA, EncodingType.BASE64);
            return signature;

        } else {
            return null;
        }
    }

    private static String addExtraSpace(String str, int maxlength) {
        return addExtraChar(str, maxlength, " ");
    }

    /**
     *
     * @param str
     * @param maxlength
     * @param chartoadd
     * @return
     */
    public static String addExtraChar(String str, int maxlength, String chartoadd) {
        String newstr = "";
        for (int j = 0; j < (maxlength - str.length()); j++) {
            newstr += chartoadd;
        }
        return newstr + str;
    }

    /**
     *
     * @param param
     * @param timeout
     * @return
     */
    public static String callwsSmartlink(String param, int timeout) {

        String result = null;
        try {

            ws.internal.payment.smartlink.WSBeanServiceLocator wssl = new ws.internal.payment.smartlink.WSBeanServiceLocator();
            wssl.setWSBeanEndpointAddress(Configuration.getProperty("ws.url.smartlink.address_scbibt"));
            WSBeanSoapBindingStub wss = (ws.internal.payment.smartlink.WSBeanSoapBindingStub) wssl.getWSBean();
            wss.setTimeout(timeout * 1000);
            return wss.callwsSmartlink(param);
        } catch (AxisFault af) {
            Helper.writeLogErrorNonDB(Helper.class, af.getMessage());
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                result = "TIMEOUT";
            }
        } catch (Exception e) {
            Helper.writeLogErrorNonDB(Helper.class, e.getMessage());
        }
        return result;
    }

    //End of SML support
    //confirm to TVSI

    /**
     *
     * @param SCBRef
     * @param partnerAccount
     * @param customerAccount
     * @param customerName
     * @param amount
     * @param ccy
     * @param channelID
     * @param transDate
     * @param addInfo
     * @param providerID
     * @param signatureSCB
     * @return
     */
    public static String callwsTVSI(java.lang.String SCBRef,
            java.lang.String partnerAccount,
            java.lang.String customerAccount,
            java.lang.String customerName,
            java.math.BigDecimal amount,
            java.lang.String ccy,
            java.lang.String channelID,
            java.lang.String transDate,
            java.lang.String addInfo,
            java.lang.String providerID,
            java.lang.String signatureSCB) {
        try {

            SCBWSLocator wssl = new SCBWSLocator();
            wssl.setSCBWSSoapEndpointAddress(Configuration.getProperty("ws.url.tvsi.address"));
            SCBWSSoapStub wss = (SCBWSSoapStub) wssl.getSCBWSSoap();
            wss.setTimeout(10000);// 40giay
            return wss.SCBRequest(SCBRef, partnerAccount, customerAccount, customerName, amount, ccy, channelID, transDate, addInfo, providerID, signatureSCB);
        } catch (AxisFault af) {
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                Helper.writeLog(Helper.class, org.apache.log4j.Level.INFO, "====================================================== TVSI WS: " + af.getFaultString());
            }
            return null;
        } catch (Exception e) {

            Helper.writeLogErrorNonDB(Helper.class, e.getMessage());
            return null;
        }
    }

    /**
     *
     * @param param
     * @param timeout
     * @return
     */
    public static String callwsIBTNAPAS(String param, int timeout) {
        try {
            ws.internal.vas.com.sml.VASLocator wssl = new ws.internal.vas.com.sml.VASLocator();
            wssl.setVASHttpSoap12EndpointEndpointAddress(Configuration.getProperty("ws.url.smartlink.address"));
            ws.internal.vas.com.sml.VASSoap12BindingStub wss = (ws.internal.vas.com.sml.VASSoap12BindingStub) wssl.getVASHttpSoap12Endpoint();
            wss.setTimeout(timeout * 1000);
            return wss.execute(param);
        } catch (AxisFault af) {
            Helper.writeLogErrorNonDB(Helper.class, "[AxisFault]ibt call napas:" + af.getMessage());
            /*
            if (af.getFaultString().contains("com.sun.xml.stream.xerces.xni.XNIException: a pseudo attribute name is expected.") || af.getFaultString().contains("java.lang.IllegalStateException: Method getLocalName() cannot be called for START_DOCUMENT event.") || af.getFaultString().contains("javax.xml.stream.XMLStreamException: ParseError at [row,col]:[4,24]\n"
                    + "Message: XML document structures must start and end within the same entity.")) {
                return "REVERT";
            }
            if (af.getFaultString().contains("javax.xml.stream.XMLStreamException: ParseError at [row,col]")
                    & af.getFaultString().contains("Message: XML document structures must start and end within the same entity.")) {
                return "REVERT";
            }
            if (af.getFaultString().contains("(404)Not Found") || af.getFaultString().contains("Connection refused: connect")) {
                return "REVERT";
            }
            return af.getFaultString().contains("java.net.SocketTimeoutException") ? "TIMEOUT" : null;
            */
            return null;
        } catch (Exception e) {
            Helper.writeLogErrorNonDB(Helper.class, "[Exception] ibt call napas:" + e.getMessage());
            return null;
        }
    }

    /**
     *
     * @param param
     * @param timeout
     * @param trace
     * @return
     */
    public static String callwsIBTNAPASwithTrace(String param, int timeout, String trace) {
        try {
            ws.internal.vas.com.sml.VASLocator wssl = new ws.internal.vas.com.sml.VASLocator();
            wssl.setVASHttpSoap12EndpointEndpointAddress(Configuration.getProperty("ws.url.smartlink.address"));
            ws.internal.vas.com.sml.VASSoap12BindingStub wss = (ws.internal.vas.com.sml.VASSoap12BindingStub) wssl.getVASHttpSoap12Endpoint();
            wss.setTimeout(timeout * 1000);
            return wss.execute(param);
        } catch (AxisFault af) {
            Helper.writeLogErrorNonDB(Helper.class, "[AxisFault]ibt call napas [" + trace + "]:" + af.getMessage());
            /*
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                return "TIMEOUT";
            }
            if (af.getFaultString().contains("com.sun.xml.stream.xerces.xni.XNIException: a pseudo attribute name is expected.")
                    || af.getFaultString().contains("Method getLocalName() cannot be called for START_DOCUMENT event.")
                    || af.getFaultString().contains("javax.xml.stream.XMLStreamException: ParseError at [row,col]:[4,24]\n"
                            + "Message: XML document structures must start and end within the same entity.")) {
                return "REVERT";
            }
            if (af.getFaultString().contains("javax.xml.stream.XMLStreamException: ParseError at [row,col]")
                    & af.getFaultString().contains("Message: XML document structures must start and end within the same entity.")) {
                return "REVERT";
            }
            if (af.getFaultString().contains("Current state START_DOCUMENT is not among the states START_ELEMENT, END_ELEMENT, UNKNOWN_EVENT_TYPE")) {
                return "REVERT";
            }
            if (af.getFaultString().contains("com.sun.xml.stream.xerces.xni.XNIException: The ' = ' character must follow")) {
                return "REVERT";
            }
            if (af.getFaultString().contains("White spaces are required between publicId and systemId.")) {
                return "REVERT";
            }
            if (af.getFaultString().contains("java.util.MissingResourceException: CloseQuoteMissingInXMLDecl")) {
                return "REVERT";
            }
            if (af.getFaultString().contains("Exception occurred while trying to invoke service method execute")) {
                return "REVERT";
            }
            if (af.getFaultString().contains("SOAP Envelope can not have children other than SOAP Header and Body")) {
                return "REVERT";
            }
            if (af.getFaultString().contains("Content is not allowed in prolog")) {
                return "REVERT";
            }
            if (af.getFaultString().contains("org.xml.sax.SAXException: Bad envelope tag:  html")) {
                return "REVERT";
            }
            if (af.getFaultString().contains("(404)Not Found") || af.getFaultString().contains("Connection refused: connect")) {
                return "REVERT";
            }
            */
            return null;
        } catch (Exception e) {
            Helper.writeLogErrorNonDB(Helper.class, "[Exception] ibt call napas[" + trace + "]:" + e.getMessage());
            return null;
        }
    }

    /**
     *
     * @param request
     * @param timeout
     * @return
     * @throws SOAPException
     * @throws ServiceException
     * @throws AxisFault
     */
    public static String callwsIBTNAPAS2(String request, int timeout) throws SOAPException, ServiceException, AxisFault {

        boolean isRevert = true;
        Object _resp = "";
        String SOAP_REQUEST = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://sml.com.vn/xsd\">\n"
                + "   <soapenv:Header/>\n"
                + "   <soapenv:Body>\n"
                + "      <xsd:execute>\n"
                + "         <!--Optional:-->\n"
                + "         <xsd:input>" + request + "</xsd:input>\n"
                + "      </xsd:execute>\n"
                + "   </soapenv:Body>\n"
                + "</soapenv:Envelope>";
        try {
            //Create a Stream Source of the Request String
            byte[] reqBytes = SOAP_REQUEST.getBytes();
            ByteArrayInputStream bis = new ByteArrayInputStream(reqBytes);
            StreamSource ss = new StreamSource(bis);

            //Create a SOAP Message Object
            MessageFactoryImpl messageFactory = new MessageFactoryImpl();
            SOAPMessage msg = messageFactory.createMessage();
            javax.xml.soap.SOAPPart soapPart = msg.getSOAPPart();

            //Set the soapPart Content with the stream source
            soapPart.setContent(ss);

            //Create a WebService Call
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTimeout(timeout * 1000);
            call.setTargetEndpointAddress(Configuration.getProperty("ws.url.smartlink.address"));

            //Invoke the WebService.
            //SOAPEnvelope resp = call.invoke(((org.apache.axis.SOAPPart)soapPart).getAsSOAPEnvelope());
            org.apache.axis.message.SOAPEnvelope soapRequest = ((org.apache.axis.SOAPPart) soapPart).getAsSOAPEnvelope();
            isRevert = false;
            // _resp= (Object)call.invoke(((org.apache.axis.SOAPPart)soapPart).getAsSOAPEnvelope());
            //  Helper.writeLogErrorNonDB(Helper.class,"INVOKE:"+SOAP_REQUEST);
            _resp = (Object) call.invoke(soapRequest);
            if (_resp != null) {
                SOAPEnvelope resp = (SOAPEnvelope) _resp;
                javax.xml.soap.SOAPBody sb = resp.getBody();
                Iterator it = sb.getChildElements();
                while (it.hasNext()) {
                    SOAPBodyElement bodyElement = (SOAPBodyElement) it.next();
                    Iterator it2 = bodyElement.getChildElements();
                    while (it2.hasNext()) {
                        SOAPElement element2 = (SOAPElement) it2.next();
                        return element2.getValue();
                    }
                }
            } else {
                Helper.writeLogErrorNonDB(Helper.class, "CALL NAPAS TRA NULL:" + SOAP_REQUEST);
                return null;
            }
        } catch (AxisFault af) {
            Helper.writeLogErrorNonDB(Helper.class, "[LOI IBT 2]SOAP MSG[" + SOAP_REQUEST + "]:" + af.getMessage());
            if (isRevert) {
                return "REVERT";
            }
            if (af.getFaultString().contains("com.sun.xml.stream.xerces.xni.XNIException: a pseudo attribute name is expected.") || af.getFaultString().contains("java.lang.IllegalStateException: Method getLocalName() cannot be called for START_DOCUMENT event.")
                    || af.getFaultString().contains("XML document structures must start and end within the same entity.")) {
                return "REVERT";
            }
            if (af.getFaultString().contains("(404)Not Found") || af.getFaultString().contains("Connection refused: connect")) {
                return "REVERT";
            }
            return af.getFaultString().contains("java.net.SocketTimeoutException") ? "TIMEOUT" : null;
        } catch (Exception e) {
            Helper.writeLogErrorNonDB(Helper.class, "[LOI IBT 3]SOAP MSG[" + SOAP_REQUEST + "]:" + e.getMessage());
            if (isRevert) {
                return "REVERT";
            }
        }
        return null;
    }     
    private static String getAccessKey() {
        return Configuration.getProperty("hmapibt.accesskey");
    }

    private static String getPartnerCode() {
        return Configuration.getProperty("hmapibt.partnercode");
    }

    private static String getSignature() {
        return Configuration.getProperty("hmapibt.signature");
    }
    public void callGWMQ(String request) throws Exception
    {
        Object _resp = "";
        String SOAP_REQUEST = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:vn=\"http://vn.com.scb\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <vn:putRequestMQ soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                    "         <trace xsi:type=\"xsd:string\">"+request+"</trace>\n" +
                    "      </vn:putRequestMQ>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
        try {
            //Create a Stream Source of the Request String
            byte[] reqBytes = SOAP_REQUEST.getBytes();
            ByteArrayInputStream bis = new ByteArrayInputStream(reqBytes);
            StreamSource ss = new StreamSource(bis);

            //Create a SOAP Message Object
            MessageFactoryImpl messageFactory = new MessageFactoryImpl();
            SOAPMessage msg = messageFactory.createMessage();
            javax.xml.soap.SOAPPart soapPart = msg.getSOAPPart();

            //Set the soapPart Content with the stream source
            soapPart.setContent(ss);

            //Create a WebService Call
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTimeout(40 * 1000);
            call.setTargetEndpointAddress(Configuration.getProperty("gwmq.url"));

            //Invoke the WebService.
            //SOAPEnvelope resp = call.invoke(((org.apache.axis.SOAPPart)soapPart).getAsSOAPEnvelope());
            org.apache.axis.message.SOAPEnvelope soapRequest = ((org.apache.axis.SOAPPart) soapPart).getAsSOAPEnvelope();
            
            // _resp= (Object)call.invoke(((org.apache.axis.SOAPPart)soapPart).getAsSOAPEnvelope());
            //  Helper.writeLogErrorNonDB(Helper.class,"INVOKE:"+SOAP_REQUEST);
            _resp = (Object) call.invoke(soapRequest);
            SOAPEnvelope resp = (SOAPEnvelope) _resp;
            javax.xml.soap.SOAPBody sb = resp.getBody();
            Iterator it = sb.getChildElements();
            while (it.hasNext()) {
                SOAPBodyElement bodyElement = (SOAPBodyElement) it.next();
                Iterator it2 = bodyElement.getChildElements();
                while (it2.hasNext()) {
                    SOAPElement element2 = (SOAPElement) it2.next();
                     element2.getValue();
                }
            }
        } catch (Exception ex) 
        {
            throw ex;
        }
    }
}
