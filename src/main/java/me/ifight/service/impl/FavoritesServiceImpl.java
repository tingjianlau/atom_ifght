package me.ifight.service.impl;

import com.alibaba.fastjson.JSONObject;
import me.ifight.dao.FavoritesBeanDao;
import me.ifight.dao.WebSiteDao;
import me.ifight.model.FavoritesBean;
import me.ifight.model.WebSite;
import me.ifight.model.WebSiteInfoVo;
import me.ifight.model.common.PageModel;
import me.ifight.model.common.PageVO;
import me.ifight.service.itf.IFavoritesService;
import me.ifight.utils.CrawlerUtils;
import me.ifight.utils.DateUtils;
import me.ifight.utils.UrlUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

    @Resource
    private WebSiteDao webSiteDao;

    @Override
    public boolean insert(FavoritesBean pojo){
        logger.info("#insert, userName={}, siteUrl={}, level={}, category={}",
                pojo.getUserName(), pojo.getSiteUrl(), pojo.getLevel(), pojo.getCategory());

        String siteUrl = pojo.getSiteUrl();
        WebSite webSite = queryByDomain(siteUrl);

        pojo.setSiteTitle(CrawlerUtils.title(siteUrl));
        pojo.setAddTime(DateUtils.now());
        pojo.setWebSite(webSite);

        logger.info("#insert, pojo={}", JSONObject.toJSONString(pojo));

        return favoritesBeanDao.insert(pojo) >0 ? true : false;
    }

    @Override
    public boolean delete(int id, String userName){
        int num = favoritesBeanDao.delete(id, userName);

        return num > 0;
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
    public PageVO select(String username, PageModel pageModel){
        logger.info("pageNum={}, pageSize={}", pageModel.getPageNum(), pageModel.getPageSize());
        int offset = pageModel.offset();
        List<FavoritesBean> favoritesBeans = favoritesBeanDao.selectByPage(username, offset, pageModel.getPageSize());
        long count = favoritesBeanDao.count();


        long pages = (long) Math.ceil((count * 1.0) / pageModel.getPageSize());
        return new PageVO(pageModel.getPageNum(), pageModel.getPageSize(), count, pages, favoritesBeans);
    }

    @Override
    public WebSiteInfoVo webSiteInfo(String url){
        logger.info("#webSiteInfo, url={}", url);
        WebSiteInfoVo webSiteInfoVo = new WebSiteInfoVo();

        String title = CrawlerUtils.title(url);
        if (StringUtils.isEmpty(title)){
            return null;
        }

        WebSite webSite = queryByDomain(url);

        if (webSite != null){
            webSiteInfoVo.setIntro(webSite.getIntro());
            webSiteInfoVo.setName(webSite.getName());
            webSiteInfoVo.setDomain(webSite.getDomain());
        }

        webSiteInfoVo.setTitle(title);

        return webSiteInfoVo;
    }

    private WebSite queryByDomain(String url){
        String host = UrlUtils.getHost(url);
        List<WebSite> webSites = webSiteDao.queryByDomain(host);
        // 按主机名搜索不到记录，则按顶级域名搜索
        if (CollectionUtils.isEmpty(webSites)){
            String domain = UrlUtils.getTopLevelDomain(url);
            webSites = webSiteDao.queryByDomain(domain);
        }

        WebSite webSite = getShortestDomain(webSites);

        return webSite;
    }

    private WebSite getShortestDomain(List<WebSite> webSites){
        WebSite ret = null;
        int minLen = Integer.MAX_VALUE;

        for (WebSite webSite : webSites){
            if (webSite.getDomain().length() < minLen){
                ret = webSite;
                minLen = webSite.getDomain().length();
            }
        }

        return ret;
    }
}
