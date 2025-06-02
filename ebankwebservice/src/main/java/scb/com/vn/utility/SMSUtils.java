/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import scb.com.vn.controller.Sms;

/**
 *
 * @author minhndb - NGUYEN DAC BINH MINH
 */
public class SMSUtils {

    /**
     *
     * @return
     */
    public static String CreateOTP() {
        String rsu = RandomStringUtils.randomAlphabetic(6);
        rsu = rsu.toUpperCase();
        return rsu;
    }

    /**
     *
     * @param phonenumber
     * @param otp
     * @param content
     * @param cardNumber
     * @return
     */
    public static int SendOTPSMS(String phonenumber, String otp, String content, String cardNumber) {
        String contentSMS = content + cardNumber + " la: " + otp
                + ". Neu giao dich KHONG do Quy khach thuc hien, vui long goi ngay Hotline SCB 1800545438.";
        Sms sms = new Sms();
        int result = sms.sendsms(phonenumber, contentSMS, "GW", "SCB");
        return result == 0 ? 1 : 0;
    }

    /**
     *
     * @param phonenumber
     * @param otp
     * @param content
     * @return
     */
    public static int SendOTPSMS(String phonenumber, String otp, String content) {
        Date currentDate = new Date();
        SimpleDateFormat fTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        String contentSMS = content + " cua khach hang qua SMS luc " + fTime.format(currentDate)
                + " ngay " + fDate.format(currentDate) + " la: " + otp;
        Sms sms = new Sms();
        int result = sms.sendsms(phonenumber, contentSMS, "GW", "SCB");
        return result == 0 ? 1 : 0;
    }
}
