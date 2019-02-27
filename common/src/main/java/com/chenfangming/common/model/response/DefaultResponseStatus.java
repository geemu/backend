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
    /** 成功  通用成功 **/
    SUCCESS(0, "成功"),
    /** 系统异常  后台未知异常，程序BUG **/
    INTERVAL_SERVER_EXCEPTION(1, "系统异常"),
    /** 认证异常  登录失败 **/
    AUTHENTICATION_EXCEPTION(2, "认证异常"),
    /** 授权异常  权限不足 **/
    AUTHORIZATION_EXCEPTION(3, "授权异常"),
    /** 请求异常  请求参数不符合要求、请求路径错误 **/
    REQUEST_PARAM_EXCEPTION(4, "请求参数校验不通过"),
    /** 业务异常  请求经过后台其它逻辑校验后不符合下一步操作 **/
    BUSINESS_EXCEPTION(5, "失败");
    /** 状态码. **/
    private int code;
    /** 提示信息. **/
    private String message;
}
