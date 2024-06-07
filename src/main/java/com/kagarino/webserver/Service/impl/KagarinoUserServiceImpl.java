package com.kagarino.webserver.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kagarino.webserver.Entity.KagarinoUser;
import com.kagarino.webserver.Mapper.KagarinoUserMapper;
import com.kagarino.webserver.Service.KagarinoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zwj
 * @since 2024-06-05
 */

@Service
public class KagarinoUserServiceImpl extends ServiceImpl<KagarinoUserMapper, KagarinoUser> implements KagarinoUserService {
    @Autowired
    KagarinoUserMapper kagarinoUserMapper;

    /**
     * @Auther: zwj
     * @Date: 2024/6/7 21:53
     * @Description: TODO 验证用户名是否已经存在
     * @Params: 用户名
     * @Return: true已存在/false不存在
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Boolean isUsernameExist(String username){
        return kagarinoUserMapper.isUsernameExist(username) > 0;
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/7 21:54
     * @Description: TODO 验证用户邮箱是否唯一
     * @Params: 用户邮箱
     * @Return: true唯一/false不唯一
     */
    public Boolean isMailExist(String mail) {
        return false;
    }
}
