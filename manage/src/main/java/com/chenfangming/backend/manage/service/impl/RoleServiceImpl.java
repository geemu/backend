package com.chenfangming.backend.manage.service.impl;

import com.chenfangming.backend.manage.persistence.mapper.IRoleMapper;
import com.chenfangming.backend.manage.service.IRoleService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * com.chenfangming.backend.manage.service.impl
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-01 14:07
 */
@Service
public class RoleServiceImpl implements IRoleService {
    private IRoleMapper roleMapper;
    private Mapper mapper;

    public RoleServiceImpl(IRoleMapper roleMapper, Mapper mapper) {
        this.roleMapper = roleMapper;
        this.mapper = mapper;
    }

    /**
     * 根据用户id查询用户所拥有的角色id集合.
     * 有效且未删除的
     * @param userId 用户id
     * @return 角色id集合
     */
    @Override
    public Set<Long> findByUserId(Long userId) {
        return roleMapper.selectByUserId(userId);
    }
}
