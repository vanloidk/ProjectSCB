/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import scb.com.vn.model.PartnerHistoryTransferResp;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Constant;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.ResponseStatusCode;
import scb.com.vn.utility.ResponseStatusCode.RestransactionHistoryByDesc;
import vn.com.scb.bian.service.IIBCoreService;
import vn.com.scb.bian.partner.dto.PartnerTransactionInfoRespDto;
import vn.com.scb.bian.partner.dto.PartnerSCBTransactionInfoDto;
import vn.com.scb.bian.partner.dto.PartnerTransactionInfoRespDto.results;

/**
 *
 * @author lydty
 * @author loinv3
 */
public class AccountController {

    public String transactionHistoryByDesc(String xml) throws Exception {
        PartnerHistoryTransferResp response = new PartnerHistoryTransferResp();

        try {
            PartnerSCBTransactionInfoDto req = (PartnerSCBTransactionInfoDto) Xml.unMarshaller(PartnerSCBTransactionInfoDto.class, xml);
            //validate
            String rsValidate = accountValidate.getTranSactionHistoryByDescValidate(req, response);
            if (!Constant.VALIDATE_VALIDATE_FROM_SCB_SUCCEED.equals(rsValidate)) {
                return rsValidate;
            }

            //call request from partner tvsi
            IIBCoreService iibService = new IIBCoreService();
            //String urlEnpoint =  getProperty("rest.tvsi.url.address");
            //Configuration.getIIBContext().setServiceEndpoint(urlEnpoint);

            PartnerTransactionInfoRespDto tvsiTranInfoRes = iibService.getHistoryTransfer(Configuration.getIIBContext(), req);
            List<results> tvsiRs = tvsiTranInfoRes.getSelectTransactionInfoPartner_out().getResults();
            PartnerTransactionInfoRespDto.transactionInfo tvsiTranInfo = tvsiTranInfoRes.getSelectTransactionInfoPartner_out().getTransactionInfo();

            //Creat history transfer response of scb
            response.setTRANSID(req.getTRANSID() == null ? "" : req.getTRANSID());
            response.setPARTNER(req.getPARTNER() == null ? "" : req.getPARTNER());
            response.setRESPONSECODE(tvsiTranInfo.getTransactionErrorCode() == null ? "" : tvsiTranInfo.getTransactionErrorCode());
            response.setRESPONSEDESC(tvsiTranInfo.getTransactionErrorMsg() == null ? "" : tvsiTranInfo.getTransactionErrorMsg());
            response.setMAC(req.getMAC() == null ? "" : req.getMAC());

            List<PartnerHistoryTransferResp.transHistoryDtl> rslScb = creatHistoryTransfers(tvsiRs);
            response.setTransHistoryDtl(rslScb);

            return Xml.Marshaller(response);
        } catch (Exception ex) {
            Helper.writeLogErrorNonDB(AccountController.class, ex.getMessage());
            response.setRESPONSECODE(ResponseStatusCode.EXCEPTION);
            return Xml.Marshaller(response);
        }
    }

    /**
     * Creat list history transer response scb
     *
     * @param tvsiRs from scb
     * @return
     */
    private List<PartnerHistoryTransferResp.transHistoryDtl> creatHistoryTransfers(List<results> tvsiRs) {

        List<PartnerHistoryTransferResp.transHistoryDtl> rslScb = new ArrayList<PartnerHistoryTransferResp.transHistoryDtl>();
        for (PartnerTransactionInfoRespDto.results rs : tvsiRs) {

            PartnerHistoryTransferResp.transHistoryDtl newRs = new PartnerHistoryTransferResp.transHistoryDtl();
            newRs.setDIEN_GIAI(rs.getDIEN_GIAI());
            newRs.setLOAI_TIEN(rs.getLOAI_TIEN());
            newRs.setMA_DON_VI_TK(rs.getMA_DON_VI_TK());
            newRs.setNGAY_GIAO_DICH(rs.getNGAY_GIAO_DICH());
            newRs.setPS_NO_CO(rs.getPS_NO_CO());
            newRs.setSO_GIAO_DICH(rs.getSO_GIAO_DICH());
            newRs.setSO_TAI_KHOAN(rs.getSO_TAI_KHOAN());
            newRs.setSO_THU_TU_GD(rs.getSO_THU_TU_GD());
            newRs.setSO_TIEN_NGTE(rs.getSO_TIEN_NGTE());
            newRs.setSO_TIEN_QDOI(rs.getSO_TIEN_QDOI());
            newRs.setTHOI_GIAN_GD(rs.getTHOI_GIAN_GD());
            newRs.setTY_GIA(rs.getTY_GIA());
            rslScb.add(newRs);
        }
        return rslScb;
    }

    /**
     * accountValidate
     */
    protected static class accountValidate {

        /**
         * Get transaction history by desc validate
         *
         * @param req
         * @param response
         * @return
         * @throws Exception
         */
        private static String getTranSactionHistoryByDescValidate(PartnerSCBTransactionInfoDto req, PartnerHistoryTransferResp response) throws Exception {
            try {
                //Check PARTNER
                String[] key = Helper.getDBI().ONL_GetMACkeys(req.getPARTNER());

                if (key.length == 0) {
                    response.setRESPONSECODE(RestransactionHistoryByDesc.NOT_EXIST_PARTNER);
                    response.setRESPONSEDESC("NOT EXIST PARTNER");
                    return Xml.Marshaller(response);
                } else {
                    //MD5Key
                    //String MD5Key;
                    //MD5Key = key[0];
                    String inpusReqMac = req.getTRANSID() + req.getACCOUNTNUM() + req.getDESCRIPTION() + req.getFROMDATE() + req.getTODATE() + req.getPARTNER();
                    Helper.writeLog(AccountController.class, Level.INFO, "Data before encryption = ["
                            + inpusReqMac + "]");
                    //Check MAC
                    String strMAC = ControllerUtil.EncriptMD5(inpusReqMac);
                    if (!strMAC.toUpperCase().equals(req.getMAC().toUpperCase())) {
                        response.setRESPONSECODE(ResponseStatusCode.WRONG_MAC);
                        response.setRESPONSEDESC("WRONG MAC");
                        return Xml.Marshaller(response);
                    }
                }

                //Check account no
                Boolean isExistPartner = Helper.getDBI().isExistPartner(req.getACCOUNTNUM());
                if (!isExistPartner) {
                    response.setRESPONSECODE(RestransactionHistoryByDesc.ACCOUNTNO_NOT_EXIST_IN_PARTNER);
                    response.setRESPONSEDESC("ACCOUNTNO IS NOT EXIST IN PARTNER");
                }

                return Constant.VALIDATE_VALIDATE_FROM_SCB_SUCCEED;
            } catch (RemoteException ex) {
                Helper.writeLogErrorNonDB(AccountController.class, ex.getMessage());
                response.setRESPONSECODE(ResponseStatusCode.EXCEPTION);
                return Xml.Marshaller(response);
            }
        }
    }
}
