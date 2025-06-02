/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import scb.com.vn.common.model.payment.EvnHcmRequest;
import scb.com.vn.controller.Fcubs;
import scb.com.vn.payment.evnhcm.billing.DanhSachHoaDon;
import scb.com.vn.payment.evnhcm.billing.DanhSachKhachHang;
import scb.com.vn.payment.evnhcm.billing.DienLuc;
import scb.com.vn.payment.evnhcm.billing.Supporter;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;

/**
 *
 * @author minhndb
 */
public class EVNHCMController {

    private final String EVN_PARTNERID = "EVNHCM";
    private final String EVNHCM_URLADDRESS = Configuration.getProperty("ws.evnhcm.url.address");

    /**
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public int bankCancelHDDT(String xml) throws Exception {
        //1. Check valid input para
        EvnHcmRequest request;
        try {
            int result;
            request = (EvnHcmRequest) Xml.unMarshaller(EvnHcmRequest.class, xml);
            String maKH = request.getMaKH();
            String totalAmount = request.getSoTien();
            Double soTien = Double.parseDouble(totalAmount);
            String maGiaoDich = request.getMaGiaoDich();
            int bankID = Integer.parseInt(request.getBankID());
            Supporter supporter = new Supporter(maKH, soTien, maGiaoDich, bankID, EVNHCM_URLADDRESS);
            result = supporter.bankCancelHDDT();

            if (result == 0) {
                String revertStatus = "";
                String refCust = Helper.getDBI().queryRefCustPayooBill(EVN_PARTNERID, maKH, totalAmount, maGiaoDich);
                if (refCust != null && !refCust.isEmpty()) {
                    Fcubs fcubs = new Fcubs();
                    revertStatus = fcubs.revertTransferFCUBS(refCust, 60);
                }

                if (revertStatus == null || revertStatus.isEmpty()) {
                    result = -3;
                }

                Helper.getDBI().deletePayooBill(EVN_PARTNERID, maKH, totalAmount, maGiaoDich, revertStatus);
            }

            return result;
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex.toString());
            return -1;
        }
    }

    /**
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public List<DanhSachHoaDon> bankRequestDuration(String xml) throws Exception {
        //1. Check valid input para
        EvnHcmRequest request;
        try {
            request = (EvnHcmRequest) Xml.unMarshaller(EvnHcmRequest.class, xml);
            int bankId = Integer.parseInt(request.getBankID());

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

            Date tTD = df.parse(request.getTuThoiDiem());
            Calendar tuThoiDiem = Calendar.getInstance();
            tuThoiDiem.setTime(tTD);

            Date dTD = df.parse(request.getDenThoiDiem());
            Calendar denThoiDiem = Calendar.getInstance();
            denThoiDiem.setTime(dTD);

            if (dTD.after(tTD)) {
                Supporter supporter = new Supporter(bankId, tuThoiDiem, denThoiDiem, EVNHCM_URLADDRESS);
                return supporter.bankRequestDuration();
            }
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex.toString());
        }

        return new ArrayList<>();
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public List<DienLuc> bankRequestMaDL() throws Exception {
        try {
            Supporter supporter = new Supporter(EVNHCM_URLADDRESS);
            return supporter.bankRequestMaDL();
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex.toString());
            return null;
        }
    }

    /**
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public List<DanhSachKhachHang> bankRequestAddress(String xml) throws Exception {
        EvnHcmRequest request;
        try {
            request = (EvnHcmRequest) Xml.unMarshaller(EvnHcmRequest.class, xml);
            String tenKh = request.getTenKh();
            String diaChi = request.getDiaChi();
            String maDL = request.getMaDL();

            Supporter supporter = new Supporter(tenKh, diaChi, maDL, EVNHCM_URLADDRESS);
            return supporter.bankRequestAddress();
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex.toString());
            return null;
        }
    }

    /**
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public List<DanhSachHoaDon> bankCheckConfirm(String xml) throws Exception {
        EvnHcmRequest request;
        try {
            request = (EvnHcmRequest) Xml.unMarshaller(EvnHcmRequest.class, xml);

            int bankID = Integer.parseInt(request.getBankID());
            String maKh = request.getMaKH();
            String maGiaoDich = request.getMaGiaoDich();

            Supporter supporter = new Supporter(bankID, maKh, maGiaoDich, EVNHCM_URLADDRESS);
            return supporter.bankCheckConfirm();
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex.toString());
            return null;
        }
    }
}
