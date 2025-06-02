package scb.com.vn.dbi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dto.PartnerDTO;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author system
 */
public class PartnerDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(PartnerDAO.class);

    /**
     *
     */
    protected static String SELECT_SQL = "SELECT accesskey,servicetype,partnercode,signature,ip FROM GW_PARTNER";

    /**
     *
     */
    protected static String SELECT_SQL_BYACCESSKEY = "SELECT accesskey,servicetype,partnercode,signature,ip FROM GW_PARTNER WHERE accesskey='%1$s'";
    final String SQL_GET_PERMISSIONS = "SELECT PARTNER_ID,B.NAME FUNCTION_NAME FROM LOC_DEV.GW_PERMISSION A,LOC_DEV.GW_FUNCTION B WHERE A.FUNCTION_ID=B.FUNCTION_ID AND A.ISPERMISSION='Y' ORDER BY PARTNER_ID";

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<Hashtable<String, String>> getallPartner() throws Exception {
        PreparedStatement statement = null;
        ArrayList<Hashtable<String, String>> listparner = new ArrayList<Hashtable<String, String>>();
        try {
            statement = connection.prepareStatement(SELECT_SQL);
            ResultSet rs = statement.executeQuery();
            if (rs == null) {
                LOGGER.warn("Not found");
                throw new Exception("Not found");
            }
            while (rs.next()) {
                Hashtable<String, String> ht = new Hashtable<String, String>();
                ht.put(PartnerDTO.accesskey, rs.getString(PartnerDTO.accesskey));
                ht.put(PartnerDTO.servicetype, rs.getString(PartnerDTO.servicetype));
                ht.put(PartnerDTO.partnercode, rs.getString(PartnerDTO.partnercode));
                ht.put(PartnerDTO.signature, rs.getString(PartnerDTO.signature));
                ht.put(PartnerDTO.ip, rs.getString(PartnerDTO.ip));
                listparner.add(ht);
            }
            return listparner;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param accesskey
     * @return
     * @throws Exception
     */
    public PartnerDTO getPartnerByAccesskey(String accesskey) throws Exception {
        PreparedStatement statement = null;
        PartnerDTO listparner = null;
        try {
            statement = connection.prepareStatement(String.format(SELECT_SQL_BYACCESSKEY, accesskey));
            ResultSet rs = statement.executeQuery();
            if (rs == null) {
                return listparner;
            }
            listparner = new PartnerDTO();
            while (rs.next()) {
                Hashtable<String, String> ht = new Hashtable<String, String>();
                ht.put(PartnerDTO.accesskey, rs.getString(PartnerDTO.accesskey));
                ht.put(PartnerDTO.servicetype, rs.getString(PartnerDTO.servicetype));
                ht.put(PartnerDTO.partnercode, rs.getString(PartnerDTO.partnercode));
                ht.put(PartnerDTO.signature, rs.getString(PartnerDTO.signature));
                ht.put(PartnerDTO.ip, rs.getString(PartnerDTO.ip));
                listparner.add(ht);
            }
            return listparner;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getGwPermissions() throws Exception {
        return JDBCEngine.executeQuery(SQL_GET_PERMISSIONS, null, connection);
    }
}
