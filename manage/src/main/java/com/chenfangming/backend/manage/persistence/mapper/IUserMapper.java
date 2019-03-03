package com.chenfangming.backend.manage.persistence.mapper;

import com.chenfangming.backend.manage.persistence.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户数据表操作
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 21:20
 */
@Mapper
@Repository
public interface IUserMapper {

    /**
     * 新增
     * @param entity 用户实体
     * @return 新增结果
     */
    boolean insertByEntity(UserEntity entity);

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
    boolean updateById(UserEntity entity);

    /**
     * 根据用户名查询
     * @param name 用户名
     * @return 查询到的用户
     */
    UserEntity selectByName(@Param("name") String name);

}
