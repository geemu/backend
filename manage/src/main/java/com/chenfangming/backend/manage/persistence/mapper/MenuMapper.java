package com.chenfangming.backend.manage.persistence.mapper;

import com.chenfangming.backend.manage.persistence.entity.MenuEntity;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 权限数据表操作.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 21:23
 */
@Mapper
@Repository
public interface MenuMapper {

  /**
   * 查询所有菜单及其可以访问的角色.
   * @return 菜单集合
   */
  List<MenuEntity> selectAllWithRole();

  /**
   * 查询用户菜单.
   * @param ids 角色id集合
   * @return 菜单集合
   */
  List<MenuEntity> selectUserMenu(@Param("ids") Set<Long> ids);

}
