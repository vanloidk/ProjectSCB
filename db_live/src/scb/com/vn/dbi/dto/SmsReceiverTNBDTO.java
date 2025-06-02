package scb.com.vn.dbi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author system
 */
public class SmsReceiverTNBDTO extends ArrayList<Hashtable<String, String>> implements
        Serializable {

    private static final long serialVersionUID = -7973359251689196103L;

    /**
     *
     */
    public static String id = "ID";

    /**
     *
     */
    public static String mobile = "MOBILE";

    /**
     *
     */
    public static String contents = "CONTENTS";

    /**
     *
     */
    public static String status = "STATUS";
}
