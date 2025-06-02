/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cw;

/**
 *
 * @author minhndb
 */
public class CardInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private static final String NOTACTIVE = "0";
    
    private String phoneNumber;
    private String cmnd;
    private String pan_mask;
    private String loc;
    private String activeDate;
    private String customerName;
    private String expiryDate;
    private String cif;
    private String cardType; // loai the chi tiet: VS platinum, VS chuan, ATM loc, ATM thuong, Master chuan, Master vang
    private String locLimit; // han muc tin dung
    private String branchCode; // don vi phat hanh
    private String cardBranch; // loai the: LC ~ ATM, MC, VS
    private String panEncrypt; // so pan encrypt
    private String transferStatus; // tinh trang da giao the hay chua. Type: String
    private boolean isTransfered = false; // tinh trang da giao the hay chua. Type: boolean
    private boolean locked; // tinh trang the co dang bi khoa hay ko. Type: boolean
    private boolean closed; // tinh trang the co dang bi dong hay ko. Type: boolean

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCmnd() {
        return cmnd;
    }

    public String getPan_mask() {
        return pan_mask;
    }

    public String getLoc() {
        return loc;
    }

    public String getActiveDate() {
        return activeDate;
    }
    
    public String getLast4Digits() {
            return (pan_mask != null && pan_mask.length() == 16) ? pan_mask.substring(12) : "";
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCif() {
        return cif;
    }

    public String getCardType() {
        return cardType;
    }

    public String getLocLimit() {
        return locLimit;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getCardBranch() {
        return cardBranch;
    }

    public String getPanEncrypt() {
        return panEncrypt;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public boolean isIsTransfered() {
        return isTransfered;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCmnd(String cmnd) {
        if (cmnd != null) {
            this.cmnd = cmnd.trim();
        }
    }

    public void setPan_mask(String pan_mask) {
        if (pan_mask != null) {
            this.pan_mask = pan_mask.trim();
        }
    }

    public void setLoc(String loc) {
        if (loc != null) {
            this.loc = loc.trim();
        }
    }

    public void setActiveDate(String activeDate) {
        if (activeDate != null) {
            this.activeDate = activeDate.trim();
        }
    }

    public void setCustomerName(String customerName) {
        if (customerName != null) {
            this.customerName = customerName.trim();
        }
    }

    public void setExpiryDate(String expiryDate) {
        if (expiryDate != null) {
            this.expiryDate = expiryDate;
        }
    }

    public void setCif(String cif) {
        if (cif != null) {
            this.cif = cif.trim();
        }
    }

    public void setCardType(String cardType) {
        if (cardType != null) {
            this.cardType = cardType.trim();
        }
    }

    public void setLocLimit(String locLimit) {
        this.locLimit = locLimit.trim();
    }

    public void setBranchCode(String branchCode) {
        if (branchCode != null) {
            this.branchCode = branchCode.trim();
        }
    }

    public void setCardBranch(String cardBranch) {
        if (cardBranch != null) {
            this.cardBranch = cardBranch;
        }
    }

    public void setPanEncrypt(String panEncrypt) {
        if (panEncrypt != null) {
            this.panEncrypt = panEncrypt;
        }
    }

    /*
    *  --01 giao the thanh cong, con lai chua giao the KH
    */
    public void setTransferStatus(String transferStatus) {
        if (transferStatus != null) {
            this.transferStatus = transferStatus;
        }
        this.isTransfered = "01".equalsIgnoreCase(transferStatus);
    }

    public void setLocked(boolean isLocked) {
        this.locked = isLocked;
    }
    
    public void setLocked(String status) {
        this.locked = !"0".equals(status);
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
    
    /* 
    * STATUS_CARD = null hoac empty la the chua dong, VE,IE,CC,.... la da dong, da huy the
    */
    public void setClosed(String status) {
        this.closed = (status != null && !status.isEmpty());
    }
    
    public boolean isActive() {
        return !NOTACTIVE.equals(activeDate);
    }
}