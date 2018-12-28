package com.chenfangming.common.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 返回状态常量枚举
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-03 15:25
 */
@Getter
@ToString
@AllArgsConstructor
public enum DefaultResponseStatus implements ResponseStatus {
    /** 成功 **/
    SUCCESS("SYS0000", "成功"),
    /** 系统异常 **/
    INTERVAL_SERVER_ERROR("SYS0001", "系统异常"),
    /** 未认证 **/
    NO_AUTHENTICATION_ERROR("SYS0002", "未认证"),
    /** 未授权 **/
    NO_AUTHORIZATION_ERROR("SYS0002", "未授权"),
    /** 账号或密码错误 **/
    ACCOUNT_OR_PASSWORD_IN_CORRECT_ERROR("USR0001", "账号或密码错误"),
    /** 账号被禁用 **/
    ACCOUNT_DISABLE_ERROR("USR0002", "账号被禁用"),
    /** 其它认证异常 **/
    OTHER_AUTHENTICATION_ERROR("USR0003", "其它认证异常");
    /** 状态码 **/
    private String code;
    /** 提示信息 **/
    private String message;
}
