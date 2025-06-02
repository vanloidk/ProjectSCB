/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author Administrator
 */
public class CustomerInfo  implements java.io.Serializable{
    String Mobile_Number;
    String address;
    String Date_Of_Birth;
    String Full_Name;
    String unique_id_value;
    String ISSUED_PLACE;
    String ISSUED_DATE;
    String EMAIL;
    private String UDF_5;

    public String getUDF_5() {
        return UDF_5;
    }

    public void setUDF_5(String UDF_5) {
        this.UDF_5 = UDF_5;
    }

    public String getMobile_Number() {
        return Mobile_Number;
    }

    public void setMobile_Number(String Mobile_Number) {
        this.Mobile_Number = Mobile_Number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate_Of_Birth() {
        return Date_Of_Birth;
    }

    public void setDate_Of_Birth(String Date_Of_Birth) {
        this.Date_Of_Birth = Date_Of_Birth;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String Full_Name) {
        this.Full_Name = Full_Name;
    }

    public String getUnique_id_value() {
        return unique_id_value;
    }

    public void setUnique_id_value(String unique_id_value) {
        this.unique_id_value = unique_id_value;
    }

    public String getISSUED_PLACE() {
        return ISSUED_PLACE;
    }

    public void setISSUED_PLACE(String ISSUED_PLACE) {
        this.ISSUED_PLACE = ISSUED_PLACE;
    }

    public String getISSUED_DATE() {
        return ISSUED_DATE;
    }

    public void setISSUED_DATE(String ISSUED_DATE) {
        this.ISSUED_DATE = ISSUED_DATE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }
    
}
