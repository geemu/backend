package com.chenfangming.backend.manage.domain.request;

import lombok.Data;

/**
 * 登录请求实体.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-12-23 14:17
 */
@Data
public class LoginRequest {
  private String name;
  private String password;
}
