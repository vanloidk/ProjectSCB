package scb.com.vn.ultility;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class Xml {

    public static Element createElement(Document doc, String name, String data) {
        Element e = doc.createElement(name);
        e.appendChild(doc.createTextNode(data));
        return e;
    }

    public static String toString(Document doc) {
        DOMImplementationLS domImplementation = (DOMImplementationLS) doc.getImplementation();
        LSSerializer lsSerializer = domImplementation.createLSSerializer();
        return lsSerializer.writeToString(doc);
    }

    public static HashMap<String, Object> get(NodeList nodelist) {
        String key = null;
        NodeList value = null;
        HashMap<String, Object> hm = new HashMap<String, Object>();

        for (int i = 0; i < nodelist.getLength(); i++) {
            Node nNode = nodelist.item(i);
            if (nNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element eElement = (Element) nNode;
            key = eElement.getNodeName();
            value = eElement.getChildNodes();

            if ((key == null) && (value == null)) {
                return null;
            }

            // 1 cap
            if (value.getLength() == 0) {
                hm.put(key, null);

            } else if (value.getLength() == 1) {
                if (value.item(0).hasChildNodes()) {
                    hm.put(key, value);
                } else {
                    hm.put(key, value.item(0).getNodeValue());
                }

            } else {
                hm.put(key, value);
            }

        }
        return hm;
    }

    public static HashMap<String, Object> get(Document doc) {
        NodeList nodelist = doc.getFirstChild().getChildNodes();
        if (nodelist == null || nodelist.getLength() == 0) {
            return null;
        }
        return get(nodelist);
    }

    public static ArrayList<HashMap<String, Object>> getMulti(NodeList nodelist) {
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        String key = null;
        NodeList value = null;

        for (int i = 0; i < nodelist.getLength(); i++) {
            HashMap<String, Object> hm = new HashMap<String, Object>();
            Node nNode = nodelist.item(i);
            if (nNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element eElement = (Element) nNode;
            key = eElement.getNodeName();
            value = eElement.getChildNodes();

            if ((key == null) && (value == null)) {
                return null;
            }

            if (value.getLength() == 0) {
                hm.put(key, null);
            } else if (value.getLength() == 1) {
                hm.put(key, value.item(0).getNodeValue());
            }

            list.add(hm);

        }
        return list;
    }

    public static String Marshaller(Object obj) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(obj.getClass());
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //m.marshal(lc, System.out);

        StringWriter sw = new StringWriter();

//        m.marshal(obj, System.out);
        m.marshal(obj, sw);
        return sw.toString();
    }

    public static Object unMarshaller(Class clazz, String xml) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //m.marshal(lc, System.out);

        Unmarshaller u = jc.createUnmarshaller();
        StringBuilder xmlStr = new StringBuilder(xml);
        Object o = (Object) u.unmarshal(new StreamSource(new StringReader(xmlStr.toString())));
//        m.marshal(o, System.out);
        return o;
    }
    
    public static Object unMarshaller(Class clazz, Node node) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Object o = (Object) jaxbUnmarshaller.unmarshal(node);
        return o;
    }

    public static ArrayList<HashMap<String, Object>> getElement(Element element) {
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        String key = null;
        NodeList value = null;

        NodeList nodelist = element.getChildNodes();
        for (int i = 0; i < nodelist.getLength(); i++) {
            HashMap<String, Object> hm = new HashMap<String, Object>();
            Node nNode = nodelist.item(i);
            if (nNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element eElement = (Element) nNode;
            key = eElement.getNodeName();
            value = eElement.getChildNodes();

            if ((key == null) && (value == null)) {
                return null;
            }

            if (value.getLength() == 0) {
                hm.put(key, null);
            } else if (value.getLength() == 1) {
                hm.put(key, value.item(0).getNodeValue());
            }

            list.add(hm);

        }
        return list;
    }

    /**
     * *
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public static Document loadXML(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            return builder.parse(is);
        } catch (Exception e) {
            return null;
        }
    }
}
