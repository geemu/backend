package com.chenfangming.backend.manage;

import com.alibaba.fastjson.JSONObject;
import com.chenfangming.common.util.StringUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dozer.DozerBeanMapper;

/**
 * Test2
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-04 22:39
 */
@Slf4j
public abstract class Test2 {

  /** name **/
  private static final String NAME = "name";
  /** struct **/
  private static final String STRUCT = "struct";

  public static void main(String[] args) {
    InputStream is = Test2.class.getClassLoader().getResourceAsStream("test.xml");
    String xml = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining(System.lineSeparator()));
    SAXReader reader = new SAXReader(false);
    StringReader stringReader = new StringReader(xml);
    Document doc;
    try {
      doc = reader.read(stringReader);
    } catch (DocumentException e) {
      e.printStackTrace();
      return;
    }
    Map<String, Object> map = toMap(doc.getRootElement());
    System.out.println(JSONObject.toJSONString(map));
    DozerBeanMapper mapper = new DozerBeanMapper();
    ResponseEntity<MyResponseBody> response = new ResponseEntity<>();
    mapper.map(map, response);
    System.out.println(JSONObject.toJSONString(response));
  }

  /**
   * dom转Map
   * @param parent 根元素
   * @return Map集合
   */
  @SuppressWarnings("unchecked")
  private static Map<String, Object> toMap(Element parent) {
    Map<String, Object> response = new HashMap(16);
    List<Element> children = parent.elements();
    for (Element child : children) {
      if (child.elements().size() > 0) {
        Map<String, Object> inner = toMap(child);
        String fieldName = getFieldName(child);
        if (null != response.get(fieldName)) {
          Object obj = response.get(fieldName);
          List mapList = new ArrayList();
          mapList.add(obj);
          mapList.add(inner);
          response.put(fieldName, mapList);
        } else {
          response.put(fieldName, inner);
        }
      } else {
        response.put(getFieldName(child), getFieldValue(child));
      }
    }
    return response;
  }

  /**
   * 获取字段所代表的属性
   * @param filed filed
   * @return String
   */
  private static String getFieldName(Element filed) {
    String fieldName = filed.getName();
    String fieldAttrName = filed.attributeValue(NAME);
    //  filedName不为空 <Head1>这是Head中元素Head1的值</Head1> {@code Head1}
    boolean isFieldNameNotEmpty = StringUtils.isNotEmpty(fieldName);
    //  fieldAttrName不为空 <field name="Body1">这是Body中元素Body1的值</field> {@code Body1}
    boolean isFiledAttrNameNotEmpty = StringUtils.isNotEmpty(fieldAttrName);
    if (isFieldNameNotEmpty && isFiledAttrNameNotEmpty) {
      // 特殊处理结构体
      if (STRUCT.equals(fieldName)) {
        return filed.getParent().attributeValue(NAME);
      }
      return fieldAttrName;
    } else {
      return fieldName;
    }
  }

  /**
   * 获取字段所代表值
   * @param filed filed
   * @return String
   */
  private static String getFieldValue(Element filed) {
    String value = filed.getText();
    boolean isEmpty = StringUtils.isEmpty(value);
    return isEmpty ? null : value;
  }
}
