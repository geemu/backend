package com.chenfangming.backend.manage.config.security;

import com.chenfangming.common.StringHelper;
import com.chenfangming.common.model.response.DefaultResponseStatus;
import com.chenfangming.common.model.response.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.ValueOperations;
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
  /** ObjectMapper. **/
  private ObjectMapper objectMapper;
  /** ValueOperations. **/
  private ValueOperations<Object, Object> valueOperations;
  /** 进入Token. **/
  public static final String ACCESS_TOKEN = "X-Access-Token";

  public MyAuthenticationSuccessHandler(ObjectMapper objectMapper, ValueOperations<Object, Object> valueOperations) {
    this.objectMapper = objectMapper;
    this.valueOperations = valueOperations;
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
    String uuid = StringHelper.uuid();
    valueOperations.set("loginUser:" + uuid, authentication, 1000L, TimeUnit.SECONDS);
    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
    resp.setHeader(ACCESS_TOKEN, uuid);
    ResponseEntity<String> response = new ResponseEntity<>(DefaultResponseStatus.SUCCESS, uuid);
    resp.getWriter().print(objectMapper.writeValueAsString(response));
    resp.getWriter().flush();
  }
}
