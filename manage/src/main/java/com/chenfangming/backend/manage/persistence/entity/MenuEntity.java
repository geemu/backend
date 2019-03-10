package com.chenfangming.backend.manage.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 权限菜单数据表
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:36
 */
@Data
@TableName("backend_menu")
public class MenuEntity implements Serializable {
    /** 主键  权限菜单id **/
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 上级权限菜单id 0是顶级id **/
    @TableField("pid")
    private Long pid;
    /** 权限名称 **/
    @TableField("name")
    private String name;
    /** 资源类型  0目录  1菜单  2按钮 **/
    @TableField("type")
    private Integer type;
    /** 请求方法 **/
    @TableField("method")
    private String method;
    /** 请求路径 **/
    @TableField("path")
    private String path;
    /** ant风格匹配 **/
    @TableField("pattern")
    private String pattern;
    /** 排序权重值  数值越大越靠后 **/
    @TableField("sort")
    private Integer sort;
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
