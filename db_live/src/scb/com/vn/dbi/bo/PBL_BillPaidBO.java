/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import scb.com.vn.dbi.dao.PBL_BillPaidDAO;
import scb.com.vn.dbi.dto.PBLBillPaidCustomerDto.PBLBillPaidCusDto;
import scb.com.vn.dbi.dto.PBLBillPaidCustomerDto.PBLBillPaidCustomerDtlForJobDto;
import scb.com.vn.dbi.dto.BillPaidCustGrpDto;
import scb.com.vn.dbi.utility.HibernateUtil;

/**
 *
 * @author SCB
 */
public class PBL_BillPaidBO extends BaseSMSSCBBO {

    private Connection conn;
    private PBL_BillPaidDAO dao = null;

    /**
     * Create a new instance of PBL_BillPaidBO
     *
     * @throws java.lang.Exception
     */
    public PBL_BillPaidBO() throws Exception {
        dao = new PBL_BillPaidDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactorySmsScb());
    }

    /**
     *
     * @param dateFile
     * @return
     * @throws Exception
     */
    public ArrayList getlist_VW_BCN(String dateFile) throws Exception {
        conn = super.getConnection();
        dao.setConnection(conn);
        return dao.getlist_VW_BCN(dateFile);
    }

    /**
     *
     * @param pRESPONSECODE
     * @param pTypeTrans
     * @param pCCY
     * @param pAmount
     * @param pTransdate
     * @param pTranscode
     * @param pPARTNERDETAILID
     * @param pIDBILLPAID
     * @param pACCOUNT
     * @throws Exception
     */
    public void BCN_RECEIVECOLLATED(String pRESPONSECODE,
            String pTypeTrans,
            String pCCY,
            String pAmount,
            String pTransdate,
            String pTranscode,
            String pPARTNERDETAILID,
            String pIDBILLPAID,
            String pACCOUNT) throws Exception {
        conn = super.getConnection();
        dao.setConnection(conn);
        dao.BCN_RECEIVECOLLATED(pRESPONSECODE, pTypeTrans, pCCY, pAmount, pTransdate, pTranscode, pPARTNERDETAILID, pIDBILLPAID, pACCOUNT);
    }

    /**
     * Insert pbl_billpaid_customer_detail
     *
     * @param billPaidCustDtls
     * @return
     * @throws Exception
     */
    public Boolean insertPBLBillPaidCusDtls(List<PBLBillPaidCustomerDtlForJobDto> billPaidCustDtls) throws Exception {

        conn = super.getConnection();
        dao.setConnection(conn);

        return dao.insertPBLBillPaidCusDtls(billPaidCustDtls);
    }

    
    /**
     * GetAllPBLBillPaidCus
     * 
     * @param partnerIds
     * @param providerIds
     * @param servicetypeIds
     * @return
     * @throws Exception 
     */
    public List<PBLBillPaidCusDto> PBL_getAllPBLBillPaidCus(List<String> partnerIds,List<String> providerIds,  List<String> servicetypeIds) throws Exception {

        conn = super.getConnection();
        dao.setConnection(conn);
        
        return dao.PBL_getAllPBLBillPaidCus(partnerIds, providerIds, servicetypeIds);
    }
    
     public  List<BillPaidCustGrpDto> PBL_getParameterForBillPaidWS() throws Exception {
        
        conn = super.getConnection();
        dao.setConnection(conn);
        
        return dao.getParameterForBillPaidWS();
    }
}
