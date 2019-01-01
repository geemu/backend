package com.chenfangming.backend.manage.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * RedisSerializerConfig.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-29 10:07
 */
@Slf4j
@Configuration
public class RedisSerializerConfig {
  /**
   * StringRedisSerializer序列化.
   * @return StringRedisSerializer
   */
  @Bean
  public StringRedisSerializer stringRedisSerializer() {
    log.info("初始化:StringRedisSerializer");
    return new StringRedisSerializer();
  }


  /**
   * Jackson2JsonRedisSerializer序列化.
   * @return Jackson2JsonRedisSerializer
   */
  @Bean
  public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
    log.info("初始化:Jackson2JsonRedisSerializer");
    Jackson2JsonRedisSerializer<Object> response = new Jackson2JsonRedisSerializer<>(Object.class);
    //  所有字段都序列化
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    //  序列化时带上参数类型
    objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    response.setObjectMapper(objectMapper);
    return response;
  }

}
