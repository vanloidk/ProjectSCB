/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.partner.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "TRANSHISTORYBYDESCRES")
public class PartnerHistoryTransferResp implements java.io.Serializable {

    private static final long serialVersionUID = -1601367309834759824L;
    private String TRANSID;
    private String PARTNER;
    private String RESPONSECODE;
    private String RESPONSEDESC;
    private List<results> results;

    @XmlElement(name = "TRANSID", nillable = true)
    public String getTRANSID() {
        return TRANSID;
    }

    public void setTRANSID(String TRANSID) {
        this.TRANSID = TRANSID;
    }

    @XmlElement(name = "PARTNER", nillable = true)
    public String getPARTNER() {
        return PARTNER;
    }

    public void setPARTNER(String PARTNER) {
        this.PARTNER = PARTNER;
    }

    @XmlElement(name = "RESPONSECODE", nillable = true)
    public String getRESPONSECODE() {
        return RESPONSECODE;
    }

    public void setRESPONSECODE(String RESPONSECODE) {
        this.RESPONSECODE = RESPONSECODE;
    }

    @XmlElement(name = "RESPONSEDESC", nillable = true)
    public String getRESPONSEDESC() {
        return RESPONSEDESC;
    }

    public void setRESPONSEDESC(String RESPONSEDESC) {
        this.RESPONSEDESC = RESPONSEDESC;
    }

    @XmlElement(name = "results", nillable = true)
    public List<results> getResults() {
        return results;
    }

    public void setResults(List<results> results) {
        this.results = results;
    }

    /**
     * Dto02
     */
    public static class results {

        private String SO_THU_TU_GD;
        private String THOI_GIAN_GD;
        private String NGAY_GIAO_DICH;
        private String TY_GIA;
        private String LOAI_TIEN;
        private String SO_TIEN_NGTE;
        private String MA_DON_VI_TK;
        private String PS_NO_CO;
        private String DIEN_GIAI;
        private String SO_TIEN_QDOI;
        private String SO_TAI_KHOAN;
        private String SO_GIAO_DICH;

        @XmlElement(name = "SO_THU_TU_GD", nillable = true)
        public String getSO_THU_TU_GD() {
            return SO_THU_TU_GD;
        }

        public void setSO_THU_TU_GD(String SO_THU_TU_GD) {
            this.SO_THU_TU_GD = SO_THU_TU_GD;
        }

        @XmlElement(name = "THOI_GIAN_GD", nillable = true)
        public String getTHOI_GIAN_GD() {
            return THOI_GIAN_GD;
        }

        public void setTHOI_GIAN_GD(String THOI_GIAN_GD) {
            this.THOI_GIAN_GD = THOI_GIAN_GD;
        }

        @XmlElement(name = "NGAY_GIAO_DICH", nillable = true)
        public String getNGAY_GIAO_DICH() {
            return NGAY_GIAO_DICH;
        }

        public void setNGAY_GIAO_DICH(String NGAY_GIAO_DICH) {
            this.NGAY_GIAO_DICH = NGAY_GIAO_DICH;
        }

        @XmlElement(name = "TY_GIA", nillable = true)
        public String getTY_GIA() {
            return TY_GIA;
        }

        public void setTY_GIA(String TY_GIA) {
            this.TY_GIA = TY_GIA;
        }

        @XmlElement(name = "LOAI_TIEN", nillable = true)
        public String getLOAI_TIEN() {
            return LOAI_TIEN;
        }

        public void setLOAI_TIEN(String LOAI_TIEN) {
            this.LOAI_TIEN = LOAI_TIEN;
        }

        @XmlElement(name = "SO_TIEN_NGTE", nillable = true)
        public String getSO_TIEN_NGTE() {
            return SO_TIEN_NGTE;
        }

        public void setSO_TIEN_NGTE(String SO_TIEN_NGTE) {
            this.SO_TIEN_NGTE = SO_TIEN_NGTE;
        }

        @XmlElement(name = "MA_DON_VI_TK", nillable = true)
        public String getMA_DON_VI_TK() {
            return MA_DON_VI_TK;
        }

        public void setMA_DON_VI_TK(String MA_DON_VI_TK) {
            this.MA_DON_VI_TK = MA_DON_VI_TK;
        }

        @XmlElement(name = "PS_NO_CO", nillable = true)
        public String getPS_NO_CO() {
            return PS_NO_CO;
        }

        public void setPS_NO_CO(String PS_NO_CO) {
            this.PS_NO_CO = PS_NO_CO;
        }

        @XmlElement(name = "DIEN_GIAI", nillable = true)
        public String getDIEN_GIAI() {
            return DIEN_GIAI;
        }

        public void setDIEN_GIAI(String DIEN_GIAI) {
            this.DIEN_GIAI = DIEN_GIAI;
        }

        @XmlElement(name = "SO_TIEN_QDOI", nillable = true)
        public String getSO_TIEN_QDOI() {
            return SO_TIEN_QDOI;
        }

        public void setSO_TIEN_QDOI(String SO_TIEN_QDOI) {
            this.SO_TIEN_QDOI = SO_TIEN_QDOI;
        }

        @XmlElement(name = "SO_TAI_KHOAN", nillable = true)
        public String getSO_TAI_KHOAN() {
            return SO_TAI_KHOAN;
        }

        public void setSO_TAI_KHOAN(String SO_TAI_KHOAN) {
            this.SO_TAI_KHOAN = SO_TAI_KHOAN;
        }

        @XmlElement(name = "SO_GIAO_DICH", nillable = true)
        public String getSO_GIAO_DICH() {
            return SO_GIAO_DICH;
        }

        public void setSO_GIAO_DICH(String SO_GIAO_DICH) {
            this.SO_GIAO_DICH = SO_GIAO_DICH;
        }
    }
}
