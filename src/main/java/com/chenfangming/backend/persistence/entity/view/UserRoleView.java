package com.chenfangming.backend.persistence.entity.view;

import com.chenfangming.backend.persistence.entity.RoleEntity;
import com.chenfangming.backend.persistence.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 用户角色视图
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-02-22 22:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRoleView extends UserEntity implements Serializable {
    /** 角色集合 **/
    private List<RoleEntity> roleList;
}
