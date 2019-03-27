package com.chenfangming.backend.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 自定义用户登录
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-23 13:03
 */
@Slf4j
public class CustomAuthFilter extends UsernamePasswordAuthenticationFilter {
    /** 用户名参数 **/
    private static final String NAME_KEY = "name";
    /** 密码参数 **/
    private static final String PASSWORD_KEY = "password";
    /** 限制只能POST **/
    private boolean postOnly = true;
    /** ObjectMapper. **/
    private ObjectMapper objectMapper;

    public CustomAuthFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpReq, HttpServletResponse httpResp) throws AuthenticationException {
        String name;
        String password;
        //  JSON格式认证
        try (InputStream is = httpReq.getInputStream()) {
            Map map = objectMapper.readValue(is, Map.class);
            name = (String) map.get(NAME_KEY);
            password = (String) map.get(PASSWORD_KEY);
        } catch (IOException e) {
            log.error("读取用户登录参数异常：{}", e);
            throw new InsufficientAuthenticationException("用户名不能为空");
        }
        if (null == name) {
            throw new InsufficientAuthenticationException("用户名不能为空");
        }
        if (null == password) {
            throw new InsufficientAuthenticationException("密码不能为空");
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(name, password);
        setDetails(httpReq, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 限制只能POST.
     * @param postOnly postOnly
     */
    @Override
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
