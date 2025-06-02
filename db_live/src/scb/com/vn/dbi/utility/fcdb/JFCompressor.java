package scb.com.vn.dbi.utility.fcdb;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 *
 * @author system
 */
public class JFCompressor {

    private static final String VERSION_INFO = "@(#) $Header: /FC@ Java Framework 4.3/source/com/iflex/fcat/infra/JFCompressor.java 2     1/10/07 10:25a Rupeshn $";
    private Deflater deflater;
    private Inflater inflater;
    private ByteArrayOutputStream byteOut;
    private String serverLangId;

    /**
     * Create a new instance of JFCompressor
     */
    public JFCompressor() {
        this.deflater = new Deflater();
        this.inflater = new Inflater();
        this.byteOut = new ByteArrayOutputStream(5120);
        this.serverLangId = "eng";
    }

    /**
     *
     * @param p_string
     * @return
     * @throws Exception
     */
    public byte[] compress(String p_string) throws Exception {
        if ((p_string == null) || (p_string.length() == 0)) {
            throw new Exception("compress(String p_string");
        }

        byte[] l_barr = p_string.getBytes();

        return compress(l_barr, 0, l_barr.length);
    }

    /**
     *
     * @param p_data
     * @return
     * @throws Exception
     */
    public byte[] compress(byte[] p_data) throws Exception {
        if ((p_data == null) || (p_data.length == 0)) {
            throw new Exception("compress(byte[] p_data)");
        }

        return compress(p_data, 0, p_data.length);
    }

    /**
     *
     * @param p_stream
     * @return
     * @throws Exception
     */
    public byte[] compress(InputStream p_stream) throws Exception {
        byte[] l_buffer = new byte[1024];

        if (p_stream == null) {
            throw new Exception("compress(InputStream p_stream)");
        }
        int l_nbytes;
        while ((l_nbytes = p_stream.read(l_buffer)) > 0) {
            this.byteOut.write(l_buffer, 0, l_nbytes);
        }

        this.deflater.setInput(this.byteOut.toByteArray());
        this.deflater.finish();
        this.byteOut.reset();

        while ((l_nbytes = this.deflater.deflate(l_buffer)) > 0) {
            this.byteOut.write(l_buffer, 0, l_nbytes);
        }

        l_buffer = this.byteOut.toByteArray();

        this.byteOut.reset();
        this.deflater.reset();

        return l_buffer;
    }

    /**
     *
     * @param p_data
     * @param p_offset
     * @param p_length
     * @return
     * @throws Exception
     */
    public byte[] compress(byte[] p_data, int p_offset, int p_length) throws Exception {
        byte[] l_buffer = new byte[1024];

        this.deflater.setInput(p_data, p_offset, p_length);
        this.deflater.finish();
        int l_nbytes;
        while ((l_nbytes = this.deflater.deflate(l_buffer)) > 0) {
            this.byteOut.write(l_buffer, 0, l_nbytes);
        }

        l_buffer = this.byteOut.toByteArray();

        this.byteOut.reset();
        this.deflater.reset();

        return l_buffer;
    }

    /**
     *
     * @param p_data
     * @return
     * @throws Exception
     */
    public String uncompress(byte[] p_data) throws Exception {
        if ((p_data == null) || (p_data.length == 0)) {
            throw new Exception("uncompress(byte[] p_data)");
        }

        return new String(uncompressRaw(p_data, 0, p_data.length));
    }

    /**
     *
     * @param p_data
     * @param p_offset
     * @param p_length
     * @return
     * @throws Exception
     */
    public String uncompress(byte[] p_data, int p_offset, int p_length) throws Exception {
        if ((p_data == null) || (p_data.length == 0)) {
            throw new Exception("uncompress(byte[] p_data, int p_offset, int p_length)");
        }
        return new String(uncompressRaw(p_data, p_offset, p_length));
    }

    /**
     *
     * @param p_data
     * @return
     * @throws Exception
     */
    public byte[] uncompressRaw(byte[] p_data) throws Exception {
        if ((p_data == null) || (p_data.length == 0)) {
            throw new Exception("uncompressRaw(byte[] p_data)");
        }

        return uncompressRaw(p_data, 0, p_data.length);
    }

    /**
     *
     * @param p_data
     * @param p_offset
     * @param p_length
     * @return
     * @throws Exception
     */
    public byte[] uncompressRaw(byte[] p_data, int p_offset, int p_length) throws Exception {
        byte[] l_buffer = new byte[1024];

        this.inflater.setInput(p_data, p_offset, p_length);
        int l_nbytes;
        while ((l_nbytes = this.inflater.inflate(l_buffer)) > 0) {
            this.byteOut.write(l_buffer, 0, l_nbytes);
        }

        l_buffer = this.byteOut.toByteArray();

        this.byteOut.reset();
        this.inflater.reset();

        return l_buffer;
    }
}
