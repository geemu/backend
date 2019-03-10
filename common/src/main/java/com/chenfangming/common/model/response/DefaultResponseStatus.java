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
    /** 成功  通用成功 **/
    SUCCESS(0, "成功"),
    EXCEPTION(1, "失败"),
    INTERVAL_SERVER_EXCEPTION(1, "系统异常"),
    AUTHENTICATION_EXCEPTION(2, "认证失败"),
    ACCOUNT_FORBIDDEN_EXCEPTION(3, "账号被禁用"),
    AUTHORIZATION_EXCEPTION(4, "权限不足"),
    /** 请求参数不符合规则  比如长度、字母、数字、正则等 **/
    REQUEST_PARAM_EXCEPTION(5, "请求参数校验不通过"),
    /** 多半用于新增数据时，数据已经存在 **/
    DATA_EXIST_EXCEPTION(6, "数据已存在"),
    /** 多半用于修改数据时，数据已经不存在了 **/
    DATA_NOT_EXIST_EXCEPTION(7, "数据不存在");
    /** 状态码 **/
    private int code;
    /** 提示信息 **/
    private String message;
}
