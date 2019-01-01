package com.chenfangming.backend.manage.config.security.support;

import com.chenfangming.common.model.response.DefaultResponseStatus;
import java.util.Collection;
import java.util.Iterator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 判断用户是否有权限.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-01 18:55
 */
@Slf4j
public class MyAccessDecisionManager implements AccessDecisionManager {
  /**
   * 判断用户是否有权限访问资源.
   * @param authentication 认证对象
   * @param object 被保护的对象
   * @param collection 当前资源所能访问的角色列表
   * @throws AccessDeniedException AccessDeniedException
   * @throws InsufficientAuthenticationException InsufficientAuthenticationException
   */
  @Override
  public void decide(Authentication authentication,
                     Object object,
                     Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
    if (authentication instanceof AnonymousAuthenticationToken) {
      log.info("匿名用户，拒绝访问受保护资源");
      throw new InsufficientAuthenticationException(DefaultResponseStatus.NO_AUTHENTICATION_FAIL.getMessage());
    }
    //  当前资源所有访问的角色列表
    Iterator<ConfigAttribute> canAccessRoleSet = collection.iterator();
    //  当前认证对象所拥有的角色列表
    Collection<? extends GrantedAuthority> currentRoleSet = authentication.getAuthorities();
    while (canAccessRoleSet.hasNext()) {
      //  可以访问的角色
      String canAccess = canAccessRoleSet.next().getAttribute();
      for (GrantedAuthority authority : currentRoleSet) {
        if (authority.getAuthority().equals(canAccess)) {
          log.info("鉴权通过，可以访问受保护资源");
          return;
        }
      }
    }
    log.info("鉴权不通过，当前认证用户无权访问受保护资源");
    throw new AccessDeniedException(DefaultResponseStatus.ACCESS_DENIED_FAIL.getMessage());
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
