
package scb.com.vn.payment;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Level;
import com.google.gson.Gson;
import scb.com.vn.common.model.qrpayment.QRPaymentRequest;
import scb.com.vn.common.model.qrpayment.QRPaymentResponse;
import scb.com.vn.model.payooqr.CheckOrderRequest;
import scb.com.vn.model.payooqr.CheckOrderResponse;
import scb.com.vn.model.payooqr.GetOrderRequest;
import scb.com.vn.model.payooqr.GetOrderResponse;
import scb.com.vn.model.payooqr.NotifyTransactionRequest;
import scb.com.vn.model.payooqr.NotifyTransactionResponse;
import scb.com.vn.controller.Fcubs;
import scb.com.vn.dbi.dto.CustomerInfo;
import scb.com.vn.dbi.dto.QRPAYMENT;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.utility.Helper;
import scb.com.vn.message.QRMessage;
import scb.com.vn.model.payooqr.Response;
import scb.com.vn.ultility.Xml;

/**
 *
 * @author lydty
 */
public class QRPaymentController {
   
    public String QRPaymentService(String xmlRequest) throws IOException, Exception
    {
        Gson gson= new Gson();
       
        QRPaymentResponse rp =new QRPaymentResponse();
        String status= ""; //Loi call service
        try
        {
            Fcubs fc=new Fcubs();
            QRPAYMENT data=new QRPAYMENT();
            rp.setIsRevert("0");
            String ProductTransfer="OLPM";
            QRPaymentRequest rq = (QRPaymentRequest) Xml.unMarshaller(QRPaymentRequest.class, xmlRequest);            
            data.setRequest(rq);
            //insert 
            rp.setPartnerid(rq.getPartnerid());
            rp.setRequestId(rq.getRequestId());
            rp.setTypeRequest(rq.getTypeRequest());
            long amountOrder = rq.getAmountOrder();
            rp.setAmountOrder(amountOrder);
            
            String qrCodeValue= rq.getQrCodeValue();
            String accountType = rq.getAccounttype();
            String CoreRef=null;
            String accountNo="";
            int paymentType=0;
            
            String[] resultPartner= Helper.getDBI().ONL_GetMACkeys(rq.getPartnerid());
            if(resultPartner==null)
            {
                status = QRMessage.ResultEnum.PARTNERID_INVALID.getValue();
                return getResponse(rp, status);
            }            
            String partnerAccount = resultPartner[4];
            
            if(accountType.equals("02"))
            {
                paymentType=6;
                accountNo= rq.getAccountno();
            }
            Helper.writeLog(QRPaymentController.class, Level.INFO,"check account:"+accountNo+";accountType:"+accountType);
            VwCustAccount view = Helper.getDBI().getCustAccountByAccountNo(accountNo);
            if(view == null)
            {
                status = QRMessage.ResultEnum.ACCOUNT_INVALID.getValue(); // TK khong ton tai
                return getResponse(rp, status);
            }
            else
            {
                Helper.writeLog(QRPaymentController.class, Level.INFO,"!view.getRecordStat().equals(\"O\"):"+!view.getRecordStat().equals("O"));
               /* if(!view.getRecordStat().equals("O"))
                {
                    status = QRMessage.ResultEnum.ACCOUNT_INVALID.getValue(); // TK Khong kha dung
                    return getResponse(rp, status);
                }*/
            }
            BigDecimal balance = view.getAcyCurrBalance();
            String custno = view.getCustNo();
            Helper.writeLog(QRPAYMENT.class, Level.INFO,"get custno:"+custno);
            CustomerInfo custInfo = Helper.getDBI().getCustInfoByCif(custno);
            data.setCustno(custno);
            data.setMobileno(custInfo.getMobile_Number());
            String custName=custInfo.getFull_Name();
            String custPhone= custInfo.getMobile_Number();
            String custAddress = custInfo.getAddress();
            String custEmail= custInfo.getEMAIL();
           
            BigDecimal amountPayment;
            if(rq.getTypeRequest().equals("CHECK"))
            {
                //CHECK PROMOTION CODE
                BigDecimal amountDiscount = new BigDecimal("0");
                if(data.getRequest().getPromotionCode()!=null)
                {
                    Object[] listCheck = Helper.getDBI().QR_CHECK_PCODE(data);
                    int intCheck = (int) listCheck[0];
                    if(intCheck!=1)
                    {
                        status = QRMessage.ResultEnum.PROMOTIONCODE_INVALID.getValue();
                        return getResponse(rp, status);
                    }
                    amountDiscount= (BigDecimal) listCheck[1];
                }
               amountPayment =BigDecimal.valueOf(amountOrder).subtract(amountDiscount);
            }
            else
            {
                amountPayment= BigDecimal.valueOf(rq.getAmountPayment());
            }
            rp.setAmountPayment(amountPayment.longValue());
            BigDecimal CheckBalace = balance.subtract(amountPayment);
            if(CheckBalace.compareTo(BigDecimal.valueOf(0)) == -1)
            {
                status = QRMessage.ResultEnum.BALANCE_INVALID.getValue(); // TK Khong du so du
                return getResponse(rp, status);
            }
            QRPartnerService service=new QRPartnerService();
            Object[] resultCheck =null;
            boolean resultInsert=false;
            if(rq.getTypeRequest().equals("CHECK"))
            {
                if(rq.getQRType() == 0)
                {
                    status = QRMessage.ResultEnum.VALID.getValue(); // TK Khong du so du
                    return getResponse(rp, status);
                }
                else
                {
                    //CALL PARTNER
                    CheckOrderRequest checkOrder =new CheckOrderRequest();
                    checkOrder.setMoneyTotal(amountOrder);
                    checkOrder.setQrCodeValue(qrCodeValue);
                    String jsonRequest =gson.toJson(checkOrder);

                    Helper.writeLog(QRPAYMENT.class, Level.INFO,"CHECK:"+jsonRequest);
                    resultCheck = service.callService(jsonRequest, rq.getTypeRequest(),rq.getPartnerid());
                    
                }
            }
            else
            if(rq.getTypeRequest().equals("PAYMENT"))
            { 
                String embebedData= "";
                String transactionId =rq.getRequestId();
                Date date = new Date();
                SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddHHmmss");
                String transactionDate = dt.format(date);
                rp.setRequestDate(transactionDate);
                String note=rq.getNote();
                String issuerPaymentCode="970429";
                //Thuc hien thanh toan

                CoreRef = fc.transferFCUBSWithProduct(ProductTransfer, accountNo, partnerAccount, amountPayment, "Thanh toan qua payooqr:"+rq.getOrderNo());
                Helper.writeLog(QRPaymentController.class,Level.INFO,"REFCORE:"+CoreRef);
                if(CoreRef==null)
                {
                    status = QRMessage.ResultEnum.CORE_INVALID.getValue(); // Hach toan loi
                    return getResponse(rp, status);
                }
                else
                    if(CoreRef.equals("TIMEOUT"))
                    {
                        status = QRMessage.ResultEnum.CORE_INVALID.getValue(); // Hach toan loi
                        return getResponse(rp, status);
                    }
                
                rp.setStatus(status);
                rp.setRefcore(CoreRef);
                data.setResponse(rp);
                
                try
                {
                     resultInsert = Helper.getDBI().insertQRPayment(data);
                }
                catch(Exception ex)
                {
                    resultInsert=false;
                    Helper.writeLog(QRPaymentController.class,Level.ERROR,"Loi Insert:"+ex.getMessage());
                }
                Helper.writeLog(QRPaymentController.class,Level.INFO,"Result Insert:"+resultInsert);
                if(!resultInsert)
                {
                    Helper.writeLog(QRPaymentController.class,Level.INFO,"INSERT LOI THUC HIEN REVERT:"+CoreRef);
                    status = QRMessage.ResultEnum.SYSTEM_ERR.getValue();
                    rp.setIsRevert("1");
                    fc.revertTransferFCUBS(CoreRef, 40);
                    return getResponse(rp, status);
                }
                else
                {
                    
                    NotifyTransactionRequest request = new NotifyTransactionRequest();
                    request.setCustomerAddress(custAddress);
                    request.setCustomerEmail(custEmail);
                    request.setCustomerName(custName);
                    request.setCustomerNo(accountNo);
                    request.setCustomerPhone(custPhone);
                    request.setEmbebedData(embebedData);
                    request.setIssuerPaymentCode(issuerPaymentCode);
                    request.setMoneyTotal(amountOrder);
                    request.setNote(note);
                    request.setPaymentType(paymentType);
                    request.setQrCodeValue(qrCodeValue);
                    request.setTransactionDate(transactionDate);
                    request.setTransactionId(transactionId);
                    String jsonRequest = gson.toJson(request);
                    status=QRMessage.ResultEnum.HOLD.getValue();
                    resultCheck = service.callService(jsonRequest, rq.getTypeRequest(),rq.getPartnerid());
                }
            }
            if(resultCheck!=null)
            {
                if(resultCheck.length>0)
                {
                    int statusCode = (int) resultCheck[0];
                    if(statusCode==200)
                    {
                        String jsonResponse = (String) resultCheck[1];
                        int returnCode=-1;
                        if(rq.getTypeRequest().equals("CHECK"))
                        {
                            CheckOrderResponse response=  gson.fromJson(jsonResponse, CheckOrderResponse.class);
                            returnCode= Integer.valueOf(response.getReturnCode()); 
                            String description= response.getDescription();
                            rp.setDescription(description +"("+returnCode+")");
                            if(returnCode ==0)
                            {
                               status = QRMessage.ResultEnum.VALID.getValue();
                               Long rpAmountOrder=response.getResponseData().getMoneyTotal();
                               String orderNo= response.getResponseData().getOrderNo();
                               String expiredDate= response.getResponseData().getExpiredDate();
                               rp.setExpiredDate(expiredDate);
                               rp.setOrderNo(orderNo);
                               rp.setAmountOrder(rpAmountOrder);
                               if(rpAmountOrder!=amountOrder)
                               {
                                   status = QRMessage.ResultEnum.AMOUNTORDER_INVALID.getValue();
                               }
                               return getResponse(rp, status);
                            }
                        }
                        else
                        {
                            NotifyTransactionResponse response=  gson.fromJson(jsonResponse, NotifyTransactionResponse.class);
                            returnCode= Integer.valueOf(response.getReturnCode()); 
                            String description= response.getDescription();
                            rp.setDescription(description +"("+returnCode+")");
                            if(returnCode==0)
                            {
                                status =QRMessage.ResultEnum.VALID.getValue();
                                rp.setResponseDate(response.getResponseData().getTransactionDate());
                                rp.setOrderNo(response.getResponseData().getOrderNo());
                            }
                        }
                        if(returnCode!=0)
                        {
                            if(returnCode==1||returnCode==-16||returnCode==-256)
                            {
                                if(rq.getTypeRequest().equals("CHECK"))
                                {
                                    status=QRMessage.ResultEnum.CHECKORDER_ERROR.getValue();
                                }
                                else
                                    status=QRMessage.ResultEnum.HOLD.getValue();
                                if(rq.getTypeRequest().equals("PAYMENT") && rq.getQRType()==1)
                                {
                                    //thuc hien getOrder
                                    GetOrderRequest request=new GetOrderRequest();
                                    request.setQrCodeValue(qrCodeValue);
                                    request.setMoneyTotal(amountOrder);
                                    String jsonRequest = gson.toJson(request);
                                    Helper.writeLog(QRPAYMENT.class, Level.ERROR,"GETORDER:"+jsonRequest);
                                    resultCheck = service.callService(jsonRequest, "GETORDER",rq.getPartnerid());
                                    statusCode = (int) resultCheck[0];
                                    if(statusCode == 200)
                                    {
                                        String resultString = (String)resultCheck[1];
                                        GetOrderResponse response = gson.fromJson(resultString, GetOrderResponse.class);
                                        int Code= Integer.valueOf(response.getReturnCode()); 
                                        String desc= response.getDescription();
                                        if(Code==0)
                                        {
                                            int statusGetOrder = response.getResponseData().getStatus();
                                            if("2;4;5".contains(String.valueOf(statusGetOrder)))
                                            {
                                                status=QRMessage.ResultEnum.VALID.getValue();
                                            }
                                            else
                                            if("3".contains(String.valueOf(statusGetOrder)))
                                            {
                                                status = QRMessage.ResultEnum.BILL_INVALID.getValue(); 
                                            }
                                            else
                                            if("1".contains(String.valueOf(statusGetOrder)))
                                            {
                                                status = QRMessage.ResultEnum.BILL_SENDFAIL.getValue(); 
                                            }
                                        }
                                    }
                                }
                            }
                            else if(returnCode==-1||returnCode==-2||returnCode==-4||returnCode==-8||returnCode==-1024)
                            {
                                status = QRMessage.ResultEnum.SIGNATURE_INVALID.getValue();
                            }
                            else
                            {
                                status = QRMessage.ResultEnum.BILL_INVALID.getValue();
                            }

                        }
                    }
                    else
                    if(statusCode==99)
                    {
                        if(!rq.getTypeRequest().equals("PAYMENT"))
                        {
                            status= QRMessage.ResultEnum.SERVICE_INVALID.getValue();
                            rp.setDescription("LOI HE THONG");
                            return getResponse(rp, status);
                        }
                        else
                        {
                            status= QRMessage.ResultEnum.HOLD.getValue();
                        }
                    }
                    else
                    {
                        status= QRMessage.ResultEnum.SYSTEM_ERR.getValue();
                        rp.setDescription("CALL PARTNER LOI:"+statusCode);
                    }
                }
            }
            else
            {
                if(CoreRef!=null)
                {   status = QRMessage.ResultEnum.HOLD.getValue();
                }
            }
            Helper.writeLog(QRPaymentController.class,Level.INFO,"CHECK CoreRef!=null:"+CoreRef);
            if(CoreRef!=null)
            {
                Helper.writeLog(QRPaymentController.class,Level.INFO,"CHECK status:"+status);
                if(!status.equals(QRMessage.ResultEnum.HOLD.getValue())&&!status.equals(QRMessage.ResultEnum.VALID.getValue()))
                {
                    if(rq.getTypeRequest().equals("PAYMENT"))
                    {
                        Helper.writeLog(QRPaymentController.class,Level.INFO,"REVERT GD QR SO REF:"+CoreRef); 
                        // giao dich hoan tien
                        rp.setIsRevert("1");
                        fc.revertTransferFCUBS(CoreRef, 40);
                    }
                }
            }
            rp.setStatus(status);
            if(rq.getTypeRequest().equals("PAYMENT")&&resultInsert)
            {
                Helper.writeLog(QRPaymentController.class,Level.INFO,"UPDATE KET QUA GDQR"); 
                data.setResponse(rp);
                Helper.getDBI().updateQRPayment(data);
            }
        }
        catch(Exception ex)
        {
            rp.setStatus(QRMessage.ResultEnum.SYSTEM_ERR.getValue());
            Helper.writeLog(QRPaymentController.class,Level.ERROR, "Exception:"+ex.getMessage());
        }
        String xmlResponse = Xml.Marshaller(rp);
        return xmlResponse;
    }
    private String getResponse(QRPaymentResponse rp,String status) throws Exception
    {
        rp.setStatus(status);
        String xmlResponse = Xml.Marshaller(rp);
        return xmlResponse;
    }
}
