package com.chenfangming.backend.manage.persistence.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户数据表
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:34
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {
    /** 主键  用户id **/
    private Long id;
    /** 用户名  忽略大小写 **/
    private String name;
    /** 密码  区分大小写 **/
    private String password;
    /** 是否可用  0不可用  1可用 **/
    private Boolean enabled;
    /** 创建人 **/
    private String createUser;
    /** 创建时间 **/
    private Date createTime;
    /** 更新人 **/
    private String updateUser;
    /** 更新时间 **/
    private Date updateTime;
}
