package me.ifight.parse;

import me.ifight.model.BookBean;
import me.ifight.service.impl.BookServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParseBook {
    private final static Logger logger = LoggerFactory.getLogger(ParseBook.class);

    public static List<BookBean> parseHtmls(List<String> urls){
        List<BookBean> bookBeanList = new ArrayList<>();
        for (String url : urls){
            bookBeanList.add((new BookServiceImpl()).parseHtml(url));
        }

        return bookBeanList;
    }

    public static void main(String[] args) {
        List<String> urls = new ArrayList<>();
        String baseDir = "./data";
        File dir = new File(baseDir);
        File[] files = dir.listFiles();
        for (File file : files){
            String fileName = file.getName();
            urls.add(Paths.get(baseDir, fileName).toString());
            //int index = Integer.parseInt(fileName.substring(2, fileName.indexOf(".")));
            //logger.info("To do parse index-{}", index);
        }

        Collections.sort(urls);
        List<String> tmpUrls = urls.subList(0, 1);
        if (urls != null && !urls.isEmpty()){
            List<BookBean> bookBeanList = ParseBook.parseHtmls(tmpUrls);

            BookServiceImpl bookService = new BookServiceImpl();
            bookService.insertList(bookBeanList);
        }

    }
}
