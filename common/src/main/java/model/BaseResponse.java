package model;

import lombok.Data;

/**
 * 基础封装返回体
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-27 10:18
 */
@Data
public class BaseResponse<T> {
    /** 返回状态码  不能为0 **/
    private Integer code;
    /** 返回提示信息 **/
    private String message;
    /** 返回消息体 **/
    private T data;
}
