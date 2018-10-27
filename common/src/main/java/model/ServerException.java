package model;

/**
 * 服务端异常
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-27 10:32
 */
public class ServerException extends RuntimeException {
    /** 状态码 **/
    private Integer code;

    /**
     * 构造
     * @param code    状态码
     * @param message 异常提示信息
     */
    public ServerException(Integer code, String message) {
        super(message);
        this.code = code;
    }


    /**
     * 构造
     * @param code    状态码
     * @param message 异常提示信息
     * @param cause   异常堆栈
     */
    public ServerException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
