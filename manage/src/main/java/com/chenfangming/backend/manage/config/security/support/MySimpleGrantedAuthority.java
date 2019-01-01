package com.chenfangming.backend.manage.config.security.support;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

/**
 * MySimpleGrantedAuthority.
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-01 17:04
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class MySimpleGrantedAuthority implements GrantedAuthority {
  /** 角色id. **/
  private final String authority;
}