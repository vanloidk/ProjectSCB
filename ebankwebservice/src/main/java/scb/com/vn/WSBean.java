package scb.com.vn;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.axis.Constants;
import org.apache.axis.MessageContext;
import org.apache.log4j.Level;
import scb.com.vn.payment.IBTController;
import scb.com.vn.utility.Authen;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;
import scb.com.vn.payment.OnlinePaymentController;

//Temp for routing
import scb.com.vn.model.BillPaymentRq;
import scb.com.vn.model.GetBillInfoRq;
import scb.com.vn.payment.IBTV2Controller;
import scb.com.vn.ultility.Xml;
import ws.internal.payment.scbgw.WSBeanSoapBindingStub;
import ws.internal.payment.scbgw.WSBeanServiceLocator;
//Temp for routing

/**
 *
 * @author minhndb
 */
public class WSBean {

    private final String _clsname = "scb.com.vn.controller.ControllerImpl";

    /**
     *
     */
    public final Properties _configprop = null;
    private final int ERR_NOSUCHMETHOD = -10, ERR_SYSTEM = -99;

    /**
     * **** Begin methods for web service app server ****
     */
    public WSBean() {
        Configuration.init();
    }

    /**
     *
     * @param methodname
     * @param partnercode
     * @param arrObjParas
     * @return
     */
    public Object[] callExecution(String methodname, String partnercode, Object[] arrObjParas) {
        String ip = MessageContext.getCurrentContext().getStrProp(Constants.MC_REMOTE_ADDR);

        try {
            //0. Ghi log ham & paras IN
            Helper.writeLog(WSBean.class, Level.INFO, String.format("[I/O-IP-PARTNERCODE-METHODNAME-PARAMS]-[IN-%1$s-%2$s-%3$s-%4$s]", ip, partnercode, methodname, Helper.getDescriptionParam(arrObjParas)));

            // Bổ sung phân luồng giao dịch napas cho mobilebanking.        
//        if (checkToRoute(methodname,arrObjParas) == 1){
//            Helper.writeLog(WSBean.class, Level.INFO, "======================Routing");
//            return callByRouting(methodname, partnercode, arrObjParas);}
            //1. Kiem tra chung thuc bo vo mobile (neu mobile thi che lai)   
            
            Helper.writeLog(WSBean.class, Level.INFO, "checkAuthen:" + methodname + "-partnercode:" + partnercode);
            /*
            int iResult_checkAuthen = (new Authen()).checkAuthen(methodname, partnercode);
            
            if (iResult_checkAuthen < 0) {
                Object[] obj_arrobj = new Object[2];
                obj_arrobj[0] = iResult_checkAuthen;
                Helper.writeLog(WSBean.class, Level.INFO, "callMethod:" + methodname + "-partnercode:" + partnercode + "-obj[0]:" + obj_arrobj[0]);
                return obj_arrobj;
            }
            */
            /* 2. Goi ham ben trong */

            Object[] obj = callMethod(methodname, partnercode, arrObjParas);

            /*
        //FOR CHECK CARD
        Object[] obj=new Object[2];
        if(methodname.equals("getCARDID")&partnercode.equals("MOCA"))
        {
            OnlinePaymentController online=new OnlinePaymentController();
            obj[1]=online.getCARDID(arrObjParas[0].toString());
        }
        else
        {
            obj[0]=-4;
        }*/
            //3. Ghi log ham & ket qua tra ve
            Helper.writeLog(WSBean.class, Level.INFO, String.format("[I/O-IP-PARTNERCODE-METHODNAME-PARAMS]-[OUT-%1$s-%2$s-%3$s-%4$s]", ip, partnercode, methodname, Helper.getDescriptionParam(obj)));
            return obj;

        } catch (Exception ex) {
            Helper.writeLog(WSBean.class, Level.INFO, "methodname:" + methodname + "-partnercode:" + partnercode + "-Exception:" + ex.getMessage());
            return null;
        }
    }

    private int checkToRoute(String methodname, Object[] arrObjParas) {
        try {
            if (methodname.equalsIgnoreCase("GetBillInfo")) {
                String xml = (String) arrObjParas[0];
                GetBillInfoRq req = (GetBillInfoRq) Xml.unMarshaller(GetBillInfoRq.class, xml);
                if (req.getUserName().equalsIgnoreCase("hieudt")
                        || req.getUserName().equalsIgnoreCase("duytxa")
                        || req.getUserName().equalsIgnoreCase("anhbtq")
                        || req.getUserName().equalsIgnoreCase("anh_ptt")) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (methodname.equalsIgnoreCase("BillPayment")) {
                String xml = (String) arrObjParas[0];
                BillPaymentRq req = (BillPaymentRq) Xml.unMarshaller(BillPaymentRq.class, xml);
                if (req.getUserName().equalsIgnoreCase("hieudt")
                        || req.getUserName().equalsIgnoreCase("duytxa")
                        || req.getUserName().equalsIgnoreCase("anhbtq")
                        || req.getUserName().equalsIgnoreCase("anh_ptt")) {
                    return 1;
                } else {
                    return 0;
                }

            } else {
                return 0;
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return 0;
        }
    }

    private Object[] callByRouting(String methodname, String partnercode, Object[] arrObjParas) {
        try {
            WSBeanServiceLocator wssl = new WSBeanServiceLocator();
            wssl.setWSBeanEndpointAddress("http://192.168.44.2/ebankgw_scb/services/WSBean?wsdl");
            WSBeanSoapBindingStub wss = (WSBeanSoapBindingStub) wssl.getWSBean();

            //wss.setHeader(scb.com.vn.ultility.ws.Stub.setSoapHeaderElement("QjAeFw0xNDA4MTUwOTE", methodname, "9S584WdTZ6vUnNty/jXLXstksna6oAjlLpw0bKdq"));            
            return wss.callExecution(methodname, partnercode, arrObjParas);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //Function for SMARKLINK CALL

    /**
     *
     * @param request
     * @return
     * @throws Exception
     */
    public String callExecutionSML(String request) throws Exception {
        IBTController controler = new IBTController();
        return controler.transferToSCB(request);
    }

    //Function for SMARKLINK CALL

    /**
     *
     * @param request
     * @return
     * @throws Exception
     */
    public String callExecutionSMLONL(String request) throws Exception {
        OnlinePaymentController controler = new OnlinePaymentController();
        return controler.SMLOnlinePayment(request);
    }
    				
/**
     *
     * @param str
     * @return
     */
    public String getString(String str) {
        String rs = "";
        String regex = "^[A-Za-z]\\w{5,29}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str.trim());
        
        if (m.matches()) {
            rs = "Return from WS server: ".concat(str);
        } else {
            rs = "Invalid input paramater.";
        }
        return rs;
    }	

    private Object[] callMethod(String methodname, String partnercode, Object[] arrObjParas) {
        Object[] obj_return = new Object[2];

        try {
            Object[] _o = Helper.convertParasIn(_clsname, methodname, arrObjParas);
            if (_o == null) {
                Helper.writeLog(this.getClass(), Level.INFO, String.format("[I/O-PARTNERCODE-METHODNAME-RESULT-ERROR]-[IN-%1$s-%2$s-%3$d-%4$s]",
                        partnercode, methodname, ERR_NOSUCHMETHOD, "ERR_NOSUCHMETHOD"));
                obj_return[0] = ERR_NOSUCHMETHOD;
                return obj_return;
            }

            Class<?>[] paramsTypeToInvoke = Helper.convertParamsTypeToPrimitive(_o);
            if (paramsTypeToInvoke == null) {
                Helper.writeLog(this.getClass(), Level.INFO, String.format("[I/O-PARTNERCODE-METHODNAME-RESULT-ERROR]-[IN-%1$s-%2$s-%3$d-%4$s]",
                        partnercode, methodname, ERR_NOSUCHMETHOD, "ERR_NOSUCHMETHOD"));
                obj_return[0] = ERR_NOSUCHMETHOD;
                return obj_return;
            }

            Class<?> cls = Class.forName(_clsname);

            Object objToInvoke = cls.getConstructor(String.class).newInstance(partnercode);
            obj_return[0] = 0;
            System.out.print(cls.getMethod(methodname, paramsTypeToInvoke).toString());

            //1.1 Kiem tra phan quyen function partner
            //kimncm bo tam thoi phan quyen
            /*
            int iResult_checkPartner = (new Authen()).checkPermission(partnercode, cls.getMethod(methodname, paramsTypeToInvoke).toString());
            if (iResult_checkPartner < 0) {
                Object[] obj_arrobj = new Object[2];
                obj_arrobj[0] = iResult_checkPartner;
                return obj_arrobj;
            }
            */
            //kimncm bo tam thoi phan quyen
            //End check permission partner
            obj_return[1] = cls.getMethod(methodname, paramsTypeToInvoke).invoke(objToInvoke, _o);
            return obj_return;

        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), String.format("[I/O-PARTNERCODE-METHODNAME-RESULT-ERROR]-[IN-%1$s-%2$s-%3$d-%4$s]",
                    partnercode, methodname, ERR_SYSTEM, e));
            obj_return[0] = ERR_SYSTEM;
            return obj_return;
        }
    }
   
   
}
