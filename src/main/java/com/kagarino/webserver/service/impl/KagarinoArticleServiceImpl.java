package com.kagarino.webserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kagarino.webserver.entity.KagarinoArticle;
import com.kagarino.webserver.mapper.KagarinoArticleMapper;
import com.kagarino.webserver.service.KagarinoArticleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 帖表 服务实现类
 * </p>
 *
 * @author zwj
 * @since 2024-06-05
 */
@Service
public class KagarinoArticleServiceImpl extends ServiceImpl<KagarinoArticleMapper, KagarinoArticle> implements KagarinoArticleService {

}
