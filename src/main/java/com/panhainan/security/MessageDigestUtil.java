package com.panhainan.security;

import com.panhainan.security.message_digest.MDUtil;
import com.panhainan.security.message_digest.SHAUtil;

/**
 * 消息摘要工具类
 * @author panhainan
 * @email panhainan@yeah.net
 * @date 2016-12-20
 */
public class MessageDigestUtil {
    public static void main(String[] args) {
        String data = "Java加密解密的艺术";
        System.out.println("MD5:\t"+MessageDigestUtil.encodeMD5ByCC(data));
        System.out.println("SHA1:\t"+MessageDigestUtil.encodeSHA1ToHex(data));
        System.out.println("SHA256:\t"+MessageDigestUtil.encodeSHA256ToHex(data));
        System.out.println("SHA384:\t"+MessageDigestUtil.encodeSHA384ToHex(data));
        System.out.println("SHA512:\t"+MessageDigestUtil.encodeSHA512ToHex(data));
    }
    /**
     * MD5 编码通过Apache Commons Codec实现
     * @param data 待编码数据
     * @return String 编码数据
     */
    public static String encodeMD5ByCC(String data) {
        String encryptData = null;
        try {
            encryptData = MDUtil.encodeMD5HexByCC(data);
        } catch (Exception e) {
            throw new RuntimeException("MD5 编码失败！", e);
        }
        return encryptData;
    }

    /**
     * SHA1 编码通过Apache Commons Codec实现
     * @param data 待编码数据
     * @return String 编码数据
     */
    public static String encodeSHA1ToHex(String data) {
        String encryptData = null;
        try {
            encryptData = SHAUtil.encodeSHAHex(data);
        } catch (Exception e) {
            throw new RuntimeException("SHA-1 编码失败！", e);
        }
        return encryptData;
    }
    /**
     * SHA256 编码通过Apache Commons Codec实现
     * @param data 待编码数据
     * @return String 编码数据
     */
    public static String encodeSHA256ToHex(String data) {
        String encryptData = null;
        try {
            encryptData = SHAUtil.encodeSHA256Hex(data);
        } catch (Exception e) {
            throw new RuntimeException("SHA-256 编码失败！", e);
        }
        return encryptData;
    }
    /**
     * SHA384 编码通过Apache Commons Codec实现
     * @param data 待编码数据
     * @return String 编码数据
     */
    public static String encodeSHA384ToHex(String data) {
        String encryptData = null;
        try {
            encryptData = SHAUtil.encodeSHA384Hex(data);
        } catch (Exception e) {
            throw new RuntimeException("SHA-384 编码失败！", e);
        }
        return encryptData;
    }
    /**
     * SHA512 编码通过Apache Commons Codec实现
     * @param data 待编码数据
     * @return String 编码数据
     */
    public static String encodeSHA512ToHex(String data) {
        String encryptData = null;
        try {
            encryptData = SHAUtil.encodeSHA512Hex(data);
        } catch (Exception e) {
            throw new RuntimeException("SHA-512 编码失败！", e);
        }
        return encryptData;
    }
}
