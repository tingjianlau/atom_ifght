package me.ifight.service.itf;

import me.ifight.model.BookVO;

import java.io.File;

public interface IBookService {
    BookVO parseHtml(String url);
    BookVO parseHtml(File file);
}
