/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.odbx;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "SCBBRANCH")
public class SCBBranch implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private List<Branch> scbBranch;

    @XmlElement(name = "BRANCHITEM", nillable = true)
    public List<Branch> getScbBranch() {
        return scbBranch;
    }

    public void setScbBranch(List<Branch> scbBranch) {
        this.scbBranch = scbBranch;
    }
    
    public void addItem(Branch item) {
        if (scbBranch == null) {
            this.scbBranch = new ArrayList<Branch>();
        }
        this.scbBranch.add(item);
    }
    
    public void addItem(String branchCode, String branchName) {
        if (scbBranch == null) {
            this.scbBranch = new ArrayList<Branch>();
        }
        Branch item = new Branch(branchCode, branchName);
        this.scbBranch.add(item);
    }
    
    public boolean isValidData() {
        return scbBranch != null && !scbBranch.isEmpty();
    }
}