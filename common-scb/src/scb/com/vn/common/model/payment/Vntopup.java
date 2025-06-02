package scb.com.vn.common.model.payment;

import javax.xml.bind.annotation.XmlElement;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * VNPAY - VNTOPUP - request <br> <PAYMENT> <br> <TRANSCODE></TRANSCODE> <br> <CHANNEL></CHANNEL> <br> <SERVICECODE></SERVICECODE> <br>
 * <PROVIDERCODE></PROVIDERCODE> <br> <PROCESSINGCODE></PROCESSINGCODE> <br> <PAYMENTMETHOD></PAYMENTMETHOD> <br> <DATETIME></DATETIME> <br>
 * <ACCOUNTNO></ACCOUNTNO> <br> <REQUEST><br> <MOBNOREQ></MOBNOREQ> <br> <MOBNOLOAD></MOBNOLOAD> <br> <AMOUNT></AMOUNT> <br> </REQUEST> <br>
 * </PAYMENT>
 */
/**
 * VNPAY - VNTOPUP - response <br> <PAYMENT> <br> <TRANSCODE></TRANSCODE> <br> <CHANNEL></CHANNEL> <br> <SERVICECODE></SERVICECODE> <br>
 * <PROVIDERCODE></PROVIDERCODE> <br> <PROCESSINGCODE></PROCESSINGCODE> <br> <PAYMENTMETHOD></PAYMENTMETHOD> <br> <DATETIME></DATETIME> <br>
 * <ACCOUNTNO></ACCOUNTNO> <br> <RESPONSE><br> <MOBNOREQ></MOBNOREQ> <br> <MOBNOLOAD></MOBNOLOAD> <br> <AMOUNT></AMOUNT> <br> </RESPONSE><br>
 * </PAYMENT>
 */
public class Vntopup {

    String mobnoreq;
    String mobnoload;
    String amount;

    @XmlElement(name = "MOBNOREQ", required = false, nillable = true)
    public String getMobnoreq() {
        return mobnoreq;
    }

    public void setMobnoreq(String mobnoreq) {
        this.mobnoreq = mobnoreq;
    }

    @XmlElement(name = "MOBNOLOAD", required = false, nillable = true)
    public String getMobnoload() {
        return mobnoload;
    }

    public void setMobnoload(String mobnoload) {
        this.mobnoload = mobnoload;
    }

    @XmlElement(name = "AMOUNT", required = false, nillable = true)
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
