/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.service;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import vn.com.scb.bian.utility.Helper;
import vn.com.scb.bian.utility.IIBUtils;
import vn.com.scb.bian.utility.IIBConstants;
import vn.com.scb.bian.*;
import vn.com.scb.bian.utility.BianException;
import vn.com.scb.bian.utility.IIBContext;

/**
 *
 * @author scb
 */
public class IIBLetterOfCreditService extends IIBService {
    
    public static final String RESOURCE_RETRIEVE_LETTEROFCREDITCARD = "/letterofcredit/v1.0/rest/retrieveLetterOfCredit";

    /**
     *
     * Lay chi tiet tai khoan bao lanh
     *
     * @param serialNo
     * @param channel
     * @param iibContext
     * @return
     */
    public RetrieveLetterOfCreditInfo_out_Type retrieveLetterOfCredit(IIBContext iibContext, String serialNo, String channel) throws BianException {
        String inputJson = "";
        try {
            RetrieveLetterOfCreditInfo_in_Type retrieveLetterOfCreditInfo_in = new RetrieveLetterOfCreditInfo_in_Type();
            
            LetterOfCreditInfoType letterOfCreditInfo = new LetterOfCreditInfoType();
            letterOfCreditInfo.setSerialNo(serialNo);         
            retrieveLetterOfCreditInfo_in.setLetterOfCreditInfo(letterOfCreditInfo);
            
            retrieveLetterOfCreditInfo_in.setTransactionInfo(IIBUtils.getTransInfpTypeCommon(channel));
            RetrieveLetterOfCreditInfo_in_Type.Container container = new RetrieveLetterOfCreditInfo_in_Type.Container();
            container.retrieveLetterOfCreditInfo_in = retrieveLetterOfCreditInfo_in;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_RETRIEVE_LETTEROFCREDITCARD);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "retrieveLetterOfCredit:" + inputJson);
                Helper.writeLogError(this.getClass(), "retrieveLetterOfCredit: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "Failed : HTTP error code :");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output = "";
            String line;
            while ((line = br.readLine()) != null) {
                output = output.concat(line);
            }
            conn.disconnect();
            RetrieveLetterOfCreditInfo_out_Type.Container retrieveLetterOfCreditInfo_out_Type_con = gson.fromJson(output, RetrieveLetterOfCreditInfo_out_Type.Container.class);
            if (retrieveLetterOfCreditInfo_out_Type_con != null) {
                RetrieveLetterOfCreditInfo_out_Type retrieveLetterOfCreditInfo_out_Type = retrieveLetterOfCreditInfo_out_Type_con.retrieveLetterOfCreditInfo_out;
                // do RetrieveLetterOfCreditInfo viet khong giong cac ham khac getTransactionErrorCode 00 thanh cong
                if (retrieveLetterOfCreditInfo_out_Type.getTransactionInfo() != null && retrieveLetterOfCreditInfo_out_Type.getTransactionInfo().getTransactionErrorCode().equals("00")) {
                    return retrieveLetterOfCreditInfo_out_Type;
                } else {
                    Helper.writeLogError(this.getClass(), "retrieveLetterOfCredit:" + inputJson);
                    Helper.writeLogError(this.getClass(), "retrieveLetterOfCredit:" + output);
                    Helper.writeLogError(this.getClass(), "retrieveLetterOfCredit:" + retrieveLetterOfCreditInfo_out_Type.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "retrieveLetterOfCredit:" + retrieveLetterOfCreditInfo_out_Type.getTransactionInfo().getTransactionReturnMsg());
                    return retrieveLetterOfCreditInfo_out_Type;
                }
            } else {
                Helper.writeLogError(this.getClass(), "retrieveLetterOfCredit - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "retrieveLetterOfCredit - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "retrieveLetterOfCredit - out null");
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "retrieveLetterOfCredit - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "retrieveLetterOfCredit - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "retrieveLetterOfCredit - Exception:" + e.toString());
        }
    }  
}
