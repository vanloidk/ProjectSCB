/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.utility.rest;

/**
 *
 * @author kimncm
 */

public enum RequestMethod {
    GET("GET"), POST("POST"), DELETE("DELETE"), PUT("PUT"), PATCH("PATCH");
    private String requestMethod;

    private RequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public static RequestMethod fromValue(String value) {
        for (RequestMethod bt : RequestMethod.values()) {
            if (bt.requestMethod.equals(value)) {
                return bt;
            }
        }
        return null;
    }
}
