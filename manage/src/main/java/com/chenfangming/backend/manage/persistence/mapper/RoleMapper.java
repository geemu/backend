package com.chenfangming.backend.manage.persistence.mapper;

import com.chenfangming.backend.manage.persistence.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色数据表操作
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 21:22
 */
@Mapper
@Repository
public interface RoleMapper {

    /**
     * 根据用户id查询角色 未删除有效的
     * @param userId 用户id
     * @return 角色集合
     */
    List<RoleEntity> selectByUserId(@Param("userId") Long userId);
}
