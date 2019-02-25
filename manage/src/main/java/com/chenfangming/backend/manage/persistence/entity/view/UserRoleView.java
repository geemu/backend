package com.chenfangming.backend.manage.persistence.entity.view;

import com.chenfangming.backend.manage.persistence.entity.UserEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 用户角色视图
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-02-22 22:19
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleView extends UserEntity implements Serializable {
    /** 角色集合 **/
    private List<RoleMenuView> roleMenuViewList;
}
