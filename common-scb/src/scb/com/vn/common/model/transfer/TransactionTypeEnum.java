/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer;

/**
 *
 * @author minhndb
 */
public enum TransactionTypeEnum {
    INTERNALACCOUNT("0"),
    INTERNALCARD("1"),
    INTERNALCMND("2"),
    EXTERNALACCOUNT("3")
    //, EXTERNALCARD("3")
    ;
    
    private final String text;

    private TransactionTypeEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    
    public static TransactionTypeEnum fromString(String text) {
        for (TransactionTypeEnum b : TransactionTypeEnum.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
