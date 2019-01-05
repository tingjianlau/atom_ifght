package me.ifight;

import me.ifight.dao.BookBeanDao;
import me.ifight.dao.UserDao;
import me.ifight.model.BookBean;
import me.ifight.model.UserVO;
import me.ifight.service.impl.BookServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    UserDao userDao;

    @Autowired
    BookBeanDao bookBeanMapper;

    @Test
    public void insert() {
        List<String> urls = new ArrayList<>();
        String baseDir = "./data";
        File dir = new File(baseDir);
        File[] files = dir.listFiles();
        for (File file : files){
            String fileName = file.getName();
            String url = Paths.get(baseDir, fileName).toString();
            int index = Integer.parseInt(fileName.substring(2, fileName.indexOf(".")));
            BookBean bookBean = (new BookServiceImpl()).parseHtml(url);
            bookBean.setLink("http://www.dzsxzw.com/?p="+index);
            bookBeanMapper.insert(bookBean);
        }
    }

    @Test
    public void queryUser(){
        UserVO userVO = new UserVO();
        userVO.setUserName("lll");
        userVO.setPwd("123");
        userDao.addUser(userVO);
    }

}
