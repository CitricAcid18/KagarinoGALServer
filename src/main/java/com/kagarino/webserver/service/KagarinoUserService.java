package com.kagarino.webserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kagarino.webserver.entity.KagarinoUser;
import com.kagarino.webserver.entity.Result;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zwj
 * @since 2024-06-05
 */

public interface KagarinoUserService extends IService<KagarinoUser> {
    Result<String> sendLogonMail(String mail);
    Result<String> createUser(String username, String password, String mail, String code);
    Result<String> login(String username,String password);
    Result<String> sendResetPasswordMail(String mail);
    Result<String> resetUserPassword(String username,String mail,String password,String code);
    Result<String> changeUsername(String id,String username);
    Result<String> changeBrief(String id,String brief);
}
