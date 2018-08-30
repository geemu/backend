package com.chenfangming.backend.core.persistence.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台管理用户数据表，不区分大小写
 * @author fangming.chen
 * @since 2018-08-28 13:17:
 * Email cfmmail@sina.com
 */
@Data
public class User implements Serializable {
    /** 主键 **/
    private Integer id;
    /** 用户名 **/
    private String name;
    /** 密码 **/
    private String password;
    /** 密码盐值 **/
    private String salt;
    /** 备注 **/
    private String remark;
    /** 状态 0 false 禁用，1 true 启用 **/
    private Boolean state;
    /** 创建人 **/
    private String createUser;
    /** 创建时间 **/
    private Date createTime;
    /** 更新人 **/
    private String updateUser;
    /** 更新时间 **/
    private Date updateTime;
    /** 是否未删除 0删除 1未删除 **/
    private Boolean isNonDelete;
}
