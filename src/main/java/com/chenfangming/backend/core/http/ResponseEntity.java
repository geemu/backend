package com.chenfangming.backend.core.http;

import com.chenfangming.backend.core.exception.BusinessException;
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


    /**
     * 没有数据的自定义状态返回
     * @param responseStatus code、message
     */
    public ResponseEntity(ResponseStatus responseStatus) {
        this(responseStatus.getCode(), responseStatus.getMessage(), null);
    }

    public ResponseEntity(BusinessException e) {
        this(e.getCode(), e.getMessage(), null);
    }

    /**
     * 成功且有数据的返回
     * @param data 数据
     */
    public ResponseEntity(T data) {
        this(DefaultResponseStatus.SUCCESS.getCode(), DefaultResponseStatus.SUCCESS.getMessage(), data);
    }

    /**
     * 成功且但无数据的返回
     */
    public ResponseEntity() {
        this(DefaultResponseStatus.SUCCESS.getCode(), DefaultResponseStatus.SUCCESS.getMessage(), null);
    }

    /**
     * 自定义状态和数据的返回
     * @param responseStatus code、message
     * @param data 数据
     */
    public ResponseEntity(ResponseStatus responseStatus, T data) {
        this(responseStatus.getCode(), responseStatus.getMessage(), data);
    }

    /**
     * 自定义的message代替responseStatus中的message且有自己的返回数据
     * @param responseStatus responseStatus
     * @param message message
     * @param data data
     */
    public ResponseEntity(ResponseStatus responseStatus, String message, T data) {
        this(responseStatus.getCode(), message, data);
    }

    /**
     * 自定义的message代替responseStatus中的message无返回数据
     * @param responseStatus responseStatus
     * @param message message
     */
    public ResponseEntity(ResponseStatus responseStatus, String message) {
        this(responseStatus.getCode(), message, null);
    }
}
