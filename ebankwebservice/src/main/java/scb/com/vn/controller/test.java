/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

/**
 *
 * @author loinv3
 */
public class test {

    private boolean delivered = false;
    
    public boolean isDelivered() {
        return delivered;
    }

    public String getDelivered() {
        return delivered ? "0" : "1";
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

}
