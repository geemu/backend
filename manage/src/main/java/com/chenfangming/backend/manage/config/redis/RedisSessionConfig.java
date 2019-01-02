//package com.chenfangming.backend.manage.config.redis;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.session.data.redis.RedisOperationsSessionRepository;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//
///**
// * RedisSession配置.
// * @author 陈方明  cfmmail@sina.com
// * @since 2018-12-29 10:55
// */
//@Configuration
//@EnableRedisHttpSession
//public class RedisSessionConfig {
//  /** RedisTemplate. **/
//  private RedisTemplate<Object, Object> redisTemplate;
//  /** Jackson2JsonRedisSerializer. **/
//  private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer;
//
//  /**
//   * 构造器注入.
//   * @param redisTemplate redisTemplate
//   * @param jackson2JsonRedisSerializer jackson2JsonRedisSerializer
//   */
//  public RedisSessionConfig(RedisTemplate<Object, Object> redisTemplate, Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer) {
//    this.redisTemplate = redisTemplate;
//    this.jackson2JsonRedisSerializer = jackson2JsonRedisSerializer;
//  }
//
//  /**
//   * 设置Redis session的序列化方式.
//   * @return SessionRepository
//   */
//  @Bean
//  public RedisOperationsSessionRepository sessionRepository() {
//    RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(redisTemplate);
//    sessionRepository.setDefaultSerializer(jackson2JsonRedisSerializer);
//    return sessionRepository;
//  }
//}
