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
public class PointOfServiceData implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
    private String motoECIIndicator;
    private String panEntryMode;
    private String posConditionCode;

    public String getMotoECIIndicator()
    {
        return motoECIIndicator;
    }

    public void setMotoECIIndicator(String motoECIIndicator)
    {
        this.motoECIIndicator = motoECIIndicator;
    }

    public String getPanEntryMode()
    {
        return panEntryMode;
    }

    public void setPanEntryMode(String panEntryMode)
    {
        this.panEntryMode = panEntryMode;
    }

    public String getPosConditionCode()
    {
        return posConditionCode;
    }

    public void setPosConditionCode(String posConditionCode)
    {
        this.posConditionCode = posConditionCode;
    }
}