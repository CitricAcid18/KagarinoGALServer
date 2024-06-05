package com.kagarino.webserver.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author zwj
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KagarinoComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID主键
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    /**
     * 帖子ID
     */
    private Long commentArticleId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论时间
     */
    private Date commentGmtRelease;

    /**
     * 被评论ID
     */
    private Long commentDialogId;

    /**
     * 评论者用户名
     */
    private String commentName;

    /**
     * 被评论者用户名
     */
    private String commentDialogName;

    /**
     * 审核状态
     */
    private Integer commentState;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;


}
