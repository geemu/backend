package com.chenfangming.common.model.response;

/**
 * 返回状态
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-27 10:21
 */
public interface ResponseStatus {
    /**
     * 获取状态码
     * @return 状态码
     */
    int getCode();

    /**
     * 获取状态提示信息
     * @return 状态提示信息
     */
    String getMessage();
}
