/**
 * TransactionInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

import com.google.gson.annotations.Expose;

public class RewardNumListType  implements java.io.Serializable {
    
    /* fromNum */  
    private RewardNumInfoType rewardNumInfo;

  
 
    public RewardNumListType() {
    }

    public RewardNumListType(
           RewardNumInfoType rewardNumInfo) {
           this.rewardNumInfo = rewardNumInfo;
        
    }

    /**
     * @return the rewardNumInfo
     */
    public RewardNumInfoType getRewardNumInfo() {
        return rewardNumInfo;
    }

    /**
     * @param rewardNumInfo the rewardNumInfo to set
     */
    public void setRewardNumInfo(RewardNumInfoType rewardNumInfo) {
        this.rewardNumInfo = rewardNumInfo;
    }
    

}
