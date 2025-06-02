/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian;

import com.google.gson.JsonElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lydty
 */
public class HistoryTransfer_fundTransferInfo {
    String fundTransferRefNum;
    String fundTransferActiveDate;
    String fundTransferAmount;
    String fundTransferNarative;
    HistoryTransfer_accountInfo destAccountInfo;
    
    @XmlElement(name="TRANSNO")
    public String getFundTransferRefNum() {
        return fundTransferRefNum;
    }

    public void setFundTransferRefNum(String fundTransferRefNum) {
        this.fundTransferRefNum = fundTransferRefNum;
    }
     @XmlElement(name="TRANSDATE")
    public String getFundTransferActiveDate() {
        return fundTransferActiveDate;
    }

    public void setFundTransferActiveDate(String fundTransferActiveDate) {
        this.fundTransferActiveDate = fundTransferActiveDate;
    }
     @XmlElement(name="AMOUNT")
    public String getFundTransferAmount() {
        return fundTransferAmount;
    }

    public void setFundTransferAmount(String fundTransferAmount) {
        this.fundTransferAmount = fundTransferAmount;
    }
     @XmlElement(name="TRANSDESC")
    public String getFundTransferNarative() {
        return fundTransferNarative;
    }

    public void setFundTransferNarative(String fundTransferNarative) {
        this.fundTransferNarative = fundTransferNarative;
    }
    @XmlElement(name="DESTACCOUNT")
    public HistoryTransfer_accountInfo getDestAccountInfo() {
        return destAccountInfo;
    }

    public void setDestAccountInfo(HistoryTransfer_accountInfo destAccountInfo) {
        this.destAccountInfo = destAccountInfo;
    }
    

}
