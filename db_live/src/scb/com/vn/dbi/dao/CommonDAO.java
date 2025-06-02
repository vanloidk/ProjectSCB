/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;

import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author loinv3
 */
public class CommonDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(CommonDAO.class);

    /**
     *
     */
    final String getTrnPartnerByAccountAndPartner = "SELECT partnerid, partnername, accountno FROM ONL_TRANS_PARTNERS WHERE accountno = ? and partnerid like 'TVSI%'";

    /**
     * Check exist account in parner
     *
     * @param accountNo
     * @return
     * @throws Exception
     */
    public Boolean isExistPartner(String accountNo) throws Exception {

        Boolean isExisted = true;
        try {
            ArrayList<String> p_args = new ArrayList<String>();
            p_args.add(accountNo);
            ArrayList<?> list = JDBCEngine.executeQuery(getTrnPartnerByAccountAndPartner, p_args, connection);
            if (list.isEmpty()) {
                return false;
            }

            return isExisted;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
