/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims;

/**
 *
 * @author minhndb
 */
public interface ICIMSRequest {
    public boolean isOutOfTime();
    public String getValueToBuildMac();
}
