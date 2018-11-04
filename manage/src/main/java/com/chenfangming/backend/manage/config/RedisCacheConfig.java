package com.chenfangming.backend.manage.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * Redis以及缓存配置
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-30 21:46
 */
@Slf4j
@EnableCaching
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisCacheConfig extends CachingConfigurerSupport {
    /** 连接池 **/
    private LettuceConnectionFactory lettuceConnectionFactory;

    @Autowired
    public void setLettuceConnectionFactory(LettuceConnectionFactory lettuceConnectionFactory) {
        this.lettuceConnectionFactory = lettuceConnectionFactory;
    }

    /**
     * 缓存管理器
     * @return 缓存管理器
     */
    @Bean
    @Override
    public CacheManager cacheManager() {
        log.info(">>开始初始化cacheManager");
        //  生成一个默认配置，通过config对象即可对缓存进行自定义配置
        RedisCacheConfiguration config = RedisCacheConfiguration
                .defaultCacheConfig()
                //  设置缓存的默认过期时间，也是使用Duration设置
                .entryTtl(Duration.ofSeconds(60))
                //  不缓存空值
                .disableCachingNullValues()
                //  禁用前缀
                .disableKeyPrefix()
                .disableCachingNullValues()
                //  key序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer()))
                // value序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer()));
        return RedisCacheManagerBuilder
                .fromConnectionFactory(lettuceConnectionFactory)
                .cacheDefaults(config)
                .build();
    }

    /**
     * 为方法和参数生成key
     * @return 密钥生成器
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        log.info(">>开始初始化keyGenerator");
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            for (Object param : params) {
                sb.append(null == param ? "" : param.toString());
            }
            return sb.toString();
        };
    }

    /**
     * StringRedisSerializer序列化
     * @return StringRedisSerializer
     */
    private StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    /**
     * Jackson序列化
     * @return Jackson2JsonRedisSerializer
     */
    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer<Object> response = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
        objectMapper.enableDefaultTyping(DefaultTyping.NON_FINAL);
        response.setObjectMapper(objectMapper);
        return response;
    }

    /**
     * 自定义序列化模板
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        log.info(">>开始初始化redisTemplate");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer());
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(stringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
