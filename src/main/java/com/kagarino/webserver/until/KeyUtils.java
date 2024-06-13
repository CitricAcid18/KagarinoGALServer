package com.kagarino.webserver.until;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/**
 * @Author: zwj
 * @Date: 2024/6/9 14:57
 * @Version: v1.0.0
 * @Description: TODO 生成验证码工具
 **/
public class KeyUtils {
    //盐字典
    private static char[] workBook = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private static String algorithm = "HmacSHA256";
    public static Integer saltLength = 32;
    /**
     * @Auther: zwj
     * @Date: 2024/6/8 16:13
     * @Description: TODO 生成验证码
     * @Params: 验证码长度
     * @Return: 验证码
     */
    public static String generateCode(int length){
        return UUID.randomUUID().toString().substring(0, length);
    }
    /**
     * @Auther: zwj
     * @Date: 2024/6/10 14:53
     * @Description: TODO 生成盐
     * @Params:
     * @Return: 长度为32的盐
     */
    public static String salt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(saltLength);
        for (int i = 0; i < saltLength; i++) {
            sb.append(workBook[random.nextInt(36)]);
        }
        return sb.toString();
    }

    /**
     * @Auther: zwj
     * @Date: 2024/6/10 15:21
     * @Description: TODO 加密
     * @Params: 密码 盐
     * @Return: 加密字符串
     */
    public static String encrypt(String password, String salt) {
        String cipher = "";
        try {
            byte[] key = salt.getBytes(StandardCharsets.UTF_8);
            //根据给定的字节数组构造一个密钥，第二个参数指定一个密钥的算法名称，生成专属密钥
            SecretKey secretKey = new SecretKeySpec(key, algorithm);
            //生成一个指定Mac算法的Mac对象
            Mac mac = Mac.getInstance(algorithm);
            //用给定密钥初始化Mac对象
            mac.init(secretKey);
            byte[] text = password.getBytes(StandardCharsets.UTF_8);
            byte[] encryptByte = mac.doFinal(text);
            cipher = bytesToHexStr(encryptByte);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e.getMessage());
        }
        return cipher;
    }

    /**
     * @Auther: zwj
     * @Date: 2024/6/10 15:20
     * @Description: TODO 加密结果转换为十六进制字符串
     * @Params: 二进制数组
     * @Return: 字符串
     */
    public static String bytesToHexStr(byte[] bytes) {
        StringBuilder hexStr = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexStr.append(hex);
        }
        return hexStr.toString();
    }

}
