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
public class CardAcceptor implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
    private Address address;
    private String idCode;
    private String name;
    private String terminalId;
    private String state;

    public CardAcceptor()
    {
        this.address = new Address();
        this.idCode = "";
        this.name = "";
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public String getIdCode()
    {
        return idCode;
    }

    public void setIdCode(String idCode)
    {
        this.idCode = idCode;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTerminalId()
    {
        return terminalId;
    }

    public void setTerminalId(String terminalId)
    {
        this.terminalId = terminalId;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }
}