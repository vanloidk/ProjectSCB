/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author minhndb
 */
public class IbtUtils {

    /**
     *
     * @return
     */
    public static List<String> getNtAccount() {
        String[] account = Configuration.getProperty("fcubs.producttransfer.kieuhoi.account").split(";");
        List<String> accounts = new ArrayList<>();
        Collections.addAll(accounts, account);
        return accounts;
    }
}
