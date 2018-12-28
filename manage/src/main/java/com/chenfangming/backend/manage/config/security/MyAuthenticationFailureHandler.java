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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 认证失败处理流程.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 16:20
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
  private ObjectMapper objectMapper;

  public MyAuthenticationFailureHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * 在身份验证尝试失败时调用.
   * @param req 请求
   * @param resp 响应.
   * @param exception 异常
   */
  @Override
  public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException {
    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
    ResponseEntity<Void> response;
    if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
      //  用户名或密码错误
      response = new ResponseEntity<>(DefaultResponseStatus.ACCOUNT_OR_PASSWORD_IN_CORRECT_ERROR);
    } else if (exception instanceof DisabledException) {
      //  账户被禁用
      response = new ResponseEntity<>(DefaultResponseStatus.ACCOUNT_DISABLE_ERROR);
    } else {
      //  其它认证异常
      response = new ResponseEntity<>(DefaultResponseStatus.OTHER_AUTHENTICATION_ERROR, exception.getMessage());
    }
    resp.getWriter().print(objectMapper.writeValueAsString(response));
    resp.getWriter().flush();
  }
}
