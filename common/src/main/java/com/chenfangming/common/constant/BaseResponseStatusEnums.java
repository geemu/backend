package com.chenfangming.common.constant;

import com.chenfangming.common.model.BaseResponseStatus;
import lombok.Getter;

/**
 * 返回状态常量枚举
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-03 15:25
 */
public final class BaseResponseStatusEnums {

    /** 用户相关异常 **/
    public enum UserEnum implements BaseResponseStatus {
        /** 未查询到用户 **/
        USER_NOT_FOUND_ERROR("USR0001", "用户名不存在，请重新输入"),
        /** 密码错误 **/
        PASSWORD_NOT_MATCH_ERROR("USR0002", "密码错误，请重新输入");
        /** 状态码 **/
        @Getter
        private String code;
        /** 提示信息 **/
        @Getter
        private String message;

        UserEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    /** 系统相关异常 **/
    public enum SystemEnum implements BaseResponseStatus {
        /** 系统异常 **/
        INTERVAL_SERVER_ERROR("SYS0001", "系统未知异常，请联系开发小哥");
        /** 状态码 **/
        @Getter
        private String code;
        /** 提示信息 **/
        @Getter
        private String message;

        SystemEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    /** 验证码相关异常 **/
    public enum VerificationCodeEnum implements BaseResponseStatus {
        /** 缓存验证码异常 **/
        CACHE_ERROR("IMG0001", "验证码获取异常，请刷新重试"),
        /** 验证码已过期 **/
        EXPIRED_ERROR("IMG0002", "验证码已过期，请重新输入"),
        /** 验证码错误 **/
        NOT_MATCH_ERROR("IMG0003", "验证码错误，请重新输入"),
        /** Redis中未获取到验证码 **/
        NOT_FOUND_ERROR("IMG0004", "请先获取验证码");
        /** 状态码 **/
        @Getter
        private String code;
        /** 提示信息 **/
        @Getter
        private String message;

        VerificationCodeEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
