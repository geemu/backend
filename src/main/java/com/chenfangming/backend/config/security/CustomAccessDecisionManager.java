package com.chenfangming.backend.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

import static com.chenfangming.backend.core.http.DefaultResponseStatus.ACCESS_ANNO_EXCEPTION;
import static com.chenfangming.backend.core.http.DefaultResponseStatus.ACCESS_AUTH_EXCEPTION;

/**
 * 判断用户是否有权限
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-01 18:55
 */
@Slf4j
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    /** 超级管理员用户的用户名 **/
    private static final String ADMIN = "admin";
    /** 登录用户的角色编号 **/
    static String ROLE_LOGIN = "ROLE_LOGIN";

    /**
     * 判断用户是否有权限访问资源
     * @param auth 认证对象
     * @param obj 被保护的对象
     * @param col 当前资源所能访问的角色列表
     * @throws AccessDeniedException AccessDeniedException
     * @throws InsufficientAuthenticationException InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication auth, Object obj, Collection<ConfigAttribute> col) throws AccessDeniedException, InsufficientAuthenticationException {
        CustomUserDetails currentUser = SecurityUtils.getCurrentUser(auth);
        if (null == currentUser) {
            log.warn("未登录用户拒绝访问受保护资源");
            throw new InsufficientAuthenticationException(ACCESS_ANNO_EXCEPTION.getMessage());
        }
        if (ADMIN.equalsIgnoreCase(currentUser.getUsername())) {
            log.warn("当前为超级管理员用户,可以访问所有资源");
            return;
        }
        // 当前资源角色列表
        Iterator<ConfigAttribute> menuRoleList = col.iterator();
        // 当前用户角色列表
        Collection<? extends GrantedAuthority> currentUserRoleList = auth.getAuthorities();
        while (menuRoleList.hasNext()) {
            // 当前资源角色
            String menRole = menuRoleList.next().getAttribute();
            // 如果当前资格登录用户就可以访问，那么直接直接放行当前登录用户
            if (ROLE_LOGIN.equals(menRole)) {
                log.warn("当前资源登录用户即可访问");
                return;
            }
            for (GrantedAuthority currentUserRole : currentUserRoleList) {
                if (currentUserRole.getAuthority().equals(menRole)) {
                    log.info("鉴权通过,用户:[{}]可以访问受保护资源", currentUser.getUsername());
                    return;
                }
            }
        }
        log.warn("鉴权不通过,用户:[{}]拒绝访问无权限资源", currentUser.getUsername());
        throw new AccessDeniedException(ACCESS_AUTH_EXCEPTION.getMessage());
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
