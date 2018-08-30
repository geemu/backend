package com.chenfangming.common.exception;
/**
 * 返回状态
 * @author fangming.chen
 * @since 2018-08-28 13:24:
 * Email cfmmail@sina.com
 */
public interface IBaseResponseStatus {
    /**
     * 获取返回状态码
     * @return 返回状态码
     */
    Integer getCode();

    /**
     * 获取返回状态码对应的提示信息
     * @return 返回状态码对应的提示信息
     */
    String getMessage();
}
