package com.kagarino.webserver;

import com.kagarino.webserver.annotation.Login;
import com.kagarino.webserver.controller.KagarinoUserController;
import com.kagarino.webserver.mapper.KagarinoUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    @Autowired
    KagarinoUserMapper kagarinoUserMapper;

    @Test
    public void createUserTest(){
        String username = "zwj123";
        String password = "123";
        String mail = "x3011703521@163.com";
        String code = "123456";
//        Result<String> res=kagarinoUserController.createUser(username,password,mail,code);
//        Assert.assertEquals(ResultEnum.CONFLICT.code,res.getCode());
    }

    @Test
    @Login
//    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void tempTest(){
        System.out.println(kagarinoUserMapper.updataUserMsgById(1L,"123123123"));
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}
