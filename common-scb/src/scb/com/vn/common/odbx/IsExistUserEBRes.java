/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.odbx;

import java.util.ArrayList;

/**
 *
 * @author minhndb
 */public class IsExistUserEBRes implements java.io.Serializable  {
    private static final long serialVersionUID = 1L;	
     private ArrayList<String> userList = new ArrayList<>();
     private int errorCode;

    public ArrayList<String> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<String> userList) {
        this.userList = userList;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


}
