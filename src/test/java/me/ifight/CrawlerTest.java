package me.ifight;

import com.alibaba.fastjson.JSON;
import me.ifight.dao.WebSiteDao;
import me.ifight.model.WebSite;
import me.ifight.utils.CrawlerUtils;
import me.ifight.utils.ParseHtmlUtils;
import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @description:
 * @author: tjliu
 * @create: 2019-01-08 21:55
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class CrawlerTest {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerTest.class);

    @Autowired
    private WebSiteDao webSiteDao;

    // 从站长之家中爬取网站信息
    @Test
    public void parseChinazTest(){
        String baseUrl = "http://top.chinaz.com/all/index%s.html";

        List<WebSite> webSiteList = new ArrayList<>();
        int count = 0;
        // 1914
        for(int index = 1; index < 1914; index++){
            String url = String.format(baseUrl, "_" + index);
            if (index == 1){
                url = String.format(baseUrl, "");
            }
            logger.info("to do {}", url);
            String html = CrawlerUtils.get(url);
            Document doc = ParseHtmlUtils.parse(html);

            List<Elements> elementsList = new ArrayList<>();

            Elements records = doc.select(".CentTxt");
            for (int i = 0; i < records.size(); i++){
                WebSite webSite = new WebSite();
                Element element = records.get(i);
                try{
                    webSite.setAlexa(Integer.parseInt(element.selectFirst("p.RtCData > a").text()));
                }catch (NumberFormatException e){
                    webSite.setAlexa(Integer.MAX_VALUE);
                }
                webSite.setDomain(element.selectFirst(".rightTxtHead > span").text());
                webSite.setName(element.selectFirst(".rightTxtHead > a").text());
                String intro = element.selectFirst(".RtCInfo").text();
                webSite.setIntro(intro);

                webSiteList.add(webSite);
                webSiteDao.insert(webSite);
                count += 1;
                logger.info("{}->{}", count, JSON.toJSONString(webSite));
            }
        }

        StringBuffer sb = new StringBuffer();
        for (WebSite webSite : webSiteList){
            sb.append(JSON.toJSONString(webSite));
            sb.append("\n");
        }

        try {
            FileUtils.write(new File("./data/00_website.txt"), sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Done");
    }

    private boolean validElements(Elements... elements){
        Set<Integer> set = new TreeSet<>();
        for (Elements elements1 : elements){
            set.add(elements1.size());
        }

        return set.size() == 1 && !set.contains(0);
    }

    @Test
    public void crawlerHtmlTest(){
        String url = "http://top.chinaz.com/all/index.html";
        String html = CrawlerUtils.get(url);
        String outPath = "./data/00.html";
        try {
            FileUtils.write(new File(outPath), html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
