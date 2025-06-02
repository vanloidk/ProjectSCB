/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import scb.com.vn.dbi.dao.DwhDAO;

/**
 *
 *
 * @author kimncm
 */
public class DwhBO {

    private DwhDAO dwhDAO;

    /**
     * Create a new instance of FCCCoreBO
     */
    public DwhBO() {
        dwhDAO = new DwhDAO();
    }
 
    /**
     * 
     * @param accountno
     * @param fromDate
     * @param toDate
     * @param srno
     * @return
     * @throws SQLException 
     */
    
    public ArrayList getTransationListByAccNew(String accountno, String fromDate, String toDate, String srno) throws SQLException {
        return dwhDAO.getTransationListByAccNew(accountno, fromDate, toDate, srno);
    }
}
