package com.chenfangming.backend.manage.persistence.entity.view;

import com.chenfangming.backend.manage.persistence.entity.MenuEntity;
import com.chenfangming.backend.manage.persistence.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 角色权限视图
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-02-22 22:21
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuView extends RoleEntity implements Serializable {
    /** 菜单集合 **/
    private List<MenuEntity> menuList;
}
