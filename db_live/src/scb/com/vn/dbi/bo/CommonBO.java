/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;
import java.sql.Connection;
import scb.com.vn.dbi.dao.CommonDAO;

/**
 *
 * @author loinv3
 */
public class CommonBO extends BaseSMSBO{
    private Connection conn;
    private final CommonDAO commonDAO;

    /**
     * Create a new instance of CWLiveBO
     */
    public CommonBO() {
        commonDAO = new CommonDAO();
    }
    
    /**
     * Check exist partner by account no
     * 
     * @param accountNo
     * @return
     * @throws Exception 
     */
     public Boolean isExistPartner(String accountNo) throws Exception{
         conn = getConnection();
         commonDAO.setConnection(conn);
          return commonDAO.isExistPartner(accountNo);
     }
}
