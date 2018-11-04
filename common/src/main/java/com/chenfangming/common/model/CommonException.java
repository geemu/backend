package com.chenfangming.common.model;

/**
 * 通用异常
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-27 10:33
 */
public class CommonException extends RuntimeException {
    private static final long serialVersionUID = 7930445374362829533L;

    /**
     * 构造
     * @param message 异常提示信息
     */
    public CommonException(String message) {
        super(message);
    }

    /**
     * 构造
     * @param message 异常提示信息
     * @param cause   异常堆栈
     */
    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }
}
