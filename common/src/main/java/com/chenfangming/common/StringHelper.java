package com.chenfangming.common;

/**
 * String工具类
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:25
 */
public class StringHelper {

    /**
     * 字符串是否为空，空的定义如下:1、为null  2、为""
     * @param cs 被检测的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(CharSequence cs) {
        return null == cs || 0 == cs.length();
    }
}
