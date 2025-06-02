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
public class ResponseForm {

    private String screen;
    private Response resp;

    @XmlElement(name = "screen", nillable = true, required = false)
    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    @XmlElement(name = "resp", nillable = true, required = false)
    public Response getResp() {
        return resp;
    }

    public void setResp(Response resp) {
        this.resp = resp;
    }
}
