/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.mvisa;

/**
 *
 * @author minhndb
 */
public class PurchaseIdentifier implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
    private String referenceNumber;
    private String type;

    public PurchaseIdentifier()
    {
        this.referenceNumber = "";
        this.type = "";
    }

    public String getReferenceNumber()
    {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber)
    {
        this.referenceNumber = referenceNumber;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}