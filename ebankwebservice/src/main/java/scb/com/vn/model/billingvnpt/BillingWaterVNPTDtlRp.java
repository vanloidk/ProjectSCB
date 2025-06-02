/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.billingvnpt;

import java.util.List;

public class BillingWaterVNPTDtlRp {

    private String Name;
    private String Address;
    private String TotalDebt;
    private List<ListBill> ListBill;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getTotalDebt() {
        return TotalDebt;
    }

    public void setTotalDebt(String TotalDebt) {
        this.TotalDebt = TotalDebt;
    }

    public List<ListBill> getListBill() {
        return ListBill;
    }

    public void setListBill(List<ListBill> ListBill) {
        this.ListBill = ListBill;
    }

    public class ListBill {

        private String debtAmount;
        private String billMonth;
        private String additionInfo;

        public String getDebtAmount() {
            return debtAmount;
        }

        public void setDebtAmount(String debtAmount) {
            this.debtAmount = debtAmount;
        }

        public String getBillMonth() {
            return billMonth;
        }

        public void setBillMonth(String billMonth) {
            this.billMonth = billMonth;
        }

        public String getAdditionInfo() {
            return additionInfo;
        }

        public void setAdditionInfo(String additionInfo) {
            this.additionInfo = additionInfo;
        }

    }

}
