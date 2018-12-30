package com.chenfangming.backend.manage.config.security.handle;

import com.chenfangming.common.model.response.DefaultResponseStatus;
import com.chenfangming.common.model.response.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 认证用户访问无权限资源处理流程.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 16:48
 */
@Slf4j
public class MyAuthenticationDeniedHandler implements AccessDeniedHandler {
  /** ObjectMapper. **/
  private ObjectMapper objectMapper;

  /**
   * 构造器注入.
   * @param objectMapper objectMapper
   */
  public MyAuthenticationDeniedHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * 认证用户访问无权限资源处理流程.
   * @param request 请求
   * @param response 响应
   * @param e 异常
   * @throws IOException IO异常
   */
  @Override
  public void handle(HttpServletRequest request,
                     HttpServletResponse response,
                     AccessDeniedException e) throws IOException {
    log.info("认证用户访问无权限资源:{}", e.getMessage());
    response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
    response.getWriter().print(objectMapper.writeValueAsString(
            new ResponseEntity<>(
                    DefaultResponseStatus.ACCESS_DENIED_ERROR)
    ));
    response.getWriter().flush();
  }
}
