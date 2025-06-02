/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author duytxa
 */
public class RequestIBT implements java.io.Serializable {

    private String trace;
    private String request;

    /**
     *
     * @return
     */
    public String getTrace() {
        return trace;
    }

    /**
     *
     * @param trace
     */
    public void setTrace(String trace) {
        this.trace = trace;
    }

    /**
     *
     * @return
     */
    public String getRequest() {
        return request;
    }

    /**
     *
     * @param request
     */
    public void setRequest(String request) {
        this.request = request;
    }

}
