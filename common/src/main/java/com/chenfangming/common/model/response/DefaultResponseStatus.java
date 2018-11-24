package com.chenfangming.common.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 返回状态常量枚举
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-03 15:25
 */
public final class DefaultResponseStatus {
    /**
     * 系统相关异常
     */
    @Getter
    @ToString
    @AllArgsConstructor
    public enum SystemEnum implements ResponseStatus {
        /** 成功 **/
        SUCCESS("SYS0000", "成功"),
        /** 系统异常 **/
        INTERVAL_SERVER_ERROR("SYS0001", "系统未知异常，请联系开发小哥"),
        /** 权限不足，未授权 **/
        NO_PERMISSION_ERROR("SYS0002", "权限不足");
        /** 状态码 **/
        private String code;
        /** 提示信息 **/
        private String message;
    }

    /** 用户相关异常 **/
    @Getter
    @ToString
    @AllArgsConstructor
    public enum UserEnum implements ResponseStatus {
        /** 用户名或密码错误 **/
        USER_OR_PASSWORD_IN_CORRECT_ERROR("USR0001", "用户名或密码错误"),
        /** 用户被禁用 **/
        USER_NOT_ENABLE_ERROR("USR0002", "账户被禁用");
        /** 状态码 **/
        private String code;
        /** 提示信息 **/
        private String message;
    }

    /** 验证码相关异常 **/
    @Getter
    @ToString
    @AllArgsConstructor
    public enum ImgCodeEnum implements ResponseStatus {
        /** 缓存验证码异常 **/
        IMG_CODE_CACHE_ERROR("IMG0001", "验证码获取异常"),
        /** 验证码已过期 **/
        IMG_CODE_EXPIRED_ERROR("IMG0002", "验证码已过期"),
        /** 验证码错误 **/
        IMG_CODE_IN_CORRECT_ERROR("IMG0003", "验证码错误");
        /** 状态码 **/
        private String code;
        /** 提示信息 **/
        private String message;
    }
}
