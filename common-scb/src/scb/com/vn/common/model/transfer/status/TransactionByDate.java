/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer.status;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hienlt6
 */
@XmlRootElement(name = "TRN")
/*dang test tren 73.20
* doi tac: WU
 */
public class TransactionByDate {

    private List<TransactionDetailByDate> trnDetail;

    @XmlElement(name = "TRN_DETAIL")
    public List<TransactionDetailByDate> getTrnDetail() {
        return trnDetail;
    }

    public void setTrnDetail(List<TransactionDetailByDate> trnDetail) {
        this.trnDetail = trnDetail;
    }

    public void addTrnDetail(TransactionDetailByDate trnDetail) {
        if (this.trnDetail == null) {
            this.trnDetail = new ArrayList<>();
        }
        this.trnDetail.add(trnDetail);
    }

}
