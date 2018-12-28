package com.chenfangming.backend.manage.config.security;

import com.chenfangming.common.model.response.DefaultResponseStatus;
import com.chenfangming.common.model.response.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 认证成功处理流程.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 16:02
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  private ObjectMapper objectMapper;

  public MyAuthenticationSuccessHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * 认证成功后流程.
   * @param req 请求
   * @param resp 响应
   * @param authentication 认证
   * @throws IOException IO异常
   */
  @Override
  public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException {
    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
    ResponseEntity<String> response = new ResponseEntity<>(DefaultResponseStatus.SUCCESS, "这是登录成功的token");
    resp.getWriter().print(objectMapper.writeValueAsString(response));
    resp.getWriter().flush();
  }
}
