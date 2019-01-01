package com.chenfangming.backend.manage.domain.response;

import lombok.Data;

/**
 * com.chenfangming.backend.manage.domain.response
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-01 13:37
 */
@Data
public class FindByNameResponse {
  /** 主键  用户id. **/
  private Long id;
  /** 用户名  忽略大小写. **/
  private String name;
  /** 密码  区分大小写. **/
  private String password;
  /** 密码盐值  区分大小写. **/
  private String salt;
  /** 是否可用  0不可用  1可用. **/
  private Boolean isEnable;
}
