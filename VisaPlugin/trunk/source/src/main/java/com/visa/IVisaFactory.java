/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visa;

import com.visa.utils.MessageType;
import scb.com.vn.common.model.mvisa.RequestMessage;
import scb.com.vn.common.model.mvisa.ResponseMessage;


/**
 *
 * @author minhndb
 */
public interface IVisaFactory
{
    public ResponseMessage execute(RequestMessage request, String testInfo, MessageType messageType) throws Exception;
}