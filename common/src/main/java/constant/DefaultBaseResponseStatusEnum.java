package constant;

import model.BaseResponseStatus;

/**
 * 返回状态常量枚举
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-27 10:24
 */
public enum DefaultBaseResponseStatusEnum implements BaseResponseStatus {
    /** 请求成功 **/
    SUCCESS("000000", "请求成功"),
    CREATED("000001", "创建成功"),
    INTERVAL_SERVER_ERROR("000002", "服务器未知异常"),
    USER_NAME_NOT_FOUND_ERROR("000003", "用户名不存在"),
    PASSWORD_NOT_VALID_ERROR("000004", "密码错误"),
    USER_OR_PASSWORD_ERROR("000005", "用户名或密码错误");

    /** 状态码 **/
    private String code;
    /** 提示信息 **/
    private String message;


    /**
     * 构造
     * @param code    状态码
     * @param message 提示信息
     */
    DefaultBaseResponseStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取状态码
     * @return 状态码
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * 获取状态提示信息
     * @return 状态提示信息
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}
