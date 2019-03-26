package com.chenfangming.backend.persistence.entity.view;

import com.chenfangming.backend.persistence.entity.MenuEntity;
import com.chenfangming.backend.persistence.entity.RoleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 权限视角色图
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-02-22 22:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuRoleView extends MenuEntity implements Serializable {
    /** 角色集合 **/
    private List<RoleEntity> roleList;
}
