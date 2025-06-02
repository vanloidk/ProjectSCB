/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.bhbl;

/**
 *
 * @author loinv3
 */
public class EnqueueDataRq {

    private String EnqueueHash;
    private EnqueueData EnqueueData;

    public String getEnqueueHash() {
        return EnqueueHash;
    }

    public void setEnqueueHash(String EnqueueHash) {
        this.EnqueueHash = EnqueueHash;
    }

    public EnqueueData getEnqueueData() {
        return EnqueueData;
    }

    public void setEnqueueData(EnqueueData EnqueueData) {
        this.EnqueueData = EnqueueData;
    }
}
