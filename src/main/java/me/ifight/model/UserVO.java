package me.ifight.model;

public class UserVO {
    private String userName;
    private String id;
    private String pwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String userId) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString(){
        return "{userId:" + this.id + ", userName:" + this.userName + ", pwd:" + this.pwd + "}";
    }
}
