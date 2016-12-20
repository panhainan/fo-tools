package com.panhainan.security;


import org.apache.commons.codec.binary.Base64;


/**
 * Base64转码工具类
 * @author panhainan
 * @email panhainan@yeah.net
 * @date 2016-12-20
 */
public class Base64Util {

    public static void main(String[] args) throws Exception {
        String data = "Java加密解密的艺术";
        System.out.println("明文：" + data);
        String encryptData = Base64Util.encodeByCC(data);
        System.out.println("密文：" + encryptData);
        String decodeData = Base64Util.decodeByCC(encryptData);
        System.out.println("明文：" + decodeData);
    }

    /**
     * 字符编码
     */
    public final static String ENCODING = "UTF-8";

    /**
     * Base64编码的Commons Codec实现
     *
     * @param data 待编码数据
     * @return String 编码数据
     * @throws Exception
     */
    public static String encodeByCC(String data) {
        String encodeData = null;
        try {
            encodeData = Base64.encodeBase64String(data.getBytes(ENCODING));
        } catch (Exception e) {
            throw new RuntimeException("编码失败！", e);
        }
        return encodeData;
    }


    /**
     * Base64解码的Commons Codec实现
     *
     * @param encodeData 待解码数据
     * @return String 解码数据
     */
    public static String decodeByCC(String encodeData) {
        String data = null;
        try {
            // 执行解码
            byte[] b = Base64.decodeBase64(encodeData.getBytes(ENCODING));
            data = new String(b, ENCODING);
        } catch (Exception e) {
            throw new RuntimeException("解码失败！", e);
        }
        return data;
    }

}
