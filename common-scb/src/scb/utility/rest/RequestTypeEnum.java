/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.utility.rest;

/**
 *
 * @author dungtq
 */
public enum RequestTypeEnum {
    
    JSON("application/json"), XML("application/xml");
    private String value = null;

    private RequestTypeEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
