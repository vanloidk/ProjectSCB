package scb.com.vn.dbi.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import scb.com.vn.dbi.connection.ConnectionManager;
import scb.com.vn.dbi.controller.*;
import scb.com.vn.dbi.utility.Configuration;
import scb.com.vn.dbi.utility.Helper;
import scb.com.vn.dbi.utility.HibernateUtil;

//Goi class tu window: java -jar dbi_server.jar scb.com.vn.dbi.server.DBIServer
public class DBIServer {
 

//    public static void main(String[] args) throws Exception {
//        if (args.length == 0) {
//            throw new Exception("1. Khong co tham so. --> Ngung run server.");
//        }
//        System.out.println("1. Tham so dau tien, DBIHOME = [" + args[0] + "], PORT = [" + args[2] + "], LOG = [" + args[1] + "]");
//        Configuration.initLoadProperty(args[0]);
//        PropertyConfigurator.configure(args[1]);
//
//        //Khoi tao ket noi JDBC, su dung BoneCP
//        /* Start connection on the first request
//        ConnectionManager.initConnection("gw");
//        ConnectionManager.initConnection("sms");
//        ConnectionManager.initConnection("fcdb");
//        ConnectionManager.initConnection("smsscb");
//        ConnectionManager.initConnection("fccgw");
//        ConnectionManager.initConnection("vninfo");
//        ConnectionManager.initConnection("domino");
//        ConnectionManager.initConnection("cwlive");
//        ConnectionManager.initConnection("cwdwh");
//        ConnectionManager.initConnection("fcccore");
//        ConnectionManager.initConnection("mms");
//         */
//        //Khoi tao ket noi Hibernate, su dung c3p0
//        HibernateUtil.getSessionFactorySmsScb();
//
//        try {
//            int rmiport = Integer.parseInt(args[2]);
//            String rmiservice = Configuration.getProperty("rmi.registry.service");
//            System.out.println(String.format("2. Registry thuc thi voi cac tham so: [port-service]-[%1$d-%2$s]", rmiport, rmiservice));
//
//            Registry registry = LocateRegistry.createRegistry(rmiport);
//            registry.rebind(rmiservice, new DBIImpl(rmiport));
//            System.out.println("3. Hoan thanh gan ket, dang lang nghe yeu cau tu may khach ...");
//            Helper.writeLog(DBIServer.class, Level.INFO, "3. Hoan thanh gan ket, dang lang nghe yeu cau tu may khach ...");
//        } catch (Exception e) {
//            System.err.println("Server exception: " + e.toString());
//        }
//    }
    
   
     public static void main(String[] args) throws Exception {
         
        
        if (args.length == 0) {
            throw new Exception("1. Khong co tham so. --> Ngung run server.");
        }
        
        // for live
     
       
        System.out.println("1. Tham so dau tien, DBIHOME = [" + args[0] + "], PORT = [" + args[2] + "], LOG = [" + args[1] + "]");
        Configuration.initLoadProperty(args[0]);
        PropertyConfigurator.configure(args[1]);
       
     
        //config cho tet u MB
        Configuration.initLoadProperty("/u01/source/gateway/dbibank/CONFIG/config.properties");        
        
       // Configuration.initLoadProperty("D:\\EBANKING\\SOURCE_CODE\\db_live\\config\\config.properties");
        PropertyConfigurator.configure(Configuration.getProperty("PATH_LOGFILE_PARTTERN"));
     
        //Khoi tao ket noi Hibernate, su dung c3p0
        HibernateUtil.getSessionFactorySmsScb();

        try {
             int rmiport = Integer.parseInt(args[2]);
            //String rmiservice = Configuration.getProperty("rmi.registry.service");
            //int rmiport = Integer.parseInt(Configuration.getProperty("rmi.registry.port"));
            String rmiservice = Configuration.getProperty("rmi.registry.service");
            System.out.println(String.format("2. Registry thuc thi voi cac tham so: [port-service]-[%1$d-%2$s]", rmiport, rmiservice));
            
            Registry registry = LocateRegistry.createRegistry(rmiport);
            
            registry.rebind(rmiservice, new DBIImpl(rmiport));
            System.out.println("3. Hoan thanh gan ket, dang lang nghe yeu cau tu may khach ...");
            Helper.writeLog(DBIServer.class, Level.INFO, "3. Hoan thanh gan ket, dang lang nghe yeu cau tu may khach ...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
    }
}
