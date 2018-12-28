package com.chenfangming.backend.manage.persistence.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户角色关联数据表
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:35
 */
@Data
public class UserRoleEntity {
    /** 主键  id **/
    private Long id;
    /** **/
    private Long userId;
    /** 角色id **/
    private Long roleId;
    /** 是否可用  0不可用  1可用 **/
    private Boolean isEnable;
    /** 创建人 **/
    private String createUser;
    /** 创建时间 **/
    private Date createTime;
    /** 更新人 **/
    private String updateUser;
    /** 更新时间 **/
    private Date updateTime;
}
