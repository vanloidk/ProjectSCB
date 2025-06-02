/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.exchgbill;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author phucnnd
 */
@XmlRootElement(name = "form")
public class RequestForm {

    private String screen;
    private String idusr;
    private Request req;


    @XmlElement(name = "screen", nillable = true, required = false)
    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    @XmlElement(name = "idusr", nillable = true, required = false)
    public String getIdusr() {
        return idusr;
    }

    public void setIdusr(String idusr) {
        this.idusr = idusr;
    }

    @XmlElement(name = "req", nillable = true, required = false)
    public Request getReq() {
        return req;
    }

    public void setReq(Request req) {
        this.req = req;
    }
}
