package com.nebula.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.SchemaOutputResolver;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test() {
        stringRedisTemplate.opsForValue().append("k1","{value}");
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        System.out.println(k1);
    }


    @Test
    public void contextLoads() {

    }

}
