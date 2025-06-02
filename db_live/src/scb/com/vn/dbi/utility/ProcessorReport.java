/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.utility;

import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Logger;

/**
 *
 * @author phucnd
 */
public class ProcessorReport {

    private static final Logger LOGGER = Logger.getLogger(ProcessorReport.class);

    //LUU Y:
    //1. Chep tat ca cac file D:\Dropbox\MYPROJECTS\JAVA\lib\xerces-2_11_0
    //vao JRE 1.6 /LIB/EXT de co the chay duoc 
    //FORUM: Once I put the xml parser jar files to ORACLE_HOME\jdk\jre\lib\ext, the opmnctl start will fail. I guess Oracle Application Server is trying to start using the xml parser class in the \ext instead of the Oracle xml parser for the server itself.
    //2. Neu de trong Spring MVC se bi loi: 
    //java.lang.NullPointerException at org.apache.commons.digester.Digester.getXMLReader(Digester.java:1068)
    /**
     *
     * @param xml
     * @param fileXslt
     * @param fileOut
     * @return
     */
    public int processorPDF(String xml, String fileXslt, String fileOut) {
        InputStream is = null;
        try {
            return 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -3;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ex) {
                LOGGER.error(ex);
                return -2;
            }
        }
    }
}
