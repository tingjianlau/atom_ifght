package me.ifight.dao;

import me.ifight.model.BookBean;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface BookBeanDao {

    int insert(@Param("pojo") BookBean pojo);

    int insertList(@Param("pojos") List< BookBean> pojo);

    List<BookBean> select(@Param("pojo") BookBean pojo);

    int update(@Param("pojo") BookBean pojo);

    BookBean queryBookById(@Param("id") int id);
}
