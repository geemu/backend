package com.chenfangming.common.constant;

import lombok.Data;

/**
 * 常量
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-11-03 16:48
 */
@Data
public class Constants {

    public class RedisConstant {
        /** 图片验证码前缀 **/
        public static final String VERIFICATION_CODE_PREFIX = "verificationCode:";
    }
}
