package me.ifight;

import me.ifight.utils.UrlUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: tjliu
 * @create: 2019-01-07 22:33
 **/
public class UrlUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(UrlUtils.class);

    @Test
    public void validateIPTest(){
        boolean flag1 = UrlUtils.validateIP("127.0.0.1");
        org.junit.Assert.assertTrue(flag1);
        boolean flag2 = UrlUtils.validateIP("127.0.0.1.1");
        org.junit.Assert.assertFalse(flag2);
        boolean flag3 = UrlUtils.validateIP("");
        org.junit.Assert.assertFalse(flag3);
        boolean flag4 = UrlUtils.validateIP("dfs.djf.dfd.dfsd");
        org.junit.Assert.assertFalse(flag4);
    }

    @Test
    public void getDomainNameTest(){
        String doamin = "baidu.com";
        String domain1 = UrlUtils.getTopLevelDomain("http://www.baidu.com");
        org.junit.Assert.assertTrue(domain1.equalsIgnoreCase(doamin));
        String domain2 = UrlUtils.getTopLevelDomain("https://www.baidu.com");
        org.junit.Assert.assertTrue(domain2.equalsIgnoreCase(doamin));
        String domain4 = UrlUtils.getTopLevelDomain("https://image.img.baidu.com");
        org.junit.Assert.assertTrue(domain4.equalsIgnoreCase(doamin));
        String domain5 = UrlUtils.getTopLevelDomain("http://image.img.baidu.com.cn");
        org.junit.Assert.assertTrue(domain5.equalsIgnoreCase("baidu.com.cn"));
        String domain6 = UrlUtils.getTopLevelDomain("http://image.img.baidu");
        org.junit.Assert.assertTrue(domain6.equalsIgnoreCase(""));
    }
}
