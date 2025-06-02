/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

/**
 *
 * @author minhndb
 */
public enum KYCErrorMsg {

    /**
     *
     */
    LOW,

    /**
     *
     */
    MEDIUM,

    /**
     *
     */
    HIGH,

    /**
     *
     */
    UNACCEPT,
    /**
     *
     */
    KYCEXCEPTION;

    /**
     *
     * @param value
     * @return
     */
    public static KYCErrorMsg getKYCError(String value) {
        switch (value) {
            case "LOW":
                return LOW;
            case "MEDIUM":
                return MEDIUM;
            case "HIGH":
                return HIGH;
            case "UNACCEPT":
                return UNACCEPT;
            default:
                return UNACCEPT;
        }
    }
}
