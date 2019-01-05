package me.ifight;

import me.ifight.dao.BookBeanDao;
import me.ifight.model.BookBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookServiceTest {
    private final static Logger logger = LoggerFactory.getLogger(BookServiceTest.class);

    @Autowired
    BookBeanDao bookBeanDao;

    @Test
    public void testQueryBookById(){
        BookBean bookBean = bookBeanDao.queryBookById(0);
        Assert.assertNull(bookBean);
    }

}
