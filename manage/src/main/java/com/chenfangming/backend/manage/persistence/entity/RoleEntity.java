package com.chenfangming.backend.manage.persistence.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色数据表实体
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:35
 */
@Data
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 2838062966868625359L;
    /** 主键  id **/
    private Long id;
    /** 角色名称  忽略大小写 **/
    private String name;
    /** 是否可用  0不可用  1可用 **/
    private Boolean isEnable;
    /** 是否未删除  0已删除  1未删除 **/
    private Boolean isNoDelete;
    /** 创建人 **/
    private String createUser;
    /** 创建时间 **/
    private Date createTime;
    /** 更新人 **/
    private String updateUser;
    /** 更新时间 **/
    private Date updateTime;
    /** 乐观锁 **/
    @Version
    private Long version;
}
