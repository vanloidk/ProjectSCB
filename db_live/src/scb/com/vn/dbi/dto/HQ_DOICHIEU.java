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
public class HQ_DOICHIEU implements java.io.Serializable {

    String SO_TN_CT;
    String Ngay_TN_CT;
    String MsgType;
    HQ_NOPTIEN_HQ obj801;
    HQ_NOPTIEN_CQQLT obj803;
    HQ_BAOLANH_TK obj804;
    HQ_BAOLANH_CHUNG obj805;
    HQ_BAOLANH_HDVD obj806;

    String Transaction_id;

    /**
     *
     * @return
     */
    public String getTransaction_id() {
        return Transaction_id;
    }

    /**
     *
     * @param Transaction_id
     */
    public void setTransaction_id(String Transaction_id) {
        this.Transaction_id = Transaction_id;
    }

    /**
     *
     * @return
     */
    public String getSO_TN_CT() {
        return SO_TN_CT;
    }

    /**
     *
     * @param SO_TN_CT
     */
    public void setSO_TN_CT(String SO_TN_CT) {
        this.SO_TN_CT = SO_TN_CT;
    }

    /**
     *
     * @return
     */
    public String getNgay_TN_CT() {
        return Ngay_TN_CT;
    }

    /**
     *
     * @param Ngay_TN_CT
     */
    public void setNgay_TN_CT(String Ngay_TN_CT) {
        this.Ngay_TN_CT = Ngay_TN_CT;
    }

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
    public HQ_NOPTIEN_HQ getObj801() {
        return obj801;
    }

    /**
     *
     * @param obj801
     */
    public void setObj801(HQ_NOPTIEN_HQ obj801) {
        this.obj801 = obj801;
    }

    /**
     *
     * @return
     */
    public HQ_NOPTIEN_CQQLT getObj803() {
        return obj803;
    }

    /**
     *
     * @param obj803
     */
    public void setObj803(HQ_NOPTIEN_CQQLT obj803) {
        this.obj803 = obj803;
    }

    /**
     *
     * @return
     */
    public HQ_BAOLANH_TK getObj804() {
        return obj804;
    }

    /**
     *
     * @param obj804
     */
    public void setObj804(HQ_BAOLANH_TK obj804) {
        this.obj804 = obj804;
    }

    /**
     *
     * @return
     */
    public HQ_BAOLANH_CHUNG getObj805() {
        return obj805;
    }

    /**
     *
     * @param obj805
     */
    public void setObj805(HQ_BAOLANH_CHUNG obj805) {
        this.obj805 = obj805;
    }

    /**
     *
     * @return
     */
    public HQ_BAOLANH_HDVD getObj806() {
        return obj806;
    }

    /**
     *
     * @param obj806
     */
    public void setObj806(HQ_BAOLANH_HDVD obj806) {
        this.obj806 = obj806;
    }
}
