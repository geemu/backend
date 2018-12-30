package com.chenfangming.backend.manage.config.security;

import com.chenfangming.backend.manage.config.security.handle.MyAnonymousDeniedHandle;
import com.chenfangming.backend.manage.config.security.handle.MyAuthenticationDeniedHandler;
import com.chenfangming.backend.manage.config.security.handle.MyAuthenticationFailureHandler;
import com.chenfangming.backend.manage.config.security.handle.MyAuthenticationSuccessHandler;
import com.chenfangming.backend.manage.config.security.handle.MyLogoutSuccessHandler;
import com.chenfangming.backend.manage.config.security.support.MyAccessDecisionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

  /**
   * 构造器注入.
   * @param objectMapper objectMapper
   */
  public BeanConfig(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * 认证成功处理流程.
   * @return MyAuthenticationFailureHandler
   */
  @Bean
  public MyAuthenticationSuccessHandler myAuthenticationSuccessHandler() {
    log.info(">>>>>>>>>>>>>>>>>>>>初始化:MyAuthenticationSuccessHandler");
    return new MyAuthenticationSuccessHandler(objectMapper);
  }

  /**
   * 认证失败处理流程.
   * @return MyAuthenticationFailureHandler
   */
  @Bean
  public MyAuthenticationFailureHandler myAuthenticationFailureHandler() {
    log.info(">>>>>>>>>>>>>>>>>>>>初始化:MyAuthenticationFailureHandler");
    return new MyAuthenticationFailureHandler(objectMapper);
  }

  /**
   * 判断用户是否有权限.
   * @return MyAccessDecisionManager
   */
  @Bean
  public MyAccessDecisionManager myAccessDecisionManager() {
    log.info(">>>>>>>>>>>>>>>>>>>>初始化:MyAccessDecisionManager");
    return new MyAccessDecisionManager();
  }

  /**
   * 认证用户访问无权限资源处理流程.
   * @return MyAuthenticationDeniedHandler
   */
  @Bean
  public MyAuthenticationDeniedHandler myAuthenticationDeniedHandler() {
    log.info(">>>>>>>>>>>>>>>>>>>>初始化:MyAuthenticationDeniedHandler");
    return new MyAuthenticationDeniedHandler(objectMapper);
  }

  /**
   * 匿名用户访问无权限资源处理流程.
   * @return MyAnonymousDeniedHandle
   */
  @Bean
  public MyAnonymousDeniedHandle myAnonymousDeniedHandle() {
    log.info(">>>>>>>>>>>>>>>>>>>>初始化:MyAnonymousDeniedHandle");
    return new MyAnonymousDeniedHandle(objectMapper);
  }

  /**
   * 注销成功处理流程.
   * @return MyLogoutSuccessHandler
   */
  @Bean
  public MyLogoutSuccessHandler myLogoutSuccessHandler() {
    log.info(">>>>>>>>>>>>>>>>>>>>初始化:MyLogoutSuccessHandler");
    return new MyLogoutSuccessHandler(objectMapper);
  }
}
