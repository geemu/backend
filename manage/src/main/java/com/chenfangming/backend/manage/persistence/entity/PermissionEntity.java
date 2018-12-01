package com.chenfangming.backend.manage.persistence.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 权限数据表实体
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:36
 */
@Data
public class PermissionEntity implements Serializable {
    /** 主键  id **/
    private Long id;
    /** 上级权限id 0是顶级id **/
    private Long pid;
    /** 权限名称 **/
    private String name;
    /** 备注 **/
    private String note;
    /** 资源类型  0目录  1菜单  2按钮 **/
    private Byte type;
    /** 请求路径 **/
    private String path;
    /** 请求方法 **/
    private String method;
    /** 样式 **/
    private String icon;
    /** 是否展开 0闭合  1展开 **/
    private Boolean isOpen;
    /** 排序权重值  数值越大越靠后 **/
    private Long sort;
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
