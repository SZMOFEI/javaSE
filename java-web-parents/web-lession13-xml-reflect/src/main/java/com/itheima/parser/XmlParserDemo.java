package com.itheima.parser;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class XmlParserDemo {
	
	//开发步骤
	@Test
	public void fun01() throws DocumentException{
		
		//1.创建解析器(SaxReader)
		SAXReader saxReader = new SAXReader();
		//2.读取xml,返回document对象
		Document document = saxReader.read("src/main/java/resources/books");
		//3.得到根元素
		Element rootElement = document.getRootElement();
		
		//......
	}
	
	//得到第一本书的名字:四十二章经
	
	@Test
	public void fun02() throws DocumentException{
		//1.创建解析器(SaxReader)
		SAXReader saxReader = new SAXReader();
		//2.读取xml
		Document document = saxReader.read("src/books.xml");
		//3.得到根元素
		Element rootElement = document.getRootElement();
		//4.得到根元素的所有孩子节点集合
		List<Element> list = rootElement.elements();
		//System.out.println(list.size());
		//获得第二的孩子,<book id="b2">
		Element bookEle = list.get(1);
		//获得第一本书对象的孩子节点集合
		List<Element> elements = bookEle.elements();
		
		Element nameEle = elements.get(0);
		
		System.out.println(nameEle.getText());
	
	}
	
	//获得apple的价格  18000
	
	@Test
	public void fun03() throws DocumentException{
		//1.创建解析器(SaxReader)
		SAXReader saxReader = new SAXReader();
		//2.读取xml
		Document document = saxReader.read("src/books.xml");
		//3.获得根元素
		Element rootElement = document.getRootElement();
		//获得apple节点
		Element appleEle = (Element) rootElement.elements().get(0);
		//获得price节点
		Element priceEle = (Element) appleEle.elements().get(1);
		
		System.out.println(priceEle.getText());
		
		
	}
	
	//获得第一本书的id属性值  b1
	@Test
	public void   fun04() throws DocumentException{
		//1.创建saxReader对象
		SAXReader saxReader = new SAXReader();
		//2.读取xml
		Document document = saxReader.read("src/books.xml");
		//3.获得根元素
		Element rootElement = document.getRootElement();
		//获得第一本书标签对象,
		Element bookEle = (Element) rootElement.elements().get(1);
		System.out.println(bookEle.attributeValue("a"));
		
	}
	

}
