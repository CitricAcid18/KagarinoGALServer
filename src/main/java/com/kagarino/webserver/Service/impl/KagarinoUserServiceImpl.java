package com.kagarino.webserver.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kagarino.webserver.Entity.KagarinoUser;
import com.kagarino.webserver.Mapper.KagarinoUserMapper;
import com.kagarino.webserver.Service.KagarinoUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zwj
 * @since 2024-06-05
 */
@Service
public class KagarinoUserServiceImpl extends ServiceImpl<KagarinoUserMapper, KagarinoUser> implements KagarinoUserService {

}
