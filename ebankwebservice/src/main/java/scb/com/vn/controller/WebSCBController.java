/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import scb.com.vn.dbi.dto.EbIssuecard;
import scb.com.vn.dbi.dto.ProcedureCallDto;
import scb.com.vn.model.RegisterIssueCardRp;
import scb.com.vn.model.RegisterIssueCardRq;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.Helper;

/**
 *
 * @author kimncm
 */
public class WebSCBController {

    final String _UNSUCESSFUL = "-1";

    /**
     *
     */
    public WebSCBController() {

    }

    /**
     *
     * @param xml
     * @return
     */
    public String RegisterIssueCard(String xml) {
        RegisterIssueCardRp response = new RegisterIssueCardRp();
        ProcedureCallDto rep = new ProcedureCallDto();
        try {

            RegisterIssueCardRq req = (RegisterIssueCardRq) Xml.unMarshaller(RegisterIssueCardRq.class, xml);

            //insert to DB
            EbIssuecard issueMC = new EbIssuecard();
            issueMC.setRegistertype("IM");
            issueMC.setIschecked('N');
            issueMC.setIdchannel("05");
            issueMC.setIssuebranchcode("000");

            issueMC.setFullname(req.getFullname());
            issueMC.setEmail(req.getEmail());
            issueMC.setExistotherbank(req.getExistotherbank().charAt(0));
            issueMC.setExistscb(req.getExistscb().charAt(0));
            issueMC.setTelCus(req.getTelCus());
            issueMC.setIncomegreaterfivem(req.getIncomegreaterfivem().charAt(0));

            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            Timestamp timestamp = Timestamp.valueOf(sdfDate.format(now));
            issueMC.setInsdate(timestamp);

            rep = Helper.getDBI().insertEbIssuecard(issueMC);
            response.setErrorCode(rep.getErrorStatus());
            response.setErrorMsg(rep.getErrorMessage());
            return Xml.Marshaller(response);

        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            //response.setErrorCode("-1");
            //response.setErrorMsg(e.toString());
            return _UNSUCESSFUL;
        }
    }

}
