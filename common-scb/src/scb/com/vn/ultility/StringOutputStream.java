/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.ultility;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author FICOMBANK
 */
public class StringOutputStream extends OutputStream {

    StringBuilder mBuf;

    @Override
    public void write(int b) throws IOException {
        mBuf.append((char) b);
    }

    public String getString() {
        return mBuf.toString();
    }
}
