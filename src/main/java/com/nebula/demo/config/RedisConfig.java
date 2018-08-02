package com.nebula.demo.config;

import com.nebula.demo.entity.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Product> prodRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Product> template = new RedisTemplate<Object, Product>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Product> redisSerializer = new Jackson2JsonRedisSerializer<Product>(Product.class);
        template.setDefaultSerializer(redisSerializer);
        return template;
    }

}
