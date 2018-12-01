package com.chenfangming.backend.manage.config.security;

import com.chenfangming.common.model.response.DefaultResponseStatus.SystemEnum;
import com.chenfangming.common.model.response.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登出成功处理流程
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 16:02
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 登出成功处理流程
     * @param req            请求
     * @param resp           响应
     * @param authentication 认证
     * @throws IOException      IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        ResponseEntity<String> response = new ResponseEntity<>(SystemEnum.SUCCESS);
        resp.getWriter().print(objectMapper.writeValueAsString(response));
        resp.getWriter().flush();
    }
}
