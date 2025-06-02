/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import java.util.List;

/**
 *
 * @author lydty
 */
public class BidiwacoBill {
    private String error_code;
    private String error_message;
    private String num_rows;
    private String checksum;
    private List<BidiwacoBillDetail> data;
    private String result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public String getNum_rows() {
        return num_rows;
    }

    public void setNum_rows(String num_rows) {
        this.num_rows = num_rows;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public List<BidiwacoBillDetail> getData() {
        return data;
    }

    public void setData(List<BidiwacoBillDetail> data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
}
