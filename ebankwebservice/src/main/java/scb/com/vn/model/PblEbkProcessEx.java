/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import scb.com.vn.common.model.payment.ResponsePayment;
import scb.com.vn.dbi.dto.PblEbkProcess;

/**
 *
 * @author FICOMBANK
 */
public class PblEbkProcessEx {

    private PblEbkProcess pep;
    private ResponsePayment respay;
    private String result;

    /**
     *
     * @return
     */
    public PblEbkProcess getPep() {
        return pep;
    }

    /**
     *
     * @param pep
     */
    public void setPep(PblEbkProcess pep) {
        this.pep = pep;
    }

    /**
     *
     * @return
     */
    public ResponsePayment getRespay() {
        return respay;
    }

    /**
     *
     * @param respay
     */
    public void setRespay(ResponsePayment respay) {
        this.respay = respay;
    }

    /**
     *
     * @return
     */
    public String getResult() {
        return result;
    }

    /**
     *
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }
}
