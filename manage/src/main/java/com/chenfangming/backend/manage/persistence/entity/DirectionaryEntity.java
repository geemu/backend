package com.chenfangming.backend.manage.persistence.entity;

import java.util.Date;
import lombok.Data;

/**
 * 字典数据表.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-29 18:27
 */
@Data
public class DirectionaryEntity {
  /** 主键  字典id. **/
  private Long id;
  /** 字典类型. **/
  private String type;
  /** key. **/
  private String key;
  /** value. **/
  private String value;
  /** 是否可用  0不可用  1可用. **/
  private Boolean isEnable;
  /** 备注. **/
  private String note;
  /** 创建人. **/
  private String createUser;
  /** 创建时间. **/
  private Date createTime;
  /** 更新人. **/
  private String updateUser;
  /** 更新时间. **/
  private Date updateTime;
}
