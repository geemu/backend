package com.chenfangming.backend.manage.persistence.mapper;

import com.chenfangming.backend.manage.persistence.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户数据表操作.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 21:20
 */
@Mapper
@Repository
public interface UserMapper {

  /**
   * 根据用户名查询用户.
   * @param name 用户名
   * @return 查询到的用户
   */
  UserEntity selectByName(@Param("name") String name);

}
