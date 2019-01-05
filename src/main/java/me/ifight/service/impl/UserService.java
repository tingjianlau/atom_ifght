package me.ifight.service.impl;

import me.ifight.dao.UserDao;
import me.ifight.model.UserVO;
import me.ifight.service.itf.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

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
}
