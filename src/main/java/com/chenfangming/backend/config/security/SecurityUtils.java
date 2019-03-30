package com.chenfangming.backend.config.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * com.chenfangming.backend.config.security
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-03-30 11:10
 */
public final class SecurityUtils {


    /**
     * 获取当前用户 如果为匿名用户返回null
     * @return CustomUserDetails
     */
    public static CustomUserDetails getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (null == context) {
            return null;
        }
        Authentication auth = context.getAuthentication();
        if (null == auth) {
            return null;
        }
        if (auth instanceof AnonymousAuthenticationToken) {
            return null;
        }
        Object principal = auth.getPrincipal();
        if (null == principal) {
            return null;
        }
        return (CustomUserDetails) principal;
    }

    /**
     * 获取当前用户 如果为匿名用户返回null
     * @return CustomUserDetails
     */
    public static CustomUserDetails getCurrentUser(Authentication auth) {
        if (null == auth) {
            return null;
        }
        if (auth instanceof AnonymousAuthenticationToken) {
            return null;
        }
        Object principal = auth.getPrincipal();
        if (null == principal) {
            return null;
        }
        return (CustomUserDetails) principal;
    }


}
