package me.ifight;

import me.ifight.dao.FavoritesBeanDao;
import me.ifight.model.FavoritesBean;
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
        List<FavoritesBean> favoritesBeanList = favoritesBeanDao.selectByPage(2, 4);

        logger.info("count={}", favoritesBeanList.size());
        for (FavoritesBean favoritesBean : favoritesBeanList){
            logger.info("id={}, url={}", favoritesBean.getId(), favoritesBean.getSiteUrl());
        }
    }

}
