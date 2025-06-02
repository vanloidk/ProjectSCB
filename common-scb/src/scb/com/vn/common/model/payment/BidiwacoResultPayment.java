
package scb.com.vn.common.model.payment;

import java.util.List;

/**
 *
 * @author lydty
 */
public class BidiwacoResultPayment {
    private String error_code;
    private String error_message;
    private String num_rows;
    private String checksum;
    private List<BidiwacoResultPaymentDetail> data;
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    

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

   

    public List<BidiwacoResultPaymentDetail> getData() {
        return data;
    }

    public void setData(List<BidiwacoResultPaymentDetail> data) {
        this.data = data;
    }

    
    
    
}
