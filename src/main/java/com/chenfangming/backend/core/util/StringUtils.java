package com.chenfangming.backend.core.util;

/**
 * String工具类
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-25 20:25
 */
public abstract class StringUtils {

  /** 工具类隐藏public构造器 **/
  private StringUtils() {}

  /**
   * 参数cs是否不为空
   * 1. 参数cs为{@code null}返回{@code false}
   * 2. 参数cs为{@code ""}返回{@code false}
   * @param cs 参数cs
   * @return boolean 参数cs是否不为空
   */
  public static boolean isNotEmpty(final CharSequence cs) {
    return !isEmpty(cs);
  }

  /**
   * 参数cs是否为空
   * 1. 参数cs为{@code null}返回{@code true}
   * 2. 参数cs为{@code ""}返回{@code true}
   * @param cs 参数cs
   * @return boolean 参数cs是否为空
   */
  public static boolean isEmpty(final CharSequence cs) {
    return null == cs || Constants.IntConstant.ZERO == cs.length();
  }

  /**
   * 参数cs是否包不含空白
   * 1. 参数cs为{@code null}返回{@code true}
   * 2. 参数cs含{@code null}元素返回{@code false}
   * 3. 参数cs含{@code ""}元素返回{@code false}
   * 4. 参数cs含{@code " "}元素返回{@code false}
   * @param cs 参数cs
   * @return 参数cs是否包不含空白
   */
  public static boolean hasNoBank(final CharSequence... cs) {
    return !hasBank(cs);
  }

  /**
   * 参数cs是否包含空白
   * 1. 参数cs为{@code null}返回{@code false}
   * 2. 参数cs含{@code null}元素返回{@code true}
   * 3. 参数cs含{@code ""}元素返回{@code true}
   * 4. 参数cs含{@code " "}元素返回{@code true}
   * @param cs 参数cs
   * @return 参数cs是否包含空白
   */
  public static boolean hasBank(final CharSequence... cs) {
    if (ArrayUtils.isEmpty(cs)) {
      return false;
    }
    for (final CharSequence item : cs) {
      if (isBlank(item)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 参数cs是否为空白
   * 1. 参数cs为{@code null}返回{@code true}
   * 2. 参数cs为{@code ""}返回{@code true}
   * 2. 参数cs全部为{@code " "}返回{@code true}
   * @param cs 参数cs
   * @return 参数cs是否为空白
   */
  public static boolean isBlank(final CharSequence cs) {
    int csLength;
    if (null == cs || Constants.IntConstant.ZERO == (csLength = cs.length())) {
      return true;
    }
    for (int i = Constants.IntConstant.ZERO; i < csLength; i++) {
      if (!Character.isWhitespace(cs.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * 参数cs是否全为空白
   * 1. 参数cs为{@code null}返回{@code true}
   * 2. 参数cs元素全为{@code null}返回{@code true}
   * 3. 参数cs长度为{@code o}返回{@code true}
   * @param cs 参数cs
   * @return 参数cs是否全为空白
   */
  public static boolean isAllBlank(final CharSequence... cs) {
    if (ArrayUtils.isEmpty(cs)) {
      return true;
    }
    for (final CharSequence item : cs) {
      if (isNotBlank(item)) {
        return false;
      }
    }
    return true;
  }

  /**
   * 参数cs是否不为空白
   * 1. 参数cs为{@code null}返回{@code false}
   * 2. 参数cs为{@code ""}返回{@code false}
   * 3. 参数cs全部为{@code " "}返回{@code false}
   * @param cs 参数cs
   * @return 参数cs是否不为空白
   */
  public static boolean isNotBlank(final CharSequence cs) {
    return !isBlank(cs);
  }

  /**
   * 参数s去除前后空格
   * 1. 参数s为{@code null}返回{@code null}
   * 2. 参数s去除前后空格后为{@code ""}返回{@code null}
   * @param s 参数s
   * @return 参数s去除前后空格
   */
  public static String trimToNull(final String s) {
    final String ds = trim(s);
    return isEmpty(ds) ? null : ds;
  }

  /**
   * 参数s去除前后空格
   * 1. 参数s为{@code null}返回{@code null}
   * @param s 参数s
   * @return 参数s去除前后空格
   */
  public static String trim(final String s) {
    return null != s ? s.trim() : null;
  }

  /**
   * 参数s去除前后空格
   * 1. 参数s为{@code null}返回{@code ""}
   * 2. 参数s去除前后空格后为{@code ""}返回{@code ""}
   * @param s 参数s
   * @return 参数s去除前后空格
   */
  public static String trimToEmpty(final String s) {
    return s == null ? Constants.StringConstant.EMPTY : s.trim();
  }

  /**
   * 参数s截取指定长度
   * @param s 参数s
   * @param length 指定长度
   * @return 参数s截取指定长度
   * @throws IllegalArgumentException length不符合规范抛出异常
   */
  public static String truncate(final String s, final int length) throws IllegalArgumentException {
    return truncate(s, Constants.IntConstant.ZERO, length);
  }

  /**
   * 参数s从指定位置截取指定长度
   * 1. 参数s为{@code null}返回{@code null}
   * 2. 参数s的长度小于需要截取的长度，返回可以被截取的长度
   * @param s 参数s
   * @param beginIndex 开始截取位置索引
   * @param length 指定长度
   * @return 参数s从指定位置截取指定长度
   * @throws IllegalArgumentException 参数beginIndex、length不符合规范抛出异常
   */
  public static String truncate(final String s, final int beginIndex, final int length) throws IllegalArgumentException {
    if (null == s) {
      return null;
    }
    if (0 > beginIndex) {
      throw new IllegalArgumentException("开始位置不能为负数");
    }
    if (0 > length) {
      throw new IllegalArgumentException("结果字符串的长度不能为负数");
    }
    if (beginIndex > s.length()) {
      return Constants.StringConstant.EMPTY;
    }
    if (s.length() > length) {
      // 结束位置索引
      final int endIndex = beginIndex + length > s.length() ? s.length() : beginIndex + length;
      return s.substring(beginIndex, endIndex);
    }
    return s.substring(beginIndex);
  }

}
