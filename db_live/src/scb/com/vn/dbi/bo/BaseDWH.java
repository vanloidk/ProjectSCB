/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.sql.Connection;
import scb.com.vn.dbi.connection.ConnectionManager;

/**
 *
 * @author lydty
 */
public class BaseDWH {

    /**
     * Create a new instance of BaseDWH
     */
    public BaseDWH() {
    }

    /**
     *
     * @return @throws Exception
     */
    protected Connection getConnection() throws Exception {
        Connection conn = ConnectionManager.getConnection("dwh");
        return conn;
    }
}
