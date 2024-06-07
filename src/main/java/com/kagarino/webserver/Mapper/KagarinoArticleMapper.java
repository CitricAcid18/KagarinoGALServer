package com.kagarino.webserver.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kagarino.webserver.Entity.KagarinoArticle;
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
