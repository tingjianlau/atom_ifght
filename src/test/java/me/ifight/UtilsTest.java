package me.ifight;

import me.ifight.service.impl.TokenDetailImpl;
import me.ifight.utils.TokenUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(UtilsTest.class);

    @Autowired
    TokenUtils tokenUtils;
    @Test
    public void jwtTest(){
        String jwt = tokenUtils.generateToken(new TokenDetailImpl("liutingjian"));

        logger.info(jwt);
    }
}
