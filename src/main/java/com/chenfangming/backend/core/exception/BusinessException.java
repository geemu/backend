package com.chenfangming.backend.core.exception;

import com.chenfangming.backend.core.http.ResponseStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 业务异常
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-27 10:33
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    public BusinessException(ResponseStatus responseStatus, String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(ResponseStatus responseStatus, Throwable cause) {
        this(responseStatus, responseStatus.getMessage(), cause);
    }

    public BusinessException(ResponseStatus responseStatus, String message) {
        this(responseStatus, message, null);
    }

    public BusinessException(ResponseStatus responseStatus) {
        this(responseStatus, null, null);
    }
}
