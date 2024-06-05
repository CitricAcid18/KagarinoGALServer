package com.kagarino.webserver;

import com.kagarino.webserver.Until.RedisUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class WebServerApplicationTests {
    @Resource
    private RedisUtils redisUtils;
    @Test
    void contextLoads() {
    }

    /**
     * 插入缓存数据
     */
    @Test
    public void set() {
        redisUtils.set("redis_key", "redis_vale");
    }

    /**
     * 读取缓存数据
     */
    @Test
    public void get() {
        String value = redisUtils.get("redis_key");
        System.out.println(value);
    }

}
