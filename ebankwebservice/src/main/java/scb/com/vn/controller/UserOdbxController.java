/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;
import scb.com.vn.constant.CommonConstant;
import scb.com.vn.model.users.UserDtoRq;
import scb.com.vn.model.users.UserDtoRp;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.GenOBDXPassword;
import scb.com.vn.utility.Helper;

/**
 *
 * @author loinv3
 */
public class UserOdbxController {

    final static Logger LOGGER = Logger.getLogger(UserOdbxController.class);
    //0: chua golive - 1: golive
    final String golive = Configuration.getProperty("sync.data.odbx.mb.golive");

    public String GetStatusUserOdbx(String xml) throws Exception {
        try {
            UserDtoRq userDto = (UserDtoRq) Xml.unMarshaller(UserDtoRq.class, xml);
            //ghi log request
            UserDtoRq userLog = new UserDtoRq();
            userLog.setUserName(userDto.getUserName());
            userLog.setPassword(userDto.getPassword());
            
            //userLog.setPassword(GenOBDXPassword.generateHash(userDto.getPassword(), userDto.getUserName()));
            LOGGER.info(Xml.Marshaller(userLog));

            UserDtoRp resp = new UserDtoRp();
            resp.setErrorCode(CommonConstant.RESPONSE_SUCCEED);
            resp.setStatus(CommonConstant.USER_UNKNOWN);

            if (userDto == null) {
                resp.setErrorCode(CommonConstant.RESPONSE_FAILED);
                resp.setErrorMsg("Input invalid.");
                return Xml.Marshaller(resp);
            }
            //kt trang thai user
            ArrayList listUser = Helper.getDBI().getUserOdbxDtl(userDto.getUserName());
            if (listUser.size() <= 0) {
                resp.setStatus(CommonConstant.USER_INVALID_NOT_FOUND);
                resp.setErrorMsg("User khong ton tai.");
                return Xml.Marshaller(resp);
            }

            resp.setGoLive(golive);
            boolean isCheck = Helper.getDBI().checkPassEB(userDto.getUserName(), userDto.getPassword());
            HashMap<?, ?> HMUser = (HashMap<?, ?>) listUser.get(0);
            //user da bi khoa
            if (!HMUser.get("lock_status").toString().equals("N")) {
                //kt trang thai khoa
                if ("KHOACHUDONG".equals(HMUser.get("u_description").toString().trim())) {
                    //user active - kt mk co dung khong
                    if (!isCheck) {
                        resp.setStatus(CommonConstant.USER_INVALID_PASS);
                        resp.setErrorMsg("User dang o trang thai chuyen doi dong mat khau khong dung.");
                        return Xml.Marshaller(resp);
                    }
                    //kt trang thai mk
                    if (HMUser.get("status") != null) {
                        switch (HMUser.get("status").toString()) {
                            case "CHUAKICHHOAT":
                                resp.setStatus(CommonConstant.USER_CHANGE_PASS_NO);
                                resp.setErrorMsg("User dang o trang thai chuyen doi va chua co thay doi mat khau.");
                                return Xml.Marshaller(resp);
                            case "DAKICHHOAT":
                                resp.setStatus(CommonConstant.USER_CHANGE_PASS_YES);
                                resp.setErrorMsg("User dang o trang thai chuyen doi va da co thay doi mat khau.");
                                return Xml.Marshaller(resp);
                            default:
                                break;
                        }
                    }
                }
                //pass dung 
                if (isCheck) {
                    switch (HMUser.get("status").toString()) {
                        case "CHUAKICHHOAT":
                            resp.setStatus(CommonConstant.USERLOCK_PASSYES_CHANGEPASSNO);
                            resp.setErrorMsg("User khoa - mat khau dung - chua thay doi mk.");
                            return Xml.Marshaller(resp);
                        case "DAKICHHOAT":
                            resp.setStatus(CommonConstant.USERLOCK_PASSYES_CHANGEPASSYES);
                            resp.setErrorMsg("User khoa - mat khau dung - da thay doi mk.");
                            return Xml.Marshaller(resp);
                        default:
                            break;
                    }
                }
                //pass sai
                if (!isCheck) {
                    resp.setStatus(CommonConstant.USERLOCK_PASSNO);
                    resp.setErrorMsg("User khoa - mat khau sai.");
                    return Xml.Marshaller(resp);
                }
            }
            //user active - kt mk co dung khong
            if (!isCheck) {
                resp.setStatus(CommonConstant.USER_INVALID_PASS);
                resp.setErrorMsg("User dang o trang thai hoat dong mat khau khong dung.");
                return Xml.Marshaller(resp);
            }

            //kt trang thai mk
            if (HMUser.get("status") != null) {
                switch (HMUser.get("status").toString()) {
                    case "CHUAKICHHOAT":
                        resp.setStatus(CommonConstant.USER_CHANGE_PASS_NO);
                        resp.setErrorMsg("User dang o trang thai hoat dong va chua co thay doi mat khau.");
                        return Xml.Marshaller(resp);
                    case "DAKICHHOAT":
                        resp.setStatus(CommonConstant.USER_CHANGE_PASS_YES);
                        resp.setErrorMsg("User dang o trang thai hoat dong va da co thay doi mat khau.");
                        return Xml.Marshaller(resp);
                    default:
                        break;
                }
            }
            return Xml.Marshaller(resp);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            UserDtoRp resp01 = new UserDtoRp();
            resp01.setStatus(CommonConstant.USER_UNKNOWN);
            resp01.setErrorCode(CommonConstant.RESPONSE_FAILED);
            resp01.setErrorMsg("co loi trong xu ly kiem tra thong tin user.");
            return Xml.Marshaller(resp01);
        }
    }
}
