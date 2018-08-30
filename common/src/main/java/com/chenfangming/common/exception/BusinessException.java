package com.chenfangming.common.exception;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常，记录警告日志，不是程序BUG导致的，比如参数校验不通过等
 * @author fangming.chen
 * @since 2018-08-28 13:19:
 * Email cfmmail@sina.com
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {
    /** 返回状态码 **/
    private Integer code;

    /**
     * 异常构造
     * @param responseStatus 返回状态码及其对应的提示信息
     */
    public BusinessException(IBaseResponseStatus responseStatus) {
        super(responseStatus.getMessage());
        this.code = responseStatus.getCode();
    }
}
