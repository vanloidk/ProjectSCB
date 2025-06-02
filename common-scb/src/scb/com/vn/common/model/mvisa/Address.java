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
public class Address implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
    private String country;
    private String county;
    private String state;
    private String zipCode;
    private String city;
    private String cardIssuerCountryCode;

    public Address()
    {
        this.country = "";
        this.county = "";
        this.state = "";
        this.zipCode = "";
        this.city = "";
        
    }
    
    

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCardIssuerCountryCode()
    {
        return cardIssuerCountryCode;
    }

    public void setCardIssuerCountryCode(String cardIssuerCountryCode)
    {
        this.cardIssuerCountryCode = cardIssuerCountryCode;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCounty()
    {
        return county;
    }

    public void setCounty(String county)
    {
        this.county = county;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }   
}