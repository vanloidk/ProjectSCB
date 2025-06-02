/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.mvisa;

/**
 *
 * @author minhndb
 */
public class ErrorMessage implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
    public ResponseStatus responseStatus;

    public ResponseStatus getResponseStatus()
    {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus)
    {
        this.responseStatus = responseStatus;
    }   
}