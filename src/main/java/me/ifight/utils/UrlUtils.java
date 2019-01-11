package me.ifight.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @description: URL解析相关
 * @author: tjliu
 * @create: 2019-01-07 21:54
 **/
public class UrlUtils {
    private static final Logger logger = LoggerFactory.getLogger(UrlUtils.class);

    private static final String IP_PATTERN = "(\\d{1,3}\\.){3}(\\d{1,3})";

    private final static Set<String> publicSuffixSet = new HashSet<String>(
            Arrays.asList("com|org|net|gov|edu|co|tv|mobi|info|asia|xxx|onion|cn|com.cn|edu.cn|gov.cn|net.cn|org.cn|jp|kr|tw|com.hk|hk|com.hk|org.hk|se|com.se|org.se|im|me"
                    .split("\\|")));

    public static boolean validateIP(String ip){
        Pattern pattern = Pattern.compile(IP_PATTERN);

        return pattern.matcher(ip).matches();
    }

    public static String getHost(String url){
        URL u = null;
        try {
            u = new URL(url);
        } catch (MalformedURLException e) {
            logger.error("parse url error", e);
            return "";
        }

        return u.getHost();
    }
    /**
     * 获取url的顶级域名
     * @param url
     * @return
     */
    public static String getTopLevelDomain(URL url) {
        String host = url.getHost();
        if (host.endsWith(".")){
            host = host.substring(0, host.length() - 1);
        }
        if (validateIP(host)){
            return host;
        }

        int index = 0;
        String candidate = host;
        while (index != -1){
            index = candidate.indexOf('.');
            String subCandidate = candidate.substring(index + 1);
            if (publicSuffixSet.contains(subCandidate)) {
                return candidate;
            }
            candidate = subCandidate;
        }
        return "";
    }

    /**
     * 获取url的顶级域名
     * @param url 网址必须包含协议
     * @return
     * @throws MalformedURLException
     */
    public static String getTopLevelDomain(String url) {
        URL u = null;
        try {
            u = new URL(url);
        } catch (MalformedURLException e) {
            logger.error("parse url error", e);
        }
        return u == null ? "" : getTopLevelDomain(u);
    }
}
