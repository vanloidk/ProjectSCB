/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.status.transferMoney;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author hienlt6
 */
public class DetailStatusReq {

    private static final Logger logger = Logger.getLogger(DetailStatusReq.class);
    private static final long serialVersionUID = 1L;

    private String transId;
    private String txnDetailId;
    private String status; //00:da tru tien, 09:tru tien that bai
    private String partnerId;
    private String typeTransfer; //01:trong he thong, 02: ngoai he thong, 03: ck nhanh
    private String typeCustAccount; // 01:the, 02:tai khoan, 03:cmnd

    private final Date transDate = new Date();

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getTxnDetailId() {
        return txnDetailId;
    }

    public void setTxnDetailId(String txnDetailId) {
        this.txnDetailId = txnDetailId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getTypeTransfer() {
        return typeTransfer;
    }

    public void setTypeTransfer(String typeTransfer) {
        this.typeTransfer = typeTransfer;
    }

    public String getTypeCustAccount() {
        return typeCustAccount;
    }

    public void setTypeCustAccount(String typeCustAccount) {
        this.typeCustAccount = typeCustAccount;
    }

    public Date getTransDate() {
        return transDate;
    }

    public String getTransDate(String partern) {
        SimpleDateFormat formatter;
        try {
            formatter = new SimpleDateFormat(partern);
        } catch (Exception e) {
            logger.error(e);
            formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        }
        return formatter.format(this.transDate);
    }
}
