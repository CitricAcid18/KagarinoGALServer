package com.kagarino.webserver.Controller;

import com.kagarino.webserver.Entity.Result;
import com.kagarino.webserver.Mapper.KagarinoUserMapper;
import com.kagarino.webserver.Service.KagarinoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zwj
 * @Date: 2024/6/5 21:55
 * @Version: v1.0.0
 * @Description: TODO 实现欢迎页相关功能的控制类
 **/
@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Autowired
    private KagarinoUserService kagarinoUserService;

    /**
     * @Auther: zwj
     * @Date: 2024/6/5 21:56
     * @Description: TODO 注册
     * @Params: 用户名username 用户密码password 用户邮箱mail
     * @Return: 成功与否及提示
     */
    @PostMapping("/user")
    public Result<String> createUser(String username,String password,String mail){
        //验证该用户名是否唯一
//        kagarinoUserMapper.
        return null;
    }

}
