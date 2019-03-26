package com.chenfangming.backend.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色权限关联数据表实体
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:36
 */
@Data
@TableName("backend_role_menu")
public class RoleMenuEntity implements Serializable {
    /** 主键  id **/
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 角色id **/
    @TableField("role_id")
    private Long roleId;
    /** 菜单id **/
    @TableField("menu_id")
    private Long menuId;
    /** 是否可用  0不可用  1可用 **/
    @TableField("enabled")
    private Boolean enabled;
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
}
