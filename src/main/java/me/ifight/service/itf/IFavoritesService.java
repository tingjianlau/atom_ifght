package me.ifight.service.itf;

import me.ifight.model.FavoritesBean;
import me.ifight.model.common.PageModel;
import me.ifight.model.common.PageVO;

import java.util.List;

public interface IFavoritesService {
    public boolean insert(FavoritesBean pojo);

    public int insertList(List< FavoritesBean> pojos);

    public List<FavoritesBean> select(FavoritesBean pojo);

    public int update(FavoritesBean pojo);

    public boolean delete(int id);

    public PageVO select(PageModel pageModel);
}

