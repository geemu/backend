package com.chenfangming.backend.manage.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 自定义用户登录.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-23 13:03
 */
@Slf4j
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  /** 限制只能POST. **/
  private boolean postOnly = true;
  /** 用户名参数. **/
  private static final String NAME_KEY = "name";
  /** 密码参数. **/
  private static final String PASSWORD_KEY = "password";
  /** ObjectMapper. **/
  @Autowired
  private ObjectMapper objectMapper;


  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response) throws AuthenticationException {
    if (postOnly && !HttpMethod.POST.name().equals(request.getMethod())) {
      throw new AuthenticationServiceException("不支持的身份验证方法: " + request.getMethod());
    }
    String name = null;
    String password = null;
    //  JSON格式认证
    try (InputStream is = request.getInputStream()) {
      Map map = objectMapper.readValue(is, Map.class);
      name = (String) map.get(NAME_KEY);
      password = (String) map.get(PASSWORD_KEY);
    } catch (IOException e) {
      log.error("以JSON格式执行认证操作时，读取认证参数异常:{}", e);
    }
    name = null == name ? "" : name.trim();
    password = null == password ? "" : password.trim();
    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(name, password);
    setDetails(request, authRequest);
    return this.getAuthenticationManager().authenticate(authRequest);
  }

  /**
   * 限制只能POST.
   * @param postOnly postOnly
   */
  @Override
  public void setPostOnly(boolean postOnly) {
    this.postOnly = postOnly;
  }
}
