package vn.com.scb.bian.utility;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.security.PrivateKey;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.security.PublicKey;

import org.apache.axis.AxisFault;


public class Helper {
    
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
    
    public static void writeLogErrorNonDB(Class<?> clazz, String msg) {
      Logger.getLogger(clazz).error(msg);
    }
    
    
    
    private static String addExtraSpace(String str, int maxlength) {
        return addExtraChar(str, maxlength, " ");
    }

    public static String addExtraChar(String str, int maxlength, String chartoadd) {
        String newstr = "";
        for (int j = 0; j < (maxlength - str.length()); j++) {
            newstr += chartoadd;
        }
        return newstr + str;
    }


    
    
}
