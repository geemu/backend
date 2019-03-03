package com.chenfangming.backend.manage.persistence.mapper;

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
public interface IRoleMapper {

    /**
     * 新增
     * @param entity 用户实体
     * @return 新增结果
     */
    boolean insertByEntity(RoleEntity entity);

    /**
     * 根据主键删除
     * @param id 主键
     * @return 删除结果
     */
    boolean deleteById(Long id);

    /**
     * 根据主键修改
     * @param entity 实体
     * @return 修改结果
     */
    boolean updateById(RoleEntity entity);

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
