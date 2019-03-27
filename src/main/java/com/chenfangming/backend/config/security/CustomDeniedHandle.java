package com.chenfangming.backend.config.security;

import com.chenfangming.backend.core.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.chenfangming.backend.core.http.DefaultResponseStatus.ACCESS_ANNO_EXCEPTION;
import static com.chenfangming.backend.core.http.DefaultResponseStatus.ACCESS_AUTH_EXCEPTION;

/**
 * 处理鉴权失败
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-03-26 22:27
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomDeniedHandle implements AuthenticationEntryPoint, AccessDeniedHandler {

    private ObjectMapper objectMapper;

    /**
     * 匿名用户访问无权限资源处理流程
     * @param httpReq HttpServletRequest
     * @param httpResp HttpServletResponse
     * @param e AuthenticationException
     * @throws IOException IOException
     */
    @Override
    public void commence(HttpServletRequest httpReq, HttpServletResponse httpResp, AuthenticationException e) throws IOException {
        log.warn("匿名用户访问无权限资源:{}", e.getMessage());
        httpResp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpResp.getWriter().print(objectMapper.writeValueAsString(new ResponseEntity<>(ACCESS_ANNO_EXCEPTION)));
        httpResp.getWriter().flush();
        httpResp.getWriter().close();
    }

    /**
     * 认证用户访问无权限资源处理流程
     * @param httpReq HttpServletRequest
     * @param httpResp HttpServletResponse
     * @param e AccessDeniedException
     * @throws IOException IOException
     */
    @Override
    public void handle(HttpServletRequest httpReq, HttpServletResponse httpResp, AccessDeniedException e) throws IOException {
        log.info("认证用户访问无权限资源:{}", e.getMessage());
        httpResp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpResp.getWriter().print(objectMapper.writeValueAsString(new ResponseEntity<>(ACCESS_AUTH_EXCEPTION)));
        httpResp.getWriter().flush();
        httpResp.getWriter().close();
    }
}
