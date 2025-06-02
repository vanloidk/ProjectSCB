package scb.com.vn.dbi.utility;

import java.io.File;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author phucng
 */
public class HibernateUtil {

    private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);

    private static SessionFactory sessionFactorySmsScb;
    private static SessionFactory sessionFactoryFcdb;
    private static SessionFactory sessionFactoryDomino;
    private static SessionFactory sessionFactoryVninfo;

    private static String absoluteFilePathSmsScb = scb.com.vn.dbi.utility.Configuration.getProperty("resource.hibernate.cfg.smsscb");
    private static String absoluteFilePathFcdb = scb.com.vn.dbi.utility.Configuration.getProperty("resource.hibernate.cfg.fcdb");
    private static String absoluteFilePathDomino = scb.com.vn.dbi.utility.Configuration.getProperty("resource.hibernate.cfg.domino");
    private static String absoluteFilePathVninfo = scb.com.vn.dbi.utility.Configuration.getProperty("resource.hibernate.cfg.vninfo");

    static DozerBeanMapper mapper = null;

    static {
        startSMS();
        /* Start connection on the first request
        startFCDB();
        startVninfo();
         */
    }

    /**
     * Start connection sms
     */
    public static void startSMS() {
        try {
            LOGGER.info("=================================================================================");
            LOGGER.info("=================================================================================");
            LOGGER.info("========Khởi tạo kết nối Hibernate: ----- SMS");

            File fSmsScb = new File(absoluteFilePathSmsScb);

            Configuration configurationSmsscb = new Configuration().configure(fSmsScb);
            ServiceRegistry serviceRegistrySmsScb = new ServiceRegistryBuilder().applySettings(configurationSmsscb.getProperties()).buildServiceRegistry();
            sessionFactorySmsScb = configurationSmsscb.buildSessionFactory(serviceRegistrySmsScb);
        } catch (Throwable ex) {
            LOGGER.error("Initial SessionFactory SMS creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Start connection FCDB
     */
    public static void startFCDB() {
        try {
            LOGGER.info("=================================================================================");
            LOGGER.info("=================================================================================");
            LOGGER.info("========Khởi tạo kết nối Hibernate: ----- FCDB");

            File fFcdb = new File(absoluteFilePathFcdb);

            Configuration configurationFcdb = new Configuration().configure(fFcdb);
            ServiceRegistry serviceRegistryFcdb = new ServiceRegistryBuilder().applySettings(configurationFcdb.getProperties()).buildServiceRegistry();
            sessionFactoryFcdb = configurationFcdb.buildSessionFactory(serviceRegistryFcdb);

        } catch (Throwable ex) {
            LOGGER.error("Initial SessionFactory FCDB creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Start connection Domino
     */
    public static void startDomino() {
        try {
            LOGGER.info("=================================================================================");
            LOGGER.info("=================================================================================");
            LOGGER.info("========Khởi tạo kết nối Hibernate: ----- Domino");

            File fDomino = new File(absoluteFilePathDomino);

            Configuration configurationDomino = new Configuration().configure(fDomino);
            ServiceRegistry serviceRegistryDomino = new ServiceRegistryBuilder().applySettings(configurationDomino.getProperties()).buildServiceRegistry();
            sessionFactoryDomino = configurationDomino.buildSessionFactory(serviceRegistryDomino);

        } catch (Throwable ex) {
            LOGGER.error("Initial SessionFactory Domino creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Start connection VnInfo
     */
    public static void startVninfo() {
        try {
            LOGGER.info("=================================================================================");
            LOGGER.info("=================================================================================");
            LOGGER.info("========Khởi tạo kết nối Hibernate: ----- Vninfo");

            File fVninfo = new File(absoluteFilePathVninfo);

            Configuration configurationVninfo = new Configuration().configure(fVninfo);
            ServiceRegistry serviceRegistryVninfo = new ServiceRegistryBuilder().applySettings(configurationVninfo.getProperties()).buildServiceRegistry();
            sessionFactoryVninfo = configurationVninfo.buildSessionFactory(serviceRegistryVninfo);
        } catch (Throwable ex) {
            LOGGER.error("Initial SessionFactory Vninfo creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    //===============================================
    /**
     *
     * @return @throws java.lang.Exception
     */
    public static SessionFactory getSessionFactorySmsScb() throws Exception {
        if (DatabaseController.isCANACCESS_SMSSCB()) {
            return sessionFactorySmsScb;
        } else {
            throw new Exception("Database SMS_SCB is off");
        }
    }

    /**
     *
     * @return @throws java.lang.Exception
     */
    public static SessionFactory getSessionFactoryFcdb() throws Exception {
        if (DatabaseController.isCANACCESS_FCDB()) {
            return sessionFactoryFcdb;
        } else {
            throw new Exception("Database FCDB is off");
        }
    }

    /**
     *
     * @return @throws java.lang.Exception
     */
    public static SessionFactory getSessionFactoryDomino() throws Exception {
        if (DatabaseController.isCANACCESS_DOMINO()) {
            return sessionFactoryDomino;
        } else {
            throw new Exception("Database DOMINO is off");
        }
    }

    /**
     *
     * @return @throws java.lang.Exception
     */
    public static SessionFactory getSessionFactoryVninfo() throws Exception {
        if (DatabaseController.isCANACCESS_VNINFO()) {
            return sessionFactoryVninfo;
        } else {
            throw new Exception("Database VNINFO is off");
        }
    }

    //===============================================
    /**
     * Shutdown all connections
     */
    public static void shutdown() {
        // Close caches and connection pools
        sessionFactorySmsScb.close();
        sessionFactoryFcdb.close();
        sessionFactoryDomino.close();
        sessionFactoryVninfo.close();

    }

    //https://developers.google.com/web-toolkit/articles/using_gwt_with_hibernate
    /**
     *
     * @return
     */
    public static Mapper getMapper() {
        if (mapper == null) {
            try {
                mapper = new DozerBeanMapper();
            } catch (Exception ex) {
                LOGGER.error(ex);
                return null;
            }
        }
        return mapper;
    }
}
