package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "GetBillServiceRp")
public class GetBillServiceRp extends MobileResponse {

    private String Language;
    private BillServiceDetail[] ListBillService;

    /**
     * @return the ListBillService
     */
    @XmlElement(name = "BillServiceDetail")
    @XmlElementWrapper(name = "ListBillService", nillable = true)
    public BillServiceDetail[] getListBillService() {
        return ListBillService;
    }

    /**
     * @param ListBillService the ListBillService to set
     */
    public void setListBillService(BillServiceDetail[] ListBillService) {
        this.ListBillService = ListBillService;
    }

    /**
     * @return the Language
     */
    @XmlElement(name = "Language", nillable = true)
    public String getLanguage() {
        return Language;
    }

    /**
     * @param Language the Language to set
     */
    public void setLanguage(String Language) {
        this.Language = Language;
    }
}
