package com.chenfangming.backend.manage.config.security;

import com.chenfangming.common.model.response.DefaultResponseStatus.SystemEnum;
import com.chenfangming.common.model.response.DefaultResponseStatus.UserEnum;
import com.chenfangming.common.model.response.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理流程
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 16:20
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 认证失败后流程
     * @param req       请求
     * @param resp      响应
     * @param exception 异常
     * @throws IOException      IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        ResponseEntity<Void> response;
        //  用户名或密码错误
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            response = new ResponseEntity<>(UserEnum.USER_OR_PASSWORD_IN_CORRECT_ERROR);
        }
        //  账户被禁用
        else if (exception instanceof DisabledException) {
            response = new ResponseEntity<>(UserEnum.USER_NOT_ENABLE_ERROR);
        }
        //  其它未知异常
        else {
            response = new ResponseEntity<>(SystemEnum.INTERVAL_SERVER_ERROR);
        }
        resp.getWriter().print(objectMapper.writeValueAsString(response));
        resp.getWriter().flush();
    }
}
