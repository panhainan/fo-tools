package com.panhainan.security.impl;

import com.panhainan.security.common.StringUtil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by FirePan on 2016/12/15.
 */
public class SHAUtil {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String data = "美剧：冰与火之歌";
        String encryptData = encodeSHA_1(data);
        System.out.println("明文："+data);
        System.out.println("SHA-1   -> 密文："+encryptData);
        encryptData = encodeSHA_256(data);
        System.out.println("SHA-256 -> 密文："+encryptData);
        encryptData = encodeSHA_384(data);
        System.out.println("SHA-384 -> 密文："+encryptData);
        encryptData = encodeSHA_512(data);
        System.out.println("SHA-512 -> 密文："+encryptData);
    }

    /**
     * 摘要长度为160的SHA-1算法加密
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encodeSHA_1(String data) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        String encryptData = StringUtil.byte2hex(messageDigest.digest(data.getBytes(StandardCharsets.UTF_8)));
        return encryptData;
    }
    /**
     * 摘要长度为256的SHA-256算法加密
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String  encodeSHA_256(String data) throws  NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        String encryptData = StringUtil.byte2hex(messageDigest.digest(data.getBytes(StandardCharsets.UTF_8)));
        return encryptData;
    }
    /**
     * 摘要长度为384的SHA-384算法加密
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String  encodeSHA_384(String data) throws  NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
        String encryptData = StringUtil.byte2hex(messageDigest.digest(data.getBytes(StandardCharsets.UTF_8)));
        return encryptData;
    }
    /**
     * 摘要长度为512的SHA-512算法加密
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encodeSHA_512(String data) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        String encryptData = StringUtil.byte2hex(messageDigest.digest(data.getBytes(StandardCharsets.UTF_8)));
        return encryptData;
    }
}
