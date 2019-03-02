package com.chenfangming.backend.manage.config.security.filter;

import com.chenfangming.backend.manage.config.security.handle.MyAuthenticationSuccessHandler;
import com.chenfangming.backend.manage.config.security.support.MyUserDetails;
import com.chenfangming.common.model.response.DefaultResponseStatus;
import com.chenfangming.common.model.response.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token解析认证实体
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-30 16:17
 */
@Slf4j
public class MyTokenFilter extends OncePerRequestFilter {
    private RedisTemplate<String, Object> redisTemplate;
    private ObjectMapper objectMapper;

    public MyTokenFilter(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * 除登录外所有的请求都拦截
     * @param req req
     * @param resp resp
     * @param chain chain
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String token = req.getHeader(MyAuthenticationSuccessHandler.X_ACCESS_TOKEN);
        if (null != token) {
            String accessToken = MyAuthenticationSuccessHandler.LOGIN_USER + token;
            MyUserDetails myUserDetails = (MyUserDetails) redisTemplate.opsForValue().get(accessToken);
            if (null == myUserDetails) {
                resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
                resp.getWriter().print(objectMapper.writeValueAsString(
                        new ResponseEntity<>(
                                DefaultResponseStatus.AUTHORIZATION_EXCEPTION)
                ));
                return;
            }
            UsernamePasswordAuthenticationToken data = new UsernamePasswordAuthenticationToken(
                    myUserDetails.getUsername(),
                    myUserDetails.getUsername(),
                    myUserDetails.getAuthorities());
            SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(data);
        } else {
            log.warn("当前请求未携带Token：[url:{},method:{}]", req.getRequestURI(), req.getMethod());
        }
        chain.doFilter(req, resp);
    }
}
