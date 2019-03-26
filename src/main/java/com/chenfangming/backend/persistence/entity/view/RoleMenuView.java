package com.chenfangming.backend.persistence.entity.view;

import com.chenfangming.backend.persistence.entity.MenuEntity;
import com.chenfangming.backend.persistence.entity.RoleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 角色权限视图
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-02-22 22:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenuView extends RoleEntity implements Serializable {
    /** 菜单集合 **/
    private List<MenuEntity> menuList;
}
