/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import com.tonbeller.sironkyc.ws.data.xsd.BoData;
import com.tonbeller.sironkyc.ws.data.xsd.CustomField;
import com.tonbeller.sironkyc.ws.data.xsd.PersonData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import scb.com.vn.common.model.transfer.ReceivingInfo;
import vn.com.scb.bian.ws.KycScore_in;
import vn.com.scb.bian.ws.KycScore_inType;
import java.util.Collections;
import java.util.Date;
import org.apache.log4j.Logger;
import vn.com.scb.bian.ws.BranchInfoType;
import vn.com.scb.bian.ws.TransactionInfoType;

/**
 *
 * @author minhndb
 */
public class KYCUtils {

    private static final Logger LOGGER = Logger.getLogger(KYCUtils.class);

    private static final String MSB = "MSB";
    private static final String MSB_ID_FM = "YY";
    private static final String OCCKEY = "KYC_SP_10";
    private static final String ZIPCODE = "001";
    private static final String BRANCHCODE = "001";
    private static final String OCCUPATION = "";
    private static final String IS_EMPTY = " ";

    /**
     *
     * @param info
     * @return
     */
    public static KycScore_in convertToKYC02(ReceivingInfo info) {
        KycScore_in request = new KycScore_in();
        KycScore_inType param = new KycScore_inType();
        PersonData person = new PersonData();
        CustomField custom = new CustomField();
        String[] sName = info.getName();
        custom.setKey(OCCKEY);
        custom.setValue(OCCUPATION);

        person.setPersonID(info.getPersonId());
        if (info.getAddress() == null) {
            person.setHCountry(" ");
        } else {
            person.setHCountry("VN");
        } 
        person.setFirstName(sName[0]);
        if (info.getLastName() != null && !info.getLastName().isEmpty()) {
            person.setLastName(sName[1]);
        }
        /*Neu passno va cmnd khong co value thi truyen gia tri de gui sang KYC*/
        if (info.getPassNo() != null && !info.getPassNo().isEmpty()) {
            person.setPassNo(new String[]{info.getPassNo()});
        } else {
            person.setPassNo(new String[]{IS_EMPTY});
        }
        if (info.getBirthDate() != null && !info.getBirthDate().isEmpty()) {
            person.setBirthDate(new String[]{info.getBirthDate()});
        } else {
            person.setBirthDate(new String[]{" "});
        }
        
        if (info.getNationality() == null) {
            person.setNatCountry(new String[]{" "});
        } else {
            person.setNatCountry(new String[]{info.getNationality()});
        } 
        person.setZipCode(ZIPCODE);
        person.setCustType(info.getCustType()); //KHCN ~ I, KHDN ~ C
        person.setCustomFields(new CustomField[]{custom});
        param.setPersons(person);

        BranchInfoType branchInfoType = new BranchInfoType();
        branchInfoType.setBranchCode(BRANCHCODE);

        TransactionInfoType transactionInfoType = new TransactionInfoType();
        transactionInfoType.setBranchInfo(branchInfoType);
        transactionInfoType.setClientCode("EBANK");

        String xref = "EBANK" + String.valueOf(System.currentTimeMillis());
        transactionInfoType.setCRefNum(xref);

        param.setTransactionInfo(transactionInfoType);
        param.setBeneficialOwnerInfo(new BoData());

        request.setKycScore_in(param);

        return request;
    }

    /**
     *
     * @param info
     * @return
     */
    public static KycScore_in convertToKYC(ReceivingInfo info) {
        KycScore_in request = new KycScore_in();
        KycScore_inType param = new KycScore_inType();
        PersonData person = new PersonData();
        CustomField custom = new CustomField();

        custom.setKey(OCCKEY);
        custom.setValue(OCCUPATION);

        person.setPersonID(info.getPersonId());
        person.setHCountry(info.getAddress());
        person.setFirstName(info.getFirstName());
        if (info.getLastName() != null && !info.getLastName().isEmpty()) {
            person.setLastName(info.getLastName());
        }

        person.setPassNo(new String[]{info.getPassNo()});
        if (info.getBirthDate() != null && !info.getBirthDate().isEmpty()) {
            person.setBirthDate(new String[]{info.getBirthDate()});
        }
        person.setNatCountry(new String[]{info.getNationality()});
        person.setZipCode(ZIPCODE);
        person.setCustType(info.getCustType()); //KHCN ~ I, KHDN ~ C
        person.setCustomFields(new CustomField[]{custom});
        param.setPersons(person);

        BranchInfoType branchInfoType = new BranchInfoType();
        branchInfoType.setBranchCode(BRANCHCODE);

        TransactionInfoType transactionInfoType = new TransactionInfoType();
        transactionInfoType.setBranchInfo(branchInfoType);
        transactionInfoType.setClientCode("EBANK");

        String xref = "EBANK" + String.valueOf(System.currentTimeMillis());
        transactionInfoType.setCRefNum(xref);

        param.setTransactionInfo(transactionInfoType);
        param.setBeneficialOwnerInfo(new BoData());

        request.setKycScore_in(param);

        return request;
    }

    /**
     *
     * @return
     */
    public static List<String> getKYCPartner() {
        String[] partner = Configuration.getProperty("kyc.partner").split(";");
        List<String> listPartner = new ArrayList<>();
        Collections.addAll(listPartner, partner);
        return listPartner;
    }

    /**
     *
     * @param partner
     * @return
     */
    public static String getKYCID(String partner) {
        String kycId = "";
        try {
            String[] items = Helper.getDBI().ONL_GetMACkeys(partner);
            if (items.length > 5) {
                kycId = items[5];
            } else {
                LOGGER.warn("Does not found the KYCID with partner = [" + partner + "]");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return kycId;
    }

    /**
     *
     * @param partner
     * @return
     */
    public static String getPersonId(String partner) {
        String result = "";
        try {
            String yy = formatDateTime(MSB_ID_FM);
            int ddd = getNumberOfYear();
            String dddFinal = String.format("%3s", ddd).replace(' ', '0');
            String nnn = getKYCID(partner);
            String sssss = String.valueOf(Helper.getDBI().getIdSeqByName("SEQ_MSB_PERSON_ID"));
            String sssssFinal = String.format("%5s", sssss).replace(' ', '0');
            result = MSB + yy + dddFinal + nnn + sssssFinal;
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }

    /**
     *
     * @param partern
     * @return
     */
    public static String formatDateTime(String partern) {
        String result = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(partern);
            result = sdf.format(new Date());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }

    /**
     *
     * @return
     */
    public static int getNumberOfYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_YEAR);
    }
}
