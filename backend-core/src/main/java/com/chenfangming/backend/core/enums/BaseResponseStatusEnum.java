package com.chenfangming.backend.core.enums;
import com.chenfangming.common.exception.IBaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * x
 * @author fangming.chen
 * @since 2018-08-28 15:37:
 * Email cfmmail@sina.com
 */
@Getter
@AllArgsConstructor
public enum BaseResponseStatusEnum implements IBaseResponseStatus {
    /** 未找到对应的用户名 **/
    USER_NOT_FOUND_EXCEPTION(-1, "用户名不存在"),
    /** 对应的用户名未启用 **/
    USER_LOCKED_EXCEPTION(-2, "账户被锁定，请联系管理员解锁账号");

    /** 返回状态码 **/
    private Integer code;
    /** 返回状态码对应的提示信息 **/
    private String message;

}
