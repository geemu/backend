package com.chenfangming.backend.manage.service;

import com.chenfangming.backend.manage.persistence.entity.MenuEntity;
import java.util.List;
import java.util.Set;

/**
 * 菜单业务逻辑.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-12 23:14
 */
public interface MenuService {
  /**
   * 查询用户菜单.
   * @param ids 角色id集合
   * @return 菜单集合
   */
  List<MenuEntity> selectUserMenu(Set<Long> ids);
}
