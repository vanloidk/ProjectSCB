package scb.com.vn.payment;

import org.apache.log4j.Logger;

/**
 *
 * @author minhndb
 */
public class PaymentException extends RuntimeException {

    String _formatmsg = "[TYPE-MSG]-[requestPayment-%1$s]";
    Throwable _ex;

    /**
     *
     * @param s
     */
    public PaymentException(String s) {
        super(s);
        Logger.getLogger(s.getClass()).error(String.format(this._formatmsg, new Object[]{this._ex}));
    }

    /**
     *
     * @param s
     * @param cause
     */
    public PaymentException(String s, Throwable cause) {
        super(s, cause);
    }

    /**
     *
     * @param s
     */
    public PaymentException(Throwable s) {
        super(s);
        System.err.println("a");
        super.fillInStackTrace();

        System.err.println("c");
        Logger.getLogger(s.getClass()).error(String.format(this._formatmsg, new Object[]{this._ex}));
    }

    /**
     *
     * @param clz
     * @param ex
     */
    public void saveLogErrorDBs(Class clz, Throwable ex) {
    }

    /**
     *
     * @param clz
     */
    public void log(Class clz) {
        Logger.getLogger(clz).error(String.format(this._formatmsg, new Object[]{this._ex}));
    }

    static PaymentException forInputString(String s) {
        return new PaymentException("For input string: \"" + s + "\"");
    }
}
