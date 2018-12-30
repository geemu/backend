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
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;


/**
 * 注销成功处理流程.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 16:02
 */
@Slf4j
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

  /** ObjectMapper. **/
  private ObjectMapper objectMapper;

  /**
   * 构造器注入.
   * @param objectMapper objectMapper
   */
  public MyLogoutSuccessHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * 注销成功处理流程.
   * @param request 请求
   * @param response 响应
   * @param authentication 认证实体
   * @throws IOException IO异常
   */
  @Override
  public void onLogoutSuccess(HttpServletRequest request,
                              HttpServletResponse response,
                              Authentication authentication) throws IOException {
    log.info(">>>>>>>>>>>>>>>>>>>>注销成功处理流程:{}<<<<<<<<<<<<<<<<<<<<", authentication);
    response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
    response.getWriter().print(objectMapper.writeValueAsString(
            new ResponseEntity<>(
                    DefaultResponseStatus.SUCCESS,
                    "注销成功")
    ));
    response.getWriter().flush();
  }
}
