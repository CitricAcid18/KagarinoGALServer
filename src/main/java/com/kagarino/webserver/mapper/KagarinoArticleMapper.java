package com.kagarino.webserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kagarino.webserver.entity.KagarinoArticle;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 帖表 Mapper 接口
 * </p>
 *
 * @author zwj
 * @since 2024-06-05
 */
@Mapper
public interface KagarinoArticleMapper extends BaseMapper<KagarinoArticle> {

}
