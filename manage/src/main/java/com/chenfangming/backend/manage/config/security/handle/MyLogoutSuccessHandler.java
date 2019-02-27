package com.chenfangming.backend.manage.config.security.handle;

import com.chenfangming.common.model.response.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 注销成功处理流程
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 16:02
 */
@Slf4j
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private ObjectMapper objectMapper;
    private RedisTemplate<String, Object> redisTemplate;

    public MyLogoutSuccessHandler(ObjectMapper objectMapper, RedisTemplate<String, Object> redisTemplate) {
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 注销成功处理流程
     * @param request 请求
     * @param response 响应
     * @param authentication 认证实体
     * @throws IOException IO异常
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        log.info("注销成功处理流程:{}", authentication);
        String token = request.getHeader(MyAuthenticationSuccessHandler.X_ACCESS_TOKEN);
        String key = MyAuthenticationSuccessHandler.LOGIN_USER + token;
        redisTemplate.delete(key);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().print(objectMapper.writeValueAsString(
                new ResponseEntity<>()
        ));
        response.getWriter().flush();
    }
}
