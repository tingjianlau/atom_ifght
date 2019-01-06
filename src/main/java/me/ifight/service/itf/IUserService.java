package me.ifight.service.itf;

import me.ifight.model.UserVO;

public interface IUserService {

    UserVO queryUserById(int id);
    boolean addUser(UserVO userVO);


    /*
    LoginDetail getLoginDetail(String username);

    String generateToken(ITokenDetail tokenDetail);
    */
}
