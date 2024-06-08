package com.kagarino.webserver.Controller;

import com.kagarino.webserver.Entity.Result;
import com.kagarino.webserver.Service.KagarinoUserService;
import com.kagarino.webserver.Until.MailMsg;
import com.kagarino.webserver.Until.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

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
        Result<String> res=new Result<>();
        //验证该用户名是否唯一
        if(kagarinoUserService.isUsernameExist(username)){
            res.setData("用户名已存在");
            return res.error(ResultEnum.CONFLICT.code,ResultEnum.CONFLICT.msg);
        }
        //验证该邮箱是否唯一
        if(kagarinoUserService.isMailExist(mail)){
            res.setData("邮箱已存在");
            return res.error(ResultEnum.CONFLICT.code,ResultEnum.CONFLICT.msg);
        }
        //邮箱验证
        MailMsg mailMsg = new MailMsg();
        try {
            mailMsg.sendLogonMail(username,mail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
