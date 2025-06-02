/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.ExchangeRate;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author minhndb
 */
public class ExchangeRateRes implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String product;
    private BigDecimal money;
    private String ccy;
    private String ccyExchange = "VND";
    private BigDecimal rate;
    private BigDecimal moneyExchange;
    private String resultCode;
    private String resultDesc;

    public ExchangeRateRes() {}

    public ExchangeRateRes(BigDecimal money, BigDecimal rate) {
        this.money = money;
        this.rate = rate;
        this.moneyExchange = money.multiply(rate);
    }
    
    public ExchangeRateRes(BigDecimal money, BigDecimal rate, String ccy, String ccyExchange
            , String resultCode) {
        this.money = money;
        this.rate = rate;
        this.ccy = ccy;
        this.ccyExchange = ccyExchange;
        this.moneyExchange = money.multiply(rate);
        this.resultCode = resultCode;
    }
    
    public ExchangeRateRes(String product, BigDecimal money, BigDecimal rate, String ccy, String ccyExchange
            , String resultCode) {
        this.product = product;
        this.money = money;
        this.rate = rate;
        this.ccy = ccy;
        this.ccyExchange = ccyExchange;
        this.moneyExchange = money.multiply(rate);
        this.resultCode = resultCode;
    }
    
    
    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getMoneyExchange() {
        return moneyExchange;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public String getProduct() {
        return product;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public String getCcy() {
        return ccy;
    }

    public String getCcyExchange() {
        return ccyExchange;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public void setMoneyExchange(BigDecimal moneyExchange) {
        this.moneyExchange = moneyExchange;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public void setCcyExchange(String ccyExchange) {
        this.ccyExchange = ccyExchange;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("product = [").append(product).append("], ");
        str.append("money = [").append(money).append("], ");
        str.append("ccy = [").append(ccy).append("], ");
        str.append("ccyExchange = [").append(ccyExchange).append("], ");
        str.append("rate = [").append(rate).append("], ");
        str.append("moneyExchange = [").append(moneyExchange).append("], ");
        str.append("resultCode = [").append(resultCode).append("], ");
        str.append("resultDesc = [").append(resultDesc).append("]");
        return str.toString();
    }
}