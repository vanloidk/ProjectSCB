/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.ultility;

import scb.com.vn.common.model.payment.ResponsePayment;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author FICOMBANK
 */
@XmlRootElement(name = "LISTCUSTOMER")
@XmlAccessorType(XmlAccessType.NONE)
public class HashMapTest2 {

    public Map<String, String> map = new HashMap<String, String>();

    @XmlElement(name = "CUSTOMER")
    public MapEntry[] getMap() {
        List<MapEntry> list = new ArrayList<MapEntry>();
        for (Entry<String, String> entry : map.entrySet()) {
            MapEntry mapEntry = new MapEntry();
            mapEntry.key = entry.getKey();
            mapEntry.value = entry.getValue();
            list.add(mapEntry);
        }
        return list.toArray(new MapEntry[list.size()]);
    }

    public void setMap(MapEntry[] arr) {
        for (MapEntry entry : arr) {
            this.map.put(entry.key, entry.value);
        }
    }

    public static class MapEntry {

        @XmlAttribute
        public String key;
        @XmlValue
        public String value;
    }

    public static void main(String args[]) throws Exception {
        
        String aaaaa = "vvdc@ccdd@ddxx@dddf111@@ky@07/2024-06/2024";
        String[] kq = aaaaa.split("@ky@");
        String kq2 = kq[1].toString();
//        HashMapTest2 mp = new HashMapTest2();  
//        mp.map.put("key1", "value1");  
//        mp.map.put("key2", "value2");  


//        Customer customer = new Customer();
////	  customer.setId(100);
//        customer.setCUSTOMERNAME("mkyong");
//        customer.setPOSITION("29");
//
//        Customer customer1 = new Customer();
//        customer1.setCUSTOMERNAME("mkyong");
//        customer1.setPOSITION("29");
//
//        List<Customer> l = new ArrayList<Customer>();
//        l.add(customer);
//        l.add(customer1);
//
//        ResponsePayment lc = new ResponsePayment();
//        lc.setList(l);



        JAXBContext jc = JAXBContext.newInstance(ResponsePayment.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        m.marshal(lc, System.out);

        Unmarshaller u = jc.createUnmarshaller();
//        u.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                
        String b = "<PAYMENT><TRANSCODE>102742</TRANSCODE><RESULT>0</RESULT><RESPONSE><AIRLINE><STATUS>0</STATUS><BOOKCODE>BL/MKHUKA</BOOKCODE><LISTCUSTOMER><CUSTOMER><CUSTOMERNAME>1.1NGUYEN</CUSTOMERNAME><POSITION>THANHLONG</POSITION></CUSTOMER></LISTCUSTOMER><SCHEDULE>2.1BL//1224K//12SEP//HAN//DLI//1121//1905</SCHEDULE><AGENT></AGENT><MAKER>LONGNT</MAKER><MOBCUST>0986592325</MOBCUST><BOOKTIME>9/25/2012 5:36:12 PM</BOOKTIME><AMOUNT>2500000</AMOUNT><DESCRIPTION>Chua thanh toan</DESCRIPTION></AIRLINE></RESPONSE></PAYMENT>";
          //<PAYMENT><LISTCUSTOMER><CUSTOMER><CUSTOMERNAME>1.1NGUYEN</CUSTOMERNAME><POSITION>THANHLONG</POSITION></CUSTOMER></LISTCUSTOMER></PAYMENT>
//        StringBuffer xmlStr = new StringBuffer(b);
        StringReader reader = new StringReader(b);
//        ResponsePayment mp2 = (ResponsePayment) u.unmarshal(new StreamSource(new StringReader(xmlStr.toString())));
        ResponsePayment mp2 = (ResponsePayment) u.unmarshal(reader);
        m.marshal(mp2, System.out);
        String z="";
    }
}
