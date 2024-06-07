package com.kagarino.webserver.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kagarino.webserver.Entity.KagarinoUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    @Select("select user_id from kagarino_user where user_name = #{username}")
    Integer isUsernameExist(@Param("username") String username);

    Integer isMailExist(String mail);
}
