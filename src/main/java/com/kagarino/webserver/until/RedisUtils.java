package com.kagarino.webserver.until;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @Author: zwj
 * @Date: 2024/6/4 11:34
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * @Auther: zwj
     * @Date: 2024/6/9 15:55
     * @Description: TODO 写入缓存，字符串类型
     * @Params: 键 值 存活时间（-1为不限时间）
     * @Return:
     */
    public boolean set(String key, String value, long time) {
        boolean result = false;
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            result = true;
        } catch (Exception e) {
            throw new ServiceException().setDetailMessage("redis set 异常/n"+e.getMessage());
        }
        return result;
    }

    /**
     * 更新缓存
     */
    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
