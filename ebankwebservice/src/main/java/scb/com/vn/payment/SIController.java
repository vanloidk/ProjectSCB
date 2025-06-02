/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;

import com.google.gson.Gson;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateReq;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateRes;
import scb.com.vn.common.model.collated.OneInventoryRequest;
import scb.com.vn.common.model.collated.OneInventoryResponse;
import scb.com.vn.common.model.collated.Collated;
import scb.com.vn.common.model.collated.CollatedDetail;
import scb.com.vn.common.model.transfer.Transaction;
import scb.com.vn.common.model.transfer.TransactionDetail;
import scb.com.vn.common.model.transfer.TransferMoneyEbankReq;
import scb.com.vn.common.model.transfer.TransferMoneyReq;
import scb.com.vn.common.model.transfer.TransferMoneyRes;
import scb.com.vn.common.model.transfer.napas.TransferMoney247DetailReq;
import scb.com.vn.common.model.transfer.napas.TransferMoney247DetailRes;
import scb.com.vn.common.model.transfer.napas.TransferMoney247EbankReq;
import scb.com.vn.common.model.transfer.napas.TransferMoney247Req;
import scb.com.vn.common.model.transfer.napas.TransferMoney247Res;
import scb.com.vn.common.model.transfer.status.GetListTransactionByDateRequest;
import scb.com.vn.common.model.transfer.status.GetListTransactionByDateResponse;
import scb.com.vn.common.model.transfer.status.TransactionByDate;
import scb.com.vn.common.model.transfer.status.TransactionDetailByDate;
import scb.com.vn.controller.Fcubs;
import scb.com.vn.controller.KYCController;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.dbi.dto.VwCustAccountNew;
import scb.com.vn.enumUtils.ResponseCodeEnum;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.ZipData;
import scb.com.vn.model.FundTransferRq;
import scb.com.vn.model.status.transferMoney.GetStatusOfTransferMoneyReq;
import scb.com.vn.model.status.transferMoney.GetStatusOfTransferMoneyRes;
import scb.com.vn.model.status.transferMoney.TransferMoneyTransactionInfo;
import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.KYCErrorMsg;
import scb.com.vn.utility.KYCUtils;
import scb.com.vn.utility.MessageMB;
import scb.com.vn.utility.SiUtils;
import scb.com.vn.utility.XMLUtils;
import vn.com.scb.bian.ExecutePaymentTransactionExternal_in_Type;
import vn.com.scb.bian.ExecutePaymentTransactionExternal_out_Type;
import vn.com.scb.bian.ExecutePaymentTransactionInternal_in_Type;
import vn.com.scb.bian.ExecutePaymentTransactionInternal_out_Type;
import vn.com.scb.bian.service.IIBPaymentService;
import vn.com.scb.bian.utility.BianException;
import vn.com.scb.bian.utility.IIBConstants;
import vn.com.tvsi.www.SCBWSLocator;
import vn.com.tvsi.www.SCBWSSoapStub;

/**
 *
 * @author lydty
 */
public class SIController {

    private static final Logger LOGGER = Logger.getLogger(SIController.class);

    String ProductTransferToSI = Configuration.getProperty("fcubs.producttransfer.transfertosi");
    String ProductTransferInternal = Configuration.getProperty("fcubs.producttransfer.transferfromsi.internal");
    String ProductTransferExternal = Configuration.getProperty("fcubs.producttransfer.transferfromsi.external");
    String ProductTCH = Configuration.getProperty("fcubs.producttransfer.tch.internal");
    private static final String productKieuhoiExternalSCB = Configuration.getProperty("fcubs.producttransfer.kieuhoi.external");
    private static final String productKieuhoiExternalSCB247 = Configuration.getProperty("fcubs.producttransfer.kieuhoi.external247");
    private static final String userIdFcubs = Configuration.getProperty("fcubs.userid");
    private static final String useridfcubs_MB = Configuration.getProperty("fcubs.userid.mobile");
    private static final String ProductFromSCB_MOBILE = Configuration.getProperty("fcubs.producttransfer.mobile.ibt");
    private static final String ProductFromSCB_COUNTER1 = Configuration.getProperty("fcubs.smartlink.product_fromscb_COUNTER1");
    private static final String ProductFromSCB_COUNTER2 = Configuration.getProperty("fcubs.smartlink.product_fromscb_COUNTER2");
    private static final String producttransferMobileID = Configuration.getProperty("fcubs.producttransfer.mobile.ID");
    private static final String GLPAYMENTDOMESTIC = Configuration.getProperty("fcubs.GLPayment.domestic");
    private static final String GLPAYMENTINTERNALID = Configuration.getProperty("fcubs.GLPayment.internalId");

    private static final String GLPAYMENTINTERID = Configuration.getProperty("fcubs.GLPayment.internal.id");
    private static final String GLKIEUHOIDOMESTIC = Configuration.getProperty("fcubs.GLKieuhoi.domestic");
    private static final String productKieuhoiInternalSCB = Configuration.getProperty("fcubs.producttransfer.kieuhoi.internal");
    private static final String productKieuhoiInternalCMNDSCB = Configuration.getProperty("fcubs.producttransfer.kieuhoi.internal.id");
    private static final String GLPAYMENTEXTERNAPAS = Configuration.getProperty("fcubs.GLPayment.external.napas");

    String ProductIDCard = Configuration.getProperty("fcubs.producttransfer.transferfromsi.idcard");
    String GWFcubsUserid = Configuration.getProperty("fcubs.userid");

    /**
     *
     * @param strXML
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws RemoteException
     * @throws IOException
     * @throws Exception
     */
    public String requestTransfer(String strXML) throws ParserConfigurationException, SAXException, RemoteException, IOException, Exception {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String MSGID = eElement.getElementsByTagName("MSGID").item(0).getTextContent();
            String TXNREF = eElement.getElementsByTagName("TXNREF").item(0).getTextContent();
            String TRANSDATE = eElement.getElementsByTagName("TRANSDATE").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String TRANSDATA = eElement.getElementsByTagName("TRANSDATA").item(0).getTextContent();
            String SIGNATURE = eElement.getElementsByTagName("SIGNATURE").item(0).getTextContent();
            if (!MSGID.endsWith("001")) {
                return getMsgError("97", "MSGID IS WRONG");
            }
            //check chu ky
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return getMsgError("98", "MAC IS WRONG");
            }
            String MD5Key = key[0].toString();
            String strMAC = ControllerUtil.EncriptMD5(MD5Key + MSGID + TXNREF + TRANSDATE);
            strMAC = strMAC.toUpperCase();
            SIGNATURE = SIGNATURE.toUpperCase();

            if (!strMAC.equals(SIGNATURE)) {
                return getMsgError("98", "MAC IS WRONG");
            }
            if (!PROVIDERID.equals("TVSI")) {
                ProductTransferInternal = ProductTCH;
            }
            //INSERT_SI_TRANSFROMSI
            Helper.writeLogErrorNonDB(SIController.class, "INSERT DU LIEU XUONG DB :" + TXNREF);
            String[] resultTRANSFER = Helper.getDBI().SI_INSERTSITRANSFROMSI(PROVIDERID, TXNREF, TRANSDATE);
            String Status = resultTRANSFER[1].toString();

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            String rpDesc = "";
            if (Status.endsWith("00")) {
                int IDMaster = Integer.valueOf(resultTRANSFER[0].toString());
                Element SCBREF = responsedoc.createElement("SCBREF");
                SCBREF.appendChild(responsedoc.createTextNode(String.valueOf(IDMaster)));
                response.appendChild(SCBREF);
                //unzip data
                String DataDetail = ZipData.UnZipToString(TRANSDATA);
                Helper.writeLogErrorNonDB(SIController.class, "du lieu cua doi tac sau unzip:" + DataDetail);
                Document docDetail = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(DataDetail)));
                NodeList NodeListGD = docDetail.getElementsByTagName("TRN_DETAIL");
                String reponseDetail = "";

                for (int i = 0; i < NodeListGD.getLength(); i++) {
                    Node NodeGD = NodeListGD.item(i);
                    if (NodeGD.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementGD = (Element) NodeGD;
                        String TXNDETAILID = eElementGD.getElementsByTagName("TXNDETAILID").item(0).getTextContent();
                        String PARTNERACCOUNT = eElementGD.getElementsByTagName("PARTNERACCOUNT").item(0).getTextContent();
                        String CUSTUMERNAME = eElementGD.getElementsByTagName("CUSTUMERNAME").item(0) == null ? eElementGD.getElementsByTagName("CUSTOMERNAME").item(0).getTextContent() : eElementGD.getElementsByTagName("CUSTUMERNAME").item(0).getTextContent();
                        String CUSTUMERACCOUNT = eElementGD.getElementsByTagName("CUSTUMERACCOUNT").item(0) == null ? eElementGD.getElementsByTagName("CUSTOMERACCOUNT").item(0).getTextContent() : eElementGD.getElementsByTagName("CUSTUMERACCOUNT").item(0).getTextContent();
                        String MOBILENO = eElementGD.getElementsByTagName("MOBILENO").item(0) == null ? "" : eElementGD.getElementsByTagName("MOBILENO").item(0).getTextContent();
                        String BRANCHCODE = eElementGD.getElementsByTagName("BRANCHCODE").item(0) == null ? "" : eElementGD.getElementsByTagName("BRANCHCODE").item(0).getTextContent();
                        String BANKCODE = eElementGD.getElementsByTagName("BANKCODE").item(0) == null ? "000000" : eElementGD.getElementsByTagName("BANKCODE").item(0).getTextContent();
                        String BRANCH = eElementGD.getElementsByTagName("BRANCH").item(0) == null ? "" : eElementGD.getElementsByTagName("BRANCH").item(0).getTextContent();
                        String DESCRIPTION = eElementGD.getElementsByTagName("DESCRIPTION").item(0) == null ? "" : eElementGD.getElementsByTagName("DESCRIPTION").item(0).getTextContent();

                        Double AMOUNT = Double.valueOf(eElementGD.getElementsByTagName("AMOUNT").item(0).getTextContent());
                        if (AMOUNT < 11000) {
                            return getMsgError("21", getMsg("21"));
                        }
                        String CCY = eElementGD.getElementsByTagName("CCY").item(0).getTextContent();

                        String Addinfo = "01";
                        String TypeCard = "ACCOUNT";// Tai khoan
                        String StatusDetail = "00";
                        String SCBIDDetail = "0";
                        String custaccount = CUSTUMERACCOUNT;

                        if (eElementGD.getElementsByTagName("AddInfo").item(0) != null) {
                            Addinfo = eElementGD.getElementsByTagName("AddInfo").item(0).getTextContent();
                        }
                        if (eElementGD.getElementsByTagName("ADDINFO").item(0) != null) {
                            Addinfo = eElementGD.getElementsByTagName("ADDINFO").item(0).getTextContent();
                        }
                        if (eElementGD.getElementsByTagName("TYPECARD").item(0) != null) {
                            TypeCard = eElementGD.getElementsByTagName("TYPECARD").item(0).getTextContent();
                            //Truy van thong tin the
                            String[] CardIfo = Helper.getDBI().getCardStatus(CUSTUMERACCOUNT);
                            String statusCard = CardIfo[0];
                            if (statusCard.equals("00")) {
                                custaccount = CardIfo[2];
                                String FULLNAME = CardIfo[1];
                            } else {
                                StatusDetail = "01";
                            }
                        }

                        String cityName = "";
                        String opendate = "";
                        if (!Addinfo.trim().equals("")) {
                            Addinfo = Addinfo.replace("|", "#");
                            String[] parts = Addinfo.split("#", -1);
                            TypeCard = parts[0].toString();
                            switch (TypeCard) {
                                case "01":
                                    TypeCard = "ACCOUNT";
                                case "02":
                                    TypeCard = "IDNUMBER";
                                case "03":
                                    TypeCard = "CARD";
                            }
                            if (parts.length > 1) {
                                cityName = parts[1].toString();
                            }
                            if (parts.length > 2) {
                                opendate = parts[2].toString();
                            }
                        }
                        switch (TypeCard) {
                            case "ACCOUNT":
                                TypeCard = "02";
                            case "IDNUMBER":
                                TypeCard = "03";
                            case "CARD":
                                TypeCard = "01";
                        }
                        String typeFunction;
                        if (BANKCODE.endsWith("000000")) {
                            typeFunction = "01"; // ck cung he thong
                            if (TypeCard.equals("01")) {
                                if (!CUSTUMERACCOUNT.substring(0, 6).contains("970429")) {
                                    return getMsgError("97", "CARDNUMBER IS WRONG");
                                } else {
                                    String[] CardIfo = Helper.getDBI().getCardStatus(CUSTUMERACCOUNT);
                                    String statusCard = CardIfo[0];
                                    if (statusCard.equals("00")) {
                                        custaccount = CardIfo[2];
                                    } else {
                                        StatusDetail = "01";
                                    }
                                }

                            }
                        } else {
                            typeFunction = "02"; // ck ngoai he thong
                        }

                        if (StatusDetail.equals("00")) {
                            int checkPregolive = Helper.getDBI().checkPartnerPregolive(PROVIDERID);
                            if (checkPregolive > 0) {
                                int checkAccountPregolive = Helper.getDBI().checkAccountPregolive(PROVIDERID, CUSTUMERACCOUNT);
                                if (checkAccountPregolive == 0) {
                                    return getMsgError("99", "ACCOUNT FOR TEST IS WRONG");
                                }
                            }
                            //INSERT_TRANSFERFROMSI_DETAIL
                            Object[] resultInsertDetail = Helper.getDBI().SI_INSERTTRANSFERFROMSIDETAIL(IDMaster,
                                    TXNDETAILID,
                                    PARTNERACCOUNT,
                                    CUSTUMERNAME,
                                    CUSTUMERACCOUNT,
                                    BANKCODE,
                                    BRANCH,
                                    AMOUNT,
                                    CCY,
                                    DESCRIPTION,
                                    typeFunction,
                                    TypeCard);
                            StatusDetail = resultInsertDetail[0].toString();
                            SCBIDDetail = resultInsertDetail[1].toString();
                            Helper.writeLogErrorNonDB(SIController.class, "TRUOC KHI HACH TOAN:" + TXNREF);
                            if (StatusDetail.endsWith("00")) {
                                Fcubs fc = new Fcubs();
                                //thuc hien chuyen khoan tu tk CTY chung khoan den TK KHACH HANG
                                String CoreRef;
                                FundTransferRq fundTransferRq = new FundTransferRq();
                                fundTransferRq.setAmount(String.valueOf(AMOUNT));
                                fundTransferRq.setFromAccount(PARTNERACCOUNT);
                                fundTransferRq.setToAccount(CUSTUMERACCOUNT);
                                fundTransferRq.setRemark(DESCRIPTION);
                                fundTransferRq.setBeneName(CUSTUMERNAME);
                                fundTransferRq.setBankCode(BANKCODE);
                                VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(PARTNERACCOUNT);
                                fundTransferRq.setCifNo(custacc.getCustNo());
                                fundTransferRq.setCityCode(cityName);
                                fundTransferRq.setBeneTel(MOBILENO);
                                fundTransferRq.setBranchName(BRANCH);
                                fundTransferRq.setBranchCode(BRANCHCODE);
                                fundTransferRq.setIDCityCode(cityName);
                                fundTransferRq.setIDOpenDate(opendate);
                                /*
                                if(typeTransfer.endsWith("02"))
                                {
                                    //chuyen khoan nhan bang cmnd
                                    fundTransferRq.setIDCityCode(cityName);
                                    fundTransferRq.setIDOpenDate(opendate);
                                    fundTransferRq.setBranchCode("137");
                                    fundTransferRq.setBranchName("CHI NHANH SAI GON");
                                }*/
                                if (BANKCODE.endsWith("000000")) {
                                    // chuyen khoan cung he thong
                                    if (!TypeCard.endsWith("03")) {
                                        //ck nhan bang tk/ The noi dia
                                        CoreRef = fc.transferWithReturnTimeOut(ProductTransferInternal, PARTNERACCOUNT, custaccount, BigDecimal.valueOf(AMOUNT), DESCRIPTION, 30);
                                    } else {
                                        ExchangeRateRes resExchange = new ExchangeRateRes();
                                        resExchange.setCcy("VND");
                                        resExchange.setCcyExchange("VND");
                                        resExchange.setRate(BigDecimal.ONE);
                                        fundTransferRq.setResExchange(resExchange);
                                        fundTransferRq.setUserId(GWFcubsUserid);
                                        fundTransferRq.setUserName(custacc.getFullName());
                                        //ck nhan bang cmnd
                                        // CoreRef= fc.transferFCUBSInternalID(fundTransferRq);
                                        fundTransferRq.setProductCode(ProductIDCard);
                                        CoreRef = transferToIDCARD(fundTransferRq);
                                        //ck nhan bang cmnd
                                        //CoreRef= fc.transferFCUBSInternalID(fundTransferRq);
                                    }

                                } else {
                                    String FeeOutAccount = Configuration.getProperty("TVSI.ACCOUNT.FEEOUT");
                                    if (FeeOutAccount.contains(PARTNERACCOUNT)) {
                                        ProductTransferExternal = Configuration.getProperty("TVSI.PRODUCT.FEEOUT");
                                    }
                                    // chuyen khoan ngoai he thong
                                    CoreRef = fc.transferFCUBSDomesticWithProductAndCharge2(fundTransferRq, ProductTransferExternal, "SI");
                                }
                                //CoreRef="TEST";
                                if (CoreRef != null) {
                                    StatusDetail = "00";
                                    if (CoreRef.equals("TIMEOUT")) {
                                        StatusDetail = "16";
                                    }
                                } else {
                                    //Lỗi hệ thống không cắt được tiền từ fcdb
                                    StatusDetail = "09";
                                    rpDesc = "Lỗi hệ thống không trừ tiền tài khoản được.";
                                }
                                //Update transferdetail
                                Date date = new Date();
                                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                                String toDay = df.format(date);
                                Helper.writeLogErrorNonDB(SIController.class, "UPDATE KQ HACH TOAN :" + CoreRef + "-" + TXNREF);
                                Helper.getDBI().SI_UPDATETRANSFERFROMSIDETAIL(Integer.valueOf(SCBIDDetail), CoreRef, toDay, StatusDetail);
                            }
                        }
                        reponseDetail = reponseDetail + "<TRN_DETAIL><TXNDETAILID>" + TXNDETAILID + "</TXNDETAILID>"
                                + "<SCBDETAILID>" + SCBIDDetail + "</SCBDETAILID>"
                                + "<RESPONSECODE>" + StatusDetail + "</RESPONSECODE>"
                                + "<DESCRIPTION>" + DESCRIPTION + "</DESCRIPTION></TRN_DETAIL>";
                    }
                }

                String zipResponseData = ZipData.ZipToString(reponseDetail);
                System.out.print("du lieu cua SCB truoc zip:" + reponseDetail);
                Element rpTRANSDATA = responsedoc.createElement("TRANSDATA");
                rpTRANSDATA.appendChild(responsedoc.createTextNode(zipResponseData));
                response.appendChild(rpTRANSDATA);
            }

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(Status));
            response.appendChild(RESPONSECODE);

            Element rpTXNREF = responsedoc.createElement("TXNREF");
            rpTXNREF.appendChild(responsedoc.createTextNode(TXNREF));
            response.appendChild(rpTXNREF);

            Element rpPROVIDERID = responsedoc.createElement("PROVIDERID");
            rpPROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(rpPROVIDERID);

            Element rpDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            rpDESCRIPTION.appendChild(responsedoc.createTextNode(rpDesc));
            response.appendChild(rpDESCRIPTION);

            String strSignSCB = ControllerUtil.EncriptMD5(MD5Key + Status + TXNREF);
            Element SIGNATURESCB = responsedoc.createElement("SIGNATURESCB");
            SIGNATURESCB.appendChild(responsedoc.createTextNode(strSignSCB));
            response.appendChild(SIGNATURESCB);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            Helper.writeLogErrorNonDB(SIController.class, stringOut.toString());
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogErrorNonDB(SIController.class, ex.getMessage());
            return getMsgError("99", "ERROR SYSTEM");
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String queryAccount(String strXML) {
        try {
            System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String MSGID = eElement.getElementsByTagName("MSGID").item(0).getTextContent();
            String TXNREF = eElement.getElementsByTagName("TXNREF").item(0).getTextContent();
            String TRANSDATE = eElement.getElementsByTagName("TRANSDATE").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String CUSTUMERACCOUNT = eElement.getElementsByTagName("CUSTUMERACCOUNT").item(0) == null ? eElement.getElementsByTagName("CUSTOMERACCOUNT").item(0).getTextContent() : eElement.getElementsByTagName("CUSTUMERACCOUNT").item(0).getTextContent();
            String SIGNATURE = eElement.getElementsByTagName("SIGNATURE").item(0).getTextContent();
            if (MSGID == null || TXNREF == null || TRANSDATE == null || PROVIDERID == null || SIGNATURE == null || CUSTUMERACCOUNT == null) {
                return getMsgError("97", "NOT ENOUGH PARAMS");
            }
            String TypeCard = eElement.getElementsByTagName("TYPECARD").item(0) == null ? "ACCOUNT" : eElement.getElementsByTagName("TYPECARD").item(0).getTextContent();
            String statusAcc = "00";
            String FULLNAME = "";
            String CardNumber = "";
            if (TypeCard.equals("CARD")) {
                CardNumber = CUSTUMERACCOUNT;
                //Truy van thong tin the
                String[] CardIfo = Helper.getDBI().getCardStatus(CUSTUMERACCOUNT);
                String statusCard = CardIfo[0];
                if (statusCard.equals("00")) {
                    CUSTUMERACCOUNT = CardIfo[2];
                    FULLNAME = CardIfo[1];
                } else {
                    if (statusCard.equals("04")) {
                        statusAcc = "01";
                    } else if (statusCard.equals("01") || statusCard.equals("02")) {
                        statusAcc = "02";
                    } else if (statusCard.equals("03")) {
                        statusAcc = "03";
                    } else {
                        statusAcc = "01";
                    }

                }
            }
            if (!MSGID.endsWith("002")) {
                return getMsgError("97", "MSGID IS WRONG");
            }
            //check chu ky
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {

                return getMsgError("98", "MAC IS WRONG");
            }
            String MD5Key = key[0].toString();
            String strMAC = ControllerUtil.EncriptMD5(MD5Key + MSGID + TXNREF + TRANSDATE);
            strMAC = strMAC.toUpperCase();
            LOGGER.info("MACSCB:" + strMAC);
            SIGNATURE = SIGNATURE.toUpperCase();
            if (!strMAC.equals(SIGNATURE)) {
                return getMsgError("98", "MAC IS WRONG");
            }
            if (statusAcc.equals("00")) {
                /* String[] CustAcc = Helper.getDBI().SI_CHECKACCOUNT(CUSTUMERACCOUNT, 0);
                statusAcc = CustAcc[1].toString();
                if (statusAcc.endsWith("00") & TypeCard.equals("ACCOUNT")) {
                    FULLNAME = CustAcc[0].toString();
                }*/
                List custacc = Helper.getDBI().GetBeneficaryName(CUSTUMERACCOUNT);
                if (custacc == null || custacc.isEmpty()) {
                    statusAcc = "01";
                } else {

                    HashMap hm_Casa = (HashMap) custacc.get(0);
                    FULLNAME = hm_Casa.get("acctdesc").toString();
                    String custno = hm_Casa.get("idcustomer").toString();
                    String checkCif = Helper.getDBI().checkCifNoCredit(custno);
                    if (checkCif.equals("1")) {
                        statusAcc = "01";
                    } else {
                        statusAcc = "00";
                    }
                }
            }
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);
            if (statusAcc.endsWith("00")) {
                Element CUSTUMERNAME = responsedoc.createElement("CUSTUMERNAME");
                CUSTUMERNAME.appendChild(responsedoc.createTextNode(FULLNAME));
                response.appendChild(CUSTUMERNAME);
            }
            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(statusAcc));
            response.appendChild(RESPONSECODE);

            Element rpCUSTUMERACCOUNT = responsedoc.createElement("CUSTUMERACCOUNT");
            if (TypeCard.equals("CARD")) {
                CUSTUMERACCOUNT = CardNumber;
            }
            rpCUSTUMERACCOUNT.appendChild(responsedoc.createTextNode(CUSTUMERACCOUNT));
            response.appendChild(rpCUSTUMERACCOUNT);

            Element rpTXNREF = responsedoc.createElement("TXNREF");
            rpTXNREF.appendChild(responsedoc.createTextNode(TXNREF));
            response.appendChild(rpTXNREF);

            Element rpPROVIDERID = responsedoc.createElement("PROVIDERID");
            rpPROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(rpPROVIDERID);

            Element rpDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            rpDESCRIPTION.appendChild(responsedoc.createTextNode(""));
            response.appendChild(rpDESCRIPTION);

            String strSignSCB = ControllerUtil.EncriptMD5(MD5Key + statusAcc + TXNREF);
            Element SIGNATURESCB = responsedoc.createElement("SIGNATURESCB");
            SIGNATURESCB.appendChild(responsedoc.createTextNode(strSignSCB));
            response.appendChild(SIGNATURESCB);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            System.out.println(stringOut.toString());
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogErrorNonDB(SIController.class, ex.getMessage());
            return getMsgError("99", ex.getMessage());
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String getListBankCode(String strXML) {
        try {
            System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String MSGID = eElement.getElementsByTagName("MSGID").item(0).getTextContent();
            String TXNREF = eElement.getElementsByTagName("TXNREF").item(0).getTextContent();
            String TRANSDATE = eElement.getElementsByTagName("TRANSDATE").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String SIGNATURE = eElement.getElementsByTagName("SIGNATURE").item(0).getTextContent();
            if (!MSGID.endsWith("003")) {
                return "99";
            }
            //check chu ky
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return "98";
            }
            String MD5Key = key[0].toString();
            String strMAC = ControllerUtil.EncriptMD5(MD5Key + MSGID + TXNREF + TRANSDATE);
            if (!strMAC.equals(SIGNATURE)) {
                return "98";
            }
            ArrayList ListBank = Helper.getDBI().SI_getListBank();
            String strListBank = "";
            for (int j = 0; j < ListBank.size(); j++) {
                List strSTK = (List) ListBank.get(j);
                strListBank = strListBank + "<BANK><BANKCODE>" + strSTK.get(1).toString() + "</BANKCODE>"
                        + "<BANKNAME>" + strSTK.get(0).toString() + "</BANKNAME>"
                        + "</BANK>";
            }
            String zipData = ZipData.ZipToString(strListBank);

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode("00"));
            response.appendChild(RESPONSECODE);

            Element rpTXNREF = responsedoc.createElement("TXNREF");
            rpTXNREF.appendChild(responsedoc.createTextNode(TXNREF));
            response.appendChild(rpTXNREF);

            Element rpPROVIDERID = responsedoc.createElement("PROVIDERID");
            rpPROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(rpPROVIDERID);

            Element LISTDATA = responsedoc.createElement("LISTDATA");
            LISTDATA.appendChild(responsedoc.createTextNode(zipData));
            response.appendChild(LISTDATA);

            String strSignSCB = ControllerUtil.EncriptMD5(MD5Key + "00" + TXNREF);
            Element SIGNATURESCB = responsedoc.createElement("SIGNATURESCB");
            SIGNATURESCB.appendChild(responsedoc.createTextNode(strSignSCB));
            response.appendChild(SIGNATURESCB);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            System.out.println(stringOut.toString());
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogErrorNonDB(SIController.class, ex.getMessage());
            return "99";
        }
    }

    /**
     *
     * @param PPARTNERID
     * @param PCUSTUMERACCOUNT
     * @param PCUSTUMERNAME
     * @param PAMOUNT
     * @param PCCY
     * @param PCHANNELID
     * @param PTRANSDATE
     * @param PADDINFO
     * @return
     */
    public String transferToSI(String PPARTNERID,
            String PCUSTUMERACCOUNT,
            String PCUSTUMERNAME,
            BigDecimal PAMOUNT,
            String PCCY,
            String PCHANNELID,
            String PTRANSDATE, //ddMMyyyhhmiss
            String PADDINFO) //TkCK|Ten KH|DIEN GIAI
    {
        try {
            //PCHANNELID: 100: EBANKING; 101: MOBILEBANKING;102: TAI QUAY; 103: TM Tai quay
            //check input
            if (PPARTNERID == null || PCUSTUMERACCOUNT == null
                    || PCUSTUMERNAME == null
                    || PCCY == null
                    || PCHANNELID == null
                    || PTRANSDATE == null) {
                return "14";
            }
            Helper.writeLog(SIController.class, org.apache.log4j.Level.INFO, "==================================== partnerid: " + PPARTNERID);
            Helper.writeLog(SIController.class, org.apache.log4j.Level.INFO, "Truoc khi insert SI_InsertSITRANFERTOSI");
            //check han muc kyc
            String branchCust = CommonUtils.getBranchAccount(PCUSTUMERACCOUNT);
            String resultCheck = CommonUtils.checkOverKYC(PCUSTUMERACCOUNT, branchCust, PAMOUNT);
            if (!resultCheck.equals("00")) {
                return resultCheck;
            }
            //check du lieu
            Object[] result = Helper.getDBI().SI_InsertSITRANFERTOSI(PPARTNERID,
                    PCUSTUMERACCOUNT, PCUSTUMERNAME, PAMOUNT,
                    PCCY, PCHANNELID,
                    PTRANSDATE, PADDINFO);

            Helper.writeLog(SIController.class, org.apache.log4j.Level.INFO, "Sau khi insert SI_InsertSITRANFERTOSI: " + result[2].toString());

            String status = result[2].toString();
            String strID = "0";
            String RESPONSECODE = "";
            String TXNREF = "";
            String DESCRIPTION = "";
            String coreref = "";
            if (status.endsWith("00")) {

                Helper.writeLog(SIController.class, org.apache.log4j.Level.INFO, "status = 0");
                strID = result[0].toString();
                String SIACCOUNT = result[1].toString();
                //chuyen khoan
                Fcubs fc = new Fcubs();
                String[] strAddInfo = PADDINFO.replace("|", "#").split("#");
                String useridfcubs = "";
                if (PCHANNELID.equals("101")) {
                    useridfcubs = Configuration.getProperty("fcubs.userid.mobile");
                }
                if (PCHANNELID.equals("102")) {
                    useridfcubs = Configuration.getProperty("fcubs.userid.counter");
                } else {
                    useridfcubs = Configuration.getProperty("fcubs.userid");
                }

                if (PCHANNELID.equals("103")) {
                    branchCust = PCUSTUMERACCOUNT.substring(0, 3);
                    PCUSTUMERACCOUNT = PCUSTUMERACCOUNT.substring(3);
                }
                String branchSI = CommonUtils.getBranchAccount(SIACCOUNT);

                Helper.writeLog(SIController.class, org.apache.log4j.Level.INFO, "Truoc khi cat tien");

                //coreref= fc.transferFCUBSWithTimeOut(ProductTransferToSI, useridfcubs, branchCust, PCUSTUMERACCOUNT, branchSI, SIACCOUNT, PAMOUNT, strAddInfo[2].toString(), 30);                
                //coreref= fc.transferFCUBSWithProduct(ProductTransferToSI,PCUSTUMERACCOUNT,SIACCOUNT, PAMOUNT, strAddInfo[2].toString());
//                String Desc = "Nop tien vao TKCK:" + strAddInfo[0].toString() + " Chu TKCK:" + strAddInfo[1].toString();
                String Desc = "Nop tien vao TKCK:" + strAddInfo[0] + " Chu TKCK:" + strAddInfo[1];
                Helper.writeLog(SIController.class, org.apache.log4j.Level.INFO, "In ra coi addInfo" + Desc);
                coreref = fc.transferFCUBSWithTimeOut(ProductTransferToSI, useridfcubs, branchCust, PCUSTUMERACCOUNT, branchSI, SIACCOUNT, PAMOUNT, Desc, 30);

                Helper.writeLog(SIController.class, org.apache.log4j.Level.INFO, "======================================================Sau khi cat tien: " + coreref);

                if (coreref == null) {
                    status = "09";
                } else if (coreref.equals("TIMEOUT")) {
                    status = "09";
                } else {
                    // trừ tiền thanhf công, confirm qua đối tác
                    //confirm to SI
                    //check chu ky                    

                    String[] key = Helper.getDBI().ONL_GetMACkeys(PPARTNERID);
                    String MD5Key = key[0].toString();

                    Helper.writeLog(SIController.class, org.apache.log4j.Level.INFO, "======================================================Lay key MD5: " + MD5Key);

                    String SignnatureSCB = ControllerUtil.EncriptMD5(MD5Key + strID + PTRANSDATE);

                    Helper.writeLog(SIController.class, org.apache.log4j.Level.INFO, "======================================================Chu Ky: " + SignnatureSCB);
                    /*
                    if (PPARTNERID.endsWith("TVSI")) 
                    {
                        Helper.writeLog(SIController.class, org.apache.log4j.Level.INFO, "======================================================Tra ket qua ws TVSI");
                        String resultConfirmSI = Helper.callwsTVSI(strID, SIACCOUNT,
                                PCUSTUMERACCOUNT,
                                PCUSTUMERNAME,
                                PAMOUNT,
                                PCCY,
                                PCHANNELID,
                                PTRANSDATE,
                                PADDINFO,
                                PPARTNERID,
                                SignnatureSCB);
                        Helper.writeLog(SIController.class, org.apache.log4j.Level.INFO, "======================================================Confirm Xong TVSI: " + resultConfirmSI);
                        if(resultConfirmSI==null)
                        {
                            status = "12"; // timeout đợi đối soát; gia lap thanh congs
                        }
                        else
                        {
                            if (resultConfirmSI.endsWith("TIMEOUT")) {
                                status = "12"; // timeout đợi đối soát
                            } else {
                                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(resultConfirmSI)));
                                Node nodeRoot = doc.getElementsByTagName("RESPONSE").item(0);
                                Element eElement = (Element) nodeRoot;

                                RESPONSECODE = eElement.getElementsByTagName("RESPONSECODE").item(0).getTextContent();
                                System.out.println("RESPONSE TVSI" + RESPONSECODE);
                                String SCBREF = eElement.getElementsByTagName("SCBREF").item(0).getTextContent();
                                TXNREF = eElement.getElementsByTagName("TXNREF").item(0).getTextContent();
                                String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
                                DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
                                String SIGNATURE = eElement.getElementsByTagName("SIGNATURE").item(0).getTextContent();
                                if (RESPONSECODE.endsWith("00")) {
                                    //xac nhan thanh cong
                                    status = "00";
                                } else {
                                    // xac nhan that bai hoan tien
                                    String refRefund = fc.transferFCUBSWithProduct(ProductTransferToSI, SIACCOUNT, PCUSTUMERACCOUNT, PAMOUNT, "HOAN TIEN CHO GIAO DICH " + coreref);
                                    status = "11";
                                    System.out.print("Da hoan tien cho giai dich " + coreref + " So ref hoan tien: " + refRefund);
                                }

                            }
                        }
                    }*/
                    status = "00";
                }

                System.out.println("CONFIRM DB:" + strID);
                Helper.getDBI().SI_CONFIRMSITRANFERTOSI(Integer.valueOf(strID), coreref, status, RESPONSECODE, TXNREF, DESCRIPTION);
            }
            System.out.println("RESULE :" + status);
            return status + "#" + coreref;
        } catch (RemoteException ex) {
            try {
                throw ex;
            } catch (RemoteException ex1) {
                LOGGER.error(ex1);
            }
            Helper.writeLogErrorNonDB(SIController.class, ex.getMessage());
            Helper.writeLog(SIController.class, org.apache.log4j.Level.INFO, "In ra log loi 1" + ex.getMessage());
            return "99";
        } catch (Exception ex) {
            Helper.writeLogErrorNonDB(SIController.class, ex.getMessage());
            Helper.writeLog(SIController.class, org.apache.log4j.Level.INFO, "In ra log loi 2" + ex.getMessage());
            return "99";
        }
    }

    /**
     *
     * @param strTKCK
     * @return
     */
    public String getSIName(String strTKCK) {
        try {
            if (strTKCK.equals("408697")) {
                return "99";
            }
            return "00" + "#" + "LE THU HUYEN";
            /*
            SCBWSLocator wssl = new SCBWSLocator();
            wssl.setSCBWSSoapEndpointAddress(Configuration.getProperty("ws.url.tvsi.address"));
            SCBWSSoapStub wss = (SCBWSSoapStub) wssl.getSCBWSSoap();
            wss.setTimeout(40000);// 40giay
            String strResult;
            strResult = wss.getCustomerName(strTKCK);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strResult)));
            Node nodeRoot = doc.getElementsByTagName("RESPONSE").item(0);
            Element eElement = (Element) nodeRoot;
            String RESPONSECODE = eElement.getElementsByTagName("RESPONSECODE").item(0).getTextContent();
            String ACCOUNTNAME = "";
            if (RESPONSECODE.endsWith("00")) {
                ACCOUNTNAME = eElement.getElementsByTagName("ACCOUNTNAME").item(0).getTextContent();
            }
            return RESPONSECODE + "#" + ACCOUNTNAME;
        } catch (AxisFault af) 
        {
            LOGGER.error(af);
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                return null;
            }*/
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    //New 2018
    /**
     *
     * @param strXML
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public String transfer247FromPartner(String strXML) throws ParserConfigurationException, SAXException, IOException {
        try {

            String status = "00";
            Helper.writeLogError(scb.com.vn.payment.IBTController.class, "REQUEST:" + strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            if (eElement.getElementsByTagName("MSGID").item(0) == null
                    || eElement.getElementsByTagName("PROVIDERID").item(0) == null
                    || eElement.getElementsByTagName("TRANSID").item(0) == null
                    || eElement.getElementsByTagName("DATETIME").item(0) == null
                    || eElement.getElementsByTagName("TONUMBER").item(0) == null
                    || eElement.getElementsByTagName("TYPETONUMBER").item(0) == null
                    || eElement.getElementsByTagName("AMOUNT").item(0) == null
                    || eElement.getElementsByTagName("CCY").item(0) == null
                    || eElement.getElementsByTagName("MAC").item(0) == null) {
                return getMsgError("99", "NOT ENOUGHT INPUT PARAMETER");
            }
            String COMMANDCODE = eElement.getElementsByTagName("MSGID").item(0).getTextContent();
            if (!COMMANDCODE.equals("QUE") && !COMMANDCODE.equals("TRN")) {
                return getMsgError("97", "COMMANDCODE IS WRONG");
            }
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String transdetailid = TRANSID;
            if (eElement.getElementsByTagName("TXNDETAILID").item(0) != null) {
                transdetailid = eElement.getElementsByTagName("TXNDETAILID").item(0).getTextContent();
            }
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            try {
                Date date = df.parse(DATETIME);
            } catch (Exception ex) {
                return getMsgError("99", "DATETIME IS WRONG");
            }
            String TONUMBER = eElement.getElementsByTagName("TONUMBER").item(0).getTextContent();
            String TYPETONUMBER = eElement.getElementsByTagName("TYPETONUMBER").item(0).getTextContent();
            if (TYPETONUMBER.equals("ACCOUNT")) {
                if (eElement.getElementsByTagName("BENID").item(0) == null) {
                    return getMsgError("99", "BENID IS EXPECTED");
                }
            }
            String BENID = eElement.getElementsByTagName("BENID") == null ? "" : eElement.getElementsByTagName("BENID").item(0).getTextContent();
            String AMOUNT = eElement.getElementsByTagName("AMOUNT").item(0).getTextContent();
            String CCY = eElement.getElementsByTagName("CCY").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0) == null ? "" : eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String ADDINFO = eElement.getElementsByTagName("ADDINFO").item(0) == null ? "" : eElement.getElementsByTagName("ADDINFO").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();
            //THUC HIEN CHECK MAC
            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                Helper.writeLog(IBTController.class, org.apache.log4j.Level.INFO, "Khong get duoc key MD5");
                return getMsgError("99", "PROVIDERID IS WRONG");
            } else {
                MD5Key = key[0].toString();
            }

            String strMAC = ControllerUtil.EncriptMD5(MD5Key + COMMANDCODE + PROVIDERID + TRANSID + DATETIME + TONUMBER + TYPETONUMBER + AMOUNT + CCY);
            strMAC = strMAC.toUpperCase();
            MAC = MAC.toUpperCase();
            if (!strMAC.equals(MAC)) {
                return getMsgError("98", "MAC IS INVALID");
            }
            if (!TYPETONUMBER.endsWith("CARD") & !TYPETONUMBER.endsWith("ACCOUNT")) {

                return getMsgError("99", "TYPETONUMBER IS INVALID");
            }
            int checkPregolive = Helper.getDBI().checkPartnerPregolive(PROVIDERID);
            if (checkPregolive > 0) {
                int checkAccountPregolive = Helper.getDBI().checkAccountPregolive(PROVIDERID, TONUMBER);
                if (checkAccountPregolive == 0) {
                    return getMsgError("99", "ACCOUNT FOR TEST IS WRONG");
                }
            }
            BigDecimal bdAmount;
            try {
                bdAmount = BigDecimal.valueOf(Long.valueOf(AMOUNT));
            } catch (Exception ex) {
                return getMsgError("99", "AMOUNT IS INVALID");
            }
            String ID = "0";
            String refcore = "";
            String DestName = "";
            //CHECK DATABASE
            String FROMNUMBER = key[4].toString();
            String TYPEFROMNUMBER = "ACCOUNT";
            String FULLNAME = PROVIDERID + "#" + "VIETNAM";
            int IDMaster = 0;
            IBTController ibt = new IBTController();
            if (COMMANDCODE.endsWith("TRN")) {
                String[] resultTRANSFER = Helper.getDBI().SI_INSERTSITRANSFROMSI(PROVIDERID, TRANSID, DATETIME);
                status = resultTRANSFER[1].toString();
                if (status.endsWith("00")) {
                    IDMaster = Integer.valueOf(resultTRANSFER[0].toString());
                    Object[] resultInsertDetail = Helper.getDBI().SI_INSERTTRANSFERFROMSIDETAIL(IDMaster,
                            transdetailid,
                            FROMNUMBER,
                            "",
                            TONUMBER,
                            BENID,
                            "",
                            Double.valueOf(AMOUNT),
                            CCY,
                            DESCRIPTION,
                            "03", TYPETONUMBER == "CARD" ? "01" : "02");
                    status = resultInsertDetail[0].toString();
                    ID = resultInsertDetail[1].toString();
                }
            }
            if (status.endsWith("00")) {
                if (COMMANDCODE.equals("QUE")) {
                    Helper.writeLogErrorNonDB(SIController.class, "QUE NAPAS:" + FROMNUMBER + "-" + TYPEFROMNUMBER + "-" + FULLNAME + "-" + TONUMBER + "-" + TYPETONUMBER + "-" + COMMANDCODE);
                    String callRequest = ibt.transfeFromSCB(FROMNUMBER, TYPEFROMNUMBER, FULLNAME,
                            TONUMBER, TYPETONUMBER, BENID, bdAmount,
                            CCY, "CH", DESCRIPTION + "#" + ID, "11111111", "SML INTERNET BANKING      HCM        VNM", "QUE");
                    Helper.writeLogErrorNonDB(SIController.class, "result of callRequest:" + callRequest + "- TOaccount:" + TONUMBER);
                    if (callRequest != null) {
                        String[] callRequestArray = callRequest.split("#");
                        status = callRequestArray[0];
                        if (status.equals("00")) {
                            DestName = callRequestArray[1];
                        }
                    }
                } else if (COMMANDCODE.equals("TRN")) {
                    //THUC HIEN TRANFER
                    Helper.writeLogErrorNonDB(SIController.class, "TRANFER NAPAS:" + FROMNUMBER + "-" + TYPEFROMNUMBER + "-" + FULLNAME + "-" + TONUMBER + "-" + TYPETONUMBER + "-" + COMMANDCODE);
                    String callTransfer = ibt.transfeFromSCB(FROMNUMBER, TYPEFROMNUMBER, FULLNAME,
                            TONUMBER, TYPETONUMBER, BENID, bdAmount,
                            CCY, "CH", DESCRIPTION + "#" + ID, "11111111", "SML INTERNET BANKING      HCM        VNM", COMMANDCODE);
                    Helper.writeLogErrorNonDB(SIController.class, "result of call transfer:" + callTransfer + "- TOaccount:" + TONUMBER);

                    if (callTransfer != null) {
                        String[] callTransferArray = callTransfer.split("#");
                        status = callTransferArray[0];
                        if (status.equals("00") || status.equals("24")) {
                            DestName = callTransferArray[1];
                            refcore = callTransferArray[2];
                        }
                    }
                }

                if (status.equals("16") || status.equals("18") || status.equals("24")) {
                    //TIMEOUT CHO DOI SOAT
                    status = "16";
                } else if (!status.equals("00")) {
                    //KHONG THANH CONG
                    status = "15";
                }
                //THUC HIEN CAP NHAT DU LIEU
                if (COMMANDCODE.endsWith("TRN")) {
                    Helper.getDBI().SI_UPDATETRANSFERFROMSIDETAIL(Double.valueOf(ID), refcore, "", status);
                }
            }
            HashMap<String, String> response = new LinkedHashMap<String, String>();
            response.put("RESPONSECODE", status);
            if (status.equals("00")) {
                if (COMMANDCODE.endsWith("QUE")) {
                    response.put("DESTNAME", DestName);
                }
                response.put("BANKTRANSID", String.valueOf(ID));
            }
            response.put("TRANSID", TRANSID);
            response.put("PROVIDERID", PROVIDERID);
            String reponse = XMLUtils.xmlParser(response, MD5Key);
            Helper.writeLogError(scb.com.vn.payment.IBTController.class, "reponse:" + reponse);
            return reponse;
        } catch (Exception ex) {
            Helper.writeLog(IBTController.class, org.apache.log4j.Level.INFO, "Exception transfer247FromPartner: " + ex.toString());
            return getMsgError("99", "ERROR SYSTEM");
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String getListBankCode247(String strXML) {
        try {
            System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            if (eElement.getElementsByTagName("MSGID") == null
                    || eElement.getElementsByTagName("PROVIDERID") == null
                    || eElement.getElementsByTagName("MAC") == null) {
                return getMsgError("99", "NOT ENOUGHT INPUT PARAMETER");
            }
            String COMMANDCODE = eElement.getElementsByTagName("MSGID").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();
            if (!COMMANDCODE.endsWith("004")) {
                return getMsgError("99", "COMMANDCODE IS WRONG");
            }
            //check chu ky
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return getMsgError("99", "PROVIDERID IS WRONG");
            }
            String MD5Key = key[0].toString();
            String strMAC = ControllerUtil.EncriptMD5(MD5Key + COMMANDCODE + PROVIDERID);
            strMAC = strMAC.toUpperCase();
            MAC = MAC.toUpperCase();
            if (!strMAC.equals(MAC)) {
                return getMsgError("98", "MAC IS WRONG");
            }
            ArrayList ListBank = Helper.getDBI().SI_getListBank247();
            String strListBank = "";
            for (int j = 0; j < ListBank.size(); j++) {
                List listBank = (List) ListBank.get(j);
                strListBank = strListBank + "<BANK><BANKCODE>" + listBank.get(1).toString() + "</BANKCODE>"
                        + "<BANKNAME>" + listBank.get(0).toString() + "</BANKNAME>"
                        + "</BANK>";
            }
            String responsecode = "00";
            String zipData = ZipData.ZipToString(strListBank);
            HashMap<String, String> response = new LinkedHashMap<String, String>();
            response.put("RESPONSECODE", responsecode);
            response.put("LISTDATA", zipData);
            response.put("PROVIDERID", PROVIDERID);
            String reponse = XMLUtils.xmlParser(response, MD5Key);
            Helper.writeLogError(scb.com.vn.payment.IBTController.class, "reponse:" + reponse);
            return reponse.toString();
        } catch (Exception ex) {
            Helper.writeLogErrorNonDB(IBTController.class, ex.getMessage());
            return getMsgError("99", "ERROR SYSTEM");
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String queryTransaction(String strXML) {
        try {
            Helper.writeLogErrorNonDB(SIController.class, strXML);

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("MSGID").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("005")) {
                return getMsgError("99", "COMMANDCODE IS WRONG");
            }
            String REFTRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return getMsgError("99", "PROVIDERID IS WRONG");
            } else {
                MD5Key = key[0].toString();
            }

            String strMAC = ControllerUtil.EncriptMD5(MD5Key + CommandCode + REFTRANSID + PROVIDERID);
            //check MAC
            strMAC = strMAC.toUpperCase();
            MAC = MAC.toUpperCase();
            if (!strMAC.equals(MAC)) {
                return getMsgError("98", "MAC IS WRONG");
            }

            List result = Helper.getDBI().SI_querryTransfer(PROVIDERID, REFTRANSID);
            String RESPONSECODE = "18";
            if (result.size() > 0) {
                HashMap _hm = (HashMap) result.get(0);
                RESPONSECODE = _hm.get("STATUS").toString();
            }

            HashMap<String, String> response = new LinkedHashMap<String, String>();
            response.put("RESPONSECODE", RESPONSECODE);
            response.put("TRANSID", REFTRANSID);
            response.put("PROVIDERID", PROVIDERID);
            response.put("DESCRIPTION", getMsg(RESPONSECODE));
            String reponse = XMLUtils.xmlParser(response, MD5Key);
            Helper.writeLogError(scb.com.vn.payment.IBTController.class, "reponse:" + reponse);
            return reponse;
        } catch (Exception ex) {
            Helper.writeLogError(SIController.class, ex);
            return getMsgError("99", "ERROR SYSTEM");
        }
    }

    private String getMsgError(String errorcode, String desc) {
        String reponse = "<RESPONSE><RESPONSECODE>" + errorcode + "</RESPONSECODE>"
                + "<DESCRIPTION>" + desc + "</DESCRIPTION>"
                + "</RESPONSE>";
        Helper.writeLogError(scb.com.vn.payment.SIController.class, "reponse:" + reponse);
        return reponse;
    }

    private String getMsg(String responseCode) {
        switch (responseCode) {
            case "00":
                return "SUCCESS";
            case "01":
                return "NOT EXIST ACCOUNT IN SCB BANK";
            case "02":
                return "DESTINATION ACCOUNT WAS CLOSED";
            case "03":
                return "DESTINATION ACCOUNT INVALID";
            case "04":
                return "DESTINATION ACCOUNT IS SLEEPING";
            case "05":
                return "DESTINATION ACCOUNT CAN NOT TRANSFER";
            case "06":
                return "SOURCE ACCOUNT IS WRONG";
            case "07":
                return "SOURCE ACCOUNT CAN NOT TRANSFER";
            case "08":
                return "SOURCE ACCOUNT IS NOT ENOUGHT BALANCE";
            case "09":
                return "TRANSFER IS FAILED";
            case "15":
                return "NOT SUCCESS";
            case "16":
                return "TIMEOUT";
            case "17":
                return "NOT EXIST BANK CODE";
            case "18":
                return "NOT EXIST TRANSACTION ID";
            case "19":
                return "EXCEEDING MAXIMUM AMOUNT FOR 1 TIME";
            case "20":
                return "EXCEEDING MAXIMUM AMOUNT FOR 1 DAY";
            case "21":
                return "LESS THAN MINIMUM AMOUNT";
            case "22":
                return "NOT ENOUGHT INPUT PARAMETER";
            case "98":
                return "MAC IS INVALID";
            default:
                return "OTHERS ERROR";
        }
    }

    private void setupProductCodeForTransferMoney(TransactionDetail req) {
        switch (req.getTransactionType()) {
            case INTERNALCMND:
                req.setUserId(userIdFcubs); // tam thoi cau hinh ve user nay vi code cu ko thay cau hinh hoac co the thay the bang useridfcubs_MB
                req.setProduct(productKieuhoiInternalCMNDSCB);
                req.setGlAccount(GLPAYMENTINTERID);
                break;
            case EXTERNALACCOUNT:
                req.setGlAccount(GLKIEUHOIDOMESTIC);
                req.setProduct(productKieuhoiExternalSCB);
                req.setUserId(userIdFcubs);
                break;
            case INTERNALCARD:
            case INTERNALACCOUNT:
                /* Da chuyen qua IIB */
                String product = productKieuhoiInternalSCB;
                req.setProduct(product);
                req.setUserId(userIdFcubs);
                break;
            default:
                break;
        }
    }

    private boolean isValidReceiving(TransferMoneyReq req, TransactionDetail transDetail) {
        boolean result = false;
        if (req.getProviderId() != null
                && !req.getProviderId().isEmpty()) {
            if (!KYCController.PARTNER.contains(req.getProviderId())) {
                KYCController kycController = new KYCController();
                String personId = KYCUtils.getPersonId(req.getProviderId());
                transDetail.getReceivingInfo().setPersonId(personId);
                if (transDetail.getReceivingInfo() != null && transDetail.getReceivingInfo().isValid()) {
                    KYCErrorMsg KYCResult = kycController.kycScore(transDetail.getReceivingInfo());
                    switch (KYCResult) {
                        case LOW:
                        case MEDIUM:
                        case HIGH:
                            result = true;
                            break;
                        case UNACCEPT:
                        default:
                            LOGGER.warn("The receiver is blocked. Info = [" + transDetail.getReceivingInfo() + "]");
                            break;
                    }
                } else {
                    LOGGER.warn("Receiving information is invalid. Info = [" + transDetail.getReceivingInfo() + "]");
                }
            } else {
                LOGGER.info("Skip KYC because list PARTNER [" + KYCController.PARTNER + "] contain partnerID = [" + req.getProviderId() + "], TxnRef = [" + req.getTxnRef() + "]");
                result = true;
            }
        } else {
            LOGGER.warn("Receiving information is invalid ProviderId");
        }

        return result;
    }

    /*Ham dang test tren 73.20
    * doi tac : WU
     */
    private boolean isValidReceivingForWesternUnion(TransferMoneyReq req, TransactionDetail transDetail) {
        boolean result = false;
        /*
        if (req.getReceivingInfo().getAddress() == null || "".equals(req.getReceivingInfo().getAddress())) {
            req.getReceivingInfo().setAddress(" ");
        }
        if (req.getReceivingInfo().getNationality() == null || "".equals(req.getReceivingInfo().getNationality())) {
            req.getReceivingInfo().setNationality(" ");
        }
         */
        //gia lap kyc

        if (req.getReceivingInfo() != null && "NG1".equals(req.getReceivingInfo().getFirstName().trim()) && "NGYEN THI KIEU".equals(req.getReceivingInfo().getLastName().trim())) {
            LOGGER.warn("gia lap kyc");
            transDetail.setResponseCode(ResponseCodeEnum.KYCHOLD.getText());
            transDetail.setDescription(ResponseCodeEnum.KYCHOLD.name());
        } else {
            if (req.getProviderId() != null && !req.getProviderId().isEmpty()) {
                if (!KYCController.PARTNER.contains(req.getProviderId())) {
                    KYCController kycController = new KYCController();
                    String personId = KYCUtils.getPersonId(req.getProviderId());
                    transDetail.getReceivingInfo().setPersonId(personId);
                    if (transDetail.getReceivingInfo() != null && transDetail.getReceivingInfo().isValidForWesternUnion()) {
                        KYCErrorMsg KYCResult = kycController.kycScoreV2(transDetail.getReceivingInfo());
                        switch (KYCResult) {
                            case KYCEXCEPTION:
                                LOGGER.warn("He thong KYC khong phan hoi, bi timeout! = [" + transDetail.getReceivingInfo() + "]");
                                transDetail.setResponseCode(ResponseCodeEnum.KYCHOLD.getText());
                                transDetail.setResponseDesc(ResponseCodeEnum.KYCHOLD.name());
                                break;
                            case LOW:
                            case MEDIUM:
                            case HIGH:
                                result = true;
                                break;
                            case UNACCEPT:
                            default:
                                LOGGER.warn("The receiver is blocked. Info = [" + transDetail.getReceivingInfo() + "]");
                                transDetail.setResponseCode(ResponseCodeEnum.KYCHOLD.getText());
                                transDetail.setResponseDesc(ResponseCodeEnum.KYCHOLD.name());
                                break;
                        }
                    } else {
                        LOGGER.warn("Receiving information is invalid. Info = [" + transDetail.getReceivingInfo() + "]");
                        transDetail.setResponseCode(ResponseCodeEnum.INVALIDRECEIVINGINFO.getText());
                        transDetail.setResponseDesc(ResponseCodeEnum.INVALIDRECEIVINGINFO.name());
                    }
                } else {
                    LOGGER.info("Skip KYC because list PARTNER [" + KYCController.PARTNER + "] contain partnerID = [" + req.getProviderId() + "], TxnRef = [" + req.getTxnRef() + "]");
                    result = true;
                }
            } else {
                LOGGER.warn("Receiving information is invalid ProviderId");
                transDetail.setResponseCode(ResponseCodeEnum.INVALIDRECEIVINGINFO.getText());
                transDetail.setResponseDesc(ResponseCodeEnum.INVALIDRECEIVINGINFO.name());
            }
        }
        return result;
    }

    /**
     *
     * @param req
     * @return
     */
    public String transferMoney(TransferMoneyReq req) {
        TransferMoneyRes response = new TransferMoneyRes();
        try {
            // insert thong tin vo table goc
            String[] resultLog = Helper.getDBI().SI_INSERTSITRANSFROMSI(req.getProviderId(),
                    req.getTxnRef(), req.getTransDate());
            // lay ma so ref lien ket duoc phan hoi neu insert thanh cong
            response.setScbRef(resultLog[0]);
            response.setResponseCode(resultLog[1]);

            // kiem tra xem co insert thong tin vo table goc duoc ko
            if ("00".equals(response.getResponseCode())) {
                // lay ra danh sach thong tin chuyen tien chi tiet
                Transaction transDetails = req.getTransDetails();
                // neu danh sach thong tin chuyen tien rong thi ko can xu ly tiep theo
                if (transDetails.getTrnDetail() != null) {
                    // duyet tung thong tin chuyen tien chi tiet
                    for (TransactionDetail transDetail : transDetails.getTrnDetail()) {
                        String status, coreRef;
                        transDetail.setScbDetailId(response.getScbRef());
                        transDetail.setupForOldPartner();
                        setupProductCodeForTransferMoney(transDetail);
                        ExchangeRateRes resExchange = transDetail.isVND()
                                ? new ExchangeRateRes(transDetail.getProduct(),
                                        transDetail.getAmountBigDecimalType(),
                                        new BigDecimal(BigInteger.ONE), transDetail.getCcy(),
                                        transDetail.getCcy(), "00")
                                : SiUtils.getExchangeRate(transDetail);

                        boolean isValidReveiver = isValidReceiving(req, transDetail);
                        if (isValidReveiver && resExchange != null && "00".equals(resExchange.getResultCode())) {
                            transDetail.setExchangeRateRes(resExchange);
                            List<String> rs = SiUtils.insertDetailToSi(transDetail);
                            String insertResult = rs.get(0);
                            if (rs.size() >= 2 && "00".equals(insertResult)) {
                                transDetail.setScbDetailId(rs.get(1));
                                VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(
                                        transDetail.getPartnerAccount());

                                if (transDetail.getCcy().equals(custacc.getCcy())) {
                                    FundTransferRq fundTransferRq = new FundTransferRq(transDetail);
                                    fundTransferRq.setUserId(transDetail.getUserId());
                                    fundTransferRq.setCifNo(custacc.getCustNo());
                                    /* them de lay ten nguoi gui */
                                    fundTransferRq.setUserName(custacc.getFullName());
                                    IIBPaymentService iibPaymentService = new IIBPaymentService();

                                    switch (transDetail.getTransactionType()) {
                                        case INTERNALCMND:
                                            fundTransferRq.setBranchCode(transDetail.getBranchCode());
                                            fundTransferRq.setBeneName(transDetail.getCustomerName());
                                            fundTransferRq.setCityCode(transDetail.getCityName());
                                            fundTransferRq.setBranchName(transDetail.getCityName());
                                            fundTransferRq.setIDOpenDate(transDetail.getOpenDate());
                                            fundTransferRq.setToAccount(transDetail.getCustomerAccount());

                                            ExecutePaymentTransactionExternal_in_Type external_id_input = SiUtils.getReqToTransferExternalId(fundTransferRq);
                                            if (external_id_input != null) {
                                                ExecutePaymentTransactionExternal_out_Type execute_external_id_out = iibPaymentService.executePaymentTransactionExternal(Configuration.getIIBContext(), external_id_input);
                                                coreRef = IIBConstants.TRANSACTION_SUCCESS.equals(execute_external_id_out
                                                        .getTransactionInfo().getTransactionReturn())
                                                                ? execute_external_id_out.getTransactionInfo().getCoreRefNum()
                                                                : null;
                                            } else {
                                                coreRef = null;
                                            }
                                            break;
                                        case EXTERNALACCOUNT:
                                            ExecutePaymentTransactionExternal_in_Type external_input = SiUtils.getReqToTransferExternalAcc(fundTransferRq);
                                            if (external_input != null) {
                                                ExecutePaymentTransactionExternal_out_Type execute_external_out = iibPaymentService.executePaymentTransactionExternal(Configuration.getIIBContext(), external_input);
                                                coreRef = IIBConstants.TRANSACTION_SUCCESS.equals(execute_external_out
                                                        .getTransactionInfo().getTransactionReturn())
                                                                ? execute_external_out.getTransactionInfo().getCoreRefNum()
                                                                : null;
                                            } else {
                                                coreRef = null;
                                            }
                                            break;
                                        case INTERNALCARD:/* INTERNALCARD luon luon di lien voi INTERNALACCOUNT */
                                            String account = SiUtils.getAccountFromCardNumber(transDetail.getCustomerAccount());
                                            if (account == null) {
                                                LOGGER.warn("Cannot get account from card number " + transDetail.getCustomerAccount());
                                                coreRef = null;
                                                break;
                                            }
                                            fundTransferRq.setToAccount(account);
                                        case INTERNALACCOUNT:
                                            /* Da chuyen qua IIB */
                                            ExecutePaymentTransactionInternal_in_Type internal_input = SiUtils.getReqToTransferInternalAcc(fundTransferRq);
                                            ExecutePaymentTransactionInternal_out_Type executePaymentTransactionInternal_out_Type = iibPaymentService.executePaymentInterRestSimpleWithBrn(Configuration.getIIBContext(), internal_input);
                                            coreRef = IIBConstants.TRANSACTION_SUCCESS.equals(executePaymentTransactionInternal_out_Type
                                                    .getTransactionInfo().getTransactionReturn())
                                                            ? executePaymentTransactionInternal_out_Type.getTransactionInfo().getCoreRefNum()
                                                            : null;
                                            break;
                                        default:
                                            LOGGER.warn("TransactionType: " + transDetail.getTransactionType()
                                                    + ", Account: " + transDetail.getCustomerAccount());
                                            coreRef = null;
                                            break;
                                    }
                                    if (coreRef != null) {
                                        if ("TIMEOUT".equals(coreRef)) {
                                            LOGGER.warn("Timeout khi goi qua core. coreRef = [TIMEOUT]");
                                            status = ResponseCodeEnum.CORETIMEOUT.getText();
                                        } else {
                                            LOGGER.info("Hach toan qua core thanh cong. Core = [" + coreRef + "]");
                                            status = ResponseCodeEnum.SUCCESS.getText();
                                        }
                                    } else {
                                        /* Loi he thong ko cat duoc tien tu fcdb */
                                        LOGGER.warn("Loi he thong ko cat duoc tien tu fcdb");
                                        status = ResponseCodeEnum.DEDUCTIONFAIlED.getText();
                                    }
                                } else {
                                    /* Don vi tien te khong khop voi thong tin tai khoan */
                                    LOGGER.warn("Don vi tien te khong khop voi thong tin tai khoan");
                                    coreRef = null;
                                    status = ResponseCodeEnum.SYSTEMERROR.getText();
                                }

                                LOGGER.info("transferMoney --> coreRef : " + coreRef + ", DetailId: " + transDetail.getScbDetailId());
                                String toDay = CommonUtils.getDate("MM/dd/yyyy HH:mm:ss");
                                Helper.getDBI().SI_UPDATETRANSFERFROMSIDETAIL(transDetail.getScbDetailIdIntegerType(), String.valueOf(coreRef), toDay, status);
                            } else {
                                /* insertDetailToSi that bai */
                                status = insertResult;
                                StringBuilder str = new StringBuilder();
                                str.append("insertDetailToSi that bai.");
                                if (rs.size() > 0) {
                                    for (int i = 0; i < rs.size(); i++) {
                                        str.append(", rs[").append(i).append("] = [").append(rs.get(i)).append("]");
                                    }
                                } else {
                                    str.append("rs.size = 0");
                                }
                                LOGGER.warn(str.toString());
                            }
                        } else {
                            /* Ko chuyen doi duoc ty gia tien te hoac invalid KYC */
                            LOGGER.warn("Ko chuyen doi duoc ty gia tien te hoac invalid KYC. isValidReveiver = " + isValidReveiver + ", resExchange = " + resExchange.toString());
                            status = ResponseCodeEnum.KYCHOLD.getText();
                        }
                        transDetail.setResponseCode(status);
                        transDetail.clearBeforeResponse();
                    }
                } else {
                    LOGGER.warn("Cannot get getTrnDetail from request");
                }
                response.setTransData(transDetails, req.isOldPartner());
            } else {
                LOGGER.warn("insert database SI_INSERTSITRANSFROMSI that bai. Response = [" + response.getResponseCode() + "]");
            }
            response.setTxnRef(req.getTxnRef());
            response.setProviderId(req.getProviderId());
            String md5Key = SiUtils.getMd5Key(req.getProviderId());
            response.setSignatureScb(md5Key);
        } catch (Exception ex) {
            LOGGER.error(ex);
            response.setResponseCode(ResponseCodeEnum.SYSTEMERROR.getText());
            response.setDescription(ResponseCodeEnum.SYSTEMERROR.name());
        }
        return XMLUtils.Marshaller(response);
    }

    private String insertDatabase(TransferMoney247Req req, TransferMoney247DetailReq reqDetail,
            TransferMoney247Res response) {
        String result = "99";
        try {
            // insert thong tin vo table goc
            String[] resultTransfer = Helper.getDBI().SI_INSERTSITRANSFROMSI(req.getProviderId(),
                    req.getTransId(), req.getDateTime());
            String status = resultTransfer[1];
            // kiem tra xem co insert thong tin vo table goc duoc ko
            if ("00".equals(status)) {
                // lay thong tin lien ket (id) toi table goc moi duoc insert
                int idMaster = Integer.valueOf(resultTransfer[0]);
                // gan transaction detail id = transaction id = 1 ma cho gon
                String transDetailId = req.getTransId();

                // insert thong tin vo bang chi tiet
                Object[] resultInsertDetail = Helper.getDBI().SI_INSERTTRANSFERFROMSIDETAILKIEUHOI(idMaster,
                        transDetailId, reqDetail.getFromNumber(), "", reqDetail.getToNumber(), reqDetail.getBenId(),
                        "", req.getAmountDoubleType(), reqDetail.getCcy(), reqDetail.getDescription(),
                        "03", "CARD".equals(reqDetail.getTypeToNumber()) ? "01" : "02",
                        reqDetail.getExchangeRateRes().getRate(),
                        reqDetail.getExchangeRateRes().getMoneyExchange(),
                        reqDetail.getExchangeRateRes().getCcyExchange(),
                        (req.getReceivingInfo() != null) ? req.getReceivingInfo().getPersonId() : "",
                        (req.getReceivingInfo() != null) ? req.getReceivingInfo().getFirstName() : "",
                        (req.getReceivingInfo() != null) ? req.getReceivingInfo().getLastName() : "",
                        (req.getReceivingInfo() != null) ? req.getReceivingInfo().getPassNo() : "",
                        (req.getReceivingInfo() != null) ? req.getReceivingInfo().getBirthDate() : "",
                        (req.getReceivingInfo() != null) ? req.getReceivingInfo().getAddress() : "",
                        (req.getReceivingInfo() != null) ? req.getReceivingInfo().getNationality() : "",
                        (req.getReceivingInfo() != null) ? req.getReceivingInfo().getCustType() : "");
                // lay ket qua thanh cong hay ko
                status = resultInsertDetail[0].toString();
                // cau hinh ma phan hoi id cua bank de tra soat sau nay
                response.setBankTransId(resultInsertDetail[1].toString());
                reqDetail.setBankTransId(response.getBankTransId());
            } else {
                LOGGER.warn("insert du lieu xuong table master that bai. TransID = [" + req.getTransId() + "], Status = [" + status + "]");
            }
            result = status;
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }

    private ExchangeRateRes exchangeMoney(TransferMoney247DetailReq req, String accountPartner) {
        try {
            // kiem tra xem co phai tien ngoai te ko. Neu ngoai te thi di chuyen doi
            if (req.isNotVnd()) {
                // lay branch cua tai khoan doi tac
                String branchCust = CommonUtils.getBranchAccount(accountPartner);

                // tao data de chuyen doi tien
                ExchangeRateReq exchangeRateReq = new ExchangeRateReq();

                exchangeRateReq.setProduct(req.getProduct());
                exchangeRateReq.setBranch(branchCust);
                exchangeRateReq.setCcy(req.getCcy());
                exchangeRateReq.setMoney(req.getAmount());
                // call qua database core de chuyen doi menh gia tien
                return Helper.getDBI().getExchangeRate(exchangeRateReq);
            } else {
                // tao data mapping 1-1 ty gia VND de tra ve
                ExchangeRateRes exchangeRateRes = new ExchangeRateRes();

                exchangeRateRes.setProduct(req.getProduct());
                exchangeRateRes.setMoney(req.getAmount());
                exchangeRateRes.setRate(BigDecimal.ONE);
                exchangeRateRes.setCcy(req.getCcy());
                exchangeRateRes.setMoneyExchange(req.getAmount());
                exchangeRateRes.setCcyExchange(req.getCcy());
                exchangeRateRes.setResultCode("00");
                return exchangeRateRes;
            }
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    /**
     *
     * @param req
     * @return
     */
    public String transferMoney247(TransferMoney247Req req) {
        TransferMoney247Res response = new TransferMoney247Res();
        try {
            // Lay so tai khoan cua doi tac
            String accountPartner = SiUtils.getAccountPartner(req.getProviderId());
            // kiem tra xem co lay duoc tai khoan cua doi tac hay ko
            if (accountPartner != null) {
                // tao message xu ly cho ibtController
                TransferMoney247DetailReq detailReq = new TransferMoney247DetailReq();

                detailReq.setFromNumber(accountPartner);
                detailReq.setTypeFromNumber("ACCOUNT");
                detailReq.setFullName(req.getProviderId() + "#" + "VIETNAM");
                detailReq.setToNumber(req.getToNumber());
                detailReq.setTypeToNumber(req.getTypeToNumber());
                detailReq.setBenId(req.getBenId());
                detailReq.setAmount(req.getAmountBigDecimalType());
                detailReq.setCcy(req.getCcy());
                detailReq.setDescription(req.getDescription());
                detailReq.setTermId("11111111");
                detailReq.setCardAcceptor("SML INTERNET BANKING      HCM        VNM");
                detailReq.setTypeFunction(req.getMsgId());
                detailReq.setProduct(productKieuhoiExternalSCB247);
                detailReq.setNapasAccount(GLPAYMENTEXTERNAPAS);
                detailReq.setMerchantType("7399");
                detailReq.setUserIdFcubs(userIdFcubs);
                detailReq.setupProcessingCode(); // cau hinh ma processingCode de truyen qua cho NAPAS

                // chuyen doi menh gia tien tu ngoai te ve VND neu co
                ExchangeRateRes exchangeRateRes = exchangeMoney(detailReq, accountPartner);
                // kiem tra thong tin KYC
                boolean isValidReveiver = true;
                if (detailReq.getTypeFunction().equals("TRN")) {
                    isValidReveiver = isValidReceiving(req);
                }

                // kiem tra xem KYC okie ko va chuyen doi ty gia tien okie ko
                if (isValidReveiver && exchangeRateRes != null && "00".equals(exchangeRateRes.getResultCode())) {

                    // cau hinh ty gia trong tham so request
                    detailReq.setExchangeRateRes(exchangeRateRes);

                    // neu la giao dich chuyen tien thi insert thong tin vao database con neu la giao dich query thi ko can
                    String status = req.isTransReq() ? insertDatabase(req, detailReq, response) : ResponseCodeEnum.SUCCESS.getText();
                    if ("00".equals(status)) {
                        IBTController ibt = new IBTController();
                        /* THUC HIEN GOI SANG NAPAS */
                        TransferMoney247DetailRes result = ibt.transferFromSCBwithObj(detailReq);
                        LOGGER.info("TransId = [" + req.getTransId() + "], Ket qua tra ve sau khi xu ly goi qua NAPAS = [" + result.getResponseCode() + "]");
                        // phan tich ket qua tra ve
                        switch (result.getResponseCode()) {
                            case "00":
                                response.setDestName(result.getDestinationName());
                                break;
                            case "16":
                            /* TIMEOUT CHO DOI SOAT */
                            case "27":
                            case "26":
                            case "18":
                            case "24":
                                status = ResponseCodeEnum.CORETIMEOUT.getText();
                                break;
                            default:
                                /* KHONG THANH CONG */
                                status = ResponseCodeEnum.TRANSFAILED.getText();
                                break;
                        }
                        //THUC HIEN CAP NHAT DU LIEU
                        if (req.isTransReq()) {
                            Helper.getDBI().SI_UPDATETRANSFERFROMSIDETAIL(response.getBankTransIdDoubleType(),
                                    result.getRefCore(), "", status);
                        }
                    } else {
                        LOGGER.warn("insert du lieu xuong database that bai. Status = [" + status + "]");
                    }
                    String md5Key = SiUtils.getMd5Key(req.getProviderId());
                    response.setResponseCode(status);
                    response.setTransId(req.getTransId());
                    response.setProvideId(req.getProviderId());
                    response.setDescription("");
                    response.signMac(md5Key);
                } else {
                    /* Ko lay duoc ty gia ngoai te hoac KYC error */
                    LOGGER.warn("transferMoney247 --> TransId = [" + req.getTransId() + "], isValidReveiver = [" + isValidReveiver + "], exchangeRateRes = [" + exchangeRateRes + "]");
                    response.setResponseCode(ResponseCodeEnum.KYCERROR.getText());
                    response.setDescription(ResponseCodeEnum.KYCERROR.name());
                }
            } else {
                /* Xu ly khi accountPartner == null */
                LOGGER.warn("transferMoney247 --> TransId = [" + req.getTransId() + "], Partner = [" + req.getProviderId() + "] -> accountPartner does not found");
                response.setResponseCode(ResponseCodeEnum.ACCOUNTINVALID.getText());
                response.setDescription(ResponseCodeEnum.ACCOUNTINVALID.name());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            response.setResponseCode(ResponseCodeEnum.SYSTEMERROR.getText());
            response.setDescription(ResponseCodeEnum.SYSTEMERROR.name());
        }
        return XMLUtils.Marshaller(response);
    }

    /*
    *Doi tac dang su dung: Western Union (WU)
    * dang test tren 73.20 
     */
    public String transferMoney247WithSender(TransferMoney247Req req) {
        TransferMoney247Res response = new TransferMoney247Res();
        try {
            // Lay so tai khoan cua doi tac
            String accountPartner = SiUtils.getAccountPartner(req.getProviderId());
            // kiem tra xem co lay duoc tai khoan cua doi tac hay ko
            if (accountPartner != null) {
                // tao message xu ly cho ibtController
                TransferMoney247DetailReq detailReq = new TransferMoney247DetailReq();

                detailReq.setFromNumber(accountPartner);
                detailReq.setTypeFromNumber("ACCOUNT");
                detailReq.setFullName(req.getProviderId() + "#" + "VIETNAM");
                detailReq.setToNumber(req.getToNumber());
                detailReq.setTypeToNumber(req.getTypeToNumber());
                detailReq.setBenId(req.getBenId());
                detailReq.setAmount(req.getAmountBigDecimalType());
                detailReq.setCcy(req.getCcy());
                detailReq.setDescription(req.getDescription());
                detailReq.setTermId("11111111");
                detailReq.setCardAcceptor("SML INTERNET BANKING      HCM        VNM");
                detailReq.setTypeFunction(req.getMsgId());
                detailReq.setProduct(productKieuhoiExternalSCB247);
                detailReq.setNapasAccount(GLPAYMENTEXTERNAPAS);
                detailReq.setMerchantType("7399");
                detailReq.setUserIdFcubs(userIdFcubs);
                detailReq.setupProcessingCode(); // cau hinh ma processingCode de truyen qua cho NAPAS
                // chuyen doi menh gia tien tu ngoai te ve VND neu co
                ExchangeRateRes exchangeRateRes = exchangeMoney(detailReq, accountPartner);
                /*Neu la TRN thi check thong tin nguoi gui*/
                boolean isValidSender = req.isTransReq() ? req.getSenderInfo().isValidSender() : true;
                if (isValidSender) {

                    /*test cases cho doi tac WU*/
                    String[] testCaseResult = testCaseForWesternUnion(req.getToNumber(), req.getBenId(), req.getReceivingInfo().getLastName(),
                            req.getReceivingInfo().getFirstName(), req.getMsgId());
                    if (!"00".equals(testCaseResult[0])) {
                        detailReq.setExchangeRateRes(exchangeRateRes);
                        String bankTransId = req.isTransReq() ? SiUtils.insertTestCaseForWU(req, response, detailReq, testCaseResult[0]) : testCaseResult[0];
                        if ("95".equals(response.getResponseCode())) {
                            response.setBankTransId(bankTransId);
                            response.setResponseCode("95");
                            response.setDescription(getErrorMessage("95"));
                        } else {
                            response.setBankTransId(bankTransId);
                            response.setResponseCode(testCaseResult[0]);
                            response.setDescription(getErrorMessage(testCaseResult[0]));
                        }
                        String md5Key = SiUtils.getMd5Key(req.getProviderId());
                        response.setTransId(req.getTransId());
                        response.setProvideId(req.getProviderId());
                        response.signMac(md5Key);
                        return XMLUtils.Marshaller(response);
                    }

                    /*Neu la TRN thi check thong tin nguoi nhan*/
                    boolean isValidReveiver = req.isTransReq() ? isValidReceivingForWesternUnion(req, response) : true;
                    // kiem tra xem KYC okie ko va chuyen doi ty gia tien okie ko   
                    if (isValidReveiver && exchangeRateRes != null && "00".equals(exchangeRateRes.getResultCode())) {
                        String description = "";
                        // cau hinh ty gia trong tham so request
                        detailReq.setExchangeRateRes(exchangeRateRes);
                        // neu la giao dich chuyen tien thi insert thong tin vao database con neu la giao dich query thi ko can
                        String status = req.isTransReq() ? insertDatabase(req, detailReq, response) : ResponseCodeEnum.SUCCESS.getText();
                        String resultInsertSender = req.isTransReq() ? SiUtils.insertToSiSenderDetail(req.getSenderInfo(), detailReq.getBankTransId()) : ResponseCodeEnum.SUCCESS.getText();
                        if ("00".equals(status) && "00".equals(resultInsertSender)) {
                            IBTController ibt = new IBTController();
                            /* THUC HIEN GOI SANG NAPAS */
                            TransferMoney247DetailRes result = ibt.transferFromSCBwithObjV2(detailReq);
                            LOGGER.info("TransId = [" + req.getTransId() + "], Ket qua tra ve sau khi xu ly goi qua NAPAS = [" + result.getResponseCode() + "]");
                            switch (result.getResponseCode()) {
                                case "00":
                                    response.setDestName(result.getDestinationName());
                                    description = ResponseCodeEnum.SUCCESS.name();
                                    break;
                                case "16":
                                /* TIMEOUT CHO DOI SOAT */
                                case "27":
                                case "26":
                                case "18":
                                case "24":
                                    status = ResponseCodeEnum.CORETIMEOUT.getText();
                                    description = ResponseCodeEnum.CORETIMEOUT.name();
                                    break;
                                case "92": //add code for wu
                                    status = ResponseCodeEnum.INVALIDRECEIVINGINFO.getText();
                                    description = ResponseCodeEnum.INVALIDRECEIVINGINFO.name();
                                    break;
                                default:
                                    /* KHONG THANH CONG */
                                    status = ResponseCodeEnum.TRANSFAILED.getText();
                                    description = ResponseCodeEnum.TRANSFAILED.name();
                                    break;
                            }
                            //THUC HIEN CAP NHAT DU LIEU
                            if (req.isTransReq()) {
                                Helper.getDBI().SI_UPDATETRANSFERFROMSIDETAIL(response.getBankTransIdDoubleType(),
                                        result.getRefCore(), "", status);
                            }
                        } else {
                            LOGGER.warn("insert du lieu xuong database that bai. Status = [" + status + "]");
                        }
                        String md5Key = SiUtils.getMd5Key(req.getProviderId());
                        response.setResponseCode(status);
                        response.setTransId(req.getTransId());
                        response.setProvideId(req.getProviderId());
                        response.setDescription(getErrorMessage(status));
                        response.signMac(md5Key);
                    } else {
                        /* Ko lay duoc ty gia ngoai te hoac KYC error */
                        LOGGER.warn("transferMoney247 --> TransId = [" + req.getTransId() + "], isValidReveiver = [" + isValidReveiver + "], exchangeRateRes = [" + exchangeRateRes + "]");
                        /*Thuc hien insert ma loi KYC vao table detail*/
                        detailReq.setExchangeRateRes(exchangeRateRes);
                        String bankTransId = SiUtils.insertErrorKycToSiTransDetail(req, response, detailReq);
                        // them du lieu vao sender
                        if (!"0".equals(bankTransId)) {
                            SiUtils.insertToSiSenderDetail(req.getSenderInfo(), bankTransId);
                        }
                        response.setDescription(getErrorMessage(response.getResponseCode()));
                        /*Cau hinh ma gd cua SCB tra ve cho doi tac*/
                        response.setBankTransId(bankTransId);
                    }
                } else {
                    LOGGER.info("Thong tin Sender khong hop le: [transId: " + req.getTransId() + "], "
                            + "Sender: [FirstName: " + req.getSenderInfo().getFirstName() + "],"
                            + "[LastName: " + req.getSenderInfo().getLastName() + "],"
                            + "[CountryIsoCode: " + req.getSenderInfo().getCountryIsoCode() + "],"
                            + "[Address: " + req.getSenderInfo().getAddress() + "],");

                    response.setResponseCode(ResponseCodeEnum.INVALIDSENDER.getText());
                    response.setDescription(ResponseCodeEnum.INVALIDSENDER.name());
                }
            } else {
                /* Xu ly khi accountPartner == null */
                LOGGER.warn("transferMoney247 --> TransId = [" + req.getTransId() + "], Partner = [" + req.getProviderId() + "] -> accountPartner does not found");
                response.setResponseCode(ResponseCodeEnum.PARTNERACCOUNTINVALID.getText());
                response.setDescription(ResponseCodeEnum.PARTNERACCOUNTINVALID.name());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            response.setResponseCode(ResponseCodeEnum.SYSTEMERROR.getText());
            response.setDescription(ResponseCodeEnum.SYSTEMERROR.name());
        }
        return XMLUtils.Marshaller(response);
    }

    /**
     *
     * @param fundTransferRq
     * @return
     * @throws BianException
     */
    public static String transferToIDCARD(FundTransferRq fundTransferRq) throws BianException {
        try {
            IIBPaymentService iibPaymentService = new IIBPaymentService();
            String coreRef;
            ExecutePaymentTransactionExternal_in_Type external_id_input = SiUtils.getReqToTransferExternalId(fundTransferRq);
            if (external_id_input != null) {
                ExecutePaymentTransactionExternal_out_Type execute_external_id_out = iibPaymentService.executePaymentTransactionExternal(Configuration.getIIBContext(), external_id_input);
                coreRef = IIBConstants.TRANSACTION_SUCCESS.equals(execute_external_id_out
                        .getTransactionInfo().getTransactionReturn())
                                ? execute_external_id_out.getTransactionInfo().getCoreRefNum()
                                : null;
            } else {
                coreRef = null;
            }
            return coreRef;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    private boolean isValidReceiving(TransferMoney247Req req) {
        boolean result = false;
        try {
            // kiem tra xem partner co hop le hay ko
            if (req.getProviderId() != null && !req.getProviderId().isEmpty()) {
                // neu partner ko nam trong danh sach skip thi di validate
                if (!KYCController.PARTNER.contains(req.getProviderId())) {
                    KYCController kycController = new KYCController();
                    // tao kycId de quan ly
                    String personId = KYCUtils.getPersonId(req.getProviderId());
                    // cau hinh lai cho bien req de dem di xu ly
                    req.getReceivingInfo().setPersonId(personId);
                    // kiem tra xem thong tin nguoi nhan co valid ko
                    if (req.getReceivingInfo() != null && req.getReceivingInfo().isValid()) {
                        KYCErrorMsg KYCResult = kycController.kycScore(req.getReceivingInfo());
                        // kiem tra ket qua tra ve
                        switch (KYCResult) {
                            case LOW:
                            case MEDIUM:
                            case HIGH:
                                result = true;
                                break;
                            case UNACCEPT:
                            default:
                                LOGGER.warn("The receiver is blocked");
                                break;
                        }
                    } else {
                        LOGGER.warn("Receiving information is invalid");
                    }
                } else {
                    result = true;
                    LOGGER.info("Skip KYC because list PARTNER [" + KYCController.PARTNER + "] contain partnerID = [" + req.getProviderId() + "], TransId = [" + req.getTransId() + "]");
                }
            } else {
                LOGGER.warn("Receiving information is invalid ProviderId");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }

    private boolean isValidReceivingForWesternUnion(TransferMoney247Req req, TransferMoney247Res response) {
        boolean result = false;
        try {
            // kiem tra xem partner co hop le hay ko
            if (req.getProviderId() != null && !req.getProviderId().isEmpty()) {
                // neu partner ko nam trong danh sach skip thi di validate
                if (req.getReceivingInfo() != null && "NG1".equals(req.getReceivingInfo().getFirstName().trim()) && "NGUYEN THI KIEU".equals(req.getReceivingInfo().getLastName().trim())) {
                    LOGGER.warn("gia lap kyc");
                    response.setResponseCode(ResponseCodeEnum.KYCHOLD.getText());
                    response.setDescription(ResponseCodeEnum.KYCHOLD.name());
                } else {
                    if (!KYCController.PARTNER.contains(req.getProviderId())) {
                        KYCController kycController = new KYCController();
                        // tao kycId de quan ly
                        String personId = KYCUtils.getPersonId(req.getProviderId());
                        // cau hinh lai cho bien req de dem di xu ly
                        req.getReceivingInfo().setPersonId(personId);
                        // kiem tra xem thong tin nguoi nhan co valid ko
                        if (req.getReceivingInfo() != null && req.getReceivingInfo().isValidForWesternUnion()) {
                            KYCErrorMsg KYCResult = kycController.kycScoreV2(req.getReceivingInfo());
                            // kiem tra ket qua tra ve
                            switch (KYCResult) {
                                case KYCEXCEPTION:
                                    LOGGER.warn("He thong KYC khong phan hoi, bi timeout ffff! = [" + req.getReceivingInfo() + "]");
                                    response.setResponseCode(ResponseCodeEnum.KYCHOLD.getText());
                                    response.setDescription(ResponseCodeEnum.KYCHOLD.name());
                                    break;
                                case LOW:
                                case MEDIUM:
                                case HIGH:
                                    result = true;
                                    break;
                                case UNACCEPT:
                                default:
                                    LOGGER.warn("The receiver is blocked");
                                    response.setResponseCode(ResponseCodeEnum.KYCHOLD.getText());
                                    response.setDescription(ResponseCodeEnum.KYCHOLD.name());
                                    break;
                            }
                        } else {
                            LOGGER.warn("Receiving information is invalid");
                            response.setResponseCode(ResponseCodeEnum.INVALIDRECEIVINGINFO.getText());
                            response.setDescription(ResponseCodeEnum.INVALIDRECEIVINGINFO.name());
                        }
                    } else {
                        result = true;
                        LOGGER.info("Skip KYC because list PARTNER [" + KYCController.PARTNER + "] contain partnerID = [" + req.getProviderId() + "], TransId = [" + req.getTransId() + "]");
                    }
                }

            } else {
                LOGGER.warn("Receiving information is invalid ProviderId");
                response.setResponseCode(ResponseCodeEnum.INVALIDRECEIVINGINFO.getText());
                response.setDescription(ResponseCodeEnum.INVALIDRECEIVINGINFO.name());
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }

    public String checkStatusTransferMoney(GetStatusOfTransferMoneyReq req) {
        GetStatusOfTransferMoneyRes response = new GetStatusOfTransferMoneyRes();
        try {
            /* Cau hinh tham so tra ve */
            response.setProviderId(req.getProviderId());
            response.setTransId(req.getTransId());
            if ("WU".equals(req.getProviderId())) {
                response.setTransactionId(req.getTransactionId());
            } else {
                response.setTransactionId(req.getTranssactionId());
            }
            response.setTxnDetailId(req.getTxnDetailId());
            /* Thuc hien query thong tin tu database */
            TransferMoneyTransactionInfo transactionInfo = Helper.getDBI().checkStatusTransferMoney(req);
            /* Kiem tra du lieu tra ve tu database co thanh cong hay ko */
            if (transactionInfo != null) {
                /* Kiem tra du lieu co duoc tim thay hay khong */
                if (transactionInfo.getStatus() != null) {
                    switch (transactionInfo.getStatus()) {
                        case "00":
                            /* giao dich thanh cong */
                            response.setResponseCode(ResponseCodeEnum.SUCCESS.getText());
                            response.setDescription(ResponseCodeEnum.SUCCESS.name());
                            break;
                        case "01":
                            /*Account khong ton tai */
                            response.setResponseCode(ResponseCodeEnum.ACCOUNTINVALID.getText());
                            response.setDescription(ResponseCodeEnum.ACCOUNTINVALID.name());
                            break;
                        case "02":
                            /*Tai Khoan den bi close*/
                            response.setResponseCode(ResponseCodeEnum.ACCDESTINATIONCLOSE.getText());
                            response.setDescription(ResponseCodeEnum.ACCDESTINATIONCLOSE.name());
                            break;
                        case "03":
                            /*Tai khoan ko the giao dich*/
                            response.setResponseCode(ResponseCodeEnum.ACCCANNOTTRANSACTION.getText());
                            response.setDescription(ResponseCodeEnum.ACCCANNOTTRANSACTION.name());
                            break;
                        case "06":
                            /*Qua thoi gian hoac sai so tai khoan*/
                            response.setResponseCode(ResponseCodeEnum.PARTNERACCOUNTWRONG.getText());
                            response.setDescription(ResponseCodeEnum.PARTNERACCOUNTWRONG.name());
                            break;
                        case "07":
                            /*Khong tim thay the*/
                            response.setResponseCode(ResponseCodeEnum.PARTNERACCOUNTINVALID.getText());
                            response.setDescription(ResponseCodeEnum.PARTNERACCOUNTINVALID.name());
                            break;
                        case "08":
                            /*Tai khoan khong du so du*/
                            response.setResponseCode(ResponseCodeEnum.PARTNERACCOUNTNOTENOUGH.getText());
                            response.setDescription(ResponseCodeEnum.PARTNERACCOUNTNOTENOUGH.name());
                            break;
                        case "09":
                            /* giao dich tru tien that bai */
                            response.setResponseCode(ResponseCodeEnum.DEDUCTIONFAIlED.getText());
                            response.setDescription(ResponseCodeEnum.DEDUCTIONFAIlED.name());
                            break;
                        case "15":
                            /* giao dich thuc hien khong thanh cong */
                            response.setResponseCode(ResponseCodeEnum.TRANSFAILED.getText());
                            response.setDescription(ResponseCodeEnum.TRANSFAILED.name());
                            break;
                        case "16":
                            /* giao dich bi timeout voi core hoac voi napas */
                            response.setResponseCode(ResponseCodeEnum.CORETIMEOUT.getText());
                            response.setDescription(ResponseCodeEnum.CORETIMEOUT.name());
                            break;
                        case "17":
                            /*bank code 247 ko ton tai*/
                            response.setResponseCode(ResponseCodeEnum.BANKCODE247NOTSUPPORT.getText());
                            response.setDescription(ResponseCodeEnum.BANKCODE247NOTSUPPORT.name());
                            break;
                        case "19":
                            /*Lon hon 1 giao dich*/
                            response.setResponseCode(ResponseCodeEnum.MORETHANMAXAMOUNT.getText());
                            response.setDescription(ResponseCodeEnum.MORETHANMAXAMOUNT.name());
                            break;
                        case "21":
                            /*So tien nho hon 1 giao dich*/
                            response.setResponseCode(ResponseCodeEnum.LESSTHANMINAMOUNT.getText());
                            response.setDescription(ResponseCodeEnum.LESSTHANMINAMOUNT.name());
                            break;
                        case "92":
                            response.setResponseCode(ResponseCodeEnum.INVALIDRECEIVINGINFO.getText());
                            response.setDescription(ResponseCodeEnum.INVALIDRECEIVINGINFO.name());
                            break;
                        case "93":
                            /*bankcode ko duoc ho tro*/
                            response.setResponseCode(ResponseCodeEnum.BANKCODENOTSUPPORT.getText());
                            response.setDescription(ResponseCodeEnum.BANKCODENOTSUPPORT.name());
                            break;
                        case "100":
                            /*bankcode ko duoc ho tro*/
                            response.setResponseCode(ResponseCodeEnum.KYCHOLD.getText());
                            response.setDescription(ResponseCodeEnum.KYCHOLD.name());
                            break;
                        case "103":
                            /*bankcode ko duoc ho tro*/
                            response.setResponseCode(ResponseCodeEnum.KYCERROR.getText());
                            response.setDescription(ResponseCodeEnum.KYCERROR.name());
                            break;
                        case "102":
                            /*KYC Exception*/
                            response.setResponseCode(ResponseCodeEnum.KYCTIMEOUT.getText());
                            response.setDescription(ResponseCodeEnum.KYCTIMEOUT.name());
                            break;
                        case "99":
                            response.setResponseCode(ResponseCodeEnum.SYSTEMERROR.getText());
                            response.setDescription(ResponseCodeEnum.SYSTEMERROR.name());
                            break;
                        default:
                            /* Cau hinh thong so mac dinh doi tac phai chu dong lien he voi bank cua minh */
                            response.setResponseCode(ResponseCodeEnum.UNKNOWSTATUS.getText());
                            response.setDescription(ResponseCodeEnum.UNKNOWSTATUS.name());
                    }
                } else {
                    /* Khong tim thay giao dich voi thong tin doi tac cung cap */
                    response.setResponseCode(ResponseCodeEnum.UNKNOWSTATUS.getText());
                    response.setDescription(ResponseCodeEnum.UNKNOWSTATUS.name());
                }
            } else {
                /* Cau hinh thong so mac dinh doi tac phai chu dong lien he voi bank cua minh */
                response.setResponseCode(ResponseCodeEnum.UNKNOWSTATUS.getText());
                response.setDescription(ResponseCodeEnum.UNKNOWSTATUS.name());
            }
        } catch (Exception ex) {
            /* Cau hinh thong so mac dinh doi tac phai chu dong lien he voi bank cua minh */
            LOGGER.error(ex);
            response.setResponseCode(ResponseCodeEnum.UNKNOWSTATUS.getText());
            response.setDescription(ResponseCodeEnum.UNKNOWSTATUS.name());
        }
        return XMLUtils.Marshaller(response);
    }

    public String getDataCollated(OneInventoryRequest req) {
        OneInventoryResponse response = new OneInventoryResponse();
        Collated coll = new Collated();
        try {
            /*Lay ra danh sach gd*/
            List<CollatedDetail> getListDataCollated = Helper.getDBI().getListDataCollated(req.getProviderId(), req.getTransDate());
            /*cau hinh response tra ve cho doi tac*/

            coll.setDetail(getListDataCollated);
            response.setTransData(coll);
            response.setProviderId(req.getProviderId());
            if (!getListDataCollated.isEmpty()) {
                response.setResponseCode(ResponseCodeEnum.SUCCESS.getText());
            } else {
                response.setResponseCode(ResponseCodeEnum.CANNOTCALL.getText());
            }
            /*Tao siganture cua SCB*/
            String md5Key = SiUtils.getMd5Key(req.getProviderId());
            response.setSignatureScb(md5Key);
        } catch (Exception ex) {
            LOGGER.error(ex);
            response.setResponseCode(ResponseCodeEnum.SYSTEMERROR.getText());
        }
        return XMLUtils.Marshaller(response);
    }

    /*Ham chuyen khoan bo sung them thong tin sender
    *Doi tac dang su dung: Western Union (WU)
    * dang test tren 73.20 
     */
    public String transferMoneyWithSender(TransferMoneyReq req) {
        TransferMoneyRes response = new TransferMoneyRes();
        try {
            // insert thong tin vo table goc
            String[] resultLog = Helper.getDBI().SI_INSERTSITRANSFROMSI(req.getProviderId(),
                    req.getTxnRef(), req.getTransDate());
            // lay ma so ref lien ket duoc phan hoi neu insert thanh cong
            response.setScbRef(resultLog[0]);
            response.setResponseCode(resultLog[1]);
            // kiem tra xem co insert thong tin vo table goc duoc ko
            if ("00".equals(response.getResponseCode())) {
                // lay ra danh sach thong tin chuyen tien chi tiet
                Transaction transDetails = req.getTransDetails();
                // neu danh sach thong tin chuyen tien rong thi ko can xu ly tiep theo
                if (transDetails.getTrnDetail() != null) {
                    // duyet tung thong tin chuyen tien chi tiet
                    for (TransactionDetail transDetail : transDetails.getTrnDetail()) {
                        String status, coreRef;
                        String errorMessage = "";
                        transDetail.setScbDetailId(response.getScbRef());
                        transDetail.setupForOldPartner();
                        setupProductCodeForTransferMoney(transDetail);
                        ExchangeRateRes resExchange = transDetail.isVND()
                                ? new ExchangeRateRes(transDetail.getProduct(),
                                        transDetail.getAmountBigDecimalType(),
                                        new BigDecimal(BigInteger.ONE), transDetail.getCcy(),
                                        transDetail.getCcy(), "00")
                                : SiUtils.getExchangeRate(transDetail);

                        /*Check thong tin sender co hop le ko */
                        boolean isValidSender = transDetail.getSenderInfo().isValidSender();
                        if (isValidSender) {
                            /*Test case RIA - test xong xoa*/
                            String[] testCaseResult = testCaseForWesternUnion(transDetail.getCustomerAccount(), transDetail.getBankCode(), transDetail.getReceivingInfo().getLastName(),
                                    transDetail.getReceivingInfo().getFirstName(), req.getMsgId());
                            if (!"00".equals(testCaseResult[0])) {
                                if ("95".equals(response.getResponseCode())) {
                                    //response.setBankTransId(bankTransId);
                                    response.setResponseCode("95");
                                    response.setDescription(getErrorMessage("95"));
                                } else {
                                    //response.setBankTransId(bankTransId);
                                    response.setResponseCode(ResponseCodeEnum.SUCCESS.getText());
                                    //response.setDescription( ResponseCodeEnum.SUCCESS.name());
                                }
                                String md5Key = SiUtils.getMd5Key(req.getProviderId());
                                transDetail.setResponseCode(testCaseResult[0]);
                                transDetail.setResponseDesc(testCaseResult[1]);
                                transDetail.clearBeforeResponse();
                                response.setScbRef(response.getScbRef());
                                response.setTransData(transDetails, req.isOldPartner());                                                             
                                response.setTxnRef(req.getTxnRef());
                                response.setProviderId(req.getProviderId());
                                response.setSignatureScb(md5Key);
                                return XMLUtils.Marshaller(response);
                            }

                            /*Check kyc*/
                            boolean isValidReveiver = isValidReceivingForWesternUnion(req, transDetail);

                            if (isValidReveiver && resExchange != null && "00".equals(resExchange.getResultCode())) {
                                transDetail.setExchangeRateRes(resExchange);
                                List<String> rs = SiUtils.insertDetailToSi(transDetail);
                                String insertResult = rs.get(0);
                                /*Kiem tra thong tin sender va insert vao db si_sender_detail*/
                                String resultInsert = SiUtils.insertToSiSenderDetail(transDetail.getSenderInfo(), rs.get(1));
                                if (rs.size() >= 2 && "00".equals(insertResult) && "00".equals(resultInsert)) {
                                    transDetail.setScbDetailId(rs.get(1));
                                    VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(
                                            transDetail.getPartnerAccount());
                                    if (transDetail.getCcy().equals(custacc.getCcy())) {
                                        FundTransferRq fundTransferRq = new FundTransferRq(transDetail);
                                        fundTransferRq.setUserId(transDetail.getUserId());
                                        fundTransferRq.setCifNo(custacc.getCustNo());
                                        /* them de lay ten nguoi gui */
                                        fundTransferRq.setUserName(transDetail.getSenderInfo().getLastName() + transDetail.getSenderInfo().getFirstName());
                                        IIBPaymentService iibPaymentService = new IIBPaymentService();
                                        switch (transDetail.getTransactionType()) {
                                            case INTERNALCMND:
                                                fundTransferRq.setBranchCode(transDetail.getBranchCode());
                                                fundTransferRq.setBeneName(transDetail.getCustomerName());
                                                fundTransferRq.setCityCode(transDetail.getCityName());
                                                fundTransferRq.setBranchName(transDetail.getCityName());
                                                fundTransferRq.setIDOpenDate(transDetail.getOpenDate());
                                                fundTransferRq.setToAccount(transDetail.getCustomerAccount());

                                                ExecutePaymentTransactionExternal_in_Type external_id_input = SiUtils.getReqToTransferExternalId(fundTransferRq);
                                                if (external_id_input != null) {
                                                    ExecutePaymentTransactionExternal_out_Type execute_external_id_out = iibPaymentService.executePaymentTransactionExternal(Configuration.getIIBContext(), external_id_input);
                                                    coreRef = IIBConstants.TRANSACTION_SUCCESS.equals(execute_external_id_out
                                                            .getTransactionInfo().getTransactionReturn())
                                                                    ? execute_external_id_out.getTransactionInfo().getCoreRefNum()
                                                                    : null;
                                                } else {
                                                    coreRef = null;
                                                }
                                                break;
                                            case EXTERNALACCOUNT:
                                                ExecutePaymentTransactionExternal_in_Type external_input = SiUtils.getReqToTransferExternalAcc(fundTransferRq);
                                                if (external_input != null) {
                                                    ExecutePaymentTransactionExternal_out_Type execute_external_out = iibPaymentService.executePaymentTransactionExternal(Configuration.getIIBContext(), external_input);
                                                    coreRef = IIBConstants.TRANSACTION_SUCCESS.equals(execute_external_out
                                                            .getTransactionInfo().getTransactionReturn())
                                                                    ? execute_external_out.getTransactionInfo().getCoreRefNum()
                                                                    : null;
                                                } else {
                                                    coreRef = null;
                                                }
                                                break;
                                            case INTERNALCARD:/* INTERNALCARD luon luon di lien voi INTERNALACCOUNT */
                                                String account = SiUtils.getAccountFromCardNumber(transDetail.getCustomerAccount());
                                                if (account == null) {
                                                    LOGGER.warn("Cannot get account from card number " + transDetail.getCustomerAccount());
                                                    coreRef = null;
                                                    break;
                                                }
                                                fundTransferRq.setToAccount(account);
                                            case INTERNALACCOUNT:
                                                /* Da chuyen qua IIB */
                                                ExecutePaymentTransactionInternal_in_Type internal_input = SiUtils.getReqToTransferInternalAcc(fundTransferRq);
                                                ExecutePaymentTransactionInternal_out_Type executePaymentTransactionInternal_out_Type = iibPaymentService.executePaymentInterRestSimpleWithBrn(Configuration.getIIBContext(), internal_input);
                                                coreRef = IIBConstants.TRANSACTION_SUCCESS.equals(executePaymentTransactionInternal_out_Type
                                                        .getTransactionInfo().getTransactionReturn())
                                                                ? executePaymentTransactionInternal_out_Type.getTransactionInfo().getCoreRefNum()
                                                                : null;
                                                break;
                                            default:
                                                LOGGER.warn("TransactionType: " + transDetail.getTransactionType()
                                                        + ", Account: " + transDetail.getCustomerAccount());
                                                coreRef = null;
                                                break;
                                        }
                                        if (coreRef != null) {
                                            if ("TIMEOUT".equals(coreRef)) {
                                                LOGGER.warn("Timeout khi goi qua core. coreRef = [TIMEOUT]");
                                                status = ResponseCodeEnum.CORETIMEOUT.getText();
                                                errorMessage = ResponseCodeEnum.CORETIMEOUT.name();
                                            } else {
                                                LOGGER.info("Hach toan qua core thanh cong. Core = [" + coreRef + "]");
                                                status = ResponseCodeEnum.SUCCESS.getText();
                                                errorMessage = ResponseCodeEnum.SUCCESS.name();
                                            }
                                        } else {
                                            /* Loi he thong ko cat duoc tien tu fcdb */
                                            LOGGER.warn("Loi he thong ko cat duoc tien tu fcdb");
                                            status = ResponseCodeEnum.DEDUCTIONFAIlED.getText();
                                            errorMessage = ResponseCodeEnum.DEDUCTIONFAIlED.name();
                                        }
                                    } else {
                                        /* Don vi tien te khong khop voi thong tin tai khoan */
                                        LOGGER.warn("Don vi tien te khong khop voi thong tin tai khoan");
                                        coreRef = null;
                                        status = ResponseCodeEnum.SYSTEMERROR.getText();
                                        errorMessage = ResponseCodeEnum.SYSTEMERROR.name();
                                    }

                                    LOGGER.info("transferMoney --> coreRef : " + coreRef + ", DetailId: " + transDetail.getScbDetailId());
                                    String toDay = CommonUtils.getDate("MM/dd/yyyy HH:mm:ss");
                                    Helper.getDBI().SI_UPDATETRANSFERFROMSIDETAIL(transDetail.getScbDetailIdIntegerType(), String.valueOf(coreRef), toDay, status);
                                } else {
                                    /* insertDetailToSi that bai */
                                    status = insertResult;
                                    errorMessage = getErrorMessage(status);
                                    StringBuilder str = new StringBuilder();
                                    str.append("insertDetailToSi that bai.");
                                    if (rs.size() > 0) {
                                        for (int i = 0; i < rs.size(); i++) {
                                            str.append(", rs[").append(i).append("] = [").append(rs.get(i)).append("]");
                                        }
                                    } else {
                                        str.append("rs.size = 0");
                                    }
                                    LOGGER.warn(str.toString());
                                }
                            } else {
                                /* Ko chuyen doi duoc ty gia tien te hoac invalid KYC */
                                LOGGER.warn("Ko chuyen doi duoc ty gia tien te hoac invalid KYC. isValidReveiver = " + isValidReveiver + ", resExchange = " + resExchange.toString());
                                status = transDetail.getResponseCode();
                                errorMessage = transDetail.getResponseDesc();
                                /*Insert vao bang detail khi gap loi kyc*/
                                transDetail.setExchangeRateRes(resExchange);
                                String[] result = SiUtils.insertErrorKycToSiTransDetail(transDetail);
                                // them du lieu vao sender
                                if (result != null && result.length > 0) {
                                    SiUtils.insertToSiSenderDetail(transDetail.getSenderInfo(), result[1]);
                                }
                                /*Cau hinh ma gd cua SCB tra ra cho doi tac*/
                                transDetail.setScbDetailId(result[1]);
                            }
                            transDetail.setResponseCode(status);
                            transDetail.setResponseDesc(errorMessage);
                        } else {
                            LOGGER.info("Thong tin Sender khong hop le: [TxnDetailId: " + transDetail.getTxnDetailId() + "], "
                                    + "Sender: [FirstName: " + transDetail.getSenderInfo().getFirstName() + "],"
                                    + "[LastName: " + transDetail.getSenderInfo().getLastName() + "],"
                                    + "[ContryIsoCode: " + transDetail.getSenderInfo().getCountryIsoCode() + "],"
                                    + "[Address: " + transDetail.getSenderInfo().getAddress() + "],");

                            transDetail.setResponseCode(ResponseCodeEnum.INVALIDSENDER.getText());
                            transDetail.setResponseDesc(ResponseCodeEnum.INVALIDSENDER.name());
                        }
                        transDetail.clearBeforeResponse();
                    }
                } else {
                    LOGGER.warn("Cannot get getTrnDetail from request");
                }
                response.setTransData(transDetails, req.isOldPartner());
            } else {
                LOGGER.warn("insert database SI_INSERTSITRANSFROMSI that bai. Response = [" + response.getResponseCode() + "]");
            }
            response.setDescription(getErrorMessage(response.getResponseCode()));
            response.setTxnRef(req.getTxnRef());
            response.setProviderId(req.getProviderId());
            String md5Key = SiUtils.getMd5Key(req.getProviderId());
            response.setSignatureScb(md5Key);
        } catch (Exception ex) {
            LOGGER.error(ex);
            response.setResponseCode(ResponseCodeEnum.SYSTEMERROR.getText());
            response.setDescription(ResponseCodeEnum.SYSTEMERROR.name());
        }
        return XMLUtils.Marshaller(response);
    }

    /*
    *Doi tac dang su dung: Western Union (WU)
    * dang test tren 73.20 
     */
    public String getListTransactionByDate(GetListTransactionByDateRequest req) {
        GetListTransactionByDateResponse response = new GetListTransactionByDateResponse();
        try {
            /*Check partnerAccount hop le ko*/
            String partnerAccount = SiUtils.getAccountPartner(req.getProviderId());
            if (partnerAccount != null) {
                /*Lay danh sach gd theo ngay ddMMyyyyHHmm*/
                List<TransactionDetailByDate> listTransaction = Helper.getDBI().getListTransactionByDate(req.getProviderId(), partnerAccount, req.getStartDateTime(), req.getEndDateTime());
                if (!listTransaction.isEmpty()) {
                    TransactionByDate trn = new TransactionByDate();
                    trn.setTrnDetail(listTransaction);
                    for (TransactionDetailByDate transDetail : trn.getTrnDetail()) {
                        String errorMessage = getErrorMessage(transDetail.getStatus());
                        transDetail.setDescription(errorMessage);
                    }
                    /*zip data*/
                    response.setTransData(trn);
                    response.setResponseCode(ResponseCodeEnum.SUCCESS.getText());
                    response.setDescription(ResponseCodeEnum.SUCCESS.name());
                } else {
                    LOGGER.warn("Khong ton tai giao dich tu ngay: " + req.getStartDateTime() + "den ngay: " + req.getEndDateTime());
                    response.setResponseCode(ResponseCodeEnum.TRANSACTIONNOTEXISTS.getText());
                    response.setDescription(ResponseCodeEnum.TRANSACTIONNOTEXISTS.name());
                }
            } else {
                LOGGER.info("stk doi tac khong ton tai trong he thong");
                response.setResponseCode(ResponseCodeEnum.PARTNERACCOUNTWRONG.getText());
                response.setDescription(ResponseCodeEnum.PARTNERACCOUNTWRONG.name());
            }
            response.setProviderId(req.getProviderId());
            response.setTransDate(req.getTransDate());
            response.setStartDateTime(req.getStartDateTime());
            response.setEndDateTime(req.getEndDateTime());
            /*Tao chu ky cua SCB*/
            response.signMac(SiUtils.getMd5Key(req.getProviderId()));
        } catch (Exception e) {
            response.setResponseCode(ResponseCodeEnum.SYSTEMERROR.getText());
            response.setDescription(ResponseCodeEnum.SYSTEMERROR.name());
            LOGGER.error(e);
        }
        return XMLUtils.Marshaller(response);
    }

    /*
    * Ham tra description theo ma loi
    * dang test tren 73.20 
     */
    public String getErrorMessage(String errorCode) {
        String errorMessage = null;
        switch (errorCode) {
            case "00":
                errorMessage = ResponseCodeEnum.SUCCESS.name();
                break;
            case "01":
                errorMessage = ResponseCodeEnum.ACCOUNTINVALID.name();
                break;
            case "02":
                errorMessage = ResponseCodeEnum.ACCDESTINATIONCLOSE.name();
                break;
            case "03":
                errorMessage = ResponseCodeEnum.ACCCANNOTTRANSACTION.name();
                break;
            case "06":
                errorMessage = ResponseCodeEnum.PARTNERACCOUNTWRONG.name();
                break;
            case "07":
                errorMessage = ResponseCodeEnum.PARTNERACCOUNTINVALID.name();
                break;
            case "08":
                errorMessage = ResponseCodeEnum.PARTNERACCOUNTNOTENOUGH.name();
                break;
            case "09":
                errorMessage = ResponseCodeEnum.DEDUCTIONFAIlED.name();
                break;
            case "15":
                errorMessage = ResponseCodeEnum.TRANSFAILED.name();
                break;
            case "16":
                errorMessage = ResponseCodeEnum.CORETIMEOUT.name();
                break;

            case "17":
                errorMessage = ResponseCodeEnum.BANKCODE247NOTSUPPORT.name();
                break;
            case "19":
                errorMessage = ResponseCodeEnum.MORETHANMAXAMOUNT.name();
                break;
            case "21":
                errorMessage = ResponseCodeEnum.LESSTHANMINAMOUNT.name();
                break;
            case "92":
                errorMessage = ResponseCodeEnum.INVALIDRECEIVINGINFO.name();
                break;
            case "93":
                errorMessage = ResponseCodeEnum.BANKCODENOTSUPPORT.name();
                break;
            case "95":
                errorMessage = ResponseCodeEnum.TRANSIDALREADYEXIST.name();
                break;
            case "100":
                errorMessage = ResponseCodeEnum.KYCHOLD.name();
                break;
            case "102":
                errorMessage = ResponseCodeEnum.KYCTIMEOUT.name();
                break;
            case "103":
                errorMessage = ResponseCodeEnum.KYCERROR.name();
                break;
            default:
                break;
        }
        return errorMessage;
    }

    /*Test case cho doi tac WU - Test xong xoa*/
    public String[] testCaseForWesternUnion(String accountNumber, String bankCode, String lastName, String firstName, String msgId) {
//        String fullName = lastName.toUpperCase() + " " + firstName.toUpperCase();
        String fullName2 = firstName.toUpperCase() + " " + lastName.toUpperCase();
        String status = ResponseCodeEnum.SUCCESS.getText();
        String description = ResponseCodeEnum.SUCCESS.name();
        LOGGER.info("first name: " + firstName + " -last name: " + lastName + "\n");
        LOGGER.info("fullName: " + fullName2);
        try {
            if ("56570704".equals(accountNumber) && "970415".equals(bankCode) && ("Chandler Gangwal Bing".toUpperCase()).equals(fullName2)) {
                status = ResponseCodeEnum.SUCCESS.getText();
                description = ResponseCodeEnum.SUCCESS.name();
            } else if ("91894187".equals(accountNumber) && "970416".equals(bankCode) && ("Ross Kumar Geller".toUpperCase()).equals(fullName2)) {
                if ("TRN".equals(msgId)) {
                    status = ResponseCodeEnum.CORETIMEOUT.getText();
                    description = ResponseCodeEnum.CORETIMEOUT.name();
                }
            } else if ("49362227".equals(accountNumber) && "970417".equals(bankCode) && ("Joey Jain Tribbiani".toUpperCase()).equals(fullName2)) {
                if ("TRN".equals(msgId)) {
                    status = ResponseCodeEnum.CORETIMEOUT.getText();
                    description = ResponseCodeEnum.CORETIMEOUT.name();
                }
            } else if ("56124373".equals(accountNumber) && "970418".equals(bankCode) && ("Phoebe Kudrow Buffay".toUpperCase()).equals(fullName2)) {
                status = ResponseCodeEnum.TRANSFAILED.getText();
                description = ResponseCodeEnum.TRANSFAILED.name();
            } else if ("97996966".equals(accountNumber) && "970419".equals(bankCode) && ("Monica Cox Geller".toUpperCase()).equals(fullName2)) {
                status = ResponseCodeEnum.TRANSFAILED.getText();
                description = ResponseCodeEnum.TRANSFAILED.name();
            } else if ("0010106584330001".equals(accountNumber) && "970426".equals(bankCode) && ("VO MAI PHUONG THAO".toUpperCase()).equals(fullName2)) {
            //wu else if ("58107359".equals(accountNumber) && "970432".equals(bankCode) && ("Barney Patrik Stinson".toUpperCase()).equals(fullName2)) {
                status = ResponseCodeEnum.PARTNERACCOUNTWRONG.getText();
                description = ResponseCodeEnum.PARTNERACCOUNTWRONG.name();
            } else if ("22653120".equals(accountNumber) && "970432".equals(bankCode) && ("Damon Gangwal Salvatore".toUpperCase()).equals(fullName2)) {
                if ("TRN".equals(msgId)) {
                    status = ResponseCodeEnum.KYCHOLD.getText();
                    description = ResponseCodeEnum.KYCHOLD.name();
                } else {
                    status = ResponseCodeEnum.CORETIMEOUT.getText();
                    description = ResponseCodeEnum.CORETIMEOUT.name();
                }
            } else if ("10171945".equals(accountNumber) && "970432".equals(bankCode) && "Elina Nina Dobrev".toUpperCase().equals(fullName2)) {
                if ("TRN".equals(msgId)) {
                    status = ResponseCodeEnum.KYCHOLD.getText();
                    description = ResponseCodeEnum.KYCHOLD.name();
                }
            } else if ("12345678910".equals(accountNumber) && "970436".equals(bankCode) && "PHAN KIM TUYEN".toUpperCase().equals(fullName2)) {
                if ("TRN".equals(msgId)) {
                    status = ResponseCodeEnum.PARTNERACCOUNTNOTENOUGH.getText();
                    description = ResponseCodeEnum.PARTNERACCOUNTNOTENOUGH.name();
                }
            } 
            //baotbq - test RIA 26/2/2024                    
            //CORETIMEOUT
            else if ("0210106246710006".equals(accountNumber) && "970432".equals(bankCode)) {
                if ("001".equals(msgId)) {
                    status = ResponseCodeEnum.CORETIMEOUT.getText();
                    description = ResponseCodeEnum.CORETIMEOUT.name();
                }
            } else if ("0860109332910005".equals(accountNumber) && "970409".equals(bankCode)) {
                if ("001".equals(msgId)) {
                    status = ResponseCodeEnum.CORETIMEOUT.getText();
                    description = ResponseCodeEnum.CORETIMEOUT.name();
                }
            }
             else if ("1370107762920001".equals(accountNumber) && "000000".equals(bankCode)) {
                if ("001".equals(msgId)) { 
                    status = ResponseCodeEnum.CORETIMEOUT.getText();
                    description = ResponseCodeEnum.CORETIMEOUT.name();
                }
            } else if ("0010100047410001".equals(accountNumber) && "000000".equals(bankCode)) {
                if ("001".equals(msgId)) {
                    status = ResponseCodeEnum.CORETIMEOUT.getText();
                    description = ResponseCodeEnum.CORETIMEOUT.name();
                }
            }
            //PARTNERACCOUNTWRONG
            else if ("0010106584330001".equals(accountNumber) && "970426".equals(bankCode)) {
                if ("001".equals(msgId)) {
                    status = ResponseCodeEnum.PARTNERACCOUNTWRONG.getText();
                    description = ResponseCodeEnum.PARTNERACCOUNTWRONG.name();
                }
            } else if ("0010110318850001".equals(accountNumber) && "970406".equals(bankCode)) {
                if ("001".equals(msgId)) {
                    status = ResponseCodeEnum.PARTNERACCOUNTWRONG.getText();
                    description = ResponseCodeEnum.PARTNERACCOUNTWRONG.name();
                }
            }
            else if ("1370108598670001".equals(accountNumber) && "000000".equals(bankCode)) {
                if ("001".equals(msgId)) { 
                    status = ResponseCodeEnum.PARTNERACCOUNTWRONG.getText();
                    description = ResponseCodeEnum.PARTNERACCOUNTWRONG.name();
                }
            } else if ("0010100324830001".equals(accountNumber) && "000000".equals(bankCode)) {
                if ("001".equals(msgId)) {
                    status = ResponseCodeEnum.PARTNERACCOUNTWRONG.getText();
                    description = ResponseCodeEnum.PARTNERACCOUNTWRONG.name();
                }
            }
            //PARTNERACCOUNTINVALID 
            else if ("0020108134000001".equals(accountNumber) && "970431".equals(bankCode)) {
                if ("001".equals(msgId)) {
                    status = ResponseCodeEnum.PARTNERACCOUNTINVALID.getText();
                    description = ResponseCodeEnum.PARTNERACCOUNTINVALID.name();
                }
            } else if ("0030100541110002".equals(accountNumber) && "970428".equals(bankCode)) {
                if ("001".equals(msgId)) {
                    status = ResponseCodeEnum.PARTNERACCOUNTINVALID.getText();
                    description = ResponseCodeEnum.PARTNERACCOUNTINVALID.name();
                }
            }
            else if ("1370108460810001".equals(accountNumber) && "000000".equals(bankCode)) {
                if ("001".equals(msgId)) {
                    status = ResponseCodeEnum.PARTNERACCOUNTINVALID.getText();
                    description = ResponseCodeEnum.PARTNERACCOUNTINVALID.name();
                }
            } else if ("0010100236220001".equals(accountNumber) && "000000".equals(bankCode)) {
                if ("001".equals(msgId)) {
                    status = ResponseCodeEnum.PARTNERACCOUNTINVALID.getText();
                    description = ResponseCodeEnum.PARTNERACCOUNTINVALID.name();
                }
            }
            //SYSTEMERROR
            else if ("0090110559210001".equals(accountNumber) && "000000".equals(bankCode)) {
                if ("001".equals(msgId)) { 
                    status = ResponseCodeEnum.SYSTEMERROR.getText();
                    description = ResponseCodeEnum.SYSTEMERROR.name();
                }
            } else if ("0010104734030001".equals(accountNumber) && "000000".equals(bankCode)) {
                if ("001".equals(msgId)) {
                    status = ResponseCodeEnum.SYSTEMERROR.getText();
                    description = ResponseCodeEnum.SYSTEMERROR.name();
                }
            }
            else if ("0810109284110006".equals(accountNumber) && "970438".equals(bankCode)) {
                if ("001".equals(msgId)) { 
                    status = ResponseCodeEnum.SYSTEMERROR.getText();
                    description = ResponseCodeEnum.SYSTEMERROR.name();
                }
            } else if ("820109357270003".equals(accountNumber) && "970430".equals(bankCode)) {
                if ("001".equals(msgId)) {
                    status = ResponseCodeEnum.SYSTEMERROR.getText();
                    description = ResponseCodeEnum.SYSTEMERROR.name();
                }
            }
            
            //DEDUCTIONFAILED 
            else if ("1370107392270004".equals(accountNumber) && "000000".equals(bankCode)) {
                if ("001".equals(msgId)) { 
                    status = ResponseCodeEnum.DEDUCTIONFAIlED.getText();
                    description = ResponseCodeEnum.DEDUCTIONFAIlED.name();
                }
            } else if ("0010100226210001".equals(accountNumber) && "000000".equals(bankCode)) {
                if ("001".equals(msgId)) {
                    status = ResponseCodeEnum.DEDUCTIONFAIlED.getText();
                    description = ResponseCodeEnum.DEDUCTIONFAIlED.name();
                }
            }
            //KYC
            else if ("0010108541430001".equals(accountNumber) && "000000".equals(bankCode)) {
                if ("001".equals(msgId)) { 
                    status = ResponseCodeEnum.KYCHOLD.getText();
                    description = ResponseCodeEnum.KYCHOLD.name();
                }
            } else if ("0010100137600001".equals(accountNumber) && "000000".equals(bankCode)) {
                if ("001".equals(msgId)) {
                    status = ResponseCodeEnum.KYCHOLD.getText();
                    description = ResponseCodeEnum.KYCHOLD.name();
                }
            }
            
            

        } catch (Exception e) {
            LOGGER.error(e);
        }
        String[] result = (status + "#" + description).split("#");
        return result;
    }

    //dung cho cap nhat giao dich chuyen tien trong he thong trên ebanking mo rong
    public String transferMoney_Ebanking(TransferMoneyEbankReq req) {
        String status = "";
        TransferMoneyRes response = new TransferMoneyRes();
        try {
            // lay ra thong tin chuyen tien chi tiet
            String coreRef;
            String userId = "";
            String product = "";
            String glAccount = "";

            //setup productcode for transfer money
            TransactionDetail transDetail = new TransactionDetail();
            switch (req.getTransactionType()) {
                case INTERNALCMND:
                    userId = userIdFcubs; // tam thoi cau hinh ve user nay vi code cu ko thay cau hinh hoac co the thay the bang useridfcubs_MB
                    product = productKieuhoiInternalCMNDSCB;
                    glAccount = GLPAYMENTINTERID;

                    break;
                case EXTERNALACCOUNT:
                    userId = GLKIEUHOIDOMESTIC;
                    product = productKieuhoiExternalSCB;
                    glAccount = userIdFcubs;
                    break;
                case INTERNALCARD:
                case INTERNALACCOUNT:
                    /* Da chuyen qua IIB */
                    userId = userIdFcubs;
                    product = productKieuhoiInternalSCB;
                    break;
                default:
                    break;
            }

            transDetail.setScbDetailId(req.getId());
            VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(
                    transDetail.getPartnerAccount());

            if (req.getCcy().equals(custacc.getCcy())) {
                FundTransferRq fundTransferRq = new FundTransferRq(transDetail);
                fundTransferRq.setUserId(userId);
                fundTransferRq.setCifNo(custacc.getCustNo());
                /* them de lay ten nguoi gui */
                fundTransferRq.setUserName(custacc.getFullName());
                IIBPaymentService iibPaymentService = new IIBPaymentService();

                switch (req.getTransactionType()) {
                    case INTERNALCMND:
                        fundTransferRq.setBranchCode(req.getBranchCode());
                        fundTransferRq.setBeneName(req.getCustomerName());
                        fundTransferRq.setCityCode(req.getCityName());
                        fundTransferRq.setBranchName(req.getCityName());
                        fundTransferRq.setIDOpenDate(req.getOpenDate());
                        fundTransferRq.setToAccount(req.getCustomerAccount());

                        ExecutePaymentTransactionExternal_in_Type external_id_input = SiUtils.getReqToTransferExternalId(fundTransferRq);
                        if (external_id_input != null) {
                            ExecutePaymentTransactionExternal_out_Type execute_external_id_out = iibPaymentService.executePaymentTransactionExternal(Configuration.getIIBContext(), external_id_input);
                            coreRef = IIBConstants.TRANSACTION_SUCCESS.equals(execute_external_id_out
                                    .getTransactionInfo().getTransactionReturn())
                                            ? execute_external_id_out.getTransactionInfo().getCoreRefNum()
                                            : null;
                        } else {
                            coreRef = null;
                        }
                        break;
                    case EXTERNALACCOUNT:
                        ExecutePaymentTransactionExternal_in_Type external_input = SiUtils.getReqToTransferExternalAcc(fundTransferRq);
                        if (external_input != null) {
                            ExecutePaymentTransactionExternal_out_Type execute_external_out = iibPaymentService.executePaymentTransactionExternal(Configuration.getIIBContext(), external_input);
                            coreRef = IIBConstants.TRANSACTION_SUCCESS.equals(execute_external_out
                                    .getTransactionInfo().getTransactionReturn())
                                            ? execute_external_out.getTransactionInfo().getCoreRefNum()
                                            : null;
                        } else {
                            coreRef = null;
                        }
                        break;
                    case INTERNALCARD:/* INTERNALCARD luon luon di lien voi INTERNALACCOUNT */
                        String account = SiUtils.getAccountFromCardNumber(transDetail.getCustomerAccount());
                        if (account == null) {
                            LOGGER.warn("Cannot get account from card number " + transDetail.getCustomerAccount());
                            coreRef = null;
                            break;
                        }
                        fundTransferRq.setToAccount(account);
                    case INTERNALACCOUNT:
                        /* Da chuyen qua IIB */
                        ExecutePaymentTransactionInternal_in_Type internal_input = SiUtils.getReqToTransferInternalAcc(fundTransferRq);
                        ExecutePaymentTransactionInternal_out_Type executePaymentTransactionInternal_out_Type = iibPaymentService.executePaymentInterRestSimpleWithBrn(Configuration.getIIBContext(), internal_input);
                        coreRef = IIBConstants.TRANSACTION_SUCCESS.equals(executePaymentTransactionInternal_out_Type
                                .getTransactionInfo().getTransactionReturn())
                                        ? executePaymentTransactionInternal_out_Type.getTransactionInfo().getCoreRefNum()
                                        : null;
                        break;
                    default:
                        LOGGER.warn("TransactionType: " + transDetail.getTransactionType()
                                + ", Account: " + transDetail.getCustomerAccount());
                        coreRef = null;
                        break;
                }
                if (coreRef != null) {
                    if ("TIMEOUT".equals(coreRef)) {
                        LOGGER.warn("Timeout khi goi qua core. coreRef = [TIMEOUT]");
                        status = ResponseCodeEnum.CORETIMEOUT.getText();
                    } else {
                        LOGGER.info("Hach toan qua core thanh cong. Core = [" + coreRef + "]");
                        status = ResponseCodeEnum.SUCCESS.getText();
                    }
                } else {
                    /* Loi he thong ko cat duoc tien tu fcdb */
                    LOGGER.warn("Loi he thong ko cat duoc tien tu fcdb");
                    status = ResponseCodeEnum.DEDUCTIONFAIlED.getText();
                }
            } else {
                /* Don vi tien te khong khop voi thong tin tai khoan */
                LOGGER.warn("Don vi tien te khong khop voi thong tin tai khoan");
                coreRef = null;
                status = ResponseCodeEnum.SYSTEMERROR.getText();
            }

            LOGGER.info("transferMoney --> coreRef : " + coreRef + ", DetailId: " + transDetail.getScbDetailId());
            String toDay = CommonUtils.getDate("MM/dd/yyyy HH:mm:ss");
            Helper.getDBI().SI_UPDATETRANSFERFROMSIDETAIL(transDetail.getScbDetailIdIntegerType(), String.valueOf(coreRef), toDay, status);
            transDetail.setResponseCode(status);
            transDetail.clearBeforeResponse();
        } catch (Exception ex) {
            LOGGER.error(ex);
            response.setResponseCode(ResponseCodeEnum.SYSTEMERROR.getText());
            response.setDescription(ResponseCodeEnum.SYSTEMERROR.name());
        }
        return status;
    }

    /*Ham cho WU duyet lai giao dich 100 tren EBMR*/
    public String transferMoney247_Ebanking(TransferMoney247EbankReq req) {
        String status = "";
        try {
            // tao message xu ly cho ibtController
            TransferMoney247DetailReq detailReq = new TransferMoney247DetailReq();
            detailReq.setFromNumber(req.getAccountPartner());
            detailReq.setTypeFromNumber("ACCOUNT");
            detailReq.setFullName(req.getProviderId() + "#" + "VIETNAM");
            detailReq.setToNumber(req.getToNumber());
            detailReq.setTypeToNumber(req.getTypeToNumber());
            detailReq.setBenId(req.getBenId());
            detailReq.setAmount(req.getAmount());
            detailReq.setCcy(req.getCcy());
            detailReq.setDescription(req.getDescription());
            detailReq.setTermId("11111111");
            detailReq.setCardAcceptor("SML INTERNET BANKING      HCM        VNM");
            detailReq.setTypeFunction(req.getMsgId());
            detailReq.setProduct(productKieuhoiExternalSCB247);
            detailReq.setNapasAccount(GLPAYMENTEXTERNAPAS);
            detailReq.setMerchantType("7399");
            detailReq.setUserIdFcubs(userIdFcubs);
            detailReq.setupProcessingCode(); // cau hinh ma processingCode de truyen qua cho NAPAS
            detailReq.setBankTransId(req.getTransId());
            Gson gson = new Gson();
            LOGGER.info("requestTransfer247: " + gson.toJson(detailReq));
            /* Kiem tra va cap nhat trang thai thong tin thanh toan truoc khi goi qua Napas */
            status = updateStatusEBMR(req, detailReq);
            if ("00".equals(status)) {
                IBTController ibt = new IBTController();
                /* THUC HIEN GOI SANG NAPAS */
                TransferMoney247DetailRes result = ibt.transferFromSCBwithObj(detailReq);
                LOGGER.info("TransId = [" + req.getTransId() + "], Ket qua tra ve sau khi xu ly goi qua NAPAS = [" + result.getResponseCode() + "]");
                // phan tich ket qua tra ve
                switch (result.getResponseCode()) {
                    case "00":
                        status = ResponseCodeEnum.SUCCESS.getText();
                        break;
                    case "16":
                    /* TIMEOUT CHO DOI SOAT */
                    case "27":
                    case "26":
                    case "18":
                    case "24":
                        status = ResponseCodeEnum.CORETIMEOUT.getText();
                        break;
                    default:
                        /* KHONG THANH CONG */
                        status = ResponseCodeEnum.TRANSFAILED.getText();
                        break;
                }
                /* THUC HIEN CAP NHAT DU LIEU */
                Helper.getDBI().SI_UPDATETRANSFERFROMSIDETAIL(new Double(req.getId()),
                        result.getRefCore(), "", status);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            status = ResponseCodeEnum.SYSTEMERROR.getText();
        }
        return status;
    }

    private String updateStatusEBMR(TransferMoney247EbankReq req, TransferMoney247DetailReq reqDetail) {
        String result = "99";
        try {
            int idMaster = Integer.valueOf(req.getIdMaster());
            // gan transaction detail id = transaction id = 1 ma cho gon
            String transDetailId = req.getTransId();
            // insert thong tin vo bang chi tiet
            Object[] resultInsertDetail = Helper.getDBI().SI_UPDATETRANSFERFROMSIDETAILKIEUHOI_EBMR(
                    idMaster,
                    transDetailId,
                    reqDetail.getFromNumber(),
                    reqDetail.getToNumber(),
                    reqDetail.getBenId(),
                    req.getAmount().doubleValue(),
                    "03",
                    "CARD".equals(reqDetail.getTypeToNumber()) ? "01" : "02",
                    BigDecimal.valueOf(Long.valueOf(req.getAmt_exchange())));
            // lay ket qua thanh cong hay ko
            String status = resultInsertDetail[0].toString();
            LOGGER.info("idMaster update: " + resultInsertDetail[1].toString());
            // cau hinh ma phan hoi id cua bank de tra soat sau nay
//            response.setBankTransId(resultInsertDetail[1].toString());
//            reqDetail.setBankTransId(response.getBankTransId());
            result = status;
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }

}
