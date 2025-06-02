/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import java.util.List;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.cw.CardInfo;
import scb.com.vn.payment.ControllerUtil;

/**
 *
 * @author minhndb
 */
public class CimsUtils {

    private static final Logger logger = Logger.getLogger(CimsUtils.class);

    /**
     *
     * @param cards
     * @return
     */
    public static boolean isMultipleCif(List<CardInfo> cards) {
        if (cards.size() > 1) {
            CardInfo info = cards.get(0);
            String cif = (info != null) ? info.getCif() : "";
            for (int i = 1; i < cards.size(); i++) {
                CardInfo item = cards.get(i);
                if (!cif.equals(item.getCif())) {
                    logger.warn("Danh sach the lay tu so dien thoai co nhieu hon 1 cif. "
                            + "Cif[0] = [" + cif + "], Cif[1] = [" + item.getCif() + "]");
                    return true;
                }
            }
        }
        return false;
    }

    private static final String MACISDIFFERENCE = "SCB = [%s] != %s = [%s]. MAC IS DIFFERENCE.";

    /**
     *
     * @param partner
     * @param value
     * @param macPartner
     * @return
     */
    public static boolean canAccess(String partner, String value, String macPartner) {
        try {
            String[] items = Helper.getDBI().ONL_GetMACkeys(partner);
            if (items.length > 0) {
                String md5Key = items[0];
                String macScb = ControllerUtil.EncriptMD5(value + md5Key);
                if (macScb.equalsIgnoreCase(macPartner)) {
                    return true;
                } else {
                    String info = String.format(MACISDIFFERENCE, macScb, partner, macPartner);
                    logger.warn(info);
                }
            } else {
                logger.warn("Does not found the md5 key with partner = [" + partner + "]");
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return false;
    }

    public static String buildMac(String macResponse, String partner) {
        String mac = "";
        try {
            String[] key = Helper.getDBI().ONL_GetMACkeys(partner);
            if (key.length > 0) {
                String md5Key = key[0];
                mac = ControllerUtil.EncriptMD5(macResponse + md5Key);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return mac;
    }

}
