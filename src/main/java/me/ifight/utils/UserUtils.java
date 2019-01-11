package me.ifight.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description: 用户操作相关的工具类
 * @author: tjliu
 * @create: 2019-01-06 15:08
 **/
public class UserUtils {
    public static String encryPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    public static boolean match(String password, String encodedPwd){
        return new BCryptPasswordEncoder().matches(password, encodedPwd);
    }

    public static void main(String[] args) {
        System.out.println(UserUtils.encryPassword("123456"));
        System.out.println(UserUtils.match("123456", "$2a$10$qr6nr0iR/z.5zWQCJao2m.ynS0qHjO85h4a4q3.2jFkHaKaf2nadu"));
    }
}
