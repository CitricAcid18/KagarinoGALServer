package com.kagarino.webserver.Controller;

import com.kagarino.webserver.Until.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zwj
 * @Date: 2024/6/3 22:49
 * @Version: v1.0.0
 * @Description: TODO 测试
 **/
@RestController
public class HelloController {
    @Autowired
    RedisUtils redisUtils;
    /**
     * @Auther: zwj
     * @Date: 2024/6/3 22:53
     * @Description: TODO 测试控制器是否正常工作
     * @Params: void
     * @Return: void
     */
    @GetMapping("/hi")
    public String sayHi(){
        return "hi,zwj";
    }

    @GetMapping("/set-redis")
    public String setRedisValue(){
        return redisUtils.set("redis_key","123")+"";
    }

    @GetMapping("/get-redis")
    public String getRedisValue(){
        return redisUtils.get("redis_key");
    }
}
