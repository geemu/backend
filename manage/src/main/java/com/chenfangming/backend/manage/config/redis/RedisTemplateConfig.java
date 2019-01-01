package com.chenfangming.backend.manage.config.redis;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis序列化模板配置.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-10 10:45
 */
@Slf4j
@Configuration
public class RedisTemplateConfig {
  /** 连接工厂. **/
  private RedisConnectionFactory redisConnectionFactory;
  /** StringRedisSerializer. **/
  private StringRedisSerializer stringRedisSerializer;
  /** GenericFastJsonRedisSerializer. **/
  private GenericFastJsonRedisSerializer genericFastJsonRedisSerializer;

  /**
   * 构造器注入.
   * @param redisConnectionFactory 连接工厂
   */
  public RedisTemplateConfig(RedisConnectionFactory redisConnectionFactory, StringRedisSerializer stringRedisSerializer, GenericFastJsonRedisSerializer genericFastJsonRedisSerializer) {
    this.redisConnectionFactory = redisConnectionFactory;
    this.stringRedisSerializer = stringRedisSerializer;
    this.genericFastJsonRedisSerializer = genericFastJsonRedisSerializer;
  }

  /**
   * 自定义序列化模板.
   * @return RedisTemplate
   */
  @Bean
  public RedisTemplate<Object, Object> redisTemplate() {
    log.info("初始化:RedisTemplate");
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    redisTemplate.setKeySerializer(stringRedisSerializer);
    Jackson2JsonRedisSerializer jack = new Jackson2JsonRedisSerializer(Object.class);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jack.setObjectMapper(objectMapper);
    GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
    redisTemplate.setValueSerializer(jack);
    redisTemplate.setHashKeySerializer(stringRedisSerializer);
    redisTemplate.setHashValueSerializer(genericFastJsonRedisSerializer);
    return redisTemplate;
  }
}
