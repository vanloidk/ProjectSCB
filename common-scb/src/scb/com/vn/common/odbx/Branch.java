/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.odbx;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "BRANCHITEM")
public class Branch implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String branchCode;
    private String branchName;
    
    public Branch() {}

    public Branch(String branchCode, String branchName) {
        this.branchCode = branchCode;
        this.branchName = branchName;
    }
    
    @XmlElement(name = "BRANCHCODE", nillable = true)
    public String getBranchCode() {
        return branchCode;
    }

    @XmlElement(name = "BRANCHNAME", nillable = true)
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
