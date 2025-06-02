/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Administrator
 */
public class IBTHmap {
     public static HashMap<String, String> hmapResponse = new HashMap<String, String>();
   public static HashMap<String, String> hmapRequest = new HashMap<String, String>();
 
    public void insertReponseIBT(String trace, String reponse) throws RemoteException
    {
        hmapResponse.put(trace, reponse);
    }
    public String getReponseIBT(String trace) throws RemoteException
    {
        if(hmapResponse.get(trace)!=null)
        {
            String response=(String)hmapResponse.get(trace);
            hmapResponse.remove(trace);
            return response;
        }
        return null;
    }
    public String getRequestTraceIBT() throws RemoteException
    {
        if(!hmapRequest.isEmpty())
        {
            for (Map.Entry<String, String> entry : hmapRequest.entrySet()) 
            {
                String trace = entry.getKey();
                hmapRequest.remove(trace);

                String value = entry.getValue();
                long beforeTime = Long.valueOf(value);
                Date curdate=new Date();
                long curTime = curdate.getTime();
                
                if(curTime - beforeTime<=1000*60*10)
                {
                    return trace;
                }
            }
        }
        return null;
    }
    public void putRequestIBT(String AuditNumber)
    {
        
        if(hmapRequest.get(AuditNumber)!=null)
        {
            hmapRequest.remove(AuditNumber);
        }
        Date curdate=new Date();
        long curTime = curdate.getTime();
        hmapRequest.put(AuditNumber, String.valueOf(curTime));
    }
    
}
