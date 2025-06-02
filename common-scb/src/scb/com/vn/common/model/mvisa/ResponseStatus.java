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
public class ResponseStatus implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
    private String code;
    private String severity;
    private String info;
    private String status;
    private String message;

    public ResponseStatus()
    {
        this.code = "";
        this.severity = "";
        this.info = "";
        this.status = "";
        this.message = "";
    }
    
    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getSeverity()
    {
        return severity;
    }

    public void setSeverity(String severity)
    {
        this.severity = severity;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public boolean isNoData()
    {
        return (code.isEmpty() && severity.isEmpty() && info.isEmpty() && status.isEmpty() && message.isEmpty());
        

    }
}