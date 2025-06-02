/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.message;

/**
 *
 * @author lydty
 */
public class QRMessage {
      public enum ResultEnum {

        VALID("0"), 
        HOLD("-1"), // hold tien cho tra soat
        SIGNATURE_INVALID("-2"), // co the thanh toan lai
        BILL_INVALID("-3"),  // bill khong the thanh toan
        SERVICE_INVALID("-4"), // co the thanh toan lai
        BILL_SENDFAIL("-5"),  // co the thanh toan lai
        CHECKORDER_ERROR("-6"), // CHECK  QRSTRING ERROR
        ACCOUNT_INVALID("1"), 
        BALANCE_INVALID("2"), // khong du so du
        CORE_INVALID("3"), //co the thanh toan lai
        PROMOTIONCODE_INVALID("4"), // Ma giam gia khong kha dung
        AMOUNTORDER_INVALID("5"), // Ma giam gia khong kha dung
        PARTNERID_INVALID("6"), // Ma giam gia khong kha dung
        SYSTEM_ERR("98");
        private String code;

        private ResultEnum(String c) {
            code = c;
        }

        public static ResultEnum get(String value) {
            for (ResultEnum pce : ResultEnum.values()) {
                if (pce.getValue().equals(value)) {
                    return pce;
                }
            }
            return null;
        }

        public String getValue() {
            return code;
        }
    }
    
   
}
