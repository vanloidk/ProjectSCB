/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dto.PBLBillPaidCustomerDto.PBLBillPaidCusDto;
import scb.com.vn.dbi.dto.PBLBillPaidCustomerDto.PBLBillPaidCustomerDtlForJobDto;
import scb.com.vn.dbi.dto.BillPaidCustGrpDto;

/**
 *
 * @author SCB
 */
public class PBL_BillPaidDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(PBL_BillPaidDAO.class);

    //BCN Collate
    final String GETLIST_VW_BCN = "BEGIN PKG_PBL_BILLPAID.BCN_GET_COLLATED(?,?); END;";
    final String BCN_RECEIVECOLLATED = "BEGIN PKG_PBL_BILLPAID.BCN_RECEIVECOLLATED(?,?,?,?,?,?,?,?,?); END;";

    final String INSERT_PBL_BILLPAID_CUS_OTT = "INSERT INTO t_org_billpaid@AIT(customer_no, customer_code, service_code, paid_period, paid_date, provider_code, amount, Paidstatus, status,customer_name)"
            + " VALUES(?,?,?,?,?,?,?,?,?,?)";

    public String GET_ALL_PBL_BILLPAID_CUS = "SELECT pbl.Id, pbl.custno, pbl.idpartner, pbl.idservicetype, pbl.idprovider, pbl.customercode, pbl.createdated FROM Pbl_Billpaid_Customer pbl WHERE 1 = 1 and ISUSED=1";
    final String GET_T_ORG_BILLPAID = "SELECT T.CUSTOMER_NO customerNo, T.CUSTOMER_CODE customerCode, T.PAID_PERIOD paidDate, T.AMOUNT totalAmount FROM t_org_billpaid@AIT T WHERE  T.PAID_PERIOD=? ";
    final String GET_PARAMATER_CALL_BILLPAID_WS = "SELECT dd.IDPARTNER parnerId, dd.IDPROVIDER providerId, dd.IDSERVICETYPE serviceTypeId FROM PBL_BILLPAID_CUSTOMER dd group by dd.IDPARTNER, dd.IDPROVIDER, dd.IDSERVICETYPE";

    /**
     *
     * @param dateFile
     * @return
     * @throws Exception
     */
    public ArrayList getlist_VW_BCN(String dateFile) throws Exception {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(GETLIST_VW_BCN);
            calStmt.setString(1, dateFile);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(2);
            int numcols = rs.getMetaData().getColumnCount();
            ArrayList result = new ArrayList();
            while (rs.next()) {
                List row = new ArrayList();
                for (int i = 1; i <= numcols; i++) {  // don't skip the last column, use <=
                    row.add(rs.getString(i));
                }
                result.add(row); // add it to the result
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
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
     * @throws SQLException
     */
    public void BCN_RECEIVECOLLATED(String pRESPONSECODE,
            String pTypeTrans,
            String pCCY,
            String pAmount,
            String pTransdate,
            String pTranscode,
            String pPARTNERDETAILID,
            String pIDBILLPAID,
            String pACCOUNT) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(BCN_RECEIVECOLLATED);
            calStmt.setString(1, pRESPONSECODE);
            calStmt.setString(2, pTypeTrans);
            calStmt.setString(3, pCCY);
            calStmt.setString(4, pAmount);
            calStmt.setString(5, pTransdate);
            calStmt.setString(6, pTranscode);
            calStmt.setString(7, pPARTNERDETAILID);
            calStmt.setString(8, pIDBILLPAID);
            calStmt.setString(9, pACCOUNT);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Get all data from ORGBILLPAID
     *
     * @return
     */
    private List<String> getTORGBILLPAID() {

        List<String> entities = new ArrayList<>();
        java.util.Date now = new java.util.Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        String year = sf.format(now);
        String mn = String.valueOf(now.getMonth());

        try {

            CallableStatement calStmt = connection.prepareCall(GET_T_ORG_BILLPAID);

            String period = mn.concat("/").concat(year);
            calStmt.setString(1, period);
            ResultSet rs = calStmt.executeQuery();

            while (rs.next()) {
                String strConcat = rs.getString("customerNo").concat(rs.getString("customerCode")).concat(rs.getString("paidDate")).concat(String.valueOf(rs.getBigDecimal("totalAmount")));
                entities.add(strConcat);
            }

            return entities;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    /**
     * Insert PBL_BillPaid_Customer_Detail
     *
     * @param billPaidCustDtls
     * @return
     * @throws Exception
     */
    public Boolean insertPBLBillPaidCusDtls(List<PBLBillPaidCustomerDtlForJobDto> billPaidCustDtls) throws Exception {
        try {
            List<String> orgBillPaids = this.getTORGBILLPAID();

            CallableStatement calStmt = connection.prepareCall(INSERT_PBL_BILLPAID_CUS_OTT);
            for (PBLBillPaidCustomerDtlForJobDto billPaidCus : billPaidCustDtls) {

                String strConcat = billPaidCus.getCusNo().concat(billPaidCus.getCustomerCode()).concat(billPaidCus.getPeriodPaid()).concat(String.valueOf(billPaidCus.getTotalAmount()));
                if (!orgBillPaids.contains(strConcat)) {
                    calStmt.setString(1, billPaidCus.getCusNo());
                    calStmt.setString(2, billPaidCus.getCustomerCode());
                    calStmt.setString(3, billPaidCus.getServiceTypeId());
                    calStmt.setString(4, billPaidCus.getPeriodPaid()); //paid_period
                    calStmt.setDate(5, (Date) billPaidCus.getCreatedDate());
                    calStmt.setString(6, billPaidCus.getProviderId());
                    calStmt.setBigDecimal(7, billPaidCus.getTotalAmount());
                    calStmt.setString(8, "N");
                    calStmt.setString(9, "N");
                    calStmt.setString(10, billPaidCus.getCustomer_name());
                    calStmt.execute();
                }
            }

            return true;
        } catch (SQLException ex) {

            LOGGER.error(ex);
            return false;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Get all PBL_Billpaid_Customer
     *
     * @param partnerIds
     * @param servicetypeIds
     * @param providerIds
     * @return list
     * @throws Exception PBLBillPaidCustomer
     */
    public List<PBLBillPaidCusDto> PBL_getAllPBLBillPaidCus(List<String> partnerIds, List<String> providerIds, List<String> servicetypeIds) throws Exception {

        List<PBLBillPaidCusDto> billPaidcusts = new ArrayList<>();

        CallableStatement calStmt = null;
        try {
            
            //Check partnerId
            if (!partnerIds.isEmpty()) {
                int i = 1;
                GET_ALL_PBL_BILLPAID_CUS += " AND pbl.idpartner in (";
                for (String element : partnerIds) {
                    if (i == partnerIds.size()) {
                        GET_ALL_PBL_BILLPAID_CUS += element;
                    } else {
                        GET_ALL_PBL_BILLPAID_CUS += element + ",";
                    }
                    i++;
                }
                GET_ALL_PBL_BILLPAID_CUS+=")";
            }

            //Check providerId
            if (!providerIds.isEmpty()) {
                int j = 1;
                GET_ALL_PBL_BILLPAID_CUS += " AND pbl.idprovider in (";
                for (String element : providerIds) {
                    if (j == providerIds.size()) {
                        GET_ALL_PBL_BILLPAID_CUS += element;
                    } else {
                        GET_ALL_PBL_BILLPAID_CUS += element + ",";
                    }
                    j++;
                }
                GET_ALL_PBL_BILLPAID_CUS+=")";

            }

            //Check servicetypeId
            if (!servicetypeIds.isEmpty()) {
                int k = 1;
                GET_ALL_PBL_BILLPAID_CUS += " AND pbl.Idservicetype in (";
                for (String element : servicetypeIds) {
                    if (k == servicetypeIds.size()) {
                      GET_ALL_PBL_BILLPAID_CUS += element;
                    } else {
                        GET_ALL_PBL_BILLPAID_CUS += element + ",";
                    }
                    k++;
                }
                GET_ALL_PBL_BILLPAID_CUS+=")";
            }

            calStmt = connection.prepareCall(GET_ALL_PBL_BILLPAID_CUS);
            /*
            for (int p = 1; p <= partnerIds.size(); p++) {
                calStmt.setString(p, partnerIds.get(p - 1));
            }
            for (int n = 1; n <= providerIds.size(); n++) {
                calStmt.setString(n + partnerIds.size(), providerIds.get(n - 1));
            }
            for (int s = 1; s <= servicetypeIds.size(); s++) {
                calStmt.setString(s + partnerIds.size() + providerIds.size(), servicetypeIds.get(s - 1));
            }*/
            LOGGER.warn("GET_ALL_PBL_BILLPAID_CUS:"+GET_ALL_PBL_BILLPAID_CUS);
            ResultSet rs = calStmt.executeQuery();

            while (rs.next()) {
                PBLBillPaidCusDto billPaidCust = new PBLBillPaidCusDto();
                billPaidCust.setId(rs.getBigDecimal("Id"));
                billPaidCust.setPartnerId(rs.getString("idpartner"));
                billPaidCust.setServiceTypeId(rs.getString("idservicetype"));
                billPaidCust.setProviderId(rs.getString("idprovider"));
                billPaidCust.setCustomerCode(rs.getString("customercode"));
                billPaidCust.setCreatedDate(rs.getDate("createdated"));
                billPaidCust.setCusNo(rs.getString("custno"));
                billPaidcusts.add(billPaidCust);
            }

            return billPaidcusts;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            return billPaidcusts;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    /**
     * GetParameterForBillPaidWS
     *
     * @return
     * @throws Exception
     */
    public List<BillPaidCustGrpDto> getParameterForBillPaidWS() throws Exception {

        try {
            CallableStatement calStmt = connection.prepareCall(GET_PARAMATER_CALL_BILLPAID_WS);
            ResultSet rs = calStmt.executeQuery();

            List<BillPaidCustGrpDto> result = new ArrayList();
            while (rs.next()) {
                BillPaidCustGrpDto dto = new BillPaidCustGrpDto();
                dto.setParnerId(rs.getString("parnerId"));
                dto.setProviderId(rs.getString("providerId"));
                dto.setServiceTypeId(rs.getString("serviceTypeId"));
                result.add(dto);
            }

            return result;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
