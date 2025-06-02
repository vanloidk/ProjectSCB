/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.dbi.dto.GiftDetail;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "GetChangGiftHistoryRp")
public class GetChangGiftHistoryRp extends MobileResponse {

    private GiftDetail[] GiftList;
    private String FromDate;
    private String ToDate;
    private String AccountNo;

    /**
     *
     */
    public GetChangGiftHistoryRp() {
    }

    /**
     * @return the GiftList
     */
    @XmlElement(name = "GiftDetail")
    @XmlElementWrapper(name = "GiftList", nillable = true)
    public GiftDetail[] getGiftList() {
        return GiftList;
    }

    /**
     * @param GiftList the GiftList to set
     */
    public void setGiftList(GiftDetail[] GiftList) {
        this.GiftList = GiftList;
    }

    /**
     * @return the FromDate
     */
    @XmlElement(name = "FromDate", nillable = true)
    public String getFromDate() {
        return FromDate;
    }

    /**
     * @param FromDate the FromDate to set
     */
    public void setFromDate(String FromDate) {
        this.FromDate = FromDate;
    }

    /**
     * @return the ToDate
     */
    @XmlElement(name = "ToDate", nillable = true)
    public String getToDate() {
        return ToDate;
    }

    /**
     * @param ToDate the ToDate to set
     */
    public void setToDate(String ToDate) {
        this.ToDate = ToDate;
    }

    /**
     * @return the AccountNo
     */
    @XmlElement(name = "AccountNo", nillable = true)
    public String getAccountNo() {
        return AccountNo;
    }

    /**
     * @param AccountNo the AccountNo to set
     */
    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }
}
