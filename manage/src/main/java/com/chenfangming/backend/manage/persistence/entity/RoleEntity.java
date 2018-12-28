package com.chenfangming.backend.manage.persistence.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 角色数据表
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:35
 */
@Data
public class RoleEntity {
    /** 主键  角色id **/
    private Long id;
    /** 角色名称  忽略大小写 **/
    private String name;
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
    /** 权限集合 **/
    private List<MenuEntity> menuEntityList;
}
