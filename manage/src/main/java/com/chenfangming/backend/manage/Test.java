package com.chenfangming.backend.manage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.stream.Collectors;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * com.chenfangming.backend.manage.
 * @author 陈方明  cfmmail@sina.com
 * @since 2019-01-04 21:13
 */
public class Test {

  /**
   * main方法.
   * @param args 参数
   */
  public static void main(String[] args) {
    InputStream is = Test.class.getClassLoader().getResourceAsStream("test.xml");
    String xml = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining(System.lineSeparator()));
    SAXReader reader = new SAXReader(false);
    StringReader stringReader = new StringReader(xml);
    Document doc = null;
    try {
      doc = reader.read(stringReader);
    } catch (DocumentException e) {
      System.err.println("加载Document异常");
      e.printStackTrace();
      return;
    }
    Element rootElement = doc.getRootElement();
    System.out.println(rootElement.asXML());
    @SuppressWarnings("unchecked") Iterator<Element> iterator = rootElement.elementIterator();
    while (iterator.hasNext()) {
      Element element = iterator.next();
      System.out.println(element.asXML());
    }
  }
}
