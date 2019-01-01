package com.chenfangming.backend.manage.service;

import java.util.Set;

/**
 * IRoleService.
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-01 14:05
 */
public interface IRoleService {

  /**
   * 根据用户id查询用户所拥有的角色id集合.
   * 有效且未删除的
   * @param userId 用户id
   * @return 角色id集合
   */
  Set<Long> findByUserId(Long userId);
}
