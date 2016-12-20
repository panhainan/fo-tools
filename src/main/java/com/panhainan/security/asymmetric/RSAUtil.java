package com.panhainan.security.asymmetric;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by FirePan on 2016/12/15.
 */
public class RSAUtil {

    public static void main(String[] args) throws Exception {
        /**
         * 公钥
         */
        byte[] publicKey;

        /**
         * 私钥
         */
        byte[] privateKey;
        // 初始化密钥
        Map<String, Object> keyMap = RSAUtil.initKey();

        publicKey = RSAUtil.getPublicKey(keyMap);
        privateKey = RSAUtil.getPrivateKey(keyMap);

        System.err.println("公钥: \n" + Base64.encodeBase64String(publicKey));
        System.err.println("私钥： \n" + Base64.encodeBase64String(privateKey));
        System.err.println("\n---私钥加密——公钥解密---");

        String inputStr1 = "RSA加密算法";
        byte[] data1 = inputStr1.getBytes();
        System.err.println("原文:\n" + inputStr1);

        // 加密
        byte[] encodedData1 = RSAUtil.encryptByPrivateKey(data1, privateKey);
        System.err.println("加密后:\n" + Base64.encodeBase64String(encodedData1));

        // 解密
        byte[] decodedData1 = RSAUtil.decryptByPublicKey(encodedData1,
                publicKey);
        String outputStr1 = new String(decodedData1);
        System.err.println("解密后:\n" + outputStr1);


        System.err.println("\n---公钥加密——私钥解密---");
        String inputStr2 = "RSA Encypt Algorithm";
        byte[] data2 = inputStr2.getBytes();
        System.err.println("原文:\n" + inputStr2);

        // 加密
        byte[] encodedData2 = RSAUtil.encryptByPublicKey(data2, publicKey);
        System.err.println("加密后:\n" + Base64.encodeBase64String(encodedData2));

        // 解密
        byte[] decodedData2 = RSAUtil.decryptByPrivateKey(encodedData2,
                privateKey);
        String outputStr2 = new String(decodedData2);
        System.err.println("解密后: " + outputStr2);
    }
    /**
     * 非对称加密密钥算法
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 公钥
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 私钥
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA密钥长度
     * 默认1024位，
     * 密钥长度必须是64的倍数，
     * 范围在512至65536位之间。
     */
    private static final int KEY_SIZE = 512;

    /**
     * 私钥解密
     *
     * @param data
     *            待解密数据
     * @param key
     *            私钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key)
            throws Exception {

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     *
     * @param data
     *            待解密数据
     * @param key
     *            公钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key)
            throws Exception {

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 生成公钥
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     *
     * @param data
     *            待加密数据
     * @param key
     *            公钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key)
            throws Exception {

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 私钥加密
     *
     * @param data
     *            待加密数据
     * @param key
     *            私钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key)
            throws Exception {

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     *            密钥Map
     * @return byte[] 私钥
     * @throws Exception
     */
    public static byte[] getPrivateKey(Map<String, Object> keyMap)
            throws Exception {

        Key key = (Key) keyMap.get(PRIVATE_KEY);

        return key.getEncoded();
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     *            密钥Map
     * @return byte[] 公钥
     * @throws Exception
     */
    public static byte[] getPublicKey(Map<String, Object> keyMap)
            throws Exception {

        Key key = (Key) keyMap.get(PUBLIC_KEY);

        return key.getEncoded();
    }

    /**
     * 初始化密钥
     *
     * @return Map 密钥Map
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {

        // 实例化密钥对生成器
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);

        // 初始化密钥对生成器
        keyPairGen.initialize(KEY_SIZE);

        // 生成密钥对
        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        // 封装密钥
        Map<String, Object> keyMap = new HashMap<String, Object>(2);

        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        return keyMap;
    }
}
