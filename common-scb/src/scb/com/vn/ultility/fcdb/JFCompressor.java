package scb.com.vn.ultility.fcdb;

import java.io.ByteArrayOutputStream;

import java.io.InputStream;

import java.util.zip.Deflater;

import java.util.zip.Inflater;

public class JFCompressor
// implements JFInfraConstants
{
	private static final String VERSION_INFO = "@(#) $Header: /FC@ Java Framework 4.3/source/com/iflex/fcat/infra/JFCompressor.java 2     1/10/07 10:25a Rupeshn $";
	private Deflater deflater;
	private Inflater inflater;
	private ByteArrayOutputStream byteOut;
	private String serverLangId;

	public JFCompressor() {
		this.deflater = new Deflater();
		this.inflater = new Inflater();
		/* 103 */this.byteOut = new ByteArrayOutputStream(5120);
		// /* */ try {
		// /* 105 */ this.serverLangId =
		// JFProperties.getProperty("FCAT.LANG.ID", "eng");
		// /* */ }
		// /* */ catch (Exception e)
		// /* */ {
		/* 110 */this.serverLangId = "eng";
		// /* */ }
	}

	public byte[] compress(String p_string) throws Exception {
		/* 134 */if ((p_string == null) || (p_string.length() == 0)) {
			/* 135 */throw new Exception("compress(String p_string");
		}

		/* 149 */byte[] l_barr = p_string.getBytes();

		/* 151 */return compress(l_barr, 0, l_barr.length);
	}

	public byte[] compress(byte[] p_data) throws Exception {
		/* 172 */if ((p_data == null) || (p_data.length == 0)) {
			/* 173 */throw new Exception("compress(byte[] p_data)");
		}

		/* 187 */return compress(p_data, 0, p_data.length);
	}

	public byte[] compress(InputStream p_stream) throws Exception {
		/* 217 */byte[] l_buffer = new byte[1024];

		/* 220 */if (p_stream == null)
			/* 221 */throw new Exception("compress(InputStream p_stream)");
		int l_nbytes;
		/* 236 */while ((l_nbytes = p_stream.read(l_buffer)) > 0) {
			/* 237 */this.byteOut.write(l_buffer, 0, l_nbytes);
		}

		/* 240 */this.deflater.setInput(this.byteOut.toByteArray());
		/* 241 */this.deflater.finish();
		/* 242 */this.byteOut.reset();

		/* 244 */while ((l_nbytes = this.deflater.deflate(l_buffer)) > 0) {
			/* 245 */this.byteOut.write(l_buffer, 0, l_nbytes);
		}

		/* 248 */l_buffer = this.byteOut.toByteArray();

		/* 250 */this.byteOut.reset();
		/* 251 */this.deflater.reset();

		/* 253 */return l_buffer;
	}

	public byte[] compress(byte[] p_data, int p_offset, int p_length) throws Exception {
		/* 287 */byte[] l_buffer = new byte[1024];

		/* 290 */this.deflater.setInput(p_data, p_offset, p_length);
		/* 291 */this.deflater.finish();
		int l_nbytes;
		/* 292 */while ((l_nbytes = this.deflater.deflate(l_buffer)) > 0) {
			/* 293 */this.byteOut.write(l_buffer, 0, l_nbytes);
		}

		/* 296 */l_buffer = this.byteOut.toByteArray();

		/* 298 */this.byteOut.reset();
		/* 299 */this.deflater.reset();

		/* 301 */return l_buffer;
	}

	public String uncompress(byte[] p_data) throws Exception {
		/* 322 */if ((p_data == null) || (p_data.length == 0)) {
			/* 323 */throw new Exception("uncompress(byte[] p_data)");
		}

		/* 337 */return new String(uncompressRaw(p_data, 0, p_data.length));
	}

	public String uncompress(byte[] p_data, int p_offset, int p_length) throws Exception {
		/* 362 */if ((p_data == null) || (p_data.length == 0)) {
			/* 363 */throw new Exception("uncompress(byte[] p_data, int p_offset, int p_length)");
		}

		/* 377 */return new String(uncompressRaw(p_data, p_offset, p_length));
	}

	public byte[] uncompressRaw(byte[] p_data) throws Exception {
		/* 399 */if ((p_data == null) || (p_data.length == 0)) {
			/* 400 */throw new Exception("uncompressRaw(byte[] p_data)");
		}

		/* 414 */return uncompressRaw(p_data, 0, p_data.length);
	}

	public byte[] uncompressRaw(byte[] p_data, int p_offset, int p_length) throws Exception {
		/* 449 */byte[] l_buffer = new byte[1024];

		/* 452 */this.inflater.setInput(p_data, p_offset, p_length);
		int l_nbytes;
		/* 453 */while ((l_nbytes = this.inflater.inflate(l_buffer)) > 0) {
			/* 454 */this.byteOut.write(l_buffer, 0, l_nbytes);
		}

		/* 457 */l_buffer = this.byteOut.toByteArray();

		/* 459 */this.byteOut.reset();
		/* 460 */this.inflater.reset();

		/* 462 */return l_buffer;
	}

}

/*
 * Location: E:\Dbanking4\system\build\kernel\FCDB_6.0.0.0.0.0.1\ Qualified
 * Name: com.iflex.fcat.infra.JFCompressor JD-Core Version: 0.6.0
 */