package com.chenfangming.backend.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * com.chenfangming.backend.domain.request
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-03-30 09:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCondition {
    /** 用户名 **/
    private String name;
    /** 密码 **/
    private String password;
}
