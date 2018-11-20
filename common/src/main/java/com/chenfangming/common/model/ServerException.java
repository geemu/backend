package com.chenfangming.common.model;

import com.chenfangming.common.model.response.ResponseStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 服务端异常
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-27 10:32
 */
@Getter
@Setter
@ToString
public class ServerException extends RuntimeException {
    private static final long serialVersionUID = -5130212054487695229L;
    /** 状态码 **/
    private String code;

    /**
     * 构造
     * @param responseStatus 返回状态
     */
    public ServerException(ResponseStatus responseStatus) {
        super(responseStatus.getMessage());
        this.code = responseStatus.getCode();
    }
}
