package com.chenfangming.backend.manage.persistence.mapper;

import com.chenfangming.backend.manage.persistence.entity.PermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限数据表操作
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 21:23
 */
@Mapper
@Repository
public interface PermissionMapper {

    /**
     * 查询权限及其可以访问的角色
     * @return 权限集合
     */
    List<PermissionEntity> select();
}
