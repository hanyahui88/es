package com.alvin.es;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("sendSuccess:{\"user\":\"fsdf\",\"content\",\"fsdfsdfsdf\"}");
        System.out.println("sendSuccess:{\"user\":\"2423\",\"content\",\"123xcxcb\"}");
        System.out.println("sendSuccess:{\"user\":\"gljkl\",\"content\",\"k'p/.k,jhm\"}");
        System.out.println("sendSuccess:{\"user\":\"cbcb\",\"content\",\"ekjdsfs\"}");
    }


}
