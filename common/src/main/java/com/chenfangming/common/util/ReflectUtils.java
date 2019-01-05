package com.chenfangming.common.util;

import java.lang.reflect.Field;

/**
 * 反射工具类.
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-04 23:11
 */
public abstract class ReflectUtils {

  /**
   * 获得一个类中所有字段列表.
   * @param clazz 类
   * @param withSuperClassFields 是否包括父类的字段列表
   * @return 字段列表
   * @throws SecurityException 安全检查异常
   */
  public static Field[] getFieldsDirectly(Class<?> clazz, boolean withSuperClassFields) throws NullPointerException {
    if (null == clazz) {
      throw new NullPointerException("clazz不能为空");
    }
    Field[] response = null;
    Class<?> searchType = clazz;
    Field[] declaredFields;
    while (searchType != null) {
      declaredFields = searchType.getDeclaredFields();
      if (null == response) {
        response = declaredFields;
      } else {
        // response = ArrayUtil.append(response, declaredFields);
      }
      searchType = withSuperClassFields ? searchType.getSuperclass() : null;
    }
    return response;
  }
}
