package scb.com.vn.utility;

import java.util.Hashtable;

/**
 *
 * @author minhndb
 */
public class PartnerInfo {

    /**
     *
     */
    public PartnerInfo() {
    }

    /**
     *
     * @param accesskey
     * @param partnercode
     * @param ip_client
     * @return
     */
    public Hashtable<String, String> getPartnerInfo(String accesskey, String partnercode, String ip_client) {
        //For MOCA  
        if (partnercode.equals("MOCA")) {
            Hashtable<String, String> hash = new Hashtable<String, String>();
            hash.put("accesskey", "7RlrArLzd4xkgdRyrJh");
            hash.put("partnercode", "MOCA");
            hash.put("signature", "taVrHmlJ03oChUKrh55A3NUZdC43ALHual+yu7Ab");
            if (accesskey.equals(hash.get("accesskey"))
                    && partnercode.equals(hash.get("partnercode"))) {
                return hash;
            } else {
                return null;

            }
        } else if (Configuration.hmWSPartners.containsKey(accesskey)) {
            Hashtable<String, String> _ht_temp = (Hashtable) Configuration.hmWSPartners.get(accesskey);

            if (accesskey.equals(_ht_temp.get("accesskey"))
                    && partnercode.equals(_ht_temp.get("partnercode"))) {
                return _ht_temp;
            } else {
                /*
                try {
                    Helper.getDBI().sendSMS("0989990921", "LOI XAC THUC PARTNER: " + partnercode, "MONITOR", "SYS");} 
                catch (Exception ex) {}  */

                return null;
            }
        } else {
            /*
            try {Helper.getDBI().sendSMS("0989990921", "LOI XAC THUC PARTNER: " + partnercode, "MONITOR", "SYS");} 
            catch (Exception ex) {}*/

            return null;
        }

//        ArrayList<Hashtable<String, String>> p = null;
//        try {
//            p = Helper.getDBI().getPartnerByAccesskey(accesskey);
//        } catch (Exception ex) {
//            Helper.writeLogError(this.getClass(), ex);
//        }
//
//        if (p == null) {
//            return null;
//        }
//
//    //        Hashtable<String, String> _ht_result = null;
//        Hashtable<String, String> _ht_temp = null;
//
//        for (int i = 0; i < p.size(); i++) {
//            _ht_temp = (Hashtable<String, String>) p.get(i);
//
//            if (_ht_temp == null) {
//                return null;
//            }
//
//            if (accesskey.equals(_ht_temp.get("accesskey"))
//                    && partnercode.equals(_ht_temp.get("partnercode"))) {
//                // && ip_client.equals(_ht_temp.get("ip"))
//                return _ht_temp;
//
//            }
//        }
//
//        return _ht_temp;
    }
}
