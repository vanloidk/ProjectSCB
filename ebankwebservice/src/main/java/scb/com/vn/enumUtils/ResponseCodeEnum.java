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
public enum ResponseCodeEnum {

    /**
     *
     */
    SUCCESS("00"),
    /**
     *
     */
    ACCOUNTINVALID("01"),
    /**
     *
     */
    ACCDESTINATIONCLOSE("02"),
    /**
     *
     */
    ACCCANNOTTRANSACTION("03"),
    /**
     *
     */
    OUTOFTIME("06"),
    /**
     *
     */
    PARTNERACCOUNTWRONG("06"),
    /**
     *
     */
    PARTNERACCOUNTINVALID("07"),
    /**
     *
     */
    PARTNERACCOUNTNOTENOUGH("08"),
    /**
     *
     */
    DEDUCTIONFAIlED("09"),
    /**
     *
     */
    TRANSFAILED("15"),
    /**
     *
     */
    BANKCODE247NOTSUPPORT("17"),
    /**
     *
     */    
    MORETHANMAXAMOUNT("19"),
    /**
     *
     */
    LESSTHANMINAMOUNT("21"),
    /**
     *
     */
    CORETIMEOUT("16"),
    /**
     *
     */
    MACISERROR("98"),
    /**
     *
     */
    INVALIDVALUE("96"),
    /**
     *
     */
    INVALIDSENDER("94"),
    /**
     *
     */
    NUMBERACCPARTNERWRONG("12"),
    /**
     *
     */
    WRONGRACCREGISTEREDINSCB("13"),
    /**
     *
     */
    CANNOTEXECUTE("21"),
     /**
     *
     */
    CANNOTCALL("22"),
    /**
     *
     */
    REFUND("102"),
    /**
     *
     */
    UNKNOWSTATUS("101"),
    /**
     *
     */
    TRANSACTIONNOTEXISTS("101"),
    /**
     *
     */
    INVALIDRECEIVINGINFO("92"),    
    /**
     *
     */
    BANKCODENOTSUPPORT("93"),
    /**
     *
     */
    SYSTEMERROR("99"),
    /**
     *
     */
    KYCHOLD("100"),
    
    /**
     *
     */
    MSGIDISWRONG("97"),
    /**
     *
     */
    TRANSIDALREADYEXIST("95"),
    /**
     *
     */
    KYCTIMEOUT("102"),
    
    KYCERROR("103");  
    
    private final String text;

    private ResponseCodeEnum(String text) {
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
    public static ResponseCodeEnum fromString(String text) {
        for (ResponseCodeEnum b : ResponseCodeEnum.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
