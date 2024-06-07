package com.kagarino.webserver.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kagarino.webserver.Entity.KagarinoComment;
import com.kagarino.webserver.Mapper.KagarinoCommentMapper;
import com.kagarino.webserver.Service.KagarinoCommentService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author zwj
 * @since 2024-06-05
 */
@Mapper
@Service
public class KagarinoCommentServiceImpl extends ServiceImpl<KagarinoCommentMapper, KagarinoComment> implements KagarinoCommentService {

}
