/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.ecom.napas;

import java.util.List;

/**
 *
 * @author tamnt12
 */
public class DataVerifyPaymentRequest {
    private Merchant merchant;
    private Transaction transaction;
    private CardInfo card;
    private Account account;
    private List<OtherInfo> otherInfo;

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public CardInfo getCard() {
        return card;
    }

    public void setCard(CardInfo card) {
        this.card = card;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<OtherInfo> getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(List<OtherInfo> otherInfo) {
        this.otherInfo = otherInfo;
    }
    
    public boolean inValid(){
        return this.transaction == null || this.transaction.inValidTrans();
    }
    
}
