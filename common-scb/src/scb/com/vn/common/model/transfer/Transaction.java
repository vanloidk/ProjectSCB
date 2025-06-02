/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "TRN")
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<TransactionDetail> trnDetail;

    @XmlElement(name = "TRN_DETAIL")
    public List<TransactionDetail> getTrnDetail() {
        return trnDetail;
    }

    public void setTrnDetail(List<TransactionDetail> trnDetail) {
        this.trnDetail = trnDetail;
    }
    
    public void addTrnDetail(TransactionDetail trnDetail) {
        if (this.trnDetail == null) {
            this.trnDetail = new ArrayList<>();
        }
        this.trnDetail.add(trnDetail);
    }
}