package scb.com.vn.common.model.payment;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Logistics {

    private String bill_code;
    private String amount;
    private String consignment_code;
    private String tax_code;
    private String container_code;
    private String data_info;
    private String ref_no;

    @XmlElement(name = "REF_NO", required = false, nillable = true)
    public String getRef_no() {
        return ref_no;
    }

    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
    }

    @XmlElement(name = "DATA_INFO", required = false, nillable = true)
    public String getData_info() {
        return data_info;
    }

    public void setData_info(String data_info) {
        this.data_info = data_info;
    }

    @XmlElement(name = "AMOUNT", required = false, nillable = true)
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @XmlElement(name = "BILL_CODE", required = false, nillable = true)
    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    @XmlElement(name = "CONSIGNMENT_CODE", required = false, nillable = true)
    public String getConsignment_code() {
        return consignment_code;
    }

    public void setConsignment_code(String consignment_code) {
        this.consignment_code = consignment_code;
    }

    @XmlElement(name = "TAX_CODE", required = false, nillable = true)
    public String getTax_code() {
        return tax_code;
    }

    public void setTax_code(String tax_code) {
        this.tax_code = tax_code;
    }

    @XmlElement(name = "CONTAINER_CODE", required = false, nillable = true)
    public String getContainer_code() {
        return container_code;
    }

    public void setContainer_code(String container_code) {
        this.container_code = container_code;
    }
}
