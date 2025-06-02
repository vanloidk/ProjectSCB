/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author lydty
 */
public class HQ_NOPTIEN_HQ implements java.io.Serializable {

    String MA_NH_TH;
    String TEN_NH_TH;
    String MA_DV;
    String MA_CHUONG;
    String TEN_DV;
    String MA_KB;
    String TEN_KB;
    String TKKB;
    String MA_NTK;
    String MA_HQ_PH;
    String MA_HQ_CQT;
    String KYHIEU_CT;
    String SO_CT;
    String LOAI_CT;
    String So_TN_CTS;
    String Ngay_TN_CTS;
    String MA_NT;
    String TY_GIA;
    String SOTIEN_TO;
    String DIENGIAI;
    String TypeForm; //Tien mat: TM; Chuyen khoan: CK
    String MAKERID;
    String BRACHID;
    String ACCOUNTNO;
    String MA_CUC;
    String TEN_CUC;
    String NGAY_BN;
    String NGAY_BC;
    String NGAY_CT;
    String STATUS;
    String CHECKERID;
    String MAKEDATE;
    String CHECKDATE;
    String CREATEDATE;
    String TYPETRANS;
    String REQUEST_ID;
    String TEN_CHUONG;
    int ID;
    String Ten_HQ_PH;

    String Ten_NTK;

    String DIACHI_DV;
    String TEN_NNTHAY;
    String DIACHI_NNTHAY;
    String NGUOI_NOP; //01: Tu nop: 02: Nop thay
    String SOTIEN_TO_NT;
    String SOURCEACCOUNTNAME;
    String NgayLap_CT;
    String NgayTruyen_CT;
    String Ma_ST;
    String So_CMT;
    String Ten_NNT;
    String DiaChi;
    String TT_Khac;
    String TaiKhoan_TH;
    String Ten_TaiKhoan_TH;
    String Transaction_ID;
    String Transaction_Date;
    String MsgType;

    /**
     *
     * @return
     */
    public String getMsgType() {
        return MsgType;
    }

    /**
     *
     * @param MsgType
     */
    public void setMsgType(String MsgType) {
        this.MsgType = MsgType;
    }

    /**
     *
     * @return
     */
    public String getADDINFO() {
        return ADDINFO;
    }

    /**
     *
     * @param ADDINFO
     */
    public void setADDINFO(String ADDINFO) {
        this.ADDINFO = ADDINFO;
    }
    String ADDINFO;

    /**
     *
     * @return
     */
    public String getTransaction_ID() {
        return Transaction_ID;
    }

    /**
     *
     * @param Transaction_ID
     */
    public void setTransaction_ID(String Transaction_ID) {
        this.Transaction_ID = Transaction_ID;
    }

    /**
     *
     * @return
     */
    public String getTransaction_Date() {
        return Transaction_Date;
    }

    /**
     *
     * @param Transaction_Date
     */
    public void setTransaction_Date(String Transaction_Date) {
        this.Transaction_Date = Transaction_Date;
    }

    /**
     *
     * @return
     */
    public String getMa_ST() {
        return Ma_ST;
    }

    /**
     *
     * @param Ma_ST
     */
    public void setMa_ST(String Ma_ST) {
        this.Ma_ST = Ma_ST;
    }

    /**
     *
     * @return
     */
    public String getSo_CMT() {
        return So_CMT;
    }

    /**
     *
     * @param So_CMT
     */
    public void setSo_CMT(String So_CMT) {
        this.So_CMT = So_CMT;
    }

    /**
     *
     * @return
     */
    public String getTen_NNT() {
        return Ten_NNT;
    }

    /**
     *
     * @param Ten_NNT
     */
    public void setTen_NNT(String Ten_NNT) {
        this.Ten_NNT = Ten_NNT;
    }

    /**
     *
     * @return
     */
    public String getDiaChi() {
        return DiaChi;
    }

    /**
     *
     * @param DiaChi
     */
    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    /**
     *
     * @return
     */
    public String getTT_Khac() {
        return TT_Khac;
    }

    /**
     *
     * @param TT_Khac
     */
    public void setTT_Khac(String TT_Khac) {
        this.TT_Khac = TT_Khac;
    }

    /**
     *
     * @return
     */
    public String getTaiKhoan_TH() {
        return TaiKhoan_TH;
    }

    /**
     *
     * @param TaiKhoan_TH
     */
    public void setTaiKhoan_TH(String TaiKhoan_TH) {
        this.TaiKhoan_TH = TaiKhoan_TH;
    }

    /**
     *
     * @return
     */
    public String getTen_TaiKhoan_TH() {
        return Ten_TaiKhoan_TH;
    }

    /**
     *
     * @param Ten_TaiKhoan_TH
     */
    public void setTen_TaiKhoan_TH(String Ten_TaiKhoan_TH) {
        this.Ten_TaiKhoan_TH = Ten_TaiKhoan_TH;
    }

    /**
     *
     * @return
     */
    public String getNgayLap_CT() {
        return NgayLap_CT;
    }

    /**
     *
     * @param NgayLap_CT
     */
    public void setNgayLap_CT(String NgayLap_CT) {
        this.NgayLap_CT = NgayLap_CT;
    }

    /**
     *
     * @return
     */
    public String getNgayTruyen_CT() {
        return NgayTruyen_CT;
    }

    /**
     *
     * @param NgayTruyen_CT
     */
    public void setNgayTruyen_CT(String NgayTruyen_CT) {
        this.NgayTruyen_CT = NgayTruyen_CT;
    }

    /**
     *
     * @return
     */
    public String getSOURCEACCOUNTNAME() {
        return SOURCEACCOUNTNAME;
    }

    /**
     *
     * @param SOURCEACCOUNTNAME
     */
    public void setSOURCEACCOUNTNAME(String SOURCEACCOUNTNAME) {
        this.SOURCEACCOUNTNAME = SOURCEACCOUNTNAME;
    }

    /**
     *
     * @return
     */
    public String getSOTIEN_TO_NT() {
        return SOTIEN_TO_NT;
    }

    /**
     *
     * @param SOTIEN_TO_NT
     */
    public void setSOTIEN_TO_NT(String SOTIEN_TO_NT) {
        this.SOTIEN_TO_NT = SOTIEN_TO_NT;
    }

    /**
     *
     * @return
     */
    public String getDIACHI_DV() {
        return DIACHI_DV;
    }

    /**
     *
     * @param DIACHI_DV
     */
    public void setDIACHI_DV(String DIACHI_DV) {
        this.DIACHI_DV = DIACHI_DV;
    }

    /**
     *
     * @return
     */
    public String getTEN_NNTHAY() {
        return TEN_NNTHAY;
    }

    /**
     *
     * @param TEN_NNTHAY
     */
    public void setTEN_NNTHAY(String TEN_NNTHAY) {
        this.TEN_NNTHAY = TEN_NNTHAY;
    }

    /**
     *
     * @return
     */
    public String getDIACHI_NNTHAY() {
        return DIACHI_NNTHAY;
    }

    /**
     *
     * @param DIACHI_NNTHAY
     */
    public void setDIACHI_NNTHAY(String DIACHI_NNTHAY) {
        this.DIACHI_NNTHAY = DIACHI_NNTHAY;
    }

    /**
     *
     * @return
     */
    public String getNGUOI_NOP() {
        return NGUOI_NOP;
    }

    /**
     *
     * @param NGUOI_NOP
     */
    public void setNGUOI_NOP(String NGUOI_NOP) {
        this.NGUOI_NOP = NGUOI_NOP;
    }

    /**
     *
     * @return
     */
    public String getTen_HQ_PH() {
        return Ten_HQ_PH;
    }

    /**
     *
     * @param Ten_HQ_PH
     */
    public void setTen_HQ_PH(String Ten_HQ_PH) {
        this.Ten_HQ_PH = Ten_HQ_PH;
    }

    /**
     *
     * @return
     */
    public String getTen_NTK() {
        return Ten_NTK;
    }

    /**
     *
     * @param Ten_NTK
     */
    public void setTen_NTK(String Ten_NTK) {
        this.Ten_NTK = Ten_NTK;
    }

    /**
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     *
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     *
     * @return
     */
    public String getMA_NH_TH() {
        return MA_NH_TH;
    }

    /**
     *
     * @param MA_NH_TH
     */
    public void setMA_NH_TH(String MA_NH_TH) {
        this.MA_NH_TH = MA_NH_TH;
    }

    /**
     *
     * @return
     */
    public String getTEN_NH_TH() {
        return TEN_NH_TH;
    }

    /**
     *
     * @param TEN_NH_TH
     */
    public void setTEN_NH_TH(String TEN_NH_TH) {
        this.TEN_NH_TH = TEN_NH_TH;
    }

    /**
     *
     * @return
     */
    public String getMA_DV() {
        return MA_DV;
    }

    /**
     *
     * @param MA_DV
     */
    public void setMA_DV(String MA_DV) {
        this.MA_DV = MA_DV;
    }

    /**
     *
     * @return
     */
    public String getMA_CHUONG() {
        return MA_CHUONG;
    }

    /**
     *
     * @param MA_CHUONG
     */
    public void setMA_CHUONG(String MA_CHUONG) {
        this.MA_CHUONG = MA_CHUONG;
    }

    /**
     *
     * @return
     */
    public String getTEN_DV() {
        return TEN_DV;
    }

    /**
     *
     * @param TEN_DV
     */
    public void setTEN_DV(String TEN_DV) {
        this.TEN_DV = TEN_DV;
    }

    /**
     *
     * @return
     */
    public String getMA_KB() {
        return MA_KB;
    }

    /**
     *
     * @param MA_KB
     */
    public void setMA_KB(String MA_KB) {
        this.MA_KB = MA_KB;
    }

    /**
     *
     * @return
     */
    public String getTEN_KB() {
        return TEN_KB;
    }

    /**
     *
     * @param TEN_KB
     */
    public void setTEN_KB(String TEN_KB) {
        this.TEN_KB = TEN_KB;
    }

    /**
     *
     * @return
     */
    public String getTKKB() {
        return TKKB;
    }

    /**
     *
     * @param TKKB
     */
    public void setTKKB(String TKKB) {
        this.TKKB = TKKB;
    }

    /**
     *
     * @return
     */
    public String getMA_NTK() {
        return MA_NTK;
    }

    /**
     *
     * @param MA_NTK
     */
    public void setMA_NTK(String MA_NTK) {
        this.MA_NTK = MA_NTK;
    }

    /**
     *
     * @return
     */
    public String getMA_HQ_PH() {
        return MA_HQ_PH;
    }

    /**
     *
     * @param MA_HQ_PH
     */
    public void setMA_HQ_PH(String MA_HQ_PH) {
        this.MA_HQ_PH = MA_HQ_PH;
    }

    /**
     *
     * @return
     */
    public String getMA_HQ_CQT() {
        return MA_HQ_CQT;
    }

    /**
     *
     * @param MA_HQ_CQT
     */
    public void setMA_HQ_CQT(String MA_HQ_CQT) {
        this.MA_HQ_CQT = MA_HQ_CQT;
    }

    /**
     *
     * @return
     */
    public String getKYHIEU_CT() {
        return KYHIEU_CT;
    }

    /**
     *
     * @param KYHIEU_CT
     */
    public void setKYHIEU_CT(String KYHIEU_CT) {
        this.KYHIEU_CT = KYHIEU_CT;
    }

    /**
     *
     * @return
     */
    public String getSO_CT() {
        return SO_CT;
    }

    /**
     *
     * @param SO_CT
     */
    public void setSO_CT(String SO_CT) {
        this.SO_CT = SO_CT;
    }

    /**
     *
     * @return
     */
    public String getLOAI_CT() {
        return LOAI_CT;
    }

    /**
     *
     * @param LOAI_CT
     */
    public void setLOAI_CT(String LOAI_CT) {
        this.LOAI_CT = LOAI_CT;
    }

    /**
     *
     * @return
     */
    public String getSo_TN_CTS() {
        return So_TN_CTS;
    }

    /**
     *
     * @param So_TN_CTS
     */
    public void setSo_TN_CTS(String So_TN_CTS) {
        this.So_TN_CTS = So_TN_CTS;
    }

    /**
     *
     * @return
     */
    public String getNgay_TN_CTS() {
        return Ngay_TN_CTS;
    }

    /**
     *
     * @param Ngay_TN_CTS
     */
    public void setNgay_TN_CTS(String Ngay_TN_CTS) {
        this.Ngay_TN_CTS = Ngay_TN_CTS;
    }

    /**
     *
     * @return
     */
    public String getMA_NT() {
        return MA_NT;
    }

    /**
     *
     * @param MA_NT
     */
    public void setMA_NT(String MA_NT) {
        this.MA_NT = MA_NT;
    }

    /**
     *
     * @return
     */
    public String getTY_GIA() {
        return TY_GIA;
    }

    /**
     *
     * @param TY_GIA
     */
    public void setTY_GIA(String TY_GIA) {
        this.TY_GIA = TY_GIA;
    }

    /**
     *
     * @return
     */
    public String getSOTIEN_TO() {
        return SOTIEN_TO;
    }

    /**
     *
     * @param SOTIEN_TO
     */
    public void setSOTIEN_TO(String SOTIEN_TO) {
        this.SOTIEN_TO = SOTIEN_TO;
    }

    /**
     *
     * @return
     */
    public String getDIENGIAI() {
        return DIENGIAI;
    }

    /**
     *
     * @param DIENGIAI
     */
    public void setDIENGIAI(String DIENGIAI) {
        this.DIENGIAI = DIENGIAI;
    }

    /**
     *
     * @return
     */
    public String getTypeForm() {
        return TypeForm;
    }

    /**
     *
     * @param TypeForm
     */
    public void setTypeForm(String TypeForm) {
        this.TypeForm = TypeForm;
    }

    /**
     *
     * @return
     */
    public String getMAKERID() {
        return MAKERID;
    }

    /**
     *
     * @param MAKERID
     */
    public void setMAKERID(String MAKERID) {
        this.MAKERID = MAKERID;
    }

    /**
     *
     * @return
     */
    public String getBRACHID() {
        return BRACHID;
    }

    /**
     *
     * @param BRACHID
     */
    public void setBRACHID(String BRACHID) {
        this.BRACHID = BRACHID;
    }

    /**
     *
     * @return
     */
    public String getACCOUNTNO() {
        return ACCOUNTNO;
    }

    /**
     *
     * @param ACCOUNTNO
     */
    public void setACCOUNTNO(String ACCOUNTNO) {
        this.ACCOUNTNO = ACCOUNTNO;
    }

    /**
     *
     * @return
     */
    public String getMA_CUC() {
        return MA_CUC;
    }

    /**
     *
     * @param MA_CUC
     */
    public void setMA_CUC(String MA_CUC) {
        this.MA_CUC = MA_CUC;
    }

    /**
     *
     * @return
     */
    public String getTEN_CUC() {
        return TEN_CUC;
    }

    /**
     *
     * @param TEN_CUC
     */
    public void setTEN_CUC(String TEN_CUC) {
        this.TEN_CUC = TEN_CUC;
    }

    /**
     *
     * @return
     */
    public String getNGAY_BN() {
        return NGAY_BN;
    }

    /**
     *
     * @param NGAY_BN
     */
    public void setNGAY_BN(String NGAY_BN) {
        this.NGAY_BN = NGAY_BN;
    }

    /**
     *
     * @return
     */
    public String getNGAY_BC() {
        return NGAY_BC;
    }

    /**
     *
     * @param NGAY_BC
     */
    public void setNGAY_BC(String NGAY_BC) {
        this.NGAY_BC = NGAY_BC;
    }

    /**
     *
     * @return
     */
    public String getNGAY_CT() {
        return NGAY_CT;
    }

    /**
     *
     * @param NGAY_CT
     */
    public void setNGAY_CT(String NGAY_CT) {
        this.NGAY_CT = NGAY_CT;
    }

    /**
     *
     * @return
     */
    public String getSTATUS() {
        return STATUS;
    }

    /**
     *
     * @param STATUS
     */
    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    /**
     *
     * @return
     */
    public String getCHECKERID() {
        return CHECKERID;
    }

    /**
     *
     * @param CHECKERID
     */
    public void setCHECKERID(String CHECKERID) {
        this.CHECKERID = CHECKERID;
    }

    /**
     *
     * @return
     */
    public String getMAKEDATE() {
        return MAKEDATE;
    }

    /**
     *
     * @param MAKEDATE
     */
    public void setMAKEDATE(String MAKEDATE) {
        this.MAKEDATE = MAKEDATE;
    }

    /**
     *
     * @return
     */
    public String getCHECKDATE() {
        return CHECKDATE;
    }

    /**
     *
     * @param CHECKDATE
     */
    public void setCHECKDATE(String CHECKDATE) {
        this.CHECKDATE = CHECKDATE;
    }

    /**
     *
     * @return
     */
    public String getCREATEDATE() {
        return CREATEDATE;
    }

    /**
     *
     * @param CREATEDATE
     */
    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    /**
     *
     * @return
     */
    public String getTYPETRANS() {
        return TYPETRANS;
    }

    /**
     *
     * @param TYPETRANS
     */
    public void setTYPETRANS(String TYPETRANS) {
        this.TYPETRANS = TYPETRANS;
    }

    /**
     *
     * @return
     */
    public String getREQUEST_ID() {
        return REQUEST_ID;
    }

    /**
     *
     * @param REQUEST_ID
     */
    public void setREQUEST_ID(String REQUEST_ID) {
        this.REQUEST_ID = REQUEST_ID;
    }

    /**
     *
     * @return
     */
    public String getTEN_CHUONG() {
        return TEN_CHUONG;
    }

    /**
     *
     * @param TEN_CHUONG
     */
    public void setTEN_CHUONG(String TEN_CHUONG) {
        this.TEN_CHUONG = TEN_CHUONG;
    }

}
