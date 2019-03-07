package com.chenfangming.backend.manage.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenfangming.backend.manage.persistence.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * 角色数据表操作
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 21:22
 */
@Mapper
@Repository
public interface IRoleMapper extends BaseMapper<RoleEntity> {

    /**
     * 根据角色名查询
     * @param name 角色名
     * @return 查询到的角色
     */
    RoleEntity selectByName(@Param("name") String name);

    /**
     * 根据用户id查询角色
     * @param userId 用户id
     * @return 角色集合
     */
    Set<Long> selectByUserId(@Param("userId") Long userId);
}
