package me.ifight.model;

import javax.validation.constraints.NotNull;

/**
 * @version V1.0.0
 * @Description 用户登陆接口参数的实体类
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/10/3 1:29
 */
public class RequestLoginUser {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public RequestLoginUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RequestLoginUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
