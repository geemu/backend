package com.chenfangming.backend.manage.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis序列化模板配置
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-10 10:45
 */
@Slf4j
@Configuration
public class RedisTemplateConfig {
    /** 连接工厂 **/
    private RedisConnectionFactory connectionFactory;
    /** StringRedisSerializer **/
    private StringRedisSerializer stringSerializer;
    /** Jackson2JsonRedisSerializer **/
    private Jackson2JsonRedisSerializer<Object> jackson2JsonSerializer;

    /**
     * 构造器注入
     * @param connectionFactory 连接工厂
     * @param stringSerializer stringSerializer
     * @param jackson2JsonSerializer jackson2JsonSerializer
     */
    public RedisTemplateConfig(RedisConnectionFactory connectionFactory,
                               StringRedisSerializer stringSerializer,
                               Jackson2JsonRedisSerializer<Object> jackson2JsonSerializer) {
        this.connectionFactory = connectionFactory;
        this.stringSerializer = stringSerializer;
        this.jackson2JsonSerializer = jackson2JsonSerializer;
    }


    /**
     * 自定义序列化模板
     * 序列化时带上参数类型
     * @return RedisTemplate
     */
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        log.info("初始化:RedisTemplate");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setDefaultSerializer(jackson2JsonSerializer);
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jackson2JsonSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonSerializer);
        return redisTemplate;
    }
}
