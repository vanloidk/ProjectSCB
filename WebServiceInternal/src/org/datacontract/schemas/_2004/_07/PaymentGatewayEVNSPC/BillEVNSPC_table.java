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

public class BillEVNSPC_table {
    
    private List<BillEVNSPC_table2> BillEVNSPCTable2s;
    private List<BillEVNSPC_table1> BillEVNSPCTable1s;
    private String MA_GD;
    private String HOADONID;
    private String TEN_CTY_DL;
    private String TEN_DL;
    private String MA_KHANG;
    private String TEN_KHANG;
    private String DCHI_KHANG;

    private String TYLE_THUE;
    private String KIHIEU_SERY;
    private String SO_SERY;
    private String KY;
    private String THANG;

    private String NAM;
    private int SO_CTO;
    private int SO_HO;
    private Long SO_TIEN;
    private Long TIEN_GTGT;
    
    @XmlElement(name = "MAGD")
    public String getMA_GD() {
        return MA_GD;
    }

    public void setMA_GD(String MA_GD) {
        this.MA_GD = MA_GD;
    }
    
    @XmlElement(name = "HOADONID")
    public String getHOADONID() {
        return HOADONID;
    }

    public void setHOADONID(String HOADONID) {
        this.HOADONID = HOADONID;
    }

    @XmlElement(name = "Table2")
    public List<BillEVNSPC_table2> getBillEVNSPCTable2() {
        return BillEVNSPCTable2s;
    }

    public void setBillEVNSPCTable2(List<BillEVNSPC_table2> BillEVNSPCTable2) {
        this.BillEVNSPCTable2s = BillEVNSPCTable2;
    }
    @XmlElement(name = "Table1")
    public List<BillEVNSPC_table1> getBillEVNSPCTable1() {
        return BillEVNSPCTable1s;
    }

    public void setBillEVNSPCTable1(List<BillEVNSPC_table1> BillEVNSPCTable1) {
        this.BillEVNSPCTable1s = BillEVNSPCTable1;
    }
    
    @XmlElement(name = "TEN_CTY_DL")
    public String getTEN_CTY_DL() {
        return TEN_CTY_DL;
    }

    public void setTEN_CTY_DL(String TEN_CTY_DL) {
        this.TEN_CTY_DL = TEN_CTY_DL;
    }

    @XmlElement(name = "TEN_DL")
    public String getTEN_DL() {
        return TEN_DL;
    }

    public void setTEN_DL(String TEN_DL) {
        this.TEN_DL = TEN_DL;
    }

    @XmlElement(name = "MA_KHANG")
    public String getMA_KHANG() {
        return MA_KHANG;
    }

    public void setMA_KHANG(String MA_KHANG) {
        this.MA_KHANG = MA_KHANG;
    }

    @XmlElement(name = "TEN_KHANG")
    public String getTEN_KHANG() {
        return TEN_KHANG;
    }

    public void setTEN_KHANG(String TEN_KHANG) {
        this.TEN_KHANG = TEN_KHANG;
    }

    @XmlElement(name = "DCHI_KHANG")
    public String getDCHI_KHANG() {
        return DCHI_KHANG;
    }

    public void setDCHI_KHANG(String DCHI_KHANG) {
        this.DCHI_KHANG = DCHI_KHANG;
    }

    @XmlElement(name = "TYLE_THUE")
    public String getTYLE_THUE() {
        return TYLE_THUE;
    }

    public void setTYLE_THUE(String TYLE_THUE) {
        this.TYLE_THUE = TYLE_THUE;
    }

    @XmlElement(name = "KIHIEU_SERY")
    public String getKIHIEU_SERY() {
        return KIHIEU_SERY;
    }

    public void setKIHIEU_SERY(String KIHIEU_SERY) {
        this.KIHIEU_SERY = KIHIEU_SERY;
    }

    @XmlElement(name = "SO_SERY")
    public String getSO_SERY() {
        return SO_SERY;
    }

    public void setSO_SERY(String SO_SERY) {
        this.SO_SERY = SO_SERY;
    }

    @XmlElement(name = "KY")
    public String getKY() {
        return KY;
    }

    public void setKY(String KY) {
        this.KY = KY;
    }

    @XmlElement(name = "THANG")
    public String getTHANG() {
        return THANG;
    }

    public void setTHANG(String THANG) {
        this.THANG = THANG;
    }

    @XmlElement(name = "NAM")
    public String getNAM() {
        return NAM;
    }

    public void setNAM(String NAM) {
        this.NAM = NAM;
    }

    @XmlElement(name = "SO_CTO")
    public int getSO_CTO() {
        return SO_CTO;
    }

    public void setSO_CTO(int SO_CTO) {
        this.SO_CTO = SO_CTO;
    }

    @XmlElement(name = "SO_HO")
    public int getSO_HO() {
        return SO_HO;
    }

    public void setSO_HO(int SO_HO) {
        this.SO_HO = SO_HO;
    }

    @XmlElement(name = "SO_TIEN")
    public Long getSO_TIEN() {
        return SO_TIEN;
    }

    public void setSO_TIEN(Long SO_TIEN) {
        this.SO_TIEN = SO_TIEN;
    }

    @XmlElement(name = "TIEN_GTGT")
    public Long getTIEN_GTGT() {
        return TIEN_GTGT;
    }

    public void setTIEN_GTGT(Long TIEN_GTGT) {
        this.TIEN_GTGT = TIEN_GTGT;
    }

    
    
}
