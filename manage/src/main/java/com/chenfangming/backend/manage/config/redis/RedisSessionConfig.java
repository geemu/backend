package com.chenfangming.backend.manage.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Redis session的一些序列化配置
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-10 13:31
 */
@Slf4j
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {

    /** Redis模板配置 **/
    private RedisTemplateConfig redisTemplateConfig;

    /**
     * 构造器注入
     * @param redisTemplateConfig 模板
     */
    @Autowired
    public RedisSessionConfig(RedisTemplateConfig redisTemplateConfig) {
        this.redisTemplateConfig = redisTemplateConfig;
    }

    /**
     * 设置Redis session的序列化方式
     * @return SessionRepository
     */
    @Bean
    public SessionRepository sessionRepository() {
        RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(redisTemplateConfig.redisTemplate());
        sessionRepository.setDefaultSerializer(redisTemplateConfig.jackson2JsonRedisSerializer());
        return sessionRepository;
    }
}
