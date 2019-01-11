package me.ifight.dao;

import me.ifight.model.WebSite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebSiteDao {

    int insert(@Param("pojo") WebSite pojo);

    int insertList(@Param("pojos") List< WebSite> pojo);

    List<WebSite> select(@Param("pojo") WebSite pojo);

    List<WebSite> queryByDomain(@Param("domain") String domain);

    int update(@Param("pojo") WebSite pojo);

}
