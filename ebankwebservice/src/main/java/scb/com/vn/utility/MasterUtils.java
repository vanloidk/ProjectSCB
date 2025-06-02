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
public class MasterUtils {

    /**
     *
     * @param cardNumber
     * @return
     */
    public static String hideCardnumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() != 16) {
            return cardNumber;
        }
        StringBuilder str = new StringBuilder();
        str.append(cardNumber.substring(0, 6));
        str.append("xxxxxx");
        str.append(cardNumber.substring(12));

        return str.toString();
    }
}
