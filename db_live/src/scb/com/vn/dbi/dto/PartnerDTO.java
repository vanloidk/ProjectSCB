package scb.com.vn.dbi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author system
 */
public class PartnerDTO extends ArrayList<Hashtable<String, String>> implements
        Serializable {

    private static final long serialVersionUID = -7973359251689196103L;

    /**
     *
     */
    public static String accesskey = "accesskey";

    /**
     *
     */
    public static String servicetype = "servicetype";

    /**
     *
     */
    public static String partnercode = "partnercode";

    /**
     *
     */
    public static String signature = "signature";

    /**
     *
     */
    public static String ip = "ip";
}
