package me.ifight.service.impl;
import me.ifight.utils.ParseHtmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class JSoupServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(JSoupServiceImpl.class);

    private static final String BASE_URL = "http://www.dzsxzw.com/?p=4003";

    public static void main(String[] args) throws IOException {
        File file = new File("tmp.html");
        ParseHtmlUtils.parse(file);
    }
}
