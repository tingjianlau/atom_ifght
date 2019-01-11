package me.ifight;

import com.alibaba.fastjson.JSON;
import me.ifight.dao.FavoritesBeanDao;
import me.ifight.model.FavoritesBean;
import me.ifight.model.WebSite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FavoritesTest {
    Logger logger = LoggerFactory.getLogger(FavoritesTest.class);

    @Autowired
    private FavoritesBeanDao favoritesBeanDao;

    @Test
    public void testInsert(){
        FavoritesBean pojo = new FavoritesBean();
        pojo.setUserName("l00452711");
        pojo.setSiteTitle("title");
        pojo.setId(0);
        pojo.setSiteUrl("www.ifight.me");
        WebSite webSite = new WebSite();
        webSite.setIntro("intor");
        webSite.setDomain("domain");
        webSite.setId(2672937);
        pojo.setWebSite(webSite);
        int id = favoritesBeanDao.insert(pojo);
        System.out.println(id);
    }

    @Test
    public void countTest(){
        long count = favoritesBeanDao.count();
        logger.info("count={}", count);
    }

    @Test
    public void selectByPage(){
        List<FavoritesBean> favoritesBeanList = favoritesBeanDao.selectByPage(1, 4);

        logger.info("count={}", favoritesBeanList.size());
        for (FavoritesBean favoritesBean : favoritesBeanList) {
            logger.info("result={}", JSON.toJSONString(favoritesBean));
            logger.info("website={}", JSON.toJSONString(favoritesBean.getWebSite()));
        }
    }

    @Test
    public void deleteTest(){
        int num1 = favoritesBeanDao.delete(333, "admin");
        int num2 = favoritesBeanDao.delete(21, "admin");
        logger.info("num1={}, num2={}", num1, num2);
    }
}
