package scb.com.vn.dbi.dto;

import java.io.Serializable;

/**
 *
 * @author system
 */
public class SendSmsDTO implements Serializable {

    private static final long serialVersionUID = -1416985413241424635L;

    /**
     *
     */
    public String pi_account;

    /**
     *
     */
    public String pi_mobile;

    /**
     *
     */
    public String pi_content;

    /**
     *
     */
    public String pi_service_code;

    /**
     *
     */
    public String pi_requestid;

    /**
     *
     */
    public int p_out;

}
