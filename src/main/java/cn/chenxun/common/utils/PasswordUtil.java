package cn.chenxun.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码工具类
 *
 * @author zhaozhenyao
 * @date 2018/5/13
 */
public class PasswordUtil {

    /**
     * 加密密码
     *
     * @param password 明文密码
     * @param salt     盐
     * @return 密文密码
     */
    public static String encrypt(String password, String salt) {
        ByteSource bytes = ByteSource.Util.bytes(salt);
        return new SimpleHash("MD5", password, bytes, 4).toHex();
    }

    public static void main(String[] args) {
        String test = encrypt("123456", "test");
        System.out.println(test);

    }



}
