/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import scb.com.vn.payment.ControllerUtil;

/**
 *
 * @author minhndb
 */
public class XMLUtils {

    private static Logger logger = Logger.getLogger(XMLUtils.class);
    private static final String XMLRESPONSEDEFAULT = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><RESPONSE><RESPONSECODE>99</RESPONSECODE><DESCRIPTION>The system is error</DESCRIPTION></RESPONSE>";

    /**
     *
     * @param data
     * @param md5Key
     * @return
     */
    public static String xmlParser(Map<String, String> data, String md5Key) {
        try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            StringBuilder str = new StringBuilder(md5Key);
            for (Map.Entry<String, String> pair : data.entrySet()) {
                Element element = responsedoc.createElement(pair.getKey());
                element.appendChild(responsedoc.createTextNode(pair.getValue()));
                response.appendChild(element);

                str.append(pair.getValue());
            }

            String resMAC = ControllerUtil.EncriptMD5(str.toString());

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut, format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (ParserConfigurationException | DOMException | IOException e) {
            return XMLRESPONSEDEFAULT;
        }
    }

    /**
     *
     * @param data
     * @return
     */
    public static String xmlParser(Map<String, String> data) {
        try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            for (Map.Entry<String, String> pair : data.entrySet()) {
                Element element = responsedoc.createElement(pair.getKey());
                element.appendChild(responsedoc.createTextNode(pair.getValue()));
                response.appendChild(element);
            }

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut, format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (ParserConfigurationException | DOMException | IOException e) {
            return XMLRESPONSEDEFAULT;
        }
    }

    /**
     *
     * @param code
     * @param description
     * @return
     */
    public static String systemIsError(String code, String description) {
        HashMap<String, String> response = new HashMap<>();
        response.put("RESPONSECODE", code);
        response.put("DESCRIPTION", description);
        return xmlParser(response);
    }

    /**
     *
     * @param keys
     * @param xml
     * @return
     */
    public static HashMap<String, String> xmlParser(List<String> keys, String xml) {
        HashMap<String, String> result = new HashMap<>();
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(xml)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element element = (Element) nodeRoot;

            for (String key : keys) {
                String value = element.getElementsByTagName(key).item(0).getTextContent();
                result.put(key, value);
            }
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            return null;
        }

        return result;
    }

    /**
     *
     * @param obj
     * @return
     */
    public static String Marshaller(Object obj) {
        try {
            JAXBContext jc = JAXBContext.newInstance(obj.getClass());
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            m.marshal(obj, sw);
            return sw.toString();
        } catch (Exception e) {
            logger.error(e);
            return "";
        }
    }

    /**
     *
     * @param clazz
     * @param xml
     * @return
     */
    public static Object unMarshaller(Class clazz, String xml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Object o = (Object) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return o;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    /**
     *
     * @param obj
     * @return
     */
    public static String MarshallerNOTFormat(Object obj) {
        try {
            JAXBContext jc = JAXBContext.newInstance(obj.getClass());
            Marshaller m = jc.createMarshaller();
            //m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            m.marshal(obj, sw);
            return sw.toString();
        } catch (Exception e) {
            logger.error(e);
            return "";
        }
    }

}
