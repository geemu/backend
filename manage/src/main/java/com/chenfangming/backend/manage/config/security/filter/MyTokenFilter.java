package com.chenfangming.backend.manage.config.security.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Token解析认证实体.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-30 16:17
 */
@Slf4j
public class MyTokenFilter extends OncePerRequestFilter {
  /** RedisTemplate. **/
  private RedisTemplate<Object, Object> redisTemplate;

  /**
   * 构造器注入.
   * @param redisTemplate redisTemplate
   */
  public MyTokenFilter(RedisTemplate<Object, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * 执行过滤器.
   * @param request request
   * @param response response
   * @param chain chain
   * @throws ServletException ServletException
   * @throws IOException IOException
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain chain) throws ServletException, IOException {
    String accessToken = request.getHeader("X-Access-Token");
    if (null != accessToken) {
      accessToken = "loginUser:" + accessToken;
      Object redisData = redisTemplate.opsForValue().get(accessToken);
      if (ObjectUtils.isEmpty(redisData)) {
        chain.doFilter(request, response);
      }
      Map map = (Map) redisData;
      System.out.println(map);
      List<SimpleGrantedAuthority> authorities = (List) map.get("authorities");
      UsernamePasswordAuthenticationToken data = new UsernamePasswordAuthenticationToken(map, map, authorities);
      data.setAuthenticated(true);
      SecurityContextHolder.createEmptyContext().setAuthentication(data);
    }
    chain.doFilter(request, response);
  }
}
