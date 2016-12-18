package com.panhainan.security.impl;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Created by FirePan on 2016/12/15.
 */
public class Base64Util {
    public static String encode(String data) throws UnsupportedEncodingException {
        String encryptData = Base64.encode(data.getBytes(StandardCharsets.UTF_8));
        return encryptData;
    }
    public static String encode(byte[] data) throws UnsupportedEncodingException {
        String encryptData = Base64.encode(data);
        return encryptData;
    }

    public static String decode(String encryptData){
        String decodeData = new String(Base64.decode(encryptData), StandardCharsets.UTF_8);
        return decodeData;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String data = "美剧：冰与火之歌";
        System.out.println("明文："+data);
        String encryptData =  encode(data);
        System.out.println("密文："+encryptData);
        String decodeData =decode(encryptData);
        System.out.println("明文："+decodeData);
    }
}
