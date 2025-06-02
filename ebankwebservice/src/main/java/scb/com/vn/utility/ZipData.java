/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author minhndb
 */
public class ZipData {

    private static final String _strEncode = "UTF8";

    /**
     *
     */
    public ZipData() {
    }

    /**
     *
     * @param strData
     * @return
     * @throws Exception
     */
    public static String ZipToString(String strData) throws Exception {
        try {
            String zipData = "";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gzos = new GZIPOutputStream(baos);
            byte[] bData = strData.getBytes(_strEncode);
            gzos.write(bData, 0, bData.length);
            gzos.close();
            byte[] b = baos.toByteArray();
            zipData = MyBase64.encode(b).replace("\r\n", ""); //Base64Utils.base64Encode(baos.toByteArray());
            baos.close();
            return zipData;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     *
     * @param strZipped
     * @return
     * @throws Exception
     */
    public static String UnZipToString(String strZipped) throws Exception {
        try {
            GZIPInputStream gzis = new GZIPInputStream(new ByteArrayInputStream(MyBase64.decode(strZipped)));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = gzis.read(buf)) > 0) {
                baos.write(buf, 0, len);
            }
            gzis.close();
            baos.close();
            return new String(baos.toByteArray(), _strEncode);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
