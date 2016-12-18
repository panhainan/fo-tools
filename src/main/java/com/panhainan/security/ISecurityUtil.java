package com.panhainan.security;

import com.panhainan.security.impl.Base64Util;
import com.panhainan.security.impl.MDUtil;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by FirePan on 2016/12/15.
 */
public class ISecurityUtil {

    String base64Encode(String data) throws UnsupportedEncodingException {
        String encryptData = null;
        try {
            encryptData = Base64Util.encode(data);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }
        return encryptData;
    }

    String md5Encode(String data) throws NoSuchAlgorithmException {
        String encryptData = null;
        try {
            encryptData = MDUtil.encodeMD5(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        }
        return encryptData;
    }

    String md2Encode(String data) throws NoSuchAlgorithmException {
        String encryptData = null;
        try {
            encryptData = MDUtil.encodeMD2(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        }
        return encryptData;
    }

}
