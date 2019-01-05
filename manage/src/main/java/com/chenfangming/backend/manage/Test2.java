package com.chenfangming.backend.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 * com.chenfangming.backend.manage.
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-04 22:39
 */
public class Test2 {
  /**
   * Dom2Map.
   * @param doc doc
   * @return Map
   */
  public static Map<String, Object> Dom2Map(Document doc) {
    Map<String, Object> map = new HashMap<>(16);
    if (null == doc) {
      return map;
    }
    Element root = doc.getRootElement();
    for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
      Element e = (Element) iterator.next();
      List list = e.elements();
      if (list.size() > 0) {
        map.put(e.getName(), Dom2Map(e));
      } else {
        map.put(e.getName(), e.getText());
      }
    }
    return map;
  }

  /**
   * Dom2Map.
   * @param e e
   * @return Map
   */
  private static Map<String, Object> Dom2Map(Element e) {
    Map<String, Object> map = new HashMap<>(16);
    List list = e.elements();
    if (list.size() > 0) {
      for (int i = 0; i < list.size(); i++) {
        Element iter = (Element) list.get(i);
        List mapList = new ArrayList();
        if (iter.elements().size() > 0) {
          Map m = Dom2Map(iter);
          if (map.get(iter.getName()) != null) {
            Object obj = map.get(iter.getName());
            if (!"java.util.ArrayList".equals(obj.getClass().getName())) {
              mapList = new ArrayList();
              mapList.add(obj);
              mapList.add(m);
            }
            if ("java.util.ArrayList".equals(obj.getClass().getName())) {
              mapList = (List) obj;
              mapList.add(m);
            }
            map.put(iter.getName(), mapList);
          } else {
            map.put(iter.getName(), m);
          }
        } else {
          if (map.get(iter.getName()) != null) {
            Object obj = map.get(iter.getName());
            if (!"java.util.ArrayList".equals(obj.getClass().getName())) {
              mapList = new ArrayList();
              mapList.add(obj);
              mapList.add(iter.getText());
            }
            if ("java.util.ArrayList".equals(obj.getClass().getName())) {
              mapList = (List) obj;
              mapList.add(iter.getText());
            }
            map.put(iter.getName(), mapList);
          } else {
            map.put(iter.getName(), iter.getText());
          }
        }
      }
    } else {
      map.put(e.getName(), e.getText());
    }
    return map;
  }
}
