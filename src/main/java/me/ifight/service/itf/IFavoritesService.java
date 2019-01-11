package me.ifight.service.itf;

import me.ifight.model.FavoritesBean;
import me.ifight.model.WebSiteInfoVo;
import me.ifight.model.common.PageModel;
import me.ifight.model.common.PageVO;

import java.util.List;

public interface IFavoritesService {
    boolean insert(FavoritesBean pojo);

    int insertList(List< FavoritesBean> pojos);

    List<FavoritesBean> select(FavoritesBean pojo);

    int update(FavoritesBean pojo);

    boolean delete(int id, String userName);

    PageVO select(String username, PageModel pageModel);

    WebSiteInfoVo webSiteInfo(String url);
}

