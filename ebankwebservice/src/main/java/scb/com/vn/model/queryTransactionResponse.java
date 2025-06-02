/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "QUERYCUSTOMERMBRESPONSE")
public class queryTransactionResponse {

    int ReturnCode;
    //detail
    private String customerno;
    private TransactionDetail[] Transactions;

    /**
     *
     */
    public queryTransactionResponse() {
    }
}
