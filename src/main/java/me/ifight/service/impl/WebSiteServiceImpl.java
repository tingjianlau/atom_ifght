package me.ifight.service.impl;

import me.ifight.dao.WebSiteDao;
import me.ifight.model.WebSite;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WebSiteServiceImpl {

    @Resource
    private WebSiteDao webSiteDao;

    public int insert(WebSite pojo){
        return webSiteDao.insert(pojo);
    }

    public int insertList(List< WebSite> pojos){
        return webSiteDao.insertList(pojos);
    }

    public List<WebSite> select(WebSite pojo){
        return webSiteDao.select(pojo);
    }

    public int update(WebSite pojo){
        return webSiteDao.update(pojo);
    }

}
