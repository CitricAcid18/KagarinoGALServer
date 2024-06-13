package com.kagarino.webserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kagarino.webserver.entity.KagarinoComment;
import com.kagarino.webserver.mapper.KagarinoCommentMapper;
import com.kagarino.webserver.service.KagarinoCommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author zwj
 * @since 2024-06-05
 */
@Service
public class KagarinoCommentServiceImpl extends ServiceImpl<KagarinoCommentMapper, KagarinoComment> implements KagarinoCommentService {

}
