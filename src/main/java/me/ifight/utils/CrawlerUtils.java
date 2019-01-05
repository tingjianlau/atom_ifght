package me.ifight.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CrawlerUtils {
    private static Logger logger = LoggerFactory.getLogger(CrawlerUtils.class);

    public static Document parse(String url){
        Document  document = null;
        try {
            document = Jsoup.connect(url)
                    .userAgent("ie7：mozilla/4.0 (compatible; msie 7.0b; windows nt 6.0)")
                    .timeout(3000)
                    .cookie("guide", "1")
                    .followRedirects(false)
                    .execute().parse();
        } catch (IOException e) {
            logger.error("parse error ", e);
            return null;
        }

        return document;
    }

    public static String get(String url){
        logger.info("#get, request url is {}" , url);
        // 创建默认的httpClient实例
        CloseableHttpClient httpClient = getHttpClient();
        HttpEntity entity = null;
        String html = "";
        try {
            HttpGet get = new HttpGet(url);
            logger.info("#get, doing request is {}...", get.getURI());
            CloseableHttpResponse httpResponse = null;
            try{
                httpResponse = httpClient.execute(get);
                if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
                    logger.error("#get, responseStatusLine=" + httpResponse.getStatusLine());
                    return "";
                }
                entity = httpResponse.getEntity();
                html = EntityUtils.toString(entity, "UTF-8");
            } finally{
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try{
                closeHttpClient(httpClient);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        return html;
    }

    private static CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
    }

    private static void closeHttpClient(CloseableHttpClient client) throws IOException{
        if (client != null){
            client.close();
        }
    }

    public static void main(String[] args) {
        logger.info(CrawlerUtils.parse("https://www.baidu.com").title());
        //String html = CrawlerUtils.get("https://juejin.im/entry/5c196e02e51d456c7e3e9001");
        //logger.info(html);
//        int max = 10276;
//        String baseUrl = "http://www.dzsxzw.com/?p=";
//        for (int i = 1; i < max; i++) {
//            String html = CrawlerUtils.get(baseUrl + i);
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (StringUtil.isEmpty(html)){
//                logger.error("#main, fail for url {}", i);
//                continue;
//            }
//            //try {
//                String toPath = "data/p-" + (i + 1) + ".html";
//                //FileUtils.write(new File(toPath), html);
//                logger.info("#main, success for url {}", (baseUrl + i));
//            //} catch (IOException e) {
//             //   e.printStackTrace();
//            //}
//        }
    }
}
