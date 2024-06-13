package com.kagarino.webserver.controller;


import com.kagarino.webserver.entity.Result;
import com.kagarino.webserver.service.KagarinoUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zwj
 * @since 2024-06-05
 */
@Api(value = "测试",tags = "用户管理测试")
@RestController
@RequestMapping("/user")
public class KagarinoUserController {
    @Autowired
    private KagarinoUserService kagarinoUserService;

    /**
     * @Auther: zwj
     * @Date: 2024/6/5 21:56
     * @Description: TODO 注册
     * @Params: 用户名 用户密码 用户邮箱 验证码
     * @Return: 成功与否及提示
     */
    @ApiOperation(value = "用户注册",notes = "用户注册")
    @PostMapping("/welcome/logon")
    public Result<String> createUser(@ApiParam(name = "username",value = "用户名",required = true) String username,
                                     @ApiParam(name = "password",value = "用户密码",required = true)String password,
                                     @ApiParam(name = "mail",value = "用户邮箱",required = true)String mail,
                                     @ApiParam(name = "code",value = "验证码",required = true)String code){
        return kagarinoUserService.createUser(username,password,mail,code);
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/10 15:52
     * @Description: TODO 发送邮箱验证码
     * @Params: 用户邮箱
     * @Return: 成功与否及提示
     */
    @GetMapping("/welcome/logon")
    public Result<String> verifyMail(String mail){
        return kagarinoUserService.sendLogonMail(mail);
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/10 15:59
     * @Description: TODO 登录功能
     * @Params: 用户名 密码
     * @Return: 成功与否及提示
     */
    @PutMapping("/welcome/login")
    public Result<String> login(String username,String password){
        return kagarinoUserService.login(username,password);
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/10 21:29
     * @Description: TODO 重置密码
     * @Params: 用户名 用户邮箱 新密码 验证码
     * @Return: 成功与否及提示
     */
    @PutMapping("/welcome/reset")
    public Result<String> resetUserPassword(String username,String mail,String password,String code){
        return kagarinoUserService.resetUserPassword(username,mail,password,code);
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/10 15:52
     * @Description: TODO 发送重置密码邮箱验证码
     * @Params: 用户邮箱
     * @Return: 成功与否及提示
     */
    @GetMapping("/welcome/reset")
    public Result<String> resetMail(String mail){
        return kagarinoUserService.sendResetPasswordMail(mail);
    }
}

