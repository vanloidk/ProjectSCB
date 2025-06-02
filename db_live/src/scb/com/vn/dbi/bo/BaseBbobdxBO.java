/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.io.Serializable;
import java.sql.Connection;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.connection.*;

/**
 * @author Nguyen Ngo Duy Phuc
 */
public class BaseBbobdxBO extends GenericHibernateBO<Object, Serializable> {

    private static final Logger LOGGER = Logger.getLogger(BaseBbobdxBO.class);

    /**
     * Create a new instance of BaseBbobdxBO
     */
    public BaseBbobdxBO() {
    }

    /**
     *
     * @return
     */
    protected Connection getConnection() {
        try {
            Connection conn = ConnectionManager.getConnection("dbobdx");
            return conn;
        } catch (Exception ex) {
            LOGGER.error("Loi getConnection:" + ex);
            return null;
        }
    }
}
