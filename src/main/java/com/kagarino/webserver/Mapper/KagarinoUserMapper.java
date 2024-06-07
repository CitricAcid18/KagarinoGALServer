package com.kagarino.webserver.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kagarino.webserver.Entity.KagarinoUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zwj
 * @since 2024-06-05
 */
@Mapper
public interface KagarinoUserMapper extends BaseMapper<KagarinoUser> {
    public Boolean isUsernameExist(String username);

    public Boolean isMailExist(String mail);
}
