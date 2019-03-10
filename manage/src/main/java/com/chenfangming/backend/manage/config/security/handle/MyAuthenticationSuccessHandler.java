package com.chenfangming.backend.manage.config.security.handle;

import com.chenfangming.backend.manage.config.security.support.MySimpleGrantedAuthority;
import com.chenfangming.backend.manage.config.security.support.MyUserDetails;
import com.chenfangming.backend.manage.persistence.entity.RoleEntity;
import com.chenfangming.backend.manage.persistence.entity.view.UserRoleView;
import com.chenfangming.common.model.response.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 认证成功处理流程
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-23 16:02
 */
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    public static final String X_ACCESS_TOKEN = "X-Access-Token";
    public static final String LOGIN_USER = "loginUser:";
    private static final long DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS = 1800L;
    private ObjectMapper objectMapper;
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 构造器注入
     * @param objectMapper objectMapper
     * @param redisTemplate redisTemplate
     */
    public MyAuthenticationSuccessHandler(ObjectMapper objectMapper, RedisTemplate<String, Object> redisTemplate) {
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 认证成功后流程
     * @param request 请求
     * @param response 响应
     * @param authentication 认证
     * @throws IOException IO异常
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("用户认证成功:{}", authentication);
        String token = request.getSession().getId();
        String key = LOGIN_USER + token;
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        UserRoleView userRoleView = new UserRoleView();
        userRoleView.setId(myUserDetails.getId());
        userRoleView.setName(myUserDetails.getUsername());
        List<RoleEntity> roleList = new ArrayList<>();
        for (MySimpleGrantedAuthority item : myUserDetails.getAuthorities()) {
            RoleEntity entity = new RoleEntity();
            entity.setId(NumberUtils.toLong(item.getAuthority()));
            roleList.add(entity);
        }
        userRoleView.setRoleList(roleList);
        redisTemplate.opsForValue().set(key, myUserDetails, DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS, TimeUnit.SECONDS);
        response.setHeader(X_ACCESS_TOKEN, token);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().print(objectMapper.writeValueAsString(new ResponseEntity<>(token)));
        response.getWriter().flush();
    }

}
