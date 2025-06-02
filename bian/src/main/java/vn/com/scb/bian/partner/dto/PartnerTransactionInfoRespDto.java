/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.partner.dto;

import java.util.List;

/**
 *
 * @author loinv3
 */
public class PartnerTransactionInfoRespDto implements java.io.Serializable {

    private static final long serialVersionUID = -1601367309834759824L;

    private selectTransactionInfoPartner_out selectTransactionInfoPartner_out;

    public selectTransactionInfoPartner_out getSelectTransactionInfoPartner_out() {
        return selectTransactionInfoPartner_out;
    }

    public void setSelectTransactionInfoPartner_out(selectTransactionInfoPartner_out selectTransactionInfoPartner_out) {
        this.selectTransactionInfoPartner_out = selectTransactionInfoPartner_out;
    }

    /**
     * Main dto
     */
    public static class selectTransactionInfoPartner_out {

        private transactionInfo transactionInfo;
        private List<results> results;

        public transactionInfo getTransactionInfo() {
            return transactionInfo;
        }

        public void setTransactionInfo(transactionInfo transactionInfo) {
            this.transactionInfo = transactionInfo;
        }

        public List<results> getResults() {
            return results;
        }

        public void setResults(List<results> results) {
            this.results = results;
        }
    }

    protected static class branchInfo {

        private String branchInfo;

        public String getBranchInfo() {
            return branchInfo;
        }

        public void setBranchInfo(String branchInfo) {
            this.branchInfo = branchInfo;
        }
    }

    /**
     * Dto01
     */
    public static class transactionInfo {

        private String cRefNum;
        private String pRefNum;
        private String transactionStartTime;
        private String transactionCompletedTime;
        private String transactionErrorCode;
        private String transactionErrorMsg;
        private String transactionReturn;
        private String transactionReturnMsg;
        private String clientCode;

        public String getcRefNum() {
            return cRefNum;
        }

        public void setcRefNum(String cRefNum) {
            this.cRefNum = cRefNum;
        }

        public String getpRefNum() {
            return pRefNum;
        }

        public void setpRefNum(String pRefNum) {
            this.pRefNum = pRefNum;
        }

        public String getTransactionStartTime() {
            return transactionStartTime;
        }

        public void setTransactionStartTime(String transactionStartTime) {
            this.transactionStartTime = transactionStartTime;
        }

        public String getTransactionCompletedTime() {
            return transactionCompletedTime;
        }

        public void setTransactionCompletedTime(String transactionCompletedTime) {
            this.transactionCompletedTime = transactionCompletedTime;
        }

        public String getTransactionErrorCode() {
            return transactionErrorCode;
        }

        public void setTransactionErrorCode(String transactionErrorCode) {
            this.transactionErrorCode = transactionErrorCode;
        }

        public String getTransactionErrorMsg() {
            return transactionErrorMsg;
        }

        public void setTransactionErrorMsg(String transactionErrorMsg) {
            this.transactionErrorMsg = transactionErrorMsg;
        }

        public String getTransactionReturn() {
            return transactionReturn;
        }

        public void setTransactionReturn(String transactionReturn) {
            this.transactionReturn = transactionReturn;
        }

        public String getTransactionReturnMsg() {
            return transactionReturnMsg;
        }

        public void setTransactionReturnMsg(String transactionReturnMsg) {
            this.transactionReturnMsg = transactionReturnMsg;
        }

        public String getClientCode() {
            return clientCode;
        }

        public void setClientCode(String clientCode) {
            this.clientCode = clientCode;
        }

    }

    /**
     * Dto02
     */
    public static class results {

        private String SO_THU_TU_GD;
        private String THOI_GIAN_GD;
        private String NGAY_GIAO_DICH;
        private String TY_GIA;
        private String LOAI_TIEN;
        private String SO_TIEN_NGTE;
        private String MA_DON_VI_TK;
        private String PS_NO_CO;
        private String DIEN_GIAI;
        private String SO_TIEN_QDOI;
        private String SO_TAI_KHOAN;
        private String SO_GIAO_DICH;

        public String getSO_THU_TU_GD() {
            return SO_THU_TU_GD;
        }

        public void setSO_THU_TU_GD(String SO_THU_TU_GD) {
            this.SO_THU_TU_GD = SO_THU_TU_GD;
        }

        public String getTHOI_GIAN_GD() {
            return THOI_GIAN_GD;
        }

        public void setTHOI_GIAN_GD(String THOI_GIAN_GD) {
            this.THOI_GIAN_GD = THOI_GIAN_GD;
        }

        public String getNGAY_GIAO_DICH() {
            return NGAY_GIAO_DICH;
        }

        public void setNGAY_GIAO_DICH(String NGAY_GIAO_DICH) {
            this.NGAY_GIAO_DICH = NGAY_GIAO_DICH;
        }

        public String getTY_GIA() {
            return TY_GIA;
        }

        public void setTY_GIA(String TY_GIA) {
            this.TY_GIA = TY_GIA;
        }

        public String getLOAI_TIEN() {
            return LOAI_TIEN;
        }

        public void setLOAI_TIEN(String LOAI_TIEN) {
            this.LOAI_TIEN = LOAI_TIEN;
        }

        public String getSO_TIEN_NGTE() {
            return SO_TIEN_NGTE;
        }

        public void setSO_TIEN_NGTE(String SO_TIEN_NGTE) {
            this.SO_TIEN_NGTE = SO_TIEN_NGTE;
        }

        public String getMA_DON_VI_TK() {
            return MA_DON_VI_TK;
        }

        public void setMA_DON_VI_TK(String MA_DON_VI_TK) {
            this.MA_DON_VI_TK = MA_DON_VI_TK;
        }

        public String getPS_NO_CO() {
            return PS_NO_CO;
        }

        public void setPS_NO_CO(String PS_NO_CO) {
            this.PS_NO_CO = PS_NO_CO;
        }

        public String getDIEN_GIAI() {
            return DIEN_GIAI;
        }

        public void setDIEN_GIAI(String DIEN_GIAI) {
            this.DIEN_GIAI = DIEN_GIAI;
        }

        public String getSO_TIEN_QDOI() {
            return SO_TIEN_QDOI;
        }

        public void setSO_TIEN_QDOI(String SO_TIEN_QDOI) {
            this.SO_TIEN_QDOI = SO_TIEN_QDOI;
        }

        public String getSO_TAI_KHOAN() {
            return SO_TAI_KHOAN;
        }

        public void setSO_TAI_KHOAN(String SO_TAI_KHOAN) {
            this.SO_TAI_KHOAN = SO_TAI_KHOAN;
        }

        public String getSO_GIAO_DICH() {
            return SO_GIAO_DICH;
        }

        public void setSO_GIAO_DICH(String SO_GIAO_DICH) {
            this.SO_GIAO_DICH = SO_GIAO_DICH;
        }

    }

}
