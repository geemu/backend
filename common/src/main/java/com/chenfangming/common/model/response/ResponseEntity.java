package com.chenfangming.common.model.response;

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
    /** 返回状态 **/
    private int code;
    /** 返回提示信息 **/
    private String message;
    /** 返回数据 **/
    private T data;

    private ResponseEntity(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseEntity() {
        this(DefaultResponseStatus.SUCCESS, null);
    }

    public ResponseEntity(ResponseStatus responseStatus) {
        this(responseStatus, null);
    }

    public ResponseEntity(T data) {
        this(DefaultResponseStatus.SUCCESS, data);
    }

    public ResponseEntity(ResponseStatus responseStatus, T data) {
        this(responseStatus.getCode(), responseStatus.getMessage(), data);
    }


}
