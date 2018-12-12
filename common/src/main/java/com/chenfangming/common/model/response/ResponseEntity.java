package com.chenfangming.common.model.response;

import com.chenfangming.common.model.BusinessException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 基础封装返回体
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-27 10:18
 */
@Getter
@ToString
@EqualsAndHashCode
public class ResponseEntity<T> {
    /** 返回状态码 **/
    private String code;
    /** 返回提示信息 **/
    private String message;
    /** 返回数据 **/
    private T data;

    public ResponseEntity(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseEntity(ResponseStatus status, T data) {
        this(status.getCode(), status.getMessage(), data);
    }

    public ResponseEntity(ResponseStatus status) {
        this(status.getCode(), status.getMessage(), null);
    }

    public ResponseEntity(T data) {
        this(DefaultResponseStatus.SUCCESS, data);
    }

    public ResponseEntity() {
        this(DefaultResponseStatus.SUCCESS);
    }


    public ResponseEntity(ResponseStatus status, String message, T data) {
        this(status.getCode(), message, data);
    }

    public ResponseEntity(ResponseStatus status, String message) {
        this(status.getCode(), message, null);
    }

    public ResponseEntity(BusinessException e) {
        this(e.getCode(), e.getMessage(), null);
    }

}
