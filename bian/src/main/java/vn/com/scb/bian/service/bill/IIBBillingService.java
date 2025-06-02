
package vn.com.scb.bian.service.bill;

import org.apache.log4j.Logger;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;
import scb.com.vn.common.model.iibpayment.BillPaymentDetailItemType;
import scb.com.vn.common.model.iibpayment.BillPaymentDetailType;
import scb.com.vn.common.model.iibpayment.BillPaymentInfoType;
import scb.com.vn.common.model.iibpayment.GetBillInfo_in;
import scb.com.vn.common.model.iibpayment.GetBillInfo_out;
import scb.com.vn.common.model.iibpayment.PayBill_in;
import scb.com.vn.common.model.iibpayment.PayBill_inConvertType;
import scb.com.vn.common.model.iibpayment.PayBill_out;
import scb.com.vn.common.model.iibpayment.TransactionInfoType;
import vn.com.scb.bian.service.IIBService;
import vn.com.scb.bian.utility.BianException;
import vn.com.scb.bian.utility.IIBConstants;
import vn.com.scb.bian.utility.IIBContext;

/**
 *
 * @author minhndb
 */
public class IIBBillingService extends IIBService {

    private static final Logger logger = Logger.getLogger(IIBBillingService.class);

    private static final String RESOURCE_SELECT_BILL = "/v1.0/rest/getBillInfo";
    private static final String RESOURCE_PAY_BILL = "/v1.0/rest/payBill";

    public GetBillInfo_out getBillInfo(IIBContext iibContext, GetBillInfo_in request,String partnerid) throws BianException {
        HttpURLConnection conn = this.getHttpURLConnection(iibContext,"/"+partnerid+RESOURCE_SELECT_BILL);
        Gson gson = new Gson();
        String inputJson = gson.toJson(request);
        logger.info("getBilInfo "+partnerid+" inputJson:" + inputJson);
        try {
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                logger.warn("getBillInfo: Failed : HTTP error code :" + conn.getResponseCode());
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()),"UTF-8"));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                output = output.append(line);
            }
            conn.disconnect();
            logger.info("getBilInfo "+partnerid+" output:" + output.toString());
            
            GetBillInfo_out response = gson.fromJson(output.toString(), GetBillInfo_out.class);

            if (response != null) {
                return response;
            } else {
                logger.warn("getBilInfo is null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "getBilInfo is null");
            }
        } catch (Exception e) {
            logger.error("getBilInfo - Exception: " + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "getBilInfo - Exception " + partnerid+":" + e.toString());
        }
    }
    
    public PayBill_out payBill(IIBContext iibContext, PayBill_inConvertType request,String partnerid) throws BianException {
        HttpURLConnection conn = this.getHttpURLConnection(iibContext,"/"+partnerid+ RESOURCE_PAY_BILL);
        Gson gson = new Gson();
        String inputJson = gson.toJson(request);
         logger.warn("payBill "+partnerid+":" + inputJson);
        try {
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes("UTF-8"));
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                logger.warn("payBill: Failed : HTTP error code : " + conn.getResponseCode());
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()),"UTF-8"));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                output = output.append(line);
            }
            conn.disconnect();
            logger.info("payBill "+partnerid+" output:" + output.toString());
            
            PayBill_out response = gson.fromJson(output.toString(), PayBill_out.class);
            
            if (response != null) {
                return response;
            } else {
              
                logger.info("payBill "+partnerid+" is null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "payBill is null");
            }
        } catch (Exception e) {
            logger.error("payBill - Exception: " + inputJson);
            logger.error("payBill - Exception: " + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "payBill - Exception: " + e.toString());
        }
    }
    public GetBillInfo_in createQueryRequest( String customerCode, String channel) {
        
        GetBillInfo_in out= new GetBillInfo_in();
        TransactionInfoType transactionInfo = new TransactionInfoType();
        transactionInfo.setClientCode(channel);
        
        BillPaymentInfoType billPaymentInfo = new BillPaymentInfoType();
        billPaymentInfo.setBillCustomerCode(customerCode);
        out.getGetBillInfo_in().setTransactionInfo(transactionInfo);
        out.getGetBillInfo_in().setBillPaymentInfo(billPaymentInfo);
        
        return out;
    }
  public PayBill_inConvertType createPayRequest(String billTransCode,String totalAmount,
            List<BillPaymentDetailType> billPaymentDetail,String moreinfo,String channel) {
       
        PayBill_inConvertType out=new PayBill_inConvertType();
        
        BillPaymentInfoType billPaymentInfo = new BillPaymentInfoType();
        billPaymentInfo.setBillTransCode(billTransCode);
        billPaymentInfo.setBillTotalAmount(totalAmount);
        billPaymentInfo.setBillMoreInfomation(moreinfo);
        out.getPayBill_in().setBillPaymentDetail(billPaymentDetail);
        out.getPayBill_in().setBillPaymentInfo(billPaymentInfo);
        TransactionInfoType transactionInfo = new TransactionInfoType();
        transactionInfo.setClientCode(channel);
        out.getPayBill_in().setTransactionInfo(transactionInfo);
        return out;
        
    }

}
