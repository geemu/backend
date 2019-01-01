package com.chenfangming.backend.manage.service.impl;

import com.chenfangming.backend.manage.persistence.entity.MenuEntity;
import com.chenfangming.backend.manage.persistence.mapper.IMenuMapper;
import com.chenfangming.backend.manage.service.MenuService;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * com.chenfangming.backend.manage.service.impl
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-12 23:17
 */
@Service
public class MenuServiceImpl implements MenuService {
  private IMenuMapper menuMapper;

  public MenuServiceImpl(IMenuMapper menuMapper) {
    this.menuMapper = menuMapper;
  }

  @Override
  public List<MenuEntity> selectUserMenu(Set<Long> ids) {
    return menuMapper.selectUserMenu(ids);
  }
}
