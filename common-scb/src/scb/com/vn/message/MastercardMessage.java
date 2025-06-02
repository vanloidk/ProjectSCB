/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.message;

/**
 *
 * @author minhndb
 */
public class MastercardMessage
{
    public enum ReasonCodeEnum
    {
        /* CARDWORD ERROR CODE */
        PARTNER_DAILY_LIMIT("1"),
        MAX_TRANSACTION_LIMIT("2"),
        MIN_TRANSACTION_LIMIT("3"),
        CONS_MONTHLY_TRAN_LIMIT("4"),
        MAX_TRAN_TYPE_LIMIT("5"),
        MIN_TRAN_TYPE_LIMIT("6"),
        ACCOUNT_TYPE("7"),
        ACCOUNT_NOT_ELIGIBLE("8"),
        NETWORK_NOT_ELIGIBLE("9"),
        CURRENCY_NOT_SUPPORTED("10"),
        INVALID_INPUT_VALUE("11"),
        DECLINE("12"),
        AUTHENTICATION_FAILED("13"),
        INVALID_INPUT_LENGTH("14"),
        RESOURCE_ERROR("15"),
        
        UNKNOW_MASTER_MESSAGE("1102"),
        
        MASTERCARD_OK("3000"),
        MASTERCARD_OK_DBI_FAILED("3001"), 
        MASTERCARD_RETURN_DBI_FAILED("3002"),
        MASTERCARD_UNKNOWN("3003"),
        MASTERCARD_FAILED("3004"),
        
        ;
        
        private String code;

        private ReasonCodeEnum(String c)
        {
            code = c;
        }
        
        public static ReasonCodeEnum getFromValue(String value)
        {
            ReasonCodeEnum[] values = ReasonCodeEnum.values();
            for (ReasonCodeEnum val : values)
            {
                if (val.getValue().equals(value))
                {
                    return val;
                }
            }
            return ReasonCodeEnum.UNKNOW_MASTER_MESSAGE;
        }
        
        public static ReasonCodeEnum getFromName(String name)
        {
            ReasonCodeEnum result;
            try
            {
                result = ReasonCodeEnum.valueOf(name);
            }
            catch (Exception e)
            {
                result = ReasonCodeEnum.UNKNOW_MASTER_MESSAGE;
            }
            return result;
        }
        
        public String getValue()
        {
            return code;
        }
    }
    
    public static String getReasonCodeDescription(ReasonCodeEnum index)
    {
        switch (index)
        {
            case PARTNER_DAILY_LIMIT:
                return "Partner has exceeded the daily limit configured in the system";
            case MAX_TRANSACTION_LIMIT:
                return "Per transaction maximum amount limit reached";    
            case MIN_TRANSACTION_LIMIT:
                return "Amount is less than the minimum configured for the partner";    
            case CONS_MONTHLY_TRAN_LIMIT:
                return "Consumers monthly transaction limit reached";    
            case MAX_TRAN_TYPE_LIMIT:
                return "Per transaction maximum amount limit for the transaction type";
            case MIN_TRAN_TYPE_LIMIT:
                return "Amount is less than the minimum allowed for the transaction type";
            case ACCOUNT_TYPE:
                return "Account Type not supported for the partner";
            case ACCOUNT_NOT_ELIGIBLE:
                return "Account not eligible";
            case NETWORK_NOT_ELIGIBLE:
                return "Partner not on boarded for the network to reach account";
            case CURRENCY_NOT_SUPPORTED:
                return "Currency is not supported for the account";
            default:
                return index.toString();
        }
    }
}
