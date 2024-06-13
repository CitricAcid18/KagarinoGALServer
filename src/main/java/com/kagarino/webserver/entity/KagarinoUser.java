package com.kagarino.webserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author zwj
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("用户实体类")
@TableName("kagarino_user")
public class KagarinoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID主键
     */
    @ApiModelProperty(value = "用户ID主键", required = true, example = "村雨遥")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户邮箱
     */
    private String userMail;

    /**
     * 注册时间
     */
    private Date userGmtLogon;

    /**
     * 登录天数
     */
    private Integer userLoginCount;

    /**
     * 发帖数量
     */
    private Integer userArticleCount;

    /**
     * 简介
     */
    private String userBrief;

    /**
     * 头像
     */
    private String userPortrait;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;


}
