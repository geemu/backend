package com.chenfangming.backend.manage.config.redis;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
   * GenericFastJsonRedisSerializer.
   * @return GenericFastJsonRedisSerializer
   */
  @Bean
  public GenericFastJsonRedisSerializer genericFastJsonRedisSerializer() {
    return new GenericFastJsonRedisSerializer();
  }

}
