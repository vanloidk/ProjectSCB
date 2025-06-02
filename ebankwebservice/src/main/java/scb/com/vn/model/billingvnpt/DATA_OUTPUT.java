/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.billingvnpt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DATA_OUTPUT")
public class DATA_OUTPUT {
    private String TEN_TINH;
    private String SDT_LH_TT;
    private String MA_TINH;
    private String TINH_ID;
    private String MA_TT;
    private String TEN_TT;
    private String TONGNO;
    private String DKTRATRUOC;
    private String CHINHCHU;
    private String DIACHI_TT;

    @XmlElement(name = "TEN_TINH", nillable = true)
    public String getTEN_TINH() {
        return TEN_TINH;
    }

    public void setTEN_TINH(String TEN_TINH) {
        this.TEN_TINH = TEN_TINH;
    }

    @XmlElement(name = "SDT_LH_TT", nillable = true)
    public String getSDT_LH_TT() {
        return SDT_LH_TT;
    }

    public void setSDT_LH_TT(String SDT_LH_TT) {
        this.SDT_LH_TT = SDT_LH_TT;
    }

    @XmlElement(name = "MA_TINH", nillable = true)
    public String getMA_TINH() {
        return MA_TINH;
    }

    public void setMA_TINH(String MA_TINH) {
        this.MA_TINH = MA_TINH;
    }

    @XmlElement(name = "TINH_ID", nillable = true)
    public String getTINH_ID() {
        return TINH_ID;
    }

    public void setTINH_ID(String TINH_ID) {
        this.TINH_ID = TINH_ID;
    }

    @XmlElement(name = "MA_TT", nillable = true)
    public String getMA_TT() {
        return MA_TT;
    }

    
    public void setMA_TT(String MA_TT) {
        this.MA_TT = MA_TT;
    }

    @XmlElement(name = "TEN_TT", nillable = true)
    public String getTEN_TT() {
        return TEN_TT;
    }

    public void setTEN_TT(String TEN_TT) {
        this.TEN_TT = TEN_TT;
    }

    @XmlElement(name = "TONGNO", nillable = true)
    public String getTONGNO() {
        return TONGNO;
    }

    public void setTONGNO(String TONGNO) {
        this.TONGNO = TONGNO;
    }

    @XmlElement(name = "DKTRATRUOC", nillable = true)
    public String getDKTRATRUOC() {
        return DKTRATRUOC;
    }

    public void setDKTRATRUOC(String DKTRATRUOC) {
        this.DKTRATRUOC = DKTRATRUOC;
    }

    @XmlElement(name = "CHINHCHU", nillable = true)
    public String getCHINHCHU() {
        return CHINHCHU;
    }

    public void setCHINHCHU(String CHINHCHU) {
        this.CHINHCHU = CHINHCHU;
    }

    @XmlElement(name = "DIACHI_TT", nillable = true)
    public String getDIACHI_TT() {
        return DIACHI_TT;
    }

    public void setDIACHI_TT(String DIACHI_TT) {
        this.DIACHI_TT = DIACHI_TT;
    }
    
}
