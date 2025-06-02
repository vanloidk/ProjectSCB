/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "GetBeneficaryNameRp")
public class GetBeneficaryNameRp extends MobileResponse {

    private String ToAccount;
    private String TxnType;
    private String BeneName;

    /**
     * @return the ToAccount
     */
    @XmlElement(name = "ToAccount", nillable = true)
    public String getToAccount() {
        return ToAccount;
    }

    /**
     * @param ToAccount the ToAccount to set
     */
    public void setToAccount(String ToAccount) {
        this.ToAccount = ToAccount;
    }

    /**
     * @return the TypeToNumber
     */
    @XmlElement(name = "TxnType", nillable = true)
    public String getTxnType() {
        return TxnType;
    }

    /**
     * @param TxnType the TypeToNumber to set
     */
    public void setTxnType(String TxnType) {
        this.TxnType = TxnType;
    }

    /**
     * @return the BeneName
     */
    @XmlElement(name = "BeneName", nillable = true)
    public String getBeneName() {
        return BeneName;
    }

    /**
     * @param BeneName the BeneName to set
     */
    public void setBeneName(String BeneName) {
        this.BeneName = BeneName;
    }
}
