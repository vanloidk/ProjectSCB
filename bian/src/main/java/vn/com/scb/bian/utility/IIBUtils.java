/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.utility;

import vn.com.scb.bian.TransactionInfoType;

/**
 *
 * @author scb
 */
public class IIBUtils {
    
     public static TransactionInfoType getTransInfpTypeCommon(String channel) {
        TransactionInfoType transactionInfo = new TransactionInfoType();
        transactionInfo.setClientCode(channel);
        transactionInfo.setCRefNum(channel.substring(0, 2).concat(String.valueOf(System.currentTimeMillis())));
        return transactionInfo;

    }
     /*
      public static String getBranchAccount(String account) {
        if (account.length() == 9) {
            return "000";
        }
        if (account == null || account == "") {
            return "";
        }
        if (account.length() < 3) {
            return "";
        }

        return account.substring(0, 3);
    }*/
}
