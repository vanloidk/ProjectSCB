/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.email;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "EMAILCC")
public class EmailCc {
    private List<String> emailDetail;

    @XmlElement(name = "EMAILDETAIL")
    public List<String> getEmailDetail() {
        return emailDetail;
    }

    public void setEmailDetail(List<String> emailDetail) {
        this.emailDetail = emailDetail;
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (emailDetail != null && !emailDetail.isEmpty()) {
            for (String detail : emailDetail) {
                str.append(detail);
            }
        }
        return str.toString();
    }
}