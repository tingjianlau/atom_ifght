package me.ifight.dao;

import me.ifight.model.UserVO;

public interface UserDao {

    UserVO getUserById(int id);
    int addUser(UserVO userVO);
}
