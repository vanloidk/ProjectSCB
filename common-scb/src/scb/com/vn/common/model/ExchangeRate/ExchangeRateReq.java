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
public class ExchangeRateReq implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String product;
    private String branch;
    private String ccy;
    private BigDecimal money;

    public ExchangeRateReq() {}

    public ExchangeRateReq(String product, String branch, String ccy, BigDecimal money) {
        this.product = product;
        this.branch = branch;
        this.ccy = ccy;
        this.money = money;
    }

    public String getBranch() {
        return branch;
    }

    public String getCcy() {
        return ccy;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public String getProduct() {
        return product;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("product = [").append(product).append("], ");
        str.append("branch = [").append(branch).append("], ");
        str.append("ccy = [").append(ccy).append("], ");
        str.append("money = [").append(money).append("]");
        return str.toString();
    }
}