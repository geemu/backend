package com.chenfangming.backend.config.security;

import com.chenfangming.backend.core.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销成功处理
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-03-26 22:36
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomAuthHandle implements LogoutSuccessHandler {

    private ObjectMapper objectMapper;

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
