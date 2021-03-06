package com.chenfangming.backend.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenfangming.backend.persistence.entity.MenuEntity;
import com.chenfangming.backend.persistence.entity.view.MenuRoleView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 权限数据表操作
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 21:23
 */
@Mapper
@Repository
public interface IMenuMapper extends BaseMapper<MenuEntity> {

    /**
     * 查询所有按钮及其可以访问的角色
     * @return 菜单集合
     */
    List<MenuRoleView> selectAllWithRole();

    /**
     * 查询用户菜单
     * @param ids 角色id集合
     * @return 菜单集合
     */
    List<MenuEntity> selectUserMenu(@Param("ids") Set<Long> ids);

}
