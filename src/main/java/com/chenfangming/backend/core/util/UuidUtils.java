package com.chenfangming.backend.core.util;

import java.util.UUID;

/**
 * UUID工具类
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-05 16:53
 */
public abstract class UuidUtils {
  /** 工具类隐藏public构造器 **/
  public UuidUtils() {}

  /**
   * 获取UUID.
   * @return String
   */
  public static String uuid() {
    return UUID.randomUUID().toString().replace(Constants.StringConstant.MIDDLE_LINE_ONE, Constants.StringConstant.EMPTY).toUpperCase();
  }
}
