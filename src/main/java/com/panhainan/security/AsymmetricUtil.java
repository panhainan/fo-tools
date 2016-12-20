package com.panhainan.security;

import com.panhainan.security.asymmetric.RSAUtil;
import org.apache.commons.codec.binary.Base64;

import java.util.HashMap;
import java.util.Map;

/**
 * 非对称加密工具类
 * @author panhainan
 * @email panhainan@yeah.net
 * @date 2016-12-20
 */
public class AsymmetricUtil {

    public static void main(String[] args) {
        String data = "Java加密解密的艺术";
        Map<String,Object> map = AsymmetricUtil.encryptRSA(data);
        System.out.println("encryptRSA:\t"+map);
        String encryptData  = (String) map.get("encryptData");
        String publicKeyStr  = (String) map.get("publicKeyStr");
        System.out.println("decryptDES:\t"+AsymmetricUtil.decryptRSA(encryptData, publicKeyStr));
    }

    /**
     * RSA 加密
     *
     * @param data 待加密数据
     * @return Map {"publicKeyStr":Base64 CC编码后的公钥,"encryptData":RSA加密后经过Base64 CC编码的数据}
     */
    public static Map<String, Object> encryptRSA(String data) {
        Map<String, Object> map = new HashMap<>();
        String encryptData = null;
        String publicKeyStr = null;
        try {
            // 初始化密钥
            Map<String, Object> keyMap = RSAUtil.initKey();
            byte[] publicKey = RSAUtil.getPublicKey(keyMap);
            byte[] privateKey = RSAUtil.getPrivateKey(keyMap);
            publicKeyStr = Base64.encodeBase64String(publicKey);
            // 加密
            encryptData = Base64.encodeBase64String(RSAUtil.encryptByPrivateKey(data.getBytes(), privateKey));
        } catch (Exception e) {
            throw new RuntimeException("RSA 加密失败！", e);
        }
        map.put("publicKeyStr", publicKeyStr);
        map.put("encryptData", encryptData);
        return map;
    }

    /**
     * PBE 解密
     *
     * @param encryptData  待解密的Base64编码数据
     * @param publicKeyStr Base64编码的公钥
     * @return String 解密数据
     */
    public static String decryptRSA(String encryptData, String publicKeyStr) {
        String data = null;
        try {
            // 初始化密钥
            byte[] key = Base64.decodeBase64(publicKeyStr);
            // 解密
            data = new String(RSAUtil.decryptByPublicKey(Base64.decodeBase64(encryptData), key));
        } catch (Exception e) {
            throw new RuntimeException("RSA 解密失败！", e);
        }
        return data;
    }
}
