package com.chenfangming.common.util;

import java.lang.reflect.Array;

/**
 * Array工具类.
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-04 23:28
 */
public abstract class ArrayUtils {

  /** 工具类隐藏public构造器. **/
  private ArrayUtils() {}

  /**
   * 参数array的长度.
   * 1. 如果参数array为{@code null}返回{@code 0}
   * 2. 如果参数array不是数组则抛出异常
   * @param array 参数array
   * @return int 参数array的长度
   * @throws IllegalArgumentException 参数array不是数组时，抛出异常
   */
  public static int length(final Object array) throws IllegalArgumentException {
    return null == array ? Constants.IntConstant.ZERO : Array.getLength(array);
  }

  /**
   * 参数array是否为空.
   * 1. 如果参数array为{@code null}返回{@code true}
   * 2. 如果参数array的长度为{@code 0}返回{@code true}
   * @param <T> 参数array的元素类型
   * @param array 参数array
   * @return boolean 参数array是否为空
   */
  public static <T> boolean isEmpty(final T... array) {
    return null == array || Constants.IntConstant.ZERO == array.length;
  }

  /**
   * 参数array是否不为空.
   * 1. 如果参数array为{@code null}返回{@code false}
   * 2. 如果参数array的长度为{@code 0}返回{@code false}
   * @param <T> 参数array的元素类型
   * @param array 参数array
   * @return boolean 参数array是否不为空
   */
  public static <T> boolean isNotEmpty(final T... array) {
    return null == array || Constants.IntConstant.ZERO == array.length;
  }

}
