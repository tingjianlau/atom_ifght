package me.ifight.service.impl;

import me.ifight.dao.UserDao;
import me.ifight.model.User;
import me.ifight.model.UserDetailImpl;
import me.ifight.model.UserVO;
import me.ifight.service.itf.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public UserVO queryUserById(int id) {
        UserVO userVO = userDao.getUserById(id);

        if(userVO != null){
            logger.info(userVO.toString());
        }else{
            logger.info("Not Found!");
        }

        return userVO;
    }

    @Override
    public boolean addUser(UserVO userVO) {
        int insertNum = userDao.addUser(userVO); // 返回值为插入的条数
        logger.info("#addUser, insertNum: " + insertNum);
        logger.info("#addUser, insertID: " + userVO.getId());

        return insertNum > 0 ? true : false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserDetail(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return create(user);
        }
    }

    private static UserDetailImpl create(User user) {
        Collection<? extends GrantedAuthority> authorities;
        try {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
        } catch (Exception e) {
            authorities = null;
        }

        Date lastPasswordReset = new Date();
        lastPasswordReset.setTime(user.getLastPasswordChange());
        return new UserDetailImpl(
                user.getUsername(),
                user.getUsername(),
                user.getPassword(),
                lastPasswordReset,
                authorities,
                user.enable()
        );
    }
}
