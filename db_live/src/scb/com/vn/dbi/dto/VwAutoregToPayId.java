/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author FICOMBANK
 */
public class VwAutoregToPayId implements java.io.Serializable {

    private String idcontract;
    private String custAccNo;
    private String idservicetype;
    private String idprovider;
    private String customercode;

    /**
     * Create a new instance of VwAutoregToPayId
     */
    public VwAutoregToPayId() {
    }

    /**
     *
     * @param idcontract
     * @param custAccNo
     */
    public VwAutoregToPayId(String idcontract, String custAccNo) {
        this.idcontract = idcontract;
        this.custAccNo = custAccNo;
    }

    /**
     *
     * @return
     */
    public String getIdcontract() {
        return idcontract;
    }

    /**
     *
     * @param idcontract
     */
    public void setIdcontract(String idcontract) {
        this.idcontract = idcontract;
    }

    /**
     *
     * @return
     */
    public String getCustAccNo() {
        return custAccNo;
    }

    /**
     *
     * @param custAccNo
     */
    public void setCustAccNo(String custAccNo) {
        this.custAccNo = custAccNo;
    }

    /**
     *
     * @return
     */
    public String getIdservicetype() {
        return idservicetype;
    }

    /**
     *
     * @param idservicetype
     */
    public void setIdservicetype(String idservicetype) {
        this.idservicetype = idservicetype;
    }

    /**
     *
     * @return
     */
    public String getIdprovider() {
        return idprovider;
    }

    /**
     *
     * @param idprovider
     */
    public void setIdprovider(String idprovider) {
        this.idprovider = idprovider;
    }

    /**
     *
     * @return
     */
    public String getCustomercode() {
        return customercode;
    }

    /**
     *
     * @param customercode
     */
    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }
}
