package scb.com.vn.utility;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Nguyen Ngo Duy Phuc
 * @version 1.0
 */
public class XmlReader {

    private static Hashtable<String, Document> documents = new Hashtable<String, Document>();

    // public static int times = 0;
    // use this to load any document you like

    /**
     *
     * @param filePath
     * @throws Exception
     */
    public static void loadDocument(String filePath) throws Exception {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document settings = docBuilder.parse(new File(filePath));
            settings.getDocumentElement().normalize();

            documents.put(filePath, settings);
        } catch (SAXParseException saxEx) {
            // saxEx.printStackTrace();
            throw new Exception(saxEx);
        } catch (SAXException saxEx) {
            // saxEx.printStackTrace();
            throw new Exception(saxEx);
        } catch (java.io.FileNotFoundException fnfEx) {
            // fnfEx.printStackTrace();
            throw new Exception(fnfEx);
        } catch (Throwable thrEx) {
            // thrEx.printStackTrace();
            throw new Exception(thrEx);
        }
    }

    // to be deleted
    /*
	 * public static String getUIMessage(String messageCode) { return
	 * getUIMessage(messageCode, null); }
	 * 
	 * // to be deleted public static String getUIMessage(String messageCode,
	 * String[] parameters) { try { // to be placed in Client constants String
	 * messageFile = MessageConstants.MESSAGE_FILE; String delim =
	 * MessageConstants.DELIM; String placeHolder =
	 * MessageConstants.PLACE_HOLDER;
	 * 
	 * // retrieved and re-format the message String message = null;
	 * StringTokenizer st = new StringTokenizer(messageCode, delim); String root
	 * = st.nextToken(); String element = st.nextToken(); message =
	 * getString(messageFile, root, element, null); if (message != null &&
	 * parameters != null) { for (int i = 0; i < parameters.length; i++) {
	 * message = message.replaceAll(placeHolder + (i + 1), parameters[i]); } }
	 * return message; } catch (Throwable t) { return null; } }
     */
    // type-safe get int

    /**
     *
     * @param doc
     * @param root
     * @param element
     * @param defValue
     * @return
     */
    public static int getInt(String doc, String root, String element, int defValue) {
        String s = xmlFind(doc, root, element);
        if (s == null) {
            return defValue;
        } else {
            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
                return defValue;
            }
        }
    }

    /**
     *
     * @param doc
     * @param root
     * @param element
     * @param position
     * @param defValue
     * @return
     */
    public static int getInt(String doc, String root, String element, int position, int defValue) {
        String s = xmlFind(doc, root, element, position);
        if (s == null) {
            return defValue;
        } else {
            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
                return defValue;
            }
        }
    }

    // type-safe get long

    /**
     *
     * @param doc
     * @param root
     * @param element
     * @param defValue
     * @return
     */
    public static long getLong(String doc, String root, String element, int defValue) {
        String s = xmlFind(doc, root, element);
        if (s == null) {
            return defValue;
        } else {
            try {
                return Long.parseLong(s);
            } catch (Exception e) {
                return defValue;
            }
        }
    }

    /**
     *
     * @param doc
     * @param root
     * @param element
     * @param position
     * @param defValue
     * @return
     */
    public static long getLong(String doc, String root, String element, int position, int defValue) {
        String s = xmlFind(doc, root, element, position);
        if (s == null) {
            return defValue;
        } else {
            try {
                return Long.parseLong(s);
            } catch (Exception e) {
                return defValue;
            }
        }
    }

    // type-safe get float

    /**
     *
     * @param doc
     * @param root
     * @param element
     * @param defValue
     * @return
     */
    public static float getFloat(String doc, String root, String element, float defValue) {
        String s = xmlFind(doc, root, element);
        if (s == null) {
            return defValue;
        } else {
            try {
                return Float.parseFloat(s);
            } catch (Exception e) {
                return defValue;
            }
        }
    }

    /**
     *
     * @param doc
     * @param root
     * @param element
     * @param position
     * @param defValue
     * @return
     */
    public static float getFloat(String doc, String root, String element, int position, float defValue) {
        String s = xmlFind(doc, root, element, position);
        if (s == null) {
            return defValue;
        } else {
            try {
                return Float.parseFloat(s);
            } catch (Exception e) {
                return defValue;
            }
        }
    }

    // type-safe get boolean

    /**
     *
     * @param doc
     * @param root
     * @param element
     * @param defValue
     * @return
     */
    public static boolean getBoolean(String doc, String root, String element, boolean defValue) {
        String s = xmlFind(doc, root, element);
        if (s == null) {
            return defValue;
        } else {
            try {
                return Boolean.getBoolean(s);
            } catch (Exception e) {
                return defValue;
            }
        }
    }

    /**
     *
     * @param doc
     * @param root
     * @param element
     * @param position
     * @param defValue
     * @return
     */
    public static boolean getBoolean(String doc, String root, String element, int position, boolean defValue) {
        String s = xmlFind(doc, root, element, position);
        if (s == null) {
            return defValue;
        } else {
            try {
                return Boolean.getBoolean(s);
            } catch (Exception e) {
                return defValue;
            }
        }
    }

    // type-safe get string

    /**
     *
     * @param doc
     * @param root
     * @param element
     * @param defValue
     * @return
     */
    public static String getString(String doc, String root, String element, String defValue) {
        String s = xmlFind(doc, root, element);
        if (s == null) {
            return defValue;
        } else {
            try {
                return s;
            } catch (Exception e) {
                return defValue;
            }
        }
    }

    /**
     *
     * @param doc
     * @param root
     * @param element
     * @param position
     * @param defValue
     * @return
     */
    public static String getString(String doc, String root, String element, int position, String defValue) {
        String s = xmlFind(doc, root, element, position);
        if (s == null) {
            return defValue;
        } else {
            try {
                return s;
            } catch (Exception e) {
                return defValue;
            }
        }
    }

    // get the document

    /**
     *
     * @param docPath
     * @return
     * @throws Exception
     */
    public static Document getDocument(String docPath) throws Exception {
        Document doc = (Document) documents.get(docPath);
        if (doc == null) { // document is not loaded yet, load it now
            loadDocument(docPath);
        }
        return (Document) documents.get(docPath);
    }

    // load all elements into a hashtable
    /*
	 * public static Hashtable loadAllElements(String docFile) { Hashtable
	 * allMessages = new Hashtable(); try { Document doc = getDocument(docFile);
	 * loadAllElements(allMessages, doc.getChildNodes().item(0), ""); return
	 * allMessages; } catch (Throwable t) { // t.printStackTrace(); } return
	 * allMessages; }
	 * 
	 * private static void loadAllElements(Hashtable messages, Node relRoot,
	 * String absName) { if (relRoot.getNodeType() == Node.ELEMENT_NODE) { if
	 * (relRoot.getChildNodes().getLength() == 1 &&
	 * relRoot.getChildNodes().item(0).getNodeType() == Node.TEXT_NODE) {
	 * 
	 * messages.put(absName, ((Element) relRoot).getChildNodes().item(0)
	 * .getNodeValue().trim()); return; } else { // parent root NodeList
	 * listRoots = relRoot.getChildNodes(); // for (int i = 0; i <
	 * listRoots.getLength(); i++) { Node childNode = listRoots.item(i);
	 * loadAllElements(messages, childNode, (absName == "" ? absName : (absName
	 * + MessageConstants.DELIM)) + childNode.getNodeName()); } } } }
     */

    /**
     *
     * @param settings
     * @param root
     * @param element
     * @return
     */

    public static String xmlFind(Document settings, String root, String element) {
        try {
            NodeList listRoots = settings.getElementsByTagName(root); // big tag
            // level
            // 2
            Node rootNode = listRoots.item(0); // get the first guy, thus, dont
            // use same name for tag in the
            // same level
            if (rootNode.getNodeType() == Node.ELEMENT_NODE) {
                Element rootElement = (Element) rootNode; // cast to element
                NodeList listChildren = rootElement.getElementsByTagName(element); // get
                // child
                // elements
                // that
                // has
                // specified
                // name
                Element firstNameElement = (Element) listChildren.item(0); // again
                // get
                // the
                // first
                // guy
                NodeList textList = firstNameElement.getChildNodes();
                Node n = (Node) textList.item(0);
                if (n == null) {
                    return null;
                }
                return ((Node) textList.item(0)).getNodeValue().trim(); // get
                // the
                // first
                // text
                // value
            }
        } catch (Throwable t) { // log
            t.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     *
     * @param settings
     * @param root
     * @param element
     * @param position
     * @return
     */
    public static String xmlFind(Document settings, String root, String element, int position) {
        try {
            NodeList listRoots = settings.getElementsByTagName(root); // big tag
            // level
            // 2
            Node rootNode = listRoots.item(position); // get the first guy,
            // thus, dont use same
            // name for tag in the
            // same level
            if (rootNode.getNodeType() == Node.ELEMENT_NODE) {
                Element rootElement = (Element) rootNode; // cast to element
                NodeList listChildren = rootElement.getElementsByTagName(element); // get
                // child
                // elements
                // that
                // has
                // specified
                // name
                Element firstNameElement = (Element) listChildren.item(0); // again
                // get
                // the
                // first
                // guy
                NodeList textList = firstNameElement.getChildNodes();
                Node n = (Node) textList.item(0);
                if (n == null) {
                    return null;
                }
                return ((Node) textList.item(0)).getNodeValue().trim(); // get
                // the
                // first
                // text
                // value
            }
        } catch (Throwable t) { // log
            t.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     *
     * @param doc
     * @param root
     * @param element
     * @return
     */
    public static String xmlFind(String doc, String root, String element) {
        try {
            Document settings = getDocument(doc);
            return xmlFind(settings, root, element);
        } catch (Throwable t) {
            Exception e = new Exception(t);
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param doc
     * @param root
     * @param element
     * @param position
     * @return
     */
    public static String xmlFind(String doc, String root, String element, int position) {
        try {
            Document settings = getDocument(doc);
            return xmlFind(settings, root, element, position);
        } catch (Throwable t) {
            Exception e = new Exception(t);
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param doc
     * @param root
     * @return
     */
    public static int getNodeListSize(String doc, String root) {
        try {
            Document settings = getDocument(doc);
            NodeList listRoots = settings.getElementsByTagName(root); // big tag
            // level
            // 2
            return listRoots.getLength();
        } catch (Throwable t) { // log
            t.printStackTrace();
            return 0;
        }
    }

}
