package com.chenfangming.backend.manage.persistence.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户数据表实体
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:34
 */
@Data
public class UserEntity implements Serializable {
    /** 主键  id **/
    private Long id;
    /** 用户名  忽略大小写 **/
    private String name;
    /** 密码  区分大小写 **/
    private String password;
    /** 密码盐值  区分大小写 **/
    private String salt;
    /** 是否可用  0不可用  1可用 **/
    private Boolean isEnable;
    /** 是否未删除  0已删除  1未删除 **/
    private Boolean isNonDelete;
    /** 创建人 **/
    private String createUser;
    /** 创建时间 **/
    private Date createTime;
    /** 更新人 **/
    private String updateUser;
    /** 更新时间 **/
    private Date updateTime;
    /** 角色集合 **/
    private List<RoleEntity> roleEntityList;
}
