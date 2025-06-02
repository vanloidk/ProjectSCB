/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.utility;

/**
 *
 * @author kimncm
 */
public class BianException extends Exception
{
    private int code;
    
    private String description;
    
    public BianException(int code, String description)
    {
        this.code = code;
        this.description = description;
    }
    
    public BianException(String message) {
        super(message);
    }
     public BianException(String errorCode, String errorMessage) {
        super(errorCode,new Throwable(errorMessage));
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }
     
}
