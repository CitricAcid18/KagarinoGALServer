package com.kagarino.webserver.until;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Objects;

/**
 * @Author: zwj
 * @Date: 2024/6/9 10:28
 * @Version: v1.0.0
 * @Description: TODO 发送邮件
 **/
public class MailUtils {
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");
        mailSender.setUsername("3011703521@qq.com");
        mailSender.setPassword("pgyhbtsskjhaddig");
        return mailSender;
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/9 10:30
     * @Description: TODO 发送只有text内容的简单邮件
     * @Params:
     * @Return:
     */
    public void sendSimpleEmail(String subject, String text, String email) {
        try {
            JavaMailSenderImpl javaMailSender = javaMailSender();
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject(subject); //邮件的主题
            mailMessage.setText(text);
            mailMessage.setTo(email); //发送给谁
            mailMessage.setFrom(Objects.requireNonNull(javaMailSender.getUsername())); //谁发送的
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:
     * @Param: [subject:标题, text:内容, html,Boolean html:是否进行html解析
     * email:接收人的邮件地址, attachmentMap:附件的名称和文件路径]
     * @return: void
     * @Author: 你自己
     * @Date: 2020/2/27
     */
//    public void SendMimeEmail(String subject, String text, Boolean html,
//                              String email, Map<String, String> attachmentMap) throws MessagingException {
//        JavaMailSenderImpl javaMailSender = JavaMailSender();
//        //一个复杂的邮件`
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        //进行组装
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, html);
//        helper.setSubject(subject);
//        helper.setText(text, html);
//
//        //附件
//        if (attachmentMap != null) {
//            Iterator<Map.Entry<String, String>> iterator = attachmentMap.entrySet().iterator();
//            //map.entrySet()得到的是set集合，可以使用迭代器遍历
//            while (iterator.hasNext()) {
//                Map.Entry<String, String> entry = iterator.next();
//                helper.addAttachment(entry.getKey(),
//                        //附件名称,要写好文件的后缀,不要少写和写错
//                        new File(entry.getValue()));
//                //附件的文件地址,可以写绝对路径,若是相对路径,如./1.png,代表的是resources下的1.png
//            }
//        }
//        helper.setTo(email); //发送给谁
//        helper.setFrom(Objects.requireNonNull(javaMailSender.getUsername())); //谁发送的
//        javaMailSender.send(mimeMessage);
//    }
}
