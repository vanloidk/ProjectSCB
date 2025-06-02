/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.ultility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author minhndb
 */
public class ZipUtils {
    private static final String ENCODE = "UTF8";
    
    public static String zip(String data) {
        String result = "";
        try(
                ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length());
                GZIPOutputStream gzip = new GZIPOutputStream(bos);
            ) {
            gzip.write(data.getBytes());
            gzip.close();
            byte[] compressed = bos.toByteArray();
            bos.close();
            result = Base64Custom.encode(compressed).replace("\r\n", "");
        } catch (Exception e) {
            System.err.println("Exception: " + e);
        }
        return result;
    }
    
    public static String unZip(String strZipped) {
        String result = "";
        try( 
                ByteArrayInputStream bais = new ByteArrayInputStream(Base64Custom.decode(strZipped));
                GZIPInputStream gzis = new GZIPInputStream(bais);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ) {
            byte[] buf = new byte[1024];
            int len;
            while((len = gzis.read(buf)) > 0) {
                baos.write(buf, 0, len);
            }
            gzis.close();
            baos.close();
            result = new String(baos.toByteArray(), ENCODE);
        } catch(Exception ex) {
            System.err.println("Exception: " + ex);
        }
        return result;
    }
}