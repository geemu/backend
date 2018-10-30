package com.chenfangming.backend.manage.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenfangming.backend.manage.persistence.entity.PermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 权限数据表操作
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 21:23
 */
@Mapper
@Repository
public interface PermissionMapper extends BaseMapper<PermissionEntity> {
}
