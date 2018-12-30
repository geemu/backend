package com.chenfangming.backend.manage.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * OperationConfig.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-29 10:00
 */
@Slf4j
@Configuration
public class OperationConfig {

  /** RedisTemplate. **/
  private RedisTemplate<Object, Object> redisTemplate;

  /**
   * 构造器注入.
   * @param redisTemplate redisTemplate
   */
  public OperationConfig(RedisTemplate<Object, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * HashOperations.
   * @return HashOperations
   */
  @Bean
  public HashOperations<Object, Object, Object> hashOperations() {
    log.info("初始化:HashOperations");
    return redisTemplate.opsForHash();
  }

  /**
   * ValueOperations.
   * @return ValueOperations
   */
  @Bean
  public ValueOperations<Object, Object> valueOperations() {
    log.info("初始化:ValueOperations");
    return redisTemplate.opsForValue();
  }

}
