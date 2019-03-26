package com.chenfangming.backend.core.util;

/**
 * 常量工具类.
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-05 11:57
 */
public abstract class Constants {
  /** 工具类隐藏public构造器. **/
  private Constants() {}

  class IntConstant {
    /** int类型的数值常量{@code 0}. **/
    static final int ZERO = 0;
  }

  class StringConstant {
    /** 空字符串{@code ""}. **/
    static final String EMPTY = "";
    /** 中横线- **/
    static final String MIDDLE_LINE_ONE = "-";
  }

}
