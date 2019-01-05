package me.ifight.service.impl;

import com.alibaba.fastjson.JSONObject;
import me.ifight.dao.FavoritesBeanDao;
import me.ifight.model.FavoritesBean;
import me.ifight.model.common.PageModel;
import me.ifight.model.common.PageVO;
import me.ifight.service.itf.IFavoritesService;
import me.ifight.utils.CrawlerUtils;
import me.ifight.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FavoritesServiceImpl implements IFavoritesService {
    public static final Logger logger = LoggerFactory.getLogger(FavoritesServiceImpl.class);

    @Resource
    private FavoritesBeanDao favoritesBeanDao;

    @Override
    public boolean insert(FavoritesBean pojo){
        logger.info("#insert, userName={}, siteUrl={}, level={}, category={}",
                pojo.getUserName(), pojo.getSiteUrl(), pojo.getLevelId(), pojo.getCategory());

        String siteUrl = pojo.getSiteUrl();

        String title = CrawlerUtils.parse(siteUrl).title();
        logger.info("title is {}", title);
        pojo.setSiteTitle(title);
        pojo.setAddTime(DateUtils.now());

        logger.info(JSONObject.toJSONString(pojo));

        return favoritesBeanDao.insert(pojo) >0 ? true : false;
    }

    @Override
    public boolean delete(int id){

        return true;
    }

    @Override
    public int insertList(List< FavoritesBean> pojos){
        return favoritesBeanDao.insertList(pojos);
    }

    @Override
    public List<FavoritesBean> select(FavoritesBean pojo){
        return favoritesBeanDao.select(pojo);
    }

    @Override
    public int update(FavoritesBean pojo){
        return favoritesBeanDao.update(pojo);
    }

    @Override
    public PageVO select(PageModel pageModel){
        logger.info("pageNum={}, pageSize={}", pageModel.getPageNum(), pageModel.getPageSize());
        int offset = pageModel.offset();
        List<FavoritesBean> favoritesBeans = favoritesBeanDao.selectByPage(offset, pageModel.getPageSize());
        long count = favoritesBeanDao.count();


        long pages = (long) Math.ceil((count * 1.0) / pageModel.getPageSize());
        return new PageVO(pageModel.getPageNum(), pageModel.getPageSize(), count, pages, favoritesBeans);
    }

}
