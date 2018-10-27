package com.chenfangming.backend.manage.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.BaseEntity;

/**
 * 用户数据表实体
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {
    /** 序列化id **/
    private static final long serialVersionUID = 3811879965122773242L;
    private Long id;
    private String name;
}
