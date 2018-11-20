package com.chenfangming.common.model.response;

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

}
