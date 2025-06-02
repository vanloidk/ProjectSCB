/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.masterpass;

import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.MobileResponse;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "PayByQRCodeRp")
public class PayByQRCodeRp extends MobileResponse implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
   
    public PayByQRCodeRp() {}
}