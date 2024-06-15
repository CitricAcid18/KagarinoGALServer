package com.kagarino.webserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kagarino.webserver.entity.KagarinoUser;
import com.kagarino.webserver.entity.Result;
import com.kagarino.webserver.mapper.KagarinoUserMapper;
import com.kagarino.webserver.service.KagarinoUserService;
import com.kagarino.webserver.until.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    KagarinoUserMapper kagarinoUserMapper;
    @Autowired
    RedisUtils redisUtils;

    /**
     * @Auther: zwj
     * @Date: 2024/6/7 21:53
     * @Description: TODO 子功能，验证用户名是否已经存在
     * @Params: 用户名
     * @Return: true已存在/false不存在
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public boolean isUsernameExist(String username){
        return kagarinoUserMapper.isUsernameExist(username) > 0;
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/7 21:54
     * @Description: TODO 子功能，验证用户邮箱是否唯一
     * @Params: 用户邮箱
     * @Return: true唯一/false不唯一
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public boolean isMailExist(String mail) {
        return kagarinoUserMapper.isMailExist(mail) > 0;
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/10 21:13
     * @Description: TODO 更新登录天数
     * @Params: 用户id 用户登录天数
     * @Return:
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public boolean updataUserLoginCount(Long id,Integer loginCount) {
        return kagarinoUserMapper.updataUserLoginCount(id,loginCount) > 0;
    }

    /**
     * @Auther: zwj
     * @Date: 2024/6/9 10:31
     * @Description: TODO 发送注册邮箱验证码
     * @Params: 邮箱
     * @Return: 成功与否及提示
     */
    public Result<String> sendLogonMail(String mail){
        Result<String> res=new Result<>();
        //验证该邮箱是否唯一
        if(isMailExist(mail)){
            res.setData("邮箱已存在");
            return res.error(ResultEnum.CONFLICT.code,ResultEnum.CONFLICT.msg);
        }
        //发送邮箱验证码
        MailUtils mailUtils=new MailUtils();
        //生成随机验证码
        String code = KeyUtils.generateCode(6);
        //将邮箱验证码以邮件地址为key存入redis,1分钟过期
        if(redisUtils.set(mail, code, 62L)) {
            //发送注册邮件
            mailUtils.sendSimpleEmail("KagarinoGAL验证码", "新用户，您的验证码为：" + code + "(有效期为一分钟)", mail);
        }else{
            res.setData("邮箱验证码发送失败");
            return res.error(ResultEnum.ERROR.code,ResultEnum.ERROR.msg);
        }
        res.setData("邮箱验证码发送成功");
        return res.success(ResultEnum.SUCCESS.code,ResultEnum.SUCCESS.msg);
    }

    /**
     * @Auther: zwj
     * @Date: 2024/6/5 21:56
     * @Description: TODO 注册
     * @Params: 用户名username 用户密码password 用户邮箱mail
     * @Return: 成功与否及提示
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Result<String> createUser(String username,String password,String mail,String code){
        Result<String> res=new Result<>();
        //验证该用户名是否唯一
        if(isUsernameExist(username)){
            res.setData("用户名已存在");
            return res.error(ResultEnum.CONFLICT.code,ResultEnum.CONFLICT.msg);
        }
        //验证该邮箱是否唯一
        if(isMailExist(mail)){
            res.setData("邮箱已存在");
            return res.error(ResultEnum.CONFLICT.code,ResultEnum.CONFLICT.msg);
        }
        //验证邮箱验证码是否正确
        //检查验证码是否过期
        String logonCode = redisUtils.get(mail);
        if(logonCode == null){
            res.setData("验证码已过期");
            return res.error(ResultEnum.MOVED_PERM.code,ResultEnum.MOVED_PERM.msg);
        }
        //比对验证码
        if(!logonCode.equals(code)) {
            redisUtils.delete(mail);
            res.setData("验证码不正确");
            return res.error(ResultEnum.UNSUPPORTED_TYPE.code,ResultEnum.UNSUPPORTED_TYPE.msg);
        }
        //加密密码
        String salt=KeyUtils.salt();
        password = salt+KeyUtils.encrypt(password,salt);
        //注册用户
        Date currentDate =new Date();
        if(kagarinoUserMapper.createUser(username,password,mail,currentDate)!=1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            res.setData("插入用户失败");
            return res.success(ResultEnum.BAD_SQL.code,ResultEnum.BAD_SQL.msg);
        }
        res.setData("用户注册成功");
        return res.success(ResultEnum.CREATED.code,ResultEnum.CREATED.msg);
    }

    /**
     * @Auther: zwj
     * @Date: 2024/6/10 20:15
     * @Description: TODO 登录
     * @Params: 用户名 密码
     * @Return: 成功与否及其提示
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Result<String> login(String username,String password){
        Result<String> res=new Result<>();
        //验证该用户名是否存在
        if(!isUsernameExist(username)){
            res.setData("用户名不存在");
            return res.error(ResultEnum.BAD_REQUEST.code,ResultEnum.BAD_REQUEST.msg);
        }
        //查询数据库用户对象，获取用户密码，用户id，用户邮箱，用户登录天数
        KagarinoUser user=kagarinoUserMapper.selectUserMsgByName(username);
        //比对密码
        String truePassword=user.getUserPassword();
        String salt=truePassword.substring(0,KeyUtils.saltLength);
        password=KeyUtils.encrypt(password,salt);
        if(!password.equals(truePassword)){
            res.setData("用户名或密码错误");
            return res.error(ResultEnum.BAD_REQUEST.code,ResultEnum.BAD_REQUEST.msg);
        }
        //制作用户token
        Map<String,String> map=new HashMap<>();
        map.put("id",user.getUserId().toString());
        map.put("username",username);
        map.put("mail",user.getUserMail());
        map.put("lastLoginTime",new Date().toString());
        String token = JWTUtils.getToken(map);
        //更新登录天数
        updataUserLoginCount(user.getUserId(),user.getUserLoginCount()+1);
        //返回用户token
        res.setData(token);
        return res.success(ResultEnum.SUCCESS.code,ResultEnum.SUCCESS.msg);
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/9 10:31
     * @Description: TODO 发送重置密码邮箱验证码
     * @Params: 邮箱
     * @Return: 成功与否及提示
     */
    public Result<String> sendResetPasswordMail(String mail){
        Result<String> res=new Result<>();
        //验证该邮箱是否存在
        if(!isMailExist(mail)){
            res.setData("邮箱不存在");
            return res.error(ResultEnum.BAD_REQUEST.code,ResultEnum.BAD_REQUEST.msg);
        }
        //发送邮箱验证码
        MailUtils mailUtils=new MailUtils();
        //生成随机验证码
        String code = KeyUtils.generateCode(8);
        //将邮箱验证码以邮件地址为key存入redis,1分钟过期
        if(redisUtils.set(mail, code, 62L)) {
            //发送重置密码邮件
            mailUtils.sendSimpleEmail("KagarinoGAL重置密码验证码", "您好，您的验证码为：" + code + "(有效期为一分钟)", mail);
        }else{
            res.setData("邮箱验证码发送失败");
            return res.error(ResultEnum.ERROR.code,ResultEnum.ERROR.msg);
        }
        res.setData("邮箱验证码发送成功");
        return res.success(ResultEnum.SUCCESS.code,ResultEnum.SUCCESS.msg);
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/10 21:29
     * @Description: TODO 重置密码
     * @Params: 用户名 用户邮箱 新密码 验证码
     * @Return: 成功与否及提示
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Result<String> resetUserPassword(String username,String mail,String password,String code){
        Result<String> res=new Result<>();
        //验证该用户名是否存在
        if(!isUsernameExist(username)){
            res.setData("用户名不存在");
            return res.error(ResultEnum.BAD_REQUEST.code,ResultEnum.BAD_REQUEST.msg);
        }
        //验证该邮箱是否属于该用户
        KagarinoUser user=kagarinoUserMapper.selectUserMsgByName(username);
        if(!mail.equals(user.getUserMail())){
            redisUtils.delete(mail);
            res.setData("用户邮箱不正确");
            return res.error(ResultEnum.BAD_REQUEST.code,ResultEnum.BAD_REQUEST.msg);
        }
        //验证邮箱验证码是否正确
        //检查验证码是否过期
        String resetCode = redisUtils.get(mail);
        if(resetCode == null){
            res.setData("验证码已过期");
            return res.error(ResultEnum.MOVED_PERM.code,ResultEnum.MOVED_PERM.msg);
        }
        //比对验证码
        if(!resetCode.equals(code)) {
            redisUtils.delete(mail);
            res.setData("验证码不正确");
            return res.error(ResultEnum.UNSUPPORTED_TYPE.code,ResultEnum.UNSUPPORTED_TYPE.msg);
        }
        //加密密码
        String salt=KeyUtils.salt();
        password = salt+KeyUtils.encrypt(password,salt);
        //更新用户密码
        if(kagarinoUserMapper.updataUserMsgById(user.getUserId(),password)!=1){
            throw new RuntimeException("用户修改密码失败");
        }
        res.setData("用户修改密码成功");
        return res.success(ResultEnum.SUCCESS.code,ResultEnum.SUCCESS.msg);
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/15 10:06
     * @Description: TODO 修改用户名
     * @Params: 用户名
     * @Return: 成功与否及提示
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Result<String> changeUsername(String id,String username){
        Result<String> res=new Result<>();
        //查询用户对象
        Long tempId=Long.parseLong(id);
        KagarinoUser user=kagarinoUserMapper.selectById(tempId);
        //验证用户是否存在
        if(user==null){
            res.setData("用户不存在，请重新登录");
            return res.error(ResultEnum.BAD_REQUEST.code,ResultEnum.BAD_REQUEST.msg);
        }
        //验证用户名是否与之前相同
        if(user.getUserName().equals(username)){
            res.setData("用户名相同，无需更改");
            return res.error(ResultEnum.BAD_REQUEST.code,ResultEnum.BAD_REQUEST.msg);
        }
        //验证修改后用户名是否重复
        if(kagarinoUserMapper.isUsernameExist(username)>0){
            res.setData("用户名已存在，请换一个用户名");
            return res.error(ResultEnum.BAD_REQUEST.code,ResultEnum.BAD_REQUEST.msg);
        }
        //改用户名
        if(kagarinoUserMapper.updataUserNameById(tempId,username)!=1){
            throw new RuntimeException("用户修改用户名失败");
        }
        res.setData("用户修改用户名成功");
        return res.success(ResultEnum.SUCCESS.code,ResultEnum.SUCCESS.msg);
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/15 11:08
     * @Description: TODO 修改简介
     * @Params: 简介
     * @Return: 成功与否及提示
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Result<String> changeBrief(String id,String brief){
        Result<String> res=new Result<>();
        Long tempId=Long.parseLong(id);
        //验证用户是否存在
        if(kagarinoUserMapper.isUserIdExist(tempId)<1){
            res.setData("用户不存在，请重新登录");
            return res.error(ResultEnum.BAD_REQUEST.code,ResultEnum.BAD_REQUEST.msg);
        }
        //改简介
        if(kagarinoUserMapper.updataUserNameById(tempId,brief)!=1){
            throw new RuntimeException("用户修改简介失败");
        }
        res.setData("用户修改简介成功");
        return res.success(ResultEnum.SUCCESS.code,ResultEnum.SUCCESS.msg);
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/15 11:16
     * @Description: TODO 用户修改头像
     * @Params: 用户图片
     * @Return: 成功与否及提示
     */
}
