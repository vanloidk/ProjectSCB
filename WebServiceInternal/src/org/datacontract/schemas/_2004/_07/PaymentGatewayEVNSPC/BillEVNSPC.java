/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thachdn
 */
@XmlRootElement(name = "BillEVNSPC")
public class BillEVNSPC {
    
    private ArrayList<BillEVNSPC_table> BillEVNSPCTable;
    private ArrayList<BillEVNSPC_table1> BillEVNSPCTable1;
    private ArrayList<BillEVNSPC_table2> BillEVNSPCTable2;
    private ArrayList<BillEVNSPC_table_CSPK> BillEVNSPCTableCSPK;
    
    
    @XmlElement(name = "Table")
    public ArrayList<BillEVNSPC_table> getBillEVNSPCTable() {
        return BillEVNSPCTable;
    }

    public void setBillEVNSPCTable(ArrayList<BillEVNSPC_table> BillEVNSPCTable) {
        this.BillEVNSPCTable = BillEVNSPCTable;
    }
    @XmlElement(name = "Table1")
    public ArrayList<BillEVNSPC_table1> getBillEVNSPCTable1() {
        return BillEVNSPCTable1;
    }

    public void setBillEVNSPCTable1(ArrayList<BillEVNSPC_table1> BillEVNSPCTable1) {
        this.BillEVNSPCTable1 = BillEVNSPCTable1;
    }
    @XmlElement(name = "Table2")
    public ArrayList<BillEVNSPC_table2> getBillEVNSPCTable2() {
        return BillEVNSPCTable2;
    }

    public void setBillEVNSPCTable2(ArrayList<BillEVNSPC_table2> BillEVNSPCTable2) {
        this.BillEVNSPCTable2 = BillEVNSPCTable2;
    }

    @XmlElement(name = "TableCSPK")
    public ArrayList<BillEVNSPC_table_CSPK> getBillEVNSPCTableCSPK() {
        return BillEVNSPCTableCSPK;
    }

    public void setBillEVNSPCTableCSPK(ArrayList<BillEVNSPC_table_CSPK> BillEVNSPCTableCSPK) {
        this.BillEVNSPCTableCSPK = BillEVNSPCTableCSPK;
    }
    
    
}