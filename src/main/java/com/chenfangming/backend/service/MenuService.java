package com.chenfangming.backend.service;

import com.chenfangming.backend.persistence.entity.MenuEntity;
import com.chenfangming.backend.persistence.mapper.IMenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 菜单业务逻辑.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-12 23:14
 */
@Slf4j
@Service
public class MenuService {
    private IMenuMapper menuMapper;

    public MenuService(IMenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    /**
     * 查询用户菜单.
     * @param ids 角色id集合
     * @return 菜单集合
     */
    public List<MenuEntity> selectUserMenu(Set<Long> ids) {
        return menuMapper.selectUserMenu(ids);
    }
}
