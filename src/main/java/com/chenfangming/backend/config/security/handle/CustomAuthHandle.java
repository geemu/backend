package com.chenfangming.backend.config.security.handle;

import com.chenfangming.backend.core.http.DefaultResponseStatus;
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
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理认证成功或失败
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-03-26 22:36
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomAuthHandle implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    private ObjectMapper objectMapper;

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
        httpResp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        com.chenfangming.backend.core.http.ResponseEntity<String> responseEntity;
        if (e instanceof DisabledException) {
            responseEntity = new com.chenfangming.backend.core.http.ResponseEntity<>(
                    DefaultResponseStatus.ACCOUNT_FORBIDDEN_EXCEPTION
            );
            httpResp.getWriter().print(objectMapper.writeValueAsString(responseEntity));
        } else if (e instanceof BadCredentialsException) {
            responseEntity = new com.chenfangming.backend.core.http.ResponseEntity<>(
                    DefaultResponseStatus.AUTHENTICATION_EXCEPTION
            );
            httpResp.getWriter().print(objectMapper.writeValueAsString(responseEntity));
        } else {
            responseEntity = new com.chenfangming.backend.core.http.ResponseEntity<>(
                    DefaultResponseStatus.INTERVAL_SERVER_EXCEPTION
            );
            httpResp.getWriter().print(objectMapper.writeValueAsString(responseEntity));
        }
        httpResp.getWriter().flush();
    }

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
    }
}
