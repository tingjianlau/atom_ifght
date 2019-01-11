package me.ifight.dao;

import me.ifight.model.FavCategory;
import me.ifight.model.FavoritesBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoritesBeanDao {

    int insert(@Param("pojo") FavoritesBean pojo);

    int insertList(@Param("pojos") List< FavoritesBean> pojo);

    List<FavoritesBean> select(@Param("pojo") FavoritesBean pojo);

    int update(@Param("pojo") FavoritesBean pojo);

    int delete(@Param("id") int id, @Param("userName") String userName);

    List<FavoritesBean> selectByPage(@Param("userName") String username, @Param("offset") int offset, @Param("limit") int limit);

    long count();

    List<FavCategory> categories();
}
