package com.chenfangming.backend.manage.persistence.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色关联数据表实体
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:35
 */
@Data
public class UserRoleEntity implements Serializable {
    /** 主键  id **/
    private Long id;
    /** 用户id **/
    private Long userId;
    /** 角色id **/
    private Long roleId;
    /** 是否可用  0不可用  1可用 **/
    private Boolean isEnable;
    /** 是否未删除  0已删除  1未删除 **/
    private Boolean isNonDelete;
    /** 创建人 **/
    private Long createUser;
    /** 创建时间 **/
    private Date createTime;
    /** 更新人 **/
    private Long updateUser;
    /** 更新时间 **/
    private Date updateTime;
}
