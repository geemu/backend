package com.chenfangming.backend.manage.security;

import com.chenfangming.common.model.response.DefaultResponseStatus.SystemEnum;
import com.chenfangming.common.model.response.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足处理流程  用来解决匿名用户访问无权限资源时的异常
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 17:03
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 权限不足处理流程  用来解决匿名用户访问无权限资源时的异常
     * @param req           请求
     * @param resp          响应
     * @param authException 异常
     * @throws IOException      IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException authException) throws IOException, ServletException {
        ResponseEntity<Void> response = new ResponseEntity<>(SystemEnum.NO_PERMISSION_ERROR);
        resp.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");
        resp.getWriter().print(objectMapper.writeValueAsString(response));
        resp.getWriter().flush();
    }
}
