package me.ifight.dao;

import me.ifight.model.User;
import me.ifight.model.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {

    UserVO getUserById(int id);
    int addUser(UserVO userVO);

    User getUserDetail(@Param("username") String username);
}
