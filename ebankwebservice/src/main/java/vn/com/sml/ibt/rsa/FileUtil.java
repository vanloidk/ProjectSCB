package vn.com.sml.ibt.rsa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Do read file to bytes & write bytes to file Source: java2s.com
 *
 * @author quangtt
 * @version 1.0
 * @since 17/02/2011
 */
public class FileUtil {

    /**
     * Writes the bytes of the key in a file.
     *
     * @param key byte array of key data.
     * @param file File Name
     * @throws java.io.IOException
     */
    public static void writeBytesToFile(byte[] key, String file) throws
            IOException {
        OutputStream out = new FileOutputStream(file);
        out.write(key);
        out.close();
    }

    /**
     * Returns the contents of the file in a byte array.
     *
     * @param fileName File Name
     * @return byte[] Teh data read from a given file as a byte array.
     * @throws java.io.IOException
     */
    public static byte[] readBytesFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Key File Error: Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;

    }
}
