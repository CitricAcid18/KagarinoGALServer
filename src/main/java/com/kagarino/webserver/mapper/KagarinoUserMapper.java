package com.kagarino.webserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kagarino.webserver.entity.KagarinoUser;
import org.apache.ibatis.annotations.*;

import java.util.Date;

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
    @Select("select count(user_id) from kagarino_user where user_name = #{username};")
    Integer isUsernameExist(@Param("username") String username);

    @Select("select count(user_id) from kagarino_user where user_mail = #{mail};")
    Integer isMailExist(@Param("mail") String mail);

    @Select("select count(user_name) from kagarino_user where user_id = #{id};")
    Integer isUserIdExist(@Param("id") Long id);

    @Insert("insert into kagarino_user (user_name, user_password, user_mail,user_gmt_logon)\n"+
            "values (#{username},#{password},#{mail},#{gmt});")
    Integer createUser(@Param("username") String username,
                       @Param("password") String password,
                       @Param("mail") String mail,
                       @Param("gmt") Date gmtLogon);
    @Select("select user_id,user_password,user_mail,user_login_count from kagarino_user where user_name = #{username};")
    KagarinoUser selectUserMsgByName(@Param("username") String username);

    @Update("UPDATE kagarino_user\n" +
            "SET user_login_count = #{loginCount}\n" +
            "WHERE user_id = #{id};")
    Integer updataUserLoginCount(@Param("id") Long id,@Param("loginCount") Integer loginCount);

    @Update("UPDATE kagarino_user\n" +
            "SET user_password = #{password}\n" +
            "WHERE user_id = #{id};")
    Integer updataUserMsgById(@Param("id") Long id,@Param("password") String password);

    @Update("UPDATE kagarino_user\n" +
            "SET user_name = #{username}\n" +
            "WHERE user_id = #{id};")
    Integer updataUserNameById(@Param("id") Long id,@Param("username") String username);
}
