package com.chenfangming.backend.manage.persistence.entity.view;

import com.chenfangming.backend.manage.persistence.entity.MenuEntity;
import lombok.*;

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
public class RoleMenuView extends MenuEntity implements Serializable {
    /** 菜单集合 **/
    private List<MenuEntity> menuList;
}
