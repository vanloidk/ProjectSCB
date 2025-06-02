/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.info;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "BRANCHITEM")
public class SCBBranchDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String branchCode;
    private String branchName;

    @XmlElement(name = "BRANCHCODE")
    public String getBranchCode() {
        return branchCode;
    }

    @XmlElement(name = "BRANCHNAME")
    public String getBranchName() {
        return branchName;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}