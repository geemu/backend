package com.chenfangming.backend.service;

import com.chenfangming.backend.persistence.mapper.IRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * RoleService.
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-01 14:05
 */
@Slf4j
@Service
public class RoleService {
    @Autowired
    private IRoleMapper roleMapper;

    /**
     * 根据用户id查询用户所拥有的角色id集合.
     * 有效且未删除的
     * @param userId 用户id
     * @return 角色id集合
     */
    public Set<Long> findByUserId(Long userId) {
        return roleMapper.selectByUserId(userId);
    }
}
