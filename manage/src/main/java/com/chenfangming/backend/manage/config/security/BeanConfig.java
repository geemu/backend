package com.chenfangming.backend.manage.config.security;

import com.chenfangming.backend.manage.config.security.filter.MyTokenFilter;
import com.chenfangming.backend.manage.config.security.handle.MyAnonymousDeniedHandle;
import com.chenfangming.backend.manage.config.security.handle.MyAuthenticationDeniedHandler;
import com.chenfangming.backend.manage.config.security.handle.MyAuthenticationFailureHandler;
import com.chenfangming.backend.manage.config.security.handle.MyAuthenticationSuccessHandler;
import com.chenfangming.backend.manage.config.security.handle.MyLogoutSuccessHandler;
import com.chenfangming.backend.manage.config.security.support.MyAccessDecisionManager;
import com.chenfangming.backend.manage.config.security.support.MyFilterInvocationSecurityMetadataSource;
import com.chenfangming.backend.manage.persistence.mapper.MenuMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * BeanConfig.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-30 17:17
 */
@Slf4j
@Configuration
public class BeanConfig {
  /** ObjectMapper. **/
  private ObjectMapper objectMapper;
  /** MenuMapper. **/
  private MenuMapper menuMapper;
  /** RedisTemplate. **/
  private RedisTemplate<Object, Object> redisTemplate;

  /**
   * 构造器注入.
   * @param objectMapper {@link org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration}
   * @param objectMapper {@link MenuMapper}
   * @param objectMapper {@link com.chenfangming.backend.manage.config.redis.RedisTemplateConfig}
   */
  public BeanConfig(ObjectMapper objectMapper,
                    MenuMapper menuMapper,
                    RedisTemplate<Object, Object> redisTemplate) {
    this.objectMapper = objectMapper;
    this.menuMapper = menuMapper;
    this.redisTemplate = redisTemplate;
  }

  /**
   * 认证成功处理流程.
   * @return {@link MyAuthenticationFailureHandler}
   */
  @Bean
  public MyAuthenticationSuccessHandler myAuthenticationSuccessHandler() {
    log.info("初始化:MyAuthenticationSuccessHandler");
    return new MyAuthenticationSuccessHandler(objectMapper);
  }

  /**
   * 认证失败处理流程.
   * @return {@link MyAuthenticationFailureHandler}
   */
  @Bean
  public MyAuthenticationFailureHandler myAuthenticationFailureHandler() {
    log.info("初始化:MyAuthenticationFailureHandler");
    return new MyAuthenticationFailureHandler(objectMapper);
  }

  /**
   * Token解析认证实体.
   * @return {@link MyTokenFilter}
   */
  @Bean
  public MyTokenFilter myTokenFilter() {
    log.info("初始化:MyTokenFilter");
    return new MyTokenFilter(redisTemplate);
  }

  /**
   * 从数据库获取资源.
   * @return {@link MyFilterInvocationSecurityMetadataSource}
   */
  @Bean
  public MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource() {
    log.info("初始化:MyFilterInvocationSecurityMetadataSource");
    return new MyFilterInvocationSecurityMetadataSource(menuMapper);
  }


  /**
   * 判断用户是否有权限.
   * @return {@link MyAccessDecisionManager}
   */
  @Bean
  public MyAccessDecisionManager myAccessDecisionManager() {
    log.info("初始化:MyAccessDecisionManager");
    return new MyAccessDecisionManager();
  }

  /**
   * 认证用户访问无权限资源处理流程.
   * @return {@link MyAuthenticationDeniedHandler}
   */
  @Bean
  public MyAuthenticationDeniedHandler myAuthenticationDeniedHandler() {
    log.info("初始化:MyAuthenticationDeniedHandler");
    return new MyAuthenticationDeniedHandler(objectMapper);
  }

  /**
   * 匿名用户访问无权限资源处理流程.
   * @return {@link MyAnonymousDeniedHandle}
   */
  @Bean
  public MyAnonymousDeniedHandle myAnonymousDeniedHandle() {
    log.info("初始化:MyAnonymousDeniedHandle");
    return new MyAnonymousDeniedHandle(objectMapper);
  }

  /**
   * 注销成功处理流程.
   * @return {@link MyLogoutSuccessHandler}
   */
  @Bean
  public MyLogoutSuccessHandler myLogoutSuccessHandler() {
    log.info("初始化:MyLogoutSuccessHandler");
    return new MyLogoutSuccessHandler(objectMapper);
  }
}
