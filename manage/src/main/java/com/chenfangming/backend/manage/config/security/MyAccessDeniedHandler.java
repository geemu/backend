package com.chenfangming.backend.manage.config.security;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * 权限不足处理流程  用来解决认证过的用户访问无权限资源时的异常.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 16:48
 */
@Slf4j
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
  private ObjectMapper objectMapper;

  public MyAccessDeniedHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * 权限不足处理流程  用来解决认证过的用户访问无权限资源时的异常.
   * @param req 请求
   * @param resp 响应
   * @param e 异常
   * @throws IOException IO异常
   */
  @Override
  public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    log.info("认证用户[{}]访问受保护资源，返回未授权", user.getUsername());
    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
    ResponseEntity<Void> response = new ResponseEntity<>(DefaultResponseStatus.NO_AUTHORIZATION_ERROR, e.getMessage());
    resp.getWriter().print(objectMapper.writeValueAsString(response));
    resp.getWriter().flush();
  }
}
