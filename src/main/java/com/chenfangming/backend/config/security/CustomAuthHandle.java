package com.chenfangming.backend.config.security;

import com.chenfangming.backend.core.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.chenfangming.backend.core.http.DefaultResponseStatus.AUTHENTICATION_EXCEPTION;

/**
 * 处理认证成功或失败
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-03-26 22:36
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomAuthHandle implements AuthenticationSuccessHandler, AuthenticationFailureHandler, LogoutSuccessHandler {

    private ObjectMapper objectMapper;

    /**
     * 认证成功后流程
     * @param httpReq HttpServletRequest
     * @param httpResp HttpServletResponse
     * @param auth Authentication
     * @throws IOException IOException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpReq, HttpServletResponse httpResp, Authentication auth) throws IOException {
        log.info("用户认证成功:{}", auth);
        String sessionId = httpReq.getSession().getId();
        httpResp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpResp.getWriter().print(objectMapper.writeValueAsString(new ResponseEntity<>(sessionId)));
        httpResp.getWriter().flush();
        httpResp.getWriter().close();
    }

    /**
     * 认证失败后流程
     * @param httpReq HttpServletRequest
     * @param httpResp HttpServletResponse
     * @param e AuthenticationException
     * @throws IOException IOException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpReq, HttpServletResponse httpResp, AuthenticationException e) throws IOException {
        log.warn("用户认证失败:{}", e.getMessage());
        ResponseEntity<String> responseEntity;
        if (e instanceof DisabledException) {
            responseEntity = new ResponseEntity<>(AUTHENTICATION_EXCEPTION, "用户被禁用");
        } else if (e instanceof BadCredentialsException) {
            responseEntity = new ResponseEntity<>(AUTHENTICATION_EXCEPTION, "用户名或密码错误");
        } else {
            responseEntity = new ResponseEntity<>(AUTHENTICATION_EXCEPTION, e.getMessage());
        }
        httpResp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpResp.getWriter().print(objectMapper.writeValueAsString(responseEntity));
        httpResp.getWriter().flush();
        httpResp.getWriter().close();
    }

    /**
     * 注销成功处理流程
     * @param httpReq HttpServletRequest
     * @param httpResp HttpServletResponse
     * @param auth Authentication
     * @throws IOException IOException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest httpReq, HttpServletResponse httpResp, Authentication auth) throws IOException {
        log.info("注销成功处理流程:{}", auth);
        httpResp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpResp.getWriter().print(objectMapper.writeValueAsString(new ResponseEntity<>()));
        httpResp.getWriter().flush();
        httpResp.getWriter().close();

    }
}
