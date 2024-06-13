package com.kagarino.webserver;

import com.kagarino.webserver.controller.KagarinoUserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: zwj
 * @Date: 2024/6/8 9:26
 * @Version: v1.0.0
 * @Description: TODO 欢迎页功能测试
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WelcomeTest {

    @Autowired
    KagarinoUserController kagarinoUserController;

    @Test
    public void createUserTest(){
        String username = "zwj123";
        String password = "123";
        String mail = "x3011703521@163.com";
        String code = "123456";
//        Result<String> res=kagarinoUserController.createUser(username,password,mail,code);
//        Assert.assertEquals(ResultEnum.CONFLICT.code,res.getCode());
    }
}
