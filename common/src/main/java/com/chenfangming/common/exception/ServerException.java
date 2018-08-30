package com.chenfangming.common.exception;
import lombok.Getter;
import lombok.Setter;

/**
 * 程序异常，记录错误日志，为程序BUG导致的异常，比如数据库操作异常显式抛出异常回滚等
 * @author fangming.chen
 * @since 2018-08-28 13:19:
 * Email cfmmail@sina.com
 */
@Getter
@Setter
public class ServerException extends RuntimeException {
    /** 返回状态码 **/
    private Integer code;

    /**
     * 异常构造
     * @param responseStatus 返回状态码及其对应的提示信息
     */
    public ServerException(IBaseResponseStatus responseStatus) {
        super(responseStatus.getMessage());
        this.code = responseStatus.getCode();
    }
}
