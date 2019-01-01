package com.chenfangming.common.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 返回状态常量枚举.
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-03 15:25
 */
@Getter
@ToString
@AllArgsConstructor
public enum DefaultResponseStatus implements ResponseStatus {
  /** 成功. **/
  SUCCESS("SYS0000", "成功"),
  /** 系统异常. **/
  INTERVAL_SERVER_ERROR("SYS0001", "系统异常"),
  /** 未认证. **/
  NO_AUTHENTICATION_FAIL("SYS0002", "未认证"),
  /** 未授权. **/
  ACCESS_DENIED_FAIL("SYS0002", "未授权"),
  /** 认证失败. **/
  AUTHENTICATION_FAIL("USR0003", "认证失败"),
  ;
  /** 状态码. **/
  private String code;
  /** 提示信息. **/
  private String message;
}
