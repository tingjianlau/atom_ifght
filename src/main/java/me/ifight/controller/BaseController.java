package me.ifight.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: Controller基类
 * @author: tjliu
 * @create: 2019-01-06 23:23
 **/
public class BaseController {
    public static String getUserName(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        for(Cookie cookie: cookies){
            if (cookie.getName().equalsIgnoreCase("username")){
                return cookie.getValue().toString();
            }
        }

        return null;
    }
}
