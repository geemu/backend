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
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /**
     * 根据用户id和角色状态查询角色  未删除的
     * @param userId   用户id
     * @param isEnable 角色状态
     * @return 角色列表
     */
    Set<RoleEntity> selectByUserId(@Param("userId") Long userId, @Param("isEnable") Boolean isEnable);
}
