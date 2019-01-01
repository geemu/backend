package com.chenfangming.backend.manage.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
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
  /** Jackson2JsonRedisSerializer. **/
  private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer;

  /**
   * 构造器注入.
   * @param redisConnectionFactory 连接工厂
   * @param stringRedisSerializer stringRedisSerializer
   * @param jackson2JsonRedisSerializer jackson2JsonRedisSerializer
   */
  public RedisTemplateConfig(RedisConnectionFactory redisConnectionFactory,
                             StringRedisSerializer stringRedisSerializer,
                             Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer) {
    this.redisConnectionFactory = redisConnectionFactory;
    this.stringRedisSerializer = stringRedisSerializer;
    this.jackson2JsonRedisSerializer = jackson2JsonRedisSerializer;
  }


  /**
   * 自定义序列化模板.
   * 序列化时带上参数类型
   * @return RedisTemplate
   */
  @Bean("redisTemplate")
  public RedisTemplate<Object, Object> redisTemplate2() {
    log.info("初始化:RedisTemplate");
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setHashKeySerializer(stringRedisSerializer);
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    return redisTemplate;
  }

  /**
   * 自定义序列化模板.
   * 序列化时不带参数类型
   * @return RedisTemplate
   */
  @Bean("redisTemplate2")
  public RedisTemplate<Object, Object> redisTemplate3() {
    log.info("初始化:RedisTemplate2");
    Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
    //  所有字段都序列化
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    serializer.setObjectMapper(objectMapper);
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setValueSerializer(serializer);
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    redisTemplate.setHashKeySerializer(stringRedisSerializer);
    redisTemplate.setHashValueSerializer(serializer);
    return redisTemplate;
  }
}
