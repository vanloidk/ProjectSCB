/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.sql.Connection;
import scb.com.vn.dbi.connection.*;

/**
 *
 * @author CORE77
 */
public class BaseCWLIVE {

    /**
     * Create a new instance of BaseCWLIVE
     */
    public BaseCWLIVE() {
    }

    /**
     *
     * @return @throws Exception
     */
    protected Connection getConnection() throws Exception {
        Connection conn = ConnectionManager.getConnection("cwlive");
        return conn;
    }
}
