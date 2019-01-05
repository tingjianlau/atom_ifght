package me.ifight.service.itf;

import me.ifight.model.BookBean;

import java.io.File;
import java.util.List;

public interface IBookService {
    BookBean parseHtml(String url);
    BookBean parseHtml(File file);

    int insert(BookBean pojo);

    int insertList(List< BookBean> pojos);

    List<BookBean> select(BookBean pojo);

    BookBean queryBookById(int id);

    int update(BookBean pojo);
}
