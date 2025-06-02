/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.utility;

import java.util.Random;
import org.apache.log4j.Logger;

/**
 *
 * @author minhndb
 */
public class Utils {
    
    private static final Logger LOGGER = Logger.getLogger(Utils.class);
    
    private static final String MILI_TIME = "yyyyMMddhhmmss";
    private static final int MIN_RANDOM = 1;
    private static final int MAX_RANDOM = 999999999;

    /**
     *
     * @param key
     * @return
     */
    public static String convertChannelOfEVN(String key) {
        switch (key) {
            case "03":
                return "22";
            case "01":
                return "20";
            case "11":
            case "02":
                return "24";
            default:
                return "22";
        }
    }

    /**
     *
     * @param cardNumber
     * @return
     */
    public static String unHideCardnumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() != 16) {
            return cardNumber;
        }
        StringBuilder str = new StringBuilder();
        str.append(cardNumber.substring(0, 6));
        str.append("xxxxxx");
        str.append(cardNumber.substring(12));

        return str.toString();
    }

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

    /**
     *
     * @param phone
     * @return
     */
    public static String swapPhoneNumber(String phone) {
        String headPhone;
        String bodyPhone;
        if (phone.length() == 11) {
            headPhone = phone.substring(0, 4);
            bodyPhone = phone.substring(4);
        } else {
            headPhone = phone.substring(0, 3);
            bodyPhone = phone.substring(3);
        }

        switch (headPhone) {
            case "0120":
                return "070" + bodyPhone;
            case "070":
                return "0120" + bodyPhone;
            case "0121":
                return "079" + bodyPhone;
            case "079":
                return "0121" + bodyPhone;
            case "0122":
                return "077" + bodyPhone;
            case "077":
                return "0122" + bodyPhone;
            case "0126":
                return "076" + bodyPhone;
            case "076":
                return "0126" + bodyPhone;
            case "0128":
                return "078" + bodyPhone;
            case "078":
                return "0128" + bodyPhone;
            case "0162":
                return "032" + bodyPhone;
            case "032":
                return "0162" + bodyPhone;
            case "0163":
                return "033" + bodyPhone;
            case "033":
                return "0163" + bodyPhone;
            case "0164":
                return "034" + bodyPhone;
            case "034":
                return "0164" + bodyPhone;
            case "0165":
                return "035" + bodyPhone;
            case "035":
                return "0165" + bodyPhone;
            case "0166":
                return "036" + bodyPhone;
            case "036":
                return "0166" + bodyPhone;
            case "0167":
                return "037" + bodyPhone;
            case "037":
                return "0167" + bodyPhone;
            case "0168":
                return "038" + bodyPhone;
            case "038":
                return "0168" + bodyPhone;
            case "0169":
                return "039" + bodyPhone;
            case "039":
                return "0169" + bodyPhone;
            case "0123":
                return "083" + bodyPhone;
            case "083":
                return "0123" + bodyPhone;
            case "0124":
                return "084" + bodyPhone;
            case "084":
                return "0124" + bodyPhone;
            case "0125":
                return "085" + bodyPhone;
            case "085":
                return "0125" + bodyPhone;
            case "0127":
                return "081" + bodyPhone;
            case "081":
                return "0127" + bodyPhone;
            case "0129":
                return "082" + bodyPhone;
            case "082":
                return "0129" + bodyPhone;
            case "0186":
                return "056" + bodyPhone;
            case "056":
                return "0186" + bodyPhone;
            case "0188":
                return "058" + bodyPhone;
            case "058":
                return "0188" + bodyPhone;
            case "0199":
                return "059" + bodyPhone;
            case "059":
                return "0199" + bodyPhone;
            default:
                return phone;
        }
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    
    public static String getRandomNumber() {
        String result;
        try {
            result = DateUtils.getFormatDate(MILI_TIME);
            int randomNumber = getRandomNumberUsingNextInt(MIN_RANDOM, MAX_RANDOM);
            result += randomNumber;
        } catch (Exception e) {
            LOGGER.error(e);
            int randomNumber = getRandomNumberUsingNextInt(MIN_RANDOM, MAX_RANDOM);
            result = String.valueOf(randomNumber);
        }
        return result;
    }
}
