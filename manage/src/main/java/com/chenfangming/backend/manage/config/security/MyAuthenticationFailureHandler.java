package com.chenfangming.backend.manage.config.security;

import com.chenfangming.common.model.response.DefaultResponseStatus;
import com.chenfangming.common.model.response.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理流程
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 16:20
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 认证失败后流程
     * @param req       请求
     * @param resp      响应
     * @param exception 异常
     * @throws IOException IO异常
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException {
        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        ResponseEntity<Void> response;
        //  用户名或密码错误
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            response = new ResponseEntity<>(DefaultResponseStatus.ACCOUNT_OR_PASSWORD_IN_CORRECT_ERROR, exception.getMessage());
        }
        //  账户被禁用
        else if (exception instanceof DisabledException) {
            response = new ResponseEntity<>(DefaultResponseStatus.ACCOUNT_DISABLE_ERROR, exception.getMessage());
        }
        //  其它未知异常
        else {
            response = new ResponseEntity<>(DefaultResponseStatus.INTERVAL_SERVER_ERROR);
        }
        resp.getWriter().print(objectMapper.writeValueAsString(response));
        resp.getWriter().flush();
    }
}
