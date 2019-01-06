package me.ifight.service.impl;

import me.ifight.dao.UserDao;
import me.ifight.model.LoginDetail;
import me.ifight.service.itf.ILoginService;
import me.ifight.service.itf.ITokenDetail;
import me.ifight.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private TokenUtils tokenUtils;

    public LoginServiceImpl() {
    }

    /*
    @Autowired
    public LoginServiceImpl(UserDao userDao, ITokenUtils tokenUtils) {
        this.userDao= userDao;
        this.tokenUtils = tokenUtils;
    }
    */

    @Override
    public LoginDetail getLoginDetail(String username) {
        return userDao.getUserDetail(username);
    }

    @Override
    public String generateToken(ITokenDetail tokenDetail) {
        return tokenUtils.generateToken(tokenDetail);
    }
}
