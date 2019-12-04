package com.adwyxx.godzilla.common.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 加密工具，使用两次MD5+Solt加密
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/12/4 15:04
 */
public class EncryptUtil {
    //加密使用的Solt常量
    private static final String ENCRYPT_SOLT="God#z!l&l@%s*0Lt";
    //Base64加解密使用的字符集
    private static final String CHAR_SET="UTF-8";

    /**
     * 两次MD5加密,不加盐
     * @author: Leo.Wang, adwyxx@qq.com
     * @param data: 明文
     * @return String: 密文
     */
    public static String encodeByMD5(String data){
        if(StringUtils.isEmpty(data))
        {
            return data;
        }
        String cipher = DigestUtils.md5Hex(data);
        return DigestUtils.md5Hex(cipher);
    }
    /**
     * MD5+Solt 两次加密
     * @author: Leo.Wang, adwyxx@qq.com
     * @param data: 明文
     * @return String: 密文
     */
    public static String encodeByMD5WithSolt(String data){
        if(StringUtils.isEmpty(data))
        {
            return data;
        }
        String cipher = DigestUtils.md5Hex(data+ENCRYPT_SOLT);
        return DigestUtils.md5Hex(cipher+ENCRYPT_SOLT);
    }

    /**
     * Base64加密
     * @author: Leo.Wang, adwyxx@qq.com
     * @param data: 明文
     * @return String: 密文
     */
    public static String encodeByBase64(String data) throws UnsupportedEncodingException {
        if(StringUtils.isEmpty(data))
        {
            return data;
        }
        return new String(Base64.getEncoder().encode(data.getBytes(CHAR_SET)));
    }

    /**
     * Base64解密
     * @author: Leo.Wang, adwyxx@qq.com
     * @param data: 密文
     * @return String: 明文
     */
    public static String decodeByBase64(String data) throws UnsupportedEncodingException {
        if(StringUtils.isEmpty(data))
        {
            return data;
        }
        return new String(Base64.getDecoder().decode(data.getBytes(CHAR_SET)));
    }

}
