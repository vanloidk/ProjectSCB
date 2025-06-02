/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author thachdn
 */
public class RequestPrint {
    private String testVal;
    private PrintBillEVNSPC printbillEVNSPC;
    
    @XmlElement(name = "TESTVAL", required = false, nillable = true)
    public String getTestVal() {
        return testVal;
    }

    public void setTestVal(String testVal) {
        this.testVal = testVal;
    }

    public PrintBillEVNSPC getPrintbillEVNSPC() {
        return printbillEVNSPC;
    }

    public void setPrintbillEVNSPC(PrintBillEVNSPC printbillEVNSPC) {
        this.printbillEVNSPC = printbillEVNSPC;
    }
    
}
