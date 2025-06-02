/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian;

/**
 *
 * @author minhndb
 */
public class ExchangeRateInfoType {
    private String rate;

    public ExchangeRateInfoType() {}

    public ExchangeRateInfoType(String rate) {
        this.rate = rate;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}