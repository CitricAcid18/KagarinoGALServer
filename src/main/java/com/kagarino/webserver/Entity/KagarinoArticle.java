package com.kagarino.webserver.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 帖表
 * </p>
 *
 * @author zwj
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kagarino_article")
public class KagarinoArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子ID主键
     */
    @TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;

    /**
     * 帖子标题
     */
    private String articleTitle;

    /**
     * 浏览数
     */
    private Integer articleScanCount;

    /**
     * 点赞数
     */
    private Integer articleLikeCount;

    /**
     * 标签
     */
    private String articleTag;

    /**
     * 评论数
     */
    private Integer articleCommentCount;

    /**
     * 作者ID
     */
    private Long articleAuthorId;

    /**
     * 作者名
     */
    private String articleAuthorName;

    /**
     * 帖子内容
     */
    private String articleContent;

    /**
     * 发帖时间
     */
    private Date articleGmtRelease;

    /**
     * 帖子配图
     */
    private String articlePortrait;

    /**
     * 帖子配图
     */
    private String articleIllustration;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;


}
