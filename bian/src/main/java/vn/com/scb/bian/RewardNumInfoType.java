/**
 * TransactionInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

import com.google.gson.annotations.Expose;

public class RewardNumInfoType  implements java.io.Serializable {
    
    /* fromNum */  
    private java.lang.String fromNum;

    /* toNum */      
    private java.lang.String toNum;

    /* programCode */      
    private java.lang.String programCode;    
 
    public RewardNumInfoType() {
    }

    public RewardNumInfoType(
           java.lang.String fromNum,
           java.lang.String toNum,
           java.lang.String programCode) {
           this.fromNum = fromNum;
           this.toNum = toNum;
           this.programCode = programCode;
    }

    /**
     * @return the fromNum
     */
    public java.lang.String getFromNum() {
        return fromNum;
    }

    /**
     * @param fromNum the fromNum to set
     */
    public void setFromNum(java.lang.String fromNum) {
        this.fromNum = fromNum;
    }

    /**
     * @return the toNum
     */
    public java.lang.String getToNum() {
        return toNum;
    }

    /**
     * @param toNum the toNum to set
     */
    public void setToNum(java.lang.String toNum) {
        this.toNum = toNum;
    }

    /**
     * @return the programCode
     */
    public java.lang.String getProgramCode() {
        return programCode;
    }

    /**
     * @param programCode the programCode to set
     */
    public void setProgramCode(java.lang.String programCode) {
        this.programCode = programCode;
    }



}
