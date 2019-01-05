package com.chenfangming.common.util;

import java.util.function.Supplier;


/**
 * 断言工具类，用于校验参数
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:25
 */
public abstract class Assert {

  /**
   * boolean 断言.
   * 如果表达式的计算结果为{@code false}，那么抛出{@link IllegalStateException}异常
   * @param expression boolean表达式
   * @param message 断言失败提示信息
   */
  public static void isTrue(boolean expression, String message) throws IllegalArgumentException {
    if (!expression) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * boolean表达式断言
   * 1. 表达式计算结果为{@code false}抛出
   * @param expression boolean表达式{@link IllegalStateException}异常
   * @param messageSupplier 断言失败的错误信息提供源
   */
  public static void isTrue(boolean expression, Supplier<String> messageSupplier) throws IllegalStateException {
    if (!expression) {
      throw new IllegalArgumentException(nullSafeGet(messageSupplier));
    }
  }

  /**
   * 获取提示信息
   * 1. messageSupplier为{@code null}返回{@code null}
   * 2. messageSupplier不为{@code null}返回输出的提示信息
   * @param messageSupplier 错误信息提供源 可能为{@code null}
   * @return 提示信息
   */
  private static String nullSafeGet(Supplier<String> messageSupplier) {
    return (null != messageSupplier ? messageSupplier.get() : null);
  }
}
