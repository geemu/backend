package com.chenfangming.common.model;
import com.chenfangming.common.exception.DefaultBaseResponseStatus;
import com.chenfangming.common.exception.IBaseResponseStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 接口返回包装体
 * @author fangming.chen
 * @since 2018-08-28 15:52:
 * Email cfmmail@sina.com
 */
@Getter
@Setter
public class BaseResponse<T> implements Serializable {
    /** 返回状态码 **/
    private Integer code;
    /** 返回状态码对应的提示信息 **/
    private String message;
    /** 返回的数据 **/
    private T data;

    /**
     * 无参数构造
     */
    public BaseResponse() {
    }

    /**
     * 全部参数构造
     * @param responseStatus 返回状态码及其对应的提示信息
     * @param data 返回的数据
     */
    public BaseResponse(IBaseResponseStatus responseStatus, T data) {
        this.code = responseStatus.getCode();
        this.message = responseStatus.getMessage();
        this.data = data;
    }

    /**
     * 自定义成功数据
     * @return 返回自定义数据
     */
    public BaseResponse<T> success(T data) {
        this.code = DefaultBaseResponseStatus.SUCCESS.getCode();
        this.message = DefaultBaseResponseStatus.SUCCESS.getMessage();
        this.data = data;
        return this;
    }
}
