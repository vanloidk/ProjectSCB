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
public enum EmailResultEnum {

    /**
     *
     */
    SUCCESS("0"),

    /**
     *
     */
    FAILTOSENDEMAIL("1"),

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
    SYSTEMERROR("99");

    private final String text;

    private EmailResultEnum(String text) {
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
    public static EmailResultEnum fromString(String text) {
        for (EmailResultEnum b : EmailResultEnum.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
