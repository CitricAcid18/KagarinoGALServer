package com.kagarino.webserver.Until;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.util.UUID;

/**
 * @Author: zwj
 * @Date: 2024/6/8 16:02
 * @Version: v1.0.0
 * @Description: TODO 发送邮箱
 **/
@Component
public class MailMsg {

    @Resource
    private JavaMailSenderImpl mailSender;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * @Auther: zwj
     * @Date: 2024/6/8 16:14
     * @Description: TODO 发送验证码
     * @Params:
     * @Return:
     */
    public boolean sendLogonMail(String username,String email) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //生成随机验证码
        String code = generateCode(6);
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //设置一个html邮件信息
        helper.setText("<p style='color: blue'>"+username+"，您的验证码为：" + code + "(有效期为一分钟)</p>", true);
        //设置邮件主题名
        helper.setSubject("KagarinoGAL验证码");
        //发给谁-》邮箱地址
        helper.setTo(email);
        //谁发的-》发送人邮箱
        helper.setFrom("3011703521@qq.com");
        //将邮箱验证码以邮件地址为key存入redis,1分钟过期
        redisTemplate.opsForValue().set(email, code, Duration.ofMinutes(1));
        mailSender.send(mimeMessage);
        return true;
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/8 16:13
     * @Description: TODO 生成验证码
     * @Params: 验证码长度
     * @Return: 验证码
     */
    public String generateCode(int length){
        return UUID.randomUUID().toString().substring(0, length);
    }

}
