/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.exchgbill;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author phucnnd
 */
public class Response {

    private String transcode;
    private String result;
    private String data;

    @XmlElement(name = "transcode", nillable = true, required = false)
    public String getTranscode() {
        return transcode;
    }

    public void setTranscode(String transcode) {
        this.transcode = transcode;
    }

    @XmlElement(name = "data", nillable = true, required = false)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @XmlElement(name = "result", nillable = true, required = false)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
