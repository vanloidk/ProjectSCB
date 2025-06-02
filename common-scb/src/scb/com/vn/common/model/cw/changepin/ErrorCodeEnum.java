/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cw.changepin;

/**
 *
 * @author minhndb
 */
public enum ErrorCodeEnum {
    SUCCESS("00"),
    FAILED("01"),
    INVALID_FORMAT("02"),
    INVALID_SIGNATURE("03"),
    SYSTEM_IS_ERROR("04"),
    INVALID_ACCESSTOKEN("05"),
    INVALID_REQUEST_MESSAGE("06"),
    OUT_OF_TIME("07"),
    MAC_IS_ERROR("08"),
    SYSTEM_IS_MAINTAINING("97"),
    TIMEOUT("98"),
    UNKNOW_ERROR_CODE("99");

    private String code;

    private ErrorCodeEnum(String c) {
        code = c;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ErrorCodeEnum getErrorCodeEnum(String value) {
        ErrorCodeEnum[] values = ErrorCodeEnum.values();
        for (ErrorCodeEnum val : values) {
            if (val.toString().equals(value)) {
                return val;
            }
        }
        return ErrorCodeEnum.UNKNOW_ERROR_CODE;
    }
}
