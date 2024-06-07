package com.kagarino.webserver.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kagarino.webserver.Entity.KagarinoUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zwj
 * @since 2024-06-05
 */

public interface KagarinoUserService extends IService<KagarinoUser> {
    Boolean isUsernameExist(String username);

    Boolean isMailExist(String mail);
}
