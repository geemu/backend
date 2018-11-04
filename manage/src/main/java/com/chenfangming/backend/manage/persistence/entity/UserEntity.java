package com.chenfangming.backend.manage.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 用户数据表实体
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:34
 */
@Data
@TableName(value = "backend_user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -8563047132273562257L;
    /** 角色集合 **/
    @TableField(exist = false)
    Set<RoleEntity> roleEntitySet;
    /** 主键  id **/
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 用户名  忽略大小写 **/
    private String name;
    /** 密码  区分大小写 **/
    @JsonIgnore
    private String password;
    /** 密码盐值  区分大小写 **/
    private String salt;
    /** 是否可用  0不可用  1可用 **/
    @TableField("is_enable")
    private Boolean isEnable;
    /** 是否未删除  0已删除  1未删除 **/
    @TableField("is_non_delete")
    private Boolean isNonDelete;
    /** 创建人 **/
    @TableField("create_user")
    private String createUser;
    /** 创建时间 **/
    @TableField("create_time")
    private Date createTime;
    /** 更新人 **/
    @TableField("update_user")
    private String updateUser;
    /** 更新时间 **/
    @TableField("update_time")
    private Date updateTime;
    /** 乐观锁 **/
    @Version
    private Long version;

}
