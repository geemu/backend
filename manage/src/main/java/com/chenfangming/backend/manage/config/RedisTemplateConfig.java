//package com.chenfangming.backend.manage.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// * Redis操作模板配置
// * @author 陈方明  cfmmail@sina.com
// * @since 2018-11-10 10:45
// */
//@Slf4j
//@Configuration
//public class RedisTemplateConfig {
//    /** 连接工厂 **/
//    private RedisConnectionFactory redisConnectionFactory;
//
//    /**
//     * 构造器注入
//     * @param redisConnectionFactory 连接工厂
//     */
//    @Autowired
//    public RedisTemplateConfig(RedisConnectionFactory redisConnectionFactory) {
//        this.redisConnectionFactory = redisConnectionFactory;
//    }
//
//    /**
//     * HashOperations<String,String,Object>
//     * @return HashOperations
//     */
//    @Bean
//    public HashOperations<Object, Object, Object> hashOperations() {
//        log.info(">>>>>>>>>>>>>>>>>>>>初始化:HashOperations");
//        return redisTemplate().opsForHash();
//    }
//
//    /**
//     * 自定义序列化模板
//     * @return RedisTemplate
//     */
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate() {
//        log.info(">>>>>>>>>>>>>>>>>>>>初始化:RedisTemplate");
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer());
//        redisTemplate.setEnableTransactionSupport(true);
//        redisTemplate.setEnableDefaultSerializer(true);
//        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer());
//        redisTemplate.setKeySerializer(stringRedisSerializer());
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
//        redisTemplate.setHashKeySerializer(stringRedisSerializer());
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//    /**
//     * Jackson序列化
//     * @return Jackson2JsonRedisSerializer
//     */
//    @Bean
//    public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
//        log.info(">>>>>>>>>>>>>>>>>>>>初始化:Jackson2JsonRedisSerializer");
//        Jackson2JsonRedisSerializer<Object> response = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
//        objectMapper.enableDefaultTyping(DefaultTyping.NON_FINAL);
//        response.setObjectMapper(objectMapper);
//        return response;
//    }
//
//    /**
//     * StringRedisSerializer序列化
//     * @return StringRedisSerializer
//     */
//    @Bean
//    public StringRedisSerializer stringRedisSerializer() {
//        log.info(">>>>>>>>>>>>>>>>>>>>初始化:StringRedisSerializer");
//        return new StringRedisSerializer();
//    }
//
//}
