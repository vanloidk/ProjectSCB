/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.enumUtils;

/**
 *
 * @author minhndb
 */
public enum CIMSResultEnum {

    /**
     *
     */
    SUCCESS("0"),

    /**
     *
     */
    UNKNOWNPHONE("1"),

    /**
     *
     */
    UNKNOWNCMND("2"),

    /**
     *
     */
    MULTIPLECIF("3"),

    /**
     *
     */
    MACISERROR("4"),

    /**
     *
     */
    INVALIDVALUE("5"),

    /**
     *
     */
    OUTOFTIME("6"),

    /**
     *
     */
    CARDDOESNOTFOUND("7"),

    /**
     *
     */
    LOCKCARDISERROR("8"),

    /**
     *
     */
    CARDISLOCKED("9"),

    /**
     *
     */
    UNSUCCESS("10"),

    /**
     *
     */
    IDDOESNOTFOUND("11"),
    
    OPENCARDISERROR("12"),
    
    /**
     *
     */
    SYSTEMERROR("99");

    private final String text;

    private CIMSResultEnum(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     * @return
     */
    public static CIMSResultEnum fromString(String text) {
        for (CIMSResultEnum b : CIMSResultEnum.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
