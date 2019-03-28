package com.chenfangming.backend.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomAuthFilter extends UsernamePasswordAuthenticationFilter {
    private ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpReq, HttpServletResponse httpResp) throws AuthenticationException {
        LoginReq currentUser;
        try (InputStream is = httpReq.getInputStream()) {
            currentUser = objectMapper.readValue(is, LoginReq.class);
        } catch (IOException e) {
            log.warn("读取用户登录参数流异常：{}", e);
            currentUser = new LoginReq("", "");
        }
        if (null == currentUser) {
            currentUser = new LoginReq("", "");
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(currentUser.getName(), currentUser.getPassword());
        setDetails(httpReq, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class LoginReq {
        /** 用户名 **/
        private String name;
        /** 密码 **/
        private String password;
    }
}
