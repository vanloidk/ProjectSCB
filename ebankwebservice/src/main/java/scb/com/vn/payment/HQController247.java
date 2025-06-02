/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;

import java.io.IOException;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import scb.com.vn.dbi.dto.HQ_BAOLANH_CHUNG;
import scb.com.vn.dbi.dto.HQ_BAOLANH_HDVD;
import scb.com.vn.dbi.dto.HQ_BAOLANH_TK;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_CQQLT;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_CQQLT_CT;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_HQ;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_HQ_GNT;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_HQ_GNTCT;

import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.HQProcess;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.IHTKKKeyStoreBean;
import scb.com.vn.utility.XMLSigner;
import java.util.Date;
import scb.com.vn.dbi.dto.HQ_DOICHIEU;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import scb.com.vn.dbi.dto.HQ_DKNNT;
import scb.com.vn.dbi.dto.HQ_DKNNT_LIENHE;
import scb.com.vn.dbi.dto.HQ_MSG;

/**
 *
 * @author lydty
 */
public class HQController247 {

    String SendCode = "79334001";
    String SendName = "Ngân Hàng Thương Mại Cổ Phần Sài Gòn";
    String MST_NH_PH = "0311449990";
    String AppName = "Payment";
    String AppVersion = "3.0";
    String AppVersion247 = "3.1";
    String MsgVersion = "3.0";
    String keyStoreFile = Configuration.getProperty("haiquan.keyStoreFile");
    String keyStorePwd = Configuration.getProperty("haiquan.keyStorePwd");

    private String getMsgName(String msgType) {
        String msgName = "";
        if (msgType.equals("101")) {
            msgName = "THÔNG ĐIỆP TRA CỨU THÔNG TIN NỢ THUẾ CỦA TỜ KHAI HẢI QUAN";
        } else {
            if (msgType.equals("102")) {
                msgName = "THÔNG ĐIỆP TRA CỨU THÔNG TIN NỢ LỆ PHÍ CỦA TỜ KHAI HẢI QUAN";
            } else {
                if (msgType.equals("103")) {
                    msgName = "THÔNG ĐIỆP TRA CỨU THÔNG TIN NỢ PHÍ, LỆ PHÍ CỦA CƠ QUAN QUẢN LÝ THU";
                } else {
                    if (msgType.equals("104")) {
                        msgName = "THÔNG ĐIỆP TRA CỨU THÔNG TIN BẢO LÃNH CHUNG";
                    } else {
                        if (msgType.equals("105")) {
                            msgName = "THÔNG ĐIỆP TRA CỨU THÔNG TIN TỜ KHAI HẢI QUAN";
                        } else {
                            if (msgType.equals("106")) {
                                msgName = "THÔNG ĐIỆP TRA CỨU THÔNG TIN DANH MỤC";
                            } else {
                                if (msgType.equals("107")) {
                                    msgName = "THÔNG ĐIỆP TRA CỨU TRẠNG THÁI GIAO DỊCH";
                                } else {
                                    if (msgType.equals("301")) {
                                        msgName = "THÔNG ĐIỆP NỘP THUẾ CHO TỜ KHAI HẢI QUAN";
                                    } else {
                                        if (msgType.equals("302")) {
                                            msgName = "THÔNG ĐIỆP NỘP LỆ PHÍ CHO NHIỀU TỜ KHAI HẢI QUAN";
                                        } else {
                                            if (msgType.equals("303")) {
                                                msgName = "THÔNG ĐIỆP NỘP THUẾ, PHÍ, LỆ PHÍ CỦA CƠ QUAN QUẢN LÝ THU";
                                            } else {
                                                if (msgType.equals("401")) {
                                                    msgName = "THÔNG ĐIỆP BẢO LÃNH NỘP THUẾ CHO TỜ KHAI HẢI QUAN (BẢO LÃNH CHO TỜ KHAI HẢI QUAN)";
                                                } else {
                                                    if (msgType.equals("402")) {
                                                        msgName = "THÔNG ĐIỆP BẢO LÃNH NỘP THUẾ CHO TỜ KHAI HẢI QUAN (BẢO LÃNH CHUNG)";
                                                    } else {
                                                        if (msgType.equals("403")) {
                                                            msgName = "THÔNG ĐIỆP BẢO LÃNH NỘP THUẾ CHO HOÁ ĐƠN VẬN ĐƠN";
                                                        } else {
                                                            if (msgType.equals("501") || msgType.equals("502") || msgType.equals("503") || msgType.equals("504") || msgType.equals("505") || msgType.equals("506")) {
                                                                msgName = "THÔNG ĐIỆP YÊU CẦU HUỶ THÔNG TIN CỦA GIAO DỊCH THANH TOÁN HOẶC BẢO LÃNH";
                                                            } else {
                                                                if (msgType.equals("801")) {
                                                                    msgName = "THÔNG ĐIỆP GỬI YÊU CẦU ĐỐI CHIẾU NỘP THUẾ CHO TỜ KHAI HẢI QUAN";
                                                                } else {
                                                                    if (msgType.equals("802")) {
                                                                        msgName = "THÔNG ĐIỆP GỬI YÊU CẦU ĐỐI CHIẾU NỘP LỆ PHÍ CHO NHIỀU TỜ KHAI HẢI QUAN";
                                                                    } else {
                                                                        if (msgType.equals("803")) {
                                                                            msgName = "THÔNG ĐIỆP GỬI YÊU CẦU ĐỐI CHIẾU NỘP THUẾ, PHÍ, LỆ PHÍ CỦA CƠ QUAN QUẢN LÝ THU";
                                                                        } else {
                                                                            if (msgType.equals("804")) {
                                                                                msgName = "THÔNG ĐIỆP GỬI YÊU CẦU ĐỐI CHIẾU BẢO LÃNH NỘP THUẾ CHO TỜ KHAI HẢI QUAN";
                                                                            } else {
                                                                                if (msgType.equals("805")) {
                                                                                    msgName = "THÔNG ĐIỆP GỬI YÊU CẦU ĐỐI CHIẾU BẢO LÃNH CHUNG";
                                                                                } else {
                                                                                    if (msgType.equals("806")) {
                                                                                        msgName = "THÔNG ĐIỆP GỬI YÊU CẦU ĐỐI CHIẾU BẢO LÃNH CHO HOÁ ĐƠN VẬN ĐƠN";
                                                                                    } else {
                                                                                        if (msgType.equals("807")) {
                                                                                            msgName = "THÔNG ĐIỆP GỬI YÊU CẦU ĐỐI CHIẾU DANH SÁCH CÁC YÊU CẦU ĐƯỢC GỬI TỪ CỔNG THANH TOÁN ĐIỆN TỬ HẢI QUAN ĐỐI VỚI GIAO DỊCH THANH TOÁN THUẾ";
                                                                                        } else {
                                                                                            if (msgType.equals("808")) {
                                                                                                msgName = "THÔNG ĐIỆP GỬI YÊU CẦU ĐỐI CHIẾU DANH SÁCH CÁC YÊU CẦU ĐƯỢC GỬI TỪ CỔNG THANH TOÁN ĐIỆN TỬ HẢI QUAN ĐỐI VỚI GIAO DỊCH THANH TOÁN LỆ PHÍ BỘ";
                                                                                            } else {
                                                                                                if (msgType.equals("901") || msgType.equals("902") || msgType.equals("903") || msgType.equals("904") || msgType.equals("905") || msgType.equals("906")) {
                                                                                                    msgName = "THÔNG ĐIỆP GỬI YÊU CẦU ĐỐI CHIẾU HUỶ";
                                                                                                } else {
                                                                                                    if (msgType.equals("800")) {
                                                                                                        msgName = "THÔNG ĐIỆP HỎI KẾT QUẢ ĐỐI CHIẾU";
                                                                                                    } else {
                                                                                                        if (msgType.equals("312")) {
                                                                                                            msgName = "THÔNG ĐIỆP NHTM THÔNG BÁO CHO TCHQ VỀ VIỆC NNT ĐÃ KÝ ỦY QUYỀN TRÍCH NỢ TÀI KHOẢN";
                                                                                                        } else {
                                                                                                            if (msgType.equals("213")) {
                                                                                                                msgName = "THÔNG ĐIỆP NHTM GỬI TCHQ THÔNG BÁO KẾT QUẢ XỬ LÝ";
                                                                                                            } else {
                                                                                                                if (msgType.equals("200")) {
                                                                                                                    msgName = "Thông điệp trả lời thông tin trong trường hợp chấp nhận thông tin thanh toán phí, lệ phí, bảo lãnh, hoặc chấp nhận yêu cầu đối chiếu";
                                                                                                                } else {
                                                                                                                    if (msgType.equals("299")) {
                                                                                                                        msgName = "Thông điệp trả lời thông tin khi thông điệp hỏi lỗi";
                                                                                                                    } else if (msgType.equals("314")) {
                                                                                                                        msgName = "THÔNG ĐIỆP NHTM THÔNG BÁO CHO TCHQ VỀ VIỆC NNT ĐÃ KÝ ỦY QUYỀN TRÍCH NỢ TÀI KHOẢN";
                                                                                                                    } else if (msgType.equals("108")) {
                                                                                                                        msgName = "THÔNG ĐIỆP TRA CỨU THÔNG TIN NNT ĐĂNG KÝ ỦY QUYỀN TCHQ THÔNG BÁO PHÁT SINH NỢ ĐẾN NHTM";
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return msgName;
    }

    private Element getDataM101XML(String strParmeter, Document doc) {
        //for 101/102/105
        String[] objs = strParmeter.split("#", -1);
        Element data = doc.createElement("Data");
        Element tmp = setValueChild(doc, "Ma_DV", objs[0] == null ? "" : objs[0].toString());
        data.appendChild(tmp);
        tmp = setValueChild(doc, "Nam_DK", objs[1] == null ? "" : objs[1].toString());
        data.appendChild(tmp);
        tmp = setValueChild(doc, "So_TK", objs[2] == null ? "" : objs[2].toString());
        data.appendChild(tmp);
        return data;
    }

    private Element getDataM103XML(String strParmeter, Document doc) {

        String[] objs = strParmeter.split("#", -1);
        Element data = doc.createElement("Data");
        Element tmp = setValueChild(doc, "So_HS", objs[0] == null ? "" : objs[0].toString());
        data.appendChild(tmp);
        tmp = setValueChild(doc, "Ma_DVQL", objs[1] == null ? "" : objs[1].toString());
        data.appendChild(tmp);
        tmp = setValueChild(doc, "KyHieu_CT", objs[2] == null ? "" : objs[2].toString());
        data.appendChild(tmp);
        tmp = setValueChild(doc, "So_CT", objs[3] == null ? "" : objs[3].toString());
        data.appendChild(tmp);
        tmp = setValueChild(doc, "Nam_CT", objs[4] == null ? "" : objs[4].toString());
        data.appendChild(tmp);
        return data;
    }

    private Element getDataM104XML(String strParmeter, Document doc) {
        String[] objs = strParmeter.split("#", -1);

        Element data = doc.createElement("Data");
        Element tmp = setValueChild(doc, "Ma_DV", objs[0] == null ? "" : objs[0].toString());
        data.appendChild(tmp);
        tmp = setValueChild(doc, "KyHieu_CT", objs[1] == null ? "" : objs[1].toString());
        data.appendChild(tmp);
        tmp = setValueChild(doc, "So_CT", objs[2] == null ? "" : objs[2].toString());
        data.appendChild(tmp);
        return data;
    }

    private Element getDataM106XML(String strParmeter, Document doc) {
        String[] objs = strParmeter.split("#", -1);
        Element data = doc.createElement("Data");
        Element tmp = setValueChild(doc, "Loai_DM", objs[0] == null ? "" : objs[0].toString());
        data.appendChild(tmp);
        tmp = setValueChild(doc, "Ma_DV", objs[1] == null ? "" : objs[1].toString());
        data.appendChild(tmp);

        return data;
    }

    private Element getDataM107XML(String strParmeter, Document doc) {
        String[] objs = strParmeter.split("#", -1);
        String Transaction_Req = objs[0].toString();
        Element data = doc.createElement("Data");
        Element tmp = setValueChild(doc, "Transaction_Req", Transaction_Req);
        data.appendChild(tmp);
        return data;
    }

    private Element getHeaderXML(String msgType, String requestid, String TransactionID, Document doc) throws RemoteException {

        AppVersion = "3.0";
        //add for 247
        msgType = msgType.substring(0, 3);
        if (msgType.equalsIgnoreCase("213") || msgType.equalsIgnoreCase("312") || msgType.equalsIgnoreCase("807") || msgType.equalsIgnoreCase("314") || msgType.equalsIgnoreCase("108")) {
            AppVersion = AppVersion247;
        }
        Element hdr = null;

        hdr = doc.createElement("Header");
        Element tmp = setValueChild(doc, "Application_Name", AppName);
        hdr.appendChild(tmp);

        tmp = setValueChild(doc, "Application_Version", AppVersion);
        hdr.appendChild(tmp);
        tmp = setValueChild(doc, "Sender_Code", SendCode);
        hdr.appendChild(tmp);
        tmp = setValueChild(doc, "Sender_Name", SendName);
        hdr.appendChild(tmp);
        tmp = setValueChild(doc, "Message_Version", MsgVersion);
        hdr.appendChild(tmp);

        tmp = setValueChild(doc, "Message_Type", msgType);
        hdr.appendChild(tmp);
        tmp = setValueChild(doc, "Message_Name", getMsgName(msgType));
        hdr.appendChild(tmp);
        tmp = setValueChild(doc, "Transaction_Date", getNowDate());
        hdr.appendChild(tmp);
        tmp = setValueChild(doc, "Transaction_ID", TransactionID);
        hdr.appendChild(tmp);
        if (msgType.equals("301") || msgType.equals("302")
                || msgType.equals("303")
                || msgType.equals("401")
                || msgType.equals("402")
                || msgType.equals("403")
                || msgType.equals("501")
                || msgType.equals("502")
                || msgType.equals("503")
                || msgType.equals("504")
                || msgType.equals("505")
                || msgType.equals("506")
                || msgType.equals("800")
                || msgType.equals("801")
                || msgType.equals("802")
                || msgType.equals("803")
                || msgType.equals("804")
                || msgType.equals("805")
                || msgType.equals("806")
                || msgType.equals("807")
                || msgType.equals("900")
                || msgType.equals("901")
                || msgType.equals("902")
                || msgType.equals("903")
                || msgType.equals("904")
                || msgType.equals("905")
                || msgType.equals("906")
                || msgType.equals("213")
                || msgType.equals("312")
                || msgType.equals("200")
                || msgType.equals("299")
                || msgType.equals("314")
                || msgType.equals("108")) {
            tmp = setValueChild(doc, "Request_ID", requestid == null ? "" : requestid);
            hdr.appendChild(tmp);
        }
        return hdr;
    }

    private Element setValueChild(Document doc, String tagName, String tagValue) {
        Element tmp = doc.createElement(tagName);
        tmp.appendChild(doc.createTextNode(tagValue));
        return tmp;
    }

    private String getNowDate() {
        String result = "";
        Calendar cld = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        result = sdf.format(cld.getTime());
        return result;
    }

    private String getTransID() throws RemoteException {
        return Helper.getDBI().HQ_getTransID();
    }

    /**
     *
     * @param msgType
     * @param strParams
     * @return
     * @throws Exception
     */
    public String getMessageQueryXML(String msgType, String strParams) throws Exception {//strParams: pram1#pram2#...
        try {
            Helper.writeLogErrorNonDB(HQController.class, "msg type:" + msgType + " params: " + strParams);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;
            Document doc = null;
            String message = "";
            String sign = "";
            Element data = null;
            try {
                db = dbf.newDocumentBuilder();
                doc = db.newDocument();

                Element customs = doc.createElement("Customs");
                String TransactionID = getTransID();
                Element header = getHeaderXML(msgType, "", TransactionID, doc);
                customs.appendChild(header);

                if (msgType.equals("101") || msgType.equals("102") || msgType.equals("105")) {
                    data = getDataM101XML(strParams, doc);
                } else if (msgType.equals("103")) {
                    data = getDataM103XML(strParams, doc);
                } else if (msgType.equals("104")) {
                    data = getDataM104XML(strParams, doc);
                } else if (msgType.equals("106")) //strParams : Loai danh muc HQ/KB/...
                {
                    data = getDataM106XML(strParams, doc);
                } else if (msgType.equals("107")) {
                    data = getDataM107XML(strParams, doc);
                }

                customs.appendChild(data);
                doc.appendChild(customs);
                doc = getXmlSigner(doc);

                message = DoctoString(doc);

            } catch (ParserConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String publicKey = XMLSigner.getPublickey(keyStoreFile, keyStorePwd);
            String response = HQProcess.callWS(publicKey, message);
            Helper.writeLogError(HQController.class, "request:" + message);
            Helper.writeLogError(HQController.class, "response:" + response);
            if (msgType.equals("106")) {

                InsertDanhMuc(response);
            }
            return response;
        } catch (Exception ex) {
            Helper.writeLogErrorNonDB(HQController.class, ex.getMessage());
            return "";
        }
    }

    private void InsertDanhMuc(String Data) throws RemoteException, ParserConfigurationException, SAXException, IOException {
        Document doc2 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(Data)));
        NodeList ListHeader2 = doc2.getElementsByTagName("Header");
        String msgtype2 = ((Element) ListHeader2.item(0)).getElementsByTagName("Message_Type").item(0).getTextContent();
        NodeList listData2 = doc2.getElementsByTagName("Item");
        if (msgtype2.equals("208")) {
            for (int i = 0; i < listData2.getLength(); i++) {
                Node nNodeGD = listData2.item(i);
                if (nNodeGD.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElementGD = (Element) nNodeGD;
                    String Ma_LH = eElementGD.getElementsByTagName("Ma_LH").item(0).getTextContent();
                    String Ten_LH = eElementGD.getElementsByTagName("Ten_LH").item(0).getTextContent();
                    String SN_AH = eElementGD.getElementsByTagName("SN_AH").item(0).getTextContent();
                    Helper.getDBI().HQ_insertDM_LH(Ma_LH, Ten_LH, SN_AH);
                }
            }
        } else {
            if (msgtype2.equals("209")) {
                for (int i = 0; i < listData2.getLength(); i++) {
                    Node nNodeGD = listData2.item(i);
                    if (nNodeGD.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementGD = (Element) nNodeGD;
                        String Ma_HQ = eElementGD.getElementsByTagName("Ma_HQ").item(0).getTextContent();
                        String Ten_HQ = eElementGD.getElementsByTagName("Ten_HQ").item(0).getTextContent();
                        String Ma_Cu = eElementGD.getElementsByTagName("Ma_Cu").item(0).getTextContent();
                        String Ma_QHNS = eElementGD.getElementsByTagName("Ma_QHNS").item(0).getTextContent();
                        Helper.getDBI().HQ_insertDM_HQ(Ma_HQ, Ten_HQ, Ma_Cu, Ma_QHNS);
                    }
                }

            } else {
                if (msgtype2.equals("211")) {
                    for (int i = 0; i < listData2.getLength(); i++) {
                        Node nNodeGD = listData2.item(i);
                        if (nNodeGD.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElementGD = (Element) nNodeGD;
                            String Ma_Loi = eElementGD.getElementsByTagName("Ma_Loi").item(0).getTextContent();
                            String Ten_Loi = eElementGD.getElementsByTagName("Ten_Loi").item(0).getTextContent();
                            Helper.getDBI().HQ_insertDM_ER(Ma_Loi, Ten_Loi);
                        }
                    }

                }
            }
        }
        if (msgtype2.equals("210")) {
            for (int i = 0; i < listData2.getLength(); i++) {
                Node nNodeGD = listData2.item(i);
                if (nNodeGD.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElementGD = (Element) nNodeGD;
                    String Ma_KB = eElementGD.getElementsByTagName("Ma_KB").item(0).getTextContent();
                    String Ten_KB = eElementGD.getElementsByTagName("Ten_KB").item(0).getTextContent();
                    Helper.getDBI().HQ_insertDM_KB(Ma_KB, Ten_KB);
                }
            }

        }
    }

    private String DoctoString(Document xmldom) {
        String resull = null;
        try {
            xmldom.setXmlStandalone(true);
            DOMSource domSource = new DOMSource(xmldom);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            /*
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no"); 
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
             */
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
            resull = sw.toString();
            return resull;
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            return resull;
        } catch (TransformerException ex) {
            ex.printStackTrace();
            return resull;
        } catch (Exception ex) {
            ex.printStackTrace();
            return resull;
        }
    }

    /**
     *
     * @param doc
     * @return
     * @throws Exception
     */
    public Document getXmlSigner(Document doc) throws Exception {
        try {
            XMLSigner abc = new XMLSigner();
            IHTKKKeyStoreBean keyStore = IHTKKKeyStoreBean.getKeyStore(keyStoreFile, keyStorePwd);
            byte[] b = null;
            String[] path = {"Customs/Data"};
            return abc.signXMLFileXPath(doc, keyStore.getPrivateKey(), keyStore.getCertChain(), path, null);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     *
     * @param doc
     * @return
     * @throws Exception
     */
    public Document getXmlSigner2(Document doc) throws Exception {
        try {
            XMLSigner abc = new XMLSigner();
            IHTKKKeyStoreBean keyStore = IHTKKKeyStoreBean.getKeyStore(keyStoreFile, keyStorePwd);
            byte[] b = null;
            String[] path = {"Customs"};
            return abc.signXMLFileXPath(doc, keyStore.getPrivateKey(), keyStore.getCertChain(), path, "DigitalSignatures");
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     *
     * @param msgType
     * @param strID
     * @return
     * @throws Exception
     */
    public String getMessageXML(String msgType, String strID) throws Exception {

        Helper.writeLogError(HQController.class, "msgType:" + msgType + " ID:" + strID);
        try {
            if (msgType.equals("312") || msgType.equals("314") || msgType.equals("108")) {
                return getMessageXML247(msgType, strID);
            }
            String msgResponse = "";
            if (msgType.equals("807")) {
                msgResponse = getMessageXML247DOICHIEU(msgType, strID);
            } else {

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db;
                Document doc = null;
                String message = "";
                String sign = "";
                Element data = null;
                db = dbf.newDocumentBuilder();
                doc = db.newDocument();

                Element customs = doc.createElement("Customs");
                String TransactionID = getTransID();
                Element header = getHeaderXML(msgType, getRequestID(msgType, strID), TransactionID, doc);
                customs.appendChild(header);
                String message2 = DoctoString(doc);
                if (msgType.equals("501") || msgType.equals("502") || msgType.equals("503") || msgType.equals("504") || msgType.equals("505") || msgType.equals("506")) {
                    //HUY GIAO DICH
                    //strID: SO_TN_CT
                    //Lay SO_TN_CT
                    String type = "";
                    if (msgType.equals("501")) {
                        type = "301";
                    } else {
                        if (msgType.equals("502")) {
                            type = "302";
                        } else {
                            if (msgType.equals("503")) {
                                type = "303";
                            } else {
                                if (msgType.equals("504")) {
                                    type = "401";
                                } else {
                                    if (msgType.equals("505")) {
                                        type = "402";
                                    } else {
                                        if (msgType.equals("506")) {
                                            type = "403";
                                        }
                                    }
                                }
                            }
                        }
                    }
                    String strSOTNCT = Helper.getDBI().HQ_getSO_TN_CT(strID, type);
                    data = getMessageHuy(strSOTNCT, doc);
                } else {
                    if (msgType.equals("801")) {
                        //GUI DOI CHIEU
                        //strID: DATE: yyyy-MM-dd
                        data = getMessageDoichieu801(strID, doc);

                    } else {
                        if (msgType.equals("802")) {
                            //strID: DATE: yyyy-MM-dd
                            data = getMessageDoichieu802(strID, doc);

                        } else {
                            if (msgType.equals("803")) {
                                //strID: DATE: yyyy-MM-dd
                                data = getMessageDoichieu803(strID, doc);
                            } else {
                                if (msgType.equals("804")) {
                                    //strID: DATE: yyyy-MM-dd
                                    data = getMessageDoichieu804(strID, doc);

                                } else {
                                    if (msgType.equals("805")) {
                                        //strID: DATE: yyyy-MM-dd
                                        data = getMessageDoichieu805(strID, doc);

                                    } else {
                                        if (msgType.equals("806")) {
                                            //strID: DATE: yyyy-MM-dd
                                            data = getMessageDoichieu806(strID, doc);

                                        } //Lay ket qua doi chieu
                                        else {
                                            if (msgType.equals("901") || msgType.equals("902") || msgType.equals("903") || msgType.equals("904") || msgType.equals("905") || msgType.equals("906")) {
                                                //GUI DOI CHIEU HUY
                                                //strID: DATE: yyyy-MM-dd
                                                data = getMessageDoichieuHuy(msgType, strID, doc);

                                                //Lay Ket qua doi chieu Huy
                                            } else {

                                                int ID = Integer.valueOf(strID);

                                                if (msgType.equals("301")) {
                                                    data = getDataM301XML(ID, doc);
                                                } else if (msgType.equals("302")) {
                                                    data = getDataM302XML(ID, doc);
                                                } else if (msgType.equals("303")) {
                                                    data = getDataM303XML(ID, doc);
                                                } else if (msgType.equals("401")) {
                                                    data = getDataM401XML(ID, doc);
                                                } else if (msgType.equals("402")) {
                                                    data = getDataM402XML(ID, doc);
                                                } else if (msgType.equals("403")) {
                                                    data = getDataM403XML(ID, doc);
                                                } else if (msgType.equals("213_311") || msgType.equals("213_304") || msgType.equals("213_305") || msgType.equals("213_201")) {
                                                    data = getDataM213XML(strID, doc, msgType);
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                customs.appendChild(data);
                Element Error = getErrorNode(doc);
                customs.appendChild(Error);
                doc.appendChild(customs);

                message = DoctoString(doc);
                doc = getXmlSigner(doc);
                message = DoctoString(doc);

                String publicKey = XMLSigner.getPublickey(keyStoreFile, keyStorePwd);
                Helper.writeLogError(HQController.class, "request:" + message);
                msgResponse = HQProcess.callWS247(publicKey, message);
            }
            Helper.writeLogError(HQController.class, "response:" + msgResponse);
            if (msgType.equals("801") || msgType.equals("802") || msgType.equals("803") || msgType.equals("804") || msgType.equals("805") || msgType.equals("806") || msgType.equals("807") || msgType.equals("901") || msgType.equals("902") || msgType.equals("903") || msgType.equals("904") || msgType.equals("905") || msgType.equals("906")) {
                return processCollated(msgType, msgResponse, strID);
            }
            return msgResponse;
        } catch (Exception ex) {
            return "";
        }
    }

    private String processCollated(String msgType, String msgResponse, String strID) throws ParserConfigurationException, SAXException, IOException, Exception {
        //Lay ket qua doi chieu

        Document doc2 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(msgResponse)));
        NodeList ListHeader2 = doc2.getElementsByTagName("Header");
        String msgtype2 = ((Element) ListHeader2.item(0)).getElementsByTagName("Message_Type").item(0).getTextContent();
        String status;
        String So_TN_CT = "";
        String Ngay_TN_CT = "";
        if (msgtype2.equals("299")) {
            status = "01";
        } else {
            status = "00";
            NodeList ListData2 = doc2.getElementsByTagName("Data");
            So_TN_CT = ((Element) ListData2.item(0)).getElementsByTagName("So_TN_CT").item(0).getTextContent();
            Ngay_TN_CT = ((Element) ListData2.item(0)).getElementsByTagName("Ngay_TN_CT").item(0).getTextContent();

        }
        NodeList Error2 = doc2.getElementsByTagName("Error");
        String ErrNumber = ((Element) Error2.item(0)).getElementsByTagName("ErrorNumber").item(0).getTextContent();
        String ErrMSG = ((Element) Error2.item(0)).getElementsByTagName("ErrorMessage").item(0).getTextContent();

        String MSGName = ((Element) ListHeader2.item(0)).getElementsByTagName("Message_Name").item(0).getTextContent();
        String Request_ID = ((Element) ListHeader2.item(0)).getElementsByTagName("Request_ID").item(0).getTextContent();
        String Transaction_ID = ((Element) ListHeader2.item(0)).getElementsByTagName("Transaction_ID").item(0).getTextContent();
        String Transaction_Date = ((Element) ListHeader2.item(0)).getElementsByTagName("Transaction_Date").item(0).getTextContent();
        String Send_Code = ((Element) ListHeader2.item(0)).getElementsByTagName("Sender_Code").item(0).getTextContent();
        String Sender_Name = ((Element) ListHeader2.item(0)).getElementsByTagName("Sender_Name").item(0).getTextContent();
        String Message_Version = ((Element) ListHeader2.item(0)).getElementsByTagName("Message_Version").item(0).getTextContent();

        HQ_MSG obj = new HQ_MSG();
        obj.setERRORMESSAGE(ErrMSG);
        obj.setERRORNUMBER(ErrNumber);
        obj.setID(0);
        obj.setMESSAGE_NAME(MSGName);
        obj.setMESSAGE_TYPE(msgType);
        obj.setREQUEST_ID(Request_ID);
        obj.setTRANSACTION_ID(Transaction_ID);
        obj.setTRANSACTION_DATE(Transaction_Date);
        obj.setSENDER_CODE(Send_Code);
        obj.setSENDER_NAME(Sender_Name);
        obj.setMESSAGE_VERSION(Message_Version);
        obj.setSO_TN_CT(So_TN_CT);
        obj.setNGAY_TN_CT(Ngay_TN_CT);
        obj.setSTATUS(status);
        Helper.getDBI().HQ_SEND_MESSAGE(obj);
        return status + "#" + msgType + "#" + strID + "#" + Request_ID;

    }

    private String getRequestID(String msgType, String ID) throws RemoteException {
        String kq = Helper.getDBI().HQ_getREQUESTID(ID, msgType);
        return kq == null ? "" : kq;
    }

    private Element getDataM301XML(int ID, Document doc) throws RemoteException, Exception {
        try {
            //for 101/102/105
            Element data = doc.createElement("Data");
            List list = Helper.getDBI().HQ_getDataNOPTIEN_HQ(ID, "06");
            if (list.size() > 0) {
                HQ_NOPTIEN_HQ obj = (HQ_NOPTIEN_HQ) list.get(0);

                Element tmp = setValueChild(doc, "Ma_NH_PH", SendCode);
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_PH", SendName);
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_NH_TH", obj.getMA_NH_TH() == null ? "" : obj.getMA_NH_TH().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_TH", obj.getTEN_NH_TH() == null ? "" : obj.getTEN_NH_TH().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_DV", obj.getMA_DV() == null ? "" : obj.getMA_DV().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_Chuong", obj.getMA_CHUONG() == null ? "" : obj.getMA_CHUONG().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV", obj.getTEN_DV() == null ? "" : obj.getTEN_DV().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_KB", obj.getMA_KB() == null ? "" : obj.getMA_KB().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_KB", obj.getTEN_KB() == null ? "" : obj.getTEN_KB().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "TKKB", obj.getTKKB() == null ? "" : obj.getTKKB().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_NTK", obj.getMA_NTK() == null ? "" : obj.getMA_NTK().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ_PH", obj.getMA_HQ_PH() == null ? "" : obj.getMA_HQ_PH().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ_CQT", obj.getMA_HQ_CQT() == null ? "" : obj.getMA_HQ_CQT().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "KyHieu_CT", obj.getKYHIEU_CT() == null ? "" : obj.getKYHIEU_CT().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT", obj.getSO_CT() == null ? "" : obj.getSO_CT().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Loai_CT", obj.getLOAI_CT() == null ? "" : obj.getLOAI_CT().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_TN_CTS", obj.getSo_TN_CTS() == null ? "" : obj.getSo_TN_CTS().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_TN_CTS", obj.getNgay_TN_CTS() == null ? "" : obj.getNgay_TN_CTS().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_BN", obj.getNGAY_BN() == null ? "" : obj.getNGAY_BN().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_BC", obj.getNGAY_BC() == null ? "" : obj.getNGAY_BC().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_CT", obj.getNGAY_CT() == null ? "" : obj.getNGAY_CT().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_NT", obj.getMA_NT() == null ? "" : obj.getMA_NT().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ty_Gia", obj.getTY_GIA() == null ? "" : obj.getTY_GIA().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "SoTien_TO", obj.getSOTIEN_TO() == null ? "" : obj.getSOTIEN_TO().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "DienGiai", obj.getDIENGIAI() == null ? "" : obj.getDIENGIAI().toString());
                data.appendChild(tmp);

                List arrlistGNT = Helper.getDBI().HQ_getDataGNT_CT(String.valueOf(ID));
                for (int i = 0; i < arrlistGNT.size(); i++) {
                    Element GNT_CT = doc.createElement("GNT_CT");
                    data.appendChild(GNT_CT);

                    HQ_NOPTIEN_HQ_GNT listGNT = (HQ_NOPTIEN_HQ_GNT) arrlistGNT.get(i);

                    int idGNT = listGNT.getID();

                    Element TTButToan = doc.createElement("TTButToan");
                    TTButToan.appendChild(doc.createTextNode(listGNT.getTTBUTTOAN()));
                    GNT_CT.appendChild(TTButToan);

                    Element Ma_HQ = doc.createElement("Ma_HQ");
                    Ma_HQ.appendChild(doc.createTextNode(listGNT.getMA_HQ()));
                    GNT_CT.appendChild(Ma_HQ);

                    Element Ma_LH = doc.createElement("Ma_LH");
                    Ma_LH.appendChild(doc.createTextNode(listGNT.getMA_LH()));
                    GNT_CT.appendChild(Ma_LH);

                    Element Nam_DK = doc.createElement("Nam_DK");
                    Nam_DK.appendChild(doc.createTextNode(listGNT.getNAM_DK()));
                    GNT_CT.appendChild(Nam_DK);

                    Element So_TK = doc.createElement("So_TK");
                    So_TK.appendChild(doc.createTextNode(listGNT.getSO_TK()));
                    GNT_CT.appendChild(So_TK);

                    Element Ma_LT = doc.createElement("Ma_LT");
                    Ma_LT.appendChild(doc.createTextNode(listGNT.getMA_LT()));
                    GNT_CT.appendChild(Ma_LT);

                    List arrlistGNTCT = Helper.getDBI().HQ_getDataNOPTIEN_HQ_GNTCT(idGNT, "00");
                    for (int j = 0; j < arrlistGNTCT.size(); j++) {
                        Element ToKhai_CT = doc.createElement("ToKhai_CT");
                        GNT_CT.appendChild(ToKhai_CT);

                        HQ_NOPTIEN_HQ_GNTCT listGNTCT = (HQ_NOPTIEN_HQ_GNTCT) arrlistGNTCT.get(j);

                        Element Ma_ST = doc.createElement("Ma_ST");
                        Ma_ST.appendChild(doc.createTextNode(listGNTCT.getMA_ST()));
                        ToKhai_CT.appendChild(Ma_ST);

                        Element NDKT = doc.createElement("NDKT");
                        NDKT.appendChild(doc.createTextNode(listGNTCT.getNDKT()));
                        ToKhai_CT.appendChild(NDKT);

                        Element SoTien_NT = doc.createElement("SoTien_NT");
                        SoTien_NT.appendChild(doc.createTextNode(listGNTCT.getSOTIEN_NT().toString()));
                        ToKhai_CT.appendChild(SoTien_NT);

                        Element SoTien_VND = doc.createElement("SoTien_VND");
                        SoTien_VND.appendChild(doc.createTextNode(listGNTCT.getSOTIEN_VND().toString()));
                        ToKhai_CT.appendChild(SoTien_VND);
                    }
                }

            }
            return data;
        } catch (Exception ex) {

            throw ex;
        }
    }

    private Element getDataM302XML(int ID, Document doc) throws RemoteException, Exception {
        try {
            //for 101/102/105
            Element data = doc.createElement("Data");
            List arrList = Helper.getDBI().HQ_getDataNOPTIEN_HQ(ID, "06");
            if (arrList.size() > 0) {
                HQ_NOPTIEN_HQ listData = (HQ_NOPTIEN_HQ) arrList.get(0);

                Element tmp = setValueChild(doc, "Ma_NH_PH", SendCode);
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_PH", SendName);
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_NH_TH", listData.getMA_NH_TH() == null ? "" : listData.getMA_NH_TH().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_TH", listData.getTEN_NH_TH() == null ? "" : listData.getTEN_NH_TH().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_DV", listData.getMA_DV() == null ? "" : listData.getMA_DV().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_Chuong", listData.getMA_CHUONG() == null ? "" : listData.getMA_CHUONG().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV", listData.getTEN_DV() == null ? "" : listData.getTEN_DV().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_KB", listData.getMA_KB() == null ? "" : listData.getMA_KB().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_KB", listData.getTEN_KB() == null ? "" : listData.getTEN_KB().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "TKKB", listData.getTKKB() == null ? "" : listData.getTKKB().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_NTK", listData.getMA_NTK() == null ? "" : listData.getMA_NTK().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ_PH", listData.getMA_HQ_PH() == null ? "" : listData.getMA_HQ_PH().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ_CQT", listData.getMA_HQ_CQT() == null ? "" : listData.getMA_HQ_CQT().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "KyHieu_CT", listData.getKYHIEU_CT() == null ? "" : listData.getKYHIEU_CT().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT", listData.getSO_CT() == null ? "" : listData.getSO_CT().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Loai_CT", listData.getLOAI_CT() == null ? "" : listData.getLOAI_CT().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_TN_CTS", listData.getSo_TN_CTS() == null ? "" : listData.getSo_TN_CTS().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_TN_CTS", listData.getNgay_TN_CTS() == null ? "" : listData.getNgay_TN_CTS().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_BN", listData.getNGAY_BN() == null ? "" : listData.getNGAY_BN().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_BC", listData.getNGAY_BC() == null ? "" : listData.getNGAY_BC().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_CT", listData.getNGAY_CT() == null ? "" : listData.getNGAY_CT().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "SoTien_TO", listData.getSOTIEN_TO() == null ? "" : listData.getSOTIEN_TO().toString());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "DienGiai", listData.getDIENGIAI() == null ? "" : listData.getDIENGIAI().toString());
                data.appendChild(tmp);

                List arrlistGNT = Helper.getDBI().HQ_getDataNOPTIEN_HQ_GNT(ID, "00");
                for (int i = 0; i < arrlistGNT.size(); i++) {
                    Element GNT_CT = doc.createElement("GNT_CT");
                    data.appendChild(GNT_CT);

                    HQ_NOPTIEN_HQ_GNT listGNT = (HQ_NOPTIEN_HQ_GNT) arrlistGNT.get(i);

                    int idGNT = listGNT.getID();

                    Element TTButToan = doc.createElement("TTButToan");
                    TTButToan.appendChild(doc.createTextNode(listGNT.getTTBUTTOAN()));
                    GNT_CT.appendChild(TTButToan);

                    Element Ma_HQ = doc.createElement("Ma_HQ");
                    Ma_HQ.appendChild(doc.createTextNode(listGNT.getMA_HQ()));
                    GNT_CT.appendChild(Ma_HQ);

                    Element Ma_LH = doc.createElement("Ma_LH");
                    Ma_LH.appendChild(doc.createTextNode(listGNT.getMA_LH()));
                    GNT_CT.appendChild(Ma_LH);

                    Element Nam_DK = doc.createElement("Nam_DK");
                    Nam_DK.appendChild(doc.createTextNode(listGNT.getNAM_DK()));
                    GNT_CT.appendChild(Nam_DK);

                    Element So_TK = doc.createElement("So_TK");
                    So_TK.appendChild(doc.createTextNode(listGNT.getSO_TK()));
                    GNT_CT.appendChild(So_TK);

                    Element Ma_LT = doc.createElement("Ma_LT");
                    Ma_LT.appendChild(doc.createTextNode(listGNT.getMA_LT()));
                    GNT_CT.appendChild(Ma_LT);

                    List arrlistGNTCT = Helper.getDBI().HQ_getDataNOPTIEN_HQ_GNTCT(idGNT, "00");
                    for (int j = 0; j < arrlistGNTCT.size(); j++) {
                        Element ToKhai_CT = doc.createElement("ToKhai_CT");
                        GNT_CT.appendChild(ToKhai_CT);

                        HQ_NOPTIEN_HQ_GNTCT listGNTCT = (HQ_NOPTIEN_HQ_GNTCT) arrlistGNTCT.get(j);

                        Element Ma_ST = doc.createElement("Ma_ST");
                        Ma_ST.appendChild(doc.createTextNode(listGNTCT.getMA_ST()));
                        ToKhai_CT.appendChild(Ma_ST);

                        Element NDKT = doc.createElement("NDKT");
                        NDKT.appendChild(doc.createTextNode(listGNTCT.getNDKT()));
                        ToKhai_CT.appendChild(NDKT);

                        Element SoTien_VND = doc.createElement("SoTien");
                        SoTien_VND.appendChild(doc.createTextNode(listGNTCT.getSOTIEN_VND().toString()));
                        ToKhai_CT.appendChild(SoTien_VND);
                    }
                }
            }
            return data;
        } catch (Exception ex) {

            throw ex;
        }
    }

    private Element getDataM303XML(int ID, Document doc) throws RemoteException, Exception {
        try {
            Element data = doc.createElement("Data");
            List arrList = Helper.getDBI().HQ_getDataNOPTIEN_HQ_CQQLT(ID, "06");
            if (arrList.size() > 0) {
                HQ_NOPTIEN_CQQLT listData = (HQ_NOPTIEN_CQQLT) arrList.get(0);

                Element tmp = setValueChild(doc, "Ma_NH_PH", SendCode);
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_PH", SendName);
                data.appendChild(tmp);
                tmp = setValueChild(doc, "KyHieu_CT", listData.getKyHieu_CT() == null ? "" : listData.getKyHieu_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT", listData.getSO_CT() == null ? "" : listData.getSO_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_CT", listData.getNGAY_CT() == null ? "" : listData.getNGAY_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_BN", listData.getNGAY_BN() == null ? "" : listData.getNGAY_BN());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_BC", listData.getNGAY_BC() == null ? "" : listData.getNGAY_BC());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_HS", listData.getSO_HS() == null ? "" : listData.getSO_HS());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_DVQL", listData.getMA_DVQL() == null ? "" : listData.getMA_DVQL());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DVQL", listData.getTEN_DVQL() == null ? "" : listData.getTEN_DVQL());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "KyHieu_CT_PT", listData.getKYHIEU_CT_PT() == null ? "" : listData.getKYHIEU_CT_PT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT_PT", listData.getSO_CT_PT() == null ? "" : listData.getSO_CT_PT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Nam_CT_PT", listData.getNAM_CT_PT() == null ? "" : listData.getNAM_CT_PT());
                data.appendChild(tmp);

                Element NguoiNT = doc.createElement("NguoiNopTien");
                data.appendChild(NguoiNT);

                tmp = setValueChild(doc, "Ma_ST", listData.getMA_ST() == null ? "" : listData.getMA_ST());
                NguoiNT.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NNT", listData.getTEN_DV() == null ? "" : listData.getTEN_DV());
                NguoiNT.appendChild(tmp);
                tmp = setValueChild(doc, "DiaChi", listData.getDIACHI_DV() == null ? "" : listData.getDIACHI_DV());
                NguoiNT.appendChild(tmp);
                tmp = setValueChild(doc, "TT_Khac", listData.getTT_KHAC() == null ? "" : listData.getTT_KHAC());
                NguoiNT.appendChild(tmp);

                Element TTNT = doc.createElement("ThongTin_NopTien");
                data.appendChild(TTNT);

                tmp = setValueChild(doc, "Ma_NT", listData.getMA_NT() == null ? "" : listData.getMA_NT());
                TTNT.appendChild(tmp);
                tmp = setValueChild(doc, "TyGia", listData.getTYGIA() == null ? "" : listData.getTYGIA());
                TTNT.appendChild(tmp);
                tmp = setValueChild(doc, "TongTien_NT", listData.getTONGTIEN_NT() == null ? "" : listData.getTONGTIEN_NT().toString());
                TTNT.appendChild(tmp);
                tmp = setValueChild(doc, "TongTien_VND", listData.getTONGTIEN_VND() == null ? "" : listData.getTONGTIEN_VND().toString());
                TTNT.appendChild(tmp);
                List arrListCT = Helper.getDBI().HQ_getDataNOPTIEN_HQ_CQQLT_CT(ID, "");
                for (int i = 0; i < arrListCT.size(); i++) {
                    HQ_NOPTIEN_CQQLT_CT listCT = (HQ_NOPTIEN_CQQLT_CT) arrListCT.get(i);
                    Element CTCT = doc.createElement("ChungTu_CT");
                    data.appendChild(CTCT);

                    tmp = setValueChild(doc, "STT", listCT.getSTT() == null ? "" : listCT.getSTT());
                    CTCT.appendChild(tmp);

                    tmp = setValueChild(doc, "NDKT", listCT.getNDKT() == null ? "" : listCT.getNDKT());
                    CTCT.appendChild(tmp);

                    tmp = setValueChild(doc, "Ten_NDKT", listCT.getTEN_NDKT() == null ? "" : listCT.getTEN_NDKT());
                    CTCT.appendChild(tmp);

                    tmp = setValueChild(doc, "SoTien_NT", listCT.getSOTIEN_NT() == null ? "" : listCT.getSOTIEN_NT().toString());
                    CTCT.appendChild(tmp);

                    tmp = setValueChild(doc, "SoTien_VND", listCT.getSOTIEN_VND() == null ? "" : listCT.getSOTIEN_VND().toString());
                    CTCT.appendChild(tmp);

                    tmp = setValueChild(doc, "GhiChu", listCT.getGHICHU() == null ? "" : listCT.getGHICHU());
                    CTCT.appendChild(tmp);
                }
                Element TKNT = doc.createElement("TaiKhoan_NopTien");
                data.appendChild(TKNT);

                tmp = setValueChild(doc, "Ma_NH_TH", listData.getMA_NH_TH() == null ? "" : listData.getMA_NH_TH());
                TKNT.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_TH", listData.getTEN_NH_TH() == null ? "" : listData.getTEN_NH_TH().toString());
                TKNT.appendChild(tmp);
                tmp = setValueChild(doc, "TaiKhoan_TH", listData.getTAIKHOAN_TH() == null ? "" : listData.getTAIKHOAN_TH().toString());
                TKNT.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_TaiKhoan_TH", listData.getTEN_TAIKHOAN_TH() == null ? "" : listData.getTEN_TAIKHOAN_TH().toString());
                TKNT.appendChild(tmp);
            }
            return data;
        } catch (Exception ex) {

            throw ex;
        }
    }

    private Element getDataM401XML(int ID, Document doc) throws RemoteException, Exception {
        try {
            //for 101/102/105
            Element data = doc.createElement("Data");
            List arrList = Helper.getDBI().HQ_getDataBAOLANH_TK(ID, "03");
            if (arrList.size() > 0) {
                HQ_BAOLANH_TK listData = (HQ_BAOLANH_TK) arrList.get(0);

                Element tmp = setValueChild(doc, "Ma_NH_PH", SendCode);
                data.appendChild(tmp);
                tmp = setValueChild(doc, "MST_NH_PH", MST_NH_PH);
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_PH", SendName);
                data.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_DV", listData.getMA_DV() == null ? "" : listData.getMA_DV());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV", listData.getTEN_DV() == null ? "" : listData.getTEN_DV());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_DV_DD", listData.getMA_DV_DD() == null ? "" : listData.getMA_DV_DD());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV_DD", listData.getTEN_DV_DD() == null ? "" : listData.getTEN_DV_DD());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ_PH", listData.getMA_HQ_PH() == null ? "" : listData.getMA_HQ_PH());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ", listData.getMA_HQ() == null ? "" : listData.getMA_HQ());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_LH", listData.getMA_LH() == null ? "" : listData.getMA_LH());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_TK", listData.getSO_TK() == null ? "" : listData.getSO_TK());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_DK", listData.getNGAY_DK() == null ? "" : listData.getNGAY_DK());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_LT", listData.getMA_LT() == null ? "" : listData.getMA_LT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Loai_CT", listData.getLOAI_CT() == null ? "" : listData.getLOAI_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "KyHieu_CT", listData.getKYHIEU_CT() == null ? "" : listData.getKYHIEU_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT", listData.getSO_CT() == null ? "" : listData.getSO_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_CT", listData.getNGAY_CT() == null ? "" : listData.getNGAY_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "TTButToan", listData.getTTBUTTOAN() == null ? "" : listData.getTTBUTTOAN());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "SNBL", listData.getSNBL() == null ? "" : listData.getSNBL());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_HL", listData.getNGAY_HL() == null ? "" : listData.getNGAY_HL());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_HHL", listData.getNGAY_HHL() == null ? "" : listData.getNGAY_HHL());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "SoTien", listData.getSOTIEN() == null ? "" : listData.getSOTIEN());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "DienGiai", listData.getDIENGIAI() == null ? "" : listData.getDIENGIAI());
                data.appendChild(tmp);
            }
            return data;
        } catch (Exception ex) {

            throw ex;
        }
    }

    private Element getDataM402XML(int ID, Document doc) throws RemoteException, Exception {
        try {
            //for 101/102/105
            Element data = doc.createElement("Data");
            List arrList = Helper.getDBI().HQ_getDataBAOLANH_CHUNG(ID, "03");
            if (arrList.size() > 0) {
                HQ_BAOLANH_CHUNG listData = (HQ_BAOLANH_CHUNG) arrList.get(0);

                Element tmp = setValueChild(doc, "Ma_NH_PH", SendCode);
                data.appendChild(tmp);
                tmp = setValueChild(doc, "MST_NH_PH", MST_NH_PH);
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_PH", SendName);
                data.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_DV", listData.getMA_DV() == null ? "" : listData.getMA_DV());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV", listData.getTEN_DV() == null ? "" : listData.getTEN_DV());
                data.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_DV_DD", listData.getMA_DV_DD() == null ? "" : listData.getMA_DV_DD());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV_DD", listData.getTEN_DV_DD() == null ? "" : listData.getTEN_DV_DD());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Loai_CT", listData.getLOAI_CT() == null ? "" : listData.getLOAI_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "KyHieu_CT", listData.getKYHIEU_CT() == null ? "" : listData.getKYHIEU_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT", listData.getSO_CT() == null ? "" : listData.getSO_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_CT", listData.getNGAY_CT() == null ? "" : listData.getNGAY_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "TTButToan", listData.getTTBUTTOAN() == null ? "" : listData.getTTBUTTOAN());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_HL", listData.getNGAY_HL() == null ? "" : listData.getNGAY_HL());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_HHL", listData.getNGAY_HHL() == null ? "" : listData.getNGAY_HHL());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "SoTien", listData.getSOTIEN() == null ? "" : listData.getSOTIEN());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "DienGiai", listData.getDIENGIAI() == null ? "" : listData.getDIENGIAI());
                data.appendChild(tmp);
            }
            return data;
        } catch (Exception ex) {

            throw ex;
        }
    }

    private Element getDataM403XML(int ID, Document doc) throws RemoteException, Exception {
        try {
            //for 101/102/105
            Element data = doc.createElement("Data");
            List arrList = Helper.getDBI().HQ_getDataBAOLANH_HDVD(ID, "03");
            if (arrList.size() > 0) {
                HQ_BAOLANH_HDVD listData = (HQ_BAOLANH_HDVD) arrList.get(0);

                Element tmp = setValueChild(doc, "Ma_NH_PH", SendCode);
                data.appendChild(tmp);
                tmp = setValueChild(doc, "MST_NH_PH", MST_NH_PH);
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_PH", SendName);
                data.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_DV", listData.getMA_DV() == null ? "" : listData.getMA_DV());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV", listData.getTEN_DV() == null ? "" : listData.getTEN_DV());
                data.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_DV_DD", listData.getMA_DV_DD() == null ? "" : listData.getMA_DV_DD());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV_DD", listData.getTEN_DV_DD() == null ? "" : listData.getTEN_DV_DD());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ_KB", listData.getMA_HQ_KB() == null ? "" : listData.getMA_HQ_KB());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_HD", listData.getSO_HD() == null ? "" : listData.getSO_HD());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_HD", listData.getNGAY_HD() == null ? "" : listData.getNGAY_HD());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_VD_01", listData.getSO_VD_01() == null ? "" : listData.getSO_VD_01());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_VD_01", listData.getNGAY_VD_01() == null ? "" : listData.getNGAY_VD_01());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_VD_02", listData.getSO_VD_02() == null ? "" : listData.getSO_VD_02());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_VD_02", listData.getNGAY_VD_02() == null ? "" : listData.getNGAY_VD_02());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_VD_03", listData.getSO_VD_03() == null ? "" : listData.getSO_VD_03());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_VD_03", listData.getNGAY_VD_03() == null ? "" : listData.getNGAY_VD_03());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_VD_04", listData.getSO_VD_04() == null ? "" : listData.getSO_VD_04());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_VD_04", listData.getNGAY_VD_04() == null ? "" : listData.getNGAY_VD_04());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_VD_05", listData.getSO_VD_05() == null ? "" : listData.getSO_VD_05());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_VD_05", listData.getNGAY_VD_05() == null ? "" : listData.getNGAY_VD_05());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Loai_CT", listData.getLOAI_CT() == null ? "" : listData.getLOAI_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "KyHieu_CT", listData.getKYHIEU_CT() == null ? "" : listData.getKYHIEU_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT", listData.getSO_CT() == null ? "" : listData.getSO_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_CT", listData.getNGAY_CT() == null ? "" : listData.getNGAY_CT());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "TTButToan", listData.getTTBUTTOAN() == null ? "" : listData.getTTBUTTOAN());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "SNBL", listData.getSNBL() == null ? "" : listData.getSNBL());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_HL", listData.getNGAY_HL() == null ? "" : listData.getNGAY_HL());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_HHL", listData.getNGAY_HHL() == null ? "" : listData.getNGAY_HHL());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "SoTien", listData.getSOTIEN() == null ? "" : listData.getSOTIEN());
                data.appendChild(tmp);
                tmp = setValueChild(doc, "DienGiai", listData.getDIENGIAI() == null ? "" : listData.getDIENGIAI());
                data.appendChild(tmp);
            }
            return data;
        } catch (Exception ex) {

            throw ex;
        }
    }

    private Element getErrorNode(Document doc) {
        Element Error = doc.createElement("Error"); //Error_Message></Error
        Element tmp = setValueChild(doc, "ErrorMessage", "Xử lý thành công");
        Error.appendChild(tmp);
        tmp = setValueChild(doc, "ErrorNumber", "0");
        Error.appendChild(tmp);
        return Error;
    }

    private Element getMessageHuy(String So_TN_CT_YCH, Document doc) {
        Element Data = doc.createElement("Data"); //Error_Message></Error
        Element tmp = setValueChild(doc, "So_TN_CT_YCH", So_TN_CT_YCH);
        Data.appendChild(tmp);
        return Data;
    }

    private Element getMessageDoichieu801(String strDate, Document doc) throws Exception {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(strDate);

            Element data = doc.createElement("Data");
            List list = Helper.getDBI().HQ_getDataDoichieu("801", date);
            Element tmpM = setValueChild(doc, "Ma_NH_DC", SendCode);
            data.appendChild(tmpM);
            tmpM = setValueChild(doc, "Ngay_DC", strDate);
            data.appendChild(tmpM);
            for (int i = 0; i < list.size(); i++) {
                Element trans = doc.createElement("Transactions");
                data.appendChild(trans);

                HQ_DOICHIEU objDC = (HQ_DOICHIEU) list.get(i);
                HQ_NOPTIEN_HQ obj = (HQ_NOPTIEN_HQ) objDC.getObj801();
                Element tmp = setValueChild(doc, "Transaction_ID", objDC.getTransaction_id());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_TN_CT", objDC.getSO_TN_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_TN_CT", objDC.getNgay_TN_CT());
                trans.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_NH_PH", SendCode);
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_PH", SendName);
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_NH_TH", obj.getMA_NH_TH() == null ? "" : obj.getMA_NH_TH().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_TH", obj.getTEN_NH_TH() == null ? "" : obj.getTEN_NH_TH().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_DV", obj.getMA_DV() == null ? "" : obj.getMA_DV().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_Chuong", obj.getMA_CHUONG() == null ? "" : obj.getMA_CHUONG().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV", obj.getTEN_DV() == null ? "" : obj.getTEN_DV().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_KB", obj.getMA_KB() == null ? "" : obj.getMA_KB().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_KB", obj.getTEN_KB() == null ? "" : obj.getTEN_KB().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "TKKB", obj.getTKKB() == null ? "" : obj.getTKKB().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_NTK", obj.getMA_NTK() == null ? "" : obj.getMA_NTK().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ_PH", obj.getMA_HQ_PH() == null ? "" : obj.getMA_HQ_PH().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ_CQT", obj.getMA_HQ_CQT() == null ? "" : obj.getMA_HQ_CQT().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "KyHieu_CT", obj.getKYHIEU_CT() == null ? "" : obj.getKYHIEU_CT().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT", obj.getSO_CT() == null ? "" : obj.getSO_CT().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Loai_CT", obj.getLOAI_CT() == null ? "" : obj.getLOAI_CT().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_BN", obj.getNGAY_BN() == null ? "" : obj.getNGAY_BN().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_BC", obj.getNGAY_BC() == null ? "" : obj.getNGAY_BC().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_CT", obj.getNGAY_CT() == null ? "" : obj.getNGAY_CT().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_NT", obj.getMA_NT() == null ? "" : obj.getMA_NT().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ty_Gia", obj.getTY_GIA() == null ? "" : obj.getTY_GIA().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "SoTien_TO", obj.getSOTIEN_TO() == null ? "" : obj.getSOTIEN_TO().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "DienGiai", obj.getDIENGIAI() == null ? "" : obj.getDIENGIAI().toString());
                trans.appendChild(tmp);

                List arrlistGNT = Helper.getDBI().HQ_getDataNOPTIEN_HQ_GNT(obj.getID(), "00");
                for (int j = 0; j < arrlistGNT.size(); j++) {
                    Element GNT_CT = doc.createElement("GNT_CT");
                    trans.appendChild(GNT_CT);

                    HQ_NOPTIEN_HQ_GNT listGNT = (HQ_NOPTIEN_HQ_GNT) arrlistGNT.get(j);
                    int idGNT = listGNT.getID();

                    Element TTButToan = doc.createElement("TTButToan");
                    TTButToan.appendChild(doc.createTextNode(listGNT.getTTBUTTOAN()));
                    GNT_CT.appendChild(TTButToan);

                    Element Ma_HQ = doc.createElement("Ma_HQ");
                    Ma_HQ.appendChild(doc.createTextNode(listGNT.getMA_HQ()));
                    GNT_CT.appendChild(Ma_HQ);

                    Element Ma_LH = doc.createElement("Ma_LH");
                    Ma_LH.appendChild(doc.createTextNode(listGNT.getMA_LH()));
                    GNT_CT.appendChild(Ma_LH);

                    Element Nam_DK = doc.createElement("Nam_DK");
                    Nam_DK.appendChild(doc.createTextNode(listGNT.getNAM_DK()));
                    GNT_CT.appendChild(Nam_DK);

                    Element So_TK = doc.createElement("So_TK");
                    So_TK.appendChild(doc.createTextNode(listGNT.getSO_TK()));
                    GNT_CT.appendChild(So_TK);

                    Element Ma_LT = doc.createElement("Ma_LT");
                    Ma_LT.appendChild(doc.createTextNode(listGNT.getMA_LT()));
                    GNT_CT.appendChild(Ma_LT);

                    List arrlistGNTCT = Helper.getDBI().HQ_getDataNOPTIEN_HQ_GNTCT(idGNT, "00");
                    for (int k = 0; k < arrlistGNTCT.size(); k++) {
                        Element ToKhai_CT = doc.createElement("ToKhai_CT");
                        GNT_CT.appendChild(ToKhai_CT);

                        HQ_NOPTIEN_HQ_GNTCT listGNTCT = (HQ_NOPTIEN_HQ_GNTCT) arrlistGNTCT.get(k);

                        Element Ma_ST = doc.createElement("Ma_ST");
                        Ma_ST.appendChild(doc.createTextNode(listGNTCT.getMA_ST()));
                        ToKhai_CT.appendChild(Ma_ST);

                        Element NDKT = doc.createElement("NDKT");
                        NDKT.appendChild(doc.createTextNode(listGNTCT.getNDKT()));
                        ToKhai_CT.appendChild(NDKT);

                        Element SoTien_NT = doc.createElement("SoTien_NT");
                        SoTien_NT.appendChild(doc.createTextNode(listGNTCT.getSOTIEN_NT().toString()));
                        ToKhai_CT.appendChild(SoTien_NT);

                        Element SoTien_VND = doc.createElement("SoTien_VND");
                        SoTien_VND.appendChild(doc.createTextNode(listGNTCT.getSOTIEN_VND().toString()));
                        ToKhai_CT.appendChild(SoTien_VND);
                    }
                }
            }
            return data;
        } catch (Exception ex) {

            throw ex;
        }
    }

    private Element getMessageDoichieu802(String strDate, Document doc) throws Exception {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(strDate);

            Element data = doc.createElement("Data");
            List list = Helper.getDBI().HQ_getDataDoichieu("802", date);
            Element tmpM = setValueChild(doc, "Ma_NH_DC", SendCode);
            data.appendChild(tmpM);
            tmpM = setValueChild(doc, "Ngay_DC", strDate);
            data.appendChild(tmpM);
            for (int i = 0; i < list.size(); i++) {
                Element trans = doc.createElement("Transactions");
                data.appendChild(trans);

                HQ_DOICHIEU objDC = (HQ_DOICHIEU) list.get(i);
                HQ_NOPTIEN_HQ obj = (HQ_NOPTIEN_HQ) objDC.getObj801();
                Element tmp = setValueChild(doc, "Transaction_ID", objDC.getTransaction_id());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_TN_CT", objDC.getSO_TN_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_TN_CT", objDC.getNgay_TN_CT());
                trans.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_NH_PH", SendCode);
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_PH", SendName);
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_NH_TH", obj.getMA_NH_TH() == null ? "" : obj.getMA_NH_TH().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_TH", obj.getTEN_NH_TH() == null ? "" : obj.getTEN_NH_TH().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_DV", obj.getMA_DV() == null ? "" : obj.getMA_DV().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_Chuong", obj.getMA_CHUONG() == null ? "" : obj.getMA_CHUONG().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV", obj.getTEN_DV() == null ? "" : obj.getTEN_DV().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_KB", obj.getMA_KB() == null ? "" : obj.getMA_KB().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_KB", obj.getTEN_KB() == null ? "" : obj.getTEN_KB().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "TKKB", obj.getTKKB() == null ? "" : obj.getTKKB().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_NTK", obj.getMA_NTK() == null ? "" : obj.getMA_NTK().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ_PH", obj.getMA_HQ_PH() == null ? "" : obj.getMA_HQ_PH().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ_CQT", obj.getMA_HQ_CQT() == null ? "" : obj.getMA_HQ_CQT().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "KyHieu_CT", obj.getKYHIEU_CT() == null ? "" : obj.getKYHIEU_CT().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT", obj.getSO_CT() == null ? "" : obj.getSO_CT().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Loai_CT", obj.getLOAI_CT() == null ? "" : obj.getLOAI_CT().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_BN", obj.getNGAY_BN() == null ? "" : obj.getNGAY_BN().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_BC", obj.getNGAY_BC() == null ? "" : obj.getNGAY_BC().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_CT", obj.getNGAY_CT() == null ? "" : obj.getNGAY_CT().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "SoTien_TO", obj.getSOTIEN_TO() == null ? "" : obj.getSOTIEN_TO().toString());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "DienGiai", obj.getDIENGIAI() == null ? "" : obj.getDIENGIAI().toString());
                trans.appendChild(tmp);

                List arrlistGNT = Helper.getDBI().HQ_getDataNOPTIEN_HQ_GNT(obj.getID(), "00");
                for (int j = 0; j < arrlistGNT.size(); j++) {
                    Element GNT_CT = doc.createElement("GNT_CT");
                    trans.appendChild(GNT_CT);

                    HQ_NOPTIEN_HQ_GNT listGNT = (HQ_NOPTIEN_HQ_GNT) arrlistGNT.get(j);
                    int idGNT = listGNT.getID();

                    Element TTButToan = doc.createElement("TTButToan");
                    TTButToan.appendChild(doc.createTextNode(listGNT.getTTBUTTOAN()));
                    GNT_CT.appendChild(TTButToan);

                    Element Ma_HQ = doc.createElement("Ma_HQ");
                    Ma_HQ.appendChild(doc.createTextNode(listGNT.getMA_HQ()));
                    GNT_CT.appendChild(Ma_HQ);

                    Element Ma_LH = doc.createElement("Ma_LH");
                    Ma_LH.appendChild(doc.createTextNode(listGNT.getMA_LH()));
                    GNT_CT.appendChild(Ma_LH);

                    Element Nam_DK = doc.createElement("Nam_DK");
                    Nam_DK.appendChild(doc.createTextNode(listGNT.getNAM_DK()));
                    GNT_CT.appendChild(Nam_DK);

                    Element So_TK = doc.createElement("So_TK");
                    So_TK.appendChild(doc.createTextNode(listGNT.getSO_TK()));
                    GNT_CT.appendChild(So_TK);

                    Element Ma_LT = doc.createElement("Ma_LT");
                    Ma_LT.appendChild(doc.createTextNode(listGNT.getMA_LT()));
                    GNT_CT.appendChild(Ma_LT);

                    List arrlistGNTCT = Helper.getDBI().HQ_getDataNOPTIEN_HQ_GNTCT(idGNT, "00");
                    for (int k = 0; k < arrlistGNTCT.size(); k++) {
                        Element ToKhai_CT = doc.createElement("ToKhai_CT");
                        GNT_CT.appendChild(ToKhai_CT);

                        HQ_NOPTIEN_HQ_GNTCT listGNTCT = (HQ_NOPTIEN_HQ_GNTCT) arrlistGNTCT.get(k);

                        Element Ma_ST = doc.createElement("Ma_ST");
                        Ma_ST.appendChild(doc.createTextNode(listGNTCT.getMA_ST()));
                        ToKhai_CT.appendChild(Ma_ST);

                        Element NDKT = doc.createElement("NDKT");
                        NDKT.appendChild(doc.createTextNode(listGNTCT.getNDKT()));
                        ToKhai_CT.appendChild(NDKT);

                        Element SoTien_VND = doc.createElement("SoTien");
                        SoTien_VND.appendChild(doc.createTextNode(listGNTCT.getSOTIEN_VND().toString()));
                        ToKhai_CT.appendChild(SoTien_VND);
                    }
                }
            }
            return data;
        } catch (Exception ex) {

            throw ex;
        }
    }

    private Element getMessageDoichieu803(String strDate, Document doc) throws Exception {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(strDate);

            Element data = doc.createElement("Data");
            List list = Helper.getDBI().HQ_getDataDoichieu("803", date);
            Element tmpM = setValueChild(doc, "Ma_NH_DC", SendCode);
            data.appendChild(tmpM);
            tmpM = setValueChild(doc, "Ngay_DC", strDate);
            data.appendChild(tmpM);
            for (int i = 0; i < list.size(); i++) {
                Element trans = doc.createElement("Transactions");
                data.appendChild(trans);

                HQ_DOICHIEU objDC = (HQ_DOICHIEU) list.get(i);
                HQ_NOPTIEN_CQQLT obj = (HQ_NOPTIEN_CQQLT) objDC.getObj803();
                Element tmp = setValueChild(doc, "Transaction_ID", objDC.getTransaction_id());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_TN_CT", objDC.getSO_TN_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_TN_CT", objDC.getNgay_TN_CT());
                trans.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_NH_PH", SendCode);
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_PH", SendName);
                trans.appendChild(tmp);

                tmp = setValueChild(doc, "KyHieu_CT", obj.getKyHieu_CT() == null ? "" : obj.getKyHieu_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT", obj.getSO_CT() == null ? "" : obj.getSO_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_CT", obj.getNGAY_CT() == null ? "" : obj.getNGAY_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_BN", obj.getNGAY_BN() == null ? "" : obj.getNGAY_BN());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_BC", obj.getNGAY_BC() == null ? "" : obj.getNGAY_BC());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_HS", obj.getSO_HS() == null ? "" : obj.getSO_HS());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_DVQL", obj.getMA_DVQL() == null ? "" : obj.getMA_DVQL());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DVQL", obj.getTEN_DVQL() == null ? "" : obj.getTEN_DVQL());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "KyHieu_CT_PT", obj.getKYHIEU_CT_PT() == null ? "" : obj.getKYHIEU_CT_PT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT_PT", obj.getSO_CT_PT() == null ? "" : obj.getSO_CT_PT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Nam_CT_PT", obj.getNAM_CT_PT() == null ? "" : obj.getNAM_CT_PT());
                trans.appendChild(tmp);

                Element NguoiNT = doc.createElement("NguoiNopTien");
                trans.appendChild(NguoiNT);

                tmp = setValueChild(doc, "Ma_ST", obj.getMA_ST() == null ? "" : obj.getMA_ST());
                NguoiNT.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV", obj.getTEN_DV() == null ? "" : obj.getTEN_DV());
                NguoiNT.appendChild(tmp);
                tmp = setValueChild(doc, "DiaChi", obj.getDIACHI_DV() == null ? "" : obj.getDIACHI_DV());
                NguoiNT.appendChild(tmp);
                tmp = setValueChild(doc, "TT_Khac", obj.getTT_KHAC() == null ? "" : obj.getTT_KHAC());
                NguoiNT.appendChild(tmp);

                Element TTNT = doc.createElement("ThongTin_NopTien");
                trans.appendChild(TTNT);

                tmp = setValueChild(doc, "Ma_NT", obj.getMA_NT() == null ? "" : obj.getMA_NT());
                TTNT.appendChild(tmp);
                tmp = setValueChild(doc, "TyGia", obj.getTYGIA() == null ? "" : obj.getTYGIA());
                TTNT.appendChild(tmp);
                tmp = setValueChild(doc, "TongTien_NT", obj.getTONGTIEN_NT() == null ? "" : obj.getTONGTIEN_NT().toString());
                TTNT.appendChild(tmp);
                tmp = setValueChild(doc, "TongTien_VND", obj.getTONGTIEN_VND() == null ? "" : obj.getTONGTIEN_VND().toString());
                TTNT.appendChild(tmp);

                List arrListCT = Helper.getDBI().HQ_getDataNOPTIEN_HQ_CQQLT_CT(obj.getID(), "");
                for (int j = 0; j < arrListCT.size(); j++) {
                    HQ_NOPTIEN_CQQLT_CT listCT = (HQ_NOPTIEN_CQQLT_CT) arrListCT.get(j);
                    Element CTCT = doc.createElement("ChungTu_CT");
                    trans.appendChild(CTCT);

                    tmp = setValueChild(doc, "STT", listCT.getSTT() == null ? "" : listCT.getSTT());
                    CTCT.appendChild(tmp);

                    tmp = setValueChild(doc, "NDKT", listCT.getNDKT() == null ? "" : listCT.getNDKT());
                    CTCT.appendChild(tmp);

                    tmp = setValueChild(doc, "Ten_NDKT", listCT.getTEN_NDKT() == null ? "" : listCT.getTEN_NDKT());
                    CTCT.appendChild(tmp);

                    tmp = setValueChild(doc, "SoTien_NT", listCT.getSOTIEN_NT() == null ? "" : listCT.getSOTIEN_NT().toString());
                    CTCT.appendChild(tmp);

                    tmp = setValueChild(doc, "SoTien_VND", listCT.getSOTIEN_VND() == null ? "" : listCT.getSOTIEN_VND().toString());
                    CTCT.appendChild(tmp);

                    tmp = setValueChild(doc, "GhiChu", listCT.getGHICHU() == null ? "" : listCT.getGHICHU());
                    CTCT.appendChild(tmp);
                }

                Element TKNT = doc.createElement("TaiKhoan_NopTien");
                trans.appendChild(TKNT);

                tmp = setValueChild(doc, "Ma_NH_TH", obj.getMA_NH_TH() == null ? "" : obj.getMA_NH_TH());
                TKNT.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_TH", obj.getTEN_NH_TH() == null ? "" : obj.getTEN_NH_TH().toString());
                TKNT.appendChild(tmp);
                tmp = setValueChild(doc, "TaiKhoan_TH", obj.getTAIKHOAN_TH() == null ? "" : obj.getTAIKHOAN_TH().toString());
                TKNT.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_TaiKhoan_TH", obj.getTEN_TAIKHOAN_TH() == null ? "" : obj.getTEN_TAIKHOAN_TH().toString());
                TKNT.appendChild(tmp);
            }

            return data;
        } catch (Exception ex) {

            throw ex;
        }
    }

    private Element getMessageDoichieu804(String strDate, Document doc) throws Exception {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(strDate);

            Element data = doc.createElement("Data");
            List list = Helper.getDBI().HQ_getDataDoichieu("804", date);
            Element tmpM = setValueChild(doc, "Ma_NH_DC", SendCode);
            data.appendChild(tmpM);
            tmpM = setValueChild(doc, "Ngay_DC", strDate);
            data.appendChild(tmpM);
            for (int i = 0; i < list.size(); i++) {
                Element trans = doc.createElement("Transactions");
                data.appendChild(trans);

                HQ_DOICHIEU objDC = (HQ_DOICHIEU) list.get(i);
                HQ_BAOLANH_TK listData = (HQ_BAOLANH_TK) objDC.getObj804();
                Element tmp = setValueChild(doc, "Transaction_ID", objDC.getTransaction_id());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_TN_CT", objDC.getSO_TN_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_TN_CT", objDC.getNgay_TN_CT());
                trans.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_NH_PH", SendCode);
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "MST_NH_PH", MST_NH_PH);
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_PH", SendName);
                trans.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_DV", listData.getMA_DV() == null ? "" : listData.getMA_DV());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV", listData.getTEN_DV() == null ? "" : listData.getTEN_DV());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_DV_DD", listData.getMA_DV_DD() == null ? "" : listData.getMA_DV_DD());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV_DD", listData.getTEN_DV_DD() == null ? "" : listData.getTEN_DV_DD());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ_PH", listData.getMA_HQ_PH() == null ? "" : listData.getMA_HQ_PH());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ", listData.getMA_HQ() == null ? "" : listData.getMA_HQ());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_LH", listData.getMA_LH() == null ? "" : listData.getMA_LH());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_TK", listData.getSO_TK() == null ? "" : listData.getSO_TK());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_DK", listData.getNGAY_DK() == null ? "" : listData.getNGAY_DK());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_LT", listData.getMA_LT() == null ? "" : listData.getMA_LT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Loai_CT", listData.getLOAI_CT() == null ? "" : listData.getLOAI_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "KyHieu_CT", listData.getKYHIEU_CT() == null ? "" : listData.getKYHIEU_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT", listData.getSO_CT() == null ? "" : listData.getSO_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_CT", listData.getNGAY_CT() == null ? "" : listData.getNGAY_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "TTButToan", listData.getTTBUTTOAN() == null ? "" : listData.getTTBUTTOAN());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "SNBL", listData.getSNBL() == null ? "" : listData.getSNBL());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_HL", listData.getNGAY_HHL() == null ? "" : listData.getNGAY_HL());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_HHL", listData.getNGAY_HHL() == null ? "" : listData.getNGAY_HHL());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "SoTien", listData.getSOTIEN() == null ? "" : listData.getSOTIEN());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "DienGiai", listData.getDIENGIAI() == null ? "" : listData.getDIENGIAI());
                trans.appendChild(tmp);
            }

            return data;
        } catch (Exception ex) {

            throw ex;
        }
    }

    private Element getMessageDoichieu805(String strDate, Document doc) throws Exception {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(strDate);

            Element data = doc.createElement("Data");
            List list = Helper.getDBI().HQ_getDataDoichieu("805", date);
            Element tmpM = setValueChild(doc, "Ma_NH_DC", SendCode);
            data.appendChild(tmpM);
            tmpM = setValueChild(doc, "Ngay_DC", strDate);
            data.appendChild(tmpM);
            for (int i = 0; i < list.size(); i++) {
                Element trans = doc.createElement("Transactions");
                data.appendChild(trans);

                HQ_DOICHIEU objDC = (HQ_DOICHIEU) list.get(i);
                HQ_BAOLANH_CHUNG listData = (HQ_BAOLANH_CHUNG) objDC.getObj805();
                Element tmp = setValueChild(doc, "Transaction_ID", objDC.getTransaction_id());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_TN_CT", objDC.getSO_TN_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_TN_CT", objDC.getNgay_TN_CT());
                trans.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_NH_PH", SendCode);
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "MST_NH_PH", MST_NH_PH);
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_PH", SendName);
                trans.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_DV", listData.getMA_DV() == null ? "" : listData.getMA_DV());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV", listData.getTEN_DV() == null ? "" : listData.getTEN_DV());
                trans.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_DV_DD", listData.getMA_DV_DD() == null ? "" : listData.getMA_DV_DD());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV_DD", listData.getTEN_DV_DD() == null ? "" : listData.getTEN_DV_DD());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Loai_CT", listData.getLOAI_CT() == null ? "" : listData.getLOAI_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "KyHieu_CT", listData.getKYHIEU_CT() == null ? "" : listData.getKYHIEU_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT", listData.getSO_CT() == null ? "" : listData.getSO_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_CT", listData.getNGAY_CT() == null ? "" : listData.getNGAY_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "TTButToan", listData.getTTBUTTOAN() == null ? "" : listData.getTTBUTTOAN());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_HL", listData.getNGAY_HL() == null ? "" : listData.getNGAY_HL());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_HHL", listData.getNGAY_HHL() == null ? "" : listData.getNGAY_HHL());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "SoTien", listData.getSOTIEN() == null ? "" : listData.getSOTIEN());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "DienGiai", listData.getDIENGIAI() == null ? "" : listData.getDIENGIAI());
                trans.appendChild(tmp);
            }

            return data;
        } catch (Exception ex) {

            throw ex;
        }
    }

    private Element getMessageDoichieu806(String strDate, Document doc) throws Exception {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(strDate);

            Element data = doc.createElement("Data");
            List list = Helper.getDBI().HQ_getDataDoichieu("806", date);
            Element tmpM = setValueChild(doc, "Ma_NH_DC", SendCode);
            data.appendChild(tmpM);
            tmpM = setValueChild(doc, "Ngay_DC", strDate);
            data.appendChild(tmpM);
            for (int i = 0; i < list.size(); i++) {
                Element trans = doc.createElement("Transactions");
                data.appendChild(trans);

                HQ_DOICHIEU objDC = (HQ_DOICHIEU) list.get(i);
                HQ_BAOLANH_HDVD listData = (HQ_BAOLANH_HDVD) objDC.getObj806();
                Element tmp = setValueChild(doc, "Transaction_ID", objDC.getTransaction_id());

                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_TN_CT", objDC.getSO_TN_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_TN_CT", objDC.getNgay_TN_CT());
                trans.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_NH_PH", SendCode);
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "MST_NH_PH", MST_NH_PH);
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_PH", SendName);
                trans.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_DV", listData.getMA_DV() == null ? "" : listData.getMA_DV());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV", listData.getTEN_DV() == null ? "" : listData.getTEN_DV());
                trans.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_DV_DD", listData.getMA_DV_DD() == null ? "" : listData.getMA_DV_DD());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV_DD", listData.getTEN_DV_DD() == null ? "" : listData.getTEN_DV_DD());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ_KB", listData.getMA_HQ_KB() == null ? "" : listData.getMA_HQ_KB());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_HD", listData.getSO_HD() == null ? "" : listData.getSO_HD());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_HD", listData.getNGAY_HD() == null ? "" : listData.getNGAY_HD());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_VD_01", listData.getSO_VD_01() == null ? "" : listData.getSO_VD_01());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_VD_01", listData.getNGAY_VD_01() == null ? "" : listData.getNGAY_VD_01());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_VD_02", listData.getSO_VD_02() == null ? "" : listData.getSO_VD_02());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_VD_02", listData.getNGAY_VD_02() == null ? "" : listData.getNGAY_VD_02());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_VD_03", listData.getSO_VD_03() == null ? "" : listData.getSO_VD_03());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_VD_03", listData.getNGAY_VD_03() == null ? "" : listData.getNGAY_VD_03());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_VD_04", listData.getSO_VD_04() == null ? "" : listData.getSO_VD_04());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_VD_04", listData.getNGAY_VD_04() == null ? "" : listData.getNGAY_VD_04());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_VD_05", listData.getSO_VD_05() == null ? "" : listData.getSO_VD_05());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_VD_05", listData.getNGAY_VD_05() == null ? "" : listData.getNGAY_VD_05());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Loai_CT", listData.getLOAI_CT() == null ? "" : listData.getLOAI_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "KyHieu_CT", listData.getKYHIEU_CT() == null ? "" : listData.getKYHIEU_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT", listData.getSO_CT() == null ? "" : listData.getSO_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_CT", listData.getNGAY_CT() == null ? "" : listData.getNGAY_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "TTButToan", listData.getTTBUTTOAN() == null ? "" : listData.getTTBUTTOAN());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "SNBL", listData.getSNBL() == null ? "" : listData.getSNBL());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_HL", listData.getNGAY_HL() == null ? "" : listData.getNGAY_HL());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_HHL", listData.getNGAY_HHL() == null ? "" : listData.getNGAY_HHL());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "SoTien", listData.getSOTIEN() == null ? "" : listData.getSOTIEN());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "DienGiai", listData.getDIENGIAI() == null ? "" : listData.getDIENGIAI());
                trans.appendChild(tmp);
            }

            return data;
        } catch (Exception ex) {

            throw ex;
        }
    }

    private Element getMessageDoichieu807(String strDate, Document doc) throws Exception {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(strDate);

            Element data = doc.createElement("Data");
            List list = Helper.getDBI().HQ_getDataDoichieu("807", date);
            Element tmpM = setValueChild(doc, "Ma_NH_DC", SendCode);
            data.appendChild(tmpM);
            tmpM = setValueChild(doc, "Ngay_DC", strDate);
            data.appendChild(tmpM);
            for (int i = 0; i < list.size(); i++) {
                Element trans = doc.createElement("Transactions");
                data.appendChild(trans);

                HQ_DOICHIEU objDC = (HQ_DOICHIEU) list.get(i);
                HQ_NOPTIEN_HQ obj = (HQ_NOPTIEN_HQ) objDC.getObj801();

                Element tmp = setValueChild(doc, "Transaction_ID", objDC.getTransaction_id());
                trans.appendChild(tmp);

                Element ttct = doc.createElement("ThongTinChungTu");
                trans.appendChild(ttct);

                tmp = setValueChild(doc, "NgayLap_CT", obj.getNgayLap_CT() == null ? "" : obj.getNgayLap_CT().toString().substring(0, 10));
                ttct.appendChild(tmp);

                tmp = setValueChild(doc, "NgayTruyen_CT", obj.getNgayTruyen_CT() == null ? "" : obj.getNgayTruyen_CT().toString());
                ttct.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_DV", obj.getMA_DV() == null ? "" : obj.getMA_DV().toString());
                ttct.appendChild(tmp);

                tmp = setValueChild(doc, "Ma_Chuong", obj.getMA_CHUONG() == null ? "" : obj.getMA_CHUONG().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_DV", obj.getTEN_DV() == null ? "" : obj.getTEN_DV().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_KB", obj.getMA_KB() == null ? "" : obj.getMA_KB().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_KB", obj.getTEN_KB() == null ? "" : obj.getTEN_KB().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "TKKB", obj.getTKKB() == null ? "" : obj.getTKKB().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_NTK", obj.getMA_NTK() == null ? "" : obj.getMA_NTK().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ_PH", obj.getMA_HQ_PH() == null ? "" : obj.getMA_HQ_PH().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_HQ_CQT", obj.getMA_HQ_CQT() == null ? "" : obj.getMA_HQ_CQT().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "KyHieu_CT", obj.getKYHIEU_CT() == null ? "" : obj.getKYHIEU_CT().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "So_CT", obj.getSO_CT() == null ? "" : obj.getSO_CT().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "Loai_CT", obj.getLOAI_CT() == null ? "" : obj.getLOAI_CT().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_BN", obj.getNGAY_BN() == null ? "" : obj.getNGAY_BN().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_CT", obj.getNGAY_CT() == null ? "" : obj.getNGAY_CT().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "Ma_NT", obj.getMA_NT() == null ? "" : obj.getMA_NT().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "Ty_Gia", obj.getTY_GIA() == null ? "" : obj.getTY_GIA().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "SoTien_TO", obj.getSOTIEN_TO() == null ? "" : obj.getSOTIEN_TO().toString());
                ttct.appendChild(tmp);
                tmp = setValueChild(doc, "DienGiai", obj.getDIENGIAI() == null ? "" : obj.getDIENGIAI().toString());
                ttct.appendChild(tmp);

                List arrlistGNT = Helper.getDBI().HQ_getDataGNT_CT(String.valueOf(obj.getID()));
                for (int j = 0; j < arrlistGNT.size(); j++) {
                    Element GNT_CT = doc.createElement("GNT_CT");
                    ttct.appendChild(GNT_CT);

                    HQ_NOPTIEN_HQ_GNT TK = (HQ_NOPTIEN_HQ_GNT) arrlistGNT.get(j);
                    int idGNT = TK.getID();

                    tmp = setValueChild(doc, "ID_HS", TK.getID_HS() == null ? "" : TK.getID_HS());
                    GNT_CT.appendChild(tmp);

                    tmp = setValueChild(doc, "TTButToan", TK.getTTBUTTOAN() == null ? "" : TK.getTTBUTTOAN());
                    GNT_CT.appendChild(tmp);

                    tmp = setValueChild(doc, "Ma_HQ", TK.getMA_HQ() == null ? "" : TK.getMA_HQ());
                    GNT_CT.appendChild(tmp);

                    tmp = setValueChild(doc, "Ma_LH", TK.getMA_LH() == null ? "" : TK.getMA_LH());
                    GNT_CT.appendChild(tmp);

                    tmp = setValueChild(doc, "Nam_DK", TK.getNAM_DK() == null ? "" : TK.getNAM_DK());
                    GNT_CT.appendChild(tmp);

                    tmp = setValueChild(doc, "So_TK", TK.getSO_TK() == null ? "" : TK.getSO_TK());
                    GNT_CT.appendChild(tmp);

                    tmp = setValueChild(doc, "Ma_LT", TK.getMA_LT() == null ? "" : TK.getMA_LT());
                    GNT_CT.appendChild(tmp);

                    List arrlistGNTCT = Helper.getDBI().HQ_getDataNOPTIEN_HQ_GNTCT(idGNT, "00");
                    for (int k = 0; k < arrlistGNTCT.size(); k++) {
                        Element ToKhai_CT = doc.createElement("ToKhai_CT");
                        GNT_CT.appendChild(ToKhai_CT);

                        HQ_NOPTIEN_HQ_GNTCT TKCT = (HQ_NOPTIEN_HQ_GNTCT) arrlistGNTCT.get(k);

                        tmp = setValueChild(doc, "Ma_ST", TKCT.getMA_ST() == null ? "" : TKCT.getMA_ST());
                        ToKhai_CT.appendChild(tmp);

                        tmp = setValueChild(doc, "NDKT", TKCT.getNDKT() == null ? "" : TKCT.getNDKT());
                        ToKhai_CT.appendChild(tmp);

                        tmp = setValueChild(doc, "SoTien_NT", TKCT.getSOTIEN_NT() == null ? "" : TKCT.getSOTIEN_NT());
                        ToKhai_CT.appendChild(tmp);

                        tmp = setValueChild(doc, "SoTien_VND", TKCT.getSOTIEN_VND() == null ? "" : TKCT.getSOTIEN_VND());
                        ToKhai_CT.appendChild(tmp);

                    }

                }

                Element ttgd = doc.createElement("ThongTinGiaoDich");
                trans.appendChild(ttgd);

                Element ttnt = doc.createElement("NguoiNopTien");
                ttgd.appendChild(ttnt);

                tmp = setValueChild(doc, "Ma_ST", obj.getMa_ST() == null ? "" : obj.getMa_ST().toString());
                ttnt.appendChild(tmp);
                tmp = setValueChild(doc, "So_CMT", obj.getSo_CMT() == null ? "" : obj.getSo_CMT().toString());
                ttnt.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NNT", obj.getTen_NNT() == null ? "" : obj.getTen_NNT().toString());
                ttnt.appendChild(tmp);
                tmp = setValueChild(doc, "DiaChi", obj.getDiaChi() == null ? "" : obj.getDiaChi().toString());
                ttnt.appendChild(tmp);
                tmp = setValueChild(doc, "TT_Khac", obj.getTT_Khac() == null ? "" : obj.getTT_Khac().toString());
                ttnt.appendChild(tmp);

                Element tknt = doc.createElement("TaiKhoan_NopTien");
                ttgd.appendChild(tknt);

                tmp = setValueChild(doc, "Ma_NH_TH", obj.getMA_NH_TH() == null ? "" : obj.getMA_NH_TH().toString());
                tknt.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_NH_TH", obj.getTEN_NH_TH() == null ? "" : obj.getTEN_NH_TH().toString());
                tknt.appendChild(tmp);
                tmp = setValueChild(doc, "TaiKhoan_TH", obj.getTaiKhoan_TH() == null ? "" : obj.getTaiKhoan_TH().toString());
                tknt.appendChild(tmp);
                tmp = setValueChild(doc, "Ten_TaiKhoan_TH", obj.getTen_TaiKhoan_TH() == null ? "" : obj.getTen_TaiKhoan_TH().toString());
                tknt.appendChild(tmp);
            }
            Element Error = getErrorNode(doc);
            data.appendChild(Error);
            return data;
        } catch (Exception ex) {

            throw ex;
        }
    }

    /**
     *
     * @param msgType
     * @param strDate
     * @param requestID
     * @return
     * @throws Exception
     */
    public String getKQDOICHIEU(String msgType, String strDate, String requestID) throws Exception {
        try {

            //msgType:801/802/803/804/805/806/901/902/903/904/905/906
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;
            Document doc = null;
            String message = "";
            String sign = "";
            db = dbf.newDocumentBuilder();
            doc = db.newDocument();
            Element customs = doc.createElement("Customs");
            String TransactionID = getTransID();
            Element header;

            if (msgType.substring(0, 1).equals("8")) {
                header = getHeaderXML("800", requestID, TransactionID, doc);
            } else {
                header = getHeaderXML("900", requestID, TransactionID, doc);
            }
            customs.appendChild(header);
            Element Data = doc.createElement("Data"); //Error_Message></Error
            Element tmp = setValueChild(doc, "Ma_NH_DC", SendCode);
            Data.appendChild(tmp);
            tmp = setValueChild(doc, "Ngay_DC", strDate);
            Data.appendChild(tmp);
            String LoaiTDDC = msgType.substring(1);
            tmp = setValueChild(doc, "Loai_TD_DC", LoaiTDDC);
            Data.appendChild(tmp);
            customs.appendChild(Data);

            Element Error = getErrorNode(doc);
            customs.appendChild(Error);
            doc.appendChild(customs);
            doc = getXmlSigner(doc);
            message = DoctoString(doc);

            String publicKey = XMLSigner.getPublickey(keyStoreFile, keyStorePwd);
            Helper.writeLogError(HQController.class, "request2:" + message);
            String msgResponse = HQProcess.callWS247DC(publicKey, message);
            Helper.writeLogError(HQController.class, "response2:" + msgResponse);
            if (msgType.substring(0, 1).equals("8")) {
                UpdateDoiChieu(msgResponse, strDate);
            } else {
                UpdateDoiChieuHuy(msgResponse, strDate);
            }
            return "0";

        } catch (Exception ex) {
            Helper.writeLogError(HQController.class, "ex:" + ex.getMessage());
            return "1";
        }
    }

    private void UpdateDoiChieu(String strXML, String strDate) throws Exception {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            NodeList ListGD = doc.getElementsByTagName("Transactions");
            NodeList ListHeader = doc.getElementsByTagName("Header");
            String msgtype = ((Element) ListHeader.item(0)).getElementsByTagName("Message_Type").item(0).getTextContent();
            for (int i = 0; i < ListGD.getLength(); i++) {
                Node nNodeGD = ListGD.item(i);
                if (nNodeGD.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElementGD = (Element) nNodeGD;
                    String Transaction_ID = eElementGD.getElementsByTagName("Transaction_ID").item(0).getTextContent();
                    String KQ_DC = eElementGD.getElementsByTagName("KQ_DC").item(0).getTextContent();
                    if (KQ_DC.equals("")) {
                        KQ_DC = "00";
                    }
                    if (KQ_DC.equals("21")) {
                        //HQ NHIEU HON
                        //insert du lieu;
                        if (msgtype.equals("851") || msgtype.equals("852")) {
                            //ket qua doi chieu thue
                            HQ_NOPTIEN_HQ obj = new HQ_NOPTIEN_HQ();
                            obj.setMA_NH_TH(eElementGD.getElementsByTagName("Ma_NH_TH").item(0).getTextContent());
                            obj.setTEN_NH_TH(eElementGD.getElementsByTagName("Ten_NH_TH").item(0).getTextContent());
                            obj.setMA_DV(eElementGD.getElementsByTagName("Ma_DV").item(0).getTextContent());
                            obj.setMA_CHUONG(eElementGD.getElementsByTagName("Ma_Chuong").item(0).getTextContent());
                            obj.setTEN_DV(eElementGD.getElementsByTagName("Ten_DV").item(0).getTextContent());
                            obj.setMA_KB(eElementGD.getElementsByTagName("Ma_KB").item(0).getTextContent());
                            obj.setTEN_KB(eElementGD.getElementsByTagName("Ten_KB").item(0).getTextContent());
                            obj.setTKKB(eElementGD.getElementsByTagName("TKKB").item(0).getTextContent());
                            obj.setMA_NTK(eElementGD.getElementsByTagName("Ma_NTK").item(0).getTextContent());
                            //obj.setMA_HQ_PH(eElementGD.getElementsByTagName("Ma_HQ_PH").item(0).getTextContent());                
                            obj.setMA_HQ_CQT(eElementGD.getElementsByTagName("Ma_HQ_CQT").item(0).getTextContent());
                            obj.setKYHIEU_CT(eElementGD.getElementsByTagName("KyHieu_CT").item(0).getTextContent());
                            obj.setSO_CT(eElementGD.getElementsByTagName("So_CT").item(0).getTextContent());
                            obj.setLOAI_CT(eElementGD.getElementsByTagName("Loai_CT").item(0).getTextContent());
                            obj.setNGAY_BN(eElementGD.getElementsByTagName("Ngay_BN").item(0).getTextContent());
                            obj.setNGAY_BC(eElementGD.getElementsByTagName("Ngay_BC").item(0).getTextContent());
                            obj.setNGAY_CT(eElementGD.getElementsByTagName("Ngay_CT").item(0).getTextContent());
                            if (msgtype.equals("851")) {
                                obj.setMA_NT(eElementGD.getElementsByTagName("Ma_NT").item(0).getTextContent());
                                obj.setTY_GIA(eElementGD.getElementsByTagName("Ty_Gia").item(0).getTextContent());
                            }
                            obj.setSOTIEN_TO(eElementGD.getElementsByTagName("SoTien_TO").item(0).getTextContent());
                            obj.setDIENGIAI(eElementGD.getElementsByTagName("DienGiai").item(0).getTextContent());
                            obj.setMAKERID("TCHQ");
                            String[] objResut = Helper.getDBI().HQ_INSERT_NOPTIEN_HQ(obj);
                            int ID = Integer.valueOf(objResut[2].toString());
                            Helper.getDBI().HQ_InsertKQDoiChieu(msgtype, objResut[2].toString(),
                                    strDate, KQ_DC, Transaction_ID);
                            //insert GNT_CT
                            NodeList ListGNT = eElementGD.getElementsByTagName("GNT_CT");
                            for (int j = 0; j < ListGNT.getLength(); j++) {
                                Node nNodeCT = ListGNT.item(i);
                                if (nNodeCT.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElementGNT = (Element) nNodeCT;
                                    HQ_NOPTIEN_HQ_GNT objGNT = new HQ_NOPTIEN_HQ_GNT();
                                    objGNT.setMA_HQ(eElementGNT.getElementsByTagName("MA_HQ").item(0).getTextContent());
                                    objGNT.setTTBUTTOAN(eElementGNT.getElementsByTagName("TTBUTTOAN").item(0).getTextContent());
                                    objGNT.setMA_LH(eElementGNT.getElementsByTagName("MA_LH").item(0).getTextContent());
                                    objGNT.setNAM_DK(eElementGNT.getElementsByTagName("NAM_DK").item(0).getTextContent());
                                    objGNT.setSO_TK(eElementGNT.getElementsByTagName("SO_TK").item(0).getTextContent());
                                    objGNT.setMA_LT(eElementGNT.getElementsByTagName("MA_LT(").item(0).getTextContent());
                                    objGNT.setIDMASTER(ID);
                                    String[] objGNTResut = Helper.getDBI().HQ_INSERT_HQ_NOPTIEN_HQ_GNT(objGNT);
                                    int IDGNT = Integer.valueOf(objGNTResut[2].toString());
                                    NodeList ListGNTCT = eElementGNT.getElementsByTagName("ToKhai_CT");
                                    for (int k = 0; j < ListGNTCT.getLength(); k++) {
                                        Node nNodeGNTCT = ListGNTCT.item(i);
                                        if (nNodeGNTCT.getNodeType() == Node.ELEMENT_NODE) {
                                            Element eElementGNTCT = (Element) nNodeGNTCT;
                                            HQ_NOPTIEN_HQ_GNTCT objGNTCT = new HQ_NOPTIEN_HQ_GNTCT();
                                            objGNTCT.setIDMASTER(IDGNT);
                                            objGNTCT.setMA_ST(eElementGNTCT.getElementsByTagName("Ma_ST").item(0).getTextContent());
                                            objGNTCT.setNDKT(eElementGNTCT.getElementsByTagName("NDKT").item(0).getTextContent());
                                            if (msgtype.equals("851")) {
                                                objGNTCT.setSOTIEN_NT(eElementGNTCT.getElementsByTagName("SoTien_NT").item(0).getTextContent());
                                                objGNTCT.setSOTIEN_VND(eElementGNTCT.getElementsByTagName("SoTien_VND").item(0).getTextContent());
                                            } else {
                                                objGNTCT.setSOTIEN_VND(eElementGNTCT.getElementsByTagName("SoTien").item(0).getTextContent());
                                                objGNTCT.setSOTIEN_NT(eElementGNTCT.getElementsByTagName("SoTien").item(0).getTextContent());
                                            }
                                            Helper.getDBI().HQ_INSERT_HQ_NOPTIEN_HQ_GNTCT(objGNTCT);
                                        }
                                    }
                                }
                            }
                        } else {
                            if (msgtype.equals("853")) {
                                HQ_NOPTIEN_CQQLT obj = new HQ_NOPTIEN_CQQLT();
                                obj.setKyHieu_CT(eElementGD.getElementsByTagName("KyHieu_CT ").item(0).getTextContent());
                                obj.setSO_CT(eElementGD.getElementsByTagName("So_CT").item(0).getTextContent());
                                obj.setNGAY_CT(eElementGD.getElementsByTagName("Ngay_CT").item(0).getTextContent());
                                obj.setNGAY_BN(eElementGD.getElementsByTagName("Ngay_BN").item(0).getTextContent());
                                obj.setNGAY_BC(eElementGD.getElementsByTagName("Ngay_BC").item(0).getTextContent());
                                obj.setSO_HS(eElementGD.getElementsByTagName("So_HS").item(0).getTextContent());
                                obj.setMA_DVQL(eElementGD.getElementsByTagName("Ma_DVQL").item(0).getTextContent());
                                obj.setTEN_DVQL(eElementGD.getElementsByTagName("Ten_DVQL").item(0).getTextContent());
                                obj.setKYHIEU_CT_PT(eElementGD.getElementsByTagName("KyHieu_CT_PT").item(0).getTextContent());
                                obj.setSO_CT_PT(eElementGD.getElementsByTagName("So_CT_PT").item(0).getTextContent());
                                obj.setNAM_CT_PT(eElementGD.getElementsByTagName("Nam_CT_PT").item(0).getTextContent());

                                NodeList ListNNT = eElementGD.getElementsByTagName("NguoiNopTien");
                                Node nNodeNNT = ListNNT.item(0);
                                Element eElementNNT = (Element) nNodeNNT;
                                obj.setMA_ST(eElementNNT.getElementsByTagName("Ma_ST").item(0).getTextContent());
                                obj.setTEN_DV(eElementNNT.getElementsByTagName("Ten_DV").item(0).getTextContent());
                                obj.setTT_KHAC(eElementNNT.getElementsByTagName("TT_Khac").item(0).getTextContent());
                                obj.setDIACHI_DV(eElementNNT.getElementsByTagName("DiaChi").item(0).getTextContent());
                                obj.setMAKERID("TCHQ");
                                NodeList ListTTNNT = eElementGD.getElementsByTagName("ThongTin_NopTien");
                                Node nNodeTTNNT = ListTTNNT.item(0);
                                Element eElementTTNNT = (Element) nNodeTTNNT;
                                obj.setMA_NT(eElementTTNNT.getElementsByTagName("Ma_NT").item(0).getTextContent());
                                obj.setTYGIA(eElementTTNNT.getElementsByTagName("TyGia").item(0).getTextContent());
                                obj.setTONGTIEN_NT(eElementTTNNT.getElementsByTagName("TongTien_NT").item(0).getTextContent());
                                obj.setTONGTIEN_VND(eElementTTNNT.getElementsByTagName("TongTien_VND").item(0).getTextContent());

                                NodeList ListTKNT = eElementGD.getElementsByTagName("TaiKhoan_NopTien");
                                Node nNodeTKNT = ListTKNT.item(0);
                                Element eElementTKNT = (Element) nNodeTKNT;
                                obj.setMA_NH_TH(eElementTKNT.getElementsByTagName("Ma_NH_TH").item(0).getTextContent());
                                obj.setTEN_NH_TH(eElementTKNT.getElementsByTagName("Ten_NH_TH").item(0).getTextContent());
                                obj.setTAIKHOAN_TH(eElementTKNT.getElementsByTagName("TaiKhoan_TH").item(0).getTextContent());
                                obj.setTEN_TAIKHOAN_TH(eElementTKNT.getElementsByTagName("Ten_TaiKhoan_TH").item(0).getTextContent());

                                String[] objResut = Helper.getDBI().HQ_INSERT_HQ_NOPTIEN_CQQLT(obj);
                                int ID = Integer.valueOf(objResut[2].toString());
                                Helper.getDBI().HQ_InsertKQDoiChieu(msgtype, objResut[2].toString(),
                                        strDate, KQ_DC, Transaction_ID);

                                NodeList ListCTCT = eElementGD.getElementsByTagName("ChungTu_CT");
                                for (int j = 0; j < ListCTCT.getLength(); j++) {
                                    Node nNodeCTCT = ListCTCT.item(i);
                                    if (nNodeCTCT.getNodeType() == Node.ELEMENT_NODE) {
                                        Element eElementCT = (Element) nNodeCTCT;
                                        HQ_NOPTIEN_CQQLT_CT objCT = new HQ_NOPTIEN_CQQLT_CT();
                                        objCT.setIDMASTER(ID);
                                        objCT.setSTT(eElementCT.getElementsByTagName("STT").item(0).getTextContent());
                                        objCT.setNDKT(eElementCT.getElementsByTagName("NDKT").item(0).getTextContent());
                                        objCT.setTEN_NDKT(eElementCT.getElementsByTagName("Ten_NDKT").item(0).getTextContent());
                                        objCT.setSOTIEN_NT(eElementCT.getElementsByTagName("SoTien_NT").item(0).getTextContent());
                                        objCT.setSOTIEN_VND(eElementCT.getElementsByTagName("SoTien_VND").item(0).getTextContent());
                                        objCT.setGHICHU(eElementCT.getElementsByTagName("GhiChu").item(0).getTextContent());
                                        Helper.getDBI().HQ_INSERT_HQ_NOPTIEN_CQQLT_CT(objCT);
                                    }
                                }

                            } else {
                                if (msgtype.equals("854")) {
                                    HQ_BAOLANH_TK obj = new HQ_BAOLANH_TK();
                                    obj.setMA_DV(eElementGD.getElementsByTagName("Ma_DV").item(0).getTextContent());
                                    obj.setTEN_DV(eElementGD.getElementsByTagName("Ten_DV").item(0).getTextContent());
                                    obj.setMA_DV_DD(eElementGD.getElementsByTagName("Ma_DV_DD").item(0).getTextContent());
                                    obj.setTEN_DV_DD(eElementGD.getElementsByTagName("Ten_DV_DD").item(0).getTextContent());
                                    obj.setMA_HQ_PH(eElementGD.getElementsByTagName("Ma_HQ_PH").item(0).getTextContent());
                                    obj.setMA_HQ(eElementGD.getElementsByTagName("Ma_HQ").item(0).getTextContent());
                                    obj.setMA_LH(eElementGD.getElementsByTagName("Ma_LH").item(0).getTextContent());
                                    obj.setSO_TK(eElementGD.getElementsByTagName("So_TK").item(0).getTextContent());
                                    obj.setNGAY_DK(eElementGD.getElementsByTagName("Ngay_DK").item(0).getTextContent());
                                    obj.setMA_LT(eElementGD.getElementsByTagName("Ma_LT").item(0).getTextContent());
                                    obj.setLOAI_CT(eElementGD.getElementsByTagName("Loai_CT").item(0).getTextContent());
                                    obj.setKYHIEU_CT(eElementGD.getElementsByTagName("KyHieu_CT").item(0).getTextContent());
                                    obj.setSO_CT(eElementGD.getElementsByTagName("So_CT").item(0).getTextContent());
                                    obj.setNGAY_CT(eElementGD.getElementsByTagName("Ngay_CT").item(0).getTextContent());
                                    obj.setTTBUTTOAN(eElementGD.getElementsByTagName("TTButToan").item(0).getTextContent());
                                    obj.setSNBL(eElementGD.getElementsByTagName("SNBL").item(0).getTextContent());
                                    obj.setNGAY_HL(eElementGD.getElementsByTagName("Ngay_HL").item(0).getTextContent());
                                    obj.setNGAY_HHL(eElementGD.getElementsByTagName("Ngay_HHL").item(0).getTextContent());
                                    obj.setSOTIEN(eElementGD.getElementsByTagName("SoTien").item(0).getTextContent());
                                    obj.setDIENGIAI(eElementGD.getElementsByTagName("DienGiai").item(0).getTextContent());
                                    obj.setMAKERID("TCHQ");
                                    String[] objResut = Helper.getDBI().HQ_INSERT_HQ_BAOLANH_TK(obj);
                                    Helper.getDBI().HQ_InsertKQDoiChieu(msgtype, objResut[2].toString(),
                                            strDate, KQ_DC, Transaction_ID);
                                } else {
                                    if (msgtype.equals("855")) {
                                        HQ_BAOLANH_CHUNG obj = new HQ_BAOLANH_CHUNG();
                                        obj.setMA_DV(eElementGD.getElementsByTagName("Ma_DV").item(0).getTextContent());
                                        obj.setTEN_DV(eElementGD.getElementsByTagName("Ten_DV").item(0).getTextContent());
                                        obj.setMA_DV_DD(eElementGD.getElementsByTagName("Ma_DV_DD").item(0).getTextContent());
                                        obj.setTEN_DV_DD(eElementGD.getElementsByTagName("Ten_DV_DD").item(0).getTextContent());
                                        obj.setLOAI_CT(eElementGD.getElementsByTagName("Loai_CT").item(0).getTextContent());
                                        obj.setKYHIEU_CT(eElementGD.getElementsByTagName("KyHieu_CT").item(0).getTextContent());
                                        obj.setSO_CT(eElementGD.getElementsByTagName("So_CT").item(0).getTextContent());
                                        obj.setNGAY_CT(eElementGD.getElementsByTagName("Ngay_CT").item(0).getTextContent());
                                        obj.setTTBUTTOAN(eElementGD.getElementsByTagName("TTButToan").item(0).getTextContent());
                                        obj.setNGAY_HL(eElementGD.getElementsByTagName("Ngay_HL").item(0).getTextContent());
                                        obj.setNGAY_HHL(eElementGD.getElementsByTagName("Ngay_HHL").item(0).getTextContent());
                                        obj.setSOTIEN(eElementGD.getElementsByTagName("SoTien").item(0).getTextContent());
                                        obj.setDIENGIAI(eElementGD.getElementsByTagName("DienGiai").item(0).getTextContent());
                                        obj.setMAKERID("TCHQ");
                                        String[] objResut = Helper.getDBI().HQ_INSERT_HQ_BAOLANH_CHUNG(obj);
                                        Helper.getDBI().HQ_InsertKQDoiChieu(msgtype, objResut[2].toString(),
                                                strDate, KQ_DC, Transaction_ID);
                                    } else {
                                        if (msgtype.equals("856")) {
                                            HQ_BAOLANH_HDVD obj = new HQ_BAOLANH_HDVD();
                                            obj.setMA_DV(eElementGD.getElementsByTagName("Ma_DV").item(0).getTextContent());
                                            obj.setTEN_DV(eElementGD.getElementsByTagName("Ten_DV").item(0).getTextContent());
                                            obj.setMA_DV_DD(eElementGD.getElementsByTagName("Ma_DV_DD").item(0).getTextContent());
                                            obj.setTEN_DV_DD(eElementGD.getElementsByTagName("Ten_DV_DD").item(0).getTextContent());
                                            obj.setMA_HQ_KB(eElementGD.getElementsByTagName("Ma_HQ_KB").item(0).getTextContent());
                                            obj.setSO_HD(eElementGD.getElementsByTagName("So_HD").item(0).getTextContent());
                                            obj.setNGAY_HD(eElementGD.getElementsByTagName("Ngay_HD").item(0).getTextContent());
                                            obj.setSO_VD_01(eElementGD.getElementsByTagName("So_VD_01").item(0).getTextContent());
                                            obj.setNGAY_VD_01(eElementGD.getElementsByTagName("Ngay_VD_01").item(0).getTextContent());
                                            obj.setSO_VD_02(eElementGD.getElementsByTagName("So_VD_02").item(0).getTextContent());
                                            obj.setNGAY_VD_02(eElementGD.getElementsByTagName("Ngay_VD_02").item(0).getTextContent());
                                            obj.setSO_VD_03(eElementGD.getElementsByTagName("So_VD_03").item(0).getTextContent());
                                            obj.setNGAY_VD_03(eElementGD.getElementsByTagName("Ngay_VD_03").item(0).getTextContent());
                                            obj.setSO_VD_04(eElementGD.getElementsByTagName("So_VD_04").item(0).getTextContent());
                                            obj.setNGAY_VD_04(eElementGD.getElementsByTagName("Ngay_VD_04").item(0).getTextContent());
                                            obj.setSO_VD_05(eElementGD.getElementsByTagName("So_VD_05").item(0).getTextContent());
                                            obj.setNGAY_VD_05(eElementGD.getElementsByTagName("Ngay_VD_05").item(0).getTextContent());
                                            obj.setLOAI_CT(eElementGD.getElementsByTagName("Loai_CT").item(0).getTextContent());
                                            obj.setKYHIEU_CT(eElementGD.getElementsByTagName("KyHieu_CT").item(0).getTextContent());
                                            obj.setSO_CT(eElementGD.getElementsByTagName("So_CT").item(0).getTextContent());
                                            obj.setNGAY_CT(eElementGD.getElementsByTagName("Ngay_CT").item(0).getTextContent());
                                            obj.setTTBUTTOAN(eElementGD.getElementsByTagName("TTButToan").item(0).getTextContent());
                                            obj.setSNBL(eElementGD.getElementsByTagName("SNBL").item(0).getTextContent());
                                            obj.setNGAY_HL(eElementGD.getElementsByTagName("Ngay_HL").item(0).getTextContent());
                                            obj.setNGAY_HHL(eElementGD.getElementsByTagName("Ngay_HHL").item(0).getTextContent());
                                            obj.setSOTIEN(eElementGD.getElementsByTagName("SoTien").item(0).getTextContent());
                                            obj.setDIENGIAI(eElementGD.getElementsByTagName("DienGiai").item(0).getTextContent());
                                            obj.setMAKERID("TCHQ");
                                            String[] objResut = Helper.getDBI().HQ_INSERT_HQ_BAOLANH_HDVD(obj);
                                            Helper.getDBI().HQ_InsertKQDoiChieu(msgtype, objResut[2].toString(),
                                                    strDate, KQ_DC, Transaction_ID);
                                        } else {
                                            if (msgtype.equals("857")) {

                                                HQ_NOPTIEN_HQ GNT = new HQ_NOPTIEN_HQ();
                                                GNT.setTransaction_ID(Transaction_ID);
                                                NodeList TTCT = eElementGD.getElementsByTagName("ThongTinChungTu");
                                                GNT.setNgayLap_CT(((Element) TTCT.item(0)).getElementsByTagName("NgayLap_CT").item(0).getTextContent());
                                                GNT.setNgayTruyen_CT(((Element) TTCT.item(0)).getElementsByTagName("NgayTruyen_CT").item(0).getTextContent());
                                                GNT.setMA_DV(((Element) TTCT.item(0)).getElementsByTagName("Ma_DV").item(0).getTextContent());
                                                GNT.setMA_CHUONG(((Element) TTCT.item(0)).getElementsByTagName("Ma_Chuong").item(0).getTextContent());
                                                GNT.setTEN_DV(((Element) TTCT.item(0)).getElementsByTagName("Ten_DV").item(0).getTextContent());
                                                GNT.setMA_KB(((Element) TTCT.item(0)).getElementsByTagName("Ma_KB").item(0).getTextContent());
                                                GNT.setTEN_KB(((Element) TTCT.item(0)).getElementsByTagName("Ten_KB").item(0).getTextContent());
                                                GNT.setTKKB(((Element) TTCT.item(0)).getElementsByTagName("TKKB").item(0).getTextContent());
                                                GNT.setMA_NTK(((Element) TTCT.item(0)).getElementsByTagName("Ma_NTK").item(0).getTextContent());
                                                GNT.setMA_HQ_PH(((Element) TTCT.item(0)).getElementsByTagName("Ma_HQ_PH").item(0).getTextContent());
                                                GNT.setMA_HQ_CQT(((Element) TTCT.item(0)).getElementsByTagName("Ma_HQ_CQT").item(0).getTextContent());
                                                GNT.setKYHIEU_CT(((Element) TTCT.item(0)).getElementsByTagName("KyHieu_CT").item(0).getTextContent());
                                                GNT.setSO_CT(((Element) TTCT.item(0)).getElementsByTagName("So_CT").item(0).getTextContent());
                                                GNT.setLOAI_CT(((Element) TTCT.item(0)).getElementsByTagName("Loai_CT").item(0).getTextContent());
                                                GNT.setNGAY_BN(((Element) TTCT.item(0)).getElementsByTagName("Ngay_BN").item(0).getTextContent());
                                                GNT.setNGAY_CT(((Element) TTCT.item(0)).getElementsByTagName("Ngay_CT").item(0).getTextContent());
                                                GNT.setMA_NT(((Element) TTCT.item(0)).getElementsByTagName("Ma_NT").item(0).getTextContent());
                                                GNT.setTY_GIA(((Element) TTCT.item(0)).getElementsByTagName("Ty_Gia").item(0).getTextContent());
                                                GNT.setSOTIEN_TO(((Element) TTCT.item(0)).getElementsByTagName("SoTien_TO").item(0).getTextContent());
                                                GNT.setDIENGIAI(((Element) TTCT.item(0)).getElementsByTagName("DienGiai").item(0).getTextContent());

                                                NodeList TTGD = eElementGD.getElementsByTagName("ThongTinGiaoDich");

                                                NodeList NNT = ((Element) TTGD.item(0)).getElementsByTagName("NguoiNopTien");
                                                GNT.setMa_ST(((Element) NNT.item(0)).getElementsByTagName("Ma_ST").item(0).getTextContent());
                                                GNT.setSo_CMT(((Element) NNT.item(0)).getElementsByTagName("So_CMT").item(0).getTextContent());
                                                GNT.setTen_NNT(((Element) NNT.item(0)).getElementsByTagName("Ten_NNT").item(0).getTextContent());
                                                GNT.setDiaChi(((Element) NNT.item(0)).getElementsByTagName("DiaChi").item(0).getTextContent());
                                                GNT.setTT_Khac(((Element) NNT.item(0)).getElementsByTagName("TT_Khac").item(0).getTextContent());

                                                NodeList TKNT = ((Element) TTGD.item(0)).getElementsByTagName("TaiKhoan_NopTien");
                                                GNT.setMA_NH_TH(((Element) TKNT.item(0)).getElementsByTagName("Ma_NH_TH").item(0).getTextContent());
                                                GNT.setTEN_NH_TH(((Element) TKNT.item(0)).getElementsByTagName("Ten_NH_TH").item(0).getTextContent());
                                                GNT.setTaiKhoan_TH(((Element) TKNT.item(0)).getElementsByTagName("TaiKhoan_TH").item(0).getTextContent());
                                                GNT.setTen_TaiKhoan_TH(((Element) TKNT.item(0)).getElementsByTagName("Ten_TaiKhoan_TH").item(0).getTextContent());
                                                //Insert GNT
                                                String[] resultGNT = Helper.getDBI().HQ_INSERT_NOPTIEN_HQ_ONLINE(GNT);
                                                if (resultGNT[0].equalsIgnoreCase("00")) {
                                                    NodeList TK = ((Element) TTCT.item(0)).getElementsByTagName("GNT_CT");
                                                    for (int k = 0; k < TK.getLength(); k++) {
                                                        HQ_NOPTIEN_HQ_GNT TOKHAI = new HQ_NOPTIEN_HQ_GNT();
                                                        TOKHAI.setID_HS(((Element) TK.item(k)).getElementsByTagName("ID_HS").item(0).getTextContent());
                                                        TOKHAI.setTTBUTTOAN(((Element) TK.item(k)).getElementsByTagName("TTButToan").item(0).getTextContent());
                                                        TOKHAI.setMA_HQ(((Element) TK.item(k)).getElementsByTagName("Ma_HQ").item(0).getTextContent());
                                                        TOKHAI.setMA_LH(((Element) TK.item(k)).getElementsByTagName("Ma_LH").item(0).getTextContent());
                                                        TOKHAI.setNAM_DK(((Element) TK.item(k)).getElementsByTagName("Nam_DK").item(0).getTextContent());
                                                        TOKHAI.setSO_TK(((Element) TK.item(k)).getElementsByTagName("So_TK").item(0).getTextContent());
                                                        TOKHAI.setMA_LT(((Element) TK.item(k)).getElementsByTagName("Ma_LT").item(0).getTextContent());
                                                        NodeList TK_CT = ((Element) TK.item(k)).getElementsByTagName("ToKhai_CT");
                                                        TOKHAI.setIDMASTER(Integer.valueOf(resultGNT[2]));

                                                        String[] resultTK = Helper.getDBI().HQ_INSERT_GNT_ONLINE(TOKHAI);
                                                        for (int j = 0; j < TK_CT.getLength(); j++) {
                                                            HQ_NOPTIEN_HQ_GNTCT TOKHAICT = new HQ_NOPTIEN_HQ_GNTCT();
                                                            TOKHAICT.setMA_ST(((Element) TK_CT.item(j)).getElementsByTagName("Ma_ST").item(0).getTextContent());
                                                            TOKHAICT.setNDKT(((Element) TK_CT.item(j)).getElementsByTagName("NDKT").item(0).getTextContent());
                                                            TOKHAICT.setSOTIEN_NT(((Element) TK_CT.item(j)).getElementsByTagName("SoTien_NT").item(0).getTextContent());
                                                            TOKHAICT.setSOTIEN_VND(((Element) TK_CT.item(j)).getElementsByTagName("SoTien_VND").item(0).getTextContent());
                                                            TOKHAICT.setIDMASTER(Integer.valueOf(resultTK[0]));
                                                            Helper.getDBI().HQ_INSERT_HQ_NOPTIEN_HQ_GNTCT(TOKHAICT);
                                                        }
                                                    }
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Helper.getDBI().HQ_InsertKQDoiChieu(msgtype, "0",
                                strDate, KQ_DC, Transaction_ID);
                    }
                }
            }

        } catch (Exception ex) {
            throw ex;
        }

    }

    private Element getMessageDoichieuHuy(String msgType, String strDate, Document doc) throws Exception {
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dt.parse(strDate);

            Element data = doc.createElement("Data");

            Element tmpM = setValueChild(doc, "Ma_NH_DC", SendCode);
            data.appendChild(tmpM);
            tmpM = setValueChild(doc, "Ngay_DC", strDate);
            data.appendChild(tmpM);
            String loai_dc = msgType.substring(1);
            tmpM = setValueChild(doc, "Loai_TD_DC", loai_dc);
            data.appendChild(tmpM);
            Element transAccept = doc.createElement("Accept_Transactions");
            data.appendChild(transAccept);
            List list = Helper.getDBI().HQ_getDataDoichieu(msgType + "a", date);
            for (int i = 0; i < list.size(); i++) {
                Element trans = doc.createElement("Transaction");
                transAccept.appendChild(trans);

                HQ_DOICHIEU objDC = (HQ_DOICHIEU) list.get(i);
                Element tmp = setValueChild(doc, "Transaction_ID", objDC.getTransaction_id());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_TN_CT", objDC.getSO_TN_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_TN_CT", objDC.getNgay_TN_CT());
                trans.appendChild(tmp);
            }
            Element transReject = doc.createElement("Reject_Transactions");
            data.appendChild(transReject);
            List list2 = Helper.getDBI().HQ_getDataDoichieu(msgType + "r", date);
            for (int i = 0; i < list2.size(); i++) {
                Element trans = doc.createElement("Transaction");
                transReject.appendChild(trans);

                HQ_DOICHIEU objDC = (HQ_DOICHIEU) list2.get(i);
                Element tmp = setValueChild(doc, "Transaction_ID", objDC.getTransaction_id());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "So_TN_CT", objDC.getSO_TN_CT());
                trans.appendChild(tmp);
                tmp = setValueChild(doc, "Ngay_TN_CT", objDC.getNgay_TN_CT());
                trans.appendChild(tmp);
            }
            return data;
        } catch (Exception ex) {

            throw ex;
        }
    }

    private void UpdateDoiChieuHuy(String strXML, String strDate) throws Exception {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            NodeList ListAC = doc.getElementsByTagName("Accept_Transactions");
            NodeList ListRJ = doc.getElementsByTagName("Reject_Transactions");
            NodeList ListHeader = doc.getElementsByTagName("Header");
            String msgtype = ((Element) ListHeader.item(0)).getElementsByTagName("Message_Type").item(0).getTextContent();

            Node nodeTransAC = ListAC.item(0);
            Element eElementAC = (Element) nodeTransAC;

            NodeList listTransAC = eElementAC.getElementsByTagName("Transaction");
            for (int i = 0; i < listTransAC.getLength(); i++) {
                Node nNodeGDAC = listTransAC.item(i);
                if (nNodeGDAC.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElementGDAC = (Element) nNodeGDAC;
                    String Transaction_ID = eElementGDAC.getElementsByTagName("Transaction_ID").item(0).getTextContent();
                    String So_TN_CT = eElementGDAC.getElementsByTagName("So_TN_CT").item(0).getTextContent();
                    String Ngay_TN_CT = eElementGDAC.getElementsByTagName("Ngay_TN_CT").item(0).getTextContent();
                    String KQ_DC = eElementGDAC.getElementsByTagName("KQ_DC").item(0).getTextContent();
                    Helper.getDBI().HQ_insertKQDOICHIEUHUY(Transaction_ID, So_TN_CT, Ngay_TN_CT, KQ_DC, strDate, "ACCEPT", msgtype);
                }
            }
            Node nodeTransRJ = ListRJ.item(0);
            Element eElementRJ = (Element) nodeTransRJ;

            NodeList listTransRJ = eElementRJ.getElementsByTagName("Transaction");
            for (int i = 0; i < listTransRJ.getLength(); i++) {
                Node nNodeGDRJ = listTransRJ.item(i);
                if (nNodeGDRJ.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElementGDRJ = (Element) nNodeGDRJ;
                    String Transaction_ID = eElementGDRJ.getElementsByTagName("Transaction_ID").item(0).getTextContent();
                    String So_TN_CT = eElementGDRJ.getElementsByTagName("So_TN_CT").item(0).getTextContent();
                    String Ngay_TN_CT = eElementGDRJ.getElementsByTagName("Ngay_TN_CT").item(0).getTextContent();
                    String KQ_DC = eElementGDRJ.getElementsByTagName("KQ_DC").item(0).getTextContent();
                    Helper.getDBI().HQ_insertKQDOICHIEUHUY(Transaction_ID, So_TN_CT, Ngay_TN_CT, KQ_DC, strDate, "REJECT", msgtype);
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void pause1(long sleeptime) {
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException ex) {
            //ToCatchOrNot
        }
    }

    private void pause2(long sleeptime) {
        Object obj = new Object();
        if (sleeptime > 0) {
            synchronized (obj) {
                try {
                    obj.wait(sleeptime);
                } catch (InterruptedException ex) {
                    //ToCatchOrNot
                }
            }
        }
    }

    private void pause3(long sleeptime) {
        long expectedtime = System.currentTimeMillis() + sleeptime;
        while (System.currentTimeMillis() < expectedtime) {
            //Empty Loop   
        }
    }

    /**
     * ** THUE HAI QUAN ONLINE 24/7- 2017***
     */
    private Element getDataM213XML(String ID, Document doc, String Type) throws RemoteException, Exception {
        try {
            //GOI QUAN HQ
            //B2: SCB goi MSG 213 de chap nhan hoac tu choi dang ky  

            String[] listParam = Helper.getDBI().HQ_getData213(ID, Type);
            Element data = doc.createElement("Data");
            Element tmp = setValueChild(doc, "Loai_TD_TraLoi", Type.substring(4));
            data.appendChild(tmp);
            tmp = setValueChild(doc, "Ma_KQ_XL", listParam[0]);
            data.appendChild(tmp);
            tmp = setValueChild(doc, "NoiDung_XL", listParam[1]);
            data.appendChild(tmp);
            return data;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private Element getDataM312XML(String ID, Document doc) throws RemoteException, Exception {
        try {

            //GOI QUAN HQ
            //B3: SCB goi MSG 312 de thong bao dang ky thanh cong.
            List listParam = Helper.getDBI().HQ_getData312(ID);

            HQ_DKNNT obj = (HQ_DKNNT) listParam.get(0);

            Element data = doc.createElement("Data");
            Element tmp = setValueChild(doc, "So_HS", obj.getSo_HS() == null ? "" : obj.getSo_HS());
            data.appendChild(tmp);
            tmp = setValueChild(doc, "Loai_HS", obj.getLoai_HS() == null ? "" : obj.getLoai_HS());
            data.appendChild(tmp);
            tmp = setValueChild(doc, "Ma_DV", obj.getMa_DV() == null ? "" : obj.getMa_DV());
            data.appendChild(tmp);
            tmp = setValueChild(doc, "Ten_DV", obj.getTen_DV() == null ? "" : obj.getTen_DV());
            data.appendChild(tmp);

            tmp = setValueChild(doc, "DiaChi", obj.getDiaChi() == null ? "" : obj.getDiaChi());
            data.appendChild(tmp);

            tmp = setValueChild(doc, "Ngay_HL", obj.getNgay_HL_DK() == null ? "" : obj.getNgay_HL_DK());
            data.appendChild(tmp);

            Element TT_NNT = doc.createElement("ThongTin_NNT");
            data.appendChild(TT_NNT);

            tmp = setValueChild(doc, "So_CMT", obj.getSo_CMT() == null ? "" : obj.getSo_CMT());
            TT_NNT.appendChild(tmp);
            tmp = setValueChild(doc, "Ho_Ten", obj.getHo_Ten() == null ? "" : obj.getHo_Ten());
            TT_NNT.appendChild(tmp);
            tmp = setValueChild(doc, "NgaySinh", obj.getNgaySinh() == null ? "" : obj.getNgaySinh());
            TT_NNT.appendChild(tmp);
            tmp = setValueChild(doc, "NguyenQuan", obj.getNguyenQuan() == null ? "" : obj.getNguyenQuan());
            TT_NNT.appendChild(tmp);

            List listTTLH = obj.getThongTinLienHe();
            for (int i = 0; i < listTTLH.size(); i++) {
                Element TT_LIENHE = doc.createElement("ThongTinLienHe");
                TT_NNT.appendChild(TT_LIENHE);
                HQ_DKNNT_LIENHE objLH = (HQ_DKNNT_LIENHE) listTTLH.get(i);
                tmp = setValueChild(doc, "So_DT", objLH.getSO_DT() == null ? "" : objLH.getSO_DT());
                TT_LIENHE.appendChild(tmp);
                tmp = setValueChild(doc, "Email", objLH.getEMAIL() == null ? "" : objLH.getEMAIL());
                TT_LIENHE.appendChild(tmp);
            }

            Element CTS = doc.createElement("ChungThuSo");
            TT_NNT.appendChild(CTS);
            tmp = setValueChild(doc, "SerialNumber", obj.getSerialNumber() == null ? "" : obj.getSerialNumber());
            CTS.appendChild(tmp);
            tmp = setValueChild(doc, "Noi_Cap", obj.getNoi_Cap() == null ? "" : obj.getNoi_Cap());
            CTS.appendChild(tmp);
            tmp = setValueChild(doc, "Ngay_HL", obj.getNgay_HL() == null ? "" : obj.getNgay_HL());
            CTS.appendChild(tmp);
            tmp = setValueChild(doc, "Ngay_HHL", obj.getNgay_HHL() == null ? "" : obj.getNgay_HHL());
            CTS.appendChild(tmp);
            tmp = setValueChild(doc, "PublicKey", obj.getPublicKey() == null ? "" : obj.getPublicKey());
            CTS.appendChild(tmp);

            Element TTTK = doc.createElement("ThongTinTaiKhoan");
            TT_NNT.appendChild(TTTK);
            tmp = setValueChild(doc, "Ma_NH_TH", obj.getMa_NH_TH() == null ? "" : obj.getMa_NH_TH());
            TTTK.appendChild(tmp);
            tmp = setValueChild(doc, "Ten_NH_TH", obj.getTen_NH_TH() == null ? "" : obj.getTen_NH_TH());
            TTTK.appendChild(tmp);
            tmp = setValueChild(doc, "TaiKhoan_TH", obj.getTaiKhoan_TH() == null ? "" : obj.getTaiKhoan_TH());
            TTTK.appendChild(tmp);
            tmp = setValueChild(doc, "Ten_TaiKhoan_TH", obj.getTen_TaiKhoan_TH() == null ? "" : obj.getTen_TaiKhoan_TH());
            TTTK.appendChild(tmp);
            return data;
        } catch (Exception ex) {
            Helper.writeLogError(HQController.class, ex.getMessage());
            throw ex;
        }
    }

    private String getMessageXML247(String msgType, String strID) throws Exception {

        Helper.writeLogError(HQController.class, "msgType:" + msgType + " ID:" + strID);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;
            Document doc = null;
            String message = "";
            String sign = "";
            Element data = null;
            db = dbf.newDocumentBuilder();
            doc = db.newDocument();

            Element customs = doc.createElement("Customs");
            doc.appendChild(customs);

            Element document = doc.createElement("Document");
            customs.appendChild(document);

            String TransactionID = getTransID();
            Element header = getHeaderXML(msgType, getRequestID(msgType, strID), TransactionID, doc);
            document.appendChild(header);

            String message2 = DoctoString(doc);
            if (msgType.equals("312")) {
                data = getDataM312XML(strID, doc);
            }
            if (msgType.equals("314")) {
                data = getDataM314XML(strID, doc);
            } else if (msgType.equals("108")) {
                data = getDataM108XML(strID, doc);
            }
            document.appendChild(data);

            Element Error = getErrorNode(doc);
            document.appendChild(Error);

            Element DigitalSignatures = doc.createElement("DigitalSignatures");
            customs.appendChild(DigitalSignatures);

            doc = getXmlSigner2(doc);
            message = DoctoString(doc);

            String publicKey = XMLSigner.getPublickey(keyStoreFile, keyStorePwd);
            Helper.writeLogError(HQController.class, "request:" + message);
            String msgResponse = HQProcess.callWS247(publicKey, message);

            Helper.writeLogError(HQController.class, "response:" + msgResponse);
            return msgResponse;
        } catch (Exception ex) {
            Helper.writeLogError(HQController.class, ex.getMessage());
            return "";
        }
    }

    /**
     *
     * @param msgType
     * @param strID
     * @return
     * @throws Exception
     */
    public String getMessageXML247DOICHIEU(String msgType, String strID) throws Exception {

        Helper.writeLogError(HQController.class, "msgType:" + msgType + " ID:" + strID);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;
            Document doc = null;
            String message = "";
            String sign = "";
            Element data = null;
            db = dbf.newDocumentBuilder();
            doc = db.newDocument();

            Element customs = doc.createElement("Customs");
            doc.appendChild(customs);

            String TransactionID = getTransID();
            Element header = getHeaderXML(msgType, getRequestID(msgType, strID), TransactionID, doc);
            customs.appendChild(header);

            String message2 = DoctoString(doc);
            if (msgType.equals("807")) {
                data = getMessageDoichieu807(strID, doc);
            }
            customs.appendChild(data);

            Element DigitalSignatures = doc.createElement("DigitalSignatures");
            customs.appendChild(DigitalSignatures);

            doc = getXmlSigner2(doc);
            message = DoctoString(doc);

            String publicKey = XMLSigner.getPublickey(keyStoreFile, keyStorePwd);
            Helper.writeLogError(HQController.class, "request:" + message);
            String msgResponse = HQProcess.callWS247(publicKey, message);
            Helper.writeLogError(HQController.class, "response:" + msgResponse);
            return msgResponse;
        } catch (Exception ex) {
            Helper.writeLogError(HQController.class, ex.getMessage());
            return "";
        }
    }

    private Element getDataM314XML(String ID, Document doc) throws RemoteException, Exception {
        try {

            //GOI QUAN HQ
            //B3: SCB goi MSG 312 de thong bao dang ky thanh cong.
            List listParam = Helper.getDBI().HQ_getData312(ID);

            HQ_DKNNT obj = (HQ_DKNNT) listParam.get(0);

            Element data = doc.createElement("Data");
            Element tmp = setValueChild(doc, "So_HS", obj.getSo_HS() == null ? "" : obj.getSo_HS());
            data.appendChild(tmp);
            tmp = setValueChild(doc, "Loai_HS", obj.getLoai_HS() == null ? "" : obj.getLoai_HS());
            data.appendChild(tmp);
            tmp = setValueChild(doc, "Ma_DV", obj.getMa_DV() == null ? "" : obj.getMa_DV());
            data.appendChild(tmp);
            tmp = setValueChild(doc, "Ten_DV", obj.getTen_DV() == null ? "" : obj.getTen_DV());
            data.appendChild(tmp);

            tmp = setValueChild(doc, "DiaChi", obj.getDiaChi() == null ? "" : obj.getDiaChi());
            data.appendChild(tmp);

            tmp = setValueChild(doc, "Ngay_HL_UQ", obj.getNGAY_HL_UQ() == null ? "" : obj.getNGAY_HL_UQ());
            data.appendChild(tmp);

            tmp = setValueChild(doc, "Ngay_HHL_UQ", obj.getNGAY_HHL_UQ() == null ? "" : obj.getNGAY_HHL_UQ());
            data.appendChild(tmp);

            Element TT_NNT = doc.createElement("ThongTin_NNT");
            data.appendChild(TT_NNT);

            tmp = setValueChild(doc, "So_CMT", obj.getSo_CMT() == null ? "" : obj.getSo_CMT());
            TT_NNT.appendChild(tmp);
            tmp = setValueChild(doc, "Ho_Ten", obj.getHo_Ten() == null ? "" : obj.getHo_Ten());
            TT_NNT.appendChild(tmp);
            tmp = setValueChild(doc, "NgaySinh", obj.getNgaySinh() == null ? "" : obj.getNgaySinh());
            TT_NNT.appendChild(tmp);
            tmp = setValueChild(doc, "NguyenQuan", obj.getNguyenQuan() == null ? "" : obj.getNguyenQuan());
            TT_NNT.appendChild(tmp);

            List listTTLH = obj.getThongTinLienHe();
            for (int i = 0; i < listTTLH.size(); i++) {
                Element TT_LIENHE = doc.createElement("ThongTinLienHe");
                TT_NNT.appendChild(TT_LIENHE);
                HQ_DKNNT_LIENHE objLH = (HQ_DKNNT_LIENHE) listTTLH.get(i);
                tmp = setValueChild(doc, "So_DT", objLH.getSO_DT() == null ? "" : objLH.getSO_DT());
                TT_LIENHE.appendChild(tmp);
                tmp = setValueChild(doc, "Email", objLH.getEMAIL() == null ? "" : objLH.getEMAIL());
                TT_LIENHE.appendChild(tmp);
            }

            Element CTS = doc.createElement("ChungThuSo");
            TT_NNT.appendChild(CTS);
            tmp = setValueChild(doc, "SerialNumber", obj.getSerialNumber() == null ? "" : obj.getSerialNumber());
            CTS.appendChild(tmp);
            tmp = setValueChild(doc, "Noi_Cap", obj.getNoi_Cap() == null ? "" : obj.getNoi_Cap());
            CTS.appendChild(tmp);
            tmp = setValueChild(doc, "Ngay_HL", obj.getNgay_HL() == null ? "" : obj.getNgay_HL());
            CTS.appendChild(tmp);
            tmp = setValueChild(doc, "Ngay_HHL", obj.getNgay_HHL() == null ? "" : obj.getNgay_HHL());
            CTS.appendChild(tmp);
            tmp = setValueChild(doc, "PublicKey", obj.getPublicKey() == null ? "" : obj.getPublicKey());
            CTS.appendChild(tmp);

            Element TTTK = doc.createElement("ThongTinTaiKhoan");
            TT_NNT.appendChild(TTTK);
            tmp = setValueChild(doc, "Ma_NH_TH", obj.getMa_NH_TH() == null ? "" : obj.getMa_NH_TH());
            TTTK.appendChild(tmp);
            tmp = setValueChild(doc, "Ten_NH_TH", obj.getTen_NH_TH() == null ? "" : obj.getTen_NH_TH());
            TTTK.appendChild(tmp);
            tmp = setValueChild(doc, "TaiKhoan_TH", obj.getTaiKhoan_TH() == null ? "" : obj.getTaiKhoan_TH());
            TTTK.appendChild(tmp);
            tmp = setValueChild(doc, "Ten_TaiKhoan_TH", obj.getTen_TaiKhoan_TH() == null ? "" : obj.getTen_TaiKhoan_TH());
            TTTK.appendChild(tmp);
            return data;
        } catch (Exception ex) {
            Helper.writeLogError(HQController.class, ex.getMessage());
            throw ex;
        }
    }

    private Element getDataM108XML(String ID, Document doc) throws RemoteException, Exception {
        try {
            //GOI QUAN HQ
            Element data = doc.createElement("Data");
            Element tmp = setValueChild(doc, "Ma_DV", ID);
            data.appendChild(tmp);
            return data;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
