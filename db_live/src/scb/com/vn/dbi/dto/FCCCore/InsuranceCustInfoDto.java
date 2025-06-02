/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto.FCCCore;

import java.util.Date;

/**
 *
 * @author loinv3
 */
public class InsuranceCustInfoDto implements java.io.Serializable {

    private static final long serialVersionUID = -1L;

    private String soCif;
    private String fullname;
    private String address;
    private String identityCard;
    private String placeOfIssued;
    private String telNo;
    private String dateIssued;

    

    public InsuranceCustInfoDto() {
    }

    public String getSoCif() {
        return soCif;
    }

    public void setSoCif(String soCif) {
        this.soCif = soCif;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getPlaceOfIssued() {
        return placeOfIssued;
    }

    public void setPlaceOfIssued(String placeOfIssued) {
        this.placeOfIssued = placeOfIssued;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }
}
