package com.chenfangming.common.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 服务端异常
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-27 10:32
 */
public class ServerException extends RuntimeException {
    private static final long serialVersionUID = -5130212054487695229L;
    /** 状态码 **/
    @Getter
    @Setter
    private String code;

    /**
     * 构造
     * @param responseStatus 返回状态
     */
    public ServerException(BaseResponseStatus responseStatus) {
        super(responseStatus.getMessage());
        this.code = responseStatus.getCode();
    }
}
