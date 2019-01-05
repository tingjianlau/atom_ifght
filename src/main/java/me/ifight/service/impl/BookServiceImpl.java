package me.ifight.service.impl;

import com.alibaba.fastjson.JSON;
import me.ifight.dao.BookBeanDao;
import me.ifight.model.BookBean;
import me.ifight.model.DouBanScore;
import me.ifight.service.itf.IBookService;
import me.ifight.utils.ParseHtmlUtils;
import me.ifight.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements IBookService {
    private static Logger logger = LoggerFactory.getLogger(ParseHtmlUtils.class);

    @Autowired
    private BookBeanDao bookBeanMapper;

    @Override
    public int insert(BookBean pojo){
        return bookBeanMapper.insert(pojo);
    }

    @Override
    public int insertList(List< BookBean> pojos){
        return bookBeanMapper.insertList(pojos);
    }

    @Override
    public List<BookBean> select(BookBean pojo){
        return bookBeanMapper.select(pojo);
    }

    @Override
    public BookBean queryBookById(int id){
        return bookBeanMapper.queryBookById(id);
    }

    @Override
    public int update(BookBean pojo){
        return bookBeanMapper.update(pojo);
    }


    @Override
    public BookBean parseHtml(String url) {
        logger.info("To parse {}", url);
        File file = new File(url);
        Document doc = ParseHtmlUtils.parse(file);
        BookBean bookBean = parseHtml(doc);
        return bookBean;
    }

    @Override
    public BookBean parseHtml(File file) {
        return null;
    }

    public BookBean parseHtml(Document doc) {
        if (doc == null) {
            return null;
        }

        BookBean bookBean = new BookBean();
        String title = doc.title();
        parseTitle(title, bookBean);

        bookBean.setImageUrl(getImageUrl(doc));

        DouBanScore douBanScore = getDoubanScore(doc);
        bookBean.setScore(douBanScore.getSocre());
        bookBean.setCount(douBanScore.getCount());

        bookBean.setBaiduPwd(getPwd(doc));

        bookBean.setBaiduLink(getBaiduLink(doc));

        String category = doc.select("[rel=category]").text();
        bookBean.setCategory(category);

        Elements tagEles = doc.select("[rel=tag]");

        List<String> tags = new ArrayList<>();
        for (Element tagEle : tagEles) {
            tags.add(tagEle.text());
        }
        bookBean.setTag(tags);

        String json = JSON.toJSONString(bookBean);
        logger.info("current book is : {}", json);
        return bookBean;
    }

    private String getImageUrl(Document doc) {
        Elements select = doc.select("[src*=http://www.dzsxzw.com/book_cover]");
        if (isNotEmpty(select)) {
            return select.get(0).attr("src");
        }
        return "";
    }

    private String getBaiduLink(Document doc) {
        Elements select = doc.select("[href*=pan.baidu.com]");
        if (isNotEmpty(select)) {
            return select.get(0).attr("href");
        }
        return "";
    }

    private void parseTitle(String title, BookBean bookBean) {
        if (StringUtil.isEmpty(title)) {
            return;
        }

        int index1 = title.indexOf("《");
        int index2 = title.indexOf("》");
        String bookName = (index1 == -1 && index2 == -1) ? "" : title.substring(index1 + 1, index2);
        String author = title.indexOf("（作者") == -1 ? "" : title.substring(index2 + 1, title.indexOf("（作者"));

        bookBean.setAuthor(author);
        bookBean.setName(bookName);
    }

    private String getPwd(Document doc) {
        String sep = "百度网盘分享密码：";
        String pwdLine = doc.select("p:contains(" + sep + ")").text().trim();

        return StringUtil.isEmpty(pwdLine) ? "" : pwdLine.substring(sep.length(), sep.length() + 4);
    }

    private DouBanScore getDoubanScore(Document doc) {
        DouBanScore douBanScore = new DouBanScore();
        Elements elements = doc.select("strong:contains(豆瓣评分)");
        if (isNotEmpty(elements)) {
            String text = elements.parents().get(0).text();

            int index1 = text.indexOf("(");
            int index2 = text.indexOf("人评价");
            if (index1 != -1 && index2 != -1){
                String count = text.substring(index1+1, index2);
                if (StringUtils.isNumeric(count)){
                    douBanScore.setCount(Integer.parseInt(count));
                }else{
                    douBanScore.setCount(-1);
                }
                String subStr = text.substring(0, index1);
                String[] splited = subStr.split(" ");
                if(StringUtil.isDecimal(splited[splited.length - 1])){
                    douBanScore.setSocre(Double.parseDouble(splited[splited.length - 1]));
                }else{
                    douBanScore.setSocre(-1);
                }
            }
        }

        return douBanScore;
    }

    private boolean isEmpty(Elements elements) {
        return elements != null && elements.isEmpty();
    }

    private boolean isNotEmpty(Elements elements) {
        return !isEmpty(elements);
    }
}
