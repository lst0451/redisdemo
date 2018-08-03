package com.nebula.demo.config;

import com.nebula.demo.entity.Block;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<Object, Block> blockRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Block> template = new RedisTemplate<Object,Block>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Block> redisSerializer = new Jackson2JsonRedisSerializer<Block>(Block.class);
        template.setDefaultSerializer(redisSerializer);
        return template;
    }




}
