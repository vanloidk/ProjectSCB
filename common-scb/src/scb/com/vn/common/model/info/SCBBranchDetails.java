/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.info;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "SCBBRANCH")
public class SCBBranchDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<SCBBranchDetail> trnDetail;

    @XmlElement(name = "BRANCHITEM")
    public List<SCBBranchDetail> getTrnDetail() {
        return trnDetail;
    }

    public void setTrnDetail(List<SCBBranchDetail> trnDetail) {
        this.trnDetail = trnDetail;
    }
}