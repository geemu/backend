package com.chenfangming.backend.manage;

import validatorAnnotation.IntegerValidate;
import validatorAnnotation.LongValidate;
import validatorAnnotation.StringValidate;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Date: 2017/7/27
 * Description:
 * History:
 */
public class Validator {

  /**
   * 验证注解
   */
  public static <T> void validate(T entity, Class<? extends Object> type) {
    Field[] fields = type.getDeclaredFields();
    if (fields == null || fields.length == 0) {
      return;
    }
    for (int i = 0; i < fields.length; i++) {
      Field field = fields[i];
      fields[i].setAccessible(true);
      //获取实例字段的值  参数的值
      Object fieldValue = null;
      try {
        fieldValue = field.get(entity);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
        throw new RuntimeException("获取字段值时出错");
      }
      IntegerValidate(field, fieldValue);
      LongValidate(field, fieldValue);
      stringValidate(field, fieldValue);

      //字段验证成功后对String进行前后空格的截取
      if (field.getType().getName().equals("java.lang.String")) {
        String key = field.getName();
        if (fieldValue == null) continue;
        setFieldValue(entity, key, fieldValue.toString().trim());
      }
      field.setAccessible(false);
    }
    //递归向上    验证父类
    validate(entity, type.getSuperclass());

  }

  /**
   * 获取字段的值
   */
  private static Object getFieldValue(Object bean, String fieldName) {
    StringBuffer result = new StringBuffer();
    String methodName = result.append("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
    Object rObject = null;
    Method method = null;
    @SuppressWarnings("rawtypes") Class[] classArr = new Class[0];
    try {
      method = bean.getClass().getMethod(methodName, classArr);
      rObject = method.invoke(bean);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rObject;
  }

  /**
   * 设置字段的值
   */
  private static void setFieldValue(Object bean, String fieldName, Object fieldValue) {
    StringBuffer result = new StringBuffer();
    String methodName = result.append("set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
    Class<?> clazz = "".getClass();
    try {
      Method method = bean.getClass().getMethod(methodName, clazz);
      method.invoke(bean, fieldValue);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 验证IntegerValidate这个注解
   * @param field 字段
   * @param fieldValue 字段的值
   */
  private static void IntegerValidate(Field field, Object fieldValue) {
    if (field.isAnnotationPresent(IntegerValidate.class)) {
      //获取注解以及成员属性值
      IntegerValidate integerValidate = field.getAnnotation(IntegerValidate.class);
      String key = field.getName();
      boolean required = integerValidate.required();
      String fieldName = integerValidate.fieldName();
      if (!(field.getType().getName().equals("java.lang.Integer"))) {
        throw new RuntimeException(String.format("参数【%s】处禁止使用@IntegerValidate", key + fieldName));
      }
      int minValue = integerValidate.minValue();
      int maxValue = integerValidate.maxValue();
      //精确报错
      if (required == true || (required == false && fieldValue != null)) {
        if (fieldValue != null) {
          if (Integer.parseInt(fieldValue.toString()) < minValue) {
            throw new RuntimeException(String.format("参数【%s】应大于等于", key + fieldName, minValue));
          }
          if (Integer.parseInt(fieldValue.toString()) > maxValue) {
            throw new RuntimeException(String.format("参数【%s】应小于等于", key + fieldName, maxValue));
          }
        } else {
          throw new RuntimeException(String.format("参数【%s】必传"));
        }
      }
    }
  }

  /**
   * 验证LongValidate这个注解
   * @param field 字段
   * @param fieldValue 字段的值
   */
  private static void LongValidate(Field field, Object fieldValue) {
    if (field.isAnnotationPresent(LongValidate.class)) {
      System.out.println("开始验证LongValidate这个注解，当前验证字段为：" + field.getName());
      //获取注解以及成员属性值
      LongValidate longValidate = field.getAnnotation(LongValidate.class);
      String key = field.getName();
      boolean required = longValidate.required();
      String fieldName = longValidate.fieldName();
      if (!(field.getType().getName().equals("java.lang.Long"))) {
        throw new RuntimeException(String.format("参数【%s】处禁止使用@LongValidate", key + fieldName));
      }
      long minValue = longValidate.minValue();
      long maxValue = longValidate.maxValue();
      //精确报错
      if (required == true || (required == false && fieldValue != null)) {
        if (fieldValue != null) {
          if (Long.parseLong(fieldValue.toString()) < minValue) {
            throw new RuntimeException(String.format("参数【%s】应大于等于", key + fieldName, minValue));
          }
          if (Long.parseLong(fieldValue.toString()) > maxValue) {
            throw new RuntimeException(String.format("参数【%s】应小于等于", key + fieldName, maxValue));
          }
        } else {
          throw new RuntimeException(String.format("参数【%s】必传"));
        }
      }
    }
  }

  /**
   * 验证StringValidate这个注解
   * @param field 字段
   * @param fieldValue 字段的值
   */
  private static void stringValidate(Field field, Object fieldValue) {
    if (field.isAnnotationPresent(StringValidate.class)) {
      //获取注解以及成员属性值
      StringValidate stringValidate = field.getAnnotation(StringValidate.class);
      String key = field.getName();
      boolean required = stringValidate.required();
      String fieldName = stringValidate.fieldName();
      if (!(field.getType().getName().equals("java.lang.String"))) {
        throw new RuntimeException(String.format("参数【%s】处禁止使用@StringValidate", key + fieldName));
      }
      int minLength = stringValidate.minLength();//注解的值
      int maxLength = stringValidate.maxLength();//注解的值
      //精确报错
      if (required == true || (required == false && fieldValue != null)) {
        if (fieldValue != null) {
          if (minLength != -1) {
            if (((String) (fieldValue)).trim().length() < minLength) {
              throw new RuntimeException(String.format("参数【%s】长度应大于等于%d", key + fieldName, minLength));
            }
          }
          if (maxLength != -1) {
            if (((String) (fieldValue)).trim().length() > maxLength) {
              throw new RuntimeException(String.format("参数【%s】长度应小于等于%d", key + fieldName, maxLength));
            }
          }
        } else {
          throw new RuntimeException(String.format("参数【%s】必传", key + fieldName));
        }
      }
    }
  }
}
