package me.ifight.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ParseHtmlUtils {
    private static Logger logger = LoggerFactory.getLogger(ParseHtmlUtils.class);

    public static Document parse(File input){
        Document doc = null;
        try {
            doc = Jsoup.parse(input, "UTF-8", "");
        }catch (Exception e){
            logger.error("#parse, parse error, " + e);
        }

        return doc;
    }

    public static Document parse(String html){
        Document doc = null;
        try {
            doc = Jsoup.parse(html);
        }catch (Exception e){
            logger.error("#parse, parse error, " + e);
            return null;
        }

        return doc;
    }

    public static String getTitle(String html){
        Document doc = parse(html);
        return doc == null ? "" : doc.title();
    }
}
