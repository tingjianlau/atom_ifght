package me.ifight.dao;

import me.ifight.model.UserVO;

public interface UserMapper {

    UserVO getUserById(int id);
    int addUser(UserVO userVO);
}
