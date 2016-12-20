package com.panhainan.security.message_digest;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

/**
 * Created by FirePan on 2016/12/15.
 */
public class MDUtil {

    public static void main(String[] args) throws Exception {
        String data = "Java加密解密的艺术";
        System.out.println("明文：" + data);
        byte[] encryptData5 = encodeMD5(data.getBytes());
        System.out.println("密文：" + encryptData5);
        byte[] encryptData2 = encodeMD2(data.getBytes());
        System.out.println("密文：" + encryptData2);
        String datacc = "MD5Hex消息摘要";
        byte[] encryptData5cc = encodeMD5ByCC(datacc);
        System.out.println("密文：" + encryptData5cc);
        String encryptData2cc = encodeMD5HexByCC(datacc);
        System.out.println("密文：" + encryptData2cc);

    }

    /**
     * MD2加密
     *
     * @param data 待加密数据
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeMD2(byte[] data) throws Exception {
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("MD2");

        // 执行消息摘要
        return md.digest(data);
    }

    /**
     * MD5加密
     *
     * @param data 待加密数据
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeMD5(byte[] data) throws Exception {
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("MD5");

        // 执行消息摘要
        return md.digest(data);
    }

    /**
     * MD5加密
     *
     * @param data 待加密数据
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeMD5ByCC(String data) throws Exception {

        // 执行消息摘要
        return DigestUtils.md5(data);
    }

    /**
     * MD5加密
     *
     * @param data 待加密数据
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static String encodeMD5HexByCC(String data) throws Exception {

        // 执行消息摘要
        return DigestUtils.md5Hex(data);
    }
}
