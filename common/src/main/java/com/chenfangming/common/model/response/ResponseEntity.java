package com.chenfangming.common.model.response;

import com.chenfangming.common.model.response.DefaultResponseStatus.SystemEnum;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
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

    /**
     * 全参构造
     * @param status 返回状态
     * @param data   返回数据
     */
    public ResponseEntity(@NotNull ResponseStatus status, @Nullable T data) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = data;
    }

    /**
     * 没有返回数据的构造
     * @param status 返回状态
     */
    public ResponseEntity(@NotNull ResponseStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    /**
     * 成功返回体
     * @param data 返回数据
     */
    public ResponseEntity(@Nullable T data) {
        this.code = SystemEnum.SUCCESS.getCode();
        this.message = SystemEnum.SUCCESS.getMessage();
        this.data = data;
    }

    /**
     * 成功返回
     */
    public ResponseEntity() {
        this.code = SystemEnum.SUCCESS.getCode();
        this.message = SystemEnum.SUCCESS.getMessage();
    }

}
