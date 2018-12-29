package com.chenfangming.backend.manage.config.redis;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis缓存配置.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-10 10:42
 */
@Slf4j
@EnableCaching
@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {
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
  public RedisCacheConfig(RedisConnectionFactory redisConnectionFactory, StringRedisSerializer stringRedisSerializer, Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer) {
    this.redisConnectionFactory = redisConnectionFactory;
    this.stringRedisSerializer = stringRedisSerializer;
    this.jackson2JsonRedisSerializer = jackson2JsonRedisSerializer;
  }

  /**
   * 缓存管理器.
   * @return 缓存管理器
   */
  @Bean
  @Override
  public CacheManager cacheManager() {
    log.info(">>>>>>>>>>>>>>>>>>>>初始化:CacheManager");
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
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
            // value序列化
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
    return RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactory)
            .cacheDefaults(config)
            .build();
  }

  /**
   * 为方法和参数生成key.
   * @return 密钥生成器
   */
  @Bean
  @Override
  public KeyGenerator keyGenerator() {
    log.info(">>>>>>>>>>>>>>>>>>>>初始化:KeyGenerator");
    return (target, method, params) -> {
      StringBuilder sb = new StringBuilder();
      sb.append(target.getClass().getName());
      for (Object param : params) {
        sb.append(null == param ? "" : param.toString());
      }
      return sb.toString();
    };
  }
}
