/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.collated;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hienlt6
 */
@XmlRootElement(name = "TRANSSACTION")
public class Collated implements Serializable{

    private List<CollatedDetail> detail;

    @XmlElement(name = "TRANSSACTION_DETAIL")
    public List<CollatedDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<CollatedDetail> detail) {
        this.detail = detail;
    }

}
