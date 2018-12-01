package com.chenfangming.backend.manage.config.security;

import com.chenfangming.common.model.response.DefaultResponseStatus.SystemEnum;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * 判断用户是否有权限
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-01 18:55
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    /**
     * 判断用户是否有权限访问资源
     * @param authentication 认证对象
     * @param object         被保护的对象
     * @param collection     当前资源所能访问的角色列表
     * @throws AccessDeniedException               AccessDeniedException
     * @throws InsufficientAuthenticationException InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //  当前资源所有访问的角色列表
        Iterator<ConfigAttribute> canAccessRoleSet = collection.iterator();
        //  当前认证对象所拥有的角色列表
        Collection<? extends GrantedAuthority> currentRoleSet = authentication.getAuthorities();
        while (canAccessRoleSet.hasNext()) {
            //  可以访问的角色
            String canAccess = canAccessRoleSet.next().getAttribute();
            for (GrantedAuthority authority : currentRoleSet) {
                if (authority.getAuthority().equals(canAccess)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException(SystemEnum.NO_PERMISSION_ERROR.getMessage());
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
