/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.partner.dto;

/**
 *
 * @author loinv3
 */
public class PartnerTransactionInfoDto implements java.io.Serializable {

    private static final long serialVersionUID = -1601367309834759824L;

    private selectTransactionInfoPartner_in selectTransactionInfoPartner_in;

    public selectTransactionInfoPartner_in getSelectTransactionInfoPartner_in() {
        return selectTransactionInfoPartner_in;
    }

    public void setSelectTransactionInfoPartner_in(selectTransactionInfoPartner_in selectTransactionInfoPartner_in) {
        this.selectTransactionInfoPartner_in = selectTransactionInfoPartner_in;
    }

    public static class selectTransactionInfoPartner_in {

        private transactionInfo transactionInfo;
        private searchInfo searchInfo;
        private accountInfo accountInfo;

        public transactionInfo getTransactionInfo() {
            return transactionInfo;
        }

        public void setTransactionInfo(transactionInfo transactionInfo) {
            this.transactionInfo = transactionInfo;
        }

        public searchInfo getSearchInfo() {
            return searchInfo;
        }

        public void setSearchInfo(searchInfo searchInfo) {
            this.searchInfo = searchInfo;
        }

        public accountInfo getAccountInfo() {
            return accountInfo;
        }

        public void setAccountInfo(accountInfo accountInfo) {
            this.accountInfo = accountInfo;
        }
    }

    /**
     * Dto 01
     */
    public static class transactionInfo {

        private String clientCode;
        private String cRefNum;

        public String getClientCode() {
            return clientCode;
        }

        public void setClientCode(String clientCode) {
            this.clientCode = clientCode;
        }

        public String getcRefNum() {
            return cRefNum;
        }

        public void setcRefNum(String cRefNum) {
            this.cRefNum = cRefNum;
        }

    }

    /**
     * Dto 02
     */
    public static class searchInfo {

        private String fromDate;
        private String toDate;
        private String keyWord;
        private String partnerName;

        public String getFromDate() {
            return fromDate;
        }

        public void setFromDate(String fromDate) {
            this.fromDate = fromDate;
        }

        public String getToDate() {
            return toDate;
        }

        public void setToDate(String toDate) {
            this.toDate = toDate;
        }

        public String getKeyWord() {
            return keyWord;
        }

        public void setKeyWord(String keyWord) {
            this.keyWord = keyWord;
        }

        public String getPartnerName() {
            return partnerName;
        }

        public void setPartnerName(String partnerName) {
            this.partnerName = partnerName;
        }
    }

    /**
     * Dto 03
     */
    public static class accountInfo {

        private String accountNum;

        public String getAccountNum() {
            return accountNum;
        }

        public void setAccountNum(String accountNum) {
            this.accountNum = accountNum;
        }
    }

}
