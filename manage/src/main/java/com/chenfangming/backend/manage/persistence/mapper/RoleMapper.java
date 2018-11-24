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
public interface RoleMapper {


    Set<RoleEntity> selectByUserId(@Param("userId") Long userId);
}
