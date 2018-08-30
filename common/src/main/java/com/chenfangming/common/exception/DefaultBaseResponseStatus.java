package com.chenfangming.common.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 默认返回状态
 * @author fangming.chen
 * @since 2018-08-28 15:37:
 * Email cfmmail@sina.com
 */
@Getter
@AllArgsConstructor
public enum DefaultBaseResponseStatus implements IBaseResponseStatus {
    /** 成功 **/
    SUCCESS(200, "SUCCESS"),
    /** 未知异常，程序BUG导致的 **/
    INTERNAL_SERVER_ERROR(500, "服务器未知异常，请联系开发小哥");

    /** 返回状态码 **/
    private Integer code;
    /** 返回状态码对应的提示信息 **/
    private String message;

}
