package me.ifight.parse;

import me.ifight.model.BookVO;
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

    public static List<BookVO> parseHtmls(List<String> urls){
        List<BookVO> bookVOList = new ArrayList<>();
        for (String url : urls){
            bookVOList.add((new BookServiceImpl()).parseHtml(url));
        }

        return bookVOList;
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
        if (urls != null && !urls.isEmpty()){
            List<BookVO> bookVOList = ParseBook.parseHtmls(urls);
        }
    }
}
