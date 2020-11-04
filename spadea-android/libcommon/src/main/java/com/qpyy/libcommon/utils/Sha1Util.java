package com.qpyy.libcommon.utils;


import com.qpyy.libcommon.BuildConfig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1Util {


    public static String shaEncode(long timestamp) {
        String val = "token" + SpUtils.getToken() + "timestamp" + timestamp + BuildConfig.SALT;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("SHA-1");
            md5.update(val.getBytes());
            byte[] m = md5.digest();//加密
            return byteArrayToHex(m).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String byteArrayToHex(byte[] byteArray) {
        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }

}
