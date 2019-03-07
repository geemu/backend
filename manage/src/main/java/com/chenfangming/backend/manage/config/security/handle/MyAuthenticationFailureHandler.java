package com.chenfangming.backend.manage.config.security.handle;

import com.chenfangming.common.model.response.DefaultResponseStatus;
import com.chenfangming.common.model.response.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理流程
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 16:20
 */
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private ObjectMapper objectMapper;

    public MyAuthenticationFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 在身份验证尝试失败时调用
     * @param request 请求
     * @param response 响应
     * @param e 异常
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException {
        log.warn("用户认证失败:{}", e.getMessage());
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        ResponseEntity<String> responseEntity;
        if (e instanceof DisabledException) {
            responseEntity = new ResponseEntity<>(
                    DefaultResponseStatus.ACCOUNT_FORBIDDEN_EXCEPTION
            );
            response.getWriter().print(objectMapper.writeValueAsString(responseEntity));
        } else if (e instanceof BadCredentialsException) {
            responseEntity = new ResponseEntity<>(
                    DefaultResponseStatus.AUTHENTICATION_EXCEPTION
            );
            response.getWriter().print(objectMapper.writeValueAsString(responseEntity));
        } else {
            responseEntity = new ResponseEntity<>(
                    DefaultResponseStatus.INTERVAL_SERVER_EXCEPTION
            );
            response.getWriter().print(objectMapper.writeValueAsString(responseEntity));
        }
        response.getWriter().flush();
    }
}
