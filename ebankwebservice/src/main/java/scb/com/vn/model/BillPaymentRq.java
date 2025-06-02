/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.payment.ResponsePayment;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "BillPaymentRq")
public class BillPaymentRq extends MobileRequest {

    private static final Logger LOGGER = Logger.getLogger(BillPaymentRp.class);

    private String CustomerCode;
    private String idservicetype;
    private String idprovider;
    private String idpartner;
    private String BillAmt;
    private String PayAmt;
    private String DebitAccount;
    private String PaymentRule;
    private String IsPrepaid;
    private BillInfo[] bills;
    private ResponsePayment respay;
    private String result;
    private String refBillpaid;
    private String CustomerNameBill;        
    private String Channel;
    private String AddInfo;
    private String Options;
    private String BillDetails;
    private String VNPTMediaType;
    private String PaymentType;    
    private String makerCode;
    private String checkerCode;

    /**
     * @return the CustomerCode
     */
    @XmlElement(name = "CustomerCode", nillable = true)
    public String getCustomerCode() {
        return CustomerCode;
    }

    /**
     * @param CustomerCode the CustomerCode to set
     */
    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    /**
     * @return the idservicetype
     */
    @XmlElement(name = "BillServiceId", nillable = true)
    public String getIdservicetype() {
        return idservicetype;
    }

    /**
     * @param idservicetype the idservicetype to set
     */
    public void setIdservicetype(String idservicetype) {
        this.idservicetype = idservicetype;
    }

    /**
     * @return the idprovider
     */
    @XmlElement(name = "BillProviderId", nillable = true)
    public String getIdprovider() {
        return idprovider;
    }

    /**
     * @param idprovider the idprovider to set
     */
    public void setIdprovider(String idprovider) {
        this.idprovider = idprovider;
    }
    
    /**
     * @return the BillAmt
     */
    @XmlElement(name = "BillAmt", nillable = true)
    public String getBillAmt() {
        return BillAmt;
    }

    /**
     * @param BillAmt the BillAmt to set
     */
    public void setBillAmt(String BillAmt) {
        this.BillAmt = BillAmt;
    }

    /**
     * @return the PayAmt
     */
    @XmlElement(name = "PayAmt", nillable = true)
    public String getPayAmt() {
        return PayAmt;
    }

    /**
     * @param PayAmt the PayAmt to set
     */
    public void setPayAmt(String PayAmt) {
        this.PayAmt = PayAmt;
    }

    /**
     * @return the DebitAccount
     */
    @XmlElement(name = "DebitAccount", nillable = true)
    public String getDebitAccount() {
        return DebitAccount;
    }

    /**
     * @param DebitAccount the DebitAccount to set
     */
    public void setDebitAccount(String DebitAccount) {
        this.DebitAccount = DebitAccount;
    }

    /**
     * @return the PaymentRule
     */
    @XmlElement(name = "PaymentRule", nillable = true)
    public String getPaymentRule() {
        return PaymentRule;
    }

    /**
     * @param PaymentRule the PaymentRule to set
     */
    public void setPaymentRule(String PaymentRule) {
        this.PaymentRule = PaymentRule;
    }

    /**
     * @return the IsPrepaid
     */
    @XmlElement(name = "IsPrepaid", nillable = true)
    public String getIsPrepaid() {
        return IsPrepaid;
    }

    /**
     * @param IsPrepaid the IsPrepaid to set
     */
    public void setIsPrepaid(String IsPrepaid) {
        this.IsPrepaid = IsPrepaid;
    }

    /**
     * @return the bills
     */
    @XmlElement(name = "BillDetail")
    @XmlElementWrapper(name = "Bills", nillable = true)
    public BillInfo[] getBills() {
        return bills;
    }

    /**
     * @param bills the bills to set
     */
    public void setBills(BillInfo[] bills) {
        this.bills = bills;
    }

    @XmlElement(name = "Channel")
    public String getChannel() {
        return Channel;
    }

    public void setChannel(String Channel) {
        this.Channel = Channel;
    }

    @XmlElement(name = "AddInfo")
    public String getAddInfo() {
        return AddInfo;
    }

    public void setAddInfo(String AddInfo) {
        this.AddInfo = AddInfo;
    }

    @XmlElement(name = "Options")
    public String getOptions() {
        return Options;
    }

    public void setOptions(String Options) {
        this.Options = Options;
    }

    @XmlElement(name = "BillDetails")
    public String getBillDetails() {
        return BillDetails;
    }

    public void setBillDetails(String BillDetails) {
        this.BillDetails = BillDetails;
    }

    /**
     * @return the respay
     */
    public ResponsePayment getRespay() {
        return respay;
    }

    /**
     * @param respay the respay to set
     */
    public void setRespay(ResponsePayment respay) {
        this.respay = respay;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }
    
    /**
     * @return the idpartner
     */
    @XmlElement(name = "IdPartner")
    public String getIdpartner() {
        return idpartner;
    }

    /**
     * @param idpartner the idpartner to set
     */
    public void setIdpartner(String idpartner) {
        this.idpartner = idpartner;
    }
    
    @XmlElement(name = "VNPTMediaType")
    public String getVNPTMediaType() {
        return VNPTMediaType;
    }
    
    public void setVNPTMediaType(String VNPTMediaType) {
        this.VNPTMediaType = VNPTMediaType;
    }
    @XmlElement(name = "PaymentType")
    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String PaymentType) {
        this.PaymentType = PaymentType;
    }
    
    @XmlElement(name = "MakerCode")
    public String getMakerCode() {
        return makerCode;
    }

    public void setMakerCode(String makerCode) {
        this.makerCode = makerCode;
    }

    @XmlElement(name = "CheckerCode")
    public String getCheckerCode() {
        return checkerCode;
    }

    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode;
    }
    
    /**
     * @return the refBillpaid
     */
    public String getRefBillpaid() {
        return refBillpaid;
    }

    /**
     * @param refBillpaid the refBillpaid to set
     */
    public void setRefBillpaid(String refBillpaid) {
        this.refBillpaid = refBillpaid;
    }

    /**
     * @return the CustomerNameBill
     */
    public String getCustomerNameBill() {
        return CustomerNameBill;
    }

    /**
     * @param CustomerNameBill the CustomerNameBill to set
     */
    public void setCustomerNameBill(String CustomerNameBill) {
        this.CustomerNameBill = CustomerNameBill;
    }
    
    

    /**
     *
     * @return
     */
    public String getResponseQueryPartner() {
        String res = null;
        try {
            if (respay != null) {
                if (respay.getResponse() != null) {
                    if (respay.getResponse().getBilling() != null) {
                        res = respay.getResponse().getIdpartner();
                    } else {
                        LOGGER.warn("respay.getResponse().getIdpartner() is null");
                    }
                } else {
                    LOGGER.warn("respay.getResponse() is null");
                }
            } else {
                LOGGER.warn("respay is null");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return res;
    }

    /**
     *
     * @return
     */
    public String getResponseQueryAmount() {
        String res = null;
        try {
            if (respay != null) {
                if (respay.getResponse() != null) {
                    if (respay.getResponse().getBilling() != null) {
                        res = respay.getResponse().getBilling().getAmount();
                    } else {
                        LOGGER.warn("respay.getResponse().getBilling() is null");
                    }
                } else {
                    LOGGER.warn("respay.getResponse() is null");
                }
            } else {
                LOGGER.warn("respay is null");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return res;
    }

}
