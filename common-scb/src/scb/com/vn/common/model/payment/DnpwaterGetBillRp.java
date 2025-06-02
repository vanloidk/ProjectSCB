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
public class DnpwaterGetBillRp {
    
    List<DnpwaterBillDetail> listBill;

    public List<DnpwaterBillDetail> getListBill() {
        return listBill;
    }

    public void setListBill(List<DnpwaterBillDetail> listBill) {
        this.listBill = listBill;
    }
}
