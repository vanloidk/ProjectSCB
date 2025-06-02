/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 *
 * @author minhndb
 */
public class MQClient {

    private static MQQueueManager qMgr;

    private String mqHostName = "192.168.67.34"; //11: live; 12: test
    private String mqChanel = "CLIENT.SCB_NAPAS";
    private String mqPort = "1414";
    private String mqQmanagerName = "SCB.NAPAS";
    private String mqNameReceive = "OUTBOX";
    private String qNameSend = "INBOX";
    private byte[] content_mes_esb;
    static Logger log = Logger.getLogger(MQClient.class);

    /*private String mqHostName="10.4.4.75";
  private String mqChanel="DEV.APP.SVRCONN";
  private String mqPort="1414";
  private String mqQmanagerName="DEVQUEUEMANAGER";
  private String mqNameReceive="REQUEST";
   private String qNameSend ="REQUEST";*/

    /**
     *
     * @param msg
     * @throws Exception
     */


    public boolean putMsgToQueue(byte[] msg) throws Exception {
       boolean send = false;
        try {
            
            MQEnvironment.hostname = mqHostName;
            MQEnvironment.channel = mqChanel; //Kenh ket noi MQ
            MQEnvironment.port = Integer.parseInt(mqPort); // 1515;
            qMgr = new MQQueueManager(mqQmanagerName); //Ten Queue manager
            int openInputOptions = MQC.MQOO_OUTPUT + MQC.MQOO_FAIL_IF_QUIESCING;
            MQQueue queueIn = qMgr.accessQueue(qNameSend, openInputOptions, null, null, null);
            MQPutMessageOptions putOptions = new MQPutMessageOptions();
            putOptions.options = MQC.MQPMO_NEW_MSG_ID | MQC.MQPMO_NEW_CORREL_ID;
            if(qMgr.isConnected())
            {
                
                MQMessage mqMessage = new MQMessage();
                mqMessage.encoding = 546;
                mqMessage.format = MQC.MQFMT_STRING;
                mqMessage.characterSet = 1208;
                send= true;
                mqMessage.write(msg);
                queueIn.put(mqMessage, putOptions);
                return true;
            }
           
            //queueIn.notifyAll();
        }
        catch (MQException e)
        {
            Helper.writeLogErrorNonDB(MQClient.class, "ERROR putMsgToQueue :"+ e.getMessage());
            if(e.reasonCode == 2018||e.reasonCode ==2019)
            {
                return false;
            }
        }
        catch (Exception e) {
            Helper.writeLogErrorNonDB(MQClient.class, "ERROR putMsgToQueue :"+ e.getMessage());
            return send;
        } finally {
            disconnectMQ();
        }
        return send;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public String getMsgFromQueue() throws Exception {
        //    Collection listMess = new ArrayList();
        String mes_content = "";
        try {
            MQEnvironment.hostname = mqHostName;
            MQEnvironment.channel = mqChanel; //Kenh ket noi MQ
            MQEnvironment.port = Integer.parseInt(mqPort); // 1515;
            qMgr = new MQQueueManager(mqQmanagerName); //Ten Queue manager
            int openInputOptions = MQC.MQOO_BROWSE + MQC.MQOO_INPUT_SHARED;
            MQQueue queue = qMgr.accessQueue(mqNameReceive, openInputOptions, null, null, null);
            MQGetMessageOptions getMessageOptions = new MQGetMessageOptions();
            getMessageOptions.options = MQC.MQOO_INPUT_AS_Q_DEF;
            //          getMessageOptions.waitInterval = MQC.MQWI_UNLIMITED;
            MQMessage mqMessage = null;

            try {
                mqMessage = new MQMessage();
                mqMessage.format = MQC.MQFMT_STRING;
                mqMessage.characterSet = 1208;
                queue.get(mqMessage, getMessageOptions);
                byte[] b = new byte[mqMessage.getMessageLength()];
                mqMessage.readFully(b);
                mes_content = new String(b, "UTF-8");
                mqMessage.clearMessage();
            } catch (IOException e) {
                log.info("---------------------");
                log.info("IOException: " + e.getMessage());
//                System.out.println("IOException: " + e.getMessage());
//                throw e;

            } catch (MQException e) {
                if (e.completionCode == 2
                        && e.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE) //                    System.out.println("All messages read.");
                {
                    log.info("All messages read.");
                }
                else //                    System.out.println("MQException: Completion Code = " +
                //                                       e.completionCode +
                //                                       " : Reason Code = " + e.reasonCode);
                {
                    log.info("MQException: Completion Code = "
                            + e.completionCode
                            + " : Reason Code = " + e.reasonCode);
                }

            }

        } catch (Exception e) {
            throw e;
        } finally {
            disconnectMQ();
        }
        return mes_content;
    }

    /**
     *
     * @param numberMsg
     * @return
     * @throws Exception
     */
    public Collection getListMsgFromQueue(int numberMsg) throws Exception {
        Collection listMess = new ArrayList();
        String mes_content = "";
        try {
            MQEnvironment.hostname = mqHostName;
            MQEnvironment.channel = mqChanel; //Kenh ket noi MQ
            MQEnvironment.port = Integer.parseInt(mqPort); // 1515;
            qMgr = new MQQueueManager(mqQmanagerName); //Ten Queue manager
            int openInputOptions = MQC.MQOO_BROWSE + MQC.MQOO_INPUT_SHARED;
            MQQueue queue = qMgr.accessQueue(mqNameReceive, openInputOptions, null, null, null);
            MQGetMessageOptions getMessageOptions = new MQGetMessageOptions();
            getMessageOptions.options = MQC.MQOO_INPUT_AS_Q_DEF;
            MQMessage mqMessage = null;
            for (int s = 0; s <= numberMsg; s++) {
                while (true) {
                    try {
                        mqMessage = new MQMessage();
                        mqMessage.format = MQC.MQFMT_STRING;
                        mqMessage.characterSet = 1208;
                        queue.get(mqMessage, getMessageOptions);
                        byte[] b = new byte[mqMessage.getMessageLength()];
                        mqMessage.readFully(b);
                        mes_content = new String(b, "UTF-8");
                        listMess.add(mes_content);
                        mqMessage.clearMessage();
                    } catch (IOException e) {
                        System.out.println("IOException: " + e.getMessage());
                        break;
                    } catch (MQException e) {
                        if (e.completionCode == 2
                                && e.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE) {
                            System.out.println("All messages read.");
                        } else {
                            System.out.println("MQException: Completion Code = "
                                    + e.completionCode
                                    + " : Reason Code = " + e.reasonCode);
                        }
                        break;
                    }
                }
            }

        } catch (Exception e) {
            throw e;
        } finally {
            disconnectMQ();
        }
        return listMess;
    }

    /**
     *
     * @throws Exception
     */
    public void disconnectMQ() throws Exception {
        try {
            if (qMgr != null) {
                if (qMgr.isConnected()) {
                    qMgr.disconnect();
                }
            }
        } catch (MQException e) {
            throw e;
        }
    }

    /**
     *
     * @param qMgr
     */
    public void setQMgr(MQQueueManager qMgr) {
        this.qMgr = qMgr;
    }

    /**
     *
     * @return
     */
    public MQQueueManager getQMgr() {
        return qMgr;
    }

    /**
     *
     * @param mqHostName
     */
    public void setMqHostName(String mqHostName) {
        this.mqHostName = mqHostName;
    }

    /**
     *
     * @return
     */
    public String getMqHostName() {
        return mqHostName;
    }

    /**
     *
     * @param mqChanel
     */
    public void setMqChanel(String mqChanel) {
        this.mqChanel = mqChanel;
    }

    /**
     *
     * @return
     */
    public String getMqChanel() {
        return mqChanel;
    }

    /**
     *
     * @param mqPort
     */
    public void setMqPort(String mqPort) {
        this.mqPort = mqPort;
    }

    /**
     *
     * @return
     */
    public String getMqPort() {
        return mqPort;
    }

    /**
     *
     * @param mqQmanagerName
     */
    public void setMqQmanagerName(String mqQmanagerName) {
        this.mqQmanagerName = mqQmanagerName;
    }

    /**
     *
     * @return
     */
    public String getMqQmanagerName() {
        return mqQmanagerName;
    }

    /**
     *
     * @param mqName
     */
    public void setMqName(String mqName) {
        this.qNameSend = mqName;
    }

    /**
     *
     * @return
     */
    public String getMqName() {
        return mqNameReceive;
    }

    /**
     *
     * @param content_mes_esb
     */
    public void setContent_mes_esb(byte[] content_mes_esb) {
        this.content_mes_esb = content_mes_esb;
    }

    /**
     *
     * @return
     */
    public byte[] getContent_mes_esb() {
        return content_mes_esb;
    }

    /**
     *
     * @param msg
     * @throws Exception
     */
    public void putMsgToQueue2(byte[] msg) throws Exception {
        try {
            MQEnvironment.hostname = mqHostName;
            MQEnvironment.channel = mqChanel; //Kenh ket noi MQ
            MQEnvironment.port = Integer.parseInt(mqPort); // 1515;
            qMgr = new MQQueueManager(mqQmanagerName); //Ten Queue manager
            int openInputOptions = MQC.MQOO_OUTPUT + MQC.MQOO_FAIL_IF_QUIESCING;
            MQQueue queueIn = qMgr.accessQueue(mqNameReceive, openInputOptions, null, null, null);
            MQPutMessageOptions putOptions = new MQPutMessageOptions();
            putOptions.options = MQC.MQPMO_NEW_MSG_ID | MQC.MQPMO_NEW_CORREL_ID;
            MQMessage mqMessage = new MQMessage();
            mqMessage.encoding = 546;
            mqMessage.format = MQC.MQFMT_STRING;
            mqMessage.characterSet = 1208;
            mqMessage.write(msg);
            queueIn.put(mqMessage, putOptions);
            //queueIn.notifyAll();
        } catch (Exception e) {
            throw e;
        } finally {
            disconnectMQ();
        }
    }
   
    public String getFromOUTBOX(String id) throws Exception {
  //    Collection listMess = new ArrayList();
      String mes_content = "";
      try {
          /*
          MQEnvironment.hostname = mqHostName;
          MQEnvironment.channel = mqChanel; //Kenh ket noi MQ
          MQEnvironment.port = Integer.parseInt(mqPort); // 1515;
          qMgr = new MQQueueManager(mqQmanagerName); //Ten Queue manager
          int openInputOptions = MQC.MQOO_BROWSE + MQC.MQOO_INPUT_SHARED;
          MQQueue queue =  qMgr.accessQueue(mqNameReceive, openInputOptions, null, null, null);
          MQGetMessageOptions getMessageOptions = new MQGetMessageOptions();
          getMessageOptions.options = MQC.MQOO_INPUT_AS_Q_DEF;
  //          getMessageOptions.waitInterval = MQC.MQWI_UNLIMITED;
        */
        MQMessage mqMessage = null;            
        try {
            mqMessage = new MQMessage();
            mqMessage.format = MQC.MQFMT_STRING;
            mqMessage.characterSet = 1208;
            mqMessage.messageId=id.getBytes();
            queueOutbox.get(mqMessage, getMessageOptions);
            byte[] b = new byte[mqMessage.getMessageLength()];
            mqMessage.readFully(b);
            mes_content = new String(b,"UTF-8"); 
            mqMessage.clearMessage();
        } catch (IOException e) {
          log.info("---------------------");
          log.info("IOException: " + e.getMessage());
//                System.out.println("IOException: " + e.getMessage());
//                throw e;

        } catch (MQException e) {
            if (e.completionCode == 2 &&
                e.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE)
//                    System.out.println("All messages read.");
                log.info("All messages read.");
            else
//                    System.out.println("MQException: Completion Code = " +
//                                       e.completionCode +
//                                       " : Reason Code = " + e.reasonCode);
                  log.info("MQException: Completion Code = " +
                                   e.completionCode +
                                   " : Reason Code = " + e.reasonCode);

        }
          
        
      } catch (Exception e) {
          throw e;
      }
      /*
      finally {            
          disconnectMQ();
      }*/
      return mes_content;
  }
    MQQueue queueOutbox;
    MQQueue queueInbox;
    MQGetMessageOptions getMessageOptions;
    MQPutMessageOptions putOptions;
  public boolean initMQOUTBOX()
  {
    String mes_content = "";
    try 
    {
        Helper.writeLogErrorNonDB(MQClient.class, "KHOI TAO MQ OUTBOX");
        MQEnvironment.hostname = mqHostName;
        MQEnvironment.channel = mqChanel; //Kenh ket noi MQ
        MQEnvironment.port = Integer.parseInt(mqPort); 
        
        long MILLIS_TO_WAIT = 2 * 1000L;
          final ExecutorService executor = Executors.newSingleThreadExecutor();

          // schedule the work
         // final Future<String> future = executor.submit( MQClient::initToMQ);
          Future future = executor.submit(new Runnable() {
              public void run() {
                  try {
                     // Helper.writeLog(MQClient.class, "KHOI TAO MQ:"+mqQmanagerName);
                      qMgr = new MQQueueManager(mqQmanagerName);
                  } catch (MQException ex) {
                      Helper.writeLogErrorNonDB(MQClient.class, "KET NOI DEN MQ LOI:"+ex.getMessage());
                  }
              }
          });
          try
          {
          // where we wait for task to complete
              final Object result = future.get( MILLIS_TO_WAIT, TimeUnit.MILLISECONDS );
          }
          catch ( Exception e )
          {
              Helper.writeLogErrorNonDB(MQClient.class, "KHOI TAO MQ TIMEOUT");
          }
          executor.shutdownNow();
           
        //qMgr = new MQQueueManager(mqQmanagerName);
        //Helper.writeLogErrorNonDB(MQClient.class,  "HOAN THANH KHOI TAO KET NOI MQ");
        int openInputOptions = MQC.MQOO_BROWSE + MQC.MQOO_INPUT_SHARED;
        queueOutbox  =  qMgr.accessQueue(mqNameReceive, openInputOptions, null, null, null);
        getMessageOptions = new MQGetMessageOptions();
        getMessageOptions.options = MQC.MQOO_INPUT_AS_Q_DEF;
        //getMessageOptions.waitInterval = 500;
        Helper.writeLogErrorNonDB(MQClient.class,  "HOAN THANH KHOI TAO QUEUE");
        return true;
    }
    catch (MQException e) 
    {
        if (e.completionCode == 2 &&
            (e.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE||e.reasonCode==2538))
           mes_content = "";
        else
            Helper.writeLogErrorNonDB(MQClient.class,"MQException2:"+ e.getMessage());
    }
    catch (Exception e) 
    {
        Helper.writeLogErrorNonDB(MQClient.class, "Exception:"+e.getMessage());
    }
    return false;
 }
  public boolean initInboxQ() throws MQException
  {try
  {
        MQEnvironment.hostname = mqHostName; 
        MQEnvironment.channel = mqChanel; //Kenh ket noi MQ
        MQEnvironment.port = Integer.parseInt(mqPort); // 1515;
        qMgr = new MQQueueManager(mqQmanagerName); //Ten Queue manager
        int openInputOptions = MQC.MQOO_OUTPUT + MQC.MQOO_FAIL_IF_QUIESCING;
        queueInbox = qMgr.accessQueue(qNameSend, openInputOptions, null, null,  null); 
        putOptions = new MQPutMessageOptions();
        putOptions.options =  MQC.MQPMO_NEW_MSG_ID | MQC.MQPMO_NEW_CORREL_ID;
        return true;
  }catch(Exception ex)
  {
      Helper.writeLogErrorNonDB(MQClient.class,"initInboxQ:"+ex.getMessage());
      return false;
  }
  }
  public void putMsgToINBOXQueue(byte[] msg) throws Exception {
    try {
        
        MQMessage mqMessage = new MQMessage();
        mqMessage.encoding = 546;
        mqMessage.format = MQC.MQFMT_STRING;
        mqMessage.characterSet = 1208;
        mqMessage.write( msg);
        queueInbox.put(mqMessage, putOptions);
    }
 
    catch (Exception e) {
        throw e;
    } 
  }
  public boolean initOutboxMQ() throws MQException
     {
         try{
            Hashtable<String, Object> properties = new Hashtable<String, Object>();
            properties.put("hostname", mqHostName);
            properties.put("port", Integer.parseInt(mqPort));
            properties.put("channel", mqChanel);

            qMgr = new MQQueueManager(mqQmanagerName, properties);
            queueOutbox = qMgr.accessQueue(mqNameReceive, MQC.MQOO_INPUT_AS_Q_DEF + MQC.MQOO_FAIL_IF_QUIESCING);
            return true;
             }
         catch(Exception ex)
         {
           Helper.writeLogErrorNonDB(MQClient.class,"khoi tao loi ex:"+ex.getMessage());
           return false;
         }
     }
     
     @SuppressWarnings("deprecation")
    public String getFromOUTBOXMQ(String trace) throws MQException {

        String msgRs = "";

        try {
            
            // Get a message from the queue
            MQMessage myMessage = new MQMessage();
            MQGetMessageOptions gmo = new MQGetMessageOptions();

            // has default values
            myMessage.messageId = trace.getBytes();
            queueOutbox.get(myMessage, gmo);
            // Extract the message data
            int strLen = myMessage.readInt();
            byte[] strData = new byte[strLen];
            myMessage.readFully(strData, 0, strLen);
            msgRs = new String(strData, 0);

            //queueOutbox.close();
            //qMgr.disconnect();

            return msgRs;
        } catch (IOException e) {
            Helper.writeLogErrorNonDB(MQClient.class,"com.scb.ibmmq.IBMMQUntils.IBMMQGet()" + e.getMessage());
        }

        return "";
    }
    
    @SuppressWarnings("deprecation")
    public void sendToINBOX(String msg) throws MQException, IOException {
        try
        {

        MQMessage myMessage = new MQMessage();
        myMessage.writeInt(msg.length());
        myMessage.writeBytes(msg);
        myMessage.messageId = "0".getBytes();
        myMessage.writeUTF("messageId");

        // Use the default put message options...
        MQPutMessageOptions pmo = new MQPutMessageOptions();
        // put the message!
        queueInbox.put(myMessage, pmo);

        //queue.close();
        //qMgr.disconnect();
        }
        catch(Exception ex)
        {
            Helper.writeLogErrorNonDB(MQClient.class, "Exception initMQINBOX:"+ex.getMessage());
        }

    }
     public boolean initMQINBOX() throws MQException, IOException {

        try
        {
            Hashtable<String, Object> properties = new Hashtable<String, Object>();
            properties.put("hostname", mqHostName);
            properties.put("port", Integer.parseInt(mqPort));
            properties.put("channel", mqChanel);

            qMgr = new MQQueueManager(mqQmanagerName, properties);
            // connect to a queue
            queueInbox= qMgr.accessQueue(qNameSend, MQC.MQOO_OUTPUT + +MQC.MQOO_FAIL_IF_QUIESCING);
            return true;
        }
        catch (Exception ex)
        {
            Helper.writeLogErrorNonDB(MQClient.class, "Exception initMQINBOX:"+ex.getMessage());
            return false;
        }
        
    }
}
