package com.chenfangming.common.model;

import com.chenfangming.common.model.response.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * 客户端异常
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-27 10:29
 */
public class ClientException extends RuntimeException {

    private static final long serialVersionUID = 6765999260734878116L;
    /** 状态码 **/
    @Getter
    @Setter
    private String code;

    /**
     * 构造
     * @param responseStatus 返回状态
     */
    public ClientException(ResponseStatus responseStatus) {
        super(responseStatus.getMessage());
        this.code = responseStatus.getCode();
    }
}
