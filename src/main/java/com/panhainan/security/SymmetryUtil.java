package com.panhainan.security;

import com.panhainan.security.symmetric.AESUtil;
import com.panhainan.security.symmetric.DESUtil;
import com.panhainan.security.symmetric.DESedeUtil;
import com.panhainan.security.symmetric.PBEUtil;
import org.apache.commons.codec.binary.Base64;

import java.util.HashMap;
import java.util.Map;

/**
 * 对称加密工具类
 * @author panhainan
 * @email panhainan@yeah.net
 * @date 2016-12-20
 */
public class SymmetryUtil {
    public static void main(String[] args) {
        String data = "Java加密解密的艺术";

        Map<String,Object> map = SymmetryUtil.encryptDES(data);
        System.out.println("encryptDES:\t"+map);
        String encryptData  = (String) map.get("encryptData");
        String keyStr  = (String) map.get("keyStr");
        System.out.println("decryptDES:\t"+SymmetryUtil.decryptDES(encryptData,keyStr));


        map = SymmetryUtil.encryptDESede(data);
        encryptData  = (String) map.get("encryptData");
        keyStr  = (String) map.get("keyStr");
        System.out.println("encryptDESede:\t"+map);
        System.out.println("decryptDESede:\t"+SymmetryUtil.decryptDESede(encryptData,keyStr));


        map = SymmetryUtil.encryptAES(data);
        encryptData  = (String) map.get("encryptData");
        keyStr  = (String) map.get("keyStr");
        System.out.println("encryptAES:\t"+map);
        System.out.println("decryptAES:\t" + SymmetryUtil.decryptAES(encryptData,keyStr));

        String pwd = "123456";
        map = SymmetryUtil.encryptPBE(data,pwd);
        encryptData  = (String) map.get("encryptData");
        keyStr  = (String) map.get("keyStr");
        System.out.println("encryptPBE:\t"+map);
        System.out.println("decryptPBE:\t" + SymmetryUtil.decryptPBE(encryptData,keyStr,pwd));


    }
    /**
     * DES 对称加密
     *
     * @param data 待加密数据
     * @return Map{"keyStr":Base64 CC编码后的密钥,"encryptData":DES加密后经过Base64 CC编码的数据}
     */
    public static Map<String, Object> encryptDES(String data) {
        Map<String, Object> map = new HashMap<>();
        String encryptData = null;
        String keyStr = null;
        try {
            byte[] key = DESUtil.initKey();
            keyStr = Base64.encodeBase64String(key);
            // 加密
            encryptData = Base64.encodeBase64String(DESUtil.encrypt(data.getBytes(), key));
        } catch (Exception e) {
            throw new RuntimeException("DES 加密失败！", e);
        }
        map.put("keyStr", keyStr);
        map.put("encryptData", encryptData);
        return map;
    }

    /**
     * DES 解密
     *
     * @param encryptData 待解密的Base64编码数据
     * @param keyStr      Base64编码的密钥
     * @return String 解密数据
     */
    public static String decryptDES(String encryptData, String keyStr) {
        String data = null;
        try {
            // 初始化密钥
            byte[] key = Base64.decodeBase64(keyStr);
            // 解密
            data = new String(DESUtil.decrypt(Base64.decodeBase64(encryptData), key));
        } catch (Exception e) {
            throw new RuntimeException("DES 解密失败！", e);
        }
        return data;
    }

    /**
     * DESede 对称加密
     *
     * @param data 待加密数据
     * @return Map {"keyStr":Base64 CC编码后的密钥,"encryptData":DESede 加密后经过Base64 CC编码的数据}
     */
    public static Map<String, Object> encryptDESede(String data) {
        Map<String, Object> map = new HashMap<>();
        String encryptData = null;
        String keyStr = null;
        try {
            // 初始化密钥
            byte[] key = DESedeUtil.initKey();
            keyStr = Base64.encodeBase64String(key);
            // 加密
            encryptData = Base64.encodeBase64String(DESedeUtil.encrypt(data.getBytes(), key));
        } catch (Exception e) {
            throw new RuntimeException("DESede 加密失败！", e);
        }
        map.put("keyStr", keyStr);
        map.put("encryptData", encryptData);
        return map;
    }

    /**
     * DESede 解密
     *
     * @param encryptData 待解密的Base64编码数据
     * @param keyStr      Base64编码的密钥
     * @return String 解密数据
     */
    public static String decryptDESede(String encryptData, String keyStr) {
        String data = null;
        try {
            // 初始化密钥
            byte[] key = Base64.decodeBase64(keyStr);
            // 解密
            data = new String(DESedeUtil.decrypt(Base64.decodeBase64(encryptData), key));
        } catch (Exception e) {
            throw new RuntimeException("DESede 解密失败！", e);
        }
        return data;
    }

    /**
     * AES 对称加密
     *
     * @param data 待加密数据
     * @return Map {"keyStr":Base64 CC编码后的密钥,"encryptData":AES加密后经过Base64 CC编码的数据}
     */
    public static Map<String, Object> encryptAES(String data) {
        Map<String, Object> map = new HashMap<>();
        String encryptData = null;
        String keyStr = null;
        try {
            // 初始化密钥
            byte[] key = AESUtil.initKey();
            keyStr = Base64.encodeBase64String(key);
            // 加密
            encryptData = Base64.encodeBase64String(AESUtil.encrypt(data.getBytes(), key));
        } catch (Exception e) {
            throw new RuntimeException("AES 加密失败！", e);
        }
        map.put("keyStr", keyStr);
        map.put("encryptData", encryptData);
        return map;
    }

    /**
     * AES 解密
     *
     * @param encryptData 待解密的Base64编码数据
     * @param keyStr      Base64编码的密钥
     * @return String 解密数据
     */
    public static String decryptAES(String encryptData, String keyStr) {
        String data = null;
        try {
            // 初始化密钥
            byte[] key = Base64.decodeBase64(keyStr);
            // 解密
            data = new String(AESUtil.decrypt(Base64.decodeBase64(encryptData), key));
        } catch (Exception e) {
            throw new RuntimeException("AES 解密失败！", e);
        }
        return data;
    }

    /**
     * PBE 基于口令的对称加密
     *
     * @param data 待加密数据
     * @param pwd  口令
     * @return Map {"keyStr":Base64 CC编码后的盐,"encryptData":PBE加密后经过Base64 CC编码的数据}
     */
    public static Map<String, Object> encryptPBE(String data, String pwd) {
        Map<String, Object> map = new HashMap<>();
        String encryptData = null;
        String saltStr = null;
        try {
            // 初始化盐
            byte[] salt = PBEUtil.initSalt();
            saltStr = Base64.encodeBase64String(salt);

            // 加密
            encryptData = Base64.encodeBase64String(PBEUtil.encrypt(data.getBytes(), pwd, salt));
        } catch (Exception e) {
            throw new RuntimeException("PBE 加密失败！", e);
        }
        map.put("keyStr", saltStr);
        map.put("encryptData", encryptData);
        return map;
    }

    /**
     * PBE 解密
     *
     * @param encryptData 待解密的Base64编码数据
     * @param keyStr      Base64编码的密钥
     * @param pwd         口令
     * @return String 解密数据
     */
    public static String decryptPBE(String encryptData, String keyStr, String pwd) {
        String data = null;
        try {
            // 拿到盐
            byte[] salt = Base64.decodeBase64(keyStr);
            // 解密
            data = new String(PBEUtil.decrypt(Base64.decodeBase64(encryptData), pwd, salt));
        } catch (Exception e) {
            throw new RuntimeException("PBE 解密失败！", e);
        }
        return data;
    }
}
