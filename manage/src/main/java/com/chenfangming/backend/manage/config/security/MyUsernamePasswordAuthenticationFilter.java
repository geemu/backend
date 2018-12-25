package com.chenfangming.backend.manage.config.security;

import com.chenfangming.backend.manage.domain.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义用户登录
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-23 13:03
 */
@Slf4j
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authRequest;
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = request.getInputStream()) {
            LoginRequest loginRequest = objectMapper.readValue(is, LoginRequest.class);
            authRequest = new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
            authRequest = new UsernamePasswordAuthenticationToken("", "");
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
